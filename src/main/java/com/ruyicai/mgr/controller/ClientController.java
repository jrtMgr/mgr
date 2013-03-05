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
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/client")
@Controller
public class ClientController {
	private Logger logger = Logger.getLogger(ClientController.class);
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
		logger.info("client/list");
		StringBuilder builder = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		if(channelid != null) {
			builder.append(" where coopid=?");
			params.add(channelid);
		}
		try{
			clientDao.findList(builder.toString(), params, page);
			view.addObject("page", page);
			view.addObject("tproducttypes", clientDao.findTproducttypeList());
		} catch (Exception e) {
			logger.error("client/list error", e);
		}
		view.setViewName("client/list");
		return view;
	}
	
	@RequestMapping("/editUI")
	public ModelAndView editUI(@RequestParam("id") Integer id, ModelAndView view){
		logger.info("client/editUI");
		try{
			view.addObject("client", clientDao.getCoop(id));
			view.addObject("tproducttypes", clientDao.findTproducttypeList());
		} catch (Exception e) {
			logger.error("client/editUI error", e);
		}
		view.setViewName("client/editUI");
		return view;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam("id") int id, 
			@RequestParam("coopname") String coopname, 
			@RequestParam("coopid") int coopid, 
			@RequestParam("rate") int rate, 
			@RequestParam("productno") String productno, 
			ModelAndView view){
		logger.info("client/edit");
		String errormsg = "修改成功";
		try{
			int i = clientDao.updateCoop(id, coopname, coopid, rate, productno);
		} catch (Exception e) {
			logger.error("client/edit error", e);
			errormsg = e.toString();
		}
		view.addObject("errormsg", errormsg);
		return page(view);
	}
	
	@RequestMapping("/deletecoop")
	public ModelAndView deletecoop(@RequestParam("id") int id, 
			ModelAndView view){
		logger.info("client/deletecoop");
		String errormsg = "删除成功";
		try{
			int i = clientDao.deleteCoop(id);
		} catch (Exception e) {
			e.printStackTrace();
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	@RequestMapping("/addUI")
	public ModelAndView addUI(ModelAndView view){
		logger.info("client/addUI");
		view.addObject("tproducttypes", clientDao.findTproducttypeList());
		view.setViewName("client/addUI");
		return view;
	}
	@RequestMapping("/add")
	public ModelAndView add(@RequestParam("coopname") String coopname, 
			@RequestParam("coopid") int coopid, 
			@RequestParam("rate") int rate, 
			@RequestParam("productno") String productno, 
			ModelAndView view){
		logger.info("news/add");
		String errormsg = "添加成功";
		try{
			int i = clientDao.addCoop(clientDao.getId()+1, coopname, coopid, rate, productno);
			view.addObject("tproducttypes", clientDao.findTproducttypeList());
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("client/add error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.addUI(view);
	}
}
