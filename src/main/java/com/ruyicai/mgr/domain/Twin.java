package com.ruyicai.mgr.domain;

import org.apache.log4j.Logger;
import org.springframework.roo.addon.entity.RooEntity;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.springframework.roo.addon.json.RooJson;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

import com.ruyicai.mgr.exception.RuyicaiException;
import com.ruyicai.mgr.util.DateUtil;
import com.ruyicai.mgr.util.ErrorCode;
import com.ruyicai.mgr.util.Page;
import com.ruyicai.mgr.util.PaySign;

@RooToString
@RooEntity(persistenceUnit = "persistenceUnit", versionField = "", table = "TWIN", schema = "JRTSCH",identifierField="flowno")
@RooJson
public class Twin {

	@Id
    @Column(name = "FLOWNO")
    private String flowno;

    @Column(name = "USERNO")
    private String userno;

    @Column(name = "AGENCYNO")
    private String agencyno;

    @Column(name = "LOTNO")
    private String lotno;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CODE")
    private String code = " ";

    @Column(name = "CASHTERM")
    private String cashterm = " ";

    @Column(name = "CASHTIME")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date cashtime = DateUtil.get1000Date();

    @Column(name = "SETTLEFLAGE")
    private BigDecimal settleflage = BigDecimal.ZERO;

    @Column(name = "TICKETCODE")
    private String ticketcode = " ";

    @Column(name = "PLAYNAME")
    private String playname = " ";

    @Column(name = "LOGICCODE")
    private String logiccode = " ";

    @Column(name = "CITYCODE")
    private String citycode = " ";

    @Column(name = "VALIDTERMCODE")
    private String validtermcode = " ";

    @Column(name = "SELLRUNCODE")
    private String sellruncode = " ";

