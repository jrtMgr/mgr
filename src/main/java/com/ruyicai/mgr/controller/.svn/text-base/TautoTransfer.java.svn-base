package com.ruyicai.mgr.controller;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tloguser;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/autoTransfer")
@Controller
public class TautoTransfer {
	
	private Logger logger = Logger.getLogger(TautoTransfer.class);

	@Autowired
	PropertiesUtil propertiesUtil;
	
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		logger.info("autoTransfer/page");
		view.setViewName("autoTransfer/page");
		return view;
	}
	
	@RequestMapping("/transfer")
	public ModelAndView transferMsg(@RequestParam(value = "userno") String userno,
			@RequestParam(value = "amt") BigDecimal amt,			
			@RequestParam(value = "accesstype") Character accesstype,
			@RequestParam(value = "hasactive") String hasactive,
			@RequestParam(value = "presentPercent")String presentPercent,
			@RequestParam(value = "presentAmt") String presentAmt,
			@RequestParam(value = "bankid", required = false, defaultValue = "ryc001") String bankid,
			HttpServletRequest request,ModelAndView view){
		logger.info("autoTransfer/transfer");
		logger.info("输入金额：" + amt + "元");
		String memo = null;
		view.addObject("accesstype",accesstype);
		view.addObject("hasactive",hasactive);
		view.addObject("presentPercent",presentPercent);
		view.addObject("presentAmt",presentAmt);
		view.setViewName("autoTransfer/page");
		Tloguser user = (Tloguser) request.getSession().getAttribute("user");
		String username = user.getNickname();
		String url0 = propertiesUtil.getLotteryurl() + "/taccounts/doDirectChargeProcess";
		String url1 = propertiesUtil.getLotteryurl() + "/taccounts/addDrawAmount";
		StringBuffer url0SB = new StringBuffer("");
		StringBuffer url1SB = new StringBuffer("");
		if(StringUtil.isEmpty(hasactive)){
			view.addObject("errormsg","请选择有无活动");
		} 
		if (hasactive.equals("1")) {
			if(bankid=="yzz001"||bankid.equals("yzz001")){
				memo = "银行转账（操作人:" + username + "）";;
			} else {
				memo = "现金入账（操作人:" + username + "）";
			}
			if (StringUtils.isNotBlank(userno)) {
				String channel = Tuserinfo.findTuserinfo(userno).getChannel();
				String subchannel = Tuserinfo.findTuserinfo(userno).getSubChannel();
				url0SB.append("userno=" + userno);
				url0SB.append("&channel=" + channel);
				url0SB.append("&subchannel=" + subchannel);
			} else{
				view.addObject("errormsg","请将数据填写完整");
				return view;
			}
			if(StringUtils.isNotBlank(amt.toString())){
				url0SB.append("&amt=" + amt.multiply(new BigDecimal(100)).toString());
			}else{
				view.addObject("errormsg","请将数据填写完整");
				return view;
			}
			url0SB.append("&accesstype=" + accesstype);
			url0SB.append("&type=" + new BigDecimal(2));
			url0SB.append("&bankid=" + bankid);
			try {
				url0SB.append("&memo=" + URLEncoder.encode(memo,"utf-8"));
			} catch (UnsupportedEncodingException e1) {
				logger.error("autoTransfer/transfer error:", e1);
			}
			String result0,result1 = null;
			String errorCode0,errorCode1 = null;
			String ttransactionid = null;
			try {
				result0 = HttpUtil.post(url0, url0SB.toString());
				JSONObject json = new JSONObject(result0);
				errorCode0 = (String) json.get("errorCode");
				ttransactionid = (String)json.getString("value");
				if(errorCode0.equals("0")||errorCode0 == "0"){
					logger.info("操作人:" + username + ", 操作:自动转账,转账充值成功");
				}else{
					logger.info("转账充值失败");
					view.addObject("errormsg","转账充值失败");
					return view;
				}
				if(amt.toString().contains(".")){
					Double drawAmtDou = Math.floor(amt.multiply(new BigDecimal(70)).doubleValue());
					logger.info("增加可提现金额：" + drawAmtDou);
					result1 = HttpUtil.post(url1, url1SB.append("userno=" + userno + "&ttransactionid=" + ttransactionid + "&drawamt=" + drawAmtDou).toString());
				}else{
					result1 = HttpUtil.post(url1, url1SB.append("userno=" + userno + "&ttransactionid=" + ttransactionid + "&drawamt=" + amt.multiply(new BigDecimal(70))).toString());
				}
				JSONObject j = new JSONObject(result1);
				errorCode1 = (String)j.getString("errorCode");
				if(errorCode0.equals("0")||errorCode0 == "0"){
					logger.info("操作人:" + username + ", 操作:自动转账,增加可提现70%成功");
				}else{
					logger.info("增加70%可提现金额失败");
					view.addObject("errormsg","增加70%可提现金额值失败");
					return view;
				}
				view.addObject("userno",userno);
				view.addObject("amt",amt);
				view.addObject("errormsg","无活动转账成功");
				return view;
			} catch (Exception e) {
				logger.error("autoTransfer/transfer error:", e);
				view.addObject("errormsg","无活动转账失败");
				return view;
			}
		}
		if(hasactive.equals("2")){
			String amtStr = null;
			if(bankid=="yzz001"||bankid.equals("yzz001")){
				memo = "银行转账（操作人:" + username + "）";
			} else {
				memo = "现金入账（操作人:" + username + "）";
			}
			if(StringUtils.isNotBlank(presentPercent)){
				if(amt.toString().contains(".")){
					Double presentAmtDou = Math.floor(amt.multiply(new BigDecimal(presentPercent).multiply(new BigDecimal(100))).doubleValue());
					logger.info("充值赠送金额：" + presentAmtDou);
					amtStr = new BigDecimal(presentAmtDou) + "";
				}else{
					amtStr = amt.multiply(new BigDecimal(presentPercent).multiply(new BigDecimal(100))) + "";
				}
				/*amtStr = amt.multiply(new BigDecimal(presentPercent)).multiply(new BigDecimal(100)).ROUND_DOWN+"";*/
			}
			if(StringUtils.isNotBlank(presentAmt)){
				amtStr = Integer.parseInt(presentAmt)*100+"";
			}
			if (StringUtils.isNotBlank(userno)) {
				String channel = Tuserinfo.findTuserinfo(userno).getChannel();
				String subchannel = Tuserinfo.findTuserinfo(userno).getSubChannel();
				url0SB.append("userno=" + userno);
				url0SB.append("&channel=" + channel);
				url0SB.append("&subchannel=" + subchannel);
			} else{
				view.addObject("errormsg","请将数据填写完整");
			}
			if(StringUtils.isNotBlank(amt.toString())){
				url0SB.append("&amt=" + amt.multiply(new BigDecimal(100)).toString());
				
			}else{
				view.addObject("errormsg","请将数据填写完整");
			}
			url0SB.append("&accesstype=" + accesstype);
			url0SB.append("&type=" + new BigDecimal(2));
			url0SB.append("&bankid=" + bankid);
			try {
				url0SB.append("&memo=" + URLEncoder.encode(memo, "utf-8"));
			} catch (UnsupportedEncodingException e1) {
				logger.error("autoTransfer/transfer error:", e1);
			}
			String result0,result1 = null;
			String errorCode0,errorCode1 = null;
			try {
				result0 = HttpUtil.post(url0, url0SB.toString());
				JSONObject json = new JSONObject(result0);
				errorCode0 = (String) json.get("errorCode");
				if(errorCode0.equals("0")||errorCode0 == "0"){
					logger.info("操作人:" + username + ", 操作:自动转账,原款充值成功");
				}else{
					logger.info("原款充值失败");
					view.addObject("errormsg","原款充值失败");
					return view;
				}
				url1SB.append("userno=" + userno);
				url1SB.append("&channel=" + Tuserinfo.findTuserinfo(userno).getChannel());
				url1SB.append("&subchannel=" + Tuserinfo.findTuserinfo(userno).getSubChannel());
				url1SB.append("&accesstype=" + accesstype);
				url1SB.append("&type=" + new BigDecimal(23));
				url1SB.append("&bankid=" + "ryc001");
				try {
					url1SB.append("&memo=" + URLEncoder.encode("充值赠送", "utf-8"));
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				url1SB.append("&amt=" + amtStr);
				result1 = HttpUtil.post(url0, url1SB.toString());
				JSONObject json1 = new JSONObject(result1);
				errorCode1 = (String) json1.get("errorCode");
				if(errorCode1.equals("0")||errorCode1 == "0"){
					view.addObject("errormsg","有活动转账成功");
					logger.info("操作人:" + username + ", 操作:自动转账,充值赠送成功");
				}else{
					logger.info("充值赠送失败");
					view.addObject("errormsg","充值赠送失败");
					return view;
				}
			}catch (Exception e) {
				logger.error("autoTransfer/transfer error:", e);
				view.addObject("errormsg","无活动转账失败");
				return view;
			}
		}
		return view;
	}
	
}
