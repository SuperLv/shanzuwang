package com.shanzuwang.web.website;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.ApiEnquiryDO;
import com.shanzuwang.service.IApiEnquiryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Hw
 * 20/04/28 13:53
 */
@Api(tags = "需求管理")
@Slf4j
@RestController
@RequestMapping("/api")
public class NeedsController {

    @Autowired
    public IApiEnquiryService iApiEnquiryService;

    @ApiOperation("需求列表")
    @GetMapping("/needs")
    private PageInfo<ApiEnquiryDO> ListApiEnquiry(Query query)
    {
         return iApiEnquiryService.ListApiEnquiry(query);
    }

    @ApiOperation("新增需求")
    @PostMapping("/needs")
    public  ApiResult<ApiEnquiryDO> AddNeeds(@RequestBody ApiEnquiryDO apiEnquiryDO)
    {
        iApiEnquiryService.save(apiEnquiryDO);
        return ApiResult.success(apiEnquiryDO);
    }
}
