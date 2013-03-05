package com.ruyicai.mgr.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.TimeInterval;
import com.ruyicai.mgr.listener.LotSucSendMsgListener;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/lssm")
@Controller
public class LotSucSendMsgController {

	private Logger logger = Logger.getLogger(LotSucSendMsgController.class);
	
	
	@RequestMapping("/timeList")
	public ModelAndView timeList(
			ModelAndView view) {
		logger.info("/lssm/timeList");
		try {
			view.addObject("list", TimeInterval.findAllTimeIntervals());
		} catch (Exception e) {
			logger.error("lssm/timeList error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("lssm/timeList");
		return view;
	}
	
	@RequestMapping("/addTIUI")
	public ModelAndView addTIUI(ModelAndView view) {
		view.setViewName("lssm/addTIUI");
		return view;
	}
	
	@RequestMapping("/editTIUI")
	public ModelAndView editTIUI(@RequestParam("id") long id, ModelAndView view) {
		view.addObject("ti", TimeInterval.findTimeInterval(id));
		view.setViewName("lssm/editTIUI");
		return view;
	}   
	@RequestMapping("/deleteTI")
	public ModelAndView deleteTI(@RequestParam("id") long id, ModelAndView view) {
		TimeInterval t = TimeInterval.findTimeInterval(id);
		t.setDelete(1);
		t.merge();
		view.addObject("errormsg", "删除成功");
		LotSucSendMsgListener.list = null;
		return this.timeList(view);
	}   
	@RequestMapping("/editTI")
	public ModelAndView editTI(@RequestParam("id") long id,
			@RequestParam("startHour") int startHour,
			@RequestParam("endHour") int endHour,
			@RequestParam("frequency") int frequency,
			@RequestParam("mobileNo") String mobileNo,ModelAndView view) {
		String errormsg = null;
		try {
			if (StringUtil.isEmpty(startHour+"") || StringUtil.isEmpty(endHour+"")||
					StringUtil.isEmpty(frequency+"")||StringUtil.isEmpty(mobileNo)) {
				view.addObject("errormsg", "不允许为空");
				view.setViewName("lssm/addTIUI");
				return view;
			}
			TimeInterval t = TimeInterval.findTimeInterval(id);
			t.setStartHour(startHour);
			t.setEndHour(endHour);
			t.setFrequency(frequency*60000L);
			t.setMobileNo(mobileNo);
			t.persist();
			errormsg="修改成功";
			LotSucSendMsgListener.list = null;
		} catch (Exception e) {
			errormsg = "修改失败";
			logger.error("lssm/addTIUI error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.timeList(view);
	}   
	
	@RequestMapping("/addTI")
	public ModelAndView addTI(
			@RequestParam("startHour") int startHour,
			@RequestParam("endHour") int endHour,
			@RequestParam("frequency") int frequency,
			@RequestParam("mobileNo") String mobileNo,
			ModelAndView view) {
		String errormsg = null;
		try {
			if (StringUtil.isEmpty(startHour+"") || StringUtil.isEmpty(endHour+"")||StringUtil.isEmpty(frequency+"")) {
				view.addObject("errormsg", "不允许为空");
				view.setViewName("lssm/addTIUI");
				return view;
			}
			TimeInterval t = new TimeInterval();
			t.setStartHour(startHour);
			t.setEndHour(endHour);
			t.setFrequency(frequency*60000L);
			t.setMobileNo(mobileNo);
			t.persist();
			errormsg="添加成功";
			LotSucSendMsgListener.list = null;
		} catch (Exception e) {
			errormsg = "添加失败";
			logger.error("lssm/addTIUI error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.timeList(view);
	}
}
