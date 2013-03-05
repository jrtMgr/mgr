package com.ruyicai.mgr.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tloguser;
import com.ruyicai.mgr.domain.Ttransaction;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.SMSUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/tpresents")
@Controller
public class TpresentController {

	private Logger logger = Logger.getLogger(TpresentController.class);

	@Autowired
	private PropertiesUtil util;

	@Autowired
	private SMSUtil smsUtil;

	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		view.setViewName("tpresents/info");
		return view;
	}
	@RequestMapping("/list")
	public ModelAndView list(@ModelAttribute("page") Page<Ttransaction> page, 
			HttpServletRequest request, ModelAndView view){
		try {
			Tloguser t = (Tloguser) request.getSession().getAttribute("user");
			Ttransaction.findList(page, "赠送"+t.getNickname());
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tpresents/page error", e);
		}
		view.setViewName("tpresents/info");
		return view;
	}
	@RequestMapping("/present")
	public ModelAndView present(@RequestParam("money") BigDecimal money,
			@RequestParam("draw") int draw,
			@RequestParam("subchannel") String subchannel,
			@RequestParam("channel") String channel,
			@RequestParam(value = "sms", required = false) String sms,
			@RequestParam("userflag") String userflag, ModelAndView view) {
		logger.info("tpresents/present");
		String viewname = "tpresents/present";
		try {
			if (BigDecimal.ONE.compareTo(money) > 0) {
				throw new RuntimeException("金额输入不正确");
			}
			if (0 != draw && 1 != draw) {
				throw new RuntimeException("可提现类型不正确");
			}
			if (StringUtil.isEmpty(userflag)) {
				throw new RuntimeException("请输入要赠送的用户标识");
			}
			String values[] = userflag.split("\\,");
			List<String> exists = new ArrayList<String>();
			List<String> notexists = new ArrayList<String>();
			for (String value : values) {
				String type = value.split("\\~")[0].trim();
				String flag = value.split("\\~")[1].trim();
				Tuserinfo tuserinfo = null;
				try {
					if ("0".equals(type)) {
						tuserinfo = Tuserinfo.findTuserinfoByuserno(flag,
								subchannel);
					} else if ("1".equals(type)) {
						tuserinfo = Tuserinfo.findTuserinfoesByMobileid(flag,
								subchannel);
					} else if ("2".equals(type)) {
						tuserinfo = Tuserinfo.findTuserinfoesByEmail(flag,
								subchannel);
					} else if ("3".equals(type)) {
						tuserinfo = Tuserinfo.findTuserinfoesByUserName(flag,
								subchannel);
					}
					if (null == tuserinfo) {
						notexists.add(type + "_" + flag);
					} else {
						exists.add(type + "_" + flag + "_"
								+ tuserinfo.getUserno());
					}
				} catch (Exception e) {
					notexists.add(type + "_" + flag);
				}
			}
			view.addObject("money", money);
			view.addObject("draw", draw);
			view.addObject("subchannel", subchannel);
			view.addObject("channel", channel);
			view.addObject("exists", exists);
			view.addObject("sms", sms);
			view.addObject("notexists", notexists);
		} catch (Exception e) {
			logger.error("tpresents/present error", e);
			view.addObject("errormsg", e.getMessage());
			viewname = "tpresents/info";
		}
		view.setViewName(viewname);
		return view;
	}

	@RequestMapping("/dopresent")
	public ModelAndView dopresent(
			@RequestParam("money") BigDecimal money,
			@RequestParam("draw") int draw,
			@RequestParam("subchannel") String subchannel,
			@RequestParam("channel") String channel,
			@RequestParam(value = "sms", required = false) String sms,
			@RequestParam("exists") String[] exists,
			@RequestParam(value = "notexists", required = false) String[] notexists,
			HttpServletRequest request,
			ModelAndView view) {
		logger.info("tpresents/dopresent");
		String url = util.getLotteryurl();
		List<String> success = new ArrayList<String>();
		List<String> fail = new ArrayList<String>();
		for (String exist : exists) {
			String[] values = exist.split("\\_");
			if (values.length != 3) {
				fail.add(exist);
				continue;
			}
			StringBuilder builder = new StringBuilder();
			Tloguser t = (Tloguser) request.getSession().getAttribute("user");
			builder.append("userno=").append(values[2]).append("&amt=")
					.append(money.multiply(new BigDecimal(100)))
					.append("&accesstype= ").append("&subchannel=")
					.append(subchannel).append("&channel=").append(channel)
					.append("&draw=").append(draw).append("&memo=").append("赠送"+t.getNickname());
			try {
				String result = HttpUtil.post(url + "/taccounts/doDirectChargeProcess",
						builder.toString());
				JSONObject json = new JSONObject(result);
				if ("0".equals(json.getString("errorCode"))) {
					success.add(exist);
					if ("1".equals(values[0])) {
						if (!StringUtil.isEmpty(sms)) {
							smsUtil.sendSMS(values[1], sms);
						}
					}
				} else {
					fail.add(exist);
				}
			} catch (Exception e) {
				fail.add(exist);
			}
		}
		if (null != notexists) {
			for (String notexist : notexists) {
				String[] values = notexist.split("\\_");
				if (values.length != 2) {
					fail.add(notexist);
					continue;
				}
				StringBuilder builder = new StringBuilder();
				builder.append("userState=0").append("&password=111111")
						.append("&subChannel=").append(subchannel)
						.append("&channel=").append(channel);
				if ("1".equals(values[0])) {
					builder.append("&mobileid=").append(values[1]);
				} else if ("2".equals(values[0])) {
					builder.append("&email=").append(values[1]);
				} else if ("3".equals(values[0])) {
					builder.append("&userName=").append(values[1]);
				} else {
					fail.add(notexist);
					continue;
				}
				try {
					String result = HttpUtil.post(url + "/tuserinfoes/register",
							builder.toString());
					JSONObject json = new JSONObject(result);
					if ("0".equals(json.getString("errorCode"))) {
						json = json.getJSONObject("value");
						String userno = json.getString("userno");
						builder = new StringBuilder();
						builder.append("userno=").append(userno)
								.append("&amt=")
								.append(money.multiply(new BigDecimal(100)))
								.append("&accesstype= ").append("&subchannel=")
								.append(subchannel).append("&channel=")
								.append(channel).append("&draw=").append(draw);
						try {
							result = HttpUtil.post(url
									+ "/taccounts/doDirectChargeProcess",
									builder.toString());
							json = new JSONObject(result);
							if ("0".equals(json.getString("errorCode"))) {
								success.add(notexist + "_" + userno);
								if ("1".equals(values[0])) {
									if (!StringUtil.isEmpty(sms)) {
										smsUtil.sendSMS(values[1], sms);
									}
								}
							} else {
								fail.add(notexist + "_" + userno);
							}
						} catch (Exception e) {
							fail.add(notexist + "_" + userno);
						}
					} else {
						fail.add(notexist);
					}
				} catch (Exception e) {
					fail.add(notexist);
				}
			}
		}
		view.addObject("success", success);
		view.addObject("fail", fail);
		view.setViewName("tpresents/result");
		return view;
	}
}
