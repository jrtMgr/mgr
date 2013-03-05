package com.ruyicai.mgr.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.PropertiesUtil;

import flexjson.JSONSerializer;

@RequestMapping("/replys")
@Controller
public class ReplysController {
	private Logger logger = Logger.getLogger(ReplysController.class);
	
	@RequestMapping("/page")
	public String page(){
		logger.info("replys/page");
		return "replys/list";
	}
	
	@Autowired
	PropertiesUtil propertiesUtil;
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "username",required = false) String username,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "startLine",required = false,defaultValue="0") int startLine,
			@RequestParam(value = "endLine",required = false, defaultValue="20") int endLine, ModelAndView view) {
		logger.info("replys/list");
		view.addObject("startLine", startLine);
		view.addObject("endLine", endLine);
		try {
			StringBuilder url = new StringBuilder(propertiesUtil.getMsgcenterurl()+"/msg/selectReplys");
			Map<String, Object> map = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(username)) {
				map.put("EQS_username", username);
			}
			if (StringUtils.isNotBlank(starttime)) {
				map.put("GED_createtime", starttime);
			}
			if (StringUtils.isNotBlank(endtime)) {
				map.put("LED_createtime", endtime);
			}
			url.append("?startLine=" + startLine);
			url.append("&endLine=" + endLine);
			String condition = new JSONSerializer().serialize(map);
			url.append("&condition=" + condition);
			String resultMessage = HttpUtil.getResultMessage(url.toString());
			resultMessage = JsonUtil.escapeJavaScript(resultMessage);
			view.addObject("result", resultMessage);
		} catch (Exception e) {
			logger.error("replys/listMg error:", e);
			view.addObject("errormsg", "异常");
		}
		view.setViewName("replys/list");
		return view;
	}
	
}
