package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.log4j.Logger;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.domain.Talibatchpay;

@RooJson
@RooJavaBean
@RooToString
@Entity
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TALIBATCHPAY", identifierField = "transactionid")
public class Talibatchpay {
	transient private static Logger logger = Logger.getLogger(Talibatchpay.class);
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "DETAILDATA")
	private String detailData;
	
	@Column(name = "BATCHNUM")
	private String batchNum = BigDecimal.ZERO.toString();
	
	@Column(name = "BATCHFEE")
	private String batchFee = BigDecimal.ZERO.toString();
	
	@Column(name = "PAYDATE")
	private Date payDate;
	
	@Column(name = "NOTIFYTIME")
	private Date notifyTime;
	
	@Column(name = "NOTIFYTYPE")
	private String notifyType;
	
	@Column(name = "NOTIFYID")
	private String notifyID;
	
	@Column(name = "SIGNTYPE")
	private String signType;
	
	@Column(name = "SUCCESSDETAILS")
	private String successDetails;
	
	@Column(name = "FAILDETAILS")
	private String failDetails;
	
	@Column(name = "STATE")
	private BigDecimal state;
}
