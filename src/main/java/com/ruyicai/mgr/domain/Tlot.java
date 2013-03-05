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
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

import com.ruyicai.mgr.consts.Const;
import com.ruyicai.mgr.consts.SubaccountType;
import com.ruyicai.mgr.exception.RuyicaiException;
import com.ruyicai.mgr.util.DateUtil;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.PaySign;
import com.ruyicai.mgr.util.StringUtil;

@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TLOT", schema = "JRTSCH", identifierField = "flowno", finders = {
		"findTlotsByTorderid", "findTlotsByLotpwd",
		"findTlotsByStateAndLotnoAndBatchcode" })
@RooJson
public class Tlot {

	@Id
	@GeneratedValue(generator = Const.IdGeneratorName)
	@GenericGenerator(name = Const.IdGeneratorName, strategy = Const.IdStrategy, //
	parameters = {
			@Parameter(name = Const.Seq_prefix, value = Const.TlotIdPrefix),
			@Parameter(name = Const.Seq_fmtWidth, value = "14"),
			@Parameter(name = TableGenerator.SEGMENT_COLUMN_PARAM, value = "ID"),
			@Parameter(name = TableGenerator.SEGMENT_VALUE_PARAM, value = "flowNo"),
			@Parameter(name = TableGenerator.VALUE_COLUMN_PARAM, value = "SEQ"),
			@Parameter(name = TableGenerator.TABLE_PARAM, value = "TSEQ") })
	@Column(name = "FLOWNO")
	private String flowno;

	@Column(name = "USERNO", columnDefinition = "VARCHAR2", length = 32)
	@NotNull
	private String userno;

	@Column(name = "LOTNO", columnDefinition = "VARCHAR2", length = 24)
	@NotNull
	private String lotno;

	@Column(name = "LOTPWD", columnDefinition = "VARCHAR2", length = 128)
	private String lotpwd;

	@Column(name = "AGENCYNO", columnDefinition = "VARCHAR2", length = 24)
	private String agencyno;

	@Column(name = "BATCHCODE", columnDefinition = "VARCHAR2", length = 36)
	@NotNull
	private String batchcode;

	@Column(name = "VALIDCODE", columnDefinition = "VARCHAR2", length = 36)
	private String validcode;

	@Column(name = "RUNCODE", columnDefinition = "VARCHAR2", length = 32)
	private String runcode;

