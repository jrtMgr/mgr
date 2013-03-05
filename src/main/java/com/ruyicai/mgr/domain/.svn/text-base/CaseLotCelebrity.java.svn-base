package com.ruyicai.mgr.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

import com.ruyicai.mgr.exception.RuyicaiException;
import com.ruyicai.mgr.util.ErrorCode;

/**
 * 合买名人
 */
@RooJavaBean
@RooToString
@RooJson
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "CASELOTCELEBRITY", identifierField = "id")
public class CaseLotCelebrity {

	/** 用户编号 */
	@Column(name = "userno")
	private String userno;

	/** 彩种(合买名人按彩种分类，合买中心为特殊情况，彩种为:center) */
	@Column(name = "lotno")
	private String lotno;

	/** 排序号 */
	@Column(name = "sortno")
	private int sortno;

	/** 修改时间 */
	@Column(name = "lastModifyTime")
	private Date lastModifyTime;

	@Column(name = "enable")
	private Boolean enable;

	/**
	 * 创建合买名人信息
	 * 
	 * @param userno
	 * @param lotno
	 * @return
	 */
	@Transactional
	public static CaseLotCelebrity createIfNotExist(String userno, String lotno) {
		if (StringUtils.isBlank(userno))
			throw new IllegalArgumentException("The userno argument is required");
		if (StringUtils.isBlank(lotno))
			throw new IllegalArgumentException("The lotno argument is required");
		List<CaseLotCelebrity> list = CaseLotCelebrity.findCaseLotCelebritysByUsernoAndLotno(userno, lotno)
				.getResultList();
		if (list != null && list.size() > 0) {
			throw new RuyicaiException(ErrorCode.CaseLot_CaseLotCelebrityExist);
		}
		CaseLotCelebrity celebrity = new CaseLotCelebrity();
		celebrity.setUserno(userno);
		celebrity.setLotno(lotno);
		celebrity.setLastModifyTime(new Date());
		celebrity.setSortno(findMaxSortnoByLotno(lotno) + 1);
		celebrity.setEnable(true);
		celebrity.persist();
		celebrity.flush();
		return celebrity;
	}

	@Transactional
	public static void updateLotnoOfCaseLotCelebrity(String newlotno, String userno, String oldlotno) {
		if (newlotno.equals(oldlotno)) {
			return;
		}
		List<CaseLotCelebrity> list = CaseLotCelebrity.findCaseLotCelebritysByUsernoAndLotno(userno, newlotno)
				.getResultList();
		if (list != null && list.size() > 0) {
			if (!newlotno.equals(oldlotno)) {
				throw new RuyicaiException(ErrorCode.CaseLot_CaseLotCelebrityExist);
			}
		}
		CaseLotCelebrity celebrity = CaseLotCelebrity.findCaseLotCelebritysByUsernoAndLotno(userno, oldlotno)
				.getSingleResult();
		celebrity.setLotno(newlotno);
		celebrity.merge();
	}

	public static void removeCaseLotCelebrity(String userno, String lotno) {
		if (StringUtils.isBlank(userno))
			throw new IllegalArgumentException("The userno argument is required");
		if (StringUtils.isBlank(lotno))
			throw new IllegalArgumentException("The lotno argument is required");
		List<CaseLotCelebrity> list = CaseLotCelebrity.findCaseLotCelebritysByUsernoAndLotno(userno, lotno)
				.getResultList();
		for (CaseLotCelebrity celebrity : list) {
			celebrity.setEnable(false);
			celebrity.merge();
		}
	}

	public static Integer findMaxSortnoByLotno(String lotno) {
		Integer sortno = entityManager()
				.createQuery("SELECT MAX(o.sortno) FROM CaseLotCelebrity o WHERE o.lotno = :lotno", Integer.class)
				.setParameter("lotno", lotno).getSingleResult();
		if (sortno == null) {
			sortno = 0;
		}
		return sortno;
	}

	@Transactional
	public static void sortCelebrityByLotno(List<CaseLotCelebrity> list) {
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				CaseLotCelebrity temp = list.get(i);
				CaseLotCelebrity celebrity = CaseLotCelebrity.findCaseLotCelebritysByUsernoAndLotno(temp.getUserno(),
						temp.getLotno()).getSingleResult();
				if (celebrity != null) {
					celebrity.setSortno(i + 1);
					celebrity.merge();
				}
			}
		}
	}

	public static TypedQuery<CaseLotCelebrity> findCaseLotCelebritysByUsernoAndLotno(String userno, String lotno) {
		EntityManager em = entityManager();
		StringBuffer sb = new StringBuffer("SELECT o FROM CaseLotCelebrity AS o WHERE o.enable = true");
		if (StringUtils.isNotBlank(userno)) {
			sb.append(" AND o.userno = :userno");
		}
		if (StringUtils.isNotBlank(lotno)) {
			sb.append(" AND o.lotno = :lotno");
		}
		sb.append(" ORDER BY o.sortno ASC");
		TypedQuery<CaseLotCelebrity> q = em.createQuery(sb.toString(), CaseLotCelebrity.class);
		if (StringUtils.isNotBlank(userno)) {
			q.setParameter("userno", userno);
		}
		if (StringUtils.isNotBlank(lotno)) {
			q.setParameter("lotno", lotno);
		}
		return q;
	}
}
