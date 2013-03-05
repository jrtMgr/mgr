package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.TableGenerator;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.consts.Const;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TTMCTRL", schema = "JRTSCH", identifierField = "id")
public class Ttmctrl {

	@Id
	@GeneratedValue(generator = Const.IdGeneratorName)
	@GenericGenerator(name = Const.IdGeneratorName, strategy = Const.IdStrategy, //
	parameters = {
			@Parameter(name = Const.Seq_fmtWidth, value = "16"),
			@Parameter(name = TableGenerator.SEGMENT_COLUMN_PARAM, value = "ID"),
			@Parameter(name = TableGenerator.SEGMENT_VALUE_PARAM, value = "ttmctrlId"),
			@Parameter(name = TableGenerator.VALUE_COLUMN_PARAM, value = "SEQ"),
			@Parameter(name = TableGenerator.TABLE_PARAM, value = "TSEQ") })
	@Column(name = "ID")
	private String id;

	@Column(name = "AGENCYNO")
	private String agencyno;

	@Column(name = "LOTNO")
	private String lotno;

	@Column(name = "MACHINENO")
	private String machineno;

	@Column(name = "MACHINESTATE")
	private BigDecimal machinestate;

	@Column(name = "TOTALCOUNT")
	private BigDecimal totalcount;

	@Column(name = "TOTALAMT")
	private BigDecimal totalamt;

	@Column(name = "MAXCOUNT")
	private BigDecimal maxcount;

	@Column(name = "MAXAMT")
	private BigDecimal maxamt;
	
	public static List<String> findByAgencyno(String agencyno) {
		return entityManager().createQuery("select distinct o.machineno from Ttmctrl o where o.agencyno=? order by o.machineno asc", String.class)
			.setParameter(1, agencyno).getResultList();
	}
}
