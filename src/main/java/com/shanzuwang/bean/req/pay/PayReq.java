package com.shanzuwang.bean.req.pay;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shanzuwang.dao.dos.BaseDO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * Created by Hw
 * 20/04/30 10:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "PayReq", description = "PayReq")
public class PayReq extends BaseDO implements Serializable {

    private Integer id;

    @JsonProperty(value = "user_id")
    private String userId;

    @JsonProperty(value = "tran_pay_type")
    private String payType;

    @JsonProperty(value = "tran_out_serial_no")
    private String outSerialNo;


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

    private String codeUrl;
}
