package com.ruyicai.mgr.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.ruyicai.mgr.controller.giveOrder.BetRequest;
import com.ruyicai.mgr.controller.giveOrder.OrderRequest;
import com.ruyicai.mgr.controller.giveOrder.util.F47102;
import com.ruyicai.mgr.controller.giveOrder.util.F47103_BH;
import com.ruyicai.mgr.controller.giveOrder.util.F47103_SJ;
import com.ruyicai.mgr.controller.giveOrder.util.F47103_Z3;
import com.ruyicai.mgr.controller.giveOrder.util.F47103_Z6;
import com.ruyicai.mgr.controller.giveOrder.util.F47104;
import com.ruyicai.mgr.controller.giveOrder.util.GeneratedCode;
import com.ruyicai.mgr.domain.Tgift;
import com.ruyicai.mgr.domain.Tgiftaudit;
import com.ruyicai.mgr.domain.Tlotctrl;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.util.HttpUtil;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.PropertiesUtil;
import com.ruyicai.mgr.util.StringUtil;

@RequestMapping("/batchgift")
@Controller
public class BatchGiftController {
	private Logger logger = Logger.getLogger(BatchGiftController.class);
	@RequestMapping("/page")
	public ModelAndView page(@ModelAttribute("page") Page<Tgiftaudit> page, 
			@RequestParam(value = "flag", required = false, defaultValue = "0") Integer flag,
			ModelAndView view){
		logger.info("batchgift/page");
		Tgiftaudit.findAllTgiftauditsByFlag(page,flag);
		view.addObject("page", page);
		view.setViewName("batchgift/taskList");
		return view;
	}
	@RequestMapping("/addTaskFile")
	public ModelAndView addTaskFile(ModelAndView view){
		logger.info("batchgift/addTaskFile");
		view.setViewName("batchgift/addTaskFile");
		return view;
	}
	
