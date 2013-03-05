package com.ruyicai.mgr.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.MsgMonitorCompositePK;
import com.ruyicai.mgr.domain.TmsgMonitor;
import com.ruyicai.mgr.listener.msgmonitor.DrawLottery;
import com.ruyicai.mgr.listener.msgmonitor.EndIssue;
import com.ruyicai.mgr.listener.msgmonitor.IssueEnd;
import com.ruyicai.mgr.listener.msgmonitor.SaveOrder;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/msgmonitor")
@Controller
public class MsgMonitorController {
	private Logger logger = Logger.getLogger(MsgMonitorController.class);

	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view) {
		logger.info("msgmonitor/page");
		view.setViewName("msgmonitor/list");
		return view;
	}
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "lotno", required=false) String lotno,
			@RequestParam(value = "type", required=false) BigDecimal type,
			@RequestParam(value = "state", required=false) BigDecimal state,
			ModelAndView view) {
		logger.info("msgmonitor/list");
		StringBuilder builder = new StringBuilder(" where");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtil.isEmpty(lotno)) {
			builder.append(" o.id.lotno=? and");
			params.add(lotno);
		}
		if(type != null) {
			builder.append(" o.id.type=? and");
			params.add(type);
		}
		if(state != null) {
			builder.append(" o.state=? and");
			params.add(state);
		}
		if (builder.toString().endsWith("where")) {
			builder.delete(builder.length() - 5, builder.length());
		}
		if (builder.toString().endsWith("and")) {
			builder.delete(builder.length() - 3, builder.length());
		}
		try {
			
			view.addObject("msgMonitors", TmsgMonitor.findTmsgMonitors(builder.toString()," order by o.id.lotno", params));
		} catch (Exception e) {
			view.addObject("errormsg", "查询出错");
			logger.error("msgmonitor/list error", e);
		}
		view.setViewName("msgmonitor/list");
		return view;
	}
	@RequestMapping("/addUI")
	public ModelAndView addTIUI(ModelAndView view) {
		view.setViewName("msgmonitor/addUI");
		return view;
	}
	
	@RequestMapping("/add")
	public ModelAndView add(
			@RequestParam("lotno") String lotno,
			@RequestParam("type") BigDecimal type,
			@RequestParam("time") BigDecimal time,
			ModelAndView view) {
		if (StringUtil.isEmpty(lotno) || StringUtil.isEmpty(type+"") || StringUtil.isEmpty(time+"")) {
			view.addObject("errormsg", "不允许为空");
			view.setViewName("msgmonitor/addUI");
			return view;
		}
			
		String errormsg = "添加成功";
		try {
			TmsgMonitor t = new TmsgMonitor();
			t.setId(new MsgMonitorCompositePK(lotno, type));
			t.setTime(time);
			t.setState(BigDecimal.ZERO);
			t.persist();
			
			if (type.intValue() == 0) {
				DrawLottery.map = null;
			}else if (type.intValue() == 1) {
				IssueEnd.map = null;
			}else if (type.intValue() == 2) {
				EndIssue.map = null;
			}else if (type.intValue() == 3) {
				SaveOrder.map = null;
			}
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("msgmonitor/add error", e);
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("msgmonitor/addUI");
		return view;
	}
	
	
	@RequestMapping("/editUI")
	public ModelAndView editUI(@RequestParam("lotno") String lotno,
			@RequestParam("type") BigDecimal type,
			ModelAndView view) {
		view.addObject("msgmonitor", TmsgMonitor.findTmsgMonitor(new MsgMonitorCompositePK(lotno, type)));
		view.setViewName("msgmonitor/editUI");
		return view;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(
			@RequestParam("lotno") String lotno,
			@RequestParam("type") BigDecimal type,
			@RequestParam("state") BigDecimal state,
			@RequestParam("time") BigDecimal time,
			ModelAndView view) {
		if (StringUtil.isEmpty(lotno) || StringUtil.isEmpty(type+"") || StringUtil.isEmpty(state+"") || StringUtil.isEmpty(time+"")) {
			view.addObject("errormsg", "不允许为空");
			view.setViewName("msgmonitor/editUI");
			return view;
		}
		String errormsg = "修改成功";
		try {
			TmsgMonitor msgMonitor = TmsgMonitor.findTmsgMonitor(new MsgMonitorCompositePK(lotno, type));
			msgMonitor.setState(state);
			msgMonitor.setTime(time);
			msgMonitor.merge();
			
			if (type.intValue() == 0) {
				DrawLottery.map = null;
			}else if (type.intValue() == 1) {
				IssueEnd.map = null;
			}else if (type.intValue() == 2) {
				EndIssue.map = null;
			}else if (type.intValue() == 3) {
				SaveOrder.map = null;
			}
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("msgmonitor/edit error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
}
