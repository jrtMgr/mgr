package com.ruyicai.mgr.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping(value = "/agencybalance")
@Controller
public class AgencyBalanceController {
	public static Map<String, String> map = new HashMap<String, String>();
	static{
		map.put("dyj", "大赢家账户余额");
		map.put("zhangzhongcai", "重庆福彩账户余额");
		map.put("sdfcby", "山东丰彩博雅余额");
		map.put("fcby11c5", "广东十一选五余额");
		map.put("15120001", "15120001");
		map.put("15120011", "15120011");
		map.put("15120021", "15120021");
		
		/*map.put("caitongtc", "山东体彩账户余额");
		map.put("jingcai", "山东竞彩账户余额");
		map.put("huacai", "华彩账户余额");
		map.put("zhangzhongyitc", "掌中弈体彩账户余额");
		map.put("fcby", "丰采博雅余额");
		map.put("zlren", "直立人余额");
		map.put("yimingzhongtian", "亿鸣中天余额");
		map.put("fcby11c5", "广东十一选五余额");*/
	}
	private Logger logger = Logger.getLogger(AgencyBalanceController.class);
	@Autowired
	PropertiesUtil propertiesUtil;

	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view) {
		logger.info("agencybalance/page");
		view.setViewName("agencybalance/list2");
		return view;
	}

	@RequestMapping("/list")
	public ModelAndView list(ModelAndView view) {
		logger.info("agencybalance/list");
		try {
			StringBuilder url = new StringBuilder(propertiesUtil.getQuartzurl()+"/agencybalance/getAll");
			String resultMessage = HttpUtil.getResultMessage(url.toString());
			resultMessage = JsonUtil.escapeJavaScript(resultMessage);
			view.addObject("result", resultMessage);
		} catch (Exception e) {
			logger.error("agencybalance/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("agencybalance/list");
		return view;
	}
	@RequestMapping("/list2")
	public ModelAndView list2(@RequestParam(value = "agencyno", required = false) String agencyno,
			@RequestParam(value = "startLine", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "endLine", required = false, defaultValue = "30") int endLine,
			ModelAndView view) {
		view.addObject("startLine", startLine);
		view.addObject("endLine", endLine);
		logger.info("agencybalance/list2");
		String errormsg = "";
		try {
			StringBuffer url = new StringBuffer(propertiesUtil.getQuartzurl()+"/agencybalance/findBalanceRecords?");
			url.append("agencyno=").append(agencyno).append("&startLine=").append(startLine).append("&endLine=").append(endLine);
			String resultMessage = HttpUtil.getResultMessage(url.toString());
			resultMessage = JsonUtil.escapeJavaScript(resultMessage);
			view.addObject("result", resultMessage);
		} catch (Exception e) {
			logger.error("agencybalance/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("agencybalance/list2");
		return view;
	}

	
	@RequestMapping("/addUI")
	public ModelAndView addUI(ModelAndView view) {
		logger.info("agencybalance/addUI");
		view.setViewName("agencybalance/addUI");
		return view;
	}

	@RequestMapping("/add")
	public ModelAndView add(@RequestParam("agencyno") String agencyno,
			@RequestParam("money") Integer money,
			@RequestParam("mobile") String mobile, 
			@RequestParam("issendmsg") int issendmsg,
			ModelAndView view) {
		logger.info("agencybalance/add");
		String errormsg = "新增成功";
		try {
			if (StringUtil.isEmpty(agencyno) || StringUtil.isEmpty(mobile) || money == null) {
				view.addObject("errormsg", "不允许为空");
				return this.addUI(view);
			}
			String url = propertiesUtil.getQuartzurl()+"/agencybalance/save?agencyno="+agencyno+"&money="+money+"&mobile="+mobile+"&issendmsg="+issendmsg;
			String result = HttpUtil.getResultMessage(url);
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg  = "添加失败 返回码"+json.getString("errorCode");
				view.setViewName("agencybalance/addUI");
				view.addObject("errormsg", errormsg);
				return view;
			} 
		} catch (Exception e) {
			logger.error("agencybalance/add error", e);
			view.addObject("errormsg", "新增失败");
			return this.addUI(view);
		}

		view.addObject("errormsg", errormsg);
		return this.list(view);
	}
	
	@RequestMapping("/delete1")
	public ModelAndView delete(@RequestParam("id") String id,  ModelAndView view) {
		logger.info("agencybalance/delete");
		String errormsg = "删除成功";
		try {
			if (StringUtil.isEmpty(id)) {
				view.addObject("errormsg", "id不允许为空");
				return this.addUI(view);
			}
			String url = propertiesUtil.getQuartzurl()+"/agencybalance/delete?id="+id;
			String result = HttpUtil.getResultMessage(url);
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg  = "删除失败 返回码"+json.getString("errorCode");
			} 
		} catch (Exception e) {
			logger.error("agencybalance/delete error", e);
			view.addObject("errormsg", "新增失败");
			return this.addUI(view);
		}

		view.addObject("errormsg", errormsg);
		return this.list(view);
	}
	
	
	
	
	
	
	
}
