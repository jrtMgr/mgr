package com.ruyicai.mgr.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tlot;
import com.ruyicai.mgr.domain.Torder;
import com.ruyicai.mgr.domain.Ttransaction;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.domain.statis.Channel;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping(value = "/webstatistic")
@Controller
public class WebstatisticController {
	private Logger logger = Logger.getLogger(WebstatisticController.class);

	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		view.addObject("channels",Channel.findChannelByYwid(19));
		view.setViewName("webstatistic/list");
		return view;
	}
	
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "channelid") String channelid,
			@RequestParam(value = "starttime") String starttime,
			@RequestParam(value = "endtime") String endtime,
			@ModelAttribute("page") Page<Torder> page, ModelAndView view) {
		logger.info("webstatistic/list");
		if (StringUtil.isEmpty(starttime) ||  StringUtil.isEmpty(endtime)) {
			view.addObject("errormsg", "时间不允许为空");
			view.addObject("channels",Channel.findChannelByYwid(19));
			view.setViewName("webstatistic/list");
			return view;
		}
		try {
			if ("-1".equals(channelid)) {
				List<Channel> cs = Channel.findChannelByYwid(19);
				for (int i = 0; i < cs.size(); i++) {
					if (i > 0) {
						channelid += ",'"+cs.get(i).getId()+"'";
					}else{
						channelid = "'"+cs.get(i).getId()+"'";
					}
				}
			}else{
				channelid = "'"+ channelid +"'";
			}
			
			//注册
			view.addObject("CountRegistUser", Tuserinfo.countRegistUser(channelid, starttime, endtime));
			//充值人数
			view.addObject("CountCharge",Ttransaction.countCharge(channelid, starttime, endtime));
			//充值总金额
			view.addObject("SumCharge",Ttransaction.sumCharge(channelid, starttime, endtime));
			Object[] o = Tlot.countBuy(channelid, starttime, endtime).get(0);
			//购彩总金额
			view.addObject("BET_COUNT_MONEY", o[0]);
			//购彩总人数
			view.addObject("BET_COUNT_USER", o[1]);
			
			Object[] o2 = Ttransaction.getSumNewUser(channelid, starttime, endtime).get(0);
			// 新用户充值金额
			view.addObject("SumNewCharge", o2[0]);
			//新用户充值人数
			view.addObject("SumNewChargeUser", o2[1]);
			
			Object[] o3 = Tlot.getSumNewUserLot(channelid, starttime, endtime).get(0);
			//新用户购彩金额
			view.addObject("SumNewLot", o3[0]);
			//新用户购彩人数
			view.addObject("SumNewLotUser", o3[1]);
			
			//中奖总金额
			view.addObject("SumWin", Tlot.getSumWin(channelid, starttime, endtime));
			view.addObject("channels",Channel.findChannelByYwid(19));
		} catch (Exception e) {
			logger.error("webstatistic/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("webstatistic/list");
		return view;
	}
}
