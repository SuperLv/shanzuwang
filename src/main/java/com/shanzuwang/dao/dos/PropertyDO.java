package com.shanzuwang.dao.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("property")
public class PropertyDO  {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 分类ID
     */
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
    private Integer ifFilter;

    /**
     * 值
     */
    private String value;

    /**
     * 是否SKU的属性
     */
    private Boolean isSku;


}
