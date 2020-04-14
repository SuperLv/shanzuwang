package com.shanzuwang.bean.req.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shanzuwang.dao.dos.BaseDO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@ApiModel(value ="PropertyAddRepValue" ,description = "PropertyAddRepDesc")
public class PropertyAddRep implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类ID
     */
    @JsonProperty(value = "category_id")
    private Integer categoryId;

    /**
     * 名称
     */
    private String name;

    /**
     * choose(多选), radio(单选), input(输入), icon(图标),brand(品牌),YesOrNo(bool)
     */
    private String type;

    /**
     * 是否展示为筛选项
     */
    @JsonProperty(value = "if_filter")
    private Integer ifFilter;

    /**
     * 值
     */
    private List<Object> value;

    /**
     * 是否SKU的属性
     */
    @JsonProperty(value = "is_sku")
    private Boolean isSku;


}
