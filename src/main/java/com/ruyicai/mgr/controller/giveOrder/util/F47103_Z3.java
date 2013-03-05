package com.ruyicai.mgr.controller.giveOrder.util;

import java.util.Random;

public class F47103_Z3 extends AbsGenCode{

	@Override
	public String getBetCode() {
		return "0101"+getBetCodeD33One()+"^";
	}
	/**
	 * 3D组三单式注码生成
	 */
	public String getBetCodeD33One(){
		Random ra=new Random();
		String betCode="";
		int[] code=new int[3];
		for(int i=0;i<code.length-1;i++){
			code[i] = ra.nextInt(10);
		}if(code[0]==code[1]){
			do{
			code[2]=ra.nextInt(10);
			}while(code[2]==code[0]);
			
		}else{
			code[2]=code[ra.nextInt(2)%2];
		}
		
		
		StringBuffer num =new StringBuffer();
		//排序
		Integer temp=0;
		for(int i=0;i<code.length;i++){
			for(int k=i+1;k<code.length;k++){
				int kk=code[k];
				int ii=code[i];
				if(kk<ii){
					temp=kk;
					code[k]=ii;
					code[i]=temp;
				}
		}
		}
		for(int i=0;i<code.length;i++){
			num.append("0").append(code[i]);
		}
		betCode=num.toString();
		return betCode;
	}
}
