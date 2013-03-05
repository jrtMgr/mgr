package com.ruyicai.mgr.service;

import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruyicai.mgr.domain.Tuserloginfo;


@Service
public class TuserloginfoDao {

	@PersistenceContext(unitName = "persistenceUnit")
	EntityManager entityManager;
	
	public List<Tuserloginfo> findByUserno(String userno) {
		List<Tuserloginfo> result = entityManager.createQuery("select o from Tuserloginfo o where o.userno=?", Tuserloginfo.class)
				.setParameter(1, userno).getResultList();
		return result;
	}
	
	@Transactional("transactionManager")
	public int deleteByuserno(String userno) {
		String sql = "delete from Tuserloginfo where userno=?";
		int num = entityManager.createNativeQuery(sql)
				.setParameter(1, userno).executeUpdate();
		return num;
	}
	
	@Transactional("transactionManager")
	public int deleteByday(Integer day) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, -day);
		String sql = "delete from Tuserloginfo where createtime<?";
		int num = entityManager.createNativeQuery(sql)
				.setParameter(1, c.getTime()).executeUpdate();
		return num;
	}
}
