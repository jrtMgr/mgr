package com.ruyicai.mgr.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.consts.BetType;
import com.ruyicai.mgr.consts.SubaccountType;
import com.ruyicai.mgr.consts.TransactionType;
import com.ruyicai.mgr.controller.dto.CaseLotAndCaseLotBuyDTO;
import com.ruyicai.mgr.domain.CaseLotBuy;
import com.ruyicai.mgr.domain.Taccount;
import com.ruyicai.mgr.domain.Taccountdetail;
import com.ruyicai.mgr.domain.Tcashdetail;
import com.ruyicai.mgr.domain.Tlot;
import com.ruyicai.mgr.domain.Torder;
import com.ruyicai.mgr.domain.Tsubaccount;
import com.ruyicai.mgr.domain.Tsubscribe;
import com.ruyicai.mgr.domain.Ttransaction;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.lot.Lottype;
import com.ruyicai.mgr.mysql.ChargecenterDao;
import com.ruyicai.mgr.util.BetCodeParseJingCaiUtil;
import com.ruyicai.mgr.util.BetCodeParseOrderInfoUtil;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.SMSUtil;
import com.ruyicai.mgr.util.StringUtil;
import com.ruyicai.mgr.vo.AccountOverview;

import flexjson.JSONSerializer;

@RequestMapping(value = "/tuserinfoes")
@Controller()
public class TuserinfoController {

	private Logger logger = LoggerFactory.getLogger(Tuserinfo.class);

	@Autowired
	ChargecenterDao chargecenterDao;

	@Autowired
	BetCodeParseOrderInfoUtil betCodeParseOrderInfoUtil;

	@Autowired
	BetCodeParseJingCaiUtil betCodeParseJingCaiUtil;

