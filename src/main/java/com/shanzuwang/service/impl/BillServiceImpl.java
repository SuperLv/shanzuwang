package com.shanzuwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.bill.BillReq;
import com.shanzuwang.bean.req.bill.BillsReq;
import com.shanzuwang.bean.req.bill.UserbillReq;
import com.shanzuwang.bean.req.product.FilterReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.dao.dos.*;
import com.shanzuwang.dao.mapper.ApiUserDao;
import com.shanzuwang.dao.mapper.BillDao;
import com.shanzuwang.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
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
@Transactional
public class BillServiceImpl extends ServiceImpl<BillDao, BillDO> implements IBillService {

    @Autowired
    BillDao billDao;
    @Autowired
    ApiUserDao apiUserDao;
    @Autowired
    IBillService iBillService;
    @Autowired
    IRiskInfoService iRiskInfoService;
    @Autowired
    IBillPackageService iBillPackageService;
    @Autowired
    ITransactionService iTransactionService;
    @Autowired
    ITransactionLogService iTransactionLogService;

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

    @SneakyThrows
    @Override
    public void CancelAccount(BillsReq billsReq) {
        String detail=billsReq.getDetail();
        String outSerialNo =billsReq.getOutSerialNo();
        // 获取IP地址
        String ip = InetAddress.getLocalHost().getHostAddress();
        for (Integer id:billsReq.billIds){
            BillDO billDO=iBillService.getById(id);

            //bill_package
            BillPackageDO billPackageDO=new BillPackageDO();
            billPackageDO.setPrice(billDO.getPrice());
            billPackageDO.setStatus("paid");
            billPackageDO.setUserId(billDO.getUserId());
            iBillPackageService.save(billPackageDO);

            //billdo
            BillDO billDO1=new BillDO();
            billDO1.setId(id);
            billDO1.setStatus("paid");
            billDO1.setPayId(billPackageDO.getId());
            billDO1.setUpdatedAt(new Date());
            iBillService.updateById(billDO1);

            //transaction
            TransactionDO transactionDO=new TransactionDO();
            transactionDO.setAmount(billDO.getPrice().intValue());
            transactionDO.setUserId(billDO.getUserId());
            transactionDO.setPayType("offline");
            transactionDO.setObjectType("bill_package");
            transactionDO.setObjectId(billPackageDO.getId());
            transactionDO.setStatus("finished");
            transactionDO.setOriginObjectId(Integer.valueOf(outSerialNo));
            transactionDO.setTitle("线下汇款");
            transactionDO.setDetail("线下汇款");
            transactionDO.setIp(ip);
            TransactionLogDO transactionLogDO=new TransactionLogDO();
            BeanUtils.copyProperties(transactionDO,transactionLogDO);
            iTransactionService.save(transactionDO);
            transactionLogDO.setTransactionId(transactionDO.getId());
            transactionLogDO.setMemo(detail);
            iTransactionLogService.save(transactionLogDO);
        }
    }

    public static void main(String[] args) throws UnknownHostException {
        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }
}
