package com.ruyicai.mgr.controller;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.PropertiesUtil;

@RequestMapping("/scoretype")
@Controller
public class ScoretypeController {
	private Logger logger = Logger.getLogger(ScoretypeController.class);
	@Autowired
	PropertiesUtil propertiesUtil;
	
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view) {
		logger.info("scoretype/page");
		try {
			
			StringBuilder url = new StringBuilder(propertiesUtil.getScoreurl()+"/selectScoreTypes");
			String resultMessage = HttpUtil.getResultMessage(url.toString());
			Map<String, Object> ll = JsonUtil.transferJson2Map(resultMessage);
			Map<String, Object> m = (Map<String, Object>) ll.get("value");
			List<Map<String, Object>> l = (List<Map<String, Object>>) m.get("list");
			
			view.addObject("list", l);
		} catch (Exception e) {
			logger.error("scoretype/list error:", e);
			view.addObject("errormsg", "异常");
		}
		view.setViewName("scoretype/list");
		return view;
	}
	
	@RequestMapping(value = "/saveOrUpdateScoreType", method = RequestMethod.POST)
	public ModelAndView saveOrUpdateScoreType(@RequestParam("scoreType") Integer scoreType, @RequestParam("memo") String memo,
			@RequestParam(value = "times") Integer times, @RequestParam("state") Integer state,
			@RequestParam(value = "scoreJson") String scoreJson, ModelAndView view) {
		logger.info("scoretype/saveOrUpdateScoreType");
		String errormsg = "保存成功";
		try {
			String url = propertiesUtil.getScoreurl()+"/saveOrUpdateScoreType";
			StringBuilder param = new StringBuilder();
			param.append("scoreType=").append(scoreType).append("&memo=");
			if(memo != null){
				param.append(URLEncoder.encode(memo, "UTF-8"));
			}
			param.append("&times=");
			if (times != null) {
				param.append(times);
			}
			param.append("&state=").append(state).append("&scoreJson=");
			if (scoreJson != null) {
				param.append(scoreJson);
			}
			logger.info("lotteryUrl:"+url+",param:"+param);
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg  =  json.toString();
			} 
		} catch (Exception e) {
			logger.error("scoretype/saveOrUpdateScoreType error:", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
}
