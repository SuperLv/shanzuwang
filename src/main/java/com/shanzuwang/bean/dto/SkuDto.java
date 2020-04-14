package com.shanzuwang.bean.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Hw
 * 20/04/09 14:46
 */
@Data
@ApiModel(value ="SkuDtoValue" ,description = "SkuDtoDesc")
public class SkuDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * rent(租赁)，(sale)售卖
     */
    private String type;

    /**
     * SPU ID
     */
    private Integer spuId;

    /**
     * web(电脑端), wechat(微信小程序), alipay(支付宝)
     */
    @JsonProperty(value = "e_platform")
    private String[] ePlatform;

    /**
     * 名称
     */
    private String name;

    /**
     * 摘要副标题
     */
    private String summary;

    /**
     * 缩略图url
     */
    private String thumb;

    /**
     * 详情图片集
     */
    private String[] images;

    /**
     * 售卖的价格
     */
    private Float price;

    /**
     * 押金价值
     */
    private Float deposit;

    /**
     * 详情
     */
    private String content;

    /**
     * 特殊属性字段
     */
    private String extra;

    /**
     * online, offline, delete
     */
    private String status;

    private Date createdAt;

    private Date updatedAt;

}
