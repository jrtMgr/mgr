package com.ruyicai.mgr.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.TableGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.consts.Const;

@RooJavaBean
@RooToString
@RooJson
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TSUBCHANNEL", schema = "JRTSCH", identifierField = "agencyno", identifierColumn = "AGENCYNO")
public class Tsubchannel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "AGENCYNO")
	private String agencyno;
	
    @Column(name = "AGENCYNAME", columnDefinition = "VARCHAR2", length = 80)
    @NotNull
    private String agencyname;
    
    @Column(name = "TELEPHONE", columnDefinition = "VARCHAR2", length = 80)
    private String telephone;
    
    @Column(name = "ADDRESS", columnDefinition = "VARCHAR2", length = 1020)
    private String address;
    
    @Column(name = "REGTIME", columnDefinition = "TIMESTAMP(6)")
    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date regtime;
    
    @Column(name = "STATE", columnDefinition = "NUMBER")
    @NotNull
    private BigDecimal state;
    
    @Column(name = "MAC", columnDefinition = "VARCHAR2", length = 128)
    @NotNull
    private String mac;
    
    @Column(name = "NOTIFYURL", columnDefinition = "VARCHAR2", length = 400)
    private String notifyurl;
    
    @Column(name = "PRIVATEKEY", columnDefinition = "VARCHAR2")
    private String privatekey;
    
    @Column(name = "PUBVATEKEY", columnDefinition = "VARCHAR2")
    private String pubvatekey;
    
    @Column(name = "NOTICEPROTOCOL", columnDefinition = "VARCHAR2", length = 400)
    private String noticeprotocol;
    
    @Column(name = "PROTOCOL", columnDefinition = "VARCHAR2", length = 400)
    private String protocol;
    
    @Column(name = "USERID", columnDefinition = "VARCHAR2", length = 16)
    private String userid;	
    
    @Column(name = "SIYAO", columnDefinition = "VARCHAR2")
    private String siyao;
    
    public static List<Tsubchannel> findAllTsubchannels1() {
        return entityManager().createQuery("SELECT o FROM Tsubchannel o where o.state = 1", Tsubchannel.class).getResultList();
    }
}
