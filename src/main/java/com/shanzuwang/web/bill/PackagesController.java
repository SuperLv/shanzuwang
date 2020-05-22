package com.shanzuwang.web.bill;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.bill.PackagesAddReq;
import com.shanzuwang.bean.req.bill.PackagesReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.service.IOrderService;
import com.shanzuwang.service.IPackageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Hw
 * 20/04/30 10:06
 */
@Api(tags = "订单管理")
@Slf4j
@RestController
@RequestMapping("/api")
public class PackagesController {
    @Autowired
    IPackageService iPackageService;

    @ApiOperation("订单列表")
    @GetMapping("/packages")
    public PageInfo<PackagesReq> ListOrders(Query query)
    {
        return iPackageService.ListOrders(query);
    }

    @ApiOperation("查询订单")
    @GetMapping("/packages/{id}")
    public ApiResult<PackagesReq> GetPackages(@PathVariable Integer id)
    {
        return ApiResult.success(iPackageService.GetPackages(id));
    }

    @ApiOperation("新增订单")
    @PostMapping("/packages")
    public ApiResult<PackagesReq> AddPackages(@RequestBody PackagesAddReq packagesAddReq)
    {
        return ApiResult.success(iPackageService.AddPackages(packagesAddReq));
    }
}
