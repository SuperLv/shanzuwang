package com.shanzuwang.service;

import com.shanzuwang.bean.req.bill.CartReq;
import com.shanzuwang.dao.dos.CartDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface ICartService extends IService<CartDO> {

    List<CartReq> ListCarS(String userId);

    CartDO AddCart(CartDO cartDO);

}
