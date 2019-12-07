package com.wn.utils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

public class Shortt {

	public static List<String> getShort(String user_phone) {
		Random rd = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			sb.append(rd.nextInt(10));
		}
		String suijim = new String(sb);
		List<String> dxht = new ArrayList<>();
		System.out.println(suijim+"这是suijima");
		dxht.add(JuheDemo.getRequest2(user_phone, suijim));
		dxht.add(suijim);
		return dxht;
	}
}

class JuheDemo {
	public static final String DEF_CHATSET = "UTF-8";
	public static final int DEF_CONN_TIMEOUT = 30000;
	public static final int DEF_READ_TIMEOUT = 30000;
	public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

	// 配置您申请的KEY
	public static final String APPKEY = "67622e3642c49c237dfe14251ef8f7977";

	// 1.屏蔽词检查测
	public static void getRequest1() {
		String result = null;
		String url = "http://v.juhe.cn/sms/black";// 请求接口地址
		Map params = new HashMap();// 请求参数
		params.put("word", "");// 需要检测的短信内容，需要UTF8 URLENCODE
		params.put("key", APPKEY);// 应用APPKEY(应用详细页查询)

		try {
			result = net(url, params, "GET");
			/*
			 * JSONObject object = JSONObject.fromObject(result);
			 * if(object.getInt("error_code")==0){
			 * System.out.println(object.get("result")); }else{
			 * System.out.println(object.get("error_code")+":"+object.get(
			 * "reason")); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 2.发送短信
	public static String getRequest2(String shoujih, String suijim) {
		String result = null;
		String decode = URLEncoder.encode("#code#=" + suijim);
		System.out.println("生成的decode:" + decode);
		String code = decode;
		String url = "http://v.juhe.cn/sms/send";// 请求接口地址
		Map params = new HashMap();// 请求参数
		params.put("mobile", shoujih);// 接收短信的手机号码
		params.put("tpl_id", "197603");// 短信模板ID，请参考个人中心短信模板设置
		params.put("tpl_value", code);// 变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a
										// href="http://www.juhe.cn/news/index/id/50"
										// target="_blank">详细说明></a>
		params.put("key", APPKEY);// APPKEY(应用详细页查询)//#code#=1234&#company#=
		params.put("dtype", "json");// 返回数据的格式,xml或json，默认json

		try {
			result = net(url, params, "GET");
			System.out.println(result + "这是返回的短信参数。。。。。");
			// JSONObject object = JSONObject.fromObject(result);
			/*
			 * if(object.getInt("error_code")==0){
			 * System.out.println(object.get("result")); }else{
			 * System.out.println(object.get("error_code")+":"+object.get(
			 * "reason")); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {

	}

	/**
	 *
	 * @param strUrl
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @param method
	 *            请求方法
	 * @return 网络请求字符串
	 * @throws Exception
	 */
	public static String net(String strUrl, Map params, String method) throws Exception {
		HttpURLConnection conn = null;
		BufferedReader reader = null;
		String rs = null;
		try {
			StringBuffer sb = new StringBuffer();
			if (method == null || method.equals("GET")) {
				strUrl = strUrl + "?" + urlencode(params);
			}
			URL url = new URL(strUrl);
			conn = (HttpURLConnection) url.openConnection();
			if (method == null || method.equals("GET")) {
				conn.setRequestMethod("GET");
			} else {
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
			}
			conn.setRequestProperty("User-agent", userAgent);
			conn.setUseCaches(false);
			conn.setConnectTimeout(DEF_CONN_TIMEOUT);
			conn.setReadTimeout(DEF_READ_TIMEOUT);
			conn.setInstanceFollowRedirects(false);
			conn.connect();
			if (params != null && method.equals("POST")) {
				try {
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.writeBytes(urlencode(params));
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			InputStream is = conn.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sb.append(strRead);
			}
			rs = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (conn != null) {
				conn.disconnect();
			}
		}
		return rs;
	}

	// 将map型转为请求参数型
	public static String urlencode(Map<String, Object> data) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry i : data.entrySet()) {
			try {
				sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
}