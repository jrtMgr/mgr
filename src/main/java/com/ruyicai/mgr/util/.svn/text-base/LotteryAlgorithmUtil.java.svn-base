package com.ruyicai.mgr.util;

import java.util.Vector;

/**
 * 彩票算法工具类
 * 
 * @author Administrator
 * 
 */
public class LotteryAlgorithmUtil {

	/**
	 * 将输入的注码转换成数组
	 * 
	 * @param strArray
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Vector getStringArrayFromString(String strArray) {
		try {
			Vector v = new Vector();
			int l = strArray.length();
			int h = l / 2;
			int n = 0;
			for (int i = 0; i < h; i++) {
				String ss = strArray.substring(n, n + 2);
				n = n + 2;
				v.add(ss);
			}
			return v;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 验证注码是否重复
	 * 
	 * @param v
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean verifyRepeat(Vector v) {
		for (int i = 0; i < v.size(); i++) {
			for (int j = v.size() - 1; j > i; j--) {
				if (v.get(i).equals(v.get(j))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 任选公用(不适合R1) 计算注数 m 任1 m=1 任二m=2······· 以此类推 注:注数计算的调用应该放到在对用户输入的信息校验之后
	 * 
	 * @param zhuma
	 * @param m
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long zhushuR(String zhuma, int m) {
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		int count = v.size();
		long l = nchoosek(count, m);
		return l;
	}

	/**
	 * 计算从n中选出k个数的组合数
	 * 
	 * @param n
	 * @param k
	 * @return
	 */
	public static long nchoosek(int n, int k) {
		// 验证传入参数，n不能小于等于0，k不能小于0，n不能小于k；k=0的时候，规定组合数为1
		if (n <= 0 || k < 0 || n < k) {
			return -1;
		}
		if (k == 0 || n == k) {
			return 1;
		}
		// 按照组合数性质，在k大于n/2时，计算从n中选出n-k的值，减少计算量
		if (k > n / 2) {
			k = n - k;
		}
		// 通过组合数公式求组合数
		long result = multiplyByStep(n, k) / multiplyByStep(k, k);
		return result;
	}

