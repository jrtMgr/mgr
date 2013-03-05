package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.TableGenerator;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.consts.Const;
import com.ruyicai.mgr.consts.SubaccountType;
import com.ruyicai.mgr.consts.TorderState;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.StringUtil;
import com.sun.istack.internal.NotNull;

@RooJavaBean
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TORDER", schema = "JRTSCH", identifierField = "id",finders={"findTordersByUserno"})
@RooToString
public class Torder {

	@Id
	@GeneratedValue(generator = Const.IdGeneratorName)
	@GenericGenerator(name = Const.IdGeneratorName, strategy = Const.IdStrategy, //
	parameters = {
			@Parameter(name = Const.Seq_Date, value = "yyyyMMdd"),
			@Parameter(name = Const.Seq_fmtWidth, value = "8"),
			@Parameter(name = TableGenerator.SEGMENT_COLUMN_PARAM, value = "ID"),
			@Parameter(name = TableGenerator.SEGMENT_VALUE_PARAM, value = "orderid"),
			@Parameter(name = TableGenerator.VALUE_COLUMN_PARAM, value = "SEQ"),
			@Parameter(name = TableGenerator.TABLE_PARAM, value = "TSEQ") })
	@Column(name = "ID", columnDefinition = "VARCHAR2", length = 32)
	private String id;

	@Column(name = "BATCHCODE", columnDefinition = "VARCHAR2", length = 15)
	@NotNull
	private String batchcode;

	@Column(name = "LOTNO", columnDefinition = "VARCHAR2", length = 6)
	@NotNull
	private String lotno;

