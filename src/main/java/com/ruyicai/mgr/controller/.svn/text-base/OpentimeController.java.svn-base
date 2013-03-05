package com.ruyicai.mgr.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Twininfo;
import com.ruyicai.mgr.mysql.PrizecrawlerDao;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/opentime")
@Controller
public class OpentimeController {
	
	private Logger logger = Logger.getLogger(OpentimeController.class);

	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view) {
		logger.info("opentime/page");
		view.setViewName("opentime/list");
		return view;
	}
	
	public static Map<String, String> map = new HashMap<String, String>();
	static{
		map.put("I10002", "澳客");
		map.put("I10003","淘宝");
		map.put("000004","彩通");
		map.put("tc0001","大赢家");
		map.put("000004","彩通");
		map.put("tx0001","腾讯");
		map.put("H00002","运维");
		map.put("H00001","客服");
		map.put("000002","内蒙福彩");
		map.put("I10001","500万");
	}
	@Autowired
	PrizecrawlerDao prizecrawlerDao;
	@RequestMapping("/list")
	public ModelAndView list(@RequestParam(value = "lotno") String lotno,
			@RequestParam(value = "batchcode") String batchcode,
			@RequestParam(value = "starttime") String starttime,
			@RequestParam(value = "endtime") String endtime,
			ModelAndView view) {
		logger.info("opentime/list");
		if (StringUtil.isEmpty(lotno) || StringUtil.isEmpty(starttime) || StringUtil.isEmpty(endtime)) {
			view.addObject("errormsg", "时间不允许为空");
			view.setViewName("opentime/list");
			return view;
		}
		StringBuilder builder = new StringBuilder("where");
		List<Object> params = new ArrayList<Object>();
		StringBuilder mysql = new StringBuilder("where");
		builder.append(" o.id.lotno=? and");
		params.add(lotno);
		mysql.append(" o.lotno = ? and");
		if (!StringUtil.isEmpty(batchcode)) {
			builder.append(" o.batchcode =? and");
			params.add(batchcode);
			mysql.append(" o.batchcode = ? and");
		}
		if (!StringUtil.isEmpty(starttime)) {
			builder.append(" to_char(o.agencyopentime, 'yyyy-mm-dd') >= ? and");
			params.add(starttime);
			mysql.append(" o.codedate >= ? and");
		}
		if (!StringUtil.isEmpty(endtime)) {
			builder.append(" to_char(o.agencyopentime, 'yyyy-mm-dd') <= ? and");
			params.add(endtime+" 23:59:59");
			mysql.append(" o.codedate <= ? and");
		}
		if (builder.toString().endsWith("and")) {
			builder.delete(builder.length() - 3, builder.length());
			mysql.delete(mysql.length() - 3, mysql.length());
		}
		if (builder.toString().endsWith("where")) {
			builder.delete(builder.length() - 5, builder.length());
			mysql.delete(mysql.length() - 5, mysql.length());
		}
		
		try {
			if(!StringUtil.isEmpty(builder.toString())){
				view.addObject("list", Twininfo.findAllTwininfoes(builder.toString(), params));
				view.addObject("list2", prizecrawlerDao.findAllTagencyprizecode2(mysql.toString(), params.toArray()));
			}
			
		} catch (Exception e) {
			view.addObject("errormsg", "查询出错");
			logger.error("opentime/list error", e);
		}
		view.setViewName("opentime/list");
		return view;
	}
	
}
