<%@page import="com.ruyicai.mgr.domain.Tuserloginfo"%>
<%@page import="com.ruyicai.mgr.controller.OpentimeController"%>
<%@page import="java.util.Map"%>
<%@page import="com.ruyicai.mgr.lot.LotCenter"%>
<%@page import="com.ruyicai.mgr.domain.Twininfo"%>
<%@page import="com.ruyicai.mgr.consts.TlotCtrlState"%>
<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.domain.Tlotctrl"%>
<%@page import="java.util.List"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</head>
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

</script>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
		<tr>
				<td style="padding: 2px 10px;">
					<form action="<%=request.getContextPath()%>/userloginfo/list"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">用户编号:</td>
									<td><input type="text" name="userno" id="userno" >
									</td>
									<td>&nbsp;&nbsp;&nbsp;<input type="submit" value="查询" class="inputButton">
									</td>
								</tr>
							</table>
						</div>
					</form>
				</td>
			</tr>
			<tr>
				<td	style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2"	class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="2%" height="30" class="thOver"></td>
								<td width="6%" class="thOver"><strong>用户编号</strong>
								</td>
								<td width="6%" class="thOver"><strong>random</strong>
								</td>
								<td width="6%" class="thOver"><strong>accesstype</strong>
								</td>
								<td width="9%" class="thOver"><strong>createtime</strong>
								</td>
							</tr>
							<%
								List<Tuserloginfo> list = (List<Tuserloginfo>) request.getAttribute("list");
								if (null != list) {
							%>
							<c:forEach items="${list}" var="uli" varStatus="num">
								<tr>
									<td title="${num.count}">${num.count}</td>
									<td>${uli.userno}</td>
									<td>${uli.random}</td>
									<td>${uli.accesstype}</td>
									<td><fmt:formatDate value="${uli.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								</tr>
							</c:forEach>
							<% } %>
						</tbody>
					</table>
				</td>
			</tr>
			<tr>
				<td>
				<div style="margin: 2px">
				<form action="<%=request.getContextPath()%>/userloginfo/deleteByuserno" style="margin: 2px;">
					按userno&nbsp;&nbsp;<input type="text" name="userno" />&nbsp;&nbsp;<input type="submit" value="删除"/>
				</form>
				</div>
				</td>
			</tr>
			<tr>
				<td>
				<div style="margin: 2px">
				<form action="<%=request.getContextPath()%>/userloginfo/deleteByday" style="margin: 2px;">
					删除几天前的数据&nbsp;&nbsp;<input type="text" name="day" />&nbsp;&nbsp;<input type="submit" value="删除"/>
				</form>
				</div>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>