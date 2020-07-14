package com.shanzuwang.web.pay;

import com.shanzuwang.service.AlipayService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @program: shanzuwang
 * @description:
 * @author: gyk
 * @create: 2020-07-09 13:39
 **/
@Slf4j
@RestController
@RequestMapping("api")
public class AliPayController {
    @Autowired
    private AlipayService alipayService;

    /**
     * Alipay notify string.
     *
     * @param request the request
     * @return the string
     * @throws Exception the exception
     */
    @ApiOperation(value = "支付宝回调")
    @RequestMapping({"/alipay/notify"})
    @ResponseBody
    public String alipayNotify(
            HttpServletRequest request) {
        Map<String, String> params = this.getNotifyParams(request);
        log.info("支付宝回调通知params:" + params.toString());

        alipayService.aliPayNotify(params);
        return "success";
    }

    private Map<String, String> getNotifyParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap();
        Map requestParams = request.getParameterMap();
        Iterator iter = requestParams.keySet().iterator();
        while (iter.hasNext()) {
            String name = (String) iter.next();
            String[] values = (String[]) ((String[]) requestParams.get(name));
            String valueStr = "";

            for (int i = 0; i < values.length; ++i) {
                valueStr = i == values.length - 1 ? valueStr + values[i] : valueStr + values[i] + ",";
            }

            params.put(name, valueStr);
        }
        return params;
    }
}
