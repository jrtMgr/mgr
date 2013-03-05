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

@RequestMapping("/gentlemanopen")
@Controller
public class GentlemanopenController {
	private Logger logger = Logger.getLogger(GentlemanopenController.class);
	
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
				if (lotno.equals("J00001")|| lotno.equals("J00002")|| lotno.equals("J00003")|| lotno.equals("J00004")) {
					continue;
				}
				tlotctrls.put(lotno, Tlotctrl.get10Tlotctrls(lotno));
			}
			view.addObject("tlotctrls", tlotctrls);
		} catch (Exception e) {
			view.addObject("errormsg", "查询出错");
			logger.error("tlotctrls/list error", e);
		}
		view.setViewName("gentlemanopen/list");
		return view;
	}
	
	@RequestMapping("/openinfo")
	public ModelAndView openinfo(@RequestParam("info") String info,
			ModelAndView view) {
		logger.info("gentlemanopen/openinfo");
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
		view.setViewName("gentlemanopen/openinfo");
		return view;
	}
	
	@RequestMapping("/opensave")
	public ModelAndView opensave(
			@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode,
			@RequestParam("winbasecode") String winbasecode,
			@RequestParam("winspecialcode") String winspecialcode,
			ModelAndView view) {
		logger.info("gentlemanopen/opensave");
		String errormsg = "保存成功";
		try {
			if(StringUtil.isEmpty(winbasecode)){
				view.addObject("errormsg", "开奖号码不允许为空");
				view.setViewName("gentlemanopen/openinfo");
				return view;
			}
			if(!LadyopenController.checkwincode(lotno, winbasecode, winspecialcode)){
				view.addObject("errormsg", "开奖号码错误");
				view.setViewName("gentlemanopen/openinfo");
				return view;
			}
			
			StringBuilder param = new StringBuilder();
			param.append("lotno=").append(lotno).append("&batchcode=").append(batchcode)
				.append("&winbasecode=").append(URLEncoder.encode(winbasecode.trim(), "UTF-8"))
				.append("&winspecialcode=").append(winspecialcode.trim())
				.append("&agencyno=").append("H00002");
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
			logger.error("gentlemanopen/opensave error", e);
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("gentlemanopen/openinfo");
		return view;
	}
	
	@RequestMapping("/info")
	public ModelAndView info(@RequestParam("info") String info,
			ModelAndView view) {
		logger.info("gentlemanopen/info");
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
			logger.error("gentlemanopen/info error", e);
		}
		view.setViewName("gentlemanopen/info");
		return view;
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
				view.setViewName("gentlemanopen/info");
				return this.info(lotno+"_"+batchcode, view);
			}
			CompositePK id = new CompositePK(lotno, batchcode, "R00001");
			Twininfo twininfo = Twininfo.findTwininfo(id);
			if (twininfo == null) {
				view.addObject("errormsg", "期还没开出来");
				view.setViewName("gentlemanopen/info");
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
			logger.error("gentlemanopen/infosave error", e);
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("gentlemanopen/info");
		return this.info(lotno+"_"+batchcode, view);
	}
}
