package com.ruyicai.mgr.consts;

import java.math.BigDecimal;

public enum LotState {

	init(-1,"新创建"),
	
	fail(0, "失败"), 
	
	ok(1, "成功"), 
	
	processing(2, "处理中"), 
	
	preBetRequest(3, "预投注"), 
	
	preBetQueue(4, "已经发送到投注队列"), 
			
	jimaijifu(5, "即买即付"),
	
	LotCenterOk(6, "彩票中心已出票"),
	
	LotCenterFail(7, "彩票中心已失败"),

	cancel(8, "撤消"),
	
	pausebet(9, "暂停销售投注"),
	
	stopbet(10, "停止销售投注"),
	
	lotcentercannotbet(11, "彩票中心不支持"),
	
	InCache(12, "进入投注缓存");
	BigDecimal state;

	String memo;

	public BigDecimal getState() {
		return state;
	}

	public void setState(BigDecimal state) {
		this.state = state;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int intValue() {
		
		return state.intValue();
	}
	
	public BigDecimal value() {
		return state;
	}

	public String memo() {
		return memo;
	}

	LotState(int val, String memo) {
		this.state = new BigDecimal(val);
		this.memo = memo;
	}
	
	public static String getMemo(BigDecimal value) {
		LotState[] lotStates = values();
		for(LotState state : lotStates) {
			if(state.value().equals(value)) {
				return state.memo;
			}
		}
		return "";
	}
}
