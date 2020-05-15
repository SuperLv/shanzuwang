package com.shanzuwang.web.product;

import com.alibaba.fastjson.JSON;
import com.shanzuwang.bean.req.product.CategoryReq;
import com.shanzuwang.bean.req.product.PropertyAddRep;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.BrandDO;
import com.shanzuwang.dao.dos.CategoryDO;
import com.shanzuwang.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by LiWeijie
 * 20/04/02 13:30
 */
@Api(tags = "分类属性")
@RestController
@Slf4j
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @ApiOperation("添加分类")
    @PostMapping("/categories")
    public ApiResult<CategoryReq> AddCategory(@RequestBody CategoryReq categoryReq)
    {
        return ApiResult.success(categoryService.AddCategory(categoryReq));
    }

    @ApiOperation("更新分类")
    @PatchMapping("/categories/{id}")
    public  ApiResult<CategoryReq> UpdateCategroy(@PathVariable Integer id,@RequestBody CategoryReq categoryReq)
    {
        return ApiResult.success(categoryService.UpdateCategory(id,categoryReq));
    }

    @ApiOperation("分类列表")
    @GetMapping("/categories")
    public  ApiResult<List<CategoryReq>> ListCategory()
    {
        return  ApiResult.success(categoryService.ListCategory());
    }

    @ApiOperation("查询分类")
    @GetMapping("/categories/{id}")
    public  ApiResult<CategoryReq> getCategory(@PathVariable Integer id)
    {
        return  ApiResult.success(categoryService.getCategory(id));
    }

    @ApiOperation("官网分类")
    @GetMapping("/categories_tree")
    public ApiResult<List<CategoryReq>> List()
    {
        return ApiResult.success(categoryService.getCategoriesTree());
    }
}
