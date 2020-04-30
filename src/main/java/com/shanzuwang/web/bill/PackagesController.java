package com.shanzuwang.web.bill;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.bill.PackagesReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.service.IOrderService;
import com.shanzuwang.service.IPackageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
