package com.shanzuwang.service;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.dao.dos.ApiEnquiryDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface IApiEnquiryService extends IService<ApiEnquiryDO> {

    public PageInfo<ApiEnquiryDO> ListApiEnquiry(Query query);

}
