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
 * 用户验证码
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("user_vcode")
public class UserVcodeDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 验证码
     */
    private String vcode;

    /**
     * 类型，1：注册，2：忘记密码
     */
    private Integer category;

    /**
     * 验证时间
     */
    private Date verifyTime;

    /**
     * 过期时间
     */
    private Date expireTime;

    /**
     * 状态，1为已验证
     */
    private Integer status;

    private Date createTime;

    private Date modifyTime;


}
