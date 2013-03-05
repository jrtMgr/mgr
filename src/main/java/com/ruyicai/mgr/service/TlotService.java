package com.ruyicai.mgr.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruyicai.mgr.domain.Tlot;

@Service
@Transactional
public class TlotService {

	@Transactional("transactionManager")
	public int updateTime() {
		return Tlot.updateTime();
	}
}
