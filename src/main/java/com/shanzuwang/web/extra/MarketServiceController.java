package com.shanzuwang.web.extra;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.extra.MarkstServiceReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.service.IMarketServiceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hw
 * 20/04/24 17:15
 */
@Api(tags = "售后管理")
@Slf4j
@RestController
@RequestMapping("/api")
public class MarketServiceController {

    @Autowired
    IMarketServiceService iMarketServiceService;

    @ApiOperation("售后列表")
    @GetMapping("/services")
    public PageInfo<MarkstServiceReq> ListMarkst(Query query)
    {
        return iMarketServiceService.ListMarkst(query);
    }

}
