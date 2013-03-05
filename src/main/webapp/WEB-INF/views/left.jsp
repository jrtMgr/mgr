<%@page import="com.ruyicai.mgr.domain.Tpermission"%>
<%@page import="java.util.List"%>
<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath() %>/styles/default.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
<script type="text/javascript">
var Tab = {};

Tab.onTabMouseOver = function(ele){
	if(ele.className=="divtab"){
		ele.className="divtabHover";
	}
}

Tab.onTabMouseOut = function(ele){
	if(ele.className=="divtabHover"){
		ele.className="divtab";
	}
}
function $(ele) {
  if (typeof(ele) == 'string'){
    ele = document.getElementById(ele)
    if(!ele){
  		return null;
    }
  }
  return ele;
}
function $T(tagName,ele){
	ele = $(ele);
	ele = ele || document;
	var ts = ele.getElementsByTagName(tagName);//此处返回的不是数组
	var arr = [];
	var len = ts.length;
	for(var i=0;i<len;i++){
		arr.push($(ts[i]));
	}
	return arr;
}
Tab.onTabClick = function(ele){
	var arr = $T("div",ele.parentElement);
	var len = arr.length;
	for(var i=0;i<len;i++){
		var c = arr[i];
		var cn = c.className;
		if(cn=="divtabCurrent"){
			c.className = "divtab";
			c.style.zIndex=""+(len-i)+"";
		}
	}
	ele.className="divtabCurrent";
	ele.style.zIndex="33";
}
var Application = {};
Application.onChildMenuClick = function(ele,flag){//flag仅在回退/前进时置为true
	if(!flag){
		var url = ele.getAttribute("url");
		window.parent.content.location = url;
	}
	Tab.onTabClick(ele);
}

Application.onChildMenuMouseOver = function(ele){
	Tab.onTabMouseOver(ele);
}

Application.onChildMenuMouseOut = function(ele){
	Tab.onTabMouseOut(ele);
}
</script>
</head>
<%! 
	public boolean ispermission(String inid, HttpServletRequest request) {
		List<Tpermission> tpermissions = (List<Tpermission>)request.getSession().getAttribute("permission");
		if(null != tpermissions && !tpermissions.isEmpty()) {
			for(Tpermission tpermission : tpermissions) {
				if(tpermission.getMenuinid().equals(new BigDecimal(inid))) {
					return true;
				}
			}
		}
		return false;
	}
