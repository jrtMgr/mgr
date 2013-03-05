package com.ruyicai.mgr.domain.statis;

import javax.persistence.Column;
import javax.persistence.Id;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

/**
 * 角色表
 */
@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "ROLE", schema = "JRTSTATIS", identifierField = "id")
public class Role{	

	@Id
	@Column(name = "ID")
	private int id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "BZ")
	private String bz;
	
}
