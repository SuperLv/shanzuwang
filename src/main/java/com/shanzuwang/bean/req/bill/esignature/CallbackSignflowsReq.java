package com.shanzuwang.bean.req.bill.esignature;

import lombok.Data;
import sun.rmi.runtime.Log;

import java.io.Serializable;

/**
 * Created by Hw
 * 20/06/02 14:50
 */
@Data
public class CallbackSignflowsReq implements Serializable{
    private String action;

    private String flowId;

    private String businessScence;

    private Integer flowStatus;

    private String statusDescription;

    private String createTime;

    private String endTime;

    private Long timestamp;


}
