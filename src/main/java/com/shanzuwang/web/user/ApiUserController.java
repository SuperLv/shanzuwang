package com.shanzuwang.web.user;

import com.shanzuwang.bean.req.bill.ApiUserbillReq;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.service.IApiUserService;
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
 * 20/05/14 14:43
 */
@Api(tags = "用户管理(官网)")
@Slf4j
@RestController
@RequestMapping
public class ApiUserController {
    @Autowired
    IApiUserService iApiUserService;

    @ApiOperation("查询用户")
    @GetMapping("/users/{id}")
    public ApiResult<ApiUserbillReq> ListApiUsers(@PathVariable String id)
    {
        return ApiResult.success(iApiUserService.ListApiUsers(id));
    }
}
