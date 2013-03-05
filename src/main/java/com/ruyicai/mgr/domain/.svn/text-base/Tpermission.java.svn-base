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
import org.springframework.transaction.annotation.Transactional;

@RooJavaBean
@RooToString
@RooJson
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TPERMISSION", schema = "JRTSCH", identifierField = "id", finders = { "findTpermissionsByUserid" })
public class Tpermission {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigDecimal id;

	private BigDecimal menuinid;

	private String userid;

	private BigDecimal state;
	
	@Transactional
	public static void updateTpermission(String userid) {
		List<Tpermission> tpermissions = Tpermission.findTpermissionsByUserid(
				userid).getResultList();
		for (Tpermission tpermission : tpermissions) {
			tpermission.setState(BigDecimal.ZERO);
			tpermission.merge();
		}
	}

	public static Tpermission findByInidAndUserid(BigDecimal menuinid,
			String userid) {
		List<Tpermission> tpermissions = Tpermission
				.entityManager()
				.createQuery(
						"select o from Tpermission o where o.menuinid=? and o.userid=?",
						Tpermission.class).setParameter(1, menuinid)
				.setParameter(2, userid).getResultList();
		return tpermissions.isEmpty() ? null : tpermissions.get(0);
	}
	
	public static List<Tpermission> findByInid(BigDecimal menuinid) {
		return Tpermission.entityManager().createQuery(
						"select o from Tpermission o where o.menuinid=? and state = 1",
						Tpermission.class).setParameter(1, menuinid).getResultList();
	}
	
	public static List<Tpermission> findByUseridAndState(String userid,
			BigDecimal state) {
		return Tpermission
				.entityManager()
				.createQuery(
						"select o from Tpermission o where o.userid=? and o.state=?",
						Tpermission.class).setParameter(1, userid)
				.setParameter(2, state).getResultList();
	}
}
