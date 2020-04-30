package com.shanzuwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.bill.Oreders;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PackageServiceImpl extends ServiceImpl<PackageDao, PackageDO> implements IPackageService {
    @Autowired
    IApiUserService iApiUserService;
    @Autowired
    IRiskInfoService iRiskInfoService;
    @Autowired
    IOrderService iOrderService;
    @Autowired
    PackageDao packageDao;
    @Autowired
    ISkuService iSkuService;
    @Autowired
    ISpuService iSpuService;

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

}
