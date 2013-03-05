package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.TableGenerator;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.consts.Const;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit",versionField = "", table = "Tfeeformula", schema = "JRTSCH", identifierField = "id")
public class Tfeeformula {
	@Id
	@GeneratedValue(generator = Const.IdGeneratorName)
	@GenericGenerator(name = Const.IdGeneratorName, strategy = Const.IdStrategy, 
	parameters = {
			@Parameter(name = Const.Seq_fmtWidth, value = "8"),
			@Parameter(name = TableGenerator.SEGMENT_COLUMN_PARAM, value = "ID"),
			@Parameter(name = TableGenerator.SEGMENT_VALUE_PARAM, value = "FeeformulaID"),
			@Parameter(name = TableGenerator.VALUE_COLUMN_PARAM, value = "SEQ"),
			@Parameter(name = TableGenerator.TABLE_PARAM, value = "TSEQ") })
	private String id;
	@Column(name = "TTRANSACTIONID")
	private String ttransactionid;
	@Column(name = "NAME")
	private String name;
	@Column(name = "MEMO")
	private String memo;
	@Column(name = "FEERATE")
	@NotNull
	private BigDecimal feerate;
	@Column(name = "LOWERLIMIT")
	@NotNull
	private BigDecimal lowerlimit;
	@Column(name = "UPPERLIMIT")
	@NotNull
	private BigDecimal upperlimit;
	@Column(name = "REGTIME")
	@NotNull
	private Date regtime;
	@Column(name = "TLOGUSERID")
	@NotNull
	private String tloguserid;
	
	
	
	
	

}
