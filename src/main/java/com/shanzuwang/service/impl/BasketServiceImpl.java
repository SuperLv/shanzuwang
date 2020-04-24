package com.shanzuwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.website.BasketReq;
import com.shanzuwang.bean.req.website.BasketsAddReq;
import com.shanzuwang.bean.req.product.SkuQueryReq;
import com.shanzuwang.dao.dos.BasketDO;
import com.shanzuwang.dao.dos.PeriodsDO;
import com.shanzuwang.dao.dos.SkuDO;
import com.shanzuwang.dao.mapper.BasketDao;
import com.shanzuwang.service.IBasketService;
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
public class BasketServiceImpl extends ServiceImpl<BasketDao, BasketDO> implements IBasketService {

    @Autowired
    IBasketService iBasketService;
    @Autowired
    IPeriodsService iPeriodsService;
    @Autowired
    ISkuService iSkuService;

    @Override
    public PageInfo<BasketReq> ListBasket(String type) {
        LambdaQueryWrapper<BasketDO> basketWeapper=new LambdaQueryWrapper<>();
        basketWeapper.eq(BasketDO::getType,type);
        Page<BasketDO> page=new Page<>();
        page(page,basketWeapper);
        List<BasketDO> basketDOS=page.getRecords();
        List<BasketReq> basketReqs=new ArrayList<>();
        for (BasketDO basketDO:basketDOS){
            BasketReq basketReq=new BasketReq();
            BeanUtils.copyProperties(basketDO,basketReq);

            //添加分期计划
            LambdaQueryWrapper<PeriodsDO> periodsWeapper=new LambdaQueryWrapper<>();
            periodsWeapper.eq(PeriodsDO::getSkuId,basketDO.getProductId());
            List<PeriodsDO> periodsDO=iPeriodsService.list(periodsWeapper);
            basketReq.setPeriod(periodsDO);

            //添加sku
            SkuQueryReq skuQueryReq=new SkuQueryReq();
            SkuDO skuDO=iSkuService.getById(basketDO.getProductId());
            BeanUtils.copyProperties(skuDO,skuQueryReq);
            String image=skuDO.getImages().replaceAll(" ","");
            skuQueryReq.setSimages(null);
            skuQueryReq.setImages(SkuServiceImpl.Strings(image));
            //eplatform
            String eplatfor=skuDO.getEPlatform().replaceAll(" ","");
            skuQueryReq.setEPlatform(null);
            skuQueryReq.setSePlatform(SkuServiceImpl.Strings(eplatfor));
            basketReq.setProduct(skuQueryReq);
            basketReqs.add(basketReq);
        }
        return new PageInfo<BasketReq>(page.getTotal(),basketReqs,1,5000,page.getPages());
    }

    @Override
    public BasketDO AddBasket(BasketsAddReq basketsAddReq) {
        Integer productid =basketsAddReq.getProductId()[0];
        BasketDO basketDO=new BasketDO();
        basketDO.setProductId(productid);
        basketDO.setType(basketsAddReq.getType());
        iBasketService.save(basketDO);
        return basketDO;
    }
}
