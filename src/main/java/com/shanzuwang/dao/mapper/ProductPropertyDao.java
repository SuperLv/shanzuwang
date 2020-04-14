package com.shanzuwang.dao.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shanzuwang.bean.dto.ProductPropertyDTO;
import com.shanzuwang.config.Constants;
import com.shanzuwang.dao.dos.BrandDO;
import com.shanzuwang.dao.dos.ProductPropertyDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *  商品属性绑定表
 * @author lv
 * @since 2020-03-25
 */
public interface ProductPropertyDao extends BaseMapper<ProductPropertyDO> {

    public List<ProductPropertyDTO>  findList(@Param(Constants.WRAPPER) LambdaQueryWrapper<ProductPropertyDO> productPropertyDO);

}
