package com.shanzuwang.dao.dos;

import com.baomidou.mybatisplus.annotation.TableField;
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
//@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("market_service")
public class MarketServiceDO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单ID
     */
    private Integer orderId;

    /**
     * 商品ID
     */
    private String userId;

    /**
     * sku_id
     */
    private Integer productId;

    /**
     * 申请类型
     */
    private String type;

    /**
     * 联系人
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 申请数量
     */
    private Integer applyNum;

    /**
     * 问题描述
     */
    @TableField(value ="`desc`" )
    private String desc;

    private Date createdAt;

    private Date updatedAt;

    /**
     * wait, repairing, finished
     */
    private String status;


}
