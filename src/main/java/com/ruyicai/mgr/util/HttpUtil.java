package com.ruyicai.mgr.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class HttpUtil {
	
	private static Logger logger = Logger.getLogger(HttpUtil.class);

	public static String post(String urlStr, String params) throws Exception {
		return new HttpUtil()._post(urlStr, params);
	}
	
	public String _post(String urlStr, String params) throws Exception {
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		conn.setDoOutput(true);
		conn.setDoInput(true);
		conn.setRequestMethod("POST");
		conn.setUseCaches(false);
		conn.setInstanceFollowRedirects(true);
		conn.setConnectTimeout(180000);
		conn.connect();
		OutputStream out = conn.getOutputStream();
		out.write(params.getBytes());
		out.close();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), "UTF-8"));
		String result = reader.readLine();
		reader.close();
		return result;
	}
	
	/**
	 * @param url
	 *            请求的新接口路径
	 * @return
	 * @throws IOException
	 */
	public static String getResultMessage(String url) throws IOException {
		IHttp http = new IHttp();
		logger.info("get方式:url=" + url);
		String re = http.getViaHttpConnection(url);
		return re;
	}
	
	/**
	 * 获取访问者IP
	 * 
	 * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
	 * 
	 * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
	 * 如果还不存在则调用Request .getRemoteAddr()。
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		} else {
			return ip;
		}
		if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		} else {
			// 多次反向代理后会有多个IP值，第一个为真实IP。
			int index = ip.indexOf(',');
			if (index != -1) {
				ip = ip.substring(0, index);
			}
		}
		return ip;
	}
	
	public static Map<String, Object> transferUrlParams2Map(String url) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (StringUtils.isBlank(url)) {
			return result;
		}
		String[] params = url.split("&");
		if (params.length > 0) {
			for (String param : params) {
				if (StringUtils.isNotBlank(param)) {
					String[] split = param.split("=");
					if (split.length > 1) {
						String key = split[0];
						String value = split[1];
						if (StringUtils.isNotBlank(value) && StringUtils.isNotBlank(key)) {
							result.put(key, value);
						}
					}
				}
			}
		}
		return result;
	}
}
