package com.shanzuwang.bean.req.product;

import com.alibaba.fastjson.annotation.JSONField;
import com.shanzuwang.bean.req.PageReq;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by LiWeijie
 * 20/04/07 15:32
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value ="spuQueryRepValue" ,description = "spuQueryRepDesc")
public class SpuQueryRep extends PageReq implements Serializable {
    private  String sorting;

    private  String filter;

    private Integer id;
    /**
     * 商品名称
     * */
    private String name;


    private String status;
}
