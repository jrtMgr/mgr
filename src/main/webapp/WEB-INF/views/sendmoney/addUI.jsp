<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.domain.Ttransaction"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 	
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
	if($.trim($("#amt").val()) == "") {
		Dialog.alert("请输入彩金金额");
		return false;
	} 
	if($.trim($("#reciverUserno").val()) == "") {
		Dialog.alert("请输入用户标识");
		return false;
	} 
	return window.confirm("确定添加吗?");
}
</script>	
</head>
<body style="margin-left: 10px;">
<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="3"><strong>赠送彩金</strong></td>
			</tr>
<form action="<%=request.getContextPath()%>/sendmoney/add" method="post" onsubmit="return onsubmitfun();">
			<tr>
				<td align="right">
					彩金金额
				</td>
				<td colspan="2">
					<input name="amt" type="text" style="width: 120px" id="amt"
								class="inputText" onfocus="this.select();" />${param.amt}元
				</td>
				</tr>
			<tr>
				<td align="right">红包内容</td>
				<td colspan="2">
					<textarea rows="20" cols="50" name="content" >${param.content }</textarea>
				</td>
			</tr>
			<tr>
				<td align="right">用户编号</td>
				<td colspan="2">
					<textarea rows="20" cols="50" name="reciverUserno" id="reciverUserno">${param.reciverUserno }</textarea><br/>
					<font color="red">每个用户间以逗号分隔</font>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
				<input type="submit" value=" 赠 送 "	class="inputButton">
				</td>
			</tr>
</form>
</body>
</html>