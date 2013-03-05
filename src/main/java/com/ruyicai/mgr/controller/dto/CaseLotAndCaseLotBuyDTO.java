package com.ruyicai.mgr.controller.dto;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;

import com.ruyicai.mgr.domain.CaseLot;
import com.ruyicai.mgr.domain.CaseLotBuy;

@RooJson
@RooJavaBean
public class CaseLotAndCaseLotBuyDTO {

	private CaseLot caseLot;
	
	private CaseLotBuy caseLotBuy;

	public CaseLotAndCaseLotBuyDTO() {
		super();
	}

	public CaseLotAndCaseLotBuyDTO(CaseLot caseLot, CaseLotBuy caseLotBuy) {
		super();
		this.caseLot = caseLot;
		this.caseLotBuy = caseLotBuy;
	}

}
