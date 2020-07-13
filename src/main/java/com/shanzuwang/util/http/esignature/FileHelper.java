package com.shanzuwang.util.http.esignature;

import com.shanzuwang.util.http.esignature.DefineException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description 文件辅助类
 * @author 宫清
 * @date 2019年7月9日 下午4:54:07
 * @since JDK1.7
 */
public class FileHelper {

	// ------------------------------公有方法start--------------------------------------------

	/**
	 * 不允许外部创建实例
	 */
	private FileHelper() {
	}

	/**
	 * @description 获取文件字节流
	 * @param filePath {@link String} 文件地址
	 * @return
	 * @throws DefineException
	 * @date 2019年7月10日 上午9:17:00
	 * @author 宫清
	 */
	public static byte[] getBytes(String filePath) throws DefineException {
		File file = new File(filePath);
		FileInputStream fis = null;
		byte[] buffer = null;
		try {
			fis = new FileInputStream(file);
			buffer = new byte[(int) file.length()];
			fis.read(buffer);
		} catch (Exception e) {
			throw new DefineException("获取文件字节流失败", e.getCause());
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					throw new DefineException("关闭文件字节流失败", e.getCause());
				}
			}
		}
		return buffer;
	}

	/**
	 * @description 计算文件contentMd5值
	 *
	 * @param filePath {@link String} 文件路径
	 * @return
	 * @author 宫清
	 * @throws DefineException
	 * @date 2019年7月14日 下午1:35:41
	 */
	public static String getContentMD5(String filePath) throws DefineException {
		Encoder encoder = Base64.getEncoder();
		// 获取文件Md5二进制数组（128位）
		byte[] bytes = getFileMd5Bytes128(filePath);
		// 对文件Md5的二进制数组进行base64编码（而不是对32位的16进制字符串进行编码）
		return encoder.encodeToString(bytes);
	}

	/**
	 * @description 获取文件MIME类型
	 *
	 * @param filePath 文件路径
	 * @return
	 * @throws DefineException
	 * @author 宫清
	 * @date 2019年7月20日 下午8:24:44
	 */
	public static String getContentType(String filePath) throws DefineException {
		Path path = Paths.get(filePath);
		try {
			return Files.probeContentType(path);
		} catch (IOException e) {
			throw new DefineException("获取文件MIME类型失败", e);
		}
	}

	/**
	 * @description 根据文件路径，获取文件base64
	 *
	 * @param path
	 * @return	
	 * @throws DefineException
	 * @author 宫清
	 * @date 2019年7月21日 下午4:22:08
	 */
	public static String getBase64Str(String path) throws DefineException {
		InputStream is = null;
		try {
			is = new FileInputStream(new File(path));
			byte[] bytes = new byte[is.available()];
			is.read(bytes);
			return Base64.getEncoder().encodeToString(bytes);
		} catch (Exception e) {
			throw new DefineException("获取文件输入流失败",e);
		}finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					throw new DefineException("关闭文件输入流失败",e);
				}
			}
		}
	}
	
	/**
	 * @description 获取文件名称
	 *
	 * @param path 文件路径
	 * @return
	 * @author 宫清
	 * @date 2019年7月21日 下午8:21:16
	 */
	public static String getFileName(String path){
		return new File(path).getName();
	}
	// ------------------------------公有方法end----------------------------------------------

	// ------------------------------私有方法start--------------------------------------------

	/**
	 * @description 获取文件Md5二进制数组（128位）
	 *
	 * @param filePath {@link String} 文件路径
	 * @return
	 * @author 宫清
	 * @throws DefineException
	 * @date 2019年7月14日 下午1:36:42
	 */
	private static byte[] getFileMd5Bytes128(String filePath) throws DefineException {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(new File(filePath));
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[1024];
			int len = -1;
			while ((len = fis.read(buffer, 0, 1024)) != -1) {
				md5.update(buffer, 0, len);
			}
			return md5.digest();
		} catch (Exception e) {
			throw new DefineException("获取文件md5二进制数组失败", e);
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					throw new DefineException("关闭读写流失败", e);
				}
			}
		}
	}
	// ------------------------------私有方法end----------------------------------------------


    /**
     * 根据图片链接转为base64数据
     *
     * @param imageUrl
     * @return
     * @throws Exception
     */
    public static String getBase64ByUrl(String imageUrl) throws Exception {
        // new一个URL对象
        URL url = new URL(imageUrl);
        // 打开链接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 设置请求方式为"GET"
        conn.setRequestMethod("GET");
        // 超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        // 通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        // 得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(inStream);
		Encoder encoder = Base64.getEncoder();
        String s = encoder.encodeToString(data);
        return s;
    }

	/**
	 * 根据图片链接转为hash数据
	 *
	 * @param imageUrl
	 * @return
	 * @throws Exception
	 */
	public static String getHashByUrl(String imageUrl) throws Exception {
		// new一个URL对象
		URL url = new URL(imageUrl);
		// 打开链接
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// 设置请求方式为"GET"
		conn.setRequestMethod("GET");
		// 超时响应时间为5秒
		conn.setConnectTimeout(5 * 1000);
		// 通过输入流获取图片数据
		InputStream inStream = conn.getInputStream();
		return md5HashCode32(inStream);
	}

    private static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }

	/**
	 * java计算文件32位md5值
	 * @param fis 输入流
	 * @return
	 */
	public static String md5HashCode32(InputStream fis) {
		try {
			//拿到一个MD5转换器,如果想使用SHA-1或SHA-256，则传入SHA-1,SHA-256
			MessageDigest md = MessageDigest.getInstance("MD5");

			//分多次将一个文件读入，对于大型文件而言，比较推荐这种方式，占用内存比较少。
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = fis.read(buffer, 0, 1024)) != -1) {
				md.update(buffer, 0, length);
			}
			fis.close();

			//转换并返回包含16个元素字节数组,返回数值范围为-128到127
			byte[] md5Bytes  = md.digest();
			StringBuffer hexValue = new StringBuffer();
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;//解释参见最下方
				if (val < 16) {
					/**
					 * 如果小于16，那么val值的16进制形式必然为一位，
					 * 因为十进制0,1...9,10,11,12,13,14,15 对应的 16进制为 0,1...9,a,b,c,d,e,f;
					 * 此处高位补0。
					 */
					hexValue.append("0");
				}
				//这里借助了Integer类的方法实现16进制的转换
				hexValue.append(Integer.toHexString(val));
			}
			return hexValue.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}


}
