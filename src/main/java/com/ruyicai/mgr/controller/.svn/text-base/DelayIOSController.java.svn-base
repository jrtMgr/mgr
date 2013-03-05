package com.ruyicai.mgr.controller;

import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;


@RequestMapping(value = "/delayIOS")
@Controller
public class DelayIOSController {
	
	private Logger logger = Logger.getLogger(DelayIOSController.class);
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@RequestMapping("/addUI")
	public ModelAndView addUI(ModelAndView view){
		logger.info("delayIOS/addUI");
		view.setViewName("delayIOS/addUI");
		return view;
	}
	
	@RequestMapping("/delaysend")
	public ModelAndView add(@RequestParam("text") String text,
			@RequestParam("usernos") String usernos,
			@RequestParam("sendTime") String sendTime,
			ModelAndView view) {
		logger.info("delayIOS/add");
		String errormsg = "添加成功";
		if(StringUtil.isEmpty(text) || StringUtil.isEmpty(usernos)){
			view.addObject("errormsg", "不允许为空");
			return this.addUI(view);
		}
		
		try {
			StringBuffer param = new StringBuffer();
			param.append("text=").append(URLEncoder.encode(text, "UTF-8")).append("&usernos=").append(usernos)
			.append("&sendTime=").append(sendTime);
			String msgUrl = propertiesUtil.getMsgcenterurl()+"/ios/delaysend";
			logger.info("msgUrl:" + msgUrl + ", param:" + param);
			
			String result = HttpUtil.post(msgUrl, param.toString());
			JSONObject jo = new JSONObject(result);
			if (!"0".equals(jo.get("errorCode"))) {
				errormsg = "调用出错";
			}
		} catch (Exception e) {
			logger.error("delayIOS/add error", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.addUI(view);
	}
	
	
	@RequestMapping("/sendAll")
	public ModelAndView sendAll(@RequestParam("text1") String text1,
			@RequestParam("sendTime1") String sendTime1,
			ModelAndView view) {
		logger.info("delayIOS/sendAll");
		String errormsg = "添加所有用户成功";
		if(StringUtil.isEmpty(text1)){
			view.addObject("errormsg", "不允许为空");
			return this.addUI(view);
		}
		
		try {
			StringBuffer param = new StringBuffer();
			param.append("text=").append(URLEncoder.encode(text1, "UTF-8"))
			.append("&sendTime=").append(sendTime1);
			String msgUrl = propertiesUtil.getMsgcenterurl()+"/ios/sendAll";
			logger.info("msgUrl:" + msgUrl + ", param:" + param);
			
			String result = HttpUtil.post(msgUrl, param.toString());
			JSONObject jo = new JSONObject(result);
			if (!"0".equals(jo.get("errorCode"))) {
				errormsg = "调用出错";
			}
		} catch (Exception e) {
			logger.error("delayIOS/add error", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.addUI(view);
	}
	
	
	
	
	
	
	@RequestMapping("/createdelaysmsWithString")
	public ModelAndView createdelaysmsWithString(@RequestParam("text2") String text2,
			@RequestParam("mobileIds") String mobileIds,
			@RequestParam("sendTime2") String sendTime2,
			ModelAndView view) {
		logger.info("delayIOS/add");
		String errormsg = "添加成功";
		if(StringUtil.isEmpty(text2) || StringUtil.isEmpty(mobileIds)){
			view.addObject("errormsg", "不允许为空");
			return this.addUI(view);
		}
		
		try {
			StringBuffer param = new StringBuffer();
			param.append("text=").append(URLEncoder.encode(text2, "UTF-8")).append("&mobileIds=").append(mobileIds)
			.append("&sendTime=").append(sendTime2);
			String msgUrl = propertiesUtil.getMsgcenterurl()+"/sms/createdelaysmsWithString";
			logger.info("msgUrl:" + msgUrl + ", param:" + param);
			
			String result = HttpUtil.post(msgUrl, param.toString());
			JSONObject jo = new JSONObject(result);
			if (!"0".equals(jo.get("errorCode"))) {
				errormsg = "调用出错";
			}
		} catch (Exception e) {
			logger.error("delayIOS/add error", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.addUI(view);
	}
}
