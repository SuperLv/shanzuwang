package com.shanzuwang.bean.req.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by LiWeijie
 * 20/04/02 15:56
 */
@ApiModel(value ="SpuAddReq" ,description = "SpuAddReqDesc")
@Data
public class SpuAddReq implements Serializable {

    /**
     * 商品名称
     * */
    private String name;

   @JsonProperty(value = "properties")
   private List<ProductPropertyDOReq> productPropertyDOReq;

    private String status;

    @JsonProperty(value = "category_id")
    private String categoryId;

}
