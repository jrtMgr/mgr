package com.ruyicai.mgr.domain;

import java.util.Date;
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
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "IPTABLES", schema = "JRTSCH")
@RooToString
public class Iptables {
	
	@Id
	@GeneratedValue(generator = Const.IdGeneratorName)
	@GenericGenerator(name = Const.IdGeneratorName, strategy = Const.IdStrategy, parameters = {
			@Parameter(name = Const.Seq_fmtWidth, value = "8"),
			@Parameter(name = TableGenerator.SEGMENT_COLUMN_PARAM, value = "ID"),
			@Parameter(name = TableGenerator.SEGMENT_VALUE_PARAM, value = "userNo"),
			@Parameter(name = TableGenerator.VALUE_COLUMN_PARAM, value = "SEQ"),
			@Parameter(name = TableGenerator.TABLE_PARAM, value = "TSEQ") })
	@Column(name="IPID")
    private String ipid;

	@Column(name="IPADDR")
    private String ipaddr;

	@Column(name="EXPIRETIME")
    private Date expiretime;

	@Column(name="STATE")
    private Short state;

	@Column(name="AGENCYNO")
    private String agencyno;

	public static List<Iptables> findIptablesByAgencyno(String agencyno) {
        return entityManager().createQuery("SELECT o FROM Iptables o where o.agencyno = ? and o.state = 1", Iptables.class)
        .setParameter(1, agencyno)
        .getResultList();
    }
}