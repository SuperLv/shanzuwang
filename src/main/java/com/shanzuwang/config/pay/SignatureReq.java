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


    /***
     * 计算字符串的Content-MD5
     * @param str 文件路径
     * @return
     */
    public static String getStringContentMD5(String str) {
        // 获取文件MD5的二进制数组（128位）
        byte[] bytes = getFileMD5Bytes1282(str);
        // 对文件MD5的二进制数组进行base64编码
        return new String(Base64.encodeBase64(bytes));
    }

    /***
     * 获取文件MD5-二进制数组（128位）
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] getFileMD5Bytes1282(String filePath) {
        FileInputStream fis = null;
        byte[] md5Bytes = null;
        try {
            File file = new File(filePath);
            fis = new FileInputStream(file);
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[1024];
            int length = -1;
            while ((length = fis.read(buffer, 0, 1024)) != -1) {
                md5.update(buffer, 0, length);
            }
            md5Bytes = md5.digest();
            fis.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return md5Bytes;
    }

    public static String acccs_token() {
        String url= SignatureReq.ACCS_TOKEN+"?appId="+SignatureReq.APP_ID+"&secret="+SignatureReq.APP_KEY+"&grantType="+"client_credentials";
        String json= HttpClient.doGet(url);
        JSONObject jsonObject = (JSONObject) JSONObject.parse(json);
        JSONObject jsonObject1= (JSONObject) JSONObject.parse(jsonObject.getString("data"));
        return jsonObject1.getString("token");
    }

    public static void main(String[] args) {
        System.out.println(getStringContentMD5("C:\\Users\\Angell\\Downloads\\闪租合同-package-20200527-33452764.pdf"));
    }
}
