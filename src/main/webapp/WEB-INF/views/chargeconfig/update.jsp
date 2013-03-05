<%@page import="com.ruyicai.mgr.consts.TlotCtrlState"%>
<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.domain.Tlotctrl"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
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
			String errormsg = (String) request.getAttribute("errormsg");
			if (!StringUtil.isEmpty(errormsg)) {%>
function showerror() {
	Dialog.alert("<%=errormsg%>");
}
$(document).ready(function() {
	showerror();
});
<%}%>	
</script>
<body style="margin: 0;padding: 0">
<div style="margin-top: 10px;"></div>
	<table width="120%" cellspacing="0" cellpadding="0" border="0" >
		<form name="submitForm" action="<%=request.getContextPath()%>/chargeconfig/update"	method="post">
		<input type="hidden" name="id" id="id" value="${c.id }"/>
			<div style="float: left;">
				<table width="35%" cellspacing="2" cellpadding="2" border="0" class="dataTable">
				<tr><td colspan="2">修改</td></tr>
				<tr>
					<td colspan="2">键:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="merid" id="merid" value="${c.id}" readonly="readonly"></input></td>
				</tr>
				<tr>
					<td colspan="2">备注:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="memo" id="memo" value="${c.memo}" style="width:300px;"></input></td>
				</tr>
				<tr>
					<td colspan="2">值:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="value" id="value" value="${c.value}" style="width: 300px;"></input></td>
				</tr>
				<tr><td colspan="2" align="center"><input type="submit" value="提交"/></td></tr>
				</table>
			</div>
		</form>
	</table>
</body>
</html>