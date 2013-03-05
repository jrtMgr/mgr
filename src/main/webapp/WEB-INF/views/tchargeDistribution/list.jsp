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
		<form name="submitForm" action="<%=request.getContextPath()%>/tchargeDistribution/distribution"
			method="post">
			<div style="float: left;">
				<table width="35%" cellspacing="2" cellpadding="2" border="0" class="dataTable">
				<tr><td colspan="2">手机充值卡权重分配</td></tr>
				<tr><td colspan="2">当前神州付：${shenzhoufuWeight}，19pay：${nineteenpayWeight}</td></tr>
				<tr>
					<td>19pay:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="nineteenpay" id="nineteenpay" /></td>
					<td>神州付:&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="shenzhoufu" id="shenzhoufu" /></td>
				</tr>
				<tr><td colspan="2" align="center"><input type="submit" value="提交"/></td></tr>
				<tr><td colspan="2"><font color="red">ps:填写整数</font></td></tr>
				</table>
			</div>
		</form>
	</table>
</body>
</html>