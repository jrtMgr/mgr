package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", identifierType = TlottypePK.class, versionField = "", table = "TLOTTYPE", schema = "JRTSCH")
public class Tlottype {

	@Column(name = "NAME")
	private String name;

	@Column(name = "CITYCODE")
	private String cityCode;

	@Column(name = "CNAME")
	private String cname;

	@Column(name = "STATE")
	private BigDecimal state;

	@Column(name = "LVL")
	private BigDecimal lvl;

	@Column(name = "PROVCODE")
	private String provCode;

	@Column(name = "BETENDLIMIT")
	private BigDecimal betendlimit;
	
	@Column(name = "ONPRIZE")
	private Integer onprize;
	
	@Column(name = "AUTOENCASH")
	private BigDecimal autoencash;

	public static List<Tlottype> findTlottype(String name, int state) {

		TypedQuery<Tlottype> query = Tlottype.entityManager().createQuery(
				"select o from Tlottype o where o.id.id=? and state=?",
				Tlottype.class);
		query.setParameter(1, name);
		query.setParameter(2, new BigDecimal(state));
		return query.getResultList();
	}

	public static List<Tlottype> findTlottypesByAgencyNo(String agencyNo) {
		TypedQuery<Tlottype> query = Tlottype.entityManager().createQuery(
				"select o from Tlottype o where agencyNo=?", Tlottype.class);
		query.setParameter(1, agencyNo);
		return query.getResultList();
	}

	public static List<Tlottype> findByNameAndAgencyno(String name,
			String agencyno) {
		TypedQuery<Tlottype> query = Tlottype.entityManager().createQuery(
				"select o from Tlottype o where name=? and agencyNo=?",
				Tlottype.class);
		query.setParameter(1, name).setParameter(2, agencyno);
		return query.getResultList();
	}

	public static List<Tlottype> findByLotno(String lotno) {
		return Tlottype
				.entityManager()
				.createQuery("select o from Tlottype o where o.id.id=?",
						Tlottype.class).setParameter(1, lotno).getResultList();
	}
	
	@Transactional
	public int update(String id, int onprize, BigDecimal autoencash) {
		String sql = "update Tlottype o set o.onprize=?, o.autoencash=? where o.id=?";
		int num = entityManager().createNativeQuery(sql)
				.setParameter(1, onprize).setParameter(2, autoencash)
				.setParameter(3, id).executeUpdate();
		return num;
	}  
}
