package com.shanzuwang.web.user;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.RiskReq;
import com.shanzuwang.bean.req.product.Query;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.RiskInfoDO;
import com.shanzuwang.service.IRiskInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Hw
 * 20/04/20 17:22
 */
@Api(tags = "风控管理")
@Slf4j
@RestController
@RequestMapping("/api")
public class RisksController {

    @Autowired
    IRiskInfoService iRiskInfoService;

    @ApiOperation("风控列表")
    @GetMapping("/risks")
    public ApiResult<PageInfo<RiskReq>> ListRisk(Query query)
    {
        return ApiResult.success(iRiskInfoService.ListRisk(query));
    }

    @ApiOperation("风控查看")
    @GetMapping("/risks/{id}")
    public ApiResult<RiskReq> getRisk(@PathVariable Integer id)
    {
        return ApiResult.success(iRiskInfoService.getRisk(id));
    }

    @ApiOperation("修改风控")
    @PatchMapping("/risks/{id}")
    public ApiResult<RiskReq> UpdateRisk(@PathVariable Integer id,@RequestBody RiskReq riskReq)
    {
        return  ApiResult.success(iRiskInfoService.UpdateRisk(id,riskReq));
    }

}
