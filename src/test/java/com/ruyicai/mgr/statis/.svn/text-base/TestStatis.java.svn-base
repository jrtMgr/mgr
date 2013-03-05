package com.ruyicai.mgr.statis;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ruyicai.mgr.domain.Iptables;
import com.ruyicai.mgr.domain.Tlotctrl;
import com.ruyicai.mgr.domain.Torder;
import com.ruyicai.mgr.domain.Tsubchannel;
import com.ruyicai.mgr.domain.Tuserinfo;
import com.ruyicai.mgr.domain.statis.Channel;
import com.ruyicai.mgr.domain.statis.PvTJ;
import com.ruyicai.mgr.domain.statis.TCooperat;
import com.ruyicai.mgr.util.PropertiesUtil;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:/META-INF/spring/applicationContext.xml",
		"classpath:/META-INF/spring/applicationContext-*.xml"})
public class TestStatis {

	
	//@Test
	public void test1(){
		//List<Object> params = new ArrayList<Object>();
		//params.add("C10313");
		//params.add("19");
		//TCooperat.findTcooperatByChannlId("010165");
		//long a = PvTJ.getRegnum("where CHANNELID = ? and ywid = ?", params);
		//long b = PvTJ.getRegPaynum("where CHANNELID = ? and ywid = ?", params);
		//long c = PvTJ.getPaymoney("where CHANNELID = ? and ywid = ?", params);
		//long d = PvTJ.getBuymoney("where CHANNELID = ? and ywid = ?", params);
		//Long e = PvTJ.getVisitnum("where CHANNELID = ? and ywid = ?", params);
		
//		TCooperat.findAllTCooperats();
//		List<Channel> l = Channel.findAllChannels();
//		for (Channel channel : l) {
//			System.out.println(channel.getCode());
//		}
		
		List<Tlotctrl> l = Tlotctrl.getTlotctrls();
		for (Tlotctrl tlotctrl : l) {
			System.out.println(tlotctrl.getId().getLotno());
			System.out.println(tlotctrl.getId().getBatchcode());
			System.out.println(tlotctrl.getEndtime());
		}
		
	}
	//@Test
	public void test2(){
		Tsubchannel tsubchannel = new Tsubchannel();
		tsubchannel.setAgencyno("2");
		tsubchannel.setAgencyname("1");
		//tsubchannel.setTelephone("1");
		tsubchannel.setPrivatekey("1");
		//tsubchannel.setAddress("1");
		tsubchannel.setRegtime(new Date());
		tsubchannel.setState(BigDecimal.ONE);
		//tsubchannel.setNotifyurl("1");
		//tsubchannel.setProtocol("1");
		//tsubchannel.setPubvatekey("1");
		//tsubchannel.setSiyao("1");
		
		tsubchannel.setMac(" ");
		tsubchannel.persist();
	}
	
	//@Test
	public void test3(){
		Tuserinfo tuserinfo=new Tuserinfo();
		//tuserinfo.setUserno("00000756");
		tuserinfo.setState(BigDecimal.ONE);
		if(null!="00000756"){
			tuserinfo.setName("00000756");
		}
		
		tuserinfo.setAgencyno("000001");
		//tuserinfo.setMobileid("ryc"+"00000756");
		//tuserinfo.setPassword("ryc"+"00000756");
		tuserinfo.setRegtime(new Date());
		tuserinfo.persist();
		
	}
	@Test
	public void testSaveIp(){
//		Iptables i = new Iptables();
//		i.setAgencyno("123");
//		i.setExpiretime(new Date());
//		i.setIpaddr("127.0.0.1");
//		i.setState(new Short("1"));
//		i.persist();
		Torder.findByBetfail2();
	}
	
	
}