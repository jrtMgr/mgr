package com.ruyicai.mgr.controller;

import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.controller.dto.AccountDto;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.RandomUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/lotCenter")
@Controller
public class LotCenterController {

	private Logger logger = Logger.getLogger(LotCenterController.class);
	@Autowired
	PropertiesUtil propertiesUtil;
	@RequestMapping("/page")
	public String page() {
		return "lotCenter/info";
	}
	
	/**
	 * 查看内蒙账户余额
	 * @return
	 */
	@RequestMapping("nmaccount")
	public ModelAndView nmaccount(ModelAndView view) {
		logger.info("NMFuCai/nmaccount");
		try {
			String url = propertiesUtil.getNmfronturl() + "/account";
			logger.info("nmfronturl:" + url);
			AccountDto accountDto = new AccountDto();
			accountDto.setName("lotteryquerymoney");
			accountDto.setRandom_num(RandomUtil.genRandomNum(6));
			accountDto.setLogic_code("15120001");
			String balance = getBalance(url, accountDto);
			logger.info("b_15120001:"+balance);
			view.addObject("b_15120001", (StringUtil.isEmpty(balance) ? "获取失败" : balance));
			
			accountDto = new AccountDto();
			accountDto.setName("lotteryquerymoney");
			accountDto.setRandom_num(RandomUtil.genRandomNum(6));
			accountDto.setLogic_code("15120011");
			balance = getBalance(url, accountDto);
			logger.info("b_15120011:"+balance);
			view.addObject("b_15120011", (StringUtil.isEmpty(balance) ? "获取失败" : balance));
			
			accountDto = new AccountDto();
			accountDto.setName("lotteryquerymoney");
			accountDto.setRandom_num(RandomUtil.genRandomNum(6));
			accountDto.setLogic_code("15120021");
			balance = getBalance(url, accountDto);
			logger.info("b_15120021:"+balance);
			view.addObject("b_15120021", (StringUtil.isEmpty(balance) ? "获取失败" : balance));
			
			//大赢家账户余额
			url = propertiesUtil.getLotteryurl() + "/dyj/getBalance";
			balance = getBalance(url, "");
			view.addObject("dyj", (StringUtil.isEmpty(balance) ? "获取失败" : balance));
			logger.info("dyj:"+ balance);
			
			/*//山东体彩账户余额
			url = propertiesUtil.getLotteryurl() + "/caitongtc/getBalance";
			balance = getBalance(url, "");
			view.addObject("caitongtc", (StringUtil.isEmpty(balance) ? "获取失败" : balance));
			logger.info("caitongtc:"+ balance);*/
			
			//山东竞彩账户余额
			url = propertiesUtil.getLotteryurl() + "/jingcai/getBalance";
			balance = getBalance(url, "");
			view.addObject("jingcai", (StringUtil.isEmpty(balance) ? "获取失败" : balance));
			logger.info("jingcai:"+ balance);
			
			/*//华彩账户余额
			url = propertiesUtil.getLotteryurl() + "/huacai/getBalance";
			balance = getBalance(url, "");
			view.addObject("huacai", (StringUtil.isEmpty(balance) ? "获取失败" : balance));
			*/
			//掌中彩账户余额
			url = propertiesUtil.getLotteryurl() + "/zhangzhongcai/getBalance";
			balance = getBalance(url, "");
			view.addObject("chongqingfucai", balance);
			logger.info("chongqingfucai:"+ balance);
			
			/*//掌中弈体彩账户余额
			url = propertiesUtil.getLotteryurl() + "/zhangzhongyitc/getBalance";
			balance = getBalance(url, "");
			view.addObject("zhangzhongyitc", balance);
			logger.info("zhangzhongyitc:"+ balance);*/
			
		/*	//丰采博雅余额
			url = propertiesUtil.getLotteryurl() + "/fcby/getBalance";
			balance = getBalance(url, "");
			view.addObject("fcby", balance);
			logger.info("fcby:"+ balance);*/
			
			/*//直立人余额
			url = propertiesUtil.getLotteryurl() + "/zlren/getBalance";
			balance = getBalance(url, "");
			view.addObject("zlren", balance);
			logger.info("zlren:"+ balance);
			*/
			//山东丰彩博雅余额
			url = propertiesUtil.getLotteryurl() + "/sdfcby/getBalance";
			balance = getBalance(url, "");
			view.addObject("sdfcby", balance);
			logger.info("sdfcby:"+ balance);
			
				/*//亿鸣中天余额
				url = propertiesUtil.getLotteryurl() + "/yimingzhongtian/getBalance";
				balance = getBalance(url, "");
				view.addObject("yimingzhongtian", balance);
				logger.info("yimingzhongtian:"+ balance);*/
			
			//广东十一选五余额
			url = propertiesUtil.getLotteryurl() + "/fcby11c5/getBalance";
			balance = getBalance(url, "");
			view.addObject("fcby11c5", balance);
			logger.info("fcby11c5:"+ balance);
		} catch (Exception e) {
			logger.error("NMFuCai/nmaccount error", e);
			view.addObject("errormsg", "出现异常");
		}
		view.setViewName("lotCenter/nmaccount");
		return view;
	}
	
