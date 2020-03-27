package com.shanzuwang.bean.req;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

/**
 * <p>
 * banner表
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Data
public class BannerAddReq implements Serializable {


    /**
     * img url
     */
    private String img_url;

    @Range
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
