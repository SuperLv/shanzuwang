package com.shanzuwang.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 请求执行时间记录
 * @author hsx
 * create at 2020-01-30
 */
@Slf4j
@Component
public class ExecuteTimeInterceptor extends BaseInterceptor {
    /**
     * 请求开始时间key
     */
    private static final String BEGIN_TIME_KEY="$req_begin";
    /**
     * 需要警告的执行时间阈值
     */
    private static final int WARN_EXECUTE_TIME=1000;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute(BEGIN_TIME_KEY, System.currentTimeMillis());
        return true;
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE-10;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
         Long beginTime = (Long) request.getAttribute(BEGIN_TIME_KEY);
         if(beginTime!=null&&(beginTime-System.currentTimeMillis())>WARN_EXECUTE_TIME){
             log.warn("request:{},执行时长超过:{}毫秒",request.getRequestURI(),WARN_EXECUTE_TIME);
         }
    }
}
