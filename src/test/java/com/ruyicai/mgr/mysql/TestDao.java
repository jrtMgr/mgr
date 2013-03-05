package com.ruyicai.mgr.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.mysql.pojo.Category;
import com.ruyicai.mgr.util.Page;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		//"classpath:/META-INF/spring/applicationContext.xml",
		"classpath:/META-INF/spring/applicationContext-mysql.xml"})
public class TestDao {

	@Autowired
	NewsDao newsDao;
	@Autowired
	IssusDao issusDao;
//	@Test
//	public void testNews(){
////		List<Category> l = newsDao.getCategory();
////		for (Category category : l) {
////			System.out.println(category.getCategoryName());
////		}
//		Page<Map<String, Object>> page = new Page<Map<String,Object>>();
//		page.setPageIndex(0);
//		page.setMaxResult(15);
//		List<Object> params = new ArrayList<Object>();
//		params.add("15");
//		params.add("0");
//		newsDao.findList(" and categoryId=?  and checked=? ", params, page);
//		System.out.println(page);
//	}
	
	@Autowired
	ClientDao clientDao;
	//@Test
	public void save(){
//		Page<Map<String,Object>> page = new Page<Map<String,Object>>();
//		System.out.println(page);
//		issusDao.findList(page);
//		System.out.println(page);
		//newsDao.addNews("123", "123", "123", "123");
		//System.out.println(clientDao.getJdbcTemplate());
		System.out.println(clientDao);
	}
	
	
	@Autowired
	PrizecrawlerDao prizecrawlerDao;
	@Test
	public void testPrizecrawlerDao(){
		//prizecrawlerDao.updatethreshold(1, 1);
		//prizecrawlerDao.updateLotcheckswitch("F47104", 0);
		prizecrawlerDao.updatetagency(1,1.5);
	}
}

