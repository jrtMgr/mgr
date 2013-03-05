<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.domain.Twininfo"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" 
	src="<%=request.getContextPath()%>/js/jquery.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Dialog.js"></script>		
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
function submint1(){
	if(!window.confirm("确定要充值吗?")) {
		return;
	}
	document.forms[0].submit();
}
</script>	
</head>
<body>
<form action="<%=request.getContextPath()%>/recharge/processCharge" method="post" id="form1">
	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
		<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="4"><strong>如意卡充值</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="10%"><select id="type" name="type">
				                <option value="mobileid" <c:if test='${"mobileid" eq type}'>selected</c:if>>手机</option>
								<option value="userName" <c:if test='${"userName" eq type}'>selected</c:if>>用户名</option>
								<option value="email" <c:if test='${"email" eq type}'>selected</c:if>>邮箱</option></select></td>
				<td width="40%"><input name="userid" id="userid" type="text" style="width: 120px" class="inputText" onfocus="this.select();"/></td>
				<td align="right" width="10%">接入方式:</td>
				<td width="40%" align="left">
					<select name="accesstype" style="width: 120px" id="accesstype" class="inputText" onfocus="this.select();">
						<option value="B">web</option>
						<option value="C">客户端</option>
						<option value="W">wap</option>
					</select>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">channel:</td>
				<td>
					<input name="channel" id="channel" type="text" style="width: 120px" class="inputText" onfocus="this.select();"/>
				</td>
				<td align="right">subchannel:</td>
				<td align="left">
					<select name="subchannel" style="width: 120px" id="subchannel" class="inputText" onfocus="this.select();">
						<option value="00092493">00092493</option>
					</select>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">卡号:</td>
				<td>
				<input name="cardno" type="text" style="width: 120px" id="cardno" class="inputText" onfocus="this.select();"/>
				</td>
				<td align="right">卡密码:</td>
				<td align="left"><input name="cardpwd" type="text" id="cardpwd" class="inputText" onfocus="this.select();"/></td>
			</tr>
			
		</tbody>
	</table>
	<table width="60%" cellspacing="0" cellpadding="2"	style="margin-top: 10px">
		<tr>
			<td align="center"><input type="button" value="充值 " class="inputButton" onclick="submint1()"></td>
		</tr>
	</table>
</form>
</body>
</html>
