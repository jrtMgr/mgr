package com.ruyicai.mgr.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.Taccountdetail;
import com.ruyicai.mgr.domain.Tlot;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping(value = "/datastat")
@Controller()
public class DatastatController {

	private Logger logger = Logger.getLogger(DatastatController.class);
	
	@RequestMapping(value = "/list")
	public ModelAndView list(ModelAndView view) {
		logger.info("datastat/list");		
		view.setViewName("datastat/list");
		return view;
	}
	
	@RequestMapping(value = "/highbuyer")
	public ModelAndView highbuyer(@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "amt", required = false) String amt,
			ModelAndView view) {
		try {
			logger.info("datastat/highbuyer");
			StringBuilder builder = new StringBuilder(
					" where t.orderstate = 1 and t.subchannel = '00092493' and t.userno != '00808738' ");
			List<Object> params = new ArrayList<Object>();
			if (!StringUtil.isEmpty(starttime)) {
				builder.append(" and to_char(t.createtime, 'yyyy-mm-dd') >= ? ");
				params.add(starttime);
				view.addObject("starttime", starttime);
			}
			if (!StringUtil.isEmpty(endtime)) {
				builder.append(" and to_char(t.createtime, 'yyyy-mm-dd') <= ? ");
				params.add(endtime);
				view.addObject("endtime", endtime);
			}
			builder.append(" union all select b.userno userno, b.num amt from caselotbuy b, caselot c where b.caselotid = c.id and c.state = '5' and b.num > 0 and b.userno != '00808738' ");
			if (!StringUtil.isEmpty(starttime)) {
				builder.append(" and to_char(c.starttime, 'yyyy-mm-dd') >= ? ");
				params.add(starttime);
			}
			if (!StringUtil.isEmpty(endtime)) {
				builder.append(" and to_char(c.starttime, 'yyyy-mm-dd') <= ? ");
				params.add(endtime);
			}
			builder.append(" ) A ");
			if (StringUtil.isEmpty(amt)) {
				logger.error("amt为空 error");
				view.addObject("errormsg", "优质用户最低购彩额(分)不能为空");
				view.setViewName("datastat/highbuyer");
				return view;
			}

			builder.append(" group by A.userno ");
			String highWhere = builder.toString() + " having sum(A.amt) >= ? ";
			String generalWhere = builder.toString()
					+ " having sum(A.amt) < ? ";
			params.add(amt);
			view.addObject("amt", amt);
			Long high = Tlot.statBuyer(highWhere, params);
			Long general = Tlot.statBuyer(generalWhere, params);
			view.addObject("high", high);
			view.addObject("general", general);
			view.setViewName("datastat/highbuyer");
		} catch (Exception e) {
			logger.error("highbuyer为空 error:", e);
		}
		return view;
	}
	
	@RequestMapping(value = "/highbuyers")
	public void highbuyers(@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "amt", required = false) String amt,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("datastat/highbuyers");
		StringBuilder builder = new StringBuilder(" where t.orderstate = 1 and t.subchannel = '00092493' and t.userno != '00808738' ");
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isEmpty(starttime)) {
			builder.append(" and to_char(t.createtime, 'yyyy-mm-dd') >= ? ");
			params.add(starttime);			
		}
		if (!StringUtil.isEmpty(endtime)) {
			builder.append(" and to_char(t.createtime, 'yyyy-mm-dd') <= ?  ");
			params.add(endtime);
		}
		builder.append(" union all select b.userno userno, b.num amt from caselotbuy b, caselot c where b.caselotid = c.id and c.state = '5' and b.num > 0 and b.userno != '00808738' ");
		if (!StringUtil.isEmpty(starttime)) {
			builder.append(" and to_char(c.starttime, 'yyyy-mm-dd') >= ? ");
			params.add(starttime);
		}
		if (!StringUtil.isEmpty(endtime)) {
			builder.append(" and to_char(c.starttime, 'yyyy-mm-dd') <= ? ");
			params.add(endtime);
		}
		builder.append(" ) A ");
		if (StringUtil.isEmpty(amt)) {
			logger.error("amt为空 error");
			return;
		}
		
		builder.append(" group by A.userno ");
		builder.append(" having sum(A.amt) >= ? order by sum(A.amt) desc");
		String highWhere = builder.toString();	
		params.add(amt);		
		List<Object[]> list = Tlot.statHighBuyer(highWhere, params);
		Tuserinfo tuserinfo = null;
		OutputStream os = null;
		WritableWorkbook wwb = null;
		try {
			os = response.getOutputStream();
			wwb = Workbook.createWorkbook(os);
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition", "attachment;filename=\""
					+ new String((java.net.URLEncoder.encode("优质用户.xls",	"UTF-8")).getBytes("UTF-8"), "GB2312") + "\"");
			
			WritableSheet ws = wwb.createSheet("sheet1", 0);
	        
			ws.addCell(new Label(0, 0, "用户编号"));
			ws.addCell(new Label(1, 0, "手机号"));
			ws.addCell(new Label(2, 0, "真实姓名"));
			ws.addCell(new Label(3, 0, "购彩金额（分）"));

			for (int i = 0; i < list.size(); i++) {
				Object[] o = list.get(i);
				int j = i+1;
				ws.addCell(new Label(0, j, o[0].toString()));  
				tuserinfo = Tuserinfo.findTuserinfo(o[0].toString());
				ws.addCell(new Label(1, j, tuserinfo.getMobileid()));  
				ws.addCell(new Label(2, j, tuserinfo.getName()));  
				ws.addCell(new Label(3, j, o[1].toString()));  
			}
			wwb.write(); 
		} catch (Exception e) {
			logger.error("发生异常:" + e);
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
	
	@RequestMapping(value = "/activitiespage")
	public ModelAndView activitiespage(ModelAndView view) {
		logger.info("datastat/activitiespage");
		view.setViewName("datastat/activities");
		return view;
	}
	
	@RequestMapping(value = "/activities")
	public ModelAndView activities(@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "userno", required = false) String userno,
			ModelAndView view) {
		logger.info("datastat/activities");
		StringBuilder builder = new StringBuilder("select memo, sum(amt) from taccountdetail t where t.ttransactiontype = 23 ");
		StringBuilder sumsql = new StringBuilder("select sum(amt) from taccountdetail t where t.ttransactiontype = 23 ");
		List<Object> params = new ArrayList<Object>();
		List<Object> sumparams = new ArrayList<Object>();
		if (!StringUtil.isEmpty(starttime)) {
			builder.append(" and to_char(t.plattime, 'yyyy-MM-dd') >= ? ");
			params.add(starttime);
			
			sumsql.append(" and to_char(t.plattime, 'yyyy-MM-dd') >= ? ");
			sumparams.add(starttime);
		}
		if (!StringUtil.isEmpty(endtime)) {
			builder.append(" and to_char(t.plattime, 'yyyy-MM-dd') <= ? ");
			params.add(endtime);
			
			sumsql.append(" and to_char(t.plattime, 'yyyy-MM-dd') <= ? ");
			sumparams.add(endtime);
		}
		if (!StringUtil.isEmpty(userno)) {
			builder.append(" and userno = ? ");
			params.add(userno);
			
			sumsql.append(" and userno = ? ");
			sumparams.add(userno);
		}
		
		builder.append(" group by memo order by memo");
		String where = builder.toString();
		try {
			List<Object[]> list  = Taccountdetail.statActivitiesData(where, params);
			view.addObject("list", list);
			
			BigDecimal sum  = Taccountdetail.statActivitiesDatasum(sumsql.toString(), sumparams).get(0);
			view.addObject("sum", sum);
		} catch (Exception e) {
			view.addObject("errormsg", "出错了");
			logger.error("datastat/activities error", e);
		}
		view.setViewName("datastat/activities");
		return view;
	}
	
	
	@RequestMapping(value = "/saledata")
	public ModelAndView saledata(@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			ModelAndView view) {
		logger.info("datastat/saledata");
		StringBuilder builder = new StringBuilder("  where t1.orderstate = 1 and t1.subchannel = '00092493' ");//含合买
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isEmpty(starttime)) {
			builder.append(" and to_char(t1.createtime, 'yyyy-MM-dd') >= ? ");//含合买
			params.add(starttime);
			view.addObject("starttime", starttime);
		}
		if (!StringUtil.isEmpty(endtime)) {
			builder.append(" and to_char(t1.createtime, 'yyyy-MM-dd') <= ? ");//含合买
			params.add(endtime);
			view.addObject("endtime", endtime);
		}
		
		builder.append(" union all select b.userno, c.lotno, b.num as amt from caselotbuy b, caselot c  where b.caselotid = c.id and c.state = '5'  and b.num > 0 ");//含合买
		if (!StringUtil.isEmpty(starttime)) {
			builder.append(" and to_char(c.starttime, 'yyyy-MM-dd') >= ? ");//含合买
			params.add(starttime);
			view.addObject("starttime", starttime);
		}
		if (!StringUtil.isEmpty(endtime)) {
			builder.append(" and to_char(c.starttime, 'yyyy-MM-dd') <= ? ");//含合买
			params.add(endtime);
			view.addObject("endtime", endtime);
		}
		
		builder.append(" ) tt where tt.userno != '00808738' group by tt.lotno) ttt where ttt.lotno = tttt.lotno order by ttt.lotno " );//含合买
		String where = builder.toString();
		List<Object[]> list  = Tlot.statSaleData(where, params);
		view.addObject("list", list);
		long count = 0;
		long amt = 0;
		for (Object[] o : list) {
			if (null != o[2]) {
				count += Long.valueOf(o[2].toString()).longValue();
			}
			if (null != o[3]) {
				amt += Long.valueOf(o[3].toString()).longValue();
			}			
		}
		view.addObject("count", count);
		view.addObject("amt", amt);
		view.setViewName("datastat/saledata");
		return view;
	}
	
	@RequestMapping(value = "/buyerloss")
	public ModelAndView buyerloss(@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "starttime2", required = false) String starttime2,
			@RequestParam(value = "endtime2", required = false) String endtime2,
			@RequestParam(value = "starttime3", required = false) String starttime3,
			@RequestParam(value = "endtime3", required = false) String endtime3,
			@RequestParam(value = "amt", required = false) String amt,
			ModelAndView view) {
		logger.info("datastat/buyerloss start");
		logger.info("starttime=" + starttime + ",endtime="+endtime+",starttime2=" + starttime2 + ",endtime2="+endtime2+
				",starttime3=" + starttime3 + ",endtime3="+endtime3+",amt="+amt);
		try {
		if (StringUtil.isEmpty(starttime)) {
			logger.error("starttime为空 error");
			view.addObject("errormsg", "老用户购彩起始时间不能为空");
			view.setViewName("datastat/buyerloss");
			return view;
		} 		
		view.addObject("starttime", starttime);
	
		if (StringUtil.isEmpty(endtime)) {
			logger.error("endtime为空 error");
			view.addObject("errormsg", "老用户购彩终止时间不能为空");
			view.setViewName("datastat/buyerloss");
			return view;
		}		
		view.addObject("endtime", endtime);
		
		if (StringUtil.isEmpty(starttime2)) {
			logger.error("starttime2为空 error");
			view.addObject("errormsg", "当前用户购彩起始时间不能为空");
			view.setViewName("datastat/buyerloss");
			return view;
		} 		
		view.addObject("starttime2", starttime2);
		
		if (StringUtil.isEmpty(endtime2)) {
			logger.error("endtime2为空 error");
			view.addObject("errormsg", "当前用户购彩终止时间不能为空");
			view.setViewName("datastat/buyerloss");
			return view;
		}	
		view.addObject("endtime2", endtime2);
		
		if (StringUtil.isEmpty(starttime3)) {
			logger.error("starttime3为空 error");
			view.addObject("errormsg", "新用户注册起始时间不能为空");
			view.setViewName("datastat/buyerloss");
			return view;
		} 		
		view.addObject("starttime3", starttime3);
		
		if (StringUtil.isEmpty(endtime3)) {
			logger.error("endtime3为空 error");
			view.addObject("errormsg", "新用户注册终止时间不能为空");
			view.setViewName("datastat/buyerloss");
			return view;
		}	
		view.addObject("endtime3", endtime3);
		
		String sqlCurrentUsers = "select count(t1.userno) from tuserinfo t1 where t1.userno in (select distinct userno from torder t where t.orderstate = 1 and t.subchannel = '00092493' and to_char(t.createtime, 'yyyy-MM-dd') >= ? and to_char(t.createtime, 'yyyy-MM-dd') <= ? union all select distinct userno from caselotbuy b, caselot c where b.caselotid = c.id and c.state = '5' and b.num > 0 and to_char(c.starttime, 'yyyy-MM-dd') >= ? and to_char(c.starttime, 'yyyy-MM-dd') <= ?) and t1.userno != '00808738'";//new
		List<Object> params = new ArrayList<Object>();
		params.add(starttime2);
		params.add(endtime2);
		params.add(starttime2);//new
		params.add(endtime2);//new
		List<Object[]> list2 = null;
		List<Object> list = Tlot.statCurrentBuyer2(sqlCurrentUsers, params);//new	
		BigDecimal currentusers = BigDecimal.ZERO;//new 
		for (Object o : list) {
			if (null != o) {
				currentusers = new BigDecimal(o.toString());
				logger.info("o=" + o);
				logger.info("currentusers=" + currentusers);
			}			
		}
		
		String sqlCurrentAmt = "select sum(t.amt) from torder t where t.orderstate = 1 and t.subchannel = '00092493' and to_char(t.createtime, 'yyyy-MM-dd') >= ? and to_char(t.createtime, 'yyyy-MM-dd') <= ?";//new
		params.clear();
		params.add(starttime2);
		params.add(endtime2);
		list.clear();
		list = Tlot.statCurrentBuyer2(sqlCurrentAmt, params);//new	
		BigDecimal currentamt = BigDecimal.ZERO;//new 
		for (Object o : list) {			
			if (null != o) {
				currentamt = new BigDecimal(o.toString());//new
				logger.info("o=" + o);
				logger.info("currentamt=" + currentamt);
			}	
		}
		view.addObject("currentusers", currentusers);
		view.addObject("currentamt", currentamt);
		
		params.clear();
		params.add(starttime);
		params.add(endtime);
		params.add(starttime);
		params.add(endtime);
		list.clear();
		list = Tlot.statCurrentBuyer2(sqlCurrentUsers, params);
		BigDecimal oldusers = BigDecimal.ZERO;//new
		for (Object o : list) {
			if (null != o) {
				oldusers = new BigDecimal(o.toString());
				logger.info("o=" + o);
				logger.info("oldusers=" + oldusers);
			}		
		}
				
		params.clear();
		params.add(starttime);
		params.add(endtime);
		list.clear();
		list = Tlot.statCurrentBuyer2(sqlCurrentAmt, params);		
		BigDecimal oldamt = BigDecimal.ZERO;//new
		for (Object o : list) {
			if (null != o) {
				oldamt = new BigDecimal(o.toString());//new
				logger.info("o=" + o);
				logger.info("oldamt=" + oldamt);
			}	
		}
		view.addObject("oldusers", oldusers);
		view.addObject("oldamt", oldamt);
		
		String sqlNewUsers1 = "select t1.userno from tuserinfo t1 where  t1.userno in (select distinct userno from torder t where t.orderstate = 1 and t.subchannel = '00092493' and to_char(t.createtime, 'yyyy-MM-dd') >= ? and to_char(t.createtime, 'yyyy-MM-dd') <= ? union all select distinct userno from caselotbuy b, caselot c where b.caselotid = c.id and c.state = '5' and b.num > 0 and to_char(c.starttime, 'yyyy-MM-dd') >= ? and to_char(c.starttime, 'yyyy-MM-dd') <= ?)";//new
		sqlNewUsers1 += " and t1.userno in (select t3.userno from tuserinfo t3 where to_char(t3.regtime, 'yyyy-MM-dd') >= ? and to_char(t3.regtime, 'yyyy-MM-dd') <= ?)";
		params.clear();
		params.add(starttime2);
		params.add(endtime2);
		params.add(starttime2);//new
		params.add(endtime2);//new
		params.add(starttime3);
		params.add(endtime3);
		List<Object> listNewUsers1 = Tlot.statCurrentBuyer2(sqlNewUsers1, params);		
		String sqlNewUsers2 = " select distinct userno from torder t where t.orderstate = 1 and t.subchannel = '00092493' and to_char(t.createtime, 'yyyy-MM-dd') >= ? and to_char(t.createtime, 'yyyy-MM-dd') <= ? union all select distinct userno from caselotbuy b, caselot c where b.caselotid = c.id and c.state = '5' and b.num > 0 and to_char(c.starttime, 'yyyy-MM-dd') >= ? and to_char(c.starttime, 'yyyy-MM-dd') <= ?";
		params.clear();
		params.add(starttime);
		params.add(endtime);
		params.add(starttime);//new
		params.add(endtime);//new
		List<Object> listNewUsers2 = Tlot.statCurrentBuyer2(sqlNewUsers2, params);
		logger.info("listNewUsers1.size=" + listNewUsers1.size());
		logger.info("listNewUsers2.size=" + listNewUsers2.size());		
		boolean resultChange = listNewUsers1.removeAll(listNewUsers2);
		logger.info("resultChange=" + resultChange + ",listNewUsers1.size=" + listNewUsers1.size());	
		BigDecimal newusers = new BigDecimal(listNewUsers1.size()-1);//去掉00808738用户
		logger.info("newusers=" + newusers);

		
		String sqlNewAmt = "select sum(tt.amt) as st from (select t1.userno, t1.amt from torder t1 where t1.orderstate = 1 and t1.subchannel = '00092493'and to_char(t1.createtime, 'yyyy-MM-dd') >= ? and to_char(t1.createtime, 'yyyy-MM-dd') <= ? union all select b.userno, b.num as amt from caselotbuy b, caselot c where b.caselotid = c.id and c.state = '5' and b.num > 0 and to_char(c.starttime, 'yyyy-MM-dd') >= ? and to_char(c.starttime, 'yyyy-MM-dd') <= ?) tt where ";
		sqlNewAmt += " tt.userno in (select t3.userno from tuserinfo t3 where to_char(t3.regtime, 'yyyy-MM-dd') >= ? and to_char(t3.regtime, 'yyyy-MM-dd') <= ? minus select t4.userno from tuserinfo t4 where t4.userno = '00808738' minus (select userno from torder t where t.orderstate = 1 and t.subchannel = '00092493' and to_char(t.createtime, 'yyyy-MM-dd') >= ? and to_char(t.createtime, 'yyyy-MM-dd') <= ? union all select userno from caselotbuy b, caselot c where b.caselotid = c.id and c.state = '5' and b.num > 0 and to_char(c.starttime, 'yyyy-MM-dd') >= ? and to_char(c.starttime, 'yyyy-MM-dd') <= ? ))";
		params.clear();
		params.add(starttime2);
		params.add(endtime2);
		params.add(starttime2);//new
		params.add(endtime2);//new
		params.add(starttime3);
		params.add(endtime3);
		params.add(starttime);
		params.add(endtime);
		params.add(starttime);//new
		params.add(endtime);//new
		list.clear();
		list = Tlot.statCurrentBuyer2(sqlNewAmt, params);		
		BigDecimal newamt = BigDecimal.ZERO;//new
		for (Object o : list) {
			if (null != o) {
				newamt = new BigDecimal(o.toString());//new
				logger.info("o=" + o);
				logger.info("newamt=" + newamt);
			}
		}		
		view.addObject("newusers", newusers);
		view.addObject("newamt", newamt);
		
		String sqlCurrentOldUsersAndAmt = "select count(distinct tt.userno) as ct, sum(tt.amt) as st from (select t1.userno, t1.amt from torder t1 where t1.orderstate = 1 and t1.subchannel = '00092493'and to_char(t1.createtime, 'yyyy-MM-dd') >= ? and to_char(t1.createtime, 'yyyy-MM-dd') <= ? union all select b.userno, b.num as amt from caselotbuy b, caselot c where b.caselotid = c.id and c.state = '5' and b.num > 0 and to_char(c.starttime, 'yyyy-MM-dd') >= ? and to_char(c.starttime, 'yyyy-MM-dd') <= ?) tt where tt.userno != '00808738'";
		sqlCurrentOldUsersAndAmt += " and tt.userno in (select distinct userno from torder t where t.orderstate = 1 and t.subchannel = '00092493' and to_char(t.createtime, 'yyyy-MM-dd') >= ? and to_char(t.createtime, 'yyyy-MM-dd') <= ? union all select distinct userno from caselotbuy b, caselot c where b.caselotid = c.id and c.state = '5' and b.num > 0 and to_char(c.starttime, 'yyyy-MM-dd') >= ? and to_char(c.starttime, 'yyyy-MM-dd') <= ?)";
		params.clear();
		params.add(starttime2);
		params.add(endtime2);
		params.add(starttime2);//new
		params.add(endtime2);//new	
		params.add(starttime);
		params.add(endtime);
		params.add(starttime);//new
		params.add(endtime);//new
		list.clear();
		list2 = Tlot.statCurrentBuyer(sqlCurrentOldUsersAndAmt, params);
		BigDecimal currentoldusers = BigDecimal.ZERO;//new
		BigDecimal currentoldamt = BigDecimal.ZERO;//new
		for (Object[] o : list2) {
			if (null != o[0]) {
				currentoldusers = new BigDecimal(o[0].toString());//new
				logger.info("o[0]=" + o[0]);
				logger.info("currentoldusers=" + currentoldusers);
			}
			if (null != o[1]) {
				currentoldamt = new BigDecimal(o[1].toString());//new
				logger.info("o[1]=" + o[1]);
				logger.info("currentoldamt=" + currentoldamt);
			}			
		}
		view.addObject("currentoldusers", currentoldusers);
		view.addObject("currentoldamt", currentoldamt);
		
		BigDecimal currentotherusers = currentusers.subtract(newusers).subtract(currentoldusers);//new
		BigDecimal currentotheramt = currentamt.subtract(newamt).subtract(currentoldamt);//new
		view.addObject("currentotherusers", currentotherusers);
		view.addObject("currentotheramt", currentotheramt);
		
		logger.info("currentotherusers=" + currentotherusers);
		logger.info("currentotheramt=" + currentotheramt);
		
		view.setViewName("datastat/buyerloss"); 
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("datastat/buyerloss error:", e);
		} 
		logger.info("datastat/buyerloss end");
		return view; 
		
	}
	
	@RequestMapping(value = "/subscribe")
	public ModelAndView subscribe(@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "lotnos", required = false) String lotnos,
			ModelAndView view) {
		logger.info("datastat/subscribe");
		StringBuilder builder = new StringBuilder(" select count(distinct t.userno) as ct, sum(t.amt) as at from tlot t ");
		builder.append(" inner join (select t1.id, t2.ordertime from torder t1 inner join tsubscribe t2 on t1.tsubscribeflowno = t2.flowno ");
		builder.append(" where t2.type = 2 and t2.state = 0 and t2.lastnum > 0 ");
		List<Object> params = new ArrayList<Object>();
		
		if (!StringUtil.isEmpty(lotnos)) {
			String[] arr = lotnos.trim().split(",");
			String temp = "";
			for (String str : arr) {
				temp += "'"  + str + "',";
			}
			temp = temp.substring(0, temp.length()-1);
			builder.append("  and t2.lotno in (" + temp + ")");
			view.addObject("lotnos", lotnos);		
		}
		builder.append(") t3 on t.torderid = t3.id where 1=1 ");
			
		if (!StringUtil.isEmpty(starttime)) {
			builder.append(" and to_char(t.ordertime, 'yyyy-mm-dd') >= ? ");
			params.add(starttime);
			view.addObject("starttime", starttime);
		}
		
		if (!StringUtil.isEmpty(endtime)) {
			builder.append(" and to_char(t.ordertime, 'yyyy-mm-dd') <= ? ");
			params.add(endtime);
			view.addObject("endtime", endtime);
		}
		
		List<Object[]> list = Tlot.statCurrentBuyer(builder.toString(), params);	
		long users = 0;
		long amt = 0;
		for (Object[] o : list) {
			if (null != o[0]) {
				users += Long.valueOf(o[0].toString()).longValue();
			}
			if (null != o[1]) {
				amt += Long.valueOf(o[1].toString()).longValue();
			}			
		}
		
		view.addObject("users", users);
		view.addObject("amt", amt);
		
		String sql = "select t.lotno, t.name from tlottypeconfig t order by t.lotno";
		list.clear();
		list = Tlot.statCurrentBuyer(sql, null);		
		view.addObject("list", list);
		
		view.setViewName("datastat/subscribe");
		return view;
	}
}
