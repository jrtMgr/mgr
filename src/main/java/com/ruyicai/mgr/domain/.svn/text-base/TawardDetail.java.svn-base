package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooToString
@RooJson
@RooJavaBean
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TAWARDDETAIL", schema = "JRTSCH", identifierField = "id", identifierColumn = "ID")
public class TawardDetail {
	public static int STATE_AUDITING = 0;
	public static int STATE_AUDITED= 1;
	public static int STATE_ING= 2;
	public static int STATE_DEAD= 3;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private BigDecimal id;

	@Column(name = "LOTNO")
	private String lotno;
	
	@Column(name = "PRIZELEVEL")
	private String level;
	
	@Column(name = "STARTTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date starttime;
	
	@Column(name = "ENDTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date endtime;
	
	@Column(name = "ADDPRIZE")
	private long addprize;
	
	@Column(name = "USERNO")
	private String userno;
	
	@Column(name = "APPLYTIME")
	private Date applytime;
	
	@Column(name = "STATE")
	private long state;
	
	public static List<TawardDetail> findList(String where, String orderby, List<Object> params) {
		TypedQuery<TawardDetail> q = TawardDetail.entityManager().createQuery(
				"SELECT o FROM TawardDetail o " + where + orderby, TawardDetail.class);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		return q.getResultList();
	}
	
	public static List<TawardDetail> findAudited() {
		TypedQuery<TawardDetail> q = TawardDetail.entityManager().createQuery(
				"SELECT o FROM TawardDetail o where (o.state = 1 or o.state = 2)", TawardDetail.class);
		return q.getResultList();
	}
}
