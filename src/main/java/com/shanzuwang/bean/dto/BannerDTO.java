package com.shanzuwang.bean.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.shanzuwang.dao.dos.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * banner表
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */

public class BannerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * img url
     */
    private String imgUrl;

    /**
     * 排序
     */
    private Integer rank;

    /**
     * 0下架，1上架
     */
    private Boolean status;

    /**
     * 名称
     */
    private String title;

    /**
     * 链接
     */
    private String url;

    /**
     * banner,  list_top_ad, list_side_ad
     */
    private String type;

    /**
     * spu_id
     */
    private Integer spuId;


}
