package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.consts.SubaccountType;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TSUBACCOUNT", schema = "JRTSCH", identifierField = "id")
public class Tsubaccount {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private BigDecimal id;

	@Column(name = "USERNO")
	private String userno;

	@Column(name = "BALANCE")
	private BigDecimal balance = BigDecimal.ZERO;

	@Column(name = "DRAWBALANCE")
	private BigDecimal drawbalance = BigDecimal.ZERO;

	@Column(name = "FREEZEBALANCE")
	private BigDecimal freezebalance = BigDecimal.ZERO;

	@Column(name = "MAC")
	private String mac;

	@Column(name = "TYPE")
	private BigDecimal type = BigDecimal.ZERO;
	
	public static Tsubaccount findTsubaccount(String userno, SubaccountType type) {
		TypedQuery<Tsubaccount> query = Tlot
		.entityManager()
		.createQuery(
				"select o from Tsubaccount o where o.userno=? and o.type=?",
				Tsubaccount.class).setParameter(1, userno).setParameter(2, type.value());
		List<Tsubaccount> results = query.getResultList();
		if(results.isEmpty()) {
			return null;
		}
		return results.get(0);
	}
}
