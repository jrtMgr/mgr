package com.ruyicai.mgr.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruyicai.sms.MenWangOldSMSServiceProvider;
import com.ruyicai.sms.MenWangSMSServiceProvider;
import com.ruyicai.sms.SMSServiceProvider;

@Service
public class SMSUtil {
	
	private Logger logger = Logger.getLogger(SMSUtil.class);
	
	@Autowired
	private MenWangSMSServiceProvider smsServiceProvider;
	
	public MenWangSMSServiceProvider getSmsServiceProvider() {
		return smsServiceProvider;
	}

	public void sendSMS(String mobileid, String content) {
		try {
			int result = smsServiceProvider.sendMessage(mobileid.split("\\,"), content);
			logger.info("sendSMS result:" + result + ",mobileid:" + mobileid + ",content:" + content);
		} catch (Exception e) {
			logger.error("sendSMS error:", e);
		} 
		
	}
	
	public String sendSMSNotry(String mobileid, String content) throws Exception {
		int result = smsServiceProvider.sendMessage(mobileid.split("\\,"), content);
		logger.info("sendSMS result:" + result + ",mobileid:" + mobileid + ",content:" + content);
		return String.valueOf(result);
	}
	
	@Autowired
	private MenWangOldSMSServiceProvider menWangOldSMSServiceProvider;
	public String sendSMS91(String mobileid, String content) {
		int result = menWangOldSMSServiceProvider.sendMessage(mobileid.split("\\,"), content);
		logger.info("sendSMS result:" + result + ",mobileid:" + mobileid + ",content:" + content);
		return String.valueOf(result);
		
	}
	
}
