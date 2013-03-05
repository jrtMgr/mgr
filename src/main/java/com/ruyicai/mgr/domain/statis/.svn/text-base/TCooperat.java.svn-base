package com.ruyicai.mgr.domain.statis;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TypedQuery;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TCOOPERAT", schema = "JRTSTATIS", identifierField = "id")
public class TCooperat implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;// 主键
	
	@Column(name = "CHANNL_ID")
	private String channlId;// 渠道编号（id）
	
	@Column(name = "COOPERAT_TYPE")
	private String cooperatType;// 合作方式
	
	@Column(name = "COUNT_TYPE")
	private String countType;// 计算方式(元/个,百分比，元/月)等
	
	@Column(name = "COUNT")
	private Double count;// 合作金额(元)
	
	public static List<TCooperat> findTcooperatByChannlId(String channelId){
		TypedQuery<TCooperat> query = TCooperat.entityManager().createQuery("select o from TCooperat o where o.channlId = ?", TCooperat.class);		
		query.setParameter(1, channelId);
		return query.getResultList();
	}
	
}
