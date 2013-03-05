package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TORG_ACCOUNT", schema = "JRTSCH", identifierField = "id")
@RooJson
public class TorgAccount {
	@Id
	@Column(name="ID", columnDefinition = "VARCHAR2(20)")
	private String id;
	
	@Column(name="USERNAME", columnDefinition = "VARCHAR2(20)")
	@NotNull
	private String userName;
	
	@Column(name="PASSWORD", columnDefinition = "VARCHAR2(64)")
	@NotNull
	private String passWord;
	
	@Column(name="ACCOUNTID", columnDefinition = "VARCHAR2(11)")
	@NotNull
	private String accountId;
	
	@Column(name="BRANCHID", columnDefinition = "VARCHAR2(8)")
	@NotNull
	private String branchId;
	
	@Column(name="PRESTR", columnDefinition = "VARCHAR2(30)")
	@NotNull
	private String prestr;
	
	@Column(name="STATUS", columnDefinition = "NUMBER(1)")
	@NotNull
	private BigDecimal status; 
	
	@Column(name="INFO", columnDefinition = "VARCHAR2(64)")
	private String info;
	
	@Column(name = "REGTIME", columnDefinition = "TIMESTAMP(6)")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date regTime;
	
	@Column(name = "LOGTIME", columnDefinition = "TIMESTAMP(6)")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date logTime;
	
	@Column(name="REALNAME", columnDefinition="VARCHAR2", length = 20)
	@NotNull
	private String realName;
	
	@Column(name="EMAIL", columnDefinition="VARCHAR2", length = 50)
	private String email;
	
	@Column(name="MOBILE", columnDefinition="VARCHAR2", length = 11)
	@NotNull
	private String mobile;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getPrestr() {
		return prestr;
	}

	public void setPrestr(String prestr) {
		this.prestr = prestr;
	}

	public BigDecimal getStatus() {
		return status;
	}

	public void setStatus(BigDecimal status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public static List<TorgAccount> findTorgAccountByAccountId(String accountId) {
		TypedQuery<TorgAccount> query = TorgAccount
				.entityManager()
				.createQuery(
						"select o from Torg_account o where accountid=?",
						TorgAccount.class);
		query.setParameter(1, accountId);
		return query.getResultList();
	}
}
