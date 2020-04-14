package com.shanzuwang.service;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.dto.BrandDTO;
import com.shanzuwang.bean.req.product.BrandQueryReq;
import com.shanzuwang.dao.dos.BrandDO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface IBrandService extends IService<BrandDO> {

    /**
     * 分页查询品牌列表
     * @param brandQueryReq 查询参数
     * @return 商品列表
     */
    PageInfo<BrandDTO> getUserByPage(BrandQueryReq brandQueryReq);

    BrandDO getBranddo(Integer id);

}
