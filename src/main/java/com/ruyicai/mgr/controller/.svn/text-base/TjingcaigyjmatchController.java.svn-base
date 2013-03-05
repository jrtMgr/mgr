package com.ruyicai.mgr.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.mysql.domain.TjingcaigyjMatch;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/tjingcaigyjmatch")
@Controller
public class TjingcaigyjmatchController {
	
	private Logger logger = Logger.getLogger(TjingcaigyjmatchController.class);

	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view) {
		logger.info("tjingcaigyjmatch/page");
		view.setViewName("tjingcaigyjmatch/list");
		return view;
	}
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "saishi") String saishi,
			@RequestParam(value = "type") BigDecimal type,
			@RequestParam(value = "number") String number,
			@ModelAttribute("page") Page<TjingcaigyjMatch> page, 
			ModelAndView view) {
		logger.info("tjingcaigyjmatch/list");
		StringBuilder builder = new StringBuilder(" where");
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isEmpty(saishi)) {
			builder.append(" o.saishi =? and");
			params.add(saishi);
		}
		if (type != null) {
			builder.append(" o.type =? and");
			params.add(type);
		}
		if (!StringUtil.isEmpty(number)) {
			builder.append(" o.number =? and");
			params.add(number);
		}
		if (builder.toString().endsWith("and")) {
			builder.delete(builder.length() - 3, builder.length());
		}
		if (builder.toString().endsWith("where")) {
			builder.delete(builder.length() - 5, builder.length());
		}
		
		try {
			TjingcaigyjMatch.findList(builder.toString(), " order by o.id desc ",
					params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			view.addObject("errormsg", "查询出错");
			logger.error("tjingcaigyjmatch/list error", e);
		}
		view.setViewName("tjingcaigyjmatch/list");
		return view;
	}
	
	
	@RequestMapping("/updateState")
	public ModelAndView updateState(@RequestParam("id") Long id,
			@RequestParam("state") BigDecimal state,
			ModelAndView view) {
		logger.info("tlottypes/update");
		String errormsg = "修改成功";
		try {
			TjingcaigyjMatch tjingcaigyjMatch = TjingcaigyjMatch.findTjingcaigyjMatch(id);
			tjingcaigyjMatch.setState(state);
			tjingcaigyjMatch.merge();
		} catch (Exception e) {
			errormsg = "出现异常";
			logger.error("tlottypes/list error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
}
