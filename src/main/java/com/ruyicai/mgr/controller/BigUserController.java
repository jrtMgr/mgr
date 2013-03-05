package com.ruyicai.mgr.controller;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Iptables;
import com.ruyicai.mgr.domain.Taccount;
import com.ruyicai.mgr.domain.Tlogic;
import com.ruyicai.mgr.domain.Tsubchannel;
import com.ruyicai.mgr.domain.Ttransaction;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/biguser")
@Controller
public class BigUserController {
	private Logger logger = Logger.getLogger(BigUserController.class);
	@RequestMapping("/list")
	public ModelAndView list(ModelAndView view){
		logger.info("biguser/list");
		try {
			view.addObject("list", Tsubchannel.findAllTsubchannels1());
		} catch (Exception e) {
			logger.error("biguser/list error:", e);
		}
		view.setViewName("biguser/list");
		return view;
	}
	
	@RequestMapping("/recharge")
	public ModelAndView reCharge(@RequestParam("agencyno") String agencyno,
			ModelAndView view) {
		Tsubchannel tsubchannel = Tsubchannel.findTsubchannel(agencyno);
		Taccount taccount = Taccount.findTaccount(agencyno);
		BigDecimal balance = taccount.getBalance();
		view.addObject("agencyno", agencyno);
		view.addObject("agencyname", tsubchannel.getAgencyname());
		view.addObject("balance", balance.divide(new BigDecimal(100)));
		view.setViewName("biguser/recharge");
		return view;
	}
	
	
	@RequestMapping("/processCharge")
	public ModelAndView processCharge(@RequestParam("agencyno") String agencyno,
			@RequestParam("money") BigDecimal money,
			ModelAndView view) {
		money = money.multiply(new BigDecimal(100));
		String errormsg = "添加成功等待财务审核";
		Tsubchannel t = Tsubchannel.findTsubchannel(agencyno);
		if (t == null) {
			errormsg = "渠道记录不存在";
		} else if (t.getState().intValue() != 1) {
			errormsg = "渠道状态非法";
		}else{
			try {
				Ttransaction transaction = new Ttransaction();
				transaction.setType(new BigDecimal(9));// 交易类型
				transaction.setUserno(agencyno);// 客户编号，赠送者
				transaction.setAcceptno(" ");// 客户编号，被赠送者，发送赠送行为时填
				transaction.setFlowno(" ");// 兑奖交易流水号
				transaction.setPlattime(new Date());// 交易时间
				transaction.setAmt(money);// 交易金额
				transaction.setFee(new BigDecimal(0));// 费率为空
				transaction.setBankid(" ");// 银行标识，投注为金软通标识
				transaction.setBankorderid(" ");// 银行订单号，充值时填写银行端得返回信息
				transaction.setBanktrace(" ");// 银行返回流水
				transaction.setState(new BigDecimal(0));// 支付状态，0：未完成，1：成功，2：失败
				transaction.setRetcode(" ");// 银行返回码
				transaction.setRetmemo(" ");// 银行返回信息描述
				transaction.setMemo("渠道账户充值");// 备注
				transaction.setBankcheck(new BigDecimal(0));// 对账标记，0：未对账，1：已对账
				
				transaction.setBankaccount(" ");
				transaction.setBankordertime(new Date());
				transaction.setPaytype(" ");
				transaction.setAccesstype('N');
				transaction.setBankrettime(new Date());
				transaction.persist();
			} catch (Exception e) {
				errormsg = e.getMessage();
				logger.error("biguser/processCharge error:", e);
			}
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("biguser/recharge");
		return view;
	}
	
	@RequestMapping("/addUI")
	public ModelAndView addUI(ModelAndView view) {
		
		view.setViewName("biguser/addUI");
		return view;
	}
	
	@RequestMapping("/add")
	public ModelAndView add(
			@RequestParam("agencyname") String agencyname,
			@RequestParam(value = "telephone", required = false) String telephone,
			@RequestParam(value = "privatekey") String privatekey,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "notifyurl", required = false) String notifyurl,
			@RequestParam(value = "pubvatekey", required = false) String pubvatekey,
			@RequestParam(value = "protocol", required = false) String protocol,
			@RequestParam(value = "siyao", required = false) String siyao,
			@RequestParam(value = "channelid", required = false) String channel,
			@RequestParam(value = "deduct", required = false) BigDecimal deduct,
			ModelAndView view) {
		String errormsg = "添加成功";
		try {
			if (StringUtil.isEmpty(agencyname)) {
				view.addObject("errormsg", "合作商名称不允许为空");
				view.setViewName("biguser/addUI");
				return view;
			}else if(StringUtil.isEmpty(privatekey)){
				view.addObject("errormsg", "密钥不允许为空");
				view.setViewName("biguser/addUI");
				return view;
			}
			add(agencyname, telephone, privatekey, address, notifyurl,
					pubvatekey, protocol, siyao, channel, deduct);
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("biguser/add error:", e);
		}
		
		view.addObject("errormsg", errormsg);
		view.setViewName("biguser/addUI");
		return view;
	}
	@Transactional
	public void add(String agencyname, String telephone, String privatekey,
			String address, String notifyurl, String pubvatekey,
			String protocol, String siyao, String channel, BigDecimal deduct) {
		Tuserinfo tuserinfo=new Tuserinfo();
		tuserinfo.setState(BigDecimal.ONE);
		tuserinfo.setName(agencyname);
		tuserinfo.setUserName(agencyname);
		tuserinfo.setAgencyno("000001");
		tuserinfo.setRegtime(new Date());
		tuserinfo.persist();
		
		tuserinfo.setMobileid("ryc"+tuserinfo.getUserno());
		tuserinfo.setPassword("ryc"+tuserinfo.getUserno());
		tuserinfo.setChannel(channel);
		tuserinfo.setDeduct(deduct);
		tuserinfo.merge();
		
		Tsubchannel tsubchannel = new Tsubchannel();
		tsubchannel.setAgencyno(tuserinfo.getUserno());
		tsubchannel.setAgencyname(agencyname);
		tsubchannel.setTelephone(telephone);
		tsubchannel.setPrivatekey(privatekey);
		tsubchannel.setAddress(address);
		tsubchannel.setRegtime(new Date());
		tsubchannel.setState(BigDecimal.ONE);
		tsubchannel.setNotifyurl(notifyurl);
		tsubchannel.setProtocol(protocol);
		tsubchannel.setPubvatekey(pubvatekey);
		tsubchannel.setSiyao(siyao);
		tsubchannel.setMac(" ");
		tsubchannel.persist();
		
		Taccount taccount=new Taccount();
		taccount.setUserno(tsubchannel.getAgencyno());
		taccount.persist();
	}
	
