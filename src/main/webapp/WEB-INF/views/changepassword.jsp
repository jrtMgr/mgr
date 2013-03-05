<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Dialog.js"></script>	
<script type="text/javascript">
function changepwd() {
	if($.trim($("#oldpassowrd").val()) == "") {
		Dialog.alert("请输入旧密码");
		return false;
	}
	if($.trim($("#newpassowrd").val()) == "") {
		Dialog.alert("请输入新密码");
		return false;
	}
 	if($.trim($("#confirmpassowrd").val()) == "") {
		Dialog.alert("请输入确认密码");
		return false;
	}
	if($.trim($("#newpassowrd").val()) != $.trim($("#confirmpassowrd").val())) {
		Dialog.alert("两次密码输入不一致");
		return false;
	}
	return true;
}
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
<body>
<form action="<%=request.getContextPath() %>/changepassword" method="post" onsubmit="return changepwd();">
<table width="100%" cellpadding="5" cellspacing="5" border="0">
<tr>
<td>&nbsp;</td>
</tr>
<tr>
	<td align="right">旧密码:</td>
	<td align="left">
		<input name="oldpassowrd" id="oldpassowrd" type="text" style="width:120px" class="inputText" onfocus="this.select();"/>
	</td>
</tr>
<tr>
<td align="right">新密码:</td>
	<td align="left">
		<input name="newpassowrd" id="newpassowrd" type="password" style="width:120px" class="inputText" onfocus="this.select();"/>
	</td>
</tr>
<tr>
<td align="right">确认密码:</td>
	<td align="left">
		<input name="confirmpassowrd" id="confirmpassowrd" type="password" style="width:120px" class="inputText" onfocus="this.select();"/>
	</td>
</tr>
<tr>
<td colspan="2" align="center">
<input type="submit" value=" 修 改 " class="inputButton">
</td>
</tr>
</table>
</form>
</body>
</html>