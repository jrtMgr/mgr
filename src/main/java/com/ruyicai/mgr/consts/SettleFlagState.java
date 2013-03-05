package com.ruyicai.mgr.consts;

import java.math.BigDecimal;

public enum SettleFlagState {

	NotExchange(0, "已开奖"),
	
	AlreadyExchange(1, "已兑奖"),
	
	NotDrawalottery(2, "未开奖"),
	
	NotHitPrize(3, "未中奖"),
	
	HitBigPrize(9, "中大奖");
	
	BigDecimal value;
	
	String memo;
	
	public BigDecimal value() {
		
		return value;
	}
	
	public int intValue() {
		
		return value.intValue();
	}
	
	SettleFlagState(int value, String memo) {
		
		this.value = new BigDecimal(value);
		this.memo = memo;
	}
	
	public static String getMemo(BigDecimal value) {
		SettleFlagState[] settleFlagStates = values();
		for(SettleFlagState settleFlagState : settleFlagStates) {
			if(settleFlagState.value().equals(value)) {
				return settleFlagState.memo;
			}
		}
		return "";
	}
}
