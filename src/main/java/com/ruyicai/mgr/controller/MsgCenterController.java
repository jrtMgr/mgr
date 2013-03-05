package com.ruyicai.mgr.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.PropertiesUtil;

@RequestMapping("/msgcenter")
@Controller
public class MsgCenterController {

	private Logger logger = LoggerFactory.getLogger(MsgCenterController.class);

	@Autowired
	private PropertiesUtil propertiesUtil;

	@RequestMapping(value = "/redirect/{id}")
	public String header(@PathVariable("id") String id) {
		logger.info("id:" + id);
		return "msgcenter/" + id;
	}

	@RequestMapping(value = "/listWaitforSendSMS")
	public @ResponseBody
	String listWaitforSendSMS(@RequestParam(value = "condition", required = false, defaultValue = "") String condition,
			@RequestParam(value = "start", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "limit", required = false, defaultValue = "30") int endLine,
			@RequestParam(value = "sort", required = false, defaultValue = "") String orderBy,
			@RequestParam(value = "dir", required = false, defaultValue = "") String orderDir) {
		logger.info("/listWaitforSendSMS condition:{}", new String[] { condition });
		String result = null;
		try {
			String[] conditionArray = condition.split("\\&");
			Map<String, String> map = new HashMap<String, String>();
			for (String str : conditionArray) {
				if (StringUtils.isNotBlank(str)) {
					String[] split = str.split("\\=");
					if (split != null && split.length == 2 && StringUtils.isNotBlank(split[0])
							&& StringUtils.isNotBlank(split[1])) {
						map.put(split[0], split[1]);
					}
				}
			}
			String conditionJson = JsonUtil.toJson(map);
			String url = propertiesUtil.getMsgcenterurl() + "/sms/selectWaitforSend?condition=" + conditionJson
					+ "&startLine=" + startLine + "&endLine=" + endLine + "&orderBy=" + orderBy + "&orderDir="
					+ orderDir;
			String message = HttpUtil.getResultMessage(url);
			JSONObject jsonObject = new JSONObject(message);
			String errorCode = jsonObject.getString("errorCode");
			if (errorCode.equals("0")) {
				result = jsonObject.getString("value");
			}
		} catch (Exception e) {
			logger.error("/listWaitforSendSMS error", e);
		}
		return result;
	}
	
	@RequestMapping(value = "/selectSMSLog")
	public @ResponseBody
	String selectSMSLog(@RequestParam(value = "condition", required = false, defaultValue = "") String condition,
			@RequestParam(value = "start", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "limit", required = false, defaultValue = "30") int endLine,
			@RequestParam(value = "sort", required = false, defaultValue = "") String orderBy,
			@RequestParam(value = "dir", required = false, defaultValue = "") String orderDir) {
		logger.info("/listWaitforSendSMS condition:{}", new String[] { condition });
		String result = null;
		try {
			String[] conditionArray = condition.split("\\&");
			Map<String, String> map = new HashMap<String, String>();
			for (String str : conditionArray) {
				if (StringUtils.isNotBlank(str)) {
					String[] split = str.split("\\=");
					if (split != null && split.length == 2 && StringUtils.isNotBlank(split[0])
							&& StringUtils.isNotBlank(split[1])) {
						map.put(split[0], split[1]);
					}
				}
			}
			String conditionJson = JsonUtil.toJson(map);
			String url = propertiesUtil.getMsgcenterurl() + "/sms/selectSMSLog?condition=" + conditionJson
					+ "&startLine=" + startLine + "&endLine=" + endLine + "&orderBy=" + orderBy + "&orderDir="
					+ orderDir;
			String message = HttpUtil.getResultMessage(url);
			logger.info("selectSMSLog:"+message);
			JSONObject jsonObject = new JSONObject(message);
			String errorCode = jsonObject.getString("errorCode");
			if (errorCode.equals("0")) {
				result = jsonObject.getString("value");
			}
		} catch (Exception e) {
			logger.error("/listWaitforSendSMS error", e);
		}
		return result;
	}
	
	@RequestMapping(value = "/selectIOSLog")
	public @ResponseBody
	String selectIOSLog(@RequestParam(value = "condition", required = false, defaultValue = "") String condition,
			@RequestParam(value = "start", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "limit", required = false, defaultValue = "30") int endLine,
			@RequestParam(value = "sort", required = false, defaultValue = "") String orderBy,
			@RequestParam(value = "dir", required = false, defaultValue = "") String orderDir) {
		logger.info("/listWaitforSendSMS condition:{}", new String[] { condition });
		String result = null;
		try {
			String[] conditionArray = condition.split("\\&");
			Map<String, String> map = new HashMap<String, String>();
			for (String str : conditionArray) {
				if (StringUtils.isNotBlank(str)) {
					String[] split = str.split("\\=");
					if (split != null && split.length == 2 && StringUtils.isNotBlank(split[0])
							&& StringUtils.isNotBlank(split[1])) {
						map.put(split[0], split[1]);
					}
				}
			}
			String conditionJson = JsonUtil.toJson(map);
			String url = propertiesUtil.getMsgcenterurl() + "/ios/selectIOSLog?condition=" + conditionJson
					+ "&startLine=" + startLine + "&endLine=" + endLine + "&orderBy=" + orderBy + "&orderDir="
					+ orderDir;
			String message = HttpUtil.getResultMessage(url);
			logger.info("selectSMSLog:"+message);
			JSONObject jsonObject = new JSONObject(message);
			String errorCode = jsonObject.getString("errorCode");
			if (errorCode.equals("0")) {
				result = jsonObject.getString("value");
			}
		} catch (Exception e) {
			logger.error("/listWaitforSendSMS error", e);
		}
		return result;
	}
	
	@RequestMapping(value = "/selectWaitforSend")
	public @ResponseBody
	String selectWaitforSend(@RequestParam(value = "condition", required = false, defaultValue = "") String condition,
			@RequestParam(value = "start", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "limit", required = false, defaultValue = "30") int endLine,
			@RequestParam(value = "sort", required = false, defaultValue = "") String orderBy,
			@RequestParam(value = "dir", required = false, defaultValue = "") String orderDir) {
		logger.info("/listWaitforSendSMS condition:{}", new String[] { condition });
		String result = null;
		try {
			String[] conditionArray = condition.split("\\&");
			Map<String, String> map = new HashMap<String, String>();
			for (String str : conditionArray) {
				if (StringUtils.isNotBlank(str)) {
					String[] split = str.split("\\=");
					if (split != null && split.length == 2 && StringUtils.isNotBlank(split[0])
							&& StringUtils.isNotBlank(split[1])) {
						map.put(split[0], split[1]);
					}
				}
			}
			String conditionJson = JsonUtil.toJson(map);
			String url = propertiesUtil.getMsgcenterurl() + "/ios/selectWaitforSend?condition=" + conditionJson
					+ "&startLine=" + startLine + "&endLine=" + endLine + "&orderBy=" + orderBy + "&orderDir="
					+ orderDir;
			String message = HttpUtil.getResultMessage(url);
			JSONObject jsonObject = new JSONObject(message);
			String errorCode = jsonObject.getString("errorCode");
			if (errorCode.equals("0")) {
				result = jsonObject.getString("value");
			}
		} catch (Exception e) {
			logger.error("/listWaitforSendSMS error", e);
		}
		return result;
	}
	
}
