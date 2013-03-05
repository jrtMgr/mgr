package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.TableGenerator;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.consts.Const;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TGIFT", schema = "JRTSCH", identifierField = "id")
public class Tgift {
	
	@Id
	@GeneratedValue(generator = Const.IdGeneratorName)
	@GenericGenerator(name = Const.IdGeneratorName, strategy = Const.IdStrategy, parameters = {
			@Parameter(name = Const.Seq_fmtWidth, value = "8"),
			@Parameter(name = TableGenerator.SEGMENT_COLUMN_PARAM, value = "ID"),
			@Parameter(name = TableGenerator.SEGMENT_VALUE_PARAM, value = "TgiftID"),
			@Parameter(name = TableGenerator.VALUE_COLUMN_PARAM, value = "SEQ"),
			@Parameter(name = TableGenerator.TABLE_PARAM, value = "TSEQ") })
	@Column(name = "ID")
	private String id;
	
	@Column(name = "BIGUSERNO")
	private String biguserno;
	
	@Column(name = "SMALLUSERNO")
	private String smalluserno;
	
	@Column(name = "FLAG")
	private BigDecimal flag;
	
	@Column(name = "FLOWNO")
	private String flowno;
	
	@Column(name = "TGIFTAUDITID")
	private String tgiftauditid;
	
	public static List<Tgift> findByflowno(String flowno) {
		TypedQuery<Tgift> query = Tlot
				.entityManager()
				.createQuery(
						"select o from Tgift o where o.flowno=?",
						Tgift.class).setParameter(1, flowno);
		return query.getResultList();
	}
	
	public static List<Tgift> findByTgiftauditid(String tgiftauditid, BigDecimal flag, int sendmun) {
		TypedQuery<Tgift> query = Tgift
				.entityManager()
				.createQuery(
						"select o from Tgift o where o.tgiftauditid=? and o.flag=? ",
						Tgift.class).setParameter(1, tgiftauditid).setParameter(2, flag).setFirstResult(0).setMaxResults(sendmun);
		return query.getResultList();
	}
	
	public static int findByTgifCount(String tgiftauditid, BigDecimal flag) {
		TypedQuery<Long> totalQ = Tgift.entityManager().createQuery(
				"select count(o) from Tgift o where o.tgiftauditid=? and o.flag=? ", Long.class)
				.setParameter(1, tgiftauditid).setParameter(2, flag);
		return totalQ.getSingleResult().intValue();
	}
}
