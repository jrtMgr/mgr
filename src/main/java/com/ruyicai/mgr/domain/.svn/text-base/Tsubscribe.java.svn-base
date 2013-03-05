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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.TableGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

import com.ruyicai.mgr.consts.Const;
import com.ruyicai.mgr.consts.SubaccountType;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.PaySign;
import com.ruyicai.mgr.util.StringUtil;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TSUBSCRIBE", schema = "JRTSCH", identifierField = "flowno", finders = "findTsubscribesByUserno")
public class Tsubscribe {

	@Id
	@GeneratedValue(generator = Const.IdGeneratorName)
	@GenericGenerator(name = Const.IdGeneratorName, strategy = Const.IdStrategy, //
	parameters = {
			@Parameter(name = Const.Seq_fmtWidth, value = "16"),
			@Parameter(name = TableGenerator.SEGMENT_COLUMN_PARAM, value = "ID"),
			@Parameter(name = TableGenerator.SEGMENT_VALUE_PARAM, value = "flowNo"),
			@Parameter(name = TableGenerator.VALUE_COLUMN_PARAM, value = "SEQ"),
			@Parameter(name = TableGenerator.TABLE_PARAM, value = "TSEQ") })
	@Column(name = "FLOWNO")
	private String flowno;

	@Column(name = "USERNO", columnDefinition = "VARCHAR2", length = 32)
	private String userno;

	@Column(name = "LOTNO", columnDefinition = "VARCHAR2", length = 24)
	private String lotno;

	@Column(name = "BATCHNUM", columnDefinition = "NUMBER")
	private BigDecimal batchnum;

	@Column(name = "LASTNUM", columnDefinition = "NUMBER")
	private BigDecimal lastnum;

	@Column(name = "BEGINBATCH", columnDefinition = "VARCHAR2", length = 60)
	private String beginbatch;

	@Column(name = "LASTBATCH", columnDefinition = "VARCHAR2", length = 60)
	private String lastbatch;

	@Column(name = "BETNUM", columnDefinition = "NUMBER")
	private BigDecimal betnum;

	@Column(name = "DRAWWAY", columnDefinition = "NUMBER")
	private BigDecimal drawway;

	@Column(name = "SELLWAY", columnDefinition = "NUMBER")
	private BigDecimal sellway;

	@Column(name = "BETCODE", columnDefinition = "VARCHAR2", length = 480)
	private String betcode;

	@Column(name = "AMOUNT", columnDefinition = "NUMBER")
	private BigDecimal amount;

	@Column(name = "TOTALAMT", columnDefinition = "NUMBER")
	private BigDecimal totalamt;

	@Column(name = "ORDERTIME", columnDefinition = "TIMESTAMP(6)")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date ordertime;

	@Column(name = "MAC", columnDefinition = "VARCHAR2", length = 256)
	private String mac;

	@Column(name = "TYPE", columnDefinition = "NUMBER")
	private BigDecimal type;

	@Column(name = "STATE", columnDefinition = "NUMBER")
	private BigDecimal state;

	@Column(name = "ENDTIME", columnDefinition = "TIMESTAMP(6)")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date endtime;

	@Column(name = "LOTMULTI", columnDefinition = "NUMBER")
	private BigDecimal lotmulti;

	@Column(name = "CHANNEL")
	private String channel;

	@Column(name = "SUBCHANNEL")
	private String subchannel;

	@Column(name = "SUBACCOUNT")
	private String subaccount;

	@Column(name = "PRIZEEND")
	private BigDecimal prizeend;

	@Column(name = "ACCOUNTNOMONEYSMS")
	private BigDecimal accountnomoneysms;

	@Column(name = "MEMO")
	private String memo;
	
	public SubaccountType getSubaccountType() {
		if (StringUtil.isEmpty(subaccount)) {
			return null;
		}
		try {
			return SubaccountType.valueOf(subaccount);
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	public void persist() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		this.entityManager.persist(this);
		this.entityManager.flush();
		Tsubscribe tsubscribe = Tsubscribe.findTsubscribe(this.getFlowno());
		this.setMac(tsubscribe.tsubscribeSign());
		tsubscribe.merge();
	}

	@Transactional
	public Tsubscribe merge() {
		this.setMac(this.tsubscribeSign());
		if (this.entityManager == null)
			this.entityManager = entityManager();
		Tsubscribe tsubscribe = this.entityManager.merge(this);
		this.entityManager.flush();
		return tsubscribe;
	}

	public String tsubscribeSign() {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer sb = new StringBuffer();
		sb.append(this.getFlowno()).append(this.getUserno())
				.append(this.getLotno()).append(this.getBatchnum())
				.append(this.getLastnum()).append(this.beginbatch)
				.append(this.getLastbatch()).append(this.getBetnum())
				.append(this.getDrawway()).append(this.getSellway())
				.append(this.getBetcode()).append(this.getAmount())
				.append(this.getTotalamt())
				.append(sFormat.format(this.getOrdertime()))
				.append(this.getType()).append(this.getState());
		try {
			return PaySign.Md5(sb.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"为tsubscribe生成加密时出错:" + this.getUserno(), e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void findList(String where, String orderby, List<Object> params, Page<Tsubscribe> page) {
		TypedQuery<Tsubscribe> q = Tsubscribe.entityManager()
				.createQuery(
						"SELECT o FROM Tsubscribe o " + where + orderby,
						Tsubscribe.class);
		if(null != params && !params.isEmpty()) {
			int index = 1;
			for(Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		q.setFirstResult(page.getPageIndex() * page.getMaxResult()).setMaxResults(page.getMaxResult());
		page.setList(q.getResultList());
		TypedQuery<Long> totalQ = Tsubscribe.entityManager().createQuery("select count(o) from Tsubscribe o " + where, Long.class);
		if(null != params && !params.isEmpty()) {
			int index = 1;
			for(Object param : params) {
				totalQ.setParameter(index, param);
				index = index + 1;
			}
		}
		page.setTotalResult(totalQ.getSingleResult().intValue());
		Query query = Tsubscribe.entityManager().createQuery("select nvl(sum(o.amount * o.batchnum),0), 1 from Tsubscribe o " + where);
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
