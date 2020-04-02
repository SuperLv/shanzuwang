package com.shanzuwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.dto.BrandDto;
import com.shanzuwang.bean.dto.SpuDto;
import com.shanzuwang.bean.req.BrandAddReq;
import com.shanzuwang.converter.BrandConverter;
import com.shanzuwang.converter.SpuConverter;
import com.shanzuwang.dao.dos.BrandDO;
import com.shanzuwang.dao.dos.SpuDO;
import com.shanzuwang.dao.mapper.BrandDao;
import com.shanzuwang.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
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
@Service
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandDO> implements IBrandService {

    /**
     * 分页查询品牌列表
     *
     * @param brandAddReq 查询参数
     * @return 品牌列表
     */
    @Override
    public PageInfo<BrandDto> getUserByPage(BrandAddReq brandAddReq) {
        Page<BrandDO> page = new Page<>(brandAddReq.getPageNo(),brandAddReq.getPageSize());
        LambdaQueryWrapper<BrandDO> queryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNotBlank(brandAddReq.getName())){
            queryWrapper.like(BrandDO::getName,brandAddReq.getName());
        }
        page(page,queryWrapper);
        List<BrandDO> brandDtos = page.getRecords();
        if(CollectionUtils.isEmpty(brandDtos)){
            return new PageInfo<>(0,brandAddReq.getPageNo(),brandAddReq.getPageSize(),0);
        }
        return new PageInfo<BrandDto>(page.getTotal(), BrandConverter.convertDOToDTO(brandDtos),
                brandAddReq.getPageNo(),brandAddReq.getPageSize(),page.getPages());
    }

    @Override
    public BrandDO getBranddo(Integer id) {
        LambdaQueryWrapper<BrandDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BrandDO::getId,id);
        BrandDO brandDO = getOne(queryWrapper);
        return brandDO;
    }
}
