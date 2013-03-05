package com.ruyicai.mgr.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJson
@Entity
@Table(name = "TUSERLOGINFO")
public class Tuserloginfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private BigDecimal id;
	
	@Column(name = "USERNO")
	private String userno;
	
	@Column(name = "RANDOM")
	private String random;
	
	@Column(name = "ACCESSTYPE")
	private String accesstype;
	
	@Column(name = "CREATETIME")
	private Date createtime;
}
