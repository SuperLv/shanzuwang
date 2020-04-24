package com.shanzuwang.web.website;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.website.BasketReq;
import com.shanzuwang.bean.req.website.BasketsAddReq;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.BasketDO;
import com.shanzuwang.service.IBasketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Hw
 * 20/04/16 18:04
 */
@Api(tags = "栏目统计")
@Slf4j
@RestController
@RequestMapping("/api")
public class BasketController {

    @Autowired
    IBasketService iBasketService;

    @ApiOperation("栏目查询")
    @GetMapping("baskets")
    public ApiResult<PageInfo<BasketReq>> ListBasket(String type)
    {
        return ApiResult.success(iBasketService.ListBasket(type));
    }

    @ApiOperation("添加栏目")
    @PostMapping("/baskets")
    public  ApiResult<BasketDO> AddBasket(@RequestBody BasketsAddReq basketsAddReq)
    {
        return ApiResult.success(iBasketService.AddBasket(basketsAddReq));
    }

    @ApiOperation("删除栏目")
    @DeleteMapping("/baskets/{id}")
    public  void DeleteBasket(@PathVariable Integer id)
    {
        iBasketService.removeById(id);
    }

}
