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
@TableName("`order`")
public class OrderDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 账户ID
     */
    private String userId;

    /**
     * SKU ID
     */
    private Integer productId;

    /**
     * 包裹ID
     */
    private Integer packageId;

    /**
     * 商品数量
     */
    private Integer productNum;

    /**
     * 订单金额
     */
    private String price;

    /**
     * 单位租金
     */
    private String unitPrice;

    /**
     * 押金
     */
    private BigDecimal deposit;

    /**
     * 押金押金抵免
     */
    private BigDecimal depositFree;

    /**
     * 地址
     */
    private String address;

    /**
     * 收货人
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 账单支付类型 month, quarter, semi_year, year, once
     */
    private String billType;

    /**
     * 租赁类型 month, day
     */
    private String rentType;

    private Integer rentDuration;

    /**
     * 总
     */
    private String periodsPrice;

    /**
     * 剩余
     */
    private String leftPeriodsPrice;

    /**
     * new, renting, paid, waiting_return, finished
     */
    private String status;

    /**
     * 0:正常，1:续租
     */
    private Integer relet;

    private String ip;


    private String extra;


}
