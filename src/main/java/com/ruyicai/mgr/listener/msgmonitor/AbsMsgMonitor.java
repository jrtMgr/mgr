package com.ruyicai.mgr.listener.msgmonitor;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.SMSUtil;
import com.ruyicai.mgr.util.StringUtil;

public abstract class AbsMsgMonitor implements MsgMonitor{
	private Logger logger = Logger.getLogger(AbsMsgMonitor.class);
	@Autowired
	private SMSUtil smsUtil;
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	public void sendmsg(String msgBody) {
		String[] mobiles = propertiesUtil.getMobiles().split(",");
		for (String mobile : mobiles) {
			if (!StringUtil.isEmpty(mobile)) {
				logger.info("开始发送短信");
				try {
					String result = smsUtil.sendSMSNotry(mobile, propertiesUtil.getName()+ msgBody);
					logger.info("发送短信返回："+result);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
