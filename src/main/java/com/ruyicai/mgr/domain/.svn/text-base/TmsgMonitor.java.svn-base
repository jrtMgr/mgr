package com.ruyicai.mgr.domain;


import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", identifierType = MsgMonitorCompositePK.class, versionField = "", table = "TMSGMONITOR", schema = "JRTSCH")
public class TmsgMonitor {

	@Column(name = "STATE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal state;

	@Column(name = "TIME", columnDefinition = "NUMBER")
	private BigDecimal time;
	
	public static List<TmsgMonitor> findTmsgMonitors(String where, String orderby, List<Object> params) {
		TypedQuery<TmsgMonitor> q = TmsgMonitor.entityManager().createQuery("SELECT o FROM TmsgMonitor o " + where + orderby, TmsgMonitor.class);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		return q.getResultList();
	}

	public static List<TmsgMonitor> findAllTmsgMonitorsByType(int i) {
		return TmsgMonitor.entityManager().createQuery("select o from TmsgMonitor o where o.id.type = ? and o.state = 0",TmsgMonitor.class)
		.setParameter(1, new BigDecimal(i))
		.getResultList();
	}
}
