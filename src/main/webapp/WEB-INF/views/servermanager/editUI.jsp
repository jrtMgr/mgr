<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
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
function submint1(){
	if(!window.confirm("确定修改吗?")) {
		return;
	}
	document.forms[0].submit();
}

</script>	
</head>
<body>
<form action="<%=request.getContextPath()%>/servermanager/edit" method="post" id="form1">
	<input type="hidden" name="id" value="${sd.id}"/>
	<table width="50%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="3" align="center"><strong>修改</strong>
				</td>
			</tr>
			<tr>
				<td align="right">URL : </td>
				<td colspan="2"><input type="text" name="url" value="${sd.url}"/></td>
			</tr>
			<tr>
				<td align="right" >JMX地址 : </td>
				<td colspan="2" ><input type="text" name="jmx" value="${sd.jmx}" style="width: 300px;"/></td>
			</tr>
			<tr>
				<td align="right">备注:  </td>
				<td colspan="2"><input type="text" name="bz" value="${sd.bz}"/></td>
			</tr>
			<tr>
				<td align="right">状态:  ${sd.status}</td>
				<td colspan="2"><select name="status">
					<option value="1" <c:if test="${sd.status == 1}}">selected="selected" </c:if>>可用</option>
					<option value="0" <c:if test="${sd.status == 1}}">selected="selected" </c:if>>不可用</option>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
	<table width="45%" cellspacing="0" cellpadding="2"	style="margin-top: 10px">
		<tr>
			<td align="center"><input type="button" value="提交 " class="inputButton" onclick="submint1()">&nbsp;&nbsp;<input type="button" value="返回  " class="inputButton" onclick="javascript:history.go(-1);"></td>
		</tr>
	</table>
</form>
</body>
</html>
