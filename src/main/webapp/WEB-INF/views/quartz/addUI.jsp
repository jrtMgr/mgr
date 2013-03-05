<%@page import="com.ruyicai.mgr.controller.AgencyBalanceController"%>
<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.domain.Twininfo"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
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
function submint1(){
	if(!window.confirm("确定添加吗?")) {
		return;
	}
	document.forms[0].submit();
}

</script>	
</head>
<body>
<form action="<%=request.getContextPath()%>/quartz/add" method="post" id="form1">
	<table width="50%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="2" align="center"><strong>新增</strong>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">url：</td>
				<td align="left">
					<input name="url" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.url}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">param：</td>
				<td align="left">
					<input name="param" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.param}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">exp：</td>
				<td align="left">
					<input name="exp" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.exp}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">name：</td>
				<td align="left">
					<input name="name" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.name}"/>
				</td>
			</tr>
		</tbody>
	</table>
	<table width="45%" cellspacing="0" cellpadding="2"	style="margin-top: 10px">
		<tr>
			<td align="center"><input type="button" value="提交 " class="inputButton" onclick="submint1()">&nbsp;&nbsp;<input type="button" value="返回  " class="inputButton" onclick="javascript:location.href='<%=request.getContextPath()%>/tjingcaiMatches/page';"></td>
		</tr>
	</table>
</form>
</body>
</html>
