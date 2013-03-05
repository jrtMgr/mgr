package com.ruyicai.mgr.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tloguser;
import com.ruyicai.mgr.lot.Lottype;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.PropertiesUtil;

import flexjson.JSONSerializer;

@RequestMapping(value = "/addnumber")
@Controller
public class TaddnumberController {
	private Logger logger = Logger.getLogger(TaddnumberController.class);
	@Autowired
	PropertiesUtil propertiesUtil;
	/**
	 * 追号管理
	 * 
	 * @param condition
	 * @param startLine
	 * @param endLine
	 * @param orderBy
	 * @param orderDir
	 * @return
	 */
	@RequestMapping("/addNumberMg")
	public ModelAndView addNumberMg(
			@RequestParam(value = "state", required = false, defaultValue = "") String state,
			@RequestParam(value = "starttime", required = false, defaultValue = "") String starttime,
			@RequestParam(value = "endtime", required = false, defaultValue = "") String endtime,
			@RequestParam(value = "userno", required = false, defaultValue = "") String userno,
			@RequestParam(value = "beginbatch", required = false, defaultValue = "") String beginbatch,
			@RequestParam(value = "lastbatch", required = false, defaultValue = "") String lastbatch,
			@RequestParam(value = "lotno", required = false, defaultValue = "") String lotno,
			@RequestParam(value = "startLine", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "endLine", required = false, defaultValue = "30") int endLine,
			@RequestParam(value = "orderBy", required = false, defaultValue = "") String orderBy,
			@RequestParam(value = "orderDir", required = false, defaultValue = "") String orderDir,
			ModelAndView view) {
		logger.info("addnumber/addNumberPage");
		view.addObject("startLine", startLine);
		view.addObject("endLine", endLine);
		view.addObject("orderBy", orderBy);
		view.addObject("orderDir", orderDir);
		try {
			StringBuilder url = new StringBuilder(
					propertiesUtil.getLotteryurl()
							+ "/select/selectTsubscribes?");
			Map<String, Object> map = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(starttime)) {
				map.put("GED_ordertime", starttime);
				view.addObject("starttime", starttime);
			}
			if (StringUtils.isNotBlank(starttime)) {
				map.put("LED_ordertime", endtime);
				view.addObject("endtime", endtime);
			}
			if (StringUtils.isNotBlank(lotno)) {
				map.put("EQS_lotno", lotno);
				view.addObject("lotno", lotno);
			}
			if (StringUtils.isNotBlank(state)) {
				map.put("EQG_state", state);
				view.addObject("state", state);
			}
			if (StringUtils.isNotBlank(userno)) {
				map.put("EQS_userno", userno);
				view.addObject("userno", userno);
			}
			if (StringUtils.isNotBlank(beginbatch)) {
				map.put("EQS_beginbatch", beginbatch);
				view.addObject(beginbatch);
			}
			if (StringUtils.isNotBlank(lastbatch)) {
				map.put("EQS_lastbatch", lastbatch);
				view.addObject(lastbatch);
			}
			url.append("startLine=" + startLine);
			url.append("&endLine=" + endLine);
			if (StringUtils.isNotBlank(orderBy)) {
				url.append("&orderBy=" + URLEncoder.encode(orderBy));
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
			logger.error("addnumber/addNumberMg error:", e);
			view.addObject("errormsg", "追号管理分页查询异常");
		}
		view.setViewName("addnumber/addNumberMg");
		return view;
	}

	/**
	 * 撤销追号
	 * @param tsubscribeNo(flowno)
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/cancelAddNumber", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData cancelAddNumber(
			@RequestParam("tsubscribeNo") String tsubscribeNo,
			HttpServletRequest request) throws Exception {
		logger.info("addnumber/cancelAddNumber");
		String lotteryUrl = propertiesUtil.getLotteryurl();
		String param = "tsubscribeNo=" + tsubscribeNo;
		String cancelAddNumberUrl = lotteryUrl+ "/bet/giveupSubscribe";
		logger.info("lotteryUrl:" + lotteryUrl + " , param:" + param);
		String result = null;
		String errorCode = null;
		try {
			result = HttpUtil.post(cancelAddNumberUrl, param);
			JSONObject json = new JSONObject(result);
			errorCode = (String) json.get("errorCode");
		} catch (Exception e) {
			logger.error("addnumber/cancelAddNumber error:", e);
		}
		Tloguser user = (Tloguser) request.getSession().getAttribute("user");
		String username = user.getNickname();
		ResponseData rd = new ResponseData();
		if(errorCode.equals("0")||errorCode == "0"){
			logger.info("操作人:" + username + ", 操作:撤销追号" + " ,追号流水编号:" + tsubscribeNo);
			ErrorCode ec = ErrorCode.OK;
			rd.setValue(ec.memo);
			rd.setErrorCode(ec.value);
		}
		return rd;
	}
}
