package com.ruyicai.mgr.consts;

import java.math.BigDecimal;

public enum TwinType {

	SMALL(0, "小奖"), BIG(9, "大奖");
	
	public BigDecimal value;
	
	public String memo;
	
	private TwinType(int value, String memo) {

		this.value = new BigDecimal(value);
		this.memo = memo;
	}
}
