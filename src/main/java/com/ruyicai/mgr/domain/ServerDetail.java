package com.ruyicai.mgr.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;


@RooJavaBean
@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "SERVERDETAIL", schema = "JRTSCH", identifierField = "id")
public class ServerDetail {

		@Id
		@GeneratedValue(strategy=GenerationType.AUTO)
		@Column(name = "ID")
		private int id;
		
		@Column(name = "URL")
		private String url;
		
		@Column(name = "STATUS")
		private Integer status;
		
		@Column(name = "BZ")
		private String bz;
		
		@Column(name = "JMX")
		private String jmx;
		
		public static List<ServerDetail> findAllServerDetailsBystatus() {
	        return entityManager().createQuery("SELECT o FROM ServerDetail o where o.status = 1 ", ServerDetail.class).getResultList();
	    }
}
