package com.ruyicai.mgr.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.Body;
import org.apache.camel.Consume;
import org.apache.camel.Header;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruyicai.mgr.controller.TawardLevelAuditController;
import com.ruyicai.mgr.mysql.IssusDao;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@Service
public class LotteryService {

	private Logger logger = Logger.getLogger(LotteryService.class);
	@Autowired
	PropertiesUtil propertiesUtil;
	
	@Produce(uri = "jms:topic:sendTawardLevelADProducer")
	private ProducerTemplate sendTawardLevelADProducer;
	public void updateTawardlevelStatejms(){
		try {
			String result = HttpUtil.post(propertiesUtil.getLotteryurl()+"/system/clearaddawardcache","");
			logger.info("调用lottery返回"+result);
		} catch (Exception e) {
			logger.error(e);
		}
		sendTawardLevelADProducer.sendBodyAndHeaders(null, new HashMap<String, Object>());
	}
	@Consume(uri = "jms:topic:sendTawardLevelADProducer")
	public void onMessage(@Body String body) {
		logger.info("jms 行了");
	}
	@Autowired
	IssusDao issusDao;
	@Produce(uri = "jms:topic:releaseIssusProducer")
	private ProducerTemplate releaseIssusProducer;
	public void releaseIssusJms(String id){
		Map<String, Object> issus = issusDao.getIssus(id);
		/*ZC_SFC("T01003"),	//足彩胜负彩
		ZC_RX9("T01004"),	//足彩任选9
		ZC_JQC("T01005"),	//足彩进球彩
		ZC_BQC("T01006"),	//足彩半全场
		vdlId=2012115, any9Id=2012115, games6Id=2012115, games4Id=2012117
		*/
		if(!"0000000".equals(issus.get("vdlId"))){
			Map<String, Object> headers = new HashMap<String, Object>();
			headers.put("LOTNO", "T01003");
			headers.put("BATCHCODE", issus.get("vdlId"));
			releaseIssusProducer.sendBodyAndHeaders(null, headers);
		}
		if(!"0000000".equals(issus.get("any9Id"))){
			Map<String, Object> headers = new HashMap<String, Object>();
			headers.put("LOTNO", "T01004");
			headers.put("BATCHCODE", issus.get("any9Id"));
			releaseIssusProducer.sendBodyAndHeaders(null, headers);
		}
		if(!"0000000".equals(issus.get("games6Id"))){
			Map<String, Object> headers = new HashMap<String, Object>();
			headers.put("LOTNO", "T01006");
			headers.put("BATCHCODE", issus.get("games6Id"));
			releaseIssusProducer.sendBodyAndHeaders(null, headers);
		}
		if(!"0000000".equals(issus.get("games4Id"))){
			Map<String, Object> headers = new HashMap<String, Object>();
			headers.put("LOTNO", "T01005");
			headers.put("BATCHCODE", issus.get("games4Id"));
			releaseIssusProducer.sendBodyAndHeaders(null, headers);
		}
	}
	@Consume(uri = "jms:topic:releaseIssusProducer")
	public void onMessage2(@Header("LOTNO") String lotno, @Header("BATCHCODE") String batchcode) {
		logger.info("收到jms消息"+lotno+"--"+batchcode);
	}
	/**
	 * 根据orderId获取Tlots
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public String getToltsByOrderId(String orderId) throws Exception {
		StringBuffer paramStr = new StringBuffer();
		paramStr.append("orderid=" + orderId);
		paramStr.append("&beginTime=" + "20100808");
		paramStr.append("&endTime=" + StringUtil.getEndTime());
		paramStr.append("&startLine=" + "0");
		paramStr.append("&endLine=" + "100");

		String url = propertiesUtil.getLotteryurl() + "/select/getTlot";
		String result = HttpUtil.post(url, paramStr.toString());
		return result;
	}

	/**
	 * 根据orderId获取Tlots(拆票) 通过订单ID查询该订单的票，如果数据库不存在Tlot,则模拟拆票(合买竞彩注码解析使用)
	 * 
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
	public String getTlotsByOrderidWrapper(String orderId) throws Exception {
		StringBuffer paramStr = new StringBuffer();
		paramStr.append("orderid=" + orderId);

		String url = propertiesUtil.getLotteryurl() + "/select/getTlotsByOrderidWrapper";
		String result = HttpUtil.post(url, paramStr.toString());
		return result;
	}

	/**
	 * 获取某场比赛的信息(供竟彩使用)
	 * 
	 * @param lotno
	 * @param day
	 * @param weekid
	 * @param teamid
	 * @return
	 * @throws Exception
	 */
	public String getjingcaimatches(String lotno, String day, String weekid, String teamid) throws Exception {
		StringBuffer paramStr = new StringBuffer();
		paramStr.append("lotno=" + lotno);
		paramStr.append("&day=" + day);
		paramStr.append("&weekid=" + weekid);
		paramStr.append("&teamid=" + teamid);

		String url = propertiesUtil.getLotteryurl() + "/select/getjingcaimatches";
		String result = HttpUtil.post(url, paramStr.toString());
		return result;
	}
}
