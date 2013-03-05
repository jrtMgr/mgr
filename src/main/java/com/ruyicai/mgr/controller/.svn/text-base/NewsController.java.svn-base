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

import com.ruyicai.mgr.mysql.NewsDao;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/news")
@Controller
public class NewsController {
	private Logger logger = Logger.getLogger(NewsController.class);
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
		logger.info("news/list");
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
			logger.error("news/list error", e);
		}
		view.setViewName("news/list");
		return view;
	}
	
	@RequestMapping("/editUI")
	public ModelAndView editUI(@RequestParam("id") String id, ModelAndView view){
		logger.info("news/editUI");
		try{
			view.addObject("categoryList", newsDao.getCategory());
			view.addObject("news", newsDao.findNewsMap(id));
		} catch (Exception e) {
			logger.error("news/editUI error", e);
		}
		view.setViewName("news/editUI");
		return view;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam("id") String id, 
			@RequestParam("title") String title, 
			@RequestParam("categoryId") String categoryId, 
			@RequestParam("orderId") String orderId, 
			@RequestParam("content") String content, 
			ModelAndView view){
		logger.info("news/edit");
		try{
			int i = newsDao.updateNews(id, title, categoryId, orderId, content);
		} catch (Exception e) {
			logger.error("news/edit error", e);
		}
		return page(view);
	}
	
	@RequestMapping("/deleteNews")
	public ModelAndView deleteNews(@RequestParam("id") String id, 
			ModelAndView view){
		logger.info("news/deleteNews");
		String errormsg = "删除成功";
		try{
			int i = newsDao.deleteNews(id);
		} catch (Exception e) {
			logger.error("news/deleteNews error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	@RequestMapping("/addUI")
	public ModelAndView addUI(ModelAndView view){
		logger.info("news/addUI");
		try{
			view.addObject("categoryList", newsDao.getCategory());
		} catch (Exception e) {
			logger.error("news/addUI error", e);
		}
		view.setViewName("news/addUI");
		return view;
	}
	@RequestMapping("/add")
	public ModelAndView add(@RequestParam("title") String title, 
			@RequestParam("categoryId") String categoryId, 
			@RequestParam("orderId") String orderId, 
			@RequestParam("content") String content, 
			ModelAndView view){
		logger.info("news/add");
		String errormsg = "添加成功";
		try{
			int i = newsDao.addNews(title, categoryId, orderId, content);
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("news/add error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.addUI(view);
	}
}
