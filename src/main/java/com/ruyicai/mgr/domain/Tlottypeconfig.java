package com.ruyicai.mgr.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TLOTTYPECONFIG", schema = "JRTSCH")
public class Tlottypeconfig {

	@Id
	@Column(name = "LOTNO")
	private String lotno;

	@Column(name = "NAME")
	private String name;

	@Column(name = "STATE")
	private BigDecimal state;

	@Column(name = "LVL")
	private BigDecimal lvl;

	@Column(name = "ONPRIZE")
	private int onprize;

	@Column(name = "AUTOENCASH")
	private BigDecimal autoencash;
	
	@Column(name = "LOTCENTERISVALID")
	private BigDecimal lotcenterisvalid;
	
	@Column(name = "BETENDTIME")
	private String betendtime;
	
	@Column(name = "HEMAIBETENDTIME")
	private String hemaibetendtime;
	/*public static List<Tlottypeconfig> findTlottype(String name, int state) {

		TypedQuery<Tlottypeconfig> query = Tlottypeconfig.entityManager().createQuery(
				"select o from Tlottype o where o.id.id=? and state=?",
				Tlottypeconfig.class);
		query.setParameter(1, name);
		query.setParameter(2, new BigDecimal(state));
		return query.getResultList();
	}

	public static List<Tlottypeconfig> findTlottypesByAgencyNo(String agencyNo) {
		TypedQuery<Tlottypeconfig> query = Tlottypeconfig.entityManager().createQuery(
				"select o from Tlottype o where agencyNo=?", Tlottypeconfig.class);
		query.setParameter(1, agencyNo);
		return query.getResultList();
	}

	public static List<Tlottypeconfig> findByNameAndAgencyno(String name,
			String agencyno) {
		TypedQuery<Tlottypeconfig> query = Tlottypeconfig.entityManager().createQuery(
				"select o from Tlottype o where name=? and agencyNo=?",
				Tlottypeconfig.class);
		query.setParameter(1, name).setParameter(2, agencyno);
		return query.getResultList();
	}

	public static List<Tlottypeconfig> findByLotno(String lotno) {
		return Tlottypeconfig
				.entityManager()
				.createQuery("select o from Tlottype o where o.id.id=?",
						Tlottypeconfig.class).setParameter(1, lotno).getResultList();
	}
	
	@Transactional
	public int update(String id, int onprize, BigDecimal autoencash) {
		String sql = "update Tlottype o set o.onprize=?, o.autoencash=? where o.id=?";
		int num = entityManager().createNativeQuery(sql)
				.setParameter(1, onprize).setParameter(2, autoencash)
				.setParameter(3, id).executeUpdate();
		return num;
	}  */
}
