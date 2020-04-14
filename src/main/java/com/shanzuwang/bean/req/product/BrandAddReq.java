package com.shanzuwang.bean.req.product;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by LiWeijie
 * 20/04/01 16:59
 */
@Data
@ApiModel(value ="brandAddReqValue" ,description = "brandAddReqDesc")
public class BrandAddReq implements Serializable {
    /**
     * 品牌名
     */
    private String name;

    /**
     * 品牌logo
     */
    private String imgUrl;
}
