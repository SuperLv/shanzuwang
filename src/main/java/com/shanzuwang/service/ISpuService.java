package com.shanzuwang.service;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.dto.SpuDto;
import com.shanzuwang.bean.dto.UserDTO;
import com.shanzuwang.bean.req.SpuReq;
import com.shanzuwang.bean.req.UserQueryReq;
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
    PageInfo<SpuDto> getUserByPage(SpuReq spuReq);

    SpuDO getSpudo(SpuReq spuReq);

}