	@RequestMapping("/deletesub")
	public ModelAndView deletesub(@RequestParam("agencyno") String agencyno,
			ModelAndView view) {
		String errormsg = "停用成功";
		try {
			delete(agencyno);
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("biguser/deletesub error:", e);
		}
		view.addObject("errormsg", errormsg);
		return this.list(view);
	}

	@Transactional
	private void delete(String agencyno) {
		Tsubchannel tsubchannel = Tsubchannel.findTsubchannel(agencyno);
		tsubchannel.setState(BigDecimal.ZERO);
		tsubchannel.merge();
		
		Tuserinfo  tuserinfo = Tuserinfo.findTuserinfo(agencyno);
		tuserinfo.setState(BigDecimal.ZERO);
		tuserinfo.merge();
	}
	
	@RequestMapping("/editUI")
	public ModelAndView editUI(@RequestParam("agencyno") String agencyno,
			ModelAndView view) {
		try {
			view.addObject("subchannel", Tsubchannel.findTsubchannel(agencyno));
		} catch (Exception e) {
			logger.error("biguser/editUI error:", e);
		}
		view.setViewName("biguser/editUI");
		return view;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam("agencyno") String agencyno,
			@RequestParam("agencyname") String agencyname,
			@RequestParam(value = "telephone", required = false) String telephone,
			@RequestParam(value = "privatekey") String privatekey,
			@RequestParam(value = "address", required = false) String address,
			@RequestParam(value = "notifyurl", required = false) String notifyurl,
			@RequestParam(value = "pubvatekey", required = false) String pubvatekey,
			@RequestParam(value = "protocol", required = false) String protocol,
			@RequestParam(value = "siyao", required = false) String siyao,
			ModelAndView view) {
		String errormsg = "修改成功";
		try {
			Tsubchannel tsubchannel = Tsubchannel.findTsubchannel(agencyno);
			tsubchannel.setAgencyname(agencyname);
			tsubchannel.setTelephone(telephone);
			tsubchannel.setPrivatekey(privatekey);
			tsubchannel.setAddress(address);
			tsubchannel.setNotifyurl(notifyurl);
			tsubchannel.setPubvatekey(pubvatekey);
			tsubchannel.setProtocol(protocol);
			tsubchannel.setSiyao(siyao);
			tsubchannel.merge();
		} catch (Exception e) {
			logger.error("biguser/edit error:", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("biguser/editUI");
		return view;
	}
	
	@RequestMapping("/getIps")
	public ModelAndView getIps(@RequestParam("agencyno") String agencyno, ModelAndView view){
		logger.info("biguser/getIps");
		try {
			view.addObject("ips", Iptables.findIptablesByAgencyno(agencyno));
		} catch (Exception e) {
			logger.error("biguser/getIps error:", e);
		}
		view.setViewName("biguser/iplist");
		return view;
	}
	
	@RequestMapping("/deleteip")
	public ModelAndView deleteip(@RequestParam("ipid") String ipid,
			ModelAndView view) {
		String errormsg = "停用成功";
		Iptables iptables = null;
		try {
			iptables =Iptables.findIptables(ipid);
			//iptables.setState(Short.valueOf("0"));
			//iptables.merge();
			iptables.remove();
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("biguser/deleteip error:", e);
		}
		view.addObject("errormsg", errormsg);
		return this.getIps(iptables.getAgencyno(), view);
	}
	
	@RequestMapping("/editip")
	public ModelAndView editip(@RequestParam("ipid") String ipid,
			@RequestParam("ipaddr") String ipaddr,
			ModelAndView view) {
		String errormsg = "修改成功";
		Iptables iptables = null;
		try {
			iptables = Iptables.findIptables(ipid);
			iptables.setIpaddr(ipaddr);
			iptables.merge();
		} catch (Exception e) {
			logger.error("biguser/editip error:", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		
		return this.getIps(iptables.getAgencyno(), view);
	}
	@RequestMapping("/addipUI")
	public ModelAndView addipUI(@RequestParam("agencyno") String agencyno,
			ModelAndView view) {
		view.setViewName("biguser/addipUI");
		return view;
	}
	@RequestMapping("/addip")
	public ModelAndView addip(
			@RequestParam("ipaddr") String ipaddr,
			@RequestParam("agencyno") String agencyno,
			ModelAndView view) {
		String errormsg = "添加成功";
		try {
			if (StringUtil.isEmpty(ipaddr) || StringUtil.isEmpty(agencyno)) {
				view.addObject("errormsg", "ipaddr,agencyno不允许为空");
				view.setViewName("biguser/addipUI");
				return view;
			}
			Iptables iptables = new Iptables();
			iptables.setAgencyno(agencyno);
			iptables.setIpaddr(ipaddr);
			iptables.setState(new Short("1"));
			iptables.setExpiretime(new Date());
			iptables.persist();
			
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("biguser/addip error:", e);
		}
		
		view.addObject("errormsg", errormsg);
		return this.getIps(agencyno, view);
	}
	
	@RequestMapping("/addTlogicUI")
	public ModelAndView page(ModelAndView view){
		logger.info("biguser/addTlogicUI");
		view.setViewName("biguser/addTlogicUI");
		return view;
	}
	
	@RequestMapping("/addTlogic")
	public ModelAndView addTlogic(@RequestParam("moneycount") String moneycount,
			@RequestParam(value = "selldate") String selldate,
			ModelAndView view) {
		String errormsg = "添加成功";
		String[] money = moneycount.split(",");
		if (StringUtil.isEmpty(moneycount) || money.length < 24) {
			view.addObject("errormsg", "金额不允许为空");
			view.setViewName("biguser/addTlogicUI");
			return view;
		}else if(StringUtil.isEmpty(selldate)){
			view.addObject("errormsg", "日期不允许为空");
			view.setViewName("biguser/addTlogicUI");
			return view;
		}
		try {
			int j = 89201;
			for (int i = 0; i < 24; i++) {
				Tlogic tlogic = new Tlogic();
				tlogic.setNum(new BigDecimal(j++));
				tlogic.setMoneycount(new BigDecimal(money[i]));
				tlogic.setSelldate(selldate);
				tlogic.persist();
			}
		} catch (Exception e) {
			logger.error("biguser/addTlogic error:", e);
			errormsg = e.getMessage();
		}
	
		view.addObject("errormsg", errormsg);
		view.setViewName("biguser/addTlogicUI");
		return view;
	}
}
