package com.ruyicai.mgr.controller;

import java.util.Date;
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

import com.ruyicai.mgr.mysql.IssusDao;
import com.ruyicai.mgr.mysql.pojo.FLData;
import com.ruyicai.mgr.mysql.pojo.Issus;
import com.ruyicai.mgr.service.LotteryService;
import com.ruyicai.mgr.util.CatchFLDateFromOKOOO;
import com.ruyicai.mgr.util.DateUtil;
import com.ruyicai.mgr.util.Page;

@RequestMapping("/events")
@Controller
public class EventsController {
	private Logger logger = Logger.getLogger(EventsController.class);
	@Autowired
	IssusDao issusDao;
	@RequestMapping("/page")
	public ModelAndView page(@ModelAttribute("page") Page<Map<String, Object>> page, 
			ModelAndView view){
		try {
			issusDao.findList(page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("events/page error", e);
		}
		view.setViewName("events/eventsList");
		return view;
	}
	
	@RequestMapping("/addAgainst")
	public ModelAndView list(@RequestParam(value = "id") String id,
			ModelAndView view){
		logger.info("events/addAgainst");
		try {
			view.addObject("IssList", issusDao.getIssus(id));
			view.addObject("list", issusDao.getFLDataList(id));
		} catch (Exception e) {
			logger.error("events/addAgainst error", e);
		}
		view.setViewName("events/addAgainst");
		return view;
	}
	
	/**
	 * 删除本期对阵数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/deleteEvents")
	public ModelAndView deleteEvents(@RequestParam(value = "id") String id,
			ModelAndView view){
		logger.info("删除本期对阵开始");
		try {
			issusDao.deleteEvents(id);
		} catch (Exception e) {
			logger.error("events/deleteEvents error", e);
		}
		view.setViewName("events/addAgainst");
		return view;
	}
	/**
	 * 
	 */
	@RequestMapping("/editEventsUI")
	public ModelAndView editEvents(@RequestParam(value = "id") String id,
			@RequestParam(value = "cc") String cc,
			ModelAndView view){
		logger.info("删除本期对阵开始");
		try {
			view.addObject("tr", issusDao.getFLDataByIdAndNun(id, cc));
		} catch (Exception e) {
			logger.error("events/editEventsUI error", e);
		}
		view.setViewName("events/editEvents");
		return view;
	}
	
	/**
	 * 修改场次内容
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/editEvents")
	public ModelAndView editEvents(@RequestParam(value = "id") String id,
			@RequestParam(value = "cc") String cc,
			@RequestParam(value = "ssmc") String ssmc,// 赛事名称
			@RequestParam(value = "zd") String HTeam,// 主队名称
			@RequestParam(value = "kd") String VTeam,// 客队名称
			@RequestParam(value = "ksrq") String ksrq,
			@RequestParam(value = "kssj") String kssj,
			@RequestParam(value = "ops") String ops,
			@RequestParam(value = "opf") String opf,
			@RequestParam(value = "opp") String opp,
			
			@RequestParam(value = "lspmzd") String lspmzd,
			@RequestParam(value = "lspmkd") String lspmkd,
			@RequestParam(value = "z8s") String z8s,
			
			@RequestParam(value = "z8f") String z8f,
			@RequestParam(value = "z8p") String z8p,
			@RequestParam(value = "z8jq") String z8jq,
			@RequestParam(value = "z8sq") String z8sq,
			@RequestParam(value = "k8s") String k8s,
			@RequestParam(value = "k8f") String k8f,
			@RequestParam(value = "k8p") String k8p,
			@RequestParam(value = "k8jq") String k8jq,
			@RequestParam(value = "k8sq") String k8sq,
			@RequestParam(value = "bsbfzd") String bsbfzd,
			@RequestParam(value = "bsbfkd") String bsbfkd,
			@RequestParam(value = "banchangzd") String banchangzd,
			@RequestParam(value = "banchangkd") String banchangkd,
			@RequestParam(value = "ou") String ou,
			@RequestParam(value = "ya") String ya,
			@RequestParam(value = "xi") String xi,
			@RequestParam(value = "ce") String ce,
			ModelAndView view) {
		logger.info("修改场次内容开始");
		String errormsg = "修改成功";
		try {
			FLData fl = new FLData();
			fl.setPid(id);
			fl.setNum(cc);
			fl.setName(ssmc);
			fl.setHTeam(HTeam);
			fl.setVTeam(VTeam);
			
			String date = ksrq + " " + kssj;// 开赛时间
			fl.setDate(date);
			
			String avgOdds = ops + "|" + opp + "|" + opf ;
			avgOdds = avgOdds.equals("||")?"":avgOdds;
			fl.setAvgOdds(avgOdds);
			
			String leagueRank = "主队" + lspmzd + "|客队" + lspmkd;
			leagueRank = leagueRank.equals("主队|客队")?"":leagueRank;
			fl.setLeagueRank(leagueRank);
			
			String HTeam8 = z8s + "胜|" + z8p + "平|" + z8f + "负|进" + z8jq + "球|失" + z8sq+"球";
			HTeam8 = HTeam8.equals("胜|平|负|进球|失球")?"":HTeam8;
			fl.setHTeam8(HTeam8);
			
			String VTeam8 = k8s + "胜|" + k8p + "平|" + k8f + "负|进" + k8jq + "球|失" + k8sq+"球";
			VTeam8 = VTeam8.equals("胜|平|负|进球|失球")?"":VTeam8;
			fl.setVTeam8(VTeam8);
			fl.setOu(ou);
			fl.setYa(ya);
			fl.setXi(xi);
			fl.setCe(ce);
			fl.setBsbfzd(bsbfzd);
			fl.setBsbfkd(bsbfkd);
			fl.setBanchangzd(banchangzd);
			fl.setBanchangkd(banchangkd);
			
			issusDao.updateFLData(fl);
			
			lotteryService.releaseIssusJms(id);
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("events/editEvents error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.list(id, view);

	}
	
	@RequestMapping("/addIssusUI")
	public ModelAndView addIssusUI(ModelAndView view){
		logger.info("删除本期对阵开始");
		try {
			view.addObject("iss", issusDao.getNextIssus());
		} catch (Exception e) {
			logger.error("events/addIssusUI error", e);
		}
		view.setViewName("events/addIssus");
		return view;
	}
	/**
	 * 设置期号
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/addIssus")
	public ModelAndView addIssus(@RequestParam(value = "vdlId") String vdlId,// 胜负彩期号
			@RequestParam(value = "any9Id") String any9Id,// 任9
			@RequestParam(value = "games6Id") String games6Id,//六场半全场期次号
			@RequestParam(value = "games4Id") String games4Id,//四场进球期次号
			ModelAndView view){
		logger.info("设置期号开始");
		StringBuffer type = new StringBuffer().append("".equals(vdlId)?"0|":"1|")
			.append("".equals(any9Id)?"0|":"1|").append("".equals(games6Id)?"0|":"1|")
			.append("".equals(games4Id)?"0":"1");//足彩期号类型
		Map<String, Object> ism = issusDao.getNextIssus();
		String errormsg = "添加成功";
		String id = "";
		if(vdlId.equals(ism.get("vdlId"))){
			errormsg = "胜负彩期数已存在";
		}else if(any9Id.equals(ism.get("any9Id"))){
			errormsg = "任9期数已存在";
		}else if(games6Id.equals(ism.get("games6Id"))){
			errormsg = "六场半全场期数已存在";
		}else if(games4Id.equals(ism.get("games4Id"))){
			errormsg = "四场进球期数已存在";
		}else{
			Issus is = new Issus();
			is.setVdlId(vdlId);
			is.setAny9Id(any9Id);
			is.setGames6Id(games6Id);
			is.setGames4Id(games4Id);
			is.setAddDate(DateUtil.format(new Date()));
			is.setType(type.toString());
			is.setFlag("0");
			try{
				issusDao.addIssus(is);
				id = issusDao.getId(vdlId, any9Id, games6Id, games4Id).toString();
				view.addObject("id", id);
			}catch(Exception e){
				logger.error("events/addIssus error", e);
				errormsg = e.getMessage();
			}
		}
		view.addObject("errormsg", errormsg);
		return this.addUI(id, view);
	}
	
	
	/**
	 * 添加页面
	 */
	@RequestMapping("/addUI")
	public ModelAndView addUI(@RequestParam(value = "id", required=false) String id,
			ModelAndView view){
		logger.info(" 添加页面");
		try {
			if (id != null && !"".equals(id)){
				view.addObject("iss", issusDao.getIssus(id));
			}
		} catch (Exception e) {
			logger.error("events/addUI error", e);
		}
		view.setViewName("events/addUI");
		return view;
	}
	
	/**
	 * 自动获取对阵信息
	 */
	@RequestMapping("/getFlData")
	public ModelAndView getFlData(@RequestParam(value = "id") String id,
			@RequestParam(value = "flUrl") String url,
			ModelAndView view){
		String errormsg = "自动获取对阵成功";
		try {
			List<FLData> list = CatchFLDateFromOKOOO.getFLDatas(url+"?a="+System.currentTimeMillis());
			for(FLData fl:list){
				fl.setPid(id);
			}
			view.addObject("dataList", list);
		} catch (Exception e) {
			logger.error("events/getFlData error", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.addUI(id, view);
	}
	
	
	/**
	 * 保存获取到的对阵信息
	 */
	@RequestMapping("/saveFlData")
	public ModelAndView saveFlData(HttpServletRequest request,
			ModelAndView view){
		logger.info("events/saveFlData");
		String errormsg = "保存成功";
		String id = request.getParameter("id");
		String[] dataName = request.getParameterValues("dataName");
		String[] num = request.getParameterValues("num");
		if (num.length != 14) {
			view.addObject("errormsg", "必须填写14个对阵");
			return this.addUI(id, view);
		}
		String[] dataHTeam = request.getParameterValues("dataHTeam");
		String[] dataVTeam = request.getParameterValues("dataVTeam");
		String[] dataDate = request.getParameterValues("dataDate");
		String[] avgOdds = request.getParameterValues("avgOdds");
		int flCount = num.length;
		FLData fl = new FLData();
		for(int i = 0;i<flCount;i++){
			fl.setPid(id);
			fl.setNum(num[i]);
			fl.setName(dataName[i]);
			fl.setHTeam(dataHTeam[i]);
			fl.setVTeam(dataVTeam[i]);
			fl.setDate(dataDate[i]);
			fl.setAvgOdds(avgOdds[i]);
			try{
				issusDao.addFLData(fl);
			}catch(Exception e){
				e.printStackTrace();
				errormsg = e.getMessage();
			}
		}
		view.addObject("errormsg", errormsg);
		return this.page(new Page<Map<String,Object>>(), view);
	}
	
	/**
	 * 删除期
	 */
	@RequestMapping("/deleteIssue")
	public ModelAndView deleteIssue(@RequestParam(value = "id") String id,
			ModelAndView view){
		try {
			issusDao.deleteEvents(id);
			issusDao.deleteIssue(id);
		} catch (Exception e) {
			logger.error("events/deleteIssue error", e);
		}
		view.addObject("errormsg", "删除成功");
		return this.page(new Page<Map<String,Object>>(), view);
	}
	
	/**
	 * 保存获取到的对阵信息
	 */
	@RequestMapping("/savecc")
	public ModelAndView savecc(@RequestParam(value = "id") String id,
			@RequestParam(value = "str1") String str1,
			@RequestParam(value = "str2") String str2,
			@RequestParam(value = "str3") String str3,
			ModelAndView view){
		logger.info("events/savecc");
		String errormsg = "保存成功";
		String[] sfc = str1.split(",");
		String[] game6 = str2.split(",");
		String[] game4 = str3.split(",");
		
		try{
			for(int i = 0;i<14;i++){
				String flag = sfc[i] + "|"+game6[i]+"|"+game4[i];
				int j = issusDao.savecc(id, i+1, flag);
			}
		}catch(Exception e){
			e.printStackTrace();
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.list(id, view);
	}
	
	@Autowired
	LotteryService lotteryService;
	/**
	 * 保存获取到的对阵信息
	 */
	@RequestMapping("/release")
	public ModelAndView release(@RequestParam(value = "id") String id,
			ModelAndView view){
		logger.info("events/release");
		String errormsg = "发布成功";
		
		try{
			issusDao.release(id);
			lotteryService.releaseIssusJms(id);
		}catch(Exception e){
			logger.error("events/release error", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.list(id, view);
	}
}