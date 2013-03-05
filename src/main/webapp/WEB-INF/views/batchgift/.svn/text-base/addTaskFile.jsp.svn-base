<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<html>
<head>
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	
}
$(document).ready(function() {
	showerror();
});
<%	
}
%>	
function checkAll(){
	var shmc = document.getElementById("filename").value;
	var notescontent = document.getElementById("notescontent").value;
	var jrtnotescontent = document.getElementById("jrtnotescontent").value;
	var biguserno = document.getElementById("biguserno").value;
	var betnum = document.getElementById("betnum").value;
	
	if(notescontent==""){
		alert("客户短信内容不能为空！");
	 	return false;
		}
	if(jrtnotescontent==""){
		alert("金软通内容不能为空！");
	 	return false;
		}
	if(biguserno==""){
		alert("大用户编号不能为空！");
	 	return false;
		}
	if(betnum==""){
		alert("注数不能为空！");
	 	return false;
		}
	
	if(shmc == ""){
		alert("没有文件！");
	 	return false;
		}
	return true;
}
</script>
</head>
<body>
		<form name="notesForm" action="<%=request.getContextPath()%>/batchgift/addgift" method="post" enctype="multipart/form-data" onsubmit="return checkAll();">
			<table class="dataTable" style="width: 50%">
				<tr>
					<td colspan="2" align="center"><strong>新增</strong></td>
				</tr>
			  <tr> 
			  	<td align="right">客户短信内容:</td>
			  	<td>
			  		<textarea rows="4" cols="27" name="notescontent" id="notescontent">${param.notescontent}</textarea>
			  	</td>
			  </tr>
			  <tr > 
			  	<td align="right">金软通内容:</td>
			  	<td>
			  		<textarea rows="4" cols="27" name="jrtnotescontent" id="jrtnotescontent">${param.jrtnotescontent}</textarea>
			  	</td>
			  </tr>
			  <tr> 
			  	<td align="right">导入手机号txt文件:</td>
			  	<td><input id="filename" type="file" name="filename" /></td>
			  </tr>
			  <tr>
			  	<td align="right">大客户编号:</td>
			  	<td><input id="biguserno" type="text" name="biguserno" value="${param.biguserno}"><font color="red">*</font></td>
			  </tr>
			  <tr>
			  	<td align="right">彩种:</td>
				<td><select name="lotno" style="width:100px">
						<option value="F47104" >双色球</option>
						<option value="F47103" >3D</option>
						<option value="F47102" >七乐彩</option>
					</select>
				</td>
			  <tr>
			  	<td align="right">注数:</td>
			  	<td><input id="betnum" type="text" name="betnum" value="${param.betnum}"></td>
			  </tr>
			  <tr>
			  	<td colspan="2" style="text-align:center;"><input type="submit" value="提交"/>
			  		<input type="button" value="返回" onclick="javascript:history.go(-1);" />
			  	</td>
			  </tr>
			</table>
		</form>
</body>
</html>