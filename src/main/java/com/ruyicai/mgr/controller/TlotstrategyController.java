package com.ruyicai.mgr.controller;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tlotencashstrategy;
import com.ruyicai.mgr.domain.Tlotstrategy;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/tlotstrategy")
@Controller
public class TlotstrategyController {
	private Logger logger = Logger.getLogger(TlotstrategyController.class);
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		logger.info("tlotstrategy/page");
		try {
			view.addObject("list", Tlotstrategy.findAllTlotstrategysByState());
			view.addObject("elist", Tlotencashstrategy.findAllByState());
		} catch (Exception e) {
			logger.error("tlotstrategy/list", e);
		}
		view.setViewName("tlotstrategy/list");
		return view;
	}
	
	@RequestMapping("/hnfcfp")
	public ModelAndView hnfcfp(ModelAndView view){
		logger.info("tlotstrategy/hnfcfp");
		try {
			view.addObject("tlotstrategy", Tlotstrategy.findTlotstrategysById());
		} catch (Exception e) {
			logger.error("tlotstrategy/hnfcfp", e);
		}
		view.setViewName("tlotstrategy/hnfcfp");
		return view;
	}
	@RequestMapping("/hnfcfpqy")
	public ModelAndView hnfcfpqy(ModelAndView view){
		logger.info("tlotstrategy/hnfcfp");
		try {
			Tlotstrategy t = Tlotstrategy.findTlotstrategysById();
			t.setState(1);
			t.merge();
			this.reinit();
		} catch (Exception e) {
			logger.error("tlotstrategy/hnfcfpqy", e);
		}
		return this.hnfcfp(view);
	}
	@RequestMapping("/hnfcfpsc")
	public ModelAndView hnfcfpsc(ModelAndView view){
		logger.info("tlotstrategy/hnfcfp");
		try {
			Tlotstrategy t = Tlotstrategy.findTlotstrategysById();
			t.setState(0);
			t.merge();
			this.reinit();
		} catch (Exception e) {
			logger.error("tlotstrategy/hnfcfpsc", e);
		}
		return this.hnfcfp(view);
	}
	@RequestMapping("/hnfcfpamt")
	public ModelAndView hnfcfpamt(@RequestParam(value = "amt") BigDecimal amt,ModelAndView view){
		logger.info("tlotstrategy/hnfcfpamt");
		String errormsg = "修改成功";
		try {
			Tlotstrategy t =Tlotstrategy.findTlotstrategysById();
			if(t.getState() == 1){
				t.setAmt(amt);
				t.merge();
				this.reinit();
			}else{
				errormsg = "状态不是可用不能修改"; 
			}
		} catch (Exception e) {
			logger.error("tlotstrategy/hnfcfpamt", e);
		}
		view.addObject("errormsg", errormsg);
		return this.hnfcfp(view);
	}
	
	@RequestMapping("/addUI")
	public String addUI(){
		return "tlotstrategy/addUI";
	}
	@RequestMapping("/addeUI")
	public String addeUI(){
		return "tlotstrategy/addeUI";
	}
	
	@RequestMapping("/reinit")
	public @ResponseBody
	ResponseData reinit() {
		ResponseData rd = new ResponseData();
		ErrorCode result = ErrorCode.OK;
		try {
			String url = propertiesUtil.getLotteryurl()+"/system/reinit";
			logger.info("调用的url:"+url);
			rd = ResponseData.fromJsonToResponseData(HttpUtil.post(url,""));
		} catch (Exception e) {
			logger.error("tlotstrategy/reinit", e);
			result = ErrorCode.ERROR;
		} 
		rd.setErrorCode(result.value);
		return rd;
	}
	@RequestMapping("/add")
	public ModelAndView add(@RequestParam(value = "userno") String userno,
			@RequestParam(value = "channel") String channel,
			@RequestParam(value = "lotno") String lotno,
			@RequestParam(value = "agencyno") String agencyno,
			@RequestParam(value = "playtype") String playtype,
			@RequestParam(value = "amt") BigDecimal amt,
			ModelAndView view){
		logger.info("tlotstrategy/add");
		String errormsg = "添加出票策略成功";
		if(!StringUtil.isEmpty(userno) && !StringUtil.isEmpty(channel)){
			view.addObject("errormsg", "userno,channel不允许同时填");
			view.setViewName("tlotstrategy/addUI");
			return view;
		}
		if(StringUtil.isEmpty(userno) && StringUtil.isEmpty(channel)
				&& StringUtil.isEmpty(lotno)){
			view.addObject("errormsg", "userno,channel,lotno必须填一个");
			view.setViewName("tlotstrategy/addUI");
			return view;
		}
		if(!StringUtil.isEmpty(playtype)){
			if(StringUtil.isEmpty(lotno)){
				view.addObject("errormsg", "没有lotno不能有playtype");
				view.setViewName("tlotstrategy/addUI");
				return view;
			}
		
		}
		if(StringUtil.isEmpty(agencyno)){
			view.addObject("errormsg", "agencyno不允许为空");
			view.setViewName("tlotstrategy/addUI");
			return view;
		}
		try {
			Tlotstrategy ts = new Tlotstrategy();
			ts.setUserno(userno);
			ts.setChannel(channel);
			ts.setLotno(lotno);
			ts.setAgencyno(agencyno);
			ts.setState(1);
			ts.setPlaytype(playtype);
			ts.setAmt(amt);
			ts.persist();
		} catch (Exception e) {
			logger.error("tlotstrategy/add", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	@RequestMapping("/adde")
	public ModelAndView adde(@RequestParam(value = "userno") String userno,
			@RequestParam(value = "channel") String channel,
			@RequestParam(value = "lotno") String lotno,
			@RequestParam(value = "amt") Integer amt,
			ModelAndView view){
		logger.info("tlotstrategy/adde");
		String errormsg = "添加兑奖策略成功";
		if(!StringUtil.isEmpty(userno) && !StringUtil.isEmpty(channel)){
			view.addObject("errormsg", "userno,channel不允许同时填");
			view.setViewName("tlotstrategy/addeUI");
			return view;
		}
		if(StringUtil.isEmpty(userno) && StringUtil.isEmpty(channel)
				&& StringUtil.isEmpty(lotno)){
			view.addObject("errormsg", "userno,channel,lotno必须填一个");
			view.setViewName("tlotstrategy/addeUI");
			return view;
		}
		if(amt == null){
			view.addObject("errormsg", "amt不允许为空");
			view.setViewName("tlotstrategy/addeUI");
			return view;
		}
		try {
			Tlotencashstrategy ts = new Tlotencashstrategy();
			ts.setUserno(userno);
			ts.setChannel(channel);
			ts.setLotno(lotno);
			ts.setState(1);
			ts.setAmt(amt);
			ts.persist();
		} catch (Exception e) {
			logger.error("tlotstrategy/adde", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	
	@Autowired
	PropertiesUtil propertiesUtil;
	@RequestMapping("/deleteTs")
	public ModelAndView deleteTs(@RequestParam(value="id") Long id,
			ModelAndView view){
		String errormsg = "删除出票成功";
		try {
			Tlotstrategy ts = Tlotstrategy.findTlotstrategy(id);
			
			int type = 0;
			String value = "";
			if (ts.getUserno()!=null) {
				type = 1;
				value = ts.getUserno();
			}else if(ts.getChannel()!=null){
				type = 2;
				value = ts.getChannel();
			}else if(ts.getLotno()!=null){
				type = 3;
				value = ts.getLotno();
			}
			StringBuilder param = new StringBuilder();
			param.append("type=").append(type).append("&value=").append(value);
			String url = propertiesUtil.getLotteryurl() + "/system/deletetlotstrategy";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))){
				errormsg = "调用lottery后台出错,返回:"+result;
			}else{
				ts.setState(0);
				ts.merge();
			}
		} catch (Exception e) {
			logger.error("tlotstrategy/deleteTs", e);
			errormsg = e.getMessage();
		}
		view.addObject(errormsg, errormsg);
		return this.page(view);
	}
	
	
	@RequestMapping("/deleteTe")
	public ModelAndView deleteTe(@RequestParam(value="id") Long id,
			ModelAndView view){
		String errormsg = "删除兑奖策略成功";
		try {
			Tlotencashstrategy ts = Tlotencashstrategy.findTlotencashstrategy(id);
			
			int type = 0;
			String value = "";
			if (ts.getUserno()!=null) {
				type = 1;
				value = ts.getUserno();
			}else if(ts.getChannel()!=null){
				type = 2;
				value = ts.getChannel();
			}else if(ts.getLotno()!=null){
				type = 3;
				value = ts.getLotno();
			}
			StringBuilder param = new StringBuilder();
			param.append("type=").append(type).append("&value=").append(value);
			String url = propertiesUtil.getLotteryurl() + "/system/deletetlotencashstrategy";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))){
				errormsg = "调用lottery后台出错,返回:"+result;
			}else{
				ts.setState(0);
				ts.merge();
			}
		} catch (Exception e) {
			e.printStackTrace();
			errormsg = e.getMessage();
		}
		view.addObject(errormsg, errormsg);
		return this.page(view);
	}
}
