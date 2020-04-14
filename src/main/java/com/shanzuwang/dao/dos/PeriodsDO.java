package com.shanzuwang.dao.dos;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.shanzuwang.dao.dos.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Objects;

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
@TableName("periods")
public class PeriodsDO  {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * sku_id
     */
    @JsonProperty(value = "sku_id")
    private Integer skuId;

    /**
     * 租赁时长
     */
    @JsonProperty(value = "rent_duration")
    private Integer rentDuration;

    /**
     * month, quarter, semi_year, year, once
     */
    @JsonProperty(value = "pay_type")
    private String payType;

    /**
     * month, day
     */
    private String type;

    /**
     * 租赁单价
     */
    @JsonProperty(value = "unit_price")
    private Float unitPrice;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PeriodsDO person = (PeriodsDO) o;
        return Objects.equals(id, person.id);
    }

}
