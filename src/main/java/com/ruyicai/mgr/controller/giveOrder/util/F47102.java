package com.ruyicai.mgr.controller.giveOrder.util;

public class F47102 extends AbsGenCode{

	@Override
	public String getBetCode() {
		return "0001"+this.getBetCode(30,7)+"^";
	}

	@Override
	public String getStringBetCode(String betcode) {
		StringBuffer rets = new StringBuffer();
		betcode = betcode.substring(4,betcode.length()-1);
		for (int i = 0; i < betcode.length(); i+=2) {
			rets.append(betcode.substring(i, i+2));
			if (i != betcode.length()-2) {
				rets.append(",");
			}
		}
		return rets.append(";").toString();
	}
}