	@RequestMapping("caitongtctransfer")
	public ModelAndView caitongtctransfer(@RequestParam("amt") String amt, ModelAndView view) {
		logger.info("NMFuCai/caitongtctransfer");
		try {
			String url = propertiesUtil.getLotteryurl() + "/caitongtc/transferBalance";
			getBalance(url, "amt=" + amt);
		} catch (Exception e) {
			logger.error("NMFuCai/nmaccount error", e);
			view.addObject("errormsg", "出现异常");
		}
		return nmaccount(view);
	}
	@RequestMapping("transfer")
	public ModelAndView transfer(@RequestParam("url") String url, ModelAndView view) {
		logger.info("NMFuCai/transfer");
		try {
			String result = HttpUtil.post(propertiesUtil.getLotteryurl() +"/"+url+"/transferToBalance", "");
			logger.info(result);
		} catch (Exception e) {
			logger.error("NMFuCai/jingcaitransfer error", e);
			view.addObject("errormsg", "出现异常");
		}
		return nmaccount(view);
	}
	
	@RequestMapping("jingcaitransfer")
	public ModelAndView jingcaitransfer(@RequestParam("amt") String amt, ModelAndView view) {
		logger.info("NMFuCai/jingcaitransfer");
		try {
			String url = propertiesUtil.getLotteryurl() + "/jingcai/transferBalance";
			getBalance(url, "amt=" + amt);
		} catch (Exception e) {
			logger.error("NMFuCai/jingcaitransfer error", e);
			view.addObject("errormsg", "出现异常");
		}
		return nmaccount(view);
	}
	
	private String getBalance(String url, String params) {
		String response = null;
		try {
			response = HttpUtil.post(url, params);
			if (StringUtil.isEmpty(response)) {
				response = "";
			}
			JSONObject json = new JSONObject(response);
			return json.getString("value");
		} catch(Exception e) {
		}
		return null;
	}

	private String getBalance(String url, AccountDto accountDto) {
		String response = null;
		try {
			response = HttpUtil.post(url, "body=" + URLEncoder.encode(accountDto.toJson(), "UTF-8"));
			if (StringUtil.isEmpty(response)) {
				response = "";
			}
			String[] responses = response.split("\t");
			if (responses.length <= 0) {
				responses = new String[] {"error"};
			}
			if (responses[0].equals(accountDto.getRandom_num())) {
				response = responses[1];
			}
		} catch(Exception e) {
		}
		return response;
	}
	
	/**
	 * 请求内蒙福彩重新兑奖
	 */
	@RequestMapping("/rePrizeNM")
	public ModelAndView rePrizeNM(
			@RequestParam("filename") String filename,
			ModelAndView view) {
		logger.info("NMFuCai/rePrizeNM");
		String errormsg = "重新兑奖成功";
		try {
			String url = propertiesUtil.getLotteryurl() + "/system/rePrizeNM";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.post(url, "filename="+filename);
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg = "兑奖失败，错误码"+json.getString("errorCode");
			} 
		} catch (Exception e) {
			errormsg = "出现异常";
			logger.error("NMFuCai/rePrizeNM error", e);
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("lotCenter/info");
		return view;
	}
	
	/**
	 * 请求内蒙福彩兑奖
	 */
	@RequestMapping("/nmencash")
	public ModelAndView encash(
			@RequestParam("time") String time,
			ModelAndView view) {
		logger.info("NMFuCai/nmencash");
		String errormsg = "兑奖成功";
		try {
			String url = propertiesUtil.getLotteryurl() + "/system/nmencash";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.post(url, "time="+time);
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg = "兑奖失败，错误码"+json.getString("errorCode");
			} 
		} catch (Exception e) {
			errormsg = "兑奖异常";
			logger.error("NMFuCai/encash error", e);
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("lotCenter/info");
		return view;
	}
	
	/**
	 * 请求内蒙福彩兑奖 单张
	 */
	@RequestMapping("/singleencash")
	public ModelAndView singleencash(@RequestParam("flowno") String flowno,	ModelAndView view) {
		logger.info("NMFuCai/singleencash");
		String errormsg = "兑奖成功";
		try {
			String url = propertiesUtil.getLotteryurl() + "/system/singleencash";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.post(url, "flowno="+flowno);
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg = "兑奖失败，错误码"+json.getString("errorCode");
			} 
		} catch (Exception e) {
			errormsg = "兑奖异常";
			logger.error("NMFuCai/singleencash error", e);
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("lotCenter/info");
		return view;
	}
	
