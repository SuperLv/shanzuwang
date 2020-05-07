package com.shanzuwang.web.bill;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.bill.BillReq;
import com.shanzuwang.bean.req.bill.BillsReq;
import com.shanzuwang.bean.req.bill.UserbillReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.service.IApiUserService;
import com.shanzuwang.service.IBillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Hw
 * 20/04/28 16:07
 */
@Api(tags = "账单管理")
@Slf4j
@RestController
@RequestMapping("/api")
public class BillController {

    @Autowired
    IBillService iBillService;

    @ApiOperation("账单列表")
    @GetMapping("/user_bills")
    public PageInfo<UserbillReq> ListUserBill(Query query)
    {
         return iBillService.ListUserbill(query);
    }

    @ApiOperation("查询账单")
    @GetMapping("/users/{id}/bills")
    public ApiResult<List<BillReq>> GetBill(@PathVariable String id){ return ApiResult.success(iBillService.GetBill(id)); }

    @ApiOperation("订单销账")
    @PostMapping("bills")
    public ApiResult CancelAccount(@RequestBody BillsReq billsReq)
    {
        iBillService.CancelAccount(billsReq);
        return new ApiResult();
    }

}
