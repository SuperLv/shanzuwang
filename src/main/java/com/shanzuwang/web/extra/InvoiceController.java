package com.shanzuwang.web.extra;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.extra.InvoiceReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.InvoiceDO;
import com.shanzuwang.service.IInvoiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
