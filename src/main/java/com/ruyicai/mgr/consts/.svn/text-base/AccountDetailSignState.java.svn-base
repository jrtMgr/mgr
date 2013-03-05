package com.ruyicai.mgr.consts;

import java.math.BigDecimal;

public enum AccountDetailSignState {
	// 投注状态，-1：出账，0: dongjie, 1：进帐

	out(-1),unfreeze(-2),freeze(2), in(1);

	BigDecimal state;

	public int intValue() {
		
		return state.intValue();
	}
	
	public BigDecimal value() {
		return state;
	}

	AccountDetailSignState(int val) {
		this.state = new BigDecimal(val);
	}
}
