package com.ruyicai.mgr.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.mysql.PrizecrawlerDao;

@RequestMapping("/channelweights")
@Controller
public class ChannelweightsController {
	private Logger logger = Logger.getLogger(ChannelweightsController.class);
	
	@Autowired
	PrizecrawlerDao prizecrawlerDao;
	
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		try {
			view.addObject("lotcheckswitchs", prizecrawlerDao.findAllLotcheckswitchs());
			view.addObject("tagencys", prizecrawlerDao.findAllTagencys());
			view.addObject("tthresholds", prizecrawlerDao.findAllTthresholds());
		} catch (Exception e) {
			logger.error("channelweights/page error", e);
		}
		view.setViewName("channelweights/info");
		return view;
	}
	
	
	@RequestMapping("/updatethreshold")
	public ModelAndView updatethreshold(
			@RequestParam(value="id") int id,
			@RequestParam(value="threshold") double threshold,
			ModelAndView view){
		String errormsg = "修改成功";
		try {
			prizecrawlerDao.updatethreshold(id, threshold);
		} catch (Exception e) {
			logger.error("channelweights/updatethreshold error", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	
	@RequestMapping("/updateLotcheckswitch")
	public ModelAndView updateLotcheckswitch(
			@RequestParam(value="lotno") String lotno,
			@RequestParam(value="state") int state,
			ModelAndView view){
		String errormsg = "修改成功";
		try {
			prizecrawlerDao.updateLotcheckswitch(lotno, state);
		} catch (Exception e) {
			logger.error("channelweights/updateLotcheckswitch error", e);
			errormsg = e.getMessage();
		}
		
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	@RequestMapping("/updatetagency")
	public ModelAndView updatetagency(
			@RequestParam(value="id") int id,
			@RequestParam(value="weight") double weight,
			ModelAndView view){
		String errormsg = "修改成功";
		try {
			prizecrawlerDao.updatetagency(id, weight);
		} catch (Exception e) {
			logger.error("channelweights/updatetagency error", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
}
