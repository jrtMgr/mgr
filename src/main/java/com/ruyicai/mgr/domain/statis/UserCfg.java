package com.ruyicai.mgr.domain.statis;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

import com.ruyicai.mgr.controller.dto.UsercfgView;

/**
 * 用户配置表
 */
@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TUSERCFG", schema = "JRTSTATIS", identifierField = "id")
public class UserCfg{	

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "USERID")
	private Integer userid;
	
	@Column(name = "CHANNELID")
	private Integer channelid;
	
	public static List<UserCfg> findUserCfgByUserid(int userid){
		return UserCfg.entityManager().createQuery("select o from UserCfg o where o.userid = ?", UserCfg.class)
		.setParameter(1, userid).getResultList();
	}
	
	public static List<UsercfgView> findUserCfgViewByUserid(int userid){
		List<UsercfgView> list = new ArrayList<UsercfgView>();
		List<Object[]> l = entityManager().createQuery("select  uc.id,uc.userid,uc.channelid,u.name ,c.name from UserCfg uc,User u, Channel c " +
				"where uc.userid = u.id and c.id = uc.channelid and uc.userid = ?").setParameter(1, userid).getResultList();
		for (Object[] objects : l) {
			UsercfgView uc = new UsercfgView();
			uc.setId((Integer)objects[0]);
			uc.setUserid((Integer)objects[1]);
			uc.setChannelid((Integer)objects[2]);
			uc.setUsername((String)objects[3]);
			uc.setChannelname((String)objects[4]);
			list.add(uc);
		}
		return list;
	}
}
