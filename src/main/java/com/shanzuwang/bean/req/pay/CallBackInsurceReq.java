package com.shanzuwang.bean.req.pay;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by Hw
 * 20/06/18 14:09
 */
@Data
public class CallBackInsurceReq implements Serializable {

    @SerializedName("Head")
    private  Head head;

    @SerializedName("Body")
    private  Body body;

    @Data
    public static  class Head
    {
        @SerializedName("RequestId")
        private String requestId;

        @SerializedName("RequestType")
        private String requestType;
    }

    @Data
    public static  class  Body
    {
        @SerializedName("MainId")
        private String  mainId;

        @SerializedName("ErrorCode")
        private String  errorCode;

        @SerializedName("ErrorMsg")
        private String errorMsg;

        @SerializedName("PolicyNo")
        private String  policyNo;

        @SerializedName("PolicyUrl")
        private String policyUrl;

        @SerializedName("PolicyHash")
        private String policyHash;

        @SerializedName("Premium")
        private String premium;

        @SerializedName("ExchangeNo")
        private  String exchangeNo;


    }
}
