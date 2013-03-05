package com.ruyicai.mgr.listener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruyicai.mgr.domain.TimeInterval;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.SMSUtil;

@Component
public class LotSucSendMsgListener {
	private static Logger logger = Logger.getLogger(LotSucSendMsgListener.class);
	@Autowired
	private PropertiesUtil propertiesUtil;
	public static List<TimeInterval> list = null;
	@Autowired
	SMSUtil smsUtil;
	public void lotSucSendMsg(){
		if (list == null) {
			list = TimeInterval.findAllTimeIntervals();
		}
		for (TimeInterval t : list) {
			int nowHour = new Date().getHours();
			if (nowHour >= t.getStartHour() && nowHour < t.getEndHour()) {
				if(System.currentTimeMillis() - TlotListener.lotSuccessTime > t.getFrequency()){
					logger.info("开始发送短信监控出票短信");
					String mobileNo = t.getMobileNo();
					try {
						for (String mobile : mobileNo.split("\\,")) {
							String result = smsUtil.sendSMSNotry(mobile, propertiesUtil.getName() + "系统有"+(System.currentTimeMillis() - TlotListener.lotSuccessTime)/60000+"分钟没出票了");
							logger.info("发送短信,result:" + result);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				} 
			}
		}
	}
	
	public static List<String> l = null;
	public void agencyBalance(){
		if(l == null){
			l = new ArrayList<String>();
		}
		logger.info(l);
		/*if (list == null) {
			list = TimeInterval.findAllTimeIntervals();
		}
		for (TimeInterval t : list) {
				if(System.currentTimeMillis() - TlotListener.lotSuccessTime > t.getFrequency()){
					logger.info("开始发送短信监控出票短信");
					String mobileNo = t.getMobileNo();
					try {
						for (String mobile : mobileNo.split("\\,")) {
							String result = smsUtil.sendSMSNotry(mobile, propertiesUtil.getName() + "系统有"+(System.currentTimeMillis() - TlotListener.lotSuccessTime)/60000+"分钟没出票了");
							logger.info("发送短信,result:" + result);
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				} 
		}
		*/
	}
}
