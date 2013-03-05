package com.ruyicai.mgr.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.javabean.RooJavaBean;

@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TIMEINTERVAL", schema = "JRTSCH")
@RooJavaBean
public class TimeInterval {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "STARTHOUR")
	private int startHour;

	@Column(name = "ENDHOUR")
	private int endHour;

	@Column(name = "FREQUENCY")
	private long frequency;

	@Column(name = "MOBILENO")
	private String mobileNo;
	
	@Column(name = "DELETEFLAG")
	private int delete;
	
	public static List<TimeInterval> findAllTimeIntervals() {
		return TimeInterval.entityManager().createQuery("SELECT o FROM TimeInterval o where o.delete=0", TimeInterval.class).getResultList();
    }
}
