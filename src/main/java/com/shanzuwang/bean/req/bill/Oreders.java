package com.shanzuwang.bean.req.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.annotation.security.DenyAll;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Hw
 * 20/04/30 15:49
 */
@Data
public class Oreders implements Serializable {
   private String bill_type;

   private Data end_date;

   private Integer id;


   /**
    * 总
    */
   @JsonProperty(value = "periods_price")
   private List<Object> periodsPrice;

    /**
     * 剩余
     */
    @JsonProperty(value = "left_periods_price")
    private List<Object> leftPeriodsPrice;

   private String price;

    @JsonProperty(value = "product_id")
    private Integer productId;

    /**
     * 商品数量
     */
    @JsonProperty(value = "product_num")
    private Integer productNum;

    private Integer rentDuration;

    private String skuName;

    private String spuName;

    private Data start_date;

    private String status;

    private  String thumb;
}
