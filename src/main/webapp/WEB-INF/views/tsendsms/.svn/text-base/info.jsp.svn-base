<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
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

function onsubmitfun() {
	if($.trim($("#sms").val()) == "") {
		Dialog.alert("请输入短信内容");
		return false;
	} 
	if($.trim($("#mobileid").val()) == "") {
		Dialog.alert("请输入手机号码");
		return false;
	} 
	return true;
}
</script>	
</head>
<body style="margin-left: 10px;">
<form action="<%=request.getContextPath()%>/tsendsms/send" method="post" onsubmit="return onsubmitfun();">
<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="3"><strong>发送短信</strong>&nbsp(剩余条数:${lastnum })</td>
			</tr>
			<tr>
				<td align="right">
					短信内容
				</td>
				<td colspan="2">
<textarea rows="20" cols="50" name="sms" id="sms"></textarea>								
				</td>
			</tr>
			<tr>
				<td align="right">手机号码</td>
				<td colspan="2">
					<textarea rows="20" cols="50" name="mobileid" id="mobileid"></textarea><br/><font color="red">每个手机号间以逗号分隔</font>
				</td>
			</tr>
			<tr>
				<td align="right">短信后缀</td>
				<td colspan="2">
					<input type="radio" id="suffix" name="suffix" value="0" checked="checked">后缀为【如意彩】|
					<input type="radio" id="suffix" name="suffix" value="91">后缀为【客服信息】
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
				<input type="submit" value=" 发 送 " class="inputButton">
				</td>
			</tr>
		</tbody>
	</table>
	</form>
</body>
</html>