<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
</head>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Dialog.js"></script>
<script language="JavaScript">
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

</script>

<body style="margin-left: 10px;">
<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" class="thOver" colspan="6"><strong>查询</strong></td>
			</tr>
			<tr>
				<td>
					<a href="<%=request.getContextPath()%>/tuserinfoes/useractionlist?userno=${account.userno}">投注信息</a>
				</td>
				<td>
				   <a href="<%=request.getContextPath()%>/tuserinfoes/caselotlist?userno=${account.userno}">合买信息</a>
				</td>
				<td>
					<a href="<%=request.getContextPath()%>/tuserinfoes/tchargelist?userno=${account.userno}">充值信息</a>
				</td>
				<td>
				赠送彩金信息
				<!-- 
					<a href="<%=request.getContextPath()%>/tuserinfoes/tpresentlist?userno=${account.userno}">赠送彩金信息</a>
					 -->
				</td>
				<td>
					<a href="<%=request.getContextPath()%>/tuserinfoes/tsubscribelist?userno=${account.userno}">追号信息</a>
				</td>
				<td>
					<a href="<%=request.getContextPath()%>/tuserinfoes/tcashlist?userno=${account.userno}">提现信息</a>
				</td>
			</tr>
		</tbody>
	</table>

	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="3" class="thOver"><strong>默认账户</strong></td>
			</tr>
			<c:if test="${account != null}">
				<tr>
					<td width="150">账户余额:</td>
					<td>${account.balance}</td>
					<td></td>
				</tr>
				<tr>
					<td>可提现余额:</td>
					<td>${account.drawbalance}</td>
					<td></td>
				</tr>
				<tr>
					<td>冻结金额:</td>
					<td>${account.freezebalance}</td>
					<td></td>
				</tr>
				<tr>
					<td>最后一笔投注金额:</td>
					<td>${account.lastbetamt}</td>
					<td></td>
				</tr>
				<tr>
					<td>最后一笔投注时间:</td>
					<td><fmt:formatDate value="${account.lastbettime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td></td>
				</tr>
				<tr>
					<td>总投注金额:</td>
					<td>${account.totalbetamt}</td>
					<td></td>
				</tr>
				<tr>
					<td>最后一笔中奖金额:</td>
					<td>${account.lastprizeamt}</td>
					<td></td>
				</tr>
				<tr>
					<td>最后一笔中奖时间:</td>
					<td><fmt:formatDate value="${account.lastprizetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td></td>
				</tr>
				<tr>
					<td>总中奖金额:</td>
					<td>${account.totalprizeamt}</td>
					<td></td>
				</tr>
				<tr>
					<td>最后一笔充值金额:</td>
					<td>${account.lastdepositamt}</td>
					<td></td>
				</tr>
				<tr>
					<td>最后一笔充值时间:</td>
					<td><fmt:formatDate value="${account.lastdeposittime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td></td>
				</tr>
				<tr>
					<td>总充值金额:</td>
					<td>${account.totaldepositamt}</td>
					<td></td>
				</tr>
				<tr>
					<td>最后一笔提现金额:</td>
					<td>${account.lastdrawamt}</td>
					<td></td>
				</tr>
				<tr>
					<td>最后一笔提现时间:</td>
					<td><fmt:formatDate value="${account.lastdrawtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td></td>
				</tr>
				<tr>
					<td>总提现金额:</td>
					<td>${account.totaldrawamt}</td>
					<td></td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="3" class="thOver"><strong>彩金账户</strong></td>
			</tr>
			<c:choose>
				<c:when test="${null != bet}">
				<tr>
					<td width="150">账户余额:</td>
					<td>${bet.balance}</td>
					<td></td>
				</tr>
				<tr>
					<td>可提现余额:</td>
					<td>${bet.drawbalance}</td>
					<td></td>
				</tr>
				<tr>
					<td>冻结金额:</td>
					<td>${bet.freezebalance}</td>
					<td></td>
				</tr>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>

	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="3" class="thOver"><strong>奖金账户</strong></td>
			</tr>
			<c:choose>
				<c:when test="${null != prize}">
				<tr>
					<td width="150">账户余额:</td>
					<td>${prize.balance}</td>
					<td></td>
				</tr>
				<tr>
					<td>可提现余额:</td>
					<td>${prize.drawbalance}</td>
					<td></td>
				</tr>
				<tr>
					<td>冻结金额:</td>
					<td>${prize.freezebalance}</td>
					<td></td>
				</tr>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</body>
</html>