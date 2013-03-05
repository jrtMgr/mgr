package com.ruyicai.mgr.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
@RooJson
@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TWITHDRAWREVIEW", schema = "JRTSCH", identifierField = "id", identifierColumn = "ID")
public class TwithdrawReview {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", columnDefinition = "NUMBER")
	private int id;
	
	@Column(name = "accountName", columnDefinition = "ACCOUNTNAME", length = 20)
	private String accountName;
	
	@Column(name = "transactionId", columnDefinition = "TRANSACTIONID", length = 32)
	private String transactionId;
	
	@Column(name = "accountNumber", columnDefinition = "ACCOUNTNUMBER", length = 32)
	private String accountNumber;
	
	@Column(name = "bankProvince", columnDefinition = "BANKPROVINCE", length = 64)
	private String bankProvince;
	
	@Column(name = "bankCity", columnDefinition = "BANKCITY", length = 64)
	private String bankCity;
	
	@Column(name = "bankName", columnDefinition = "BANKNAME", length = 100)
	private String bankName;
	
	@Column(name = "amt", columnDefinition = "NUMBER")
	private Double amt;
	
	@Column(name = "state", columnDefinition = "STATE", length = 20)
	private Integer state;
	
	@Column(name = "dateTime", columnDefinition = "TIMESTAMP(6)")
	private Date dateTime;
	
	@Column(name = "subBankName", columnDefinition = "SUBBANKNAME", length = 100)
	private String subBankName;
	

	public static List<TwithdrawReview> findTwithdrawReviewByTransactionId(String transactionId){
		TypedQuery<TwithdrawReview> q = TwithdrawReview.entityManager()
		.createQuery(
				"SELECT o FROM TwithdrawReview o where o.transactionId = ?" ,
				TwithdrawReview.class);
		q.setParameter(1, transactionId);
		return q.getResultList();
	}
}