	/**
	 * 手工请求如意彩
	 */
	@RequestMapping("/encash")
	public ModelAndView encash(@RequestParam(value = "lotno") String lotno,
		@RequestParam(value = "batchcode") String batchcode, ModelAndView view) {
		logger.info("NMFuCai/singleencash");
		String errormsg = "手工请求如意彩兑奖成功";
		try {
			String url = propertiesUtil.getLotteryurl() + "/system/encash";
			logger.info("lotteryUrl:" + url);
			StringBuffer para = new StringBuffer();
			para.append("lotno=").append(lotno).append("&batchcode=").append(batchcode);
			String result = HttpUtil.post(url, para.toString());
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg = "兑奖失败，错误码"+json.getString("errorCode");
			} 
		} catch (Exception e) {
			errormsg = "出现异常";
			logger.error("NMFuCai/encash error", e);
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("lotCenter/info");
		return view;
	}
	
	/**
	 * 大赢家新期
	 */
	@RequestMapping("/tlotctrl")
	public ModelAndView tlotctrl(
			@RequestParam("lotno") String lotno,
			@RequestParam("lotid") String lotid,
			ModelAndView view) {
		logger.info("DYJ/tlotctrl");
		String errormsg = "新期成功";
		try {
			String url = propertiesUtil.getLotteryurl() + "/bet/tlotctrl";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.post(url, "lotno="+lotno+"&lotid="+lotid);
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg = "新期失败，错误码"+json.getString("errorCode");
			} 
		} catch (Exception e) {
			errormsg = "出现异常";
			logger.error("DYJ/tlotctrl error", e);
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("lotCenter/info");
		return view;
	}
	
	/**
	 * 大赢家开奖
	 */
	@RequestMapping("/twininfo")
	public ModelAndView twininfo(
			@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode,
			ModelAndView view) {
		logger.info("DYJ/twininfo");
		String errormsg = "开奖成功";
		try {
			String url = propertiesUtil.getLotteryurl() + "/bet/twininfo";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.post(url, "lotno="+lotno+"&batchcode="+batchcode);
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg = "开奖失败，错误码"+json.getString("errorCode");
			} 
		} catch (Exception e) {
			errormsg = "出现异常";
			logger.error("DYJ/twininfo error", e);
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("lotCenter/info");
		return view;
	}
	
	/**
	 * 大赢家兑奖
	 */
	@RequestMapping("/prizeorder")
	public ModelAndView prizeorder(
			@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode,	
			ModelAndView view) {
		logger.info("DYJ/singleencash");
		String errormsg = "兑奖成功";
		try {
			String url = propertiesUtil.getLotteryurl() + "/bet/prizeorder";
			logger.info("lotteryUrl:" + url);
			String result = HttpUtil.post(url, "lotno="+lotno+"&batchcode="+batchcode);
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg = "兑奖失败，错误码"+json.getString("errorCode");
			} 
		} catch (Exception e) {
			errormsg = "兑奖异常";
			logger.error("DYJ/prizeorder error", e);
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("lotCenter/info");
		return view;
	}
	
	/**
	 * 手动开新期
	 */
	@RequestMapping("/newissue")
	public ModelAndView newissue(
			@RequestParam("lotno") String lotno,
			@RequestParam("batchcode") String batchcode,
			@RequestParam("agencyno") String agencyno,
			@RequestParam("starttime") String starttime,
			@RequestParam("endtime") String endtime,	
			ModelAndView view) {
		logger.info("lotCenter/newissue");
		String errormsg = "手动开新期成功";
		try {
			String url = propertiesUtil.getLotteryurl() + "/system/newissue";
			logger.info("lotteryUrl:" + url);
			StringBuffer para = new StringBuffer();
			para.append("lotno=").append(lotno).append("&batchcode=").append(batchcode).append("&agencyno=").append(agencyno)
			.append("&starttime=").append(starttime).append("&endtime=").append(endtime);
			String result = HttpUtil.post(url, para.toString());
			JSONObject json = new JSONObject(result);
			if(!"0".equals(json.getString("errorCode"))) {
				errormsg = "手动开新期失败，错误码"+json.getString("errorCode");
			} 
		} catch (Exception e) {
			errormsg = "手动开新期异常";
			logger.error("lotCenter/newissue error", e);
		}
		view.addObject("errormsg", errormsg);
		view.setViewName("lotCenter/info");
		return view;
	}
}
