package com.ruyicai.mgr.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tlot;
import com.ruyicai.mgr.domain.Torder;
import com.ruyicai.mgr.listener.TlotListener;
import com.ruyicai.mgr.service.TlotService;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/tmonitors")
@Controller
public class TmonitorController {

	private Logger logger = Logger.getLogger(TmonitorController.class);
	@Autowired
	private PropertiesUtil util;

	@RequestMapping("/top")
	public String top() {
		return "tmonitors/top";
	}

	@RequestMapping("/page")
	public String page() {
		return "tmonitors/page";
	}

	@RequestMapping("/index")
	public ModelAndView index(ModelAndView view) {
		List<Integer> list = new ArrayList<Integer>();
		String key = new SimpleDateFormat("yyyyMMddHH").format(new Date());
		int min = Integer.parseInt(new SimpleDateFormat("mm")
				.format(new Date()));
		int max = 0;
		for (int i = 0; i <= min; i++) {
			Integer num = TlotListener.getMap().get(
					key + ((i < 10) ? "0" + i : i));
			if (null == num) {
				num = 0;
			}
			list.add(num);
			if(num > max) {
				max = num;
			}
		}
		view.addObject("max", max);
		try {
			List<Long> l = Tlot.findByBetfail();
			l.add(Torder.findByBetfail());
			view.addObject("failnum", l);
		} catch (Exception e) {
			logger.error("tmonitors/index error", e);
		}
		view.addObject("list", list);
		view.setViewName("tmonitors/index");
		return view;
	}

	@RequestMapping("/failnum")
	public @ResponseBody
	ResponseData failnum() {
		ResponseData data = new ResponseData();
		try {
			List<Long> list = Tlot.findByBetfail();
			data.setErrorCode("0");
			list.add(Torder.findByBetfail());
			data.setValue(list);
		} catch (Exception e) {
			logger.error("tmonitors/failnum error", e);
		}
		return data;
	}
	
	
	@RequestMapping("/state")
	public @ResponseBody
	ResponseData state(@RequestParam("flowno") String flowno) {
		Tlot tlot = Tlot.findTlot(flowno);
		ResponseData data = new ResponseData();
		data.setValue(tlot.getInstate());
		return data;
	}

	@RequestMapping("faillist")
	public ModelAndView faillist(ModelAndView view) {
		view.addObject("faillist", Tlot.findFailList());
		view.setViewName("tmonitors/faillist");
		return view;
	}
	
	@RequestMapping("torderFaillist")
	public ModelAndView torderFaillist(ModelAndView view) {
		view.addObject("torderFaillist", Torder.findFailList());
		view.setViewName("tmonitors/torderFaillist");
		return view;
	}
	@RequestMapping("torderFaillist2")
	public ModelAndView torderFaillist2(ModelAndView view) {
		view.addObject("torderFaillist", Torder.findFailList2());
		view.setViewName("tmonitors/torderFaillist");
		return view;
	}
	
	@Autowired
	TlotService tlotService;
	@RequestMapping("/updateTime")
	public @ResponseBody
	ResponseData updateTime() {
		ResponseData data = new ResponseData();
		String errormsg = "重置时间成功";
		ErrorCode errorcode = ErrorCode.OK;
		int i = 0;
		try {
			i = tlotService.updateTime();
		} catch (Exception e) {
			logger.error("tmonitors/updateTime error", e);
			errormsg = "重置时间失败";
			errorcode = ErrorCode.ERROR;
		}
		data.setErrorCode(errorcode.value);
		data.setValue(errormsg+",成功个数:"+i);
		return data;
	}
	@RequestMapping("betAll")
	public ModelAndView betAll(ModelAndView view) {
		String errormsg = "重投成功";
		try {
			List<Tlot> tlots = Tlot.findFailList();
			for (Tlot tlot : tlots) {
				String result = HttpUtil.post(util.getLotteryurl()
						+ "/system/continuebet", "flowno=" + tlot.getFlowno());
				JSONObject json = new JSONObject(result);
				if (!"0".equals(json.getString("errorCode"))) {
					errormsg += " 失败flowno:"+tlot.getFlowno();
				}
				Thread.sleep(100L);
			}
			view.addObject("faillist", Tlot.findFailList());
		} catch (Exception e) {
			logger.error("tmonitors/betAll error", e);
			errormsg = "重投失败e:"+e.getMessage();
		}
		
		view.addObject("errormsg", errormsg);
		view.setViewName("tmonitors/faillist");
		return view;
	}
	
