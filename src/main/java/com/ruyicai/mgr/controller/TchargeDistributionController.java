package com.ruyicai.mgr.controller;


import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;

import flexjson.JSONSerializer;

/*
 * 充值权重分配
 * @author ZhangBuyun
 *
 */
@RequestMapping("/tchargeDistribution")
@Controller
public class TchargeDistributionController {
	
	private Logger logger = Logger.getLogger(TchargeDistributionController.class);
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@RequestMapping("/list")
	public ModelAndView goList(ModelAndView view){
		logger.info("tchargeDistribution/list");
		String result;
		try {
			result = HttpUtil.post(propertiesUtil.getChargeCenterurl() + "/charge!getweight","");
			JSONObject jo = new JSONObject(result);
			view.addObject("shenzhoufuWeight", jo.get("shenzhoufuWeight"));
			view.addObject("nineteenpayWeight", jo.get("nineteenpayWeight"));
		} catch (Exception e) {
			logger.error("tchargeDistribution/list error", e);
		}
		view.setViewName("tchargeDistribution/list");
		return view;
	}
	
	/**
	 * 充值权重分配
	 * @param nineteenpay
	 * @param shenzhoufu
	 * @param view
	 * @return
	 */
	@RequestMapping("/distribution")
	public ModelAndView distributionInfo(@RequestParam(value="nineteenpay") String nineteenpay,
			@RequestParam(value="shenzhoufu") String shenzhoufu,
			ModelAndView view){
		logger.info("tchargeDistribution/distribution");
		String result,errormsg=null;
		if(nineteenpay.equals("0") && shenzhoufu.equals("0")){
			errormsg  = "充值权重不能同时为0!";
			view.addObject("errormsg", errormsg);
			view.setViewName("tchargeDistribution/list");
			return view;
		}
		if(nineteenpay=="" || shenzhoufu==""){
			errormsg  = "充值权重不能同时为空!";
			view.addObject("errormsg", errormsg);
			view.setViewName("tchargeDistribution/list");
			return view;
		}
		String url = propertiesUtil.getChargeCenterurl() + "/charge!resetweight";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nineteenpay", nineteenpay);
		map.put("shenzhoufu", shenzhoufu);
		String param = "jsonString=" + new JSONSerializer().serialize(map);;
		try {
			result = HttpUtil.post(url, param);
			JSONObject jsObject = new JSONObject(result);
			if("0".equals(jsObject.getString("error_code"))) {
				errormsg  = "设置手机充值权重分配成功!";
			} else {
				errormsg  = "设置手机充值权重分配失败!";
			}
		} catch (Exception e) {
			logger.error("tchargeDistribution/distribution error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.goList(view);
	}

}
