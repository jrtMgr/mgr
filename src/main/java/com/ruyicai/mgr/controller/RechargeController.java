package com.ruyicai.mgr.controller;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.consts.Const;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/recharge")
@Controller
public class RechargeController {

	private Logger logger = Logger.getLogger(RechargeController.class);
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@RequestMapping("/page")
	public String page() {
		return "recharge/processCharge";
	}
	
	@RequestMapping("/processCharge")
	public ModelAndView processCharge(
			@RequestParam("userid") String userid,
			@RequestParam("accesstype") String accesstype,
			@RequestParam("cardno") String cardno,
			@RequestParam("cardpwd") String cardpwd,
			@RequestParam("channel") String channel,
			@RequestParam("subchannel") String subchannel,
			@RequestParam(value = "type") String type,
			ModelAndView view) {
		logger.info("recharge/processCharge");
		logger.info("type=" + type + ";" + "userid=" + userid + ";" + "accesstype=" + accesstype + ";" + "cardno=" + cardno + ";"
				+ "cardpwd=" + cardpwd + ";" + "channel=" + channel + ";" + "subchannel=" + subchannel);
		String errormsg = null;
		try {
			if (StringUtil.isEmpty(type) || StringUtil.isEmpty(userid)) {
				view.addObject("type", type);
				view.addObject("errormsg", "type或者userid为空");
				view.setViewName("recharge/processCharge");
				return view;
			}

			Tuserinfo user = null;
			if (Const.Tuserinfo_Mobileid.equals(type)) {
				user = Tuserinfo.findTuserinfoesByMobileid(userid, subchannel);
			} else if (Const.Tuserinfo_Email.equals(type)) {
				user = Tuserinfo.findTuserinfoesByEmail(userid, subchannel);
			} else if (Const.Tuserinfo_UserName.equals(type)) {
				user = Tuserinfo.findTuserinfoesByUserName(userid, subchannel);
			}
			view.addObject("type", type);

			if (user == null) {
				view.addObject("errormsg", "用户不存在");
				view.setViewName("recharge/processCharge");
				return view;
			}
			StringBuilder param = new StringBuilder();
			param.append("jsonString={userno:'").append(user.getUserno())
					.append("',accesstype:'").append(accesstype.trim())
					.append("',cardno:'").append(cardno.trim())
					.append("',cardpwd:'").append(cardpwd.trim())
					.append("',channel:'").append(channel.trim())
					.append("',subchannel:'").append(subchannel)
					.append("',agencyno:'").append(user.getAgencyno())
					.append("',chargetype:'1',bankid:'ryc001',paytype:'0300'}");
			String url = propertiesUtil.getChargeCenterurl()
					+ "/ruyicaicardcharge!ruyicaiCardCharge";
			logger.info("url:" + url + "?" + param.toString());
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);
			if ("0".equals(json.getString("error_code"))) {
				errormsg = "充值成功";
			} else if ("240002".equals(json.getString("error_code"))) {
				errormsg = "数据库中对应的平台充值卡记录不存在";
			} else if ("240003".equals(json.getString("error_code"))) {
				errormsg = "数据库中对应的平台充值卡记录密码与用户输入的不符";
			} else if ("240004".equals(json.getString("error_code"))) {
				errormsg = "数据库中对应的平台充值卡记录状态不为激活状态";
			} else if ("240005".equals(json.getString("error_code"))) {
				errormsg = "数据库中对应的平台充值卡金额记录小于0";
			} else if ("240006".equals(json.getString("error_code"))) {
				errormsg = "校验如意卡信息出现错误";
			} else if ("240104".equals(json.getString("error_code"))) {
				errormsg = "数据库中对应的平台充值卡记录已过期";
			} else if ("800010".equals(json.getString("error_code"))) {
				errormsg = "卡的chargetype有误";
			} else if ("500".equals(json.getString("error_code"))) {
				errormsg = "远程服务器内部错误";
			}

		} catch (Exception e) {
			errormsg = "充值失败";
			logger.error("recharge/processCharge error", e);
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("recharge/processCharge");
		return view;
	}
}
