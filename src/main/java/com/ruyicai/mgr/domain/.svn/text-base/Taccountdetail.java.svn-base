package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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

import org.apache.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.TableGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

import com.ruyicai.mgr.consts.Const;
import com.ruyicai.mgr.exception.RuyicaiException;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.PaySign;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TACCOUNTDETAIL", schema = "JRTSCH")
public class Taccountdetail {
	@Id
	@GeneratedValue(generator = Const.IdGeneratorName)
	@GenericGenerator(name = Const.IdGeneratorName, strategy = Const.IdStrategy, //
	parameters = {
			@Parameter(name = Const.Seq_fmtWidth, value = "16"),
			@Parameter(name = TableGenerator.SEGMENT_COLUMN_PARAM, value = "ID"),
			@Parameter(name = TableGenerator.SEGMENT_VALUE_PARAM, value = "taccountdetailId"),
			@Parameter(name = TableGenerator.VALUE_COLUMN_PARAM, value = "SEQ"),
			@Parameter(name = TableGenerator.TABLE_PARAM, value = "TSEQ") })
	@Column(name = "ID")
	private String id;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "PLATTIME", columnDefinition = "TIMESTAMP(6)")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date plattime;

	@Column(name = "AMT", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal amt;

	@Column(name = "DRAWAMT", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal drawamt;

	@Column(name = "BLSIGN", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal blsign;

	@Column(name = "MEMO", columnDefinition = "VARCHAR2", length = 256)
	@NotNull
	private String memo;

	@Column(name = "BALANCE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal balance;

	@Column(name = "TACCOUNTTYPE", columnDefinition = "NUMBER")
	private BigDecimal taccounttype;

	@Column(name = "TTRANSACTIONID", columnDefinition = "VARCHAR2", length = 128)
	@NotNull
	private String ttransactionid;

	@Column(name = "TTRANSACTIONTYPE", columnDefinition = "NUMBER")
	private BigDecimal ttransactiontype;

	@Column(name = "MOBILEID", columnDefinition = "VARCHAR2", length = 44)
	private String mobileid;

	@Column(name = "USERNO", columnDefinition = "VARCHAR2", length = 32)
	@NotNull
	private String userno;

	@Column(name = "DRAWBALANCE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal drawbalance;

	@Column(name = "MAC", columnDefinition = "VARCHAR2", length = 256)
	@NotNull
	private String mac = " ";

	@Column(name = "STATE", columnDefinition = "NUMBER")
	private BigDecimal state;

	@Column(name = "FREEZEAMT", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal freezeamt;

	@Column(name = "FREEZEBALANCE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal freezebalance;

	@Column(name = "FLOWNO", columnDefinition = "VARCHAR2")
	@NotNull
	private String flowno;
	
	@Column(name = "OTHERID", columnDefinition = "VARCHAR2")
	@NotNull
	private String otherid;
	
	@Transactional
	public Taccountdetail merge() {
		this.setMac(this.taccountDetailSign());
		if (this.entityManager == null)
			this.entityManager = entityManager();
		Taccountdetail merged = this.entityManager.merge(this);
		this.entityManager.flush();
		return merged;
	}

	@Transactional
	public void persist() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		this.entityManager.persist(this);
		this.entityManager.flush();
		Taccountdetail detail = Taccountdetail.findTaccountdetail(this.getId());
		this.setMac(detail.taccountDetailSign());
		detail.merge();
	}

	public static List<Taccountdetail> findByTransactionid(String transactionid) {
		TypedQuery<Taccountdetail> query = Taccountdetail
				.entityManager()
				.createQuery(
						"select o from Taccountdetail o where o.ttransactionid=? order by plattime desc",
						Taccountdetail.class);
		query.setParameter(1, transactionid);
		return query.getResultList();
	}

	public String taccountDetailSign() {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer inStr = new StringBuffer();
		inStr.append(this.getId()).append(sFormat.format(this.getPlattime()))
				.append(this.getAmt().longValue())
				.append(this.getDrawamt().longValue()).append(this.getBlsign())
				.append(this.getMemo()).append(this.getBalance().longValue())
				.append(this.getTaccounttype())
				.append(this.getTtransactionid())
				.append(this.getTtransactiontype()).append(this.getMobileid())
				.append(this.getUserno())
				.append(this.getDrawbalance().longValue());
		try {
			return PaySign.Md5(inStr.toString());
		} catch (Exception e) {
			Logger logger = Logger.getLogger(Tuserinfo.class);
			logger.error("为taccountdetail生成加密时出错", e);
			throw new RuyicaiException(ErrorCode.Data_MD5);
		}
	}

	public static void findByUsernoAndDate(String userno, String begin,
			String end, Page<Taccountdetail> page) {
		TypedQuery<Taccountdetail> query = Taccountdetail
				.entityManager()
				.createQuery(
						"select o from Taccountdetail o where userno=? and to_char(o.plattime, 'yyyymmdd') >= ? and to_char(o.plattime, 'yyyymmdd') <= ? order by o.plattime desc",
						Taccountdetail.class).setParameter(1, userno)
				.setParameter(2, begin).setParameter(3, end)
				.setFirstResult(page.getPageIndex() * page.getMaxResult())
				.setMaxResults(page.getMaxResult());
		List<Taccountdetail> resultList = query.getResultList();
		if (resultList.isEmpty()) {

			throw new RuyicaiException(ErrorCode.Taccountdetail_Empty);
		}
		page.setList(resultList);

		TypedQuery<Long> totalQuery = Taccountdetail
				.entityManager()
				.createQuery(
						"select count(o) from Taccountdetail o where userno=? and to_char(o.plattime, 'yyyymmdd') >= ? and to_char(o.plattime, 'yyyymmdd') <= ?",
						Long.class).setParameter(1, userno)
				.setParameter(2, begin).setParameter(3, end);
		page.setTotalResult(totalQuery.getSingleResult().intValue());
	}

	public static Taccountdetail findbyTranIdAndType(String ttransactionid,
			BigDecimal type) {
		TypedQuery<Taccountdetail> query = Taccountdetail
				.entityManager()
				.createQuery(
						"select o from Taccountdetail o where o.ttransactionid=? and o.ttransactiontype=? order by plattime desc",
						Taccountdetail.class);
		query.setParameter(1, ttransactionid).setParameter(2, type);
		List<Taccountdetail> results = query.getResultList();
		if (results.isEmpty()) {
			return null;
		}
		return results.get(0);
	}
	
	
	public static List<Taccountdetail> findbyTranIdAndTypeList(String ttransactionid,
			BigDecimal type) {
		TypedQuery<Taccountdetail> query = Taccountdetail
				.entityManager()
				.createQuery(
						"select o from Taccountdetail o where o.ttransactionid=? and o.ttransactiontype=? order by plattime desc",
						Taccountdetail.class);
		query.setParameter(1, ttransactionid).setParameter(2, type);
		List<Taccountdetail> results = query.getResultList();
		
		return results;
	}
	
	public static BigDecimal findUserPresent(String userno) {
		BigDecimal ret = BigDecimal.ZERO;
		BigDecimal ret1 = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=1 " +
				"and memo like '%赠送%'", BigDecimal.class).setParameter(1, userno).getSingleResult();
		BigDecimal ret2 = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=1 and ttransactiontype=23 " +
				"and memo like '%加奖%'", BigDecimal.class).setParameter(1, userno).getSingleResult();	
		BigDecimal ret3 = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=1 " +
				"and memo in ('专家发单返点佣金', '合买发单返奖')", BigDecimal.class).setParameter(1, userno).getSingleResult();//---
		ret1 = (ret1 == null? BigDecimal.ZERO : ret1);
		ret2 = (ret2 == null? BigDecimal.ZERO : ret2);
		ret3 = (ret3 == null? BigDecimal.ZERO : ret3);
		ret = ret1.add(ret2).add(ret3);
		return ret == null? BigDecimal.ZERO : ret;
	} 
	
	public static BigDecimal findUserPrize(String userno) {
		BigDecimal ret = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=1 " +
				"and memo in ('奖金', '兑奖划款', '体彩兑奖划款','用户兑奖划款', '合买返奖', '合买佣金', '合买发起人佣金划款', '官方加奖')", BigDecimal.class).setParameter(1, userno).getSingleResult();
		return ret == null? BigDecimal.ZERO : ret;
	} 
	
	public static BigDecimal findUserHMBackPrize(String userno) {
		BigDecimal ret = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=1 " +
				"and memo='合买返奖'", BigDecimal.class).setParameter(1, userno).getSingleResult();
		return ret == null? BigDecimal.ZERO : ret;
	}
	//中奖加奖
	public static BigDecimal findUserZJJJ(String userno) {
		BigDecimal ret = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=1 " +
				"and memo like '%加奖'", BigDecimal.class).setParameter(1, userno).getSingleResult();
		return ret == null? BigDecimal.ZERO : ret;
	}
	public static BigDecimal findUserHMFDBackPrize(String userno) {
		BigDecimal ret = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=1 " +
				"and memo='合买发单返奖'", BigDecimal.class).setParameter(1, userno).getSingleResult();
		return ret == null? BigDecimal.ZERO : ret;
	} 
	
	public static BigDecimal findUserHMYJ(String userno) {
		BigDecimal ret = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=1 " +
				"and memo='合买佣金'", BigDecimal.class).setParameter(1, userno).getSingleResult();
		return ret == null? BigDecimal.ZERO : ret;
	} 
	
	public static BigDecimal findUserQXZH(String userno) {
		BigDecimal ret = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=1 " +
				"and memo='取消追号,返还金额'", BigDecimal.class).setParameter(1, userno).getSingleResult();
		return ret == null? BigDecimal.ZERO : ret;
	} 
	
	public static BigDecimal findUserYCZH(String userno) {
//		BigDecimal ret = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=-1 " +
//				"and memo='预存追号金额'", BigDecimal.class).setParameter(1, userno).getSingleResult();
		BigDecimal ret = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=-1 " +
		        "and ttransactiontype = 8", BigDecimal.class).setParameter(1, userno).getSingleResult();
		return ret == null? BigDecimal.ZERO : ret;
	}
	
	public static BigDecimal findUserJFDHCJ(String userno) {
		BigDecimal ret = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=1 " +
				"and memo='积分兑换彩金'", BigDecimal.class).setParameter(1, userno).getSingleResult();
		return ret == null? BigDecimal.ZERO : ret;
	}
	
	public static BigDecimal findUserBetSuccess(String userno) {
		BigDecimal ret1 = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=-1 " +
				"and ttransactiontype=1", BigDecimal.class).setParameter(1, userno).getSingleResult();
		BigDecimal ret2 = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=1 " +
				"and ttransactiontype=7 and memo='投注失败冲正'", BigDecimal.class).setParameter(1, userno).getSingleResult();
		ret1 = (ret1 == null? BigDecimal.ZERO : ret1);
		ret2 = (ret2 == null? BigDecimal.ZERO : ret2);
		return ret1.subtract(ret2);
	}
	
	public static BigDecimal findUserSubscribeSuccess(String userno) {
		BigDecimal ret1 = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=-1 " +
				"and ttransactiontype=8", BigDecimal.class).setParameter(1, userno).getSingleResult();
		BigDecimal ret2 = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=1 " +
				"and ttransactiontype=7 and memo in ('取消追号,返还金额', '追号失败返款')", BigDecimal.class).setParameter(1, userno).getSingleResult();
		ret1 = (ret1 == null? BigDecimal.ZERO : ret1);
		ret2 = (ret2 == null? BigDecimal.ZERO : ret2);
		return ret1.subtract(ret2);
	}
	
	public static BigDecimal findUserBetSuccessHM(String userno) {
		BigDecimal ret1 = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=-1 " +
				"and ttransactiontype=19", BigDecimal.class).setParameter(1, userno).getSingleResult();
		BigDecimal ret2 = entityManager().createQuery("select sum(amt) from Taccountdetail where userno=? and blsign=1 " +
				"and ttransactiontype  in ('30', '31')", BigDecimal.class).setParameter(1, userno).getSingleResult();
		ret1 = (ret1 == null? BigDecimal.ZERO : ret1);
		ret2 = (ret2 == null? BigDecimal.ZERO : ret2);
		return ret1.subtract(ret2);
	}
	
	public static BigDecimal findAllEncash(String date) {
		BigDecimal ret = BigDecimal.ZERO;
		BigDecimal ret1 = entityManager().createQuery("select sum(amt) from Taccountdetail where blsign=1 and to_char(plattime,'yyyy-mm-dd')=? and ttransactiontype in (6, 17,18, 33) ", BigDecimal.class).setParameter(1, date).getSingleResult();
		BigDecimal ret2 = entityManager().createQuery("select sum(amt) from Taccountdetail where blsign=-1 and to_char(plattime,'yyyy-mm-dd')=? and ttransactiontype = 32) ", BigDecimal.class).setParameter(1, date).getSingleResult();
		ret1 = (ret1 == null? BigDecimal.ZERO : ret1);
		ret2 = (ret2 == null? BigDecimal.ZERO : ret2);
		ret = ret1.subtract(ret2);
		return ret;
	} 
	
	@SuppressWarnings("unchecked")
	public static void findList(String where, String orderby, List<Object> params, Page<Taccountdetail> page) {
		TypedQuery<Taccountdetail> q = Taccountdetail.entityManager().createQuery("SELECT o FROM Taccountdetail o " + where + orderby, Taccountdetail.class);
		if(null != params && !params.isEmpty()) {
			int index = 1;
			for(Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		q.setFirstResult(page.getPageIndex() * page.getMaxResult()).setMaxResults(page.getMaxResult());
		page.setList(q.getResultList());
		TypedQuery<Long> totalQ = Taccountdetail.entityManager().createQuery("select count(o) from Taccountdetail o " + where, Long.class);
		if(null != params && !params.isEmpty()) {
			int index = 1;
			for(Object param : params) {
				totalQ.setParameter(index, param);
				index = index + 1;
			}
		}
		page.setTotalResult(totalQ.getSingleResult().intValue());		
	}
	
	public static List<Object[]> getTaccountdetailInfo(String userno) {
		Query q = Taccountdetail.entityManager().createNativeQuery("select ttransactiontype, blsign, sum(amt) from taccountdetail  t where t.userno=? group by ttransactiontype, blsign order by ttransactiontype");
		q.setParameter(1, userno);
		return q.getResultList();
	}
	
	public static List<Object[]> statActivitiesData(String sql, List<Object> params) {
		Query q = Taccountdetail.entityManager().createNativeQuery(sql);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		return q.getResultList();
	}
	
	public static List<BigDecimal> statActivitiesDatasum(String sql, List<Object> params) {
		Query q = Taccountdetail.entityManager().createNativeQuery(sql);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		return q.getResultList();
	}
}
