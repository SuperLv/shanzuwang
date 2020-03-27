package com.shanzuwang.dao.dos;

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
@TableName("transaction")
public class TransactionDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 修改时间
     */
    private Date modifyTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 交易金额，单位为分
     */
    private Integer amount;

    private String userId;

    /**
     * 交易类型，比如支付宝
     */
    private String payType;

    /**
     * 交易对象类型
     */
    private String objectType;

    private Integer objectId;

    private Integer originObjectId;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String memo;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 外部系统交易号
     */
    private String outSerialNo;

    /**
     * 订单标题
     */
    private String title;

    /**
     * 回调地址
     */
    private String callback;

    /**
     * 订单详情
     */
    private String detail;

    /**
     * 支付帐号
     */
    private String paymentAccount;

    private String ip;


}
