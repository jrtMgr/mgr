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
	if(!window.confirm("确定要修改吗?")) {
		return;
	}
	document.forms[0].submit();
}
</script>	
</head>
<body>
<form action="<%=request.getContextPath()%>/lssm/editTI" method="post" id="form1">
<input type="hidden" name="id" value="${ti.id}"></input>
	<table width="50%" class="dataTable">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="2" align="center"><strong>修改</strong>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">开始时间：</td>
				<td align="left"><input name="startHour" id="startHour" type="text" style="width: 120px"
				 class="inputText" onfocus="this.select();" value="${ti.startHour}"/></td>
			</tr>
			<tr>
				<td height="30" align="right">结束时间；</td>
				<td align="left"><input name="endHour" id="endHour" type="text" style="width: 120px" 
				class="inputText" onfocus="this.select();" value="${ti.endHour}"/></td>
			</tr>
			<tr>
				<td height="30" align="right">间隔时间（分钟）：</td>
				<td align="left"><input name="frequency" id="frequency" type="text" style="width: 120px" 
				class="inputText" onfocus="this.select();" value="${ti.frequency/60000}"/></td>
			</tr>
			<tr>
				<td height="30" align="right">手机号码：</td>
				<td align="left"><input name="mobileNo" id="mobileNo" type="text" style="width: 120px" 
				class="inputText" onfocus="this.select();" value="${ti.mobileNo}"/></td>
			</tr>
		</tbody>
	</table>
	<table width="50%" cellspacing="0" cellpadding="2"	style="margin-top: 10px">
		<tr>
			<td align="center"><input type="button" value="保存 " class="inputButton" onclick="submint1()">&nbsp;&nbsp;
			<input type="button" value="返回  " class="inputButton" onclick="javascript:history.go(-1);"></td>
		</tr>
	</table>
</form>
</body>
</html>
