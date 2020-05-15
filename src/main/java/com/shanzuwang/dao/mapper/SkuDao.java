package com.shanzuwang.dao.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.product.SkuQueryReq;
import com.shanzuwang.dao.dos.SkuDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface SkuDao extends BaseMapper<SkuDO> {

    public  List<SkuQueryReq> ListSkus(Page<SkuDO> pageInfo, @Param("sku")SkuQueryReq skuQueryReq);



}
