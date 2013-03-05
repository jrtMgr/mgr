package com.ruyicai.mgr.util;

import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 新发送短信
 */
public class MessageRunnable implements Runnable {
	private Logger logger = LoggerFactory.getLogger(MessageRunnable.class);
	private String mobileid;
	private String content;

	public MessageRunnable(String content, String mobileid) {
		this.content = content;
		this.mobileid = mobileid;
	}

	public void run() {
		try {
			String msgurl = "http://192.168.99.6/msgcenter/sms/delaysend";
			//String msgurl = "http://219.148.162.70:9080/msgcenter/sms/delaysend";//测试url
			String str = "mobileIds=" + mobileid + "&text=" + URLEncoder.encode(content, "UTF-8");
			logger.info("短信url："+ msgurl+" 手机号："+mobileid+",短信内容："+content);
			String resStr = HttpUtil.post(msgurl, str);
			logger.info("短信接口返回："+resStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
