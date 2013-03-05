package com.ruyicai.mgr.controller;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.domain.CompositePK;
import com.ruyicai.mgr.domain.Tlotctrl;
import com.ruyicai.mgr.domain.Twininfo;
import com.ruyicai.mgr.lot.Lottype;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/ladyopen")
@Controller
public class LadyopenController {

	private Logger logger = Logger.getLogger(LadyopenController.class);
	
	@Autowired
	private PropertiesUtil propertiesUtil;

	@RequestMapping("/list")
	public ModelAndView list(ModelAndView view) {
		logger.info("ladyopen/list");
		try {
			Map<String, String> lottypes = Lottype.getMap();
			Map<String, List<Tlotctrl>> tlotctrls = new LinkedHashMap<String, List<Tlotctrl>>();
			Set<String> lotnos = lottypes.keySet();
			for (String lotno : lotnos) {
				if (lotno.equals("J00001")|| lotno.equals("J00002")|| lotno.equals("J00003")|| lotno.equals("J00004")) {
					continue;
				}
				tlotctrls.put(lotno, Tlotctrl.get10Tlotctrls(lotno));
			}
			view.addObject("tlotctrls", tlotctrls);
		} catch (Exception e) {
			view.addObject("errormsg", "查询出错");
			logger.error("ladyopen/list error", e);
		}
		view.setViewName("ladyopen/list");
		return view;
	}

