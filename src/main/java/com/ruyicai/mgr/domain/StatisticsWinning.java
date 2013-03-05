package com.ruyicai.mgr.domain;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TSTATISTICSWINNING", schema = "JRTSCH")
@RooJson
public class StatisticsWinning {
	
	public static String DAYRANKING = "DayRanking";//日统计
	public static String MONTHRANKING = "MonthRanking";//月统计
	public static String TOTALRANKING = "TotalRanking";//总统计 
	public static String LATESTWINNING = "LatestWin";//最新购买
	
	@Id
	@Column(name = "NAME")
    private String name;
	
	@Column(name = "VALUE")
    private String value;
	
}
