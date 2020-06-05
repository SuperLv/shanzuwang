package com.shanzuwang.bean.req.bill.esignature.component;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Hw
 * 20/06/04 15:46
 */
@Data
public class Template implements Serializable {
    private String name;

    private String templateId;

    private SimpleFormFields simpleFormFields;
}
