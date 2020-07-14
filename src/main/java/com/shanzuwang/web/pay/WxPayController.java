
package com.shanzuwang.web.pay;

import com.github.wxpay.sdk.WXPayUtil;
import com.shanzuwang.service.WxPayService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;


/**
 * The type Wx pay controller.
 *
 * @program: payment -controller
 * @description:
 * @author: gyk
 * @create: 2019 -10-10 17:16
 */
@Controller
@Slf4j
@RequestMapping("/api")
public class WxPayController {

    @Autowired
    private WxPayService wxPayService;

    /**
     * Wx pay notify string.
     *
     * @param request  the request
     * @param response the response
     * @return string string
     * @throws Exception the exception
     * @Description:微信支付回调
     * @author cmw
     * @date 2019年9月26日
     */
    @RequestMapping(
            value = {"/wxpay/notify"},
            method = {RequestMethod.POST}
    )
    @ResponseBody
    @ApiOperation(value = "微信支付回调")
    public String wxPayNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HashMap<String, String> returnMap = new HashMap<>();
        returnMap.put("return_code", "SUCCESS");
        returnMap.put("return_msg", "OK");
        BufferedReader br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream()));
        StringBuilder sb = new StringBuilder();
        try {
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            log.info("wxPay payBack error:" + e.getMessage());
        } finally {
            br.close();
        }
        //sb为微信返回的xml
        String notityXml = sb.toString();
        log.info("wxPay payBack notify:" + notityXml);
        wxPayService.wxPayNotify(notityXml);
        return WXPayUtil.mapToXml(returnMap);
    }
}

