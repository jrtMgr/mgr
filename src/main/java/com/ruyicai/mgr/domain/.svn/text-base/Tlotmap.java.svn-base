package com.ruyicai.mgr.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.consts.Const;

@RooJson
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TLOTMAP", schema = "JRTSCH", identifierType= TlotmapPK.class)
public class Tlotmap implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Column(name = "realflowno")
	private String realflowno;
	@Column(name = "betflag")
	private int betflag;
	@Column(name = "betnoticenum")
	private int betnoticenum;
	@Column(name = "winflag")
	private int winflag;
	@Column(name = "winnoticenum")
	private int winnoitcenum;
	@Column(name = "state")
	private int state;
	@Column(name="errorcode")
	private String errorcode;

	public String getErrorcode() {
		return errorcode;
	}

	public void setErrorcode(String errorcode) {
		this.errorcode = errorcode;
	}

	public String getRealflowno() {
		return realflowno;
	}

	public void setRealflowno(String realflowno) {
		this.realflowno = realflowno;
	}

	public int getBetflag() {
		return betflag;
	}

	public void setBetflag(int betflag) {
		this.betflag = betflag;
	}

	public int getBetnoticenum() {
		return betnoticenum;
	}

	public void setBetnoticenum(int betnoticenum) {
		this.betnoticenum = betnoticenum;
	}

	public int getWinflag() {
		return winflag;
	}

	public void setWinflag(int winflag) {
		this.winflag = winflag;
	}

	public int getWinnoitcenum() {
		return winnoitcenum;
	}

	public void setWinnoitcenum(int winnoitcenum) {
		this.winnoitcenum = winnoitcenum;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public static List<Tlot> findTlotByTlotmap(String agencyflowno){
		return Tlot
				.entityManager()
				.createQuery(
						"select o from Tlot o, Tlotmap t where t.id.agencyflowno = ? and o.torderid = t.realflowno order by o.ordertime desc",
						Tlot.class)
				.setParameter(1, agencyflowno).getResultList();
	}
	
	public static List<Tlotmap> findTlotmapByTlot(String flowno){
		return Tlotmap
				.entityManager()
				.createQuery(
						"select t from Tlot o, Tlotmap t where o.flowno = ? and o.torderid = t.realflowno order by o.ordertime desc",
						Tlotmap.class)
				.setParameter(1, flowno).getResultList();
	}
	
	public static List<Tlotmap> findTlotmapByAgencyflowno(String agencyflowno){
		return Tlotmap
				.entityManager()
				.createQuery(
						"select t from  Tlotmap t where t.id.agencyflowno = ?",
						Tlotmap.class)
				.setParameter(1, agencyflowno).getResultList();
	}
	public static long findNotbetCount(){
		TypedQuery<Long> total=Tlotmap.entityManager().createQuery("select count(*)  from Tlotmap t where realflowno is null and inserttime between sysdate-2 and sysdate",Long.class);
		return total.getSingleResult();
	}
}
