package com.shanzuwang.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shanzuwang.bean.req.bill.CartReq;
import com.shanzuwang.bean.req.product.SkuQueryReq;
import com.shanzuwang.dao.dos.CartDO;
import com.shanzuwang.dao.dos.PeriodsDO;
import com.shanzuwang.dao.dos.SkuDO;
import com.shanzuwang.dao.mapper.CartDao;
import com.shanzuwang.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzuwang.service.IPeriodsService;
import com.shanzuwang.service.ISkuService;
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
public class CartServiceImpl extends ServiceImpl<CartDao, CartDO> implements ICartService {

    @Autowired
    ISkuService iSkuService;
    @Autowired
    ICartService iCartService;
    @Autowired
    IPeriodsService iPeriodsService;

    @Override
    public List<CartReq> ListCarS(String userId) {
        LambdaQueryWrapper<CartDO> cartWrapper=new LambdaQueryWrapper<>();
        cartWrapper.eq(CartDO::getUserId,userId);
        List<CartDO> cartDOS=iCartService.list(cartWrapper);
        List<CartReq> cartReqs=new ArrayList<>();
        for (CartDO cartDO:cartDOS){
            CartReq cartReq=new CartReq();
            BeanUtils.copyProperties(cartDO,cartReq);

            //period
            PeriodsDO periodsDO=iPeriodsService.getById(cartDO.getPeriodId());
            cartReq.setPeriod(periodsDO);

            //product
            SkuDO skuDO=iSkuService.getById(cartDO.getProductId());
            SkuQueryReq skuQueryReq=new SkuQueryReq();
            BeanUtils.copyProperties(skuDO,skuQueryReq);
            skuQueryReq.setImages(null);
            skuQueryReq.setSimages(SkuServiceImpl.Strings(skuDO.getImages()));
            skuQueryReq.setSePlatform(SkuServiceImpl.Strings(skuDO.getEPlatform()));
            skuQueryReq.setEPlatform(null);
            cartReq.setProduct(skuQueryReq);
            cartReqs.add(cartReq);
        }
        return cartReqs;
    }

    @Override
    public CartDO AddCart(CartDO cartDO) {
        LambdaQueryWrapper<CartDO> cartWrapper=new LambdaQueryWrapper<>();
        cartWrapper.eq(CartDO::getUserId,cartDO.getUserId());
        cartWrapper.eq(CartDO::getPeriodId,cartDO.getPeriodId());
        cartWrapper.eq(CartDO::getProductId,cartDO.getProductId());
        CartDO cartDO1=iCartService.getOne(cartWrapper);
        if (cartDO1!=null){
            cartDO1.setProductNum(cartDO.getProductNum()+cartDO1.getProductNum());
            iCartService.updateById(cartDO1);
            return cartDO1;
        }else {
            iCartService.save(cartDO);
            return cartDO;
        }
    }
}
