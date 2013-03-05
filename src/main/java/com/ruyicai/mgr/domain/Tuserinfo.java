package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.TableGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.dbre.RooDbManaged;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

import com.ruyicai.mgr.consts.Const;
import com.ruyicai.mgr.exception.RuyicaiException;
import com.ruyicai.mgr.util.DateUtil;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.PaySign;

@RooJavaBean
@RooJson
@RooEntity(persistenceUnit = "persistenceUnit", table = "TUSERINFO", schema = "JRTSCH", versionField = "", identifierField = "userno", identifierColumn = "USERNO", finders = {
		"findTuserinfoesByUserName", "findTuserinfoesByMobileid",
		"findTuserinfoesByEmail" })
//@RooDbManaged(automaticallyDelete = true)
public class Tuserinfo {

	@Id
	@GeneratedValue(generator = Const.IdGeneratorName)
	@GenericGenerator(name = Const.IdGeneratorName, strategy = Const.IdStrategy, parameters = {
			@Parameter(name = Const.Seq_fmtWidth, value = "8"),
			@Parameter(name = TableGenerator.SEGMENT_COLUMN_PARAM, value = "ID"),
			@Parameter(name = TableGenerator.SEGMENT_VALUE_PARAM, value = "userNo"),
			@Parameter(name = TableGenerator.VALUE_COLUMN_PARAM, value = "SEQ"),
			@Parameter(name = TableGenerator.TABLE_PARAM, value = "TSEQ") })
	@Column(name = "USERNO")
	private String userno;

	@Column(name = "MODTIME", columnDefinition = "TIMESTAMP(6)")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date modtime = DateUtil.get1000Date();

