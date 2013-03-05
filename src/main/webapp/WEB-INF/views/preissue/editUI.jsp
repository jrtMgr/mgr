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
<form action="<%=request.getContextPath()%>/preissue/edit" method="post" id="form1">
	<table width="50%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="2" align="center"><strong>修改未开期</strong>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">采种编号：</td>
				<td align="left">
					<input name="lotno" style="width: 120px" class="inputText" onfocus="this.select();" value="${tlotctrl.id.lotno}" readonly="readonly"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">期号：</td>
				<td align="left"><input name="batchcode" style="width: 120px" class="inputText" onfocus="this.select();" value="${tlotctrl.id.batchcode}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td height="30" align="right">开始时间：</td>
				<td align="left">
				<input name="starttime" type="text" style="width: 120px" class="inputText" onfocus="this.select();" value='<fmt:formatDate value="${tlotctrl.starttime}" pattern="yyyy-MM-dd HH:mm:ss"/>' />
				</td>
			</tr>
			<tr>
				<td height="30" align="right">结束时间：</td>
				<td align="left"><input name="endtime" type="text" style="width: 120px" class="inputText" onfocus="this.select();"  value='<fmt:formatDate value="${tlotctrl.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>' /></td>
			</tr>
			<tr>
				<td height="30" align="right">结束投注时间：</td>
				<td align="left"><input name="endbettime"  type="text" style="width: 120px" class="inputText" onfocus="this.select();"  value='<fmt:formatDate value="${tlotctrl.endbettime}" pattern="yyyy-MM-dd HH:mm:ss"/>' /></td>
			</tr>
			<tr>
				<td height="30" align="right">合买终止时间：</td>
				<td align="left">
					<input name="hemaiendtime" type="text" style="width: 120px" class="inputText" onfocus="this.select();"  value='<fmt:formatDate value="${tlotctrl.hemaiendtime}" pattern="yyyy-MM-dd HH:mm:ss"/>' />
				</td>
			</tr>
			<tr>
				<td height="30" align="right">状态：</td>
				<td align="left">
					<select name="state" style="width:80px">
						<option value="-1" <c:if test="${-1 == tlotctrl.state}">selected</c:if>>未开期</option>
						<option value="0" <c:if test="${0 == tlotctrl.state}">selected</c:if>>当前期</option>
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
