package com.shanzuwang.service;

import com.shanzuwang.bean.req.pay.PayReq;
import com.shanzuwang.dao.dos.TransactionDO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: shanzuwang
 * @description:
 * @author: gyk
 * @create: 2020-07-09 13:42
 **/

public interface AlipayService {
    /**
     * Ali pay notify.
     *
     * @param map the map
     * @throws Exception the exception
     */
    void aliPayNotify(Map<String, String> map);

    /**
     * Create order response object.
     *
     * @param orderRequest the order request
     * @param request      the request
     * @return the response object
     */
    PayReq createOrder(TransactionDO transactionDO, HttpServletRequest request);

}