	@Column(name = "AMT", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal amt;

	@Column(name = "PAYTYPE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal paytype;

	@Column(name = "ORDERSTATE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal orderstate;

	@Column(name = "BETTYPE", columnDefinition = "VARCHER2", length = 20)
	@NotNull
	private BigDecimal bettype;

	@Column(name = "PRIZESTATE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal prizestate;

	@Column(name = "ORDERPRIZEAMT", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal orderprizeamt;

	@Column(name = "WINBASECODE", columnDefinition = "VARCHAR2", length = 60)
	private String winbasecode;

	@Column(name = "ORDERTYPE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal ordertype;

	@Column(name = "TSUBSCRIBEFLOWNO", columnDefinition = "VARCHAR2", length = 16)
	private String tsubscribeflowno;

	@Column(name = "TLOTCASEID", columnDefinition = "VARCHAR2", length = 16)
	private String tlotcaseid;

	@Column(name = "CREATETIME", columnDefinition = "TIMESTAMP(6)")
	private Date createtime;

	@Column(name = "USERNO", columnDefinition = "VARCHAR2", length = 32)
	@NotNull
	private String userno;

	@Column(name = "BUYUSERNO", columnDefinition = "VARCHAR2", length = 32)
	@NotNull
	private String buyuserno;

	@Column(name = "MEMO")
	private String memo;

	@Column(name = "SUBACCOUNT")
	private String subaccount;

	@Column(name = "BETNUM", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal betnum;
	
	@Column(name = "BETCODE")
	private String betcode;
	
	@Column(name = "BODY")
	private String body;
	
	@Column(name = "INSTATE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal instate;
	
	@Column(name = "AGENCYNO")
	private String agencyno;
	@Column(name = "PAYSTATE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal paystate;
	
	@Column(name = "SUBCHANNEL")
	private String subchannel;
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
	
	public static Torder findBySubscribeAndBatchcodeAndOrderstate(
			String subscribeno, String batchcode, BigDecimal orderstate) {
		TypedQuery<Torder> q = Torder
				.entityManager()
				.createQuery(
						"select o from Torder o where o.tsubscribeflowno=? and o.batchcode=? and o.orderstate=?",
						Torder.class)
				.setLockMode(LockModeType.PESSIMISTIC_WRITE)
				.setParameter(1, subscribeno).setParameter(2, batchcode)
				.setParameter(3, orderstate);
		List<Torder> results = q.getResultList();
		return results.isEmpty() ? null : results.get(0);
	}

	public static List<Torder> findByLotnoAndBatchcodeAndState(String lotno,
			String batchcode, BigDecimal orderstate) {
		return Torder
				.entityManager()
				.createQuery(
						"select o from Torder o where o.lotno=? and o.batchcode=? and o.orderstate=?",
						Torder.class)
				.setLockMode(LockModeType.PESSIMISTIC_WRITE)
				.setParameter(1, lotno).setParameter(2, batchcode)
				.setParameter(3, orderstate).getResultList();
	}

	/**
	 * 订单列表查询
	 * 参数中  TsubscribeFlowno 和 userno 必须选填一项，否则无法获得正确数据
	 * @param TsubscribeFlowno 追号记录的流水号 用于找到对应最好的所有订单记录
	 * @param userno 用户id
	 * @param lotno 彩种
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @param page 分页以及数据存储对象
	 */
	public static void findBySubscribeOrToUserno(
			String TsubscribeFlowno, String userno,String lotno, int prizestate,
			String beginTime,String endTime,Page<Torder> page) {
		String where ="";
		String orderby = " order by o.createtime desc";
		if(!TsubscribeFlowno.isEmpty()){
			where = where+" and o.tsubscribeflowno='"+TsubscribeFlowno+"' ";
			orderby = " order by o.batchcode asc";
		}else{
			where = where+" and o.ordertype='"+TorderState.orderType_bet.value()+"' ";
		}
		if(!userno.isEmpty()){
			where = where+" and o.userno='"+userno+"' ";
		}
		if(!lotno.isEmpty()){
			where = where+" and o.lotno='"+lotno+"' ";
		}
		if(prizestate==0||prizestate==1){
			where = where+" and (o.prizestate='"+TorderState.prizeState_no.value()+"' or o.prizestate='"+TorderState.prizeState_wait.value()+"') ";
		}else if(prizestate==2){
			where = where+" and o.prizestate='"+TorderState.prizeState_processing.value()+"' ";
		}else if(prizestate==3||prizestate==4){
			where = where+" and (o.prizestate='"+TorderState.prizeState_complete.value()+"' or o.prizestate='"+TorderState.prizeState_tall.value()+"') ";
		}
		
		TypedQuery<Torder> q = Torder
				.entityManager()
				.createQuery(
						"select o from Torder o where to_char(o.createtime,'yyyymmdd')>=? and to_char(o.createtime,'yyyymmdd')<=? "
						+ where+ orderby,
						Torder.class)
				.setParameter(1, beginTime).setParameter(2, endTime)
				.setFirstResult(page.getPageIndex())
				.setMaxResults(page.getMaxResult());
		page.setList(q.getResultList());
		
		TypedQuery<Long> total = Torder
		.entityManager()
		.createQuery(
				"select count(o) from Torder o where to_char(o.createtime,'yyyymmdd')>=? and to_char(o.createtime,'yyyymmdd')<=? " + where
				, Long.class).setParameter(1, beginTime)
				.setParameter(2, endTime);
		Integer totalRes = total.getSingleResult().intValue();
		page.setTotalResult(totalRes);
		Torder.entityManager().clear();
	}
	
	/**
	 * 查询失败订单
	 */
	public static List<Torder> findFailList() {
		return Torder.entityManager().createQuery(
						"select o from Torder o where o.orderstate = 0 and o.bettype = 2",
						Torder.class).getResultList();
	}
	
	/**
	 * 查询失败订单数量
	 */
	public static Long findByBetfail() {
		return Torder
				.entityManager()
				.createQuery(
						"select count(o) from Torder o where o.orderstate = 0 and o.bettype = 2",
						Long.class).getSingleResult();
	}
	
	/**
	 * 查询未付款失败订单
	 */
	public static List<Torder> findFailList2() {
		return Torder.entityManager().createQuery(
				"select o from Torder o where (o.tlotcaseid is null or o.tlotcaseid = ' ') and o.orderstate = 0 " +
				"and not exists (select l.flowno from Tlot l where l.torderid = o.id) and o.paystate = 0" +
				" order by o.createtime desc", Torder.class).getResultList();
	}
	/**
	 * 查询未付款失败订单数量
	 */
	public static Long findByBetfail2() {
		return Torder.entityManager().createQuery(
						"select count(o) from Torder o where (o.tlotcaseid is null or o.tlotcaseid = ' ') and o.orderstate = 0 " +
						"and not exists (select l.flowno from Tlot l where l.torderid = o.id) and o.paystate = 0",
						Long.class).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public static void findList(String where, String orderby,
			List<Object> params, Page<Torder> page) {
		TypedQuery<Torder> q = Tlot.entityManager().createQuery(
				"SELECT o FROM Torder o " + where + orderby, Torder.class);
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
		
		TypedQuery<Long> totalQ = Torder.entityManager().createQuery(
				"select count(o) from Torder o " + where, Long.class);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				totalQ.setParameter(index, param);
				index = index + 1;
			}
		}
		page.setTotalResult(totalQ.getSingleResult().intValue());
		
		Query query = Torder.entityManager().createQuery(
				"select nvl(sum(o.amt),0), nvl(sum(o.orderprizeamt),0) from Torder o "
						+ where);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				query.setParameter(index, param);
				index = index + 1;
			}
		}
		List<Object[]> results = (List<Object[]>) query.getResultList();
		page.setValue(results.get(0));
	}
	
	public static List<Torder> findBySubscribeno(String subscribeno) {
		return Torder
				.entityManager()
				.createQuery(
						"select o from Torder o where o.tsubscribeflowno = ? ",
						Torder.class).setParameter(1, subscribeno)
				.getResultList();
	}
	
	
	public static BigDecimal findUserBetSuccess(String userno) {
		BigDecimal ret = entityManager().createQuery("select sum(t.amt) from Torder t where t.buyuserno = ? and t.orderstate = 1 and (t.tsubscribeflowno is null or t.tsubscribeflowno = ' ') ", BigDecimal.class).setParameter(1, userno).getSingleResult();
        return ret == null? BigDecimal.ZERO : ret;
		
	}
	
	public static BigDecimal findUserSubscribeSuccess(String userno) {
		BigDecimal ret = entityManager().createQuery("select sum(t.amt) from Torder t where t.buyuserno=? and t.orderstate=1 and t.tsubscribeflowno is not null and t.tsubscribeflowno != ' '", BigDecimal.class).setParameter(1, userno).getSingleResult();
        return ret == null? BigDecimal.ZERO : ret;
	} 
	
	public static BigDecimal findAllUserbet(String date) {
		BigDecimal ret = entityManager().createQuery("select sum(t.amt) from Torder t where t.orderstate = 1 and t.subchannel = '00092493' and to_char(t.createtime, 'yyyy-MM-dd')=? ", BigDecimal.class).setParameter(1, date).getSingleResult();
        return ret == null? BigDecimal.ZERO : ret;
		
	}

}
