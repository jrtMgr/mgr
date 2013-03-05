package com.ruyicai.mgr.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
/**
 * 合并用户
 * @author Administrator
 *
 */
@RequestMapping(value = "/tcombine")
@Controller()
public class TcombinetuserinfoController {
	private Logger logger = LoggerFactory.getLogger(TcombinetuserinfoController.class);
	@Autowired
	PropertiesUtil propertiesUtil;
	@RequestMapping(value = "/list")
	public ModelAndView list(ModelAndView view){
		logger.info("tcombine/list");		
		view.setViewName("tcombine/list");
		return view;
	}
	/*
	 * username不重复合并
	 */
	@RequestMapping(value="/combineTuserinfo")
	public ModelAndView combineTuserinfo(@RequestParam("fromUserno") String fromUserno,
			@RequestParam("toUserno") String toUserno,
			HttpServletRequest request,ModelAndView view){
		logger.info("tcombine/combineTuserinfo");
		view.setViewName("tcombine/list");
		Tloguser user = (Tloguser) request.getSession().getAttribute("user");
		String username = user.getNickname();
		String combineTuserinfoUrl = propertiesUtil.getLotteryurl() + "/tuserinfoes/combineTuserinfo";
		String param = "fromUserno=" + fromUserno + "&toUserno=" + toUserno;
		String result = null;
		String errorCode = null;
		try {
			result = HttpUtil.post(combineTuserinfoUrl, param);
			logger.info("post请求返回value:" + result);
			JSONObject json = new JSONObject(result);
			errorCode = (String) json.get("errorCode");
		} catch (Exception e) {
			logger.error("tcombine/combineTuserinfo error", e);
		}
		if (errorCode.equals("0") || errorCode == "0") {
			logger.info("操作者:" + username + ", 操作:对username不重复用户进行合并" + " ,from：" + fromUserno + "，to：" + toUserno);
			view.addObject("errormsg","合并用户成功");
		}else{
			view.addObject("errormsg","合并用户失败");
		}
		return view;
	}
	
	/*
	 * username重复合并
	 */
	@RequestMapping(value="/combineRepeatUsername")
	public ModelAndView combineRepeatUsername(@RequestParam("fromUserno") String fromUserno,
			@RequestParam("toUserno") String toUserno,
			HttpServletRequest request,ModelAndView view){
		logger.info("tcombine/combineRepeatUsername");
		view.setViewName("tcombine/list");
		Tloguser user = (Tloguser) request.getSession().getAttribute("user");
		String username = user.getNickname();
		String combineTuserinfoUrl = propertiesUtil.getLotteryurl() + "/tuserinfoes/combineRepeatUsername";
		String param = "fromUserno=" + fromUserno + "&toUserno=" + toUserno;
		String result = null;
		String errorCode = null;
		try {
			result = HttpUtil.post(combineTuserinfoUrl, param);
			logger.info("post请求返回value:" + result);
			JSONObject json = new JSONObject(result);
			errorCode = (String) json.get("errorCode");
		} catch (Exception e) {
			logger.error("tcombine/combineRepeatUsername error", e);
		}
		if (errorCode.equals("0") || errorCode == "0") {
			logger.info("操作者:" + username + ", 操作:对username重复用户进行合并" + " ,from：" + fromUserno + "，to：" + toUserno);
			view.addObject("errormsg","合并用户成功");
		}else{
			view.addObject("errormsg","合并用户失败");
		}
		return view;
	}
}
