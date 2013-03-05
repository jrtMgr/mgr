//package com.ruyicai.mgr.statis;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//import org.apache.log4j.Logger;
//
//import com.ruyicai.common.Constants;
//import com.ruyicai.common.ConstantsLottery;
//import com.ruyicai.common.HttpUtil;
//import com.ruyicai.dto.BetRequest;
//import com.ruyicai.dto.CaseLotRequest;
//import com.ruyicai.dto.OrderRequest;
//import com.ruyicai.dto.SubscribeRequest;
//import com.ruyicai.protocol.ClientInfo;
//import com.ruyicai.util.CommonUtil;
//import com.ruyicai.util.LotteryCommonService;
//import com.ruyicai.util.Tools;
//import com.sun.org.apache.bcel.internal.generic.InstructionConstants.Clinit;
//
///**
// * 投注与赠彩的service
// * @author 丁俊杰
// * @date 2011-08-01
// *
// */
//public class BetLotService {
//	private Logger log = Logger.getLogger(this.getClass());
//	//private Logger backgroundlog = Logger.getLogger("BACKGROUND_LOG");
//	
//	/**
//	 * 单票投注
//	 * @param clientInfo
//	 * @return
//	 */
//	public String bet(ClientInfo clientInfo){
//		String url = ConstantsLottery.URL+"bet/tobet?";
//		JSONObject resJson = new JSONObject();
//		String resStr = null;//投注返回的字符串
//		String errorCode = "";//返回码
//		String message = "";//返回信息描述
//		String oneAmount = "";//每注的金额，默认是2元，大乐透追加为3元每注
//		String issuperaddition = clientInfo.getIsSuperaddition();
//		if(!Tools.isEmpty(issuperaddition)&&issuperaddition.equals("0")){
//			oneAmount = "3";
//		} else if (clientInfo.getLotNo().equals("T01001")
//				&&CommonUtil.issuperaddition(clientInfo.getBetCode(), clientInfo.getAmount(), clientInfo.getLotmulti())) { //根据注码判断是否是追加
//			oneAmount = "3";
//		} else {
//			oneAmount = "2";
//		}
//		String userno = clientInfo.getUserno();
//		if(Tools.isEmpty(userno))
//		  userno = Tools.getUserNo(clientInfo.getPhonenum());
//		StringBuffer paramBuffer = new StringBuffer();
//		paramBuffer.append("userno=" + userno);//用户编号
//		paramBuffer.append("&batchCode=" + clientInfo.getBatchCode());//期号,
//		paramBuffer.append("&betCode=" + clientInfo.getBetCode());//注码
//		paramBuffer.append("&lotNo=" + clientInfo.getLotNo());//彩种
//		paramBuffer.append("&lotMulti=" + clientInfo.getLotmulti());//倍数
//		paramBuffer.append("&oneAmount=" + oneAmount);//默认为2元
//		paramBuffer.append("&amount=" + clientInfo.getAmount());//金额
//		paramBuffer.append("&sellWay=" + clientInfo.getSellway());//销售方式 
//		paramBuffer.append("&batchNum=" + clientInfo.getBatchnum());//追号期数
//		paramBuffer.append("&isSync=true");// 投注为同步的方式
//		paramBuffer.append("&channel="+clientInfo.getCoopId());//渠道编号
//		log.info("投注的请求串:"+url+paramBuffer.toString());
//		try {
//			resStr = HttpUtil.sendByPostUtF(url, paramBuffer.toString());
//			log.info("投注返回的字符串:"+resStr);
//			JSONObject  resStrJson = JSONObject.fromObject(resStr);
//			if(resStrJson.getString("errorCode").equals("0")) {
//				errorCode = "0000";
//				message = "投注成功";
//			} else if(resStrJson.getString("errorCode").equals("20100710")||resStrJson.getString("errorCode").equals("20100701")) {//余额不足
//				errorCode = "0406";
//				message = "余额不足";
//			} else if(resStrJson.getString("errorCode").equals("20100706")) {//该期已过期
//				errorCode = "9999";
//				message = "该期已过期";
//			} else if(resStrJson.getString("errorCode").equals("20100705")) {//该期不存在
//				errorCode = "9999";
//				message = "该期不存在";
//			} else {
//				errorCode = "9999";
//				message = "投注失败";
//			}
//		} catch (Exception e) {
//			errorCode = "9999";
//			message = "系统异常！";
//			log.error("投注异常！！！！！",e);
//		}
//		resJson.put(Constants.ERRORCODE, errorCode);
//		resJson.put(Constants.MESSAGE, message);
//		return resJson.toString();
//	}
//	
//	/**
//	 * 订单投注
//	 * @param clientInfo
//	 * @return
//	 */
//	public String betByOrder(ClientInfo clientInfo){
//		String url = ConstantsLottery.URL+"bet/tobetOrder?";
//		JSONObject resJson = new JSONObject();
//		String resStr = null;//投注返回的字符串
//		String errorCode = "";//返回码
//		String message = "";//返回信息描述
//		String oneAmount = "";//每注的金额，默认是2元，大乐透追加为3元每注
//		String issuperaddition = clientInfo.getIsSuperaddition();
//		if(!Tools.isEmpty(issuperaddition)&&issuperaddition.equals("0")){
//			oneAmount = "300";
//		} else if (clientInfo.getLotNo().equals("T01001")
//				&&CommonUtil.issuperaddition(clientInfo.getBetCode(), clientInfo.getAmount(), clientInfo.getLotmulti())) { //根据注码判断是否是追加
//			oneAmount = "300";
//		} else {
//			oneAmount = "200";
//		}
//		String userno = clientInfo.getUserno();
//		if(Tools.isEmpty(userno))
//		  userno = Tools.getUserNo(clientInfo.getPhonenum());
//		OrderRequest orderRequest = new OrderRequest();
//		orderRequest.setBuyuserno(userno); //用户编号
//		orderRequest.setUserno(userno);
//		orderRequest.setPaytype(BigDecimal.ONE); //追号时所有期全部扣款
//		orderRequest.setBettype(new BigDecimal(2)); //投注类型为购彩
//		
//		List<BetRequest> betRequests = new ArrayList<BetRequest>(); //彩票信息
//		BetRequest betRequest = new BetRequest();
//		betRequest.setAmt(new BigDecimal(Integer.parseInt(clientInfo.getAmount())/Integer.parseInt(clientInfo.getLotmulti()))); //金额
//		String lotNo = clientInfo.getLotNo();
//		if (lotNo!=null && !lotNo.equals("")&&(lotNo.equals("F47102")||lotNo.equals("F47104"))) {
//			String betCode = clientInfo.getBetCode();
//			String[] split = betCode.split("\\^");
//			StringBuffer sBuffer = new StringBuffer();
//			for (int i = 0; i < split.length; i++) {
//				String newString  = split[i].substring(0, 2)+"01"+split[i].substring(4);
//				sBuffer.append(newString).append("^");
//			}
//			betRequest.setBetcode(sBuffer.toString()); //注码
//		} else if (lotNo!=null && !lotNo.equals("") && lotNo.equals("F47103")) { //福彩3D
//			String betCode = clientInfo.getBetCode();
//			StringBuffer sBuffer = new StringBuffer();
//			if (betCode.substring(0, 2).equals("20")) { //直选投注 注码格式为20010105^0106^0107,所以要特殊处理
//				String newString  = betCode.substring(0, 2)+"01"+betCode.substring(4);
//				betRequest.setBetcode(newString);
//			} else if (betCode.substring(0, 2).equals("12")) { //组6和值(如果注码小于10客户端传过来的注码是1位前面没有加0，所以在此处理)
//				if (betCode.substring(4, betCode.indexOf("^")).length()==1) {
//					String newString  = betCode.substring(0, 2)+"01"+"0"+betCode.substring(4);
//					sBuffer.append(newString);
//				} else {
//					String newString  = betCode.substring(0, 2)+"01"+betCode.substring(4);
//					sBuffer.append(newString);
//				}
//				betRequest.setBetcode(sBuffer.toString()); //注码
//			} else if (betCode.substring(0, 2).equals("10")) { //直选和值 (如果和值为10客户端传过来的注码多加了0，所以在此处理)
//				if (betCode.substring(4, betCode.indexOf("^")).length()==3) {
//					String newString  = betCode.substring(0, 2)+"01"+betCode.substring(5);
//					sBuffer.append(newString);
//				} else {
//					String newString  = betCode.substring(0, 2)+"01"+betCode.substring(4);
//					sBuffer.append(newString);
//				}
//				betRequest.setBetcode(sBuffer.toString()); //注码
//			} else {
//				String[] split = betCode.split("\\^");
//				for (int i = 0; i < split.length; i++) {
//					String newString  = split[i].substring(0, 2)+"01"+split[i].substring(4);
//					sBuffer.append(newString).append("^");
//				}
//				betRequest.setBetcode(sBuffer.toString()); //注码
//			}
//		} else {
//			betRequest.setBetcode(clientInfo.getBetCode()); //注码
//		}
//		betRequests.add(betRequest);
//		orderRequest.setBetRequests(betRequests);
//		
//		//获取期号
//		/*String batchCode = clientInfo.getBatchCode();
//		if (batchCode==null || batchCode.equals("")) {
//			if (Constants.BATCHCODE_MAP.get(clientInfo.getLotNo())!=null) {
//				JSONObject batchCodeJsonObject = (JSONObject)Constants.BATCHCODE_MAP.get(clientInfo.getLotNo());
//				batchCode = batchCodeJsonObject.getString("batchCode");
//			}
//		}*/
//		//从lottery获取当前期号
//		String newBatchCode = LotteryCommonService.getCurrentBatchCodeByLotNo(lotNo);
//		
//		int batchnumInt = 1;
//		if (clientInfo.getBatchnum()!=null && !clientInfo.getBatchnum().equals("")) { //追号期数
//			batchnumInt = Integer.parseInt(clientInfo.getBatchnum());
//		}
//		List<SubscribeRequest> subscribeRequests = new ArrayList<SubscribeRequest>(); //追号SubscribeRequest
//		if (batchnumInt>1) {
//			orderRequest.setBettype(new BigDecimal(0)); //投注类型为追号
//			String afterIssue = LotteryCommonService.getAfterIssue(clientInfo.getLotNo(), newBatchCode, (batchnumInt-1)+"");
//			if (afterIssue!=null && !afterIssue.equals("") && !afterIssue.equals("null")) {
//				JSONArray afterIssueArray = JSONArray.fromObject(afterIssue);
//				for (int i = 0; i < afterIssueArray.size(); i++) {
//					SubscribeRequest subscribeRequest = new SubscribeRequest();
//					JSONObject afterIssueObject = (JSONObject)afterIssueArray.get(i);
//					JSONObject idObject = afterIssueObject.getJSONObject("id");
//					subscribeRequest.setAmt(new BigDecimal(Integer.parseInt(clientInfo.getAmount())));
//					subscribeRequest.setBatchcode(idObject.getString("batchcode"));
//					subscribeRequest.setLotmulti(new BigDecimal(clientInfo.getLotmulti()));
//					subscribeRequests.add(subscribeRequest);
//				}
//			}
//		}
//		orderRequest.setSubscribeRequests(subscribeRequests);
//		
//		orderRequest.setLotno(clientInfo.getLotNo()); //彩种
//		orderRequest.setBatchcode(newBatchCode); //期号
//		orderRequest.setLotmulti(new BigDecimal(clientInfo.getLotmulti())); //倍数
//		Integer totalAmt = Integer.parseInt(clientInfo.getAmount()) * batchnumInt; //总金额=单注金额*追号期数
//		orderRequest.setAmt(new BigDecimal(totalAmt)); //总金额
//		orderRequest.setOneamount(new BigDecimal(oneAmount)); //单注金额
//		orderRequest.setSubchannel("00092493"); //用户系统
//		orderRequest.setChannel(clientInfo.getCoopId()); //渠道编号
//		log.info("订单投注的参数:"+JSONObject.fromObject(orderRequest));
//		try {
//			resStr = HttpUtil.sendByPostUtF(url, "body="+JSONObject.fromObject(orderRequest));
//			log.info("订单投注返回的字符串:"+resStr);
//			JSONObject  resStrJson = JSONObject.fromObject(resStr);
//			if(resStrJson.getString("errorCode").equals("0")) {
//				errorCode = "0000";
//				message = "投注成功";
//			} else if(resStrJson.getString("errorCode").equals("20100710")||resStrJson.getString("errorCode").equals("20100701")) {//余额不足
//				errorCode = "0406";
//				message = "余额不足";
//			} else if(resStrJson.getString("errorCode").equals("20100706")) {//该期已过期
//				errorCode = "9999";
//				message = "该期已过期";
//			} else if(resStrJson.getString("errorCode").equals("20100705")) {//该期不存在
//				errorCode = "9999";
//				message = "该期不存在";
//			} else {
//				errorCode = "9999";
//				message = "投注失败";
//			}
//		} catch (Exception e) {
//			errorCode = "9999";
//			message = "系统异常！";
//			log.error("投注异常！！！！！",e);
//		}
//		resJson.put(Constants.ERRORCODE, errorCode);
//		resJson.put(Constants.MESSAGE, message);
//		return resJson.toString();
//	}
//	
//	
//	/**
//	 * 赠彩
//	 * @param clientInfo
//	 * @return
//	 */
//	public String give(ClientInfo clientInfo){
//		String url = ConstantsLottery.URL+"bet/give?";
//		JSONObject resJson = new JSONObject();
//		String resStr = null;//投注返回的字符串
//		String errorCode = "";//返回码
//		String message = "";//返回信息描述
//		String to_moblie_code = clientInfo.getTo_mobile_code();//被赠送人手机号
//		StringBuffer paramBuffer = null;
//		String success = "";//赠送成功的手机号
//		String fail = "";//赠送失败的手机号
//		String userno = clientInfo.getUserno();
//		if(Tools.isEmpty(userno))
//			  userno = Tools.getUserNo(clientInfo.getPhonenum());
//		String oneAmount = "";
//		String issuperaddition = clientInfo.getIsSuperaddition();
//		if(!Tools.isEmpty(issuperaddition)&&issuperaddition.equals("0")){
//			oneAmount = "3";
//		}else {
//			oneAmount = "2";
//		}
//		JSONObject giftResult = new JSONObject();//赠送结果json
//		try {
//			String[] toMobleArry = to_moblie_code.split(","); 
//			for(int i=0;i<toMobleArry.length;i++){
//				if(toMobleArry[i].trim().equals("")){
//					continue;
//				}
//				paramBuffer = new StringBuffer();
//				paramBuffer.append("userno=" + userno);//用户编号
//				paramBuffer.append("&batchCode=" + clientInfo.getBatchCode());//期号,
//				paramBuffer.append("&betCode=" + clientInfo.getBetCode());//注码
//				paramBuffer.append("&lotNo=" + clientInfo.getLotNo());//彩种
//				paramBuffer.append("&lotMulti=" + clientInfo.getLotmulti());//倍数
//				paramBuffer.append("&oneAmount=" + oneAmount);//默认为2元
//				paramBuffer.append("&amount=" + clientInfo.getAmount());//金额
//				paramBuffer.append("&sellWay=" + clientInfo.getSellway());//销售方式 
//				paramBuffer.append("&batchNum=" + clientInfo.getBatchnum());//追号期数
//				paramBuffer.append("&isSync=true");// 投注为同步的方式
//				paramBuffer.append("&channel="+clientInfo.getCoopId());//渠道编号
//				paramBuffer.append("&bettype=" + "5");// 赠送不发送短信
//				paramBuffer.append("&to_mobile_code=" + toMobleArry[i]);//被赠送人的手机号码
//				resStr = HttpUtil.sendByPostUtF(url, paramBuffer.toString());
//				log.info("赠送返回结果:"+resStr);
//				JSONObject  resStrJson = JSONObject.fromObject(resStr);
//				if(resStrJson.getString("errorCode").equals("0")){
//					success += toMobleArry[i]+","; 
//				}else{
//					fail += toMobleArry[i]+",";
//				}
//				errorCode = "0000";
//				message = "赠送成功";
//			}
//			
//		} catch (Exception e) {
//			errorCode = "9999";
//			message = "赠送失败";
//			log.error("赠送发生异常:"+e.toString());
//			e.printStackTrace();
//		}
//		giftResult.put("success", success);
//		giftResult.put("fail", fail);
//		resJson.put(Constants.ERRORCODE, errorCode);
//		resJson.put(Constants.MESSAGE, message);
//		resJson.put(Constants.GIFT_RESULT, giftResult);
//		return resJson.toString();
//	}
//	
//	/**
//	 * 订单赠彩
//	 * @param clientInfo
//	 * @return
//	 */
//	public String giveByOrder(ClientInfo clientInfo){
//		String url = ConstantsLottery.URL+"bet/tobetOrder?";
//		JSONObject resJson = new JSONObject();
//		String resStr = null;//投注返回的字符串
//		String errorCode = "";//返回码
//		String message = "";//返回信息描述
//		String to_moblie_code = clientInfo.getTo_mobile_code();//被赠送人手机号
//		//StringBuffer paramBuffer = null;
//		String success = "";//赠送成功的手机号
//		String fail = "";//赠送失败的手机号
//		String userno = clientInfo.getUserno();
//		if(Tools.isEmpty(userno))
//			  userno = Tools.getUserNo(clientInfo.getPhonenum());
//		String oneAmount = "";
//		String issuperaddition = clientInfo.getIsSuperaddition();
//		if(!Tools.isEmpty(issuperaddition)&&issuperaddition.equals("0")){
//			oneAmount = "300";
//		}else {
//			oneAmount = "200";
//		}
//		JSONObject giftResult = new JSONObject();//赠送结果json
//		try {
//			String[] toMobleArry = to_moblie_code.split(","); 
//			for(int i=0;i<toMobleArry.length;i++){
//				if(toMobleArry[i].trim().equals("")){
//					continue;
//				}
//				//获取被赠送人的userno
//				String acceptNo = "";
//				String queryUsers = LotteryCommonService.queryUsersByMobileid(toMobleArry[i].trim(), "00092493");
//				if (queryUsers!=null && !queryUsers.trim().equals("")) {
//					JSONObject fromObject = JSONObject.fromObject(queryUsers);
//					if (fromObject.has("errorCode")) {
//						if (!fromObject.getString("errorCode").trim().equals("0")) { //用户不存在进行注册
//							String register = LotteryCommonService.register(toMobleArry[i].trim(), "00000", "111111111111111111", clientInfo.getCoopId(), "0");
//							if (register != null && !register.trim().equals("")) {
//								JSONObject fromObject2 = JSONObject.fromObject(register);
//								if (fromObject2.has("errorCode") && fromObject2.getString("errorCode").equals("0")) {
//									JSONObject valueObject = (JSONObject)fromObject2.get("value");
//									if (valueObject.has("userno")) {
//										acceptNo = valueObject.getString("userno");
//									}
//								}
//							}
//						} else {
//							JSONObject valueObject = (JSONObject)fromObject.get("value");
//							if (valueObject.has("userno")) {
//								acceptNo = valueObject.getString("userno");
//							}
//						}
//					}
//				}
//				
//				OrderRequest orderRequest = new OrderRequest();
//				orderRequest.setBuyuserno(userno); //用户编号
//				if (acceptNo!=null && !acceptNo.trim().equals("")) {
//					orderRequest.setUserno(acceptNo);
//				} else {
//					continue;
//				}
//				
//				List<BetRequest> betRequests = new ArrayList<BetRequest>(); //彩票信息
//				BetRequest betRequest = new BetRequest();
//				betRequest.setAmt(new BigDecimal(Integer.parseInt(clientInfo.getAmount())/Integer.parseInt(clientInfo.getLotmulti()))); //金额
//				String lotNo = clientInfo.getLotNo();
//				if (lotNo!=null && !lotNo.equals("")&&(lotNo.equals("F47102")||lotNo.equals("F47104"))) {
//					String betCode = clientInfo.getBetCode();
//					String[] split = betCode.split("\\^");
//					StringBuffer sBuffer = new StringBuffer();
//					for (int j = 0; j < split.length; j++) {
//						String newString  = split[j].substring(0, 2)+"01"+split[j].substring(4);
//						sBuffer.append(newString).append("^");
//					}
//					betRequest.setBetcode(sBuffer.toString()); //注码
//				} else if (lotNo!=null && !lotNo.equals("") && lotNo.equals("F47103")) {
//					String betCode = clientInfo.getBetCode();
//					StringBuffer sBuffer = new StringBuffer();
//					if (betCode.substring(0, 2).equals("20")) { //直选投注 注码格式为20010105^0106^0107,所以要特殊处理
//						String newString  = betCode.substring(0, 2)+"01"+betCode.substring(4);
//						betRequest.setBetcode(newString);
//					} else if (betCode.substring(0, 2).equals("12")) { //组6和值(如果注码小于10客户端传过来的注码是1位前面没有加0，所以在此处理)
//						if (betCode.substring(4, betCode.indexOf("^")).length()==1) {
//							String newString  = betCode.substring(0, 2)+"01"+"0"+betCode.substring(4);
//							sBuffer.append(newString);
//						} else {
//							String newString  = betCode.substring(0, 2)+"01"+betCode.substring(4);
//							sBuffer.append(newString);
//						}
//						betRequest.setBetcode(sBuffer.toString()); //注码
//					} else if (betCode.substring(0, 2).equals("10")) { //直选和值 (如果和值为10客户端传过来的注码多加了0，所以在此处理)
//						if (betCode.substring(4, betCode.indexOf("^")).length()==3) {
//							String newString  = betCode.substring(0, 2)+"01"+betCode.substring(5);
//							sBuffer.append(newString);
//						} else {
//							String newString  = betCode.substring(0, 2)+"01"+betCode.substring(4);
//							sBuffer.append(newString);
//						}
//						betRequest.setBetcode(sBuffer.toString()); //注码
//					} else {
//						String[] split = betCode.split("\\^");
//						for (int j = 0; j < split.length; j++) {
//							String newString  = split[j].substring(0, 2)+"01"+split[j].substring(4);
//							sBuffer.append(newString).append("^");
//						}
//						betRequest.setBetcode(sBuffer.toString()); //注码
//					}
//				} else {
//					betRequest.setBetcode(clientInfo.getBetCode()); //注码
//				}
//				betRequests.add(betRequest);
//				orderRequest.setBetRequests(betRequests);
//				
//				//获取期号
//				/*String batchCode = clientInfo.getBatchCode();
//				if (batchCode==null || batchCode.equals("")) {
//					if (Constants.BATCHCODE_MAP.get(clientInfo.getLotNo())!=null) {
//						JSONObject batchCodeJsonObject = (JSONObject)Constants.BATCHCODE_MAP.get(clientInfo.getLotNo());
//						batchCode = batchCodeJsonObject.getString("batchCode");
//					}
//				}*/
//				//从lottery获取当前期号
//				String newBatchCode = LotteryCommonService.getCurrentBatchCodeByLotNo(lotNo);
//				
//				orderRequest.setLotno(clientInfo.getLotNo()); //彩种
//				orderRequest.setBatchcode(newBatchCode); //期号
//				orderRequest.setLotmulti(new BigDecimal(clientInfo.getLotmulti())); //倍数
//				Integer totalAmt = Integer.parseInt(clientInfo.getAmount());
//				orderRequest.setAmt(new BigDecimal(totalAmt)); //总金额
//				orderRequest.setOneamount(new BigDecimal(oneAmount)); //单注金额
//				orderRequest.setSubchannel("00092493"); //用户系统
//				orderRequest.setBettype(new BigDecimal(5)); //控制后台是否发送短信
//				orderRequest.setChannel(clientInfo.getCoopId()); //渠道编号
//				resStr = HttpUtil.sendByPostUtF(url, "body="+JSONObject.fromObject(orderRequest));
//				log.info("订单赠送返回结果:"+resStr);
//				JSONObject  resStrJson = JSONObject.fromObject(resStr);
//				if(resStrJson.getString("errorCode").equals("0")){
//					success += toMobleArry[i]+","; 
//				}else{
//					fail += toMobleArry[i]+",";
//				}
//				errorCode = "0000";
//				message = "赠送成功";
//			}
//			
//		} catch (Exception e) {
//			errorCode = "9999";
//			message = "赠送失败";
//			log.error("赠送发生异常:"+e.toString());
//			e.printStackTrace();
//		}
//		giftResult.put("success", success);
//		giftResult.put("fail", fail);
//		resJson.put(Constants.ERRORCODE, errorCode);
//		resJson.put(Constants.MESSAGE, message);
//		resJson.put(Constants.GIFT_RESULT, giftResult);
//		return resJson.toString();
//	}
//	
//	/**
//	 * 参与合买
//	 * @param clientInfo
//	 * @return
//	 */
//	public String betCaseLot(ClientInfo clientInfo){
//		JSONObject resJson = new JSONObject();
//		String url = ConstantsLottery.URL+"bet/betCaselot";
//		String userno = clientInfo.getUserno();
//		if(Tools.isEmpty(userno))
//		  userno = Tools.getUserNo(clientInfo.getPhonenum());
//		StringBuffer paramStr = new StringBuffer();
//		String safeAmt = clientInfo.getSafeAmt();
//		if(Tools.isEmpty(safeAmt))
//			safeAmt = "0";
//		paramStr.append("userno="+userno);
//		paramStr.append("&amt="+clientInfo.getAmount());
//		paramStr.append("&caseId="+clientInfo.getCaseid());
//		paramStr.append("&safeAmt="+safeAmt);
//		log.info("参与合买的参数:"+paramStr.toString());
//		String resStr = HttpUtil.sendByPostUtF(url, paramStr.toString());
//		log.info("参与合买返回resStr:"+resStr);
//		if(Tools.isEmpty(resStr))
//			resStr = Tools.paramError();
//		JSONObject resStrJson = JSONObject.fromObject(resStr);
//		if(resStrJson.getString("errorCode").equals("0")){
//			resJson.put(Constants.ERRORCODE, "0000");
//			resJson.put(Constants.MESSAGE, "参与合买成功");	
//		}else if(resStrJson.getString("errorCode").equals("500001")){
//			resJson.put(Constants.ERRORCODE, "9999");
//			resJson.put(Constants.MESSAGE, "合买方案不存在");
//		}else if(resStrJson.getString("errorCode").equals("500002")){
//			resJson.put(Constants.ERRORCODE, "9999");
//			resJson.put(Constants.MESSAGE, "合买方案已满");
//		}else if(resStrJson.getString("errorCode").equals("500004")){
//			resJson.put(Constants.ERRORCODE, "9999");
//			resJson.put(Constants.MESSAGE, "保底金额异常");
//		}else{
//			resJson.put(Constants.ERRORCODE, "9999");
//			resJson.put(Constants.MESSAGE, "参与合买失败");
//		}
//		return resJson.toString();
//	}
//	
//	/**
//	 * 发起合买
//	 * @param clientInfo
//	 * @return
//	 */
//	public String startCaseLot(ClientInfo clientInfo){
//		JSONObject resJson = new JSONObject();
//		String url = ConstantsLottery.URL+"bet/tobetOrder";
//		String userno = clientInfo.getUserno();
//		if(Tools.isEmpty(userno))
//		  userno = Tools.getUserNo(clientInfo.getPhonenum());
//		OrderRequest orderRequest = new OrderRequest();
//		orderRequest.setBuyuserno(userno); //用户编号
//		orderRequest.setBettype(new BigDecimal(3)); //投注类型为合买
//		orderRequest.setChannel(clientInfo.getCoopId()); //渠道编号
//		
//		List<BetRequest> betRequests = new ArrayList<BetRequest>(); //彩票信息
//		BetRequest betRequest = new BetRequest();
//		betRequest.setAmt(new BigDecimal(Integer.parseInt(clientInfo.getAmount())/Integer.parseInt(clientInfo.getLotmulti()))); //金额
//		String lotNo = clientInfo.getLotNo();
//		if (lotNo!=null && !lotNo.equals("")&&(lotNo.equals("F47102")||lotNo.equals("F47104"))) { 
//			//福彩客户端传过来的注码带倍数,而lottery不需要,所以在此转成1倍的
//			String betCode = clientInfo.getBetCode();
//			String[] split = betCode.split("\\^");
//			StringBuffer sBuffer = new StringBuffer();
//			for (int i = 0; i < split.length; i++) {
//				String newString  = split[i].substring(0, 2)+"01"+split[i].substring(4);
//				sBuffer.append(newString).append("^");
//			}
//			betRequest.setBetcode(sBuffer.toString()); //注码
//		} else if (lotNo!=null && !lotNo.equals("")&&(lotNo.equals("F47103"))) { //福彩3D
//			String betCode = clientInfo.getBetCode();
//			StringBuffer sBuffer = new StringBuffer();
//			if (betCode.substring(0, 2).equals("20")) { //直选投注 注码格式为20010105^0106^0107,所以要特殊处理
//				String newString  = betCode.substring(0, 2)+"01"+betCode.substring(4);
//				betRequest.setBetcode(newString);
//			} else if (betCode.substring(0, 2).equals("12")) { //组6和值(如果注码小于10客户端传过来的注码是1位前面没有加0，所以在此处理)
//				if (betCode.substring(4, betCode.indexOf("^")).length()==1) {
//					String newString  = betCode.substring(0, 2)+"01"+"0"+betCode.substring(4);
//					sBuffer.append(newString);
//				} else {
//					String newString  = betCode.substring(0, 2)+"01"+betCode.substring(4);
//					sBuffer.append(newString);
//				}
//				betRequest.setBetcode(sBuffer.toString()); //注码
//			} else if (betCode.substring(0, 2).equals("10")) { //直选和值 (如果和值为10客户端传过来的注码多加了0，所以在此处理)
//				if (betCode.substring(4, betCode.indexOf("^")).length()==3) {
//					String newString  = betCode.substring(0, 2)+"01"+betCode.substring(5);
//					sBuffer.append(newString);
//				} else {
//					String newString  = betCode.substring(0, 2)+"01"+betCode.substring(4);
//					sBuffer.append(newString);
//				}
//				betRequest.setBetcode(sBuffer.toString()); //注码
//			} else {
//				String[] split = betCode.split("\\^");
//				for (int i = 0; i < split.length; i++) {
//					String newString  = split[i].substring(0, 2)+"01"+split[i].substring(4);
//					sBuffer.append(newString).append("^");
//				}
//				betRequest.setBetcode(sBuffer.toString()); //注码
//			}
//		} else {
//			betRequest.setBetcode(clientInfo.getBetCode()); //注码
//		}
//		betRequests.add(betRequest);
//		orderRequest.setBetRequests(betRequests);
//		
//		orderRequest.setLotsType(new BigDecimal(CommonUtil.getLotsTypeForHeMai(lotNo, clientInfo.getBetCode()))); //0-单式上传，1-复式，2-胆拖，3-多方案
//		orderRequest.setLotno(clientInfo.getLotNo()); //彩种
//		orderRequest.setBatchcode(clientInfo.getBatchCode()); //期号
//		orderRequest.setLotmulti(new BigDecimal(clientInfo.getLotmulti())); //倍数
//		orderRequest.setAmt(new BigDecimal(clientInfo.getAmount())); //总金额
//		orderRequest.setOneamount(new BigDecimal(clientInfo.getOneAmount())); //单注金额
//		orderRequest.setSubchannel("00092493"); //用户系统
//		
//		CaseLotRequest caseLotRequest = new CaseLotRequest(); //合买信息
//		caseLotRequest.setSafeAmt(Long.parseLong(clientInfo.getSafeAmt().equals("")?"0":clientInfo.getSafeAmt())); //保底金额
//		caseLotRequest.setBuyAmt(Long.parseLong(clientInfo.getBuyAmt())); //购买金额
//		caseLotRequest.setTotalAmt(Long.parseLong(clientInfo.getAmount())); //总金额
//		caseLotRequest.setCommisionRatio(Integer.parseInt(clientInfo.getCommisionRatio())); //提成比例
//		caseLotRequest.setTitle(""); //标题
//		caseLotRequest.setDesc(clientInfo.getDescription()); //描述
//		caseLotRequest.setVisibility(Integer.parseInt(clientInfo.getVisibility())); //是否可见
//		caseLotRequest.setMinAmt(Long.parseLong(clientInfo.getMinAmt())); //最低认购金额
//		caseLotRequest.setStarter(userno); //发起人
//		caseLotRequest.setCaselotinfo("");
//		orderRequest.setCaseLotRequest(caseLotRequest);
//		log.info("发起合买的参数:"+JSONObject.fromObject(orderRequest));
//		String resStr = HttpUtil.sendByPostUtF(url, "body="+JSONObject.fromObject(orderRequest));
//		log.info("发起合买返回resStr:"+resStr);
//		if(Tools.isEmpty(resStr))
//			resStr = Tools.paramError();
//		JSONObject resStrJson = JSONObject.fromObject(resStr);
//		if(resStrJson.getString("errorCode").equals("0")){
//			resJson.put(Constants.ERRORCODE, "0000");
//			resJson.put(Constants.MESSAGE, "发起合买成功");	
//		}else{
//			resJson.put(Constants.ERRORCODE, "9999");
//			resJson.put(Constants.MESSAGE, "发起合买失败");
//		}
//		return resJson.toString();
//	}
//	
//	/**
//	 * 合买撤单
//	 * @param clientInfo
//	 * @return
//	 */
//	public String cancelCase(ClientInfo clientInfo){
//		JSONObject resJson = new JSONObject();
//		String url = ConstantsLottery.URL+"bet/cancelCaselot";
//		String userno = clientInfo.getUserno();
//		if(Tools.isEmpty(userno))
//		  userno = Tools.getUserNo(clientInfo.getPhonenum());
//		StringBuffer paramStr = new StringBuffer();
//		paramStr.append("userno="+userno);
//		paramStr.append("&caseId="+clientInfo.getCaseid());
//		String resStr = HttpUtil.sendByPostUtF(url, paramStr.toString());
//		log.info("resStr合买撤单方案返回结果====="+resStr);
//		if(Tools.isEmpty(resStr))
//			resStr = Tools.paramError();
//		JSONObject resStrJson = JSONObject.fromObject(resStr);
//		if(resStrJson.getString("errorCode").equals("0")){//合买撤单成功
//			resJson.put(Constants.ERRORCODE, "0000");
//			resJson.put(Constants.MESSAGE, "合买撤单成功");
//		}else if(resStrJson.getString("errorCode").equals("500003")){
//			resJson.put(Constants.ERRORCODE, "9999");
//			resJson.put(Constants.MESSAGE, "只有发起人才能取消");
//		}else{
//			resJson.put(Constants.ERRORCODE, "9999");
//			resJson.put(Constants.MESSAGE, "合买撤销异常");
//		}
//		return resJson.toString();
//	}
//	/**
//	 * 合买撤资
//	 * @param clientInfo
//	 * @return
//	 */
//	public String cancelCaseLotBuy(ClientInfo clientInfo){
//		JSONObject resJson = new JSONObject();
//		String url = ConstantsLottery.URL+"bet/cancelCaselotbuy";
//		String userno = clientInfo.getUserno();
//		if(Tools.isEmpty(userno))
//		  userno = Tools.getUserNo(clientInfo.getPhonenum());
//		StringBuffer paramStr = new StringBuffer();
//		paramStr.append("userno="+userno);
//		paramStr.append("&caseId="+clientInfo.getCaseid());
//		String resStr = HttpUtil.sendByPostUtF(url, paramStr.toString());
//		log.info("resStr合买撤资方案返回结果====="+resStr);
//		if(Tools.isEmpty(resStr))
//			resStr = Tools.paramError();
//		JSONObject resStrJson = JSONObject.fromObject(resStr);
//		if(resStrJson.getString("errorCode").equals("0")){//合买撤资成功
//			resJson.put(Constants.ERRORCODE, "0000");
//			resJson.put(Constants.MESSAGE, "合买撤资成功");
//		}else if(resStrJson.getString("errorCode").equals("500009")){
//			resJson.put(Constants.ERRORCODE, "9999");
//			resJson.put(Constants.MESSAGE, "合买发起人不能撤资");
//		}else if(resStrJson.getString("errorCode").equals("500005")){
//			resJson.put(Constants.ERRORCODE, "9999");
//			resJson.put(Constants.MESSAGE, "只有合买本人才能撤资");
//		}else if(resStrJson.getString("errorCode").equals("500010")){
//			resJson.put(Constants.ERRORCODE, "9999");
//			resJson.put(Constants.MESSAGE, "撤资的合买进度大于20%");
//		}else{
//			resJson.put(Constants.ERRORCODE, "9999");
//			resJson.put(Constants.MESSAGE, "合买撤资异常");
//		}
//		return resJson.toString();
//	}
//	
//}
