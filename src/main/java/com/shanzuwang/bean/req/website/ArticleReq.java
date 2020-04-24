package com.shanzuwang.bean.req.website;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Data
public class ArticleReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    @JsonProperty(value = "sub_title")
    private String subTitle;

    /**
     * 分类ID
     */
    @JsonProperty(value = "cate_id")
    private Integer cateId;

    /**
     * 分类名称
     * */
    @JsonProperty(value = "cate_name")
    private String cateName;

    private int[] path;

    /**
     * 大图
     */
    private String thumb;

    /**
     * 作者
     */
    private String author;

    /**
     * 来源
     */
    private String origin;

    /**
     * 内容
     */
    private String content;

    /**
     * 0下架，1在线
     */
    private Boolean status;

    private Date createdAt;

    private Date updatedAt;


}
