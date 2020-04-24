package com.shanzuwang.dao.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shanzuwang.dao.dos.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author lv
 * @since 2020-03-25
 */
@Data
@Accessors(chain = true)
@TableName("article")
public class ArticleDO {

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
