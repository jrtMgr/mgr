package com.ruyicai.mgr.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Ttransaction;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping(value = "/userchannel")
@Controller()
public class UserChannelController {

	private Logger logger = LoggerFactory.getLogger(Tuserinfo.class);
	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam(value = "userno", required = false) String userno,
			@RequestParam(value = "mobileid", required = false) String mobileid,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "certid", required = false) String certid,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "nickname", required = false) String nickname,
			@ModelAttribute("page") Page<Tuserinfo> page, ModelAndView view) {
		logger.info("userchannel/list");
		StringBuilder builder = new StringBuilder("where");
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isEmpty(userno)) {
			builder.append(" o.userno=? and");
			params.add(userno);
		}
		if (!StringUtil.isEmpty(mobileid)) {
			builder.append(" o.mobileid=? and");
			params.add(mobileid);
		}
		if (!StringUtil.isEmpty(email)) {
			builder.append(" o.email=? and");
			params.add(email);
		}
		if (!StringUtil.isEmpty(userName)) {
			builder.append(" o.userName=? and");
			params.add(userName);
		}
		if (!StringUtil.isEmpty(certid)) {
			builder.append(" o.certid=? and");
			params.add(certid);
		}
		if (!StringUtil.isEmpty(name)) {
			builder.append(" o.name=? and");
			params.add(name);
		}
		if (!StringUtil.isEmpty(nickname)) {
			builder.append(" o.nickname=? and");
			params.add(nickname);
		}
		if (builder.toString().endsWith("and")) {
			builder.delete(builder.length() - 3, builder.length());
		}
		if (builder.toString().endsWith("where")) {
			builder.delete(builder.length() - 5, builder.length());
		}
		try {
			if (!StringUtil.isEmpty(builder.toString()))
				Tuserinfo.findList(builder.toString(), " order by o.regtime desc", params, page);

		} catch (Exception e) {
			logger.error("userchannel/list出错", e);
		}
		view.addObject("page", page);
		view.setViewName("userchannel/list");
		return view;
	}
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	@RequestMapping("/updateUserChannel")
	public ModelAndView updateUserChannel(@RequestParam("userno") String userno,
			@RequestParam(value = "channel") String channel, ModelAndView view) {
		logger.info("/userchannel/updateUserCertid");
		String errormsg = "修改成功";
		try {
			logger.info("用户：" + userno + "，channel：" + channel);
			Tuserinfo tuserinfo = Tuserinfo.findTuserinfo(userno);
			tuserinfo.setChannel(channel);
			tuserinfo.merge();
			String result = HttpUtil.post(propertiesUtil.getLotteryurl() + "/tuserinfoes/modify", "userno=" + userno);
			JSONObject json = new JSONObject(result);
			if (!"0".equals(json.getString("errorCode"))) {
				errormsg = "修改失败，返回码：" + json.getString("errorCode");
			}
		} catch (Exception e) {
			logger.error("userchannel/updateUserChannel", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.list(userno, null, null, null, null, null, null, new Page<Tuserinfo>(), view);
	}

}
