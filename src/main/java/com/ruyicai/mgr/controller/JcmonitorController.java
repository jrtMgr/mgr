package com.ruyicai.mgr.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tlot;
import com.ruyicai.mgr.domain.Tlotmap;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.PropertiesUtil;

@RequestMapping("/jcmonitor")
@Controller
public class JcmonitorController {
	private Logger logger = Logger.getLogger(JcmonitorController.class);
	@Autowired
	private PropertiesUtil util;

	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view) {
		try {
			view.addObject("fail", Tlot.findJcfailCount().get(0));
			view.addObject("trueFail", Tlot.findJcFailCount().get(0));
			view.addObject("bignotbet", Tlotmap.findNotbetCount());
		} catch (Exception e) {
			logger.error("jcmonitor/page",e);
		}
		view.setViewName("jcmonitor/page");
		return view;
	}
	
	@RequestMapping("/jcnotauditPage")
	public ModelAndView jcnotauditPage(ModelAndView view) {
		try {
			view.addObject("jcnotaudit", Tlot.findJcNotaudit().get(0));
		} catch (Exception e) {
			logger.error("jcmonitor/jcnotauditPage",e);
		}
		view.setViewName("jcmonitor/jcnotaudit");
		return view;
	}
	@RequestMapping("/findJcList")
	public ModelAndView findJcFailList(@RequestParam(value="flag") String flag, 
			@ModelAttribute("page") Page<Tlot> page, ModelAndView view) {
		logger.info("jcmonitor/findJcFailList");
		try {
			if (flag.equals("fail")) {
				Tlot.findJcFailList(page);
			}else if(flag.equals("trueFail")){
				Tlot.findJctrueFailList(page);
			}
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("jcmonitor/findJcList error", e);
			view.addObject("errormsg", e.getMessage());
		}
		return this.page(view);
	}
	
	@RequestMapping("/findJcnotauditList")
	public ModelAndView findJcnotauditList(@RequestParam(value="lotno") String lotno, 
			@ModelAttribute("page") Page<Tlot> page, ModelAndView view) {
		logger.info("jcmonitor/findJcFailList");
		try {
			Tlot.findJcNotauditList(lotno, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("jcmonitor/jcnotaudit error", e);
			view.addObject("errormsg", e.getMessage());
		}
		return this.jcnotauditPage(view);
	}
	
	@RequestMapping("/queryTicket")
	public @ResponseBody ResponseData queryTicket(@RequestParam("flowno") String flowno){
		String result = "";
		ResponseData responseData = new ResponseData();
		try {
			result = HttpUtil.post(util.getLotteryurl()	+ "/sdfcby/queryTicket", "flowno=" + flowno);
			JSONObject jo = new JSONObject(result);
			if ("0".equals(jo.get("errorCode"))) {
				responseData.setErrorCode(ErrorCode.OK.value);
				responseData.setValue("操作成功");
			}
		} catch (Exception e) {
			logger.error("jcmonitor/queryTicket error", e);
			responseData.setErrorCode(ErrorCode.ERROR.value);
			responseData.setValue("操作失败");
		}
		return responseData;
	}
	
	@RequestMapping("/setpeilu")
	public @ResponseBody ResponseData setpeilu(@RequestParam("flowno") String flowno){
		String result = "";
		ResponseData responseData = new ResponseData();
		try {
			result = HttpUtil.post(util.getLotteryurl()	+ "/system/setpeilu", "flowno=" + flowno);
			JSONObject jo = new JSONObject(result);
			if ("0".equals(jo.get("errorCode"))) {
				responseData.setErrorCode(ErrorCode.OK.value);
			}
			
		} catch (Exception e) {
			logger.error("jcmonitor/setpeilu error", e);
			responseData.setErrorCode(ErrorCode.ERROR.value);
		}
		return responseData;
	}
	
	@RequestMapping("/findMsg")
	public ModelAndView findMsg(@RequestParam(value="flowno",required=false)String flowno,
			ModelAndView view){
		logger.info("jcmonitor/findMsg");
		List<Tlotmap> tlotmaps = Tlotmap.findTlotmapByTlot(flowno);
		view.addObject("tlotmaps",tlotmaps);
		view.setViewName("jcmonitor/biguserlist");
		return view;
	}
	
	@RequestMapping("/continuebet")
	public @ResponseBody
	ResponseData continuebet(@RequestParam("flowno") String flowno) {
		logger.info("jcmonitor/continuebet");
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
			logger.error("jcmonitor/continuebet error", e);
			errormsg = "出现异常e:"+e.getMessage();
			errorcode = ErrorCode.ERROR;
		}
		data.setErrorCode(errorcode.value);
		data.setValue(errormsg);
		return data;
	}
	
	@RequestMapping("/undeduct")
	public @ResponseBody
	ResponseData undeduct(@RequestParam("orderid") String orderid) {
		logger.info("jcmonitor/undeduct");
		ResponseData data = new ResponseData();
		String errormsg = "已冲正,请稍后查看结果";
		ErrorCode errorcode = ErrorCode.OK;
		try {
			String result = HttpUtil.post(util.getLotteryurl()
					+ "/system/orderundeduct", "orderid=" + orderid);
			JSONObject json = new JSONObject(result);
			if (!"0".equals(json.getString("errorCode"))) {
				errormsg = "冲正失败"+json.getString("errorCode");
				errorcode = ErrorCode.ERROR;
			}
		} catch (Exception e) {
			errormsg = "重投失败"+e.getMessage();
			logger.error("jcmonitor/undeduct error", e);
			errorcode = ErrorCode.ERROR;
		}
		data.setErrorCode(errorcode.value);
		data.setValue(errormsg);
		return data;
	}
	@RequestMapping("/rebet")
	public @ResponseBody ResponseData rebet(){
		ResponseData data = new ResponseData();
		ErrorCode errorcode = ErrorCode.OK;
		logger.info("重投开始.....");
		try{
			String requestStr=HttpUtil.post(util.getLotteryurl()
					+ "/channel/rebet", "");
			logger.info("重投返回的是："+requestStr);
			if(requestStr.indexOf("sucessful")==-1){
				errorcode=ErrorCode.ERROR;
			}
		}catch(Exception e ){
			errorcode=ErrorCode.ERROR;
			logger.error("jcmonitor/rebet error", e);
		}
		data.setErrorCode(errorcode.value);
		data.setValue("重投结束");
		return data;
	}
}
