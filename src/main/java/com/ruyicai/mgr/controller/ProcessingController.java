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

import com.ruyicai.mgr.consts.BetType;
import com.ruyicai.mgr.consts.Const;
import com.ruyicai.mgr.domain.Tlot;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/processing")
@Controller
public class ProcessingController {
	private Logger logger = Logger.getLogger(ProcessingController.class);

	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view) {
		view.setViewName("processing/list");
		return view;
	}
	
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "lotno", required = false) String lotno,
			@RequestParam(value = "agencyno", required = false) String agencyno,
			@RequestParam(value = "state", required = false) BigDecimal instate,
			@ModelAttribute("page") Page<Tlot> page, ModelAndView view) {
		logger.info("tlots/list");
		try {
			StringBuilder builder = new StringBuilder(" where o.state=1 and");
			List<Object> params = new ArrayList<Object>();
			if (instate != null) {
				builder.append(" o.instate=? and");
				params.add(instate);
			}
			if (!StringUtil.isEmpty(lotno)) {
				builder.append(" o.lotno=? and");
				params.add(lotno);
			}
			if (!StringUtil.isEmpty(agencyno)) {
				builder.append(" o.agencyno=? and");
				params.add(agencyno);
			}
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (!StringUtil.isEmpty(builder.toString()))
				Tlot.findList(builder.toString(), " order by o.ordertime desc",
						params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("processing/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		return this.page(view);
	}
}
