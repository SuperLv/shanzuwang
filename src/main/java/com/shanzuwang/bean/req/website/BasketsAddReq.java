package com.shanzuwang.bean.req.website;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Hw
 * 20/04/20 14:07
 */
@Data
@ApiModel(value ="BasketsAddValue" ,description = "BasketsAddReqDesc")
public class BasketsAddReq implements Serializable {
    @JsonProperty(value = "product_id")
    private Integer[] productId;

    /**
     * hot_rent(热租), printer（打印复印），computer(办公电脑)，vr(会议其他)
     */
    private String type;

}
