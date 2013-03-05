package com.ruyicai.mgr.listener;

import org.apache.camel.Consume;
import org.apache.camel.Header;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.SMSUtil;
import com.ruyicai.mgr.util.StringUtil;

@Component
public class DrawLotteryJMS {
	
	@Consume(uri = "jms:queue:drawSms")
	public void issue1(@Header("type") String type, @Header("msg") String msg) {
		this.sendmsg(type+":"+msg);
	}
	
	@Autowired
	private SMSUtil smsUtil;
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	private void sendmsg(String msgBody) {
		String[] mobiles = propertiesUtil.getMobiles().split(",");
		for (String mobile : mobiles) {
			if (!StringUtil.isEmpty(mobile)) {
				try {
					String result = smsUtil.sendSMSNotry("13146351343", propertiesUtil.getName()+ msgBody);
					JSONObject json = new JSONObject(result);
					if (!"0".equals(json.getString("errorCode"))) {
						
					} 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
