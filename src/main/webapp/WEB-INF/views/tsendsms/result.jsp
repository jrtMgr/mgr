<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript">
	
<%String errormsg = (String) request.getAttribute("errormsg");
			if (!StringUtil.isEmpty(errormsg)) {%>
	function showerror() {
		Dialog.alert
("<%=errormsg%>");
	}
	$(document).ready(function() {
		showerror();
	})
;
<%}%>
	
</script>
</head>
<body style="margin-left: 10px;">
	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="4"><strong>发送结果</strong>
				</td>
			</tr>
			<tr>
				<td>成功：</td>
				<td>${success}</td>
				<td>失败：</td>
				<td>${fail}</td>
			</tr>
		</tbody>
	</table>
	<table width="30%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver"><strong>失败手机号码</strong>
				</td>
			</tr>
			<c:forEach items="${faillist}" var="m">
				<tr>
					<td>${m}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>