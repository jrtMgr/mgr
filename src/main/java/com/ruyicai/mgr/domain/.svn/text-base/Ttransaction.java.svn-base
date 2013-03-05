package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.TableGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.consts.Const;
import com.ruyicai.mgr.util.Page;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TTRANSACTION", schema = "JRTSCH")
@RooDbManaged(automaticallyDelete = true)
public class Ttransaction {
	@Id
	@GeneratedValue(generator = Const.IdGeneratorName)
	@GenericGenerator(name = Const.IdGeneratorName, strategy = Const.IdStrategy, //
	parameters = {
			@Parameter(name = Const.Seq_Date, value = "yyyyMMdd"),
			@Parameter(name = Const.Seq_fmtWidth, value = "24"),
			@Parameter(name = TableGenerator.SEGMENT_COLUMN_PARAM, value = "ID"),
			@Parameter(name = TableGenerator.SEGMENT_VALUE_PARAM, value = "ttransactionId"),
			@Parameter(name = TableGenerator.VALUE_COLUMN_PARAM, value = "SEQ"),
			@Parameter(name = TableGenerator.TABLE_PARAM, value = "TSEQ") })
	@Column(name = "ID")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "TYPE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal type;

	@Column(name = "USERNO", columnDefinition = "VARCHAR2", length = 32)
	@NotNull
	private String userno;

	@Column(name = "ACCEPTNO", columnDefinition = "VARCHAR2", length = 32)
	@NotNull
	private String acceptno;

	@Column(name = "FLOWNO", columnDefinition = "VARCHAR2", length = 64)
	@NotNull
	private String flowno;

	@Column(name = "PLATTIME", columnDefinition = "TIMESTAMP(6)")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date plattime;

