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


function hnfcfpamt(){
	if(!window.confirm("确定修改吗?")) {
		return;
	}
	var amt = document.getElementById("amt");
	var url = "<%=request.getContextPath()%>/tlotstrategy/hnfcfpamt?amt="+amt.value;
	window.location.href = url;
}
function hnfcfpqy(){
	if(!window.confirm("确定启用吗?")) {
		return;
	}
	var url = "<%=request.getContextPath()%>/tlotstrategy/hnfcfpqy";
	window.location.href = url;
}
function hnfcfpsc(){
	if(!window.confirm("确定删除吗?")) {
		return;
	}
	var url = "<%=request.getContextPath()%>/tlotstrategy/hnfcfpsc";
	window.location.href = url;
}

</script>	
</head>
<body style="margin-left: 10px;">
<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tr>
			<td colspan="6">分配</td>
		</tr>
		<tr class="dataTableHead" >
			<td class="thOver" style="border-top: 0px;">采种</td>
			<td class="thOver" style="border-top: 0px;">彩票中心</td>
			<td class="thOver" style="border-top: 0px;" width="200px;" colspan="2">金额（分）</td>
			<td class="thOver" style="border-top: 0px;" >状态</td>
			<td class="thOver" style="border-top: 0px;">操作</td>
		</tr>
		<tr>
			<td>${tlotstrategy.lotno}</td>
			<td>${tlotstrategy.agencyno}</td>
			<c:if test="${tlotstrategy.state == 1}">
			<td colspan="2">
				<input id="amt" type="text" value="${tlotstrategy.amt}" style="width: 80px;"/>
				<input type="button" value="修改" onclick="javascript:hnfcfpamt()"/>
			</td>
			<td>启用</td>
			<td><a onclick="hnfcfpsc()" style="cursor: hand;">停用</a></td>
			</c:if>
			<c:if test="${tlotstrategy.state == 0}">
			<td colspan="2">
				${tlotstrategy.amt}
			</td>
			<td>停用</td>
			<td><a onclick="hnfcfpqy()" style="cursor: hand;">启用</a></td>
			</c:if> 
		</tr>
	</table>
</html>