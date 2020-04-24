package com.shanzuwang.service;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.website.BasketReq;
import com.shanzuwang.bean.req.website.BasketsAddReq;
import com.shanzuwang.dao.dos.BasketDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface IBasketService extends IService<BasketDO> {

    public PageInfo<BasketReq> ListBasket(String type);

    public BasketDO AddBasket(BasketsAddReq basketsAddReq);

}
