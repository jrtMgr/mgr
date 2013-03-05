package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.TableGenerator;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.consts.CashDetailState;
import com.ruyicai.mgr.consts.Const;
import com.ruyicai.mgr.util.Page;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TCASHDETAIL", schema = "JRTSCH", identifierField = "id")
@RooJson
public class Tcashdetail {

	@Id
	@GeneratedValue(generator = Const.IdGeneratorName)
	@GenericGenerator(name = Const.IdGeneratorName, strategy = Const.IdStrategy, 
	parameters = {
			@Parameter(name = Const.Seq_fmtWidth, value = "8"),
			@Parameter(name = TableGenerator.SEGMENT_COLUMN_PARAM, value = "ID"),
			@Parameter(name = TableGenerator.SEGMENT_VALUE_PARAM, value = "TcashdetailID"),
			@Parameter(name = TableGenerator.VALUE_COLUMN_PARAM, value = "SEQ"),
			@Parameter(name = TableGenerator.TABLE_PARAM, value = "TSEQ") })
	private String id;

	@Column(name = "USERNO")
	private String userno;

	@Column(name = "TTRANSACTIONID")
	private String ttransactionid;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PROVCODE")
	private String provcode;

	@Column(name = "AREACODE")
	private String areacode;

	@Column(name = "PROVNAME")
	private String provname;

	@Column(name = "AREANAME")
	private String areaname;

	@Column(name = "AMT")
	private BigDecimal amt;

	@Column(name = "FEE")
	private BigDecimal fee;

	@Column(name = "PLATTIME")
	private Date plattime;

	@Column(name = "BANKNAME")
	private String bankname;

	@Column(name = "BANKACCOUNT")
	private String bankaccount;

	@Column(name = "STATE")
	private BigDecimal state;

	@Column(name = "SUBBANKNAME")
	private String subbankname;
	
	@Column(name = "REJECTREASON")
	private String rejectReason;
	
	@Column(name = "TYPE")
	private BigDecimal type;
	
	@Column(name = "BATCHNO")
	private String batchno;
	
	@Column(name = "MODIFYTIME")
	private Date modifytime;
	
	public static List<Tcashdetail> getTcashdetailByUserNo(
			String userno) {

		List<Tcashdetail> resultList = Tcashdetail
				.entityManager()
				.createQuery(
						"select o from Tcashdetail o where o.userno=?  order by o.plattime desc ",Tcashdetail.class)
				.setParameter(1, userno)
				.getResultList();
		return resultList;
	}
	
	
	public static Tcashdetail getTcashdetailByTransactionId(
			String ttransactionid) {

		List<Tcashdetail> resultList = Tcashdetail
				.entityManager()
				.createQuery(
						"select o from Tcashdetail o where o.ttransactionid=? ",Tcashdetail.class)
				.setParameter(1, ttransactionid)
				.getResultList();
		
		return resultList.get(0);
	}

	public static void findAllCashDetail(String userno, String beginTime,
			String endTime,Page<Tcashdetail> page) {
		page.setList(Tcashdetail
		.entityManager()
		.createQuery(
				"select o from Tcashdetail o where to_char(o.plattime,'yyyymmdd')>=? and to_char(o.plattime,'yyyymmdd')<=?  and userno=?",
				Tcashdetail.class).setParameter(1, beginTime)
				.setParameter(2, endTime).setParameter(3, userno)
				.setFirstResult(page.getPageIndex())
				.setMaxResults(page.getMaxResult())
				.getResultList());
		
		TypedQuery<Long> total = Tcashdetail
		.entityManager()
		.createQuery(
				"select count(o) from Tcashdetail o where to_char(o.plattime,'yyyymmdd')>=? and to_char(o.plattime,'yyyymmdd')<=?  and userno=?"
				, Long.class).setParameter(1, beginTime)
				.setParameter(2, endTime).setParameter(3, userno);
		Integer totalRes = total.getSingleResult().intValue();
		page.setTotalResult(totalRes);
		Tsubscribe.entityManager().clear();
	}
	
	@SuppressWarnings("unchecked")
	public static void findList(String where, String orderby, List<Object> params, Page<Tcashdetail> page) {
		TypedQuery<Tcashdetail> q = Tcashdetail.entityManager()
				.createQuery(
						"SELECT o FROM Tcashdetail o " + where + orderby,
						Tcashdetail.class);
		if(null != params && !params.isEmpty()) {
			int index = 1;
			for(Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		q.setFirstResult(page.getPageIndex() * page.getMaxResult()).setMaxResults(page.getMaxResult());
		page.setList(q.getResultList());
		TypedQuery<Long> totalQ = Tcashdetail.entityManager().createQuery("select count(o) from Tcashdetail o " + where, Long.class);
		if(null != params && !params.isEmpty()) {
			int index = 1;
			for(Object param : params) {
				totalQ.setParameter(index, param);
				index = index + 1;
			}
		}
		page.setTotalResult(totalQ.getSingleResult().intValue());
		Query query = Tcashdetail.entityManager().createQuery("select nvl(sum(o.amt),0), nvl(sum(o.fee),0) from Tcashdetail o " + where);
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
	
	
	public static List<Tcashdetail> findList(String where, String orderby, List<Object> params) {
		TypedQuery<Tcashdetail> q = Tcashdetail.entityManager()
				.createQuery(
						"SELECT o FROM Tcashdetail o " + where + orderby,
						Tcashdetail.class);
		if(null != params && !params.isEmpty()) {
			int index = 1;
			for(Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		return q.getResultList();
	}
	
	public static BigDecimal findUserCashFee(String userno) {
		BigDecimal ret = entityManager().createQuery("select sum(fee) from Tcashdetail where userno=? and state=105", BigDecimal.class).setParameter(1, userno).getSingleResult();
		return ret == null? BigDecimal.ZERO : ret;
	}
	
	
	
	public static List<Tcashdetail> selectAuditedTcashDetail() {
		TypedQuery<Tcashdetail> q = Tcashdetail.entityManager()
				.createQuery("SELECT o FROM Tcashdetail o where o.state = 103 and o.type = 1 order by o.ttransactionid" ,Tcashdetail.class);
		return q.getResultList();
	}
	
	public static BigDecimal findUserCash(String userno) {
		BigDecimal ret = entityManager().createQuery("select sum(amt) from Tcashdetail where userno=? and state=105", BigDecimal.class).setParameter(1, userno).getSingleResult();
		return ret == null? BigDecimal.ZERO : ret;
	}
	
	public static BigDecimal findUserVerifyCash(String userno) {
		BigDecimal ret = entityManager().createQuery("select sum(amt) from Tcashdetail where userno=? and state in (1,103)", BigDecimal.class).setParameter(1, userno).getSingleResult();
		return ret == null? BigDecimal.ZERO : ret;
	} 
}
