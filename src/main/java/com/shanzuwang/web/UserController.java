package com.shanzuwang.web;

import com.alibaba.fastjson.JSONObject;
import com.shanzuwang.bean.bo.PageInfo;
import com.shanzuwang.bean.dto.UserDTO;
import com.shanzuwang.bean.req.UserAddReq;
import com.shanzuwang.bean.req.UserQueryReq;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.UserDO;
import com.shanzuwang.enums.ReturnCodeEnum;
import com.shanzuwang.service.IUserService;
import com.shanzuwang.util.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * 用户服务
 *
 * @author lv
 * @since 2020/1/31
 */
@Api(tags = "用户管理")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
    /**
     * @PreAuthorize(value="isAuthenticated()") 表示需要认证(登陆)过的才可访问，因已经在过滤器中配置了全局的，故这里无需配置
     * @PreAuthorize(“hasAuthority(‘xxx’)”) 这的xxx是角色或资源名称
     * @PreAuthorize("hasAnyRole('xxx')")、@PreAuthorize("hasRole('xxx')") 具有某个角色权限才能访问
     * 【注意: springsecurity 默认的hasRole('xx')中xx会增加上前缀ROLE_，因此实际角色是ROLE_xx，
     * 这个前缀可以通过自定义DefaultWebSecurityExpressionHandler修改defaultRolePrefix来改变； hasAuthority默认不会增加前缀】
     * 更多表达式用法参见{@link https://docs.spring.io/spring-security/site/docs/5.2.2.BUILD-SNAPSHOT/reference/htmlsingle/#el-common-built-in}
     */
    /**
     * 分页查询用户
     * @param userQueryReq 查询参数，支持id精确查找和name模糊查找
     * @return 用户列表
     */
    @ApiOperation("列表查询")
    @GetMapping("/page")
    public ApiResult<PageInfo<UserDTO>> queryUserByPage(@Valid UserQueryReq userQueryReq){
        return ApiResult.success(userService.getUserByPage(userQueryReq));
    }

    /**
     * 用户登录
     * @param phone 用户名
     * @param pwd 用户密码
     * @return
     */
    @ApiOperation("登陆")
    @PostMapping("/login")
    public ApiResult<String> login(String phone,String pwd){
        if(StringUtils.isAnyBlank(phone,pwd)){
            return ApiResult.build(ReturnCodeEnum.LOGIN_FAIL);
        }
        UserDTO user = userService.login(phone,pwd);
        if(Optional.ofNullable(user).isPresent()){
            String token = JwtUtil.encode(JSONObject.parseObject(JSONObject.toJSONString(user)));
            log.info("{}登录成功，生成token:{}",phone,token);
            return ApiResult.success(token);
        }
        return ApiResult.build(ReturnCodeEnum.LOGIN_FAIL);
    }

    /**
     * 添加用户
     * @param userAddReq
     * @return
     */
    @ApiOperation("添加用户")
    @PostMapping("/add")
//    @PreAuthorize("hasRole('ADMIN')")
    public ApiResult<Integer> addUser(@Valid @RequestBody  UserAddReq userAddReq){
        //创建人
//        UserDTO createUser = (UserDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(userAddReq,userDO);
        userService.save(userDO);
        log.info("用户:{}新增用户:{}",123,userDO.getId());
        return ApiResult.success(userDO.getId());
    }


}
