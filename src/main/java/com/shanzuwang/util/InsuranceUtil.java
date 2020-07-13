package com.shanzuwang.util;

import com.alibaba.fastjson.JSON;
import com.shanzuwang.bean.req.pay.CallBackInsurceReq;
import com.shanzuwang.config.pay.InsuranceReq;
import com.shanzuwang.util.http.HttpClient;


import java.util.Date;

/**
 * Created by Hw
 * 人保接口x
 * 20/06/18 9:50
 */
public class InsuranceUtil {


    public static void main(String[] args) {
         String  json="{\n" +
                 "    \"Head\":{\n" +
                 "        \"RequestId\":\"123123123\",\n" +
                 "        \"RequestType\":\"policyquery\"\n" +
                 "    },\n" +
                 "    \"Body\":{\n" +
                 "        \"MainId\":\"201810150000000001\",\n" +
                 "        \"ErrorCode\":\"000\",\n" +
                 "        \"ErrorMsg\":\"成功\",\n" +
                 "        \"PolicyNo\":\"PJBQ20183304********\",\n" +
                 "        \"PolicyUrl\":\"www.epicc.com.cn/******\",\n" +
                 "        \"PolicyHash\":\"fa12352315994de165dce815439f******\"\n" +
                 "    }\n" +
                 "}";
        CallBackInsurceReq callBackInsurceReq= JSON.parseObject(json,CallBackInsurceReq.class);
        System.out.println(callBackInsurceReq.getBody().getPolicyUrl());
    }

    //投保申请
     public String AddInsurance(InsuranceReq insuranceReq)
     {
         insuranceReq.setRequestId("254545411221222");
         insuranceReq.setRequestType("exchangequery");
         insuranceReq.setRequestTime("2018-10-12 23:23:01");
         insuranceReq.setMainId("3354701");
         insuranceReq.setTxHash("bf9d22e7d3346d5ad44365359557f3731e6dbe27ca66e05277ee19c247bc439d");
         insuranceReq.setSeller("bch1912003");
         insuranceReq.setGoodsType("3C");
         insuranceReq.setGoodsName("尼康相机二号");
         insuranceReq.setGoodsModel("D751");
         insuranceReq.setQuantity("1");
         insuranceReq.setBuyTime("2018-10-12 23:23:01");
         insuranceReq.setStartTime("2018-10-12 23:23:01");
         insuranceReq.setEndTime("2020-10-23 23:23:01");
         insuranceReq.setAlipayOrderNo("2020050122001477121446559333");
         insuranceReq.setAlipayOrderPrice("1254.50");
         insuranceReq.setAlipayOrderAmount("1000");
         insuranceReq.setPrice("841110");
         insuranceReq.setCertName("林文年");
         insuranceReq.setCertType("100");
         insuranceReq.setCertNo("440823199103180034");
         insuranceReq.setCertMobile("18569547056");
         insuranceReq.setConfirmTime("2018-10-15 23:23:01");
         insuranceReq.setInsuredIdNo("91440300359291078L");
         insuranceReq.setInsuredName("美邦电脑租赁（深圳）有限公司");
         insuranceReq.setInsuredMobile("沪公网安备11010102002019号");
         insuranceReq.setInsuredMail("zhoujie@slease.cn");
         String json= HttpClient.postXmlHttps(getXmlInfo(insuranceReq),"https://hzhbtest.picczj.com:9443/bchireserver","utf-8");
         System.out.println(json);
         return json;
     }

     //缴费确认
     public  String AddPay(InsuranceReq insuranceReq)
     {
         String json= HttpClient.postXmlHttps(getXmlInfo(insuranceReq),"https://hzhbtest.picczj.com:9443/bchireserver","utf-8");
         System.out.println(json);
         return json;
     }


