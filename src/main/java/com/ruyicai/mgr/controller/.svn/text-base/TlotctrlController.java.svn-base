package com.ruyicai.mgr.controller;

import java.net.URLEncoder;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.consts.TlotCtrlState;
import com.ruyicai.mgr.domain.CompositePK;
import com.ruyicai.mgr.domain.Tlotctrl;
import com.ruyicai.mgr.domain.Twininfo;
import com.ruyicai.mgr.lot.Lottype;
import com.ruyicai.mgr.util.DateUtil;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/tlotctrls")
@Controller
public class TlotctrlController {

	private Logger logger = Logger.getLogger(TlotctrlController.class);
	
	@Autowired
	private PropertiesUtil propertiesUtil;

	@RequestMapping("/list")
	public ModelAndView list(ModelAndView view) {
		logger.info("tlotctrls/list");
		try {
			Map<String, String> lottypes = Lottype.getMap();
			Map<String, List<Tlotctrl>> tlotctrls = new LinkedHashMap<String, List<Tlotctrl>>();
			Set<String> lotnos = lottypes.keySet();
			for (String lotno : lotnos) {
				if (lotno.startsWith("J")) {
					continue;
				}
				tlotctrls.put(lotno, Tlotctrl.get10Tlotctrls(lotno));
			}
			view.addObject("tlotctrls", tlotctrls);
		} catch (Exception e) {
			view.addObject("errormsg", "查询出错");
			logger.error("tlotctrls/list error", e);
		}
		view.setViewName("tlotctrls/list");
		return view;
	}

	@RequestMapping("/updateHemaiEndtime")
	public ModelAndView updateHemaiEndtime(@RequestParam("info") String info,
			@RequestParam("hemaiEndtime") String hemaiEndtime,
			ModelAndView view) {
		logger.info("tlotctrls/updateHemaiEndtime");
		try {
			StringBuilder param = new StringBuilder();
			param.append("info=").append(info).append("&hemaiEndtime=").append(hemaiEndtime);
			String url = propertiesUtil.getLotteryurl() + "/system/updateHemaiEndtime";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			view.addObject("errormsg", "修改合买时间出错");
			logger.error("tlotctrls/list error", e);
		}
		return this.list(view);
	}
	
	@RequestMapping("/openinfo")
	public ModelAndView openinfo(@RequestParam("info") String info,
			ModelAndView view) {
		logger.info("tlotctrls/openinfo");
		try {
			String[] values = info.split("_");
			CompositePK id = new CompositePK(values[0], values[1], "R00001");
			Twininfo twininfo = Twininfo.findTwininfo(id);
			if (null == twininfo) {
				twininfo = new Twininfo();
				twininfo.setId(id);
			}
			view.addObject("twininfo", twininfo);
		} catch (Exception e) {
			view.addObject("errormsg", "查询开奖信息出错");
			logger.error("tlotctrls/openinfo error", e);
		}
		view.setViewName("tlotctrls/openinfo");
		return view;
	}
	
	@RequestMapping("/doencash")
	public void doencash(@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode,
			ModelAndView view) {
		logger.info("tlotctrls/openinfo");
		try {
			Tlotctrl tlotctrl = Tlotctrl.findTlotctrl(new CompositePK(lotno, batchcode, "R00001"));
			if(TlotCtrlState.not_started.value().equals(tlotctrl.getState()) || TlotCtrlState.open.value().equals(tlotctrl.getState())) {
				view.addObject("errormsg", "期状态是0或1不允许修改");
				return;
			}
			HttpUtil.post(propertiesUtil.getLotteryurl() + "/system/encash", "lotno=" + lotno + "&batchcode=" + batchcode);
		} catch (Exception e) {
			view.addObject("errormsg", "查询开奖信息出错");
			logger.error("tlotctrls/openinfo error", e);
		}
	}

	@RequestMapping("/opensave")
	public ModelAndView opensave(
			@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode,
			@RequestParam("winbasecode") String winbasecode,
			@RequestParam("winspecialcode") String winspecialcode,
			@RequestParam("agencyno") String agencyno,
			ModelAndView view) {
		logger.info("tlotctrls/opensave");
		String errormsg = "保存成功";
		try {
			StringBuilder param = new StringBuilder();
			param.append("lotno=").append(lotno).append("&batchcode=").append(batchcode)
				.append("&winbasecode=").append(URLEncoder.encode(winbasecode.trim(), "UTF-8"))
				.append("&winspecialcode=").append(winspecialcode.trim())
				.append("&agencyno=").append(agencyno);
			String url = propertiesUtil.getLotteryurl() + "/system/rycdraw";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg = "保存失败，返回码："+json.getString("errorCode");
			} 
			view.addObject("twininfo", Twininfo.findTwininfo(new CompositePK(lotno, batchcode, "R00001")));
		} catch (Exception e) {
			errormsg = "保存失败"+e.getMessage();
			logger.error("tlotctrls/opensave error", e);
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("tlotctrls/openinfo");
		return view;
	}
	@RequestMapping("/lotctrllist")
	public ModelAndView lotctrllist(@RequestParam("lotno") String lotno,
			ModelAndView view) {
		logger.info("tlotctrls/list");
		try {
			List<Tlotctrl> tlotctrls = Tlotctrl.findTlotctrls(lotno);
			view.addObject("tlotctrls", tlotctrls);
		} catch (Exception e) {
			view.addObject("errormsg", "查询出错");
			logger.error("tlotctrls/lotctrllist error", e);
		}
		view.setViewName("tlotctrls/ctrllist");
		return view;
	}
	
