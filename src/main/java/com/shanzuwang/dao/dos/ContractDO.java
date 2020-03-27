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
@TableName("contract")
public class ContractDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String userId;

    /**
     * local, e_sign
     */
    private String thirdType;

    private String company;

    private String phone;

    private String contractSn;

    private String objectId;

    private String objectType;

    private String thirdAccountId;

    private String thirdDocId;

    /**
     * new, signed
     */
    private String status;

    private Date createdAt;

    private Date updatedAt;

    private String extra;


}
