package com.ruyicai.mgr.controller.giveOrder.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class F47103_BH extends AbsGenCode {
	private List<String> codes = new ArrayList<String>();
	@Override
	public String getBetCode() {
		if(codes.size()==0){
			codes = getAllD3Code();
		}
		String code = "";
		if(codes.size()>0){
			int length = codes.size();
			Random random = new Random();
			int nextInt = random.nextInt(length);// 0-length之间的一个随机数字
			code = codes.remove(nextInt);
		}
		return "0001" + code + "^";
	}
	/**
	 * 生成1000种3D注码的组合
	 */
	private List<String> getAllD3Code()	{
		List<String> myCodes = new ArrayList<String>();
		for(int b=0;b<=9;b++){
			for(int s=0;s<=9;s++){
				for(int g=0;g<=9;g++){
					myCodes.add("0"+b+"0"+s+"0"+g);
				}	
			}	
		}
		return myCodes;
	}
}
