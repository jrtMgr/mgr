package com.ruyicai.mgr.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.controller.dto.UsercfgView;
import com.ruyicai.mgr.domain.statis.Channel;
import com.ruyicai.mgr.domain.statis.Role;
import com.ruyicai.mgr.domain.statis.User;
import com.ruyicai.mgr.domain.statis.UserCfg;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/suser")
@Controller
public class StatisticUserController {
	private Logger logger = Logger.getLogger(StatisticUserController.class);
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		logger.info("suser/page");
		try {
			view.addObject("roles", Role.findAllRoles());
		} catch (Exception e) {
			logger.error("suser/page error", e);
		}
		
		view.setViewName("suser/list");
		return view;
	}
	
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "roleid", required = false) Integer roleid,
			@ModelAttribute("page") Page<User> page, ModelAndView view) {
		logger.info("suser/list");
		try {
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			if(!StringUtil.isEmpty(name)) {
				builder.append(" o.name=? and");
				params.add(name);
			}
			if (roleid != null) {
				builder.append(" o.roleid=? and");
				params.add(roleid);
			}
			
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			User.findList(builder.toString(), " order by o.regdate desc", params, page);
			view.addObject("page", page);
			view.addObject("roles", Role.findAllRoles());
		} catch (Exception e) {
			logger.error("suser/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("suser/list");
		return view;
	}
	
	@RequestMapping("/editUI")
	public ModelAndView editUI(@RequestParam("id") Integer id,
			ModelAndView view){
		logger.info("suser/editUI");
		try {
			view.addObject("roles", Role.findAllRoles());
			view.addObject("user", User.findUser(id));
		} catch (Exception e) {
			logger.error("suser/editUI error", e);
		}
		view.setViewName("suser/editUI");
		return view;
	}
	
	@RequestMapping("/addUI")
	public ModelAndView addUI(ModelAndView view){
		logger.info("suser/addUI");
		try {
			view.addObject("roles", Role.findAllRoles());
		} catch (Exception e) {
			logger.error("suser/addUI error", e);
		}
		view.setViewName("suser/addUI");
		return view;
	}
	
	
	@RequestMapping("/edit")
	public ModelAndView edit(
			@RequestParam(value = "id") Integer id,
			@RequestParam(value = "roleid", required=false) Integer roleid,
			@RequestParam(value = "pass", required=false) String pass,
			@RequestParam(value = "realname", required=false) String realname,
			@RequestParam(value = "tel", required=false) String tel,
			@RequestParam(value = "bz", required=false) String bz,
			@RequestParam(value = "status", required=false) Integer status,
			ModelAndView view) {
		logger.info("suser/edit");
		String errormsg = "修改成功";
		try {
			User u = User.findUser(id);
			u.setRoleid(roleid);
			if (!StringUtil.isEmpty(pass)) {
				u.setPass(pass);
			}
			u.setRealname(realname);
			u.setTel(tel);
			u.setBz(bz);
			u.setStatus(status);
			u.merge();

		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("suser/edit error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	@RequestMapping("/add")
	public ModelAndView add(
			@RequestParam(value = "roleid") Integer roleid,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "pass") String pass,
			@RequestParam(value = "realname", required=false) String realname,
			@RequestParam(value = "tel", required=false) String tel,
			@RequestParam(value = "bz", required=false) String bz,
			ModelAndView view) {
		logger.info("suser/add");
		String errormsg = "新增成功";
		try {
			if (StringUtil.isEmpty(name) || StringUtil.isEmpty(pass) ) {
				view.addObject("errormsg", "用户名或密码允许为空");
				return this.addUI(view);
			}
			if (User.findUserByname(name).size() > 0) {
				view.addObject("errormsg", "用户名已经存在");
				return this.addUI(view);
			}
		
			User u = new User();
			u.setRoleid(roleid);
			u.setName(name);
			u.setPass(pass);
			u.setRealname(realname);
			u.setRegdate(new Date());
			u.setTel(tel);
			u.setBz(bz);
			u.setStatus(1);
			u.persist()	;

		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("suser/add error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	@RequestMapping("/usercfglist")
	public ModelAndView usercfglist(
			@RequestParam(value = "userid") Integer userid,
			ModelAndView view) {
		logger.info("suser/edit");
		try {
			List<UsercfgView> list = UserCfg.findUserCfgViewByUserid(userid);
			view.addObject("list", list);
		} catch (Exception e) {
			logger.error("suser/usercfglist error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("suser/usercfglist");
		return view;
	}
	
	@RequestMapping("/addusercfgUI")
	public ModelAndView addusercfgUI(ModelAndView view) {
		logger.info("suser/addusercfgUI");
		view.setViewName("suser/addusercfgUI");
		return view;
	}
	@RequestMapping("/addusercfg")
	public ModelAndView addusercfg(@RequestParam(value = "userid") Integer userid,
			@RequestParam(value = "channelid") Integer channelid,ModelAndView view) {
		logger.info("suser/addusercfg");
		String errormsg ="添加成功";
		try {
			Channel c = Channel.findChannel(channelid);
			if (c == null) {
				view.addObject("errormsg", "渠道id不存在请输入正确的渠道id");
				return this.usercfglist(userid, view);
			}
			UserCfg uc = new UserCfg();
			uc.setUserid(userid);
			uc.setChannelid(channelid);
			uc.persist();
		} catch (Exception e) {
			logger.error("suser/addusercfg error", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.usercfglist(userid, view);
	}
	
	@RequestMapping("/delUsercfg")
	public ModelAndView delUsercfg(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "userid") Integer userid,
			ModelAndView view) {
		logger.info("suser/addusercfg");
		String errormsg ="删除成功";
		UserCfg uc = null;
		try {
			uc = UserCfg.findUserCfg(id);
			uc.remove();
		} catch (Exception e) {
			logger.error("suser/delUsercfg error", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.usercfglist(userid, view);
	}
	
	@RequestMapping("/editUsercfg")
	public ModelAndView editUsercfg(@RequestParam(value = "id") Integer id,
			@RequestParam(value = "channelid") Integer channelid,
			ModelAndView view) {
		logger.info("suser/editUsercfg");
		String errormsg ="编辑成功";
		UserCfg uc = null;
		try {
			uc = UserCfg.findUserCfg(id);
			Channel c = Channel.findChannel(channelid);
			if (c == null) {
				view.addObject("errormsg", "渠道id不存在请输入正确的渠道id");
				return this.usercfglist(uc.getUserid(), view);
			}
			uc.setChannelid(channelid);
			uc.merge();
		} catch (Exception e) {
			logger.error("suser/editUsercfg error", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.usercfglist(uc.getUserid(), view);
	}
	
	
	@RequestMapping("/editUsercfgUI")
	public ModelAndView editUsercfgUI(@RequestParam("id") Integer id,
			ModelAndView view){
		logger.info("suser/editUsercfgUI");
		try {
			view.addObject("usercfg", UserCfg.findUserCfg(id));
		} catch (Exception e) {
			logger.error("suser/editUsercfgUI error", e);
		}
		view.setViewName("suser/editUsercfgUI");
		return view;
	}
}
