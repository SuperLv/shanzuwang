package com.shanzuwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shanzuwang.bean.dto.ProductPropertyDTO;
import com.shanzuwang.dao.dos.BrandDO;
import com.shanzuwang.dao.dos.ProductPropertyDO;
import com.shanzuwang.dao.dos.UserDO;
import com.shanzuwang.dao.mapper.ProductPropertyDao;
import com.shanzuwang.service.IProductPropertyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Service
public class ProductPropertyServiceImpl extends ServiceImpl<ProductPropertyDao, ProductPropertyDO> implements IProductPropertyService {

    @Autowired
    ProductPropertyDao productPropertyDao;

    @Override
    public List<ProductPropertyDTO> findList(Integer actorId) {
        LambdaQueryWrapper<ProductPropertyDO>  queryWrapper=new LambdaQueryWrapper<>();
        if (actorId!=null){
            queryWrapper.eq(ProductPropertyDO::getActorId,actorId);
        }
        List<ProductPropertyDTO> productPropertyDTOS =productPropertyDao.findList(queryWrapper);
        BrandDO brandDO= JSON.parseObject(productPropertyDTOS.get(0).getVal(),BrandDO.class);
        productPropertyDTOS.get(0).setValue(brandDO);
        productPropertyDTOS.get(0).setVal(null);
        return productPropertyDTOS;
    }
}
