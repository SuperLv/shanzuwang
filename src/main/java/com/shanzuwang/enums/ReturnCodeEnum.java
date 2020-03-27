package com.shanzuwang.enums;

import lombok.Getter;

/**
 * 响应结果码及描述
 *
 * @author lv
 */
@Getter
public enum ReturnCodeEnum {
    /**
     * 代码执行抛出异常
     */
    SERVER_ERROR(500,"服务器内部出现异常"),
    /**
     * 成功
     */
    SUCCESS(200,"成功"),
    /**
     * 除成功和异常外,其他结果码均为5为数
     */
    PARAMS_ERROR(10001,"参数有误"),
    /**
     * 没有数据返回
     */
    NO_DATA(10002,"未查询到数据"),
    /**
     * 数据已经存在
     */
    DATA_EXISTED(10003,"数据已存在"),
    /**
     * token 有误或失效
     */
    TOKEN_UNAUTHORIZED(10004,"token 有误或失效"),
    /**
     * 登录失败
     */
    LOGIN_FAIL(10005,"用户名或密码有误")
    ;

    /**
     * 结果码
     */
    private int code;
    /**
     * 结果描述
     */
    private String message;

    ReturnCodeEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 通过code获取枚举对象
     * @param code
     * @return
     */
    public static ReturnCodeEnum getEnumByCode(Integer code) {
        for (ReturnCodeEnum retCodeEnum : ReturnCodeEnum.values()) {
            if (retCodeEnum.code == code) {
                return retCodeEnum;
            }
        }
        return null;
    }

}
