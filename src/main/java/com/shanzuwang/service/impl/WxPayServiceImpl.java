
package com.shanzuwang.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayUtil;
import com.shanzuwang.bean.req.pay.PayReq;
import com.shanzuwang.bean.req.pay.WxPayUtil;
import com.shanzuwang.dao.dos.TransactionDO;
import com.shanzuwang.service.ITransactionService;
import com.shanzuwang.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * The interface wx pay service.
 */
@Slf4j
@Service
public class WxPayServiceImpl implements WxPayService {
    @Autowired
    ITransactionService iTransactionService;

    @Autowired
    private WxPayUtil wxPayUtil;

    @Override
    public PayReq createOrder(TransactionDO transactionDO, HttpServletRequest request) {
        PayReq payReq = new PayReq();
        String ip = transactionDO.getIp();
        String orderFee = String.valueOf(transactionDO.getAmount());
        Map<String, String> data = new HashMap<>();
        data.put("trade_type", "NATIVE");
        data.put("body", transactionDO.getTitle());
        data.put("out_trade_no", transactionDO.getId().toString());
        data.put("total_fee", orderFee);
        data.put("spbill_create_ip", ip);
        data.put("notify_url", wxPayUtil.getNotifyUrl());
        Map<String, String> resp = null;
        WXPay wxpay = new WXPay(wxPayUtil);
        try {
            log.info("wxpay request: " + JSONObject.toJSONString(data));
            resp = wxpay.unifiedOrder(data);
            if (resp == null) {
                return null;
            }
            log.info("wxpay success response : " + JSONObject.toJSONString(resp));
        } catch (Exception e) {
            log.error("wxpay error response : " + JSONObject.toJSONString(resp));
        }
        String returnCode = resp.get("return_code");
        String resultCode = resp.get("result_code");
        if ("SUCCESS".equals(returnCode)) {
            if ("SUCCESS".equals(resultCode)) {
                Long time = System.currentTimeMillis() / 1000L;
                String timestamp = time.toString();
                resp.put("timestamp", timestamp);
                payReq.setCodeUrl(resp.get("code_url"));
                log.info("wxpay createOrder resp:{} ", JSONObject.toJSONString(resp));
                return payReq;
            } else {
                log.error("wxpay fail response 订单号：{},错误信息：{}", transactionDO.getId(), JSONObject.toJSONString(resp));
                return null;
            }
        } else {
            log.error("wxpay fail response 订单号：{},错误信息：{}", transactionDO.getId(), JSONObject.toJSONString(resp));
            return null;
        }
    }


    @Override
    public void wxPayNotify(String notifyXml) throws Exception {
        Map map = WXPayUtil.xmlToMap(notifyXml);
        String returnCode = (String) map.get("return_code");
        WxPayUtil wxPayUtil = new WxPayUtil();
        log.info("wxPayUtil:{}", JSONObject.toJSONString(wxPayUtil));
        WXPay wxpay = new WXPay(wxPayUtil);
        if (!wxpay.isPayResultNotifySignatureValid(map)) {
            return;
        }
        String orderNo = (String) map.get("out_trade_no");
        if ("SUCCESS".equals(returnCode)) {
            TransactionDO transactionDO = iTransactionService.getById(orderNo);
            if (transactionDO == null) {
                log.error("orderNo:{} payOrder not found", orderNo);
                return;
            }
            //随机数
            String nonceStr = WXPayUtil.generateNonceStr();
            WXPay wxPay = new WXPay(wxPayUtil);
            SortedMap<String, String> packageParams = new TreeMap<String, String>();
            packageParams.put("appid", wxPayUtil.getAppID());
            packageParams.put("mch_id", wxPayUtil.getMchID());
            packageParams.put("nonce_str", nonceStr);
            packageParams.put("out_trade_no", orderNo);
            Map<String, String> stringMap = wxPay.orderQuery(packageParams);
            String tradeState = stringMap.get("trade_state");
            String payOrderId = (String) map.get("transaction_id");
            if ("SUCCESS".equals(tradeState)) {
                //执行业务逻辑
                TransactionDO transactionDO1 = new TransactionDO();
                transactionDO1.setOutSerialNo(payOrderId);
                transactionDO1.setId(transactionDO.getId());
                transactionDO1.setPayTime(new Date());
                transactionDO1.setPayType("finished");
                iTransactionService.updateById(transactionDO1);
            } else {
                log.error("orderNo:{} queryWxOrder not paid {}", orderNo, tradeState);
            }
        } else {
            log.error("orderNo:{} wxPayNotify fail {}", orderNo, map.get("return_msg"));
        }
    }
}