%>
<body style="border-right: 1px solid #B1B1AD">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td style="padding:0px 0px 0px 0px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr valign="top">
					<td align="left" id="_VMenutable" class="verticalTabpageBar">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="right" valign="bottom">
							<div id="_ChildMenu">
							<% 
								if(ispermission("1", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tpermissions/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">权限设置</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("2", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tuserinfoes/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">用户信息</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("3", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tlots/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">彩票信息</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("4", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tlotctrls/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">期信息</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("5", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tcharges/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">充值信息</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("6", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tpresents/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">赠送彩金</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("7", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tsendsms/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">发送短信</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("8", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tmonitors/index" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">实时监控</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("9", request)) {
							%>
								<div url="<%=request.getContextPath() %>/updateStatistic/index" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">刷新统计</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("10", request)) {
							%>
								<div url="<%=request.getContextPath() %>/recharge/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">如意卡充值</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("11", request)) {
							%>
								<div url="<%=request.getContextPath() %>/lotCenter/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">彩票中心</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("12", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tcard/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">如意卡管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("15", request)) {
							%>
								<div url="<%=request.getContextPath() %>/caselot/listMg" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">合买置顶管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("16", request)) {
							%>
								<div url="<%=request.getContextPath() %>/caselot/listCelebrity" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">合买名人管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("17", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tcwtj/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">账务统计</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("18", request)) {
							%>
								<div url="<%=request.getContextPath() %>/lssm/timeList" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">监控时间</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("19", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tawardlevel/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">采种加奖管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("20", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tawardlevelAudit/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">采种加奖审核</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("21", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tactivities/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">活动管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("22", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tcash/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">提现信息</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("23", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tcashCW/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">提现财务处理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("24", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tlottypes/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">彩种管理</span></b></div>
								<div url="<%=request.getContextPath() %>/tlottypes/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">彩种管理2</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("25", request)) {
							%>
								<div url="<%=request.getContextPath() %>/biguser/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">合作商管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("26", request)) {
							%>
								<div url="<%=request.getContextPath() %>/biguserCW/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">充值财务审核</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("27", request)) {
							%>
								<div url="<%=request.getContextPath() %>/channel/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">渠道管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("28", request)) {
							%>
								<div url="<%=request.getContextPath() %>/preissue/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">预开期管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("29", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tdnabind/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">dna管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("30", request)) {
							%>
								<div url="<%=request.getContextPath() %>/news/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">资讯管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("31", request)) {
							%>
								<div url="<%=request.getContextPath() %>/newsappro/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">资讯审核</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("32", request)) {
							%>
								<div url="<%=request.getContextPath() %>/msgmonitor/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">短信监控管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("33", request)) {
							%>
								<div url="<%=request.getContextPath() %>/torders/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">订单信息</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("34", request)) {
							%>
								<div url="<%=request.getContextPath() %>/webstatistic/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">互联网数据统计</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("35", request)) {
							%>
								<div url="<%=request.getContextPath() %>/suser/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">统计用户管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("36", request)) {
							%>
								<div url="<%=request.getContextPath() %>/batchgift/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">批量赠送管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("37", request)) {
							%>
								<div url="<%=request.getContextPath() %>/batchgiftAudit/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">批量赠送审核</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("38", request)) {
							%>
								<div url="<%=request.getContextPath() %>/events/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">足彩对阵</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("39", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tlotstrategy/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">出票中心管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("40", request)) {
							%>
								<div url="<%=request.getContextPath() %>/yw/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">统计业务管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("41", request)) {
							%>
								<div url="<%=request.getContextPath() %>/servermanager/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">服务器管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("42", request)) {
							%>
								<div url="<%=request.getContextPath() %>/client/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">客户端渠道管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("43", request)) {
							%>
								<div url="<%=request.getContextPath() %>/clientdaily/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">客户端日统计</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("44", request)) {
							%>
								<div url="<%=request.getContextPath() %>/cmsg/mpage" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">留言管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("45", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tlottypeconfig/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">自有彩种管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("46", request)) {
							%>
								<div url="<%=request.getContextPath() %>/opentime/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">各渠道开奖时间</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("47", request)) {
							%>
								<div url="<%=request.getContextPath() %>/channelweights/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">开奖渠道管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("48", request)) {
							%>
								<div url="<%=request.getContextPath() %>/scoretype/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">积分管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("49", request)) {
							%>
								<div url="<%=request.getContextPath() %>/scoredetail/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">用户积分查询</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("50", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tlotcenterDuizhang/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">彩票中心对账</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("51", request)) {
							%>
								<div url="<%=request.getContextPath() %>/agent/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">代理用户</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("52", request)) {
							%>
								<div url="<%=request.getContextPath() %>/ladyopen/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">客服开奖</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("53", request)) {
							%>
								<div url="<%=request.getContextPath() %>/gentlemanopen/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">运维开奖</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("54", request)) {
							%>
								<div url="<%=request.getContextPath() %>/userloginfo/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">用户登录信息</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("55", request)) {
							%>
								<div url="<%=request.getContextPath() %>/caselot/caselotListMg" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">合买管理</span></b></div>
							<% 
								}
							%>
							
							<% 
								if(ispermission("56", request)) {
							%>
								<div url="<%=request.getContextPath() %>/datastat/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">数据统计</span></b></div>
							<% 
								}
							%>
							
							<% 
								if(ispermission("57", request)) {
							%>
								<div url="<%=request.getContextPath() %>/addnumber/addNumberMg" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">追号管理</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("58", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tjingcaiMatches/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">竞彩赛事</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("59", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tjingcairesult/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">竞彩赛果</span></b></div>
							<% 
								}
							%>
							
							<% 
								if(ispermission("60", request)) {
							%>
								<div url="<%=request.getContextPath() %>/betmachine/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">投注机</span></b></div>
							<% 
								}
							%>

							<% 
								if(ispermission("61", request)) {
							%>
								<div url="<%=request.getContextPath() %>/jcmonitor/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">竞彩监控</span></b></div>
								<div url="<%=request.getContextPath() %>/jcmonitor/jcnotauditPage" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">竞彩中奖未审</span></b></div>
							<% 
								}
							%>
							
							<% 
								if(ispermission("62", request)) {
							%>
								<div url="<%=request.getContextPath() %>/present/presentMsg" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">赠送中心管理</span></b></div>
							<% 
								}
							%>
							
							<% 
								if(ispermission("63", request)) {
							%>
								<div url="<%=request.getContextPath() %>/autoTransfer/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">转账</span></b></div>
							<% 
								}
							%>

							<% 
							if(ispermission("64", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tjingcaigyjmatch/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">竞彩冠亚军赛事</span></b></div>
							<% 
								}
							%>
							<% 
							if(ispermission("65", request)) {
							%>
								<div url="<%=request.getContextPath() %>/biguserquery/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">大客户查询</span></b></div>
							<% 
								}
							%>
							<% 
							if(ispermission("66", request)) {
							%>
								<div url="<%=request.getContextPath() %>/replys/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">查询留言回复</span></b></div>
							<% 
								}
							%>
								<% 
							if(ispermission("67", request)) {
							%>
								<div url="<%=request.getContextPath() %>/wapstat/regstat" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">wap数据统计</span></b></div>
							<% 
								}
							%>
							
							<% 
							if(ispermission("68", request)) {
							%>
								<div url="<%=request.getContextPath() %>/sendmoney/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">赠送彩金红包</span></b></div>
							<% 
								}
							%>
							
								<% 
							if(ispermission("69", request)) {
							%>
								<div url="<%=request.getContextPath() %>/delayIOS/addUI" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">增加IOS推送</span></b></div>
							<% 
								}
							%>
							
									<% 
							if(ispermission("70", request)) {
							%>
								<div url="<%=request.getContextPath() %>/ttransfer/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">代充值管理</span></b></div>
							<% 
								}
							%>
							
									<% 
							if(ispermission("71", request)) {
							%>
								<div url="<%=request.getContextPath() %>/letter/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">站内消息管理</span></b></div>
							<% 
								}
							%>
							
								<% 
							if(ispermission("72", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tcombine/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">用户合并</span></b></div>
							<% 
								}
							%>

								<% 
							if(ispermission("73", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tdrawerMonitor/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">出票监控</span></b></div>
							<% 
								}
							%>
							
								<% 
							if(ispermission("74", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tchargeDistribution/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">充值权重分配</span></b></div>
							<% 
								}
							%>
								<% 
							if(ispermission("75", request)) {
							%>
								<div url="<%=request.getContextPath() %>/processing/page" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">查看处理中票</span></b></div>
							<% 
								}
							%>
							<% 
								if(ispermission("76", request)) {
							%>
								<div url="<%=request.getContextPath() %>/dmsg/mpage" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">删除留言</span></b></div>
							<% 
								}
							%>
							
							<% 
								if(ispermission("77", request)) {
							%>
								<div url="<%=request.getContextPath() %>/tlotstrategy/hnfcfp" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">湖南福彩分配</span></b></div>
							<% 
								}
							%>
							
							<% 
								if(ispermission("78", request)) {
							%>
								<div url="<%=request.getContextPath() %>/agencybalance/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">大客户余额监控</span></b></div>
								<div url="<%=request.getContextPath() %>/agencybalance/list2" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">大客户余额查询</span></b></div>
							
							<% 
								}
							%>
							<% 
								if(ispermission("79", request)) {
							%>
								<div url="<%=request.getContextPath() %>/quartz/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">定时任务管理</span></b></div>
							<% 
								}
							%>
							
							<% 
								if(ispermission("80", request)) {
							%>
								<div url="<%=request.getContextPath() %>/chargeconfig/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">充值信息配置</span></b></div>
							<% 
								}
							%>
							
							<% 
								if(ispermission("81", request)) {
							%>
								<div url="<%=request.getContextPath() %>/userchannel/list" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">修改用户渠道</span></b></div>
							<% 
								}
							%>
							
							<% 
								if(ispermission("83", request)) {
							%>
								<div url="<%=request.getContextPath() %>/msgcenter/redirect/listWaitforSendSMS" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">待发送短信</span></b></div>
								<div url="<%=request.getContextPath() %>/msgcenter/redirect/selectSMSLog" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">短信发送日志</span></b></div>
								<div url="<%=request.getContextPath() %>/msgcenter/redirect/selectWaitforSend" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">待发ios消息</span></b></div>
								<div url="<%=request.getContextPath() %>/msgcenter/redirect/selectIOSLog" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab"><b><span style="margin-left: 20px">ios发送日志</span></b></div>
								
							<% 
								}
							%>
							
							</div>
							</td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</body>
</html>