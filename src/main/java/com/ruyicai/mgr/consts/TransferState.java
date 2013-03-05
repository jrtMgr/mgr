package com.ruyicai.mgr.consts;

import java.math.BigDecimal;

public enum TransferState {
	Valid(1, "有效"),
	Invalid(0,"无效");

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
	
	TransferState(int val, String memo) {
		this.state = new BigDecimal(val);
		this.memo = memo;
	}
	
	public static String getMemo(BigDecimal state) {
		TransferState[] transferStates = values();
		for(TransferState transferState : transferStates) {
			if(transferState.value().equals(state)) {
				return transferState.memo();
			}
		}
		return "";
	}
}
