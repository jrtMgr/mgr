package com.ruyicai.mgr.lot;

import java.util.LinkedHashMap;
import java.util.Map;

public class LotCenter {

	private static Map<String, String> map = new LinkedHashMap<String, String>();
	
	static {
		map.put("000002", "内蒙福彩");
		map.put("tc0001", "大赢家");
		map.put("R00001", "金软瑞彩");
		map.put("000004", "山东体彩");
		map.put("000006", "华彩");
		map.put("tx0001", "腾讯");
		map.put("000007", "竞彩");
		map.put("fcby", "风采博雅");
		map.put("000008", "重庆福彩");
		map.put("000009", "掌中弈");
		map.put("sdfcby", "山东丰彩博雅");
		map.put("zlren", "直立人");
		map.put("ymzt", "亿鸣中天");
		map.put("by11c5", "广东十一选五");
		map.put("tc0002", "出票中心");
		map.put("sublot", "世纪博雅");
	}
	
	public static Map<String, String> getMap() {
		return map;
	}
	
	public static String getName(String agencyno) {
		return map.get(agencyno);
	}
}
