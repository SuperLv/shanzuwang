package com.shanzuwang.util.http.esignature;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shanzuwang.config.pay.SignatureReq;
import com.shanzuwang.util.http.HttpClient;
import org.apache.commons.collections4.MapUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by Hw
 * 20/06/01 18:27
 */
public class HttpCfgHelper {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(HttpClient.class);
    private static final int MAX_TIMEOUT = 3000; //超时时间
    private static final int MAX_TOTAL=10; //最大连接数
    private static final int ROUTE_MAX_TOTAL=3; //每个路由基础的连接数
    private static final  int MAX_RETRY = 5; //重试次数
    private static PoolingHttpClientConnectionManager connMgr; //连接池
    private static HttpRequestRetryHandler retryHandler; //重试机制
    public static String doSignatureGet(String url) {
        try {
            HttpClient.getLogger();
            org.apache.http.client.HttpClient client = new DefaultHttpClient();
            //发送get请求
            HttpGet request = new HttpGet(url);
            request.addHeader("Content-Type", "application/json");
            request.addHeader("X-Tsign-Open-App-Id", SignatureReq.APP_ID);
            request.addHeader("X-Tsign-Open-Token",SignatureReq.acccs_token());
            HttpResponse response = client.execute(request);
            String strResult = EntityUtils.toString(response.getEntity());
            return strResult;
        } catch (IOException e) {
            System.out.println(e);
        }

        return null;
    }

    public static String postSignatureData(String requestStr,String uri,String charset){
        HttpURLConnection urlcon=null;
        InputStream in = null;
        OutputStream out = null;
        String result = null;
        try {
            URL url = new URL(uri);
            urlcon = (HttpURLConnection) url.openConnection();
            urlcon.setRequestMethod("POST");
            // 设置通用的请求属性
            urlcon.setRequestProperty("accept", "*/*");
            urlcon.setRequestProperty("connection", "Keep-Alive");
            urlcon.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            urlcon.setRequestProperty("Content-Type", "application/json");
            urlcon.setRequestProperty("X-Tsign-Open-App-Id",SignatureReq.APP_ID);
            urlcon.setRequestProperty("X-Tsign-Open-Token",SignatureReq.acccs_token());

            urlcon.setDoOutput(true);
            urlcon.setDoInput(true);
            urlcon.connect();// 获取连接
            out = urlcon.getOutputStream();
            out.write(requestStr.getBytes(charset));
            out.flush();
            in = urlcon.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(in, charset));
            StringBuffer bs = new StringBuffer();
            String line = null;
            while ((line = buffer.readLine()) != null) {
                bs.append(line);
            }
            result = bs.toString();
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            try{
                if (null!=in)
                    in.close();
                if (null!=out)
                    out.close();
                if (null!=urlcon)
                    urlcon.disconnect();
            }catch (Exception e1){

            }
        }
        return result;
    }

    public static JSONObject sendHttp(RequestType reqType, String url, Map<String, String> headers, Object param)
            throws DefineException{
        HttpRequestBase reqBase = reqType.getHttpType(url);
        HttpClient.getLogger();
        LOGGER.info("\n--->>开始向地址[{}]发起 [{}] 请求",url,reqBase.getMethod());
        LOGGER.info("--->>请求头为{}", JSON.toJSONString(headers));
        long startTime = System.currentTimeMillis();
        CloseableHttpClient httpClient = getHttpClient();
        //设置请求url
        config(reqBase);

        //设置请求头
        if(MapUtils.isNotEmpty(headers)) {
            for(Map.Entry<String, String> entry :headers.entrySet()) {
                reqBase.setHeader(entry.getKey(), entry.getValue());
            }
        }

        //添加参数 参数是json字符串
        if(param != null && param instanceof String) {
            String paramStr = String.valueOf(param);
            LOGGER.info("--->>请求参数为：{}", paramStr);
            ((HttpEntityEnclosingRequest) reqBase).setEntity(
                    new StringEntity(String.valueOf(paramStr), ContentType.create("application/json", "UTF-8")));
        }
        //参数时字节流数组
        else if(param != null && param instanceof byte[]) {
            LOGGER.info("--->>请求参数为文件流");
            byte[] paramBytes = (byte[])param;
            ((HttpEntityEnclosingRequest) reqBase).setEntity(new ByteArrayEntity(paramBytes));
        }


        //响应对象
        CloseableHttpResponse res = null;
        //响应内容
        String resCtx = null;
        try {
            //执行请求
            res = httpClient.execute(reqBase);
            LOGGER.info("--->>执行请求完毕，响应状态：{}",res.getStatusLine());

            if(res.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                throw new DefineException("--->>HTTP访问异常:"+res.getStatusLine());
            }

            //获取请求响应对象和响应entity
            HttpEntity httpEntity = res.getEntity();
            if(httpEntity != null) {
                resCtx = EntityUtils.toString(httpEntity,"utf-8");
                LOGGER.info("--->>获取响应内容：{}",resCtx);
            }

        } catch (Exception e) {
            throw new DefineException("请求失败",e);
        }finally {
            if(res != null) {
                try {
                    res.close();
                } catch (IOException e) {
                    throw new DefineException("--->>关闭请求响应失败",e);
                }
            }
        }
        long endTime = System.currentTimeMillis();
        LOGGER.info("--->>请求执行完毕，耗费时长：{} 秒",(endTime - startTime) / 1000);
        return JSONObject.parseObject(resCtx);
    }

    /**
     * @description 请求头和超时时间配置
     *
     * @param httpReqBase
     * @author 宫清
     * @date 2019年7月11日 上午12:42:34
     */
    private static void config(HttpRequestBase httpReqBase) {
        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(MAX_TIMEOUT)
                .setConnectTimeout(MAX_TIMEOUT)
                .setSocketTimeout(MAX_TIMEOUT)
                .build();
        httpReqBase.setConfig(requestConfig);
    }

    /**
     * @description 获取HttpClient
     *
     * @return
     * @author 宫清
     * @date 2019年7月11日 上午1:08:25
     */
    private static CloseableHttpClient getHttpClient() {
        return HttpClients.custom()
                .setConnectionManager(connMgr)
                .setRetryHandler(retryHandler)
                .build();
    }
}
