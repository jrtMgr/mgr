package com.ruyicai.mgr.domain.statis;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.domain.Tbiguserinfo;
import com.ruyicai.mgr.domain.Twininfo;


/**
 * 业务推广PV统计表
 */

@RooJavaBean
@RooJson
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "PVTJ", schema = "JRTSTATIS", identifierField = "id", identifierColumn = "ID")
public class PvTJ {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "TJDATE")
	private String tjdate;
	
	@Column(name = "YWID")
	private String ywid;
	
	@Column(name = "CHANNELID")
	private String channelid;
	
	@Column(name = "PROVINCE")
	private String province;
	
	@Column(name = "VISITNUM")
	private int visitnum;

	@Column(name = "REGNUM")
	private int regnum;
	
	@Column(name = "PAYNUM")
	private int paynum;
	
	@Column(name = "PERCENT")
	private float percent;
	
	@Column(name = "UVNUM")
	private int uvnum;
	
	@Column(name = "REGPAYNUM")
	private int regpaynum;
	
	/**
	 * 获取注册用户数
	 * @param where
	 * @param param
	 * @return
	 */
	public static Long getRegnum(String where, List<Object> params) {
		TypedQuery<Long> query = PvTJ.entityManager().createQuery("select sum(o.regnum) from PvTJ o "+ where, Long.class);
    	if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				query.setParameter(index, param);
				index = index + 1;
			}
		}
    	
		return query.getSingleResult()==null?0:query.getSingleResult();
    }
	/**
	 * 注册当天充值的用户数
	 * @param where
	 * @param param
	 * @return
	 */
	//有问题
	public static Long getRegPaynum(String where, List<Object> params) {
    	TypedQuery<Long> query=PvTJ.entityManager().createQuery("select sum(o.regpaynum) from PvTJ o "+ where, Long.class);
    	if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				query.setParameter(index, param);
				index = index + 1;
			}
		}
		return query.getSingleResult()==null?0:query.getSingleResult();
    }
	
	/**
	 * 获取充值总金额
	 * @param where
	 * @param param
	 * @return
	 */
	public static BigDecimal getPaymoney(String where, List<Object> params) {
		Query query=PvTJ.entityManager().createNativeQuery("select sum(o.paymoney) as paymoney from jrtstatis.PAYTJ o "+ where);
    	if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				query.setParameter(index, param);
				index = index + 1;
			}
		}
		return  query.getSingleResult()==null?BigDecimal.ZERO:(BigDecimal)query.getSingleResult();
    }
	
	/**
	 * 获取购彩总金额
	 * @param where
	 * @param param
	 * @return
	 */
	public static BigDecimal getBuymoney(String where, List<Object> params) {
		Query query=PvTJ.entityManager().createNativeQuery("select sum(o.buymoney) as buymoney from jrtstatis.buytj o "+ where);
    	if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				query.setParameter(index, param);
				index = index + 1;
			}
		}
		return query.getSingleResult()==null?BigDecimal.ZERO:(BigDecimal)query.getSingleResult();
    }
	
	/**
	 * 用户访问数
	 * @param where
	 * @param param
	 * @return
	 */
	public static Long getVisitnum(String where, List<Object> params) {
    	TypedQuery<Long> query=PvTJ.entityManager().createQuery("select sum(visitnum) as visitnum from PvTJ o "+ where, Long.class);
    	if (null != params && !params.isEmpty()) {
			int index = 1;
			for (Object param : params) {
				query.setParameter(index, param);
				index = index + 1;
			}
		}
		return query.getSingleResult()==null?0:query.getSingleResult();
    }
}
