package com.ruyicai.mgr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJson
@RooJavaBean
@RooToString
@Entity
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "DNAPAY", identifierField = "transactionid")
public class Dnapay {
	@Id
	@Column(name = "TRANSACTIONID")
	private String transactionid;
	
	@Column(name = "USERNO")
	private String userno;
	
	@Column(name = "MOBILEID")
	private String mobileid;
		
	@Column(name = "AMT")
	private String amt;
		
	@Column(name = "CHARGETIME")
	private Date chargetime;
	
	@Column(name = "NOTIFYTIME")
	private Date notifytime;
	
	@Column(name = "STATE")
	private String state;
		
	@Column(name = "RETCODE")
	private String retcode;
	
	@Column(name = "RETMEMO")
	private String retmemo;
	
	@Column(name = "MEMO")
	private String memo;		
}
