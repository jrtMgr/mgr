package com.ruyicai.mgr.domain;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.TableGenerator;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.format.annotation.DateTimeFormat;

import com.ruyicai.mgr.consts.Const;

import java.math.BigDecimal;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TFILERESULT", schema = "JRTSCH")
public class Tfileresult {
	@Id
	@GeneratedValue(generator = Const.IdGeneratorName)
	@GenericGenerator(name = Const.IdGeneratorName, strategy = Const.IdStrategy, //
	parameters = {
			@Parameter(name = Const.Seq_prefix, value = "FR"),
			@Parameter(name = Const.Seq_fmtWidth, value = "22"),
			@Parameter(name = Const.Seq_Date, value = "yyyyMMdd"),
			@Parameter(name = TableGenerator.SEGMENT_COLUMN_PARAM, value = "ID"),
			@Parameter(name = TableGenerator.SEGMENT_VALUE_PARAM, value = "TfileresultId"),
			@Parameter(name = TableGenerator.VALUE_COLUMN_PARAM, value = "SEQ"),
			@Parameter(name = TableGenerator.TABLE_PARAM, value = "TSEQ") })
	@Column(name = "ID")
	private String id;

	@Column(name = "PLATTIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date plattime;

	@Column(name = "FILEPATH")
	private String filepath;

	@Column(name = "STATE")
	private BigDecimal state;

	@Column(name = "TYPE")
	private BigDecimal type;

	@Column(name = "AGENCYNO")
	private String agencyno;

	public static Tfileresult createTfileresult(String filename, String agencyno) {
		Tfileresult tfileresult = new Tfileresult();
		tfileresult.setState(BigDecimal.ZERO);
		tfileresult.setFilepath(filename);
		tfileresult.setAgencyno(agencyno);
		tfileresult.setPlattime(new Date());
		tfileresult.setType(BigDecimal.ZERO);
		tfileresult.persist();
		return tfileresult;
	}

	public static List<Tfileresult> findByAgencynoAndFilepath(String filepath,
			String agencyno) {
		TypedQuery<Tfileresult> query = Tfileresult
				.entityManager()
				.createQuery(
						"select o from Tfileresult o where filepath=? and agencyno=?",
						Tfileresult.class).setParameter(1, filepath)
				.setParameter(2, agencyno);
		return query.getResultList();
	}
}
