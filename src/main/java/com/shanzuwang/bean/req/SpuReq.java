package com.shanzuwang.bean.req;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created by LiWeijie
 * 20/04/02 15:56
 */
@ApiModel(value ="SpuReq" ,description = "SpuReqDesc")
@Data
public class SpuReq extends PageReq {

    private Integer id;
    /**
     * 商品名称
     * */
    private String name;

}
