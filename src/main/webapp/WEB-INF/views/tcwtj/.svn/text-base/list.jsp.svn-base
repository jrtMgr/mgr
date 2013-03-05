<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.ruyicai.mgr.domain.Tcwtj"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>财务统计</title>
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<style type="text/css">
td {border: 1px solid #C6C6C6}
</style>
<script type="text/javascript">
function datechange() {
	var date = $("#year").val() + "-" + $("#month").val();
	window.parent.content.location = "<%=request.getContextPath() %>/tcwtj/list?date=" + date;
}
<% 
String errormsg = (String)request.getAttribute("errormsg");
if(!StringUtil.isEmpty(errormsg)) {
%>
function showerror() {
	alert("<%=errormsg%>");
	//Dialog.alert("<%=errormsg%>");
}
<%	
}
%>	
</script>
</head>
<body onload="javascript:showerror()">
<% 
	String date = (String)request.getAttribute("date");
	String[] values = date.split("\\-");
	String year = values[0];
	String month = values[1];
	pageContext.setAttribute("year", year);
	pageContext.setAttribute("month", month);
%>
<table width="111%" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td class="" style="" colspan="2" align="center" height="50px"><select id="year" onchange="datechange()">
			<option value="2010"<c:if test="${year eq '2010'}"> selected="selected"</c:if>>2010</option>
			<option value="2011"<c:if test="${year eq '2011'}"> selected="selected"</c:if>>2011</option>
			<option value="2012"<c:if test="${year eq '2012'}"> selected="selected"</c:if>>2012</option>
			<option value="2013"<c:if test="${year eq '2013'}"> selected="selected"</c:if>>2013</option>
			<option value="2014"<c:if test="${year eq '2014'}"> selected="selected"</c:if>>2014</option>
			<option value="2015"<c:if test="${year eq '2015'}"> selected="selected"</c:if>>2015</option>
			<option value="2016"<c:if test="${year eq '2016'}"> selected="selected"</c:if>>2016</option>
			<option value="2017"<c:if test="${year eq '2017'}"> selected="selected"</c:if>>2017</option>
			<option value="2018"<c:if test="${year eq '2018'}"> selected="selected"</c:if>>2018</option>
			<option value="2019"<c:if test="${year eq '2019'}"> selected="selected"</c:if>>2019</option>
			<option value="2020"<c:if test="${year eq '2020'}"> selected="selected"</c:if>>2020</option>
		</select><b>年</b>
		<select id="month" onchange="datechange()">
			<option value="01"<c:if test="${month eq '01'}"> selected="selected"</c:if>>1</option>
			<option value="02"<c:if test="${month eq '02'}"> selected="selected"</c:if>>2</option>
			<option value="03"<c:if test="${month eq '03'}"> selected="selected"</c:if>>3</option>
			<option value="04"<c:if test="${month eq '04'}"> selected="selected"</c:if>>4</option>
			<option value="05"<c:if test="${month eq '05'}"> selected="selected"</c:if>>5</option>
			<option value="06"<c:if test="${month eq '06'}"> selected="selected"</c:if>>6</option>
			<option value="07"<c:if test="${month eq '07'}"> selected="selected"</c:if>>7</option>
			<option value="08"<c:if test="${month eq '08'}"> selected="selected"</c:if>>8</option>
			<option value="09"<c:if test="${month eq '09'}"> selected="selected"</c:if>>9</option>
			<option value="10"<c:if test="${month eq '10'}"> selected="selected"</c:if>>10</option>
			<option value="11"<c:if test="${month eq '11'}"> selected="selected"</c:if>>11</option>
			<option value="12"<c:if test="${month eq '12'}"> selected="selected"</c:if>>12</option>
		</select>
		<b>月数据统计表</b></td>
	</tr>
	<tr>
		<td align="center" style="padding-top: 5px;padding-bottom: 5px;border-bottom: 0px;"><b>增加金额</b></td>
		<td align="center" style="padding-top: 5px;padding-bottom: 5px;border-bottom: 0px;"><b>减少金额</b></td>
	</tr>
	<tr>
		<td style="border:0">
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="dataTable">
				<tr class="dataTableHead">
					<td width="7%" class="thOver" style="border-top: 0px;">日期</td>
					<td class="thOver" style="border-top: 0px;">客户充值</td>
					<td class="thOver" style="border-top: 0px;">渠道充值</td>
					<td class="thOver" style="border-top: 0px;">兑奖</td>
					<td class="thOver" style="border-top: 0px;">活动赠彩</td>
					<td class="thOver" style="border-top: 0px;">充值赠彩</td>
					<td class="thOver" style="border-top: 0px;">撤单</td>
					<td class="thOver" style="border-top: 0px;">增加合计</td>
				</tr>
				<% 
					List<Tcwtj> list = (List<Tcwtj>)request.getAttribute("list");
					BigDecimal userchargeAll = BigDecimal.ZERO;
					BigDecimal channelchargeAll = BigDecimal.ZERO;
					BigDecimal encashAll = BigDecimal.ZERO;
					BigDecimal activitypersentAll = BigDecimal.ZERO;
					BigDecimal chargepersentAll = BigDecimal.ZERO;
					BigDecimal cannelAll = BigDecimal.ZERO;
					BigDecimal inhjAll = BigDecimal.ZERO;
					BigDecimal userbetAll = BigDecimal.ZERO;
					BigDecimal channelbetAll = BigDecimal.ZERO;
					BigDecimal drawAll = BigDecimal.ZERO;
					BigDecimal feeAll = BigDecimal.ZERO;
					BigDecimal other1All = BigDecimal.ZERO;
					BigDecimal outhjAll = BigDecimal.ZERO;
					for(Tcwtj tcwtj : list) {
				%>
				<tr>
					<td title="<fmt:formatDate value="<%=tcwtj.getTjdate() %>" pattern="dd"/>"><fmt:formatDate value="<%=tcwtj.getTjdate() %>" pattern="dd"/></td>
					<td title="<%=tcwtj.getUsercharge() %>"><%=tcwtj.getUsercharge() %></td>
					<td title="<%=tcwtj.getChannelcharge() %>"><%=tcwtj.getChannelcharge() %></td>
					<td title="<%=tcwtj.getEncash() %>"><%=tcwtj.getEncash() %></td>
					<td title="<%=tcwtj.getActivitypersent() %>"><%=tcwtj.getActivitypersent() %></td>
					<td title="<%=tcwtj.getChargepersent() %>"><%=tcwtj.getChargepersent() %></td>
					<td title="<%=tcwtj.getCannel() %>"><%=tcwtj.getCannel() %></td>
					<td title="<%=tcwtj.getInhj() %>"><%=tcwtj.getInhj() %></td>
				</tr>
				<% 
					userchargeAll = userchargeAll.add(tcwtj.getUsercharge());
					channelchargeAll = channelchargeAll.add(tcwtj.getChannelcharge());
					encashAll = encashAll.add(tcwtj.getEncash());
					activitypersentAll = activitypersentAll.add(tcwtj.getActivitypersent());
					chargepersentAll = chargepersentAll.add(tcwtj.getChargepersent());
					cannelAll = cannelAll.add(tcwtj.getCannel());
					inhjAll = inhjAll.add(tcwtj.getInhj());
					}
				%>
				<tr>
					<td>合计</td>
					<td title="<%=userchargeAll %>"><%=userchargeAll %></td>
					<td title="<%=channelchargeAll %>"><%=channelchargeAll %></td>
					<td title="<%=encashAll %>"><%=encashAll %></td>
					<td title="<%=activitypersentAll %>"><%=activitypersentAll %></td>
					<td title="<%=chargepersentAll %>"><%=chargepersentAll %></td>
					<td title="<%=cannelAll %>"><%=cannelAll %></td>
					<td title="<%=inhjAll %>"><%=inhjAll %></td>
				</tr>
			</table>
		</td>
		<td style="border:0">
			<table width="100%" cellspacing="0" cellpadding="0" border="0" class="dataTable">
				<tr class="dataTableHead">
					<td class="thOver" style="border-top: 0px;">客户投注</td>
					<td class="thOver" style="border-top: 0px;">渠道投注</td>
					<td class="thOver" style="border-top: 0px;">提现</td>
					<td class="thOver" style="border-top: 0px;">手续费</td>
					<td class="thOver" style="border-top: 0px;">其他</td>
					<td class="thOver" style="border-top: 0px;">减少合计</td>
					<td width="18%" class="thOver" style="border-top: 0px;">账户余额</td>
					<td class="thOver" style="border-top: 0px;">备注</td>
					<td class="thOver" style="border-top: 0px;">操作</td>
				</tr>
				<% 
					for(Tcwtj tcwtj : list) {
				%>
				<tr>
					<td title="<%=tcwtj.getUserbet() %>"><%=tcwtj.getUserbet() %></td>
					<td title="<%=tcwtj.getChannelbet() %>"><%=tcwtj.getChannelbet() %></td>
					<td title="<%=tcwtj.getDraw() %>"><%=tcwtj.getDraw() %></td>
					<td title="<%=tcwtj.getFee() %>"><%=tcwtj.getFee() %></td>
					<td title="<%=tcwtj.getOther1() %>"><%=tcwtj.getOther1() %></td>
					<td title="<%=tcwtj.getOuthj() %>"><%=tcwtj.getOuthj() %></td>
					<td title="<%=tcwtj.getBalance() %>"><%=tcwtj.getBalance() %></td>
					<td title="<%=StringUtil.isEmpty(tcwtj.getMemo()) ? "" : tcwtj.getMemo() %>"><%=StringUtil.isEmpty(tcwtj.getMemo()) ? "" : tcwtj.getMemo() %></td>
					<td><a href="<%=request.getContextPath()%>/tcwtj/editUI?id=<%=tcwtj.getId()%>&date=<%=year+"-"+month%>">修改</a></td>
				</tr>	
				<% 
					userbetAll = userbetAll.add(tcwtj.getUserbet());
					channelbetAll = channelbetAll.add(tcwtj.getChannelbet());
					drawAll = drawAll.add(tcwtj.getDraw());
					feeAll = feeAll.add(tcwtj.getFee());
					other1All = other1All.add(tcwtj.getOther1());
					outhjAll = outhjAll.add(tcwtj.getOuthj());
					}
				%>
				<tr>
					<td title="<%=userbetAll %>"><%=userbetAll %></td>
					<td title="<%=channelbetAll %>"><%=channelbetAll %></td>
					<td title="<%=drawAll %>"><%=drawAll %></td>
					<td title="<%=feeAll %>"><%=feeAll %></td>
					<td title="<%=other1All %>"><%=other1All %></td>
					<td title="<%=outhjAll %>"><%=outhjAll %></td>
					<td></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr><td style="border: 0; text-align: center;" colspan="2"><a href="<%=request.getContextPath()%>/tcwtj/generateReport?date=<%=year+"-"+month%>">生成EXCEL</a></td></tr>
</table>
</body>
</html>