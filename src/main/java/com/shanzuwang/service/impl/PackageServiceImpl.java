package com.shanzuwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.bill.Oreders;
import com.shanzuwang.bean.req.bill.PackagesAddReq;
import com.shanzuwang.bean.req.bill.PackagesReq;
import com.shanzuwang.bean.req.product.FilterReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.dao.dos.*;
import com.shanzuwang.dao.mapper.ApiUserDao;
import com.shanzuwang.dao.mapper.PackageDao;
import com.shanzuwang.dao.mapper.SpuDao;
import com.shanzuwang.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.org.apache.bcel.internal.generic.NEW;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Service
public class PackageServiceImpl extends ServiceImpl<PackageDao, PackageDO> implements IPackageService {
    @Autowired
    IApiUserService iApiUserService;
    @Autowired
    IRiskInfoService iRiskInfoService;
    @Autowired
    IOrderService iOrderService;
    @Autowired
    IPackageService iPackageService;
    @Autowired
    PackageDao packageDao;
    @Autowired
    ISkuService iSkuService;
    @Autowired
    ISpuService iSpuService;
    @Autowired
    ICartService iCartService;
    @Autowired
    IPeriodsService iPeriodsService;
    @Autowired
    ITransactionService iTransactionService;
    @Autowired
    ITransactionLogService iTransactionLogService;
    @Autowired
    IShoppingAddressService iShoppingAddressService;

    @Override
    public PageInfo<PackagesReq> ListOrders(Query query) {
        Page<PackagesReq> page=new Page<>(query.getPageNo(),query.getPageSize());
        FilterReq filterReq= JSON.parseObject(query.getFilter(),FilterReq.class);
        String[][] conditions=filterReq.getConditions();
        List<PackagesReq> orderDOS= new ArrayList<>();
        if (conditions[0][0].equals("status")){
           orderDOS=packageDao.ListPackagesReq(page,null,conditions[0][2]);
        }else {
            orderDOS=packageDao.ListPackagesReq(page,conditions[0][2],null);
        }

        List<PackagesReq> packagesReqs =new ArrayList<>();
        for (PackagesReq packagesReq:orderDOS){
            //订单信息
            LambdaQueryWrapper<OrderDO> ordersWrapper=new LambdaQueryWrapper<>();
            ordersWrapper.eq(OrderDO::getPackageId,packagesReq.getId());
            List<OrderDO> orderDOS1=iOrderService.list(ordersWrapper);
            List<Oreders> orderDOS2=new ArrayList<>();
            for (OrderDO orderDO1:orderDOS1){
                Oreders oreders=new Oreders();
                oreders.setId(orderDO1.getId());
                oreders.setPrice(orderDO1.getPrice());
                orderDOS2.add(oreders);
            }
            packagesReq.setOrders(orderDOS2);
            packagesReqs.add(packagesReq);
        }
        return new PageInfo<PackagesReq>(page.getTotal(), packagesReqs,query.getPageNo(),query.getPageSize(),page.getPages());
    }

    @Override
    public PackagesReq GetPackages(Integer id) {
        PackagesReq packagesReq=packageDao.GetPackagesReq(id);
        LambdaQueryWrapper<OrderDO> ordersWrapper=new LambdaQueryWrapper<>();
        ordersWrapper.eq(OrderDO::getPackageId,packagesReq.getId());
        List<OrderDO> orderDOS=iOrderService.list(ordersWrapper);
        List<Oreders> oredersList=new ArrayList<>();
        for (OrderDO orderDO:orderDOS){
            Oreders oreders=new Oreders();
            BeanUtils.copyProperties(orderDO,oreders);
            ArrayList<Object> leftPeriodsPrices  = JSON.parseObject(orderDO.getLeftPeriodsPrice(), new TypeReference<ArrayList<Object>>(){});
            ArrayList<Object> periodsPrices  = JSON.parseObject(orderDO.getPeriodsPrice(), new TypeReference<ArrayList<Object>>(){});
            if (leftPeriodsPrices.size()>0){
                oreders.setLeftPeriodsPrice(leftPeriodsPrices);
            }
            if (periodsPrices.size()>0){
                oreders.setPeriodsPrice(periodsPrices);
            }
           SkuDO skuDO= iSkuService.getById(orderDO.getProductId());
            oreders.setSkuName(skuDO.getName());
            SpuDO spuDao=iSpuService.getById(skuDO.getSpuId());
            oreders.setSpuName(spuDao.getName());
            oreders.setThumb(skuDO.getThumb());
            oredersList.add(oreders);
        }
        packagesReq.setOrders(oredersList);
        return packagesReq;
    }

