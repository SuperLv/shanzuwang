package com.shanzuwang.web.product;

import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.PropertyDO;
import com.shanzuwang.service.IPropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by LiWeijie
 * 20/04/02 14:03
 */
@Api(tags = "属性管理")
@Slf4j
@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    IPropertyService iPropertyService;

    @ApiOperation("添加属性")
    @PostMapping("/inster")
    public ApiResult<PropertyDO> AddPropert(@RequestBody PropertyDO propertyDO)
    {
          iPropertyService.save(propertyDO);
          return ApiResult.success(propertyDO);
    }

    @ApiOperation("修改属性")
    @PostMapping("/")
    public ApiResult<PropertyDO> UpdatePropert(@RequestBody PropertyDO propertyDO)
    {
        iPropertyService.updateById(propertyDO);
        return ApiResult.success(propertyDO);
    }
}
