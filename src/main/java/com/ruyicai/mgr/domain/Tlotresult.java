package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.Date;
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
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TLOTRESULT", schema = "JRTSCH", identifierField = "id")
public class Tlotresult {

	@Id
	@GeneratedValue(generator = Const.IdGeneratorName)
	@GenericGenerator(name = Const.IdGeneratorName, strategy = Const.IdStrategy, //
	parameters = {
			@Parameter(name = Const.Seq_prefix, value = "LR"),
			@Parameter(name = Const.Seq_fmtWidth, value = "14"),
			@Parameter(name = TableGenerator.SEGMENT_COLUMN_PARAM, value = "ID"),
			@Parameter(name = TableGenerator.SEGMENT_VALUE_PARAM, value = "lotresult"),
			@Parameter(name = TableGenerator.VALUE_COLUMN_PARAM, value = "SEQ"),
			@Parameter(name = TableGenerator.TABLE_PARAM, value = "TSEQ") })
	@Column(name = "ID")
	private String id;

	@Column(name = "FLOWNO")
	private String flowno;

	@Column(name = "AGENCYNO")
	private String agencyno;

	@Column(name = "LOTNO")
	private String lotno;

	@Column(name = "BATCHCODE")
	private String batchcode;

	@Column(name = "BETCODE")
	private String betcode;

	@Column(name = "AMT")
	private BigDecimal amt;

	@Column(name = "ORDERTIME")
	private Date ordertime;

	@Column(name = "ERRORCODE")
	private String errorcode;

	@Column(name = "STATE")
	private BigDecimal state;

	public static Tlotresult createTlotresult(Tlot tlot, String errorcode,
			BigDecimal state) {
		Tlotresult tlotresult = new Tlotresult();
		tlotresult.setFlowno(tlot.getFlowno());
		tlotresult.setAgencyno(tlot.getAgencyno());
		tlotresult.setLotno(tlot.getLotno());
		tlotresult.setBatchcode(tlot.getBatchcode());
		tlotresult.setBetcode(tlot.getCurrentbetcode());
		tlotresult.setAmt(tlot.getAmt());
		tlotresult.setOrdertime(new Date());
		tlotresult.setErrorcode(errorcode);
		tlotresult.setState(state);
		tlotresult.persist();
		return tlotresult;
	}

	public static Tlotresult findLast(String flowno) {
		TypedQuery<Tlotresult> query = Tlotresult
				.entityManager()
				.createQuery(
						"select o from Tlotresult o where flowno=? order by ordertime desc",
						Tlotresult.class).setParameter(1, flowno)
				.setMaxResults(1);
		List<Tlotresult> results = query.getResultList();
		return results.isEmpty() ? null : results.get(0);
	}

	@SuppressWarnings("unchecked")
	public static List<String> findAgencynos(String flowno) {
		Query query = Tlotresult
				.entityManager()
				.createQuery(
						"select distinct agencyno from Tlotresult o where flowno=?")
				.setParameter(1, flowno);
		return query.getResultList();
	}

	public static List<Tlotresult> findByFlownoAndState(String flowno,
			BigDecimal state) {
		return Tlotresult
				.entityManager()
				.createQuery(
						"select o from Tlotresult o where flowno=? and state=?",
						Tlotresult.class).setParameter(1, flowno)
				.setParameter(2, state).getResultList();
	}
	
	public static Tlotresult createTlotresult(Tlot tlot) {
		List<Tlotresult> results = Tlotresult.findByFlownoAndState(tlot.getFlowno(), BigDecimal.ONE);
		if(!results.isEmpty()) {
			return null;
		}
		Tlotresult tlotresult = createTlotresult(tlot, " ", new BigDecimal(2));
		tlot.setTlotresultid(tlotresult.getId());
		return tlotresult;
	}
}
