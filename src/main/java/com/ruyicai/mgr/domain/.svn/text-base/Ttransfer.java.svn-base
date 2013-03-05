package com.ruyicai.mgr.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.domain.Ttransfer;
import com.ruyicai.mgr.util.DateUtil;
import com.ruyicai.mgr.util.Page;

@RooJavaBean
@RooToString
@RooJson
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TTRANSFER", schema = "JRTSCH", identifierField = "userno", identifierType = String.class)
public class Ttransfer implements Serializable {
		private static final long serialVersionUID = 1L;
		transient Logger logger = LoggerFactory.getLogger(Ttransfer.class);
		
		@Id
		@Column(name = "USERNO")
		private String userno;
		
		@Column(name = "STATE")
		@NotNull
		private BigDecimal state = new BigDecimal(0);
		
		@Column(name = "CREATETIME", columnDefinition = "TIMESTAMP(6)")
		@Temporal(TemporalType.TIMESTAMP)
		@DateTimeFormat(style = "S-")
		private Date createtime = DateUtil.get1000Date();

		@Column(name = "MODIFYTIME", columnDefinition = "TIMESTAMP(6)")
		@Temporal(TemporalType.TIMESTAMP)
		@DateTimeFormat(style = "S-")
		private Date modifytime = DateUtil.get1000Date();
		
		
		@SuppressWarnings("unchecked")
		public static void findList(String where, String orderby, List<Object> params, Page<Ttransfer> page) {
			TypedQuery<Ttransfer> q = Ttransfer.entityManager()
					.createQuery(
							"SELECT o FROM Ttransfer o " + where + orderby,
							Ttransfer.class);
			if(null != params && !params.isEmpty()) {
				int index = 1;
				for(Object param : params) {
					q.setParameter(index, param);
					index = index + 1;
				}
			}
			q.setFirstResult(page.getPageIndex() * page.getMaxResult()).setMaxResults(page.getMaxResult());
			page.setList(q.getResultList());
			TypedQuery<Long> totalQ = Ttransfer.entityManager().createQuery("select count(o) from Ttransfer o " + where, Long.class);
			if(null != params && !params.isEmpty()) {
				int index = 1;
				for(Object param : params) {
					totalQ.setParameter(index, param);
					index = index + 1;
				}
			}
			page.setTotalResult(totalQ.getSingleResult().intValue());		
		}
		
}
