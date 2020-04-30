package com.shanzuwang.bean.req.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shanzuwang.dao.dos.BaseDO;
import com.shanzuwang.dao.dos.BasketDO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Hw
 * 20/04/29 14:08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "BillReq",description = "BillReqDesc")
public class BillReq extends BaseDO implements Serializable{
    /**
     * 公司名称
     */
    @JsonProperty(value = "company_name")
    private String companyName;

    private Integer id;

    @JsonProperty(value = "user_id")
    private String userId;

    /**
     * 订单号
     */
    @JsonProperty(value = "order_id")
    private Integer orderId;



    /**
     * 第几期
     */
    @JsonProperty(value = "period_num")
    private Integer periodNum;

    private Date deadline;

    /**
     * 结束租赁时间
     */
    @JsonProperty(value = "end_date")
    private Date endDate;

    /**
     * new, paid
     */
    private String status;

    @JsonProperty(value = "pay_id")
    private Integer payId;

    private BigDecimal price;

    @JsonProperty(value = "order_price")
    private BigDecimal orderPrice;

    @JsonProperty(value = "package_id")
    private Integer packageId;

    private String extra;

    @JsonProperty(value = "invoice_status")
    private String invoiceStatus;

    @JsonProperty(value = "invoice_id")
    private Integer invoiceId;

    private String leftPeriodsPrices;

    private String periodsPrice;

    private List<Object> leftPeriodsPrice;

    @JsonProperty(value = "periods_price")
    private List<Object> periodsPrices;

    @JsonProperty(value = "sku_name")
    private String skuName;

    @JsonProperty(value = "spu_name")
    private String spuName;

    private Date startDate;
}
