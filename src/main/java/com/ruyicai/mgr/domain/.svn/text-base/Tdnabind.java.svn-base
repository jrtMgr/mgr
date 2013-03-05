package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.TableGenerator;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.consts.Const;
import com.ruyicai.mgr.exception.RuyicaiException;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.Page;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TDNABIND", schema = "JRTSCH", identifierField = "id", finders = {
		"findTdnabindsByUserno", "findTdnabindsByMobileid" })
public class Tdnabind {

	@Id
	@GeneratedValue(generator = Const.IdGeneratorName)
	@GenericGenerator(name = Const.IdGeneratorName, strategy = Const.IdStrategy, //
	parameters = {
			@Parameter(name = TableGenerator.SEGMENT_COLUMN_PARAM, value = "ID"),
			@Parameter(name = TableGenerator.SEGMENT_VALUE_PARAM, value = "ID"),
			@Parameter(name = TableGenerator.VALUE_COLUMN_PARAM, value = "SEQ"),
			@Parameter(name = TableGenerator.TABLE_PARAM, value = "TSEQ") })
	@Column(name = "ID")
	private String id;

	@Column(name = "MOBILEID")
	private String mobileid;

	@Column(name = "NAME")
	private String name;

	@Column(name = "BANKCARDNO")
	private String bankcardno;

	@Column(name = "CERTID")
	private String certid;

	@Column(name = "BANKADDRESS")
	private String bankaddress;

	@Column(name = "CERTIDADDRESS")
	private String certidaddress;

	@Column(name = "BINDTIME")
	private Date bindtime;

	@Column(name = "STATE")
	private BigDecimal state;

	@Column(name = "USERNO")
	private String userno;

	@Column(name = "BANKNAME")
	private String bankname;
	
	public static Tdnabind findByUsernoAndState(String userno, BigDecimal state) {
		List<Tdnabind> results = Tdnabind
				.entityManager()
				.createQuery(
						"SELECT o FROM Tdnabind AS o WHERE o.userno = :userno and o.state = :state",
						Tdnabind.class).setParameter("userno", userno)
				.setParameter("state", state).getResultList();
		if (results.isEmpty()) {
			throw new RuyicaiException(ErrorCode.Tdnabind_NotExists);
		}
		return results.get(0);
	}

	public static void deleteByUsernoAndState(String userno, BigDecimal state) {
		Query query = Tdnabind
				.entityManager()
				.createQuery(
						"delete from Tdnabind t where t.userno = ? and t.state = ?")
				.setParameter(1, userno).setParameter(2, state);
		query.executeUpdate();
	}
	
	public static Tdnabind createTdnabind(String mobileid, String name, String bankcardno, String certid, String bankaddress, String certidaddress, BigDecimal state, String userno) {
		Tdnabind tdnabind = new Tdnabind();
		tdnabind.setMobileid(mobileid);
		tdnabind.setName(name);
		tdnabind.setBankcardno(bankcardno);
		tdnabind.setCertid(certid);
		tdnabind.setBankaddress(bankaddress);
		tdnabind.setCertidaddress(certidaddress);
		tdnabind.setCertid(certid);
		tdnabind.setState(state);
		tdnabind.setUserno(userno);
		tdnabind.setBindtime(new Date());
		tdnabind.persist();
		return tdnabind;
	}
	
	public static Tdnabind modifyTdnabind(String mobileid, String name, String bankcardno, String certid, String bankaddress, String certidaddress, BigDecimal state, String userno) {
		List<Tdnabind> results = Tdnabind.entityManager().createQuery(
				"SELECT o FROM Tdnabind AS o WHERE o.userno = :userno and o.state = :state",
				Tdnabind.class).setParameter("userno", userno).setParameter("state", state).getResultList();
		Tdnabind tdnabind = null;
		if (results.isEmpty()) {
			tdnabind = new Tdnabind();
			tdnabind.setMobileid(mobileid);
			tdnabind.setName(name);
			tdnabind.setBankcardno(bankcardno);
			tdnabind.setCertid(certid);
			tdnabind.setBankaddress(bankaddress);
			tdnabind.setCertidaddress(certidaddress);
			tdnabind.setCertid(certid);
			tdnabind.setState(state);
			tdnabind.setUserno(userno);
			tdnabind.setBindtime(new Date());
			tdnabind.persist();
		} else {
			tdnabind = results.get(0);
			tdnabind.setMobileid(mobileid);
			tdnabind.setName(name);
			tdnabind.setBankcardno(bankcardno);
			tdnabind.setCertid(certid);
			tdnabind.setBankaddress(bankaddress);
			tdnabind.setCertidaddress(certidaddress);
			tdnabind.setCertid(certid);
			tdnabind.setState(state);
			//tdnabind.setUserno(userno);
			tdnabind.setBindtime(new Date());
			tdnabind.merge();
		}		
		return tdnabind;
	}
	
	public static void findList(String where, String orderby,
			List<Object> params, Page<Tdnabind> page) {
		TypedQuery<Tdnabind> q = Tlot.entityManager().createQuery(
				"SELECT o FROM Tdnabind o " + where + orderby, Tdnabind.class);
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
				"select count(o) from Tdnabind o " + where, Long.class);
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
