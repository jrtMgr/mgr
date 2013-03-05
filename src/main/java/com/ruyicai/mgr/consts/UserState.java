package com.ruyicai.mgr.consts;

import java.math.BigDecimal;

public enum UserState {

	normal(1, "正常"), closed(0, "关闭"),locked(2, "暂停");

	BigDecimal state;
	
	String memo;
	
	public String getMemo() {
		return memo;
	}
	
	public int intValue() {
		
		return state.intValue();
	}

	public BigDecimal value() {
		return state;
	}

	UserState(int val, String memo) {
		this.state = new BigDecimal(val);
		this.memo = memo;
	}
	
	public static String getMemo(int state) {
		for(UserState userState : values()) {
			if(state == userState.intValue()) {
				return userState.getMemo();
			}
		}
		return "";
	}
}
