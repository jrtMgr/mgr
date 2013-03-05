package com.ruyicai.mgr.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tloguser;
import com.ruyicai.mgr.domain.Tmenu;
import com.ruyicai.mgr.domain.Tpermission;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.PaySign;

@Controller
public class IndexController {
	private Logger logger = Logger.getLogger(IndexController.class);
	
	@RequestMapping(value = "/frame/{id}")
	public String header(@PathVariable("id") String id) {
		return id;
	}

	@RequestMapping(value = "/login")
	public @ResponseBody
	ResponseData login(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {
		ErrorCode errorCode = ErrorCode.OK;
		ResponseData data = new ResponseData();
		try {
			List<Tloguser> tlogusers = Tloguser.findTlogusersByNickname(
					username).getResultList();
			if (tlogusers.isEmpty()) {
				throw new RuntimeException();
			}
			Tloguser tloguser = tlogusers.get(0);
			if (!tloguser.getState().equals(BigDecimal.ONE)) {
				throw new RuntimeException();
			}
			if (!tloguser.getPwd().equals(PaySign.EncoderByMd5(password))) {
				throw new RuntimeException();
			}
			session.setAttribute("user", tloguser);
			session.setAttribute("permission", Tpermission
					.findByUseridAndState(tloguser.getId(), BigDecimal.ONE));
			session.setAttribute("usermenu", Tmenu.findByUserid(tloguser.getId()));
		} catch (Exception e) {
			logger.error("login error", e);
			errorCode = ErrorCode.ERROR;
			data.setValue("用户名或密码错误");
		}
		data.setErrorCode(errorCode.value);
		return data;
	}

	@RequestMapping("/logout")
	public @ResponseBody
	ResponseData logout(HttpSession session) {
		session.removeAttribute("user");
		session.removeAttribute("permission");
		session.removeAttribute("menu");
		session.invalidate();
		ResponseData data = new ResponseData();
		data.setErrorCode(ErrorCode.OK.value);
		return data;
	}

	@RequestMapping("/changepassword")
	public ModelAndView changepassword(
			@RequestParam("oldpassowrd") String oldpassowrd,
			@RequestParam("newpassowrd") String newpassowrd,
			@RequestParam("confirmpassowrd") String confirmpassowrd,
			HttpSession session, ModelAndView view) {
		String errormsg = "修改成功";
		try {
			Tloguser tloguser = (Tloguser) session.getAttribute("user");
			if (null == oldpassowrd || !tloguser.getPwd().equals(PaySign.EncoderByMd5(oldpassowrd))) {
				throw new RuntimeException("旧密码错误");
			}
			if (null == newpassowrd || !newpassowrd.equals(confirmpassowrd)) {
				throw new RuntimeException("两次密码不一致辞");
			}
			tloguser = Tloguser.findTloguser(tloguser.getId());
			tloguser.setPwd(PaySign.EncoderByMd5(newpassowrd));
			tloguser.merge();
			session.setAttribute("user", tloguser);
		} catch (Exception e) {
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("changepassword");
		return view;
	}
}
