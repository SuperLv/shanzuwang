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
import com.shanzuwang.bean.req.product.SkuQueryReq;
import com.shanzuwang.dao.dos.ApiUserDO;
import com.shanzuwang.dao.dos.OrderDO;
import com.shanzuwang.dao.dos.SkuDO;
import com.shanzuwang.dao.dos.UserDO;
import com.shanzuwang.dao.mapper.OrderDao;
import com.shanzuwang.service.IApiUserService;
import com.shanzuwang.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzuwang.service.ISkuService;
import com.shanzuwang.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderDO> implements IOrderService {

    @Autowired
    IOrderService iOrderService;
    @Autowired
    ISkuService iSkuService;
    @Autowired
    IApiUserService iApiUserService;

    @Override
    public PageInfo<Oreders> ListListOreders(Query query) {
        Page<OrderDO> page=new Page<>(query.getPageNo(),query.getPageSize());
        LambdaQueryWrapper<OrderDO> orderWrapper=new LambdaQueryWrapper<>();
        FilterReq filterReq= JSON.parseObject(query.getFilter(),FilterReq.class);
        orderWrapper.eq(OrderDO::getPackageId,filterReq.getConditions()[0][2]);
        page(page,orderWrapper);
        List<OrderDO> orderDOS=page.getRecords();
        List<Oreders> oreders=new ArrayList<>();
        for (OrderDO orderDO:orderDOS){

            oreders.add(ReturnOreders(orderDO));
        }

        return new PageInfo<Oreders>(page.getTotal(),oreders,query.getPageNo(),query.getPageSize(),page.getPages());
    }

    @Override
    public Oreders GetOreders(Integer id) {
        return ReturnOreders(iOrderService.getById(id));
    }

    public  Oreders ReturnOreders(OrderDO orderDO)
    {
        Oreders oreders1=new Oreders();
        BeanUtils.copyProperties(orderDO,oreders1);
        ArrayList<Object> leftPeriodsPrices  = JSON.parseObject(orderDO.getLeftPeriodsPrice(), new TypeReference<ArrayList<Object>>(){});
        ArrayList<Object> periodsPrices  = JSON.parseObject(orderDO.getPeriodsPrice(), new TypeReference<ArrayList<Object>>(){});
        oreders1.setLeftPeriodsPrice(leftPeriodsPrices);
        oreders1.setPeriodsPrice(periodsPrices);
        //sku
        SkuDO skuDO=iSkuService.getById(orderDO.getProductId());
        SkuQueryReq skuQueryReq=new SkuQueryReq();
        BeanUtils.copyProperties(skuDO,skuQueryReq);
        String image=skuDO.getImages().replaceAll(" ","");
        skuQueryReq.setSimages(null);
        skuQueryReq.setSimages(SkuServiceImpl.Strings(image));
        //eplatform
        String eplatfor=skuDO.getEPlatform().replaceAll(" ","");
        skuQueryReq.setEPlatform(null);
        skuQueryReq.setSePlatform(SkuServiceImpl.Strings(eplatfor));
        oreders1.setSkuQueryReq(skuQueryReq);
        ApiUserDO apiUserDO=iApiUserService.getById(orderDO.getUserId());
        oreders1.setApiUserDO(apiUserDO);
        return  oreders1;
    }
}
