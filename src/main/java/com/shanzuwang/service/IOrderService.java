package com.shanzuwang.service;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.bill.Oreders;
import com.shanzuwang.bean.req.bill.PackagesReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.dao.dos.OrderDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface IOrderService extends IService<OrderDO> {

    public PageInfo<Oreders> ListListOreders(Query query);

    public Oreders  GetOreders(Integer id);

}
