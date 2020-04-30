package com.shanzuwang.bean.req.user;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 新增用户请求参数
 *
 * @author lv
 * @since 2020/2/3
 */
@ApiModel(value ="userADDReqValue" ,description = "userAddReqDesc")
@Data
public class UserAddReq implements Serializable {

    /**
     * 用户姓名
     */
    @NotBlank(message = "用户姓名不能为空")
    private String name;

    /**
     * 年龄
     */
    @Range(min = 0,max = 120,message = "年龄最小0岁，最大120岁")
    private Integer age;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "1[3-9]\\d{9}",message = "手机号格式有误")
    private String phone;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 3,max = 20,message = "密码最少3位，最多20位")
    private String password;
}
