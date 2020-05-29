package com.shanzuwang.bean.req.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Hw
 * 20/05/27 10:36
 */
@Data
@ApiModel(value = "ApiUserQueryReq",description = "ApiUserQueryReqDesc")
public class ApiUserQueryReq  implements Serializable{
    private String username;

    private String password;

    @JsonProperty(value = "auth_type")
    private String authType;

}