	/**
	 * A(m,n) 算法
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static long multiplyByStep(int a, int b) {
		if (b <= 0 || b > a) {
			return -1;
		}
		int m = a;
		int n = a - b + 1;
		// 定义默认返回值
		long result = 1l;
		// m大于等于n，从n以1为步长乘到m;m小于n，从m以1为步长乘到n
		if (m >= n) {
			for (int i = n; i <= m; i++) {
				result = result * i;
			}
		} else {
			for (int i = m; i <= n; i++) {
				result = result * i;
			}
		}
		return result;
	}

	/**
	 * 注数计算 zhuma 为 030506,04 样式
	 * 
	 * @param zhuma1
	 * @param zhuma2
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long getQ2Zhushu(String zhuma1, String zhuma2) {
		Vector<String> v = new Vector<String>();
		Vector<String> v1 = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma1);
		v1 = LotteryAlgorithmUtil.getStringArrayFromString(zhuma2);
		long num1 = v.size();
		long num2 = v1.size();
		return num1 * num2;
	}

	/**
	 * 注数计算 zhuma 为 030506,04,03样式
	 * 
	 * @param zhuma1
	 * @param zhuma2
	 * @param zhuma3
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long getQ3Zhushu(String zhuma1, String zhuma2, String zhuma3) {
		Vector<String> v = new Vector<String>();
		Vector<String> v1 = new Vector<String>();
		Vector<String> v2 = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma1);
		v1 = LotteryAlgorithmUtil.getStringArrayFromString(zhuma2);
		v2 = LotteryAlgorithmUtil.getStringArrayFromString(zhuma3);
		long num1 = v.size();
		long num2 = v1.size();
		long num3 = v2.size();
		return num1 * num2 * num3;
	}

	/**
	 * 选前二组选 注数计算
	 * 
	 * @param zhuma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long zhushuZ2(String zhuma) {
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		int count = v.size();
		long l = nchoosek(count, 2);
		return l;
	}

	/**
	 * 选前3组选 注数计算
	 * 
	 * @param zhuma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long zhushuZ3(String zhuma) {
		Vector<String> v = new Vector<String>();
		v = LotteryAlgorithmUtil.getStringArrayFromString(zhuma);
		int count = v.size();
		long l = nchoosek(count, 3);
		return l;
	}

	/**
	 * 胆拖R2 注数计算 danma 为1位 拖码最少为3位
	 * 
	 * @param danma
	 * @param tuoma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long DanTuoR2zhushu(String danma, String tuoma) {
		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		int count = v.size();
		long l = 0;
		if (count == 1) {
			Vector<String> v1 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
			l = nchoosek(v1.size(), 1);
		}
		return l;
	}

	/**
	 * 胆拖R3 注数计算 danma 为1位 拖码最少为3位
	 * 
	 * @param danma
	 * @param tuoma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long DanTuoR3zhushu(String danma, String tuoma) {
		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		Vector<String> v1 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
		int count = v.size();
		if (count == 1) {
			long l = 0;
			l = nchoosek(v1.size(), 2);
			return l;
		}
		int count1 = v1.size();
		return count1;

	}

	/**
	 * 胆拖R4注数计算
	 * 
	 * @param danma
	 * @param tuoma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long DanTuoR4zhushu(String danma, String tuoma) {
		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		int count = v.size();
		long l = 0;
		if (count == 3) {
			Vector<String> v1 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
			l = v1.size();
		} else {
			Vector<String> v1 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
			l = nchoosek(v1.size(), 2);
		}
		return l;
	}

	/**
	 * 胆拖R5注数计算
	 * 
	 * @param danma
	 * @param tuoma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long DanTuoR5zhushu(String danma, String tuoma) {
		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		Vector<String> v1 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
		int count = v.size();
		long l = 0;
		if (count == 4) {
			l = v1.size();
		} else if (count == 3) {
			l = nchoosek(v1.size(), 2);
		} else if (count == 2) {
			l = nchoosek(v1.size(), 3);
		} else if (count == 1) {
			l = nchoosek(v1.size(), 4);
		}
		return l;
	}

	/**
	 * 胆拖R6注数计算
	 * 
	 * @param danma
	 * @param tuoma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long DanTuoR6zhushu(String danma, String tuoma) {
		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		Vector<String> v1 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
		int count = v.size();
		long l = 0;
		if (count == 5) {
			l = v1.size();
		} else if (count == 4) {
			l = nchoosek(v1.size(), 2);
		} else if (count == 3) {
			l = nchoosek(v1.size(), 3);
		} else if (count == 2) {
			l = nchoosek(v1.size(), 4);
		} else if (count == 1) {
			l = nchoosek(v1.size(), 5);
		}
		return l;
	}

	/**
	 * 胆拖R7注数计算
	 * 
	 * @param danma
	 * @param tuoma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long DanTuoR7zhushu(String danma, String tuoma) {
		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		Vector<String> v1 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
		int count = v.size();
		long l = 0;
		if (count == 6) {
			l = v1.size();
		} else if (count == 5) {
			l = nchoosek(v1.size(), 2);
		} else if (count == 4) {
			l = nchoosek(v1.size(), 3);
		} else if (count == 3) {
			l = nchoosek(v1.size(), 4);
		} else if (count == 2) {
			l = nchoosek(v1.size(), 5);
		} else if (count == 1) {
			l = nchoosek(v1.size(), 6);
		}
		return l;
	}

	/**
	 * 胆拖R8注数计算
	 * 
	 * @param danma
	 * @param tuoma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long DanTuoR8zhushu(String danma, String tuoma) {
		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		Vector<String> v1 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
		int count = v.size();
		long l = 0;
		if (count == 7) {
			l = v1.size();
		} else if (count == 6) {
			l = nchoosek(v1.size(), 2);
		} else if (count == 5) {
			l = nchoosek(v1.size(), 3);
		} else if (count == 4) {
			l = nchoosek(v1.size(), 4);
		} else if (count == 3) {
			l = nchoosek(v1.size(), 5);
		} else if (count == 2) {
			l = nchoosek(v1.size(), 6);
		} else if (count == 1) {
			l = nchoosek(v1.size(), 7);
		}
		return l;
	}

	/**
	 * 前二组选 注数计算
	 * 
	 * @param danma
	 * @param tuoma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long DanTuoZ2zhushu(String danma, String tuoma) {
		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		Vector<String> v1 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
		int count = v.size();
		long l = 0;
		if (count == 1) {
			l = v1.size();
		}
		return l;
	}

	/**
	 * 前三组选 注数计算
	 * 
	 * @param danma
	 * @param tuoma
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static long DanTuoZ3zhushu(String danma, String tuoma) {
		Vector<String> v = LotteryAlgorithmUtil.getStringArrayFromString(danma);
		Vector<String> v1 = LotteryAlgorithmUtil.getStringArrayFromString(tuoma);
		int count = v.size();
		long l = 0;
		if (count == 1) {
			l = nchoosek(v1.size(), 2);
		} else if (count == 2) {
			l = v1.size();
		}
		return l;
	}

	/**
	 * 将注码数组转换成带","的字符串
	 * 
	 * @param stringArray
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String joinStringArrayWithComma(Vector v) {
		String resultStr = "";
		if (v == null || v.size() == 0) {
			return "";
		} else {
			for (int i = 0; i < v.size(); i++) {
				resultStr += v.get(i) + ",";
			}
			if (resultStr.charAt(resultStr.length() - 1) == ',') {
				resultStr = resultStr.substring(0, resultStr.length() - 1);
			}
			return resultStr;
		}
	}

	/**
	 * 将3D注码中的"0"去掉
	 * 
	 * @param str
	 * @return
	 */
	public static String removeZero3D(String str) {
		StringBuffer sBuffer = new StringBuffer();
		int j = 1;
		for (int i = 0; i < str.length() / 2; i++) {
			sBuffer.append(str.substring(j, j + 1));
			j += 2;
		}
		return sBuffer.toString();
	}

	/**
	 * 每两个字符加一个空格
	 * 
	 * @param str
	 * @return
	 */
	public static String joinStringWithBlank(String str) {
		StringBuffer sBuffer = new StringBuffer();
		if (str != null && !str.trim().equals("") && str.length() % 2 == 0) {
			int len = str.length() / 2;
			for (int i = 0; i < len; i++) {
				if (i == len) {
					sBuffer.append(str.subSequence(i * 2, i * 2 + 2));
				} else {
					sBuffer.append(str.subSequence(i * 2, i * 2 + 2)).append(" ");
				}
			}
			// System.out.println(sb.toString());
		}
		return sBuffer.toString();
	}
}
