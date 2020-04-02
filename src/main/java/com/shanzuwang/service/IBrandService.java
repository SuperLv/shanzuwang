package com.shanzuwang.service;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.dto.BrandDto;
import com.shanzuwang.bean.dto.SpuDto;
import com.shanzuwang.bean.req.BrandAddReq;
import com.shanzuwang.bean.req.SpuReq;
import com.shanzuwang.dao.dos.BrandDO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shanzuwang.dao.dos.SpuDO;

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
     * @param brandAddReq 查询参数
     * @return 商品列表
     */
    PageInfo<BrandDto> getUserByPage(BrandAddReq brandAddReq);

    BrandDO getBranddo(Integer id);

}
