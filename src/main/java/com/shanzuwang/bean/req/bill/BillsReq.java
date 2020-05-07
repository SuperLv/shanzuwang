package com.shanzuwang.bean.req.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Hw
 * 20/05/07 14:28
 */
@Data
@ApiModel(value = "BillsReq",description = "BillsReqDesc")
public class BillsReq implements Serializable {
    @JsonProperty(value = "bill_ids")
    public Integer[] billIds;

    public String detail;

    @JsonProperty(value = "out_serial_no")
    public  String outSerialNo;
}
