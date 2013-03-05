package com.ruyicai.mgr.listener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.Consume;
import org.apache.camel.Header;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TlotListener {
	private Logger logger = Logger.getLogger(TlotListener.class);
	private static Map<String, Integer> map = new HashMap<String, Integer>();

	public static long lotSuccessTime = System.currentTimeMillis();
	
	@Consume(uri = "jms:topic:lotSuccess")
	public void onMessage(@Body String body) {
		String key = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
		Integer num = map.get(key);
		if(null == num) {
			num = 0;
		}
		map.put(key, ++num);
		lotSuccessTime = System.currentTimeMillis();
	}
	
	public static Map<String, Integer> getMap() {
		return map;
	}
	
	/**
	 * 记录开期时间
	 */
	public static Map<String, Long> jieqi = new HashMap<String, Long>();
	
	/**
	 * 开期
	 */
	@Consume(uri = "jms:queue:VirtualTopicConsumers.mgr-lis.issue-end")
	public void issue(@Header("LOTNO") String lotno, @Header("BATCHCODE") String batchcode) {
		logger.info("收到jms消息，issue-end lotno：" + lotno + ", bactcode:" + batchcode);
		jieqi.put(lotno+"_"+batchcode, System.currentTimeMillis());
	}
	
	/**
	 * 记录开奖时间
	 */
	public static Map<String, Long> kaijiang = new HashMap<String, Long>();

	/**
	 * 开奖
	 */
	@Consume(uri = "jms:queue:VirtualTopicConsumers.mgr-lis.drawLottery")
	public void drawLottery(@Header("lotno") String lotno,	@Header("batchcode") String batchcode) {
		logger.info("收到jms消息，drawLottery lotno：" + lotno + ", bactcode:" + batchcode);
		kaijiang.put(lotno+"_"+batchcode, System.currentTimeMillis());
		jieqi.remove(lotno+"_"+batchcode);
	}
	
	/**
	 * 派奖结束
	 */
	@Consume(uri = "jms:queue:VirtualTopicConsumers.mgr-lis.encashend-topic")
	public void encashend(@Header("LOTNO") String lotno, @Header("BATCHCODE") String batchcode) {
		logger.info("收到jms消息，encashend-topic lotno：" + lotno + ", bactcode:" + batchcode);
		kaijiang.remove(lotno+"_"+batchcode);
	}
	
	public static Map<String, Long> touzhu = new HashMap<String, Long>();
	/**
	 * 投注后出票
	 */
	@Consume(uri = "jms:queue:VirtualTopicConsumers.mgr.sendSaveOrderSMS")
	public void touzhu(@Header("orderid") String orderid) {
		touzhu.put(orderid, System.currentTimeMillis());
	}
	
}
