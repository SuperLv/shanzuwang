package com.shanzuwang.web.bill;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.bill.Oreders;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.service.IOrderService;
import com.shanzuwang.service.impl.OrderServiceImpl;
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
 * 20/05/06 15:32
 */
@Api(tags = "设备订单")
@Slf4j
@RestController
@RequestMapping("/api")
public class OredersController {

    @Autowired
    IOrderService iOrderService;

    @ApiOperation("设备(订单)列表")
    @GetMapping("/orders")
    public PageInfo<Oreders> ListOreders(Query query){return iOrderService.ListListOreders(query);}

    @ApiOperation("查询订单")
    @GetMapping("/orders/{id}")
    public ApiResult<Oreders> GetOreders(@PathVariable Integer id)
    {
        return  ApiResult.success(iOrderService.GetOreders(id));
    }
}
