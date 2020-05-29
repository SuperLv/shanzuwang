package com.shanzuwang.bean.req.bill.esignature;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Hw
 * 20/05/27 18:40
 */
@Data
public class Person implements Serializable {
    private String  thirdPartyUserId;

    private String  name;

    private String idNumber;

    private String idType;

    private String mobile;

    private String email;
}
