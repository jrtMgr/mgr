package com.ruyicai.mgr.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.PropertiesUtil;

import flexjson.JSONSerializer;

@RequestMapping("/dmsg")
@Controller
public class DmsgController {
	private Logger logger = Logger.getLogger(DmsgController.class);
	
	@RequestMapping("/mpage")
	public ModelAndView managerpage(
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "userno", required = false) String userno,
			@RequestParam(value = "startLine", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "endLine", required = false, defaultValue = "20") int endLine, ModelAndView view) {
		logger.info("caselot/page");
		view.addObject("startLine", startLine);
		view.addObject("endLine", endLine);
		try {
			// 合买分页数据
			StringBuilder url = new StringBuilder(propertiesUtil.getMsgcenterurl()+"/msg/selectMsgs");
			Map<String, Object> map = new HashMap<String, Object>();
			if (id != null) {
				map.put("EQI_id", id);
			}
			if (StringUtils.isNotBlank(userno)) {
				map.put("EQS_userno", userno);
			}
			url.append("?startLine=" + startLine);
			url.append("&endLine=" + endLine);
			String condition = new JSONSerializer().serialize(map);
			url.append("&condition=" + condition);
			String resultMessage = HttpUtil.getResultMessage(url.toString());
			resultMessage = JsonUtil.escapeJavaScript(resultMessage);
			view.addObject("result", resultMessage);
		} catch (Exception e) {
			logger.error("dmsg/listMg error:", e);
			view.addObject("errormsg", "异常");
		}
		view.setViewName("dmsg/managerpage");
		return view;
	}
	
	@Autowired
	PropertiesUtil propertiesUtil;
	@RequestMapping(value = "/updateFlag", method = RequestMethod.POST)
	public @ResponseBody ResponseData updateFlag(@RequestParam("id") String id,
			@RequestParam("flag") int flag, ModelAndView view) {
		logger.info("dmsg/updateFlag");
		ResponseData rd = new ResponseData();
		StringBuffer param = new StringBuffer();
		param.append("id=").append(id).append("&flag=").append(flag);
		try {
			String url = propertiesUtil.getMsgcenterurl()+"/msg/updateFlag?";
			String resultMessage = HttpUtil.getResultMessage(url + param);
			rd = ResponseData.fromJsonToResponseData(resultMessage);
		} catch (Exception e) {
			logger.error("dmsg/updateFlag error", e);
			rd.setErrorCode(ErrorCode.ERROR.value);
		}
		return rd;
	}
}
