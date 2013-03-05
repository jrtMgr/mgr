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
	if(!window.confirm("确定添加吗?")) {
		return;
	}
	document.forms[0].submit();
}

</script>	
</head>
<body>
<form action="<%=request.getContextPath()%>/msgmonitor/add" method="post" id="form1">
	<table width="50%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="2" align="center"><strong>添加监控</strong>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">采种：</td>
				<td align="left">
					<select name="lotno" style="width:100px">
						<option value="">---请选择--</option>
						<c:forEach var="lot" items="<%=com.ruyicai.mgr.lot.Lottype.getMap().entrySet() %>">
							<option value="${lot.key}">${lot.value}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">监控类别:</td>
				<td><select name="type" style="width:100px">
						<option value="">---请选择--</option>
						<option value="0" >开奖后派奖</option>
						<option value="1" >开期后开奖</option>
						<option value="2" >监控结期</option>
						<option value="3" >投注后出票</option>
						
				</select>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">超时时间：</td>
				<td align="left"><input name="time" style="width: 120px" class="inputText" onfocus="this.select();"/></td>
			</tr>
		</tbody>
	</table>
	<table width="45%" cellspacing="0" cellpadding="2"	style="margin-top: 10px">
		<tr>
			<td align="center"><input type="button" value="提交 " class="inputButton" onclick="submint1()">&nbsp;&nbsp;<input type="button" value="返回  " class="inputButton" onclick="javascript:history.go(-1);"></td>
		</tr>
	</table>
</form>
</body>
</html>