	@RequestMapping("/addgift")
	public ModelAndView addgift(@RequestParam("notescontent") String notescontent,
		@RequestParam("jrtnotescontent") String jrtnotescontent,
		@RequestParam("biguserno") String biguserno,// 大客户编号
		@RequestParam("lotno") String lotno, // 彩票种类
		@RequestParam("betnum") Long betnum,// 注数
		HttpServletRequest request,	ModelAndView view){
		logger.info("batchgift/getmsg");
		view.setViewName("batchgift/addTaskFile");
		if (StringUtil.isEmpty(notescontent) || StringUtil.isEmpty(jrtnotescontent)) {
			view.addObject("errormsg", "不允许为空");
			return view;
		}
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
	        MultipartFile multipartFile = multipartRequest.getFile("filename");
			List<String> num = new ArrayList<String>();
			BufferedReader reader = new BufferedReader(new InputStreamReader(multipartFile.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.trim().length() == 11) {
					num.add(line.trim());
				}else{
					view.addObject("errormsg", "添加失败，手机号:"+line+"有误,请修改后再提交");
					return view;
				}
			}
			reader.close();
			if (num.size() == 0) {
				view.addObject("errormsg", "数据有误");
				return view;
			}
			Tuserinfo tuserinfo = Tuserinfo.findTuserinfo(biguserno);
			if(tuserinfo==null){
				view.addObject("errormsg", "大客户编号错误");
				return view;
			}
			Tgiftaudit tgiftaudit = new Tgiftaudit();
			tgiftaudit.setBigname(tuserinfo.getName());
			tgiftaudit.setBetnum(betnum);
			tgiftaudit.setLotno(lotno);
			tgiftaudit.setBiguserno(biguserno);//大客户编号
			
			tgiftaudit.setFlowno("0000");//审核类型：0000为运营系统批量赠送，1111为客户端HTTP请求小批量赠送，其它
			tgiftaudit.setFlag(0);//'审核状态标志，0 ：待审，1：通过，2：未通过'
			tgiftaudit.setSmscontent(notescontent.trim()+"&&"+jrtnotescontent.trim());//客户短信内容+jrt内容
			tgiftaudit.setFailure(0L);
			tgiftaudit.setAgetime(new Date());
			tgiftaudit.setAllamt(200*num.size()*tgiftaudit.getBetnum());//获取需要赠送的总金额
			tgiftaudit.setSuccess(0L);
			tgiftaudit.setFailure(0L);
			tgiftaudit.setAftertime(new Date());
			tgiftaudit.persist();
			
			Tgift tgift;
			for (String telephone : num) {
				tgift = new Tgift();
				tgift.setBiguserno(biguserno);  //大客户编号
				tgift.setSmalluserno(telephone);//小客户手机号码
				tgift.setFlag(BigDecimal.ZERO);
				tgift.setTgiftauditid(tgiftaudit.getId());//审核表的id 对应审核表中的流水号
				tgift.setFlowno(" ");
				tgift.persist();
			}
			view.addObject("errormsg", "添加成功,总笔数："+num.size()+",总金额："+tgiftaudit.getAllamt()/100);
		} catch (Exception e) {
			logger.error("batchgift/addgift error:", e);
			view.addObject("errormsg", e.getMessage());
			return view;
		}
		return view;
	}
	/**
	 * 撤销
	 * @param id
	 * @param flag
	 * @param view
	 * @return
	 */
	@RequestMapping("/doAudit")
	public ModelAndView doAudit(@RequestParam(value = "id") String id,
			ModelAndView view){
		logger.info("batchgift/doAudit");
		String errormsg = "撤销成功";
		try {
			Tgiftaudit tgiftaudit = Tgiftaudit.findTgiftaudit(id);
			tgiftaudit.setFlag(-1);
			tgiftaudit.setAftertime(new Date());
			tgiftaudit.merge();
		} catch (Exception e) {
			e.printStackTrace();
			errormsg = e.getMessage();
		}
		view.addObject("errormsg", errormsg);
		return this.page(new Page<Tgiftaudit>(),0, view);
	}
	@Autowired
	private PropertiesUtil propertiesUtil;
	public static Map<String, GeneratedCode> betcodemap = new HashMap<String, GeneratedCode>();
	static{
		betcodemap.put("F47102", new F47102());
		betcodemap.put("F47104", new F47104());
		betcodemap.put("F47103_SJ", new F47103_SJ());
		betcodemap.put("F47103_BH", new F47103_BH());
		betcodemap.put("F47103_Z3", new F47103_Z3());
		betcodemap.put("F47103_Z6", new F47103_Z6());
	}
	/**
	 * 赠送方法
	 * @param id
	 * @param notes
	 * @param lotno
	 * @param sendmun
	 * @param view
	 * @return
	 */
	@RequestMapping("/dogift")
	public ModelAndView dogift(@RequestParam(value = "id") String id,// 流水号
			@RequestParam(value = "notes") String notes,// 短信通道开 on 关 off
			@RequestParam(value = "lotno") String lotno,// 采种
			@RequestParam(value = "type", required=false) String type,// 采种
			@RequestParam(value = "sendmun") int sendmun,//一次赠送数量
			@RequestParam(value = "flag") BigDecimal flag,//赠送未赠送0  赠送失败2
			ModelAndView view){
		logger.info("batchgift/dogift");
		String errormsg = "赠送成功";//返回信息描述
		try {
			Tgiftaudit tgiftaudit = Tgiftaudit.findTgiftaudit(id);
			if (tgiftaudit.getFlag() != 1) {
				view.addObject("errormsg", "不是已审核状态");
				return this.todetail(id, view);
			}
			String userno = tgiftaudit.getBiguserno();
			Tuserinfo user = Tuserinfo.findTuserinfo(userno);
			
			long num = tgiftaudit.getBetnum();
			Tlotctrl tlotctrl = Tlotctrl.findCurrentTlotctrls(lotno);
			OrderRequest orderRequest = new OrderRequest();
			orderRequest.setLotno(lotno); //彩种
			orderRequest.setBatchcode(tlotctrl.getId().getBatchcode()); //期号
			orderRequest.setLotmulti(BigDecimal.ONE); //倍数
			orderRequest.setAmt(new BigDecimal(200*num)); //总金额
			orderRequest.setOneamount(new BigDecimal(200)); //单注金额
			orderRequest.setSubchannel("00092493"); //用户系统
			orderRequest.setBettype(new BigDecimal(5)); //控制后台是否发送短信
			orderRequest.setChannel(user.getChannel()); //渠道编号
			orderRequest.setBuyuserno(userno); 
			
			String genType = "";
			if (lotno.equals("F47103")) {
				genType = lotno+"_"+type;
			}else{
				genType = lotno;
			}
			GeneratedCode gc = betcodemap.get(genType);
			String contents = "";
			String jrtnotes = "";
			if("on".equals(notes)){
				String clientcontents = tgiftaudit.getSmscontent(); // 客户短信和jrt内容
				String clientnotes = clientcontents.split("&&")[0].trim(); // 短信
				jrtnotes = clientcontents.split("&&")[1].trim(); // jrt内容
				String lotname = "";
				if ("F47102".equals(lotno)) {
					lotname = "七乐彩";
				}else if("F47104".equals(lotno)){
					lotname = "双色球";
				}else if("F47103".equals(lotno)){
					lotname = "3D";
					if ("Z3".equals(type)) {
						lotname += "组三";
					}else if ("Z6".equals(type)) {
						lotname += "组六";
					}
				}
				//contents = clientnotes + "福彩" + lotname + tlotctrl.getId().getBatchcode() + "期彩票" + tgiftaudit.getBetnum() + "注:";
				contents = clientnotes + lotname + tlotctrl.getId().getBatchcode() + "期彩票" + tgiftaudit.getBetnum() + "注:";
			}
			
			
			//String url = propertiesUtil.getLotteryurl() + "/bet/tobetOrder";//旧的lottery 赠送接口
			//String url = propertiesUtil.getPresentCenterurl() + "/savepresent";//旧的lottery 赠送接口
			String url = propertiesUtil.getLotteryurl() + "/present/savepresent";//旧的lottery 赠送接口
			//String registurl = propertiesUtil.getLotteryurl() + "/tuserinfoes/register";
			String msgurl = "http://192.168.99.6/msgcenter/sms/delaysend";
			//String msgurl = "http://192.168.0.118:8000/msgcenter/sms/delaysend";//测试url
			
			int start = 0;
			while(start < sendmun){
				int end = 500;
				if (start+500 > sendmun) {
					end = sendmun - start;
				}
				List<Tgift> gifts = Tgift.findByTgiftauditid(id, flag, end);// 0为赠送，1为成功
				for (Tgift tgift : gifts) {
					List<BetRequest> betRequests = new ArrayList<BetRequest>();
					BetRequest betRequest = new BetRequest();
					String sbetcode = "";
					for(int i=0;i<num;i++){
						betRequest = new BetRequest();
						betRequest.setAmt(new BigDecimal(200));
						String betcode = gc.getBetCode();
						betRequest.setBetcode(betcode);
						betRequests.add(betRequest);
						sbetcode += gc.getStringBetCode(betcode);
					}
					orderRequest.setBetRequests(betRequests);
					
					/*Tuserinfo tuserinfo = Tuserinfo.findTuserinfoesByMobileid(tgift.getSmalluserno(), "00092493");
					if (tuserinfo==null) {//注册
						logger.info("用户不存在,调用lottery注册");
						String param = "mobileid="+tgift.getSmalluserno()+"&password=00000&channel="+user.getChannel()+"&certid=111111111111111111&userState=0";
						String result = HttpUtil.post(registurl, param);
						JSONObject json = new JSONObject(result);
						if ("0".equals(json.get("errorCode"))) {
							JSONObject valueObject = (JSONObject) json.get("value");
							orderRequest.setUserno(valueObject.getString("userno"));
						}else{
							logger.info("注册失败继续执行下一个号码，远程服务器返回："+json);
							continue;
						
						}
					}else{
						orderRequest.setUserno(tuserinfo.getUserno());
					}*/
					orderRequest.setReciverMobile(tgift.getSmalluserno());
					logger.info("赠送，参数为："+new JSONObject(orderRequest));
					String resStr = HttpUtil.post(url, "body="+new JSONObject(orderRequest));
					JSONObject resStrJson = new JSONObject(resStr);
					if(resStrJson.getString("errorCode").equals("0")){
						logger.info("赠送成功,返回:"+resStr);
						JSONObject jo = resStrJson.getJSONObject("value");
						tgift.setFlowno(jo.getString("orderid"));
						tgift.setFlag(BigDecimal.ONE);
						tgift.merge();
						if ("on".equals(notes)) {
							logger.info("短信接口参数，手机号："+tgift.getSmalluserno()+",内容："+contents + sbetcode + jrtnotes);
							resStr = HttpUtil.post(msgurl, "mobileIds=" + tgift.getSmalluserno() + "&text=" + URLEncoder.encode(contents + sbetcode + jrtnotes, "UTF-8"));
							logger.info("短信接口返回："+resStr);
						}
					}else{
						tgift.setFlag(new BigDecimal(2));
						tgift.merge();
						
						logger.info("调用lottery 送票失败，后台返回：" +resStrJson);
						errormsg = "调用lottery 送票失败，后台返回:"+resStrJson.getString("errorCode");
					}
				}
				
				start = start + 500;
			}
			
			//统计出 赠送成功的 失败的
			int fail = Tgift.findByTgifCount(id, BigDecimal.ZERO);
			int sendfail = Tgift.findByTgifCount(id, new BigDecimal(2));
			int suc = Tgift.findByTgifCount(id, BigDecimal.ONE);
			logger.info("还未赠送的数量："+fail+",已经赠送的数量："+suc);
			if (fail == 0 && sendfail ==0) {
				tgiftaudit.setFlag(2);
				tgiftaudit.setFailure(1L);
				tgiftaudit.setSuccess(new Long(suc));
				tgiftaudit.merge();
			}else{
				tgiftaudit.setSuccess(new Long(suc));
				tgiftaudit.merge();
			}
			//更新赠送记录表数
		} catch (Exception e) {
			e.printStackTrace();
			errormsg = e.getMessage();
		} 
		view.addObject("errormsg", errormsg);
		return this.todetail(id, view);
	}
	/**
	 * 已审核订单详细
	 * @param id
	 * @param flag
	 * @param view
	 * @return
	 */
	@RequestMapping("/todetail")
	public ModelAndView todetail(@RequestParam(value = "id") String id,
			ModelAndView view){
		logger.info("batchgift/todetail");
		try {
			view.addObject("tgiftaudit", Tgiftaudit.findTgiftaudit(id));
			view.addObject("sucnum", Tgift.findByTgifCount(id, BigDecimal.ONE));
			view.addObject("failnum", Tgift.findByTgifCount(id, BigDecimal.ZERO));
			view.addObject("sendfailnum", Tgift.findByTgifCount(id, new BigDecimal(2)));
		} catch (Exception e) {
			logger.error("batchgift/todetail error:", e);
		}
		view.setViewName("batchgift/detail");
		return view;
	}
	
	public static void main(String[] args) {
		int sendmun = 1200;
		int start = 0;
		while(start < sendmun){
			int end = 500;
			if (start+500 > sendmun) {
				end = sendmun - start;
			}
			start = start + 500;
			
		}
	}
}
