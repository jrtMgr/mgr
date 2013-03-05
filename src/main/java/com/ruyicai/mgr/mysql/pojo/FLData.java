package com.ruyicai.mgr.mysql.pojo;

import java.io.Serializable;

public class FLData implements Serializable{
	private String pid;
	private String num;
	private String name;
	private String HTeam;
	private String VTeam;
	private String date;
	private String avgOdds;
	private String leagueRank;
	private String HTeam8;
	private String VTeam8;
	private String result1;
	private String result2;
	private String result3;
	private String result4;
	private String flag;
	private String ou;
	private String ya;
	private String xi;
	private String ce;
	private String bsbfzd;//总比赛比分主队
	private String bsbfkd;//总比赛比分客队
	private String banchangzd;//半场主队比分
	private String banchangkd;//半场客队比分

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getResult1() {
		return result1;
	}

	public void setResult1(String result1) {
		this.result1 = result1;
	}

	public String getResult2() {
		return result2;
	}

	public void setResult2(String result2) {
		this.result2 = result2;
	}

	public String getResult3() {
		return result3;
	}

	public void setResult3(String result3) {
		this.result3 = result3;
	}

	public String getResult4() {
		return result4;
	}

	public void setResult4(String result4) {
		this.result4 = result4;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHTeam() {
		return HTeam;
	}

	public void setHTeam(String hTeam) {
		HTeam = hTeam;
	}

	public String getVTeam() {
		return VTeam;
	}

	public void setVTeam(String vTeam) {
		VTeam = vTeam;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAvgOdds() {
		return avgOdds;
	}

	public void setAvgOdds(String avgOdds) {
		this.avgOdds = avgOdds;
	}

	public String getLeagueRank() {
		return leagueRank;
	}

	public void setLeagueRank(String leagueRank) {
		this.leagueRank = leagueRank;
	}

	public String getHTeam8() {
		return HTeam8;
	}

	public void setHTeam8(String hTeam8) {
		HTeam8 = hTeam8;
	}

	public String getVTeam8() {
		return VTeam8;
	}

	public void setVTeam8(String vTeam8) {
		VTeam8 = vTeam8;
	}

	
	
	public String getOu() {
		return ou;
	}

	public void setOu(String ou) {
		this.ou = ou;
	}

	public String getYa() {
		return ya;
	}

	public void setYa(String ya) {
		this.ya = ya;
	}

	public String getXi() {
		return xi;
	}

	public void setXi(String xi) {
		this.xi = xi;
	}

	public String getCe() {
		return ce;
	}

	public void setCe(String ce) {
		this.ce = ce;
	}
	

	public String getBsbfzd() {
		return bsbfzd;
	}

	public void setBsbfzd(String bsbfzd) {
		this.bsbfzd = bsbfzd;
	}

	public String getBsbfkd() {
		return bsbfkd;
	}

	public void setBsbfkd(String bsbfkd) {
		this.bsbfkd = bsbfkd;
	}
	
	

	public String getBanchangzd() {
		return banchangzd;
	}

	public void setBanchangzd(String banchangzd) {
		this.banchangzd = banchangzd;
	}

	public String getBanchangkd() {
		return banchangkd;
	}

	public void setBanchangkd(String banchangkd) {
		this.banchangkd = banchangkd;
	}

	public String toLog(){
		return "pid="+this.getPid()+";num="+this.getNum()+";name="+this.getName()+";HTeam="+this.getHTeam()
					+";VTeam="+this.getVTeam()+";date="+this.getDate()+";avgOdds="+this.getAvgOdds()+";leagueRank="+this.getLeagueRank()
					+";HTeam8="+this.getHTeam8()+";VTeam8="+this.getVTeam8()+";result1="+this.getResult1()+";result2="+this.getResult2()+
					";result3="+this.getResult3()+";result4="+this.getResult4()+";bsbfzd="+this.getBsbfzd()+";bsbfkd="+this.getBsbfkd();
	}
}
