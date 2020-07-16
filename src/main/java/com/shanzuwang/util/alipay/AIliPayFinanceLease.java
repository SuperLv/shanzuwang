package com.shanzuwang.util.alipay;

import cn.com.antcloud.api.AntFinTechApiClient;
import cn.com.antcloud.api.AntFinTechProfile;
import cn.com.antcloud.api.twc.v1_0_0.model.LeaseOrderExtra;
import cn.com.antcloud.api.twc.v1_0_0.model.ProductInfo;
import cn.com.antcloud.api.twc.v1_0_0.request.*;
import cn.com.antcloud.api.twc.v1_0_0.response.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shanzuwang.config.pay.AlipayReq;
import com.shanzuwang.dao.dos.*;
import com.shanzuwang.service.*;
import com.shanzuwang.util.DateUtils;
import com.shanzuwang.util.http.HttpClient;
import com.shanzuwang.util.http.esignature.FileHelper;
import com.shanzuwang.web.qiniu.QiNiuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Hw
 * 20/06/23 10:15
 */
@Service
public class AIliPayFinanceLease {

    @Autowired
    private IPackageService iPackageService;
    @Autowired
    private IOrderService iOrderService;
    @Autowired
    private ISkuService iSkuService;
    @Autowired
    private ISpuService iSpuService;


    // 初始化客户端
   public static  AntFinTechProfile profile = AntFinTechProfile.getProfile(
            AlipayReq.ACCESS_URL,
            AlipayReq.ACCESS_KEY,
            AlipayReq.ACCESS_SECRET);

