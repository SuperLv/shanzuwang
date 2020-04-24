package com.shanzuwang.bean.req.extra;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shanzuwang.dao.dos.BillDO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Hw
 * 20/04/21 16:45
 */
@Data
public class InvoiceReq implements Serializable {

    public  Integer id;
    /**
     * 用户ID
     */
    @JsonProperty(value = "user_id")
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
    @JsonProperty(value = "invoice_cont")
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
    @JsonProperty(value = "biz_num")
    private String bizNum;

    /**
     * 备注
     */
    @JsonProperty(value = "user_comment")
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
     * 订单信息
     * */
    public List<BillDO> bills;

    /**
     * 发票号
     */
    @JsonProperty(value = "invoice_sn")
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
    @JsonProperty(value = "sys_comment")
    private String sysComment;

    @JsonProperty(value = "created_at")
    private Date createdAt;

    @JsonProperty(value = "updated_at")
    private Date updatedAt;

}
