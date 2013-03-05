package com.ruyicai.mgr.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.consts.CashDetailState;
import com.ruyicai.mgr.consts.CashDetailType;
import com.ruyicai.mgr.consts.Const;
import com.ruyicai.mgr.domain.Tcashdetail;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.domain.TwithdrawReview;
import com.ruyicai.mgr.util.DateUtil;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping(value = "/tcash")
@Controller()
public class TcashController {

	private Logger logger = Logger.getLogger(TcashController.class);

	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		view.addObject("starttime", DateUtil.format("yyyy-MM-dd", new Date()));
		view.addObject("state", 1);
		view.setViewName("tcash/list");
		return view;
	}
	
	@RequestMapping("/list")
	public ModelAndView tcashlist(
			@RequestParam(value = "userno", required = false) String userno,
			@RequestParam(value = "usertype", required = false) String usertype,
			@RequestParam(value = "userid", required = false) String userid,
			@RequestParam(value = "subchannel", required = false) String subchannel,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "starttime2", required = false) String starttime2,
			@RequestParam(value = "endtime2", required = false) String endtime2,
			@RequestParam(value = "state", required = false) BigDecimal state,
			@RequestParam(value = "type", required = false) BigDecimal type,
			@RequestParam(value = "amt1", required = false) BigDecimal amt1,
			@RequestParam(value = "amt2", required = false) BigDecimal amt2,
			@RequestParam(value = "sort", required = false) BigDecimal sort,
			@RequestParam(value = "ttransactionid", required = false) String ttransactionid,
			@RequestParam(value = "bankaccount", required = false) String bankaccount,
			@ModelAttribute("page") Page<Tcashdetail> page, ModelAndView view) {
		logger.info("/tcash/list");
		try {
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			if (!StringUtil.isEmpty(usertype) && !StringUtil.isEmpty(userid)) {
				Tuserinfo tuserinfo = null;
				try {
					if (Const.Tuserinfo_Mobileid.equals(usertype)) {
						tuserinfo = Tuserinfo.findTuserinfoesByMobileid(userid,subchannel);
					} else if (Const.Tuserinfo_Email.equals(usertype)) {
						tuserinfo = Tuserinfo.findTuserinfoesByEmail(userid,subchannel);
					} else if (Const.Tuserinfo_UserName.equals(usertype)) {
						tuserinfo = Tuserinfo.findTuserinfoesByUserName(userid,subchannel);
					}
				} catch (Exception exception) {
					throw new RuntimeException("用户不存在");
				}
				if (null == tuserinfo) {
					throw new RuntimeException("用户不存在");
				}
				builder.append(" o.userno=? and");
				params.add(tuserinfo.getUserno());
				view.addObject("usertype", usertype);
				view.addObject("userid", userid);
			}	
			
			if (!StringUtil.isEmpty(userno)) {
				builder.append(" o.userno=? and");
				params.add(userno);
			}
			
			if (!StringUtil.isEmpty(starttime)) {
				builder.append(" to_char(o.plattime, 'yyyy-mm-dd') >= ? and");
				params.add(starttime);
				view.addObject("starttime", starttime);
			}
			if (!StringUtil.isEmpty(endtime)) {
				builder.append(" to_char(o.plattime, 'yyyy-mm-dd') <= ? and");
				params.add(endtime);
			}
			if (!StringUtil.isEmpty(starttime2)) {
				builder.append(" to_char(o.modifytime, 'yyyy-mm-dd') >= ? and");
				params.add(starttime2);
			}
			if (!StringUtil.isEmpty(endtime2)) {
				builder.append(" to_char(o.modifytime, 'yyyy-mm-dd') <= ? and");
				params.add(endtime2);
			}
			if (null != state) {
				if (state.equals(BigDecimal.ONE)) {
					builder.append(" o.state = 1 and");
				} else if (state.equals(new BigDecimal(103))) {
					builder.append(" o.state = 103 and");
				} else if (state.equals(new BigDecimal(104))) {
					builder.append(" o.state = 104 and");
				} else if (state.equals(new BigDecimal(105))) {
					builder.append(" o.state = 105 and");
				} else if (state.equals(new BigDecimal(106))) {
					builder.append(" o.state = 106 and");
				}
				view.addObject("state", state);
			}
			if (!StringUtil.isEmpty(ttransactionid)) {
				ttransactionid = ttransactionid.trim();
				builder.append(" o.ttransactionid = ? and");
				params.add(ttransactionid);
			}
			if (!StringUtil.isEmpty(bankaccount)) {
				bankaccount = bankaccount.trim();
				builder.append(" o.bankaccount = ? and");
				params.add(bankaccount);
			}
			
			if (type != null) {
				builder.append(" o.type = ? and");
				params.add(type);
			}
			if (amt1 != null) {
				builder.append(" o.amt >= ? and");
				params.add(amt1);
			}
			if (amt2 != null) {
				builder.append(" o.amt <= ? and");
				params.add(amt2);
			}
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			
			String orderBy = " order by o.plattime desc";
			if (sort != null) {
				if (sort.equals(BigDecimal.ONE)) {
					 orderBy = " order by o.plattime desc";
				} else if (sort.equals(new BigDecimal(2))) {
					 orderBy = " order by o.modifytime desc";
				}
			}
			if (!StringUtil.isEmpty(builder.toString()))
				Tcashdetail.findList(builder.toString(), orderBy, params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tcash/list", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.addObject("userno", userno);
		view.setViewName("/tcash/list");
		return view;
	}
	
	@RequestMapping("/findCashById")
	public ModelAndView findCashById(
		@RequestParam("id") String id, ModelAndView view) {
		Tcashdetail t = null;
		try {
			t = Tcashdetail.findTcashdetail(id);
		} catch (Exception e) {
			logger.error("/tcash/editUI", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.addObject("tcashdetail", t);
		if (null!=t) {
			view.addObject("type", t.getType().toString());
			view.addObject("state", "1");
		}
		view.setViewName("/tcash/editUI");
		return view;
	}
	
	@RequestMapping("/findCashById2")
	public ModelAndView findCashById2(
		@RequestParam("id") String id, ModelAndView view) {
		Tcashdetail t = null;
		try {
			t = Tcashdetail.findTcashdetail(id);
		} catch (Exception e) {
			logger.error("/tcash/editUI", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.addObject("tcashdetail", t);
		if (null!=t) {
			view.addObject("type", t.getType().toString());
			view.addObject("state", "104");
		}
		view.setViewName("/tcash/editUI2");
		return view;
	}
	@RequestMapping(value = "/editCash", method = RequestMethod.POST)
	public ModelAndView editCash(
		@RequestParam("id") String id, 
		@RequestParam("name") String name, 
		@RequestParam("bankaccount") String bankaccount, 
		@RequestParam("bankname") String bankname, 
		@RequestParam("provname") String provname, 
		@RequestParam("areaname") String areaname, 
		@RequestParam("state") BigDecimal state, 
		@RequestParam(value = "rejectReason", required=false) String rejectReason, 
		ModelAndView view) {
		String errormsg = "修改成功";
		try {
			Tcashdetail t = Tcashdetail.findTcashdetail(id);
			if (t.getState().equals(CashDetailState.Bohui.value()) || t.getState().equals(CashDetailState.Chenggong.value()) || 
					t.getState().equals(CashDetailState.Quxiao.value())) {
				errormsg = "状态为执行中或取消不允许修改";
			}else if(state.equals(CashDetailState.Bohui.value())){
				t.setModifytime(new Date());
				t.setRejectReason(rejectReason);
				t.merge();
				
				String url = propertiesUtil.getLotteryurl() + "/taccounts/bohuiTcashDetail";
				String result = HttpUtil.post(url, "cashdetailId="+t.getId()+"&rejectreason="+URLEncoder.encode(rejectReason, "UTF-8"));
				JSONObject json = new JSONObject(result);
				if("0".equals(json.getString("errorCode"))) {
					errormsg = "驳回成功";				
					logger.info("taccounts/bohuiTcashDetail：" + errormsg);
				} else if("500".equals(json.getString("errorCode"))) {
					errormsg = "远程服务器内部错误";
				} 
			}else{
				boolean flag = false;
				if (!t.getName().equals(name)) {
					t.setName(name);
					flag = true;
				}
				if (!t.getBankaccount().equals(bankaccount)) {
					t.setBankaccount(bankaccount);
					flag = true;
				}
				if (!t.getBankname().equals(bankname)) {
					t.setBankname(bankname);
					flag = true;
				}
				if (!t.getProvname().equals(provname)) {
					t.setProvname(provname);
					flag = true;
				}
				if (!t.getAreaname().equals(areaname)) {
					t.setAreaname(areaname);
					flag = true;
				}
				if (flag) {
					TwithdrawReview tw = new TwithdrawReview();
					tw.setAccountName(t.getName());
					tw.setTransactionId(t.getTtransactionid());
					tw.setAccountNumber(t.getBankaccount());
					tw.setBankProvince(t.getProvname());
					tw.setBankCity(t.getAreaname());
					tw.setBankName(t.getBankname());
					tw.setAmt(t.getAmt().doubleValue());
					tw.setState(t.getState().intValue());
					tw.setDateTime(new Date());
					tw.setSubBankName(t.getSubbankname());
					tw.persist();
				}
				if (!t.getState().equals(state)) {
					t.setModifytime(new Date());
					t.setState(state);
					flag = true;
				}
				if (flag) {
					t.merge();
				}else {
					errormsg = "没有修改";
				}
			}
			
			if (t.getState().equals(CashDetailState.Yishenghe.value())) {
				List<Tcashdetail> tcashdetails = new ArrayList<Tcashdetail>();
				tcashdetails.add(t);
				this.sendMail(tcashdetails);
			}
			
		} catch (Exception e) {
			logger.error("tcash/findCashById", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.findCashById(id, view);
	}
	
	private void sendMail(List<Tcashdetail> tcashdetails) {
		try {
			if (tcashdetails.size() == 0) {
				return;
			}
			String subject = "已审核的提现用户提现信息";
			StringBuffer content = new StringBuffer();
			int i = 1;
			for (Tcashdetail tcashdetail : tcashdetails) {
				content.append(i).append("、用户编号:")
						.append(tcashdetail.getUserno()).append("；姓名：")
						.append(tcashdetail.getName()).append("；提现金额：")
						.append(tcashdetail.getAmt()).append("分；手续费：")
						.append(tcashdetail.getFee()).append("分；开户银行名称：")
						.append(tcashdetail.getBankname())
						.append("；银行帐号或支付宝帐号：")
						.append(tcashdetail.getBankaccount())
						.append("；开户行所在地：").append(tcashdetail.getAreaname())
						.append("；提现方式：")
						.append(CashDetailType.getMemo(tcashdetail.getType()))
						.append("；提现ID：").append(tcashdetail.getId())
						.append("；交易ID：")
						.append(tcashdetail.getTtransactionid()).append("<br>");
				i++;
			}
			String to = propertiesUtil.getDrawcashMailToUsers();
			String url =  propertiesUtil.getMsgcenterurl() + "/mail/sendMail";
			StringBuffer params = new StringBuffer();
			params.append("to=").append(to).append("&subject=").append(subject)
					.append("&content=").append(content);
			logger.info("url=" + url + "；params=" + params.toString());
			String result = HttpUtil.post(url, params.toString());
			logger.info("发送已审核提现信息的result=" + result);
		} catch (Exception e) {
			logger.error("tcash/list", e);
			logger.error("drawcash sendMail error:", e);
		}
	}
	
	@RequestMapping("/appromore")
	public ModelAndView appro(HttpServletRequest request, ModelAndView view){
		logger.info("newsappro/appromore");
		String errormsg = "";
		try{
			List<Tcashdetail> tcashdetails = new ArrayList<Tcashdetail>();
			
			String[] approvalMoreIds = request.getParameterValues("checkboxname");
			for(int i=0;i<approvalMoreIds.length;i++){
				Tcashdetail tcashdetail= Tcashdetail.findTcashdetail(approvalMoreIds[i]);
				if(tcashdetail.getState().equals(CashDetailState.Tixian.value())){
					tcashdetail.setState(CashDetailState.Yishenghe.value());
					tcashdetail.setModifytime(new Date());
					tcashdetail.merge();
					
					tcashdetails.add(tcashdetail);
				}else{
					errormsg += approvalMoreIds[i]+"状态不是待审核。";
				}
			}			
			this.sendMail(tcashdetails);
		} catch (Exception e) {
			logger.error("tcash/appromore", e);
		}
		if(StringUtil.isEmpty(errormsg)){
			errormsg = "审批成功";
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("/tcash/list");
		return view;
	}
	
	@RequestMapping("/generateReport")
	public void generateReport(
			@RequestParam(value = "userno", required = false) String userno,
			@RequestParam(value = "usertype", required = false) String usertype,
			@RequestParam(value = "userid", required = false) String userid,
			@RequestParam(value = "subchannel", required = false) String subchannel,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "starttime2", required = false) String starttime2,
			@RequestParam(value = "endtime2", required = false) String endtime2,
			@RequestParam(value = "state", required = false) BigDecimal state,
			@RequestParam(value = "type", required = false) BigDecimal type,
			@RequestParam(value = "amt1", required = false) BigDecimal amt1,
			@RequestParam(value = "amt2", required = false) BigDecimal amt2,
			@RequestParam(value = "sort", required = false) BigDecimal sort,
			@RequestParam(value = "ttransactionid", required = false) String ttransactionid,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("/generateReport/list");
		OutputStream os = null;
		WritableWorkbook wwb = null;
		try {
			
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			
			
			if (!StringUtil.isEmpty(usertype) && !StringUtil.isEmpty(userid)) {
				Tuserinfo tuserinfo = null;
				try {
					if (Const.Tuserinfo_Mobileid.equals(usertype)) {
						tuserinfo = Tuserinfo.findTuserinfoesByMobileid(userid,subchannel);
					} else if (Const.Tuserinfo_Email.equals(usertype)) {
						tuserinfo = Tuserinfo.findTuserinfoesByEmail(userid,subchannel);
					} else if (Const.Tuserinfo_UserName.equals(usertype)) {
						tuserinfo = Tuserinfo.findTuserinfoesByUserName(userid,subchannel);
					}
				} catch (Exception exception) {
					throw new RuntimeException("用户不存在");
				}
				if (null == tuserinfo) {
					throw new RuntimeException("用户不存在");
				}
				builder.append(" o.userno=? and");
				params.add(tuserinfo.getUserno());
			}
			
			
			if (!StringUtil.isEmpty(userno)) {
				builder.append(" o.userno=? and");
				params.add(userno);
			}
			
			if (!StringUtil.isEmpty(starttime)) {
				builder.append(" to_char(o.plattime, 'yyyy-mm-dd') >= ? and");
				params.add(starttime);
			}
			if (!StringUtil.isEmpty(endtime)) {
				builder.append(" to_char(o.plattime, 'yyyy-mm-dd') <= ? and");
				params.add(endtime);
			}
			if (!StringUtil.isEmpty(starttime2)) {
				builder.append(" to_char(o.modifytime, 'yyyy-mm-dd') >= ? and");
				params.add(starttime2);
			}
			if (!StringUtil.isEmpty(endtime2)) {
				builder.append(" to_char(o.modifytime, 'yyyy-mm-dd') <= ? and");
				params.add(endtime2);
			}
			if (null != state) {
				if (state.equals(BigDecimal.ONE)) {
					builder.append(" o.state = 1 and");
				} else if (state.equals(new BigDecimal(103))) {
					builder.append(" o.state = 103 and");
				} else if (state.equals(new BigDecimal(104))) {
					builder.append(" o.state = 104 and");
				} else if (state.equals(new BigDecimal(105))) {
					builder.append(" o.state = 105 and");
				} else if (state.equals(new BigDecimal(106))) {
					builder.append(" o.state = 106 and");
				}
			}
			if (type != null) {
				if (type.equals(new BigDecimal(2))) {
					//throw new RuntimeException("支付宝提现不用导出");
				}
				builder.append(" o.type = ? and");
				params.add(type);
			}
			if (amt1 != null) {
				builder.append(" o.amt >= ? and");
				params.add(amt1);
			}
			if (amt2 != null) {
				builder.append(" o.amt <= ? and");
				params.add(amt2);
			}
			if (!StringUtil.isEmpty(ttransactionid)) {
				builder.append(" o.ttransactionid = ? and");
				params.add(ttransactionid);
			}
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			String orderBy = " order by o.plattime desc";
			if (sort != null) {
				if (sort.equals(BigDecimal.ONE)) {
					 orderBy = " order by o.plattime desc";
				} else if (sort.equals(new BigDecimal(2))) {
					 orderBy = " order by o.modifytime desc";
				}
			}
			if (!StringUtil.isEmpty(builder.toString())){
				List<Tcashdetail> list = Tcashdetail.findList(builder.toString(),orderBy, params);
				os = response.getOutputStream();
				wwb = Workbook.createWorkbook(os);
				response.setContentType("application/dowload");
				response.setHeader("Content-disposition", "attachment;filename=\""
						+ new String((java.net.URLEncoder.encode("提现信息报表.xls",	"UTF-8")).getBytes("UTF-8"), "GB2312") + "\"");
				
				// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
				WritableSheet ws = wwb.createSheet("sheet1", 0);
		  
				ws.setColumnView(0, 10);
				ws.setColumnView(1, 40);
				ws.setColumnView(2, 10);
				ws.setColumnView(3, 15);
				ws.setColumnView(4, 10);
				ws.setColumnView(5, 25);
				ws.setColumnView(6, 20);
				ws.setColumnView(7, 25);
				ws.setColumnView(8, 8);
				
				
				ws.addCell(new Label(0, 0, "用户编号"));
				ws.addCell(new Label(1, 0, "交易ID"));
				ws.addCell(new Label(2, 0, "用户名"));
				ws.addCell(new Label(3, 0, "提现金额(元)"));
				ws.addCell(new Label(4, 0, "手续费(元)"));
				ws.addCell(new Label(5, 0, "提现时间"));
				ws.addCell(new Label(6, 0, "开户名称"));
				ws.addCell(new Label(7, 0, "银行账号"));
				ws.addCell(new Label(8, 0, "状态"));
				
				
				BigDecimal amtAll = BigDecimal.ZERO;
				BigDecimal feeAll = BigDecimal.ZERO;
				
				for (int i = 0; i < list.size(); i++) {
					Tcashdetail t = list.get(i);
					int j = i+1;
			        ws.addCell(new Label(0, j, t.getUserno()));   
			        ws.addCell(new Label(1, j, t.getTtransactionid()));
			        ws.addCell(new Label(2, j, t.getName()));
			        ws.addCell(new Label(3, j, t.getAmt().divide(new BigDecimal(100)).toString()));
			        amtAll = amtAll.add(t.getAmt());
			        ws.addCell(new Label(4, j, t.getFee().divide(new BigDecimal(100)).toString()));
			        feeAll = feeAll.add(t.getFee());
			        ws.addCell(new Label(5, j, t.getPlattime().toString()));
			        ws.addCell(new Label(6, j, t.getBankname()));
			        ws.addCell(new Label(7, j, t.getBankaccount()));
			        ws.addCell(new Label(8, j, CashDetailState.getMemo(t.getState())));
				}
				
				int k = list.size()+1;
				ws.addCell(new Label(0, k, "合计"));
		        ws.addCell(new Label(3, k, amtAll.divide(new BigDecimal(100)).toString()));
		        ws.addCell(new Label(4, k, feeAll.divide(new BigDecimal(100)).toString()));
		        ws.setProtected(true);
		        //wwb.setProtected(false);
		 
				wwb.write();   
			}
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
	
	@RequestMapping("/cashMore")
	public ModelAndView cashMore(HttpServletRequest request, ModelAndView view){
		logger.info("tcash/cashMore");
		String result = "";
		String errormsg = "";
		try{
			String[] approvalMoreIds = request.getParameterValues("checkboxname2");
			String ids = "";
			for(int i=0;i<approvalMoreIds.length;i++){
				if (i>0) {
					ids += "|";
				}
				ids += approvalMoreIds[i];
			}
			String url = propertiesUtil.getChargeCenterurl()+"/batchpay!alipay?jsonString={'ids':'"+ids+"'}";
			result = HttpUtil.getResultMessage(url);
			logger.info("访问"+url+"。返回：" + result);
			JSONObject jo = new JSONObject(result);
			view.addObject("jo", jo);
			String errorCode = jo.has("errorCode") ? jo.getString("errorCode") : "";
			if (StringUtil.isEmpty(errorCode)) {
				errormsg = "服务器内部错误";
			} else {
				if(!(ErrorCode.OK.value).equals(jo.getString("errorCode"))) {
					errormsg = ErrorCode.get(errorCode).memo;
				}
			}
			view.addObject("errormsg", errormsg);
		} catch (Exception e) {
			logger.error("/tcash/cashMore", e);
			view.addObject("errormsg", e.getMessage());
		}		
		view.setViewName("/tcash/tozhifubao");
		return view;
	}
	
	@RequestMapping("/batchpay")
	public ModelAndView batchpay(
		@RequestParam("id") String id, ModelAndView view) {
		String result = "";
		String errormsg = "";
		try {
			String url = propertiesUtil.getChargeCenterurl()+"/batchpay!alipay?jsonString={'ids':'"+id+"'}";
			result = HttpUtil.getResultMessage(url);
			logger.info("访问"+url+"。返回：" + result);
			JSONObject jo = new JSONObject(result);
			view.addObject("jo", jo);
			String errorCode = jo.has("errorCode") ? jo.getString("errorCode") : "";
			if (StringUtil.isEmpty(errorCode)) {
				errormsg = "服务器内部错误";
			} else {
				if(!(ErrorCode.OK.value).equals(jo.getString("errorCode"))) {
					errormsg = ErrorCode.get(errorCode).memo;
				}
			}
			view.addObject("errormsg", errormsg);	
		} catch (Exception e) {
			logger.error("tcash/tozhifubao", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("/tcash/tozhifubao");
		return view;
	}
}
