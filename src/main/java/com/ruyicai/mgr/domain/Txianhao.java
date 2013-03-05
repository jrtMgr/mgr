package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TXIANHAO", schema = "JRTSCH", identifierField = "id")
public class Txianhao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private BigDecimal id;

	@Column(name = "LOTNO")
	private String lotno;

	@Column(name = "AGENCYNO")
	private String agencyno;

	@Column(name = "BATCHCODE")
	private String batchcode;

	@Column(name = "UPDATETIME")
	private Date updatetime;

	@Column(name = "BETCODE")
	private String betcode;

	@Column(name = "COUNT")
	private BigDecimal count;

	@Column(name = "STATE")
	private BigDecimal state;

	public static Txianhao createTxianhao(String lotno, String agencyno,
			String batchcode, Date updatetime, String betcode, BigDecimal count) {
		Txianhao txianhao = new Txianhao();
		txianhao.setLotno(lotno);
		txianhao.setAgencyno(agencyno);
		txianhao.setBatchcode(batchcode);
		txianhao.setUpdatetime(updatetime);
		txianhao.setBetcode(betcode);
		txianhao.setCount(count);
		txianhao.setState(BigDecimal.ZERO);
		txianhao.persist();
		return txianhao;
	}

	public static void closeActive(String lotno, String agencyno) {
		Txianhao.entityManager()
				.createQuery(
						"update Txianhao o set o.state = 1 where o.lotno=? and o.agencyno=?")
				.setParameter(1, lotno).setParameter(2, agencyno)
				.executeUpdate();
	}
}