    @Override
    public PackagesReq AddPackages(PackagesAddReq packagesAddReq) {
        //iShoppingAddressService
        ShoppingAddressDO shoppingAddressDO=iShoppingAddressService.getById(packagesAddReq.getAddressId());
        Integer[] Cartids=packagesAddReq.getCartIds();
        String userId=shoppingAddressDO.getUserId();
        //获取免押金额
        LambdaQueryWrapper<RiskInfoDO> riskInfoWrapper=new LambdaQueryWrapper<>();
        riskInfoWrapper.eq(RiskInfoDO::getUserId,userId);
        RiskInfoDO riskInfoDO=iRiskInfoService.getOne(riskInfoWrapper);
        Float  risk_deposit=riskInfoDO.getDeposit()-riskInfoDO.getDepositUsed();  //当前可用额度
        Float  risk_tempDeposit=riskInfoDO.getTempDeposit()-riskInfoDO.getTempDepositUsed(); //当前可用临时额度
        Float deposit=Float.valueOf(0); //sku总押金
        Float price=Float.valueOf(0);  //单期金额

        //packages
        PackageDO packageDO=new PackageDO();
        packageDO.setUserId(userId);
        String address=shoppingAddressDO.getProvince()+shoppingAddressDO.getCity()+shoppingAddressDO.getArea()+shoppingAddressDO.getAddress();
        packageDO.setAddress(address);
        packageDO.setCompany(riskInfoDO.getCompanyName());
        packageDO.setName(shoppingAddressDO.getName());
        packageDO.setPhone(shoppingAddressDO.getPhoneNum());
        List<OrderDO> orderDOS=new ArrayList<>();
        Calculate calculate=new Calculate();
        calculate.setAddress(shoppingAddressDO);
        calculate.setUserId(userId);
        Float  risk_deposit1=risk_deposit;  //当前可用额度
        Float  risk_tempDeposit1=risk_tempDeposit;
        if (Cartids!=null) {
           for (Integer cid : Cartids) {
              CartDO cartDO = iCartService.getById(cid);
              calculate.setRisk_deposit1(risk_deposit1);
              calculate.setRisk_tempDeposit1(risk_tempDeposit1);
              calculate.setDeposit(deposit);
              calculate.setPrice(price);
              Calculate calculate1 = Orders(cartDO, calculate);
              price = calculate1.getPrice();
              deposit = calculate1.getDeposit();
              risk_deposit1 = calculate1.getRisk_deposit1();
              risk_tempDeposit1 = calculate1.getRisk_tempDeposit1();
              orderDOS.add(calculate1.getOrderDO());
                }
             }else {
              List<CartDO> cartDOS=  packagesAddReq.getOrders();
              for (CartDO cartDO:cartDOS){
                  calculate.setRisk_deposit1(risk_deposit1);
                  calculate.setRisk_tempDeposit1(risk_tempDeposit1);
                  calculate.setDeposit(deposit);
                  calculate.setPrice(price);
                  Calculate calculate1 = Orders(cartDO, calculate);
                  price = calculate1.getPrice();
                  deposit = calculate1.getDeposit();
                  risk_deposit1 = calculate1.getRisk_deposit1();
                  risk_tempDeposit1 = calculate1.getRisk_tempDeposit1();
                  orderDOS.add(calculate1.getOrderDO());
              }
            }
            Float deposits=(risk_tempDeposit+risk_deposit)-deposit;
            if (deposits>=0){
                packageDO.setDeposit(Float.valueOf(0));
                deposits= Float.valueOf(0);
            }else {
                packageDO.setDeposit(deposits*-1);
                deposits=Float.valueOf(deposits*-1);
            }
            packageDO.setPrice(String.valueOf(price+deposits));
            packageDO.setComment(packagesAddReq.getComment());
            packageDO.setStatus("new");
            packageDO.setInvoiceStatus("new");
            packageDO.setType("online");
            packageDO.setUpdatedAt(new Date());
            iPackageService.save(packageDO);
            for (OrderDO o:orderDOS){
                o.setPackageId(packageDO.getId());
                iOrderService.save(o);
            }
            //transaction
            TransactionDO transactionDO=new TransactionDO();
            transactionDO.setAmount((int) ((price+deposits)*100));
            transactionDO.setUserId(userId);
            transactionDO.setPayType("alipay_web");
            transactionDO.setMemo(null);
            transactionDO.setObjectType("package");
            transactionDO.setObjectId(packageDO.getId());
            transactionDO.setStatus("new");
            transactionDO.setTitle("订单支付");
            transactionDO.setDetail("订单支付");
            transactionDO.setIp("127.0.0.1");
            TransactionLogDO transactionLogDO=new TransactionLogDO();
            BeanUtils.copyProperties(transactionDO,transactionLogDO);
            iTransactionService.save(transactionDO);
            transactionLogDO.setTransactionId(transactionDO.getId());
            iTransactionLogService.save(transactionLogDO);
        return null;
    }

