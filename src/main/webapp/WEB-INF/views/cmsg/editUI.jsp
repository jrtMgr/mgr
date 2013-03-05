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
	window.opener.location="<%=request.getContextPath() %>/cmsg/mpage";
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
	<form action="<%=request.getContextPath()%>/cmsg/reply" method="post" onsubmit="return confirm('确认保存?')">
	<input type="hidden" value="${param.id }" name="id" id="id"/>
	<table border="0px" style="margin-top: 2px; margin-left: 30px; margin-bottom:20px;" class="dataTable" >
			<tr>
				<td align="right">留言内容：</td>
				<td>${content}</td>
			</tr>
			<tr>
				<td align="right">回复内容：</td>
				
				<td><textarea rows="6" cols="35" name="reply" id="reply" >${reply.value.reply }</textarea></td>
			</tr>
			<tr>
				<td align="right">备注：</td>
				<td><input type="text" id="bz" name="bz" value="${reply.value.bezu }"/></td>
			</tr>
			<tr>
				<td align="right">回复时间：</td>
				<td >
					<%
						Map m =(Map)request.getAttribute("reply");
						if(m!=null){
							Map mm = (Map)m.get("value");
							if(mm!=null){
								Long l = (Long)mm.get("createtime");
								out.print(DateUtil.format(new Date(l)));
							} 
						}
				%>
			</tr>
			<tr>
				<td align="right">回复人：</td>
				<td>${reply.value.username}</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="&nbsp保&nbsp存&nbsp"/> </td>
			</tr>
	</table>
	</form>
</body>
</html>