package com.ruyicai.mgr.controller;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tloguser;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;

@RequestMapping("/tcard")
@Controller
public class TcardController {
	private Logger logger = Logger.getLogger(TcardController.class);
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view) {
		logger.info("tcard/page begin");
		String errormsg = null;
		String beginno = "";
		try {
			String url = propertiesUtil.getChargeCenterurl() + "/ruyicaicardcharge!getBeginno";
			logger.info("chargecenterUrl:" + url);
			String result = HttpUtil.post(url, "");
			JSONObject json = new JSONObject(result);
			if("0".equals(json.getString("error_code"))) {
				errormsg = "获取beginno成功";
				beginno = json.getString("value");
				logger.info("获取beginno成功, beginno=" + beginno);
			} else if("500".equals(json.getString("error_code"))) {
				errormsg = "远程服务器内部错误";
			} else {
				errormsg = "远程服务器后台错误";
			}
		} catch (Exception e) {
			errormsg = "获取beginno失败";
			logger.error("tcard/page error", e);
		}
		
		view.addObject("errormsg", errormsg);		
		view.addObject("beginno", beginno);
		view.setViewName("tcard/cardmanage");
		logger.info("tcard/page end");
		return view;		
	}
	
	@RequestMapping("/gencard")
	public ModelAndView gencard(
			@RequestParam("amt") String amt,
			@RequestParam("type") String type,
			@RequestParam("cardfrom") String cardfrom,
			@RequestParam("agencyno") String agencyno,
			@RequestParam("channel") String channel,
			@RequestParam("beginno") String beginno,
			@RequestParam("endno") String endno,
			@RequestParam("endtime") String endtime,
			HttpSession session,
			ModelAndView view) {
		logger.info("tcard/gencard begin");
		String errormsg = null;		
		try {
			Tloguser tloguser = (Tloguser) session.getAttribute("user");
			if (tloguser == null) {
				errormsg = "session失效";
				logger.info("tcard/gencard:" + errormsg);
				view.addObject("errormsg", errormsg);		
				view.setViewName("tcard/cardreport");
				return view;
			}
			StringBuilder param = new StringBuilder();
			param.append("jsonString={amt:").append(amt).append(",type:").append(type).append(",cardfrom:").append(cardfrom)
				.append(",agencyno:'").append(agencyno).append("',channel:'").append(channel.trim())
				.append("',beginno:").append(beginno).append(",endno:").append(endno.trim())
				.append(",endtime:'").append(endtime.trim()).append("',manager:'").append(tloguser.getNickname()).append("'}");
			String url = propertiesUtil.getChargeCenterurl() + "/ruyicaicardcharge!genCard";
			logger.info("chargecenterUrl:" + url + "；param=" + param.toString());
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);
			if("0".equals(json.getString("error_code"))) {
				errormsg = "生成卡成功";				
				logger.info("tcard/gencard：" + errormsg);
			} else if("500".equals(json.getString("error_code"))) {
				errormsg = "远程服务器内部错误";
			} else {
				errormsg = "远程服务器后台错误";
			}
		} catch (Exception e) {
			errormsg = "生成卡失败";
			logger.error("tcard/gencard error", e);
		}
		view.addObject("errormsg", errormsg);		
		view.setViewName("tcard/cardreport");
		logger.info("tcard/gencard end");
		return view;	
	}
	
	@RequestMapping("/getcansells")
	public @ResponseBody
    ResponseData getCanSells(
    		@RequestParam("amt") String amt,
			@RequestParam("type") String type,
			@RequestParam("cardfrom") String cardfrom,		
			@RequestParam("channel") String channel) {
		logger.info("tcard/getcansells begin");
		ErrorCode errorCode = ErrorCode.OK;
		ResponseData data = new ResponseData();	
		String ret = "";
		try {
			StringBuilder param = new StringBuilder();
			param.append("jsonString={amt:").append(amt).append(",type:").append(type).append(",cardfrom:").append(cardfrom)
				.append(",channel:'").append(channel.trim()).append("'}");;
			String url = propertiesUtil.getChargeCenterurl() + "/ruyicaicardcharge!getCanSells";
			logger.info("chargecenterUrl:" + url + "；param=" + param.toString());
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);
			if("0".equals(json.getString("error_code"))) {			
				ret = json.getString("value");
				logger.info("获取cansells成功, ret=" + ret);
			} else if("500".equals(json.getString("error_code"))) {
				ret = "远程服务器内部错误";				
				logger.error("tcard/page error:" + ret);
			} else {
				ret = "远程服务器后台错误";
				logger.error("tcard/page error:" + ret);
			}
		} catch (Exception e) {
			ret = "获取cansells失败";
			logger.error("tcard/page error", e);
		}
		
		data.setValue(ret);
		data.setErrorCode(errorCode.value);
		return data;		
	}
	
	@RequestMapping("/sellcard")
	public ModelAndView sellcard(
			@RequestParam("amt2") String amt,
			@RequestParam("type2") String type,
			@RequestParam("cardfrom2") String cardfrom,			
			@RequestParam("channel2") String channel,
			@RequestParam("sellamt") String sellamt,			
			ModelAndView view) {
		logger.info("tcard/gencard begin");
		String errormsg = null;		
		String path = "";
		
		try {			
			StringBuilder param = new StringBuilder();
			param.append("jsonString={amt:").append(amt).append(",type:").append(type).append(",cardfrom:").append(cardfrom)
				.append(",channel:'").append(channel.trim())
				.append("',sellamt:").append(sellamt).append("}");
			String url = propertiesUtil.getChargeCenterurl() + "/ruyicaicardcharge!sell";
			logger.info("chargecenterUrl:" + url + "；param=" + param.toString());
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);
			if("0".equals(json.getString("error_code"))) {
				errormsg = "销售卡成功";	
				path = propertiesUtil.getChargeCenterurlOut() + "/ruyicaicardcharge!getFile?jsonString={\"url\":" +
						"\"" + json.getString("value") + "\"}";
				logger.info("tcard/gencard：errormsg=" + errormsg + "；path=" + path);
			} else if("500".equals(json.getString("error_code"))) {
				errormsg = "远程服务器内部错误";
			} else {
				errormsg = "远程服务器后台错误";
			}
		} catch (Exception e) {
			errormsg = "销售卡失败";
			logger.error("tcard/gencard error", e);
		}
		
		view.addObject("errormsg", errormsg);
		view.addObject("path", path);
		view.setViewName("tcard/cardfile");
		logger.info("tcard/gencard end");
		return view;	
	}
}
