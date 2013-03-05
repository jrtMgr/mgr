package com.ruyicai.mgr.controller;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.mysql.domain.MatchesCompositePK;
import com.ruyicai.mgr.mysql.domain.TjingcaiMatches;
import com.ruyicai.mgr.util.DateUtil;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping(value = "/tjingcaiMatches")
@Controller
public class TjingcaiMatchesController {

	private Logger logger = Logger.getLogger(TjingcaiMatchesController.class);
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view) {
		view.setViewName("tjingcaiMatches/list");
		return view;
	}
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "day") String day,
			@RequestParam(value = "type") BigDecimal type,
			@RequestParam(value = "state") BigDecimal state,
			@RequestParam(value = "audit") BigDecimal audit,
			@ModelAttribute("page") Page<TjingcaiMatches> page, ModelAndView view) {
		logger.info("tjingcaiMatches/list");
		try {
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			if(!StringUtil.isEmpty(day)) {
				builder.append(" o.id.day=? and");
				params.add(day);
			}
			if (type !=null) {
				builder.append(" o.id.type=? and");
				params.add(type);
			}
			if (state != null) {
				builder.append(" o.state=? and");
				params.add(state);
			}
			if (audit != null) {
				builder.append(" o.audit=? and");
				params.add(audit);
			}
			
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			TjingcaiMatches.findList(builder.toString(), " order by o.id.day desc,o.id.teamid desc ",
						params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tjingcaiMatches/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("tjingcaiMatches/list");
		return view;
	}
	
	@RequestMapping("/editUI")
	public ModelAndView editUI(@RequestParam("type") BigDecimal type,
			@RequestParam("day") String day,
			@RequestParam("weekid") BigDecimal weekid,
			@RequestParam("teamid") String teamid,
			ModelAndView view) {
		logger.info("tjingcaiMatches/editUI");
		try {
			view.addObject("tjingcaiMatches", TjingcaiMatches.findTjingcaiMatches(new MatchesCompositePK(type, day, weekid, teamid)));
		} catch (Exception e) {
			logger.error(e);
			view.addObject("errormsg", "出错了看日志");
		}
		view.setViewName("tjingcaiMatches/editUI");
		return view;
	}
	
	@RequestMapping("/jingcaiend")
	public ModelAndView jingcaiend(@RequestParam("type") BigDecimal type,
			@RequestParam("day") String day,
			@RequestParam("weekid") BigDecimal weekid,
			@RequestParam("teamid") String teamid,
			ModelAndView view) {
		logger.info("tjingcaiMatches/jingcaiend");
		String errormsg = "";
		try {
			String result = HttpUtil.post(propertiesUtil.getLotteryurl() + "/system/jingcaiend",
					"event=" + type+"_"+day+"_"+weekid+"_"+teamid);
			logger.info(result);
			JSONObject jo = new JSONObject(result);
			if("0".equals(jo.get("errorCode"))){
				errormsg = "期结成功";
			}else{
				errormsg = "期结失败";
			}
		} catch (Exception e) {
			logger.error(e);
			view.addObject("errormsg", "出错了看日志");
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam("type") BigDecimal type,
			@RequestParam("day") String day,
			@RequestParam("weekid") BigDecimal weekid,
			@RequestParam("teamid") String teamid,
			@RequestParam("endtime") String endtimeStr,
			@RequestParam("saleflag") BigDecimal saleflag,
			@RequestParam("state") BigDecimal state,
			@RequestParam("league") String league,
			@RequestParam("team") String team,
			@RequestParam("time") String timeStr,
			@RequestParam("unsupport") String unsupport,
			@RequestParam("shortname") String shortname,
			ModelAndView view) {
		logger.info("tdnabind/edit");
		String errormsg = "修改成功";
		try {
			Date endtime = DateUtil.parse(endtimeStr);
			Date time = DateUtil.parse(timeStr);
			if (endtime == null || time == null || StringUtil.isEmpty(endtimeStr) || saleflag == null || state == null ||
					StringUtil.isEmpty(league)||StringUtil.isEmpty(team) || StringUtil.isEmpty(timeStr) ) {
				view.addObject("errormsg", "不允许为空");
				return this.editUI(type, day, weekid, teamid, view);
			}
			TjingcaiMatches tjingcaiMatches =  TjingcaiMatches.findTjingcaiMatches(new MatchesCompositePK(type, day, weekid, teamid));
			tjingcaiMatches.setEndtime(endtime);
			tjingcaiMatches.setSaleflag(saleflag);
			tjingcaiMatches.setState(state);
			tjingcaiMatches.setLeague(league);
			tjingcaiMatches.setTeam(team);
			tjingcaiMatches.setTime(time);
			tjingcaiMatches.setUnsupport(unsupport);
			tjingcaiMatches.setShortname(shortname);
			tjingcaiMatches.merge();
		} catch (Exception e) {
			logger.error("tjingcaiMatches/edit error", e);
			view.addObject("errormsg", "修改失败");
			return this.editUI(type, day, weekid, teamid, view);
		}
		
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	
	
	
	@RequestMapping("/addUI")
	public ModelAndView addUI(ModelAndView view) {
		logger.info("tjingcaiMatches/addUI");
		view.setViewName("tjingcaiMatches/addUI");
		return view;
	}
	
	@RequestMapping("/add")
	public ModelAndView add(@RequestParam("type") BigDecimal type,
			@RequestParam("day") String day,
			@RequestParam("weekid") BigDecimal weekid,
			@RequestParam("teamid") String teamid,
			@RequestParam("endtime") String endtimeStr,
			@RequestParam("saleflag") BigDecimal saleflag,
			@RequestParam("state") BigDecimal state,
			@RequestParam("league") String league,
			@RequestParam("team") String team,
			@RequestParam("time") String timeStr,
			@RequestParam("unsupport") String unsupport,
			@RequestParam("shortname") String shortname,
			ModelAndView view) {
		logger.info("tjingcaiMatches/add");
		String errormsg = "新增成功";
		try {
			Date endtime = DateUtil.parse(endtimeStr);
			Date time = DateUtil.parse(timeStr);
			if (type == null ||StringUtil.isEmpty(day) ||weekid == null ||StringUtil.isEmpty(teamid) || endtime == null || time == null || StringUtil.isEmpty(endtimeStr) || saleflag == null || state == null ||
					StringUtil.isEmpty(league)||StringUtil.isEmpty(team) || StringUtil.isEmpty(timeStr) ) {
				view.addObject("errormsg", "不允许为空");
				return this.addUI(view);
			}
			TjingcaiMatches tjingcaiMatches = new TjingcaiMatches();
			tjingcaiMatches.setId(new MatchesCompositePK(type, day, weekid, teamid));
			tjingcaiMatches.setEndtime(endtime);
			tjingcaiMatches.setSaleflag(saleflag);
			tjingcaiMatches.setState(state);
			tjingcaiMatches.setLeague(league);
			tjingcaiMatches.setTeam(team);
			tjingcaiMatches.setTime(time);
			tjingcaiMatches.setUnsupport(unsupport);
			tjingcaiMatches.setAudit(BigDecimal.ONE);
			tjingcaiMatches.setShortname(shortname);
			tjingcaiMatches.persist();
		} catch (Exception e) {
			logger.error("tjingcaiMatches/add error", e);
			view.addObject("errormsg", "新增失败");
			return this.addUI(view);
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	@Autowired
	PropertiesUtil propertiesUtil;
	@RequestMapping("/getjingcairesult")
	public ModelAndView getjingcairesult(@RequestParam("type") BigDecimal type,
			@RequestParam("day") String day,
			@RequestParam("weekid") BigDecimal weekid,
			@RequestParam("teamid") String teamid,
			ModelAndView view) {
		logger.info("tjingcaiMatches/getjingcairesult");
		String errormsg = "";
		try {
			String result = HttpUtil.post(propertiesUtil.getPrizecrawlerurl() + "/jingcai/getjingcairesult",
					"id=" + type+"_"+day+"_"+weekid+"_"+teamid);
			logger.info(result);
			JSONObject jo = new JSONObject(result);
			if("0".equals(jo.get("errorCode"))){
				errormsg = "获取成功";
			}else{
				errormsg = "获取失败";
			}
		} catch (Exception e) {
			errormsg = "获取出错出错";
			logger.error("tjingcaiMatches/getjingcairesult error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	@RequestMapping("/getfootballmatches")
	public ModelAndView getfootballmatches(ModelAndView view) {
		logger.info("tjingcaiMatches/getfootballmatches");
		String errormsg = "";
		try {
			String result = HttpUtil.post(propertiesUtil.getPrizecrawlerurl() + "/jingcai/getfootballmatches", "");
			logger.info(result);
			JSONObject jo = new JSONObject(result);
			if("0".equals(jo.get("errorCode"))){
				errormsg = "获取成功";
			}else{
				errormsg = "获取失败";
			}
		} catch (Exception e) {
			errormsg = "获取出错";
			logger.error("tjingcaiMatches/getjingcairesult error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	@RequestMapping("/getbasketballmatches")
	public ModelAndView getbasketballmatches(ModelAndView view) {
		logger.info("tjingcaiMatches/getbasketballmatches");
		String errormsg = "";
		try {
			String result = HttpUtil.post(propertiesUtil.getPrizecrawlerurl() + "/jingcai/getbasketballmatches", "");
			logger.info(result);
			JSONObject jo = new JSONObject(result);
			if("0".equals(jo.get("errorCode"))){
				errormsg = "获取成功";
			}else{
				errormsg = "获取失败";
			}
		} catch (Exception e) {
			errormsg = "出错";
			logger.error("tjingcaiMatches/getjingcairesult error", e);
		}
		
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	
	@RequestMapping("/appromore")
	public ModelAndView appro(HttpServletRequest request,
			ModelAndView view){
		logger.info("newsappro/appromore");
		String errormsg = "";
		try{
			String[] approvalMoreIds = request.getParameterValues("checkboxname");
			for(int i=0;i<approvalMoreIds.length;i++){
				logger.info("被审核的id为:"+approvalMoreIds[i]);
				TjingcaiMatches t = TjingcaiMatches.findTjingcaiMatches(MatchesCompositePK.fromJsonToMatchesCompositePK(approvalMoreIds[i]));
				if(t != null && !BigDecimal.ZERO.equals(t.getAudit())){
					t.setAudit(BigDecimal.ZERO);
					t.merge();
				}else{
					errormsg += approvalMoreIds[i] + "有误或是已审核。";
				}
			}
		} catch (Exception e) {
			logger.info("出现异常"+e);
			errormsg = "出现异常";
		}
		if(StringUtil.isEmpty(errormsg)){
			errormsg = "审批成功";
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	@RequestMapping("/saveEventsUI")
	public ModelAndView saveEvents(ModelAndView view) {
		logger.info("tjingcaiMatches/saveEvents");
		view.setViewName("tjingcaiMatches/saveEvents");
		return view;
	}
	
	@RequestMapping("/saveEvents")
	public ModelAndView saveEvents(@RequestParam("type") BigDecimal type,
			@RequestParam("name") String name,
			@RequestParam("shortname") String shortname,
			ModelAndView view) {
		logger.info("tjingcaiMatches/saveEvents");
		String errormsg = "";
		try {
			StringBuilder param = new StringBuilder();
			param.append("type=").append(type)
			.append("&name=").append(URLEncoder.encode(name, "UTF-8"))
			.append("&shortname=").append(URLEncoder.encode(shortname, "UTF-8"));
			String url =propertiesUtil.getPrizecrawlerurl() + "/events/saveEvents";
			logger.info("lotteryUrl:" + url);
			logger.info("param:" + param);
			String result = HttpUtil.post(url, param.toString());
			logger.info(result);
			JSONObject jo = new JSONObject(result);
			if("0".equals(jo.get("errorCode"))){
				errormsg = "成功";
			}else{
				errormsg = "失败,返回码"+jo.get("errorCode");
			}
		} catch (Exception e) {
			errormsg = "出错";
			logger.error("tjingcaiMatches/saveEvents error", e);
		}
		logger.info(errormsg);
		view.addObject("errormsg", errormsg);
		return this.saveEvents(view);
	}
}
