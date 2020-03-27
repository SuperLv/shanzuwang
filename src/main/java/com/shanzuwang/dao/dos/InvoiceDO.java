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
@TableName("invoice")
public class InvoiceDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 联系人
     */
    private String name;

    /**
     * 联系手机号
     */
    private String phone;

    /**
     * 发票内容
     */
    private String invoiceCont;

    /**
     * 金额
     */
    private Float figure;

    private String type;

    /**
     * 公司名称
     */
    private String company;

    /**
     * 公司营业执照号
     */
    private String bizNum;

    /**
     * 备注
     */
    private String userComment;

    /**
     * 省市区
     */
    private String city;

    /**
     * 地址
     */
    private String address;

    /**
     * 发票号
     */
    private String invoiceSn;

    /**
     * 物流公司
     */
    private String express;

    /**
     * 物流单号
     */
    private String expressSn;

    /**
     * wait(待开), done(已开), 
     */
    private String status;

    /**
     * 后台备注
     */
    private String sysComment;

    private Date createdAt;

    private Date updatedAt;


}
