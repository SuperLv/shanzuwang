package com.shanzuwang.config.pay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.internal.util.codec.Base64;
import com.shanzuwang.util.http.HttpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Hw
 * 20/05/28 10:27
 */
public class SignatureReq {
    private static  final String hosturl="https://smlopenapi.esign.cn";

    public static  final String APP_ID="7438805788";

    public static  final String APP_KEY="51830926febd1476a6088f5035f80eac";

    public static  final String CREATE_TEMPLATE=hosturl+"/v1/files/getUploadUrl";

    public static  final String ACCS_TOKEN=hosturl+"/v1/oauth2/access_token";

    public static  final String CREATE_PERSON=hosturl+"/v1/accounts/createByThirdPartyUserId";

    public static  final String CREATE_ORGANIZE=hosturl+"/v1/organizations/createByThirdPartyUserId";

    public static  final String CREATE_DOWNLOAD=hosturl+"/v1/files/";

    public static  final String CREATE_SIGNFLOWS=hosturl+"/v1/signflows";

    //流程文档下载
    public static   String CREATE_FLOW_DOCUMENTS=hosturl+"/v1/signflows/{0}/documents";

    //添加平台自动盖章签署区
    public static  String CREATE_PLATFORMSIGN=hosturl+"/v1/signflows/{0}/signfields/platformSign";

    //添加平台手动盖章签署区
    public  static String  CREATE_HANDSIGN=hosturl+"/v1/signflows/{0}/signfields/handSign";

    //根据关键字返回坐标
    public static  String QUERY_KEYWORD=hosturl+"/v1/signflows/{0}/documents/{1}/searchWordsPosition";

    //获取签署地址
    public static String QUERY_SIGN_URL=hosturl+"/v1/signflows/{0}/executeUrl";

    //签署流程开启
    public static String START_SIGNFLOWS=hosturl+"/v1/signflows/{0}/start";

    //通过模板创建文件
    public static String CREATEBY_TEMPLEATE=hosturl+"/v1/files/createByTemplate";


/**
 * e签宝具体详细接口👇
 * https://qianxiaoxia.yuque.com/books/share/23123f7b-8baa-4376-95d3-c03f22d07c5c/xuanyuanapi_go8uhr
 * */
    public static String acccs_token() {
        String url= SignatureReq.ACCS_TOKEN+"?appId="+SignatureReq.APP_ID+"&secret="+SignatureReq.APP_KEY+"&grantType="+"client_credentials";
        String json= HttpClient.doGet(url);
        JSONObject jsonObject = (JSONObject) JSONObject.parse(json);
        JSONObject jsonObject1= (JSONObject) JSONObject.parse(jsonObject.getString("data"));
        return jsonObject1.getString("token");
    }

    public static void main(String[] args) {

    }
}
