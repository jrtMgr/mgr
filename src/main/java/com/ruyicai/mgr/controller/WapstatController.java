package com.ruyicai.mgr.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping(value = "/wapstat")
@Controller()
public class WapstatController {

	private Logger logger = Logger.getLogger(WapstatController.class);
	
	@RequestMapping(value = "/list")
	public ModelAndView list(ModelAndView view) {
		logger.info("wapstat/list");		
		view.setViewName("wapstat/list");
		return view;
	}
	
	@RequestMapping(value = "/regstat")
	public ModelAndView highbuyer(@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,		
			ModelAndView view) {
		logger.info("wapstat/regstat");
		Long regusers = 0L;
		try {
			StringBuilder builder = new StringBuilder(
					" where o.subChannel = '00092493' and o.channel = '1' ");
			List<Object> params = new ArrayList<Object>();
			if (!StringUtil.isEmpty(starttime)) {
				builder.append(" and to_char(o.regtime, 'yyyy-mm-dd') >= ? ");
				params.add(starttime);
				view.addObject("starttime", starttime);
			}
			if (!StringUtil.isEmpty(endtime)) {
				builder.append(" and to_char(o.regtime, 'yyyy-mm-dd') <= ? ");
				params.add(endtime);
				view.addObject("endtime", endtime);
			}

			regusers = Tuserinfo.statRegUser(builder.toString(), params);
		} catch (Exception e) {
			view.addObject("errormsg", e.getMessage());
		}
		view.addObject("regusers", regusers);	
		view.setViewName("wapstat/regstat");
		return view;
	}	
}
