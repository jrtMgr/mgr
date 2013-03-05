package com.ruyicai.mgr.listener.msgmonitor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ruyicai.mgr.domain.Tlotctrl;
import com.ruyicai.mgr.domain.TmsgMonitor;

@Component
public class EndIssue extends AbsMsgMonitor {
	/**
	 * 标识结期 短信的map
	 */
	private static Map<String, Integer> jqMap = new HashMap<String, Integer>();
	public static Map<String, Long> map = null;
	@Override
	public void process() {
		if (map == null) {
			map = new HashMap<String, Long>();
			List<TmsgMonitor> list = TmsgMonitor.findAllTmsgMonitorsByType(2);
			if (list.size() == 0) {
				return;
			}
			map.put(list.get(0).getId().getLotno(), list.get(0).getTime().longValue());
		}
		
		if(map.get("000000") == null){
			return;
		}
		List<Tlotctrl> l = Tlotctrl.getTlotctrls();
		for (Tlotctrl tlotctrl : l) {
			if(System.currentTimeMillis() - tlotctrl.getEndtime().getTime() > map.get("000000")){
				if (jqMap.get(tlotctrl.getId().getLotno()+"_"+tlotctrl.getId().getBatchcode()) == null) {
					String msgBody  = "采种编号：" + tlotctrl.getId().getLotno() +"，期号:"+tlotctrl.getId().getBatchcode()+"。结期时间为：" +
					tlotctrl.getEndtime() + "，已经超过"+((System.currentTimeMillis() - tlotctrl.getEndtime().getTime())/60000L)+"分钟没有结期。";
					sendmsg(msgBody);
					jqMap.put(tlotctrl.getId().getLotno()+"_"+tlotctrl.getId().getBatchcode(), 0);
				}
			}
		}
	}

}
