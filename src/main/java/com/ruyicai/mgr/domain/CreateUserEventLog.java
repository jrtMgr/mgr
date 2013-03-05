package com.ruyicai.mgr.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "CREATEUSEREVENTLOG", schema = "JRTSCH")
@RooToString
public class CreateUserEventLog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ID")
	private String id;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="MEMO")
	private String memo;
	
	@Column(name="CREATETIME")
	private String createtime;

}
