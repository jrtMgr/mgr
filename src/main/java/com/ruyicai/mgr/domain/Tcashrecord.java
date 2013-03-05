package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.util.Page;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TCASHRECORD", schema = "JRTSCH", identifierField = "id", identifierColumn = "ID")
public class Tcashrecord {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigDecimal id;
	
	@Column(name = "DCDATE")
	private Date dcdate;
	
	@Column(name = "AMT")
	private BigDecimal amt;
	
	public static void findList(Page<Tcashrecord> page) {
		TypedQuery<Tcashrecord> q = Tcashrecord.entityManager().createQuery(
				"select o from Tcashrecord o order by o.dcdate desc",  Tcashrecord.class);
		q.setFirstResult(page.getPageIndex() * page.getMaxResult())
				.setMaxResults(page.getMaxResult());
		page.setList(q.getResultList());
		TypedQuery<Long> totalQ = Tcashrecord.entityManager().createQuery(
				"select count(o) from Tcashrecord o ", Long.class);
		page.setTotalResult(totalQ.getSingleResult().intValue());
	}
}
