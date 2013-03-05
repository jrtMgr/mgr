package com.ruyicai.mgr.consts;

import java.math.BigDecimal;

public enum TaccountDetailSign {
	// 投注状态，0：失败，1：成功，2：处理中

	zhuihao(0), taocan(1), touzhu(2),hemai(3);

	BigDecimal state;

	public int intValue() {
		
		return state.intValue();
	}
	
	public BigDecimal value() {
		return state;
	}

	TaccountDetailSign(int val) {
		this.state = new BigDecimal(val);
	}
}
