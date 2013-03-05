package com.ruyicai.mgr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.mysql.ClientDao;
import com.ruyicai.mgr.mysql.NewsDao;
import com.ruyicai.mgr.util.Page;

@RequestMapping("/clientdaily")
@Controller
public class ClientDailyController {
	private Logger logger = Logger.getLogger(ClientDailyController.class);
	@Autowired
	NewsDao newsDao;
	
	@Autowired
	ClientDao clientDao;
	
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		return this.list(null, new Page<Map<String,Object>>(), view);
	}
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "channelid") Integer channelid,
			@ModelAttribute("page") Page<Map<String, Object>> page, ModelAndView view){
		logger.info("clientdaily/list");
		StringBuilder builder = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		if(channelid != null) {
			builder.append(" AND p.coopid=?");
			params.add(channelid);
		}
		try{
			clientDao.findCDList(builder.toString(), params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("clientdaily/list error", e);
		}
		view.setViewName("clientdaily/list");
		return view;
	}
	
}
