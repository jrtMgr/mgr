package com.ruyicai.mgr.controller;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;

@RequestMapping("/updateStatistic")
@Controller
public class UpdateStatisticController {
	private Logger logger = LoggerFactory.getLogger(UpdateStatisticController.class);
	@Autowired
	private PropertiesUtil util;
	@RequestMapping("/index")
	public String index() {
		return "updateStatistic/info";
	}
	@RequestMapping("/update")
	public ModelAndView update(
			@RequestParam("key") String key,
			ModelAndView view) {
		String url = util.getLotteryurl();
		String errormsg = "刷新成功";
		try {
			String result = HttpUtil.post(url + "/system/updateStatistic", "key=" + key);
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg = "刷新失败";
			}
		} catch(Exception e) {
			logger.error("tuserinfoes/talibatchpaylist", e);
			errormsg = "刷新失败";
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("updateStatistic/info");
		return view;
	}
	
	@RequestMapping("/jingcaicancel")
	public ModelAndView jingcaicancel(
			@RequestParam("event") String event,
			ModelAndView view) {
		String url = util.getLotteryurl();
		String errormsg = "调用成功";
		try {
			String result = HttpUtil.post(url + "/system/jingcaicancel", "event=" + event);
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg = "调用失败"+json;
			}
		} catch(Exception e) {
			logger.error("tuserinfoes/jingcaicancel", e);
			errormsg = "调用失败";
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("updateStatistic/info");
		return view;
	}
}
