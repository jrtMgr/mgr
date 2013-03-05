package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJson
@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", identifierType = CompositePK.class, versionField = "", table = "TWININFO", schema = "JRTSCH")
public class Twininfo {

	@Column(name = "PLAYNAME", columnDefinition = "VARCHAR2", length = 10)
	private String playname = " ";

	@Column(name = "WINBASECODE", columnDefinition = "VARCHAR2", length = 50)
	private String winbasecode = " ";

	@Column(name = "WINSPECIALCODE", columnDefinition = "VARCHAR2", length = 10)
	private String winspecialcode = " ";

	@Column(name = "ACTSELLAMT", columnDefinition = "NUMBER")
	private BigDecimal actsellamt = new BigDecimal(0);

	@Column(name = "VALIDSELLAMT", columnDefinition = "NUMBER")
	private BigDecimal validsellamt = new BigDecimal(0);

	@Column(name = "WINGRADE", columnDefinition = "NUMBER")
	private BigDecimal wingrade = new BigDecimal(0);

	@Column(name = "WINMONEY", columnDefinition = "NUMBER")
	private BigDecimal winmoney = new BigDecimal(0);

	@Column(name = "WINNUMBER", columnDefinition = "NUMBER")
	private BigDecimal winnumber = new BigDecimal(0);

	@Column(name = "FORWARDAMT", columnDefinition = "NUMBER")
	private BigDecimal forwardamt = new BigDecimal(0);

	@Column(name = "INFO")
	private String info;

	@Column(name = "STATE")
	private BigDecimal state;

	@Column(name = "AGENCYOPENTIME")
	private Date agencyopentime;

	public static List<Twininfo> findAllTwininfoes(String where, List<Object> params) {
        TypedQuery<Twininfo> q = Twininfo.entityManager()
				.createQuery(
						"SELECT o FROM Twininfo o " + where,
						Twininfo.class);
		if(null != params && !params.isEmpty()) {
			int index = 1;
			for(Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		 return q.getResultList();
	}
}
