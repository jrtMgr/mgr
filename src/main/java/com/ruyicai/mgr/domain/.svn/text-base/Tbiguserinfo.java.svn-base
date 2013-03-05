package com.ruyicai.mgr.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJson
@RooEntity(persistenceUnit = "persistenceUnit", table = "TBIGUSERINFO", schema = "JRTSCH", versionField = "", identifierField = "userbs", identifierColumn = "USERNO", finders = {
		"findTbiguserBySubAndUserbs"})
public class Tbiguserinfo implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private BigDecimal id;
	
	@Column(name = "USERNO")
	@NotNull
	private String userno;

	@Column(name = "TYPE", columnDefinition = "VARCHAR2", length = 8)
	@NotNull
	private String type;

	@Column(name = "OUTUSERNO", columnDefinition = "VARCHAR2", length = 32)
	@NotNull
	private String outuserno;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getUserno() {
		return userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOutuserno() {
		return outuserno;
	}

	public void setOutuserno(String outuserno) {
		this.outuserno = outuserno;
	}
	
	public static List<Tbiguserinfo> findTbiguserByTypeAndOutuserno(String type, String outuserno) {
        
    	TypedQuery<Tbiguserinfo> query=Twininfo.entityManager().createQuery("select o from Tbiguserinfo o where type=? and outuserno=?", Tbiguserinfo.class);
    	query.setParameter(1, type);
    	query.setParameter(2, outuserno);
		List<Tbiguserinfo> results = query.getResultList();
		return results;
    }
	
	public static List<Tbiguserinfo> findTbiguserByTypeAndUserno(String type, String userno) {
        
    	TypedQuery<Tbiguserinfo> query=Twininfo.entityManager().createQuery("select o from Tbiguserinfo o where type=? and userno=?", Tbiguserinfo.class);
    	query.setParameter(1, type);
    	query.setParameter(2, userno);
		List<Tbiguserinfo> results = query.getResultList();
		return results;
    }
}
