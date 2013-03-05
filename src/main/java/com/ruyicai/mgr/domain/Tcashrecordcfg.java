package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TCASHRECORDCFG", schema = "JRTSCH", identifierField = "id", identifierColumn = "ID")
public class Tcashrecordcfg {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigDecimal id;
	   
	@Column(name = "CASHRECORDID")
	private BigDecimal cashrecordid;
	
	@Column(name = "TTRANSACTIONID")
	private String ttransactionid;
	
	@Column(name = "NUM")
	private int num;
	
	@Column(name = "AMT")
	private BigDecimal amt;
	
	@Column(name = "NAME")
	private String name;
	
	 public static List<Tcashrecordcfg> findTcashrecordcfgsByCashrecordid(BigDecimal cashrecordid) {
        return entityManager().createQuery("SELECT o FROM Tcashrecordcfg o where o.cashrecordid = ? order by o.num", Tcashrecordcfg.class)
        .setParameter(1,cashrecordid)
        .getResultList();
    }
}
