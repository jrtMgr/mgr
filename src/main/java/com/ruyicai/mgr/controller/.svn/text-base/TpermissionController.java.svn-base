package com.ruyicai.mgr.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tloguser;
import com.ruyicai.mgr.domain.Tmenu;
import com.ruyicai.mgr.domain.Tpermission;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.PaySign;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/tpermissions")
@Controller
public class TpermissionController {

	private Logger logger = Logger.getLogger(TpermissionController.class);

	
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view) {
		logger.info("tpermissions/page");
		try {
			view.addObject("list", Tmenu.findAllTmenus());
		} catch (Exception e) {
			e.printStackTrace();
		}
		view.setViewName("tpermissions/list");
		return view;
	}
	
	
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "state", required = false) BigDecimal state,
			@RequestParam(value = "menuid", required = false) BigDecimal menuid,
			@ModelAttribute("page") Page<Tloguser> page, ModelAndView view) {
		logger.info("tpermissions/list");
		try {
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			if(!StringUtil.isEmpty(username)) {
				builder.append(" o.nickname=? and");
				params.add(username);
				view.addObject("username", username);
			}
			if (state != null) {
				builder.append(" o.state=? and");
				params.add(state);
				view.addObject("state", state);
			}
			if (menuid != null) {
				Tmenu tmenu = Tmenu.findTmenu(menuid);
				List<Tpermission> list =  Tpermission.findByInid(tmenu.getInid());
				builder.append(" o.id in (");
				if (list.size()>0) {
					for (int i = 0; i < list.size(); i++) {
						Tpermission p = list.get(i);
						builder.append(p.getUserid()).append(",");
					}
					if (builder.toString().endsWith(",")) {
						builder.delete(builder.length() - 1, builder.length());
					}
				}else{
					builder.append("-1");
				}
				builder.append(")");
			}
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			if (!StringUtil.isEmpty(builder.toString()))
				Tloguser.findList(builder.toString(), "",
						params, page);
			view.addObject("page", page);
			view.addObject("list", Tmenu.findAllTmenus());
		} catch (Exception e) {
			logger.error("tpermissions/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("tpermissions/list");
		return view;
	}
		
	@RequestMapping("/addUI")
	public ModelAndView addUI(ModelAndView view) {
		logger.info("tpermissions/addUI");
		view.setViewName("tpermissions/addUI");
		return view;
	}
	@RequestMapping("/add")
	public ModelAndView add(@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			ModelAndView view) {
		logger.info("tpermissions/add");
		String errormsg = "添加成功";
		try {
			if (StringUtil.isEmpty(username) || StringUtil.isEmpty(password)) {
				errormsg = "不允许为空";
				view.addObject("errormsg", errormsg);
				view.setViewName("tpermissions/addUI");
				return view;
			}
			Tloguser tloguser = new Tloguser();
			tloguser.setPwd(PaySign.EncoderByMd5(password));
			tloguser.setNickname(username);
			tloguser.setAgencyno(" ");
			tloguser.setPrestr(" ");
			tloguser.setState(BigDecimal.ONE);
			tloguser.persist();
		} catch (Exception e) {
			view.addObject("errormsg", e.getMessage());
			logger.error("tpermission/list error", e);
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("tpermissions/list");
		return view;
	}
	@RequestMapping("/editUI")
	public ModelAndView editUI(@RequestParam(value = "id") String id,
			ModelAndView view) {
		logger.info("tpermission/editUI");
		try {
			Tloguser.findTloguser(id);
			Tloguser tloguser = Tloguser.findTloguser(id);
			if (null == tloguser) {
				throw new RuntimeException("用户不存在");
			}
			
			List<Tmenu> tmenus = Tmenu.findAllTmenus();
			List<Tpermission> tpermissions = Tpermission.findByUseridAndState(tloguser.getId(), BigDecimal.ONE);
			view.addObject("tloguser", tloguser);
			view.addObject("permission", tpermissions);
			view.addObject("menulist", tmenus);
		} catch (Exception e) {
			view.addObject("errormsg", e.getMessage());
			logger.error("tpermission/editUI error", e);
		}
		view.setViewName("tpermissions/editUI");
		return view;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "userid") String userid,
			@RequestParam(value = "menuinid") String[] menuinid,
			ModelAndView view) {
		logger.info("tpermissions/edit");
		String errormsg = "保存成功";
		try {
			Tpermission.updateTpermission(userid);
			for (String inid : menuinid) {
				Tpermission tpermission = Tpermission.findByInidAndUserid(new BigDecimal(inid), userid);
				if (null != menuinid) {
					if (null == tpermission) {
						tpermission = new Tpermission();
						tpermission.setMenuinid(new BigDecimal(inid));
						tpermission.setUserid(userid);
						tpermission.setState(BigDecimal.ONE);
						tpermission.persist();
					} else {
						tpermission.setState(BigDecimal.ONE);
						tpermission.merge();
					}
				}
			}
		} catch (Exception e) {
			errormsg = "保存失败";
			logger.error("tpermissions/edit error", e);
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("tpermissions/list");
		return view;
	}

	
	@RequestMapping("/delete")
	public ModelAndView delete(@RequestParam(value = "id") String id,
			ModelAndView view) {
		logger.info("tpermissions/edit");
		String errormsg = "删除成功";
		try {
			Tloguser tloguser = Tloguser.findTloguser(id);
			tloguser.setState(BigDecimal.ZERO);
			tloguser.merge();
			
			List<Tpermission> tpermissions = Tpermission.findByUseridAndState(id, BigDecimal.ONE);
			for (Tpermission tpermission : tpermissions) {
				tpermission.setState(BigDecimal.ZERO);
				tpermission.merge();
			}
		} catch (Exception e) {
			errormsg = "删除失败";
			logger.error("tpermissions/edit error", e);
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("tpermissions/list");
		return view;
	}
}
