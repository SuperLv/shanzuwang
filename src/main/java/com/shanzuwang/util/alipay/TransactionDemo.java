package com.shanzuwang.util.alipay;

import cn.com.antcloud.api.AntFinTechApiClient;
import cn.com.antcloud.api.AntFinTechProfile;
import cn.com.antcloud.api.twc.v1_0_0.model.Identity;
import cn.com.antcloud.api.twc.v1_0_0.model.Location;
import cn.com.antcloud.api.twc.v1_0_0.request.*;
import cn.com.antcloud.api.twc.v1_0_0.response.*;
import com.alibaba.fastjson.JSON;
import com.shanzuwang.config.pay.AlipayReq;
import com.shanzuwang.dao.dos.RiskInfoDO;
import com.shanzuwang.service.IRiskInfoService;
import com.shanzuwang.util.alipay.AIliPayFinanceLease;
import com.shanzuwang.util.http.HttpClient;
import com.shanzuwang.util.http.esignature.FileHelper;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionDemo {

    @Autowired
    public static IRiskInfoService iRiskInfoService;

    public static void main(String[] args) throws Exception {
//          FileTransaction();
//        passBackOrder();
//        testPost("https://hzhbtest.picczj.com/bchireserver");


//        Map<String, String> header = Maps.newHashMap();
//        header.put("user_name", "shanzu_testusr");
//        header.put("sign","e4ae742fdc79d22b");
//        header.put("api_name","credit.evaluation.share.api");
//        header.put("query_reason","LOAN_AUDIT");
//        header.put("params","{\"id_no\":\"440823199103180034\",\"name\":\"林文年\"}");
//        String json=HttpClient.post("https://starapi.afufintech.com/submit",header);
//        System.out.println(json);
    }

    //存证获取事务id
    public static CreateTransResponse AddTrans(RiskInfoDO riskInfoDO,String orderNumber) throws InterruptedException {
        // 初始化客户端
        AntFinTechApiClient client = new AntFinTechApiClient(AIliPayFinanceLease.profile);

        // 构建订单绑定信息，需要提供的参数有：订单号，商户订单号（值可为空），订单支付方式（默认填写Alipay）
        String orderInfo = "{\"orderNumber\":\""+orderNumber+"\", \"merchantOrderNumber\":\"11111\", \"paymentChannel\":\"Alipay\"}";

        // 构建请求
        CreateTransRequest createTransRequest = new CreateTransRequest();
        Identity customer = new Identity();
        customer.setCertName(riskInfoDO.getLegalPersonName());
        customer.setCertType("IDENTITY_CARD");
        // 测试时请替换为真实证件号码
        customer.setCertNo(riskInfoDO.getLegalPersonIdcard());
        customer.setMobileNo(riskInfoDO.getLegalPersonPhone());
        customer.setUserType("PERSON");
        createTransRequest.setCustomer(customer);
        createTransRequest.setProperties(orderInfo);
        createTransRequest.setTsr(true);
        createTransRequest.setProductInstanceId(AlipayReq.PRODUCT_INSTANCEIT);


        HttpClient.getLogger();
        // 发送请求，并且获取响应结果
        CreateTransResponse response = client.execute(createTransRequest);
        System.out.println(JSON.toJSONString(createTransRequest));
        return response;
    }

    public static void   passBackOrder() throws InterruptedException {
        // 初始化客户端
        AntFinTechApiClient client = new AntFinTechApiClient(AIliPayFinanceLease.profile);
        // 构建请求
        CreateLeaseOrderRequest request = new CreateLeaseOrderRequest();
        request.setAlipayOrderAmount(Long.valueOf(2342));
        request.setAlipayOrderNo("20191021346782544488890123455239");
        request.setAlipayOrderTotalAmount(Long.valueOf(54332));
        request.setDepositWaiveAmount(Long.valueOf(1000));
        request.setInsuranceCoverage(Long.valueOf(0));
        request.setInsuranceOrderNo("null");
        request.setItemName("test_name");
        request.setItemPrice(Long.valueOf(99999));
        request.setItemType("test_type");
        request.setProductInstanceId(AlipayReq.PRODUCT_INSTANCEIT);
        request.setMerchantAlipayAccount("example@email.com");
        request.setMerchantAlipayId("2088126548970327");
        request.setMerchantName("某某公司");
        request.setMerchantOrderNo("null");
        String termEnd="143452300000";
        request.setTenancyTermEnd(Long.valueOf(termEnd));
        String termStart="143452300000";
        request.setTenancyTermStart(Long.valueOf(termStart));
        request.setRegionName("CN-HANGZHOU-FINANCE");


        HttpClient.getLogger();
        // 发送请求，并且获取响应结果
        CreateLeaseOrderResponse response = client.execute(request);
        System.out.println(response.getSuccess()+response.getResultMsg()+response.getResultCode());
    }

    //文本存证
    public static  void  AddOrder(String transactionId) throws InterruptedException {
        // 初始化客户端
        AntFinTechApiClient client = new AntFinTechApiClient(AIliPayFinanceLease.profile);

        // 构建请求
        CreateTextRequest request = new CreateTextRequest();
        Location location = new Location();
        location.setCity("杭州");
        location.setImei("359426002899056");
        location.setImsi("460001357924680");
        location.setIp("0.0.0.0");
        location.setLatitude("66.33");
        location.setLongitude("23.26");
        location.setMacAddr("00-01-6C-06-A6-29");
        location.setProperties("{\"key\":\"value\"}");
        request.setLocation(location);
        request.setNotaryContent("123123");
        request.setPhase("123");
        request.setProperties("{\"key\":\"value\"}");
        request.setTransactionId(transactionId);
        request.setTsr(true);
        request.setProductInstanceId(AlipayReq.PRODUCT_INSTANCEIT);
        request.setRegionName("CN-HANGZHOU-FINANCE");

        // 发送请求，并且获取响应结果
        CreateTextResponse response = client.execute(request);
        System.out.println(response.getTxHash());
    }

    //文件内容存证
    public static String AddFile(CreateFileRequest request) throws Exception {
        // 初始化客户端
        AntFinTechApiClient client = new AntFinTechApiClient(AIliPayFinanceLease.profile);
        request.setProductInstanceId(AlipayReq.PRODUCT_INSTANCEIT);
        request.setRegionName("CN-HANGZHOU-FINANCE");
        // 发送请求，并且获取响应结果
        CreateFileResponse response = client.execute(request);
        System.out.println(JSON.toJSONString(response));
        return response.getTxHash();
    }

    public static  void  getTransaction(String transactionId) throws InterruptedException {
        // 初始化客户端
        AntFinTechApiClient client = new AntFinTechApiClient(AIliPayFinanceLease.profile);

        // 构建请求
        GetTransRequest request = new GetTransRequest();
        request.setTransactionId(transactionId);
        request.setProductInstanceId(AlipayReq.PRODUCT_INSTANCEIT);
        request.setRegionName("CN-HANGZHOU-FINANCE");

        // 发送请求，并且获取响应结果
        GetTransResponse response = client.execute(request);
        System.out.println(response.getFileUrl());
    }

    public static   CreateFileResponse  FileTransaction() throws InterruptedException {
        // 初始化客户端
        AntFinTechApiClient client = new AntFinTechApiClient(AIliPayFinanceLease.profile);

        // 构建请求
        CreateFileRequest request = new CreateFileRequest();
        request.setFileNotaryType("FILE_RAW");
        request.setHashAlgorithm("SHA256");
        Location location = new Location();
        location.setCity("杭州");
        location.setImei("359426002899056");
        location.setImsi("460001357924680");
        location.setIp("0.0.0.0");
        location.setLatitude("66.33");
        location.setLongitude("23.26");
        location.setMacAddr("00-01-6C-06-A6-29");
        location.setProperties("{\"key\":\"value\"}");
        request.setLocation(location);
        request.setNotaryFile("57597962f9f93933f0df880edc054300eb52c4397965879654b10c18d4b66fb3");
        request.setNotaryName("testfile");
        request.setPhase("123");
        request.setProperties("{\"key\":\"value\"}");
        request.setTransactionId("877c4383-9c83-477b-b7ec-03828a946e54");
        request.setTsr(true);
        request.setProductInstanceId(AlipayReq.PRODUCT_INSTANCEIT);
        request.setRegionName("CN-HANGZHOU-FINANCE");

        // 发送请求，并且获取响应结果
        CreateFileResponse response = client.execute(request);
        System.out.println(response.getTxHash());
        return response;
    }

    public static String userImageTxHash(RiskInfoDO riskInfoDO,String orderId) throws Exception {
        CreateTransResponse transResponse=AddTrans(riskInfoDO,orderId);
        CreateFileRequest request = new CreateFileRequest();
        String notaryFile= FileHelper.getBase64ByUrl("http://cdn.img.shanzuwang.com/"+riskInfoDO.getIdcardImgA());
        request.setNotaryFile(notaryFile);
        request.setNotaryName("testfile");
        request.setPhase("123");
        request.setTransactionId(transResponse.getTransactionId());
        getTransaction(transResponse.getTransactionId());
        return TransactionDemo.AddFile(request);
    }

}
