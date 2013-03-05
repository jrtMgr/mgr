<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.domain.Twininfo"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>合作商增加</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/Dialog.js"></script>
<script type="text/javascript">	
<%String errormsg = (String) request.getAttribute("errormsg");
			if (!StringUtil.isEmpty(errormsg)) {%>
function showerror() {
	Dialog.alert("<%=errormsg%>");
}
$(document).ready(function() {
	showerror();
});
<%}%>	
function submint1(){
	if(!window.confirm("确定要添加吗?")) {
		return;
	}
	
	document.forms[0].submit();
}
</script>
</head>
<body>
	<form action="<%=request.getContextPath()%>/biguser/addip" method="post">
		<table width="33%" cellspacing="0" cellpadding="2" class="dataTable"
			style="margin-left: 50px; margin-top: 20px;">
			<tbody>
				<tr class="dataTableHead">
					<td height="30" class="thOver" colspan="3" align="center"><strong>添加ip</strong>
					</td>
				</tr>
				<tr>
					<td align="right">渠道编号：</td>
					<td colspan="2"><input name="agencyno" id="agencyno" type="text" class="inputText" onfocus="this.select();" value="${param.agencyno}" /></td>
				</tr>
				<tr>
					<td align="right">ip地址：</td>
					<td colspan="2"><input name="ipaddr" id= 'ipaddr' type="text" class="inputText" onfocus="this.select();" /></td>
				</tr>
			</tbody>
		</table>
		<table width="45%" cellspacing="0" cellpadding="2"
			style="margin-top: 10px">
			<tr>
				<td align="center" colspan="3"><input type="submit" value="提交 "
					class="inputButton" >&nbsp;&nbsp;<input
					type="button" value="返回  " class="inputButton"
					onclick="javascript:history.go(-1);">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
