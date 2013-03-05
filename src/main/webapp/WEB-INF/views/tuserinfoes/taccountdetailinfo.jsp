<%@page import="java.math.BigDecimal"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.consts.TransactionType"%>
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
	<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Dialog.js"></script>
</head>
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
	<table width="100%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="3" class="thOver">账户明细2&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/tuserinfoes/accountoverview?userno=${userno}">返回账户明细</a></td>
			</tr>
			<tr>
					<td>交易类型</td>
					<td>进出账</td>
					<td>金额（分）</td>
			</tr>
			<c:forEach var="t" items="${list}" varStatus="i">
			    <tr>	
			   <c:forEach var="t1" items="${t}" varStatus="j">			  
			      <td>${t1}</td>
			   </c:forEach>	
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>