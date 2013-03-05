package com.ruyicai.mgr.consts;

import java.math.BigDecimal;

public enum BetType {
	// 投注状态，0：失败，1：成功，2：处理中

	zhuihao(0, "追号"), taocan(1, "套餐"), touzhu(2, "投注"), hemai(3, "合买"), zengsong(
			4, "赠送"), zengsong_nosms(5, "赠送");

	BigDecimal state;

	String memo;

	public int intValue() {

		return state.intValue();
	}

	public BigDecimal value() {
		return state;
	}

	BetType(int val, String memo) {
		this.state = new BigDecimal(val);
		this.memo = memo;
	}
	
	public static String getBetType(BigDecimal state) {
		BetType[] betTypes = values();
		for(BetType betType : betTypes) {
			if(betType.state.equals(state)) {
				return betType.memo;
			}
		}
		return "";
	}
}
