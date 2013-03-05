<%@page import="org.json.JSONObject"%>
<%@page import="com.ruyicai.mgr.util.DateUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
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
	window.opener.location="<%=request.getContextPath() %>/caselot/caselotListMg";
	window.close();
	
}
$(document).ready(function() {
	showerror();
});
<%	
}
%>	
</script>
</head>
<body style="margin-left: 10px; font-size: 12px;">
	<form action="<%=request.getContextPath()%>/caselot/updateDescription" method="post" onsubmit="return confirm('确认保存?')">
	<input type="hidden" value="${param.id }" name="id" id="id"/>
	<table border="0px" style="margin-top: 2px; margin-left: 30px; margin-bottom:20px;" class="dataTable" >
			<tr>
				<td align="right">合买id:</td>
				<td><input type="text" name="caseid" value="${param.c}"> </td>
			</tr>
			<tr>
				<td align="right">方案描述：</td>
				<td><textarea rows="6" cols="35" name="description" id="description" >${d}</textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="&nbsp保&nbsp存&nbsp"/> </td>
			</tr>
	</table>
	</form>
</body>
</html>