package com.ruyicai.mgr.util;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 竟彩公共类
 * 
 * @author Administrator
 * 
 */
public class JingCaiUtil {

	private static Logger logger = Logger.getLogger(JingCaiUtil.class);

	/**
	 * 根据weekId获得星期(供竟彩星期转换使用)
	 * 
	 * @param weekId
	 * @return
	 */
	public static String getWeekByWeekId(String weekId) {
		String week = "";
		if (weekId != null && weekId.trim().equals("1")) {
			week = "星期一";
		} else if (weekId != null && weekId.trim().equals("2")) {
			week = "星期二";
		} else if (weekId != null && weekId.trim().equals("3")) {
			week = "星期三";
		} else if (weekId != null && weekId.trim().equals("4")) {
			week = "星期四";
		} else if (weekId != null && weekId.trim().equals("5")) {
			week = "星期五";
		} else if (weekId != null && weekId.trim().equals("6")) {
			week = "星期六";
		} else if (weekId != null && weekId.trim().equals("7")) {
			week = "星期日";
		}
		return week;
	}

	/**
	 * 获取竟彩篮球胜分差的投注内容
	 * 
	 * @param id
	 * @param peiLvString
	 * @param betCode
	 * @return
	 */
	public static String getBetContentForJ00007(String id, String peiLvString, String betCode) {
		StringBuffer sBuffer = new StringBuffer("");
		if (betCode != null) {
			int len = betCode.length() / 2;
			for (int i = 0; i < len; i++) {
				String code = betCode.substring(2 * i, 2 * i + 2);
				String peiLv = getPeiLvByCode(id, peiLvString, code);
				if (i == len - 1) {
					sBuffer.append(getScoreByCodeForJ00007(code));
					if (peiLv != null && !peiLv.equals("")) {
						sBuffer.append("(" + peiLv + ")");
					}
				} else {
					sBuffer.append(getScoreByCodeForJ00007(code));
					if (peiLv != null && !peiLv.equals("")) {
						sBuffer.append("(" + peiLv + ")");
					}
					sBuffer.append(" ");
				}
			}
		}
		return sBuffer.toString();
	}

	/**
	 * 竟彩篮球胜分差：根据注码得到分差
	 * 
	 * @param code
	 * @return
	 */
	public static String getScoreByCodeForJ00007(String code) {
		String result = "";
		if (code != null) {
			if (code.equals("01")) {
				result = "主胜1-5分";
			} else if (code.equals("02")) {
				result = "主胜6-10分";
			} else if (code.equals("03")) {
				result = "主胜11-15分";
			} else if (code.equals("04")) {
				result = "主胜16-20分";
			} else if (code.equals("05")) {
				result = "主胜21-25分";
			} else if (code.equals("06")) {
				result = "主胜26+分";
			} else if (code.equals("11")) {
				result = "客胜1-5分";
			} else if (code.equals("12")) {
				result = "客胜6-10分";
			} else if (code.equals("13")) {
				result = "客胜11-15分";
			} else if (code.equals("14")) {
				result = "客胜16-20分";
			} else if (code.equals("15")) {
				result = "客胜21-25分";
			} else if (code.equals("16")) {
				result = "客胜26+分";
			}
		}
		return result;
	}

	/**
	 * 获取竟彩篮球大小分的投注内容
	 * 
	 * @param id
	 * @param peiLvString
	 * @param betCode
	 * @return
	 */
	public static JSONObject getBetContentForJ00008(String id, String peiLvString, String betCode) {
		JSONObject resultObject = new JSONObject();

		StringBuffer sBuffer = new StringBuffer(""); // 投注内容
		String basePoint = ""; // 基本分
		if (betCode != null && betCode.length() == 1) {
			JSONObject resultObject1 = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, betCode);
			String peiLv = resultObject1.getString("peiLv");
			basePoint = resultObject1.getString("basePoint");
			sBuffer.append(getDXBySingleCodeForJC(betCode));
			if (peiLv != null && !peiLv.equals("")) {
				sBuffer.append("(" + peiLv + ")");
			}
		} else if (betCode != null && betCode.length() == 2) {
			String code1 = betCode.substring(0, 1);
			JSONObject resultObject1 = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code1);
			String peiLv1 = resultObject1.getString("peiLv");
			basePoint = resultObject1.getString("basePoint");
			sBuffer.append(getDXBySingleCodeForJC(code1));
			if (peiLv1 != null && !peiLv1.equals("")) {
				sBuffer.append("(" + peiLv1 + ")");
			}
			sBuffer.append(" ");

