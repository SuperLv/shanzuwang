package com.shanzuwang.bean.req;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by LiWeijie
 * 20/04/01 16:59
 */
@Data
public class BrandAddReq extends PageReq implements Serializable {
    private  Integer id;
    /**
     * 品牌名
     */
    private String name;

    /**
     * 品牌logo
     */
    private String imgUrl;
}