	@Column(name = "AMT", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal amt;

	@Column(name = "FEE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal fee;

	@Column(name = "BANKID", columnDefinition = "VARCHAR2", length = 32)
	private String bankid;

	@Column(name = "BANKACCOUNT", columnDefinition = "VARCHAR2", length = 128)
	@NotNull
	private String bankaccount;

	@Column(name = "BANKORDERID", columnDefinition = "VARCHAR2", length = 128)
	@NotNull
	private String bankorderid;

	@Column(name = "BANKORDERTIME", columnDefinition = "TIMESTAMP(6)")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date bankordertime;

	@Column(name = "BANKTRACE", columnDefinition = "VARCHAR2", length = 128)
	@NotNull
	private String banktrace;

	@Column(name = "BANKRETTIME", columnDefinition = "TIMESTAMP(6)")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date bankrettime;

	@Column(name = "STATE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal state;

	@Column(name = "RETCODE", columnDefinition = "VARCHAR2", length = 32)
	@NotNull
	private String retcode;

	@Column(name = "RETMEMO", columnDefinition = "VARCHAR2", length = 256)
	@NotNull
	private String retmemo;

	@Column(name = "MEMO", columnDefinition = "VARCHAR2", length = 512)
	private String memo;

	@Column(name = "BANKCHECK", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal bankcheck;

	@Column(name = "PAYTYPE", columnDefinition = "VARCHAR2", length = 16)
	@NotNull
	private String paytype;

	@Column(name = "ACCESSTYPE", columnDefinition = "CHAR")
	@NotNull
	private Character accesstype;
	
	@Column(name = "CHANNEL")
	private String channel;

	@Column(name = "SUBCHANNEL")
	private String subchannel;

	@SuppressWarnings("unchecked")
	public static void findList(String where, String orderby, List<Object> params, Page<Ttransaction> page) {
		TypedQuery<Ttransaction> q = Ttransaction.entityManager()
				.createQuery(
						"SELECT o FROM Ttransaction o " + where + orderby,
						Ttransaction.class);
		if(null != params && !params.isEmpty()) {
			int index = 1;
			for(Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		q.setFirstResult(page.getPageIndex() * page.getMaxResult()).setMaxResults(page.getMaxResult());
		page.setList(q.getResultList());
		TypedQuery<Long> totalQ = Ttransaction.entityManager().createQuery("select count(o) from Ttransaction o " + where, Long.class);
		if(null != params && !params.isEmpty()) {
			int index = 1;
			for(Object param : params) {
				totalQ.setParameter(index, param);
				index = index + 1;
			}
		}
		page.setTotalResult(totalQ.getSingleResult().intValue());
		Query query = Ttransaction.entityManager().createQuery("select nvl(sum(o.amt),0), nvl(sum(o.fee),0) from Ttransaction o " + where);
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
	
	@SuppressWarnings("unchecked")
	public static List<Ttransaction> findList(String where, String orderby, List<Object> params) {
		TypedQuery<Ttransaction> q = Ttransaction.entityManager().createQuery(
						"SELECT o FROM Ttransaction o " + where + orderby, Ttransaction.class);
		if(null != params && !params.isEmpty()) {
			int index = 1;
			for(Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		return q.getResultList();
	}
	
	public static BigDecimal findAllUserCharge(String date) {
		return entityManager().createQuery("select sum(amt) from Ttransaction where to_char(plattime,'yyyy-mm-dd')=? and type in (2,3,10) and state=1", BigDecimal.class).setParameter(1, date).getSingleResult();
	}
	
	public static BigDecimal findAllChannelCharge(String date) {
		return entityManager().createQuery("select sum(amt) from Ttransaction where to_char(plattime,'yyyy-mm-dd')=? and type = 9 and state=1", BigDecimal.class).setParameter(1, date).getSingleResult();
	}
	
	public static BigDecimal findAllEncash(String date) {
		return entityManager().createQuery("select sum(amt) from Ttransaction where to_char(plattime,'yyyy-mm-dd')=? and type = 6 and state=1", BigDecimal.class).setParameter(1, date).getSingleResult();
	}
	
	public static BigDecimal findAllActivitypersent(String date) {
		return entityManager().createQuery("select sum(amt) from Ttransaction where to_char(plattime,'yyyy-mm-dd')=? and type in (14, 23) and state=1", BigDecimal.class).setParameter(1, date).getSingleResult();
	}
	
	public static BigDecimal findAllChargepersent(String date) {
		return entityManager().createQuery("select sum(amt) from Ttransaction where to_char(plattime,'yyyy-mm-dd')=? and type in (12, 13) and state=1", BigDecimal.class).setParameter(1, date).getSingleResult();
	}
	
	public static BigDecimal findAllUserbet(String date) {
		return entityManager().createQuery("select sum(ad.amt) from Taccountdetail ad where exists (select o.id from Ttransaction o where to_char(plattime,'yyyy-mm-dd')=? and blsign=-1 and ad.userno=o.userno and type in (1, 8) and o.id=ad.ttransactionid and not exists (select c.agencyno from Tsubchannel c where c.agencyno = o.userno))", BigDecimal.class).setParameter(1, date).getSingleResult();
	}
	
	public static BigDecimal findAllChannelbet(String date) {
		return entityManager().createQuery("select sum(ad.amt) from Taccountdetail ad where exists (select o.id from Ttransaction o where to_char(plattime,'yyyy-mm-dd')=? and blsign=-1 and ad.userno=o.userno and type in (1, 8) and o.id=ad.ttransactionid and exists (select c.agencyno from Tsubchannel c where c.agencyno = o.userno))", BigDecimal.class).setParameter(1, date).getSingleResult();
	}
	
	public static BigDecimal findAllDraw(String date) {
		return entityManager().createQuery("select sum(amt) from Tcashdetail o where to_char(plattime,'yyyy-mm-dd')=? and state in (1,103,105)", BigDecimal.class).setParameter(1, date).getSingleResult();
	}
	
	public static BigDecimal findAllFee(String date) {
		BigDecimal feecharge = entityManager().createQuery("select sum(fee) from Ttransaction where to_char(plattime,'yyyy-mm-dd')=? and type in (2,3,10) and state=1", BigDecimal.class).setParameter(1, date).getSingleResult();
		feecharge = null == feecharge ? BigDecimal.ZERO : feecharge;
		BigDecimal feedraw = entityManager().createQuery("select sum(fee) from Tcashdetail o where to_char(plattime,'yyyy-mm-dd')=? and state in (1,103,105)", BigDecimal.class).setParameter(1, date).getSingleResult();
		feedraw = null == feedraw ? BigDecimal.ZERO : feedraw;
		return feecharge.add(feedraw);
	}
	
	public static BigDecimal findAllCannel(String date) {
		BigDecimal ret = entityManager().createQuery("select sum(ad.amt) from Taccountdetail ad where exists (select o.id from Ttransaction o where exists(select a.id from Taccountdetail a where to_char(a.plattime,'yyyy-mm-dd')=? and o.id=a.ttransactionid) and blsign=1 and ad.userno=o.userno and type in (1, 8) and o.id=ad.ttransactionid)", BigDecimal.class).setParameter(1, date).getSingleResult();
		return ret == null? BigDecimal.ZERO : ret;
	}
	
	public static BigDecimal findUserCharge(String userno) {
		BigDecimal ret = entityManager().createQuery("select sum(amt) from Ttransaction where userno=? and type in (2,3,10) and state=1", BigDecimal.class).setParameter(1, userno).getSingleResult();
		return ret == null? BigDecimal.ZERO : ret;
	}
	
	public static BigDecimal findUserChargeFee(String userno) {
		BigDecimal ret = entityManager().createQuery("select sum(fee) from Ttransaction where userno=? and type in (2,3,10) and state=1", BigDecimal.class).setParameter(1, userno).getSingleResult();
		return ret == null? BigDecimal.ZERO : ret;
	}

	public static List<Ttransaction> findTransactionByTypeAndState(BigDecimal type, BigDecimal state) {
		TypedQuery<Ttransaction> q = entityManager().createQuery("select t from Ttransaction t where type=? and state=?", Ttransaction.class)
		.setParameter(1, type).setParameter(2, state);
		return q.getResultList();
	} 
	
	public static Long findUserChargeByTimeAndChannel(String beginTime, String endTime, String subchannel, String channel) {
		return entityManager().createQuery("select count(distinct userno) from Ttransaction where type in (2,3,10) and state=1 and accesstype='W' and channel=? and to_char(plattime,'yyyy-mm-dd')>=? and to_char(plattime,'yyyy-mm-dd')<=?", 
				Long.class).setParameter(1, channel).setParameter(2, beginTime).setParameter(3, endTime).getSingleResult();
	}
	
	/**
	 * 充值人数 
	 * @param channel
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public static Long countCharge(String channel, String starttime, String endtime){
		return entityManager().createQuery("select count(distinct o.userno) from Ttransaction o where o.type in (2, 3, 10) and  o.channel in ("+channel+")  and o.state = 1 and to_char(o.plattime, 'yyyy-mm-dd')>=? and to_char(o.plattime, 'yyyy-mm-dd')<=?", Long.class)
		.setParameter(1, starttime).setParameter(2, endtime).getSingleResult();
	}
	
	
	/**
	 * 充值金额
	 * @param channel
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public static BigDecimal sumCharge(String channel, String starttime, String endtime){
		return entityManager().createQuery("select sum(o.amt)/100 from Ttransaction o where o.type in (2, 3, 10) and  o.channel in ("+channel+")  and o.state = 1 and to_char(o.plattime, 'yyyy-mm-dd')>= ? and to_char(o.plattime, 'yyyy-mm-dd')<=?", BigDecimal.class)
		.setParameter(1, starttime).setParameter(2, endtime).getSingleResult();
	}
	
	/**
	 * 新用户 充值人数 充值金额
	 * @param channel
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public static List<Object[]> getSumNewUser(String channel, String starttime, String endtime){
		return entityManager().createQuery("select sum(t.amt)/100 ,count(distinct t.userno) from Ttransaction t where type in (2, 3, 10) and t.state = 1 and t.channel in ("+channel+") and to_char(t.plattime, 'yyyy-mm-dd')>=?"+
				" and to_char(t.plattime, 'yyyy-mm-dd')<=? and t.userno in  (select userno  from Tuserinfo u  where u.channel in ("+channel+") and to_char(u.regtime, 'yyyy-mm-dd')>=? and to_char(u.regtime, 'yyyy-mm-dd')<=?) " )
				.setParameter(1, starttime).setParameter(2, endtime).setParameter(3, starttime).setParameter(4, endtime).getResultList();
	}
	
	public static void findList(Page<Ttransaction> page, String memo) {
		TypedQuery<Ttransaction> q = Ttransaction.entityManager().createQuery(
				"select o from Ttransaction o where o.type=23 and o.memo=? order by o.plattime desc",  Ttransaction.class);
		q.setParameter(1, memo);
		q.setFirstResult(page.getPageIndex() * page.getMaxResult())
				.setMaxResults(page.getMaxResult());
		page.setList(q.getResultList());
		TypedQuery<Long> totalQ = Ttransaction.entityManager().createQuery(
				"select count(o) from Ttransaction o where o.type=23 and o.memo=? ", Long.class);
		totalQ.setParameter(1, memo);
		page.setTotalResult(totalQ.getSingleResult().intValue());
	}
}
