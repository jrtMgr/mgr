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
function deletets(id){
	if(!window.confirm("确定删除吗?")) {
		return;
	}
	var url = "<%=request.getContextPath()%>/tlotstrategy/deleteTs?id="+id;
	window.location.href = url;
	
}


function deletete(id){
	if(!window.confirm("确定删除吗?")) {
		return;
	}
	var url = "<%=request.getContextPath()%>/tlotstrategy/deleteTe?id="+id;
	window.location.href = url;
	
}

function reinit(){
	Dialog.confirm("确定吗?",function(){
		$.ajax({
			url:"<%=request.getContextPath() %>/tlotstrategy/reinit",
			type:"POST",
			success:function(data){
				if(data.errorCode == "0") {
					Dialog.alert("成功");
				}else{
					Dialog.alert("失败");
				}
			}
		});
	},function(){return false;})
}

</script>	
</head>
<body style="margin-left: 10px;">
<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tr>
			<td colspan="6">tlotstrategy</td>
		</tr>
		<tr class="dataTableHead" >
			<td class="thOver" style="border-top: 0px;" width="20px"></td>
			<td class="thOver" style="border-top: 0px;">userno</td>
			<td class="thOver" style="border-top: 0px;">channel</td>
			<td class="thOver" style="border-top: 0px;">playtype</td>
			<td class="thOver" style="border-top: 0px;">amt</td>
			<td class="thOver" style="border-top: 0px;">lotno</td>
			<td class="thOver" style="border-top: 0px;">agencyno</td>
			<td class="thOver" style="border-top: 0px;">操作</td>
		</tr>
		<c:forEach items="${list}" var="t" varStatus="num">
			<tr>
				<td width="10%">${num.count}</td>
				<td>${t.userno}</td>
				<td>${t.channel}</td>
				<td>${t.playtype}</td>
				<td>${t.amt}</td>
				<td>${t.lotno}</td>
				<td>${t.agencyno}</td>
				<td><a onclick="deletets('${t.id}')" style="cursor: hand;">删除</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="8" align="left"><a style="cursor:hand; font-size: 12px;" href="<%=request.getContextPath()%>/tlotstrategy/addUI">新增出票策略</a>
			</td>
		</tr>
	</table>
	<hr>
	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tr>
			<td colspan="6">tlotencashstrategy</td>
		</tr>
		<tr class="dataTableHead" >
			<td class="thOver" style="border-top: 0px;" width="20px"></td>
			<td class="thOver" style="border-top: 0px;">userno</td>
			<td class="thOver" style="border-top: 0px;">channel</td>
			<td class="thOver" style="border-top: 0px;">lotno</td>
			<td class="thOver" style="border-top: 0px;">amt</td>
			<td class="thOver" style="border-top: 0px;">操作</td>
		</tr>
		<c:forEach items="${elist}" var="tt" varStatus="num">
			<tr>
				<td width="10%">${num.count}</td>
				<td>${tt.userno}</td>
				<td>${tt.channel}</td>
				<td>${tt.lotno}</td>
				<td>${tt.amt}</td>
				<td><a onclick="deletete('${tt.id}')" style="cursor: hand;">删除</a></td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="6" align="left"><a style="cursor:hand; font-size: 12px;" href="<%=request.getContextPath()%>/tlotstrategy/addeUI">新增兑奖策略</a>
			</td>
		</tr>
	</table>
	<a style="cursor:hand; font-size: 12px;" onclick="reinit();">清缓存</a></body>
</html>