package com.ruyicai.mgr.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.ServerDetail;
import com.ruyicai.mgr.domain.statis.Yw;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/servermanager")
@Controller
public class ServerManagerController {
	private Logger logger = Logger.getLogger(ServerManagerController.class);
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		logger.info("servermanager/page");
		try {
			view.addObject("sds", ServerDetail.findAllServerDetailsBystatus());
		} catch (Exception e) {
			logger.error("servermanager/page error", e);
		}
		view.setViewName("servermanager/list");
		return view;
	}
	
	
	@RequestMapping("/editUI")
	public ModelAndView editUI(@RequestParam("id") Integer id,
			ModelAndView view){
		logger.info("servermanager/editUI");
		try {
			view.addObject("sd", ServerDetail.findServerDetail(id));
		} catch (Exception e) {
			logger.error("servermanager/editUI error", e);
		}
		view.setViewName("servermanager/editUI");
		return view;
	}
	
	@RequestMapping("/addUI")
	public ModelAndView addUI(ModelAndView view){
		view.setViewName("servermanager/addUI");
		return view;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "url") String url,
			@RequestParam(value = "jmx") String jmx,
			@RequestParam(value = "status") Integer status,
			@RequestParam(value = "bz") String bz,
			ModelAndView view) {
		logger.info("servermanager/edit");
		String errormsg = "修改成功";
		try {
			ServerDetail sd = ServerDetail.findServerDetail(id);
			sd.setUrl(url);
			sd.setStatus(status);
			sd.setBz(bz);
			sd.setJmx(jmx);
			sd.merge();
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("servermanager/edit error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	@RequestMapping("/add")
	public ModelAndView add(
			@RequestParam(value = "url", required=false) String url,
			@RequestParam(value = "jmx", required=false) String jmx,
			@RequestParam(value = "bz", required=false) String bz,
			ModelAndView view) {
		logger.info("servermanager/add");
		String errormsg = "新增成功";
		try {
			if (StringUtil.isEmpty(url)) {
				view.addObject("errormsg", "不允许为空");
				return this.addUI(view);
			}
			ServerDetail sd = new ServerDetail();
			sd.setUrl(url);
			sd.setBz(bz);
			sd.setJmx(jmx);
			sd.setStatus(1);
			
			sd.persist();
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("servermanager/add error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
}
