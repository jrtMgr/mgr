package com.ruyicai.mgr.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ibm.icu.math.BigDecimal;
import com.ruyicai.mgr.controller.dto.CaseLotCelebrityDTO;
import com.ruyicai.mgr.domain.CaseLot;
import com.ruyicai.mgr.domain.CaseLotCelebrity;
import com.ruyicai.mgr.domain.CreateUserEventLog;
import com.ruyicai.mgr.domain.Tloguser;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.exception.RuyicaiException;
import com.ruyicai.mgr.lot.Lottype;
import com.ruyicai.mgr.util.DateUtil;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.JsonUtil;
import com.ruyicai.mgr.util.PropertiesUtil;

import flexjson.JSONSerializer;

@RequestMapping(value = "/caselot")
@Controller
public class TcaselotController {

	private Logger logger = Logger.getLogger(TcaselotController.class);

	@Autowired
	PropertiesUtil propertiesUtil;
	@RequestMapping("/huifuUI")
	public ModelAndView huifuUI(@RequestParam("c") String c,
			@RequestParam("b") String b,
			ModelAndView view){
		logger.info("caselot/editUI");
		try {
			String name = new String(b.getBytes("iso8859-1"),"utf-8");
			view.addObject("d", name);
		} catch (UnsupportedEncodingException e) {
			logger.error("caselot/huifuUI error", e);
		}
		view.setViewName("caselot/editUI");
		return view;
	}
	
	@RequestMapping(value = "/updateDescription")
	public ModelAndView reply(@RequestParam("caseid") String caseid,
			@RequestParam("description") String description,ModelAndView view) {
		logger.info("caselot/reply");
		String errormsg = "保存成功";
		try {
			StringBuilder param = new StringBuilder();
			param.append("caseid=").append(caseid).append("&description=")
			.append(URLEncoder.encode(description, "UTF-8"));
			String url = propertiesUtil.getLotteryurl()+"/caselot/updateDescription";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg  =  json.toString();
			} 
		} catch (Exception e) {
			logger.error("caselot/updateDescription error", e);
			errormsg = "保存失败"+e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("caselot/editUI");
		return view;
	}
	
