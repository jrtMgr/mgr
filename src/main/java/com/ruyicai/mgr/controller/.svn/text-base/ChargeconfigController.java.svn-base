package com.ruyicai.mgr.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.mysql.ChargecenterDao;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

import flexjson.JSONSerializer;

@RequestMapping("/chargeconfig")
@Controller
public class ChargeconfigController {

	private Logger logger = LoggerFactory.getLogger(ChargeconfigController.class);
	
	@Autowired
	ChargecenterDao chargecenterDao;
	
	@Autowired
	PropertiesUtil propertiesUtil;
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "transactionid", required = false) String transactionid,
			@ModelAttribute("page") Page<Map<String, Object>> page, ModelAndView view) {
		logger.info("/chargeconfig/list");
		try {
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			builder.append(" 1=1 and");
			if (!StringUtil.isEmpty(transactionid)) {
				builder.append(" o.transactionid = ? and");
				params.add(transactionid);
				view.addObject("transactionid", transactionid);
			}
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}

			chargecenterDao.findChargeconfigList(builder.toString(), params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("chargeconfig/list", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("chargeconfig/list");
		return view;
	}
	
	@RequestMapping("/toupdate")
	public ModelAndView toupdate(@RequestParam(value = "id", required = false) String id,
		ModelAndView view){
		try {
			view.addObject("c", chargecenterDao.findChargeconfig(id));
		} catch (Exception e) {
			logger.error("chargeconfig/toupdate error", e);
		}
		view.setViewName("chargeconfig/update");
		return view;
	}
	
	@RequestMapping("/update")
	public ModelAndView update(@RequestParam(value = "id", required = false) String id,
		@RequestParam(value = "memo", required = false) String memo,
		@RequestParam(value = "value", required = false) String value,
		ModelAndView view){
		String errormsg = "修改成功";
		try {
			String url = propertiesUtil.getChargeCenterurl() + "/charge!modifychargeconfig";
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("id", URLEncoder.encode(id,"UTF-8"));
			param.put("memo", URLEncoder.encode(memo,"UTF-8"));
			param.put("value", URLEncoder.encode(value,"UTF-8"));
			String param1 = "jsonString=" + new JSONSerializer().serialize(param);;
			String result = HttpUtil.post(url, param1);
			JSONObject jsObject = new JSONObject(result);
			if(!"0".equals(jsObject.getString("error_code"))) {
				errormsg  = "修改失败!";
			}
		} catch (Exception e) {
			logger.error("chargeconfig/update error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.toupdate(id, view);
	}
}
