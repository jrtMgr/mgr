package com.ruyicai.mgr.domain;

import javax.persistence.Column;

import org.springframework.roo.addon.entity.RooIdentifier;
import org.springframework.roo.addon.tostring.RooToString;

@RooIdentifier
@RooToString
public class TlottypePK {

	@Column(name = "ID")
	private String id;
	
	@Column(name = "AGENCYNO")
	private String agencyNo;
}
