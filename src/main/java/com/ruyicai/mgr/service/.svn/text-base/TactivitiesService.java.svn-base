package com.ruyicai.mgr.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruyicai.mgr.domain.Tactivities;
import com.ruyicai.mgr.exception.RuyicaiException;
import com.ruyicai.mgr.util.ErrorCode;

@Service
@Transactional
public class TactivitiesService {
	private static Logger logger = LoggerFactory.getLogger(TactivitiesService.class);
	

	@Transactional
	public void addbatch(String lotno, String subchannel, String channels, Integer activitytype,
			String amt, Integer state) {
		String[] arrChannel = channels.split(",");
		if (arrChannel.length == 0) {
			throw new RuyicaiException(ErrorCode.Tactivities_ChannelNull);
		}
		
		for (String channel : arrChannel) {
			Tactivities.createIfNotExist(lotno, subchannel, channel, activitytype, amt, state);
		}
	}
	
	@Transactional
	public void setbatch(String lotno, String subchannel, String channels, Integer activitytype,
			String amt, Integer state) {
		logger.info("lotno=" + lotno + ";subchannel=" + subchannel + ";channels=" + channels + ";activitytype=" + activitytype.toString()
				+ ";amt=" + amt + ";state=" + state);
		
		String[] arrChannel = channels.split(",");
		if (arrChannel.length == 1 && "".equals(arrChannel[0])) {
			throw new RuyicaiException(ErrorCode.Tactivities_ChannelNull);
		}	
		
		if ("all".equals(channels)) {
			if (lotno != null && !"".equals(lotno.trim())) {
				Tactivities.updateTactivities(lotno, subchannel, activitytype, amt, state);
			} else {
				Tactivities.updateTactivities(subchannel, activitytype, amt, state);
			}
			
		} else { 
			/*StringBuffer sb = new StringBuffer("");
			for (String channel : arrChannel) {
				sb.append(",").append("'").append(channel).append("'");
			}
			sb.delete(0, 1);
			logger.info("channels(sb)=" + sb.toString());*/
			
			if (lotno != null && !"".equals(lotno.trim())) {
				//Tactivities.updateTactivitiesBy(lotno, subchannel, sb.toString(), activitytype, amt, state);
				
				for(String channel : arrChannel) {
					Tactivities.updateTactivitiesBy(lotno, subchannel, channel, activitytype, amt, state);
				}
				
			} else {
				//Tactivities.updateTactivitiesBy(subchannel, sb.toString(), activitytype, amt, state);
				
				for(String channel : arrChannel) {
					Tactivities.updateTactivitiesBy(subchannel, channel, activitytype, amt, state);
				}
			}
			
		}
	}
}
