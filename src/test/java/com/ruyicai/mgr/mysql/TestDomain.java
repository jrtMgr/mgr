package com.ruyicai.mgr.mysql;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ruyicai.mgr.mysql.domain.MatchesCompositePK;
import com.ruyicai.mgr.mysql.domain.TjingcaiMatches;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/applicationContext.xml",
		"classpath:/META-INF/spring/applicationContext-*.xml"})
public class TestDomain {

	@Test
	public void testDao(){
		System.out.println(TjingcaiMatches.findAllTjingcaiMatcheses());
		MatchesCompositePK m  = new MatchesCompositePK();
		System.out.println(TjingcaiMatches.findTjingcaiMatches(new MatchesCompositePK(BigDecimal.ONE  ,"20111111",new BigDecimal(5),"174")));
	}
}
