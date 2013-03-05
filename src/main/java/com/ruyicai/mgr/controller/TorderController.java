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

import com.ruyicai.mgr.domain.Torder;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.StringUtil;

@Controller
@RequestMapping("/torders")
public class TorderController {
	private Logger logger = Logger.getLogger(TorderController.class);
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		view.setViewName("torders/list");
		return view;
	}
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "lotno", required = false) String lotno,
			@RequestParam(value = "startbatchcode", required = false) String startbatchcode,
			@RequestParam(value = "endbatchcode", required = false) String endbatchcode,
			@RequestParam(value = "state", required = false) BigDecimal state,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@ModelAttribute("page") Page<Torder> page, ModelAndView view) {
		logger.info("Torder/list");
		try {
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			if(!StringUtil.isEmpty(id)) {
				builder.append(" o.id=? and");
				params.add(id);
			}else{
				if (StringUtil.isEmpty(lotno) || StringUtil.isEmpty(startbatchcode) || StringUtil.isEmpty(endbatchcode)) {
					view.addObject("errormsg", "采种或期号不允许为空");
					return this.page(view);
				}else{
					builder.append(" o.lotno=? and o.batchcode>=? and o.batchcode<=? and");
					params.add(lotno);
					params.add(startbatchcode);
					params.add(endbatchcode);
				}
				
				if (!StringUtil.isEmpty(starttime)) {
					builder.append(" o.createtime is not null and to_char(o.createtime, 'yyyy-mm-dd') >= ? and");
					params.add(starttime);
					view.addObject("starttime", starttime);
				}
				if (!StringUtil.isEmpty(endtime)) {
					builder.append(" o.createtime is not null and to_char(o.createtime, 'yyyy-mm-dd') <= ? and");
					params.add(endtime);
					view.addObject("endtime", endtime);
				}
				
				
				if (null != state) {
					if (state.intValue() == 0) {
						builder.append(" o.orderstate = 0 and");
					}
					if (state.intValue() == 1) {
						builder.append(" o.orderstate = 1 and");
					}
					if (state.intValue() == 2) {
						builder.append(" o.orderstate = 2 and");
					}
					if (state.intValue() == 3) {
						builder.append(" o.orderstate = 3 and");
					}
				}
			}
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			
			if (!StringUtil.isEmpty(builder.toString())) {
				Torder.findList(builder.toString(), " order by o.createtime desc",
						params, page);
			}
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("torders/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("torders/list");
		return view;
	}
	
	@RequestMapping("/updateAgencyno")
	public ModelAndView updateAgencyno(@RequestParam(value = "id") String id,
			@RequestParam(value = "agencyno") String agencyno,ModelAndView view){
		String errormsg = "修改成功";
		try {
			Torder torder = Torder.findTorder(id);
			torder.setAgencyno(agencyno);
			torder.merge();
		} catch (Exception e) {
			logger.error("torders/updateAgencyno error", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("torders/list");
		return view;
	}
	
}
