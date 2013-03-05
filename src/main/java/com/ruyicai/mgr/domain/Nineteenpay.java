package com.ruyicai.mgr.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJson
@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "NINETEENPAY", identifierField = "transactionid")
public class Nineteenpay {
	@Id
	@Column(name = "TRANSACTIONID")
	private String transactionid;

	@Column(name = "USERNO")
	private String userno;

	@Column(name = "CARDNO")
	private String cardno;

	@Column(name = "CARDPWD")
	private String cardpwd;

	@Column(name = "AMT")
	private String amt;

	@Column(name = "TOTALAMT")
	private String totalamt;

	@Column(name = "balance")
	private String balance;

	@Column(name = "CARDTYPE")
	private String cardtype;

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
