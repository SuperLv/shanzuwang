package com.shanzuwang.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shanzuwang.bean.dto.ProductPropertyDTO;
import com.shanzuwang.dao.dos.ProductPropertyDO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  商品属性绑定类
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
public interface IProductPropertyService extends IService<ProductPropertyDO> {

    public List<ProductPropertyDTO> findList(Integer actorId);
}
