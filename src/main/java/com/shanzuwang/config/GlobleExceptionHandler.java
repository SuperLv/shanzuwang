package com.shanzuwang.config;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.enums.ReturnCodeEnum;
import com.shanzuwang.util.Constants;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * 全局异常捕获处理
 *
 * @author hsx
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobleExceptionHandler {

    /**
     * 404异常处理
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ApiResult<Object> exceptionHandle(Exception e,HttpServletRequest request) {
        // 处理404
        return new ApiResult<>(HttpStatus.NOT_FOUND.value(), "地址"+request.getRequestURL()+"不存在");
    }

    /**
     * json异常处理
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({JSONException.class, JsonParseException.class, HttpMessageNotReadableException.class, HttpMessageConversionException.class})
    public ApiResult<Object> jsonExceptionHandler(Exception e) {
        log.warn(e.getMessage());
        StringBuilder message = new StringBuilder("parameter error:");
        if (e.getCause() != null && e.getCause() instanceof MismatchedInputException) {
            MismatchedInputException exception = (MismatchedInputException)e.getCause();
            exception.getPath().stream().filter(n -> (n.getFieldName() != null))
                    .forEach(n -> message.append(n.getFieldName()).append(","));
        }else{
            message.append(e.getMessage());
        }
        return new ApiResult<>(ReturnCodeEnum.PARAMS_ERROR.getCode(),message.toString());
    }

    /**
     * 处理jwt异常
     * @param e
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({SignatureException.class, ExpiredJwtException.class})
    public ApiResult<Object> jwtExceptionHandler(Exception e,HttpServletRequest request) {
       String xAuthToken = request.getHeader(Constants.X_AUTH_TOKEN);
        log.info("--token有误");
        if(e instanceof SignatureException){
            log.warn("token:{}非法",xAuthToken);
        }else{
            log.warn("token:{}过期",xAuthToken);
        }
        return ApiResult.build(ReturnCodeEnum.TOKEN_UNAUTHORIZED);
    }


    /**
     * 异常处理
     *
     * @param e 异常
     * @return 处理结果
     */
    @ExceptionHandler(value = Exception.class)
    public ApiResult<Object> exceptionHandle(Exception e) {
        log.error("出现错误,异常类型:{}:", e.getClass().getName(), e);
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException methodArgException = (MethodArgumentNotValidException) e;
            return new ApiResult<>(ReturnCodeEnum.PARAMS_ERROR.getCode(), methodArgException.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        } else if (e instanceof BindException) {
            BindException bindException = (BindException) e;
            return new ApiResult<>(ReturnCodeEnum.PARAMS_ERROR.getCode(), bindException.getAllErrors().get(0).getDefaultMessage());
        } else if (e instanceof ValidationException || e instanceof ConstraintViolationException) {
            return new ApiResult<>(ReturnCodeEnum.PARAMS_ERROR.getCode(), e.getMessage());
        }else if (e instanceof MethodArgumentTypeMismatchException){
            //方法参数不是预期类型时
            return new ApiResult<>(ReturnCodeEnum.PARAMS_ERROR.getCode(), e.getLocalizedMessage());
        }
        //这里也可以处理系统自定义的异常等
        return ApiResult.error("出现错误:"+e.getMessage());
    }

}
