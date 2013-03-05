package com.ruyicai.mgr.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.controller.dto.BigUserTransactionDto;
import com.ruyicai.mgr.domain.Tsubchannel;
import com.ruyicai.mgr.domain.Ttransaction;
import com.ruyicai.mgr.util.DateUtil;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;

@RequestMapping("/biguserCW")
@Controller
public class BigUserCWController {
	
	private Logger logger = LoggerFactory.getLogger(BigUserCWController.class);
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@RequestMapping("/list")
	public ModelAndView list(ModelAndView view){
		logger.info("biguserCW/list");
		List<Ttransaction> list = Ttransaction.findTransactionByTypeAndState(new BigDecimal(9), new BigDecimal(0));
		List<BigUserTransactionDto> blist = new ArrayList<BigUserTransactionDto>();
		String state = "";
		String type = "";
		Tsubchannel subchannel;
		BigUserTransactionDto b;
		try {
			for (Ttransaction t : list) {
				if (t.getState().intValue() == 0) {
					state = "未完成";
				}else{
					state = "";
				}
				if(t.getType().intValue() == 9){
					type = "大客户充值";
				}else{
					type = "";
				}
				subchannel = Tsubchannel.findTsubchannel(t.getUserno());
				b = new BigUserTransactionDto(t.getId(), type, t.getPlattime(),
						t.getAmt().divide(new BigDecimal(100)), state, t.getUserno(), subchannel.getAgencyname(), subchannel.getTelephone());
				
				blist.add(b);
			}
			view.addObject("list", blist);
		} catch (Exception e) {
			logger.error("biguserCW/list error:", e);
		}
		view.setViewName("biguserCW/list");
		return view;
	}
	
	@RequestMapping("/confirm")
	public ModelAndView confirm(@RequestParam("id") String id,
			ModelAndView view){
		logger.info("biguserCW/confirm");
		String errormsg = "充值成功";
		try {
					
			Ttransaction t = Ttransaction.findTtransaction(id);
			StringBuffer params = new StringBuffer();
			params.append("ttransactionid=").append(id)
			.append("&bankorderid=").append(" ")
			.append("&bankordertime=").append(DateUtil.format(new Date()))
			.append("&banktrace=").append(" ")
			.append("&bankorderid=").append(" ")
			.append("&retcode=").append(" ")
			.append("&retmemo=").append(" ")
			.append("&amt=").append(t.getAmt())
			.append("&drawamt=").append(t.getAmt());
			String result = HttpUtil.post(propertiesUtil.getLotteryurl()+"/taccounts/doChargeProcess", params.toString());
			JSONObject json = new JSONObject(result);
			if (!"0".equals(json.getString("errorCode"))) {
				errormsg = "充值失败,返回码：" + json.getString("errorCode");
			}
		} catch (Exception e) {
			logger.error("biguserCW/confirm error:", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("biguserCW/list");
		return view;
	}
	
	@RequestMapping("/cannel")
	public ModelAndView cannel(@RequestParam("id") String id,
			ModelAndView view){
		logger.info("biguserCW/confirm");
		String errormsg = "取消充值成功";
		try {
			Ttransaction ttransaction = Ttransaction.findTtransaction(id);
			ttransaction.setState(new BigDecimal(2));
			ttransaction.merge();
			
		} catch (Exception e) {
			logger.error("biguserCW/cannel error:", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("biguserCW/list");
		return view;
	}
}
