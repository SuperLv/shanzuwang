package com.shanzuwang.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 拦截器
 * @author hsx
 * create at 2020-01-30
 */
public abstract class BaseInterceptor implements HandlerInterceptor {
    /**
     * 拦截器顺序,数字越小优先级越高
     * @return 优先级数字
     */
   public abstract int getOrder();

}
