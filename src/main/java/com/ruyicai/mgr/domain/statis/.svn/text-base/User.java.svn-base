package com.ruyicai.mgr.domain.statis;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.util.Page;

/**
 * 用户信息表
 */
@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TUSER", schema = "JRTSTATIS", identifierField = "id")
public class User{	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "REALNAME")
	private String realname;
	
	@Column(name = "TEL")
	private String tel;
	
	@Column(name = "REGDATE")
	private Date regdate;	
	
	@Column(name = "STATUS")
	private Integer status;	
	
	@Column(name = "BZ")
	private String bz;	
	
	@Column(name = "PASS")
	private String pass;
	
	@Column(name = "ROLEID")
	private Integer roleid;
	
	public static List<User> findUserByname(String name){
		return User.entityManager().createQuery("select o from User o where o.name = ?", User.class)
		.setParameter(1, name).getResultList();
	}
	
	public static void findList(String where, String orderby,
			List<Object> params, Page<User> page) {
		TypedQuery<User> q = User.entityManager().createQuery(
				"select o from User o " + where + orderby, User.class);
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
		TypedQuery<Long> totalQ = User.entityManager().createQuery(
				"select count(o) from User o " + where, Long.class);
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
