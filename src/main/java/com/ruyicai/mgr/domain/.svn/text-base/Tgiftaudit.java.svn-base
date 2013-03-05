package com.ruyicai.mgr.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.TableGenerator;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.consts.Const;
import com.ruyicai.mgr.util.Page;


@RooJavaBean
@RooToString
@RooJson
@RooEntity(persistenceUnit = "persistenceUnit", table = "TGIFTAUDIT", schema = "JRTSCH", versionField = "", identifierField = "id", identifierColumn = "ID")
public class Tgiftaudit {
	@Id
	@GeneratedValue(generator = Const.IdGeneratorName)
	@GenericGenerator(name = Const.IdGeneratorName, strategy = Const.IdStrategy, parameters = {
			@Parameter(name = Const.Seq_fmtWidth, value = "16"),
			@Parameter(name = TableGenerator.SEGMENT_COLUMN_PARAM, value = "ID"),
			@Parameter(name = TableGenerator.SEGMENT_VALUE_PARAM, value = "TgiftauditID"),
			@Parameter(name = TableGenerator.VALUE_COLUMN_PARAM, value = "SEQ"),
			@Parameter(name = TableGenerator.TABLE_PARAM, value = "TSEQ") })
	@Column(name = "ID")
    private String id;
	
	@Column(name = "BIGUSERNO")
    private String biguserno;
	
	@Column(name = "BIGNAME")
    private String bigname;
	
	@Column(name = "ALLAMT")
    private Long allamt;
	
	@Column(name = "FLOWNO")
    private String flowno;
	
	@Column(name = "FLAG")
    private Integer flag;
	
	@Column(name = "LOTNO")
    private String lotno;
	
	@Column(name = "BETNUM")
    private Long betnum;
	
	@Column(name = "SUCCESS")
    private Long success;
	
	@Column(name = "FAILURE")
    private Long failure;
	
	@Column(name = "AGETIME")
    private Date agetime;
	
	@Column(name = "AFTERTIME")
    private Date aftertime;
	
	@Column(name = "SMSCONTENT")
    private String smscontent;
	
	@Column(name = "AGENCYCODE")
    private String agencycode;
	
	public static List<Tgiftaudit> findAllTgiftauditsByFlag(int flag) {
        return entityManager().createQuery("SELECT o FROM Tgiftaudit o where o.flag = ? order by o.agetime desc", Tgiftaudit.class).setParameter(1, flag)
        .getResultList();
    }
	public static void findAllTgiftauditsByFlag(Page<Tgiftaudit> page, int flag) {
		TypedQuery<Tgiftaudit> q = Tgiftaudit.entityManager().createQuery(
				"select o from Tgiftaudit o where o.flag = ? order by o.agetime desc",  Tgiftaudit.class);
		q.setParameter(1, flag);
		q.setFirstResult(page.getPageIndex() * page.getMaxResult())
				.setMaxResults(page.getMaxResult());
		page.setList(q.getResultList());
		TypedQuery<Long> totalQ = Tgiftaudit.entityManager().createQuery(
				"select count(o) from Tgiftaudit o where o.flag = ? ", Long.class);
		totalQ.setParameter(1, flag);
		page.setTotalResult(totalQ.getSingleResult().intValue());
	}
}