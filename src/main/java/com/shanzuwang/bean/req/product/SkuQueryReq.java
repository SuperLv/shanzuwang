package com.shanzuwang.bean.req.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shanzuwang.dao.dos.PeriodsDO;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Data
@ApiModel(value ="SkuQueryReqValue" ,description = "SkuQueryReqDesc")
public class SkuQueryReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * web(电脑端), wechat(微信小程序), alipay(支付宝)
     */
    @JsonProperty(value = "e_platform")
    private String[] sePlatform;

    /**
     * 详情图片集
     */
    @JsonProperty(value = "images")
    private String[] images;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * rent(租赁)，(sale)售卖
     */
    private String type;

    /**
     * SPU ID
     */
    @JsonProperty(value = "spu_id")
    private Integer spuId;

    /**
     * web(电脑端), wechat(微信小程序), alipay(支付宝)
     */
    private String ePlatform;

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
    private String simages;

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

    @JsonProperty(value = "periods")
    private List<PeriodsDO> periodsDO;

    @JsonProperty(value = "properties")
    private List<Properties> propertiesef;

}
