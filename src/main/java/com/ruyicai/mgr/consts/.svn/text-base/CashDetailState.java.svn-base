package com.ruyicai.mgr.consts;

import java.math.BigDecimal;

public enum CashDetailState {

	Tixian(1, "待解决"),
	Yishenghe(103,"已审核"),
	Bohui(104, "驳回"),
	Chenggong(105, "成功"),
	Quxiao(106, "提现取消");

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

	CashDetailState(int val, String memo) {
		this.state = new BigDecimal(val);
		this.memo = memo;
	}
	
	public static String getMemo(BigDecimal state) {
		CashDetailState[] cashDetailStates = values();
		for(CashDetailState cashDetailState : cashDetailStates) {
			if(cashDetailState.value().equals(state)) {
				return cashDetailState.memo();
			}
		}
		return "";
	}
}
