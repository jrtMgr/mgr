package com.ruyicai.mgr.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Tcwtj;
import com.ruyicai.mgr.util.StringUtil;
@RequestMapping("/tcwtj")
@Controller
public class TcwtjController {
	private Logger logger = Logger.getLogger(TcwtjController.class);
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "date", required = false) String date, 
			ModelAndView view) {
		logger.info("/tcwtj/list");
		try {
			if(StringUtil.isEmpty(date)) {
				date = new SimpleDateFormat("yyyy-MM").format(new Date());
			}
			view.addObject("list", Tcwtj.findByDate(date));
		} catch (Exception e) {
			logger.error("tcwtj/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.addObject("date", date);
		view.setViewName("tcwtj/list");
		return view;
	}
	
	@RequestMapping("/editUI")
	public ModelAndView editUI(
			@RequestParam(value = "id", required = true) BigDecimal id, 
			@RequestParam(value = "date", required = true) String date, 
			ModelAndView view) {
		logger.info("/tcwtj/editUI");
		try {
			view.addObject("tcwtj", Tcwtj.findTcwtj(id));
		} catch (Exception e) {
			logger.error("tcwtj/editUI error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.addObject("date", date);
		view.setViewName("tcwtj/editUI");
		return view;
	}
	@RequestMapping("/edit")
	public ModelAndView edit(
			@ModelAttribute(value="tcwtj") Tcwtj tcwtj,
			@RequestParam(value = "date", required = true) String date, 
			ModelAndView view) {
		logger.info("/tcwtj/edit");
		try {
			Tcwtj t = Tcwtj.findTcwtj(tcwtj.getId());
			t.setUsercharge(tcwtj.getUsercharge());
			t.setChannelcharge(tcwtj.getChannelcharge());
			t.setEncash(tcwtj.getEncash());
			t.setActivitypersent(tcwtj.getActivitypersent());
			t.setChargepersent(tcwtj.getChargepersent());
			t.setCannel(tcwtj.getCannel());
			t.setInhj(tcwtj.getInhj());
			
			t.setUserbet(tcwtj.getUserbet());
			t.setChannelbet(tcwtj.getChannelbet());
			t.setDraw(tcwtj.getDraw());
			t.setFee(tcwtj.getFee());
			t.setOther1(tcwtj.getOther1());
			t.setOuthj(tcwtj.getOuthj());
			t.setBalance(tcwtj.getBalance());
			t.setMemo(tcwtj.getMemo());
			
			t.merge();
		} catch (Exception e) {
			logger.error("tcwtj/edit error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.addObject("errormsg", "保存成功");
		return this.list(date, view);
	}
	
	/*
	 * 生成报表 
	 */
	@RequestMapping("/generateReport")
	public void generateReport(@RequestParam(value = "date", required = false) String date, 
			HttpServletRequest request, HttpServletResponse response)throws IOException {
		OutputStream os = null;
		WritableWorkbook wwb = null;
		try {
			if(StringUtil.isEmpty(date)) {
				date = new SimpleDateFormat("yyyy-MM").format(new Date());
			}
			List<Tcwtj> cwtjs =  Tcwtj.findByDate(date);
			os = response.getOutputStream();
			wwb = Workbook.createWorkbook(os);
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition", "attachment;filename=\""
					+ new String((java.net.URLEncoder.encode(date+"财务报表.xls",	"UTF-8")).getBytes("UTF-8"), "GB2312") + "\"");
			
			// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
			WritableSheet ws = wwb.createSheet("sheet1", 0);
			WritableFont wf = new WritableFont(WritableFont.ARIAL, 15,WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
				     jxl.format.Colour.BLACK); // 定义格式 字体 下划线 斜体 粗体 颜色
			WritableCellFormat wcf = new WritableCellFormat(wf); // 单元格定义
			wcf.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式


			ws.addCell(new Label(0, 0, "增加金额", wcf));
	        ws.mergeCells(0, 0, 7, 0);
	        ws.addCell(new Label(8, 0, "减少金额", wcf));
	        ws.mergeCells(8, 0, 15, 0);
	        
			ws.addCell(new Label(0, 1, "日期"));
			ws.addCell(new Label(1, 1, "客户充值"));
			ws.addCell(new Label(2, 1, "渠道充值"));
			ws.addCell(new Label(3, 1, "兑奖"));
			ws.addCell(new Label(4, 1, "活动赠彩"));
			ws.addCell(new Label(5, 1, "充值赠彩"));
			ws.addCell(new Label(6, 1, "撤单"));
			ws.addCell(new Label(7, 1, "增加合计"));
			ws.addCell(new Label(8, 1, "客户投注"));
			ws.addCell(new Label(9, 1, "渠道投注"));
			ws.addCell(new Label(10, 1, "提现"));
			ws.addCell(new Label(11, 1, "手续费"));
			ws.addCell(new Label(13, 1, "减少合计"));
			ws.addCell(new Label(14, 1, "账户余额"));
			ws.addCell(new Label(15, 1, "备注"));
			
			BigDecimal userchargeAll = BigDecimal.ZERO;
			BigDecimal channelchargeAll = BigDecimal.ZERO;
			BigDecimal encashAll = BigDecimal.ZERO;
			BigDecimal activitypersentAll = BigDecimal.ZERO;
			BigDecimal chargepersentAll = BigDecimal.ZERO;
			BigDecimal cannelAll = BigDecimal.ZERO;
			BigDecimal inhjAll = BigDecimal.ZERO;
			BigDecimal userbetAll = BigDecimal.ZERO;
			BigDecimal channelbetAll = BigDecimal.ZERO;
			BigDecimal drawAll = BigDecimal.ZERO;
			BigDecimal feeAll = BigDecimal.ZERO;
			BigDecimal other1All = BigDecimal.ZERO;
			BigDecimal outhjAll = BigDecimal.ZERO;
			WritableCellFormat c = new WritableCellFormat(NumberFormats.TEXT);
			
			for (int i = 0; i < cwtjs.size(); i++) {
				Tcwtj tcwtj = cwtjs.get(i);
				int j = i+2;
		        ws.addCell(new Label(0, j, tcwtj.getTjdate().toString()));   
		        ws.addCell(new Label(1, j, tcwtj.getUsercharge().toString(), c));
		        ws.addCell(new Label(2, j, tcwtj.getChannelcharge().toString(), c));
		        ws.addCell(new Label(3, j, tcwtj.getEncash().toString(), c));
		        ws.addCell(new Label(4, j, tcwtj.getActivitypersent().toString(), c));
		        ws.addCell(new Label(5, j, tcwtj.getChargepersent().toString(), c));
		        ws.addCell(new Label(6, j, tcwtj.getCannel().toString(), c));
		        ws.addCell(new Label(7, j, tcwtj.getInhj().toString(), c));
		        ws.addCell(new Label(8, j, tcwtj.getUserbet().toString(), c));
		        ws.addCell(new Label(9, j, tcwtj.getChannelbet().toString(), c));
		        ws.addCell(new Label(10, j, tcwtj.getDraw().toString(), c));
		        ws.addCell(new Label(11, j, tcwtj.getFee().toString(), c));
		        ws.addCell(new Label(12, j, tcwtj.getOther1().toString(), c));
		        ws.addCell(new Label(13, j, tcwtj.getOuthj().toString(), c));
		        ws.addCell(new Label(14, j, tcwtj.getBalance().toString(), c));
		        ws.addCell(new Label(15, j, tcwtj.getMemo()));
		        
		        userchargeAll = userchargeAll.add(tcwtj.getUsercharge());
				channelchargeAll = channelchargeAll.add(tcwtj.getChannelcharge());
				encashAll = encashAll.add(tcwtj.getEncash());
				activitypersentAll = activitypersentAll.add(tcwtj.getActivitypersent());
				chargepersentAll = chargepersentAll.add(tcwtj.getChargepersent());
				cannelAll = cannelAll.add(tcwtj.getCannel());
				inhjAll = inhjAll.add(tcwtj.getInhj());

				userbetAll = userbetAll.add(tcwtj.getUserbet());
				channelbetAll = channelbetAll.add(tcwtj.getChannelbet());
				drawAll = drawAll.add(tcwtj.getDraw());
				feeAll = feeAll.add(tcwtj.getFee());
				other1All = other1All.add(tcwtj.getOther1());
				outhjAll = outhjAll.add(tcwtj.getOuthj());
			}
			
			int k = cwtjs.size()+2;
			ws.addCell(new Label(0, k, "合计"));
			ws.addCell(new Label(1, k, userchargeAll.toString(), c));
	        ws.addCell(new Label(2, k, channelchargeAll.toString(), c));
	        ws.addCell(new Label(3, k, encashAll.toString(), c));
	        ws.addCell(new Label(4, k, activitypersentAll.toString(), c));
	        ws.addCell(new Label(5, k, chargepersentAll.toString(), c));
	        ws.addCell(new Label(6, k, cannelAll.toString(), c));
	        ws.addCell(new Label(7, k, inhjAll.toString(), c));
	        ws.addCell(new Label(8, k, userbetAll.toString(), c));
	        ws.addCell(new Label(9, k, channelbetAll.toString(), c));
	        ws.addCell(new Label(10, k, drawAll.toString(), c));
	        ws.addCell(new Label(11, k, feeAll.toString(), c));
	        ws.addCell(new Label(12, k, other1All.toString(), c));
	        ws.addCell(new Label(13, k, outhjAll.toString(), c));
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
}
