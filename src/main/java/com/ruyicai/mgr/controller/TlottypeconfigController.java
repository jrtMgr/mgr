package com.ruyicai.mgr.controller;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tlottypeconfig;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/tlottypeconfig")
@Controller
public class TlottypeconfigController {
	
	private Logger logger = Logger.getLogger(TlottypeconfigController.class);
	
	@Autowired
	private PropertiesUtil propertiesUtil;

	@RequestMapping("/list")
	public ModelAndView list(ModelAndView view) {
		logger.info("tlottypeconfig/list");
		try {
			view.addObject("tlottypeconfigs", Tlottypeconfig.findAllTlottypeconfigs());
		} catch (Exception e) {
			view.addObject("errormsg", "查询出错");
			logger.error("tlottypeconfig/list error", e);
		}
		view.setViewName("tlottypeconfig/list");
		return view;
	}
	
	@RequestMapping("/update")
	public ModelAndView updateHemaiEndtime(@RequestParam("lotno") String lotno,
			@RequestParam("state") BigDecimal state,
			@RequestParam("lvl") BigDecimal lvl,
			@RequestParam("onprize") int onprize,
			@RequestParam("autoencash") BigDecimal autoencash,
			@RequestParam("lotcenterisvalid") BigDecimal lotcenterisvalid,
			@RequestParam("betendtime") String betendtime,
			@RequestParam("hemaibetendtime") String hemaibetendtime,
			ModelAndView view) {
		logger.info("tlottypes/update");
		String errormsg = "修改成功";
		try {
			Tlottypeconfig tlottype = Tlottypeconfig.findTlottypeconfig(lotno);
			tlottype.setState(state);
			tlottype.setLvl(lvl);
			tlottype.setOnprize(onprize);
			tlottype.setAutoencash(autoencash);
			tlottype.setLotcenterisvalid(lotcenterisvalid);
			tlottype.setBetendtime(betendtime);
			tlottype.setHemaibetendtime(hemaibetendtime);
			tlottype.merge();
			
			String lotteryurls = propertiesUtil.getLotteryUrls();
			String[] urls = lotteryurls.split("\\,");
			for(String url : urls) {
				String result = HttpUtil.post(url + "/system/reinit", "");
				JSONObject jsonObject = new JSONObject(result);
				if(!"0".equals(jsonObject.getString("errorCode"))) {
					errormsg += url + "通知出错,";
				}
			}
			if(!StringUtil.isEmpty(errormsg)) {
				view.addObject("errormsg", errormsg);
			}
		} catch (Exception e) {
			errormsg = "出错"+e.getMessage();
			logger.error("tlotctrls/list error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.list(view);
	}
}
