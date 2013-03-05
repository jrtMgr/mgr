package com.ruyicai.mgr.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.mysql.NewsDao;
import com.ruyicai.mgr.mysql.domain.TjingcaiResult;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping(value = "/tjingcairesult")
@Controller
public class TjingcaiResultController {

	private Logger logger = Logger.getLogger(TjingcaiResultController.class);
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view) {
		view.setViewName("tjingcairesult/list");
		return view;
	}
	@Autowired
	NewsDao newsDao;
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "type") String type,
			@RequestParam(value = "day") String day,
			@RequestParam(value = "audit") Integer audit,
			@ModelAttribute("page") Page<Map<String, Object>> page, ModelAndView view) {
		logger.info("tjingcairesult/list");
		try {
			StringBuilder builder = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			if (!StringUtil.isEmpty(type)) {
				builder.append(" and t.type = ?");
				params.add(type);
			}
			if (!StringUtil.isEmpty(day)) {
				builder.append(" and t.day = ?");
				params.add(day);
			}
			if (audit != null) {
				builder.append(" and r.audit = ?");
				params.add(audit);
			}
			newsDao.findjingcaiResultList(builder.toString(), params, page);
			//TjingcaiResult.findList(builder.toString(), "order by o.id desc", params, page);
			page.getList();
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tjingcairesult/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("tjingcairesult/list");
		return view;
	}
	
	@RequestMapping("/editUI")
	public ModelAndView editUI(@RequestParam("id") String id,
			ModelAndView view) {
		logger.info("tjingcairesult/editUI");
		try {
			view.addObject("tjingcairesult", TjingcaiResult.findTjingcaiResult(id));
		} catch (Exception e) {
			logger.error(e);
			view.addObject("errormsg", "出错了看日志");
		}
		view.setViewName("tjingcairesult/editUI");
		return view;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam("id") String id,
			@RequestParam("cancel") BigDecimal cancel,
			@RequestParam("letpoint") String letpoint,
			@RequestParam("basepoint") String basepoint,
			@RequestParam("result") String result,
			@RequestParam("firsthalfresult") String firsthalfresult,
			@RequestParam("b0") String b0,
			@RequestParam("b1") String b1,
			@RequestParam("b2") String b2,
			@RequestParam("b3") String b3,
			@RequestParam("b4") String b4,
			@RequestParam("b5") String b5,
			@RequestParam("b6") String b6,
			ModelAndView view) {
		logger.info("tdnabind/edit");
		String errormsg = "操作成功";
		try {
			TjingcaiResult tjingcaiResult = TjingcaiResult.findTjingcaiResult(id);
			tjingcaiResult.setCancel(cancel);
			tjingcaiResult.setLetpoint(letpoint);
			tjingcaiResult.setBasepoint(basepoint);
			tjingcaiResult.setResult(result);
			tjingcaiResult.setFirsthalfresult(firsthalfresult);
			tjingcaiResult.setB0(b0);
			tjingcaiResult.setB1(b1);
			tjingcaiResult.setB2(b2);
			tjingcaiResult.setB3(b3);
			tjingcaiResult.setB4(b4);
			tjingcaiResult.setB5(b5);
			tjingcaiResult.setB6(b6);
			tjingcaiResult.merge();
		} catch (Exception e) {
			logger.error("tjingcairesult/edit error", e);
			view.addObject("errormsg", "修改失败");
			return this.editUI(id, view);
		}
		view.addObject("errormsg", errormsg);
		return this.editUI(id, view);
	}
	
	
	@RequestMapping("/addUI")
	public ModelAndView addUI(ModelAndView view) {
		logger.info("tjingcairesult/addUI");
		view.setViewName("tjingcairesult/addUI");
		return view;
	}
	@RequestMapping("/add")
	public ModelAndView add(@RequestParam("id") String id,
			@RequestParam("cancel") BigDecimal cancel,
			@RequestParam("letpoint") String letpoint,
			@RequestParam("basepoint") String basepoint,
			@RequestParam("result") String result,
			@RequestParam("firsthalfresult") String firsthalfresult,
			@RequestParam("b0") String b0,
			@RequestParam("b1") String b1,
			@RequestParam("b2") String b2,
			@RequestParam("b3") String b3,
			@RequestParam("b4") String b4,
			@RequestParam("b5") String b5,
			@RequestParam("b6") String b6,
			ModelAndView view) {
		logger.info("tjingcairesult/add");
		String errormsg = "新增成功";
		try {
			TjingcaiResult tjingcaiResult = new TjingcaiResult();
			tjingcaiResult.setId(id);
			tjingcaiResult.setCancel(cancel);
			tjingcaiResult.setLetpoint(letpoint);
			tjingcaiResult.setBasepoint(basepoint);
			tjingcaiResult.setResult(result);
			tjingcaiResult.setFirsthalfresult(firsthalfresult);
			tjingcaiResult.setB0(b0);
			tjingcaiResult.setB1(b1);
			tjingcaiResult.setB2(b2);
			tjingcaiResult.setB3(b3);
			tjingcaiResult.setB4(b4);
			tjingcaiResult.setB5(b5);
			tjingcaiResult.setB6(b6);
			tjingcaiResult.setAudit(BigDecimal.ONE);
			tjingcaiResult.persist();
		} catch (Exception e) {
			logger.error("tjingcairesult/add error", e);
			return this.addUI(view);
		}
		
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	@Autowired
	PropertiesUtil propertiesUtil;
	
	@RequestMapping("/getfootballresult")
	public ModelAndView getfootballmatches(ModelAndView view) {
		logger.info("tjingcairesult/getfootballresult");
		String errormsg = "";
		try {
			String result = HttpUtil.post(propertiesUtil.getPrizecrawlerurl() + "/jingcai/getfootballresult", "");
			logger.info(result);
			JSONObject jo = new JSONObject(result);
			if("0".equals(jo.get("errorCode"))){
				errormsg = "获取成功";
			}else{
				errormsg = "获取失败";
			}
		} catch (Exception e) {
			errormsg = "获取出错";
			logger.error("tjingcairesult/getjingcairesult error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	@RequestMapping("/getbasketballresult")
	public ModelAndView getbasketballmatches(ModelAndView view) {
		logger.info("tjingcairesult/getbasketballresult");
		String errormsg = "";
		try {
			String result = HttpUtil.post(propertiesUtil.getPrizecrawlerurl() + "/jingcai/getbasketballresult", "");
			logger.info(result);
			JSONObject jo = new JSONObject(result);
			if("0".equals(jo.get("errorCode"))){
				errormsg = "获取成功";
			}else{
				errormsg = "获取失败";
			}
		} catch (Exception e) {
			view.addObject("errormsg", "出错");
			logger.error("tjingcairesult/getjingcairesult error", e);
		}
		
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	@RequestMapping("/appromore")
	public ModelAndView appro(@RequestParam("checkboxname") String[] approvalMoreIds,
			ModelAndView view){
		logger.info("tjingcairesult/appromore");
		String errormsg = "";
		try{
			for(int i=0;i<approvalMoreIds.length;i++){
				logger.info("被审核的id为:"+approvalMoreIds[i]);
				TjingcaiResult t = TjingcaiResult.findTjingcaiResult(approvalMoreIds[i]);
				if(t != null && !BigDecimal.ZERO.equals(t.getAudit())){
					
					String result = HttpUtil.post(propertiesUtil.getLotteryurl() + "/system/jingcairesult", "event="+approvalMoreIds[i]);
					JSONObject jo = new JSONObject(result);
					if(!"0".equals(jo.get("errorCode"))){
						errormsg = approvalMoreIds[i] + "调用lottery出错.";
					}
				}else{
					errormsg += approvalMoreIds[i] + "有误或是已审核。";
				}
			}
		} catch (Exception e) {
			logger.info("出现异常"+e);
			errormsg = "出现异常";
		}
		if(StringUtil.isEmpty(errormsg)){
			errormsg = "操作成功";
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}

	@RequestMapping("/audit")
	public ModelAndView audit(@RequestParam("id") String id,
			ModelAndView view){
		logger.info("tjingcairesult/appromore");
		String errormsg = "";
		try{
				logger.info("被审核的id为:"+id);
				TjingcaiResult t = TjingcaiResult.findTjingcaiResult(id);
				if(t != null && !BigDecimal.ZERO.equals(t.getAudit())){
					
					String result = HttpUtil.post(propertiesUtil.getLotteryurl() + "/system/jingcairesult", "event="+id);
					JSONObject jo = new JSONObject(result);
					if(!"0".equals(jo.get("errorCode"))){
						errormsg = id + "调用lottery出错.";
					}
				}else{
					errormsg += id + "有误或是已审核。";
				}
		} catch (Exception e) {
			logger.info("出现异常"+e);
			errormsg = "出现异常";
		}
		if(StringUtil.isEmpty(errormsg)){
			errormsg = "操作成功";
		}
		view.addObject("errormsg", errormsg);
		return this.editUI(id, view);
	}
	
	@RequestMapping("/deleteResult")
	public ModelAndView deleteResult(@RequestParam("id") String id,
			ModelAndView view){
		logger.info("tjingcairesult/deleteResult");
		String errormsg = "删除成功";
		try{
				logger.info("被删除的id为:"+id);
				TjingcaiResult t = TjingcaiResult.findTjingcaiResult(id);
				if(t != null){
					t.remove();
				}else{
					errormsg = id + "有误或。";
				}
		} catch (Exception e) {
			logger.error("tjingcairesult/deleteResult error", e);
			errormsg = "出现异常";
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
}
