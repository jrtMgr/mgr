package com.ruyicai.mgr.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruyicai.mgr.service.LotteryService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 竟彩注码解析
 * 
 * @author Administrator
 * 
 */
@Service
public class BetCodeParseJingCaiUtil {

	@Autowired
	LotteryService lotteryService;

	/**
	 * 注码解析
	 * 
	 * @param lotNo
	 * @param userNo
	 * @param orderId
	 * @param multiple
	 * @param isCaseLot
	 * @return
	 * @throws Exception
	 */
	public JSONArray parseBetCode(String lotNo, String orderId, String multiple, boolean isCaseLot) throws Exception {
		JSONArray resultArray = new JSONArray();

		String result = "";
		if (isCaseLot) { // 合买(如果未出票就调用拆票的接口)
			result = lotteryService.getTlotsByOrderidWrapper(orderId);
		} else { // 普通投注
			result = lotteryService.getToltsByOrderId(orderId);
		}
		if (!StringUtil.isEmpty(result)) {
			JSONObject fromObject = JSONObject.fromObject(result);
			if (fromObject != null && !StringUtil.isEmpty(fromObject.getString("errorCode"))
					&& fromObject.getString("errorCode").equals("0")) {
				JSONObject valueObject = fromObject.getJSONObject("value");
				JSONArray list = valueObject.getJSONArray("list");
				if (list != null && list.size() > 0) {
					for (int i = 0; i < list.size(); i++) {
						JSONObject tlotObject = list.getJSONObject(i);
						String betCode = tlotObject.getString("betcode"); // 注码
						String peiLvString = tlotObject.getString("peilu"); // 赔率(20120520*7*001*|1:12.0|3:12.0|^20120520*7*002*|1:12.0|3:12.0|^)
						if (lotNo != null && lotNo.equals("J00001")) { // 竞彩足球胜平负
							// 502@20120409|1|001|3^20120409|1|002|10^
							JSONObject object = new JSONObject();
							object.put("lotName", "竞彩足球胜平负");
							object.put("multiple", multiple); // 倍数
							String[] split1 = betCode.split("@");
							String wanfa = split1[0]; // 玩法
							JSONObject wanfaObject = JingCaiUtil.getJingCaiPlay(wanfa);
							object.put("play", wanfaObject.getString("play"));

							StringBuffer sBuffer = new StringBuffer();
							String[] split2 = split1[1].split("\\^");
							for (String string : split2) {
								String[] split3 = string.split("\\|");
								String day = split3[0];
								String weekid = split3[1];
								String teamid = split3[2];
								String match = lotteryService.getjingcaimatches(lotNo, day, weekid, teamid);
								if (!StringUtil.isEmpty(match)) {
									JSONObject matchObject = JSONObject.fromObject(match);
									if (matchObject != null && !StringUtil.isEmpty(matchObject.getString("errorCode"))
											&& matchObject.getString("errorCode").equals("0")) {
										JSONObject matchValueObject = matchObject.getJSONObject("value");
										if (StringUtil.isEmpty(matchValueObject.getString("matches"))
												|| matchValueObject.getString("matches").equals("null")) {
											continue;
										}
										JSONObject matchesObject = matchValueObject.getJSONObject("matches");
										String team = matchesObject.getString("team");
										String zhu = ""; // 主队
										String ke = ""; // 客队
										if (!StringUtil.isEmpty(team) && !team.trim().equals("null")
												&& team.indexOf(":") > -1) {
											String[] split = team.split(":");
											zhu = split[0];
											ke = split[1];
										}
										// 联赛
										String league = matchesObject.getString("league");
										String id = day + "*" + weekid + "*" + teamid + "*";
										String betContent = JingCaiUtil.getBetContentForJ00001(id, peiLvString,
												split3[3]); // 投注内容
										sBuffer.append(
												JingCaiUtil.getWeekByWeekId(weekid) + " " + teamid + " " + league + " "
														+ zhu + "vs" + ke + "\n您的投注:" + betContent + ";").append("\n");
									}
								}
							}
							if (sBuffer.toString().endsWith("\n")) {
								object.put("betCode", sBuffer.toString().substring(0, sBuffer.toString().length() - 1));
							} else {
								object.put("betCode", sBuffer.toString());
							}
							resultArray.add(object);
						} else if (lotNo != null && lotNo.equals("J00002")) { // 竞彩足球比分
							// 502@20120520|7|001|0542^20120520|7|002|50^
							JSONObject object = new JSONObject();
							object.put("lotName", "竞彩足球比分");
							object.put("multiple", multiple); // 倍数
							String[] split1 = betCode.split("@");
							String wanfa = split1[0]; // 玩法
							JSONObject wanObject = JingCaiUtil.getJingCaiPlay(wanfa);
							object.put("play", wanObject.getString("play"));

							StringBuffer sBuffer = new StringBuffer();
							String[] split2 = split1[1].split("\\^");
							for (String string : split2) {
								String[] split3 = string.split("\\|");
								String day = split3[0];
								String weekid = split3[1];
								String teamid = split3[2];
								String match = lotteryService.getjingcaimatches(lotNo, day, weekid, teamid);
								if (!StringUtil.isEmpty(match)) {
									JSONObject matchObject = JSONObject.fromObject(match);
									if (matchObject != null && !StringUtil.isEmpty(matchObject.getString("errorCode"))
											&& matchObject.getString("errorCode").equals("0")) {
										JSONObject matchValueObject = matchObject.getJSONObject("value");
										if (StringUtil.isEmpty(matchValueObject.getString("matches"))
												|| matchValueObject.getString("matches").equals("null")) {
											continue;
										}
										JSONObject matchesObject = matchValueObject.getJSONObject("matches");
										String team = matchesObject.getString("team");
										String zhu = ""; // 主队
										String ke = ""; // 客队
										if (!StringUtil.isEmpty(team) && !team.trim().equals("null")
												&& team.indexOf(":") > -1) {
											String[] split = team.split(":");
											zhu = split[0];
											ke = split[1];
										}
										// 联赛
										String league = matchesObject.getString("league");
										String id = day + "*" + weekid + "*" + teamid + "*";
										String betContent = JingCaiUtil.getBetContentForJ00002(id, peiLvString,
												split3[3]); // 投注内容
										sBuffer.append(
												JingCaiUtil.getWeekByWeekId(weekid) + " " + teamid + " " + league + " "
														+ zhu + "vs" + ke + "\n您的投注:" + betContent + ";").append("\n");
									}
								}
							}
							if (sBuffer.toString().endsWith("\n")) {
								object.put("betCode", sBuffer.toString().substring(0, sBuffer.toString().length() - 1));
							} else {
								object.put("betCode", sBuffer.toString());
							}
							resultArray.add(object);
						} else if (lotNo != null && lotNo.equals("J00003")) { // 竞彩足球总进球数
							// 500@20120528|1|164|7^20120528|1|165|0^
							JSONObject object = new JSONObject();
							object.put("lotName", "竞彩足球总进球数");
							object.put("multiple", multiple); // 倍数
							String[] split1 = betCode.split("@");
							String wanfa = split1[0]; // 玩法
							JSONObject wanfaObject = JingCaiUtil.getJingCaiPlay(wanfa);
							object.put("play", wanfaObject.getString("play"));

							StringBuffer sBuffer = new StringBuffer();
							String[] split2 = split1[1].split("\\^");
							for (String string : split2) {
								String[] split3 = string.split("\\|");
								String day = split3[0];
								String weekid = split3[1];
								String teamid = split3[2];
								String match = lotteryService.getjingcaimatches(lotNo, day, weekid, teamid);
								if (!StringUtil.isEmpty(match)) {
									JSONObject matchObject = JSONObject.fromObject(match);
									if (matchObject != null && !StringUtil.isEmpty(matchObject.getString("errorCode"))
											&& matchObject.getString("errorCode").equals("0")) {
										JSONObject matchValueObject = matchObject.getJSONObject("value");
										if (StringUtil.isEmpty(matchValueObject.getString("matches"))
												|| matchValueObject.getString("matches").equals("null")) {
											continue;
										}
										JSONObject matchesObject = matchValueObject.getJSONObject("matches");
										String team = matchesObject.getString("team");
										String zhu = ""; // 主队
										String ke = ""; // 客队
										if (!StringUtil.isEmpty(team) && !team.trim().equals("null")
												&& team.indexOf(":") > -1) {
											String[] split = team.split(":");
											zhu = split[0];
											ke = split[1];
										}
										// 联赛
										String league = matchesObject.getString("league");
										String id = day + "*" + weekid + "*" + teamid + "*";
										String betContent = JingCaiUtil.getBetContentForJ00003(id, peiLvString,
												split3[3]); // 投注内容
										sBuffer.append(
												JingCaiUtil.getWeekByWeekId(weekid) + " " + teamid + " " + league + " "
														+ zhu + "vs" + ke + "\n您的投注:" + betContent + ";").append("\n");
									}
								}
							}
							if (sBuffer.toString().endsWith("\n")) {
								object.put("betCode", sBuffer.toString().substring(0, sBuffer.toString().length() - 1));
							} else {
								object.put("betCode", sBuffer.toString());
							}
							resultArray.add(object);
						} else if (lotNo != null && lotNo.equals("J00004")) { // 竞彩足球半全场
							// 504@20120520|7|002|13^20120520|7|005|00^20120520|7|008|03^20120528|1|159|00^
							JSONObject object = new JSONObject();
							object.put("lotName", "竞彩足球半全场");
							object.put("multiple", multiple); // 倍数
							String[] split1 = betCode.split("@");
							String wanfa = split1[0]; // 玩法
							JSONObject wanfaObject = JingCaiUtil.getJingCaiPlay(wanfa);
							object.put("play", wanfaObject.getString("play"));

							StringBuffer sBuffer = new StringBuffer();
							String[] split2 = split1[1].split("\\^");
							for (String string : split2) {
								String[] split3 = string.split("\\|");
								String day = split3[0];
								String weekid = split3[1];
								String teamid = split3[2];
								String match = lotteryService.getjingcaimatches(lotNo, day, weekid, teamid);
								if (!StringUtil.isEmpty(match)) {
									JSONObject matchObject = JSONObject.fromObject(match);
									if (matchObject != null && !StringUtil.isEmpty(matchObject.getString("errorCode"))
											&& matchObject.getString("errorCode").equals("0")) {
										JSONObject matchValueObject = matchObject.getJSONObject("value");
										if (StringUtil.isEmpty(matchValueObject.getString("matches"))
												|| matchValueObject.getString("matches").equals("null")) {
											continue;
										}
										JSONObject matchesObject = matchValueObject.getJSONObject("matches");
										String team = matchesObject.getString("team");
										String zhu = ""; // 主队
										String ke = ""; // 客队
										if (!StringUtil.isEmpty(team) && !team.trim().equals("null")
												&& team.indexOf(":") > -1) {
											String[] split = team.split(":");
											zhu = split[0];
											ke = split[1];
										}
										// 联赛
										String league = matchesObject.getString("league");
										String id = day + "*" + weekid + "*" + teamid + "*";
										String betContent = JingCaiUtil.getBetContentForJ00004(id, peiLvString,
												split3[3]); // 投注内容
										sBuffer.append(
												JingCaiUtil.getWeekByWeekId(weekid) + " " + teamid + " " + league + " "
														+ zhu + "vs" + ke + "\n您的投注:" + betContent + ";").append("\n");
									}
								}
							}
							if (sBuffer.toString().endsWith("\n")) {
								object.put("betCode", sBuffer.toString().substring(0, sBuffer.toString().length() - 1));
							} else {
								object.put("betCode", sBuffer.toString());
							}
							resultArray.add(object);
						} else if (lotNo != null && lotNo.equals("J00005")) { // 竞彩篮球胜负
							// 502@20120520|7|301|0^20120520|7|302|3^
							JSONObject object = new JSONObject();
							object.put("lotName", "竞彩篮球胜负");
							object.put("multiple", multiple); // 倍数
							String[] split1 = betCode.split("@"); // 502@20111121|1|001|31^20111121|1|002|3^
							String wanfa = split1[0]; // 玩法
							JSONObject wanfaObject = JingCaiUtil.getJingCaiPlay(wanfa);
							object.put("play", wanfaObject.getString("play"));

							StringBuffer sBuffer = new StringBuffer();
							String[] split2 = split1[1].split("\\^");
							for (String string : split2) {
								String[] split3 = string.split("\\|");
								String day = split3[0];
								String weekid = split3[1];
								String teamid = split3[2];
								String match = lotteryService.getjingcaimatches(lotNo, day, weekid, teamid);
								if (!StringUtil.isEmpty(match)) {
									JSONObject matchObject = JSONObject.fromObject(match);
									if (matchObject != null && !StringUtil.isEmpty(matchObject.getString("errorCode"))
											&& matchObject.getString("errorCode").equals("0")) {
										JSONObject matchValueObject = matchObject.getJSONObject("value");
										if (StringUtil.isEmpty(matchValueObject.getString("matches"))
												|| matchValueObject.getString("matches").equals("null")) {
											continue;
										}
										JSONObject matchesObject = matchValueObject.getJSONObject("matches");
										String team = matchesObject.getString("team");
										String zhu = ""; // 主队
										String ke = ""; // 客队
										if (!StringUtil.isEmpty(team) && !team.trim().equals("null")
												&& team.indexOf(":") > -1) {
											String[] split = team.split(":");
											zhu = split[0];
											ke = split[1];
										}
										// 联赛
										String league = matchesObject.getString("league");
										String id = day + "*" + weekid + "*" + teamid + "*";
										String betContent = JingCaiUtil.getBetContentForJ00005(id, peiLvString,
												split3[3]); // 投注内容
										sBuffer.append(
												JingCaiUtil.getWeekByWeekId(weekid) + " " + teamid + " " + league + " "
														+ ke + "vs" + zhu + "\n您的投注:" + betContent + ";").append("\n");
									}
								}
							}
							if (sBuffer.toString().endsWith("\n")) {
								object.put("betCode", sBuffer.toString().substring(0, sBuffer.toString().length() - 1));
							} else {
								object.put("betCode", sBuffer.toString());
							}
							resultArray.add(object);
						} else if (lotNo != null && lotNo.equals("J00006")) { // 竞彩篮球让分胜负
							// 502@20120520|7|301|03^20120520|7|302|3^
							JSONObject object = new JSONObject();
							object.put("lotName", "竞彩篮球让分胜负");
							object.put("multiple", multiple); // 倍数
							String[] split1 = betCode.split("@"); // 502@20120409|1|301|0^20120409|1|302|3^
							String wanfa = split1[0]; // 玩法
							JSONObject wanfaObject = JingCaiUtil.getJingCaiPlay(wanfa);
							object.put("play", wanfaObject.getString("play"));

							StringBuffer sBuffer = new StringBuffer();
							String[] split2 = split1[1].split("\\^");
							for (String string : split2) {
								String[] split3 = string.split("\\|");
								String day = split3[0];
								String weekid = split3[1];
								String teamid = split3[2];
								String match = lotteryService.getjingcaimatches(lotNo, day, weekid, teamid);
								if (!StringUtil.isEmpty(match)) {
									JSONObject matchObject = JSONObject.fromObject(match);
									if (matchObject != null && !StringUtil.isEmpty(matchObject.getString("errorCode"))
											&& matchObject.getString("errorCode").equals("0")) {
										JSONObject matchValueObject = matchObject.getJSONObject("value");
										if (StringUtil.isEmpty(matchValueObject.getString("matches"))
												|| matchValueObject.getString("matches").equals("null")) {
											continue;
										}
										JSONObject matchesObject = matchValueObject.getJSONObject("matches");
										String team = matchesObject.getString("team");
										String zhu = ""; // 主队
										String ke = ""; // 客队
										if (!StringUtil.isEmpty(team) && !team.trim().equals("null")
												&& team.indexOf(":") > -1) {
											String[] split = team.split(":");
											zhu = split[0];
											ke = split[1];
										}
										String id = day + "*" + weekid + "*" + teamid + "*";
										// 联赛
										String league = matchesObject.getString("league");
										JSONObject codeObject = JingCaiUtil.getBetContentForJ00006(id, peiLvString,
												split3[3]); // 投注内容
										String betContent = codeObject.getString("betContent");
										String letPoint = codeObject.getString("letPoint");
										/*
										 * sBuffer.append(JingCaiUtil.
										 * getWeekByWeekId
										 * (weekid)+" "+teamid+" "+league+" "
										 * +ke+"("+letPoint+")"+zhu+"\n您的投注:"+
										 * betContent+";").append("\n");
										 */
										sBuffer.append(JingCaiUtil.getWeekByWeekId(weekid) + " " + teamid + " "
												+ league + " ");
										if (letPoint != null && !letPoint.equals("")) {
											sBuffer.append(ke + "(" + letPoint + ")" + zhu);
										} else {
											sBuffer.append(ke + "vs" + zhu);
										}
										sBuffer.append("\n您的投注:" + betContent + ";").append("\n");
									}
								}
							}
							if (sBuffer.toString().endsWith("\n")) {
								object.put("betCode", sBuffer.toString().substring(0, sBuffer.toString().length() - 1));
							} else {
								object.put("betCode", sBuffer.toString());
							}
							resultArray.add(object);
						} else if (lotNo != null && lotNo.equals("J00007")) { // 竞彩篮球胜分差
							// 526@20120520|7|301|13^20120528|1|452|05^20120528|1|454|12^
							JSONObject object = new JSONObject();
							object.put("lotName", "竞彩篮球胜分差");
							object.put("multiple", multiple); // 倍数
							String[] split1 = betCode.split("@"); // 502@20120401|7|301|0205^20120401|7|302|12^
							String wanfa = split1[0]; // 玩法
							JSONObject wanfaObject = JingCaiUtil.getJingCaiPlay(wanfa);
							object.put("play", wanfaObject.getString("play"));

							StringBuffer sBuffer = new StringBuffer();
							String[] split2 = split1[1].split("\\^");
							for (String string : split2) {
								String[] split3 = string.split("\\|");
								String day = split3[0];
								String weekid = split3[1];
								String teamid = split3[2];
								String match = lotteryService.getjingcaimatches(lotNo, day, weekid, teamid);
								if (!StringUtil.isEmpty(match)) {
									JSONObject matchObject = JSONObject.fromObject(match);
									if (matchObject != null && !StringUtil.isEmpty(matchObject.getString("errorCode"))
											&& matchObject.getString("errorCode").equals("0")) {
										JSONObject matchValueObject = matchObject.getJSONObject("value");
										if (StringUtil.isEmpty(matchValueObject.getString("matches"))
												|| matchValueObject.getString("matches").equals("null")) {
											continue;
										}
										JSONObject matchesObject = matchValueObject.getJSONObject("matches");
										String team = matchesObject.getString("team");
										String zhu = ""; // 主队
										String ke = ""; // 客队
										if (!StringUtil.isEmpty(team) && !team.trim().equals("null")
												&& team.indexOf(":") > -1) {
											String[] split = team.split(":");
											zhu = split[0];
											ke = split[1];
										}
										String id = day + "*" + weekid + "*" + teamid + "*";
										// 联赛
										String league = matchesObject.getString("league");
										String betContent = JingCaiUtil.getBetContentForJ00007(id, peiLvString,
												split3[3]); // 投注内容
										sBuffer.append(
												JingCaiUtil.getWeekByWeekId(weekid) + " " + teamid + " " + league + " "
														+ ke + "vs" + zhu + "\n您的投注:" + betContent + ";").append("\n");
									}
								}
							}
							if (sBuffer.toString().endsWith("\n")) {
								object.put("betCode", sBuffer.toString().substring(0, sBuffer.toString().length() - 1));
							} else {
								object.put("betCode", sBuffer.toString());
							}
							resultArray.add(object);
						} else if (lotNo != null && lotNo.equals("J00008")) { // 竞彩篮球大小分
							// 502@20120520|7|301|12^20120520|7|302|12^
							JSONObject object = new JSONObject();
							object.put("lotName", "竞彩篮球大小分");
							object.put("multiple", multiple); // 倍数
							String[] split1 = betCode.split("@"); // 502@20120406|5|472|1^20120406|5|473|2^20120406|5|477|1^20120406|5|478|1^
							String wanfa = split1[0]; // 玩法
							JSONObject wanfaObject = JingCaiUtil.getJingCaiPlay(wanfa);
							object.put("play", wanfaObject.getString("play"));

							StringBuffer sBuffer = new StringBuffer();
							String[] split2 = split1[1].split("\\^");
							for (String string : split2) {
								String[] split3 = string.split("\\|");
								String day = split3[0];
								String weekid = split3[1];
								String teamid = split3[2];
								String match = lotteryService.getjingcaimatches(lotNo, day, weekid, teamid);
								if (!StringUtil.isEmpty(match)) {
									JSONObject matchObject = JSONObject.fromObject(match);
									if (matchObject != null && !StringUtil.isEmpty(matchObject.getString("errorCode"))
											&& matchObject.getString("errorCode").equals("0")) {
										JSONObject matchValueObject = matchObject.getJSONObject("value");
										if (StringUtil.isEmpty(matchValueObject.getString("matches"))
												|| matchValueObject.getString("matches").equals("null")) {
											continue;
										}
										JSONObject matchesObject = matchValueObject.getJSONObject("matches");
										String team = matchesObject.getString("team");
										String zhu = ""; // 主队
										String ke = ""; // 客队
										if (!StringUtil.isEmpty(team) && !team.trim().equals("null")
												&& team.indexOf(":") > -1) {
											String[] split = team.split(":");
											zhu = split[0];
											ke = split[1];
										}
										String id = day + "*" + weekid + "*" + teamid + "*";
										// 联赛
										String league = matchesObject.getString("league");
										JSONObject codeObject = JingCaiUtil.getBetContentForJ00008(id, peiLvString,
												split3[3]); // 投注内容
										String betContent = codeObject.getString("betContent");
										String basePoint = codeObject.getString("basePoint");
										/*
										 * sBuffer.append(JingCaiUtil.
										 * getWeekByWeekId
										 * (weekid)+" "+teamid+" "+league+" "
										 * +ke+"("+basePoint+")"+zhu+"\n您的投注:"+
										 * betContent+";").append("\n");
										 */
										sBuffer.append(JingCaiUtil.getWeekByWeekId(weekid) + " " + teamid + " "
												+ league + " ");
										if (basePoint != null && !basePoint.equals("")) {
											sBuffer.append(ke + "(" + basePoint + ")" + zhu);
										} else {
											sBuffer.append(ke + "vs" + zhu);
										}
										sBuffer.append("\n您的投注:" + betContent + ";").append("\n");
									}
								}
							}
							if (sBuffer.toString().endsWith("\n")) {
								object.put("betCode", sBuffer.toString().substring(0, sBuffer.toString().length() - 1));
							} else {
								object.put("betCode", sBuffer.toString());
							}
							resultArray.add(object);
						} else { // 未知彩种
							JSONObject object = new JSONObject();
							object.put("lotName", "未知");
							object.put("multiple", multiple); // 倍数
							object.put("play", "未知");
							object.put("betCode", "");
							resultArray.add(object);
						}
					}
				}
			}
		}

		return resultArray;
	}

}
