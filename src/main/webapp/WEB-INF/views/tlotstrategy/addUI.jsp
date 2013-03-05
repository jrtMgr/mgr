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
	var userno = document.getElementById("userno").value;
	var channel = document.getElementById("channel").value;
	var playtype = document.getElementById("playtype").value;
	var lotno = document.getElementById("lotno").value;
	var agencyno = document.getElementById("agencyno").value;
	
	
	if(userno!="" && channel!=""){
		alert("userno,channel不能同时填！！");
	 	return false;
		}
	if(userno=="" && channel=="" && lotno==""){
		alert("userno,channel,lotno必须填一个！！");
	 	return false;
		}
	if(playtype!=""){
		if(lotno == ""){
			alert("有playtype必须有lotno");
		 	return false;
		}
		
		}
	if(agencyno==""){
		alert("agencyno必填！！");
	 	return false;
		}
	return true;
}
</script>
</head>
<body>
		<form name="notesForm" action="<%=request.getContextPath()%>/tlotstrategy/add" method="post" onSubmit="checkAll();">
			<table class="dataTable" style="width: 50%">
				<tr>
					<td colspan="2" align="center"><strong>新增</strong></td>
				</tr>
			  <tr>
			  	<td align="right">userno:</td>
			  	<td><input id="userno" type="text" name="userno" value="${param.userno}">(和channel只能填一个)</td>
			  </tr>
			  <tr>
			  	<td align="right">channel:</td>
			  	<td><input id="channel" type="text" name="channel" value="${param.channel}">(选填)</td>
			  </tr>
			   <tr>
			  	<td align="right">playtype:</td>
			  	<td><input id="playtype" type="text" name="playtype" value="${param.playtype}">(有playtype必须有lotno)</td>
			  </tr>
			  <tr>
			  	<td align="right">lotno:</td>
			  	<td><input id="lotno" type="text" name="lotno" value="${param.lotno}">(选填)</td>
			  </tr>
			  
			  <tr>
			  	<td align="right">agencyno:</td>
			  	<td><input id="agencyno" type="text" name="agencyno" value="${param.agencyno}"><font color="red">必填</font></td>
			  </tr>
			   <tr>
			  	<td align="right">amt:</td>
			  	<td><input id="amt" type="text" name="amt" value="${param.amt}"></td>
			  </tr>
			  <tr>
			  	<td colspan="2" style="text-align:center;"><input type="submit" value="提交" onclick="return checkAll()" />
			  		<input type="button" value="返回" onclick="javascript:history.go(-1);" />
			  	</td>
			  </tr>
			</table>
		</form>
</body>
</html>