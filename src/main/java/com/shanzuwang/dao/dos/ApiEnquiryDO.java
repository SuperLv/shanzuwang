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
 * 
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("api_enquiry")
public class ApiEnquiryDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID，可以为空
     */
    private String userId;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 姓名
     */
    private String name;

    /**
     * 城市
     */
    private String city;

    /**
     * 租赁类型，长租，短租
     */
    private String rentType;

    /**
     * 需求描述
     */
    private String desc;

    /**
     * 后台人员备注
     */
    private String comment;

    /**
     * 0未回访，1已回访
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createdAt;

    /**
     * 回访时间
     */
    private Date updatedAt;


}
