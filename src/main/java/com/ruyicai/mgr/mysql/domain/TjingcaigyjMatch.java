package com.ruyicai.mgr.mysql.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.util.Page;


@RooJson
@RooToString
@RooEntity(identifierField = "id", versionField="", table="tjingcaigyjmatch", persistenceUnit="clientPersistenceUnit", transactionManager="newsTransactionManager")
public class TjingcaigyjMatch {
	
	@Id
	@Column(name = "id")
	private long id;
	
	@Column(name = "saishi")
	private String saishi;
	
	@Column(name = "type")
	private BigDecimal type;
	
	@Column(name = "number")
	private String  number;
	
	@Column(name = "team")
	private String  team;
	
	@Column(name = "state")
	private BigDecimal state;
	
	@Column(name = "award")
	private String  award;
	
	@Column(name = "popularityRating")
	private String popularityRating;
	
	@Column(name = "probability")
	private String  probability;

	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getSaishi() {
		return saishi;
	}


	public void setSaishi(String saishi) {
		this.saishi = saishi;
	}


	public BigDecimal getType() {
		return type;
	}


	public void setType(BigDecimal type) {
		this.type = type;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getTeam() {
		return team;
	}


	public void setTeam(String team) {
		this.team = team;
	}


	public BigDecimal getState() {
		return state;
	}


	public void setState(BigDecimal state) {
		this.state = state;
	}


	public String getAward() {
		return award;
	}


	public void setAward(String award) {
		this.award = award;
	}


	public String getPopularityRating() {
		return popularityRating;
	}


	public void setPopularityRating(String popularityRating) {
		this.popularityRating = popularityRating;
	}


	public String getProbability() {
		return probability;
	}


	public void setProbability(String probability) {
		this.probability = probability;
	}


	public static void findList(String where, String orderby,
			List<Object> params, Page<TjingcaigyjMatch> page) {
		TypedQuery<TjingcaigyjMatch> q = TjingcaigyjMatch.entityManager().createQuery(
				"SELECT o FROM TjingcaigyjMatch o " + where + orderby, TjingcaigyjMatch.class);
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
		TypedQuery<Long> totalQ = TjingcaigyjMatch.entityManager().createQuery(
				"select count(o.id) from TjingcaigyjMatch o " + where, Long.class);
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
