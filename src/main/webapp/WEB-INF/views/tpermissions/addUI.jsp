<%@page import="com.ruyicai.mgr.util.StringUtil"%>
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
<body>
	<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td
				style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
				<table width="100%" cellspacing="0" cellpadding="2"
					class="dataTable">
					<tbody>
						<tr class="dataTableHead">
							<td class="thOver"><strong>添加用户</strong></td>
							<td class="thOver"></td>
						</tr>
						<tr>
							<td style="padding: 2px 10px;">
								<form action="<%=request.getContextPath()%>/tpermissions/add"	method="post">
									<input type="hidden" name="type" value="2">
									<div style="float: left;">用户名:<input name="username" type="text" style="width: 120px" id="username"
											class="inputText" onfocus="this.select();" />
											密码:<input name="password" type="text" style="width: 120px" id="password"
											class="inputText" onfocus="this.select();" />
										<input type="submit" value="添加" class="inputButton"/>
										<input type="button" value="返回" class="inputButton" onclick="javascript:history.go(-1)"/>
									</div>
								</form>
							</td>
							<td></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
	
</body>
</html>