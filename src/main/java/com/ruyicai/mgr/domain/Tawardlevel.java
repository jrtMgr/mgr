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
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooToString
@RooJson
@RooJavaBean
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TAWARDLEVEL", schema = "JRTSCH", identifierField = "id", identifierColumn = "ID")
public class Tawardlevel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private BigDecimal id;

	@Column(name = "LOTNO")
	private String lotno;
	
	@Column(name = "PRIZELEVEL")
	private String level;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "LEVELPRIZE")
	private long levelprize;
	
	@Column(name = "ADDPRIZE")
	private long addprize;
	
	public static List<Tawardlevel> findList(String where, String orderby, List<Object> params) {
		TypedQuery<Tawardlevel> q = Tawardlevel.entityManager().createQuery(
				"SELECT o FROM Tawardlevel o " + where + orderby, Tawardlevel.class);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		return q.getResultList();
	}
	
	public static Tawardlevel findTawardlevelByLotnoAndLevel(String lotno, String level) {
		TypedQuery<Tawardlevel> q = Tawardlevel.entityManager().createQuery(
				"SELECT o FROM Tawardlevel o where o.lotno = ? and o.level = ?", Tawardlevel.class)
				.setParameter(1, lotno).setParameter(2, level);
		return q.getSingleResult();
	}
}
