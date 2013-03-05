package com.ruyicai.mgr.consts;

import java.math.BigDecimal;


public enum TlotCtrlState {

	not_started(-1,"未开期"),
	open(0,"当前期"),
	ended(1,"已结期"),
	drawed(2,"已开奖"),
	prize_start(3, "算奖中"),
	prize_end(4, "算奖结束");
	
	private BigDecimal value;
	
	private String memo;
	
	private TlotCtrlState(int value,String memo){
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
		TlotCtrlState[] tlotCtrlStates = values();
		for(TlotCtrlState tlotCtrlState : tlotCtrlStates) {
			if(tlotCtrlState.value().equals(state)) {
				return tlotCtrlState.memo();
			}
		}
		return "";
	}
}
