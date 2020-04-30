package com.shanzuwang.service;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.bill.BillReq;
import com.shanzuwang.bean.req.bill.UserbillReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.dao.dos.BillDO;
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
public interface IBillService extends IService<BillDO> {

    public PageInfo<UserbillReq> ListUserbill(Query query);

    public List<BillReq> GetBill(String userid);

}
