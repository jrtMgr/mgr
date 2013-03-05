<%@ page language="java" contentType="text/html; charset=utf-8"%>
<jsp:directive.page import="com.ruyicai.mgr.domain.Tgiftaudit" />
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<html>
<head>
<link href="<%=request.getContextPath()%>/styles/default.css" rel="stylesheet" type="text/css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<title>审核列表页面</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Dialog.js"></script>	
<script type="text/javascript">	
<% 
String errormsg = (String)request.getAttribute("errormsg");
if(!StringUtil.isEmpty(errormsg)) {
%>
function showerror() {
	alert("<%=errormsg%>");
	
}
$(document).ready(function() {
	showerror();
});
<%	
}
%>	
function deletec(id){
	if(!window.confirm("确定撤销吗?")) {
		return;
	}
	var url = "<%=request.getContextPath()%>/batchgiftAudit/doAudit?id="+id+"&flag=-1";
	window.location.href = url;
}
function sure(id){
	if(!window.confirm("确定审核成功吗?")) {
		return;
	}
	var url = "<%=request.getContextPath()%>/batchgiftAudit/doAudit?id="+id+"&flag=1";
	window.location.href = url;
}
</script>
</head>
<body>
	<table class="dataTable" width="60%">
		<tr class="dataTableHead" >
			<td class="thOver" style="border-top: 0px;" width="100px">编号</td>
			<td class="thOver" style="border-top: 0px;">赠送创建时间</td>
			<td class="thOver" style="border-top: 0px;">大客户编号</td>
			<td class="thOver" style="border-top: 0px;">大客户名称</td>
			<td class="thOver" style="border-top: 0px;">总需金额</td>
			<td class="thOver" style="border-top: 0px;">操作</td>
		</tr>
		<c:forEach items="${list}" var="tgiftaudit" varStatus="num">
			<tr>
				<td width="10%">${num.count}</td>
				<td><fmt:formatDate value="${tgiftaudit.agetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${tgiftaudit.biguserno}</td>
				<td>${tgiftaudit.bigname}</td>
				<td>${tgiftaudit.allamt/100} 元</td>
				<td><a style="cursor: hand;" onclick="sure('${tgiftaudit.id}')">审核通过</a>|
				<a style="cursor: hand;" onclick="deletec('${tgiftaudit.id}')">撤销</a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>