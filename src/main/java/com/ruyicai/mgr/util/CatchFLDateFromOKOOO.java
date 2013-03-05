package com.ruyicai.mgr.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;

import com.ruyicai.mgr.mysql.pojo.FLData;

public class CatchFLDateFromOKOOO {
      	public static void main(String[] args) throws Exception {
		
		String url = "http://www.okooo.com/zucai/12125/";
//		url = "http://www.okooo.com/Lottery06/WorldCup/WCLotteryIndex.php?LotteryType=WCFourGoal";//4场进球彩
//		url = "http://www.okooo.com/Lottery06/WorldCup/WCLotteryIndex.php?LotteryType=WCSixHalfToTo";//6场半全场
//		url = "http://www.okooo.com/Lottery06/SoccerIndex.php"; //胜负彩/任9
		String encoding = "gb2312";//网页编码类型
		String num = "games14";//比赛类型：games14=胜负/任9,games6=6场半全场，games4=4场进球彩
		List<FLData> datas = getFLDatas(url);
		if(null != datas) {
			for(FLData data : datas) {
				StringBuilder sb = new StringBuilder();
				sb.append(data.getName()).append("---").append(data.getNum()).append("---")
					.append(data.getHTeam()).append("---").append(data.getVTeam()).append("---")
					.append(data.getDate()).append("---").append(data.getAvgOdds());
				System.out.println(sb.toString());
			}
		}
	}
      	
      	public static List<FLData> getFLDatas(String urlString) {
      		List<FLData> datas = new ArrayList<FLData>();
      		try{
	    	 	URL url = new URL(urlString);
				URLConnection connection = url.openConnection();
				connection.setDoInput(true);
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				connection.connect();
				InputStream in = connection.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in, "gb2312"));
				String line = null;
				StringBuilder sb = new StringBuilder();
				while((line = reader.readLine()) != null) {
					sb.append(line);
				}
				reader.close();
				int begin = sb.toString().indexOf("<tbody>");
				int end = sb.toString().indexOf("</tbody>");
				
				String target = sb.toString().substring(begin,end).replaceAll("<br />", "|")
						.replaceAll("<br>", "|");
				
				String str = find(target);
				
				sb = new StringBuilder();
				sb.append("<?xml version='1.0' encoding='UTF-8'?>");
				sb.append(str).append("</tr></tbody>");
				
				Document document = DocumentHelper.parseText(sb.toString().replaceAll("<input>", "").replaceAll("&nbsp", "")
						.replaceAll("<!--tzbox>", "").replaceAll("<INPUT>", "").replaceAll("<!-->", ""));
				Element root = document.getRootElement();
				List<Node> nodes = root.elements("tr");
				for(Node node : nodes) {
						FLData fldata = new FLData();
						Element element = (Element)node;
						List<Node> tbs = element.elements("td");
						System.out.println(tbs.get(0).getText().trim());//场次
						System.out.println(((Element)tbs.get(1)).element("a").getText().trim());//赛事
						System.out.println("2013"+((Element)tbs.get(2)).element("span").getText());//时间
						Element e3 = (Element)tbs.get(3);
						List<Element> a = e3.elements("a");
						Element s = a.get(0).element("span");
						Element s1 = a.get(1).element("em");
						Element s2 = a.get(2).element("span");
						fldata.setNum(tbs.get(0).getText().trim());
						fldata.setHTeam(s.getText());
						fldata.setVTeam(s2.getText());
						fldata.setName(((Element)tbs.get(1)).element("a").getText().trim());
						fldata.setDate("2013-"+((Element)tbs.get(2)).element("span").getText());
						String avg = ((Element) (a.get(0).elements("em")).get(1)).getText().trim()+"|"
									+s1.getText()+"|"
									+a.get(2).element("em").getText().trim();
						fldata.setAvgOdds(avg);
						datas.add(fldata);
				}
	      	}catch(Exception e){
				e.printStackTrace();
				datas = null;
			}
		return datas;
	}
	/**
	 * 根据输入url
	 *//*
	public static List<FLData> getFLDatas(String urlString) {
		
		List<FLData> datas = new ArrayList<FLData>();
		try{
			URL url = new URL(urlString);
			
			URLConnection connection = url.openConnection();
			connection.setDoInput(true);
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.connect();
			InputStream in = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in, "gb2312"));
			String line = null;
			
			StringBuilder sb = new StringBuilder();
			
			while((line = reader.readLine()) != null) {
				
				sb.append(line);
			}
			
			int begin = sb.toString().indexOf("<form");
			int end = sb.toString().indexOf("</form");
			
			String target = sb.toString().substring(begin,end).replaceAll("<br />", "|")
					.replaceAll("<br>", "|");
			
			String str = find(target);
			
			sb = new StringBuilder();
			sb.append("<?xml version='1.0' encoding='UTF-8'?>");
			sb.append(str).append("</tr></form>");
			
			Document document = DocumentHelper.parseText(sb.toString().replaceAll("<input>", "").replaceAll("&nbsp", "")
					.replaceAll("<!--tzbox>", "").replaceAll("<INPUT>", "").replaceAll("<!-->", ""));
			Element root = document.getRootElement();
			List<Node> nodes = root.elements("tr");
			
			int index = 1;
			for(Node node : nodes) {
				if(index<(15)){
					FLData fldata = new FLData();
					Element element = (Element)node;
					List<Node> tbs = element.elements("td");
					fldata.setNum(tbs.get(0).getText().trim());
					fldata.setHTeam(((Element)tbs.get(1)).element("a").getText().trim());
					fldata.setVTeam(((Element)tbs.get(3)).element("a").getText().trim());
					fldata.setName(tbs.get(4).getText().split("\\|")[0]);
					fldata.setDate("2012-"+tbs.get(4).getText().split("\\|")[1]);
					String s1 = ((Element)tbs.get(1)).element("span").getText();
					String s2 = ((Element)tbs.get(2)).element("span").getText();
					String s3 = ((Element)tbs.get(3)).element("span").getText();
					String avg = s1.substring(0, s1.length()-1).trim()+"|"
								+s2.substring(0, s1.length()-1).trim()+"|"
								+s3.substring(0, s1.length()-1).trim();
					fldata.setAvgOdds(avg);
					datas.add(fldata);
				}
				index ++;
			}
		}catch(Exception e){
			e.printStackTrace();
			datas = null;
		}
		return datas;
	}
	*/
	/**
	 * 将html标签去掉<>中的属性，改为可转换xml的格式
	 * @param target
	 * @return
	 */
	public static String find(String target){
		
		int a = -1;//第一个'<'出现的位置
		int b = -1;//第一个'>'出现的位置
		int c = -1;//第二个'<'出现的位置
		int d = -1;//'<>'括起来的部分里第一个' '出现的位置
		String word = null;
		StringBuffer sb = new StringBuffer();
		String str = null;
		String tempStr = null;
		while(( a = target.indexOf("<"))!=-1){
			b = target.indexOf(">");
			str = target.substring(a+1,target.length());
			c = str.indexOf("<")+a+1;
			if(c<=b){
				tempStr = target.substring(b+1,target.length());
				target = target.substring(a,c);
				target = target+tempStr;
				
			}else{
				word = target.substring(a+1,b);
				d = word.indexOf(" ");
				if(d == -1){
					sb.append(target.substring(0,b+1));
				}else{
					sb.append(target.substring(0,a+d+1)).append(">");
				}
				target = target.substring(b+1,target.length());
			}
		}
		
		return sb.toString();
	}
	
}


