package com.shanzuwang.service;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.user.RiskReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.dao.dos.RiskInfoDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface IRiskInfoService extends IService<RiskInfoDO> {

    public PageInfo<RiskReq> ListRisk(Query query);

    public RiskReq getRisk(Integer id);

    public RiskReq UpdateRisk(Integer id,RiskReq riskReq);

    public RiskReq AddRisk(RiskReq riskReq);
}
