package com.shanzuwang.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shanzuwang.bean.req.product.PropertyAddRep;
import com.shanzuwang.dao.dos.BrandDO;
import com.shanzuwang.dao.dos.PeriodsDO;
import com.shanzuwang.dao.dos.PropertyDO;
import com.shanzuwang.dao.mapper.PropertyDao;
import com.shanzuwang.service.IPropertyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shanzuwang.util.JsonListUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
public class PropertyServiceImpl extends ServiceImpl<PropertyDao, PropertyDO> implements IPropertyService {
    @Autowired
    IPropertyService iPropertyService;

    @Override
    public PropertyAddRep AddProperty(Integer id, PropertyAddRep propertyAddRep) {
        PropertyDO propertyDO=new PropertyDO();
        BeanUtils.copyProperties(propertyAddRep,propertyDO);
        propertyDO.setValue(JSON.toJSONString(propertyAddRep.getValue()));
        propertyDO.setCategoryId(id);
        iPropertyService.save(propertyDO);
        propertyAddRep.setId(propertyDO.getId());
        propertyAddRep.setCategoryId(id);
        return propertyAddRep;
    }

    @Override
    public PropertyAddRep getProperty(Integer id) {
        PropertyDO propertyDO=iPropertyService.getById(id);
        PropertyAddRep propertyAddRep=new PropertyAddRep();
        BeanUtils.copyProperties(propertyDO,propertyAddRep);
        ArrayList<Object> value  = JSON.parseObject(propertyDO.getValue(), new TypeReference<ArrayList<Object>>(){});
        propertyAddRep.setValue(value);
        return propertyAddRep;
    }

    @Override
    public PropertyAddRep UpdateProperty(Integer id, PropertyAddRep propertyAddRep) {
        PropertyDO propertyDO=new PropertyDO();
        BeanUtils.copyProperties(propertyAddRep,propertyDO);
        propertyDO.setValue(JSON.toJSONString(propertyAddRep.getValue()));
        propertyDO.setCategoryId(id);
        propertyDO.setId(id);
        iPropertyService.updateById(propertyDO);
        propertyAddRep.setId(id);
        return propertyAddRep;
    }

    @Override
    public List<PropertyAddRep> ListProperty(Integer id) {
        LambdaQueryWrapper<PropertyDO> propertyWrapper=new LambdaQueryWrapper<>();
        propertyWrapper.eq(PropertyDO::getCategoryId,id);
        List<PropertyDO> propertyDOS=iPropertyService.list(propertyWrapper);
        List<PropertyAddRep> list=new ArrayList<>();
        for (PropertyDO propertyDO:propertyDOS ){
            PropertyAddRep propertyAddRep=new PropertyAddRep();
            BeanUtils.copyProperties(propertyDO,propertyAddRep);
            ArrayList<Object> value  = JSON.parseObject(propertyDO.getValue(), new TypeReference<ArrayList<Object>>(){});
            propertyAddRep.setValue(value);
            list.add(propertyAddRep);
        }
        return list;
    }


}
