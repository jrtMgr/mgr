package com.ruyicai.mgr.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.charge.ChargeType;
import com.ruyicai.mgr.consts.Const;
import com.ruyicai.mgr.consts.TransactionState;
import com.ruyicai.mgr.domain.Tsubchannel;
import com.ruyicai.mgr.domain.Ttransaction;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.exception.RuyicaiException;
import com.ruyicai.mgr.util.DateUtil;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/tcharges")
@Controller
public class TchargeController {

	private Logger logger = Logger.getLogger(TchargeController.class);
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		view.addObject("starttime", DateUtil.format("yyyy-MM-dd", new Date()));
		view.addObject("state", 1);
		view.setViewName("tcharges/list");
		return view;
	}

	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "ttransactionid", required = false) String ttransactionid,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "channeltype", required = false) String channeltype,
			@RequestParam(value = "channel", required = false) String channel,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "bankid", required = false) String bankid,
			@RequestParam(value = "ttype", required = false) String ttype,
			@RequestParam(value = "agencyno", required = false) String agencyno,
			
			@RequestParam(value = "state", required = false) BigDecimal state,
			@ModelAttribute("page") Page<Ttransaction> page, ModelAndView view) {
		logger.info("/tcharges/list");
		try {
			boolean flag = false;
			Tuserinfo tuserinfo = null;
			StringBuilder builder = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			if (!StringUtil.isEmpty(channeltype)
					&& !StringUtil.isEmpty(channel)) {
				if ("tuserinfo".equals(channeltype)) {
					builder.append(", Tuserinfo u where o.userno=u.userno and u.channel=? and");
					params.add(channel);
					flag = true;
				}
				if ("tcharge".equals(channeltype)) {
					builder.append(" where  o.channel=? and");
					params.add(channel);
					flag = true;
				}
				view.addObject("channeltype",channeltype);
			}
			if (StringUtil.isEmpty(builder.toString())) {
				builder.append(" where ");
			}
			if ("cz".equals(ttype)) {
				builder.append(" (o.type=2 or o.type=3 or o.type=10) and");
				if (!StringUtil.isEmpty(bankid)) {
					builder.append(" o.bankid = ? and");
					params.add(bankid);
					flag = true;
				}
			}else if("kk".equals(ttype)){
				builder.append(" o.type=24 and");
			}else if("bucz".equals(ttype)){
				builder.append(" o.type=9 and");
				if (!StringUtil.isEmpty(agencyno)) {
					builder.append(" o.userno = ? and");
					params.add(agencyno);
				}
				flag = true;
			} else if("zs".equals(ttype)){
				builder.append(" o.type=23 and");
				flag = true;
			}
			view.addObject("ttype",ttype);
			
			if (!StringUtil.isEmpty(type) && !StringUtil.isEmpty(id)) {
				try {
					if ("userno".equals(type)) {
						tuserinfo = Tuserinfo.findTuserinfoByuserno(id, Const.Subchannel);
					} else	if (Const.Tuserinfo_Mobileid.equals(type)) {
						tuserinfo = Tuserinfo.findTuserinfoesByMobileid(id,
								Const.Subchannel);
					} else if (Const.Tuserinfo_Email.equals(type)) {
						tuserinfo = Tuserinfo.findTuserinfoesByEmail(id,
								Const.Subchannel);
					} else if (Const.Tuserinfo_UserName.equals(type)) {
						tuserinfo = Tuserinfo.findTuserinfoesByUserName(id,
								Const.Subchannel);
					}
				} catch (Exception exception) {
					throw new RuntimeException("用户不存在");
				}
				if (null == tuserinfo) {
					throw new RuntimeException("用户不存在");
				}
				builder.append(" o.userno=? and");
				params.add(tuserinfo.getUserno());
				view.addObject("type", type);
				view.addObject("id", id);
				flag = true;
			}
			if(!StringUtil.isEmpty(ttransactionid)) {
				ttransactionid = ttransactionid.trim();
				builder.append(" o.id=? and");
				params.add(ttransactionid);
				flag = true;
			}
			if (!StringUtil.isEmpty(starttime)) {
				builder.append(" to_char(o.plattime, 'yyyy-mm-dd') >= ? and");
				params.add(starttime);
				view.addObject("starttime", starttime);
				flag = true;
			}
			if (!StringUtil.isEmpty(endtime)) {
				builder.append(" to_char(o.plattime, 'yyyy-mm-dd') <= ? and");
				params.add(endtime);
				flag = true;
			}
			
			if (null != state ) {
				if (state.equals(BigDecimal.ZERO)) {
					builder.append(" o.state = 0 and");
					flag = true;
				}
				if (state.equals(BigDecimal.ONE)) {
					builder.append(" o.state = 1 and");
					flag = true;
				}
				if (state.equals(new BigDecimal(2))) {
					builder.append(" o.state = 2 and");
					flag = true;
				}
				if (state.equals(new BigDecimal(3))) {
					builder.append(" o.state = 3 and");
					flag = true;
				}
				view.addObject("state", state);
			}
			
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			if (flag)
				Ttransaction.findList(builder.toString(),
						" order by o.plattime desc", params, page);
			view.addObject("subchannels", Tsubchannel.findAllTsubchannels());
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tcharges/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("tcharges/list");
		return view;
	}
	
	
	/*
	 * 生成报表 
	 */
	//@RequestMapping("/generateReport")
	public void generateReport(@RequestParam(value = "ttransactionid", required = false) String ttransactionid,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "channeltype", required = false) String channeltype,
			@RequestParam(value = "channel", required = false) String channel,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "ttype", required = false) String ttype,
			@RequestParam(value = "bankid", required = false) String bankid,
			@RequestParam(value = "state", required = false) BigDecimal state,
			@RequestParam(value = "agencyno", required = false) String agencyno,
			HttpServletRequest request, HttpServletResponse response)throws IOException {
		OutputStream os = null;
		WritableWorkbook wwb = null;
		try {
			boolean flag = false;
			Tuserinfo tuserinfo = null;
			StringBuilder builder = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			if (!StringUtil.isEmpty(channeltype)
					&& !StringUtil.isEmpty(channel)) {
				if ("tuserinfo".equals(channeltype)) {
					builder.append(", Tuserinfo u where o.userno=u.userno and u.channel=? and");
					params.add(channel);
					flag = true;
				}
				if ("tcharge".equals(channeltype)) {
					builder.append(" where  o.channel=? and");
					params.add(channel);
					flag = true;
				}
			}
			if (StringUtil.isEmpty(builder.toString())) {
				builder.append(" where ");
			}
			if ("cz".equals(ttype)) {
				builder.append(" (o.type=2 or o.type=3 or o.type=10) and");
				if (!StringUtil.isEmpty(bankid)) {
					builder.append(" o.bankid = ? and");
					params.add(bankid);
					flag = true;
				}
			}else if("kk".equals(ttype)){
				builder.append(" o.type=24 and");
			}else if("bucz".equals(ttype)){
				builder.append(" o.type=9 and");
				if (!StringUtil.isEmpty(agencyno)) {
					builder.append(" o.userno = ? and");
					params.add(agencyno);
				}
				flag = true;
			}
			
			if (!StringUtil.isEmpty(type) && !StringUtil.isEmpty(id)) {
				try {
					if (Const.Tuserinfo_Mobileid.equals(type)) {
						tuserinfo = Tuserinfo.findTuserinfoesByMobileid(id,
								Const.Subchannel);
					} else if (Const.Tuserinfo_Email.equals(type)) {
						tuserinfo = Tuserinfo.findTuserinfoesByEmail(id,
								Const.Subchannel);
					} else if (Const.Tuserinfo_UserName.equals(type)) {
						tuserinfo = Tuserinfo.findTuserinfoesByUserName(id,
								Const.Subchannel);
					}
				} catch (Exception exception) {
					throw new RuntimeException("用户不存在");
				}
				if (null == tuserinfo) {
					throw new RuntimeException("用户不存在");
				}
				builder.append(" o.userno=? and");
				params.add(tuserinfo.getUserno());
				flag = true;
			}
			if(!StringUtil.isEmpty(ttransactionid)) {
				builder.append(" o.id=? and");
				params.add(ttransactionid);
				flag = true;
			}
			if (!StringUtil.isEmpty(starttime)) {
				builder.append(" to_char(o.plattime, 'yyyy-mm-dd') >= ? and");
				params.add(starttime);
				flag = true;
			}
			if (!StringUtil.isEmpty(endtime)) {
				builder.append(" to_char(o.plattime, 'yyyy-mm-dd') <= ? and");
				params.add(endtime);
				flag = true;
			}
			if (null != state ) {
				if (state.equals(BigDecimal.ZERO)) {
					builder.append(" o.state = 0 and");
					flag = true;
				}
				if (state.equals(BigDecimal.ONE)) {
					builder.append(" o.state = 1 and");
					flag = true;
				}
				if (state.equals(new BigDecimal(2))) {
					builder.append(" o.state = 2 and");
					flag = true;
				}
				if (state.equals(new BigDecimal(3))) {
					builder.append(" o.state = 3 and");
					flag = true;
				}
			}
			
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			List<Ttransaction> list = null;
			if (flag)
				list = Ttransaction.findList(builder.toString(), " order by o.plattime desc", params);
		
			os = response.getOutputStream();
			wwb = Workbook.createWorkbook(os);
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition", "attachment;filename=\""
					+ new String((java.net.URLEncoder.encode("充值信息报表.xls",	"UTF-8")).getBytes("UTF-8"), "GB2312") + "\"");
			
			WritableSheet ws = wwb.createSheet("sheet1", 0);
	        
			ws.addCell(new Label(0, 0, "交易ID"));
			ws.addCell(new Label(1, 0, "用户手机号"));
			ws.addCell(new Label(2, 0, "用户姓名"));
			ws.addCell(new Label(3, 0, "充值方式"));
			ws.addCell(new Label(4, 0, "充值时间"));
			ws.addCell(new Label(5, 0, "金额(元)"));
			ws.addCell(new Label(6, 0, "手续费（元）"));
			ws.addCell(new Label(7, 0, "状态"));
			ws.addCell(new Label(8, 0, "银行返回"));
			ws.addCell(new Label(9, 0, "备注"));
			ws.addCell(new Label(10, 0, "如意卡卡号"));
			ws.addCell(new Label(11, 0, "渠道"));
			
			
			BigDecimal amtAll = BigDecimal.ZERO;
			BigDecimal feeAll = BigDecimal.ZERO;
			for (int i = 0; i < list.size(); i++) {
				Ttransaction transaction = list.get(i);
				int j = i+1;
		        ws.addCell(new Label(0, j, transaction.getId()));  
		        Tuserinfo tuserinfo2 = Tuserinfo.findTuserinfo(transaction.getUserno());
		        String mobileid;
		        String name;
		        if (tuserinfo2 == null) {
					mobileid = "没有这个用户";
					name = "没有这个用户";
				}else{
					mobileid = tuserinfo2.getMobileid();
					name = tuserinfo2.getName();
				}
		        ws.addCell(new Label(1, j, mobileid));
		        ws.addCell(new Label(2, j, name));
		        if ("bucz".equals(ttype)) {
		        	ws.addCell(new Label(3, j, Tsubchannel.findTsubchannel(transaction.getUserno()).getAgencyname()));
				}else{
				   ws.addCell(new Label(3, j, ChargeType.getMemo(transaction.getBankid())));
				}
		        ws.addCell(new Label(4, j, transaction.getPlattime().toString()));
		        ws.addCell(new Label(5, j, transaction.getAmt().divide(new BigDecimal(100)).toString()));
		        amtAll = amtAll.add(transaction.getAmt());
		        ws.addCell(new Label(6, j, transaction.getFee().divide(new BigDecimal(100)).toString()));
		        feeAll = feeAll.add(transaction.getFee());
		        ws.addCell(new Label(7, j, TransactionState.getMemo(transaction.getState())));
		        ws.addCell(new Label(8, j, transaction.getRetmemo()));
		        ws.addCell(new Label(9, j, transaction.getMemo()));
		        ws.addCell(new Label(10, j, transaction.getBankaccount()));
		        ws.addCell(new Label(11, j, transaction.getChannel()));
			}
			
			int k = list.size()+1;
			ws.addCell(new Label(0, k, "合计"));
	        ws.addCell(new Label(5, k, amtAll.divide(new BigDecimal(100)).toString()));
	        ws.addCell(new Label(6, k, feeAll.divide(new BigDecimal(100)).toString()));
			wwb.write();   
		} catch (Exception e) {
			logger.error("发生异常:"+e);
			e.printStackTrace();
		} finally {
			if (wwb != null) {
				try {
					wwb.close();
				} catch (WriteException e) {
					e.printStackTrace();
				}
			}
			if (os != null) {
				os.close();
			}
			
		}
	}
	
	@RequestMapping(value = "/getStatChargeUsers.htm", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData getStatChargeUsers(@RequestParam("beginTime") String beginTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "subchannel", required = false, defaultValue="00092493") String subchannel,
			@RequestParam(value = "channel", required = false) String channel
			) {
		ResponseData rd = new ResponseData();
		ErrorCode result = ErrorCode.OK;
		
		try {
			Long ret = Ttransaction.findUserChargeByTimeAndChannel(beginTime, endTime, subchannel, channel);
			rd.setValue(ret);
		} catch (RuyicaiException e) {
			result = e.getErrorCode();
		} catch (Exception e) {
			logger.error("根据时间段和channel查询充值用户数处理出现异常", e);
			result = ErrorCode.ERROR;
		} 
		rd.setErrorCode(result.value);
		return rd;
	}
	
	@RequestMapping("/bankOrderQuery")
	public ModelAndView bankOrderQuery(ModelAndView view) {
		view.setViewName("tcharges/bankorderquery");
		return view;
	}
	
	
	@RequestMapping("/alipay")
	public ModelAndView alipay(
			@RequestParam(value = "ttransactionid", required = false) String ttransactionid,
			@RequestParam(value = "bankorderid", required = false) String bankorderid,
			ModelAndView view) {
		logger.info("tcharges/alipay begin");
		logger.info("ttransactionid=" + ttransactionid + ";bankorderid=" + bankorderid);
		String errormsg = null;		
		Map<String, Object> map = null;
		
		if (StringUtil.isEmpty(bankorderid) && StringUtil.isEmpty(ttransactionid)) {
			logger.info("支付宝订单查询->ttransactionid和bankorderid都为空");
			errormsg = "支付宝订单查询，商户订单号和支付宝交易号不能都为空";
			view.addObject("errormsg", errormsg);	
			view.setViewName("tcharges/alipay");
			return view;
		}
		if (!StringUtil.isEmpty(bankorderid)) {
			bankorderid = bankorderid.trim();			
		}
		if (!StringUtil.isEmpty(ttransactionid)) {
			ttransactionid = ttransactionid.trim();			
		}
		
		try {			
			StringBuilder param = new StringBuilder();
			param.append("jsonString={\"ttransactionid\":\"").append(ttransactionid).append("\",\"tradeno\":\"").append(bankorderid).append("\"}");
			String url = propertiesUtil.getChargeCenterurl() + "/tradequery!alipay";
			logger.info("chargecenterUrl:" + url + "；param=" + param.toString());
			//String result = "{\"buyer_id\":\"2088102680625790\",\"trade_no\":\"2012030267420579\",\"use_coupon\":\"F\",\"subject\":\"如意彩金\",\"is_total_fee_adjust\":\"F\",\"out_trade_no\":\"BJ201203020000000000000003423936\",\"trade_status\":\"交易成功结束\",\"gmt_payment\":\"2012-03-02 11:16:22\",\"discount\":\"0.00\",\"buyer_email\":\"574700498@qq.com\",\"gmt_create\":\"2012-03-02 11:15:19\",\"operator_role\":\"B\",\"price\":\"10.00\",\"to_buyer_fee\":\"0.00\",\"to_seller_fee\":\"10.00\",\"total_fee\":\"10.00\",\"gmt_last_modified_time\":\"2012-03-02 11:16:22\",\"seller_id\":\"2088301685770478\",\"quantity\":\"1\",\"seller_email\":\"chongzhi@ruyicai.com\",\"flag_trade_locked\":\"0\",\"payment_type\":\"商品购买\",\"coupon_used_fee\":\"0.00\", \"errorCode\":\"0\"}";//HttpUtil.post(url, param.toString());
			String result = HttpUtil.post(url, param.toString());
			logger.info("result:" + result);
			JSONObject json = new JSONObject(result);			
			if("0".equals(json.getString("errorCode"))) {
				errormsg = "查询成功";
				map = JsonUtil.transferJson2Map(result);
				logger.info("tcharges/alipay：" + errormsg);
			} else if("500".equals(json.getString("errorCode"))) {
				errormsg = "远程服务器内部错误";
			} else {
				errormsg = "远程服务器后台错误";
			}
		} catch (Exception e) {
			errormsg = "查询失败";
			logger.error("tcharges/alipay error", e);
		}
		
		view.addObject("ttransactionid", ttransactionid);
		view.addObject("bankorderid", bankorderid);
		view.addObject("errormsg", errormsg);
		view.addAllObjects(map);
		view.setViewName("tcharges/alipay");		
		logger.info("tcharges/alipay end");
		return view;
	}
	@RequestMapping("/lthjpay")
	public ModelAndView lthjpay(
			@RequestParam(value = "ttransactionid", required = false) String ttransactionid,
			@RequestParam(value = "ordertime", required = false) String ordertime,
			ModelAndView view) {
		logger.info("tcharges/lthjpay begin");
		logger.info("ttransactionid=" + ttransactionid + ";ordertime=" + ordertime);
		String errormsg = null;		
		Map<String, Object> map = null;
		
		if (StringUtil.isEmpty(ttransactionid)) {
			logger.info("银联手机订单订单查询->ttransactionid为空");
			view.addObject("errormsg", errormsg);	
			view.setViewName("tcharges/lthjpay");
			return view;
		}
		
		if (!StringUtil.isEmpty(ttransactionid)) {
			ttransactionid = ttransactionid.trim();			
		}		
		if (!StringUtil.isEmpty(ordertime)) {
			ordertime = ordertime.trim();			
		}
		
		try {			
			StringBuilder param = new StringBuilder();
			param.append("jsonString={\"ttransactionid\":\"").append(ttransactionid).append("\",\"ordertime\":\"").append(ordertime).append("\"}");
			String url = propertiesUtil.getChargeCenterurl() + "/tradequery!lthjpay";
			logger.info("chargecenterUrl:" + url + "；param=" + param.toString());
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);			
			if("0".equals(json.getString("errorCode"))) {
				map = JsonUtil.transferJson2Map(result);
				logger.info("tcharges/lthjpay：" + errormsg);
			} else if("500".equals(json.getString("errorCode"))) {
				errormsg = "远程服务器内部错误";
			} else {
				errormsg = "远程服务器后台错误";
			}
		} catch (Exception e) {
			errormsg = "查询失败";
			logger.error("tcharges/lthjpay error", e);
		}
		
		view.addObject("errormsg", errormsg);
		view.addAllObjects(map);
		view.setViewName("tcharges/lthjpay");		
		logger.info("tcharges/lthjpay end");
		return view;
	}
	
	@RequestMapping("/lakalapay")
	public ModelAndView lakalapay(
			@RequestParam(value = "ttransactionid", required = false) String ttransactionid,
			@RequestParam(value = "ordertime", required = false) String ordertime,
			ModelAndView view) {
		logger.info("tcharges/lakalapay begin");
		logger.info("ttransactionid=" + ttransactionid + ";ordertime=" + ordertime);
		String errormsg = null;		
		Map<String, Object> map = null;
		
		if (StringUtil.isEmpty(ttransactionid)) {
			logger.info("拉卡拉订单订单查询->ttransactionid为空");
			view.addObject("errormsg", errormsg);	
			view.setViewName("tcharges/lakalapay");
			return view;
		}
		
		if (!StringUtil.isEmpty(ttransactionid)) {
			ttransactionid = ttransactionid.trim();			
		}
		
		if (!StringUtil.isEmpty(ordertime)) {
			ordertime = ordertime.trim();			
		}
		
		try {			
			StringBuilder param = new StringBuilder();
			param.append("jsonString={\"ttransactionid\":\"").append(ttransactionid).append("\",\"ordertime\":\"").append(ordertime).append("\"}");
			String url = propertiesUtil.getChargeCenterurl() + "/tradequery!lakalapay";
			logger.info("chargecenterUrl:" + url + "；param=" + param.toString());
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);			
			if("0".equals(json.getString("errorCode"))) {
				map = JsonUtil.transferJson2Map(result);
				logger.info("tcharges/lakalapay：" + errormsg);
			} else if("500".equals(json.getString("errorCode"))) {
				errormsg = "远程服务器内部错误";
			} else {
				errormsg = "远程服务器后台错误";
			}
		} catch (Exception e) {
			errormsg = "查询失败";
			logger.error("tcharges/lakalapay error", e);
		}
		
		view.addObject("errormsg", errormsg);
		view.addAllObjects(map);
		view.setViewName("tcharges/lakalapay");		
		logger.info("tcharges/lakalapay end");
		return view;
	}
	
	@RequestMapping("/doChargeProcess")
	public ModelAndView doChargeProcess(
			@RequestParam("amt") String amt,
			@RequestParam("ttransactionid") String ttransactionid,
			@RequestParam("bankorderid") String bankorderid,
			@RequestParam("bankordertime") String bankordertime,
			@RequestParam("banktrace") String banktrace,
			@RequestParam("retcode") String retcode,
			@RequestParam("retmemo") String retmemo,			
			@RequestParam("flag") String flag,
			ModelAndView view) {
		logger.info("tcharges/doChargeProcess");
		String errormsg = "手动置订单成功";
		try {
			StringBuilder param = new StringBuilder();
			if ("lthjpay".equals(flag)) {
				param.append("amt=").append(amt).append("&ttransactionid=").append(ttransactionid.trim())
				.append("&bankorderid=").append(bankorderid.trim())
				.append("&bankordertime=").append(DateUtil.format(new Date()))
				.append("&banktrace=").append(banktrace.trim())
				.append("&retcode=").append("00")
				.append("&retmemo=").append("Success!")
				.append("&drawamt=").append(getDrawamt(amt));
		
				view.setViewName("tcharges/lthjpay");				
			} else if ("alipay".equals(flag)) {
				String amt2 = (new BigDecimal(amt)).multiply(new BigDecimal(100)).toString();
				
				param.append("amt=").append(amt2).append("&ttransactionid=").append(ttransactionid.trim())
				.append("&bankorderid=").append(bankorderid.trim())
				.append("&bankordertime=").append(bankordertime)
				.append("&banktrace=").append(" ")
				.append("&retcode=").append(retcode)
				.append("&retmemo=").append(retmemo)
				.append("&drawamt=").append(getDrawamt(amt2));
				
				view.setViewName("tcharges/alipay");
			} else if ("dnapay".equals(flag)) {
				String amt3 = (new BigDecimal(amt)).multiply(new BigDecimal(100)).toString();
				
				param.append("amt=").append(amt3).append("&ttransactionid=").append(ttransactionid.trim())
				.append("&bankorderid=").append(bankorderid.trim())
				.append("&bankordertime=").append(DateUtil.format(new Date()))
				.append("&banktrace=").append(banktrace)
				.append("&retcode=").append(retcode)
				.append("&retmemo=").append("交易成功")
				.append("&drawamt=").append(getDrawamt(amt3));
				
				view.setViewName("tcharges/dnapay");
			}
			
			if (StringUtil.isEmpty(param.toString())) {
				errormsg = "参数有误";
				view.addObject("errormsg", errormsg);		
				return view;
			}
			
			String url = propertiesUtil.getLotteryurl() + "/taccounts/doChargeProcess";
			logger.info("lotteryUrl:" + url);
			logger.info("param:" + param.toString());
			String result = HttpUtil.post(url, param.toString());
			logger.info("result:" + result);
			JSONObject json = new JSONObject(result);
			logger.info(json);
			if("300005".equals(json.getString("errorCode"))) {
				errormsg = "操作失败：Ttransaction已经成功";
			} else if("300002".equals(json.getString("errorCode"))){
				errormsg = "Ttransaction为空";
			} else if("300007".equals(json.getString("errorCode"))){
					errormsg = "操作失败：平台返回金额与本地数据库金额不符";
			} else if("0".equals(json.getString("errorCode"))){
				errormsg = "操作成功";
			} else {
				errormsg = "操作失败，返回码："+json.getString("errorCode");
			}
		} catch (Exception e) {
			errormsg = "失败"+e.getMessage();
			logger.error("tcharges/doChargeProcess error", e);
		}
		view.addObject("errormsg", errormsg);		
		return view;
	}
	
	public static String getDrawamt(String amt) {
		BigDecimal ret = BigDecimal.ZERO;
		BigDecimal amtBigDecimal = new BigDecimal(amt);
		ret = amtBigDecimal.multiply(new BigDecimal(70)).divide(new BigDecimal(100), 0, BigDecimal.ROUND_HALF_UP);
		return ret.toString();
	}
	
	@RequestMapping("/chinapay")
	public ModelAndView chinapay(
			@RequestParam(value = "ttransactionid", required = false) String ttransactionid,
			@RequestParam(value = "transdate", required = false) String transdate,
			@RequestParam(value = "gateid", required = false) String gateid,
			ModelAndView view) {
		logger.info("tcharges/alipay begin");
		logger.info("ttransactionid=" + ttransactionid + ";transdate=" + transdate + ";gateid=" + gateid);
		String errormsg = null;		
		Map<String, Object> map = null;
		
		boolean flag = true;
		if (flag) {
			errormsg = "上海银联订单查询，暂不支持";
			view.addObject("errormsg", errormsg);	
			view.setViewName("tcharges/chinapay");
			return view;
		}
		
		if (StringUtil.isEmpty(ttransactionid)) {
			logger.info("上海银联订单查询->ttransactionid都为空");
			errormsg = "上海银联订单查询，商户订单号不能为空";
			view.addObject("errormsg", errormsg);	
			view.setViewName("tcharges/chinapay");
			return view;
		}
		if (StringUtil.isEmpty(transdate)) {
			logger.info("上海银联订单查询->transdate都为空");
			errormsg = "上海银联订单查询，交易日期不能为空";
			view.addObject("errormsg", errormsg);	
			view.setViewName("tcharges/chinapay");
			return view;
		}
		if (StringUtil.isEmpty(gateid)) {
			logger.info("上海银联订单查询->gateid为空，gateid=" + gateid + "，有卡查询");			
		} else {
			logger.info("上海银联订单查询->gateid不为空，gateid=" + gateid + "，无卡查询");	
		}
		
		try {			
			StringBuilder param = new StringBuilder();
			param.append("jsonString={\"ttransactionid\":\"").append(ttransactionid).append("\",\"transdate\":\"").append(transdate).append("\",\"gateid\":\"").append(gateid).append("\"}");
			String url = propertiesUtil.getChargeCenterurl() + "/tradequery!chinapay";
			logger.info("chargecenterUrl:" + url + "；param=" + param.toString());
			String result = "{\"buyer_id\":\"2088102680625790\",\"trade_no\":\"2012030267420579\",\"use_coupon\":\"F\",\"subject\":\"如意彩金\",\"is_total_fee_adjust\":\"F\",\"out_trade_no\":\"BJ201203020000000000000003423936\",\"trade_status\":\"交易成功结束\",\"gmt_payment\":\"2012-03-02 11:16:22\",\"discount\":\"0.00\",\"buyer_email\":\"574700498@qq.com\",\"gmt_create\":\"2012-03-02 11:15:19\",\"operator_role\":\"B\",\"price\":\"10.00\",\"to_buyer_fee\":\"0.00\",\"to_seller_fee\":\"10.00\",\"total_fee\":\"10.00\",\"gmt_last_modified_time\":\"2012-03-02 11:16:22\",\"seller_id\":\"2088301685770478\",\"quantity\":\"1\",\"seller_email\":\"chongzhi@ruyicai.com\",\"flag_trade_locked\":\"0\",\"payment_type\":\"商品购买\",\"coupon_used_fee\":\"0.00\", \"errorCode\":\"0\"}";//HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);			
			if("0".equals(json.getString("errorCode"))) {
				errormsg = "查询成功";
				map = JsonUtil.transferJson2Map(result);
				logger.info("tcharges/chinapay：" + errormsg);
			} else if("500".equals(json.getString("errorCode"))) {
				errormsg = "远程服务器内部错误";
			} else {
				errormsg = "远程服务器后台错误";
			}
		} catch (Exception e) {
			errormsg = "查询失败";
			logger.error("tcharges/chinapay error", e);
		}
		
		view.addObject("ttransactionid", ttransactionid);
		view.addObject("transdate", transdate);
		view.addObject("gateid", gateid);
		view.addObject("errormsg", errormsg);
		view.addAllObjects(map);
		view.setViewName("tcharges/chinapay");		
		logger.info("tcharges/chinapay end");
		return view;
	}
	
	@RequestMapping("/dnapay")
	public ModelAndView dnapay(
			@RequestParam(value = "ttransactionid", required = false) String ttransactionid,
			ModelAndView view) {
		logger.info("tcharges/dnapay begin");
		logger.info("ttransactionid=" + ttransactionid);
		String errormsg = null;		
		Map<String, Object> map = null;
		if (StringUtil.isEmpty(ttransactionid)) {
			logger.info("DNA订单查询->ttransactionid为空");
			errormsg = "DNA订单查询，商户订单号不能为空";
			view.addObject("errormsg", errormsg);	
			view.setViewName("tcharges/dnapay");
			return view;
		}
		
		if (!StringUtil.isEmpty(ttransactionid)) {
			ttransactionid = ttransactionid.trim();			
		}
		try {			
			StringBuilder param = new StringBuilder();
			param.append("jsonString={\"ttransactionid\":\"").append(ttransactionid).append("\"}");
			String url = propertiesUtil.getChargeCenterurl() + "/tradequery!dnapay";
			logger.info("chargecenterUrl:" + url + "；param=" + param.toString());
			//String result = "{\"buyer_id\":\"2088102680625790\",\"trade_no\":\"2012030267420579\",\"use_coupon\":\"F\",\"subject\":\"如意彩金\",\"is_total_fee_adjust\":\"F\",\"out_trade_no\":\"BJ201203020000000000000003423936\",\"trade_status\":\"交易成功结束\",\"gmt_payment\":\"2012-03-02 11:16:22\",\"discount\":\"0.00\",\"buyer_email\":\"574700498@qq.com\",\"gmt_create\":\"2012-03-02 11:15:19\",\"operator_role\":\"B\",\"price\":\"10.00\",\"to_buyer_fee\":\"0.00\",\"to_seller_fee\":\"10.00\",\"total_fee\":\"10.00\",\"gmt_last_modified_time\":\"2012-03-02 11:16:22\",\"seller_id\":\"2088301685770478\",\"quantity\":\"1\",\"seller_email\":\"chongzhi@ruyicai.com\",\"flag_trade_locked\":\"0\",\"payment_type\":\"商品购买\",\"coupon_used_fee\":\"0.00\", \"errorCode\":\"0\"}";//HttpUtil.post(url, param.toString());
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);			
			if("0".equals(json.getString("errorCode"))) {
				errormsg = "查询成功";
				map = JsonUtil.transferJson2Map(result);
				logger.info("tcharges/dnapay：" + errormsg);
			} else if("500".equals(json.getString("errorCode"))) {
				errormsg = "远程服务器内部错误";
			} else {
				errormsg = "远程服务器后台错误";
			}
		} catch (Exception e) {
			errormsg = "查询失败";
			logger.error("tcharges/dnapay error", e);
		}
		
		view.addObject("ttransactionid", ttransactionid);
		view.addObject("errormsg", errormsg);
		view.addAllObjects(map);
		view.setViewName("tcharges/dnapay");		
		logger.info("tcharges/dnapay end");
		return view;
	}
}
