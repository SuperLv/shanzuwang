package com.shanzuwang.config.pay;

/**
 * Created by Hw
 * 20/05/21 10:59
 */
public final class AlipayReq {
    /**
     * 支付宝网关（固定）
     * */
    public static final   String URL="https://openapi.alipay.com/gateway.do/alipay.trade.wap.pay";

    /**
     * APPID 即创建应用后生成
     * */
    public static final  String APPID="2018080660933228";

    /**
     * 开发者私钥，由开发者自己生成
     * */
    public  static final String APP_PRIVATE_KEY="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDwP3RWdgI+d5UcixsO7JE6g6/5ZmZ+PE3DCrbx+cWnUFQLAyqfWR6tTA2Nfd0LllVbm7pZqudd3ra6tTVeDawmBgW2sZZBm27gbHt4Mu7OfYe9/HkzQ/gTvJHmZ9qdnlgjR2WxHrgAkuNqNrjCAPfUst0HsVwUEBRGGzY+6ylQ/Lh/7KBGKiGxNp+c8iwDhyUuqe5+RFqMU9n4rArDiWEYvPgO3sd0JKIdi3HuWRnQuc+dNol38DiJLDqVGW1w3EICQIvIVFevu9vdw+lzYebJTy6/tBcLNlwT55jfpFKuXXIzzj0BNLYVNkOyOL/9Dmg4qgJ26WJ7Hk/See9krdanAgMBAAECggEATDSUfmhoRdBAqBEnwBAh8OGcjAuDLz+JmvhnF1IW8/9y6RtUBb+LD4U0N1FZMDe7U5eOAKtJQGlIpOidyPulQ1pdrZmfA6h9/wnqEDJ3tV/0Ertm5nAE3J3P2bnPineTlX0sMIaB7wyoviQlbsRH2Fvz6GDFyEdhZutyxXpqXJlvvLrc/jmpcQQEQBST4moApw3GfdOcNadUnzBJocbNtz4GrG25m4D0xXV8Ke4RwR35Gr/2JU3yl2h8fR4myAwHim7NDuD95PX27ZZwVUOMC5rIccYkHS+8DInZ8rynymko7XNfUqlnUIaSqsExF5z0SKr4YM+GVtNhRoaQ5akdYQKBgQD967bw3TPqw2aCjq1JKx5qQWtZBdBgPIGEN+tEUDI5P7dOVH9ut5UynlJ/Mr4C+EXAAD84XTDMxyPsSRMFYDFCZ6baLr7pooA5PAej+IFlEXsPWzNBERUBZibvrw5PUOBVZi2PSK3DfBYVcdJZuTaD+6YRnwGiaqwTP1LAwMiVMwKBgQDyNxPr4wroU9wqmc9nRDbXlSOevHQ/jf4MknAtU9vEhBo60Lswn9wg8rUcUJZdPzYdZZXrnW5kDosXG1+qV0Vhv0UGAm5oNw5E2QljPHeRim0SYZ1Izvspws/mjzrm2wnnRo4lyicp19vRoMCxheyITPwL463uMO+mNlnHSqmQvQKBgFqXvJ9W7Gz35hzydpTCU4mpaXlLf7q2G6dmpAOtcR3Hw8iokQaZbRuwLNLA8dXxakFEVUnwxbZp3kExGR4z4Z9Fh9wUDX0hwwmohzlTMiczA2rJKRIv3ASxw3aESGC4BDGz0NFsJsK2BNMF1vy3/eE86VUkG9ssn9QGJaorneTNAoGBAKtgcQX71OHhQ+wYe/kS+MK8UUGlEZNOQixfCGPp65s15OEsguW6liPx1lc8ZceATCesktDsyQieiadFxQBfU0X/8TR5T+kI2123Or8uL2OYz+erjX/Qg0FfB36YZRKmdHFhKH5wdBlVE8FUvkJdAajG36cB5zSbB1VRK2Co4ST5AoGBAI57FmHEP6bObivFELrR8NG3IXDtqJGu65e75LJQBxgnv3m45llE26MjORlLGDdK5FlpUgZwsFasM5ii3Il0ljTjjkgM4nGmp30pIvsiNRsRFpJIpcF15c0t9PU1h6zEiQcyuuPdIwDmFtJDQo7HGz7YbsAiNb05g8aAyBH4MG7B";
    /**
     * 参数返回格式，只支持 json
     * */
    public  static final String FORMAT="json";

    /**
     * 编码集，支持 GBK/UTF-8
     * */
    public static final  String CHARSET="UTF-8";

    /**
     * 支付宝公钥，由支付宝生成
     * */
    public static final  String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs8dEjM4MKqj6gWuXM5y/Qlj1446x2gpTnaxHJVqt9/ey1IN1MJfZKeOwAwhR3suDWobfnBcjoYQ1lwceHOos3Zgld4i2nbE3rnKbS0cjjaNA9leQSGEh8liPH3G3uBkVZ9cFNIalE/ib2iAPJY2TdcX7Vabi8uB7c89qal8J1JdXlXyAs9ocN+hE/oLyWVX1m5+cSMqpNayPvBvbGqlyJFRoGnG2QC4FYbMU9h4wXr9JRJAyOAsxauzQnTRe2Lm5PhowVNpRm96A1EXd+KMy+4p6t7T3EJkv7r856lrOqN5ioZe0RsIQMtgfdnrJ/NvQCMK6bTJ2J6IhkpKS45iLFwIDAQAB";

    /**
     * 商户生成签名字符串所使用的签名算法类型，目前支持 RSA2 和 RSA，推荐使用 RSA2
     * */
    public static final  String SIGN_TYPE="RSA2";

    /**
     * 区块链accesskey
     * */
    public static String ACCESS_KEY="LTAIEAwhmMxixxf5";

    /**
     * 区块链accessSerret
     * */
    public static String ACCESS_SECRET="KRrKbMjthYyBc8kM0MSZYNJJ8begcH";

    /**
     * 区块链URL
     * */
    public static String ACCESS_URL="https://prodapigw.cloud.alipay.com";

    /**
     * 实例 ID 配置，正式环境请更换为 notary-api-prod
     */
    public static String PRODUCT_INSTANCEIT = "notary-api-test";
}
