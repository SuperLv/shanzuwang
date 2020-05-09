package com.shanzuwang.dao.mapper;

import com.shanzuwang.bean.req.bill.BillReq;
import com.shanzuwang.bean.req.bill.StatiSticsReq;
import com.shanzuwang.dao.dos.BillDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface BillDao extends BaseMapper<BillDO> {

    public List<BillReq> ListBillReq(String userid);

    public StatiSticsReq StatiStics();

}
