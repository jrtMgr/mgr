package com.ruyicai.mgr.mysql.domain;

import java.math.BigDecimal;

import javax.persistence.Column;

import org.springframework.roo.addon.entity.RooIdentifier;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.roo.addon.tostring.RooToString;

@RooIdentifier
@RooJson
public final class MatchesCompositePK {
	    
		
		@Column(name = "type", nullable = false)
		private BigDecimal type;
		
		@Column(name = "day", nullable = false)
		private String day;
		
		@Column(name = "weekid", nullable = false)
		private BigDecimal weekid;
		
		@Column(name = "teamid", nullable = false)
		private String teamid;
		
		@Override
		public String toString() {
			return this.toJson();
		}
}