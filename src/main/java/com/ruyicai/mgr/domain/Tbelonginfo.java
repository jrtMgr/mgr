package com.ruyicai.mgr.domain;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TBELONGINFO", schema = "JRTSCH",identifierField="provCode")
public class Tbelonginfo {
	
	@Id
	@Column(name = "PROVCODE")
	private String provCode;
	
	@Column(name = "PROVNAME")
	private String provName;
	
	@Column(name = "BPROVCODE")
	private String bprovCode;
	
	@Column(name = "BPROVNAME")
	private String bprovName;
}
