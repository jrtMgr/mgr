package com.ruyicai.mgr.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.camel.CamelContext;
import org.apache.camel.Consume;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.TawardDetail;
import com.ruyicai.mgr.domain.Tawardlevel;
import com.ruyicai.mgr.domain.Tloguser;
import com.ruyicai.mgr.service.LotteryService;
import com.ruyicai.mgr.util.DateUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/tawardlevel")
@Controller
public class TawardLevelController {
	private Logger logger = Logger.getLogger(TawardLevelController.class);
	
	@RequestMapping("/page")
	public String page() {
		return "tawardlevel/list";
	}
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "lotno", required = false) String lotno,
			ModelAndView view) {
		logger.info("/tawardlevel/list");
		List<Tawardlevel> list = new ArrayList<Tawardlevel>();
		List<TawardDetail> awardlist = new ArrayList<TawardDetail>();
		try {
			boolean flag = false;
			StringBuilder builder = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			
			if (!StringUtil.isEmpty(lotno)) {
				builder.append(" where o.lotno = ?");
				params.add(lotno);
				flag = true;
			}
			
			if (flag){
				list = Tawardlevel.findList(builder.toString(), " order by o.level ", params);
				awardlist = TawardDetail.findList(builder.toString(), " order by o.state, o.applytime desc ", params);
			}
			view.addObject("list", list);
			view.addObject("awardlist", awardlist);
		} catch (Exception e) {
			logger.error("tcharges/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("tawardlevel/list");
		return view;
	}
	
	@RequestMapping("/addADUI")
	public ModelAndView addTIUI(@RequestParam("lotno") String lotno,
			ModelAndView view) {
		view.addObject("lotno", lotno);
		view.setViewName("tawardlevel/addADUI");
		return view;
	}
	
	@RequestMapping("/addAD")
	public ModelAndView addTI(
			@RequestParam("lotno") String lotno,
			@RequestParam("level") String level,
			@RequestParam("starttime") String starttime,
			@RequestParam("endtime") String endtime,
			@RequestParam("addprize") long addprize,
			HttpServletRequest request,
			ModelAndView view) {
		String errormsg = null;
		try {
			if (StringUtil.isEmpty(lotno) || StringUtil.isEmpty(level) || StringUtil.isEmpty(starttime) || StringUtil.isEmpty(endtime)||StringUtil.isEmpty(addprize+"")) {
				view.addObject("errormsg", "不允许为空");
				view.setViewName("tawardlevel/addADUI");
				return view;
			}
			
			TawardDetail tawardDetail = new TawardDetail();
			tawardDetail.setLotno(lotno);
			tawardDetail.setLevel(level);
			
			Date start = DateUtil.parse(starttime);
			Date end = DateUtil.parse(endtime);
			if (start.after(end)) {
				view.addObject("errormsg", "开始时间必须小于结束时间");
				view.setViewName("tawardlevel/addADUI");
				return view;
			}
			
			tawardDetail.setStarttime(start);
			tawardDetail.setEndtime(end);
			tawardDetail.setAddprize(addprize);
			tawardDetail.setState(TawardDetail.STATE_AUDITING);
			
			Tloguser tloguser = (Tloguser)request.getSession().getAttribute("user");
			tawardDetail.setUserno(tloguser.getId());
			tawardDetail.setApplytime(new Date());
			tawardDetail.persist();
			errormsg="添加成功";
			
		} catch (Exception e) {
			errormsg = "添加失败";
			logger.error("tawardlevel/addADUI error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.list(lotno, view);
	}
}
