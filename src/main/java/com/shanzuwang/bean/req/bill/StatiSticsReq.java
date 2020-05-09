package com.shanzuwang.bean.req.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Hw
 * 20/05/08 15:59
 */
@Data
@ApiModel(value = "StatiSticsReq",description = "StatiSticsReqDesc")
public class StatiSticsReq implements Serializable{
    @JsonProperty(value = "article_num")
    Integer articleNum;

    @JsonProperty(value = "brand_num")
    Integer brandNum;

    @JsonProperty(value = "case_num")
    Integer caseNum;

    @JsonProperty(value = "expired_total")
    BigDecimal expiredTotal;

    @JsonProperty(value = "invoice_total")
    Integer invoiceTotal;

    @JsonProperty(value = "order_product_num")
    Integer orderProductNum;

    @JsonProperty(value = "order_total")
    BigDecimal orderTotal;

    @JsonProperty(value = "package_deposit_total")
    BigDecimal packageDepositTotal;

    @JsonProperty(value = "package_num")
    Integer packageNum;

    @JsonProperty(value = "paid_total")
    BigDecimal paidTotal;

    @JsonProperty(value = "risk_num")
    Integer riskNum;

    @JsonProperty(value = "service_num")
    Integer serviceNum;

    @JsonProperty(value = "sku_len_online")
    Integer skuLenOnline;

    @JsonProperty(value = "sku_num")
    Integer skuNum;

    @JsonProperty(value = "spu_num")
    Integer spuNum;

    @JsonProperty(value = "user_num")
    Integer userNum;

    @JsonProperty(value = "wait_pay_total")
    BigDecimal waitPayTotal;
}
