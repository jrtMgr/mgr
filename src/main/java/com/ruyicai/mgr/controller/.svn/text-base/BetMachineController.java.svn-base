package com.ruyicai.mgr.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;

@RequestMapping("/betmachine")
@Controller
public class BetMachineController {
	private Logger logger = Logger.getLogger(BetMachineController.class);
	@Autowired
	private PropertiesUtil util;

	@RequestMapping("/page")
	public String index() {
		return "betmachine/info";
	}
	
	@RequestMapping("/getMachineState")
	public ModelAndView getMachineState(ModelAndView view) {
		String url = util.getLotteryurl();
		try {
			String result = HttpUtil.post(url + "/sdfcby/getMachineState", "");
			JSONObject json = new JSONObject(result);
			view.addObject("result", json.get("value")); 
		} catch(Exception e) {
			logger.error("betmachine/getMachineState error:", e);
			view.addObject("errormsg", "获取出错");
		}
		view.setViewName("betmachine/info");
		return view;
	}
	@RequestMapping("/machineCommand")
	public @ResponseBody ResponseData machineCommand(@RequestParam("machineno") String machineno,
			@RequestParam("type") String type){
		String result = "";
		ResponseData responseData = new ResponseData();
		try {
			result = HttpUtil.post(util.getLotteryurl()	+ "/sdfcby/machineCommand", "machineno=" + machineno+"&type="+type);
			JSONObject jo = new JSONObject(result);
			if ("0".equals(jo.get("errorCode"))) {
				responseData.setErrorCode(ErrorCode.OK.value);
				responseData.setValue("操作成功");
			}
			
		} catch (Exception e) {
			logger.error("betmachine/machineCommand error:", e);
			responseData.setErrorCode(ErrorCode.ERROR.value);
			responseData.setValue("操作失败");
		}
		return responseData;
	}
}
