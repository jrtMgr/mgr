package com.ruyicai.mgr.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tloguser;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

import flexjson.JSONSerializer;

@RequestMapping("/agent")
@Controller
public class AgentController {
	private Logger logger = Logger.getLogger(AgentController.class);
	@Autowired
	PropertiesUtil propertiesUtil;
	@RequestMapping("/page")
	public ModelAndView page(@RequestParam(value = "userno", required = false, defaultValue = "") String userno,
			@RequestParam(value = "level", required = false, defaultValue = "") Integer level,
			@RequestParam(value = "startLine", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "endLine", required = false, defaultValue = "20") int endLine, ModelAndView view) {
		logger.info("agent/page");
		view.addObject("startLine", startLine);
		view.addObject("endLine", endLine);
		try {
			// 合买分页数据
			StringBuilder url = new StringBuilder(propertiesUtil.getAgenturl()+"/findAllUserAgency");
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtil.isEmpty(userno)) {
				map.put("EQS_userno", userno);
			}
			if (level != null) {
				map.put("EQI_level", level);
			}
			url.append("?startLine=" + startLine);
			url.append("&endLine=" + endLine);
			String condition = new JSONSerializer().serialize(map);
			url.append("&condition=" + condition);
			String resultMessage = HttpUtil.getResultMessage(url.toString());
			resultMessage = JsonUtil.escapeJavaScript(resultMessage);
			view.addObject("result", resultMessage);
		} catch (Exception e) {
			logger.error("agent/list error:", e);
			view.addObject("errormsg", "异常");
		}
		view.setViewName("agent/list");
		return view;
	}
	
	
	@RequestMapping("/commisionRatio")
	public ModelAndView commisionRatio(@RequestParam("userno") String userno, ModelAndView view){
		logger.info("agent/commisionRatio");
		try {
			String url = propertiesUtil.getAgenturl()+"/findAgencyPrecent?userno="+userno;
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.getResultMessage(url);
			Map m = JsonUtil.transferJson2Map(result);
			//List<Map<String, Object>> l = (List<Map<String, Object>>) m.get("value");
			view.addObject("list", m.get("value"));
		} catch (Exception e) {
			logger.error("agent/commisionRatio error:", e);
		}
		view.setViewName("agent/commisionRatio");
		return view;
	}
	
	@RequestMapping(value = "/updateCommisionRatio")
	public @ResponseBody ResponseData updateCommisionRatio(@RequestParam("userno") String userno,
			@RequestParam("lotno") String lotno, 
			@RequestParam("commisionRatio") BigDecimal percent, 
			ModelAndView view) {
		logger.info("agent/updateCommisionRatio");
		ResponseData data = new ResponseData();
		StringBuffer param = new StringBuffer();
		param.append("userno=").append(userno).append("&lotno=").append(lotno).append("&percent=").append(percent);
		try {
			String result = HttpUtil.post(propertiesUtil.getAgenturl()+"/modifyAgencyPrecentNotValidate", param.toString());
			data = ResponseData.fromJsonToResponseData(result);
		} catch (Exception e) {
			logger.error("agent/updateCommisionRatio error:", e);
			data.setErrorCode(ErrorCode.ERROR.value);
			data.setValue("异常");
		}
		return data;
	}
	

	@RequestMapping(value = "/addUI")
	public String addUI(){
		return "agent/addUI";
	}

	
	@RequestMapping(value = "/add")
	public ModelAndView add(@RequestParam("userno") String userno,
			@RequestParam("parentUserno") String parentUserno, 
			ModelAndView view) {
		logger.info("agent/add");
		String errormsg = "createAgent成功";
		StringBuffer param = new StringBuffer();
		param.append("userno=").append(userno).append("&parentUserno=").append(parentUserno);
		try {
			String result = HttpUtil.post(propertiesUtil.getAgenturl()+"/createUserAgency", param.toString());
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg  = "添加失败 返回码"+json.getString("errorCode");
				view.setViewName("agent/addUI");
				view.addObject("errormsg", errormsg);
				return view;
			} 
		} catch (Exception e) {
			logger.error("agent/add error:", e);
		}
		view.addObject("errormsg", errormsg);
		return this.page(userno, null, 0, 20, view);
	}
}
