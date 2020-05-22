package com.shanzuwang.bean.req.pay;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Hw
 * 20/05/21 9:39
 */
@Data
@Component
@ConfigurationProperties(prefix = "pay.alipay")
public class AlipayConfig {
    /**
     * 支付宝gatewayUrl
     */
    private String gatewayUrl;
    /**
     * 商户应用id
     */
    private String appid;
    /**
     * RSA私钥，用于对商户请求报文加签
     */
    private String appPrivateKey;
    /**
     * 支付宝RSA公钥，用于验签支付宝应答
     */
    private String alipayPublicKey;
    /**
     * 应用公钥证书
     */

    private String appPublicKeyCert;

    /**
     * 支付宝公钥证书
     */
    private String alipayPublicKeyCert;
    /**
     * 支付宝根证书
     */
    private String alipayRootCert;
    /**
     * 签名类型
     */
    private String signType = "RSA2";
    /**
     * 格式
     */
    private String formate = "json";
    /**
     * 编码
     */
    private String charset = "UTF-8";
    /**
     * 同步地址
     */
    private String returnUrl;
    /**
     * 异步地址
     */
    private String notifyUrl;
    /**
     * 最大查询次数
     */
    private static int maxQueryRetry = 5;
    /**
     * 查询间隔（毫秒）
     */
    private static long queryDuration = 5000;
    /**
     * 最大撤销次数
     */
    private static int maxCancelRetry = 3;
    /**
     * 撤销间隔（毫秒）
     */
    private static long cancelDuration = 3000;
}