     public String getXmlInfo(InsuranceReq insuranceReq)
     {
         String xmlInfo="<?xml version=\"1.0\" encoding=\"UTF-8\"?><Request>\n" +
                 "  <Head>\n" +
                 "    <RequestId>"+insuranceReq.getRequestId()+"</RequestId>\n" +
                 "    <RequestType>"+insuranceReq.getRequestType()+"</RequestType>\n" +
                 "    <RequestTime>"+insuranceReq.getRequestTime()+"</RequestTime>\n" +
                 "    <Md5Value>"+MD5.encode(insuranceReq.getRequestId()+insuranceReq.getMainId()+"PicCTest1234")+"</Md5Value>\n" +
                 "  </Head>\n" +
                 "  <Body>\n" +
                 "    <MainId>"+insuranceReq.getMainId()+"</MainId>\n" +
                 "    <txHash>"+insuranceReq.getTxHash()+"</txHash>\n" +
                 "    <Seller>"+insuranceReq.getSeller()+"</Seller>\n" +
                 "    <GoodsType>"+insuranceReq.getGoodsType()+"</GoodsType>\n" +
                 "    <GoodsName>"+insuranceReq.getGoodsName()+"</GoodsName>\n" +
                 "    <GoodsModel>"+insuranceReq.getGoodsModel()+"</GoodsModel>\n" +
                 "    <Quantity>"+insuranceReq.getQuantity()+"</Quantity>\n" +
                 "    <BuyTime>"+insuranceReq.getBuyTime()+"</BuyTime>\n" +
                 "    <StartTime>"+insuranceReq.getStartTime()+"</StartTime>\n" +
                 "    <EndTime>"+insuranceReq.getEndTime()+"</EndTime>\n" +
                 "    <alipayOrderNo>"+insuranceReq.getAlipayOrderNo()+"</alipayOrderNo>\n" +
                 "    <alipayOrderPrice>"+insuranceReq.getAlipayOrderPrice()+"</alipayOrderPrice>\n" +
                 "    <alipayOrderAmount>"+insuranceReq.getAlipayOrderAmount()+"</alipayOrderAmount>\n" +
                 "    <alipayOrderTotalAmount>"+insuranceReq.getAlipayOrderTotalAmount()+"</alipayOrderTotalAmount>\n" +
                 "    <Price>"+insuranceReq.getPrice()+"</Price>\n" +
                 "    <CertName>"+insuranceReq.getCertName()+"</CertName>\n" +
                 "    <CertType>"+insuranceReq.getCertType()+"</CertType>\n" +
                 "    <CertNo>"+insuranceReq.getCertNo()+"</CertNo>\n" +
                 "    <CertMobile>"+insuranceReq.getCertMobile()+"</CertMobile>\n" +
                 "    <ConfirmTime>"+insuranceReq.getConfirmTime()+"</ConfirmTime>\n" +
                 "  </Body>\n" +
                 "  <Insured>\n" +
                 "    <InsuredIdNo>"+insuranceReq.getInsuredIdNo()+"</InsuredIdNo>\n" +
                 "    <InsuredName>"+insuranceReq.getInsuredName()+"</InsuredName>\n" +
                 "    <InsuredMobile>"+insuranceReq.getInsuredMobile()+"</InsuredMobile>\n" +
                 "    <InsuredAddress>"+insuranceReq.getInsuredAddress()+"</InsuredAddress>\n" +
                 "    <InsuredMail>"+insuranceReq.getInsuredMail()+"</InsuredMail>\n" +
                 "  </Insured>\n" +
                 "</Request>";
          return  xmlInfo;
     }

     public String getXmlfees(InsuranceReq insuranceReq)
     {
         String smde="<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                 "<Request>\n" +
                 " <Head>\n" +
                 " <RequestId>"+insuranceReq.getRequestId()+"</RequestId>\n" +
                 "  <RequestType>"+insuranceReq.getRequestType()+"</RequestType>\n" +
                 "  <RequestTime>"+insuranceReq.getRequestTime()+"</RequestTime>\n" +
                 "  <Md5Value>"+MD5.encode(insuranceReq.getRequestId()+insuranceReq.getMainId()+"PicCTest1234")+"</Md5Value>\n" +
                 " </Head>\n" +
                 " <Body>\n" +
                 "  <MainId>"+insuranceReq.getMainId()+"</MainId>\n" +
                 "  <ExchangeNo>"+insuranceReq.getExtendInfos()+"</ExchangeNo>\n" +
                 "  <Seller>"+insuranceReq.getSeller()+"</Seller>\n" +
                 "  <txHash>"+insuranceReq.getTxHash()+"</txHash>\n" +
                 "  <AlipayId>"+insuranceReq.getAlipayId()+"</AlipayId>\n" +
                 "  <Premium>"+insuranceReq.getPremium()+"</Premium> \n" +
                 "  <PayTime>"+insuranceReq.getPayTime()+"</PayTime> \n" +
                 " </Body>\n" +
                 "</Request>";
         return smde;
     }
}