	@RequestMapping("/editUI")
	public ModelAndView editUI(@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode,
			@RequestParam("agencyno") String agencyno,
			ModelAndView view) {
		logger.info("tlotctrls/editUI");
		logger.info("lotno:"+lotno+",batchcode:"+batchcode+",agencyno:"+agencyno);
		CompositePK c = new CompositePK(lotno, batchcode, agencyno);
		Tlotctrl tlotctrl = Tlotctrl.findTlotctrl(c);
		view.addObject("tlotctrl", tlotctrl);
		view.setViewName("tlotctrls/editUI");
		return view;
	}
	
	@RequestMapping("/edit")
	public ModelAndView edit(
			@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode,
			@RequestParam("starttime") String starttime,
			@RequestParam("endtime") String endtime,
			@RequestParam("endbettime") String endbettime,
			@RequestParam("hemaiendtime") String hemaiendtime,
			@RequestParam("state") String state,
			@RequestParam("encashstate") String encashstate,
			ModelAndView view) {
		logger.info("tlotctrls/edit");
		if (StringUtil.isEmpty(starttime) || StringUtil.isEmpty(endtime) || StringUtil.isEmpty(endbettime) || StringUtil.isEmpty(hemaiendtime)) {
			view.addObject("errormsg", "不允许为空");
			view.setViewName("tlotctrls/addUI");
			return view;
		}
		Date start = DateUtil.parse(starttime);
		Date end = DateUtil.parse(endtime);
		if (start.after(end)) {
			view.addObject("errormsg", "开始时间必须小于结束时间");
			view.setViewName("tlotctrls/addADUI");
			return view;
		}
			
		String errormsg = "修改成功";
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("lotno=").append(lotno).append("&batchcode=").append(batchcode).append("&starttime=").append(starttime)
			.append("&endtime=").append(endtime).append("&state=").append(state).append("&endbettime=").append(endbettime)
			.append("&hemaiendtime=").append(hemaiendtime).append("&encashstate=").append(encashstate);
			String result = HttpUtil.post(propertiesUtil.getLotteryurl() + "/system/modifytlotctrl", sb.toString());
			JSONObject json = new JSONObject(result);
			if (!"0".equals(json.getString("errorCode"))) {
				errormsg = "修改失败，返回码："+json.getString("errorCode");
			}
		} catch (Exception e) {
			logger.error("tlotctrls/edit error", e);
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.editUI(lotno, batchcode, "R00001", view);
	}
	
	@RequestMapping("/checkencashend")
	public ModelAndView checkencashend(@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode,
			ModelAndView view) {
		logger.info("tlotctrls/checkencashend");
		String errormsg = "";
		try {
			StringBuilder param = new StringBuilder();
			param.append("lotno=").append(lotno).append("&batchcode=").append(batchcode);
			String url = propertiesUtil.getLotteryurl() + "/system/checkencashend";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);
			errormsg = json.getString("value");
		} catch (Exception e) {
			errormsg = e.getMessage();
			logger.error("tlotctrls/checkencashend", e);
		}
		view.addObject("errormsg", "返回值："+errormsg);
		view.setViewName("tlotctrls/ctrllist");
		return this.lotctrllist(lotno, view);
	}
	
	
	@RequestMapping("/checkprizeend")
	public ModelAndView checkprizeend(@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode,
			ModelAndView view) {
		logger.info("tlotctrls/checkencashend");
		String errormsg = "";
		try {
			StringBuilder param = new StringBuilder();
			param.append("lotno=").append(lotno).append("&batchcode=").append(batchcode);
			String url = propertiesUtil.getLotteryurl() + "/system/checkprizeend";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);
			errormsg = json.getString("value");
		} catch (Exception e) {
			errormsg =e.getMessage();
			logger.error("tlotctrls/ctrllist", e);
		}
		view.addObject("errormsg", "返回值："+errormsg);
		view.setViewName("tlotctrls/ctrllist");
		return this.lotctrllist(lotno, view);
	}
	@RequestMapping(value = "/getWincode", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData getWincode2(@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode) {
		ResponseData rd = new ResponseData();
		try {
			Twininfo t = Twininfo.findTwininfo(new CompositePK(lotno, batchcode, "R00001"));
			if (t == null) {
				rd.setErrorCode(ErrorCode.Twininfo_Notexist.value);
				return rd;
			}
			String wincode = null;
			if("T01001".equals(lotno) || "F47104".equals(lotno)){
				wincode = this.getWincode(t.getWinbasecode(), t.getWinspecialcode());
			}else{
				wincode = t.getWinbasecode();
			}
			rd.setValue(wincode);
			rd.setErrorCode(ErrorCode.OK.value);
		} catch (Exception e) {
			logger.error("tlotctrls/getWincode", e);
			rd.setErrorCode(ErrorCode.ERROR.value);
		}
		return rd;
	}
	
	@RequestMapping(value = "/prize", method = RequestMethod.POST)
	public @ResponseBody
	ResponseData prize(@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode,
			@RequestParam("wincode") String wincode) {
		logger.info("tlotctrls/ctrllist");
		ResponseData rd = new ResponseData();
		try {
			StringBuffer param = new StringBuffer();
			param.append("batchcode=").append(batchcode).append("&lotno=").append(lotno).append("&wincode=").append(wincode);
			String resultMessage = HttpUtil.post(propertiesUtil.getLotteryurl()+"/system/prize", param.toString());
			rd = ResponseData.fromJsonToResponseData(resultMessage);
		} catch (Exception e) {
			logger.error("tlotctrls/prize", e);
			rd.setErrorCode(ErrorCode.ERROR.value);
		}
		return rd;
	}
	
	public String getWincode(String winbasecode, String winspecialcode) {
		return StringUtil.join("|", winbasecode, winspecialcode);
	}
}
