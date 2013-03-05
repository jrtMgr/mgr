package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

import com.ruyicai.mgr.exception.RuyicaiException;
import com.ruyicai.mgr.util.DateUtil;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.PaySign;

@RooJavaBean
@RooToString
@RooJson
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TACCOUNT", schema = "JRTSCH", identifierField = "userno", identifierColumn = "USERNO")
@RooDbManaged(automaticallyDelete = true)
public class Taccount {

	@Id
	@Column(name = "USERNO")
	private String userno;

	@Column(name = "BALANCE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal balance = new BigDecimal(0);

	@Column(name = "DRAWBALANCE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal drawbalance = new BigDecimal(0);

	@Column(name = "LASTBETAMT", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal lastbetamt = new BigDecimal(0);

	@Column(name = "LASTBETTIME", columnDefinition = "TIMESTAMP(6)")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date lastbettime = DateUtil.get1000Date();

	@Column(name = "TOTALBETAMT", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal totalbetamt = new BigDecimal(0);

	@Column(name = "LASTPRIZEAMT", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal lastprizeamt = new BigDecimal(0);

	@Column(name = "LASTPRIZETIME", columnDefinition = "TIMESTAMP(6)")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date lastprizetime = DateUtil.get1000Date();

	@Column(name = "TOTALPRIZEAMT", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal totalprizeamt = new BigDecimal(0);

	@Column(name = "LASTDEPOSITAMT", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal lastdepositamt = new BigDecimal(0);

	@Column(name = "LASTDEPOSITTIME", columnDefinition = "TIMESTAMP(6)")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date lastdeposittime = DateUtil.get1000Date();

	@Column(name = "TOTALDEPOSITAMT", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal totaldepositamt = new BigDecimal(0);

	@Column(name = "LASTDRAWAMT", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal lastdrawamt = new BigDecimal(0);

	@Column(name = "LASTDRAWTIME", columnDefinition = "TIMESTAMP(6)")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date lastdrawtime = DateUtil.get1000Date();

	@Column(name = "TOTALDRAWAMT", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal totaldrawamt = new BigDecimal(0);

	@Column(name = "MAC", columnDefinition = "VARCHAR2", length = 256)
	@NotNull
	private String mac = " ";

	@Column(name = "FREEZEBALANCE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal freezebalance = new BigDecimal(0);

	@Transactional
	public Taccount merge() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		this.setMac(this.taccountSign());
		Taccount merged = this.entityManager.merge(this);
		this.entityManager.flush();
		return merged;
	}

	@Transactional
	public void persist() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		this.entityManager.persist(this);
		this.entityManager.flush();
		Taccount account = Taccount.findTaccount(this.getUserno());
		account.merge();
	}

	public String taccountSign() {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer inStr = new StringBuffer();
		inStr.append(this.getUserno()).append(this.getBalance().longValue())
				.append(this.getDrawbalance().longValue())
				.append(this.getLastbetamt().longValue())
				.append(sFormat.format(this.getLastbettime()))
				.append(this.getTotalbetamt().longValue())
				.append(this.getLastprizeamt().longValue())
				.append(sFormat.format(this.getLastprizetime()))
				.append(this.getTotalprizeamt().longValue())
				.append(this.getLastdepositamt().longValue())
				.append(sFormat.format(this.getLastdeposittime()))
				.append(this.getTotaldepositamt().longValue())
				.append(this.getLastdrawamt().longValue())
				.append(sFormat.format(this.getLastdrawtime()))
				.append(this.getTotaldrawamt().longValue());
		try {
			return PaySign.Md5(inStr.toString());
		} catch (Exception e) {

			Logger logger = Logger.getLogger(Taccount.class);
			logger.info("Taccount生成Mac错误");
			throw new RuyicaiException(ErrorCode.Data_MD5);
		}
	}
	
	public static BigDecimal findAllBalance() {
		return entityManager().createQuery("select sum(balance) from Taccount", BigDecimal.class).getSingleResult();
	}
}
