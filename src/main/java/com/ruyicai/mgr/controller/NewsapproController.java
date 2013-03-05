package com.ruyicai.mgr.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.mysql.NewsDao;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/newsappro")
@Controller
public class NewsapproController {
	private Logger logger = Logger.getLogger(NewsapproController.class);
	@Autowired
	NewsDao newsDao;
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		return this.list(null, null, new Page<Map<String,Object>>(), view);
	}
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "categoryId", required=false) String categoryId,
			@RequestParam(value = "checked", required=false) String checked,
			@ModelAttribute("page") Page<Map<String, Object>> page, ModelAndView view){
		logger.info("newsappro/list");
		view.addObject("selCategoryID", categoryId);
		StringBuilder builder = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		if(!StringUtil.isEmpty(categoryId)) {
			builder.append(" and categoryId=?");
			params.add(categoryId);
		}
		if (!StringUtil.isEmpty(checked)) {
			builder.append(" and checked=?");
			params.add(checked);
		}
		try{
			view.addObject("categoryList", newsDao.getCategory());
			newsDao.findList(builder.toString(), params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("newsappro/list error", e);
		}
		view.setViewName("newsappro/list");
		return view;
	}
	
	@RequestMapping("/contentUI")
	public ModelAndView contentUI(@RequestParam("id") String id, ModelAndView view){
		logger.info("newsappro/editUI");
		try{
			view.addObject("news",newsDao.findNewsMap(id));
		} catch (Exception e) {
			logger.error("newsappro/contentUI error", e);
		}
		view.setViewName("newsappro/content");
		return view;
	}
	
	@RequestMapping("/appro")
	public ModelAndView appro(@RequestParam("id") String id, 
			@RequestParam("checked") String checked, 
			ModelAndView view){
		logger.info("newsappro/edit");
		String errormsg = "审批成功";
		try{
			int i = newsDao.approNews(id, checked);
		} catch (Exception e) {
			logger.error("newsappro/appro error", e);
		}
		view.addObject("errormsg", errormsg);
		return page(view);
	}
	
	
	@RequestMapping("/appromore")
	public ModelAndView appro(HttpServletRequest request, ModelAndView view){
		logger.info("newsappro/appromore");
		String errormsg = "审批成功";
		try{
			String[] approvalMoreIds = request.getParameterValues("checkboxname");
			for(int i=0;i<approvalMoreIds.length;i++){
				newsDao.approNews(approvalMoreIds[i], "0");
			}
		} catch (Exception e) {
			logger.error("newsappro/appromore error", e);
		}
		view.addObject("errormsg", errormsg);
		return page(view);
	}
}