    public static void main(String[] args) throws Exception {
//        inform();
//        financingStorage(null);
//        uploadinGcommodity();
//        promise();
//        createRental();
//        paymentInform();
        String publickey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAl/kuo3pnvnXZVlMzhDp1AEwgWUXCkfDkk6rlFsE0ohQAQeDAVVD0y4kDtqmgTaRM6NiIpj5Xi3p0k//DZcHj1+KKbn92M4X6SvfkGh/QKGPf/AfEaP3vqT2eizAjdWXvMbvJ7Pu9NFRWQApjiJtFZHUE9mCa7ZMj+fbkrkqWptDkSnp9fMt2ZjlRJG4r9y5gR/262/U0aIY5QOljeO8WVcRqrXDqWB2rTDujUi6FvDySN2+AyjtL6KKJxlBtXqeuF90csF5uZdg40wD4nBKkVJjVVlpWt6vQA6unxsfrtUxr2TBwBpbbvKh5WwJSRTf7Q8zZDP2AisPpO1x8w71s7QIDAQAB";
        String privatekey="MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCJiaz1CXrHCbWGgqpdVJO38Gb3oStv6Vcrl/NZG1lVmBa3mlSUYCL4HZj5HNIJj4h9I6A7G2KjE+vY2jr2Flx/WyyRMiIwYuil7ttOP2ZY+sJF/L7vhq/NAasXZZCUa+M1WGsh3J2DbXCCnL5l5nh78csybdsSShiW4vl02zWOxWUk/teElwSUiC2jffbRSCzhZC2iamPEk58TFTWKPgE3KQdkggQ7dN/TFtN5olTuKKKhcx7lTa8PBCno8GbixcyRaBg+FkUZpVOUDIHCIjieDcZYgzQHVqjybpB9rLAr6KcdMEeifXLEmFzmW3TbLVEHhbBp9hBKCScU5WoutDovAgMBAAECggEAGDQXMMPrTE2EGVizPFte53ax2BAPwdDxWyWRWHP8o5hxHvxUuAu2+6zRt7z/pVkR7tuvHdey4vnlfjLhP18uBed4j1CgPZ2aTWEcTMfRbgUq3QAeZa5xD4WkoyhnagcWY9PcfogBXBiNPR7yTqJhP4aMeKJmc/CAJUVqGstcwHPvqDZEQyWys5OgCWe5a5p2webOQrAYJLWYjcqvEFiA3GFVhDDe+dyXzN7GwyCuSsNxmftMQus6zghiTTjxU1/kMsXsXWK27VwdxSEDhYxaLNkr2QqtHHrs0Y1QNbY6rEh9iHDPgZwNH3XTKh2wr/QvUutLcuRzLWtmrTHehWQsmQKBgQDX5mJZAZOW8R7oCCL7LdhsaTx/0JDhNFpda5CV/c1J3FhNG+tpUsrf7RxoW185Ci5tQ4WgEO4bJHcxVEoSWQPDHwzohMLiDpBomU1zAnMOVDq5Wu1kjOI4jCGYaw3FHyClm1pfAmHRzSH4Xdy2KTld/7ZXq0kW2uh0qOCHKXxajQKBgQCjFVOFhFLrciBRgP2PFkPcKc8FForCGJYwr+c/nJwEsTmAKOHT32rbLIe72flLRYoxhHEp5fysXyXyTr7S4JxVWKYwByAEA/pHRRbmSwTJK9m9JIHlbYkICkSsLhdUTCw67ss7p91TxpXgartep+aDjdKfqR/diP8tVi/pRjI2qwKBgQDVZXDl+snvkqpyu8AftwPIvAfBadwZzI5Y8j26E1+61qEsriD0PJIr3QiMGWw4S9jxxKCqQrGmgFeTmXWRRwyR6vnuRXL+z1hrGsPTnqMskZJtrPMhxKLBM7jtFztIEGlLZw9fA1K+dVxTSisingKG535KjxGyFnNXA6jRz+6K7QKBgCS7Q/GZqTeAoAjbfcDBqg0nmYoy1pP7B7D1nac1NerUo/favWqEMsQk1nmYEc9DXWnjw71FINEOKTTXSaGjl3KdoejFYg+lebSduheqo+xPpR4138/2CNw3u9nwE68X7QplCH9qQq6A1vcR8xvRw1i0E9GEPTofhiIG/ImVFvA7AoGBAL4FPR/Hyi0d4/K0DSZYG5Yt6YLFyaYrpZTTjMu8jEJYXHP8ESpVPLkSaZPIovtAEJg6DVlA47OiKDul36RhCQJuU6nu0GTrLpuf2cFhWb6ZMcLUQsWsMAP03mm+FYSMlCCWCHciZChyj5oq4rMYarQ4hNcPV61QOH2PJmKdjkhl";
        AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do"
                ,"2016073000126174",privatekey,"json",AlipayReq.CHARSET,publickey,AlipayReq.SIGN_TYPE);
        AlipaySystemOauthTokenRequest request = new AlipaySystemOauthTokenRequest();
        request.setCode("1bdb3b89cabf41cb8ff8369fc89aTD13");
        request.setGrantType("authorization_code");
        try {
            AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient.execute(request);
//            System.out.println(oauthTokenResponse.getAccessToken());
            System.out.println(JSON.toJSONString(oauthTokenResponse));
        } catch (AlipayApiException e) {
            //处理异常
            e.printStackTrace();
        }
    }

    /**
     * 通知注册
     * */
    public static  String  inform() throws InterruptedException {
        AntFinTechApiClient client = new AntFinTechApiClient(profile);
        // 构建请求
        CreateLeaseNotifyregisterRequest request = new CreateLeaseNotifyregisterRequest();
        request.setProductInstanceId(AlipayReq.PRODUCT_INSTANCEIT);
        request.setRegionName("CN-HANGZHOU-FINANCE");

        HttpClient.getLogger();
        // 发送请求，并且获取响应结果
        CreateLeaseNotifyregisterResponse response = client.execute(request);
        System.out.println(JSON.toJSONString(response));
        String json= String.valueOf(response.getCode());
        return   json;
    }

    /**
     *  上传⽤户信息
     *  url：https://apdevcenter.cloud.alipay.com/console/openapi/product/TWC/apis/twc.notary.lease.userinfo.create/versions/1.0/document?tenantName=CZKZJVCN
     * */
    public  String  financingStorage(RiskInfoDO riskInfoDO,String orderId) throws Exception {
        AntFinTechApiClient client = new AntFinTechApiClient(profile);

        // 构建请求
        CreateLeaseUserinfoRequest request = new CreateLeaseUserinfoRequest();
        request.setApplicationId("KKREQACN_WYPEYBRC");
        request.setAlipayUid("dongsheng.chen@slease.cn");
        request.setLeaseCorpId(riskInfoDO.getBizLicenseNum());
        request.setLeaseCorpName(riskInfoDO.getCompanyName());
        request.setLeaseCorpOwnerName(riskInfoDO.getLegalPersonName());
        request.setLoginId("15111115415555");
        request.setLoginTime(DateUtils.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        request.setLoginType(Long.valueOf(2));
        request.setOrderId(orderId);
        request.setUserEmail(riskInfoDO.getEmail());
        request.setUserId(riskInfoDO.getLegalPersonIdcard());
        request.setUserImageHash(FileHelper.getHashByUrl(QiNiuController.domain+"/"+riskInfoDO.getIdcardImgA()));
        request.setUserImageTxHash(TransactionDemo.userImageTxHash(riskInfoDO,orderId));

        PackageDO packageDO=iPackageService.getById(orderId);
        request.setUserName(packageDO.getName());
        request.setUserPhoneNumber(packageDO.getPhone());
        request.setUserType(Long.valueOf(2));
        request.setProductInstanceId(AlipayReq.PRODUCT_INSTANCEIT);
        request.setRegionName("CN-HANGZHOU-FINANCE");

        HttpClient.getLogger();
        // 发送请求，并且获取响应结果
        CreateLeaseUserinfoResponse response = client.execute(request);
        System.out.println(JSON.toJSONString(response));
        return response.getResponseData();
    }

    /**
     * 上传订单产品服务信息
     * url：https://apdevcenter.cloud.alipay.com/console/openapi/product/TWC/apis/twc.notary.lease.orderinfo.create/versions/1.0/document?tenantName=CZKZJVCN
     * */
    public  String  uploadinGcommodity(String orderId) throws InterruptedException {
        AntFinTechApiClient client = new AntFinTechApiClient(profile);
        // 构建请求
        CreateLeaseOrderinfoRequest request = new CreateLeaseOrderinfoRequest();

        //获取订单信息
        LambdaQueryWrapper<OrderDO> orderWrapper=new LambdaQueryWrapper<>();
        orderWrapper.eq(OrderDO::getPackageId,orderId);
        List<OrderDO> orderDOS=iOrderService.list(orderWrapper);

        BigDecimal depositFree = null;
        //产品信息填充
        List<ProductInfo> productInfos=new ArrayList<>();
        //总月租金
        Integer monthPrice=0;
        for (OrderDO orderDo:orderDOS) {
            SkuDO skuDO=iSkuService.getById(orderDo.getProductId());
            SpuDO spuDO=iSpuService.getById(skuDO.getSpuId());
            ProductInfo productInfo=new ProductInfo();
            productInfo.setSupplierId("34538f37389447a1a26fc94f0c15eb24");
            productInfo.setProductId("GYS2237");
            productInfo.setProductName(spuDO.getName());
            productInfo.setProductModel(skuDO.getName());
            productInfo.setProductNumber(Long.valueOf(orderDo.getProductNum()));
            productInfo.setProductPrice(Long.valueOf(orderDo.getPrice()));
            productInfo.setSupplierVersion(String.valueOf(0));
            productInfos.add(productInfo);
            ArrayList<Object> leftPeriodsPrices  = JSON.parseObject(orderDo.getLeftPeriodsPrice(), new TypeReference<ArrayList<Object>>(){});
            leftPeriodsPrices.get(0);
            monthPrice+=Integer.valueOf(orderDo.getPeriodsPrice());
        }
        request.setProductInfo(productInfos);


        request.setAcutalPreAuthFree(Long.valueOf(123400));
        request.setApplicationId("KKREQACN_WYPEYBRC");
        /**
         * 到期买断价格
         * */
        request.setBuyOutPrice(Long.valueOf(123400));
        request.setDepositFree(Long.valueOf(String.valueOf(depositFree)));

        //额外信息的主键
        List<LeaseOrderExtra> leaseOrderExtras=new ArrayList<>();
        LeaseOrderExtra leaseOrderExtra=new LeaseOrderExtra();
        leaseOrderExtra.setKey("userid");
        leaseOrderExtra.setValue("529be491e5a66f3a161b926f665d195d");
        leaseOrderExtras.add(leaseOrderExtra);
        request.setLeaseOrderExtra(leaseOrderExtras);

        //融资租赁服务协议文件hash
        request.setLeaseServiceFileHash("53d05dfba2c6589b2075e28537ffe63835c9c2a7b3eb658ac0acf15caaa09205");
        request.setLeaseServiceFileTxHash("c1bb8457e77c8a82649013f73da8201f3f21115e76039e1d28931766d8bc57d7");

        //订单信息
        request.setOrderCreateTime("2019-8-31 12:00:00");
        request.setOrderId(orderId);
        request.setOrderPayId("2019122822001498531404220500");
        request.setOrderPayType(Long.valueOf(3));
        request.setOrderPayTime("2019-8-31 12:00:00");



        request.setRentPricePerMonth(Long.valueOf(123400));
        request.setRentTerm(Long.valueOf(12));
        request.setUserAddress("浙江省杭州市西湖区xxx");
        request.setProductInstanceId(AlipayReq.PRODUCT_INSTANCEIT);
        request.setRegionName("CN-HANGZHOU-FINANCE");

        HttpClient.getLogger();
        // 发送请求，并且获取响应结果
        CreateLeaseOrderinfoResponse response = client.execute(request);
        System.out.println(JSON.toJSONString(response));
        return  response.getResponseData();
    }

    /**
     * 租赁服务平台上传承诺信息
     * url：https://apdevcenter.cloud.alipay.com/console/openapi/product/TWC/apis/twc.notary.lease.promise.create/versions/1.0/document?tenantName=CZKZJVCN
     * */
    public static  String promise() throws InterruptedException {
        AntFinTechApiClient client = new AntFinTechApiClient(profile);
        // 构建请求
        CreateLeasePromiseRequest request = new CreateLeasePromiseRequest();
        request.setApplicationId("KKREQACN_WYPEYBRC");
        request.setAuditMode(Long.valueOf(0));
        request.setClearingOrg("PEPKBZAR");
        request.setCreditOrg("WYPEYBRC");
        request.setLeaseAlipayUid("2088221536490224");
        request.setLimit(Long.valueOf(40));
        request.setOrderId("33452656");

        List<String> paydate=new ArrayList<>();
        paydate.add("2019-8-31 12:00:00");
        request.setPayDateList(paydate);

        List<Long> money=new ArrayList<>();
        money.add(Long.valueOf(123400));
        request.setPayMoneyList(money);
        request.setPayPeriod(Long.valueOf(1));
        request.setProductInstanceId(AlipayReq.PRODUCT_INSTANCEIT);
        request.setRegionName("CN-HANGZHOU-FINANCE");

        HttpClient.getLogger();
        // 发送请求，并且获取响应结果
        CreateLeasePromiseResponse response = client.execute(request);
        System.out.println(JSON.toJSONString(response));
        return null;
    }

   /**
    *  租赁系统上传租金归还记录 分期调用
    *  url：https://apdevcenter.cloud.alipay.com/console/openapi/product/TWC/apis/twc.notary.lease.rental.create/versions/1.0/document?tenantName=CZKZJVCN
    * */
   public static String createRental() throws InterruptedException {
       AntFinTechApiClient client = new AntFinTechApiClient(profile);

       // 构建请求
       CreateLeaseRentalRequest request = new CreateLeaseRentalRequest();
       request.setApplicationId("KKREQACN_WYPEYBRC");
       request.setCharge(Long.valueOf(0));
       request.setIsFinish(true);
       request.setLeaseTermIndex(Long.valueOf(1));
       request.setOrderId("33452656");
       request.setRemainRental(Long.valueOf(0));
       request.setRemainTerm(Long.valueOf(0));
       request.setRentalMoney(Long.valueOf(123400));
       request.setRentalReturnState(Long.valueOf(1));
       request.setReturnTime("2019-07-31 12:00:00");
       request.setReturnVoucherSerial("2019122822001498531404220500");
       request.setReturnVoucherType(Long.valueOf(1));
       request.setReturnWay(Long.valueOf(3));
       request.setProductInstanceId(AlipayReq.PRODUCT_INSTANCEIT);
       request.setRegionName("CN-HANGZHOU-FINANCE");

       HttpClient.getLogger();
       // 发送请求，并且获取响应结果
       CreateLeaseRentalResponse response = client.execute(request);
       System.out.println(JSON.toJSONString(response));
       return null;
   }

}
