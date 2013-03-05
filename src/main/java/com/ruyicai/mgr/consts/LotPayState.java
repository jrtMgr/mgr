package com.ruyicai.mgr.consts;

import java.math.BigDecimal;

public enum LotPayState {

	INIT(0,"未付款"),
	
	FREEZE(1, "冻结成功"),
	
	FREEZE_ERROR(-1, "冻结失败"),
	
	UNFREEZE(2, "解冻成功"), 
	
	UNFREEZE_ERROR(-2, "解冻失败"),
	
	DEDUCT(3, "扣款成功"), 
	
	DEDUCT_ERROR(-3, "扣款失败");

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

	LotPayState(int val, String memo) {
		this.state = new BigDecimal(val);
		this.memo = memo;
	}
}
