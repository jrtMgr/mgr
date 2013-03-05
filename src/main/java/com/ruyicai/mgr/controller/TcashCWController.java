package com.ruyicai.mgr.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.consts.CashDetailState;
import com.ruyicai.mgr.domain.Tcashdetail;
import com.ruyicai.mgr.domain.Tcashrecord;
import com.ruyicai.mgr.domain.Tcashrecordcfg;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.SMSUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping(value = "/tcashCW")
@Controller()
public class TcashCWController {

	private Logger logger = Logger.getLogger(TcashCWController.class);
	
	@Autowired
	private PropertiesUtil propertiesUtil;
	@RequestMapping("/exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出方法开始");
		OutputStream os = null;
		WritableWorkbook wwb = null;
		try {
			List<Tcashdetail> list = Tcashdetail.selectAuditedTcashDetail();
			if (list.size() == 0) {
				response.getWriter().print("没有可导出数据");
				return;
			}
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition", "attachment;filename=\"" + new String((java.net.URLEncoder.encode("支付宝批量付款.xls",
							"UTF-8")).getBytes("UTF-8"), "GB2312") + "\"");
			os = response.getOutputStream();
			wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("sheet1", 0);
			for (int i = 0; i < 1000; i++) {
				ws.setColumnView(i, 20);
			}
			WritableFont wf = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.NO_BOLD, false);
			WritableCellFormat wcfF = new jxl.write.WritableCellFormat(wf,NumberFormats.TEXT);
			// 准备设置excel工作表的标题
			String[] title1 = { "日期", "总金额", "总笔数", "支付宝帐号(Email)" };
			for (int i = 0; i < title1.length; i++) {
				// 在Excel中，第一个参数表示列，第二个表示行
				Label label = new Label(i, 0, title1[i], wcfF);
				ws.addCell(label);
			}
		
			BigDecimal sumAtm = BigDecimal.ZERO;
			for (Tcashdetail tr : list) {
				sumAtm = sumAtm.add(tr.getAmt());
			}
			String[] title2 = { getDate(), sumAtm.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString(), Integer.toString(list.size()), "tixian@ruyicai.com" };
		
			for (int i = 0; i < title2.length; i++) {
				Label label = new Label(i, 1, title2[i], wcfF);
				if (i == 1) {
					jxl.write.Number labelNF = new jxl.write.Number(i, 1, Double.parseDouble(title2[i]));
					try {
						ws.addCell(labelNF);
						continue;
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					}
				}
				if (i==3) { 
					try {
						WritableHyperlink link = new WritableHyperlink(i, 1, new URL(" mailto:tixian@ruyicai.com"));
						 link.setDescription("tixian@ruyicai.com");
						ws.addHyperlink(link);
						continue;
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}
				ws.addCell(label);
			}
		
			String[] title3 = {"商户流水号","收款银行户名", "收款银行帐号", "收款开户银行",
					"收款银行所在省份", "收款银行所在市", "收款支行名称", "金额", "对公对私标志", "备注" };
			for (int i = 0; i < title3.length; i++) {
				Label label = new Label(i, 2, title3[i], wcfF);
				ws.addCell(label);
			}
			
			
			Tcashrecord tcr = new Tcashrecord();
			tcr.setDcdate(new Date());
			tcr.persist();
			BigDecimal sumamt = new BigDecimal(0);
			int count = 3;
			for (int i = 0; i < list.size(); i++) {
				Tcashdetail tr = list.get(i);
				String[] title4 = {Integer.toString(i+1),tr.getName(), tr.getBankaccount(), tr.getBankname(),
						"", "",	"", tr.getAmt().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).toString(),
						Integer.toString(2), "如意彩金提现"};
				for (int j = 0; j < title4.length; j++) {
					Label label = new Label(j, count, title4[j], wcfF);
					ws.addCell(label);
				}
				count ++;
				
				tr.setState(CashDetailState.Chenggong.value());
				tr.merge();
				
				Tcashrecordcfg tcrc = new Tcashrecordcfg();
				tcrc.setNum(i+1);
				tcrc.setCashrecordid(tcr.getId());
				tcrc.setTtransactionid(tr.getTtransactionid());
				tcrc.setName(tr.getName());
				tcrc.setAmt(tr.getAmt().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP));
				tcrc.persist();
				sumamt = sumamt.add(tr.getAmt());
			}
			tcr.setAmt(sumamt);
			tcr.merge();
			
			wwb.write();
		} catch (Exception e) {
			logger.error("tcash/exportExcel", e);
		} finally {
			if (wwb != null) {
				try {
					wwb.close();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@RequestMapping("/exportExcelagain")
	public void exportExcelagain(@RequestParam("tcashrecordid") BigDecimal cashrecordid,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出方法开始");
		OutputStream os = null;
		WritableWorkbook wwb = null;
		try {
			List<Tcashrecordcfg> list2 =  Tcashrecordcfg.findTcashrecordcfgsByCashrecordid(cashrecordid);
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition", "attachment;filename=\"" + new String((java.net.URLEncoder.encode("支付宝批量付款.xls",
							"UTF-8")).getBytes("UTF-8"), "GB2312") + "\"");
			os = response.getOutputStream();
			wwb = Workbook.createWorkbook(os);
			WritableSheet ws = wwb.createSheet("sheet1", 0);
			for (int i = 0; i < 1000; i++) {
				ws.setColumnView(i, 20);
			}
			WritableFont wf = new jxl.write.WritableFont(WritableFont.createFont("宋体"), 11, WritableFont.NO_BOLD, false);
			WritableCellFormat wcfF = new jxl.write.WritableCellFormat(wf,NumberFormats.TEXT);
			// 准备设置excel工作表的标题
			String[] title1 = { "日期", "总金额", "总笔数", "支付宝帐号(Email)" };
			for (int i = 0; i < title1.length; i++) {
				// 在Excel中，第一个参数表示列，第二个表示行
				Label label = new Label(i, 0, title1[i], wcfF);
				ws.addCell(label);
			}
		
			BigDecimal sumAtm = BigDecimal.ZERO;
			for (Tcashrecordcfg tr : list2) {
				sumAtm = sumAtm.add(tr.getAmt());
			}
			String[] title2 = { getDate(), sumAtm.toString(), Integer.toString(list2.size()), "tixian@ruyicai.com" };
		
			for (int i = 0; i < title2.length; i++) {
				Label label = new Label(i, 1, title2[i], wcfF);
				if (i == 1) {
					jxl.write.Number labelNF = new jxl.write.Number(i, 1, Double.parseDouble(title2[i]));
					try {
						ws.addCell(labelNF);
						continue;
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					}
				}
				if (i==3) { 
					try {
						WritableHyperlink link = new WritableHyperlink(i, 1, new URL(" mailto:tixian@ruyicai.com"));
						 link.setDescription("tixian@ruyicai.com");
						ws.addHyperlink(link);
						continue;
					} catch (RowsExceededException e) {
						e.printStackTrace();
					} catch (WriteException e) {
						e.printStackTrace();
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
				}
				ws.addCell(label);
			}
			String[] title3 = {"商户流水号","收款银行户名", "收款银行帐号", "收款开户银行",
					"收款银行所在省份", "收款银行所在市", "收款支行名称", "金额", "对公对私标志", "备注" };
			for (int i = 0; i < title3.length; i++) {
				Label label = new Label(i, 2, title3[i], wcfF);
				ws.addCell(label);
			}
			int count = 3;
			for (int i = 0; i < list2.size(); i++) {
				Tcashrecordcfg tr2 = list2.get(i);
				Tcashdetail tr = Tcashdetail.getTcashdetailByTransactionId(tr2.getTtransactionid());
				String[] title4 = {Integer.toString(i+1),tr.getName(), tr.getBankaccount(), tr.getBankname(),
						"", "",	"", (tr.getAmt().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)).toString(),
						Integer.toString(2), "如意彩金提现"};
				for (int j = 0; j < title4.length; j++) {
					Label label = new Label(j, count, title4[j], wcfF);
					ws.addCell(label);
				}
				count ++;
			}
			wwb.write();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (wwb != null) {
				try {
					wwb.close();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// 得到当前时间
	private String getDate() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DATE);
		return c.get(Calendar.YEAR) + "" + (month >= 10 ? month : ("0" + month))
				+ "" + (day >= 10 ? day : ("0" + day));
	}
	
	@RequestMapping("/page")
	public ModelAndView page(@ModelAttribute("page") Page<Tcashrecord> page, ModelAndView view){
		try {
			Tcashrecord.findList(page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tcashCW/page", e);
		}
		view.setViewName("tcashCW/financeVerify");
		return view;
	}
	
	@RequestMapping("/tcashrecordcfg")
	public ModelAndView page(@RequestParam("tcashrecordid") BigDecimal tcashrecordid,
			ModelAndView view){
		try {
			view.addObject("list", Tcashrecordcfg.findTcashrecordcfgsByCashrecordid(tcashrecordid));
		} catch (Exception e) {
			logger.error("tcashCW/tcashrecordcfg", e);
		}
		view.setViewName("tcashCW/tcrc");
		return view;
	}

	@RequestMapping("/chexiao")
	public ModelAndView chexiao(@RequestParam("ttransactionid") String ttransactionid,
			@RequestParam("tcashrecordid") BigDecimal tcashrecordid,
			ModelAndView view){
		String errormsg = "撤销成功";
		try {
			Tcashdetail t = Tcashdetail.getTcashdetailByTransactionId(ttransactionid.trim());
			if (!CashDetailState.Chenggong.value().equals(t.getState())) {
				errormsg = "状态不是成功不允许撤销";
			}else{
				t.setModifytime(new Date());
				t.setState(CashDetailState.Yishenghe.value());
				t.merge();
			}
		} catch (Exception e) {
			logger.error("tcashCW/chexiao", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.page(tcashrecordid, view);
	}
	
	public static Map<Integer, String> map = new HashMap<Integer, String>();
	static{
		map.put(1, "银行账号不存在");
		map.put(2, "账户姓名和银行开户姓名不符");
		map.put(3, "处理失败,账号有误,请更换");
		map.put(4, "暂不支持该银行提现");
	}
	@Autowired
	private SMSUtil smsUtil;
	
	@RequestMapping("/bohui")
	public ModelAndView bohui(@RequestParam("ttransactionid") String ttransactionids,
			@RequestParam("rejectReason") Integer rejectReason,
			@RequestParam("tcashrecordid") BigDecimal tcashrecordid,
			ModelAndView view){
		String errormsg = "";
		try {
			String urlStr = propertiesUtil.getLotteryurl()+"/taccounts/bohuiTcashDetail";
			logger.info("ttransactionId=" + ttransactionids);
			String[] ts = ttransactionids.split(",");
			for (String ttransactionid : ts) {
				Tcashdetail tcashdetail =Tcashdetail.getTcashdetailByTransactionId(ttransactionid.trim());
	            if (tcashdetail == null) {
	            	logger.info("提现明细表为空,transactionId="+ttransactionid);
	            	view.addObject("errormsg",ttransactionid+"没有对应记录");
					return this.page(tcashrecordid, view);
	            }
	            if (!CashDetailState.Chenggong.value().equals(tcashdetail.getState())) {
					view.addObject("errormsg",ttransactionid+"状态不是成功不允许驳回");
					return this.page(tcashrecordid, view);
				}
			}
			
			for (String ttransactionid : ts) {
				Tcashdetail tcashdetail =Tcashdetail.getTcashdetailByTransactionId(ttransactionid.trim());
				String result = HttpUtil.post(urlStr, "cashdetailId="+tcashdetail.getId()+"&rejectreason="+URLEncoder.encode(map.get(rejectReason), "UTF-8"));
				logger.info("驳回提现返回结果,result="+result+",ttransactionId="+ttransactionid);
				JSONObject json = new JSONObject(result);
				if(!"0".equals(json.getString("errorCode"))) {
					logger.info("taccounts/bohuiTcashDetail：" + errormsg);
					errormsg += ttransactionid+"驳回失败。";
				}else{
					//发送短信
					String mobileid = Tuserinfo.findTuserinfo(tcashdetail.getUserno()).getMobileid();
					if(mobileid != null){
						logger.info("体现失败发送短信发送短信");
						smsUtil.sendSMSNotry(mobileid, "如意彩提现失败，"+map.get(rejectReason));
					}
				}
			}
		} catch (Exception e) {
			logger.error("tcashCW/bohui", e);
			errormsg = e.getMessage();
		}
		if (StringUtil.isEmpty(errormsg)) {
			errormsg = "驳回成功";
		}
		view.addObject("errormsg", errormsg);
		return this.page(tcashrecordid, view);
	}
}
