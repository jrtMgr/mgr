<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.domain.Twininfo"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
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
<form action="<%=request.getContextPath()%>/biguser/processCharge" method="post" id="form1" onsubmit="return window.confirm('确定要充值吗?')">
	<table width="40%" cellspacing="0" class="dataTable">
		<tbody>
		<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="2" align="center"><strong>大客户充值</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="10%">合作商编号:</td>
				<td width="40%">
					<input name="agencyno" type="text" style="width: 120px" class="inputText" onfocus="this.select();" value="${agencyno}" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">合作商名称:</td>
				<td>
					<input type="text" style="width: 120px" class="inputText" onfocus="this.select();" value="${agencyname}" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">账户余额（元）:</td>
				<td>
					<input  type="text" style="width: 120px"  class="inputText" onfocus="this.select();" value="${balance}" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">充值金额（元）:</td>
				<td>
					<input name="money" type="text" style="width: 120px" class="inputText" onfocus="this.select();"/>
				</td>
			</tr>
		</tbody>
	</table>
	<table width="40%" cellspacing="0" cellpadding="2"	style="margin-top: 10px">
		<tr>
			<td align="center"><input type="submit" value="充值 " class="inputButton">&nbsp;&nbsp;<input type="button" value="返回 " class="inputButton" onclick="javascript:history.go(-1);"></td>
		</tr>
	</table>
</form>
</body>
</html>
