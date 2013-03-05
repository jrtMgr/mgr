<%@page import="com.ruyicai.mgr.domain.Tcashrecord"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
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

	function exportExcel() { 
		window.location="<%=request.getContextPath() %>/tcashCW/exportExcel";
	} 

</script>
</head>
<body>
<table class="dataTable" width="60%">
	<tr class="dataTableHead" >
		<td class="thOver" style="border-top: 0px;" width="100px">编号</td>
		<td class="thOver" style="border-top: 0px;">导出时间</td>
		<td class="thOver" style="border-top: 0px;">金额（分）</td>
		<td class="thOver" style="border-top: 0px;">详细</td>
	</tr>
	<%
	Page<Tcashrecord> page2 = (Page<Tcashrecord>)request.getAttribute("page");
	if(page2.getList().size()!=0) {
		%>
		
		<c:forEach items="${page.list}" var="tcr" varStatus="num">
			<tr>
				<td width="10%">${num.count}</td>
				<td title="${tcr.dcdate}"><fmt:formatDate value="${tcr.dcdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td width="10%">${tcr.amt}</td>
				<td><a href="<%=request.getContextPath()%>/tcashCW/tcashrecordcfg?tcashrecordid=${tcr.id}">详细</a>
				<a href="<%=request.getContextPath()%>/tcashCW/exportExcelagain?tcashrecordid=${tcr.id}">重新导出</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td align="left" id="dg1_PageBar" colspan="3"><div
					style="float: right; font-family: Tahoma">
					<c:choose>
						<c:when test="${page.pageIndex != 0}">
							<a	href="<%=request.getContextPath()%>/tcashCW/page?maxResult=${page.maxResult}&pageIndex=0">第一页</a>
						</c:when>
						<c:otherwise>
					第一页
				</c:otherwise>
					</c:choose>
					|&nbsp;
					<c:choose>
						<c:when test="${page.pageIndex > 0}">
							<a href="<%=request.getContextPath()%>/tcashCW/page?maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
						</c:when>
						<c:otherwise>
					上一页
				</c:otherwise>
					</c:choose>
					&nbsp;|&nbsp;
					<c:choose>
						<c:when test="${page.pageIndex + 1 < page.totalPage}">
							<a href="<%=request.getContextPath()%>/tcashCW/page?maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
						</c:when>
						<c:otherwise>
					下一页
				</c:otherwise>
					</c:choose>
					&nbsp;|&nbsp;
					<c:choose>
						<c:when test="${page.pageIndex + 1 != page.totalPage}">
							<a href="<%=request.getContextPath()%>/tcashCW/page?tmaxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
						</c:when>
						<c:otherwise>
					最末页
				</c:otherwise>
					</c:choose>
					&nbsp;|&nbsp; &nbsp;&nbsp;转到第&nbsp;<input type="text"
						onkeyup="value=value.replace(/\D/g,'')" style="width: 40px"
						class="inputText" id="_PageBar_Index">&nbsp;页&nbsp;&nbsp;<input
						type="button" value="跳转" class="inputButton" onclick="go()">
					<script type="text/javascript">
						function go() {
							var pageindex = parseInt($("#_PageBar_Index").val()) - 1;
							window.location.href="<%=request.getContextPath()%>/tcashCW/page?maxResult=${page.maxResult}&pageIndex=" + pageindex;
						}
					</script>
				</div>
				<div style="float: left; font-family: Tahoma">共
					${page.totalResult} 条记录，每页 ${page.maxResult} 条，当前第
					${page.pageIndex + 1} / ${page.totalPage} 页</div></td>
		</tr>
	<%} %>
	<tr style="height: 80px;">
		<td align="left" ><input type="button" value="提现信息导出" onclick="javascript:exportExcel();"></td>
	</tr>
</table>
</body>
</html>