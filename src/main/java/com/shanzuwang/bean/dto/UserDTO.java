package com.shanzuwang.bean.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户信息
 *
 * @author lv
 * @since 2020/1/31
 */
@ApiModel(value ="userDTOValue" ,description = "userDTODesc")
@Data
public class UserDTO implements Serializable {

    /**
     * 登录后系统分配的访问 token
     */
    private String token;

    /**
     * 本次登录时间
     */
    private String currentLoginTime;
    
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 手机号
     */
    private String phone;

}
