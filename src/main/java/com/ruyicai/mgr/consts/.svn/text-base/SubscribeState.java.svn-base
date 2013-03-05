package com.ruyicai.mgr.consts;

public enum SubscribeState {

	NORMAL(0, "正常"),
	ZHANTING(1, "暂停"),
	ZHUXIAO(2, "注销"),
	JIESHU(3, "结束");
	
	private int value;
	
	private String memo;
	
	private SubscribeState(int value, String memo) {
		this.value = value;
		this.memo = memo;
	}
	
	public static String getMemo(int value) {
		SubscribeState[] subscribeStates = values();
		for(SubscribeState subscribeState : subscribeStates) {
			if(subscribeState.value == value) {
				return subscribeState.memo;
			}
		}
		return "";
	}
}
