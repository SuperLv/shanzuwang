package com.shanzuwang.bean.req.extra;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shanzuwang.bean.req.product.SkuQueryReq;
import lombok.Data;

import java.util.Date;

/**
 * Created by Hw
 * 20/04/24 17:22
 */
@Data
public class MarkstServiceReq {
    private Integer id;

    /**
     * 订单ID
     */
    @JsonProperty(value = "order_id")
    private Integer orderId;

    /**
     * 商品ID
     */
    @JsonProperty(value = "user_id")
    private String userId;

    /**
     * sku_id
     */
    @JsonProperty(value = "product_id")
    private Integer productId;

    /**
     * 申请类型
     */
    private String type;

    /**
     * 联系人
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 申请数量
     */
    @JsonProperty(value = "apply_num")
    private Integer applyNum;

    /**
     * 问题描述
     */
    private String desc;

    @JsonProperty(value = "created_at")
    private Date createdAt;

    @JsonProperty(value = "updated_at")
    private Date updatedAt;

    /**
     * wait, repairing, finished
     */
    private String status;

    @JsonProperty(value = "sku")
    private SkuQueryReq skuQueryReq;
}