	@RequestMapping("torderbetAll")
	public ModelAndView torderbetAll(ModelAndView view) {
		String errormsg = "已重投,请稍后查看结果";
		try { 
			List<Torder> torders = Torder.findFailList();
			for (Torder torder : torders) {
				String orderid = torder.getId();
				List<Tlot> l = Tlot.findTlotsByTorderid(orderid).getResultList();
				if (l.size() == 0) {
					String body = Torder.findTorder(orderid).getBody();
					String result = HttpUtil.post(util.getLotteryurl()
							+ "/system/orderbet", "orderid=" + orderid+"&body="+body);
					JSONObject json = new JSONObject(result);
					if (!"0".equals(json.getString("errorCode"))) {
						errormsg = "重投失败"+json.getString("errorCode");
					}
				}else{
					errormsg = "有订单下有票不允许重投";
				}
				Thread.sleep(100L);
			}
		} catch (Exception e) {
			errormsg = "重投失败"+e.getMessage();
			logger.error("tmonitors/torderbetAll error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.torderFaillist(view);
	}
	
	
	
	@RequestMapping("/continuebet")
	public @ResponseBody
	ResponseData continuebet(@RequestParam("flowno") String flowno,
			@RequestParam("agencyno") String agencyno,
			@RequestParam("ordertime") String ordertime) {
		ResponseData data = new ResponseData();
		String errormsg = "已重投,请稍后查看结果";
		ErrorCode errorcode = ErrorCode.OK;
		try {
			logger.info("重投参数flowno:"+flowno+",anencyno:"+agencyno+",ordertime:"+ordertime);
			Tlot tlot = Tlot.findTlot(flowno);
			if(!agencyno.equals(tlot.getAgencyno())) {
				logger.info("修改angencyno");
				tlot.setAgencyno(agencyno);
				tlot.merge();
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(tlot.getLotcenterordertime() == null || StringUtil.isEmpty(ordertime)){
				tlot.setLotcenterordertime(new Date());
				tlot.merge();
			}else if(!ordertime.equals(sdf.format(tlot.getLotcenterordertime()))) {
				logger.info("修改ordertime");
				tlot.setLotcenterordertime(sdf.parse(ordertime));
				tlot.merge();
			}
			String result = HttpUtil.post(util.getLotteryurl()	+ "/system/continuebet", "flowno=" + flowno);
			JSONObject json = new JSONObject(result);
			if (!"0".equals(json.getString("errorCode"))) {
				errormsg = "重投失败:"+result;
				errorcode = ErrorCode.ERROR;
			}
		} catch (Exception e) {
			logger.error("tmonitors/liscontinuebett error", e);
			errormsg = "出现异常e:"+e.getMessage();
			errorcode = ErrorCode.ERROR;
		}
		data.setErrorCode(errorcode.value);
		data.setValue(errormsg);
		return data;
	}
	
	@RequestMapping("/torderUndeduct")
	public @ResponseBody
	ResponseData torderUndeduct(@RequestParam("orderid") String orderid) {
		ResponseData data = new ResponseData();
		String errormsg = "已冲正,请稍后查看结果";
		ErrorCode errorcode = ErrorCode.OK;
		try {
			String result = HttpUtil.post(util.getLotteryurl()
					+ "/system/orderundeduct", "orderid=" + orderid);
			JSONObject json = new JSONObject(result);
			if (!"0".equals(json.getString("errorCode"))) {
				errormsg = "冲正失败"+json.getString("errorCode");
				errorcode = ErrorCode.ERROR;
			}
		} catch (Exception e) {
			errormsg = "冲正失败"+e.getMessage();
			logger.error("tmonitors/torderUndeduct error", e);
			errorcode = ErrorCode.ERROR;
		}
		data.setErrorCode(errorcode.value);
		data.setValue(errormsg);
		return data;
	}
	
	@RequestMapping("/orderbet")
	public @ResponseBody
	ResponseData orderbet(@RequestParam("orderid") String orderid) {
		ResponseData data = new ResponseData();
		String errormsg = "已重投,请稍后查看结果";
		ErrorCode errorcode = ErrorCode.OK;
		try {
			List<Tlot> l = Tlot.findTlotsByTorderid(orderid).getResultList();
			if (l.size() == 0) {
				String body = Torder.findTorder(orderid).getBody();
				String result = HttpUtil.post(util.getLotteryurl()
						+ "/system/orderbet", "orderid=" + orderid+"&body="+body);
				JSONObject json = new JSONObject(result);
				if (!"0".equals(json.getString("errorCode"))) {
					errormsg = "重投失败"+json.getString("errorCode");
					errorcode = ErrorCode.ERROR;
				}
			}else{
				errormsg = "订单下有票不允许重投";
			}
			
		} catch (Exception e) {
			errormsg = "重投失败"+e.getMessage();
			logger.error("tmonitors/orderbet error", e);
			errorcode = ErrorCode.ERROR;
		}
		data.setErrorCode(errorcode.value);
		data.setValue(errormsg);
		return data;
	}
	
	@RequestMapping("/continuebetall")
	public @ResponseBody
	ResponseData continuebetall(@RequestParam("flowno") String[] flowno) {
		ResponseData data = new ResponseData();
		String errormsg = "已重投,请稍后查看结果";
		ErrorCode errorcode = ErrorCode.OK;
		try {
			for (String f : flowno) {
				HttpUtil.post(util.getLotteryurl() + "/bet/continuebet", "flowno=" + f);
			}
		} catch (Exception e) {
			errormsg = "重投失败"+e.getMessage();
			errorcode = ErrorCode.ERROR;
			logger.error("tmonitors/continuebetall error", e);
		}
		data.setErrorCode(errorcode.value);
		data.setValue(errormsg);
		return data;
	}

	@RequestMapping("/num")
	public @ResponseBody
	ResponseData num() {
		List<Integer> list = new ArrayList<Integer>();
		String key = new SimpleDateFormat("yyyyMMddHH").format(new Date());
		int min = Integer.parseInt(new SimpleDateFormat("mm")
				.format(new Date()));
		for (int i = 0; i <= min; i++) {
			Integer num = TlotListener.getMap().get(
					key + ((i < 10) ? "0" + i : i));
			if (null == num) {
				num = 0;
			}
			list.add(num);
		}
		ResponseData data = new ResponseData();
		data.setErrorCode("0");
		data.setValue(list);
		return data;
	}

	@RequestMapping("/sell")
	public ModelAndView sell(
			@RequestParam(value = "type", required = false, defaultValue = "1") String type,
			@RequestParam(value = "date", required = false) String date,
			@RequestParam(value = "month", required = false) String month,
			ModelAndView view) {
		List<Object[]> list = null;
		List<Long> result = new ArrayList<Long>();
		if ("2".equals(type)) {
			int dd = 0;
			if (StringUtil.isEmpty(month)) {
				month = new SimpleDateFormat("yyyy-MM").format(new Date());
				dd = Integer.parseInt(new SimpleDateFormat("dd")
						.format(new Date()));
			} else {
				if (new SimpleDateFormat("yyyy-MM").format(new Date()).equals(
						month)) {
					dd = Integer.parseInt(new SimpleDateFormat("dd")
							.format(new Date()));
				} else {
					dd = 31;
				}
			}
			list = Tlot.findByMonthGroupby(month);
			for (int i = 1; i <= dd; i++) {
				boolean flag = true;
				for (Object[] objs : list) {
					if ((month + (i < 10 ? "-0" + i : "-" + i))
							.equals((String) objs[0])) {
						result.add((Long) objs[1]);
						flag = false;
						break;
					}
				}
				if (flag) {
					result.add(0L);
				}
			}
		} else {
			int hh = 0;
			if (StringUtil.isEmpty(date)) {
				date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
				hh = Integer.parseInt(new SimpleDateFormat("HH")
						.format(new Date()));
			} else {
				if (new SimpleDateFormat("yyyy-MM-dd").format(new Date())
						.equals(date)) {
					hh = Integer.parseInt(new SimpleDateFormat("HH")
							.format(new Date()));
				} else {
					hh = 24;
				}
			}
			list = Tlot.findByDateGroupby(date);
			for (int i = 0; i < hh; i++) {
				boolean flag = true;
				for (Object[] objs : list) {
					if ((date + (i < 10 ? " 0" + i : " " + i))
							.equals((String) objs[0])) {
						result.add((Long) objs[1]);
						flag = false;
						break;
					}
				}
				if (flag) {
					result.add(0L);
				}
			}
		}
		view.addObject("max", getMax(result));
		view.addObject("list", result);
		view.addObject("type", type);
		view.addObject("date", date);
		view.addObject("month", month);
		return view;
	}

	private Long getMax(List<Long> list) {
		Long max = 0L;
		if (!list.isEmpty()) {
			for (Long l : list) {
				if (l > max) {
					max = l;
				}
			}
		}
		return max;
	}
}
