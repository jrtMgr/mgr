package com.ruyicai.mgr.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tloguser;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.PropertiesUtil;

import flexjson.JSONSerializer;

@RequestMapping("/cmsg")
@Controller
public class CmsgController {
	private Logger logger = Logger.getLogger(CmsgController.class);
	
	@RequestMapping("/page")
	public String page(){
		logger.info("cmsg/page");
		return "cmsg/page";
	}
	
	@RequestMapping("/clientpage")
	public String clientpage(){
		logger.info("cmsg/clientpage");
		return "cmsg/clientpage";
	}
	
	@RequestMapping("/mpage")
	public ModelAndView managerpage(
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "userno", required = false) String userno,
			@RequestParam(value = "startLine", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "endLine", required = false, defaultValue = "20") int endLine, ModelAndView view) {
		logger.info("caselot/page");
		view.addObject("startLine", startLine);
		view.addObject("endLine", endLine);
		try {
			StringBuilder url = new StringBuilder(propertiesUtil.getMsgcenterurl()+"/msg/selectMsgs");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("EQI_flag", 0);
			if (id != null) {
				map.put("EQI_id", id);
			}
			if (StringUtils.isNotBlank(userno)) {
				map.put("EQS_userno", userno);
			}
			url.append("?startLine=" + startLine);
			url.append("&endLine=" + endLine);
			String condition = new JSONSerializer().serialize(map);
			url.append("&condition=" + condition);
			String resultMessage = HttpUtil.getResultMessage(url.toString());
			resultMessage = JsonUtil.escapeJavaScript(resultMessage);
			view.addObject("result", resultMessage);
		} catch (Exception e) {
			logger.error("cmsg/listMg error:", e);
			view.addObject("errormsg", "异常");
		}
		view.setViewName("cmsg/managerpage");
		return view;
	}
	
	@Autowired
	PropertiesUtil propertiesUtil;
	@RequestMapping(value = "/updateFlag", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData updateFlag(@RequestParam("id") String id,
			@RequestParam("flag") int flag, ModelAndView view) {
		logger.info("cmsg/updateFlag");
		ResponseData rd = new ResponseData();
		StringBuffer param = new StringBuffer();
		param.append("id=").append(id).append("&flag=").append(flag);
		try {
			String url = propertiesUtil.getMsgcenterurl()+"/msg/updateFlag?";
			String resultMessage = HttpUtil.getResultMessage(url + param);
			rd = ResponseData.fromJsonToResponseData(resultMessage);
		} catch (Exception e) {
			logger.error("cmsg/updateFlag error", e);
			rd.setErrorCode(ErrorCode.ERROR.value);
		}
		return rd;
	}
	

	@RequestMapping(value = "/reply")
	public ModelAndView reply(@RequestParam("id") String id,
			@RequestParam("reply") String reply,
			@RequestParam("bz") String bz,ModelAndView view, HttpSession session) {
		logger.info("cmsg/reply");
		String errormsg = "保存成功";
		try {
			Tloguser user = (Tloguser) session.getAttribute("user");
			StringBuilder param = new StringBuilder();
			param.append("id=").append(id).append("&reply=")
			.append(URLEncoder.encode(reply, "UTF-8"))
			.append("&username=").append(user.getNickname())
			.append("&bz=").append(URLEncoder.encode(bz, "UTF-8"));
			String url = propertiesUtil.getMsgcenterurl()+"/msg/reply";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg  =  json.toString();
			} 
		} catch (Exception e) {
			logger.error("cmsg/reply error", e);
			errormsg = "保存失败"+e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("cmsg/editUI");
		return view;
	}
	
	@RequestMapping("/huifuUI")
	public ModelAndView huifuUI(@RequestParam("id") String id, ModelAndView view){
		logger.info("cmsg/editUI");
		try {
			String url = propertiesUtil.getMsgcenterurl()+"/msg/selectMsgs?condition={EQI_id:"+id+"}";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.getResultMessage(url);
			JSONObject json = new JSONObject(result);
			JSONObject j = (JSONObject) json.get("value");
			JSONArray ja = (JSONArray) j.get("list");
			JSONObject jj = (JSONObject) ja.get(0);
			view.addObject("content", jj.get("content"));
			
			String re = propertiesUtil.getMsgcenterurl()+"/msg/selectReply?id="+id+"";
			logger.info("lotteryUrl:" + url);
			String res = HttpUtil.getResultMessage(re);
			view.addObject("reply", JsonUtil.transferJson2Map(res));
		} catch (Exception e) {
			logger.error("cmsg/huifuUI error", e);
		}
		view.setViewName("cmsg/editUI");
		return view;
	}
}
