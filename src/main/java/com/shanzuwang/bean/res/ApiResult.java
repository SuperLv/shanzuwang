package com.shanzuwang.bean.res;

import com.shanzuwang.enums.ReturnCodeEnum;
import lombok.Data;

/**
 * 接口响应结果
 * @author lv
 * @date 2019/02/12
 */
@Data
public class ApiResult<T> implements java.io.Serializable {
    private static final long serialVersionUID = -5851160699740034401L;

    private String message = ReturnCodeEnum.SUCCESS.getMessage();

    private Integer code = ReturnCodeEnum.SUCCESS.getCode();

    private T response;

    private boolean success=true;

    public ApiResult() {
    }

    public ApiResult(T response) {
        this.code = ReturnCodeEnum.SUCCESS.getCode();
        this.response = response;

    }

    public ApiResult(int code, String message) {
        this.message = message;
        this.code = code;
    }

    public ApiResult(T response, int code, String message) {
        this.code = code;
        this.message = message;
        this.response = response;
    }

    public ApiResult(Throwable e) {
        this.code = ReturnCodeEnum.SERVER_ERROR.getCode();
        this.message = e.getMessage();
    }
    public  static   ApiResult build(ReturnCodeEnum retCodeEnum) {
        return new ApiResult<>(null, retCodeEnum.getCode(), retCodeEnum.getMessage());
    }
    public  static  <T> ApiResult<T> build(ReturnCodeEnum retCodeEnum, T response) {
        return new ApiResult<>(response, retCodeEnum.getCode(), retCodeEnum.getMessage());
    }

    /**
     * 错误结果
     * @param <T>
     * @return
     */
    public static <T> ApiResult<T> error() {
        return new ApiResult<>((T)null,ReturnCodeEnum.SERVER_ERROR.getCode(), ReturnCodeEnum.SERVER_ERROR.getMessage());
    }
    /**
     * 错误结果
     * @param message
     * @param <T>
     * @return
     */
    public static <T> ApiResult<T> error(String message) {
        return new ApiResult<>((T)null,ReturnCodeEnum.SERVER_ERROR.getCode(), message);
    }
    /**
     * 成功
     * @param response
     * @param <T>
     * @return
     */
    public static <T> ApiResult<T> success(T response) {
        return new ApiResult<>(response,ReturnCodeEnum.SUCCESS.getCode(), ReturnCodeEnum.SUCCESS.getMessage());
    }
    /**
     * 成功
     * @param message
     * @param response
     * @param <T>
     * @return
     */
    public static <T> ApiResult<T> success(String message, T response) {
        return new ApiResult<>(response,ReturnCodeEnum.SUCCESS.getCode(), message);
    }
}
