package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TLOTSTRATEGY", schema = "JRTSCH", identifierField = "id")
public class Tlotstrategy {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private long id;

	@Column(name = "USERNO")
	private String userno;

	@Column(name = "CHANNEL")
	private String channel;

	@Column(name = "LOTNO")
	private String lotno;

	@Column(name = "AGENCYNO")
	private String agencyno;
	
	@Column(name = "PLAYTYPE")
	private String playtype;
	
	@Column(name = "STATE")
	private int state;
	
	@Column(name = "AMT")
	private BigDecimal amt;
	public static List<Tlotstrategy> findAllTlotstrategysByState() {
        return entityManager().createQuery("SELECT o FROM Tlotstrategy o where o.state = 1", Tlotstrategy.class).getResultList();
    }
	
	public static Tlotstrategy findTlotstrategysById() {
        return entityManager().createQuery("SELECT o FROM Tlotstrategy o where o.id = -1", Tlotstrategy.class).getResultList().get(0);
    }

}