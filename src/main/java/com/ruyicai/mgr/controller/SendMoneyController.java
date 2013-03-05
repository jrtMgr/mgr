package com.ruyicai.mgr.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tloguser;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

import flexjson.JSONSerializer;

@RequestMapping("/sendmoney")
@Controller
public class SendMoneyController {
	private Logger logger = Logger.getLogger(SendMoneyController.class);
	
	@Autowired
	PropertiesUtil propertiesUtil;
	
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "sendusername", required = false) String sendusername,
			@RequestParam(value = "reciverUserno", required = false) String reciverUserno,
			@RequestParam(value = "reciveState", required = false) String reciveState,
			@RequestParam(value = "startLine", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "endLine", required = false, defaultValue = "30") int endLine,
			@RequestParam(value = "orderBy", required = false, defaultValue = "") String orderBy,
			@RequestParam(value = "orderDir", required = false, defaultValue = "") String orderDir,ModelAndView view) {
		logger.info("/sendmoney/list");
		view.addObject("startLine", startLine);
		view.addObject("endLine", endLine);
		try {
			StringBuilder url = new StringBuilder(propertiesUtil.getActioncenterurl()
					+ "/sendMoney/selectSendMoneyDetails");
			Map<String, Object> map = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(sendusername)) {
				map.put("EQS_sendusername", sendusername);
			}
			if (StringUtils.isNotBlank(reciverUserno)) {
				map.put("EQS_reciverUserno", reciverUserno);
			}
			if (StringUtils.isNotBlank(reciveState)) {
				map.put("EQI_reciveState", reciveState);
			}
			url.append("?startLine=" + startLine);
			url.append("&endLine=" + endLine);
			String condition = new JSONSerializer().serialize(map);
			url.append("&condition=" + condition);
			
			String resultMessage = HttpUtil.getResultMessage(url.toString());
			resultMessage = JsonUtil.escapeJavaScript(resultMessage);
			view.addObject("result", resultMessage);
		} catch (Exception e) {
			logger.error("sendmoney/list error:", e);
			view.addObject("errormsg", "增彩管理分页查询异常");
		}	
		view.setViewName("sendmoney/list");
		return view;
	}
	
	@RequestMapping("/add")
	public ModelAndView add(@RequestParam("reciverUserno") String reciverUserno,
			@RequestParam("content") String content,
			@RequestParam("amt") BigDecimal amt,
			HttpServletRequest request, ModelAndView view){
		logger.info("/sendmoney/add");
		String errormsg = "添加红包成功";	
		try {
			if (BigDecimal.ONE.compareTo(amt) > 0) {
				throw new RuntimeException("金额输入不正确");
			}
			if (StringUtil.isEmpty(reciverUserno) || StringUtil.isEmpty(content)) {
				view.addObject("errormsg", "不允许为空");
				return this.addInfo(view);
			}
			String[] usernos = reciverUserno.split(",");
			for (String userno : usernos) {
				Tuserinfo user = Tuserinfo.findTuserinfo(userno);
				if (user == null) {
					view.addObject("errormsg", userno+"编号不存在，请核对");
					return this.addInfo(view);
				}
			}
			String sendusername = ((Tloguser) request.getSession().getAttribute("user")).getNickname();
			for (String userno : usernos) {
				String url = propertiesUtil.getActioncenterurl() + "/sendMoney/savesendMoney";
				StringBuffer param = new StringBuffer();
				param.append("sendusername=").append(sendusername).append("&reciverUserno=").append(userno)
				.append("&content=").append(content).append("&amt=").append(amt);
				
				String result = HttpUtil.post(url, param.toString());
				logger.info(result);
			}
		} catch (Exception e) {
			logger.error("sendmoney/add error", e);
			errormsg = e.getMessage();			
		}
		view.addObject("errormsg", errormsg);
		return this.list(null, null, null, 0, 30, null, null, view);
	}
	
	
	@RequestMapping("/addInfo")
	public ModelAndView addInfo(ModelAndView view) {
		logger.info("/sendmoney/addInfo");
		view.setViewName("sendmoney/addUI");
		return view;
	}
	
	
}
