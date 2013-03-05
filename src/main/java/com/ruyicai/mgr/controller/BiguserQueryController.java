package com.ruyicai.mgr.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tlot;
import com.ruyicai.mgr.domain.Tlotmap;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;

@RequestMapping(value = "/biguserquery")
@Controller
public class BiguserQueryController {
private Logger logger = Logger.getLogger(BiguserQueryController.class);

	@Autowired
	private PropertiesUtil util;
	
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		view.setViewName("biguserquery/list");
		return view;
	}
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "agencyflowno") String agencyflowno, ModelAndView view) {
		logger.info("biguserquery/list");
		try {
			view.addObject("list", Tlotmap.findTlotByTlotmap(agencyflowno));
			view.addObject("list2",Tlotmap.findTlotmapByAgencyflowno(agencyflowno));
		} catch (Exception e) {
			logger.error("biguserquery/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("biguserquery/list");
		return view;
	}
	
	@RequestMapping("/list2")
	public ModelAndView list2(
			@RequestParam(value = "flowno") String flowno, ModelAndView view) {
		logger.info("biguserquery/list2");
		try {
			view.addObject("list2", Tlotmap.findTlotmapByTlot(flowno));
			view.addObject("list",Tlot.findTlotByFlowno(flowno));
			
		} catch (Exception e) {
			logger.error("biguserquery/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("biguserquery/list");
		return view;
	}
	
	@RequestMapping("/continuebet")
	public @ResponseBody
	ResponseData continuebet(@RequestParam("flowno") String flowno) {
		logger.info("biguserquery/continuebet");
		ResponseData data = new ResponseData();
		String errormsg = "已重投,请稍后查看结果";
		ErrorCode errorcode = ErrorCode.OK;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			logger.info("重投参数flowno:"+flowno+",ordertime:"+sdf.format(date));
			String result = "";
			if ("neimeng".equals(util.getName())) {
				result = HttpUtil.post(util.getLotteryurl()	+ "/bet/continuebet", "flowno=" + flowno);
			}else{
				result = HttpUtil.post(util.getLotteryurl()	+ "/system/continuebet", "flowno=" + flowno);
			}
			JSONObject json = new JSONObject(result);
			if (!"0".equals(json.getString("errorCode"))) {
				errormsg = "重投失败:"+result;
				errorcode = ErrorCode.ERROR;
			}
		} catch (Exception e) {
			logger.error("biguserquery/continuebet error:", e);
			errormsg = "出现异常e:"+e.getMessage();
			errorcode = ErrorCode.ERROR;
		}
		data.setErrorCode(errorcode.value);
		data.setValue(errormsg);
		return data;
	}
}
