package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", identifierType = CompositePK.class, versionField = "", table = "TLOTCTRL", schema = "JRTSCH")
public class Tlotctrl {

	@Column(name = "STARTTIME", columnDefinition = "TIMESTAMP(6)")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date starttime;

	@Column(name = "ENDTIME", columnDefinition = "TIMESTAMP(6)")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date endtime;

	@Column(name = "STATE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal state;

	@Column(name = "CREATETIME")
	private Date createtime;
	
	@Column(name = "ENDBETTIME")
	private Date endbettime;

	@Column(name = "HEMAIENDTIME")
	private Date hemaiendtime;
	
	@Column(name = "ENCASHSTATE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal encashstate;
	
	
	public static List<Tlotctrl> get10Tlotctrls(String lotno) {
		return Tlotctrl
				.entityManager()
				.createQuery(
						"select o from Tlotctrl o where o.id.lotno=? and o.id.agencyno = 'R00001' and o.state!=-1 order by o.id.batchcode desc",
						Tlotctrl.class).setParameter(1, lotno).setMaxResults(10).getResultList();
	}
	/**
	 *
	 * @param lotno
	 * @return
	 */
	public static List<Tlotctrl> getTlotctrls() {
		return Tlotctrl
				.entityManager()
				.createQuery(
						"select o from Tlotctrl o where o.state=0 and o.id.agencyno = 'R00001'",
						Tlotctrl.class).getResultList();
	}
	
	public static List<Tlotctrl> findTlotctrls(String lotno) {
		return Tlotctrl
		.entityManager()
		.createQuery(
				"select o from Tlotctrl o where o.id.lotno=? and o.id.agencyno = 'R00001' and o.state!=-1 order by o.id.batchcode desc",
				Tlotctrl.class).setParameter(1, lotno).setMaxResults(50).getResultList();
	}
	public static List<Tlotctrl> findpreIssueTlotctrls(String where, String orderby, List<Object> params) {
		TypedQuery<Tlotctrl> q = Tlot.entityManager().createQuery(
				"SELECT o FROM Tlotctrl o " + where + orderby, Tlotctrl.class);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		return q.getResultList();
	}
	
	/**
	 *
	 * @param lotno
	 * @return
	 */
	public static Tlotctrl findCurrentTlotctrls(String lotno){
		return Tlotctrl
				.entityManager()
				.createQuery(
						"select o from Tlotctrl o where o.state=0 and o.id.agencyno = 'R00001' and o.id.lotno=?",
						Tlotctrl.class).setParameter(1, lotno).getSingleResult();
	}
}
