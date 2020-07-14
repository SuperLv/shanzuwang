package com.shanzuwang.service;

import com.shanzuwang.bean.req.pay.PayReq;
import com.shanzuwang.dao.dos.TransactionDO;

import javax.servlet.http.HttpServletRequest;

/**
 * The interface wx pay service.
 */
public interface WxPayService {

    /**
     * Create order response object.
     *
     * @param request the request
     * @return the response object
     */
    PayReq createOrder(TransactionDO transactionDO, HttpServletRequest request);

    /**
     * Wx pay notify.
     *
     * @param notifyXml the notify xml
     * @throws Exception the exception
     */
    void wxPayNotify(String notifyXml) throws Exception;


}
