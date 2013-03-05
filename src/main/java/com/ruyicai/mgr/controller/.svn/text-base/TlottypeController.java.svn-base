package com.ruyicai.mgr.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.lottery.lot.LotI;
import com.ruyicai.mgr.domain.ServerDetail;
import com.ruyicai.mgr.domain.Tlottype;
import com.ruyicai.mgr.domain.TlottypePK;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/tlottypes")
@Controller
public class TlottypeController {
	
	private Logger logger = Logger.getLogger(TlottypeController.class);
	
	@Autowired
	private PropertiesUtil propertiesUtil;

	@RequestMapping("/list")
	public ModelAndView list(ModelAndView view) {
		logger.info("tlottypes/list");
		try {
			view.addObject("tlottypes", Tlottype.findAllTlottypes());
		} catch (Exception e) {
			view.addObject("errormsg", "查询出错");
			logger.error("tlottypes/list error", e);
		}
		
		view.setViewName("tlottypes/list");
		return view;
	}
	
	@RequestMapping("/update")
	public ModelAndView updateHemaiEndtime(@RequestParam("id") String id,
			@RequestParam("agencyno") String agencyno,
			@RequestParam("state") BigDecimal state,
			@RequestParam("lvl") BigDecimal lvl,
			@RequestParam("betendlimit") BigDecimal betendlimit,
			@RequestParam("onprize") int onprize,
			@RequestParam("autoencash") BigDecimal autoencash,
			ModelAndView view) {
		logger.info("tlottypes/update");
		try {
			Tlottype tlottype = Tlottype.findTlottype(new TlottypePK(id, agencyno));
			tlottype.setState(state);
			tlottype.setLvl(lvl);
			tlottype.setBetendlimit(betendlimit);
			tlottype.setOnprize(onprize);
			tlottype.setAutoencash(autoencash);
			tlottype.merge();
			new Tlottype().update(id, onprize, autoencash);
			String lotteryurls = propertiesUtil.getLotteryUrls();
			String[] urls = lotteryurls.split("\\,");
			String errormsg = null;
			for(String url : urls) {
				String result = HttpUtil.post(url + "/system/reinit", "");
				JSONObject jsonObject = new JSONObject(result);
				if(!"0".equals(jsonObject.getString("errorCode"))) {
					errormsg += url + "通知出错,";
				}
			}
			if(!StringUtil.isEmpty(errormsg)) {
				view.addObject("errormsg", errormsg);
			}
		} catch (Exception e) {
			view.addObject("errormsg", "出现异常");
			logger.error("tlottypes/list error", e);
		}
		return this.list(view);
	}
	
	static Map<String,String> map = new HashMap<String, String>();
	{
		map.put("T01006", "BanQuanChang");
		map.put("T01001", "DaLeTou");
		map.put("T01008", "DanChang");
		map.put("T01010", "DuoLeCai");
		map.put("T01012", "ElevenDuoJin");
		map.put("J00002", "JingcaiBF");
		map.put("J00004", "JingcaiBQC");
		map.put("J00008", "JingcaiLQDXF");
		map.put("J00006", "JingcaiLQRFSF");
		map.put("J00005", "JingcaiLQSF");
		map.put("J00007", "JingcaiLQSFC");
		map.put("J00001", "JingcaiSFP");
		map.put("J00003", "JingcaiZJQ");
		map.put("T01005", "JinQiuCai");
		map.put("T01002", "PaiLieSan");
		map.put("T01011", "PaiLieWu");
		map.put("F47102", "QiLeCai");
		map.put("T01009", "QiXingCai");
		map.put("F47105", "QunYingHui");
		map.put("T01004", "RenJiuChang");
		map.put("T01003", "ShengFuCai");
		map.put("T01007", "ShiShiCai");
		map.put("F47104", "ShuangSeQiu");
		map.put("F47103", "ThreeD");
		map.put("T01013", "TwentyTwoSelectFive");
	}
	
	
	@RequestMapping("/jmx")
	public ModelAndView jmx(@RequestParam("lotno") String lotno,
			ModelAndView view) {
		logger.info("tlottypes/jmx");
		String errormsg = "";
		try {
			List<ServerDetail> list = ServerDetail.findAllServerDetailsBystatus();
			ObjectName objectName=new ObjectName("com.ruyicai.lottery.lot.lottype:type="+map.get(lotno)+",name="+lotno);
			if (list.size() > 0) {
				for (ServerDetail serverDetail : list) {
					String jmxaddress = serverDetail.getJmx();
					if (!"".equals(jmxaddress) || jmxaddress!= null) {
						JMXServiceURL address = new JMXServiceURL(jmxaddress);
					    JMXConnector connector = JMXConnectorFactory.connect(address);
					    MBeanServerConnection mbsc = connector.getMBeanServerConnection();
					    connector.connect();
					   
					    if (mbsc.isRegistered(objectName)) {
					    	final LotI lot = JMX.newMBeanProxy(mbsc, objectName, LotI.class);
					    	lot.initTlottypes();
					    	errormsg = "调用成功";
						}else{
							errormsg = "jmx地址不存在对应MBEAN";
						}
					}
				}
			}else{
				errormsg = "没有设置jmx地址";
			}
		} catch (Exception e) {
			errormsg = "mgr出错请看日志";
			logger.error("tlottypes/list error", e);
		}	
		view.addObject("errormsg",errormsg);
		return this.list(view);
	}
}
