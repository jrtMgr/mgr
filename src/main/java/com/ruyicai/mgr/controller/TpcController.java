package com.ruyicai.mgr.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.lot.Lottype;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.PropertiesUtil;

import flexjson.JSONSerializer;

@RequestMapping("/tpc")
@Controller
public class TpcController {
	private Logger logger = Logger.getLogger(TpcController.class);
	@Autowired
	PropertiesUtil propertiesUtil;
	@RequestMapping("/page")
	public String page(){
		logger.info("cmsg/page");
		return "cmsg/page";
	}
	
	@RequestMapping("/listMg")
	public ModelAndView listMg(@RequestParam(value = "sortState", required = false, defaultValue = "0") int sortState,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "lotno", required = false, defaultValue = "") String lotno,
			@RequestParam(value = "startLine", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "endLine", required = false, defaultValue = "20") int endLine,
			@RequestParam(value = "orderBy", required = false, defaultValue = "") String orderBy,
			@RequestParam(value = "orderDir", required = false, defaultValue = "") String orderDir, ModelAndView view) {
		logger.info("caselot/page");
		view.addObject("startLine", startLine);
		view.addObject("endLine", endLine);
		view.addObject("orderBy", orderBy);
		view.addObject("orderDir", orderDir);
		try {
			StringBuilder url = new StringBuilder(propertiesUtil.getLotteryurl()
					+ "/select/selectCaseLots?state=1&state=2&state=3");
			if (StringUtils.isNotBlank(search)) {
				url.append("&search=" + search);
				view.addObject("search", search);
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("EQI_sortState", sortState + "");
			view.addObject("sortState", sortState);
			if (StringUtils.isNotBlank(lotno)) {
				map.put("EQS_lotno", lotno);
				view.addObject("lotno", lotno);
			}
			url.append("&startLine=" + startLine);
			url.append("&endLine=" + endLine);
			if (StringUtils.isNotBlank(orderBy)) {
				url.append("&orderBy=" + orderBy);
			}
			if (StringUtils.isNotBlank(orderDir)) {
				url.append("&orderDir=" + orderDir);
			}
			String condition = new JSONSerializer().serialize(map);
			url.append("&condition=" + condition);
			String resultMessage = HttpUtil.getResultMessage(url.toString());
			resultMessage = JsonUtil.escapeJavaScript(resultMessage);
			view.addObject("result", resultMessage);

			Map<String, String> lotTypes = Lottype.getMap();
			view.addObject("lotTypes", lotTypes);
		} catch (Exception e) {
			logger.error("tpc/listMg error:", e);
			view.addObject("errormsg", "合买置顶管理分页查询异常");
		}
		view.setViewName("caselot/listMg");
		return view;
	}
}
