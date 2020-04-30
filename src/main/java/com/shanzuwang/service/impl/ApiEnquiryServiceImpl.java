package com.shanzuwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.dao.dos.ApiEnquiryDO;
import com.shanzuwang.dao.mapper.ApiEnquiryDao;
import com.shanzuwang.service.IApiEnquiryService;
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
public class ApiEnquiryServiceImpl extends ServiceImpl<ApiEnquiryDao, ApiEnquiryDO> implements IApiEnquiryService {

    @Autowired
    IApiEnquiryService iApiEnquiryService;

    @Override
    public PageInfo<ApiEnquiryDO> ListApiEnquiry(Query query) {
        Page<ApiEnquiryDO> page=new Page<>(query.getPageNo(),query.getPageSize());
        LambdaQueryWrapper<ApiEnquiryDO> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(ApiEnquiryDO::getCreatedAt);
        page(page,lambdaQueryWrapper);
        List<ApiEnquiryDO> apiEnquiryDOS=page.getRecords();
        return new PageInfo<ApiEnquiryDO>(page.getTotal(),apiEnquiryDOS,query.getPageNo(),query.getPageSize(),page.getPages());
    }
}
