package com.ruyicai.mgr.consts;

import java.math.BigDecimal;

public enum CashDetailType {

	Yihang(1, "银行"),
	Zhifubao(2,"支付宝");

	BigDecimal type;

	String memo;

	public int intValue() {
		
		return type.intValue();
	}
	
	public BigDecimal value() {
		return type;
	}

	public String memo() {
		return memo;
	}

	CashDetailType(int val, String memo) {
		this.type = new BigDecimal(val);
		this.memo = memo;
	}
	
	public static String getMemo(BigDecimal type) {
		CashDetailType[] cashDetailTypes = values();
		for(CashDetailType cashDetailType : cashDetailTypes) {
			if(cashDetailType.value().equals(type)) {
				return cashDetailType.memo();
			}
		}
		return "";
	}
}
