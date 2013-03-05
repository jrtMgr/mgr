<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.domain.Tmenu"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.ruyicai.mgr.domain.Tpermission"%>
<%@page import="java.util.List"%>
<%@page import="com.ruyicai.mgr.domain.Tloguser"%>
<%@page import="com.ruyicai.mgr.consts.UserState"%>
<%@page import="com.ruyicai.mgr.domain.Tuserinfo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</script>
</head>
<%!public boolean ispermission(BigDecimal menuinid, HttpServletRequest request) {
		List<Tpermission> tpermissions = (List<Tpermission>)request.getAttribute("permission");
		if (null != tpermissions) {
			for (Tpermission tpermission : tpermissions) {
				if (tpermission.getMenuinid().equals(menuinid)) {
					return true;
				}
			}
		}
		return false;
	}
	%>
<body>
	<table width="50%" cellspacing="0" cellpadding="0">
		<tr>
			<td	style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
				<form action="<%=request.getContextPath()%>/tpermissions/edit" method="post">
				<input type="hidden" name="userid" value="${tloguser.id}">
				<table width="100%" cellspacing="0" cellpadding="2"	class="dataTable">
					<tbody>
						<tr class="dataTableHead">
							<td class="thOver" colspan="2"><strong>权限设置</strong></td>
						</tr>
						<c:forEach items="${menulist}" var="menu" varStatus="num">
								<%
									Tmenu tmenu = (Tmenu) pageContext.getAttribute("menu");
								%>
							<tr>
								<td width="5%" align="right"><input type="checkbox" name="menuinid"
									<%=ispermission(tmenu.getInid(), request) ? "checked='checked'"	: ""%> value="${menu.inid}">
								</td>
								<td width="20%" align="left">${menu.name}</td>
							
							</tr>
						</c:forEach>
						<tr>
							<td colspan="2" align="center"><input type="submit" value="保存" class="inputButton">
							<input type="button" value="返回" class="inputButton" onclick="javascript:history.go(-1);">
							</td>
						</tr>
					</tbody>
				</table>
				</form>
			</td>
		</tr>
		</tbody>
	</table>
</body>
</html>