	@Column(name = "BETNUM", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal betnum;

	@Column(name = "DRAWWAY", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal drawway;

	@Column(name = "SELLWAY", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal sellway;

	@Column(name = "BETCODE", columnDefinition = "VARCHAR2", length = 480)
	@NotNull
	private String betcode;

	@Column(name = "CHECKCODE", columnDefinition = "VARCHAR2", length = 256)
	private String checkcode;

	@Column(name = "AMT", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal amt;

	@Column(name = "ORDERTIME", columnDefinition = "TIMESTAMP(6)")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date ordertime;

	@Column(name = "SETTLEFLAG", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal settleflag;

	@Column(name = "PRIZEAMT", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal prizeamt;

	@Column(name = "PRIZELITTLE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal prizelittle;

	@Column(name = "PRIZEINFO", columnDefinition = "VARCHAR2", length = 480)
	private String prizeinfo = " ";

	@Column(name = "PRIZETIME", columnDefinition = "TIMESTAMP(6)")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date prizetime;

	@Column(name = "MACHINENO", columnDefinition = "VARCHAR2", length = 80)
	private String machineno;

	@Column(name = "GIVEUPTIME", columnDefinition = "TIMESTAMP(6)")
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date giveuptime;

	@Column(name = "STATE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal state;

	@Column(name = "TRANSFERSTATE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal transferstate;

	@Column(name = "BETTYPE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal bettype;

	@Column(name = "SUBSCRIBENO", columnDefinition = "VARCHAR2", length = 64)
	private String subscribeno;

	@Column(name = "PBATCHCODE", columnDefinition = "VARCHAR2", length = 28)
	private String pbatchcode;

	@Column(name = "CASEID", columnDefinition = "VARCHAR2", length = 64)
	private String caseid;

	@Column(name = "MAC", columnDefinition = "VARCHAR2", length = 256)
	private String mac = " ";

	@Column(name = "BUYUSERNO", columnDefinition = "VARCHAR2", length = 32)
	private String buyUserno;

	@Column(name = "LOTMULTI", columnDefinition = "NUMBER")
	private BigDecimal lotmulti;

	@Column(name = "CHANNEL")
	private String channel;

	@Column(name = "SUBCHANNEL")
	private String subchannel;

	@Column(name = "TORDERID")
	private String torderid;

	@Column(name = "INSTATE")
	private BigDecimal instate;

	@Column(name = "SUBACCOUNT")
	private String subaccount;

	@Column(name = "PAYSTATE")
	private BigDecimal paystate;

	@Column(name = "CURRENTBETCODE")
	private String currentbetcode;

	@Column(name = "FAILNUM")
	private BigDecimal failnum;
	
	@Column(name = "LOTCENTERORDERTIME")
	private Date lotcenterordertime;

	@Column(name = "LOTCENTERISVALID")
	private BigDecimal lotcenterisvalid;
	
	@Column(name = "LATESTPRINTTIME")
	private Date latestprinttime;
	
	@Column(name = "REALPRIZEAMT")
	private BigDecimal realprizeamt;
	
	@Column(name = "PEILU")
	private String peilu;
	
	public String getPeilu() {
		return peilu;
	}

	public void setPeilu(String peilu) {
		this.peilu = peilu;
	}

	public BigDecimal getRealprizeamt() {
		return realprizeamt;
	}

	public void setRealprizeamt(BigDecimal realprizeamt) {
		this.realprizeamt = realprizeamt;
	}

	public Date getLatestprinttime() {
		return latestprinttime;
	}

	public void setLatestprinttime(Date latestprinttime) {
		this.latestprinttime = latestprinttime;
	}

	public BigDecimal getLotcenterisvalid() {
		return lotcenterisvalid;
	}

	public void setLotcenterisvalid(BigDecimal lotcenterisvalid) {
		this.lotcenterisvalid = lotcenterisvalid;
	}


	private transient String tlotresultid;

	public String getTlotresultid() {
		return tlotresultid;
	}

	public void setTlotresultid(String tlotresultid) {
		this.tlotresultid = tlotresultid;
	}

	public String getCurrentbetcode() {
		return currentbetcode;
	}

	public void setCurrentbetcode(String currentbetcode) {
		this.currentbetcode = currentbetcode;
	}

	public BigDecimal getFailnum() {
		return failnum;
	}

	public void setFailnum(BigDecimal failnum) {
		this.failnum = failnum;
	}

	public BigDecimal getPaystate() {
		return paystate;
	}

	public void setPaystate(BigDecimal paystate) {
		this.paystate = paystate;
	}

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

	public String getSubaccount() {
		return subaccount;
	}

	public void setSubaccount(String subaccount) {
		this.subaccount = subaccount;
	}

	public BigDecimal getInstate() {
		return instate;
	}

	public void setInstate(BigDecimal instate) {
		this.instate = instate;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getSubchannel() {
		return subchannel;
	}

	public void setSubchannel(String subchannel) {
		this.subchannel = subchannel;
	}

	public BigDecimal getLotmulti() {
		return lotmulti;
	}

	public void setLotmulti(BigDecimal lotmulti) {
		this.lotmulti = lotmulti;
	}

	public String getFlowno() {
		return this.flowno;
	}

	public void setFlowno(String flowno) {
		this.flowno = flowno;
	}

	public String getUserno() {
		return " ".equals(this.userno) ? null : this.userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public String getLotno() {
		return " ".equals(this.lotno) ? null : this.lotno;
	}

	public void setLotno(String lotno) {
		this.lotno = lotno;
	}

	public String getLotpwd() {
		return " ".equals(this.lotpwd) ? null : this.lotpwd;
	}

	public void setLotpwd(String lotpwd) {
		this.lotpwd = lotpwd;
	}

	public String getAgencyno() {
		return " ".equals(this.agencyno) ? null : this.agencyno;
	}

	public void setAgencyno(String agencyno) {
		this.agencyno = agencyno;
	}

	public String getBatchcode() {
		return " ".equals(this.batchcode) ? null : this.batchcode;
	}

	public void setBatchcode(String batchcode) {
		this.batchcode = batchcode;
	}

	public String getValidcode() {
		return " ".equals(this.validcode) ? null : this.validcode;
	}

	public void setValidcode(String validcode) {
		this.validcode = validcode;
	}

	public String getRuncode() {
		return " ".equals(this.runcode) ? null : this.runcode;
	}

	public void setRuncode(String runcode) {
		this.runcode = runcode;
	}

	public BigDecimal getBetnum() {
		return this.betnum;
	}

	public void setBetnum(BigDecimal betnum) {
		this.betnum = betnum;
	}

	public BigDecimal getDrawway() {
		return this.drawway;
	}

	public void setDrawway(BigDecimal drawway) {
		this.drawway = drawway;
	}

	public BigDecimal getSellway() {
		return this.sellway;
	}

	public void setSellway(BigDecimal sellway) {
		this.sellway = sellway;
	}

	public String getBetcode() {
		return " ".equals(this.betcode) ? null : this.betcode;
	}

	public void setBetcode(String betcode) {
		this.betcode = betcode;
	}

	public String getCheckcode() {
		return " ".equals(this.checkcode) ? null : this.checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public BigDecimal getAmt() {
		return this.amt;
	}

	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}

	public Date getOrdertime() {
		return this.ordertime;
	}

	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}

	public BigDecimal getSettleflag() {
		return this.settleflag;
	}

	public void setSettleflag(BigDecimal settleflag) {
		this.settleflag = settleflag;
	}

	public BigDecimal getPrizeamt() {
		return this.prizeamt;
	}

	public void setPrizeamt(BigDecimal prizeamt) {
		this.prizeamt = prizeamt;
	}

	public BigDecimal getPrizelittle() {
		return this.prizelittle;
	}

	public void setPrizelittle(BigDecimal prizelittle) {
		this.prizelittle = prizelittle;
	}

	public String getPrizeinfo() {
		return " ".equals(this.prizeinfo) ? null : this.prizeinfo;
	}

	public void setPrizeinfo(String prizeinfo) {
		if (prizeinfo == null) {
			prizeinfo = " ";
		}
		this.prizeinfo = prizeinfo;
	}

	public Date getPrizetime() {
		return this.prizetime;
	}

	public void setPrizetime(Date prizetime) {
		this.prizetime = prizetime;
	}

	public String getMachineno() {
		return " ".equals(this.machineno) ? null : this.machineno;
	}

	public void setMachineno(String machineno) {
		this.machineno = machineno;
	}

	public Date getGiveuptime() {
		return this.giveuptime;
	}

	public void setGiveuptime(Date giveuptime) {
		this.giveuptime = giveuptime;
	}

	public BigDecimal getState() {
		return this.state;
	}

	public void setState(BigDecimal state) {
		this.state = state;
	}

	public BigDecimal getTransferstate() {
		return this.transferstate;
	}

	public void setTransferstate(BigDecimal transferstate) {
		this.transferstate = transferstate;
	}

	public BigDecimal getBettype() {
		return this.bettype;
	}

	public void setBettype(BigDecimal bettype) {
		this.bettype = bettype;
	}

	public String getSubscribeno() {
		return " ".equals(this.subscribeno) ? null : this.subscribeno;
	}

	public void setSubscribeno(String subscribeno) {
		this.subscribeno = subscribeno;
	}

	public String getPbatchcode() {
		return " ".equals(this.pbatchcode) ? null : this.pbatchcode;
	}

	public void setPbatchcode(String pbatchcode) {
		this.pbatchcode = pbatchcode;
	}

	public String getCaseid() {
		return " ".equals(this.caseid) ? null : this.caseid;
	}

	public void setCaseid(String caseid) {
		this.caseid = caseid;
	}

	public String getMac() {
		return " ".equals(this.mac) ? null : this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getTorderid() {
		return torderid;
	}

	public void setTorderid(String torderid) {
		this.torderid = torderid;
	}

	public Date getLotcenterordertime() {
		return lotcenterordertime;
	}

	public void setLotcenterordertime(Date lotcenterordertime) {
		this.lotcenterordertime = lotcenterordertime;
	}

	@Transactional
	public void persist() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		this.entityManager.persist(this);
		this.entityManager.flush();
		Tlot tlottc = Tlot.findTlot(this.getFlowno());
		this.setMac(tlottc.tlotSign());
		tlottc.merge();
	}

	@Transactional
	public Tlot merge() {
		this.setMac(this.tlotSign());
		if (this.entityManager == null)
			this.entityManager = entityManager();
		Tlot merged = this.entityManager.merge(this);
		this.entityManager.flush();
		return merged;
	}

	public boolean isValid() {
		return this.mac.equals(this.tlotSign());
	}

	public String tlotSign() {
		SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer sb = new StringBuffer();
		sb.append(this.getFlowno());
		sb.append(this.getUserno());
		sb.append(this.getLotno());
		sb.append(this.getLotpwd());
		sb.append(this.getAgencyno());
		sb.append(this.getBatchcode());
		sb.append(this.getValidcode());
		sb.append(this.getRuncode());
		sb.append(this.getBetnum());
		sb.append(this.getDrawway());
		sb.append(this.getSellway());
		sb.append(this.getBetcode());
		sb.append(this.getCheckcode());
		sb.append(this.getAmt().longValue());
		sb.append(sFormat.format(this.getOrdertime()));
		sb.append(this.getSettleflag());
		sb.append(this.getPrizeamt().longValue());
		sb.append(this.getPrizelittle());
		sb.append(this.getPrizeinfo());
		sb.append(sFormat.format(this.getPrizetime()));
		sb.append(this.getMachineno());
		sb.append(sFormat.format(this.getGiveuptime()));
		sb.append(this.getState());
		sb.append(this.getTransferstate());
		sb.append(this.getBettype());
		try {
			return PaySign.Md5(sb.toString());
		} catch (Exception e) {
			Logger logger = Logger.getLogger(Tuserinfo.class);
			logger.error("为tlot生成加密时出错", e);
			throw new RuyicaiException(ErrorCode.Data_MD5);
		}
	}

	public String getBuyUserno() {
		if (StringUtil.isEmpty(buyUserno)) {
			return this.userno;
		}
		return buyUserno;
	}

	public void setBuyUserno(String buyUserno) {
		this.buyUserno = buyUserno;
	}

	@SuppressWarnings("unchecked")
	public static void findList(String where, String orderby,
			List<Object> params, Page<Tlot> page) {
		TypedQuery<Tlot> q = Tlot.entityManager().createQuery(
				"SELECT o FROM Tlot o " + where + orderby, Tlot.class);
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
				"select count(o) from Tlot o " + where, Long.class);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				totalQ.setParameter(index, param);
				index = index + 1;
			}
		}
		page.setTotalResult(totalQ.getSingleResult().intValue());
		Query query = Tlot.entityManager().createQuery(
				"select nvl(sum(o.amt),0), nvl(sum(o.prizeamt),0) from Tlot o "
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

	@SuppressWarnings("unchecked")
	public static List<Tlot> findList(String where, String orderby, List<Object> params) {
		TypedQuery<Tlot> q = Tlot.entityManager().createQuery(
				"SELECT o FROM Tlot o " + where + orderby, Tlot.class);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		return q.getResultList();
	}
	
	public static List<Tlot> findBySubscribeno(String subscribeno) {
		return Tlot
				.entityManager()
				.createQuery(
						"select o from Tlot o where o.subscribeno = ? order by o.ordertime desc",
						Tlot.class).setParameter(1, subscribeno)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public static List<Object[]> findByDateGroupby(String date) {
		Query query = Tlot
				.entityManager()
				.createQuery(
						"select to_char(o.ordertime, 'yyyy-mm-dd hh24'), count(o) from Tlot o where o.state=1 and to_char(o.ordertime, 'yyyy-mm-dd')=? group by to_char(o.ordertime, 'yyyy-mm-dd hh24') order by to_char(o.ordertime, 'yyyy-mm-dd hh24') asc");
		query.setParameter(1, date);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public static List<Object[]> findByMonthGroupby(String month) {
		Query query = Tlot
				.entityManager()
				.createQuery(
						"select to_char(o.ordertime, 'yyyy-mm-dd'), count(o) from Tlot o where o.state=1 and to_char(o.ordertime, 'yyyy-mm')=? group by to_char(o.ordertime, 'yyyy-mm-dd') order by to_char(o.ordertime, 'yyyy-mm-dd') asc");
		query.setParameter(1, month);
		return query.getResultList();
	}

	public static List<Long> findByBetfail() {
		return Tlot
				.entityManager()
				.createQuery(
						"select count(o) from Tlot o where o.state = 1 and (o.instate<1 or o.instate>2) and o.ordertime between sysdate-7 and sysdate ",
						Long.class).getResultList();
	}

	public static List<Tlot> findFailList() {
		return Tlot
				.entityManager()
				.createQuery(
						"select o from Tlot o where o.state = 1 and o.instate!=1 and o.instate !=2 and o.ordertime between sysdate-7 and sysdate",
						Tlot.class).getResultList();
	}
	
	public static BigDecimal findUserPrize(String userno) {
		BigDecimal ret = entityManager().createQuery("select sum(prizeamt) from Tlot where userno=? ", BigDecimal.class).setParameter(1, userno).getSingleResult();
		return ret == null? BigDecimal.ZERO : ret;
	}
	
	public static BigDecimal findUserBetSuccess(String userno) {
//		BigDecimal ret = entityManager().createQuery("select sum(amt) from Tlot where buyuserno=? and state=1 and (subscribeno is null or subscribeno = ' ') ", BigDecimal.class).setParameter(1, userno).getSingleResult();
//		return ret == null? BigDecimal.ZERO : ret;
		return BigDecimal.ZERO;
	}
	
	public static BigDecimal findUserSubscribeSuccess(String userno) {
//		BigDecimal ret = entityManager().createQuery("select sum(amt) from Tlot where userno=? and state=1 and subscribeno is not null and subscribeno != ' '", BigDecimal.class).setParameter(1, userno).getSingleResult();
//		return ret == null? BigDecimal.ZERO : ret;
		return BigDecimal.ZERO;
	} 
	
	public static List<Object[]> countBuy(String channel, String starttime, String endtime){
		return entityManager().createQuery("select sum(o.amt)/100 ,count(distinct o.userno) from Tlot o where o.instate=1 and o.channel in ("+channel+") and to_char(o.ordertime, 'yyyy-mm-dd')>=? and to_char(o.ordertime, 'yyyy-mm-dd')<=?")
		.setParameter(1, starttime).setParameter(2, endtime).getResultList();
	}
	
	
	/**
	 * 新用户 充值人数 充值金额
	 * @param channel
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public static List<Object[]> getSumNewUserLot(String channel, String starttime, String endtime){
		return entityManager().createQuery("select sum(t.amt)/100 ,count(distinct t.userno) from Tlot t where t.instate=1 and t.channel in ("+channel+") and to_char(t.ordertime, 'yyyy-mm-dd')>=? "+
				" and to_char(t.ordertime, 'yyyy-mm-dd')<=? and t.userno in  (select userno  from Tuserinfo u  where  channel in ("+channel+") and to_char(u.regtime, 'yyyy-mm-dd')>=? and to_char(u.regtime, 'yyyy-mm-dd')<=? )" )
				.setParameter(1, starttime).setParameter(2, endtime).setParameter(3, starttime).setParameter(4, endtime).getResultList();
	}
	
	/**
	 * 中奖总金额
	 */
	public static BigDecimal getSumWin(String channel, String starttime, String endtime){
		return entityManager().createQuery("select sum(o.prizeamt)/100 from Tlot o where o.instate=1 and o.channel in ("+channel+") and to_char(o.prizetime, 'yyyy-mm-dd')>=? and to_char(o.prizetime, 'yyyy-mm-dd')<=? ", BigDecimal.class)
		.setParameter(1, starttime).setParameter(2, endtime).getSingleResult();
	}	
	
	
	public static int updateTime() {
		String sql = "update Tlot o set o.lotcenterordertime = ? where o.state = 1 and o.instate!=1 and o.instate !=2 and o.agencyno!='R00001'";
		int num = entityManager().createNativeQuery(sql)
				.setParameter(1, new Date())
				.executeUpdate();
		return num;
	}
	
	@SuppressWarnings("unchecked")
	public static Long statBuyer(String where, List<Object> params) {
		//TypedQuery<Long> q = Tlot.entityManager().createQuery(
			//	"select count (s) from (select o.userno, sum(o.amt) from Tlot o  " + where, Long.class);
		//Query q = Tlot.entityManager().createNativeQuery(
				//"select count (*) from (select o.userno, sum(o.amt) from Tlot o  " + where, Long.class);
		Query q = Tlot.entityManager().createNativeQuery("select A.userno, sum(A.amt) from (select t.userno userno, t.amt from torder t " + where);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		
		return Long.valueOf(q.getResultList().size());
	}
	
	@SuppressWarnings("unchecked")
	public static List<Object[]> statHighBuyer(String where, List<Object> params) {
		Query q = Tlot.entityManager().createNativeQuery("select A.userno, sum(A.amt) from (select t.userno userno, t.amt from torder t " + where);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Object[]> statSaleData(String where, List<Object> params) {
		//Query q = Tlot.entityManager().createNativeQuery("select t1.lotno, t2.name, t1.ct, t1.st from (select o.lotno, count(distinct o.userno) as ct, sum(o.amt) as st from Tlot o " + where);//tlot
		Query q = Tlot.entityManager().createNativeQuery("select ttt.lotno, tttt.name, ttt.ct, ttt.st from tlottypeconfig tttt, (select tt.lotno, count(distinct tt.userno) as ct, sum(tt.amt) as st from (select t1.userno, t1.lotno, t1.amt from torder t1 " + where);//含合买
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Object[]> statCurrentBuyer(String sql, List<Object> params) {
		Query q = Tlot.entityManager().createNativeQuery(sql);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Object> statCurrentBuyer2(String sql, List<Object> params) {
		Query q = Tlot.entityManager().createNativeQuery(sql);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		return q.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Object[]> findJcCount() {
		String date = DateUtil.format("yyyy-MM-dd", new Date());
		System.out.println("统计成功的日期为："+ date);
		return Tlot.entityManager().createNativeQuery(
						"select count(t.flowno),sum(t.amt),sum(t.prizeamt) from Tlot t where t.state = 1 and t.instate = 1 and t.agencyno='sdfcby' and to_char(t.lotcenterordertime, 'yyyy-mm-dd') >= ?  ")
				.setParameter(1, date) .getResultList();
	}
	@SuppressWarnings("unchecked")
	public static List<Long> findJcfailCount() {
		return Tlot.entityManager().createNativeQuery(
						"select count(t.flowno) from Tlot t where t.state = 1 and t.instate = 2 and t.agencyno='sdfcby' and t.ordertime between sysdate-7 and sysdate")
				.getResultList();
	}
	@SuppressWarnings("unchecked")
	public static List<Long> findJcFailCount() {
		return Tlot.entityManager().createNativeQuery(
						"SELECT count(o.flowno) FROM Tlot o where o.state = 1 and (o.instate>2 or o.instate < 1) and o.agencyno='sdfcby' and o.ordertime between sysdate-7 and sysdate")
				.getResultList();
	}
	@SuppressWarnings("unchecked")
	public static List<Long> findJcNotaudit() {
		return Tlot.entityManager().createNativeQuery(
						"select count(t.flowno) from Tlot t where t.state = 1 and t.instate = 1 and t.agencyno='sdfcby' and t.prizeamt > 0 and t.lotcenterisvalid = 0")
				.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static void findJcFailList(Page<Tlot> page) {
		TypedQuery<Tlot> q = Tlot.entityManager().createQuery(
				"SELECT o FROM Tlot o where o.state = 1 and o.instate = 2 and o.agencyno= 'sdfcby' and o.ordertime between sysdate-7 and sysdate order by o.ordertime desc" , Tlot.class);
		q.setFirstResult(page.getPageIndex() * page.getMaxResult())
				.setMaxResults(page.getMaxResult());
		page.setList(q.getResultList());
		
		TypedQuery<Long> totalQ = Tlot.entityManager().createQuery(
				"select count(o) from Tlot o where o.state = 1 and o.instate = 2 and o.agencyno= 'sdfcby' and o.ordertime between sysdate-7 and sysdate" , Long.class);
		page.setTotalResult(totalQ.getSingleResult().intValue());
	}
	@SuppressWarnings("unchecked")
	public static void findJcNotauditList(String lotno, Page<Tlot> page) {
		String sql = "SELECT o FROM Tlot o where o.state = 1 and o.instate = 1 and o.agencyno='sdfcby' and o.prizeamt > 0 and o.lotcenterisvalid = 0";
		if(!StringUtil.isEmpty(lotno)){
			sql += " and o.lotno = ?";
		}
		TypedQuery<Tlot> q = Tlot.entityManager().createQuery(sql, Tlot.class);
		if(!StringUtil.isEmpty(lotno)){
			q.setParameter(1, lotno);
		}
		q.setFirstResult(page.getPageIndex() * page.getMaxResult())
				.setMaxResults(page.getMaxResult());
		page.setList(q.getResultList());
		
		String count = "select count(o) from Tlot o where o.state = 1 and o.instate = 1 and o.agencyno='sdfcby' and o.prizeamt > 0 and o.lotcenterisvalid = 0";
		if(!StringUtil.isEmpty(lotno)){
			count += " and o.lotno = ?";
		}
		TypedQuery<Long> totalQ = Tlot.entityManager().createQuery(count, Long.class);
		if(!StringUtil.isEmpty(lotno)){
			totalQ.setParameter(1, lotno);
		}
		page.setTotalResult(totalQ.getSingleResult().intValue());
	}
	
	@SuppressWarnings("unchecked")
	public static void findJctrueFailList(Page<Tlot> page) {
		TypedQuery<Tlot> q = Tlot.entityManager().createQuery(
				"SELECT o FROM Tlot o where o.state = 1 and (o.instate>2 or o.instate < 1) and o.agencyno='sdfcby' and o.ordertime between sysdate-7 and sysdate" , Tlot.class);
		q.setFirstResult(page.getPageIndex() * page.getMaxResult())
				.setMaxResults(page.getMaxResult());
		page.setList(q.getResultList());
		
		TypedQuery<Long> totalQ = Tlot.entityManager().createQuery(
				"SELECT count(o) FROM Tlot o where o.state = 1 and (o.instate>2 or o.instate < 1) and o.agencyno='sdfcby' and o.ordertime between sysdate-7 and sysdate" , Long.class);
		page.setTotalResult(totalQ.getSingleResult().intValue());
	}
	
	public static List<Tlot> findTlotByFlowno(String flowno){
		return Tlot
				.entityManager()
				.createQuery(
						"select o from Tlot o where o.flowno = ?",
						Tlot.class)
				.setParameter(1, flowno).getResultList();
	}
}
