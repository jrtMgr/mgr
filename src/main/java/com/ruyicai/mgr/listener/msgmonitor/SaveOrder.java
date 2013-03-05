package com.ruyicai.mgr.listener.msgmonitor;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

import com.ruyicai.mgr.domain.TmsgMonitor;
import com.ruyicai.mgr.domain.Torder;
import com.ruyicai.mgr.listener.TlotListener;
import com.ruyicai.mgr.util.DateUtil;

@Component
public class SaveOrder extends AbsMsgMonitor {
	public static Map<String, Long> map = null;
	@Override
	public void process() {
		if (map == null) {
			map = new HashMap<String, Long>();
			List<TmsgMonitor> list = TmsgMonitor.findAllTmsgMonitorsByType(3);
			for (TmsgMonitor msgMonitor : list) {
				map.put(msgMonitor.getId().getLotno(), msgMonitor.getTime().longValue());
			}
		}
		String lotno;
		Iterator<Entry<String, Long>> i = TlotListener.touzhu.entrySet().iterator();
		while (i.hasNext()) {
			Entry<String, Long> e = i.next();
			String orderid = e.getKey();
			Torder torder = Torder.findTorder(orderid);
			if(torder.getInstate().intValue() == 1){
				i.remove();
				continue;
			}
			lotno = torder.getLotno();
			if (map.get(lotno) == null) {
				continue;
			}
			if(System.currentTimeMillis() - e.getValue()> map.get(lotno)){
				String msgBody  = "订单号:" + orderid +",采种："+lotno+",期号："+torder.getBatchcode()+",投注时间为：" + DateUtil.format(new Date(e.getValue()))
				 + "，已经"+((System.currentTimeMillis() - e.getValue())/1000L)+"秒没有出票。";
				sendmsg(msgBody);
				i.remove();
			}
			
		}
	}

}
