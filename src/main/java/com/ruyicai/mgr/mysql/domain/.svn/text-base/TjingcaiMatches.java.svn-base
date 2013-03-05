package com.ruyicai.mgr.mysql.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.domain.Tdnabind;
import com.ruyicai.mgr.domain.Tlot;
import com.ruyicai.mgr.util.Page;


@RooJson
@RooToString
@RooEntity(identifierType = MatchesCompositePK.class, versionField="", table="tjingcaimatches", persistenceUnit="clientPersistenceUnit", transactionManager="newsTransactionManager")
public class TjingcaiMatches {
	
	@Column(name = "endtime")
	private Date endtime;
	
	@Column(name = "saleflag")
	private BigDecimal saleflag;
	
	@Column(name = "state")
	private BigDecimal state;
	
	@Column(name = "league")
	private String league;
	
	@Column(name = "team")
	private String team;
	
	@Column(name = "time")
	private Date time;

	@Column(name = "unsupport")
	private String unsupport;
	
	@Column(name = "shortname")
	private String shortname;
	
	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	/**
	 * 0审核   1未审核 
	 */
	@Column(name = "audit")
	private BigDecimal audit;
	
	public BigDecimal getAudit() {
		return audit;
	}

	public void setAudit(BigDecimal audit) {
		this.audit = audit;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public BigDecimal getSaleflag() {
		return saleflag;
	}

	public void setSaleflag(BigDecimal saleflag) {
		this.saleflag = saleflag;
	}

	public BigDecimal getState() {
		return state;
	}

	public void setState(BigDecimal state) {
		this.state = state;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getUnsupport() {
		return unsupport;
	}

	public void setUnsupport(String unsupport) {
		this.unsupport = unsupport;
	}

	public static void findList(String where, String orderby,
			List<Object> params, Page<TjingcaiMatches> page) {
		TypedQuery<TjingcaiMatches> q = TjingcaiMatches.entityManager().createQuery(
				"SELECT o FROM TjingcaiMatches o " + where + orderby, TjingcaiMatches.class);
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
		TypedQuery<Long> totalQ = TjingcaiMatches.entityManager().createQuery(
				"select count(o.id.type) from TjingcaiMatches o " + where, Long.class);
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
