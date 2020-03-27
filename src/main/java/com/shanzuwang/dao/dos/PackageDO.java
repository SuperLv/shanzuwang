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
@TableName("package")
public class PackageDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

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
     * 金额
     */
    private String price;

    /**
     * 押金
     */
    private Float deposit;

    /**
     * 备注
     */
    private String comment;

    /**
     * new, paid, finished, cancel
     */
    private String status;

    private Date createdAt;

    private Date updatedAt;

    private String extra;

    /**
     * 开票状态 new, done
     */
    private String invoiceStatus;

    /**
     * 发票id
     */
    private Integer invoiceId;

    /**
     * 类型
     */
    private String type;

    /**
     * 公司
     */
    private String company;


}
