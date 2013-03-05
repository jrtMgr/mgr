package com.ruyicai.mgr.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tdnabind;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping(value = "/tdnabind")
@Controller
public class TdnabindController {

	private Logger logger = Logger.getLogger(TdnabindController.class);
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view) {
		view.setViewName("tdnabind/list");
		return view;
	}
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "userno", required = false) String userno,
			@RequestParam(value = "mobileid", required = false) String mobileid,
			@RequestParam(value = "state", required = false) BigDecimal state,
			@ModelAttribute("page") Page<Tdnabind> page, ModelAndView view) {
		logger.info("tdnabind/list");
		try {
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			if(!StringUtil.isEmpty(userno)) {
				builder.append(" o.userno=? and");
				params.add(userno);
				view.addObject("userno", userno);
			}
			if (!StringUtil.isEmpty(mobileid)) {
				builder.append(" o.mobileid=? and");
				params.add(mobileid);
				view.addObject("mobileid", mobileid);

			}
			if (state != null) {
				builder.append(" o.state=? and");
				params.add(state);
				view.addObject("state", state);

			}
			
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			if (!StringUtil.isEmpty(builder.toString()))
				Tdnabind.findList(builder.toString(), " order by o.bindtime desc",
						params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tdnabind/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("tdnabind/list");
		return view;
	}
	
	@RequestMapping("/editUI")
	public ModelAndView editUI(@RequestParam("id") String id,
			ModelAndView view) {
		logger.info("tdnabind/editUI");
		view.addObject("tdnabind", Tdnabind.findTdnabind(id));
		view.setViewName("tdnabind/editUI");
		return view;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(
			@RequestParam("id") String id,
			@RequestParam("name") String name,
			@RequestParam("bankcardno") String bankcardno,
			@RequestParam("certid") String certid,
			@RequestParam("bankaddress") String bankaddress,
			@RequestParam("certidaddress") String certidaddress,
			@RequestParam("state") BigDecimal state,
			@RequestParam("bankname") String bankname,
			HttpServletRequest request,
			ModelAndView view) {
		logger.info("tdnabind/edit");
		String errormsg = "修改成功";
		try {
			if (StringUtil.isEmpty(id) || StringUtil.isEmpty(name) || StringUtil.isEmpty(bankcardno) ||
					StringUtil.isEmpty(certid)||StringUtil.isEmpty(bankaddress) || StringUtil.isEmpty(certidaddress) ) {
				view.addObject("errormsg", "不允许为空");
				view.setViewName("tdnabind/editUI");
				return view;
			}
			
			Tdnabind tdnabind = Tdnabind.findTdnabind(id);
			tdnabind.setName(name);
			tdnabind.setBankcardno(bankcardno);
			tdnabind.setCertid(certid);
			tdnabind.setBankaddress(bankaddress);
			tdnabind.setCertidaddress(certidaddress);
			tdnabind.setState(state);
			tdnabind.setBankname(bankname);
			tdnabind.merge();
			
		} catch (Exception e) {
			logger.error("tdnabind/edit error", e);
			view.addObject("errormsg", "修改失败");
			view.setViewName("tdnabind/editUI");
			return view;
		}
		view.addObject("errormsg",errormsg);
		return this.page(view);
	}
}
