package com.shanzuwang.service;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.dto.SpuDTO;
import com.shanzuwang.bean.req.product.SpuAddReq;
import com.shanzuwang.bean.req.product.SpuQueryRep;
import com.shanzuwang.bean.req.product.SpuReq;
import com.shanzuwang.dao.dos.SpuDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface ISpuService extends IService<SpuDO> {

    /**
     * 分页查询商品列表
     * @param spuReq 查询参数
     * @return 商品列表
     */
    PageInfo<SpuDTO> getSpuByPage(SpuQueryRep spuReq);

    SpuDTO getSpudo(Integer id);

    SpuDO updateSpudo(SpuReq spuReq);

    SpuDO addSpudo(SpuAddReq spuAddReq);
}