    @Column(name = "SELLTIME")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "S-")
    private Date selltime = DateUtil.get1000Date();

    @Column(name = "PRIZEINFO")
    private String prizeinfo = " ";

    @Column(name = "CERTID")
    private String certid = " ";

    @Column(name = "PHONE")
    private String phone = " ";

    @Column(name = "WINAMOUNT")
    private BigDecimal winamount;

    @Column(name = "MAC")
    private String mac = " ";

    @Column(name = "CASEID")
    private String caseid = " ";

	@Column(name = "CLASS")
    private BigDecimal CLASS = BigDecimal.ZERO;

    @Column(name = "TYPE")
    private BigDecimal type = BigDecimal.ZERO;
    
    private transient Date abandontime;

    public Date getAbandontime() {
		return abandontime;
	}
	public void setAbandontime(Date abandontime) {
		this.abandontime = abandontime;
	}
	public BigDecimal getType() {
		return type;
	}
	public void setType(BigDecimal type) {
		this.type = type;
	}
    public BigDecimal getCLASS() {
		return CLASS;
	}
	public void setClass(BigDecimal CLASS) {
		this.CLASS = CLASS;
	}
	
	public String getFlowno() {
        return " ".equals(this.flowno) ? null : this.flowno;
    }
    
    public void setFlowno(String flowno) {
        this.flowno = flowno;
    }
    
    public String getUserno() {
        return " ".equals(this.userno) ? null : this.userno;
    }
    
    public void setUserno(String userno) {
        this.userno = userno;
    }
    
    public String getAgencyno() {
        return " ".equals(this.agencyno) ? null : this.agencyno;
    }
    
    public void setAgencyno(String agencyno) {
        this.agencyno = agencyno;
    }
    
    public String getLotno() {
        return " ".equals(this.lotno) ? null : this.lotno;
    }
    
    public void setLotno(String lotno) {
        this.lotno = lotno;
    }
    
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    public String getCode() {
        return " ".equals(this.code) ? null : this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getCashterm() {
        return " ".equals(this.cashterm) ? null : this.cashterm;
    }
    
    public void setCashterm(String cashterm) {
        this.cashterm = cashterm;
    }
    
    public Date getCashtime() {
        return this.cashtime;
    }
    
    public void setCashtime(Date cashtime) {
        this.cashtime = cashtime;
    }
    
    public BigDecimal getSettleflage() {
        return this.settleflage;
    }
    
    public void setSettleflage(BigDecimal settleflage) {
        this.settleflage = settleflage;
    }
    
    public String getTicketcode() {
        return " ".equals(this.ticketcode) ? null : this.ticketcode;
    }
    
    public void setTicketcode(String ticketcode) {
        this.ticketcode = ticketcode;
    }
    
    public String getPlayname() {
        return " ".equals(this.playname) ? null : this.playname;
    }
    
    public void setPlayname(String playname) {
        this.playname = playname;
    }
    
    public String getLogiccode() {
        return " ".equals(this.logiccode) ? null : this.logiccode;
    }
    
    public void setLogiccode(String logiccode) {
        this.logiccode = logiccode;
    }
    
    public String getCitycode() {
        return " ".equals(this.citycode) ? null : this.citycode;
    }
    
    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }
    
    public String getValidtermcode() {
        return " ".equals(this.validtermcode) ? null : this.validtermcode;
    }
    
    public void setValidtermcode(String validtermcode) {
        this.validtermcode = validtermcode;
    }
    
    public String getSellruncode() {
        return " ".equals(this.sellruncode) ? null : this.sellruncode;
    }
    
    public void setSellruncode(String sellruncode) {
        this.sellruncode = sellruncode;
    }
    
    public Date getSelltime() {
        return this.selltime;
    }
    
    public void setSelltime(Date selltime) {
        this.selltime = selltime;
    }
    
    public String getPrizeinfo() {
        return " ".equals(this.prizeinfo) ? null : this.prizeinfo;
    }
    
    public void setPrizeinfo(String prizeinfo) {
    	if(prizeinfo==null){
    		prizeinfo=" ";
    	}
        this.prizeinfo = prizeinfo;
    }
    
    public String getCertid() {
        return " ".equals(this.certid) ? null : this.certid;
    }
    
    public void setCertid(String certid) {
        this.certid = certid;
    }
    
    public String getPhone() {
        return " ".equals(this.phone) ? null : this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public BigDecimal getWinamount() {
        return this.winamount;
    }
    
    public void setWinamount(BigDecimal winamount) {
        this.winamount = winamount;
    }
    
    public String getMac() {
        return this.mac;
    }
    
    public void setMac(String mac) {
        this.mac = mac;
    }
    
    public String getCaseid() {
        return " ".equals(this.caseid) ? null : this.caseid;
    }
    
    public void setCaseid(String caseid) {
        this.caseid = caseid;
    }
    
    public void setCLASS(BigDecimal CLASS) {
        this.CLASS = CLASS;
    }
    
    @Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
        this.entityManager.flush();
        Twin twintc = Twin.findTwin(this.getFlowno());
        this.setMac(twintc.twinSign());
        twintc.merge();
    }
	
	@Transactional
    public Twin merge() {
		this.setMac(this.twinSign());
        if (this.entityManager == null) this.entityManager = entityManager();
        Twin merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
    
    public String twinSign() {
    	
    	SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	StringBuilder builder = new StringBuilder();
    	builder.append(this.getFlowno())
    	.append(this.getUserno())
    	.append(this.getAgencyno())
    	.append(this.getLotno())
    	.append(this.getAmount().longValue())
    	.append(this.getCode())
    	.append(this.getCashterm())
    	.append(sFormat.format(this.getCashtime()))
    	.append(this.getCLASS())
    	.append(this.getType())
    	.append(this.getSettleflage())
    	.append(this.getTicketcode())
    	.append(this.getPlayname())
    	.append(this.getLogiccode())
    	.append(this.getCitycode())
    	.append(this.getValidtermcode())
    	.append(this.getSellruncode())
    	.append(sFormat.format(this.getSelltime()))
    	.append(this.getPrizeinfo())
    	.append(this.getCertid())
    	.append(this.getPhone())
    	.append(this.getWinamount().longValue());
    	try {
			return PaySign.Md5(builder.toString());
		} catch (Exception e) {
			Logger logger = Logger.getLogger(Tuserinfo.class);
			logger.error("为Twin生成加密时出错", e);
			throw new RuyicaiException(ErrorCode.Data_MD5);
		}
    }
	
	public static List<Twin> findUnencashTwintc() {
		TypedQuery<Twin> query=Twin.entityManager().createQuery("select o from Twintc o where settleflage=0", Twin.class);
		return query.getResultList();
	}
	//中奖记录查询
	public static void findAllWin(String userno,String beginTime,String endTime,String lotno ,Page<Twin> page){
		String where = "";
		if(!lotno.equals("")){
			where = " and t.lotno=?";
		}
		TypedQuery<Twin> query=Twin.entityManager().createQuery
		("from Twin t where to_char(t.selltime,'yyyymmdd')>=? and to_char(t.selltime,'yyyymmdd')<=? and t.userno=? "+where+" order by cashtime desc",Twin.class)
		.setParameter(1, beginTime)
		.setParameter(2, endTime)
		.setParameter(3, userno)
		.setFirstResult(page.getPageIndex())
		.setMaxResults(page.getMaxResult());
		if(!lotno.equals("")){
			query.setParameter(4, lotno);
		}
		List<Twin> list = query.getResultList();//增加变量接收，修改session关闭问题
		
		page.setList(list);
		if (list==null||list.size()==0) {
			throw new RuyicaiException(ErrorCode.Select_NotExist);
		}
	
		TypedQuery<Long> total=Twin.entityManager().createQuery
		("select count(t) from Twin t where to_char(t.selltime,'yyyymmdd')>=? and to_char(t.selltime,'yyyymmdd')<=? and t.userno=? "+where,Long.class)
		.setParameter(1, beginTime)
		.setParameter(2, endTime)
		.setParameter(3, userno);
		if(!lotno.equals("")){
			total.setParameter(4, lotno);
		}
		Integer totalRes = total.getSingleResult().intValue();
	
		page.setTotalResult(totalRes);
		page.setTotalPage(totalRes);
		Twin.entityManager().clear();
		
		
	}
}
