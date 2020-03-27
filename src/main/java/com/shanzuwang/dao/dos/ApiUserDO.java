package com.shanzuwang.dao.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.shanzuwang.dao.dos.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("api_user")
public class ApiUserDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 昵称
     */
    private String nickName;

    private String email;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 性别，0为女，男为1
     */
    private Boolean gender;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 年龄，临时使用
     */
    private Integer age;

    /**
     * 备注
     */
    private String memo;

    /**
     * 用户状态，1为有效
     */
    private Integer status;

    private Date lastLoginTime;

    private Date createTime;

    private Date modifyTime;

    private String wechatOpenId;

    private String alipayUserId;


}
