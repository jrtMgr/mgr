package com.ruyicai.mgr.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.service.TuserloginfoDao;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@Controller
@RequestMapping("/userloginfo")
public class UserloginfoController {
	private Logger logger = Logger.getLogger(UserloginfoController.class);
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		logger.info("userloginfo/list");
		view.setViewName("userloginfo/list");
		return view;
	}
	
	@Autowired
	TuserloginfoDao tuserloginfoDao;
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam("userno") String userno,
			ModelAndView view){
		logger.info("userloginfo/list");
		try {
			view.addObject("list", tuserloginfoDao.findByUserno(userno));
		} catch (Exception e) {
			logger.error("userloginfo/list", e);
		}
		view.setViewName("userloginfo/list");
		return view;
	}
	@Autowired
	PropertiesUtil propertiesUtil;
	
	@RequestMapping("/deleteByuserno")
	public ModelAndView deleteByday(@RequestParam("userno") String userno,
			ModelAndView view){
		logger.info("userloginfo/deleteByuserno");
		if(StringUtil.isEmpty(userno)){
			view.addObject("errormsg", "userno不允许为空");
			view.setViewName("userloginfo/list");
			return view;
		}
		String errormsg = "删除成功";
		try {
			tuserloginfoDao.deleteByuserno(userno);
			
			String result = HttpUtil.post(propertiesUtil.getLotteryurl() + "/system/reinit", "");
			JSONObject json = new JSONObject(result);
			if (!"0".equals(json.getString("errorCode"))) {
				errormsg = "失败，返回码："+json.getString("errorCode");
			}
		} catch (Exception e) {
			logger.error("userloginfo/deleteByuserno", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("userloginfo/list");
		return view;
	}
	
	@RequestMapping("/deleteByday")
	public ModelAndView deleteByuserno(@RequestParam("day") Integer day,
			ModelAndView view){
		logger.info("userloginfo/deleteByday");
		if(day == null){
			view.addObject("errormsg", "day不允许为空");
			view.setViewName("userloginfo/list");
			return view;
		}
		String errormsg = "删除成功";
		try {
			tuserloginfoDao.deleteByday(day);
			String result = HttpUtil.post(propertiesUtil.getLotteryurl() + "/system/reinit", "");
			JSONObject json = new JSONObject(result);
			if (!"0".equals(json.getString("errorCode"))) {
				errormsg = "失败，返回码："+json.getString("errorCode");
			}
		} catch (Exception e) {
			logger.error("userloginfo/deleteByday", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("userloginfo/list");
		return view;
	}
}
