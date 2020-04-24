package com.shanzuwang.service;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.extra.MarkstServiceReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.dao.dos.MarketServiceDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface IMarketServiceService extends IService<MarketServiceDO> {

     public PageInfo<MarkstServiceReq> ListMarkst(Query query);
}
