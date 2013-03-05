package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.LockModeType;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.enhanced.TableGenerator;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.transaction.annotation.Transactional;

import com.ruyicai.mgr.consts.Const;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.Page.Sort;
import com.ruyicai.mgr.util.PropertyFilter;

@RooEntity(persistenceUnit = "persistenceUnit",  versionField="",finders = { "findCaseLotsByLotnoAndState", "findCaseLotsByStarter",
		"findCaseLotsByLotnoAndBatchcodeAndState" })
@RooJavaBean
@RooJson
public class CaseLot {

	@Id
	@GeneratedValue(generator = Const.IdGeneratorName)
	@GenericGenerator(name = Const.IdGeneratorName, strategy = Const.IdStrategy, //
	parameters = { @Parameter(name = Const.Seq_prefix, value = Const.CaseIdPrefix),
			@Parameter(name = Const.Seq_fmtWidth, value = "14"),
			@Parameter(name = TableGenerator.SEGMENT_COLUMN_PARAM, value = "ID"),
			@Parameter(name = TableGenerator.SEGMENT_VALUE_PARAM, value = "flowNo"),
			@Parameter(name = TableGenerator.VALUE_COLUMN_PARAM, value = "SEQ"),
			@Parameter(name = TableGenerator.TABLE_PARAM, value = "TSEQ") })
	@Column(name = "id")
	private String id;

	private String starter;

	/** 合买最小购买金额。也代表每注多少钱oneamount */
	private Long minAmt;

	private Long totalAmt;

	private Long safeAmt;

	private Long buyAmtByStarter;
	/** 佣金比例 */
	private BigDecimal commisionRatio;

	private Date startTime;

	private Date endTime;
	/** 显示类型 */
	private Integer visibility;

	private String description;

	private String title;
	/** 交易状态 */
	private BigDecimal state;
	/** 用户看到的状态 */
	private BigDecimal displayState;
	/** 用户看到的状态描述 */
	private String displayStateMemo;

	private String content;

	private String orderid;

	private String lotno;

	private String batchcode;

	private BigDecimal winFlag;
	/** 暂时没用 */
	private Long winLittleAmt;
	/** 税后奖金 */
	private Long winBigAmt;
	/** 税前奖金 */
	private Long winPreAmt;
	/** 中奖时间 */
	private Date winStartTime;

	private Date winEndTime;

	private String winDetail;

	private String caselotinfo;
	/** 参与人购买金额 */
	private long buyAmtByFollower;
	/** 0：普通合买，3：申请置顶合买大厅，4:申请置顶合买中心，8：置顶合买大厅，9：置顶合买中心 */
	private Integer sortState;
	/** 参与人数 */
	private Long participantCount;
	/** 方案类型 */
	private BigDecimal lotsType;
	/** 是否有战绩 0：没有，1：有 */
	private BigDecimal hasachievement;
	/** 是否中奖 0：没有，1：有.(取消的合买也会有中奖状态) */
	private BigDecimal isWinner;

	public static CaseLot findCaseLot(String id, boolean lock) {
		EntityManager entityManager = entityManager();
		CaseLot account = entityManager.find(CaseLot.class, id, lock ? LockModeType.PESSIMISTIC_WRITE
				: LockModeType.NONE);
		return account;
	}

	public static CaseLot findCaseLot(String id) {
		return findCaseLot(id, false);
	}

