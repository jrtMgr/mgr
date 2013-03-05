package com.ruyicai.mgr.controller.giveOrder.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbsGenCode implements GeneratedCode{
	//33选6 countNum为33  getNum为6
	public String getBetCode(int countNUm,int getNum){
		List<Integer> randoms=new ArrayList<Integer>();
		Random ra=new Random();
		//生成7为不相同的随机数
		while(randoms.size()!=getNum){
			//生成一个不为零的随机数
			int random=ra.nextInt(countNUm+1);
			while(random==0){
				random=ra.nextInt(countNUm+1);
			}
			//判断是否有重复记录
			boolean panduan=true;
			for(int j=0;j<randoms.size();j++){
				if(randoms.get(j)==random){
					panduan=false;
					break;
				}
			}
			if(panduan){
				randoms.add(random);
			}
		}
		//排序
		Integer temp=0;
		for(int i=0;i<randoms.size();i++){
			for(int k=i+1;k<randoms.size();k++){
				int kk=randoms.get(k);
				int ii=randoms.get(i);
				if(kk<ii){
					temp=kk;
					randoms.set(k,ii);
					randoms.set(i,temp);
				}
			}
		}
		//小于10的数字加0
		String stb="";
		for(int i=0;i<randoms.size();i++){
			if(randoms.get(i)<10){
				stb=stb+"0"+randoms.get(i);
			}else{
				stb=stb+randoms.get(i);
			}
		}
		return stb.toString();
	}
	
	@Override
	public String getStringBetCode(String betcode) {
		betcode = betcode.substring(4, betcode.length()-1);
		StringBuffer rets = new StringBuffer();
		rets.append(betcode.substring(1, 2))
		.append(betcode.substring(3, 4))
		.append(betcode.substring(5, 6)).append(";");
		return rets.toString();
	}
}
