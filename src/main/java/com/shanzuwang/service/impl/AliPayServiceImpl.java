package com.shanzuwang.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.shanzuwang.bean.req.pay.AlipayConfig;
import com.shanzuwang.bean.req.pay.PayReq;
import com.shanzuwang.dao.dos.TransactionDO;
import com.shanzuwang.service.AlipayService;
import com.shanzuwang.service.ITransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * https://opendocs.alipay.com/apis/api_2/alipay.user.agreement.unsign
 * https://opendocs.alipay.com/open/20190319114403226822/api
 * https://docs.alipay.com/pre-open/20170601105911096277/ndbkbn
 * The interface wx pay service.
 */
@Slf4j
@Service
public class AliPayServiceImpl implements AlipayService {
    /**
     * The Alipay park sign scene.
     */
    private final String ALIPAY_SIGN_SCENE = "INDUSTRY|MOBILE";
    @Autowired
    ITransactionService iTransactionService;
    @Autowired
    private AlipayConfig alipayConfig;

    @Override
    public PayReq createOrder(TransactionDO transactionDO, HttpServletRequest request) {
        PayReq payReq = new PayReq();
        log.info("alipay createOrder input  :", JSONObject.toJSONString(transactionDO));
        Map<String, Object> result = new HashMap();
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig.getGatewayUrl(), alipayConfig.getAppid(), alipayConfig.getAppPrivateKey(), "json", "utf-8", alipayConfig.getAlipayPublicKey(), "RSA2");
        AlipayTradePrecreateRequest alipayTradePrecreateRequest = new AlipayTradePrecreateRequest();
        AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
        model.setOutTradeNo(transactionDO.getId().toString());
        model.setTotalAmount(new BigDecimal(transactionDO.getAmount()).multiply(BigDecimal.valueOf(0.01)) + "");
        model.setSubject(transactionDO.getTitle());
        alipayTradePrecreateRequest.setNotifyUrl(alipayConfig.getNotifyUrl());
        alipayTradePrecreateRequest.setBizModel(model);
        try {
            AlipayTradePrecreateResponse response = alipayClient.execute(alipayTradePrecreateRequest);
            log.info("alipay createOrder response  :", JSONObject.toJSONString(response));
            if (response.isSuccess()) {
                result.put("codeUrl", response.getQrCode());
                result.put("orderNo", response.getOutTradeNo());
                result.put("payType", 1);
                payReq.setCodeUrl(response.getQrCode());
                return payReq;
            } else {
                return null;
            }
        } catch (Exception e) {
            log.error("alipay error  :", e.getMessage());
        }
        return null;
    }


    @Override
    public void aliPayNotify(Map<String, String> map) {
        String orderNo = map.get("out_trade_no");
        TransactionDO transactionDO = iTransactionService.getById(orderNo);
        if (transactionDO == null) {
            log.error("orderNo:{} payOrder not found", orderNo);
            return;
        }
        boolean isSave = false;
        try {
            isSave = AlipaySignature.rsaCheckV1(map, alipayConfig.getAlipayPublicKey(), "utf-8", "RSA2");
        } catch (Exception e) {
            log.error("orderNo:{} AlipaySignature error", orderNo);
        }
        if (!isSave) {
            log.error("orderNo:{} AlipaySignature fail", orderNo);
            return;
        }
        String tradeStatus = map.get("trade_status");
        String tradeNo = map.get("trade_no");
        if ("TRADE_SUCCESS".equals(tradeStatus)) {
            //执行业务逻辑
            TransactionDO transactionDO1 = new TransactionDO();
            transactionDO1.setOutSerialNo(tradeNo);
            transactionDO1.setId(transactionDO.getId());
            transactionDO1.setPayType("finished");
            iTransactionService.updateById(transactionDO1);
        }
    }
}
