package com.ruyicai.mgr.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.TlotcenterDuizhang;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping(value = "/tlotcenterDuizhang")
@Controller
public class TlotcenterDuizhangController {

	private Logger logger = Logger.getLogger(TlotcenterDuizhangController.class);
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view) {
		logger.info("tlotcenterDuizhang/page");
		view.setViewName("tlotcenterDuizhang/page");
		return view;
	}
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "lotno", required = false) String lotno,
			@RequestParam(value = "agencyno", required = false) String agencyno,
			@RequestParam(value = "batchcode1", required = false) String batchcode1,
			@RequestParam(value = "batchcode2", required = false) String batchcode2,
			@ModelAttribute("page") Page<TlotcenterDuizhang> page, ModelAndView view) {
		logger.info("tlotcenterDuizhang/list");
		try {
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			if (!StringUtil.isEmpty(starttime)) {
				builder.append(" to_char(o.createtime, 'yyyy-mm-dd') >= ? and");
				params.add(starttime);
			}
			if (!StringUtil.isEmpty(endtime)) {
				builder.append(" to_char(o.createtime, 'yyyy-mm-dd') <= ? and");
				params.add(endtime);
			}
			if (!StringUtil.isEmpty(agencyno)) {
				builder.append(" o.id.agencyno = ? and");
				params.add(agencyno);
			}
			if (!StringUtil.isEmpty(batchcode1)) {
				builder.append(" o.id.batchcode >= ? and");
				params.add(batchcode1);
			}
			if (!StringUtil.isEmpty(batchcode2)) {
				builder.append(" o.id.batchcode <= ? and");
				params.add(batchcode2);
			}
			String orderby = " order by o.createtime desc";
			if (!StringUtil.isEmpty(lotno)) {
				builder.append(" o.id.lotno=? and");
				params.add(lotno);
				orderby = " order by o.id.batchcode desc";
			}
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			
			TlotcenterDuizhang.findList(builder.toString(), orderby, params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tlotcenterDuizhang/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("tlotcenterDuizhang/page");
		return view;
	}
	
}
