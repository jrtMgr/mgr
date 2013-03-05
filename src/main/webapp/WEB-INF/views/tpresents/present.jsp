<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
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
</script>	
</head>
<body style="margin-left: 10px;">

<form action="<%=request.getContextPath()%>/tpresents/dopresent" method="post">
<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="3"><strong>确认赠送</strong></td>
			</tr>
			<tr>
				<td align="right">
					彩金金额:
				</td>
				<td colspan="2">
					${money}元
					<input type="hidden" name="money" value="${money}">
				</td>
				</tr>
			<tr>
				<td align="right">可提现:</td>
				<td colspan="2">
					<c:choose>
						<c:when test="${draw==0}">
							否
						</c:when>
						<c:otherwise>
							是
						</c:otherwise>
					</c:choose>
					<input type="hidden" name="draw" value="${draw}">
				</td>
			</tr>
			<tr>
				<td align="right">用户系统:</td>
				<td colspan="2">
					${subchannel}
					<input type="hidden" name="subchannel" value="${subchannel}">
				</td>
			</tr>
			<tr>
				<td align="right">渠道:</td>
				<td colspan="2">
					${channel}
					<input type="hidden" name="channel" value="${channel}">
				</td>
			</tr>
			<tr>
				<td align="right">短信内容:</td>
				<td colspan="2">
					${sms}
					<input type="hidden" name="sms" value="${sms}">
				</td>
			</tr>
			<tr>
				<td align="right">存在用户:</td>
				<td colspan="2">
					<c:forEach items="${exists}" var="user">
						${user}<br/>
						<input type="hidden" name="exists" value="${user}">
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td align="right">不存在用户:</td>
				<td colspan="2">
					<c:forEach items="${notexists}" var="user">
						${user}<br/>
						<input type="hidden" name="notexists" value="${user}">
					</c:forEach>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
				<input type="submit" value=" 确认赠送 "
								class="inputButton">
				</td>
			</tr>
		</tbody>
	</table>
	</form>
</body>
</html>