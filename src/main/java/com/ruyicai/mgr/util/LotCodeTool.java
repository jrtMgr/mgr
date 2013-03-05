package com.ruyicai.mgr.util;

import java.util.Collections;
import java.util.Random;
import java.util.Vector;

import org.apache.log4j.Logger;

public class LotCodeTool {

	private static final Logger logger = Logger.getLogger(LotCodeTool.class);

	/**
	 * @num,生成注数
	 * @type ,T01001，大赢家，T01002,排列三
	 * */
	public static String makecode(int num, String type) {

		if (type.equals("T01001")) {
			logger.info("------生成超级大赢家的注码-------");
			logger.info("----------超级大赢家---投注的注数是：" + num);
			StringBuffer sb = new StringBuffer();

			if (num > 5) {
				logger.info("彩票注数不能大于5注");
				return null;
			}
			if (num == 0) {
				return null;
			}
			boolean flag = false;
			for (int i = 0; i < num; i++) {
				String scode = getCode(5, 30) + "-" + getCode(2, 12);
				if (flag)
					sb.append(";");
				sb.append(scode);
				flag = true;
			}
			return sb.toString();
		}
		if (type.equals("T01002")) {
			logger.info("-----排列三的投注注数是：" + num);
			StringBuffer sb = new StringBuffer();

			boolean flag = false;
			if (num > 5) {
				logger.info("彩票注数不能大于5注");
				return null;
			}
			if (num == 0) {
				logger.info("投注的注数不能为0");
				return null;
			}
			for (int i = 0; i < num; i++) {
				String scode = "1|" + get3DCode(3, 9);
				if (flag)
					sb.append(";");
				sb.append(scode);
				flag = true;
			}

			return sb.toString();
		}
		return null;
	}

	/**
	 * 解析3D，排列三注码
	 * 
	 * @param code
	 *            ,注码
	 * */
	public String get3DCode(String code) {
		if (code != "") {
			String[] codestr = code.split("\\|");
			String getcode = "";
			if (codestr.length == 2) {
				getcode = codestr[1];
			}
			String[] codenum = getcode.split(",");
			if (codenum.length == 3) {
				getcode = codenum[0] + " " + codenum[1] + " " + codenum[2];
			}
			return getcode;
		}
		return null;
	}

	/**
	 * @param count
	 *            最大到多少，如35，最大到35
	 * @param cap
	 *            ,总共产生几个数，如5，产生5个数
	 * @author fengqinyun 排序
	 * */
	public static Vector<Integer> getRandomIntArrayWithinRange(int count, int cap) {
		Vector<Integer> v = new Vector<Integer>();
		Vector<Integer> ret = new Vector<Integer>();
		for (int i = 1; i <= cap; i++) {
			v.add(new Integer(i));
		}
		// 确保不重复
		for (int i = 1; i <= count; i++) {
			int index = getRandomIntWithinRange(cap + 1 - i);
			ret.add(v.remove(index - 1));
		}
		Collections.sort(ret);
		return ret;
	}

	/**
	 * 3D随机产生
	 * 
	 * @author fengqinyun] 不排序
	 * */
	public static Vector<Integer> getRandomIntArrayWithinRange3D(int count, int cap) {
		Vector<Integer> v = new Vector<Integer>();
		Vector<Integer> ret = new Vector<Integer>();
		for (int i = 1; i <= cap; i++) {
			v.add(new Integer(i));
		}

		for (int i = 1; i <= count; i++) {
			int index = getRandomIntWithinRange(cap + 1 - i);
			ret.add(v.remove(index - 1));
		}
		return ret;
	}

	/**
	 * 大乐透注码产生
	 * 
	 * @author fengqinyun
	 * */
	public static String getCode(int count, int cap) {
		Vector<Integer> v = getRandomIntArrayWithinRange(count, cap);
		boolean flag = false;
		StringBuffer sbu = new StringBuffer();
		for (int i = 0; i < v.size(); i++) {
			if (flag)
				sbu.append(" ");
			String code = ((Integer) v.get(i)).toString();
			if (code.length() < 2)
				code = "0" + code;
			sbu.append(code);
			flag = true;
		}
		return sbu.toString();
	}

	/**
	 * 产生3D注码
	 * 
	 * @author fengqinyun
	 * */
	public static String get3DCode(int count, int cap) {
		Vector<Integer> v = getRandomIntArrayWithinRange3D(count, cap);
		boolean flag = false;
		StringBuffer sbu = new StringBuffer();
		for (int i = 0; i < v.size(); i++) {
			if (flag)
				sbu.append(",");
			String code = ((Integer) v.get(i)).toString();
			sbu.append(code);
			flag = true;
		}
		return sbu.toString();
	}

	public static int getRandomIntWithinRange(int cap) {
		return new Random().nextInt(cap) + 1;
	}
}