	/**
	 * 查询合买分页
	 * 
	 * @param state
	 *            合买状态数组
	 * @param sortStateFlag
	 *            合买排序标识。0：合买大厅，1：合买中心
	 * @param conditionMap
	 *            查询条件集合
	 * @param page
	 *            分页集合
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	public static Page<CaseLot> findCaseLot(BigDecimal[] state, Integer sortStateFlag, String search,
			Map<String, Object> conditionMap, Page<CaseLot> page) {
		EntityManager em = CaseLot.entityManager();
		String sql = "SELECT o.* FROM CaseLot o left join Tuserinfo u on o.starter=u.userno left join Tuserachievement a on o.lotno = a.lotno and o.starter = a.userno ";
		String countSql = "SELECT count(*) FROM CaseLot o left join Tuserinfo u on o.starter=u.userno left join Tuserachievement a on o.lotno = a.lotno and o.starter = a.userno ";
		StringBuilder whereSql = new StringBuilder(" WHERE 1=1 ");

		// 组装whereSql开始
		if (null != state && state.length > 0) {
			whereSql.append(" AND (");
			int index = 1;
			for (BigDecimal s : state) {
				whereSql.append(" o.state=:state" + index + " or");
				index += 1;
			}
			whereSql.delete(whereSql.length() - 2, whereSql.length());
			whereSql.append(")");
		}
		if (StringUtils.isNotBlank(search)) {
			whereSql.append(" AND ( o.id like :search1 or u.nickname like :search2 ) ");
		}
		List<PropertyFilter> pfList = null;
		if (conditionMap != null && conditionMap.size() > 0) {
			pfList = PropertyFilter.buildFromMap(conditionMap);
			String buildSql = PropertyFilter.transfer2Sql(pfList, "o");
			whereSql.append(buildSql);
		}
		// 组装whereSql结束
		// 组装置顶排序开始
		List<Sort> sortList = page.fetchSort();
		StringBuilder topOrderSql = new StringBuilder(" ORDER BY o.sortState DESC, ");
		if (page.isOrderBySetted()) {
			for (Sort sort : sortList) {
				topOrderSql.append(" " + sort.getProperty() + " " + sort.getDir() + ",");
			}
			topOrderSql.append(" o.startTime DESC ");
		} else {
			topOrderSql.append(" o.totalAmt DESC,o.startTime DESC ");
		}
		// 组装置顶排序结束
		// sortState值大于4的，为置顶合买
		String topWhereSql = whereSql.toString() + " AND o.sortState > 4";
		String topSql = sql + topWhereSql + topOrderSql.toString();
		String topCountSql = countSql + topWhereSql;
		String allCountSql = countSql + whereSql.toString();

		Query q = em.createNativeQuery(topSql, CaseLot.class);
		Query topTotal = em.createNativeQuery(topCountSql);
		Query allTotal = em.createNativeQuery(allCountSql);
		if (null != state && state.length > 0) {
			int index = 1;
			for (BigDecimal s : state) {
				q.setParameter("state" + index, s);
				topTotal.setParameter("state" + index, s);
				allTotal.setParameter("state" + index, s);
				index += 1;
			}
		}
		if (StringUtils.isNotBlank(search)) {
			q.setParameter("search1", "%" + search + "%");
			q.setParameter("search2", "%" + search + "%");
			topTotal.setParameter("search1", "%" + search + "%");
			topTotal.setParameter("search2", "%" + search + "%");
			allTotal.setParameter("search1", "%" + search + "%");
			allTotal.setParameter("search2", "%" + search + "%");
		}
		if (conditionMap != null && conditionMap.size() > 0) {
			PropertyFilter.setMatchValue2Query(q, pfList);
			PropertyFilter.setMatchValue2Query(topTotal, pfList);
			PropertyFilter.setMatchValue2Query(allTotal, pfList);
		}

		q.setFirstResult(page.getPageIndex()).setMaxResults(page.getMaxResult());
		List<CaseLot> topResultList = q.getResultList();// 置顶合买集合
		List<BigDecimal> topCountList = topTotal.getResultList();
		int topCount = topCountList.get(0).intValue();// 置顶合买总数
		List<BigDecimal> allCountList = allTotal.getResultList();
		int allCount = allCountList.get(0).intValue();// 合买总数

		if (sortStateFlag == 1) {// 合买中心查询,只查置顶
			page.setList(topResultList);
			page.setTotalResult(topCount);
			return page;
		} else {// 合买大厅查询，置顶+普通
			int normalMaxResult = page.getMaxResult() - topResultList.size();
			if (normalMaxResult <= 0) {
				page.setList(topResultList);
				page.setTotalResult(allCount);
				return page;
			}
			int normalPageIndex = 0;
			if (page.getPageIndex() <= topCount) {
				normalPageIndex = 0;
			} else if (page.getPageIndex() > topCount) {
				if (page.getCurrentPageNo() == 1) {
					normalPageIndex = 0;
				} else {
					normalPageIndex = page.getPageIndex() - topCount;
				}
			}
			if (normalPageIndex < 0) {
				normalPageIndex = 0;
			}

			// 普通排序开始
			StringBuilder normalOrderSql = new StringBuilder(" ORDER BY ");
			if (page.isOrderBySetted()) {
				for (Sort sort : sortList) {
					normalOrderSql.append(" " + sort.getProperty() + " " + sort.getDir() + ",");
				}
				normalOrderSql.append(" o.startTime DESC ");
			} else {
				normalOrderSql
						.append(" (o.buyAmtByStarter+o.buyAmtByFollower+o.safeAmt)/o.totalAmt DESC,o.startTime DESC ");
			}
			// 普通排序结束
			// sortState值小于等于4的，为普通合买
			String normalWhereSql = whereSql.toString() + " AND o.sortState <= 4";
			String normalSql = sql + normalWhereSql + normalOrderSql.toString();
			String normalCountSql = countSql + normalWhereSql;
			Query normalq = em.createNativeQuery(normalSql, CaseLot.class);
			Query normalTotal = em.createNativeQuery(normalCountSql);
			if (null != state && state.length > 0) {
				int index = 1;
				for (BigDecimal s : state) {
					normalq.setParameter("state" + index, s);
					normalTotal.setParameter("state" + index, s);
					index += 1;
				}
			}
			if (StringUtils.isNotBlank(search)) {
				normalq.setParameter("search1", "%" + search + "%");
				normalq.setParameter("search2", "%" + search + "%");
				normalTotal.setParameter("search1", "%" + search + "%");
				normalTotal.setParameter("search2", "%" + search + "%");
			}
			if (conditionMap != null && conditionMap.size() > 0) {
				PropertyFilter.setMatchValue2Query(normalq, pfList);
				PropertyFilter.setMatchValue2Query(normalTotal, pfList);
			}
			normalq.setFirstResult(normalPageIndex).setMaxResults(normalMaxResult);
			List<CaseLot> normalResultList = normalq.getResultList();
			List<BigDecimal> normalCountList = normalTotal.getResultList();
			int normalCount = normalCountList.get(0).intValue();
			List<CaseLot> resultList = new ArrayList<CaseLot>();
			resultList.addAll(topResultList);
			resultList.addAll(normalResultList);
			page.setList(resultList);
			page.setTotalResult(topCount + normalCount);
			return page;
		}
	}

	@Transactional
	public static void updateCaseLotBySortState(String caselotid, Integer sortState) {
		if (StringUtils.isBlank(caselotid)) {
			throw new IllegalArgumentException("The caselotid argument is required");
		}
		if (sortState == null) {
			throw new IllegalArgumentException("The sortState argument is required");
		}
		CaseLot caseLot = CaseLot.findCaseLot(caselotid);
		caseLot.setSortState(sortState);
		caseLot.merge();
	}
}
