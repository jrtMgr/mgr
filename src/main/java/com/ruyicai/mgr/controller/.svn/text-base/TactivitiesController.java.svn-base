package com.ruyicai.mgr.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tactivities;
import com.ruyicai.mgr.domain.Tloguser;
import com.ruyicai.mgr.exception.RuyicaiException;
import com.ruyicai.mgr.lot.Lottype;
import com.ruyicai.mgr.service.TactivitiesService;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.PropertiesUtil;


@RequestMapping("/tactivities")
@Controller
public class TactivitiesController {
	private Logger logger = Logger.getLogger(TactivitiesController.class);
	
	@Autowired
	private TactivitiesService tactivitiesService;
	
	@Autowired
	PropertiesUtil propertiesUtil;
	
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "lotno", required = false) String lotno,
			@RequestParam(value = "subchannel", required = false) String subchannel,
			@RequestParam(value = "channel", required = false) String channel,
			@RequestParam(value = "activitytype", required = false) Integer activitytype,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "startLine", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "endLine", required = false, defaultValue = "20") int endLine,
			@RequestParam(value = "orderBy", required = false, defaultValue = "") String orderBy,
			@RequestParam(value = "orderDir", required = false, defaultValue = "") String orderDir,ModelAndView view) {
		logger.info("/tactivities/list");
		view.addObject("lotno", lotno);
		view.addObject("subchannel", subchannel);
		view.addObject("channel", channel);
		view.addObject("activitytype", activitytype);
		view.addObject("state", state);
		view.addObject("startLine", startLine);
		view.addObject("endLine", endLine);
		try {
			StringBuilder url = new StringBuilder(propertiesUtil.getActioncenterurl()
					+ "/tactivity/selectTactivity?");
			if(StringUtils.isNotBlank(state)){
				url.append("&state=" + state);
			}
			if (StringUtils.isNotBlank(orderBy)) {
				url.append("&orderBy=" + orderBy);
			}
			if (StringUtils.isNotBlank(orderDir)) {
				url.append("&orderDir=" + orderDir);
			}
			url.append("&startLine=" + startLine);
			url.append("&endLine=" + endLine);
			String resultMessage = HttpUtil.getResultMessage(url.toString());
			resultMessage = JsonUtil.escapeJavaScript(resultMessage);
			view.addObject("result", resultMessage);
			Map<String, String> lotTypes = Lottype.getMap();
			view.addObject("lotTypes", lotTypes);
		} catch (Exception e) {
			logger.error("caselot/listMg error:", e);
			view.addObject("errormsg", "活动管理分页查询异常");
		}	
		view.setViewName("tactivities/list");
		return view;
	}
	
	/**
	 * 更改活动状态
	 * @param id
	 * @param state
	 * @param request
	 * @param view
	 * @return
	 */
	@RequestMapping("/updateActiveState")
	public @ResponseBody
	ResponseData updateActiveState(@RequestParam("id") String id,
			@RequestParam("state") String state,
			HttpServletRequest request) {
		logger.info("tactivities/updateActiveState");
		Tloguser user = (Tloguser) request.getSession().getAttribute("user");
		String username = user.getNickname();
		String activeUrl = propertiesUtil.getActioncenterurl();
		String param = "id=" + id + "&state=" + state;
		String updateActiveStateUrl = activeUrl + "/tactivity/updateState";
		logger.info("updateActiveStateUrl:" + updateActiveStateUrl + ", param:"
				+ param);
		String result = null;
		String errorCode = null;
		try {
			result = HttpUtil.post(updateActiveStateUrl, param);
			JSONObject json = new JSONObject(result);
			errorCode = (String) json.get("errorCode");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseData rd = new ResponseData();
		if (errorCode.equals("0") || errorCode == "0") {
			if(state=="0"||state.equals("0")){
				logger.info("操作者:" + username + ", 操作:关闭活动" + " ,活动编号：" + id);
			}else if (state == "1"||state.equals("1")){
				logger.info("操作者:" + username + ", 操作:开启活动" + " ,活动编号：" + id);
			}
			ErrorCode ec = ErrorCode.OK;
			rd.setValue(ec.memo);
			rd.setErrorCode(ec.value);
		}
		return rd;
	}
	
	@RequestMapping("/toset")
	public ModelAndView toset(
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "lotno", required = false) String lotno,
			@RequestParam(value = "subchannel", required = false) String subchannel,
			@RequestParam(value = "channel", required = false) String channel,
			@RequestParam(value = "activitytype", required = false) Integer activitytype,
			@RequestParam(value = "amt", required = false) String amt,
			@RequestParam(value = "state", required = false) Integer state,
			ModelAndView view) {
		logger.info("/tactivities/toset");
		
		try {			
			view.addObject("id", id);
			view.addObject("lotno", lotno);
			view.addObject("subchannel", subchannel);
			view.addObject("channel", channel);
			view.addObject("activitytype", activitytype);
			view.addObject("amt", amt);
			view.addObject("state", state);			
		} catch (Exception e) {
			logger.error("tactivities/toset error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("tactivities/set");
		return view;
	}
	
	@RequestMapping("/set")
	public ModelAndView set(
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "lotno", required = false) String lotno,
			@RequestParam(value = "subchannel", required = false) String subchannel,
			@RequestParam(value = "channel", required = false) String channel,
			@RequestParam(value = "activitytype", required = false) Integer activitytype,
			@RequestParam(value = "amt", required = false) String amt,
			@RequestParam(value = "state", required = false) Integer state,
			ModelAndView view) {
		logger.info("/tactivities/set");
		String errormsg = null;	
		
		channel = channel == null ? null : channel.trim();
		amt = amt == null ? null : amt.trim();
		try {			
			view.addObject("id", id);
			view.addObject("lotno", lotno);
			view.addObject("subchannel", subchannel);
			view.addObject("channel", channel);
			view.addObject("activitytype", activitytype);
			view.addObject("amt", amt);
			view.addObject("state", state);
			Tactivities.updateTactivities(id, lotno, subchannel, channel, activitytype, amt, state);
			errormsg = "活动维护成功";
		} catch (Exception e) {
			logger.error("tactivities/set error", e);
			errormsg = e.getMessage();			
		}
		
		view.addObject("errormsg", errormsg);
		view.setViewName("tactivities/set");
		return view;
	}
	
	@RequestMapping("/add")
	public ModelAndView add(			
			@RequestParam(value = "lotno", required = false) String lotno,
			@RequestParam(value = "subchannel", required = false) String subchannel,
			@RequestParam(value = "channel", required = false) String channel,
			@RequestParam(value = "activitytype", required = false) Integer activitytype,
			@RequestParam(value = "amt", required = false) String amt,
			@RequestParam(value = "state", required = false) Integer state,
			ModelAndView view) {
		logger.info("/tactivities/add");
		String errormsg = null;	
		String id = "";
		channel = channel == null ? null : channel.trim();
		amt = amt == null ? null : amt.trim();
		try {
			if (null == activitytype) {
				view.setViewName("tactivities/add");
				return view;
			}
			view.addObject("lotno", lotno);
			view.addObject("subchannel", subchannel);
			view.addObject("channel", channel);
			view.addObject("activitytype", activitytype);
			view.addObject("amt", amt);
			view.addObject("state", state);
			Tactivities tactivities = Tactivities.createIfNotExist(lotno, subchannel, channel, activitytype, amt, state);
			errormsg = "活动添加成功";			
			id = String.valueOf(tactivities.getId());
		} catch (RuyicaiException e) {
			logger.error("活动已存在", e);
			errormsg = e.getMessage();			
		} catch (Exception e) {
			logger.error("tactivities/add error", e);
			errormsg = e.getMessage();			
		}
		view.addObject("id", id);
		view.addObject("errormsg", errormsg);
		view.setViewName("tactivities/add");
		return view;
	}
		
	@RequestMapping("/addbatch")
	public ModelAndView addbatch(			
			@RequestParam(value = "lotno", required = false) String lotno,
			@RequestParam(value = "subchannel", required = false) String subchannel,
			@RequestParam(value = "channel", required = false) String channel,
			@RequestParam(value = "activitytype", required = false) Integer activitytype,
			@RequestParam(value = "amt", required = false) String amt,
			@RequestParam(value = "state", required = false) Integer state,
			ModelAndView view) {
		logger.info("/tactivities/addbatch");
		String errormsg = null;	
		channel = channel == null ? null : channel.trim();
		amt = amt == null ? null : amt.trim();
		try {
			if (null == activitytype) {
				view.setViewName("tactivities/addbatch");
				return view;
			}
			view.addObject("id", "");
			view.addObject("lotno", lotno);
			view.addObject("subchannel", subchannel);
			view.addObject("channel", channel);
			view.addObject("activitytype", activitytype);
			view.addObject("amt", amt);
			view.addObject("state", state);
			tactivitiesService.addbatch(lotno, subchannel, channel, activitytype, amt, state);
			errormsg = "活动批量添加成功";	
		} catch (RuyicaiException e) {
			logger.error("活动批量添加失败", e);
			errormsg = e.getMessage();			
		} catch (Exception e) {
			logger.error("tactivities/addbatch error", e);
			errormsg = e.getMessage();			
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("tactivities/addbatch");
		return view;
	}
	@RequestMapping("/setbatch")
	public ModelAndView setbatch(			
			@RequestParam(value = "lotno", required = false) String lotno,
			@RequestParam(value = "subchannel", required = false) String subchannel,
			@RequestParam(value = "channel", required = false) String channel,
			@RequestParam(value = "activitytype", required = false) Integer activitytype,
			@RequestParam(value = "amt", required = false) String amt,
			@RequestParam(value = "state", required = false) Integer state,
			ModelAndView view) {
		logger.info("/tactivities/setbatch");
		String errormsg = null;	
		channel = channel == null ? null : channel.trim();
		amt = amt == null ? null : amt.trim();
		try {
			if (null == activitytype) {
				view.setViewName("tactivities/setbatch");
				return view;
			}
			view.addObject("id", "");
			view.addObject("lotno", lotno);
			view.addObject("subchannel", subchannel);
			view.addObject("channel", channel);
			view.addObject("activitytype", activitytype);
			view.addObject("amt", amt);
			view.addObject("state", state);
			tactivitiesService.setbatch(lotno, subchannel, channel, activitytype, amt, state);
			errormsg = "活动批量维护成功";
		} catch (RuyicaiException e) {
			logger.error("活动批量维护失败", e);
			errormsg = e.getMessage();			
		} catch (Exception e) {
			logger.error("tactivities/setbatch error", e);
			errormsg = e.getMessage();			
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("tactivities/setbatch");
		return view;
	}
	
}
