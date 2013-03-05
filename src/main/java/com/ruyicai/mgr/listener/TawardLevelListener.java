package com.ruyicai.mgr.listener;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruyicai.mgr.domain.TawardDetail;
import com.ruyicai.mgr.domain.Tawardlevel;
import com.ruyicai.mgr.service.LotteryService;

@Component
public class TawardLevelListener {
	private static Logger logger = Logger.getLogger(TawardLevelListener.class);
	
	public static List<TawardDetail> list = null;
	
	@Autowired
	LotteryService lotteryService;
	public void process(){
		if (list == null) {
			list = TawardDetail.findAudited();
		}
		long now = System.currentTimeMillis();
		boolean flag = false;
		for (TawardDetail tawardDetail : list) {
			if(tawardDetail.getStarttime().getTime() <= now && tawardDetail.getEndtime().getTime() >= now
					&& tawardDetail.getState() == TawardDetail.STATE_AUDITED){
				Tawardlevel tawardlevel = Tawardlevel.findTawardlevelByLotnoAndLevel(tawardDetail.getLotno(), tawardDetail.getLevel());
				tawardlevel.setAddprize(tawardDetail.getAddprize());
				tawardlevel.merge();
				lotteryService.updateTawardlevelStatejms();
				
				tawardDetail.setState(TawardDetail.STATE_ING);
				tawardDetail.merge();
				
			}else if(tawardDetail.getEndtime().getTime() <= now){
				if (tawardDetail.getState() == TawardDetail.STATE_ING) {
					Tawardlevel tawardlevel = Tawardlevel.findTawardlevelByLotnoAndLevel(tawardDetail.getLotno(), tawardDetail.getLevel());
					tawardlevel.setAddprize(0);
					tawardlevel.merge();
					lotteryService.updateTawardlevelStatejms();
				}
				
				tawardDetail.setState(TawardDetail.STATE_DEAD);
				tawardDetail.merge();
				flag = true;
			}
		}
		if(flag){
			list = null;
		}
	}
}
