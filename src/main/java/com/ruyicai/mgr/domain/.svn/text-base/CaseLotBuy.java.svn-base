package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.consts.TransactionType;
import com.ruyicai.mgr.controller.dto.CaseLotAndCaseLotBuyDTO;
import com.ruyicai.mgr.util.Page;

@RooJavaBean
@RooJson
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", finders = { "findCaseLotBuysByCaselotId", "findCaseLotBuysByUserno", "findCaseLotBuysByCaselotIdAndBuyType" })
public class CaseLotBuy {

	/** 合买ID */
	private String caselotId;

	/** 用户编号 */
	private String userno;

	/** 购买金额 */
	private BigDecimal num;

	/** 购买时间 */
	private Date buyTime;

	/** 1:正常，0:撤资 */
	private Integer flag;

	/** 购买时的保底金额 */
	private BigDecimal safeAmt;

	/** 冻结的保底金额，也就是实际可保底的金额 */
	private BigDecimal freezeSafeAmt;

	/** 购买类型。1:发起者，0:参与者 */
	private Integer buyType;

	/** 中奖金额 */
	private BigDecimal prizeAmt;

	/** 佣金金额 */
	private BigDecimal commisionPrizeAmt;

	@SuppressWarnings("unchecked")
	public static BigDecimal findUserBetSuccessHM(String userno) {
		Query query = entityManager()
				.createNativeQuery(
						"select sum(num) from caselotbuy  t where t.id in ("
								+ "select t1.id from caselotbuy t1, caselot t2 where t1.userno=:userno and t2.state in (3,5) and t1.caselotid = t2.id)");
		query.setParameter("userno", userno);
		List<BigDecimal> list = null;
		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		BigDecimal ret = 0 == list.size() ? BigDecimal.ZERO : list.get(0);
		return ret == null ? BigDecimal.ZERO : ret;
	}

	@SuppressWarnings("unchecked")
	public static void findList(String where, String orderby, List<Object> params, Page<CaseLotAndCaseLotBuyDTO> page) {
		EntityManager em = entityManager();
		Query q = em.createQuery("SELECT t,o FROM CaseLot t,CaseLotBuy o " + where + orderby);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				q.setParameter(index, param);
				index = index + 1;
			}
		}
		q.setFirstResult(page.getPageIndex() * page.getMaxResult()).setMaxResults(page.getMaxResult());
		List<Object[]> list = q.getResultList();
		List<CaseLotAndCaseLotBuyDTO> resultlist = new ArrayList<CaseLotAndCaseLotBuyDTO>();
		for (Object[] objs : list) {
			CaseLot caselot = (CaseLot) objs[0];
			CaseLotBuy caselotbuy = (CaseLotBuy) objs[1];
			CaseLotAndCaseLotBuyDTO dto = new CaseLotAndCaseLotBuyDTO(caselot, caselotbuy);
			resultlist.add(dto);
		}
		page.setList(resultlist);

		TypedQuery<Long> totalQ = em.createQuery("SELECT count(o) FROM CaseLot t,CaseLotBuy o " + where, Long.class);
		if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				totalQ.setParameter(index, param);
				index = index + 1;
			}
		}
		page.setTotalResult(totalQ.getSingleResult().intValue());
		Query query = em.createQuery(
				"SELECT nvl(sum(o.num),0),nvl(sum(o.safeAmt),0),nvl(sum(o.freezeSafeAmt),0), nvl(sum(o.prizeAmt),0), nvl(sum(o.commisionPrizeAmt),0) FROM CaseLot t,CaseLotBuy o " + where);
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
	
	public static BigDecimal findUserHMPrize(String userno) {
		BigDecimal ret = entityManager().createQuery("select sum(prizeAmt) from CaseLotBuy where userno =? ", BigDecimal.class).setParameter(1, userno).getSingleResult();
		BigDecimal ret1 = entityManager().createQuery("select sum(commisionPrizeAmt) from CaseLotBuy where userno =? ", BigDecimal.class).setParameter(1, userno).getSingleResult();
		ret = ret == null? BigDecimal.ZERO : ret;
		ret1 = ret1 == null? BigDecimal.ZERO : ret1;
		return ret.add(ret1);
	}
	
}
