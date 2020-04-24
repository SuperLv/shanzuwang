package com.shanzuwang.web.user;

import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.req.PageReq;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.ApiUserDO;
import com.shanzuwang.service.IApiUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hw
 * 20/04/20 14:51
 */

@Api(tags = "会员管理")
@Slf4j
@RestController
@RequestMapping("/api")
public class ClientsController {

    @Autowired
    IApiUserService iApiUserService;

    @ApiOperation("会员列表")
    @GetMapping("/clients")
    public ApiResult<PageInfo<ApiUserDO>> ListApiUser(PageReq pageReq)
    {
        return  ApiResult.success(iApiUserService.ListClients(pageReq));
    }
}
