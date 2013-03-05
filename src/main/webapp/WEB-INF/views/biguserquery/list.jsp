<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
<%@page import="com.ruyicai.mgr.consts.SettleFlagState"%>
<%@page import="com.ruyicai.mgr.consts.BetType"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.ruyicai.mgr.domain.Tlot"%>
<%@page import="com.ruyicai.mgr.lot.LotCenter"%>
<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.consts.UserState"%>
<%@page import="com.ruyicai.mgr.domain.Tuserinfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://blog.csdn.net/qjyong" prefix="q" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Dialog.js"></script>	
</head>
<script type="text/javascript">	
<% 
String errormsg = (String)request.getAttribute("errormsg");
if(!StringUtil.isEmpty(errormsg)) {
%>
function showerror() {
	Dialog.alert("<%=errormsg%>");
}
$(document).ready(function() {
	showerror();
});
<%	
}
%>	

function continuebet(flowno) {
	$.ajax({
		url:"<%=request.getContextPath() %>/biguserquery/continuebet",
		type:"POST",
		data:"flowno=" + flowno,
		success:function(data){
			if(data.errorCode == "0") {
				alert("重投成功");
				$("#td" + flowno).html("已投出");
			}else{
				alert(data.value);
			}
		}
	});
}

function undeduct(flowno) {
	if(!window.confirm("确定要冲正吗?")) {
		return;
	}
	$.ajax({
		url:"<%=request.getContextPath() %>/biguserquery/undeduct",
		type:"POST",
		data:"flowno=" + flowno,
		success:function(data){
			if(data.errorCode == "0") {
				alert("冲正成功");
				$("#cz" + flowno).html("已冲正");
			}
		}
	});
}

