package com.shanzuwang.bean.req.website;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shanzuwang.bean.req.product.SkuQueryReq;
import com.shanzuwang.dao.dos.PeriodsDO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Hw
 * 20/04/16 17:25
 */
@Data
@ApiModel(value ="BasketReqValue" ,description = "BasketReqDesc")
public class BasketReq implements Serializable {

    private Integer id;

    private List<PeriodsDO> period;

    private SkuQueryReq product;

    @JsonProperty(value = "product_id")
    private Integer productId;

    @JsonProperty(value = "product_type")
    private String productType;

    @JsonProperty(value = "spu_name")
    public String spuName;

    public  String type;
}
