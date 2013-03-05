package com.ruyicai.mgr.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping(value = "/quartz")
@Controller
public class QuartzController {
	private Logger logger = Logger.getLogger(QuartzController.class);
	@Autowired
	PropertiesUtil propertiesUtil;

	@RequestMapping("/list")
	public ModelAndView list(ModelAndView view) {
		logger.info("quartz/list");
		try {
			StringBuilder url = new StringBuilder(propertiesUtil.getQuartzurl()+"/getTimerList");
			String resultMessage = HttpUtil.getResultMessage(url.toString());
			resultMessage = JsonUtil.escapeJavaScript(resultMessage);
			view.addObject("result", resultMessage);
		} catch (Exception e) {
			logger.error("quartz/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("quartz/list");
		return view;
	}

	@RequestMapping("/addUI")
	public ModelAndView addUI(ModelAndView view) {
		logger.info("quartz/addUI");
		view.setViewName("quartz/addUI");
		return view;
	}

	@RequestMapping("/add")
	public ModelAndView add(@RequestParam(value = "url") String url,
			@RequestParam(value = "param") String param,
			@RequestParam(value = "exp") String exp, 
			@RequestParam(value = "name") String name, 
			ModelAndView view) {
		logger.info("quartz/add");
		String errormsg = "新增成功";
		try {
			if (StringUtil.isEmpty(url) || StringUtil.isEmpty(param) ||  StringUtil.isEmpty(exp) ||  StringUtil.isEmpty(name)) {
				view.addObject("errormsg", "不允许为空");
				return this.addUI(view);
			}
			String result = HttpUtil.post(propertiesUtil.getQuartzurl()+"/saveTimerAndRemoveJobInfo", 
					"url="+url+"&param="+param+"&exp="+exp+"&name="+name);
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg  = "添加失败 返回码"+json.getString("errorCode");
			} 
		} catch (Exception e) {
			logger.error("quartz/add error", e);
			errormsg = "新增失败";
		}

		view.addObject("errormsg", errormsg);
		return this.list(view);
	}
	
	@RequestMapping("/qiyong")
	public ModelAndView qiyong(@RequestParam("id") String id,  ModelAndView view) {
		logger.info("quartz/qiyong");
		String errormsg = "启用成功";
		try {
			if (StringUtil.isEmpty(id)) {
				view.addObject("errormsg", "id不允许为空");
				return this.addUI(view);
			}
			String url = propertiesUtil.getQuartzurl()+"/enableTimer?timerid="+id;
			String result = HttpUtil.getResultMessage(url);
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg  = "失败 返回码"+json.getString("errorCode");
			} 
		} catch (Exception e) {
			logger.error("quartz/qiyong error", e);
			errormsg = "失败";
		}

		view.addObject("errormsg", errormsg);
		return this.list(view);
	}
	
	@RequestMapping("/tingyong")
	public ModelAndView tingyong(@RequestParam("id") String id,  ModelAndView view) {
		logger.info("quartz/tingyong");
		String errormsg = "停用成功";
		try {
			if (StringUtil.isEmpty(id)) {
				view.addObject("errormsg", "id不允许为空");
				return this.addUI(view);
			}
			String url = propertiesUtil.getQuartzurl()+"/unableTimer?triggername="+id;
			String result = HttpUtil.getResultMessage(url);
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg  = "失败 返回码"+json.getString("errorCode");
			} 
		} catch (Exception e) {
			logger.error("quartz/tingyong error", e);
			errormsg = "失败";
		}

		view.addObject("errormsg", errormsg);
		return this.list(view);
	}
	
	@RequestMapping("/delete1")
	public ModelAndView delete1(@RequestParam("id") String id,  ModelAndView view) {
		logger.info("quartz/delete1");
		String errormsg = "删除成功";
		try {
			if (StringUtil.isEmpty(id)) {
				view.addObject("errormsg", "id不允许为空");
				return this.addUI(view);
			}
			String url = propertiesUtil.getQuartzurl()+"/delete?triggername="+id;
			String result = HttpUtil.getResultMessage(url);
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg  = "失败 返回码"+json.getString("errorCode");
			} 
		} catch (Exception e) {
			logger.error("quartz/delete1 error", e);
			errormsg = "失败";
		}

		view.addObject("errormsg", errormsg);
		return this.list(view);
	}
}
