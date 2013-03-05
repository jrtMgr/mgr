package com.ruyicai.mgr.consts;

import java.math.BigDecimal;

public enum OrderState {
	init(0), processing(1), finished(2);

	BigDecimal state;

	public int intValue() {
		
		return state.intValue();
	}
	
	public BigDecimal value() {
		return state;
	}

	OrderState(int val) {
		this.state = new BigDecimal(val);
	}
}
