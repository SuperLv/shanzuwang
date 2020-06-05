package com.shanzuwang.bean.req.bill.esignature;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Hw
 * 20/06/02 13:22
 */
@Data
@ApiModel(value = "SignflowsReq",description = "SignflowsReqDesc")
public class SignflowsReq implements Serializable {

    /***
     * 是否自动归档，默认false；
     * */
    private  boolean autoArchive;

    private String businessScene;

    private ConfigInfo configInfo;

    private Integer contractValidity;

    private  Integer contractRemind;

    private  Integer signValidity;

    private  String  initiatorAccountId;

    private  String initiatorAuthorizedAccountId;

    @Data
    public static class ConfigInfo
    {
        private String  noticeDeveloperUrl;

        private String  noticeType;

        private String redirectUrl;

        private String  signPlatform;
    }

}
