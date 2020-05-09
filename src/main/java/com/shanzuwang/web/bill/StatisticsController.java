package com.shanzuwang.web.bill;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shanzuwang.bean.req.bill.BillPackagesListReq;
import com.shanzuwang.bean.req.bill.StatiSticsReq;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.OrderDO;
import com.shanzuwang.service.IBillService;
import com.shanzuwang.service.IOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hw
 * 20/05/08 14:32
 */
@Api(tags = "首页统计")
@Slf4j
@RestController
@RequestMapping("/api")
public class StatisticsController {

    @Autowired
    IOrderService iOrderService;
    @Autowired
    IBillService iBillService;

    @ApiOperation("数据统计")
    @GetMapping("/statistics")
    public ApiResult<StatiSticsReq> List()
    {
        return ApiResult.success(iBillService.StatisStics());
    }

    @ApiOperation("今日进账")
    @GetMapping("/statistics/income_by_day")
    public ApiResult<BillPackagesListReq> receipts(String time)
    {
        return ApiResult.success(iBillService.Receipts(time));
    }
}
