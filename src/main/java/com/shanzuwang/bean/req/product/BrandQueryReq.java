package com.shanzuwang.bean.req.product;

import com.shanzuwang.bean.req.PageReq;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by LiWeijie
 * 20/04/01 16:59
 */
@Data
@ApiModel(value ="brandQueryValue" ,description = "brandQueryDesc")
public class BrandQueryReq extends PageReq implements Serializable {
    /**
     * 品牌名
     */
    private String name;

    /**
     * 品牌logo
     */
    private String imgUrl;
}
