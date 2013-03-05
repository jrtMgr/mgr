<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.domain.Twininfo"%>
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
<form action="<%=request.getContextPath()%>/tdnabind/edit" method="post" id="form1">
<input type="hidden" value="${tdnabind.id}" name="id"/>
	<table width="50%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="2" align="center"><strong>修改用户绑定信息</strong>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">用户名：</td>
				<td align="left">
					<input name="name" style="width: 120px" class="inputText" onfocus="this.select();" value="${tdnabind.name}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">卡号：</td>
				<td align="left"><input name="bankcardno" style="width: 120px" class="inputText" onfocus="this.select();" value="${tdnabind.bankcardno}" /></td>
			</tr>
			<tr>
				<td height="30" align="right">开户身份证：</td>
				<td align="left">
				<input name="certid" type="text" style="width: 120px" class="inputText" onfocus="this.select();" value="${tdnabind.certid}" />
				</td>
			</tr>
			<tr>
				<td height="30" align="right">身份证所在地：</td>
				<td align="left"><input name="certidaddress" type="text" style="width: 120px" class="inputText" onfocus="this.select();"  value="${tdnabind.certidaddress}" /></td>
			</tr>
			<tr>
				<td height="30" align="right">开户行所在地：</td>
				<td align="left"><input name="bankaddress" type="text" style="width: 120px" class="inputText" onfocus="this.select();"  value="${tdnabind.bankaddress}" /></td>
			</tr>
			<tr>
				<td height="30" align="right">银行名称：</td>
				<td align="left"><input name="bankname" type="text" style="width: 120px" class="inputText" onfocus="this.select();"  value="${tdnabind.bankname}" /></td>
			</tr>
			<tr>
				<td height="30" align="right">手机号：</td>
				<td align="left">${tdnabind.mobileid}</td>
			</tr>
			<tr>
				<td height="30" align="right">用户状态：</td>
				<td align="left">
					<select name="state" style="width:80px">
						<option value="1" <c:if test="${1 == tdnabind.state}">selected</c:if>>绑定</option>
						<option value="0" <c:if test="${0 == tdnabind.state}">selected</c:if>>未绑定</option>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
	<table width="45%" cellspacing="0" cellpadding="2"	style="margin-top: 10px">
		<tr>
			<td align="center"><input type="button" value="提交 " class="inputButton" onclick="submint1()">&nbsp;&nbsp;<input type="button" value="返回  " class="inputButton" onclick="javascript:location.href='<%=request.getContextPath()%>/tdnabind/page';"></td>
		</tr>
	</table>
</form>
</body>
</html>
