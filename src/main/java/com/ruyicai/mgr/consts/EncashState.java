package com.ruyicai.mgr.consts;

import java.math.BigDecimal;


public enum EncashState {
	encash_notstart1(-1, "未派奖"),
	encash_notstart(0, "未派奖"),
	encash_start(1, "派奖中"),
	encash_end(2,"派奖结束");
	
	private BigDecimal value;
	
	private String memo;
	
	private EncashState(int value,String memo){
		this.value = new BigDecimal(value);
		this.memo = memo;
	}
	
	public BigDecimal value() {
		return value;
	}
	
	public String memo() {
		return memo;
	}
	
	public static String getMemo(BigDecimal state) {
		EncashState[] tlotCtrlStates = values();
		for(EncashState tlotCtrlState : tlotCtrlStates) {
			if(tlotCtrlState.value().equals(state)) {
				return tlotCtrlState.memo();
			}
		}
		return "";
	}
}
