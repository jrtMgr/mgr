package com.ruyicai.mgr.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

import flexjson.JSONSerializer;

@RequestMapping(value = "/scoredetail")
@Controller
public class ScoredetailController {

	private Logger logger = Logger.getLogger(ScoredetailController.class);

	public static Map<String, String>  scoreTypeMap = new HashMap<String, String>();
	private void getScoreDetail(){
		StringBuilder url = new StringBuilder(propertiesUtil.getScoreurl()+"/selectScoreTypes");
		String resultMessage = "";
		try {
			resultMessage = HttpUtil.getResultMessage(url.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> ll = JsonUtil.transferJson2Map(resultMessage);
		Map<String, Object> m = (Map<String, Object>) ll.get("value");
		List<Map<String, Object>> l = (List<Map<String, Object>>) m.get("list");
		for (Map<String, Object> map : l) {
			scoreTypeMap.put(map.get("scoreType").toString(), map.get("memo").toString());
		}
	}
	@Autowired
	PropertiesUtil propertiesUtil;
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		this.getScoreDetail();
		view.setViewName("scoredetail/list");
		return view;
	}
	@RequestMapping("/list1")
	public ModelAndView list1(
			@RequestParam(value = "userno", required = false) String userno,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "scoreType", required = false) Integer scoreType,
			@RequestParam(value = "startLine", required = false, defaultValue = "0") Integer startLine,
			@RequestParam(value = "endLine", required = false, defaultValue = "20") Integer endLine,
			@RequestParam(value = "orderBy", required = false, defaultValue = "createTime") String orderBy,
			@RequestParam(value = "orderDir", required = false, defaultValue = "") String orderDir, 
			
			ModelAndView view) {
		logger.info("scoredetail/list1");
		view.addObject("startLine", startLine);
		view.addObject("endLine", endLine);
		view.addObject("orderBy", orderBy);
		view.addObject("orderDir", orderDir);
		try {
			StringBuilder url = new StringBuilder(propertiesUtil.getScoreurl() + "/findScoreDetailByUserno?flag=true&userno=");
			if (!StringUtils.isEmpty(userno)) {
				url.append(userno);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			if (scoreType != null) {
				if (scoreType == -2) {
					map.put("NQI_scoreType", -1);
				}else{
					map.put("EQI_scoreType", scoreType);
				}
			}
			if(!StringUtil.isEmpty(starttime)){
				map.put("GTD_createTime", starttime);
			}
			if(!StringUtil.isEmpty(endtime)){
				map.put("LED_createTime",URLEncoder.encode(endtime + " 23:59:59", "utf-8"));
			}
			if (!map.isEmpty()) {
				String condition = new JSONSerializer().serialize(map);
				url.append("&condition=" + condition);
			}
			if (startLine != null) {
				url.append("&startLine=" + startLine);
			}
			if (endLine != null) {
				url.append("&endLine=" + endLine);
			}
			if (StringUtils.isNotBlank(orderBy)) {
				url.append("&orderBy=" + orderBy);
			}
			if (StringUtils.isNotBlank(orderDir)) {
				url.append("&orderDir=" + orderDir);
			}
			
			String resultMessage = HttpUtil.getResultMessage(url.toString());
			resultMessage = JsonUtil.escapeJavaScript(resultMessage);
			view.addObject("result", resultMessage);
		} catch (Exception e) {
			logger.error("scoredetail/list error:", e);
			view.addObject("errormsg", "查询异常");
		}
		view.setViewName("scoredetail/list");
		return view;
	}
	
	
	@RequestMapping(value = "/addScore", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData addScore(@RequestParam("userno") String userno,
			@RequestParam("giveScore") Integer giveScore, 
			@RequestParam("memo") String memo, 
			
			HttpSession session, ModelAndView view) {
		logger.info("scoredetail/addScore");
		logger.info("添加积分的操作人员为:" + session.getAttribute("user"));
		ResponseData rd = new ResponseData();
		try {
			StringBuffer param = new StringBuffer();
			param.append("userno=").append(userno).append("&giveScore=").append(giveScore)
			.append("&scoreType=").append(99)
			.append("&memo=").append(URLEncoder.encode(memo.trim(), "UTF-8"));
			String result = HttpUtil.post(propertiesUtil.getScoreurl() + "/addTuserinfoScore", param.toString());
			JSONObject jo = new JSONObject(result);
			rd.setErrorCode(jo.getString("errorCode"));
			rd.setValue(jo.get("value"));
		} catch (Exception e) {
			logger.error("scoredetail/addScore error:", e);
		}
		return rd;
	}
}