	@RequestMapping("/openinfo")
	public ModelAndView openinfo(@RequestParam("info") String info,
			ModelAndView view) {
		logger.info("ladyopen/openinfo");
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
			logger.error("ladyopen/openinfo error", e);
		}
		view.setViewName("ladyopen/openinfo");
		return view;
	}
	
	@RequestMapping("/info")
	public ModelAndView info(@RequestParam("info") String info,
			ModelAndView view) {
		logger.info("ladyopen/info");
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
			logger.error("ladyopen/info error", e);
		}
		view.setViewName("ladyopen/info");
		return view;
	}
	@RequestMapping("/opensave")
	public ModelAndView opensave(
			@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode,
			@RequestParam("winbasecode") String winbasecode,
			@RequestParam("winspecialcode") String winspecialcode,
			ModelAndView view) {
		logger.info("ladyopen/opensave");
		String errormsg = "保存成功";
		try {
			if(StringUtil.isEmpty(winbasecode)){
				view.addObject("errormsg", "开奖号码不允许为空");
				view.setViewName("ladyopen/openinfo");
				return view;
			}
			if(!checkwincode(lotno, winbasecode, winspecialcode)){
				view.addObject("errormsg", "开奖号码错误");
				view.setViewName("ladyopen/openinfo");
				return view;
			}
			
			StringBuilder param = new StringBuilder();
			param.append("lotno=").append(lotno).append("&batchcode=").append(batchcode)
				.append("&winbasecode=").append(URLEncoder.encode(winbasecode.trim(), "UTF-8"))
				.append("&winspecialcode=").append(winspecialcode.trim())
				.append("&agencyno=").append("H00001");
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
		view.setViewName("ladyopen/openinfo");
		return view;
	}
	public static boolean checkwincode(String lotno, String wincode, String specialcode){
		if ("F47104".equals(lotno)) {//212027083011   09   \\d{12}$  12位数字	
			return (wincode.matches("\\d{12}$") && specialcode.matches("\\d{2}$"));
		}else if("F47103".equals(lotno)){//040306   00
			return (wincode.matches("\\d{6}$"));
		}else if("F47102".equals(lotno)){//07130806301912        26
			return (wincode.matches("\\d{14}$") && specialcode.matches("\\d{2}$"));
		}else if("T01001".equals(lotno)){//01 02 17 18 33+05 12
			return wincode.length() == 20;
		}else if("T01002".equals(lotno)){//469
			return wincode.matches("\\d{3}$");
		}else if("T01011".equals(lotno)){//46905
			return wincode.matches("\\d{5}$");
		}else if("T01007".equals(lotno)){//54993
			return wincode.matches("\\d{5}$");
		}else if("T01009".equals(lotno)){//5120354
			return wincode.matches("\\d{7}$");
		}else if("T01010".equals(lotno)){//02 05 07 04 08
			return wincode.length() == 14;
		}else if("T01003".equals(lotno)){//30033033300101
			return wincode.length() == 14;
		}else if("T01004".equals(lotno)){//30033033300101
			return wincode.length() == 14;
		}else if("T01005".equals(lotno)){//13300323
			return wincode.length() == 8;
		}else if("T01006".equals(lotno)){//13300323
			return wincode.length() == 12;
		}else if("T01012".equals(lotno)){//08 07 05 04 03
			return wincode.length() == 14;
		}else if("T01013".equals(lotno)){//04 05 08 19 21
			return wincode.length() == 14;
		}else if("T01014".equals(lotno)){//04 05 08 19 21
			return wincode.length() == 14;
		}else if("T01015".equals(lotno)){//04 05 08 19 21 01 01 01
			return wincode.length() == 23;
		}else if("F47107".equals(lotno)){//040306   00
			return (wincode.matches("\\d{6}$"));
		}
		return false;
		
	}
	
	
	@RequestMapping("/infosave")
	public ModelAndView opensave(
			@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode,
			@RequestParam("shiji") String shiji,
			@RequestParam("youxiao") String youxiao,
			@RequestParam("jiangchi") String jiangchi,
			@RequestParam("grade") String[] grades,
			@RequestParam("num") String[] nums,
			@RequestParam("money") String[] moneys,
			ModelAndView view) {
		logger.info("ladyopen/infosave");
		String errormsg = "保存成功";
		try {
			if (StringUtil.isEmpty(shiji) || StringUtil.isEmpty(youxiao) || StringUtil.isEmpty(jiangchi)) {
				view.addObject("errormsg", "不允许为空");
				view.setViewName("ladyopen/info");
				return this.info(lotno+"_"+batchcode, view);
			}
			CompositePK id = new CompositePK(lotno, batchcode, "R00001");
			Twininfo twininfo = Twininfo.findTwininfo(id);
			if (twininfo == null) {
				view.addObject("errormsg", "期还没开出来");
				view.setViewName("ladyopen/info");
				return view;
			}
			StringBuilder builder = new StringBuilder();
			builder.append(shiji).append("_").append(youxiao).append("_").append(jiangchi).append(",");
			int index = 0;
			for (String grade : grades) {
				builder.append(grade).append("_").append(nums[index]).append("_").append(moneys[index]).append(";");
				index += 1;
			}
			builder.deleteCharAt(builder.length() - 1);
			
			
			StringBuilder param = new StringBuilder();
			param.append("lotno=").append(lotno).append("&batchcode=").append(batchcode)
				.append("&info=").append(builder);
			String url = propertiesUtil.getLotteryurl() + "/system/saveinfo";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg = "保存失败，返回码："+json.getString("errorCode");
			} 
			
			view.addObject("twininfo", Twininfo.findTwininfo(new CompositePK(lotno, batchcode, "R00001")));
		} catch (Exception e) {
			errormsg = "保存失败";
			logger.error("ladyopen/infosave error", e);
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("ladyopen/info");
		return this.info(lotno+"_"+batchcode, view);
	}
	
	
	
	@RequestMapping("/savetrycode")
	public ModelAndView savetrycode(
			@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode,
			@RequestParam("tryCode") String tryCode,
			ModelAndView view) {
		logger.info("ladyopen/savetrycode");
		String errormsg = "保存成功";
		try {
			if(StringUtil.isEmpty(lotno) || StringUtil.isEmpty(batchcode) || StringUtil.isEmpty(tryCode)){
				view.addObject("errormsg", "不允许为空");
				return this.list(view);
			}
			
			StringBuilder param = new StringBuilder();
			param.append("lotno=").append(lotno).append("&batchcode=").append(batchcode)
				.append("&trycode=").append(tryCode);
			String url = propertiesUtil.getLotteryurl() + "/system/savetrycode";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg = "失败，返回码："+json.getString("errorCode");
			} 
		} catch (Exception e) {
			errormsg = "失败"+e.getMessage();
			logger.error("ladyopen/opensave error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.list(view);
	}
	
	@RequestMapping("/chearinfo")
	public ModelAndView chearinfo(
			@RequestParam("lotno1") String lotno,
			@RequestParam("batchcode1") String batchcode,
			@RequestParam("agencyno1") String agencyno,
			ModelAndView view) {
		logger.info("ladyopen/chearinfo");
		String errormsg = "成功";
		try {
			if(StringUtil.isEmpty(lotno) || StringUtil.isEmpty(batchcode) || StringUtil.isEmpty(agencyno)){
				view.addObject("errormsg", "不允许为空");
				return this.list(view);
			}
			
			StringBuilder param = new StringBuilder();
			param.append("lotno=").append(lotno).append("&batchcode=").append(batchcode)
				.append("&agencyno=").append(agencyno);
			String url = propertiesUtil.getLotteryurl() + "/system/clearinfo";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.post(url, param.toString());
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg = "失败，返回码："+json.getString("errorCode");
			} else{
				String ret = HttpUtil.getResultMessage("http://192.168.99.122:80/system/chearinfo");
				logger.info(ret);
			}
			
		} catch (Exception e) {
			errormsg = "失败"+e.getMessage();
			logger.error("ladyopen/opensave error", e);
		}
		view.addObject("errormsg", errormsg);
		return this.list(view);
	}
}
