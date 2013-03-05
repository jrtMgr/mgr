package com.ruyicai.mgr.listener.msgmonitor;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.ruyicai.mgr.domain.TmsgMonitor;
import com.ruyicai.mgr.listener.TlotListener;

@Component
public class IssueEnd extends AbsMsgMonitor {
	public static Map<String, Long> map = null;
	@Override
	public void process() {
		if (map == null) {
			map = new HashMap<String, Long>();
			List<TmsgMonitor> list = TmsgMonitor.findAllTmsgMonitorsByType(1);
			for (TmsgMonitor msgMonitor : list) {
				map.put(msgMonitor.getId().getLotno(), msgMonitor.getTime().longValue());
			}
		}
		String lotno;
		Iterator<Entry<String, Long>> i = TlotListener.jieqi.entrySet().iterator();
		while (i.hasNext()) {
			Entry<String, Long> e = i.next();
			lotno = e.getKey().split("_")[0];
			if(map.get(lotno) == null){
				continue;
			}
			if (System.currentTimeMillis() - e.getValue() > map.get(lotno)) {
				String msgBody  = "采种编号：" + lotno +"，期号:"+e.getKey().split("_")[1]+"开期后有" + 
				(System.currentTimeMillis()- e.getValue())/60000L + "分钟没开奖了";
				sendmsg(msgBody);
				i.remove();
			}
		}
	}

}
