package com.ruyicai.mgr.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	public static boolean isEmpty(String str) {
		if (StringUtils.isEmpty(str))
			return true;
		if ("".equals(str.trim()))
			return true;
		return false;
	}

	public static boolean isEmpty(Character c) {
		if (null == c)
			return true;
		if ("".equals(c))
			return true;
		return false;
	}

	public static boolean isInt(String str) {
		return str.matches("^[0-9]*$");
	}

	// 嗖付支付传过来的金额是"分"
	public static boolean isFen(String str) {
		return str.matches("^[0-9]+$");
	}
	
	/**
	 * 结束时间默认为当前时间
	 * @return
	 */
	public static String getEndTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(new Date());
	}
	
	public static String join(String split, String... values) {
		StringBuilder builder = new StringBuilder();
		for(String s : values) {
			builder.append(s).append(split);
		}
		if(!isEmpty(split)) {
			builder.deleteCharAt(builder.length() - 1);
		}
		return builder.toString().trim();
	}
}
