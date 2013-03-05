package com.ruyicai.mgr.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.exception.RuyicaiException;
import com.ruyicai.mgr.util.Page;

@RooJavaBean
@RooToString
@RooJson
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TACTIVITIES", identifierField = "id")
public class Tactivities {
	/** 彩种 */
	private String lotno;
	/** 大渠道 */
	private String subChannel;
	/** 渠道 */
	private String channel;
	/** 活动类型 */
	private Integer activityType;
	/** 活动金额表达式 */
	private String amt;
	/** 状态 0:失效,1:有效 */
	private Integer state;

	/**
	 * 创建活动
	 * 
	 * @param lotno
	 *            彩种,不需要传Null
	 * @param subChannel
	 *            用户大渠道subChannel(subChannel和channel必须有一个不为空)
	 * @param channel
	 *            用户渠道channel(subChannel和channel必须有一个不为空)
	 * @param activityType
	 *            活动类型
	 * @param amt
	 *            活动金额表达式,根据各种活动定义(默认有效)
	 * @param state
	 *            状态
	 * @return
	 */
	public static Tactivities createIfNotExist(String lotno, String subChannel, String channel, Integer activityType,
			String amt, Integer state) {
		if (judgeExist(lotno, subChannel, channel, activityType)) {
			throw new RuyicaiException("活动已存在");
		}
		// state默认有效
		if (state == null) {
			state = 1;
		}
		Tactivities tactivities = new Tactivities();
		tactivities.setLotno(lotno);
		tactivities.setSubChannel(subChannel);
		tactivities.setChannel(channel);
		tactivities.setActivityType(activityType);
		tactivities.setState(state);
		tactivities.setAmt(amt);
		tactivities.persist();
		return tactivities;
	}

	/**
	 * 更新活动状态
	 * 
	 * @param id
	 *            活动ID
	 * @param state
	 *            状态类型
	 */
	public static void updateTactivitiesDisable(Long id, Integer state) {
		Tactivities tactivities = Tactivities.findTactivities(id);
		tactivities.setState(state);
		tactivities.merge();
	}

