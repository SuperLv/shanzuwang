package com.shanzuwang.bean.req.bill;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shanzuwang.dao.dos.BaseDO;
import com.shanzuwang.dao.dos.OrderDO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Hw
 * 20/04/30 10:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PackagesReq",description = "OrdersReqDesc")
public class PackagesReq extends BaseDO implements Serializable {

    private Integer id;

    @JsonProperty(value = "user_id")
    private String userId;

    @JsonProperty(value = "user_name")
    private String userName;

    @JsonProperty(value = "user_phone")
    private String userPhone;

    /**
     * 地址
     */
    private String address;

    /**
     * 收货人
     */
    private String name;

    private List<Oreders> orders;

    /**
     * 手机号
     */
    private String phone;

    @JsonProperty(value = "tran_pay_type")
    private String payType;

    @JsonProperty(value = "tran_out_serial_no")
    private String outSerialNo;


    /**
     * 金额
     */
    private String price;

    /**
     * 押金
     */
    private Float deposit;

    /**
     * 备注
     */
    private String comment;

    /**
     * new, paid, finished, cancel
     */
    private String status;

    private String extra;

    /**
     * 开票状态 new, done
     */
    @JsonProperty(value = "invoice_status")
    private String invoiceStatus;

    /**
     * 发票id
     */
    @JsonProperty(value = "invoice_id")
    private Integer invoiceId;

    /**
     * 类型
     */
    private String type;

    /**
     * 公司
     */
    private String company;


    @JsonProperty(value = "company_name")
    private String companyName;
}
