package com.shanzuwang.bean.req.bill.esignature;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Hw
 * 20/05/29 14:14
 */
@Data
public class TemplateReq implements Serializable {
    private String contentMd5;

    private String contentType;

    private boolean convert2Pdf;

    private String fileName;

    private Integer fileSize;

    private String accountId;
}
