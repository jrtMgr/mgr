package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.util.DateUtil;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TUSERCHANNEL", schema = "JRTSCH", identifierColumn="MOBILEID", identifierField="mobileid")
@RooDbManaged(automaticallyDelete = true)
public class Tuserchannel {
	
	@Id
    @Column(name = "MOBILEID")
    private String mobileid;
	
	@Column(name = "STATE", columnDefinition = "NUMBER")
    @NotNull
    private BigDecimal state = new BigDecimal(0);
    
    @Column(name = "USERFROM", columnDefinition = "NUMBER")
    @NotNull
    private BigDecimal userfrom = new BigDecimal(0);
    
    @Column(name = "USERNO", columnDefinition = "VARCHAR2", length = 32)
    @NotNull
    private String userno = " ";
    
    @Column(name = "AGENCYNO", columnDefinition = "VARCHAR2", length = 24)
    @NotNull
    private String agencyno = " ";
    
    @Column(name = "PLATTIME", columnDefinition = "TIMESTAMP(6)")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date plattime = DateUtil.get1000Date();
    
    @Column(name = "INFO", columnDefinition = "VARCHAR2", length = 400)
    @NotNull
    private String info = " ";

}