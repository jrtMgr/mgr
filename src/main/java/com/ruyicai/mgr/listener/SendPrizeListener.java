package com.ruyicai.mgr.listener;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ruyicai.mgr.listener.msgmonitor.MsgMonitor;

@Component
public class SendPrizeListener{
	//private Logger logger = Logger.getLogger(SendPrizeListener.class);
	@Autowired
	List<MsgMonitor> msgMonitors;
	public void sendPrize(){
		for (MsgMonitor msgMonitor : msgMonitors) {
			msgMonitor.process();
		}
	}
}
