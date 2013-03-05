package com.ruyicai.mgr.controller.giveOrder.util;

import java.util.Random;

public class F47103_SJ extends AbsGenCode {

	@Override
	public String getBetCode() {
		return "0001" + getBetCodeD3() + "^";
	}

	/**
	 * 3D注码随机生成(不能排序且可生成重复注码)
	 */
	private String getBetCodeD3() {
		Random ra = new Random();
		String betCode = "";
		int number = ra.nextInt(1000);
		String num = String.valueOf(number);
		int length = num.length();
		if (length == 3) {
			betCode = "0" + num.charAt(0) + "0" + num.charAt(1) + "0"
					+ num.charAt(2);
		} else if (length == 2) {
			betCode = "000" + num.charAt(0) + "0" + num.charAt(1);
		} else if (length == 1) {
			betCode = "00000" + num.charAt(0);
		}
		return betCode;
	}
}
