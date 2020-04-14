package com.shanzuwang.web.product;

import com.shanzuwang.bean.req.product.PropertyAddRep;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.PropertyDO;
import com.shanzuwang.service.IPropertyService;
import io.micrometer.core.instrument.Meter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by LiWeijie
 * 20/04/02 14:03
 */
@Api(tags = "属性管理")
@Slf4j
@RestController
@RequestMapping("/api")
public class PropertyController {

    @Autowired
    IPropertyService iPropertyService;


    @ApiOperation("修改属性")
    @PatchMapping("/categories/{cid}/properties/{pid}")
    public ApiResult<PropertyAddRep> UpdatePropert(@PathVariable Integer cid,@PathVariable Integer pid,@RequestBody PropertyAddRep propertyAddRep)
    {
        return ApiResult.success(iPropertyService.UpdateProperty(pid,propertyAddRep));
    }

    @ApiOperation("新增属性")
    @PostMapping("/categories/{id}/properties")
    public  ApiResult<PropertyAddRep> AddCategory(@PathVariable Integer id, @RequestBody PropertyAddRep propertyAddRep)
    {
        return  ApiResult.success(iPropertyService.AddProperty(id,propertyAddRep));
    }

    @ApiOperation("查询属性")
    @GetMapping("/categories/{cid}/properties/{pid}")
    public  ApiResult<PropertyAddRep> getCategory(@PathVariable Integer cid,@PathVariable Integer pid)
    {
        return  ApiResult.success(iPropertyService.getProperty(pid));
    }

    @ApiOperation("属性列表")
    @GetMapping("/categories/{cid}/properties")
    public  ApiResult<List<PropertyAddRep>> ListCategory(@PathVariable Integer cid)
    {
        return  ApiResult.success(iPropertyService.ListProperty(cid));
    }

    @ApiOperation("删除属性")
    @DeleteMapping("/categories/{cid}/properties/{pid}")
    public  ApiResult<PropertyAddRep> DeleteCategory(@PathVariable Integer cid,@PathVariable Integer pid)
    {
        iPropertyService.removeById(pid);
        return  ApiResult.success(null);
    }
}
