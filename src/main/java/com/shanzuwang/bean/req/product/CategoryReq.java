package com.shanzuwang.bean.req.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shanzuwang.dao.dos.CategoryDO;
import lombok.Data;
import lombok.experimental.Accessors;

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
public class CategoryReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 父ID
     */
    @JsonProperty(value = "parent_id")
    private String parentId;

    /**
     * 名称
     */
    private String name;

    /**
     * 路径
     */
    private int[] path;

    /**
     * 显示次序
     */
    private Integer rank;

    /**
     * 图标名称
     */
    @JsonProperty(value = "icon_name")
    private String iconName;

    /**
     * 是否显示在menu
     */
    @JsonProperty(value = "menu_show")
    private Integer menuShow;

    /**
     * 状态0，1
     */
    private Integer status;

    @JsonProperty(value = "created_at")
    private Date createdAt;

    @JsonProperty(value = "updated_at")
    private Date updatedAt;

    private List<CategoryReq>  children;

}
