package com.ruyicai.mgr.controller.giveOrder.util;

import java.util.Random;

public class F47104 extends AbsGenCode{

	@Override
	public String getBetCode() {
		return "0001"+getBetCode(33,6)+"~"+this.getRandomforBlue()+"^";
	}
	
	/**
	 * 生成蓝球号码
	 */
	public String getRandomforBlue(){
		int random=new Random().nextInt(16)+1;
		return random<10?"0"+random:random+"";
	}

	@Override
	public String getStringBetCode(String betcode) {
		StringBuffer ret = new StringBuffer();
		String[] s = betcode.split("~");
		String hong = s[0].substring(4);
		for (int i = 0; i < hong.length(); i+=2) {
			ret.append(hong.substring(i, i+2));
			if (i!= hong.length()-2) {
				ret.append(",");
			}
		}
		ret.append(" +"+s[1].substring(0, s[1].length()-1)).append(";");
		return ret.toString();
	}
}
