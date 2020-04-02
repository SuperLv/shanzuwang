package com.shanzuwang.bean.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by LiWeijie
 * 20/04/02 16:42
 */
@Data
@ApiModel(value ="BrandDtoValue" ,description = "BrandDtoDesc")
public class BrandDto implements Serializable {
    /**
     * 品牌名
     */
    private String name;

    /**
     * 品牌logo
     */
    private String imgUrl;
}
