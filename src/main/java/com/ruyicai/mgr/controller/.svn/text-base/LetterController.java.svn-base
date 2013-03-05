package com.ruyicai.mgr.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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

import flexjson.JSONSerializer;

@RequestMapping("/letter")
@Controller
public class LetterController {
	private Logger logger = Logger.getLogger(LetterController.class);
	
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		logger.info("letter/page");
		view.addObject("startLine", 0);
		view.addObject("endLine", 20);
		view.setViewName("letter/list");
		return view;
	}

	@Autowired
	PropertiesUtil propertiesUtil;
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "fromUserno", required = false) String fromUserno,
			@RequestParam(value = "toUserno", required = false) String toUserno,
			@RequestParam(value = "startLine", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "endLine", required = false, defaultValue = "20") int endLine, ModelAndView view) {
		logger.info("letter/list");
		view.addObject("startLine", startLine);
		view.addObject("endLine", endLine);
		try {
			StringBuilder url = new StringBuilder(propertiesUtil.getMsgcenterurl()+"/letter/findLetterByPage");
			Map<String, Object> map = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(fromUserno)) {
				map.put("EQS_fromUserno", fromUserno);
			}
			if (StringUtils.isNotBlank(toUserno)) {
				map.put("EQS_toUserno", toUserno);
			}
			url.append("?startLine=" + startLine);
			url.append("&endLine=" + endLine);
			String condition = new JSONSerializer().serialize(map);
			url.append("&condition=" + condition);
			String result = HttpUtil.getResultMessage(url.toString());
			result =  JsonUtil.escapeJavaScript(result);
			view.addObject("result", result);
		} catch (Exception e) {
			logger.error("letter/list error:", e);
			view.addObject("errormsg", "异常");
		}
		view.setViewName("letter/list");
		return view;
	}
	
	@RequestMapping("/addUI")
	public ModelAndView addUI(ModelAndView view){
		view.setViewName("letter/addUI");
		return view;
	}	
	
	
	@RequestMapping("/add")
	public ModelAndView add(@RequestParam(value = "fromUserno") String fromUserno,
			@RequestParam(value = "toUserno") String toUserno, 
			@RequestParam(value = "title") String title,
			@RequestParam(value = "letterType", required = false, defaultValue = "0") Integer letterType,
			@RequestParam(value = "content", required = false) String content,
			ModelAndView view) {
				
		logger.info("letter/createLetterBatch");
		String errormsg = "新增成功";
		try {
			if (StringUtil.isEmpty(fromUserno) || StringUtil.isEmpty(toUserno)||StringUtil.isEmpty(title)||StringUtil.isEmpty(content)  ) {
				view.addObject("errormsg", "不允许为空");
				return this.addUI(view);
			}
			StringBuffer params = new StringBuffer();
			params.append("fromUserno=").append(fromUserno).append("&toUserno=").append(toUserno).append("&title=").append(URLEncoder.encode(title, "UTF-8"))
			.append("&letterType=").append(letterType).append("&content=").append(URLEncoder.encode(content, "UTF-8"));
			String result = HttpUtil.post(propertiesUtil.getMsgcenterurl() + "/letter/createLetterBatch",
					params.toString());
			logger.info(result);
			JSONObject jo = new JSONObject(result);
			if("0".equals(jo.get("errorCode"))){
				errormsg = "添加成功";
			}else{
				errormsg = result;
			}
		} catch (Exception e) {
			logger.error("letter/add error", e);
			view.addObject("errormsg", "新增失败");
			return this.addUI(view);
		}
		
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
}
