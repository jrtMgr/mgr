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
					<form action="<%=request.getContextPath()%>/msgmonitor/list"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">彩种:</td>
									<td><select name="lotno" style="width:100px">
											<option value="">全部</option>
											<c:forEach var="lot" items="<%=com.ruyicai.mgr.lot.Lottype.getMap().entrySet() %>">
												<option value="${lot.key}" <c:if test="${lot.key eq param.lotno}">selected</c:if>>${lot.value}</option>
											</c:forEach>
									</select>
									</td>
									
									<td align="right">监控类别:</td>
									<td><select name="type" style="width:100px">
											<option value="">全部</option>
											<option value="0" <c:if test="${0 == param.type}">selected</c:if>>开奖后派奖</option>
											<option value="1" <c:if test="${1 == param.type}">selected</c:if>>开期后开奖</option>
											<option value="2" <c:if test="${2 == param.type}">selected</c:if>>监控结期</option>
											<option value="3" <c:if test="${3 == param.type}">selected</c:if>>投注后出票</option>
											
									</select>
									</td>
									<td align="right">状态:</td>
									<td><select name="state" style="width:100px">
											<option value="">全部</option>
											<option value="0" <c:if test="${0 == param.state}">selected</c:if>>启用</option>
											<option value="1" <c:if test="${1 == param.steat}">selected</c:if>>停用</option>
											
									</select>
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
								<td width="5%" class="thOver"><strong>彩种编号</strong>
								</td>
								<td width="6%" class="thOver"><strong>监控类别</strong>
								</td>
								<td width="5%" class="thOver"><strong>状态</strong>
								</td>
								<td width="9%" class="thOver"><strong>超时时间(毫秒)</strong>
								</td>
								<td width="6%" class="thOver"><strong>操作</strong>
								</td>
							</tr>
							<c:forEach items="${msgMonitors}" var="msgMonitor" varStatus="num">
								<tr>
									<td title="${num.count}">${num.count}</td>
									<td >${msgMonitor.id.lotno}</td>
									<td>
										<c:if test="${0 == msgMonitor.id.type}">开奖后派奖</c:if>
										<c:if test="${1 == msgMonitor.id.type}">开期后开奖</c:if>
										<c:if test="${2 == msgMonitor.id.type}">监控结期</c:if>
										<c:if test="${3 == msgMonitor.id.type}">投注后出票</c:if>
									</td>
									<td title="${msgMonitor.state}">
										<c:if test="${0 == msgMonitor.state}">启用</c:if>
										<c:if test="${1 == msgMonitor.state}">停用</c:if>
									</td>
									<td title="${msgMonitor.time}">${msgMonitor.time} | ${msgMonitor.time/60000}分钟</td>
									<td>
										<a href="<%=request.getContextPath()%>/msgmonitor/editUI?lotno=${msgMonitor.id.lotno}&type=${msgMonitor.id.type}">修改</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
			
			<tr><td style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px; font-size: 14px;"><a href="<%=request.getContextPath()%>/msgmonitor/addUI">新增</a></td></tr>
		</tbody>
	</table>
</body>
</html>