	@RequestMapping(value = "/list")
	public ModelAndView list(@RequestParam(value = "userno", required = false) String userno,
			@RequestParam(value = "mobileid", required = false) String mobileid,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "certid", required = false) String certid,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "nickname", required = false) String nickname,
			@ModelAttribute("page") Page<Tuserinfo> page, ModelAndView view) {
		logger.info("tuserinfoes/list");
		StringBuilder builder = new StringBuilder("where");
		List<Object> params = new ArrayList<Object>();
		if (!StringUtil.isEmpty(userno)) {
			builder.append(" o.userno=? and");
			params.add(userno);
		}
		if (!StringUtil.isEmpty(mobileid)) {
			builder.append(" o.mobileid=? and");
			params.add(mobileid);
		}
		if (!StringUtil.isEmpty(email)) {
			builder.append(" o.email=? and");
			params.add(email);
		}
		if (!StringUtil.isEmpty(userName)) {
			builder.append(" o.userName=? and");
			params.add(userName);
		}
		if (!StringUtil.isEmpty(certid)) {
			builder.append(" o.certid=? and");
			params.add(certid);
		}
		if (!StringUtil.isEmpty(name)) {
			builder.append(" o.name=? and");
			params.add(name);
		}
		if (!StringUtil.isEmpty(nickname)) {
			builder.append(" o.nickname=? and");
			params.add(nickname);
		}
		if (builder.toString().endsWith("and")) {
			builder.delete(builder.length() - 3, builder.length());
		}
		if (builder.toString().endsWith("where")) {
			builder.delete(builder.length() - 5, builder.length());
		}
		try {
			if (!StringUtil.isEmpty(builder.toString()))
				Tuserinfo.findList(builder.toString(), " order by o.regtime desc", params, page);

		} catch (Exception e) {
			logger.error("tuserinfoes/list出错", e);
		}
		view.addObject("page", page);
		view.setViewName("tuserinfoes/list");
		return view;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public @ResponseBody
	Page<Tuserinfo> page(@RequestParam(value = "queryParam", required = false) String queryParam,
			@RequestParam(value = "start", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "limit", required = false, defaultValue = "20") int endLine,
			@RequestParam(value = "sort", required = false) String orderBy,
			@RequestParam(value = "dir", required = false) String orderDir) throws Exception {
		logger.info("tuserinfoes/page queryParam:{}", new String[] { queryParam });
		Page<Tuserinfo> page = new Page<Tuserinfo>(startLine, endLine, orderBy, orderDir);
		page = Tuserinfo.findTuserinfoesByPage(queryParam, page);
		return page;
	}

	@RequestMapping(value = "/info")
	public ModelAndView info(@RequestParam(value = "userno") String userno, ModelAndView view) {
		Tuserinfo tuserinfo = Tuserinfo.findTuserinfo(userno);
		Taccount taccount = Taccount.findTaccount(userno);
		Tsubaccount caijin = Tsubaccount.findTsubaccount(userno, SubaccountType.BET);
		Tsubaccount jiangjin = Tsubaccount.findTsubaccount(userno, SubaccountType.PRIZE);
		view.addObject("user", tuserinfo);
		view.addObject("account", taccount);
		view.addObject("bet", caijin);
		view.addObject("prize", jiangjin);
		view.setViewName("tuserinfoes/info");
		return view;
	}

	@RequestMapping("/tlotlist")
	public ModelAndView tlotlist(@RequestParam(value = "userno") String userno,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "lotno", required = false) String lotno,
			@RequestParam(value = "agencyno", required = false) String agencyno,
			@RequestParam(value = "batchcode", required = false) String batchcode,
			@RequestParam(value = "prize", required = false) String prize,
			@RequestParam(value = "state", required = false) BigDecimal state,
			@RequestParam(value = "bettype", required = false) BigDecimal bettype,
			@ModelAttribute("page") Page<Tlot> page, ModelAndView view) {
		logger.info("tuserinfoes/tlotlist");
		try {
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			builder.append(" (o.userno=? or (o.buyUserno=? and (o.bettype=4 or o.bettype=5))) and");
			params.add(userno);
			params.add(userno);
			if (!StringUtil.isEmpty(starttime)) {
				builder.append(" to_char(o.ordertime, 'yyyy-mm-dd') >= ? and");
				params.add(starttime);
			}
			if (!StringUtil.isEmpty(endtime)) {
				builder.append(" to_char(o.ordertime, 'yyyy-mm-dd') <= ? and");
				params.add(endtime);
			}
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
			if (!StringUtil.isEmpty(prize) && "yes".equals(prize)) {
				builder.append(" o.prizeamt > 0 and");
			}
			if (!StringUtil.isEmpty(prize) && "no".equals(prize)) {
				builder.append(" o.prizeamt = 0 and");
			}
			if (null != state && new BigDecimal(-1).equals(state)) {
				builder.append(" o.state = -1 and");
			}
			if (null != state && BigDecimal.ZERO.equals(state)) {
				builder.append(" o.state = 0 and");
			}
			if (null != state && BigDecimal.ONE.equals(state)) {
				builder.append(" o.state = 1 and");
			}
			if (null != state && new BigDecimal(2).equals(state)) {
				builder.append(" o.state = 2 and");
			}
			if (null != bettype && BetType.touzhu.value().equals(bettype)) {
				builder.append(" o.bettype = 2 and");
			}
			if (null != bettype && BetType.hemai.value().equals(bettype)) {
				builder.append(" o.bettype = 3 and");
			}
			if (null != bettype && BetType.taocan.value().equals(bettype)) {
				builder.append(" o.bettype = 1 and");
			}
			if (null != bettype && BetType.zengsong.value().equals(bettype)) {
				builder.append(" (o.bettype = 4 or o.bettype = 5) and");
			}
			if (null != bettype && BetType.zhuihao.value().equals(bettype)) {
				builder.append(" o.bettype = 0 and");
			}
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			Tlot.findList(builder.toString(), " order by o.ordertime desc", params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tuserinfoes/tlotlist error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.addObject("userno", userno);
		view.setViewName("tuserinfoes/tlotlist");
		return view;
	}

	@RequestMapping("/gotlotlist")
	public ModelAndView goTlotList(@RequestParam(value = "torderid") String torderid,
			@RequestParam(value = "startLine", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "endLine", required = false, defaultValue = "30") int endLine, ModelAndView view) {
		logger.info("tuserinfoes/gotlotlist");
		view.addObject("torderid", torderid);
		view.addObject("startLine", startLine);
		view.addObject("endLine", endLine);
		StringBuffer url = new StringBuffer(propertiesUtil.getLotteryurl() + "/select/getTlotsWithPage?");
		url.append("startLine=" + startLine);
		url.append("&endLine=" + endLine);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("EQS_torderid", torderid);
		view.addObject("torderid", torderid);
		String condition = new JSONSerializer().serialize(map);
		url.append("&condition=" + condition);
		try {
			String resultMessage = HttpUtil.getResultMessage(url.toString());
			resultMessage = JsonUtil.escapeJavaScript(resultMessage);
			view.addObject("result", resultMessage);
			Map<String, String> lotTypes = Lottype.getMap();
			view.addObject("lotTypes", lotTypes);
		} catch (Exception e) {
			logger.error("tuserinfoes/gotlotlist error:", e);
			view.addObject("errormsg", "用户订单信息查询异常");
		}
		view.setViewName("tuserinfoes/tlotlistpage");
		return view;
	}

	@RequestMapping("/useractionlist")
	public ModelAndView showList(@RequestParam(value = "userno") String userno,
			@RequestParam(value = "lotno", required = false) String lotno,
			@RequestParam(value = "batchcode", required = false) String batchcode,
			@RequestParam(value = "bettype", required = false) String bettype,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "startamt", required = false) String startamt,
			@RequestParam(value = "endamt", required = false) String endamt,
			@RequestParam(value = "orderBy", required = false) String orderBy,
			@RequestParam(value = "orderDir", required = false) String orderDir,
			@RequestParam(value = "startLine", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "endLine", required = false, defaultValue = "30") int endLine, ModelAndView view) {
		logger.info("tuserinfoes/useractionlist");
		view.addObject("startLine", startLine);
		view.addObject("endLine", endLine);
		view.addObject("userno", userno);
		StringBuilder url = new StringBuilder(propertiesUtil.getLotteryurl() + "/select/selectTuseraction?");
		url.append("startLine=" + startLine);
		url.append("&endLine=" + endLine);
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("EQS_orderuserno", userno);
			view.addObject("userno", userno);
			if (StringUtils.isNotBlank(lotno)) {
				map.put("EQS_lotno", lotno);
				view.addObject("lotno", lotno);
			}
			if (StringUtils.isNotBlank(batchcode)) {
				map.put("EQS_batchcode", batchcode);
				view.addObject("batchcode", batchcode);
			}
			if (StringUtils.isNotBlank(bettype)) {
				map.put("EQG_bettype", new BigDecimal(bettype));
				view.addObject("bettype", bettype);
			}
			if (StringUtils.isNotBlank(state)) {
				map.put("EQG_state", new BigDecimal(state));
				view.addObject("state", state);
			}
			if (StringUtils.isNotBlank(starttime)) {
				map.put("GED_modifytime", starttime);
				view.addObject("starttime", starttime);
			}
			if (StringUtils.isNotBlank(endtime)) {
				map.put("LED_modifytime", endtime);
				view.addObject("endtime", endtime);
			}
			if (StringUtils.isNotBlank(startamt)) {
				map.put("GEG_orderamt", new BigDecimal(startamt).multiply(new BigDecimal(100)));
				view.addObject("startamt", startamt);
			}
			if (StringUtils.isNotBlank(endamt)) {
				map.put("LEG_orderamt", new BigDecimal(endamt).multiply(new BigDecimal(100)));
				view.addObject("endamt", endamt);
			}
			if (StringUtils.isNotBlank(orderBy)) {
				url.append("&orderBy=" + orderBy);
			}
			if (StringUtils.isNotBlank(orderDir)) {
				url.append("&orderDir=" + orderDir);
			}
			String condition = new JSONSerializer().serialize(map);
			url.append("&condition=" + condition);
			String resultMessage = HttpUtil.getResultMessage(url.toString());
			resultMessage = JsonUtil.escapeJavaScript(resultMessage);
			view.addObject("result", resultMessage);
			Map<String, String> lotTypes = Lottype.getMap();
			view.addObject("lotTypes", lotTypes);
		} catch (Exception e) {
			logger.error("caselot/listMg error:", e);
			view.addObject("errormsg", "用户投注信息查询异常");
		}
		view.setViewName("tuserinfoes/newlistpage");
		return view;
	}

	/**
	 * 普通投注orderinfo格式转换
	 * 
	 * @param lotno
	 * @param orderinfo
	 * @param multiple
	 * @param view
	 * @return
	 */
	@RequestMapping("/parseOrderinfo")
	public ModelAndView parseOrderinfo(@RequestParam(value = "lotno") String lotno,
			@RequestParam(value = "orderinfo") String orderinfo, @RequestParam(value = "multiple") String multiple,
			ModelAndView view) {
		logger.info("/parseOrderinfo lotno:{},orderinfo:{},multiple:{}", new String[] { lotno, orderinfo, multiple });
		List<String> result = new ArrayList<String>();
		try {
			JSONArray parseBetCodes = betCodeParseOrderInfoUtil.parseBetCode(lotno, orderinfo, multiple);
			if (parseBetCodes != null) {
				for (int j = 0; j < parseBetCodes.size(); j++) {
					net.sf.json.JSONObject parseBetCodeJson = (net.sf.json.JSONObject) parseBetCodes.get(j);
					result.add(parseBetCodeJson.getString("betCode"));
				}
			}
			view.addObject("result", result);
			view.setViewName("tuserinfoes/showtorderinfoes");
		} catch (Exception e) {
			logger.error("/parseOrderinfo error", e);
		}
		return view;
	}

	/**
	 * 竞彩投注格式转换
	 * 
	 * @param lotno
	 * @param orderId
	 * @param multiple
	 * @param isCaseLot
	 * @param view
	 * @return
	 */
	@RequestMapping("/parseOrderinfoJingCai")
	public ModelAndView parseOrderinfoJingCai(@RequestParam(value = "lotno") String lotno,
			@RequestParam(value = "orderId") String orderId, @RequestParam(value = "multiple") String multiple,
			@RequestParam(value = "isCaseLot") boolean isCaseLot, ModelAndView view) {
		logger.info("/parseOrderinfoJingCai lotno:{},orderId:{},multiple:{},isCaseLot:{}", new String[] { lotno,
				orderId, multiple, isCaseLot + "" });
		List<String> result = new ArrayList<String>();
		try {
			JSONArray parseBetCodes = betCodeParseJingCaiUtil.parseBetCode(lotno, orderId, multiple, isCaseLot);
			if (parseBetCodes != null) {
				for (int j = 0; j < parseBetCodes.size(); j++) {
					net.sf.json.JSONObject parseBetCodeJson = (net.sf.json.JSONObject) parseBetCodes.get(j);
					result.add(parseBetCodeJson.getString("betCode"));
				}
			}
			view.addObject("result", result);
			view.setViewName("tuserinfoes/showtorderinfoes");
		} catch (Exception e) {
			logger.error("/parseOrderinfoJingCai error", e);
		}
		return view;
	}

	@RequestMapping("/caselotlist")
	public ModelAndView caselotlist(@RequestParam(value = "userno") String userno,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "lotno", required = false) String lotno,
			@RequestParam(value = "batchcode", required = false) String batchcode,
			@RequestParam(value = "caselotId", required = false) String caselotId,
			@RequestParam(value = "prize", required = false) String prize,
			@ModelAttribute("page") Page<CaseLotAndCaseLotBuyDTO> page, ModelAndView view) {
		logger.info("tuserinfoes/caselotlist");
		try {
			StringBuilder builder = new StringBuilder(" where t.id = o.caselotId and ");
			List<Object> params = new ArrayList<Object>();
			builder.append("o.userno=? and");
			params.add(userno);
			if (!StringUtil.isEmpty(starttime)) {
				builder.append(" to_char(o.buyTime, 'yyyy-mm-dd') >= ? and");
				params.add(starttime);
			}
			if (!StringUtil.isEmpty(endtime)) {
				builder.append(" to_char(o.buyTime, 'yyyy-mm-dd') <= ? and");
				params.add(endtime);
			}
			if (!StringUtil.isEmpty(caselotId)) {
				builder.append(" o.caselotId=? and");
				params.add(caselotId);
			}
			if (!StringUtil.isEmpty(lotno)) {
				builder.append(" t.lotno=? and");
				params.add(lotno);
			}
			if (!StringUtil.isEmpty(batchcode)) {
				builder.append(" t.batchcode=? and");
				params.add(batchcode);
			}
			if (!StringUtil.isEmpty(prize) && "yes".equals(prize)) {
				builder.append(" o.prizeAmt > 0 and");
			}
			if (!StringUtil.isEmpty(prize) && "no".equals(prize)) {
				builder.append(" o.prizeAmt = 0 and");
			}
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			CaseLotBuy.findList(builder.toString(), " order by o.buyTime desc", params, page);
			view.addObject("params", params);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tuserinfoes/caselotlist error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.addObject("userno", userno);
		view.setViewName("tuserinfoes/caselotlist");
		return view;
	}

	@RequestMapping("/tchargelist")
	public ModelAndView tchargelist(@RequestParam("userno") String userno,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "bankid", required = false) String bankid,
			@RequestParam(value = "state", required = false) BigDecimal state,
			@ModelAttribute("page") Page<Ttransaction> page, ModelAndView view) {
		logger.info("/tuserinfoes/tchargelist");
		try {
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			builder.append(" o.userno=? and (o.type=2 or o.type=3 or o.type=10) and");
			params.add(userno);
			if (!StringUtil.isEmpty(starttime)) {
				builder.append(" to_char(o.plattime, 'yyyy-mm-dd') >= ? and");
				params.add(starttime);
			}
			if (!StringUtil.isEmpty(endtime)) {
				builder.append(" to_char(o.plattime, 'yyyy-mm-dd') <= ? and");
				params.add(endtime);
			}
			if (!StringUtil.isEmpty(bankid)) {
				builder.append(" o.bankid = ? and");
				params.add(bankid);
			}
			if (null != state && state.equals(BigDecimal.ZERO)) {
				builder.append(" o.state = 0 and");
			}
			if (null != state && state.equals(BigDecimal.ONE)) {
				builder.append(" o.state = 1 and");
			}
			if (null != state && state.equals(new BigDecimal(2))) {
				builder.append(" o.state = 2 and");
			}
			if (null != state && state.equals(new BigDecimal(3))) {
				builder.append(" o.state = 3 and");
			}
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			Ttransaction.findList(builder.toString(), " order by o.plattime desc", params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tuserinfoes/tchargelist", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.addObject("userno", userno);
		view.setViewName("tuserinfoes/tchargelist");
		return view;
	}

	@RequestMapping("/tsubscribelist")
	public ModelAndView tsubscribelist(@RequestParam(value = "userno") String userno,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "lotno", required = false) String lotno,
			@RequestParam(value = "state", required = false) BigDecimal state,
			@ModelAttribute("page") Page<Tsubscribe> page, ModelAndView view) {
		logger.info("tuserinfoes/tsubscribelist");
		try {
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			builder.append(" o.userno=? and");
			params.add(userno);
			if (!StringUtil.isEmpty(starttime)) {
				builder.append(" to_char(o.ordertime, 'yyyy-mm-dd') >= ? and");
				params.add(starttime);
			}
			if (!StringUtil.isEmpty(endtime)) {
				builder.append(" to_char(o.ordertime, 'yyyy-mm-dd') <= ? and");
				params.add(endtime);
			}
			if (!StringUtil.isEmpty(lotno)) {
				builder.append(" o.lotno=? and");
				params.add(lotno);
			}
			if (null != state && BigDecimal.ZERO.equals(state)) {
				builder.append(" o.state = 0 and");
			}
			if (null != state && BigDecimal.ONE.equals(state)) {
				builder.append(" o.state = 1 and");
			}
			if (null != state && new BigDecimal(2).equals(state)) {
				builder.append(" o.state = 2 and");
			}
			if (null != state && new BigDecimal(3).equals(state)) {
				builder.append(" o.state = 3 and");
			}
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			Tsubscribe.findList(builder.toString(), " order by o.ordertime desc", params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tuserinfoes/tsubscribelist error", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.addObject("userno", userno);
		view.setViewName("tuserinfoes/tsubscribelist");
		return view;
	}

	@RequestMapping("/tsubscribeinfo")
	public ModelAndView tsubscribelist(@RequestParam(value = "flowno") String flowno, ModelAndView view) {
		logger.info("tuserinfoes/tsubscribeinfo");
		try {
			view.addObject("torders", Torder.findBySubscribeno(flowno));
		} catch (Exception e) {
			view.addObject("errormsg", e.getMessage());
			logger.error("tuserinfoes/tsubscribeinfo error", e);
		}
		view.setViewName("tuserinfoes/tsubscribeinfo");
		return view;
	}

	@RequestMapping("/tpresentlist")
	public ModelAndView tpresentlist(@RequestParam("userno") String userno,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@ModelAttribute("page") Page<Ttransaction> page, ModelAndView view) {
		logger.info("/tuserinfoes/tpresentlist");
		view.addObject("userno", userno);
		view.setViewName("tuserinfoes/tpresentlist");
		return view;
	}

	@Autowired
	private PropertiesUtil propertiesUtil;

	@RequestMapping("/updateUserState")
	public ModelAndView updateUserState(@RequestParam("userno") String userno,
			@RequestParam(value = "state") BigDecimal state, @ModelAttribute("page") Page<Ttransaction> page,
			ModelAndView view) {
		logger.info("/tuserinfoes/updateUserState");
		String errormsg = "修改成功";
		try {
			logger.info("用户：" + userno + "，状态：" + state);
			String result = HttpUtil.post(propertiesUtil.getLotteryurl() + "/tuserinfoes/updateState", "userno="
					+ userno + "&state=" + state);
			JSONObject json = new JSONObject(result);
			if (!"0".equals(json.getString("errorCode"))) {
				errormsg = "修改失败，返回码：" + json.getString("errorCode");
			}
		} catch (Exception e) {
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		// 修改用户状态 用户状态：0关闭 1正常 2暂停
		return this.accountoverview(userno, view);
	}

	@RequestMapping("/updateUserCertid")
	public ModelAndView updateUserCertid(@RequestParam("userno") String userno,
			@RequestParam(value = "certid") String certid, @ModelAttribute("page") Page<Ttransaction> page,
			ModelAndView view) {
		logger.info("/tuserinfoes/updateUserCertid");
		String errormsg = "修改成功";
		try {
			logger.info("用户：" + userno + "，certid：" + certid);
			String result = HttpUtil.post(propertiesUtil.getLotteryurl() + "/tuserinfoes/modify", "userno=" + userno
					+ "&certid=" + certid);
			JSONObject json = new JSONObject(result);
			if (!"0".equals(json.getString("errorCode"))) {
				errormsg = "修改失败，返回码：" + json.getString("errorCode");
			}
		} catch (Exception e) {
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.accountoverview(userno, view);
	}

	@RequestMapping("/updateUserPwd")
	public ModelAndView updateUserPwd(@RequestParam("userno") String userno, @RequestParam(value = "pwd") String pwd,
			ModelAndView view) {
		logger.info("/tuserinfoes/updateUserPwd");
		String errormsg = "重置成功";
		try {
			if (StringUtil.isEmpty(pwd) || pwd.length() < 6) {
				errormsg = "密码为空或小于6";
			} else {
				logger.info("用户名：" + userno + "，密码：" + pwd);
				String result = HttpUtil.post(propertiesUtil.getLotteryurl() + "/tuserinfoes/resetPassword", "userno="
						+ userno + "&password=" + pwd);
				JSONObject json = new JSONObject(result);
				if (!"0".equals(json.getString("errorCode"))) {
					errormsg = "重置失败，返回码：" + json.getString("errorCode");
				}
			}
		} catch (Exception e) {
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		// 修改用户状态 用户状态：0关闭 1正常 2暂停
		return this.accountoverview(userno, view);
	}

	@Autowired
	private SMSUtil smsUtil;

	@RequestMapping("/updateUserPwdSJ")
	public ModelAndView updateUserPwdSJ(@RequestParam("userno") String userno,
			@RequestParam("mobileid") String mobileid, ModelAndView view) {
		logger.info("/tuserinfoes/updateUserPwd");
		if (StringUtil.isEmpty(mobileid)) {
			view.addObject("errormsg", "手机号码必填");
			this.accountoverview(userno, view);
		}
		String errormsg = "重置成功";
		try {
			String pwd = (100000 + new Random().nextInt(900000)) + "";
			logger.info("用户名：" + userno + ",密码：" + pwd);
			String result = HttpUtil.post(propertiesUtil.getLotteryurl() + "/tuserinfoes/resetPassword", "userno="
					+ userno + "&password=" + pwd);
			JSONObject json = new JSONObject(result);
			if (!"0".equals(json.getString("errorCode"))) {
				errormsg = "重置失败，返回码：" + json.getString("errorCode");
			} else {
				result = smsUtil.sendSMSNotry(mobileid, "新密码： " + pwd + "  为保证你的安全，请重新设置密码。");
				if (!"1".equals(result)) {
					errormsg = "发送短信失败，返回码：" + json.getString("errorCode");
				} else {
					errormsg += "，短信发送成功，发送手机号为：" + mobileid;
				}
			}
		} catch (Exception e) {
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		// 修改用户状态 用户状态：0关闭 1正常 2暂停
		return this.accountoverview(userno, view);
	}

	@RequestMapping("/tcashlist")
	public ModelAndView tcashlist(@RequestParam("userno") String userno,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "state", required = false) BigDecimal state,
			@RequestParam(value = "type", required = false) BigDecimal type,
			@ModelAttribute("page") Page<Tcashdetail> page, ModelAndView view) {
		logger.info("/tuserinfoes/tcashlist");
		try {
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			builder.append(" o.userno=? and");
			params.add(userno);
			if (!StringUtil.isEmpty(starttime)) {
				builder.append(" to_char(o.plattime, 'yyyy-mm-dd') >= ? and");
				params.add(starttime);
			}
			if (!StringUtil.isEmpty(endtime)) {
				builder.append(" to_char(o.plattime, 'yyyy-mm-dd') <= ? and");
				params.add(endtime);
			}
			if (null != state) {
				if (state.equals(BigDecimal.ONE)) {
					builder.append(" o.state = 1 and");
				}
				if (state.equals(new BigDecimal(104))) {
					builder.append(" o.state = 104 and");
				}
				if (state.equals(new BigDecimal(105))) {
					builder.append(" o.state = 105 and");
				}
				if (state.equals(new BigDecimal(106))) {
					builder.append(" o.state = 106 and");
				}
				view.addObject("state", state);
			}
			if (type != null) {
				builder.append(" o.type = ? ");
				params.add(type);
			}

			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}
			Tcashdetail.findList(builder.toString(), " order by o.plattime desc", params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tuserinfoes/tcashlist", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.addObject("userno", userno);
		view.setViewName("tuserinfoes/tcashlist");
		return view;
	}

	@RequestMapping(value = "/accountoverview")
	public ModelAndView accountoverview(@RequestParam(value = "userno") String userno, ModelAndView view) {
		try {
			Tuserinfo tuserinfo = Tuserinfo.findTuserinfo(userno);
			Taccount taccount = Taccount.findTaccount(userno);

			AccountOverview ao = new AccountOverview();
			ao.setUserno(userno);
			ao.setBalance(taccount.getBalance().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP));
			ao.setDrawbalance(taccount.getDrawbalance().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP));
			ao.setFreezebalance(taccount.getFreezebalance().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP));

			BigDecimal incharge = Ttransaction.findUserCharge(userno).divide(new BigDecimal(100), 2,
					BigDecimal.ROUND_HALF_UP);
			ao.setIncharge(incharge);
			BigDecimal inpresent = Taccountdetail.findUserPresent(userno).divide(new BigDecimal(100), 2,
					BigDecimal.ROUND_HALF_UP);
			ao.setInpresent(inpresent);// 不太准确
//			BigDecimal inprize = (Tlot.findUserPrize(userno).add(CaseLotBuy.findUserHMPrize(userno))).divide(
//					new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
			BigDecimal inprize = Taccountdetail.findUserPrize(userno).divide(new BigDecimal(100), 2,
					BigDecimal.ROUND_HALF_UP);
			ao.setInprize(inprize);// Taccountdetail.findUserPrize(userno)不太准
//			BigDecimal inzjjj = Taccountdetail.findUserZJJJ(userno).divide(new BigDecimal(100), 2,
//					BigDecimal.ROUND_HALF_UP);
//			ao.setInzjjj(inzjjj);
//			BigDecimal inhmbackprize = Taccountdetail.findUserHMBackPrize(userno).divide(new BigDecimal(100), 2,
//					BigDecimal.ROUND_HALF_UP);
//			ao.setInhmbackprize(inhmbackprize);
//			BigDecimal inhmfdbackprize = Taccountdetail.findUserHMFDBackPrize(userno).divide(new BigDecimal(100), 2,
//					BigDecimal.ROUND_HALF_UP);
//			ao.setInhmfdbackprize(inhmfdbackprize);
//			BigDecimal inhmyj = Taccountdetail.findUserHMYJ(userno).divide(new BigDecimal(100), 2,
//					BigDecimal.ROUND_HALF_UP);
//			ao.setInhmyj(inhmyj);
////			BigDecimal inqxzh = Taccountdetail.findUserQXZH(userno).divide(new BigDecimal(100), 2,
////					BigDecimal.ROUND_HALF_UP);
//			BigDecimal inqxzh = BigDecimal.ZERO;
//			ao.setInqxzh(inqxzh);
			BigDecimal jfdhcj = Taccountdetail.findUserJFDHCJ(userno).divide(new BigDecimal(100), 2,
					BigDecimal.ROUND_HALF_UP);
			ao.setJfdhcj(jfdhcj);
			// BigDecimal inheji =
			// incharge.add(inpresent).add(inprize).add(inzjjj).add(inhmfdbackprize).add(inhmyj).add(inqxzh).add(jfdhcj);
//			BigDecimal inheji = incharge.add(inpresent).add(inprize).add(inzjjj).add(inhmfdbackprize).add(inqxzh)
//					.add(jfdhcj);
			BigDecimal inheji = incharge.add(inpresent).add(inprize).add(jfdhcj);
			ao.setInheji(inheji);

			BigDecimal outchargefee = Ttransaction.findUserChargeFee(userno).divide(new BigDecimal(100), 2,
					BigDecimal.ROUND_HALF_UP);
			ao.setOutchargefee(outchargefee);
			BigDecimal outcashfee = Tcashdetail.findUserCashFee(userno).divide(new BigDecimal(100), 2,
					BigDecimal.ROUND_HALF_UP);
			ao.setOutcashfee(outcashfee);
			BigDecimal outcash = Tcashdetail.findUserCash(userno).divide(new BigDecimal(100), 2,
					BigDecimal.ROUND_HALF_UP);
			ao.setOutcash(outcash);
			BigDecimal outverifycash = Tcashdetail.findUserVerifyCash(userno).divide(new BigDecimal(100), 2,
					BigDecimal.ROUND_HALF_UP);
			ao.setOutverifycash(outverifycash);
			BigDecimal outbetsuccess = Taccountdetail.findUserBetSuccess(userno).divide(new BigDecimal(100), 2,
					BigDecimal.ROUND_HALF_UP);// Tlot Torder 
			ao.setOutbetsuccess(outbetsuccess);
//			BigDecimal outsubscribesuccessTemp = Torder.findUserSubscribeSuccess(userno);// Tlot Torder
//			BigDecimal outsubscribesuccess = outsubscribesuccessTemp.divide(new BigDecimal(100), 2,
//					BigDecimal.ROUND_HALF_UP);
			BigDecimal outsubscribesuccess = Taccountdetail.findUserSubscribeSuccess(userno).divide(new BigDecimal(100), 2,
					BigDecimal.ROUND_HALF_UP);
			ao.setOutsubscribesuccess(outsubscribesuccess);
//			BigDecimal outhmsuccess = CaseLotBuy.findUserBetSuccessHM(userno).divide(new BigDecimal(100), 2,
//					BigDecimal.ROUND_HALF_UP);
			BigDecimal outhmsuccess = Taccountdetail.findUserBetSuccessHM(userno).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
			ao.setOuthmsuccess(outhmsuccess);
//			BigDecimal outyczhTemp = (Taccountdetail.findUserYCZH(userno).subtract(outsubscribesuccessTemp))
//					.compareTo(BigDecimal.ZERO) > 0 ? Taccountdetail.findUserYCZH(userno).subtract(
//					outsubscribesuccessTemp) : BigDecimal.ZERO;
//			BigDecimal outyczh = (outyczhTemp).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);
//			ao.setOutyczh(outyczh);
			BigDecimal outyczh = BigDecimal.ZERO;
			BigDecimal ouheji = outchargefee.add(outcashfee).add(outcash).add(outverifycash).add(outbetsuccess)
					.add(outsubscribesuccess).add(outhmsuccess).add(outyczh);
			ao.setOuheji(ouheji);

			view.addObject("user", tuserinfo);
			view.addObject("ao", ao);
		} catch (Exception e) {
			e.printStackTrace();
		}
		view.setViewName("tuserinfoes/accountoverview");
		return view;
	}

	@RequestMapping(value = "/taccountdetailinfo")
	public ModelAndView taccountdetailInfo(@RequestParam(value = "userno") String userno, ModelAndView view) {
		try {
			List<Object[]> list = Taccountdetail.getTaccountdetailInfo(userno);
			int i = 0;
			for (Object[] o : list) {
//				logger.info("o[0]=" + o[0]);
//				String type = TransactionType.getMemo((BigDecimal)o[0]);
//				logger.info("type=" + type);
//				o[0] = type;
//				for (Object a : o) {
//					logger.info("a=" + a);
//				}	
				o[0] = TransactionType.getMemo((BigDecimal)o[0]);
			}
			view.addObject("list", list);
			view.addObject("userno", userno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		view.setViewName("tuserinfoes/taccountdetailinfo");
		return view;
	}
	@RequestMapping("/taccountdetaillist")
	public ModelAndView taccountdetaillist(@RequestParam("userno") String userno,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "transactiontype", required = false) BigDecimal transactiontype,
			@ModelAttribute("page") Page<Taccountdetail> page, ModelAndView view) {
		logger.info("/tuserinfoes/taccountdetaillist");
		StringBuilder builder = new StringBuilder(" where o.userno=? ");
		List<Object> params = new ArrayList<Object>();
		params.add(userno);
		if (!StringUtil.isEmpty(starttime)) {
			builder.append(" and to_char(o.plattime, 'yyyy-mm-dd') >= ?");
			params.add(starttime);
		}
		if (!StringUtil.isEmpty(endtime)) {
			builder.append(" and to_char(o.plattime, 'yyyy-mm-dd') <= ?");
			params.add(endtime);
		}
		if (transactiontype != null) {
			builder.append(" and o.ttransactiontype = ?");
			params.add(transactiontype);
		}
		try {
			Taccountdetail.findList(builder.toString(), " order by o.plattime desc", params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tuserinfoes/taccountdetaillist", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.addObject("userno", userno);
		view.setViewName("tuserinfoes/taccountdetaillist");
		return view;
	}

	@RequestMapping("/nineteenpaylist")
	public ModelAndView nineteenpaylist(@RequestParam(value = "transactionid", required = false) String transactionid,
			@RequestParam(value = "userno", required = false) String userno,
			@RequestParam(value = "cardtype", required = false) String cardtype,
			@RequestParam(value = "cardno", required = false) String cardno,
			@RequestParam(value = "cardpwd", required = false) String cardpwd,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "state", required = false) String state,
			@ModelAttribute("page") Page<Map<String, Object>> page, ModelAndView view) {
		logger.info("/tuserinfoes/nineteenpaylist");
		try {
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			builder.append(" 1=1 and");
			if (!StringUtil.isEmpty(transactionid)) {
				builder.append(" o.transactionid = ? and");
				params.add(transactionid);
				view.addObject("transactionid", transactionid);
			}
			if (!StringUtil.isEmpty(userno)) {
				builder.append(" o.userno = ? and");
				params.add(userno);
			}
			if (!StringUtil.isEmpty(cardno)) {
				builder.append(" o.cardno = ? and");
				params.add(cardno);
			}
			if (!StringUtil.isEmpty(cardpwd)) {
				builder.append(" o.cardpwd = ? and");
				params.add(cardpwd);
			}
			if (!StringUtil.isEmpty(cardtype)) {
				builder.append(" o.cardtype = ? and");
				params.add(cardtype);
			}
			if (!StringUtil.isEmpty(starttime)) {
				builder.append(" o.chargetime >= ? and");
				params.add(starttime);
				view.addObject("starttime", starttime);
			}
			if (!StringUtil.isEmpty(endtime)) {
				builder.append(" o.chargetime <= ? and");
				params.add(endtime);
				view.addObject("endtime", endtime);
			}
			if (!StringUtil.isEmpty(state)) {
				builder.append(" o.state = ? and");
				params.add(state);
			}
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}

			chargecenterDao.findNineteenpayList(builder.toString(), params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tuserinfoes/nineteenpaylist", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.addObject("userno", userno);
		view.setViewName("tuserinfoes/nineteenpaylist");
		return view;
	}

	@RequestMapping("/dnapaylist")
	public ModelAndView dnapaylist(@RequestParam(value = "transactionid", required = false) String transactionid,
			@RequestParam(value = "userno", required = false) String userno,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@RequestParam(value = "state", required = false) String state,
			@ModelAttribute("page") Page<Map<String, Object>> page, ModelAndView view) {
		logger.info("/tuserinfoes/dnapaylist");
		try {
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			builder.append(" 1=1 and");
			if (!StringUtil.isEmpty(transactionid)) {
				builder.append(" o.transactionid = ? and");
				params.add(transactionid);
			}
			if (!StringUtil.isEmpty(userno)) {
				builder.append(" o.userno = ? and");
				params.add(userno);
			}
			if (!StringUtil.isEmpty(starttime)) {
				builder.append(" o.chargetime >= ? and");
				params.add(starttime);
				view.addObject("starttime", starttime);
			}
			if (!StringUtil.isEmpty(endtime)) {
				builder.append(" o.chargetime <= ? and");
				params.add(endtime);
				view.addObject("endtime", endtime);
			}
			if (!StringUtil.isEmpty(state)) {
				builder.append(" o.state = ? and");
				params.add(state);
			}
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}

			chargecenterDao.findDnapayList(builder.toString(), params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tuserinfoes/dnapaylist", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.addObject("userno", userno);
		view.setViewName("tuserinfoes/dnapaylist");
		return view;
	}

	@RequestMapping("/talibatchpaylist")
	public ModelAndView talibatchpaylist(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "account", required = false) String account,
			@RequestParam(value = "starttime", required = false) String starttime,
			@RequestParam(value = "endtime", required = false) String endtime,
			@ModelAttribute("page") Page<Map<String, Object>> page, ModelAndView view) {
		logger.info("/tuserinfoes/talibatchpaylist");
		try {
			StringBuilder builder = new StringBuilder(" where");
			List<Object> params = new ArrayList<Object>();
			builder.append(" 1=1 and");
			if (!StringUtil.isEmpty(id)) {
				builder.append(" o.id = ? and");
				params.add(id);
			}
			if (!StringUtil.isEmpty(name)) {
				builder.append(" o.detailData like ? and");
				params.add("%" + name + "%");
			}
			if (!StringUtil.isEmpty(account)) {
				builder.append(" o.detailData like ? and");
				params.add("%" + account + "%");
			}
			if (!StringUtil.isEmpty(starttime)) {
				builder.append(" o.payDate >= ? and");
				params.add(starttime + " 0:0:0");
				view.addObject("starttime", starttime);
			}
			if (!StringUtil.isEmpty(endtime)) {
				builder.append(" o.payDate <= ? and");
				params.add(endtime + " 23:59:59");
				view.addObject("endtime", endtime);
			}
			if (builder.toString().endsWith("and")) {
				builder.delete(builder.length() - 3, builder.length());
			}
			if (builder.toString().endsWith("where")) {
				builder.delete(builder.length() - 5, builder.length());
			}

			chargecenterDao.findTalibatchpayList(builder.toString(), params, page);
			view.addObject("page", page);
		} catch (Exception e) {
			logger.error("tuserinfoes/talibatchpaylist", e);
			view.addObject("errormsg", e.getMessage());
		}
		view.setViewName("tuserinfoes/talibatchpaylist");
		return view;
	}
}