    @Override
    public PackagesReq UpdatePackages(PackageDO packageDO) {
        iPackageService.updateById(packageDO);
        PackageDO packageDO1=iPackageService.getById(packageDO.getId());
        PackagesReq packagesReq=new PackagesReq();
        BeanUtils.copyProperties(packageDO1,packagesReq);
        return packagesReq;
    }

    public static String PeriodsPrice(Integer  rentDuration,Float price)
    {
        String periodsPrice="";
        for (int i=0;i<rentDuration;i++){
            if(i!=rentDuration-1){
                periodsPrice=periodsPrice+price+", ";
            }else {
                periodsPrice=periodsPrice+price;
            }
        }
        periodsPrice="["+periodsPrice+"]";
        return periodsPrice;
    }

    public  Calculate  Orders(CartDO cartDO,Calculate calculate)
    {
        Calculate calculates=new Calculate();
        String address=calculate.getAddress().getProvince()+calculate.getAddress().getCity()+calculate.getAddress().getArea()+calculate.getAddress().getAddress();
        OrderDO orderDO=new OrderDO();
        orderDO.setUserId(calculate.getUserId());
        SkuDO skuDO=iSkuService.getById(cartDO.getProductId());
        calculates.setDeposit(calculate.deposit+skuDO.getDeposit());
        PeriodsDO periodsDO=iPeriodsService.getById(cartDO.getPeriodId());
        orderDO.setProductId(skuDO.getId());
        orderDO.setProductNum(cartDO.getProductNum());
        //获取租赁时长
        Integer  rentDuration=  periodsDO.getRentDuration();
        String payType=periodsDO.getPayType();
        Calendar calendar = new GregorianCalendar();
        Date startDate = cartDO.getStartDate();
        calendar.setTime(startDate);
        if (periodsDO.getType().equals("month")){
            calendar.add(calendar.MONTH, periodsDO.getRentDuration());//把日期往后增加一个月.整数往后推,负数往前移动
        }else {
            calendar.add(calendar.DATE,periodsDO.getRentDuration());//把日期往后增加一天.整数往后推,负数往前移动
        }

        if (payType.equals("quarter")){
            rentDuration=rentDuration/3;
        }else if (payType.equals("semi_year")){
            rentDuration=rentDuration/6;
        }else if (payType.equals("year")){
            rentDuration=rentDuration/12;
        }else if (payType.equals("once")){
            rentDuration=1;
        }
        //获取总金额
        Float sumPrice=periodsDO.getUnitPrice()*periodsDO.getRentDuration()*cartDO.getProductNum();
        orderDO.setPrice(String.valueOf(sumPrice));
        orderDO.setUnitPrice(String.valueOf(sumPrice/rentDuration));
        calculates.setPrice(calculate.getPrice()+(sumPrice/rentDuration));
        Float tempdeposits=calculate.getRisk_tempDeposit1()-skuDO.getDeposit();
        if (tempdeposits>=0){
            orderDO.setTempDepositUsed(skuDO.getDeposit());
            calculates.setRisk_tempDeposit1(tempdeposits);
        }else {
            orderDO.setTempDepositUsed(calculate.getRisk_tempDeposit1());
            calculates.setRisk_tempDeposit1(Float.valueOf(0));
            Float  deposits=calculate.getRisk_deposit1()+tempdeposits;
            if (deposits>=0){
                calculates.setRisk_deposit1(deposits);
                orderDO.setDepositFree(BigDecimal.valueOf(tempdeposits*-1));
            }else {
                calculates.setRisk_deposit1(Float.valueOf(0));
                orderDO.setDeposit(BigDecimal.valueOf(deposits*-1));
                orderDO.setDepositFree(BigDecimal.valueOf(calculate.getRisk_deposit1()));
            }
        }
        orderDO.setAddress(address);
        orderDO.setName(calculate.getAddress().getName());
        orderDO.setPhone(calculate.getAddress().getPhoneNum());
        orderDO.setStartDate(startDate);
        orderDO.setEndDate(calendar.getTime());
        orderDO.setBillType(periodsDO.getPayType());
        orderDO.setRentType(periodsDO.getType());
        orderDO.setRentDuration(periodsDO.getRentDuration());
        orderDO.setPeriodsPrice(PeriodsPrice(rentDuration,sumPrice/rentDuration));
        orderDO.setLeftPeriodsPrice(PeriodsPrice(rentDuration,sumPrice/rentDuration));
        orderDO.setStatus("new");
        orderDO.setUpdatedAt(new Date());
        calculates.setOrderDO(orderDO);
        return calculates;
    }

    @Data
    private class  Calculate
    {
        String  userId;

        OrderDO orderDO;

        Float  risk_deposit1;

        Float  risk_tempDeposit1;

        Float deposit; //sku总押金

        Float price;  //单期金额

        ShoppingAddressDO address;
    }

    public static void main(String[] args) {

    }
}