	@Column(name = "REGTIME", columnDefinition = "TIMESTAMP(6)")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "S-")
	private Date regtime = DateUtil.get1000Date();

	@Column(name = "QQ", columnDefinition = "VARCHAR2", length = 60)
	private String qq = " ";

	@Column(name = "USERNAME", columnDefinition = "VARCHAR2", length = 60)
	private String userName = " ";

	@Column(name = "MSN", columnDefinition = "VARCHAR2", length = 128)
	private String msn = " ";

	@Column(name = "NAME", columnDefinition = "VARCHAR2", length = 80)
	private String name = " ";

	@Column(name = "PHONE", columnDefinition = "VARCHAR2", length = 200)
	private String phone = " ";

	@Column(name = "NICKNAME", columnDefinition = "VARCHAR2", length = 256)
	private String nickname = " ";

	@Column(name = "TYPE", columnDefinition = "NUMBER")
	private BigDecimal type = new BigDecimal(0);

	@Column(name = "MOBILEID", columnDefinition = "VARCHAR2", length = 44, unique = true)
	@NotNull
	private String mobileid = " ";

	@Column(name = "STATE", columnDefinition = "NUMBER")
	@NotNull
	private BigDecimal state = new BigDecimal(0);

	@Column(name = "AGENCYNO", columnDefinition = "VARCHAR2", length = 24)
	@NotNull
	private String agencyno = " ";

	@Column(name = "PASSWORD", columnDefinition = "VARCHAR2", length = 256)
	@NotNull
	private String password = " ";

	@Column(name = "CERTID", columnDefinition = "VARCHAR2", length = 72)
	@NotNull
	private String certid = " ";

	@Column(name = "EMAIL", columnDefinition = "VARCHAR2", length = 200)
	@NotNull
	private String email = " ";

	@Column(name = "ADDRESS", columnDefinition = "VARCHAR2", length = 200)
	@NotNull
	private String address = " ";

	@Column(name = "INFO", columnDefinition = "VARCHAR2", length = 200)
	@NotNull
	private String info = " ";

	@Column(name = "MAC", columnDefinition = "VARCHAR2", length = 256)
	@NotNull
	private String mac = " ";

	@Column(name = "ACCESSTYPE", columnDefinition = "CHAR")
	@NotNull
	private Character accesstype = 'N';

	@Column(name = "CHANNEL", columnDefinition = "VARCHAR2", length = 44)
	@NotNull
	private String channel = " ";

	@Column(name = "LEAVE", columnDefinition = "VARCHAR2", length = 80)
	@NotNull
	private String leave = " ";

	@Column(name = "SUBCHANNEL", columnDefinition = "VARCHAR2", length = 8)
	private String subChannel;
	
	@Column(name = "IMEI", columnDefinition = "VARCHAR2", length = 32)
	private String imei;

	@Column(name = "DEDUCT", columnDefinition = "NUMBER", length = 2)
	private BigDecimal deduct;
	
	public BigDecimal getDeduct() {
		return deduct;
	}

	public void setDeduct(BigDecimal deduct) {
		this.deduct = deduct;
	}

	public String getImei() {
		return " ".equals(imei) ? null : imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public String getSubChannel() {
		return " ".equals(subChannel) ? null : subChannel;
	}

	public void setSubChannel(String subChannel) {
		this.subChannel = subChannel;
	}

	public String getUserno() {
		return this.userno;
	}

	public void setUserno(String userno) {
		this.userno = userno;
	}

	public Date getModtime() {
		return this.modtime;
	}

	public void setModtime(Date modtime) {
		this.modtime = modtime;
	}

	public Date getRegtime() {
		return this.regtime;
	}

	public void setRegtime(Date regtime) {
		this.regtime = regtime;
	}

	public String getQq() {
		return " ".equals(qq) ? null : this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getUserName() {
		return " ".equals(this.userName) ? null : this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMsn() {
		return " ".equals(this.msn) ? null : this.msn;
	}

	public void setMsn(String msn) {
		this.msn = msn;
	}

	public String getName() {
		return " ".equals(this.name) ? null : this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return " ".equals(this.phone) ? null : this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNickname() {
		return " ".equals(this.nickname) ? null : this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public BigDecimal getType() {
		return this.type;
	}

	public void setType(BigDecimal type) {
		this.type = type;
	}

	public String getMobileid() {
		return " ".equals(this.mobileid) ? null : this.mobileid;
	}

	public void setMobileid(String mobileid) {
		this.mobileid = mobileid;
	}

	public BigDecimal getState() {
		return this.state;
	}

	public void setState(BigDecimal state) {
		this.state = state;
	}

	public String getAgencyno() {
		return " ".equals(this.agencyno) ? null : this.agencyno;
	}

	public void setAgencyno(String agencyno) {
		this.agencyno = agencyno;
	}

	public String getPassword() {
		return " ".equals(this.password) ? null : this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCertid() {
		return " ".equals(this.certid) ? null : this.certid;
	}

	public void setCertid(String certid) {
		this.certid = certid;
	}

	public String getEmail() {
		return " ".equals(this.email) ? null : this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return " ".equals(this.address) ? null : this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInfo() {
		return " ".equals(this.info) ? null : this.info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getMac() {
		return " ".equals(this.mac) ? null : this.mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Character getAccesstype() {
		return this.accesstype;
	}

	public void setAccesstype(Character accesstype) {
		this.accesstype = accesstype;
	}

	public String getChannel() {
		return " ".equals(this.channel) ? null : this.channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getLeave() {
		return " ".equals(this.leave) ? null : this.leave;
	}

	public void setLeave(String leave) {
		this.leave = leave;
	}

	@Transactional
	public Tuserinfo merge() {
		this.setMac(this.userInfoSign());
		if (this.entityManager == null)
			this.entityManager = entityManager();
		Tuserinfo merged = this.entityManager.merge(this);
		this.entityManager.flush();
		return merged;
	}

	@Transactional
	public void persist() {
		if (this.entityManager == null)
			this.entityManager = entityManager();
		this.entityManager.persist(this);
		this.entityManager.flush();
		Tuserinfo user = Tuserinfo.findTuserinfo(this.getUserno());
		this.setMac(user.userInfoSign());
		user.merge();
	}

	public static void findList(String where, String orderby, List<Object> params, Page<Tuserinfo> page) {
		TypedQuery<Tuserinfo> q = Tuserinfo.entityManager()
				.createQuery(
						"SELECT o FROM Tuserinfo o " + where + orderby,
						Tuserinfo.class);
		if(null != params && !params.isEmpty()) {
			int index = 1;
			for(Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		page.setList(q.getResultList());
		TypedQuery<Long> totalQ = Tuserinfo.entityManager().createQuery("select count(o) from Tuserinfo o " + where, Long.class);
		if(null != params && !params.isEmpty()) {
			int index = 1;
			for(Object param : params) {
				totalQ.setParameter(index, param);
				index = index + 1;
			}
		}
		page.setTotalResult(totalQ.getSingleResult().intValue());
	}

	private String userInfoSign() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuffer str = new StringBuffer();
		str.append(this.getUserno()).append(this.getState())
				.append(this.getType()).append(sdf.format(this.getRegtime()))
				.append(sdf.format(this.getModtime()));
		try {
			return PaySign.Md5(str.toString());
		} catch (Exception e) {
			Logger logger = Logger.getLogger(Tuserinfo.class);
			logger.error("生成用户信息校验码出错", e);
			throw new RuyicaiException(ErrorCode.UserReg_UserMacError);
		}
	}
	
	public static Tuserinfo findTuserinfoByuserno(String userno, String subChannel) {
        if (userno == null || userno.length() == 0) return null;
		EntityManager em = Tuserinfo.entityManager();
		TypedQuery<Tuserinfo> q = em
				.createQuery(
						"SELECT Tuserinfo FROM Tuserinfo AS tuserinfo WHERE tuserinfo.userno = :userno and tuserinfo.subChannel = :subChannel",
						Tuserinfo.class);
		q.setParameter("userno", userno);
		q.setParameter("subChannel", subChannel);
		List<Tuserinfo> tuserinfos = q.getResultList(); 
		return tuserinfos.isEmpty() ? null : tuserinfos.get(0);
		
    }
	public static Tuserinfo findTuserinfoesByUserName(
			String userName, String subChannel) {
		if (userName == null || userName.length() == 0)
			throw new IllegalArgumentException(
					"The userName argument is required");
		EntityManager em = Tuserinfo.entityManager();
		TypedQuery<Tuserinfo> q = em
				.createQuery(
						"SELECT Tuserinfo FROM Tuserinfo AS tuserinfo WHERE tuserinfo.userName = :userName and tuserinfo.subChannel = :subChannel",
						Tuserinfo.class);
		q.setParameter("userName", userName);
		q.setParameter("subChannel", subChannel);
		List<Tuserinfo> tuserinfos = q.getResultList(); 
		return tuserinfos.isEmpty() ? null : tuserinfos.get(0);
	}

	public static Tuserinfo findTuserinfoesByEmail(String email,
			String subChannel) {
		if (email == null || email.length() == 0)
			throw new IllegalArgumentException("The email argument is required");
		EntityManager em = Tuserinfo.entityManager();
		TypedQuery<Tuserinfo> q = em
				.createQuery(
						"SELECT Tuserinfo FROM Tuserinfo AS tuserinfo WHERE tuserinfo.email = :email and tuserinfo.subChannel = :subChannel",
						Tuserinfo.class);
		q.setParameter("email", email);
		q.setParameter("subChannel", subChannel);
		List<Tuserinfo> tuserinfos = q.getResultList(); 
		return tuserinfos.isEmpty() ? null : tuserinfos.get(0);
	}

	public static Tuserinfo findTuserinfoesByMobileid(
			String mobileid, String subChannel) {
		if (mobileid == null || mobileid.length() == 0)
			throw new IllegalArgumentException(
					"The mobileid argument is required");
		EntityManager em = Tuserinfo.entityManager();
		TypedQuery<Tuserinfo> q = em
				.createQuery(
						"SELECT Tuserinfo FROM Tuserinfo AS tuserinfo WHERE tuserinfo.mobileid = :mobileid and tuserinfo.subChannel = :subChannel",
						Tuserinfo.class);
		q.setParameter("mobileid", mobileid);
		q.setParameter("subChannel", subChannel);
		List<Tuserinfo> tuserinfos = q.getResultList(); 
		return tuserinfos.isEmpty() ? null : tuserinfos.get(0);
	}

	public static Page<Tuserinfo> findTuserinfoesByPage(String queryParam, Page<Tuserinfo> page) {
		EntityManager em = Tuserinfo.entityManager();
		String sql = "SELECT o FROM Tuserinfo o ";
		String countSql = "SELECT count(*) FROM Tuserinfo o ";
		StringBuilder whereSql = new StringBuilder(" WHERE 1=1 ");
		if (StringUtils.isNotBlank(queryParam)) {
			whereSql.append(" AND (userno like :queryParam1 OR username like :queryParam2 )");
		}
		StringBuilder orderSql = new StringBuilder(" ORDER BY ");
		if (page.isOrderBySetted()) {
			orderSql.append(" " + page.getOrderBy() + " " + page.getOrderDir());
		} else {
			orderSql.append(" userno ASC");
		}
		String tsql = sql + whereSql.toString() + orderSql.toString();
		String tCountSql = countSql + whereSql.toString();
		TypedQuery<Tuserinfo> q = em.createQuery(tsql, Tuserinfo.class);
		TypedQuery<Long> total = em.createQuery(tCountSql, Long.class);
		if (StringUtils.isNotBlank(queryParam)) {
			q.setParameter("queryParam1", "%" + queryParam + "%");
			total.setParameter("queryParam1", "%" + queryParam + "%");
			q.setParameter("queryParam2", "%" + queryParam + "%");
			total.setParameter("queryParam2", "%" + queryParam + "%");
		}
		q.setFirstResult(page.getPageIndex()).setMaxResults(page.getMaxResult());
		List<Tuserinfo> resultList = q.getResultList();
		int count = total.getSingleResult().intValue();
		page.setList(resultList);
		page.setTotalResult(count);
		return page;
	}
	
	/**
	 * 注册总人数
	 * @param channel
	 * @param starttime
	 * @param endtime
	 * @return
	 */
	public static Long countRegistUser(String channel, String starttime, String endtime){
		return Tuserinfo.entityManager().createQuery("select count(o) from Tuserinfo o where o.channel in ("+channel+") and to_char(o.regtime, 'yyyy-mm-dd')>=? and to_char(o.regtime, 'yyyy-mm-dd')<= ? ", Long.class)
		.setParameter(1, starttime).setParameter(2, endtime).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public static Long statRegUser(String where, List<Object> params) {	
		Query q = Tuserinfo.entityManager().createQuery("select count(o) from Tuserinfo o  " + where);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		
		return (Long)q.getSingleResult();
	}
	
	public static Tuserinfo findTuserinfoByUsernoOnly(String userno) {
        if (userno == null || userno.length() == 0) return null;
		EntityManager em = Tuserinfo.entityManager();
		TypedQuery<Tuserinfo> q = em
				.createQuery(
						"SELECT Tuserinfo FROM Tuserinfo AS tuserinfo WHERE tuserinfo.userno = :userno ",
						Tuserinfo.class);
		q.setParameter("userno", userno);
		List<Tuserinfo> tuserinfos = q.getResultList(); 
		return tuserinfos.isEmpty() ? null : tuserinfos.get(0);
		
    }
}
