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
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("periods")
public class PeriodsDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * sku_id
     */
    private Integer skuId;

    /**
     * 租赁时长
     */
    private Integer rentDuration;

    /**
     * month, quarter, semi_year, year, once
     */
    private String payType;

    /**
     * month, day
     */
    private String type;

    /**
     * 租赁单价
     */
    private Float unitPrice;


}
