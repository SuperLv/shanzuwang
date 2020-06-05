package com.shanzuwang.util.http;

import ch.qos.logback.classic.Level;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.shanzuwang.config.Constants;
import com.shanzuwang.config.pay.SignatureReq;
import com.shanzuwang.service.SignatureService;
import com.shanzuwang.util.http.esignature.DefineException;
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
import org.springframework.beans.factory.annotation.Autowired;


import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

public class HttpClient {

	@Autowired
	static
	SignatureService signatureService;

	public static String doGet(String url) {
		try {
			getLogger();
			org.apache.http.client.HttpClient client = new DefaultHttpClient();
			//发送get请求
			HttpGet request = new HttpGet(url);
			HttpResponse response = client.execute(request);

			/**请求发送成功，并得到响应**/
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				/**读取服务器返回过来的json字符串数据**/
				String strResult = EntityUtils.toString(response.getEntity());

				return strResult;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}



	public static String postData(String requestStr,String uri,String charset,String dateTime){
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
			//拼接Authorization
			final Base64.Decoder decoder = Base64.getDecoder();
			final Base64.Encoder encoder = Base64.getEncoder();
			final String text = Constants.ACCOUNTSid+":"+dateTime;
			System.out.println(text);
			final byte[] textByte = text.getBytes("UTF-8");
			final String encodedText = encoder.encodeToString(textByte);
			urlcon.setRequestProperty("Authorization",encodedText);

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



	public static String postDataHttps(String requestStr,String uri,String charset){
		String result = "";
		BufferedReader in = null;
		HttpsURLConnection connection = null;
		DataOutputStream outStream = null;
		try {
			byte[] xmlbyte = requestStr.getBytes(charset);
			System.out.println("POST请求的URL为："+uri);
			System.out.println("POST请求的Json为："+requestStr);
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
			URL realUrl = new URL(uri);

			connection = (HttpsURLConnection) realUrl.openConnection();
			connection.setSSLSocketFactory(sc.getSocketFactory());
			connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");

			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestProperty("Content-Type", "application/json");
			// 建立实际的连接

			outStream = new DataOutputStream(connection.getOutputStream());

			double dd = System.currentTimeMillis();
			outStream.write(xmlbyte);
			outStream.flush();
			outStream.close();

			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			// System.out.println("获取的结果为："+result);
			System.out.println("1 is :" + (System.currentTimeMillis() - dd));
			System.out.println("Retuen string is :" + result);
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {

			}

		} catch (Exception e) {
			System.out.println("发送请求出现异常！" + e);
			 e.printStackTrace();

		}
		// 使用finally块来关闭输入流
		finally {
			try{
				if (null!=in)
					in.close();
				if (null!=outStream)
					outStream.close();
				if (null!=connection)
					connection.disconnect();
				}catch (Exception e1){

			}
		}
		return result;
	}

	public static String getDataHttps(String uri,String charset){
		String result = "";
		BufferedReader in = null;
		HttpsURLConnection connection = null;
		DataOutputStream outStream = null;
		try {
//			byte[] xmlbyte = requestStr.getBytes(charset);
			System.out.println("GET请求的URL为："+uri);
//			System.out.println("GET请求的Json为："+requestStr);
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
			URL realUrl = new URL(uri);

			connection = (HttpsURLConnection) realUrl.openConnection();
			connection.setSSLSocketFactory(sc.getSocketFactory());
			connection.setHostnameVerifier(new TrustAnyHostnameVerifier());
			connection.setDoOutput(true);
			connection.setRequestMethod("GET");

			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.setRequestProperty("Content-Type", "application/json");
			// 建立实际的连接

			outStream = new DataOutputStream(connection.getOutputStream());

			double dd = System.currentTimeMillis();
//			outStream.write(xmlbyte);
			outStream.flush();
			outStream.close();

			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			// System.out.println("获取的结果为："+result);
			System.out.println("1 is :" + (System.currentTimeMillis() - dd));
			System.out.println("Retuen string is :" + result);
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {

			}
		} catch (Exception e) {
			System.out.println("发送请求出现异常！" + e);
			 e.printStackTrace();

		}
		// 使用finally块来关闭输入流
		finally {
			try{
				if (null!=in)
					in.close();
				if (null!=outStream)
					outStream.close();
				if (null!=connection)
					connection.disconnect();
				}catch (Exception e1){

			}
		}
		return result;
	}





	/**
	 * 关闭HttpClient  日志打印
	 * */
	public  static  void getLogger()
	{
		Set<String> loggers = new HashSet<>(Arrays.asList("org.apache.http", "groovyx.net.http"));
		for(String log:loggers) {
			ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(log);
			logger.setLevel(Level.INFO);
			logger.setAdditive(false);
		}
	}

	/**
	 * @description 创建文件流上传 请求头
	 *
	 * @param contentMd5
	 * @param contentType
	 * @return
	 * @author 宫清
	 * @date 2019年7月20日 下午8:13:15
	 */
	public static Map<String, String> buildUploadHeader(String contentMd5, String contentType) {
		Map<String, String> header = Maps.newHashMap();
		header.put("Content-MD5", contentMd5);
		header.put("Content-Type", contentType);
		return header;
	}

	/**
	 * 通用请求头
	 * */
	public static Map<String, String> buildHeader() {
		Map<String, String> header = Maps.newHashMap();
		header.put("X-Tsign-Open-App-Id", SignatureReq.APP_ID);
		header.put("X-Tsign-Open-Token", SignatureReq.acccs_token());
		header.put("Content-Type","application/json");
		return header;
	}





	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}




	public static void main(String[] args) {
//		Person person=new Person();
//		person.setThirdPartyUserId("8402e392e1b94ec389538229c85a9534");
//		person.setName("林文年");
//		person.setIdNumber("440823199103180034");
//		person.setMobile("15601623426");
//		person.setEmail("1822984038@qq.com");
		System.out.println(SignatureReq.acccs_token());;
	}

}
