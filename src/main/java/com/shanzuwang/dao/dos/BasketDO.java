package com.shanzuwang.dao.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
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
@TableName("basket")
public class BasketDO  {

    private static final long serialVersionUID = 1L;

    @JsonProperty(value = "product_id")
    private Integer productId;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * hot_rent(热租), printer（打印复印），computer(办公电脑)，vr(会议其他)
     */
    private String type;

    private String productType;


}
