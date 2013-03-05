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

import com.ruyicai.mgr.domain.Tlot;
import com.ruyicai.mgr.domain.Ttransaction;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.domain.statis.Channel;
import com.ruyicai.mgr.domain.statis.PvTJ;
import com.ruyicai.mgr.domain.statis.TCooperat;
import com.ruyicai.mgr.domain.statis.User;
import com.ruyicai.mgr.domain.statis.UserCfg;
import com.ruyicai.mgr.domain.statis.Yw;
import com.ruyicai.mgr.util.DateUtil;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/channel")
@Controller
public class ChannelController {

	private Logger logger = Logger.getLogger(ChannelController.class);
	
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		try {
			view.addObject("ywlist", Yw.findAllYws());
		} catch (Exception e) {
			logger.error("channel/page error", e);
		}
		view.setViewName("channel/list");
		return view;
	}
	
	
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "ywid", required = false) Integer ywid,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "username", required = false) String username,
			@ModelAttribute("page") Page<Channel> page , ModelAndView view) {
		logger.info("/channel/list");
		try {
			StringBuilder builder = new StringBuilder("where o.status = 1 and");
			List<Object> params = new ArrayList<Object>();
			if (!StringUtil.isEmpty(username)) {
				List<User> l = User.findUserByname(username);
				if (User.findUserByname(username).size() == 0) {
					throw new RuntimeException("用户不存在");
				}
				User u = l.get(0);
				List<UserCfg> usercfgs = UserCfg.findUserCfgByUserid(u.getId());
				if (usercfgs.size()>0) {
					builder.append(" o.id in (");
					for (int i = 0; i < usercfgs.size(); i++) {
						if(i==0){
							builder.append(usercfgs.get(i).getChannelid());
						}else{
							builder.append(","+usercfgs.get(i).getChannelid());
						}
					}
					builder.append(") and");
				}
			}
			if(ywid != null) {
				builder.append(" o.ywid =? and");
				params.add(ywid);
			}
			if(id != null) {
				builder.append(" o.id =? and");
				params.add(id);
			}
			if(!StringUtil.isEmpty(name)) {
				builder.append(" o.name like '%").append(name).append("%'");
			}
			
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			Channel.findList(builder.toString(), " order by o.cjdate desc", params, page);
			view.addObject("page", page);
			view.addObject("ywlist", Yw.findAllYws());
		} catch (Exception e) {
			logger.error("channel/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		
		view.setViewName("channel/list");
		return view;
	}
	@RequestMapping("/deletechannel")
	public ModelAndView deletechannel(@RequestParam(value = "id") int id, 
			ModelAndView view) {
		logger.info("/channel/editUI");
		String errormsg = "删除成功";
		try {
			Channel c = Channel.findChannel(id);
			c.setStatus(0);
			c.merge();
			
		} catch (Exception e) {
			logger.error("channel/editUI error", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
	@RequestMapping("/editUI")
	public ModelAndView editUI(@RequestParam(value = "id") int id, ModelAndView view) {
		logger.info("/channel/editUI");
		try {
			view.addObject("ywlist", Yw.findAllYws());
			view.addObject("model", Channel.findChannel(id));
			view.addObject("cooperat", TCooperat.findTcooperatByChannlId(id+""));
		} catch (Exception e) {
			logger.error("channel/editUI error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("channel/findchannel");
		return view;
	}
	
	@RequestMapping("/updatechannel")
	public ModelAndView updatechannel(@RequestParam(value = "id") int id, 
			@RequestParam(value = "ywid") int ywid, 
			@RequestParam(value = "name") String name,
			@RequestParam(value = "url") String url,
			@RequestParam(value = "tel") String tel,
			@RequestParam(value = "bz") String bz,
			@RequestParam(value = "cooperat_type") String cooperat_type,
			@RequestParam(value = "count0") Double count0,
			@RequestParam(value = "count_type0") String count_type0,
			@RequestParam(value = "count1", required = false ) Double count1,
			@RequestParam(value = "count_type1", required = false ) String count_type1,
			
			@RequestParam(value = "isopen", required = false) Integer isopen,
			@RequestParam(value = "regist", required = false) Double regist,
			@RequestParam(value = "charge", required = false) Double charge,
			ModelAndView view) {
		logger.info("/channel/updatechannel");
		String errormsg = "更新成功";
		try {
			Channel c = Channel.findChannel(id);
			if (c != null) {
				c.setYwid(ywid);
				c.setName(name);
				c.setUrl(url);
				c.setTel(tel);
				c.setBz(bz);
				c.setIsopen(isopen);
				c.setRegist(regist);
				c.setCharge(charge);
				c.merge();
				String channelid = id+"";
				List<TCooperat> l = TCooperat.findTcooperatByChannlId(channelid);
				for (TCooperat tCooperat : l) {
					tCooperat.remove();
				}
				if (cooperat_type.equals("CPA+CPS")) {
					TCooperat co = new TCooperat();
					co.setCooperatType("CPA");
					co.setChannlId(channelid);
					co.setCount(count0);
					co.setCountType(count_type0);
					co.persist();
					
					TCooperat co1 = new TCooperat();
					co1.setCooperatType("CPS");
					co1.setChannlId(channelid);
					co1.setCount(count1);
					co1.setCountType(count_type1);
					co1.persist();
				} else {
					TCooperat co1 = new TCooperat();
					co1.setCooperatType(cooperat_type);
					co1.setChannlId(channelid);
					co1.setCount(count0);
					co1.setCountType(count_type0);
					co1.persist();
				}
			}
		} catch (Exception e) {
			logger.error("channel/findchannel error", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.editUI(id, view);
	}
	
	@RequestMapping("/channelDetailUI")
	public ModelAndView channelDetail(@RequestParam(value = "id") Integer id,ModelAndView view){
		view.setViewName("channel/channeldetail");
		return view;
	}
	
	@RequestMapping("/channelDetaildo")
	public ModelAndView channelDetail(@RequestParam(value = "id") Integer id, 
			@RequestParam(value = "starttime") String starttime,
			@RequestParam(value = "endtime") String endtime,
			ModelAndView view) {
		String errormsg = "";
		if (id == null || StringUtil.isEmpty(starttime) || StringUtil.isEmpty(endtime)) {
			view.addObject("errormsg", "不允许为空");
			view.setViewName("channel/channeldetail");
			return view;
		}
		StringBuilder builder = new StringBuilder(
				"where to_char(o.tjdate, 'yyyy-mm-dd') >= ? and to_char(o.tjdate, 'yyyy-mm-dd') <= ? and o.channelid = ?");
		try {
			List<Object> params = new ArrayList<Object>();
			params.add(starttime);
			params.add(endtime);
			params.add(id.toString());
			
			//获取注册用户数
			long regnum = PvTJ.getRegnum(builder.toString(), params);
			
			//注册当天充值的用户数
			long regpaynum = PvTJ.getRegPaynum(builder.toString(), params);
			//获取充值总金额
			long paymoney = PvTJ.getPaymoney(builder.toString(), params).longValue();
			
			//获取购彩总金额
			long buymoney = PvTJ.getBuymoney(builder.toString(), params).longValue();
			
			//访问数
			long visitnum = PvTJ.getVisitnum(builder.toString(),params);
			
			Double balance = new Double(0.00);
			Double cpaBlance = new Double(0.00);//CPA的结算金额
			Double cpcBlance = new Double(0.00);//CPc的结算金额
			Double cpsBlance = new Double(0.00);//CPS的结算金额
			Double rivetBlance = new Double(0.00);//固定金额的结算金额
			List<TCooperat> list = TCooperat.findTcooperatByChannlId(id.toString());
			for (TCooperat cooperat : list) {
				if (cooperat.getCooperatType().equals("CPA")) {
					// 如果是CPA regnum*count 注册用户数*单个金额
					cpaBlance = regnum * cooperat.getCount();
				}
				if (cooperat.getCooperatType().equals("CPC")) {
					// 如果是cpc visitnum* count 访问量*金额
					cpcBlance = visitnum * cooperat.getCount();
				}
				if (cooperat.getCooperatType().equals("CPS")) {
					// 如果是cps buymoney*count/100 购彩金额*金额 除以100
					cpsBlance = buymoney * cooperat.getCount() / 100;
				}
				if (cooperat.getCooperatType().equals("RIVET")) {
				    //固定金额的规则 月份数
					rivetBlance = (cooperat.getCount()*(DateUtil.dispersionMonth2(starttime, endtime)==0?1:DateUtil.dispersionMonth2(starttime, endtime)));
				}
			}
			balance = cpaBlance + cpcBlance + cpsBlance + rivetBlance;
			
			
			view.addObject("regnum", regnum);
			view.addObject("regpaynum", regpaynum);
			view.addObject("paymoney", paymoney);
			view.addObject("buymoney", buymoney);
			view.addObject("visitnum", visitnum);
			view.addObject("balance", balance);
		} catch (Exception e) {
			logger.error("channel/channelDetail error", e);
			errormsg = e.getMessage();
		}
		
		view.addObject("errormsg", errormsg);
		view.setViewName("channel/channeldetail");
		return view;
	}
	@RequestMapping("/originalDetailUI")
	public ModelAndView originalDetailUI (@RequestParam(value = "channelid") String channelid,
			ModelAndView view) {
		view.setViewName("channel/originalDetail");
		return view;
	}
	@RequestMapping("/originalDetail")
	public ModelAndView originalDetail (
			@RequestParam(value = "channelid") String channel,
			@RequestParam(value = "starttime") String starttime,
			@RequestParam(value = "endtime") String endtime, ModelAndView view) {
		logger.info("channle/originalDetail");
		if (StringUtil.isEmpty(starttime) ||  StringUtil.isEmpty(endtime)) {
			view.addObject("errormsg", "时间不允许为空");
			view.setViewName("channel/originalDetail");
			return view;
		}
		try {
			channel = "'"+channel+"'";
			//注册
			view.addObject("CountRegistUser", Tuserinfo.countRegistUser(channel, starttime, endtime));
			//充值人数
			view.addObject("CountCharge",Ttransaction.countCharge(channel, starttime, endtime));
			//充值总金额
			view.addObject("SumCharge",Ttransaction.sumCharge(channel, starttime, endtime));
			Object[] o = Tlot.countBuy(channel, starttime, endtime).get(0);
			//购彩总金额
			view.addObject("BET_COUNT_MONEY", o[0]);
			//购彩总人数
			view.addObject("BET_COUNT_USER", o[1]);
			
			Object[] o2 = Ttransaction.getSumNewUser(channel, starttime, endtime).get(0);
			// 新用户充值金额
			view.addObject("SumNewCharge", o2[0]);
			//新用户充值人数
			view.addObject("SumNewChargeUser", o2[1]);
			
			Object[] o3 = Tlot.getSumNewUserLot(channel, starttime, endtime).get(0);
			//新用户购彩金额
			view.addObject("SumNewLot", o3[0]);
			//新用户购彩人数
			view.addObject("SumNewLotUser", o3[1]);
			
			//中奖总金额
			view.addObject("SumWin", Tlot.getSumWin(channel, starttime, endtime));
			
		} catch (Exception e) {
			logger.error("channel/originalDetail error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("channel/originalDetail");
		return view;
	}
	
	
	
	@RequestMapping("/addUI")
	public ModelAndView addUI(ModelAndView view){
		logger.info("channel/addUI");
		try {
			view.addObject("ywlist", Yw.findAllYws());
		} catch (Exception e) {
			logger.error("channel/addUI error", e);
		}
		view.setViewName("channel/addUI");
		return view;
	}
	
	
	@RequestMapping("/add")
	public ModelAndView add(@RequestParam(value = "ywid") Integer ywid, 
			@RequestParam(value = "name") String name,
			@RequestParam(value = "url") String url,
			@RequestParam(value = "tel") String tel,
			@RequestParam(value = "bz") String bz,
			ModelAndView view) {
		logger.info("channel/add");
		String errormsg = "新增成功";
		try {
			if (StringUtil.isEmpty(name) ) {
				view.addObject("errormsg", "用户名不允许为空");
				return this.addUI(view);
			}
			Channel c = new Channel();
			c.setId(Channel.findId()+1);
			c.setYwid(ywid);
			c.setCode(" ");
			c.setName(name);
			c.setUrl(url);
			c.setTel(tel);
			c.setBz(bz);
			c.setCjdate(new Date());
			c.setStatus(1);
			c.setBduserid(0);
			c.setIsopen(0);
			c.setRegist(0.5);
			c.setCharge(0.5);
			c.persist();

		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("suser/add error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.page(view);
	}
	
}
