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
<form action="<%=request.getContextPath()%>/suser/edit" method="post" id="form1">
	<input type="hidden" name="id" value="${user.id}"/>
	<table width="50%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="2" align="center"><strong>修改</strong>
				</td>
			</tr>
			<tr>
				<td align="right">角色:</td>
				<td><select name="roleid" style="width:100px">
						<c:forEach var="role" items="${roles}">
							<option value="${role.id}" <c:if test="${role.id eq user.roleid}">selected</c:if>>${role.name}</option>
						</c:forEach>
				</select>
			</tr>
			<tr>
				<td align="right">登录名: </td>
				<td><input type="text" name="name" value="${user.name}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td align="right">密码: </td>
				<td><input type="text" name="pass" value=""/><font color="red"> 为空则不更新密码</font></td>
			</tr>
			<tr>
				<td align="right">真实姓名:  </td>
				<td><input type="text" name="realname" value="${user.realname}"/></td>
			</tr>
			<tr>
				<td align="right">联系方式: </td>
				<td><input type="text" name="tel" value="${user.tel}"/></td>
			</tr>
			<tr>
				<td align="right">备注:  </td>
				<td><input type="text" name="bz" value="${user.bz}"/></td>
			</tr>
			<tr>
				<td align="right">状态:  </td>
				<td><select name="status" style="width:60px">
						<option value="1" <c:if test='${user.status eq 1}'>selected</c:if> >可用</option>
						<option value="0" <c:if test='${user.status eq 0}'>selected</c:if>>不可用</option>
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
