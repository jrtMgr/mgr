package com.ruyicai.mgr.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.util.SMSUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/tsendsms")
@Controller
public class TsendSMSController {

	private Logger logger = Logger.getLogger(TsendSMSController.class);

	@Autowired
	private SMSUtil smsUtil;
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view) {
		view.addObject("lastnum", smsUtil.getSmsServiceProvider().queryBalance());
		view.setViewName("tsendsms/info");
		return view;
	}

	@RequestMapping("/send")
	public ModelAndView send(@RequestParam("sms") String sms,
			@RequestParam("mobileid") String mobileid, 
			@RequestParam("suffix") String suffix, 
			ModelAndView view) {
		logger.info("tsendsms/send");
		String viewname = "tsendsms/result";
		int success = 0;
		int fail = 0;
		List<String> failList = new ArrayList<String>();
		try {
			if (StringUtil.isEmpty(sms)) {
				throw new RuntimeException("短信内容不能为空");
			}
			if (StringUtil.isEmpty(mobileid)) {
				throw new RuntimeException("手机号码不能为空");
			}
			String[] mobileids = mobileid.split("\\,");
			for (String value : mobileids) {
				try {
					String result = "";
					if ("0".equals(suffix)) {
						result = smsUtil.sendSMSNotry(value.trim(), sms);
					}else if("91".equals(suffix)){
						result = smsUtil.sendSMS91(value.trim(), sms);
					}
					
					if (!"1".equals(result)) {
						fail += 1;
						failList.add(value);
					} else {
						success += 1;
					}
				} catch (Exception e) {
					fail += 1;
					failList.add(value);
				}
			}
		} catch (Exception e) {
			logger.error("tsendsms/send error", e);
			viewname = "tsendsms/info";
		}
		view.addObject("success", success);
		view.addObject("fail", fail);
		view.addObject("faillist", failList);
		view.setViewName(viewname);
		return view;
	}
}
