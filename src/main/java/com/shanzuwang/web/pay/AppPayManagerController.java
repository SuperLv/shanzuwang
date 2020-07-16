package com.shanzuwang.web.pay;

import cn.com.antcloud.api.twc.v1_0_0.request.CreateFileRequest;
import cn.com.antcloud.api.twc.v1_0_0.response.CreateTransResponse;
import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.shanzuwang.config.pay.AlipayReq;
import com.shanzuwang.dao.dos.RiskInfoDO;
import com.shanzuwang.dao.dos.TransactionDO;
import com.shanzuwang.service.IRiskInfoService;
import com.shanzuwang.service.ITransactionService;
import com.shanzuwang.util.alipay.AIliPayFinanceLease;
import com.shanzuwang.util.alipay.TransactionDemo;
import com.shanzuwang.util.http.esignature.FileHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.shanzuwang.util.alipay.TransactionDemo.AddTrans;
import static com.shanzuwang.util.alipay.TransactionDemo.getTransaction;

/**
 * Created by Hw
 * 20/05/21 9:42
 */
@Api("alli上链接口")
@Slf4j
@RestController
@RequestMapping("alipay1")
public class AppPayManagerController {

    @Autowired
    IRiskInfoService iRiskInfoService;
    @Autowired
    AIliPayFinanceLease aIliPayFinanceLease;
    @Autowired
    ITransactionService iTransactionService;

        private static Logger logger = LoggerFactory.getLogger(AppPayManagerController.class);

    public static void main(String[] args) throws AlipayApiException {
            AlipayClient alipayClient =  new  DefaultAlipayClient(AlipayReq.URL,AlipayReq.APPID,AlipayReq.APP_PRIVATE_KEY,AlipayReq.FORMAT,AlipayReq.CHARSET,AlipayReq.ALIPAY_PUBLIC_KEY,AlipayReq.SIGN_TYPE);
            AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request
            alipayRequest.setReturnUrl( "http://domain.com/CallBack/return_url.jsp" );
            alipayRequest.setNotifyUrl( "http://domain.com/CallBack/notify_url.jsp" ); //在公共参数中设置回跳和通知地址
            AlipayTradePagePayModel model = new AlipayTradePagePayModel();
            model.setSubject("我是测试数据");
            model.setOutTradeNo("2120685");
            model.setTimeoutExpress("30m");
            model.setTotalAmount("0.01");
            model.setProductCode("QUICK_WAP_WAY");
            alipayRequest.setBizModel(model);
            String form= "" ;
            try  {
                    form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
                    System.out.println(form);
            }  catch  (AlipayApiException e) {
                    e.printStackTrace();
            }
    }

    public static   void  doPost(HttpServletRequest httpRequest,HttpServletResponse httpResponse)   throws  ServletException, IOException  {
                AlipayClient alipayClient =  new  DefaultAlipayClient(AlipayReq.URL,AlipayReq.APPID,AlipayReq.APP_PRIVATE_KEY,AlipayReq.FORMAT,AlipayReq.CHARSET,AlipayReq.ALIPAY_PUBLIC_KEY,AlipayReq.SIGN_TYPE);
                AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request
                alipayRequest.setReturnUrl( "http://domain.com/CallBack/return_url.jsp" );
                alipayRequest.setNotifyUrl( "http://domain.com/CallBack/notify_url.jsp" ); //在公共参数中设置回跳和通知地址
                AlipayTradePagePayModel model = new AlipayTradePagePayModel();
                model.setSubject("我是测试数据");
                model.setOutTradeNo("2120685");
                model.setTimeoutExpress("30m");
                model.setTotalAmount("0.01");
                model.setProductCode("QUICK_MSECURITY_PAY");
                alipayRequest.setBizModel(model);
                String form= "" ;
                try  {
                        form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
                }  catch  (AlipayApiException e) {
                        e.printStackTrace();
                }
                httpResponse.setContentType( "text/html;charset="  + AlipayReq.CHARSET);
                httpResponse.getWriter().write(form); //直接将完整的表单html输出到页面
                httpResponse.getWriter().flush();
                httpResponse.getWriter().close();
        }

    @RequestMapping("/callback")
    @ResponseBody
    public  String alipayCallBack(HttpServletRequest request)
    {
            Map<String, String> params = convertRequestParamsToMap(request); // 将异步通知中收到的待验证所有参数都存放到map中
            String paramsJson = JSON.toJSONString(params);
            String  tradeNo= params.get("trade_no");
            String  outTradeNo=params.get("out_trade_no");
            String  trade_status=params.get("trade_status");
            logger.info("支付宝回调，{}", paramsJson);
            try {
                    // 调用SDK验证签名
                    boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayReq.ALIPAY_PUBLIC_KEY, AlipayReq.CHARSET, AlipayReq.SIGN_TYPE);
                    if (signVerified) {
                        //交易成功
                        if (trade_status.equals("TRADE_SUCCESS")){
                            TransactionDO transactionDO=iTransactionService.getById(outTradeNo);
                            if (transactionDO!=null){
                               //执行业务逻辑
                                TransactionDO transactionDO1=new TransactionDO();
                                transactionDO1.setOutSerialNo(outTradeNo);
                                transactionDO1.setPayType("finished");
                                iTransactionService.updateById(transactionDO1);
                            }
                        }
                         return "success";
                    } else {
                            logger.info("支付宝回调签名认证失败，signVerified=false, paramsJson:{}", paramsJson);
                            return "failure";
                    }
            } catch (AlipayApiException e) {
                    logger.error("支付宝回调签名认证失败,paramsJson:{},errorMsg:{}", paramsJson, e.getMessage());
                    return "failure";
            }
    }

     // 将request中的参数转换成Map
    private static Map<String, String> convertRequestParamsToMap(HttpServletRequest request) {
                Map<String, String> retMap = new HashMap<String, String>();

                Set<Map.Entry<String, String[]>> entrySet = request.getParameterMap().entrySet();

                for (Map.Entry<String, String[]> entry : entrySet) {
                        String name = entry.getKey();
                        String[] values = entry.getValue();
                        int valLen = values.length;

                        if (valLen == 1) {
                                retMap.put(name, values[0]);
                        } else if (valLen > 1) {
                                StringBuilder sb = new StringBuilder();
                                for (String val : values) {
                                        sb.append(",").append(val);
                                }
                                retMap.put(name, sb.toString().substring(1));
                        } else {
                                retMap.put(name, "");
                        }
                }

                return retMap;
        }

    @PostMapping("/cochain")
    @ApiOperation("融资租赁上链测试")
    private  String  cochain() throws Exception {
        RiskInfoDO riskInfoDO=iRiskInfoService.getById(2289);
        aIliPayFinanceLease.financingStorage(riskInfoDO,"32451633");
        return  null;
    }

}
