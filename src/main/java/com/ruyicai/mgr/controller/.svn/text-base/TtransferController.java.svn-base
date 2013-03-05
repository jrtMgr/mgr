package com.ruyicai.mgr.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.consts.Const;
import com.ruyicai.mgr.domain.Tactivities;
import com.ruyicai.mgr.domain.Tsubchannel;
import com.ruyicai.mgr.domain.Ttransaction;
import com.ruyicai.mgr.domain.Ttransfer;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.exception.RuyicaiException;
import com.ruyicai.mgr.lot.Lottype;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/ttransfer")
@Controller
public class TtransferController {
	private Logger logger = Logger.getLogger(TtransferController.class);
	
	
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "userno", required = false) String userno,			
			@RequestParam(value = "state", required = false) BigDecimal state,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@ModelAttribute("page") Page<Ttransfer> page, ModelAndView view) {
		logger.info("/ttransfer/list start");
		try {
			boolean flag = false;
			StringBuilder builder = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			
			builder.append(" where ");
			
			if(!StringUtil.isEmpty(userno)) {
				builder.append(" o.userno=? and");
				params.add(userno);
				flag = true;
			}
			if (!StringUtil.isEmpty(starttime)) {
				builder.append(" to_char(o.modifytime, 'yyyy-mm-dd') >= ? and");
				params.add(starttime);
				view.addObject("starttime", starttime);
				flag = true;
			}
			if (!StringUtil.isEmpty(endtime)) {
				builder.append(" to_char(o.modifytime, 'yyyy-mm-dd') <= ? and");
				params.add(endtime);
				view.addObject("endtime", endtime);
				flag = true;
			}
			
			if (null != state ) {
				if (state.equals(BigDecimal.ZERO)) {
					builder.append(" o.state = 0 and");
					flag = true;
				} else if (state.equals(BigDecimal.ONE)) {
					builder.append(" o.state = 1 and");
					flag = true;
				} else {
					builder.append(" 1 = 1 and");
					flag = true;//
				}
				
				
				view.addObject("state", state);
			}
			
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			if (flag)
				Ttransfer.findList(builder.toString(), " order by o.modifytime desc", params, page);		
			view.addObject("page", page);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ttransfer/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("ttransfer/list");
		logger.info("/ttransfer/list end");
		return view;
	}
	
	
	@RequestMapping("/toadd")
	public ModelAndView add(		
			ModelAndView view) {		
		logger.info("/ttransfer/toadd");		
		view.setViewName("ttransfer/add");
		return view;
	}
	
	
	@RequestMapping("/add")
	public ModelAndView add(			
			@RequestParam(value = "userno", required = false) String userno,		
			@RequestParam(value = "state", required = false) BigDecimal state,
			ModelAndView view) {		
		logger.info("/ttransfer/add");
		String errormsg = null;
		userno = userno == null ? null : userno.trim();	    
		
		try {
			if (StringUtils.isEmpty(userno)) {
				errormsg = "用户编号不能为空";	
				view.addObject("errormsg", errormsg);	
				view.addObject("userno", userno);			
				view.addObject("state", state);
				view.setViewName("ttransfer/add");
				return view;
			}
			
			Tuserinfo tuserinfo = Tuserinfo.findTuserinfoByUsernoOnly(userno);
			if (null== tuserinfo) {
				errormsg = "用户信息表中，用户编号" + userno + "不存在";
				view.addObject("errormsg", errormsg);	
				view.addObject("userno", userno);			
				view.addObject("state", state);
				view.setViewName("ttransfer/add");
				return view;
			}
			
			Ttransfer ttransfer = Ttransfer.findTtransfer(userno);
			if (null != ttransfer) {
				errormsg = "代充值权限表中，用户编号" + userno + "已存在";
				view.addObject("errormsg", errormsg);	
				view.addObject("userno", userno);			
				view.addObject("state", state);
				view.setViewName("ttransfer/add");
				return view;
			}			
		
			ttransfer = new Ttransfer();
			ttransfer.setUserno(userno);
			ttransfer.setState(state);
			Date date = new Date();
			ttransfer.setCreatetime(date);
			ttransfer.setModifytime(date);
			ttransfer.persist();
			errormsg = "添加成功";			
		} catch (RuyicaiException e) {
			logger.error("添加失败：", e);
			errormsg = e.getMessage();			
		} catch (Exception e) {
			logger.error("ttransfer/add error", e);
			errormsg = e.getMessage();			
		}
		
		view.addObject("errormsg", errormsg);	
		view.addObject("userno", userno);			
		view.addObject("state", state);
		view.setViewName("ttransfer/add");
		return view;
	}
	
	@RequestMapping("/toset")
	public ModelAndView toset(
			@RequestParam(value = "userno") String userno,
			ModelAndView view) {
		logger.info("/ttransfer/toset");
		String errormsg = null;
		userno = userno == null ? null : userno.trim();	  
		
		try {
			if (StringUtils.isEmpty(userno)) {
				errormsg = "用户编号不能为空";	
				view.addObject("errormsg", errormsg);	
				view.addObject("userno", userno);			
				view.setViewName("ttransfer/list");
				return view;
			}		
			
			Ttransfer ttransfer = Ttransfer.findTtransfer(userno);
			if (null == ttransfer) {
				errormsg = "代充值权限表中，用户编号" + userno + "不存在";
				view.addObject("errormsg", errormsg);					
				view.setViewName("ttransfer/list");
				return view;
			}			
			view.addObject("userno", ttransfer.getUserno());		
			view.addObject("state", ttransfer.getState());			
		} catch (Exception e) {
			logger.error("ttransfer/toset error", e);
			view.addObject("errormsg", e.getMessage());
		}
		
		view.setViewName("ttransfer/set");
		return view;
	}
	
	@RequestMapping("/set")
	public ModelAndView set(			
			@RequestParam(value = "userno", required = false) String userno,		
			@RequestParam(value = "state", required = false) BigDecimal state,
			ModelAndView view) {		
		logger.info("/ttransfer/set");
		String errormsg = null;
		userno = userno == null ? null : userno.trim();	    
		try {
			if (StringUtils.isEmpty(userno)) {
				errormsg = "用户编号不能为空";	
				view.addObject("errormsg", errormsg);	
				view.addObject("userno", userno);			
				view.addObject("state", state);
				view.setViewName("ttransfer/set");
				return view;
			}		
			Ttransfer ttransfer = Ttransfer.findTtransfer(userno);
			if (null == ttransfer) {
				errormsg = "代充值权限表中，用户编号" + userno + "不存在";
				view.addObject("errormsg", errormsg);	
				view.addObject("userno", userno);			
				view.addObject("state", state);
				view.setViewName("ttransfer/set");
				return view;
			}			
		
			ttransfer.setState(state);
			Date date = new Date();
			ttransfer.setModifytime(date);
			ttransfer.merge();
			errormsg = "维护成功";			
		} catch (RuyicaiException e) {
			logger.error("tpresents/set error", e);
			errormsg = e.getMessage();			
		} catch (Exception e) {
			logger.error("ttransfer/set error", e);
			errormsg = e.getMessage();			
		}
		
		view.addObject("errormsg", errormsg);	
		view.addObject("userno", userno);			
		view.addObject("state", state);
		view.setViewName("ttransfer/set");
		return view;
	}
}
