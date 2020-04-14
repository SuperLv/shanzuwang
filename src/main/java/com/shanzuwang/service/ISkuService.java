package com.shanzuwang.service;

import com.shanzuwang.bean.req.product.SkuQueryReq;
import com.shanzuwang.dao.dos.SkuDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface ISkuService extends IService<SkuDO> {

    /**
     * sku列表
     * */
    List<SkuQueryReq> ListSkus(Integer spuId);

    SkuQueryReq  getSku(Integer spuid, Integer skuid);

    SkuQueryReq  UpdateSku(Integer spuid, Integer skuid,SkuQueryReq skuQueryReq);

    SkuQueryReq  AddSku(Integer spuid,SkuQueryReq skuQueryReq);

}
