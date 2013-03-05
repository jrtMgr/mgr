package com.ruyicai.mgr.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.statis.Yw;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/yw")
@Controller
public class YwController {
	private Logger logger = Logger.getLogger(YwController.class);
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		logger.info("yw/page");
		try {
			view.addObject("yws", Yw.findAllYwsBystatus());
		} catch (Exception e) {
			logger.error("yw/page", e);
		}
		view.setViewName("yw/list");
		return view;
	}
	
	
	@RequestMapping("/editUI")
	public ModelAndView editUI(@RequestParam("id") Integer id,
			ModelAndView view){
		logger.info("suser/editUI");
		try {
			view.addObject("yw", Yw.findYw(id));
		} catch (Exception e) {
			logger.error("yw/editUI", e);
		}
		view.setViewName("yw/editUI");
		return view;
	}
	@RequestMapping("/addUI")
	public ModelAndView addUI(ModelAndView view){
		view.setViewName("yw/addUI");
		return view;
	}
	@RequestMapping("/edit")
	public ModelAndView edit(
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "code", required=false) String code,
			@RequestParam(value = "name", required=false) String name,
			@RequestParam(value = "bz", required=false) String bz,
			ModelAndView view) {
		logger.info("suser/edit");
		String errormsg = "修改成功";
		try {
			Yw yw = Yw.findYw(id);
			yw.setCode(code);
			yw.setName(name);
			yw.setBz(bz);
			yw.merge();
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("yw/edit error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	@RequestMapping("/deleteyw")
	public ModelAndView delete(
			@RequestParam(value = "id") Integer id,
			ModelAndView view) {
		logger.info("yw/edit");
		String errormsg = "删除成功";
		try {
			Yw yw = Yw.findYw(id);
			yw.setStatus(0);
			yw.merge();
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("yw/edit error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	@RequestMapping("/add")
	public ModelAndView add(
			@RequestParam(value = "code", required=false) String code,
			@RequestParam(value = "name", required=false) String name,
			@RequestParam(value = "bz", required=false) String bz,
			ModelAndView view) {
		logger.info("yw/add");
		String errormsg = "新增成功";
		try {
			if (StringUtil.isEmpty(name)) {
				view.addObject("errormsg", "不允许为空");
				return this.addUI(view);
			}
			Yw yw = new Yw();
			yw.setId(Yw.findId()+1);
			yw.setCode(code);
			yw.setName(name);
			yw.setBz(bz);
			yw.setCjdate(new Date());
			yw.setStatus(1);
			yw.persist();
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("yw/add error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
}
