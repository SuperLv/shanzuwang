package com.shanzuwang.web.extra;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.bill.ApiUserbillReq;
import com.shanzuwang.bean.req.extra.InvoiceReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.config.annotation.User;
import com.shanzuwang.dao.dos.InvoiceDO;
import com.shanzuwang.service.IInvoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * Created by Hw
 * 20/04/21 16:10
 */
@Api(tags = "发票管理")
@Slf4j
@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    IInvoiceService iInvoiceService;

    @ApiOperation("发票列表")
    @GetMapping("/invoice")
    public ApiResult<PageInfo<InvoiceDO>> ListInvoice(Query query)
    {
        return  ApiResult.success(iInvoiceService.ListInvoice(query));
    }

    @ApiOperation("查看发票")
    @GetMapping("/invoice/{id}")
    public  ApiResult<InvoiceReq>  getInvoice(@PathVariable Integer id)
    {
        return  ApiResult.success(iInvoiceService.getInvoice(id));
    }

    @ApiOperation("发票总金额")
    @GetMapping("/invoice/sum")
    public ApiResult<Float>  getInvoiceSum()
    {
        return ApiResult.success(iInvoiceService.getInvoiceSum("8402e392e1b94ec389538229c85a9534"));
    }

    @ApiOperation("新增开票")
    @PostMapping("/invoices")
    @ApiImplicitParams({
            @ApiImplicitParam( name = "access-token", value = "token", paramType = "header", dataType = "String", required = true )
    })
    public ApiResult<InvoiceDO>  addInvoices(@RequestBody InvoiceDO invoiceDO,@ApiIgnore @User ApiUserbillReq apiUserbillReq)
    {
        if (apiUserbillReq.getId()!=null&&apiUserbillReq.getId()!=""){
            invoiceDO.setUserId(apiUserbillReq.getId());
        }
        iInvoiceService.save(invoiceDO);
        return  ApiResult.success(invoiceDO);
    }



}
