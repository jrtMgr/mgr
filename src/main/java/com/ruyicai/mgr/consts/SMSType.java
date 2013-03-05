package com.ruyicai.mgr.consts;

public enum SMSType {

	OPEN("open"),	// 开奖短信
	
	GIFT("gift"),	// 赠送短信
	
	HITSMALL("hitsmall"),	// 中小奖短信
	
	HITSMALLGIFT("hitsmallgift"),	// 中小奖赠送短信
	
	HITBIG("hitbig"),	// 中大奖短信
	
	HITBIGGIFT("hitbiggift"),	// 中大奖赠送短信
	
	ADDNUM("addnum"),	// 追号失败短信
	
	TAOCAN("taocan");	// 套餐金额不足短信
	
	private String value;

	public String getValue() {
		return value;
	}
	
	private SMSType(String value) {
		this.value = value;
	}
}