function golist(){
	var flownoVal = document.getElementById("flowno1").value;
	location.href = "<%=request.getContextPath()%>/biguserquery/list2?flowno=" + flownoVal;
}
</script>
<body>
	<div style="margin-top: 10px;"></div>
			<form action="<%=request.getContextPath()%>/biguserquery/list"
				method="post">
				<div style="float: left;">
					<table width="100%" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td align="right">根据客户订单号查票。大客户订单号:</td>
							<td ><input name="agencyflowno" type="text" style="width: 110px" value="${param.agencyflowno}"
								id="agencyflowno" class="inputText" onfocus="this.select();"/></td>
							<td align="center"><input type="submit" value="查询" class="inputButton">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
							<td align="right">根据票号查询大客户订单。 票号:</td>
							<td id="flowno2" name="flowno2"><input id="flowno1" name="flowno1" type="text" style="width: 110px" value="${param.flowno}"
								id="flowno1" class="inputText" onfocus="this.select();"/></td>
							<td align="center"><input type="button" value="查询" class="inputButton" onclick="golist();">&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</div>
			</form>
			<table width="100%" cellspacing="0" cellpadding="2"
				class="dataTable">
				<tbody>
					<tr class="dataTableHead">
						<td width="2%" height="30" class="thOver"></td>
						<td width="12%" class="thOver"><strong>流水号</strong></td>
						<td width="7%" class="thOver"><strong>用户编号</strong></td>
						<td width="6%" class="thOver"><strong>渠道</strong></td>
						<td width="6%" class="thOver"><strong>用户系统</strong></td>
						<td width="5%" class="thOver"><strong>彩种</strong></td>
						<td width="8%" class="thOver"><strong>期号</strong></td>
						<td width="4%" class="thOver"><strong>注数</strong></td>
						<td width="4%" class="thOver"><strong>倍数</strong></td>
						<td width="6%" class="thOver"><strong>金额</strong></td>
						<td width="5%" class="thOver"><strong>中奖金额</strong></td>
						<td width="12%" class="thOver"><strong>投注时间</strong></td>
						<td width="5%" class="thOver"><strong>类型</strong></td>
						<td width="6%" class="thOver"><strong>标识</strong></td>
						<td width="6%" class="thOver"><strong>赔率</strong></td>
						<td width="6%" class="thOver"><strong>状态</strong></td>
					</tr>
						<c:forEach items="${list}" var="lot" varStatus="num">
							<% 
							Tlot tlot = (Tlot)pageContext.getAttribute("lot");
							String lottype = Lottype.getName(tlot.getLotno());
							String type = BetType.getBetType(tlot.getBettype());
							String flag = SettleFlagState.getMemo(tlot.getSettleflag());
							String instate = "";
							if(new BigDecimal(-1).equals(tlot.getInstate())) {
								instate = "未处理";
							}
							if(new BigDecimal(7).equals(tlot.getInstate())) {
								instate = "失败";
							}
							if(BigDecimal.ONE.equals(tlot.getInstate())) {
								instate = "成功";
							}
							if(new BigDecimal(2).equals(tlot.getInstate())) {
								instate = "处理中";
							}
						%>
							<tr>
								<td title="${num.count}">${num.count}</td>
								<td title="${lot.flowno}">${lot.flowno}</td>
								<td title="${lot.userno}">${lot.userno}</td>
								<td title="${lot.channel}">${lot.channel}</td>
								<td title="${lot.subchannel}">${lot.subchannel}</td>
								<td title="<%=lottype%>"><%=lottype%></td>
								<td title="${lot.batchcode}">${lot.batchcode}</td>
								<td title="${lot.betnum}">${lot.betnum}</td>
								<td title="${lot.lotmulti}">${lot.lotmulti}</td>
								<td title="${lot.amt}">${lot.amt}</td>
								<td title="${lot.prizeamt}">${lot.prizeamt}</td>
								<td title="${lot.ordertime}"><fmt:formatDate value="${lot.ordertime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td title="<%=type%>"><%=type%></td>
								<td title="<%=flag%>"><%=flag%></td>
							 	<td title="${lot.peilu}">${lot.peilu}</td> 
							<%
								if(new BigDecimal(7).equals(tlot.getInstate())) {
							%>
								<td title="<%=instate%>"><td id="td${lot.flowno}"><input type="button" value="重投" onclick="continuebet('${lot.flowno}');" /></td>
								<%-- <td id="cz${lot.flowno}"><%=instate%><input type="button" value="冲正" onclick="undeduct('${lot.flowno}');"/></td>
								</td> --%>
							<%} else { %>
								<td title="<%=instate%>"><%=instate%></td>
							<%} %>
							</tr>
						</c:forEach>
				</tbody>
			</table>
			
			<hr>
			<%-- <form action="<%=request.getContextPath()%>/biguserquery/list2"
				method="post">
				<div style="float: left;">
					<table width="100%" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td align="right">根据票号查询大客户订单。 票号:</td>
							<td ><input name="flowno" type="text" style="width: 110px" value="${param.flowno}"
								id="flowno" class="inputText" onfocus="this.select();"/></td>
							<td align="center"><input type="submit" value="查询" class="inputButton">&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
					</table>
				</div>
			</form> --%>
			<table width="100%" cellspacing="0" cellpadding="2"
				class="dataTable">
				<tbody>
					<tr class="dataTableHead">
						<td width="2%" height="30" class="thOver"></td>
						<td width="8%" class="thOver"><strong>agencyno</strong></td>
						<td width="10%" class="thOver"><strong>大客户订单编号</strong></td>
						<td width="12%" class="thOver"><strong>订单编号</strong></td>
						<td width="5%" class="thOver"><strong>投注通知</strong></td>
						<td width="6%" class="thOver"><strong>投注通知数</strong></td>
						<td width="5%" class="thOver"><strong>中奖通知</strong></td>
						<td width="6%" class="thOver"><strong>中奖通知数</strong></td>
						<td width="6%" class="thOver"><strong>投注状态</strong></td>
						<td width="6%" class="thOver"><strong>errorcode</strong></td>
					</tr>
					<c:forEach items="${list2}" var="lotmap" varStatus="num">
						<tr>
							<td title="${num.count}">${num.count}</td>
							<td title="${lotmap.id.agencyno}">${lotmap.id.agencyno}</td>
							<td title="${lotmap.id.agencyflowno}">${lotmap.id.agencyflowno}</td>
							<td title="${lotmap.realflowno}">${lotmap.realflowno}</td>
							<td title="${lotmap.betflag}">${lotmap.betflag}</td>
							<td title="${lotmap.betnoticenum}">${lotmap.betnoticenum}</td>
							<td title="${lotmap.winflag}">${lotmap.winflag}</td>
							<td title="${lotmap.winnoitcenum}">${lotmap.winnoitcenum}</td>
							<td title="${lotmap.state}">${lotmap.state}</td>
							<td title="${lotmap.errorcode}">${lotmap.errorcode}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
</body>
</html>