	@RequestMapping("/listMg")
	public ModelAndView listMg(@RequestParam(value = "sortState", required = false, defaultValue = "0") int sortState,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "lotno", required = false, defaultValue = "") String lotno,
			@RequestParam(value = "startLine", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "endLine", required = false, defaultValue = "20") int endLine,
			@RequestParam(value = "orderBy", required = false, defaultValue = "startTime") String orderBy,
			@RequestParam(value = "orderDir", required = false, defaultValue = "") String orderDir, ModelAndView view) {
		logger.info("caselot/page");
		view.addObject("startLine", startLine);
		view.addObject("endLine", endLine);
		view.addObject("orderBy", orderBy);
		view.addObject("orderDir", orderDir);
		try {
			// 合买分页数据
			StringBuilder url = new StringBuilder(propertiesUtil.getLotteryurl()
					+ "/select/selectCaseLots?state=1&state=2&state=3");
			if (StringUtils.isNotBlank(search)) {
				url.append("&search=" + search);
				view.addObject("search", search);
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("EQI_sortState", sortState + "");
			view.addObject("sortState", sortState);
			if (StringUtils.isNotBlank(lotno)) {
				map.put("EQS_lotno", lotno);
				view.addObject("lotno", lotno);
			}
			url.append("&startLine=" + startLine);
			url.append("&endLine=" + endLine);
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
			view.addObject("errormsg", "合买置顶管理分页查询异常");
		}
		view.setViewName("caselot/listMg");
		return view;
	}
	
	@RequestMapping(value = "/updateCaselotBySortState", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData updateCaselotBySortState(@RequestParam("caselotid") String caselotid,
			@RequestParam("sortState") int sortState, ModelAndView view) {
		logger.info("caselot/updateCaseLotApplyForTopper");
		ResponseData rd = new ResponseData();
		String errormsg = "操作已成功";
		ErrorCode errorcode = ErrorCode.OK;
		//StringBuffer param = new StringBuffer();
		//param.append("caselotid=").append(caselotid).append("&sortState=").append(sortState);
		try {
			//CaseLot.updateCaseLotBySortState(caselotid, sortState);
			CaseLot c = CaseLot.findCaseLot(caselotid);
			c.setSortState(sortState);
			c.merge();
		} catch (Exception e) {
			e.printStackTrace();
			errormsg = "操作失败";
			errorcode = ErrorCode.ERROR;
		}
		rd.setErrorCode(errorcode.value);
		rd.setValue(errormsg);
		return rd;
	}

	/**
	 * 初始化合买名人树菜单
	 * 
	 * @param view
	 * @return
	 */
	@RequestMapping("/listCelebrity")
	public ModelAndView listCelebrity(ModelAndView view) {
		logger.info("caselot/listCelebrity");
		Map<String, String> lotTypes = new LinkedHashMap<String, String>();
		lotTypes.put("center", "合买中心");
		lotTypes.putAll(Lottype.getMap());
		view.addObject("lotTypes", lotTypes);
		return view;
	}

	/**
	 * 合买名人树请求
	 * 
	 * @param node
	 * @param view
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectCelebrity")
	public @ResponseBody
	List<CaseLotCelebrityDTO> selectCelebrity(@RequestParam("node") String node) throws Exception {
		logger.info("caselot/selectCelebrity");
		TypedQuery<CaseLotCelebrity> query = CaseLotCelebrity.findCaseLotCelebritysByUsernoAndLotno(null, node);
		List<CaseLotCelebrity> celebrityList = query.getResultList();
		List<CaseLotCelebrityDTO> resultList = new ArrayList<CaseLotCelebrityDTO>();
		for (CaseLotCelebrity celebrity : celebrityList) {
			Tuserinfo userinfo = Tuserinfo.findTuserinfo(celebrity.getUserno());
			CaseLotCelebrityDTO dto = null;
			if (userinfo == null) {
				dto = new CaseLotCelebrityDTO(celebrity.getId(), "", celebrity.getUserno(), celebrity.getLotno(),
						celebrity.getSortno());
			} else {
				dto = new CaseLotCelebrityDTO(celebrity.getId(), userinfo.getNickname(), celebrity.getUserno(),
						celebrity.getLotno(), celebrity.getSortno());
			}
			resultList.add(dto);
		}
		return resultList;
	}

	/**
	 * 删除合买名人
	 * 
	 * @param userno
	 *            用户编号
	 * @param lotno
	 *            彩种编号
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/removeCelebrity", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData removeCelebrity(@RequestParam("userno") String userno, @RequestParam("lotno") String lotno)
			throws Exception {
		logger.info("caselot/removeCelebrity");
		ResponseData rd = new ResponseData();
		ErrorCode result = ErrorCode.OK;
		try {
			CaseLotCelebrity.removeCaseLotCelebrity(userno, lotno);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除合买名人 出错 " + e.getMessage());
			throw new Exception(e);
		}
		rd.setValue(result.memo);
		rd.setErrorCode(result.value);
		return rd;
	}

	/**
	 * 创建合买名人
	 * 
	 * @param userno
	 *            用户编号
	 * @param lotno
	 *            彩种编号
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/createCaseLotCelebrity", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData createCaseLotCelebrity(@RequestParam("userno") String userno, @RequestParam("lotno") String lotno)
			throws Exception {
		logger.info("caselot/createCaseLotCelebrity");
		ResponseData rd = new ResponseData();
		ErrorCode result = ErrorCode.OK;
		CaseLotCelebrity celebrity = CaseLotCelebrity.createIfNotExist(userno, lotno);
		rd.setValue(celebrity);
		rd.setErrorCode(result.value);
		return rd;
	}

	/**
	 * 排序合买名人
	 * 
	 * @param data
	 *            某彩种下名人数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sortCelebrity", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData sortCelebrity(@RequestParam("data") String data) throws Exception {
		logger.info("caselot/sortCelebrity");
		ResponseData rd = new ResponseData();
		ErrorCode result = ErrorCode.OK;
		try {
			List<CaseLotCelebrity> celebritys = (List<CaseLotCelebrity>) CaseLotCelebrity
					.fromJsonArrayToCaseLotCelebritys(data);
			CaseLotCelebrity.sortCelebrityByLotno(celebritys);
			rd.setValue(result.memo);
		} catch (RuyicaiException e) {
			e.printStackTrace();
			logger.error("排序合买名人出错, errorcode:" + e.getErrorCode().value);
			result = e.getErrorCode();
			rd.setValue(e.getMessage());
			rd.setErrorCode(e.getErrorCode().value);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("排序合买名人出错");
			result = ErrorCode.ERROR;
			rd.setValue(e.getMessage());
			rd.setErrorCode(ErrorCode.ERROR.value);
		}
		rd.setErrorCode(result.value);
		return rd;
	}

	/**
	 * 更新合买名人所在彩种
	 * 
	 * @param newlotno
	 *            新彩种编号
	 * @param userno
	 *            用户编号
	 * @param oldlotno
	 *            旧彩种编号
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCaseLotCelebrity", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData updateCelebrity(@RequestParam("newlotno") String newlotno, @RequestParam("userno") String userno,
			@RequestParam("oldlotno") String oldlotno) throws Exception {
		logger.info("caselot/updateCelebrity");
		ResponseData rd = new ResponseData();
		ErrorCode result = ErrorCode.OK;
		CaseLotCelebrity.updateLotnoOfCaseLotCelebrity(newlotno, userno, oldlotno);
		rd.setValue(result.memo);
		rd.setErrorCode(result.value);
		return rd;
	}

	/**
	 * 合买管理
	 * @param batchcode
	 * @param displayState
	 * @param sortStateFlag
	 * @param search
	 * @param lotno
	 * @param startLine
	 * @param endLine
	 * @param orderBy
	 * @param orderDir
	 * @param view
	 * @return
	 */
	@RequestMapping("/caselotListMg")
	public ModelAndView caselotListMg(@RequestParam(value = "totalAmtStart", required = false , defaultValue = "")String totalAmtStart,
			@RequestParam(value = "totalAmtEnd", required = false, defaultValue = "")String totalAmtEnd,
			@RequestParam(value = "starttime",required = false, defaultValue = "")String starttime,
			@RequestParam(value = "endtime", required = false, defaultValue = "")String endtime,
			@RequestParam(value = "batchcode", required = false, defaultValue = "") String batchcode,
			@RequestParam(value = "displayState", required = false, defaultValue = "") String displayState,
			@RequestParam(value = "sortStateFlag", required = false, defaultValue = "0") int sortStateFlag,
			@RequestParam(value = "search", required = false) String search,
			@RequestParam(value = "mysort", required = false, defaultValue = "") String mysort,
			@RequestParam(value = "lotno", required = false, defaultValue = "") String lotno,
			@RequestParam(value = "startLine", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "endLine", required = false, defaultValue = "20") int endLine,
			@RequestParam(value = "orderBy", required = false, defaultValue = "startTime") String orderBy,
			@RequestParam(value = "orderDir", required = false, defaultValue = "") String orderDir,
			@RequestParam(value = "description", required = false, defaultValue = "") String description,ModelAndView view){
		logger.info("caselot/caselotPage");
		view.addObject("mysort", mysort);
		view.addObject("startLine", startLine);
		view.addObject("endLine", endLine);
		view.addObject("orderBy", orderBy);
		view.addObject("orderDir", orderDir);
		try {
			// 合买分页数据
			StringBuilder url = new StringBuilder(propertiesUtil.getLotteryurl()
					+ "/select/selectCaseLots?sortStateFlag="+sortStateFlag);
			if (StringUtils.isNotBlank(search)) {
				url.append("&search=" + URLEncoder.encode(search));
				view.addObject("search", search);
			}
			Map<String, Object> map = new HashMap<String, Object>();
			if (StringUtils.isNotBlank(starttime)) {
				map.put("GED_startTime", starttime);
				view.addObject("starttime", starttime);
			}
			if (StringUtils.isNotBlank(endtime)) {
				map.put("LED_startTime", endtime);
				view.addObject("endtime", endtime);
			}
			if (StringUtils.isNotBlank(totalAmtStart)) {
				try {
					map.put("GEL_totalAmt", Long.parseLong(totalAmtStart) * 100);
					view.addObject("totalAmtStart", totalAmtStart);
				} catch (Exception e) {
					logger.error("caselot/listMg error:", e);
					view.addObject("errormsg", "方案金额输入有误");
				}
			}
			if (StringUtils.isNotBlank(totalAmtEnd)) {
				try {
					map.put("LEL_totalAmt", Long.parseLong(totalAmtEnd) * 100);
					view.addObject("totalAmtEnd", totalAmtEnd);
				} catch (Exception e) {
					logger.error("caselot/listMg error:", e);
					view.addObject("errormsg", "方案金额输入有误");
				}
			}
			if (StringUtils.isNotBlank(lotno)) {
				map.put("EQS_lotno", lotno);
				view.addObject("lotno", lotno);
			}
			if(StringUtils.isNotBlank(batchcode)) {
				map.put("EQS_batchcode", batchcode);
				view.addObject(batchcode);
			}
			if(StringUtils.isNotBlank(displayState)) {
				map.put("EQG_displayState", new BigDecimal(displayState));
				view.addObject(displayState);
			}
			if(StringUtils.isNotBlank(description)) {
				map.put("LIKES_description", description);
				view.addObject(description);
			}
			url.append("&startLine=" + startLine);
			url.append("&endLine=" + endLine);
			if (StringUtils.isNotBlank(orderBy)) {
				url.append("&orderBy=" + URLEncoder.encode(orderBy));
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
			view.addObject("errormsg", "合买管理分页查询异常");
		}
		view.setViewName("caselot/caselotListMg");
		return view;
	}
	
	/**
	 * 合买撤单
	 * 
	 * @param userno
	 * @param caselotid
	 * @return
	 */
	@RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData cacelOrder(@RequestParam("userno") String userno,
			@RequestParam("caselotid") String caselotid,
			HttpServletRequest request) throws JSONException {
		Tloguser user = (Tloguser) request.getSession().getAttribute("user");
		String username = user.getNickname();
		logger.info("caselot/cancelOrder");
		String lotteryUrl = propertiesUtil.getLotteryurl();
		String param = "userno=" + userno + "&caseId=" + caselotid ;
		String cancelOrderUrl = lotteryUrl+ "/caselot/cancelCaselot";
		logger.info("cancelOrderUrl:" + cancelOrderUrl + ", param:" + param);
		String result = null;
		String errorCode = null;
		try {
			result = HttpUtil.post(cancelOrderUrl, param);
			JSONObject json = new JSONObject(result);
			errorCode = (String) json.get("errorCode");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseData rd = new ResponseData();
		if(errorCode.equals("0")||errorCode == "0"){
			logger.info("操作者:" + username + ", 操作:撤销合买" + " ,合买编号：" + caselotid);
			ErrorCode ec = ErrorCode.OK;
			rd.setValue(ec.memo);
			rd.setErrorCode(ec.value);
		}
		return rd;
	}
	
	/**
	 * 强制撤单
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/forcedToCancelCaselot", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData forcedToCancelCaselotByID(@RequestParam("caseId") String caseId,
			HttpServletRequest request) throws JSONException {
		logger.info("caselot/forcedToCancelCaselot");
		Tloguser user = (Tloguser) request.getSession().getAttribute("user");
		String username = user.getNickname();
		String lotteryUrl = propertiesUtil.getLotteryurl();
		String url = lotteryUrl + "/caselot/cancelCaselotNoValidate";
		String param = "caseId=" + caseId;
		String result = null;
		String errorCode = null;
		try {
			result = HttpUtil.post(url, param);
			JSONObject json = new JSONObject(result);
			errorCode = (String) json.get("errorCode");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseData rd = new ResponseData();
		if(errorCode.equals("0")||errorCode == "0"){
			logger.info("操作者:" + username + ", 操作:强制撤单" + " ,合买编号：" + caseId);
			ErrorCode ec = ErrorCode.OK;
			rd.setValue(ec.memo);
			rd.setErrorCode(ec.value);
		}
		return rd;
	}
	
	@RequestMapping("/selectCaseLotBuysSimplify")
	public ModelAndView selectCaseLotBuysSimplify(@RequestParam(value = "caselotid") String caselotid,
			@RequestParam(value = "startLine", required = false, defaultValue = "0") int startLine,
			@RequestParam(value = "userno", required = false) String userno,
			@RequestParam(value = "endLine", required = false, defaultValue = "30") int endLine, ModelAndView view) {
		logger.info("caselot/selectCaseLotBuysSimplify");
		view.addObject("torderid", caselotid);
		view.addObject("startLine", startLine);
		view.addObject("endLine", endLine);
		StringBuffer url = new StringBuffer(propertiesUtil.getLotteryurl() + "/select/selectCaseLotBuysSimplify?");
		url.append("caselotid=" + caselotid);
		url.append("&startLine=" + startLine);
		url.append("&endLine=" + endLine);
		
		if (StringUtils.isNotEmpty(userno)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("EQS_userno", userno);
			String condition = new JSONSerializer().serialize(map);
			url.append("&condition=" + condition);
		}
		
		
		try {
			String resultMessage = HttpUtil.getResultMessage(url.toString());
			resultMessage = JsonUtil.escapeJavaScript(resultMessage);
			view.addObject("result", resultMessage);
		} catch (Exception e) {
			logger.error("caselot/caselotdetail error:", e);
			view.addObject("errormsg", "查询异常");
		}
		view.setViewName("caselot/caselotdetail");
		return view;
	}
}