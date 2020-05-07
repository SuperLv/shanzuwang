package com.shanzuwang.bean.req.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shanzuwang.bean.req.product.ProductPropertyDOReq;
import com.shanzuwang.bean.req.product.SkuQueryReq;
import com.shanzuwang.dao.dos.ApiUserDO;
import com.shanzuwang.dao.dos.ProductPropertyDO;
import com.shanzuwang.dao.dos.UserDO;
import lombok.Data;

import javax.annotation.security.DenyAll;
import javax.print.attribute.standard.MediaSize;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Hw
 * 20/04/30 15:49
 */
@Data
public class Oreders implements Serializable {

    /**
     * 地址
     */
    private String address;


    @JsonProperty(value = "bill_type")
    private String billType;


    /**
     * 押金价值
     */
    private Float deposit;

    /**
     * 押金押金抵免
     */
    @JsonProperty(value = "deposit_free")
    private BigDecimal depositFree;

    @JsonProperty(value = "end_date")
    private Data endDate;

    private String extra;

    private Integer id;


    /**
    * 总
    */
    @JsonProperty(value = "periods_price")
    private List<Object> periodsPrice;

    private String name;

    @JsonProperty(value = "start_date")
    private Date startDate;

    /**
     * 手机号码
     */
    private String phone;


    @JsonProperty(value = "package_id")
    private Integer packageId;




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

    @JsonProperty(value = "rent_duration")
    private Integer rentDuration;

    @JsonProperty(value = "sku_name")
    private String skuName;

    @JsonProperty(value = "spu_name")
    private String spuName;

    @JsonProperty(value = "rent_type")
    private  String rentType;

    /**
     * 单位租金
     */
    @JsonProperty(value = "unit_price")
    private String unitPrice;



    private String status;

    private  String thumb;

    @JsonProperty(value = "product")
    private SkuQueryReq skuQueryReq;

    @JsonProperty(value = "user")
    private ApiUserDO apiUserDO;

    @JsonProperty(value = "user_id")
    private String userId;
}
