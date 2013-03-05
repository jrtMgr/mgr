package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.util.Page;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", identifierType = CompositePK.class, versionField = "", table = "TLOTCENTERDUIZHANG", schema = "JRTSCH")
public class TlotcenterDuizhang {

	@EmbeddedId
	private CompositePK id;
	
	@Column(name = "LOTCENTERAMT")
	public BigDecimal lotcenteramt;
	
	@Column(name = "AMT")
	public BigDecimal amt;
	
	@Column(name = "LOTCENTERPRIZEAMT")
	public BigDecimal lotcenterprizeamt;
	
	@Column(name = "PRIZEAMT")
	public BigDecimal prizeamt;
	
	@Column(name = "MEMO")
	public String memo;
	
	@Column(name = "SELLSTATE")
	public BigDecimal sellstate;
	
	@Column(name = "ENCASHSTATE")
	public BigDecimal encashstate;
	
	@Column(name = "CREATETIME")
	public Date createtime;
	
	public static void findList(String where, String orderby,
			List<Object> params, Page<TlotcenterDuizhang> page) {
		TypedQuery<TlotcenterDuizhang> q = TlotcenterDuizhang.entityManager().createQuery(
				"SELECT o FROM TlotcenterDuizhang o " + where + orderby, TlotcenterDuizhang.class);
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
		
		TypedQuery<Long> totalQ = Tlot.entityManager().createQuery(
				"select count(o.lotcenteramt) from TlotcenterDuizhang o " + where, Long.class);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				totalQ.setParameter(index, param);
				index = index + 1;
			}
		}
		page.setTotalResult(totalQ.getSingleResult().intValue());
		
		
		
		
		
		Query query = Tcashdetail.entityManager().createQuery("select sum(o.lotcenteramt), sum(o.amt),sum(o.lotcenterprizeamt),sum(o.prizeamt) from TlotcenterDuizhang o " + where);
		if(null != params && !params.isEmpty()) {
			int index = 1;
			for(Object param : params) {
				query.setParameter(index, param);
				index = index + 1;
			}
		}
		List<Object[]> results = (List<Object[]>)query.getResultList();
		page.setValue(results.get(0));
	}
}
