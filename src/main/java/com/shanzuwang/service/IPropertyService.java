package com.shanzuwang.service;

import com.shanzuwang.bean.req.product.PropertyAddRep;
import com.shanzuwang.dao.dos.PropertyDO;
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
public interface IPropertyService extends IService<PropertyDO> {
    public PropertyAddRep AddProperty(Integer id, PropertyAddRep propertyAddRep);

    public PropertyAddRep getProperty(Integer id);

    public PropertyAddRep  UpdateProperty(Integer id,PropertyAddRep propertyAddRep);

    public List<PropertyAddRep> ListProperty(Integer id);
}
