package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.List;

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
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TMENUNEW", schema = "JRTSCH", identifierField = "id")
public class Tmenu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigDecimal id;

	private BigDecimal inid;

	private String name;

	private String url;

	public static List<Tmenu> findByUserid(String userid) {
		return Tmenu
				.entityManager()
				.createQuery(
						"select distinct o from Tmenu o, Tpermission p where o.inid = p.menuinid and p.userid=? and p.state = 1",
						Tmenu.class).setParameter(1, userid).getResultList();
	}
}
