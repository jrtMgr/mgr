package com.ruyicai.mgr.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.consts.BetType;
import com.ruyicai.mgr.consts.Const;
import com.ruyicai.mgr.consts.SettleFlagState;
import com.ruyicai.mgr.domain.Tlot;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.lot.LotCenter;
import com.ruyicai.mgr.lot.Lottype;
import com.ruyicai.mgr.util.DateUtil;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping(value = "/tlots")
@Controller
public class TlotController {

	private Logger logger = Logger.getLogger(TlotController.class);

	
	@RequestMapping("/page")
	public ModelAndView page(ModelAndView view){
		view.addObject("sellstarttime", DateUtil.format("yyyy-MM-dd", new Date()));
		view.setViewName("tlots/list");
		return view;
	}
	@RequestMapping("/list")
	public ModelAndView list(
			@RequestParam(value = "flowno", required = false) String flowno,
			@RequestParam(value = "lotno", required = false) String lotno,
			@RequestParam(value = "agencyno", required = false) String agencyno,
			@RequestParam(value = "batchcode", required = false) String batchcode,
			@RequestParam(value = "channel", required = false) String channel,
			@RequestParam(value = "subchannel", required = false) String subchannel,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "usertype", required = false) String usertype,
			@RequestParam(value = "userid", required = false) String userid,
			@RequestParam(value = "prize", required = false) String prize,
			@RequestParam(value = "state", required = false) BigDecimal state,
			@RequestParam(value = "bettype", required = false) BigDecimal bettype,
			@RequestParam(value = "sellstarttime", required = false) String sellstarttime,
			@RequestParam(value = "sellendtime", required = false) String sellendtime,
			@ModelAttribute("page") Page<Tlot> page, ModelAndView view) {
		logger.info("tlots/list");
		try {
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			if(!StringUtil.isEmpty(flowno)) {
				builder.append(" o.flowno=? and");
				params.add(flowno);
			}else {
				if (!StringUtil.isEmpty(lotno)) {
					builder.append(" o.lotno=? and");
					params.add(lotno);
				}
				if (!StringUtil.isEmpty(agencyno)) {
					builder.append(" o.agencyno=? and");
					params.add(agencyno);
				}
				if (!StringUtil.isEmpty(batchcode)) {
					builder.append(" o.batchcode=? and");
					params.add(batchcode);
				}
				
				if (!StringUtil.isEmpty(channel)) {
					builder.append(" o.channel=? and");
					params.add(channel);
				}
				if (!StringUtil.isEmpty(subchannel)) {
					builder.append(" o.subchannel=? and");
					params.add(subchannel);
				}
				
				if (!StringUtil.isEmpty(starttime)) {
					builder.append(" to_char(o.ordertime, 'yyyy-mm-dd') >= ? and");
					params.add(starttime);
				}
				if (!StringUtil.isEmpty(endtime)) {
					builder.append(" to_char(o.ordertime, 'yyyy-mm-dd') <= ? and");
					params.add(endtime);
				}
				if (!StringUtil.isEmpty(sellstarttime)) {
					builder.append(" o.lotcenterordertime is not null and to_char(o.lotcenterordertime, 'yyyy-mm-dd') >= ? and");
					params.add(sellstarttime);
					view.addObject("sellstarttime", sellstarttime);
				}
				if (!StringUtil.isEmpty(sellendtime)) {
					builder.append(" o.lotcenterordertime is not null and to_char(o.lotcenterordertime, 'yyyy-mm-dd') <= ? and");
					params.add(sellendtime);
				}
				if (!StringUtil.isEmpty(usertype) && !StringUtil.isEmpty(userid)) {
					Tuserinfo tuserinfo = null;
					try {
						if (Const.Tuserinfo_Mobileid.equals(usertype)) {
							tuserinfo = Tuserinfo.findTuserinfoesByMobileid(userid,Const.Subchannel);
						} else if (Const.Tuserinfo_Email.equals(usertype)) {
							tuserinfo = Tuserinfo.findTuserinfoesByEmail(userid,Const.Subchannel);
						} else if (Const.Tuserinfo_UserName.equals(usertype)) {
							tuserinfo = Tuserinfo.findTuserinfoesByUserName(userid,Const.Subchannel);
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
				
				if (!StringUtil.isEmpty(prize)) {
					if ("yes".equals(prize)) {
						builder.append(" o.prizeamt > 0 and");
					}
					if ("no".equals(prize)) {
						builder.append(" o.prizeamt = 0 and");
					}
					view.addObject("prize",prize);
				}
				
				if (null != state) {
					if (new BigDecimal(-1).equals(state)) {
						builder.append(" o.state = -1 and");
					}
					if (BigDecimal.ZERO.equals(state)) {
						builder.append(" o.state = 0 and");
					}
					if (BigDecimal.ONE.equals(state)) {
						builder.append(" o.state = 1 and");
					}
					if (new BigDecimal(2).equals(state)) {
						builder.append(" o.state = 2 and");
					}
					view.addObject("state", state);
				}
				
				if (null != bettype) {
					if (BetType.touzhu.value().equals(bettype)) {
						builder.append(" o.bettype = 2 and");
					}
					if (BetType.hemai.value().equals(bettype)) {
						builder.append(" o.bettype = 3 and");
					}
					if (BetType.taocan.value().equals(bettype)) {
						builder.append(" o.bettype = 1 and");
					}
					if (BetType.zengsong.value().equals(bettype)) {
						builder.append(" (o.bettype = 4 or o.bettype = 5) and");
					}
					if (BetType.zhuihao.value().equals(bettype)) {
						builder.append(" o.bettype = 0 and");
					}
				}
			}
			
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			if (!StringUtil.isEmpty(builder.toString()))
				Tlot.findList(builder.toString(), " order by o.ordertime desc",
						params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tlots/list error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("tlots/list");
		return view;
	}
	
	
	/*
	 * 生成报表 
	 */
	//@RequestMapping("/generateReport")
	public void generateReport(@RequestParam(value = "flowno", required = false) String flowno,
			@RequestParam(value = "lotno") String lotno,
			@RequestParam(value = "batchcode") String batchcode,
			@RequestParam(value = "channel", required = false) String channel,
			@RequestParam(value = "subchannel", required = false) String subchannel,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "usertype", required = false) String usertype,
			@RequestParam(value = "userid", required = false) String userid,
			@RequestParam(value = "prize", required = false) String prize,
			@RequestParam(value = "state", required = false) BigDecimal state,
			@RequestParam(value = "bettype", required = false) BigDecimal bettype,
			@RequestParam(value = "sellstarttime", required = false) String sellstarttime,
			@RequestParam(value = "sellendtime", required = false) String sellendtime,
			HttpServletRequest request, HttpServletResponse response)throws IOException {
		OutputStream os = null;
		WritableWorkbook wwb = null;
		try {
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			if(!StringUtil.isEmpty(flowno)) {
				builder.append(" o.flowno=? and");
				params.add(flowno);
			}else {
				if (!StringUtil.isEmpty(lotno)) {
					builder.append(" o.lotno=? and");
					params.add(lotno);
				}
				if (!StringUtil.isEmpty(batchcode)) {
					builder.append(" o.batchcode=? and");
					params.add(batchcode);
				}
				if (!StringUtil.isEmpty(channel)) {
					builder.append(" o.channel=? and");
					params.add(channel);
				}
				if (!StringUtil.isEmpty(subchannel)) {
					builder.append(" o.subchannel=? and");
					params.add(subchannel);
				}
				
				if (!StringUtil.isEmpty(starttime)) {
					builder.append(" to_char(o.ordertime, 'yyyy-mm-dd') >= ? and");
					params.add(starttime);
				}
				if (!StringUtil.isEmpty(endtime)) {
					builder.append(" to_char(o.ordertime, 'yyyy-mm-dd') <= ? and");
					params.add(endtime);
				}
				if (!StringUtil.isEmpty(sellstarttime)) {
					builder.append(" o.lotcenterordertime is not null and to_char(o.lotcenterordertime, 'yyyy-mm-dd') >= ? and");
					params.add(sellstarttime);
				}
				if (!StringUtil.isEmpty(sellendtime)) {
					builder.append(" o.lotcenterordertime is not null and to_char(o.lotcenterordertime, 'yyyy-mm-dd') <= ? and");
					params.add(sellendtime);
				}
				if (!StringUtil.isEmpty(usertype) && !StringUtil.isEmpty(userid)) {
					Tuserinfo tuserinfo = null;
					try {
						if (Const.Tuserinfo_Mobileid.equals(usertype)) {
							tuserinfo = Tuserinfo.findTuserinfoesByMobileid(userid,Const.Subchannel);
						} else if (Const.Tuserinfo_Email.equals(usertype)) {
							tuserinfo = Tuserinfo.findTuserinfoesByEmail(userid,Const.Subchannel);
						} else if (Const.Tuserinfo_UserName.equals(usertype)) {
							tuserinfo = Tuserinfo.findTuserinfoesByUserName(userid,Const.Subchannel);
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
				
				if (!StringUtil.isEmpty(prize)) {
					if ("yes".equals(prize)) {
						builder.append(" o.prizeamt > 0 and");
					}
					if ("no".equals(prize)) {
						builder.append(" o.prizeamt = 0 and");
					}
				}
				
				if (null != state) {
					if (new BigDecimal(-1).equals(state)) {
						builder.append(" o.state = -1 and");
					}
					if (BigDecimal.ZERO.equals(state)) {
						builder.append(" o.state = 0 and");
					}
					if (BigDecimal.ONE.equals(state)) {
						builder.append(" o.state = 1 and");
					}
					if (new BigDecimal(2).equals(state)) {
						builder.append(" o.state = 2 and");
					}
				}
				
				if (null != bettype) {
					if (BetType.touzhu.value().equals(bettype)) {
						builder.append(" o.bettype = 2 and");
					}
					if (BetType.hemai.value().equals(bettype)) {
						builder.append(" o.bettype = 3 and");
					}
					if (BetType.taocan.value().equals(bettype)) {
						builder.append(" o.bettype = 1 and");
					}
					if (BetType.zengsong.value().equals(bettype)) {
						builder.append(" (o.bettype = 4 or o.bettype = 5) and");
					}
					if (BetType.zhuihao.value().equals(bettype)) {
						builder.append(" o.bettype = 0 and");
					}
				}
			}
			
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			List<Tlot> list = Tlot.findList(builder.toString(), " order by o.ordertime desc", params);
		
			os = response.getOutputStream();
			wwb = Workbook.createWorkbook(os);
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition", "attachment;filename=\""
					+ new String((java.net.URLEncoder.encode("彩票信息报表.xls",	"UTF-8")).getBytes("UTF-8"), "GB2312") + "\"");
			
			// Workbook的createSheet方法有两个参数，第一个是工作表的名称，第二个是工作表在工作薄中的位置
			WritableSheet ws = wwb.createSheet("sheet1", 0);
	        
			ws.addCell(new Label(0, 0, "流水号"));
			ws.addCell(new Label(1, 0, "用户编号"));
			ws.addCell(new Label(2, 0, "彩票中心"));
			ws.addCell(new Label(3, 0, "渠道"));
			ws.addCell(new Label(4, 0, "用户系统"));
			ws.addCell(new Label(5, 0, "彩种"));
			ws.addCell(new Label(6, 0, "期号"));
			ws.addCell(new Label(7, 0, "注数"));
			ws.addCell(new Label(8, 0, "倍数"));
			ws.addCell(new Label(9, 0, "销售方式"));
			ws.addCell(new Label(10, 0, "金额（元）"));
			ws.addCell(new Label(11, 0, "中奖金额（元）"));
			ws.addCell(new Label(12, 0, "投注时间"));
			ws.addCell(new Label(13, 0, "类型"));
			ws.addCell(new Label(14, 0, "标识"));
			ws.addCell(new Label(15, 0, "状态"));
			
			
			BigDecimal amtAll = BigDecimal.ZERO;
			BigDecimal prizeamtAll = BigDecimal.ZERO;
			
			for (int i = 0; i < list.size(); i++) {
				Tlot tlot = list.get(i);
				int j = i+1;
		        ws.addCell(new Label(0, j, tlot.getFlowno()));   
		        ws.addCell(new Label(1, j, tlot.getUserno()));
		        ws.addCell(new Label(2, j, LotCenter.getName(tlot.getAgencyno())));
		        ws.addCell(new Label(3, j, tlot.getChannel()));
		        ws.addCell(new Label(4, j, tlot.getSubchannel()));
		        ws.addCell(new Label(5, j, Lottype.getName(tlot.getLotno())));
		        ws.addCell(new Label(6, j, tlot.getBatchcode()));
		        ws.addCell(new Label(7, j, tlot.getBetnum().toString()));
		        ws.addCell(new Label(8, j, tlot.getLotmulti().toString()));
		        String sellway = "";
				if(BigDecimal.ZERO.equals(tlot.getSellway())) {
					sellway = "自选";
				} else if(new BigDecimal(2).equals(tlot.getSellway())) {
					sellway = "机选";
				}
		        ws.addCell(new Label(9, j, sellway));
		        ws.addCell(new Label(10, j, tlot.getAmt().divide(new BigDecimal(100)).toString()));
		        amtAll = amtAll.add(tlot.getAmt());
		        ws.addCell(new Label(11, j, tlot.getPrizeamt().divide(new BigDecimal(100)).toString()));
		        prizeamtAll = prizeamtAll.add(tlot.getPrizeamt());
		        ws.addCell(new Label(12, j, tlot.getOrdertime().toString()));
		        ws.addCell(new Label(13, j, BetType.getBetType(tlot.getBettype())));
		        ws.addCell(new Label(14, j, SettleFlagState.getMemo(tlot.getSettleflag())));
		        String state1 = "";
				if(new BigDecimal(-1).equals(tlot.getState())) {
					state1 = "未处理";
				}else if(BigDecimal.ZERO.equals(tlot.getState())) {
					state1 = "失败";
				}else if(BigDecimal.ONE.equals(tlot.getState())) {
					state1 = "成功";
				}else if(new BigDecimal(2).equals(tlot.getState())) {
					state1 = "处理中";
				}
		        ws.addCell(new Label(15, j, state1));
		        
			}
			
			int k = list.size()+1;
			ws.addCell(new Label(0, k, "合计"));
	        ws.addCell(new Label(10, k, amtAll.divide(new BigDecimal(100)).toString()));
	        ws.addCell(new Label(11, k, prizeamtAll.divide(new BigDecimal(100)).toString()));
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
