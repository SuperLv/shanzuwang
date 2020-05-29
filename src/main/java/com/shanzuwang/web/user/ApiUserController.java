package com.shanzuwang.web.user;

import com.alibaba.fastjson.JSONObject;
import com.shanzuwang.bean.req.bill.ApiUserbillReq;
import com.shanzuwang.bean.req.user.ApiUserQueryReq;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.ApiUserDO;
import com.shanzuwang.service.IApiUserService;
import com.shanzuwang.util.CommonDataServiceManager;
import com.shanzuwang.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Hw
 * 20/05/14 14:43
 */
@Api(tags = "用户管理(官网)")
@Slf4j
@RestController
@RequestMapping("/api")
public class ApiUserController {
    @Autowired
    IApiUserService iApiUserService;
    @Autowired
    private CommonDataServiceManager commonDataService;

    @ApiOperation("查询用户")
    @GetMapping("/users/{id}")
    public ApiResult<ApiUserbillReq> ListApiUsers(@PathVariable String id)
    {
        return ApiResult.success(iApiUserService.ListApiUsers(id));
    }

    @ApiOperation("用户登陆")
    @PostMapping("/auth/tokens")
    public ApiResult<ApiUserbillReq> GetApiUsers(@RequestBody ApiUserQueryReq apiUserQueryReq)
    {
       ApiUserDO apiUserDO= iApiUserService.GetApiUsers(apiUserQueryReq);
       if (apiUserDO!=null){
           ApiUserbillReq apiUserbillReq=new ApiUserbillReq();
           BeanUtils.copyProperties(apiUserDO,apiUserbillReq);
           String token = JwtUtil.encode(JSONObject.parseObject(JSONObject.toJSONString(apiUserDO)));

           //将用户数据放入缓存 以便用token可以获取用户全部信息
           apiUserbillReq.setToken(token);
           commonDataService.putCurrentUserDataToRedis(apiUserbillReq);

           log.info("登录成功，生成token:{}",token);
           return ApiResult.success(apiUserbillReq);
       }else{
           log.info("登陆失败");
           return  ApiResult.success(null);
       }
    }

}
