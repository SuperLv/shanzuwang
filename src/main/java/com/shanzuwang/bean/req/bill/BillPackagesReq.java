package com.shanzuwang.bean.req.bill;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.shanzuwang.bean.req.user.RiskReq;
import com.shanzuwang.dao.dos.BaseDO;
import com.shanzuwang.dao.dos.RiskInfoDO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Hw
 * 20/05/08 17:27
 */
@Data
@ApiModel(value = "BillPackagesReq",description = "BillPackagesReqDesc")
public class BillPackagesReq extends BaseDO implements Serializable {

    private Integer id;

    private Integer price;

    private RiskReq risk;

    private String status;

    @JsonProperty(value = "user_id")
    private String userId;

}
