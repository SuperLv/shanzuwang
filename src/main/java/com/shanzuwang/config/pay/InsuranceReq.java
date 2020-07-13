package com.shanzuwang.config.pay;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by Hw
 * 20/06/12 13:55
 */
@Data
public class InsuranceReq {

    /**
     * 请求编号
     * */
    private String RequestId;

    /**
     * 请求类型：exchangequery
     * */
    private String RequestType;

    /**
     * 请求时间
     * */
    private String RequestTime;

    /**
     * 数据加密校验字符串
     * */
    private String Md5Value;

    /**
     *订单编号
     * */
    private String MainId;

    /**
     *蚂蚁存证凭据（订单上链Hash）
     * */
    private String txHash;

    /**
     * 商家名称(由 PICC 分配 ID)
     * */
    private String Seller;

    /**
     * 产品类目：目前支持四个字段（3C，手机，珠宝，其他）
     * */
    private String GoodsType;

   /**
    * 物品名称
    * */
    private String GoodsName;

    /**
     * 具体型号
     * */
    private String GoodsModel;

    /**
     *产品数量
     * */
    private String Quantity;

    /**
     * 下 单 时 间 ： 格 式 为
     “YYYY-MM-DD HH:MI:SS”
     * */
    private String BuyTime;

    /**
     * 租 赁 起 始 时 间 ： 格 式 为
     “YYYY-MM-DD HH:MI:SS”
     * */
    private String StartTime;

    /**
     *租 赁 终 止 时 间 ： 格 式 为“YYYY-MM-DD HH:MI:SS”（如：2010-01-01 23:23:01）
     * */
    private String  EndTime;

    /**
     * 支付宝订单号
     * */
    private String alipayOrderNo;

    /**
     * 订单总价值（分），详见说明
     * */
    private String  alipayOrderPrice;

    /**
     * 租金金额（分），详见说明
     * */
    private String alipayOrderAmount;

    /**
     * 租金订单交易全租期总金额
     （分），详见说明
     * */
    private Integer alipayOrderTotalAmount;

     /**
      * 合 同 保 障 总 金 额 ， 即 保 额（分）
      * */
    private String Price;

    /**
     * 承租人姓名
     * */
    private String  CertName;

    /**
     * 承租人证件类型
     * */
    private String  CertType;

    /**
     *承租人证件号码
     */
    private  String  CertNo;

    /**
     * 承租人手机
     * */
    private  String CertMobile;

    /**
     * 承租人确认收货时间：格式为
     “YYYY-MM-DD HH:MI:SS”（如：
     2010-01-01 23:23:01）
     * */
    private String ConfirmTime;

    private String ExtendInfos;

    /**
     * 企业社会统一信用代码
     * */
    private String  InsuredIdNo;

    /**
     * 企业名称
     * */
    private String  InsuredName;

    /**
     * 企业联系方式
     * */
    private String  InsuredMobile;

    /**
     *企业地址
     * */
    private String InsuredAddress;

    /**
     * 企业联系邮箱
     * */
    private String InsuredMail;

    /**
     * 支付宝交易订单号

     * */
    private String AlipayId;

    /**
     * 支付宝交易金额
     * */
    private String Premium;

    /**
     * 支付时间
     * */
    private String PayTime;


}
