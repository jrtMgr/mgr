package com.ruyicai.mgr.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TORDERFORM", schema = "JRTSCH",identifierField="id")
public class Torderform {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigDecimal id;
	
	@Column(name = "ORDERID")
	private String orderid;
	
	@Column(name = "FLOWNO")
	private String flowno;
	
	@Column(name = "TYPE")
	private BigDecimal type;
}