	/**
	 * 判断是否存在
	 * 
	 * @param lotno
	 *            彩种,不需要的传Null
	 * @param subChannel
	 *            用户大渠道subChannel(subChannel和channel必须有一个不为空)
	 * @param channel
	 *            用户渠道channel(subChannel和channel必须有一个不为空)
	 * @param activityType
	 *            活动类型
	 * @return
	 */
	public static Boolean judgeExist(String lotno, String subChannel, String channel, Integer activityType) {
		TypedQuery<Tactivities> query = Tactivities.findTactivitiesByLotnoAndSubchannelAndChannelAndActivitytype(lotno,
				subChannel, channel, activityType);
		List<Tactivities> list = query.getResultList();
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 查询有效的活动
	 * 
	 * @param lotno
	 *            彩种,不需要的传Null
	 * @param subChannel
	 *            用户大渠道subChannel(subChannel和channel必须有一个不为空)
	 * @param channel
	 *            用户渠道channel(subChannel和channel必须有一个不为空)
	 * @param activityType
	 *            活动类型
	 * @return
	 */
	public static Tactivities findTactivities(String lotno, String subChannel, String channel, Integer activityType) {
		if (activityType == null)
			throw new IllegalArgumentException("The activityType argument is required");
		if (StringUtils.isBlank(subChannel) && StringUtils.isBlank(channel))
			throw new IllegalArgumentException("subChannel或channel至少有一个不为空");
		EntityManager em = Tactivities.entityManager();
		StringBuffer sql = new StringBuffer(
				"SELECT o FROM Tactivities AS o WHERE o.state = '1' AND o.activityType = :activityType");
		if (StringUtils.isNotBlank(lotno)) {
			sql.append(" AND o.lotno = :lotno");
		}
		if (StringUtils.isNotBlank(subChannel)) {
			sql.append(" AND o.subChannel = :subChannel");
		}
		if (StringUtils.isNotBlank(channel)) {
			sql.append(" AND o.channel = :channel");
		}
		TypedQuery<Tactivities> q = em.createQuery(sql.toString(), Tactivities.class);
		q.setParameter("activityType", activityType);
		if (StringUtils.isNotBlank(lotno)) {
			q.setParameter("lotno", lotno);
		}
		if (StringUtils.isNotBlank(subChannel)) {
			q.setParameter("subChannel", subChannel);
		}
		if (StringUtils.isNotBlank(channel)) {
			q.setParameter("channel", channel);
		}
		List<Tactivities> resultList = q.getResultList();
		if (resultList != null && resultList.size() > 0) {
			return resultList.get(0);
		} else {
			return null;
		}
	}

	public static TypedQuery<Tactivities> findTactivitiesByLotnoAndSubchannelAndChannelAndActivitytype(String lotno,
			String subChannel, String channel, Integer activityType) {
		if (activityType == null)
			throw new IllegalArgumentException("The activityType argument is required");
		if (StringUtils.isBlank(subChannel) && StringUtils.isBlank(channel))
			throw new IllegalArgumentException("subChannel或channel至少有一个不为空");
		EntityManager em = Tactivities.entityManager();
		StringBuffer sql = new StringBuffer("SELECT o FROM Tactivities AS o WHERE o.activityType = :activityType");
		if (StringUtils.isNotBlank(lotno)) {
			sql.append(" AND o.lotno = :lotno");
		}
		if (StringUtils.isNotBlank(subChannel)) {
			sql.append(" AND o.subChannel = :subChannel");
		}
		if (StringUtils.isNotBlank(channel)) {
			sql.append(" AND o.channel = :channel");
		}
		TypedQuery<Tactivities> q = em.createQuery(sql.toString(), Tactivities.class);
		q.setParameter("activityType", activityType);
		if (StringUtils.isNotBlank(lotno)) {
			q.setParameter("lotno", lotno);
		}
		if (StringUtils.isNotBlank(subChannel)) {
			q.setParameter("subChannel", subChannel);
		}
		if (StringUtils.isNotBlank(channel)) {
			q.setParameter("channel", channel);
		}
		return q;
	}
	
	public static void findList(String where, String orderby, List<Object> params, Page<Tactivities> page) {
		TypedQuery<Tactivities> q = Tactivities.entityManager().createQuery(
				"SELECT o FROM Tactivities o " + where + orderby, Tactivities.class);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		q.setFirstResult(page.getPageIndex() * page.getMaxResult()).setMaxResults(page.getMaxResult());
		page.setList(q.getResultList());
		
		TypedQuery<Long> totalQ = Tactivities.entityManager().createQuery(
				"select count(o) from Tactivities o " + where, Long.class);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				totalQ.setParameter(index, param);
				index = index + 1;
			}
		}
		page.setTotalResult(totalQ.getSingleResult().intValue());	
	}
		
	public static void updateTactivities(Long id, String lotno, String subchannel, String channel, 
			Integer activitytype, String amt, Integer state) {
		Tactivities tactivities = Tactivities.findTactivities(id);
		tactivities.setLotno(lotno);
		tactivities.setSubChannel(subchannel);
		tactivities.setChannel(channel);
		tactivities.setActivityType(activitytype);
		tactivities.setAmt(amt);
		tactivities.setState(state);
		tactivities.merge();
	}
	
	public static void updateTactivitiesBy(String lotno, String subchannel, String channel, 
			Integer activitytype, String amt, Integer state) {
		/*Tactivities.entityManager().createNativeQuery("update Tactivities set amt=?, state=? where activityType=? and " +
				"lotno=? and subChannel=? and channel in (" + "?) ").setParameter(1, amt).setParameter(2, state)
				.setParameter(3, activitytype).setParameter(4, lotno).setParameter(5, subchannel).setParameter(6, channel).executeUpdate();*/
		
		Tactivities.entityManager().createQuery("update Tactivities set amt=?, state=? where activityType=? and " +
				"lotno=? and subChannel=? and channel=?").setParameter(1, amt).setParameter(2, state)
				.setParameter(3, activitytype).setParameter(4, lotno).setParameter(5, subchannel).setParameter(6, channel).executeUpdate();
	}
	
	public static void updateTactivitiesBy(String subchannel, String channel, 
			Integer activitytype, String amt, Integer state) {
		/*Tactivities.entityManager().createNativeQuery("update Tactivities set amt=?, state=? where activityType=? and " +
				"subChannel=? and channel in (" + "?) ").setParameter(1, amt).setParameter(2, state)
				.setParameter(3, activitytype).setParameter(4, subchannel).setParameter(5, channel).executeUpdate();*/
		
		Tactivities.entityManager().createQuery("update Tactivities set amt=?, state=? where activityType=? and " +
				"subChannel=? and channel=?").setParameter(1, amt).setParameter(2, state)
				.setParameter(3, activitytype).setParameter(4, subchannel).setParameter(5, channel).executeUpdate();
	}
	
	
	public static void updateTactivities(String lotno, String subchannel, 
			Integer activitytype, String amt, Integer state) {		
		Tactivities.entityManager().createNativeQuery("update Tactivities set amt=?, state=? where activityType=? and " +
				"lotno=? and subChannel=? ").setParameter(1, amt).setParameter(2, state)
				.setParameter(3, activitytype).setParameter(4, lotno).setParameter(5, subchannel).executeUpdate();
	}
	
	public static void updateTactivities(String subchannel, 
			Integer activitytype, String amt, Integer state) {		
		Tactivities.entityManager().createNativeQuery("update Tactivities set amt=?, state=? where activityType=? and " +
				"subChannel=? ").setParameter(1, amt).setParameter(2, state)
				.setParameter(3, activitytype).setParameter(4, subchannel).executeUpdate();
	}
}
