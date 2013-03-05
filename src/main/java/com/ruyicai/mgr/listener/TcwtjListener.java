package com.ruyicai.mgr.listener;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ruyicai.mgr.domain.Taccount;
import com.ruyicai.mgr.domain.Taccountdetail;
import com.ruyicai.mgr.domain.Tcwtj;
import com.ruyicai.mgr.domain.Ttransaction;
import com.ruyicai.mgr.domain.Torder;
import com.ruyicai.mgr.util.DateUtil;

@Component
public class TcwtjListener {

	private static Logger logger = Logger.getLogger(TcwtjListener.class);
	
	public void process() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		logger.info("账务统计开始, time:" + sdf.format(new Date()));
		String date = new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.getPreDate(1));
		logger.info("统计日期, date:" + date);
		Tcwtj tcwtj = new Tcwtj();
		tcwtj.setTjdate(DateUtil.getPreDate(1));
		BigDecimal balance = getValue(Taccount.findAllBalance(), BigDecimal.ZERO);
		tcwtj.setBalance(balance);
		BigDecimal usercharge = getValue(Ttransaction.findAllUserCharge(date), BigDecimal.ZERO);
		tcwtj.setUsercharge(usercharge);
		BigDecimal channelcharge = getValue(Ttransaction.findAllChannelCharge(date), BigDecimal.ZERO);
		tcwtj.setChannelcharge(channelcharge);
		BigDecimal encash = getValue(Taccountdetail.findAllEncash(date), BigDecimal.ZERO);//getValue(Ttransaction.findAllEncash(date), BigDecimal.ZERO);
		tcwtj.setEncash(encash);
		BigDecimal activitypersent = getValue(Ttransaction.findAllActivitypersent(date), BigDecimal.ZERO);
		tcwtj.setActivitypersent(activitypersent);
		BigDecimal chargepersent = getValue(Ttransaction.findAllChargepersent(date), BigDecimal.ZERO);
		tcwtj.setChargepersent(chargepersent);
		BigDecimal cannel = getValue(Ttransaction.findAllCannel(date), BigDecimal.ZERO);
		tcwtj.setCannel(cannel);
		BigDecimal inhj = usercharge.add(channelcharge).add(encash).add(activitypersent).add(chargepersent).add(cannel);
		tcwtj.setInhj(inhj);
		BigDecimal userbet = getValue(Torder.findAllUserbet(date), BigDecimal.ZERO);//getValue(Ttransaction.findAllUserbet(date), BigDecimal.ZERO);
		tcwtj.setUserbet(userbet);
		BigDecimal channelbet = getValue(Ttransaction.findAllChannelbet(date), BigDecimal.ZERO);
		tcwtj.setChannelbet(channelbet);
		BigDecimal draw = getValue(Ttransaction.findAllDraw(date), BigDecimal.ZERO);
		tcwtj.setDraw(draw);
		BigDecimal fee = getValue(Ttransaction.findAllFee(date), BigDecimal.ZERO);
		tcwtj.setFee(fee);
		BigDecimal outhj = userbet.add(channelbet).add(draw).add(fee);
		tcwtj.setOuthj(outhj);
		tcwtj.setOther1(BigDecimal.ZERO);
		tcwtj.persist();
		logger.info("账务统计结束, time:" + sdf.format(new Date()));
	}
	
	public BigDecimal getValue(BigDecimal value, BigDecimal def) {
		if(null == value) {
			return def;
		}
		return value;
	}
}
