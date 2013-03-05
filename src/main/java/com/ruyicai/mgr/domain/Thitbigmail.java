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
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "THITBIGMAIL", schema = "JRTSCH", identifierField = "id")
public class Thitbigmail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private BigDecimal id;

	@Column(name = "MAIL")
	private String mail;

	@Column(name = "TYPE")
	private BigDecimal type;

	@Column(name = "MEMO")
	private String memo;

	public static List<Thitbigmail> findBytype(BigDecimal type) {
		return Thitbigmail
				.entityManager()
				.createQuery("select o from Thitbigmail o where type=?",
						Thitbigmail.class).setParameter(1, type)
				.getResultList();
	}
}
