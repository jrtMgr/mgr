package com.ruyicai.mgr.consts;

import java.math.BigDecimal;

public enum LotInState {

	init(-1,"初始化"),
	
	fail(0, "失败"), 
	
	ok(1, "成功"), 
	
	processing(2, "处理中"), 
	
	LotCenterFail(7, "失败");

	BigDecimal state;

	String memo;

	public int intValue() {
		
		return state.intValue();
	}
	
	public BigDecimal value() {
		return state;
	}

	public String memo() {
		return memo;
	}

	LotInState(int val, String memo) {
		this.state = new BigDecimal(val);
		this.memo = memo;
	}
	
	public static String getMemo(BigDecimal value) {
		LotInState[] lotStates = values();
		for(LotInState state : lotStates) {
			if(state.value().equals(value)) {
				return state.memo;
			}
		}
		return "";
	}
}
