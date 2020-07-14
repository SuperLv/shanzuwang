package com.shanzuwang.bean.req.pay;

import com.github.wxpay.sdk.WXPayConfig;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.InputStream;


/**
 * The type Wx my config util.
 */
@Component
@Data
public class WxPayUtil implements WXPayConfig {

    /**
     * The constant TYPE_NATIVE.
     */
    public static String TYPE_NATIVE = "NATIVE";
    /**
     * The constant TYPE_JSAPI.
     */
    public static String TYPE_JSAPI = "JSAPI";
    /**
     * The constant APP_TYPE.
     */
    public static String APP_TYPE = "APP";


    private String appID = "wx41a7349a679ab689";

    private String mchID = "1344436801";

    private String appSecret = "42771617c1ac21ade6959ce024058621";

    private String key = "4e680e694eec4c64b8dce801c02a77b3";

    private String notifyUrl = "localhost:8091/shanzuwang/api/wxpay/notify";

    private InputStream certStream;

    /**
     * Instantiates a new Wx my config util.
     *
     * @throws Exception the exception
     */
    public WxPayUtil() {
    }

    public WxPayUtil(String appID, String mchID, String appSecret, String key) {
        this.appID = appID;
        this.mchID = mchID;
        this.appSecret = appSecret;
        this.key = key;
    }

    @Override
    public String getAppID() {
        return this.appID;
    }

    @Override
    public String getMchID() {
        return this.mchID;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    /**
     * Gets app secret.
     *
     * @return the app secret
     */
    public String getAppSecret() {
        return appSecret;
    }

    /**
     * Sets app secret.
     *
     * @param appSecret the app secret
     */
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    @Override
    public InputStream getCertStream() {
        InputStream in = WxPayUtil.class.getClassLoader().getResourceAsStream("static/" + mchID + "_apiclient_cert.p12");
        return in;
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }


}