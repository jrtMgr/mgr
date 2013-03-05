<%@page import="com.ruyicai.mgr.util.Page"%>
<%@page import="java.util.List"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.domain.statis.User"%>
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
function deleteyw(id){
	if(!window.confirm("确定删除吗?")) {
		return;
	}
	var url = '<%=request.getContextPath()%>/yw/deleteyw?id='+id;
	window.location.href = url;
}
</script>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td	style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2"	class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="4%" height="30" class="thOver"></td>
								<td width="8%" class="thOver"><strong>ID</strong>
								</td>
								<td width="12%" class="thOver"><strong>业务代码</strong>
								</td>
								<td width="12%" class="thOver"><strong>业务名</strong>
								</td>
								<td width="12%" class="thOver"><strong>创建人ID</strong>
								</td>
								<td width="12%" class="thOver"><strong>创建时间</strong>
								</td>
								<td width="12%" class="thOver"><strong>备注</strong>
								</td>
								<td width="12%" class="thOver"><strong>操作</strong>
								</td>
							</tr>
							<c:forEach items="${yws}" var="yw" varStatus="num">
								<tr>
									<td>${num.count}</td>
									<td>${yw.id}</td>
									<td>${yw.code}</td>
									<td>${yw.name}</td>
									<td>${yw.bduserid}</td>
									<td>${yw.cjdate}</td>
									<td>${yw.bz}</td>
									<td>
										<a style="cursor: hand;" onclick="deleteyw('${yw.id}')">删除</a> |
										<a href="<%=request.getContextPath()%>/yw/editUI?id=${yw.id}">修改</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
			
			<tr><td style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px; font-size: 14px;"><a href="<%=request.getContextPath()%>/yw/addUI">新增</a></td></tr>
		</tbody>
	</table>
</body>
</html>