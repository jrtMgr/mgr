package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

import org.apache.log4j.Logger;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

import com.ruyicai.mgr.exception.RuyicaiException;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.PaySign;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TPLATACCOUNT", schema = "JRTSCH", identifierField = "agencyno")
public class Tplataccount {

	@Id
	@Column(name = "AGENCYNO")
	private String agencyno;

	@Column(name = "PAYAMT")
	private BigDecimal payamt;

	@Column(name = "REGTIME")
	private Date regtime;

	@Column(name = "MODTIME")
	private Date modtime;

	@Column(name = "EARNAMT")
	private BigDecimal earnamt;

	@Column(name = "LASTPAYAMT")
	private BigDecimal lastpayamt;

	@Column(name = "TOTALPAYAMT")
	private BigDecimal totalpayamt;

	@Column(name = "LASTEARNAMT")
	private BigDecimal lastearnamt;

	@Column(name = "TOTALEARNAMT")
	private BigDecimal totalearnamt;

	@Column(name = "LASTSTLTIME")
	private Date laststltime;

	@Column(name = "MAC")
	private String mac;

	@Column(name = "TYPE")
	private BigDecimal type;

	@Column(name = "AGENTNO")
	private String agentno;
	
	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.setMac(this.tplataccountSign());
        this.entityManager.persist(this);
    }
	
	@Transactional
    public Tplataccount merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.setMac(this.tplataccountSign());
        Tplataccount merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
	
	public String tplataccountSign() {
		
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append(this.getAgencyno())
		.append(this.getPayamt().longValue())
		.append(sFormat.format(this.getRegtime()))
		.append(sFormat.format(this.getModtime()))
		.append(this.getEarnamt().longValue())
		.append(this.getLastpayamt().longValue())
		.append(this.getTotalpayamt().longValue())
		.append(this.getLastearnamt().longValue())
		.append(this.getTotalearnamt().longValue())
		.append(sFormat.format(this.getLaststltime()));
		
		try {
			
			return PaySign.Md5(builder.toString());
		} catch (Exception e) {
			
			Logger logger = Logger.getLogger(Tplataccount.class);
			logger.info("Tplataccount生成Mac错误");
			throw new RuyicaiException(ErrorCode.MAC_ERROR);
		}
	}
}
