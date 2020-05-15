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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("cart")
public class CartDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @JsonProperty(value = "user_id")
    private String userId;

    @JsonProperty(value = "product_id")
    private Integer productId;

    @JsonProperty(value = "product_num")
    private Integer productNum;

    @JsonProperty(value = "period_id")
    private Integer periodId;

    @JsonProperty(value = "start_date")
    private Date startDate;

    @JsonProperty(value = "rent_days")
    private Integer rentDays;

    private String extra;


}
