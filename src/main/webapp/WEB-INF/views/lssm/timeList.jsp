<%@page import="com.ruyicai.mgr.domain.TimeInterval"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>时间</title>
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript">
<% 
String errormsg = (String)request.getAttribute("errormsg");
if(!StringUtil.isEmpty(errormsg)) {
%>
function showerror() {
	alert("<%=errormsg%>");
	//Dialog.alert("<%=errormsg%>");
}
<%	
}
%>	

function _delete(id){
	if(!window.confirm("确定要删除吗?")) {
		return;
	}
	location.href="<%=request.getContextPath()%>/lssm/deleteTI?id="+id;
}
</script>
</head>
<body onload="javascript:showerror()">
<table width="100%" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td style="border:0">
			<table width="60%" cellspacing="0" cellpadding="0" border="0" class="dataTable">
				<tr class="dataTableHead">
					<td class="thOver" style="border-top: 0px;">开始时间</td>
					<td class="thOver" style="border-top: 0px;">结束时间</td>
					<td class="thOver" style="border-top: 0px;">超时时间（分钟）</td>
					<td class="thOver" style="border-top: 0px;">手机号码（多个用","分割）</td>
					<td class="thOver" style="border-top: 0px;">修改</td>
				</tr>
				<% 
					List<TimeInterval> list = (List<TimeInterval>)request.getAttribute("list");
					for(TimeInterval t : list) {
				%>
				<tr>
					<td title="<%=t.getStartHour() %>"><%=t.getStartHour() %></td>
					<td title="<%=t.getEndHour() %>"><%=t.getEndHour() %></td>
					<td title="<%=t.getFrequency()%>"><%=t.getFrequency()/60000L%></td>
					<td title="<%=t.getFrequency()%>"><%=t.getMobileNo()%></td>
					<td><a href="<%=request.getContextPath()%>/lssm/editTIUI?id=<%=t.getId()%>">修改</a>|<a onclick="_delete(<%=t.getId()%>)">删除</a></td>
				</tr>
				<%} %>
			</table>
		</td>
	</tr>
	<tr><td><a href="<%=request.getContextPath()%>/lssm/addTIUI">新增</a></td></tr>
</table>
</body>
</html>