<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户信息</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Dialog.js"></script>	
<script type="text/javascript">	
<% 
String errormsg = (String)request.getAttribute("errormsg");
String type = (String)request.getAttribute("type");
String state = (String)request.getAttribute("state");
String temp = "state=1";
if (!StringUtil.isEmpty(type) && !StringUtil.isEmpty(state)) {
	temp = "state=" + state + "&type=" + type;
}
if(!StringUtil.isEmpty(errormsg)) {
%>
function showerror() {
	alert("<%=errormsg%>");
	window.opener.location="<%=request.getContextPath() %>/tcash/list?<%=temp%>";
	window.close();
	
}
$(document).ready(function() {
	showerror();
});
<%	
}
%>	
</script>
</head>
<body style="margin-left: 10px; font-size: 12px;">
	<form action="<%=request.getContextPath()%>/tcash/editCash" method="post" onsubmit="return confirm('是否确认保存修改?')">
	<input type="hidden" name="id" value="${tcashdetail.id}">
	<table border="1px" style="margin-top: 2px; margin-left: 30px; margin-bottom:20px;" class="dataTable" >
			<tr class="dataTableHead">
				<td width="" height="30" class="thOver" colspan="2" align="center"><strong>编辑用户提现信息</strong></td>
			</tr>
			<tr>
				<td align="right" width="200px;">收款银行户名：</td>
				<td ><input type="text" name="name" id="name" value="${tcashdetail.name}" /> </td>
			</tr>
			<tr>
				<td align="right">收款银行账号：</td>
				<td><input type="text" name="bankaccount" id="bankaccount" value="${tcashdetail.bankaccount}" /></td>
			</tr>
			<tr>
				<td align="right">收款开户银行：</td>
				<td><input type="text" name="bankname" id="bankname" value="${tcashdetail.bankname}" /></td>
			</tr>
			<tr>
				<td align="right">收款银行所在省份：</td>
				<td><input type="text" name="provname" id="provname" value="${tcashdetail.provname}" /></td>
			</tr>
			<tr>
				<td align="right">收款银行所在市：</td>
				<td><input type="text" name="areaname" id="areaname" value="${tcashdetail.areaname}"/></td>
			</tr>
			<tr>
				<td align="right">状态：</td>
				<td>
				<div align="left"><select id="state" name="state">
					<option value="104" <c:if test="${tcashdetail.state eq 104}">selected</c:if>>驳回</option>
				</select>
				</div>
				</td>
			</tr>
				<tr>
					<td align="right">驳回原因：</td>
					<td><input type="text" name="rejectReason"/></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="&nbsp保&nbsp存&nbsp"/> </td>
			</tr>
	</table>
	</form>
</body>
</html>