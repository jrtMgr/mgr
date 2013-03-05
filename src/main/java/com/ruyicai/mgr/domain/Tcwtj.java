package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJson
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TCWTJ", schema = "JRTSCH", identifierField = "id", identifierColumn = "ID")
public class Tcwtj {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private BigDecimal id;

	private Date tjdate;

	private BigDecimal usercharge;

	private BigDecimal channelcharge;

	private BigDecimal encash;

	private BigDecimal activitypersent;

	private BigDecimal chargepersent;
	
	private BigDecimal cannel;

	private BigDecimal inhj;

	private BigDecimal userbet;

	private BigDecimal channelbet;

	private BigDecimal draw;

	private BigDecimal fee;

	private BigDecimal other1;

	private BigDecimal outhj;

	private BigDecimal balance;

	private String memo;

	public static List<Tcwtj> findByDate(String date) {
		return entityManager()
				.createQuery(
						"select o from Tcwtj o where to_char(o.tjdate, 'yyyy-mm') = ? order by o.tjdate asc",
						Tcwtj.class).setParameter(1, date).getResultList();
	}
}
