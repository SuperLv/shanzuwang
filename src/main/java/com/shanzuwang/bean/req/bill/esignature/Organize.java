package com.shanzuwang.bean.req.bill.esignature;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Hw
 * 20/05/28 10:19
 */
@Data
public class Organize implements Serializable {

    private String  thirdPartyUserId;

    /**
     * 企业创建人在e签宝的个人账号唯一标识，由创建个人账号接口调用返回的accountId
     * */
    private String  creator;

    private String name;

    private String idType;

    private String idNumber;

    private String orgLegalIdNumber;

    private String orgLegalName;
}
