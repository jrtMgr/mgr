package com.ruyicai.mgr.domain;

import javax.persistence.Column;

import org.springframework.roo.addon.entity.RooIdentifier;
import org.springframework.roo.addon.tostring.RooToString;

@RooIdentifier
@RooToString
public final class CompositePK {
	    
	    @Column(name = "LOTNO", nullable = false)
	    private String lotno;
	    
	    @Column(name = "BATCHCODE", nullable = false)
	    private String batchcode;
	    
		@Column(name = "AGENCYNO", columnDefinition = "VARCHAR2", length = 24)
		private String agencyno;
	    
}
