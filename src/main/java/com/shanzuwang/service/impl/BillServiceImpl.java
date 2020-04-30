package com.shanzuwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.bill.BillReq;
import com.shanzuwang.bean.req.bill.UserbillReq;
import com.shanzuwang.bean.req.product.FilterReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.dao.dos.ApiUserDO;
import com.shanzuwang.dao.dos.BillDO;
import com.shanzuwang.dao.mapper.ApiUserDao;
import com.shanzuwang.dao.mapper.BillDao;
import com.shanzuwang.service.IBillService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzuwang.service.IRiskInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class BillServiceImpl extends ServiceImpl<BillDao, BillDO> implements IBillService {

    @Autowired
    BillDao billDao;
    @Autowired
    ApiUserDao apiUserDao;
    @Autowired
    IBillService iBillService;
    @Autowired
    IRiskInfoService iRiskInfoService;

    @Override
    public PageInfo<UserbillReq> ListUserbill(Query query) {
        Page<ApiUserDO> page=new Page<>(query.getPageNo(),query.getPageSize());
        FilterReq filterReq= JSON.parseObject(query.getFilter(),FilterReq.class);
        UserbillReq userbillReq=new UserbillReq();
        userbillReq.setCompanyName(filterReq.getConditions()[0][2]);
        userbillReq.setPhone(filterReq.getConditions()[1][2]);
        List<UserbillReq> userbillReqs= apiUserDao.ListUserbill(page,userbillReq);
        List<UserbillReq> userbillReqsL= new ArrayList<>();
        for (UserbillReq userbillReq1:userbillReqs){
            LambdaQueryWrapper<BillDO> billWrapper=new LambdaQueryWrapper<>();
            billWrapper.eq(BillDO::getUserId,userbillReq1.getId());
            userbillReq1.setBills(iBillService.list(billWrapper));
            userbillReqsL.add(userbillReq1);
        }
        return new PageInfo<UserbillReq>(page.getTotal(),userbillReqsL,query.getPageNo(),query.getPageSize(),page.getPages());
    }

    @Override
    public List<BillReq> GetBill(String userid) {
        List<BillReq> billReqs=billDao.ListBillReq(userid);
        List<BillReq> billReqs1=new ArrayList<>();
        for (BillReq billReq:billReqs){
//            BillReq billReq1=new BillReq();
//            BeanUtils.copyProperties(billReq,billReq1);
            ArrayList<Object> leftPeriodsPrices  = JSON.parseObject(billReq.getLeftPeriodsPrices(), new TypeReference<ArrayList<Object>>(){});
            ArrayList<Object> periodsPrices  = JSON.parseObject(billReq.getPeriodsPrice(), new TypeReference<ArrayList<Object>>(){});
            billReq.setPeriodsPrice(null);
            if (leftPeriodsPrices.size()>0){
                billReq.setPeriodsPrices(leftPeriodsPrices);
            }
            billReq.setLeftPeriodsPrice(null);
            if (periodsPrices.size()>0){
                billReq.setPeriodsPrices(periodsPrices);
            }

            billReqs1.add(billReq);
        }
        return billReqs;
    }
}
