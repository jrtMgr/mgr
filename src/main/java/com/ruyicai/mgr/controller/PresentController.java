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

import com.ruyicai.mgr.lot.Lottype;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.PropertiesUtil;

import flexjson.JSONSerializer;

@RequestMapping(value = "/present")
@Controller
public class PresentController {

	private Logger logger = Logger.getLogger(PresentController.class);
	
	@Autowired
	PropertiesUtil propertiesUtil;
	
	@RequestMapping("/presentMsg")
	public  ModelAndView presentList(
			@RequestParam(value = "orderid", required = false)String orderid,
			@RequestParam(value = "lotno", required = false)String lotno,
			@RequestParam(value = "batchcode", required = false)String batchcode,
			@RequestParam(value = "buyUserno", required = false)String buyUserno,
			@RequestParam(value = "reciverMobile", required = false)String reciverMobile,
			@RequestParam(value = "reciverUserno", required = false)String reciverUserno,
			@RequestParam(value = "reciveState", required = false)String reciveState,
			@RequestParam(value = "startLine", required = false, defaultValue = "0") Integer startLine,
			@RequestParam(value = "endLine", required = false, defaultValue = "30") Integer endLine,
			@RequestParam(value = "orderBy", required = false) String orderBy,
			@RequestParam(value = "orderDir", required = false) String orderDir, ModelAndView view){
		logger.info("present/presentMsg");
		view.addObject("startLine", startLine);
		view.addObject("endLine" , endLine);
		try {
			StringBuilder url = new StringBuilder(propertiesUtil.getLotteryurl()
					+ "/present/selectPresentDetails?");
			Map<String, String> map = new HashMap<String, String>();
			if (StringUtils.isNotBlank(orderid)) {
				map.put("EQS_orderid", orderid);
			}
			if (StringUtils.isNotBlank(lotno)) {
				map.put("EQS_lotno", lotno);
			}
			if (startLine != null) {
				url.append("&startLine=" + startLine);
			}
			if (endLine != null) {
				url.append("&endLine=" + endLine);
			}
			if (StringUtils.isNotBlank(batchcode)) {
				map.put("EQS_batchCode", batchcode);
			}
			if (StringUtils.isNotBlank(buyUserno)) {
				map.put("EQS_buyUserno", buyUserno);
			}
			if (StringUtils.isNotBlank(reciverMobile)) {
				map.put("EQS_reciverMobile", reciverMobile);
			}
			if (StringUtils.isNotBlank(reciverUserno)) {
				map.put("EQS_reciverUserno", reciverUserno);
			}
			if (StringUtils.isNotBlank(reciveState)) {
				map.put("EQG_reciveState", reciveState);
			}
			if (StringUtils.isNotBlank(orderBy)) {
				url.append("&orderBy=" + orderBy);
			}
			if (StringUtils.isNotBlank(orderDir)) {
				url.append("&orderDir=" + orderDir);
			}
			String condition = new JSONSerializer().serialize(map);
			url.append("&condition=" + condition);
			String resultMessage = HttpUtil.getResultMessage(url.toString());
			resultMessage = JsonUtil.escapeJavaScript(resultMessage);
			view.addObject("result", resultMessage);

			Map<String, String> lotTypes = Lottype.getMap();
			view.addObject("lotTypes", lotTypes);
		} catch (Exception e) {
			logger.error("present/presentMsg error:", e);
			view.addObject("errormsg", "赠送中心管理赠送分页查询异常");
		}
		view.setViewName("present/presentList");
		return view;
	}
	
}
