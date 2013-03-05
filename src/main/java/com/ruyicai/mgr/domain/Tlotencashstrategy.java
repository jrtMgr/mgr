package com.ruyicai.mgr.domain;

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
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TLOTENCASHSTRATEGY", schema = "JRTSCH", identifierField = "id")
public class Tlotencashstrategy {

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

	@Column(name = "STATE")
	private int state;
	
	@Column(name = "AMT")
	private int amt;
	public static List<Tlotencashstrategy> findAllByState() {
        return entityManager().createQuery("SELECT o FROM Tlotencashstrategy o where o.state = 1", Tlotencashstrategy.class).getResultList();
    }

}