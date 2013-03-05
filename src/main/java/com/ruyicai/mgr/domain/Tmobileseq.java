package com.ruyicai.mgr.domain;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TMOBILESEG", schema = "JRTSCH",identifierField="id")
public class Tmobileseq {
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "PROVCODE")
	private String provCode;
	
	@Column(name = "AREACODE")
	private String areaCode;
	
	@Column(name = "SIMTYPE")
	private String simType;
	
	@Column(name = "PROVNAME")
	private String provName;
	
	@Column(name = "AREANAME")
	private String areaName;
}
