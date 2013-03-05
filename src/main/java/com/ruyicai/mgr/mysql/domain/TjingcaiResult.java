package com.ruyicai.mgr.mysql.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.util.Page;

@RooToString
@RooJavaBean
@RooEntity(identifierField = "id", versionField="", table="tjingcairesult", persistenceUnit="clientPersistenceUnit", transactionManager="newsTransactionManager")
public class TjingcaiResult {

	@Id
	private String id;
	
	@Column(name="cancel")
	private BigDecimal cancel;
	
	@Column(name="letpoint")
	private String letpoint;
	
	@Column(name="basepoint")
	private String basepoint;
	
	@Column(name="result")
	private String result;
	
	@Column(name="firsthalfresult")
	private String firsthalfresult;
	
	@Column(name="b0")
	private String b0;
	
	@Column(name="b1")
	private String b1;
	
	@Column(name="b2")
	private String b2;
	
	@Column(name="b3")
	private String b3;
	
	@Column(name="b4")
	private String b4;
	
	@Column(name="b5")
	private String b5;
	
	@Column(name="b6")
	private String b6;
	
	/**
	 * 0审核   1未审核 
	 */
	@Column(name = "audit")
	private BigDecimal audit;
	
	public static void findList(String where, String orderby,
			List<Object> params, Page<TjingcaiResult> page) {
		TypedQuery<TjingcaiResult> q = TjingcaiResult.entityManager().createQuery(
				"SELECT o FROM TjingcaiResult o " + where + orderby, TjingcaiResult.class);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		q.setFirstResult(page.getPageIndex() * page.getMaxResult())
				.setMaxResults(page.getMaxResult());
		page.setList(q.getResultList());
		TypedQuery<Long> totalQ = TjingcaiResult.entityManager().createQuery(
				"select count(o.id) from TjingcaiResult o " + where, Long.class);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				totalQ.setParameter(index, param);
				index = index + 1;
			}
		}
		page.setTotalResult(totalQ.getSingleResult().intValue());
	}
	
}
