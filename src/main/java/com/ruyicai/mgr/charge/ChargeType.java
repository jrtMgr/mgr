package com.ruyicai.mgr.charge;

import java.util.LinkedHashMap;
import java.util.Map;

public class ChargeType {

	private static Map<String, String> map = new LinkedHashMap<String, String>();
		
	static {
		map.put("y00003", "易宝");
		map.put("y00004", "易宝2");
		map.put("gyj001", "19pay");
		map.put("zfb001", "支付宝");
		map.put("ryc001", "如意彩");
		map.put("dna001", "DNA");
		map.put("msy001", "民生银行");
		map.put("um0001", "联动优势");
		map.put("syl001", "上海银联(有卡)");
		map.put("syl002", "上海银联(无卡)");
		map.put("cft001", "财付通");
		map.put("USSD01", "USSD");
		map.put("YZF001", "翼支付");
		map.put("szf001", "神州付");
		map.put("lhj001", "银联手机支付");
		map.put("yzz001", "银行转账");
		map.put("xrz001", "现金入账");
		map.put("lkl001", "拉卡拉");
	}
	
	public static Map<String, String> getMap() {
		return map;
	}
	
	public static String getMemo(String key) {
		return map.get(key);
	}
}
