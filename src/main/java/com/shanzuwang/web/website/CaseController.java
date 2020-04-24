package com.shanzuwang.web.website;

import com.shanzuwang.bean.req.website.CaseReq;
import com.shanzuwang.bean.req.PageReq;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.service.ICaseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Hw
 * 20/04/16 9:59
 */
@Api(tags = "案例管理")
@Slf4j
@RestController
@RequestMapping("/api")
public class CaseController {

    @Autowired
    ICaseService iCaseService;

    @ApiOperation("新增案例")
    @PostMapping("/cases")
    public ApiResult<CaseReq> AddCase(@RequestBody CaseReq caseReq)
    {
        return ApiResult.success(iCaseService.AddCase(caseReq));
    }

    @ApiOperation("修改案例")
    @PatchMapping("/cases/{id}")
    public ApiResult<CaseReq> UpdateCase(@PathVariable Integer id,@RequestBody CaseReq caseReq)
    {
        return ApiResult.success(iCaseService.UpdateCase(id,caseReq));
    }

    @ApiOperation("案例列表")
    @GetMapping("/cases")
    public ApiResult<List<CaseReq>> ListCase(PageReq pageReq)
    {
        return ApiResult.success(iCaseService.ListCase(pageReq));
    }

    @ApiOperation("案例查询")
    @GetMapping("/cases/{id}")
    public ApiResult<CaseReq> getCase(@PathVariable Integer id)
    {
        return ApiResult.success(iCaseService.getCase(id));
    }

    @ApiOperation("案例删除")
    @DeleteMapping("/cases/{id}")
    public void  DeleteCase(@PathVariable Integer id) { iCaseService.removeById(id); }

}
