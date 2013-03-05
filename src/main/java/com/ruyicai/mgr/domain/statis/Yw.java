package com.ruyicai.mgr.domain.statis;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

/**
 * 业务表
 * date 2011-11-07
 */
@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "YW", schema = "JRTSTATIS", identifierField = "id")
public class Yw implements java.io.Serializable{	

	@Id
	private int id;
	
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "BDUSERID")
	private int bduserid;
	
	@Column(name = "CJDATE")
	private Date cjdate;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "BZ")
	private String bz;
	
	public static int findId() {
		return entityManager().createQuery("SELECT max(o.id) FROM Yw o ", Integer.class).getSingleResult();
	}
	
	 public static List<Yw> findAllYwsBystatus() {
        return entityManager().createQuery("SELECT o FROM Yw o where o.status = 1 ", Yw.class).getResultList();
    }
}
