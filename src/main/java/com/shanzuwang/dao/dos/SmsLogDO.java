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
 * 短信记录
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sms_log")
public class SmsLogDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 发送网关
     */
    private String gateway;

    /**
     * 短信内容
     */
    private String content;

    /**
     * 短信长度
     */
    private Integer length;

    /**
     * 发送状态，由短信网关返回
     */
    private String status;

    private Date createTime;

    private Date modifyTime;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 来源应用
     */
    private String app;

    /**
     * 应用场景
     */
    private String scene;


}
