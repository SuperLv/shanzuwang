package com.shanzuwang.web.product;

import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.BrandDO;
import com.shanzuwang.dao.dos.CategoryDO;
import com.shanzuwang.service.ICategoryService;
import com.shanzuwang.service.impl.CategoryServiceImpl;
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
 * 20/04/02 13:30
 */
@Api(tags = "分类属性")
@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @ApiOperation("添加分类")
    @PostMapping("/inster")
    public ApiResult<CategoryDO> AddCategory(@RequestBody CategoryDO categoryDO)
    {
        categoryService.save(categoryDO);
        return ApiResult.success(categoryDO);
    }

    @ApiOperation("更新分类")
    @PostMapping("/update")
    public  ApiResult<CategoryDO> UpdateCategroy(@RequestBody CategoryDO categoryDO)
    {
        categoryService.updateById(categoryDO);
        return ApiResult.success(categoryDO);
    }

}
