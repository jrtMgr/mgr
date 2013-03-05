package com.ruyicai.mgr.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.CompositePK;
import com.ruyicai.mgr.domain.Tlotctrl;
import com.ruyicai.mgr.util.DateUtil;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/preissue")
@Controller
public class PreIssueController {
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	private Logger logger = Logger.getLogger(PreIssueController.class);

	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view) {
		logger.info("preissue/page");
		view.setViewName("preissue/list");
		return view;
	}
	
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "lotno", required=false) String lotno,
			ModelAndView view) {
		logger.info("preissue/list");
		StringBuilder builder = new StringBuilder("where o.state = -1");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtil.isEmpty(lotno)) {
			builder.append(" and o.id.lotno=? ");
			params.add(lotno);
		}
		try {
			
			view.addObject("tlotctrls", Tlotctrl.findpreIssueTlotctrls(builder.toString()," order by o.id.batchcode", params));
		} catch (Exception e) {
			view.addObject("errormsg", "查询出错");
			logger.error("preissue/list error", e);
		}
		view.setViewName("preissue/list");
		return view;
	}
	@RequestMapping("/addUI")
	public ModelAndView addTIUI(ModelAndView view) {
		view.setViewName("preissue/addUI");
		return view;
	}
	
	@RequestMapping("/add")
	public ModelAndView add(
			@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode,
			@RequestParam("starttime") String starttime,
			@RequestParam("endtime") String endtime,
			@RequestParam("endbettime") String endbettime,
			@RequestParam("hemaiendtime") String hemaiendtime,
			ModelAndView view) {
		if (StringUtil.isEmpty(lotno) || StringUtil.isEmpty(batchcode) || StringUtil.isEmpty(starttime) || 
				StringUtil.isEmpty(endtime) || StringUtil.isEmpty(endbettime) || StringUtil.isEmpty(hemaiendtime)) {
			view.addObject("errormsg", "不允许为空");
			view.setViewName("preissue/addUI");
			return view;
		}
		
		Date start = DateUtil.parse(starttime);
		Date end = DateUtil.parse(endtime);
		if (start.after(end)) {
			view.addObject("errormsg", "开始时间必须小于结束时间");
			view.setViewName("preissue/addADUI");
			return view;
		}
			
		String errormsg = "添加成功";
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("lotno=").append(lotno).append("&batchcode=").append(batchcode).append("&starttime=").append(starttime)
			.append("&endtime=").append(endtime).append("&state=-1").append("&endbettime=").append(endbettime).append("&hemaiendtime=").append(hemaiendtime);
			String result = HttpUtil.post(propertiesUtil.getLotteryurl() + "/system/modifytlotctrl", sb.toString());
			JSONObject json = new JSONObject(result);
			if (!"0".equals(json.getString("errorCode"))) {
				errormsg = "添加失败，返回码："+json.getString("errorCode");
			}
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("preissue/add error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.list(lotno, view);
	}
	
	@RequestMapping("/addauto")
	public ModelAndView addauto(
			@RequestParam("lotno") String lotno,
			@RequestParam("num") int num,
			ModelAndView view) {
		if (StringUtil.isEmpty(lotno) || StringUtil.isEmpty(num+"")) {
			view.addObject("errormsg", "不允许为空");
			view.setViewName("preissue/addUI");
			return view;
		}
		
		String errormsg = "自动添加成功";
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("lotno=").append(lotno).append("&num=").append(num);
			String result = HttpUtil.post(propertiesUtil.getLotteryurl() + "/system/createissue", sb.toString());
			JSONObject json = new JSONObject(result);
			if (!"0".equals(json.getString("errorCode"))) {
				errormsg = "添加失败，返回码："+json.getString("errorCode");
			}
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("preissue/addauto error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.list(lotno, view);
	}
	
	
	@RequestMapping("/editUI")
	public ModelAndView editUI(@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode,
			@RequestParam("agencyno") String agencyno,
			ModelAndView view) {
		CompositePK c = new CompositePK(lotno, batchcode, agencyno);
		Tlotctrl tlotctrl = Tlotctrl.findTlotctrl(c);
		view.addObject("tlotctrl", tlotctrl);
		view.setViewName("preissue/editUI");
		return view;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(
			@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode,
			@RequestParam("starttime") String starttime,
			@RequestParam("endtime") String endtime,
			@RequestParam("endbettime") String endbettime,
			@RequestParam("hemaiendtime") String hemaiendtime,
			@RequestParam("state") String state,
			ModelAndView view) {
		if (StringUtil.isEmpty(starttime) || StringUtil.isEmpty(endtime) || StringUtil.isEmpty(endbettime) || StringUtil.isEmpty(hemaiendtime)) {
			view.addObject("errormsg", "不允许为空");
			view.setViewName("preissue/addUI");
			return view;
		}
		
		Date start = DateUtil.parse(starttime);
		Date end = DateUtil.parse(endtime);
		if (start.after(end)) {
			view.addObject("errormsg", "开始时间必须小于结束时间");
			view.setViewName("preissue/addADUI");
			return view;
		}
			
		String errormsg = "修改成功";
		try {
			if (Tlotctrl.findTlotctrl(new CompositePK(lotno, batchcode, "R00001")).getState().intValue() != -1) {
				view.addObject("errormsg", "不是未开期 不可以修改");
				view.setViewName("preissue/addUI");
				return view;
			}
			StringBuffer sb = new StringBuffer();
			sb.append("lotno=").append(lotno).append("&batchcode=").append(batchcode).append("&starttime=").append(starttime)
			.append("&endtime=").append(endtime).append("&state=").append(state).append("&endbettime=").append(endbettime).append("&hemaiendtime=").append(hemaiendtime);
			String result = HttpUtil.post(propertiesUtil.getLotteryurl() + "/system/modifytlotctrl", sb.toString());
			JSONObject json = new JSONObject(result);
			if (!"0".equals(json.getString("errorCode"))) {
				errormsg = "修改失败，返回码："+json.getString("errorCode");
			}
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("preissue/edit error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.list(lotno, view);
	}
}
