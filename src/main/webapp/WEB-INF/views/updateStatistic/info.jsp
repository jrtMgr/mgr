<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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

function onsubmitfun() {
	if($.trim($("#key").val()) == "") {
		Dialog.alert("请输入KEY");
		return false;
	} 
	return true;
}

function onsubmitfun() {
	if($.trim($("#event").val()) == "") {
		Dialog.alert("请输入event");
		return false;
	} 
	return true;
}
</script>	
</head>
<body style="margin-left: 10px;">
<form action="<%=request.getContextPath()%>/updateStatistic/update" method="post" onsubmit="return onsubmitfun();">
<table width="30%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="3"><strong>刷新统计</strong></td>
			</tr>
			<tr>
				<td align="right">
					KEY:&nbsp;
				</td>
				<td colspan="2">
					<input name="key" type="text" style="width: 120px" id="key"
								class="inputText" onfocus="this.select();" />
				</td>
				</tr>
			<tr>
				<td colspan="3" align="center">
				<input type="submit" value=" 刷 新 "
								class="inputButton">
				</td>
			</tr>
			<tr><td colspan="3">&nbsp;</td></tr>
			<tr><td colspan="3" align="center"><a href="http://jrtcms.ruyicai.cn/WebWithoutLogin/tools!jspToHtmlAllTime">生成开奖公告的请求地址</a></td></tr>
		</tbody>
	</table>
	</form>
	
	<form action="<%=request.getContextPath()%>/updateStatistic/jingcaicancel" method="post" onsubmit="return onsubmitfun2();">
	<table width="30%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="3"><strong>取消竞彩</strong></td>
			</tr>
			<tr>
				<td align="right">
					event:&nbsp;
				</td>
				<td colspan="2">
					<input name="event" type="text" style="width: 120px" id="key"
								class="inputText" onfocus="this.select();" />
				</td>
				</tr>
			<tr>
				<td colspan="3" align="center">
				<input type="submit" value=" 确定 "
								class="inputButton">
				</td>
			</tr>
			<tr><td colspan="3">&nbsp;</td></tr>
		</tbody>
	</table>
	</form>
</body>
</html>