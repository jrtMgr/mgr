package com.ruyicai.mgr.util;

import java.math.BigDecimal;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证工具类
 * 
 * @author Administrator
 * 
 */
public class VerificationAlgorithmUtil {

	// 多乐彩注码验证的正则
	public static String duoLeCai_Regex_R1 = "((0[1-9])|10|11)((\\s0[1-9])||\\s10|\\s11){0,5}";
	public static String duoLeCai_Regex_R2 = "((0[1-9])|10|11)((\\s0[1-9])||\\s10|\\s11){1,2}";
	public static String duoLeCai_Regex_R3 = "((0[1-9])|10|11)((\\s0[1-9])||\\s10|\\s11){2,3}";
	public static String duoLeCai_Regex_R4 = "((0[1-9])|10|11)((\\s0[1-9])||\\s10|\\s11){3,6}";
	public static String duoLeCai_Regex_R5 = "((0[1-9])|10|11)((\\s0[1-9])||\\s10|\\s11){4,9}";
	public static String duoLeCai_Regex_R6 = "((0[1-9])|10|11)((\\s0[1-9])||\\s10|\\s11){5,7}";
	public static String duoLeCai_Regex_R7 = "((0[1-9])|10|11)((\\s0[1-9])||\\s10|\\s11){6,8}";
	public static String duoLeCai_Regex_R8 = "((0[1-9])|10|11)((\\s0[1-9])||\\s10|\\s11){7}";
	public static String duoLeCai_Regex_Q2 = "((0[1-9])|10|11)((\\s0[1-9])||\\s10|\\s11){0,10}";
	public static String duoLeCai_Regex_Q3 = "((0[1-9])|10|11)((\\s0[1-9])||\\s10|\\s11){0,10}";
	public static String duoLeCai_Regex_Z2 = "((0[1-9])|10|11)((\\s0[1-9])||\\s10|\\s11){1,7}";
	public static String duoLeCai_Regex_Z3 = "((0[1-9])|10|11)((\\s0[1-9])||\\s10|\\s11){2,8}";
	public static String duoLeCai_Regex_DanTuo_RenXuan = "((0[1-9])|10|11)((\\s0[1-9])||\\s10|\\s11){2,10}";
	public static String duoLeCai_Regex_DanTuo_ZX3 = "((0[1-9])|10|11)((\\s0[1-9])||\\s10|\\s11){3,10}";

	/**
	 * 多乐彩胆拖验证
	 * @param realCode
	 * @param beishu
	 * @param regex
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean checkDuoLeCaiDTBetCode(String realCode,
			 BigDecimal beishu, String regex) {
		String[] split = realCode.split("\\$");
		// 正则验证
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(split[0]);
		if (!matcher.matches()) {
			return false;
		}
		Matcher matcher2 = pattern.matcher(split[1]);
		if (!matcher2.matches()) {
			return false;
		}
		//注码个数验证
		Vector vector0 = LotteryAlgorithmUtil.getStringArrayFromString(split[0]
				.trim().replaceAll(" ", ""));
		Vector vector1 = LotteryAlgorithmUtil.getStringArrayFromString(split[1]
				.trim().replaceAll(" ", ""));
		int sum = vector0.size() + vector1.size();
		if (sum < 3 || sum > 11) {
			return false;
		}
		// 判断注码是否重复
		Vector danmaVector = LotteryAlgorithmUtil
				.getStringArrayFromString(split[0].trim().replaceAll(" ", "")
						+ split[1].trim().replaceAll(" ", ""));
		if (!LotteryAlgorithmUtil.verifyRepeat(danmaVector)) {
			return false;
		}
		return true;
	}

	/**
	 * 验证多乐彩注码格式
	 * @param realCode
	 * @param beishu
	 * @param regex
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean checkDuoLeCaiBetCodeAndAmount(String realCode,
			 BigDecimal beishu, String regex) {
		// 正则验证
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(realCode);
		if (!matcher.matches()) {
			return false;
		}
		// 判断注码是否重复
		Vector danmaVector = LotteryAlgorithmUtil
				.getStringArrayFromString(realCode.trim().replaceAll(" ", ""));
		if (!LotteryAlgorithmUtil.verifyRepeat(danmaVector)) {
			return false;
		}
		return true;
	}

}
