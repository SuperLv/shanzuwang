package com.shanzuwang.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.extra.MarkstServiceReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.bean.req.product.SkuQueryReq;
import com.shanzuwang.dao.dos.MarketServiceDO;
import com.shanzuwang.dao.dos.SkuDO;
import com.shanzuwang.dao.mapper.MarketServiceDao;
import com.shanzuwang.service.IMarketServiceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzuwang.service.ISkuService;
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
public class MarketServiceServiceImpl extends ServiceImpl<MarketServiceDao, MarketServiceDO> implements IMarketServiceService {

    @Autowired
    IMarketServiceService iMarketServiceService;
    @Autowired
    ISkuService iSkuService;

    @Override
    public PageInfo<MarkstServiceReq> ListMarkst(Query query) {
        Page<MarketServiceDO> page=new Page<>(query.getPageNo(),query.getPageSize());
        LambdaQueryWrapper markstWrapper=new LambdaQueryWrapper();
        page(page,markstWrapper);
        List<MarketServiceDO> marketServiceDOS=page.getRecords();
        List<MarkstServiceReq> markstServiceReqs=new ArrayList<>();
        for (MarketServiceDO marketServiceDO:marketServiceDOS){
            MarkstServiceReq markstServiceReq=new MarkstServiceReq();
            BeanUtils.copyProperties(marketServiceDO,markstServiceReq);

            //sku
            SkuDO skuDO=iSkuService.getById(marketServiceDO.getProductId());
            SkuQueryReq skuQueryReq=new SkuQueryReq();
            BeanUtils.copyProperties(skuDO,skuQueryReq);
            String image=skuDO.getImages().replaceAll(" ","");
            skuQueryReq.setSimages(null);
            skuQueryReq.setSimages(SkuServiceImpl.Strings(image));
            //eplatform
            String eplatfor=skuDO.getEPlatform().replaceAll(" ","");
            skuQueryReq.setEPlatform(null);
            skuQueryReq.setSePlatform(SkuServiceImpl.Strings(eplatfor));
            markstServiceReq.setSkuQueryReq(skuQueryReq);
            markstServiceReqs.add(markstServiceReq);
        }
        return new PageInfo<MarkstServiceReq>(page.getTotal(),markstServiceReqs,query.getPageNo(),query.getPageSize(),page.getPages());
    }
}
