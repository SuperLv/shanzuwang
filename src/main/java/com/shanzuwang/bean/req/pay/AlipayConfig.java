package com.shanzuwang.bean.req.pay;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by Hw
 * 20/05/21 9:39
 */
@Data
@Component
public class AlipayConfig {
    /**
     * 支付宝gatewayUrl
     */
    //private String gatewayUrl="https://openapi.alipay.com/gateway.do";
    private String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
    /**
     * 商户应用id
     */
    private String appid = "2016102600766657";
    /**
     * RSA私钥，用于对商户请求报文加签
     */
    private String appPrivateKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCqx5H3d8pUkIF3R8uBrzMvHVp9K05IF47hh9G3mvwPtE5mVC/+Y8E7kOysrsvtamuaKRma4lKVYiJzYIU1kzviMn8Xihrx1HYVDAZzyJAYBEWoKUrTrcj3tWiXsjDpUzlIIQgBWyLngtCpWjdwMsMrtQTfGdDZocK350lKTaEJZbkdhFo+wIMMDEWiJ5NcK+T8jfHe9oWnaJUWrElLfY+n1YUdXv25gaL7yQt39V/Z4MsW0xjeAUUrBCW77B6hP/Q92/dZEnka/SsvaydO/VOBy/SBawGWoQ1X5+0/pqFni2SOdACnHL10KOnEim7hDjLl5Du9bs3VxI6xoCJe3SAzAgMBAAECggEAdTlhx0TxnBRI2dBtL0pMTuXOLCbip/eLuuEYoff/VuREAFd/ThW5GKME4g9lqUSoMXxOuBLr7uAal6nEJh83Nq/Eryq8DgzxNMc5UpZz2hhsm94kH6KlVOqvrZjBnBblNc5+iTe+gL05b1WewiQF9eVeP6mfwxMW7lcS5q7cf+604m6ATnBhyHG4ajsd5DORldpmwCdxSmOucyQ0OJF43Ue/3bghQSoklrmiw/AuY6hgS5TBpLzQM/lK+/Mpjm0KwUKRMs1oTNi8ubkkT+6Y32JIGk3l30hRf5R6Y31n7qGQtUwE1qpvsZcHQ7HoIVeLrqu2sZ/r4/KL567YM4avUQKBgQDY0FKLY2rNPUZY4DkCqte2F2R+0HiNhQBWnzXvDDoIOMLnagyAVKPuMry3KfCMW4yhG0Z5mwY/xyMebFDiwv11nNFikPQRVEO4bcwplkyA7TWiePBMedHyqvuRTsGzxmfX2yKR6riDpDiNY+Dvojtp8ukzMlnMTykZ+qJHtnEziQKBgQDJpU75jKT26UoSRX/1WXjMhZdHNi2yve5MDTNDBMUbE7iB3TqsjUec9XJG5lJYG1VL9mkE76RfnPL3VeZJaK9IzFc3ZNEZ5XuezOakwcj8nNf45pRNuhzUiAwQDIHSICvj72/md1UOzo1L5g2aywdz+pHh9TS9lB1cPj2BmFE62wKBgFjDmTfF4QDb2EQksFJ+PUgodZVRpjlSPVw5XGxc1hltm6YAjdOwU1j+3rIicoAULxP6RbUdmcZcse0PrWHZMMmJBVRUQggiNoN4OEDCbtnNw8qbts2ohiLSi6Hm23jNftApwBJ7RUVn41tHi9yvz6FFuOGj+I2jzxD6wpuXfur5AoGBAJF2hAM3t2U7I6kMo+MtORxpHplLdynjI72PJDSrgzkmP8rG+eWyQT/0PAzzZ+GRTjXvOxFJgs1KnmdYezjnoNMmPaCDROimiEglThj6hm/dAVvXkpKDuPqEbhdBvoEDnkXe211pJl35GSrweU36OpUq5AQZ7IL6DzgU2i/b3nVTAoGBANiNWJVnnMiJYMGO/8dxjXEqBtrE/Z3pfoDp2kTsfDYNTnf2VmvF/ApJ2nbD+LRQ8NDaN4uSeaP7cVNW2ScVLE7vI+EJnjIoJFmdX0iPfZ/X4GhRHJhr4BKc0kWMbEItDIzi47bIBRY0if+TuND9bsPxCznpQUcBtHoDBZqhxEy6";
    /**
     * 支付宝RSA公钥，用于验签支付宝应答
     */
    private String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAo6N3EEwYxcBqMWpFr8JWyPP6lV5dJ41uWIhZiUAGhvqYLZZ534hHSrdrflGOnTSf1AvtY9PUvaoCia07ePG/iXyxjA1q430EN68a6I6mvcT1dLMr6X07YLRMvyG4CcI4bMb4d92eV1o0yFIWNCTKToW+8msDXPwFhMvSD4iPGvaz+gWna1wTvFrL0GyknlGfo8yBujHTyQjWwBcTZW4/FaTqbriiEv8wUVyCau7Asbc+sm8hc6uXP5Q+xa9qHFOstC9fXWgQoZSfrljatxgbOHGmzDfV2w0hzaVCmBjZY+q7eevKN4V8n/GUubblY7WvA5eiF/e9uUHEGi19OwDUowIDAQAB";

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
    private String notifyUrl = "localhost:8091/shanzuwang/api/alipay/notify";
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
