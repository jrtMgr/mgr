package com.ruyicai.mgr.util;

import org.springframework.stereotype.Service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 注码解析(解析订单的orderinfo)
 * 
 * @author Administrator
 * 
 */
@Service
public class BetCodeParseOrderInfoUtil {

	public JSONArray parseBetCode(String lotNo, String orderinfo, String multiple) {
		JSONArray resultArray = new JSONArray();

		if (lotNo != null && lotNo.equals("F47104")) { // 双色球
			// 1001*06131521242533~14^_1_200_1400!1001*11131517253133~05^_1_200_1400
			JSONObject object = new JSONObject();
			object.put("lotName", "双色球");
			object.put("multiple", multiple); // 倍数
			String[] orderInfos = orderinfo.split("!");
			for (String orderInfo : orderInfos) {
				String[] split = orderInfo.split("_");
				String code = split[0];
				String[] split2 = code.split("\\^");
				for (String string : split2) {
					if (string.length() > 4) {
						String play = "0"; // 投注类型
						if (string.trim().substring(0, 2).equals("00")) {
							play = "DS"; // 单式
							object.put("play", "单式");
						} else if (string.trim().substring(0, 2).equals("10")
								|| string.trim().substring(0, 2).equals("20")
								|| string.trim().substring(0, 2).equals("30")) {
							play = "FS"; // 复式
							object.put("play", "复式");
						} else if (string.trim().substring(0, 2).equals("40")
								|| string.trim().substring(0, 2).equals("50")) { // 胆拖
							play = "DT";
							object.put("play", "胆拖");
						}
						if (play.trim().equals("DS")) { // 单式
							String red = LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
									.getStringArrayFromString(string.substring(4, string.indexOf('~'))));
							String blue = string.substring(string.indexOf('~') + 1);
							object.put("betCode", "红球:" + red + "蓝球:" + blue); // 解析后的注码
						} else if (play.trim().equals("FS")) {
							String red = LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
									.getStringArrayFromString(string.substring(string.indexOf('*') + 1,
											string.indexOf('~'))));
							String blue = LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
									.getStringArrayFromString(string.substring(string.indexOf('~') + 1)));
							object.put("betCode", "红球:\n" + red + "蓝球:\n" + blue); // 解析后的注码
						} else if (play.trim().equals("DT")) { // 胆拖
							String redDanma = LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
									.getStringArrayFromString(string.substring(4, string.indexOf("*"))));
							String redTuoma = LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
									.getStringArrayFromString(string.substring(string.indexOf("*") + 1,
											string.indexOf("~"))));
							String blue = LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
									.getStringArrayFromString(string.substring(string.indexOf("~") + 1)));
							object.put("betCode", "红球胆码:\n" + redDanma + "红球拖码:\n" + redTuoma + "蓝球:\n" + blue);
						} else {
							object.put("multiple", multiple); // 倍数
							object.put("play", "未知");
							object.put("betCode", string);
						}
					}
					resultArray.add(object);
				}
			}
		} else if (lotNo != null && lotNo.equals("F47103")) { // 福彩3D
			// 320109000102030405060708^_1_200_16800
			JSONObject object = new JSONObject();
			object.put("lotName", "福彩3D");
			object.put("multiple", multiple); // 倍数
			String[] orderInfos = orderinfo.split("!");
			for (String orderInfo : orderInfos) {
				String[] split = orderInfo.split("_");
				String code = split[0];
				if (code.substring(0, 2).equals("20")) { // 直选复式
															// 20020102^0106^0100^
					object.put("multiple", orderinfo.substring(2, 4)); // 倍数
					// play = "ZHF";
					object.put("play", "直选复式");
					String[] str = code.split("\\^");
					String bai = LotteryAlgorithmUtil.removeZero3D(str[0].substring(6));
					String shi = LotteryAlgorithmUtil.removeZero3D(str[1].substring(2));
					String ge = LotteryAlgorithmUtil.removeZero3D(str[2].substring(2));
					object.put("betCode", "百位:" + bai + " 十位:" + shi + " 个位:" + ge);
					resultArray.add(object);
					continue;
				} else {
					String[] split2 = code.split("\\^");
					for (String string : split2) {
						String play = "0"; // 投注类型
						if (string.substring(0, 2).equals("00")) { // 单选单式
							play = "DXD";
							object.put("play", "单选单式");
						} else if (string.substring(0, 2).equals("01")) { // 组3单式
							play = "Z3D";
							object.put("play", "组3单式");
						} else if (string.substring(0, 2).equals("02")) { // 组6单式
							play = "Z6D";
							object.put("play", "组6单式");
						} else if (string.substring(0, 2).equals("31")) { // 组3复式
							play = "Z3F";
							object.put("play", "组3复式");
						} else if (string.substring(0, 2).equals("32")) { // 组6复式
							play = "Z6F";
							object.put("play", "组6复式");
						} else if (string.substring(0, 2).equals("34")) { // 单选单复式
							play = "DXDF";
							object.put("play", "单选单复式");
						} else if (string.substring(0, 2).equals("54")) { // 胆拖
							play = "DT";
							object.put("play", "胆拖");
						} else if (string.substring(0, 2).equals("10")) { // 直选和值
							play = "ZXHZ";
							object.put("play", "直选和值");
						} else if (string.substring(0, 2).equals("11")) { // 组3和值
							play = "Z3HZ";
							object.put("play", "组3和值");
						} else if (string.substring(0, 2).equals("12")) { // 组6和值
							play = "Z6HZ";
							object.put("play", "组6和值");
						}
						if (play.trim().equals("DXD") || play.trim().equals("Z3D") || play.trim().equals("Z6D")) { // 单式
							String bet_code = LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
									.getStringArrayFromString(string.substring(4, string.length())));
							object.put("betCode", bet_code);
						} else if (play.trim().equals("Z3F") || play.trim().equals("Z6F") || play.trim().equals("DXDF")) { // 复式
							String bet_code = LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
									.getStringArrayFromString(string.substring(6)));
							object.put("betCode", bet_code);
						} else if (play.trim().equals("DT")) {
							String danMa = LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
									.getStringArrayFromString(string.substring(4, string.indexOf("*")))); // 胆码
							String tuoMa = LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
									.getStringArrayFromString(string.substring(string.indexOf("*") + 1))); // 拖码
							object.put("betCode", "胆码:" + danMa + "拖码:" + tuoMa);
						} else if (play.trim().equals("ZXHZ") || play.trim().equals("Z3HZ")
								|| play.trim().equals("Z6HZ")) {
							String bet_code = LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
									.getStringArrayFromString(string.substring(4, string.length())));
							object.put("betCode", bet_code);
						} else {
							object.put("multiple", multiple); // 倍数
							object.put("play", "未知");
							object.put("betCode", string);
						}
						resultArray.add(object);
					}
				}
			}
		} else if (lotNo != null && lotNo.equals("F47102")) { // 七乐彩
			// 000101121418202329^_2_200_200!1001*040512131418192021222327282930^_2_200_1287000
			JSONObject object = new JSONObject();
			object.put("lotName", "七乐彩");
			object.put("multiple", multiple); // 倍数
			String[] orderInfos = orderinfo.split("!");
			for (String orderInfo : orderInfos) {
				String[] split = orderInfo.split("_");
				String code = split[0];
				String[] split2 = code.split("\\^");
				for (String string : split2) {
					if (string.substring(0, 2).equals("00")) { // 单式
						object.put("play", "单式");
						String bet_code = LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
								.getStringArrayFromString(string.substring(4)));
						object.put("betCode", bet_code);
					} else if (string.substring(0, 2).equals("10")) { // 复式
						object.put("play", "复式");
						String bet_code = LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
								.getStringArrayFromString(string.substring(5)));
						object.put("betCode", bet_code);
					} else if (string.substring(0, 2).equals("20")) { // 胆拖
						object.put("play", "胆拖");
						String danMa = LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
								.getStringArrayFromString(string.substring(4, string.indexOf("*")))); // 胆码
						String tuoMa = LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
								.getStringArrayFromString(string.substring(string.indexOf("*") + 1))); // 拖码
						object.put("betCode", "胆码:" + danMa + "\n拖码:" + tuoMa);
					} else {
						object.put("multiple", multiple); // 倍数
						object.put("play", "未知");
						object.put("betCode", string);
					}
					resultArray.add(object);
				}
			}
		} else if (lotNo != null && lotNo.equals("T01001")) { // 超级大乐透
			// 12 21 30$11 13 18 19 22-04$02 10 11_2_200_6000
			JSONObject object = new JSONObject();
			object.put("lotName", "超级大乐透");
			object.put("multiple", multiple); // 倍数
			String[] orderInfos = orderinfo.split("!");
			for (String orderInfo : orderInfos) {
				String[] split = orderInfo.split("_");
				String code = split[0];
				String[] split2 = code.split(";");
				for (String string : split2) {
					if (string.indexOf("-") > -1) { // 单式或复式或胆拖
						if (string.indexOf("$") > -1) { // 胆拖
							object.put("play", "胆拖");
							String[] qianHou = string.split("-");
							String qianQuDanMa = ""; // 前驱胆码
							String qianQuTuoMa = ""; // 前驱拖码
							String houQuDanMa = ""; // 后驱胆码
							String houQuTuoMa = ""; // 后驱拖码
							// 前区
							if (qianHou[0].indexOf("$") > -1) {
								String[] qianQu = qianHou[0].split("\\$");
								qianQuDanMa = qianQu[0].replaceAll(" ", ","); // 前驱胆码
								qianQuTuoMa = qianQu[1].replaceAll(" ", ","); // 前驱拖码
							} else {
								qianQuTuoMa = qianHou[0].replace(" ", ",");
							}
							// 后区
							if (qianHou[1].indexOf("$") > -1) {
								String[] houQu = qianHou[1].split("\\$");
								houQuDanMa = houQu[0].replaceAll(" ", ",");
								houQuTuoMa = houQu[1].replaceAll(" ", ",");
							} else {
								houQuTuoMa = qianHou[1].replaceAll(" ", ",");
							}
							object.put("betCode", "前区胆码:" + qianQuDanMa + "\n前区拖码:" + qianQuTuoMa + "\n后区胆码:"
									+ houQuDanMa + "\n后区拖码:" + houQuTuoMa);
						} else { // 单式或复式
							String[] qianHou = string.split("-");
							String[] qian = qianHou[0].split(" ");
							String[] hou = qianHou[1].split(" ");
							if (qian.length == 5 && hou.length == 2) {
								object.put("play", "单式");
							} else {
								object.put("play", "复式");
							}
							object.put("betCode",
									qianHou[0].replaceAll(" ", ",") + "+" + qianHou[1].replaceAll(" ", ","));
						}
					} else { // 生肖乐
						String[] split3 = string.split(" ");
						if (split3.length == 2) {
							object.put("play", "生肖乐单式");
						} else {
							object.put("play", "生肖乐复式");
						}
						object.put("betCode", string.replaceAll(" ", ","));
					}
					resultArray.add(object);
				}
			}
		} else if (lotNo != null && lotNo.equals("T01002")) { // 排列三
			// S1|11_2_200_13800!1|4,16,5_2_200_400
			JSONObject object = new JSONObject();
			object.put("lotName", "排列三");
			object.put("multiple", multiple); // 倍数
			String[] orderInfos = orderinfo.split("!");
			for (String orderInfo : orderInfos) {
				String[] split = orderInfo.split("_");
				String code = split[0];
				String[] split2 = code.split(";");

				for (String string : split2) {
					String[] split3 = string.split("\\|");
					if (split3[0].equals("1")) { // 直选
						object.put("play", "直选");
					} else if (split3[0].equals("6")) { // 组选
						object.put("play", "组选");
					} else if (split3[0].equals("S1")) { // 直选和值
						object.put("play", "直选和值");
					} else if (split3[0].equals("S9")) { // 组选和值
						object.put("play", "组选和值");
					} else if (split3[0].equals("S3")) { // 组3和值
						object.put("play", "组3和值");
					} else if (split3[0].equals("S6")) { // 组6和值
						object.put("play", "组6和值");
					} else if (split3[0].equals("F3")) { // 组3包号
						object.put("play", "组3包号");
					} else if (split3[0].equals("F6")) { // 组6包号
						object.put("play", "组6包号");
					} else {
						object.put("play", "未知");
					}
					object.put("betCode", split3[1]);
					resultArray.add(object);
				}

			}
		} else if (lotNo != null && lotNo.equals("T01007")) { // 时时彩
			// 5D|012345678,123456789,125679,01349,0126_2_200_1944000
			JSONObject object = new JSONObject();
			object.put("lotName", "时时彩");
			object.put("multiple", multiple); // 倍数
			String[] orderInfos = orderinfo.split("!");
			for (String orderInfo : orderInfos) {
				String[] split = orderInfo.split("_");
				String code = split[0];
				String[] split2 = code.split(";");
				for (String string : split2) {
					String[] split3 = string.split("\\|");
					if (split3[0].equals("5D")) { // 五星
						object.put("play", "五星");
					} else if (split3[0].equals("3D")) { // 三星
						object.put("play", "三星");
					} else if (split3[0].equals("2D")) { // 二星
						object.put("play", "二星");
					} else if (split3[0].equals("1D")) { // 一星
						object.put("play", "一星");
					} else if (split3[0].equals("5F")) { // 五星复选
						object.put("play", "五星复选");
					} else if (split3[0].equals("5T")) { // 五星通选
						object.put("play", "五星通选");
					} else if (split3[0].equals("3F")) { // 三星复选
						object.put("play", "三星复选");
					} else if (split3[0].equals("2F")) { // 二星复选
						object.put("play", "二星复选");
					} else if (split3[0].equals("H2")) { // 二星和值
						object.put("play", "二星和值");
					} else if (split3[0].equals("S2")) { // 二星组选和值
						object.put("play", "二星组选和值");
					} else if (split3[0].equals("DD")) { // 大小单双
						object.put("play", "大小单双");
						String shi = split3[1].substring(0, 1); // 大小
						String ge = split3[1].substring(1, 2); // 单双
						String shiStr = "";
						if (shi.equals("2")) {
							shiStr = "大";
						} else if (shi.equals("1")) {
							shiStr = "小";
						} else if (shi.equals("5")) {
							shiStr = "单";
						} else if (shi.equals("4")) {
							shiStr = "双";
						}
						String geStr = "";
						if (ge.equals("2")) {
							geStr = "大";
						} else if (ge.equals("1")) {
							geStr = "小";
						} else if (ge.equals("5")) {
							geStr = "单";
						} else if (ge.equals("4")) {
							geStr = "双";
						}
						object.put("betCode", "十位:" + shiStr + "|个位:" + geStr);
						resultArray.add(object);
						continue;
					} else if (split3[0].equals("Z2")) { // 二星组选
						object.put("play", "二星组选");
					} else if (split3[0].equals("F2")) { // 二星组选复式
						object.put("play", "二星组选复式");
					} else {
						object.put("play", "未知");
					}
					object.put("betCode", split3[1]);
					resultArray.add(object);
				}
			}
		} else if (lotNo != null && lotNo.equals("T01009")) { // 七星彩
			// 3,6,6,5,7,4,7;_1_200_200!9,3,1,9,2,5,9;_1_200_200!9,9,0,4,4,1,5;_1_200_200!7,1,3,5,8,9,1;_1_200_200!5,8,9,0,9,4,7;_1_200_200
			JSONObject object = new JSONObject();
			object.put("lotName", "七星彩");
			object.put("multiple", multiple); // 倍数
			String[] orderInfos = orderinfo.split("!");
			for (String orderInfo : orderInfos) {
				String[] split = orderInfo.split("_");
				String code = split[0];
				String[] split2 = code.split(";");
				for (String string : split2) {
					if (string.indexOf(",") > -1) {
						boolean isD = true;
						String[] split3 = string.split(",");
						for (int i = 0; i < split3.length; i++) {
							if (split3[i].length() > 1) {
								isD = false;
								break;
							}
						}
						if (isD) {
							object.put("play", "单式");
						} else {
							object.put("play", "复式");
						}
					} else {
						object.put("play", "单式");
					}
					object.put("betCode", string);
					resultArray.add(object);
				}
			}
		} else if (lotNo != null && lotNo.equals("T01011")) { // 排列五
			// 4,5,9,3,4;_1_200_200!3,8,5,1,1;_1_200_200!8,9,1,6,4;_1_200_200!1,1,6,5,6;_1_200_200!0,6,7,1,8;_1_200_200
			JSONObject object = new JSONObject();
			object.put("lotName", "排列五");
			object.put("multiple", multiple); // 倍数
			String[] orderInfos = orderinfo.split("!");
			for (String orderInfo : orderInfos) {
				String[] split = orderInfo.split("_");
				String code = split[0];
				String[] split2 = code.split(";");
				for (String string : split2) {
					if (string.indexOf(",") > -1) {
						boolean isD = true;
						String[] split3 = string.split(",");
						for (int i = 0; i < split3.length; i++) {
							if (split3[i].length() > 1) {
								isD = false;
								break;
							}
						}
						if (isD) {
							object.put("play", "单式");
						} else {
							object.put("play", "复式");
						}
					} else {
						object.put("play", "单式");
					}
					object.put("betCode", string);
					resultArray.add(object);
				}
			}
		} else if (lotNo != null && lotNo.equals("T01010")) { // 十一选五
			// R8|01 02 06 07 08 09 10 11_1_200_200!R8|02 04 05 06 07 08 10
			// 11_1_200_200!R8|01 02 03 04 05 07 08 09_1_200_200
			JSONObject object = new JSONObject();
			object.put("lotName", "十一选五");
			object.put("multiple", multiple); // 倍数
			String[] orderInfos = orderinfo.split("!");
			for (String orderInfo : orderInfos) {
				String[] split = orderInfo.split("_");
				String code = split[0];
				String[] split2 = code.split(";");

				for (String string : split2) {
					if (code.indexOf("$") > -1) { // 胆拖
						if (code.indexOf("|") > -1) {
							String[] split3 = string.split("\\|");
							if (split3[0].equals("R2")) { // 任选二胆拖
								object.put("play", "任选二胆拖");
							} else if (split3[0].equals("R3")) { // 任选三胆拖
								object.put("play", "任选三胆拖");
							} else if (split3[0].equals("R4")) { // 任选四胆拖
								object.put("play", "任选四胆拖");
							} else if (split3[0].equals("R5")) { // 任选五胆拖
								object.put("play", "任选五胆拖");
							} else if (split3[0].equals("R6")) { // 任选六胆拖
								object.put("play", "任选六胆拖");
							} else if (split3[0].equals("R7")) { // 任选七胆拖
								object.put("play", "任选七胆拖");
							} else if (split3[0].equals("R8")) { // 任选八胆拖
								object.put("play", "任选八胆拖");
							} else if (split3[0].equals("Q2")) { // 选前二直选胆拖
								object.put("play", "选前二直选胆拖");
							} else if (split3[0].equals("Q3")) { // 选前三直选胆拖
								object.put("play", "选前三直选胆拖");
							} else if (split3[0].equals("Z2")) { // 选前二组选胆拖
								object.put("play", "选前二组选胆拖");
							} else if (split3[0].equals("Z3")) { // 选前三组选胆拖
								object.put("play", "选前三组选胆拖");
							} else {
								object.put("play", "未知");
							}
							String[] danTuo = split3[1].split("\\$");
							object.put("betCode", "胆码:" + danTuo[0] + " 拖码:" + danTuo[1]);
						}
					} else {
						if (code.indexOf("|") > -1) {
							String[] split3 = string.split("\\|");
							if (split3[0].equals("R1")) { // 任选一
								object.put("play", "任选一");
							} else if (split3[0].equals("R2")) { // 任选二
								object.put("play", "任选二");
							} else if (split3[0].equals("R3")) { // 任选三
								object.put("play", "任选三");
							} else if (split3[0].equals("R4")) { // 任选四
								object.put("play", "任选四");
							} else if (split3[0].equals("R5")) { // 任选五
								object.put("play", "任选五");
							} else if (split3[0].equals("R6")) { // 任选六
								object.put("play", "任选六");
							} else if (split3[0].equals("R7")) { // 任选七
								object.put("play", "任选七");
							} else if (split3[0].equals("R8")) { // 任选八
								object.put("play", "任选八");
							} else if (split3[0].equals("Q2")) { // 选前二直选
								object.put("play", "选前二直选");
							} else if (split3[0].equals("Q3")) { // 选前三直选
								object.put("play", "选前三直选");
							} else if (split3[0].equals("Z2")) { // 选前二组选
								object.put("play", "选前二组选");
							} else if (split3[0].equals("Z3")) { // 选前三组选
								object.put("play", "选前三组选");
							} else {
								object.put("play", "未知");
							}
							object.put("betCode", split3[1]);
						}
					}
					resultArray.add(object);
				}
			}
		} else if (lotNo != null && lotNo.equals("T01012")) { // 十一运夺金
			// 111@0211^_1_200_200!102@*020411^_1_200_600
			JSONObject object = new JSONObject();
			object.put("lotName", "十一运夺金");
			object.put("multiple", multiple); // 倍数
			String[] orderInfos = orderinfo.split("!");
			for (String orderInfo : orderInfos) {
				String[] split = orderInfo.split("_");
				String code = split[0];

				String[] split1 = code.split("@"); // 102@*02040511^
				String wanfa = split1[0]; // 玩法
				if (wanfa != null && wanfa.trim().equals("101")) { // 任选一复式
					object.put("play", "任选一复式");
					object.put("betCode", convertBetCodeFor11YDJ_FS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("102")) { // 任选二复式
					object.put("play", "任选二复式");
					object.put("betCode", convertBetCodeFor11YDJ_FS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("103")) { // 任选三复式
					object.put("play", "任选三复式");
					object.put("betCode", convertBetCodeFor11YDJ_FS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("104")) { // 任选四复式
					object.put("play", "任选四复式");
					object.put("betCode", convertBetCodeFor11YDJ_FS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("105")) { // 任选五复式
					object.put("play", "任选五复式");
					object.put("betCode", convertBetCodeFor11YDJ_FS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("106")) { // 任选六复式
					object.put("play", "任选六复式");
					object.put("betCode", convertBetCodeFor11YDJ_FS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("107")) { // 任选七复式
					object.put("play", "任选七复式");
					object.put("betCode", convertBetCodeFor11YDJ_FS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("108")) { // 选前二组选复式
					object.put("play", "选前二组选复式");
					object.put("betCode", convertBetCodeFor11YDJ_FS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("109")) { // 选前三组选复式
					object.put("play", "选前三组选复式");
					object.put("betCode", convertBetCodeFor11YDJ_FS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("111")) { // 任选二单式
					object.put("play", "任选二单式");
					object.put("betCode", convertBetCodeFor11YDJ_DS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("112")) { // 任选三单式
					object.put("play", "任选三单式");
					object.put("betCode", convertBetCodeFor11YDJ_DS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("113")) { // 任选四单式
					object.put("play", "任选四单式");
					object.put("betCode", convertBetCodeFor11YDJ_DS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("114")) { // 任选五单式
					object.put("play", "任选五单式");
					object.put("betCode", convertBetCodeFor11YDJ_DS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("115")) { // 任选六单式
					object.put("play", "任选六单式");
					object.put("betCode", convertBetCodeFor11YDJ_DS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("116")) { // 任选七单式
					object.put("play", "任选七单式");
					object.put("betCode", convertBetCodeFor11YDJ_DS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("117")) { // 任选八单式
					object.put("play", "任选八单式");
					object.put("betCode", convertBetCodeFor11YDJ_DS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("121")) { // 任选二胆拖
					object.put("play", "任选二胆拖");
					object.put("betCode", convertBetCodeFor11YDJ_DT(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("131")) { // 选前二组选单式
					object.put("play", "选前二组选单式");
					object.put("betCode", convertBetCodeFor11YDJ_DS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("151")) { // 选前三组选单式
					object.put("play", "选前三组选单式");
					object.put("betCode", convertBetCodeFor11YDJ_DS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("141")) { // 选前二直选单式
					object.put("play", "选前二直选单式");
					object.put("betCode", convertBetCodeFor11YDJ_DS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("142")) { // 选前二直选定位复式
					object.put("play", "选前二直选定位复式");
					object.put("betCode", convertBetCodeFor11YDJ_DWFS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("161")) { // 选前三直选单式
					object.put("play", "选前三直选单式");
					object.put("betCode", convertBetCodeFor11YDJ_DS(split1[1]));
				} else if (wanfa != null && wanfa.trim().equals("162")) { // 选前三直选定位复式
					object.put("play", "选前三直选定位复式");
					object.put("betCode", convertBetCodeFor11YDJ_DWFS(split1[1]));
				} else {
					object.put("play", "未知");
					object.put("betCode", split1[1]);
				}
				resultArray.add(object);
			}
		} else if (lotNo != null && lotNo.equals("T01003")) { // 足球胜负彩
			// 3,1,0,3,1,0,1,0,3,1,0,3,1,0_1_200_200
			JSONObject object = new JSONObject();
			object.put("lotName", "足球胜负彩");
			object.put("multiple", multiple); // 倍数
			String[] orderInfos = orderinfo.split("!");
			for (String orderInfo : orderInfos) {
				String[] split = orderInfo.split("_");
				String code = split[0];

				if (code.indexOf(",") > -1) {
					boolean isD = true;
					String[] split2 = code.split(",");
					for (int i = 0; i < split2.length; i++) {
						if (split2[i].length() > 1) {
							isD = false;
							break;
						}
					}
					if (isD) {
						object.put("play", "单式");
					} else {
						object.put("play", "复式");
					}
				} else {
					object.put("play", "单式");
				}
				object.put("betCode", code);
				resultArray.add(object);
			}
		} else if (lotNo != null && lotNo.equals("T01004")) { // 足球任九场
			// 3,1,3,1,0,1,3,0,3,#,#,#,#,#_1_200_200
			JSONObject object = new JSONObject();
			object.put("lotName", "足球任九场");
			object.put("multiple", multiple); // 倍数
			String[] orderInfos = orderinfo.split("!");
			for (String orderInfo : orderInfos) {
				String[] split = orderInfo.split("_");
				String code = split[0];

				if (code.indexOf("$") > -1) {
					object.put("play", "胆拖");
				} else {
					if (code.indexOf(",") > -1) {
						String[] split2 = code.split(",");
						boolean isD = true;
						for (int i = 0; i < split2.length; i++) {
							if (split2[i].length() > 1) {
								isD = false;
								break;
							}
						}
						if (isD) {
							object.put("play", "单式");
						} else {
							object.put("play", "复式");
						}
					} else {
						object.put("play", "单式");
					}
				}
				object.put("betCode", code);
				resultArray.add(object);
			}
		} else if (lotNo != null && lotNo.equals("T01005")) { // 足球进球彩
			// 0,1,2,1,2,1,1,2_1_200_200
			JSONObject object = new JSONObject();
			object.put("lotName", "足球进球彩");
			object.put("multiple", multiple); // 倍数
			String[] orderInfos = orderinfo.split("!");
			for (String orderInfo : orderInfos) {
				String[] split = orderInfo.split("_");
				String code = split[0];

				if (code.indexOf(",") > -1) {
					String[] split2 = code.split(",");
					boolean isD = true;
					for (int i = 0; i < split2.length; i++) {
						if (split2[i].length() > 1) {
							isD = false;
							break;
						}
					}
					if (isD) {
						object.put("play", "单式");
					} else {
						object.put("play", "复式");
					}
				} else {
					object.put("play", "单式");
				}
				object.put("betCode", code);
				resultArray.add(object);
			}
		} else if (lotNo != null && lotNo.equals("T01006")) { // 足球半全场
			// 3,1,3,1,0,3,1,1,3,0,1,3_2_200_200
			JSONObject object = new JSONObject();
			object.put("lotName", "足球半全场");
			object.put("multiple", multiple); // 倍数
			String[] orderInfos = orderinfo.split("!");
			for (String orderInfo : orderInfos) {
				String[] split = orderInfo.split("_");
				String code = split[0];

				if (code.indexOf(",") > -1) {
					String[] split2 = code.split(",");
					boolean isD = true;
					for (int i = 0; i < split2.length; i++) {
						if (split2[i].length() > 1) {
							isD = false;
							break;
						}
					}
					if (isD) {
						object.put("play", "单式");
					} else {
						object.put("play", "复式");
					}
				} else {
					object.put("play", "单式");
				}
				object.put("betCode", code);
				resultArray.add(object);
			}
		} else if (lotNo != null && lotNo.equals("T01013")) { // 22选5
			// 502@20120508|2|424|1^20120508|2|425|2^_1_200_200
			JSONObject object = new JSONObject();
			object.put("lotName", "22选5");
			object.put("multiple", multiple); // 倍数
			String[] orderInfos = orderinfo.split("!");
			for (String orderInfo : orderInfos) {
				String[] split = orderInfo.split("_");
				String code = split[0];

				String[] split2 = code.split("@");
				if (split2[0].equals("0")) { // 单式
					object.put("play", "单式");
					String codeStr = split2[1].substring(0, split2[1].length() - 1);
					object.put("betCode", LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
							.getStringArrayFromString(codeStr)));
				} else if (split2[0].equals("1")) { // 复式
					object.put("play", "复式");
					String codeStr = split2[1].substring(0, split2[1].length() - 1);
					object.put("betCode", LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
							.getStringArrayFromString(codeStr)));
				} else if (split2[0].equals("2")) { // 胆拖
					object.put("play", "胆拖");
					String danMa = split2[1].substring(0, split2[1].indexOf("*"));
					String tuoMa = split2[1].substring(split2[1].indexOf("*") + 1, split2[1].length() - 1);
					object.put(
							"betCode",
							"胆码:"
									+ LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
											.getStringArrayFromString(danMa))
									+ "\n拖码:"
									+ LotteryAlgorithmUtil.joinStringArrayWithComma(LotteryAlgorithmUtil
											.getStringArrayFromString(tuoMa)));
				} else {
					object.put("play", "未知");
					object.put("betCode", orderinfo);
				}
				resultArray.add(object);
			}
		} else { // 未知彩种
			JSONObject object = new JSONObject();
			object.put("lotName", "未知");
			object.put("multiple", multiple); // 倍数
			object.put("play", "未知");
			object.put("betCode", orderinfo);
			resultArray.add(object);
		}

		return resultArray;
	}

	/**
	 * 11运夺金注码转换(胆拖)
	 * 
	 * @param betCode
	 * @return
	 */
	public String convertBetCodeFor11YDJ_DT(String betCode) {
		StringBuffer sBuffer = new StringBuffer();
		String[] split = betCode.split("\\*");
		String danMa = split[0];
		String tuoMa = split[1].substring(0, split[1].length() - 1);
		sBuffer.append("胆码:" + LotteryAlgorithmUtil.joinStringWithBlank(danMa) + "拖码:"
				+ LotteryAlgorithmUtil.joinStringWithBlank(tuoMa));
		String string = sBuffer.toString();
		if (string.endsWith("\n")) {
			return string.substring(0, string.length() - 1);
		} else {
			return sBuffer.toString();
		}
	}

	/**
	 * 11运夺金注码转换(单式)
	 * 
	 * @param code
	 * @return
	 */
	public String convertBetCodeFor11YDJ_DS(String betCode) {
		StringBuffer sBuffer = new StringBuffer();
		String[] split = betCode.split("\\^");
		for (String string : split) {
			sBuffer.append(LotteryAlgorithmUtil.joinStringWithBlank(string)).append("\n");
		}
		String string = sBuffer.toString();
		if (string.endsWith("\n")) {
			return string.substring(0, string.length() - 1);
		} else {
			return sBuffer.toString();
		}
	}

	/**
	 * 11运夺金注码转换(定位复式)
	 * 
	 * @param betCode
	 * @return
	 */
	public String convertBetCodeFor11YDJ_DWFS(String betCode) {
		StringBuffer sBuffer = new StringBuffer();
		String[] split = betCode.split("\\*");
		if (split.length == 2) { // 142@04*050607^
			String wan = split[0];
			String qian = split[1].substring(0, split[1].length() - 1);
			sBuffer.append("万位:" + LotteryAlgorithmUtil.joinStringWithBlank(wan) + "千位:"
					+ LotteryAlgorithmUtil.joinStringWithBlank(qian));
		} else if (split.length == 3) { // 162@0305*04*08^
			String wan = split[0];
			String qian = split[1];
			String bai = split[2].substring(0, split[2].length() - 1);
			sBuffer.append("万位:" + LotteryAlgorithmUtil.joinStringWithBlank(wan) + "千位:"
					+ LotteryAlgorithmUtil.joinStringWithBlank(qian) + "百位:"
					+ LotteryAlgorithmUtil.joinStringWithBlank(bai));
		}
		String string = sBuffer.toString();
		if (string.endsWith("\n")) {
			return string.substring(0, string.length() - 1);
		} else {
			return sBuffer.toString();
		}
	}

	/**
	 * 11运夺金注码转换(复式)
	 * 
	 * @param code
	 * @return
	 */
	public String convertBetCodeFor11YDJ_FS(String betCode) {
		String string = betCode.substring(betCode.indexOf("*") + 1, betCode.indexOf("^"));
		String code = LotteryAlgorithmUtil.joinStringWithBlank(string);
		return code;
	}
}