			String code2 = betCode.substring(1, 2);
			JSONObject resultObject2 = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code2);
			String peiLv2 = resultObject2.getString("peiLv");
			sBuffer.append(getDXBySingleCodeForJC(code2));
			if (peiLv2 != null && !peiLv2.equals("")) {
				sBuffer.append("(" + peiLv2 + ")");
			}
		}
		resultObject.put("betContent", sBuffer.toString());
		resultObject.put("basePoint", basePoint);
		return resultObject;
	}

	/**
	 * 竟彩篮球大小分:根据单个注码获得大小分
	 * 
	 * @param code
	 * @return
	 */
	public static String getDXBySingleCodeForJC(String code) {
		String result = "";
		if (code != null && code.equals("1")) {
			result = "大";
		} else if (code != null && code.equals("2")) {
			result = "小";
		}
		return result;
	}

	/**
	 * 获取竟彩玩法
	 * 
	 * @param code
	 * @return
	 */
	public static JSONObject getJingCaiPlay(String wanfa) {
		JSONObject object = new JSONObject();
		if (wanfa != null && wanfa.trim().equals("500")) {
			object.put("play", "单关");
			object.put("valueType", "0");
		} else if (wanfa != null && wanfa.trim().equals("502")) {
			object.put("play", "过关2串1");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("503")) {
			object.put("play", "过关3串1");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("504")) {
			object.put("play", "过关4串1");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("505")) {
			object.put("play", "过关5串1");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("506")) {
			object.put("play", "过关6串1");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("507")) {
			object.put("play", "过关7串1");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("508")) {
			object.put("play", "过关8串1");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("509")) {
			object.put("play", "过关2串3");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("510")) {
			object.put("play", "过关3串6");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("511")) {
			object.put("play", "过关3串7");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("512")) {
			object.put("play", "过关4串10");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("513")) {
			object.put("play", "过关4串14");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("514")) {
			object.put("play", "过关4串15");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("515")) {
			object.put("play", "过关5串15");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("516")) {
			object.put("play", "过关5串25");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("517")) {
			object.put("play", "过关5串30");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("518")) {
			object.put("play", "过关5串31");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("519")) {
			object.put("play", "过关6串21");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("520")) {
			object.put("play", "过关6串41");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("521")) {
			object.put("play", "过关6串56");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("522")) {
			object.put("play", "过关6串62");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("523")) {
			object.put("play", "过关6串63");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("524")) {
			object.put("play", "过关7串127");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("525")) {
			object.put("play", "过关8串255");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("526")) {
			object.put("play", "过关3串3");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("527")) {
			object.put("play", "过关3串4");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("528")) {
			object.put("play", "过关4串6");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("529")) {
			object.put("play", "过关4串11");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("530")) {
			object.put("play", "过关5串10");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("531")) {
			object.put("play", "过关5串20");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("532")) {
			object.put("play", "过关5串26");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("533")) {
			object.put("play", "过关6串15");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("534")) {
			object.put("play", "过关6串35");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("535")) {
			object.put("play", "过关6串50");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("536")) {
			object.put("play", "过关6串57");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("537")) {
			object.put("play", "过关7串120");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("538")) {
			object.put("play", "过关8串247");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("539")) {
			object.put("play", "过关4串4");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("540")) {
			object.put("play", "过关4串5");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("541")) {
			object.put("play", "过关5串16");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("542")) {
			object.put("play", "过关6串20");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("543")) {
			object.put("play", "过关6串42");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("544")) {
			object.put("play", "过关5串5");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("545")) {
			object.put("play", "过关5串6");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("546")) {
			object.put("play", "过关6串22");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("547")) {
			object.put("play", "过关7串35");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("548")) {
			object.put("play", "过关8串70");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("549")) {
			object.put("play", "过关6串6");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("550")) {
			object.put("play", "过关6串7");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("551")) {
			object.put("play", "过关7串21");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("552")) {
			object.put("play", "过关8串56");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("553")) {
			object.put("play", "过关7串7");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("554")) {
			object.put("play", "过关7串8");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("555")) {
			object.put("play", "过关8串28");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("556")) {
			object.put("play", "过关8串8");
			object.put("valueType", "1");
		} else if (wanfa != null && wanfa.trim().equals("557")) {
			object.put("play", "过关8串9");
			object.put("valueType", "1");
		} else {
			object.put("play", "未知");
			object.put("valueType", "1");
		}
		return object;
	}

	/**
	 * 根据注码获得赔率
	 * 
	 * @param id
	 * @param peiLvString
	 * @param code
	 * @return
	 */
	public static String getPeiLvByCode(String id, String peiLvString, String code) {
		// 20120520*7*001*|1:12.0|3:12.0|^20120520*7*002*|1:12.0|3:12.0|^
		String peiLv = ""; // 赔率
		if (!StringUtil.isEmpty(peiLvString)) {
			String[] split = peiLvString.split("\\^");
			for (String string : split) {
				String[] split2 = string.split("\\|");
				if (split2[0].equals(id)) {
					for (int i = 1; i < split2.length; i++) {
						String[] split3 = split2[i].split(":");
						if (split3[0].equals(code)) {
							peiLv = split3[1];
							break;
						}
					}
				}
			}
		}
		return peiLv;
	}

	/**
	 * 根据注码获得赔率(竞彩篮球让分胜负,竞彩篮球大小分)
	 * 
	 * @param id
	 * @param peiLvString
	 * @param code
	 * @return
	 */
	public static JSONObject getPeiLvByCodeForJ00006OrJ00008(String id, String peiLvString, String code) {
		// 20120529*2*301*|2(202.5):1.750|^20120529*2*302*|2(153.5):1.700|1(153.5):1.700|^20120530*3*303*|2(180.5):1.750|^
		JSONObject resultObject = new JSONObject();

		String peiLv = ""; // 赔率
		String letPoint = ""; // 让分胜负
		String basePoint = ""; // 基本分
		if (!StringUtil.isEmpty(peiLvString)) {
			String[] split = peiLvString.split("\\^");
			for (String string : split) {
				String[] split2 = string.split("\\|");
				if (split2[0].equals(id)) {
					for (int i = 1; i < split2.length; i++) {
						String[] split3 = split2[i].split(":");
						if (split3[0].substring(0, split3[0].indexOf("(")).equals(code)) {
							peiLv = split3[1];
							Pattern pattern = Pattern.compile(".*\\((.*)\\).*");
							Matcher matcher = pattern.matcher(split3[0]);
							if (matcher.find()) {
								letPoint = matcher.group(1);
								basePoint = matcher.group(1);
							}
							break;
						}
					}
				}
			}
		}

		resultObject.put("peiLv", peiLv);
		resultObject.put("letPoint", letPoint);
		resultObject.put("basePoint", basePoint);
		return resultObject;
	}

	/**
	 * 根据注码获取投注内容(竞彩足球胜负平)
	 * 
	 * @param id
	 * @param peiLvString
	 * @param code
	 * @return
	 */
	public static String getBetContentForJ00001(String id, String peiLvString, String betCode) {
		StringBuffer sBuffer = new StringBuffer(""); // 投注内容
		if (betCode != null && betCode.length() == 1) {
			String peiLv = getPeiLvByCode(id, peiLvString, betCode);
			sBuffer.append(getSPFBySingleCodeForJC_Z(betCode));
			if (peiLv != null && !peiLv.equals("")) {
				sBuffer.append("(" + peiLv + ")");
			}
		} else if (betCode != null && betCode.length() == 2) {
			String code1 = betCode.substring(0, 1);
			String peiLv1 = getPeiLvByCode(id, peiLvString, code1);
			sBuffer.append(getSPFBySingleCodeForJC_Z(code1));
			if (peiLv1 != null && !peiLv1.equals("")) {
				sBuffer.append("(" + peiLv1 + ")");
			}
			sBuffer.append(" ");

			String code2 = betCode.substring(1, 2);
			String peiLv2 = getPeiLvByCode(id, peiLvString, code2);
			sBuffer.append(getSPFBySingleCodeForJC_Z(code2));
			if (peiLv2 != null && !peiLv2.equals("")) {
				sBuffer.append("(" + peiLv2 + ")");
			}
		} else if (betCode != null && betCode.length() == 3) {
			String code1 = betCode.substring(0, 1);
			String peiLv1 = getPeiLvByCode(id, peiLvString, code1);
			sBuffer.append(getSPFBySingleCodeForJC_Z(code1));
			if (peiLv1 != null && !peiLv1.equals("")) {
				sBuffer.append("(" + peiLv1 + ")");
			}
			sBuffer.append(" ");

			String code2 = betCode.substring(1, 2);
			String peiLv2 = getPeiLvByCode(id, peiLvString, code2);
			sBuffer.append(getSPFBySingleCodeForJC_Z(code2));
			if (peiLv2 != null && !peiLv2.equals("")) {
				sBuffer.append("(" + peiLv2 + ")");
			}
			sBuffer.append(" ");

			String code3 = betCode.substring(2, 3);
			String peiLv3 = getPeiLvByCode(id, peiLvString, code3);
			sBuffer.append(getSPFBySingleCodeForJC_Z(code3));
			if (peiLv3 != null && !peiLv3.equals("")) {
				sBuffer.append("(" + peiLv3 + ")");
			}
		}
		return sBuffer.toString();
	}

	/**
	 * 根据注码获取投注内容(竞彩篮球胜负)
	 * 
	 * @param id
	 * @param peiLvString
	 * @param code
	 * @return
	 */
	public static String getBetContentForJ00005(String id, String peiLvString, String betCode) {
		StringBuffer sBuffer = new StringBuffer(""); // 投注内容
		if (betCode != null && betCode.length() == 1) {
			String peiLv = getPeiLvByCode(id, peiLvString, betCode);
			sBuffer.append(getSPFBySingleCodeForJC_L(betCode));
			if (peiLv != null && !peiLv.equals("")) {
				sBuffer.append("(" + peiLv + ")");
			}
		} else if (betCode != null && betCode.length() == 2) {
			String code1 = betCode.substring(0, 1);
			String peiLv1 = getPeiLvByCode(id, peiLvString, code1);
			sBuffer.append(getSPFBySingleCodeForJC_L(code1));
			if (peiLv1 != null && !peiLv1.equals("")) {
				sBuffer.append("(" + peiLv1 + ")");
			}
			sBuffer.append(" ");

			String code2 = betCode.substring(1, 2);
			String peiLv2 = getPeiLvByCode(id, peiLvString, code2);
			sBuffer.append(getSPFBySingleCodeForJC_L(code2));
			if (peiLv2 != null && !peiLv2.equals("")) {
				sBuffer.append("(" + peiLv2 + ")");
			}
		} else if (betCode != null && betCode.length() == 3) {
			String code1 = betCode.substring(0, 1);
			String peiLv1 = getPeiLvByCode(id, peiLvString, code1);
			sBuffer.append(getSPFBySingleCodeForJC_L(code1));
			if (peiLv1 != null && !peiLv1.equals("")) {
				sBuffer.append("(" + peiLv1 + ")");
			}
			sBuffer.append(" ");

			String code2 = betCode.substring(1, 2);
			String peiLv2 = getPeiLvByCode(id, peiLvString, code2);
			sBuffer.append(getSPFBySingleCodeForJC_L(code2));
			if (peiLv2 != null && !peiLv2.equals("")) {
				sBuffer.append("(" + peiLv2 + ")");
			}
			sBuffer.append(" ");

			String code3 = betCode.substring(2, 3);
			String peiLv3 = getPeiLvByCode(id, peiLvString, code2);
			sBuffer.append(getSPFBySingleCodeForJC_L(code3));
			if (peiLv3 != null && !peiLv3.equals("")) {
				sBuffer.append("(" + peiLv3 + ")");
			}
		}
		return sBuffer.toString();
	}

	/**
	 * 根据注码获取竞彩篮球让分胜负投注内容
	 * 
	 * @param id
	 * @param peiLvString
	 * @param code
	 * @return
	 */
	public static JSONObject getBetContentForJ00006(String id, String peiLvString, String betCode) {
		JSONObject resultObject = new JSONObject();

		StringBuffer sBuffer = new StringBuffer(""); // 投注内容
		String letPoint = ""; // 让分
		if (betCode != null && betCode.length() == 1) {
			JSONObject peiLvByObject = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, betCode);
			String peiLv = peiLvByObject.getString("peiLv");
			letPoint = peiLvByObject.getString("letPoint");
			sBuffer.append(getSPFBySingleCodeForJC_L(betCode));
			if (peiLv != null && !peiLv.equals("")) {
				sBuffer.append("(" + peiLv + ")");
			}
		} else if (betCode != null && betCode.length() == 2) {
			String code1 = betCode.substring(0, 1);
			JSONObject peiLvByObject1 = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code1);
			String peiLv1 = peiLvByObject1.getString("peiLv");
			letPoint = peiLvByObject1.getString("letPoint");
			sBuffer.append(getSPFBySingleCodeForJC_L(code1));
			if (peiLv1 != null && !peiLv1.equals("")) {
				sBuffer.append("(" + peiLv1 + ")");
			}
			sBuffer.append(" ");

			String code2 = betCode.substring(1, 2);
			JSONObject peiLvByObject2 = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code2);
			String peiLv2 = peiLvByObject2.getString("peiLv");
			sBuffer.append(getSPFBySingleCodeForJC_L(code2));
			if (peiLv2 != null && !peiLv2.equals("")) {
				sBuffer.append("(" + peiLv2 + ")");
			}
		} else if (betCode != null && betCode.length() == 3) {
			String code1 = betCode.substring(0, 1);
			JSONObject peiLvByObject1 = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code1);
			String peiLv1 = peiLvByObject1.getString("peiLv");
			letPoint = peiLvByObject1.getString("letPoint");
			sBuffer.append(getSPFBySingleCodeForJC_L(code1));
			if (peiLv1 != null && !peiLv1.equals("")) {
				sBuffer.append("(" + peiLv1 + ")");
			}
			sBuffer.append(" ");

			String code2 = betCode.substring(1, 2);
			JSONObject peiLvByObject2 = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code2);
			String peiLv2 = peiLvByObject2.getString("peiLv");
			sBuffer.append(getSPFBySingleCodeForJC_L(code2));
			if (peiLv2 != null && !peiLv2.equals("")) {
				sBuffer.append("(" + peiLv2 + ")");
			}
			sBuffer.append(" ");

			String code3 = betCode.substring(2, 3);
			JSONObject peiLvByObject3 = getPeiLvByCodeForJ00006OrJ00008(id, peiLvString, code2);
			String peiLv3 = peiLvByObject3.getString("peiLv");
			sBuffer.append(getSPFBySingleCodeForJC_L(code3));
			if (peiLv3 != null && !peiLv3.equals("")) {
				sBuffer.append("(" + peiLv3 + ")");
			}
		}

		resultObject.put("betContent", sBuffer.toString());
		resultObject.put("letPoint", letPoint);
		return resultObject;
	}

	/**
	 * 根据单个注码获取竟彩胜平负(篮球)
	 * 
	 * @param code
	 * @return
	 */
	public static String getSPFBySingleCodeForJC_L(String code) {
		String result = "";
		if (code != null && code.equals("0")) { // 负
			result = "主负";
		} else if (code != null && code.equals("3")) { // 胜
			result = "主胜";
		}
		return result;
	}

	/**
	 * 根据单个注码获取竟彩胜平负(足球)
	 * 
	 * @param code
	 * @return
	 */
	public static String getSPFBySingleCodeForJC_Z(String code) {
		String result = "";
		if (code != null && code.equals("0")) { // 负
			result = "负";
		} else if (code != null && code.equals("1")) { // 平
			result = "平";
		} else if (code != null && code.equals("3")) { // 胜
			result = "胜";
		}
		return result;
	}

	/**
	 * 解析竟彩赔率(篮球)
	 * 
	 * @param object
	 * @param xml
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	public static void parseXMLForJingcaiPeiLvLQ(JSONObject object, String xml, String id) {
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			Element bodyElement = root.element("body");
			Element matchListElement = bodyElement.element("matchList");
			List items = matchListElement.elements("item");
			boolean isExist = false;
			for (Iterator it = items.iterator(); it.hasNext();) {
				Element itemElement = (Element) it.next();
				Element idElement = itemElement.element("id");
				String idXML = idElement.getText();
				if (idXML.equals(id)) {
					isExist = true;
					// 玩法:胜负
					Element vsElement = itemElement.element("vs");
					Element v0Element = vsElement.element("v0");
					if (v0Element != null && !v0Element.getText().equals("0000")) {
						object.put("v0", v0Element.getText());
					} else {
						object.put("v0", "");
					}
					Element v3Element = vsElement.element("v3");
					if (v3Element != null && !v3Element.getText().equals("0000")) {
						object.put("v3", v3Element.getText());
					} else {
						object.put("v3", "");
					}
					// 玩法:让分胜负
					Element letVsElement = itemElement.element("letVs");
					Element letVs_V0Element = letVsElement.element("v0");
					if (letVs_V0Element != null && !letVs_V0Element.getText().equals("0000")) {
						object.put("letVs_v0", letVs_V0Element.getText());
					} else {
						object.put("letVs_v0", "");
					}
					Element letVs_V3Element = letVsElement.element("v3");
					if (letVs_V3Element != null && !letVs_V3Element.getText().equals("0000")) {
						object.put("letVs_v3", letVs_V3Element.getText());
					} else {
						object.put("letVs_v3", "");
					}
					Element letPointElement = letVsElement.element("letPoint");
					if (letPointElement != null && !letPointElement.getText().equals("0000")) {
						object.put("letPoint", letPointElement.getText());
					} else {
						object.put("letPoint", "");
					}
					// 玩法:大小分
					Element bsElement = itemElement.element("bs");
					Element gElement = bsElement.element("g");
					if (gElement != null && !gElement.getText().equals("0000")) {
						object.put("g", gElement.getText());
					} else {
						object.put("g", "");
					}
					Element lElement = bsElement.element("l");
					if (lElement != null && !lElement.getText().equals("0000")) {
						object.put("l", lElement.getText());
					} else {
						object.put("l", "");
					}
					Element basePointElement = bsElement.element("basePoint");
					if (basePointElement != null && !basePointElement.getText().equals("0000")) {
						object.put("basePoint", basePointElement.getText());
					} else {
						object.put("basePoint", "");
					}
					// 玩法:胜分差
					Element diffElement = itemElement.element("diff");
					Element v01Element = diffElement.element("v01");
					if (v01Element != null && !v01Element.getText().equals("0000")) {
						object.put("v01", v01Element.getText());
					} else {
						object.put("v01", "");
					}
					Element v02Element = diffElement.element("v02");
					if (v02Element != null && !v02Element.getText().equals("0000")) {
						object.put("v02", v02Element.getText());
					} else {
						object.put("v02", "");
					}
					Element v03Element = diffElement.element("v03");
					if (v03Element != null && !v03Element.getText().equals("0000")) {
						object.put("v03", v03Element.getText());
					} else {
						object.put("v03", "");
					}
					Element v04Element = diffElement.element("v04");
					if (v04Element != null && !v04Element.getText().equals("0000")) {
						object.put("v04", v04Element.getText());
					} else {
						object.put("v04", "");
					}
					Element v05Element = diffElement.element("v05");
					if (v05Element != null && !v05Element.getText().equals("0000")) {
						object.put("v05", v05Element.getText());
					} else {
						object.put("v05", "");
					}
					Element v06Element = diffElement.element("v06");
					if (v06Element != null && !v06Element.getText().equals("0000")) {
						object.put("v06", v06Element.getText());
					} else {
						object.put("v06", "");
					}
					Element v11Element = diffElement.element("v11");
					if (v11Element != null && !v11Element.getText().equals("0000")) {
						object.put("v11", v11Element.getText());
					} else {
						object.put("v11", "");
					}
					Element v12Element = diffElement.element("v12");
					if (v12Element != null && !v12Element.getText().equals("0000")) {
						object.put("v12", v12Element.getText());
					} else {
						object.put("v12", "");
					}
					Element v13Element = diffElement.element("v13");
					if (v13Element != null && !v13Element.getText().equals("0000")) {
						object.put("v13", v13Element.getText());
					} else {
						object.put("v13", "");
					}
					Element v14Element = diffElement.element("v14");
					if (v14Element != null && !v14Element.getText().equals("0000")) {
						object.put("v14", v14Element.getText());
					} else {
						object.put("v14", "");
					}
					Element v15Element = diffElement.element("v15");
					if (v15Element != null && !v15Element.getText().equals("0000")) {
						object.put("v15", v15Element.getText());
					} else {
						object.put("v15", "");
					}
					Element v16Element = diffElement.element("v16");
					if (v16Element != null && !v16Element.getText().equals("0000")) {
						object.put("v16", v16Element.getText());
					} else {
						object.put("v16", "");
					}
				}
			}
			if (!isExist) {
				// 玩法:胜负
				object.put("v0", "");
				object.put("v3", "");
				// 玩法:让分胜负
				object.put("letVs_v0", "");
				object.put("letVs_v3", "");
				object.put("letPoint", "");
				// 玩法:大小分
				object.put("g", "");
				object.put("l", "");
				object.put("basePoint", "");
				// 玩法:胜分差
				object.put("v01", "");
				object.put("v02", "");
				object.put("v03", "");
				object.put("v04", "");
				object.put("v05", "");
				object.put("v06", "");
				object.put("v11", "");
				object.put("v12", "");
				object.put("v13", "");
				object.put("v14", "");
				object.put("v15", "");
				object.put("v16", "");
			}
		} catch (DocumentException e) {
			logger.error("解析竟彩赔率(篮球)发生异常", e);
		}
	}

	/**
	 * 解析竟彩赔率(足球)
	 * 
	 * @param object
	 * @param xml
	 * @param id
	 */
	@SuppressWarnings("unchecked")
	public static void parseXMLForJingcaiPeiLvZQ(JSONObject object, String xml, String id) {
		try {
			Document document = DocumentHelper.parseText(xml);
			Element root = document.getRootElement();
			Element bodyElement = root.element("body");
			Element matchListElement = bodyElement.element("matchList");
			List items = matchListElement.elements("item");
			boolean isExist = false;
			for (Iterator it = items.iterator(); it.hasNext();) {
				Element itemElement = (Element) it.next();
				Element idElement = itemElement.element("id");
				String idXML = idElement.getText();
				if (idXML.equals(id)) {
					isExist = true;
					// 胜平负
					Element vsElement = itemElement.element("vs");
					Element v0Element = vsElement.element("v0");
					if (v0Element != null && !v0Element.getText().equals("0000")) {
						object.put("v0", v0Element.getText());
					} else {
						object.put("v0", "");
					}
					Element v1Element = vsElement.element("v1");
					if (v1Element != null && !v1Element.getText().equals("0000")) {
						object.put("v1", v1Element.getText());
					} else {
						object.put("v1", "");
					}
					Element v3Element = vsElement.element("v3");
					if (v3Element != null && !v3Element.getText().equals("0000")) {
						object.put("v3", v3Element.getText());
					} else {
						object.put("v3", "");
					}
					Element letPointElement = vsElement.element("letPoint");
					if (letPointElement != null && !letPointElement.getText().equals("0000")) {
						object.put("letPoint", letPointElement.getText());
					} else {
						object.put("letPoint", "");
					}
					// 比分
					Element scoreElement = itemElement.element("score");
					Element v00Element = scoreElement.element("v00");
					if (v00Element != null && !v00Element.getText().equals("0000")) {
						object.put("score_v00", v00Element.getText());
					} else {
						object.put("score_v00", "");
					}
					Element v01Element = scoreElement.element("v01");
					if (v01Element != null && !v01Element.getText().equals("0000")) {
						object.put("score_v01", v01Element.getText());
					} else {
						object.put("score_v01", "");
					}
					Element v02Element = scoreElement.element("v02");
					if (v02Element != null && !v02Element.getText().equals("0000")) {
						object.put("score_v02", v02Element.getText());
					} else {
						object.put("score_v02", "");
					}
					Element v03Element = scoreElement.element("v03");
					if (v03Element != null && !v03Element.getText().equals("0000")) {
						object.put("score_v03", v03Element.getText());
					} else {
						object.put("score_v03", "");
					}
					Element v04Element = scoreElement.element("v04");
					if (v04Element != null && !v04Element.getText().equals("0000")) {
						object.put("score_v04", v04Element.getText());
					} else {
						object.put("score_v04", "");
					}
					Element v05Element = scoreElement.element("v05");
					if (v04Element != null && !v05Element.getText().equals("0000")) {
						object.put("score_v05", v05Element.getText());
					} else {
						object.put("score_v05", "");
					}
					Element v09Element = scoreElement.element("v09");
					if (v09Element != null && !v09Element.getText().equals("0000")) {
						object.put("score_v09", v09Element.getText());
					} else {
						object.put("score_v09", "");
					}
					Element v10Element = scoreElement.element("v10");
					if (v10Element != null && !v10Element.getText().equals("0000")) {
						object.put("score_v10", v10Element.getText());
					} else {
						object.put("score_v10", "");
					}
					Element v11Element = scoreElement.element("v11");
					if (v11Element != null && !v11Element.getText().equals("0000")) {
						object.put("score_v11", v11Element.getText());
					} else {
						object.put("score_v11", "");
					}
					Element v12Element = scoreElement.element("v12");
					if (v12Element != null && !v12Element.getText().equals("0000")) {
						object.put("score_v12", v12Element.getText());
					} else {
						object.put("score_v12", "");
					}
					Element v13Element = scoreElement.element("v13");
					if (v13Element != null && !v13Element.getText().equals("0000")) {
						object.put("score_v13", v13Element.getText());
					} else {
						object.put("score_v13", "");
					}
					Element v14Element = scoreElement.element("v14");
					if (v14Element != null && !v14Element.getText().equals("0000")) {
						object.put("score_v14", v14Element.getText());
					} else {
						object.put("score_v14", "");
					}
					Element v15Element = scoreElement.element("v15");
					if (v15Element != null && !v15Element.getText().equals("0000")) {
						object.put("score_v15", v15Element.getText());
					} else {
						object.put("score_v15", "");
					}
					Element v20Element = scoreElement.element("v20");
					if (v20Element != null && !v20Element.getText().equals("0000")) {
						object.put("score_v20", v20Element.getText());
					} else {
						object.put("score_v20", "");
					}
					Element v21Element = scoreElement.element("v21");
					if (v21Element != null && !v21Element.getText().equals("0000")) {
						object.put("score_v21", v21Element.getText());
					} else {
						object.put("score_v21", "");
					}
					Element v22Element = scoreElement.element("v22");
					if (v22Element != null && !v22Element.getText().equals("0000")) {
						object.put("score_v22", v22Element.getText());
					} else {
						object.put("score_v22", "");
					}
					Element v23Element = scoreElement.element("v23");
					if (v23Element != null && !v23Element.getText().equals("0000")) {
						object.put("score_v23", v23Element.getText());
					} else {
						object.put("score_v23", "");
					}
					Element v24Element = scoreElement.element("v24");
					if (v24Element != null && !v24Element.getText().equals("0000")) {
						object.put("score_v24", v24Element.getText());
					} else {
						object.put("score_v24", "");
					}
					Element v25Element = scoreElement.element("v25");
					if (v25Element != null && !v25Element.getText().equals("0000")) {
						object.put("score_v25", v25Element.getText());
					} else {
						object.put("score_v25", "");
					}
					Element v30Element = scoreElement.element("v30");
					if (v30Element != null && !v30Element.getText().equals("0000")) {
						object.put("score_v30", v30Element.getText());
					} else {
						object.put("score_v30", "");
					}
					Element v31Element = scoreElement.element("v31");
					if (v31Element != null && !v31Element.getText().equals("0000")) {
						object.put("score_v31", v31Element.getText());
					} else {
						object.put("score_v31", "");
					}
					Element v32Element = scoreElement.element("v32");
					if (v32Element != null && !v32Element.getText().equals("0000")) {
						object.put("score_v32", v32Element.getText());
					} else {
						object.put("score_v32", "");
					}
					Element v33Element = scoreElement.element("v33");
					if (v33Element != null && !v33Element.getText().equals("0000")) {
						object.put("score_v33", v33Element.getText());
					} else {
						object.put("score_v33", "");
					}
					Element v40Element = scoreElement.element("v40");
					if (v40Element != null && !v40Element.getText().equals("0000")) {
						object.put("score_v40", v40Element.getText());
					} else {
						object.put("score_v40", "");
					}
					Element v41Element = scoreElement.element("v41");
					if (v41Element != null && !v41Element.getText().equals("0000")) {
						object.put("score_v41", v41Element.getText());
					} else {
						object.put("score_v41", "");
					}
					Element v42Element = scoreElement.element("v42");
					if (v42Element != null && !v42Element.getText().equals("0000")) {
						object.put("score_v42", v42Element.getText());
					} else {
						object.put("score_v42", "");
					}
					Element v50Element = scoreElement.element("v50");
					if (v50Element != null && !v50Element.getText().equals("0000")) {
						object.put("score_v50", v50Element.getText());
					} else {
						object.put("score_v50", "");
					}
					Element v51Element = scoreElement.element("v51");
					if (v51Element != null && !v51Element.getText().equals("0000")) {
						object.put("score_v51", v51Element.getText());
					} else {
						object.put("score_v51", "");
					}
					Element v52Element = scoreElement.element("v52");
					if (v52Element != null && !v52Element.getText().equals("0000")) {
						object.put("score_v52", v52Element.getText());
					} else {
						object.put("score_v52", "");
					}
					Element v90Element = scoreElement.element("v90");
					if (v90Element != null && !v90Element.getText().equals("0000")) {
						object.put("score_v90", v90Element.getText());
					} else {
						object.put("score_v90", "");
					}
					Element v99Element = scoreElement.element("v99");
					if (v99Element != null && !v99Element.getText().equals("0000")) {
						object.put("score_v99", v99Element.getText());
					} else {
						object.put("score_v99", "");
					}
					// 总进球数
					Element goalElement = itemElement.element("goal");
					Element goal_v0Element = goalElement.element("v0");
					if (goal_v0Element != null && !goal_v0Element.getText().equals("0000")) {
						object.put("goal_v0", goal_v0Element.getText());
					} else {
						object.put("goal_v0", "");
					}
					Element goal_v1Element = goalElement.element("v1");
					if (goal_v1Element != null && !goal_v1Element.getText().equals("0000")) {
						object.put("goal_v1", goal_v1Element.getText());
					} else {
						object.put("goal_v1", "");
					}
					Element goal_v2Element = goalElement.element("v2");
					if (goal_v2Element != null && !goal_v2Element.getText().equals("0000")) {
						object.put("goal_v2", goal_v2Element.getText());
					} else {
						object.put("goal_v2", "");
					}
					Element goal_v3Element = goalElement.element("v3");
					if (goal_v3Element != null && !goal_v3Element.getText().equals("0000")) {
						object.put("goal_v3", goal_v3Element.getText());
					} else {
						object.put("goal_v3", "");
					}
					Element goal_v4Element = goalElement.element("v4");
					if (goal_v4Element != null && !goal_v4Element.getText().equals("0000")) {
						object.put("goal_v4", goal_v4Element.getText());
					} else {
						object.put("goal_v4", "");
					}
					Element goal_v5Element = goalElement.element("v5");
					if (goal_v5Element != null && !goal_v5Element.getText().equals("0000")) {
						object.put("goal_v5", goal_v5Element.getText());
					} else {
						object.put("goal_v5", "");
					}
					Element goal_v6Element = goalElement.element("v6");
					if (goal_v6Element != null && !goal_v6Element.getText().equals("0000")) {
						object.put("goal_v6", goal_v6Element.getText());
					} else {
						object.put("goal_v6", "");
					}
					Element goal_v7Element = goalElement.element("v7");
					if (goal_v7Element != null && !goal_v7Element.getText().equals("0000")) {
						object.put("goal_v7", goal_v7Element.getText());
					} else {
						object.put("goal_v7", "");
					}
					// 半全场
					Element halfElement = itemElement.element("half");
					Element half_v00Element = halfElement.element("v00");
					if (half_v00Element != null && !half_v00Element.getText().equals("0000")) {
						object.put("half_v00", half_v00Element.getText());
					} else {
						object.put("half_v00", "");
					}
					Element half_v01Element = halfElement.element("v01");
					if (half_v01Element != null && !half_v01Element.getText().equals("0000")) {
						object.put("half_v01", half_v01Element.getText());
					} else {
						object.put("half_v01", "");
					}
					Element half_v03Element = halfElement.element("v03");
					if (half_v03Element != null && !half_v03Element.getText().equals("0000")) {
						object.put("half_v03", half_v03Element.getText());
					} else {
						object.put("half_v03", "");
					}
					Element half_v10Element = halfElement.element("v10");
					if (half_v10Element != null && !half_v10Element.getText().equals("0000")) {
						object.put("half_v10", half_v10Element.getText());
					} else {
						object.put("half_v10", "");
					}
					Element half_v11Element = halfElement.element("v11");
					if (half_v11Element != null && !half_v11Element.getText().equals("0000")) {
						object.put("half_v11", half_v11Element.getText());
					} else {
						object.put("half_v11", "");
					}
					Element half_v13Element = halfElement.element("v13");
					if (half_v13Element != null && !half_v13Element.getText().equals("0000")) {
						object.put("half_v13", half_v13Element.getText());
					} else {
						object.put("half_v13", "");
					}
					Element half_v30Element = halfElement.element("v30");
					if (half_v30Element != null && !half_v30Element.getText().equals("0000")) {
						object.put("half_v30", half_v30Element.getText());
					} else {
						object.put("half_v30", "");
					}
					Element half_v31Element = halfElement.element("v31");
					if (half_v31Element != null && !half_v31Element.getText().equals("0000")) {
						object.put("half_v31", half_v31Element.getText());
					} else {
						object.put("half_v31", "");
					}
					Element half_v33Element = halfElement.element("v33");
					if (half_v33Element != null && !half_v33Element.getText().equals("0000")) {
						object.put("half_v33", half_v33Element.getText());
					} else {
						object.put("half_v33", "");
					}
				}
			}
			if (!isExist) {
				// 胜平负
				object.put("v3", "");
				object.put("v1", "");
				object.put("v0", "");
				object.put("letPoint", "");
				// 比分
				object.put("score_v00", "");
				object.put("score_v01", "");
				object.put("score_v02", "");
				object.put("score_v03", "");
				object.put("score_v04", "");
				object.put("score_v05", "");
				object.put("score_v09", "");
				object.put("score_v10", "");
				object.put("score_v11", "");
				object.put("score_v12", "");
				object.put("score_v13", "");
				object.put("score_v14", "");
				object.put("score_v15", "");
				object.put("score_v20", "");
				object.put("score_v21", "");
				object.put("score_v22", "");
				object.put("score_v23", "");
				object.put("score_v24", "");
				object.put("score_v25", "");
				object.put("score_v30", "");
				object.put("score_v31", "");
				object.put("score_v32", "");
				object.put("score_v33", "");
				object.put("score_v40", "");
				object.put("score_v41", "");
				object.put("score_v42", "");
				object.put("score_v50", "");
				object.put("score_v51", "");
				object.put("score_v52", "");
				object.put("score_v90", "");
				object.put("score_v99", "");
				// 总进球数
				object.put("goal_v0", "");
				object.put("goal_v1", "");
				object.put("goal_v2", "");
				object.put("goal_v3", "");
				object.put("goal_v4", "");
				object.put("goal_v5", "");
				object.put("goal_v6", "");
				object.put("goal_v7", "");
				// 半全场
				object.put("half_v00", "");
				object.put("half_v01", "");
				object.put("half_v03", "");
				object.put("half_v10", "");
				object.put("half_v11", "");
				object.put("half_v13", "");
				object.put("half_v30", "");
				object.put("half_v31", "");
				object.put("half_v33", "");
			}
		} catch (DocumentException e) {
			logger.error("解析竟彩赔率(足球)发生异常", e);
		}
	}

	/**
	 * 获取竞彩足球比分投注内容
	 * 
	 * @param id
	 * @param peiLvString
	 * @param betCode
	 * @return
	 */
	public static String getBetContentForJ00002(String id, String peiLvString, String betCode) {
		StringBuffer resultBuffer = new StringBuffer("");
		int l = betCode.length();
		int h = l / 2;
		int n = 0;
		for (int i = 0; i < h; i++) {
			String code = betCode.substring(n, n + 2);
			String peiLv = getPeiLvByCode(id, peiLvString, betCode);
			n = n + 2;
			if (i == h - 1) {
				if (code.equals("90")) {
					resultBuffer.append("胜其他");
					if (peiLv != null && !peiLv.equals("")) {
						resultBuffer.append("(" + peiLv + ")");
					}
				} else if (code.equals("99")) {
					resultBuffer.append("平其他");
					if (peiLv != null && !peiLv.equals("")) {
						resultBuffer.append("(" + peiLv + ")");
					}
				} else if (code.equals("09")) {
					resultBuffer.append("负其他");
					if (peiLv != null && !peiLv.equals("")) {
						resultBuffer.append("(" + peiLv + ")");
					}
				} else {
					resultBuffer.append(code.substring(0, 1) + ":" + code.substring(1));
					if (peiLv != null && !peiLv.equals("")) {
						resultBuffer.append("(" + peiLv + ")");
					}
				}
			} else {
				if (code.equals("90")) {
					resultBuffer.append("胜其他");
					if (peiLv != null && !peiLv.equals("")) {
						resultBuffer.append("(" + peiLv + ")");
					}
					resultBuffer.append(" ");
				} else if (code.equals("99")) {
					resultBuffer.append("平其他");
					if (peiLv != null && !peiLv.equals("")) {
						resultBuffer.append("(" + peiLv + ")");
					}
					resultBuffer.append(" ");
				} else if (code.equals("09")) {
					resultBuffer.append("负其他");
					if (peiLv != null && !peiLv.equals("")) {
						resultBuffer.append("(" + peiLv + ")");
					}
					resultBuffer.append(" ");
				} else {
					resultBuffer.append(code.substring(0, 1) + ":" + code.substring(1));
					if (peiLv != null && !peiLv.equals("")) {
						resultBuffer.append("(" + peiLv + ")");
					}
					resultBuffer.append(" ");
				}
			}
		}
		return resultBuffer.toString();
	}

	/**
	 * 获取竞彩足球总进球投注内容
	 * 
	 * @param id
	 * @param peiLvString
	 * @param betCode
	 * @return
	 */
	public static String getBetContentForJ00003(String id, String peiLvString, String betCode) {
		StringBuffer resultBuffer = new StringBuffer("");
		for (int i = 0; i < betCode.length(); i++) {
			String code = betCode.substring(i, i + 1);
			String peiLv = getPeiLvByCode(id, peiLvString, code);
			if (i == betCode.length() - 1) {
				resultBuffer.append(code);
				if (peiLv != null && !peiLv.equals("")) {
					resultBuffer.append("(" + peiLv + ")");
				}
			} else {
				resultBuffer.append(code);
				if (peiLv != null && !peiLv.equals("")) {
					resultBuffer.append("(" + peiLv + ")");
				}
				resultBuffer.append(" ");
			}
		}
		return resultBuffer.toString();
	}

	/**
	 * 获取竞彩足球半场胜平负投注内容
	 * 
	 * @param id
	 * @param peiLvString
	 * @param betCode
	 * @return
	 */
	public static String getBetContentForJ00004(String id, String peiLvString, String betCode) {
		StringBuffer resultBuffer = new StringBuffer("");
		int l = betCode.length();
		int h = l / 2;
		int n = 0;
		for (int i = 0; i < h; i++) {
			String code = betCode.substring(n, n + 2);
			String peiLv = getPeiLvByCode(id, peiLvString, code);
			n = n + 2;
			if (i == h - 1) {
				resultBuffer.append(parseCodeForJ00004(code));
				if (peiLv != null && !peiLv.equals("")) {
					resultBuffer.append("(" + peiLv + ")");
				}
			} else {
				resultBuffer.append(parseCodeForJ00004(code));
				if (peiLv != null && !peiLv.equals("")) {
					resultBuffer.append("(" + peiLv + ")");
				}
				resultBuffer.append(" ");
			}
		}
		return resultBuffer.toString();
	}

	/**
	 * 解析竞彩足球半场胜平负的注码
	 * 
	 * @param code
	 * @return
	 */
	public static String parseCodeForJ00004(String code) {
		String result = "";
		if (code != null && code.equals("00")) {
			result = "负负";
		} else if (code != null && code.equals("01")) {
			result = "负平";
		} else if (code != null && code.equals("03")) {
			result = "负胜";
		} else if (code != null && code.equals("11")) {
			result = "平平";
		} else if (code != null && code.equals("10")) {
			result = "平负";
		} else if (code != null && code.equals("13")) {
			result = "平胜";
		} else if (code != null && code.equals("33")) {
			result = "胜胜";
		} else if (code != null && code.equals("30")) {
			result = "胜负";
		} else if (code != null && code.equals("31")) {
			result = "胜平";
		}
		return result;
	}

}
