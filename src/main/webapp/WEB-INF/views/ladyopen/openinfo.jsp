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
function confirmSave() {
	Dialog.confirm("彩种编号:" + $("#lotno").val() + ", 期号:" + $("#batchcode").val() + "<br/>基本号码:" +  $("#winbasecode").val() + "<br/>特殊号码:" + $("#winspecialcode").val() + "<br/>确定要保存吗?", function() {
		$("#form1").submit();
	}, function(){}, 300, 170);
	return false;
}
</script>	
</head>
<body>
<form action="<%=request.getContextPath()%>/ladyopen/opensave" method="post" id="form1" onsubmit="return confirmSave();">

<table width="100%" class="dataTable">
		<tr class="dataTableHead">
			<td height="30" class="thOver" colspan="2"><strong>基本信息</strong>
			</td>
		</tr>
		<tr>
			<td align="right" width="20%">彩种编号:</td>
			<td ><input type="text" name="lotno" id="lotno" value="${twininfo.id.lotno}" readonly="readonly"></td>
		</tr>
		<tr>
			<td align="right" width="220%">期号:</td>
			<td ><input type="text" name="batchcode" id="batchcode" value="${twininfo.id.batchcode}" readonly="readonly"></td>
		</tr>
			<tr>
				<td align="right" width="20%">基本号码:</td>
				<td ><input name="winbasecode" id="winbasecode" type="text" style="width: 150px"
		class="inputText" onfocus="this.select();" value="${twininfo.winbasecode}" /></td>
		</tr>
		<tr>
			<td align="right" width="20%">特殊号码:</td>
			<td align="left"><input name="winspecialcode" id="winspecialcode" type="text" style="width: 80px"
	class="inputText" onfocus="this.select();" value="${twininfo.winspecialcode}" /></td>
		</tr>
		<tr>
			<td align="center" colspan="2"><input type="submit" value=" 保存 " class="inputButton"></td>
		</tr>
		</table>
		</form>
</body>
</html>