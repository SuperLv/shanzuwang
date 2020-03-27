package com.shanzuwang.dao.dos;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
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
@TableName("bill")
public class BillDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    /**
     * 订单号
     */
    private Integer orderId;

    /**
     * 第几期
     */
    private Integer periodNum;

    private Date deadline;

    /**
     * new, paid
     */
    private String status;

    private Date createdAt;

    private Date updatedAt;

    private Integer payId;

    private BigDecimal price;

    private String extra;

    private String invoiceStatus;

    private Integer invoiceId;


}
