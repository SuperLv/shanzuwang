package com.shanzuwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.dto.SpuDTO;
import com.shanzuwang.bean.req.product.SpuAddReq;
import com.shanzuwang.bean.req.product.SpuQueryRep;
import com.shanzuwang.bean.req.product.SpuReq;
import com.shanzuwang.converter.SpuConverter;
import com.shanzuwang.dao.dos.CategoryDO;
import com.shanzuwang.dao.dos.ProductPropertyDO;
import com.shanzuwang.dao.dos.SpuDO;
import com.shanzuwang.dao.mapper.SpuDao;
import com.shanzuwang.service.ICategoryService;
import com.shanzuwang.service.IProductPropertyService;
import com.shanzuwang.service.ISpuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Slf4j
@Service
public class SpuServiceImpl extends ServiceImpl<SpuDao, SpuDO> implements ISpuService {

     @Autowired
     SpuDao spuDao;
     @Autowired
     IProductPropertyService iProductPropertyService;
     @Autowired
     ISpuService iSpuService;
     @Autowired
     ICategoryService iCategoryService;

    /**
     * 分页查询商品列表
     *
     * @param spuReq 查询参数
     * @return 商品列表
     */
    @Override
    public PageInfo<SpuDTO> getSpuByPage(SpuQueryRep spuReq) {
        Page<SpuDO> page = new Page<>(spuReq.getPageNo(),spuReq.getPageSize());
        List<SpuDTO> spuDOList = spuDao.findList(page,spuReq);
        if(CollectionUtils.isEmpty(spuDOList)){
            return new PageInfo<>(0,spuReq.getPageNo(),spuReq.getPageSize(),0);
        }
        return new PageInfo<>(page.getTotal(), spuDOList,
                spuReq.getPageNo(),spuReq.getPageSize(),page.getPages());
    }

    @Override
    public SpuDTO getSpudo(Integer id) {
        LambdaQueryWrapper<SpuDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SpuDO::getId,id);
        SpuDTO spuDTO= SpuConverter.convertDOToDTO(getOne(queryWrapper));
        LambdaQueryWrapper<CategoryDO> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(CategoryDO::getId,spuDTO.getCategoryId());
        CategoryDO categoryDO=iCategoryService.getOne(queryWrapper1);
        String path=categoryDO.getPath();
        int numb= Integer.parseInt(path.substring(1,path.length()-1));
        int[] ints = new int[1];
        ints[0]=numb;
        spuDTO.setPath(ints);
        spuDTO.setProperties(iProductPropertyService.findList(id));
        return spuDTO;
    }

    @Override
    public SpuDO updateSpudo(SpuReq spuReq) {
        try {
            SpuDO spuDO=new SpuDO();
            spuDO.setId(spuReq.getId());
            spuDO.setName(spuReq.getName());
            spuDO.setStatus(spuDO.getStatus());
            spuDao.updateById(spuDO);

            //修改属性绑定表
            ProductPropertyDO productPropertyDO=new ProductPropertyDO();
            productPropertyDO.setActorId(spuReq.getId());
            productPropertyDO.setTargetId(spuReq.getProductPropertyDOReq().get(0).getId());
            productPropertyDO.setValue(JSON.toJSONString(spuReq.getProductPropertyDOReq().get(0).getValue()));

            UpdateWrapper<ProductPropertyDO> updateWrapper=new UpdateWrapper<>();
            updateWrapper.eq("actor_id",spuReq.getId());
            updateWrapper.eq("target_id",spuReq.getProductPropertyDOReq().get(0).getId());
            iProductPropertyService.update(productPropertyDO,updateWrapper);
            return  spuDao.selectById(spuReq.getId());
        }catch (Exception e)
        {
            return null;
        }
    }

    @Override
    public SpuDO addSpudo(SpuAddReq spuAddReq) {
        SpuDO spuDO=new SpuDO();
        spuDO.setStatus(spuAddReq.getStatus());
        spuDO.setName(spuAddReq.getName());
        spuDO.setCategoryId(spuAddReq.getCategoryId());
        iSpuService.save(spuDO);

        ProductPropertyDO productPropertyDO=new ProductPropertyDO();
        String value=JSON.toJSONString(spuAddReq.getProductPropertyDOReq().get(0).getValue());
        productPropertyDO.setValue(value);
        productPropertyDO.setActorId(spuDO.getId());
        productPropertyDO.setTargetId(spuAddReq.getProductPropertyDOReq().get(0).getId());
        iProductPropertyService.save(productPropertyDO);
        return spuDO;
    }


}
