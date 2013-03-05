package com.ruyicai.mgr.util;

import java.util.Random;

public class RandomUtil {

	public static String genRandomNum(int n) {
		Random random = new Random();
		String sRand = "";
		for (int i = 0; i < n; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
		}
		if (sRand.substring(0, 1).equals("0")) {
			sRand = "1" + sRand.substring(1);
		}
		return sRand;
	}
}
