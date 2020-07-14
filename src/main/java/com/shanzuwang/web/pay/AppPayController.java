package com.shanzuwang.web.pay;

import com.shanzuwang.bean.req.pay.PayReq;
import com.shanzuwang.bean.res.ApiResult;
import com.shanzuwang.dao.dos.TransactionDO;
import com.shanzuwang.service.AlipayService;
import com.shanzuwang.service.ITransactionService;
import com.shanzuwang.service.WxPayService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by Hw
 * 20/05/21 9:42
 */
@Slf4j
@RestController
@RequestMapping("api")
public class AppPayController {

    private static Logger logger = LoggerFactory.getLogger(AppPayController.class);
    @Autowired
    ITransactionService iTransactionService;
    @Autowired
    private AlipayService alipayService;
    @Autowired
    private WxPayService wxPayService;

    /**
     * Server order pay object.
     *
     * @param sign    the sign
     * @param request the request
     * @return the object
     * @throws Exception the exception
     */
    @RequestMapping(
            value = {"/pay/create"},
            consumes = {"application/json;charset=UTF-8"},
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"}
    )
    @ResponseBody
    @ApiOperation(value = "支付下单")
    @SuppressWarnings("all")
    public ApiResult<PayReq> createOrder(
            @Valid @RequestBody TransactionDO transactionDO,
            HttpServletRequest request) {
        PayReq payReq = new PayReq();
        switch (transactionDO.getPayType()) {
            case "1":
                payReq = alipayService.createOrder(transactionDO,
                        request);
                break;
            case "2":
            default:
                payReq = wxPayService.createOrder(transactionDO,
                        request);

        }
        return new ApiResult(payReq);
    }
}
