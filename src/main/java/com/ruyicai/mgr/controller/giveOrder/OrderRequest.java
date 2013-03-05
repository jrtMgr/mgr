package com.ruyicai.mgr.controller.giveOrder;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJson
@RooToString
@RooJavaBean
public class OrderRequest {

	String batchcode;
	String lotno;
	BigDecimal amt; 
	BigDecimal bettype;
	String userno;
	BigDecimal lotmulti;
	String buyuserno;
	String channel;
	String subchannel;
	BigDecimal paytype;
	BigDecimal oneamount;
	List<BetRequest> betRequests;
	/** 赠送彩票接受人手机号 */
	String reciverMobile;
}
