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

function submit1(){
	var sendmun = document.getElementById("sendmun").value;
	if(sendmun == ""){
		alert("请输入赠送数量");
		return;
	}
	var submit1 = document.getElementById("sb");
	submit1.value = "正在执行请等待";
	submit1.disabled=true;
	document.forms[0].submit();
	
}
</script>
</head>
<body>
		<form action="<%=request.getContextPath()%>/batchgift/dogift" method="post">
			<input type="hidden" name="id" value="${tgiftaudit.id}"/>
			<input type="hidden" name="lotno" value="${tgiftaudit.lotno}"/>
			<table class="dataTable" style="width: 50%; word-break:break-all; word-wrap:break-all;">
				<tr>
					<td colspan="2" align="center">已经赠送<font size="18px" color="red">${sucnum}</font>个手机,
					还有<font size="18px" color="red">${failnum}</font>个手机未送,
					已送失败<font size="18px" color="red">${sendfailnum}</font>个</td>
				</tr>
			  <tr>
			  	<td align="right">大客户:</td>
			  	<td>${tgiftaudit.biguserno}(${tgiftaudit.bigname})</td>
			  </tr>
			  <tr>
			  	<td align="right">每个手机号注数:</td>
			  	<td>${tgiftaudit.betnum}</td>
			  </tr>
			   <tr>
			  	<td align="right">总金额:</td>
			  	<td>${tgiftaudit.allamt/100}</td>
			  </tr>
			  <tr>
			  	<td align="right">彩种:</td>
				<td>
				<c:if test="${tgiftaudit.lotno eq 'F47103'}">3D</c:if>
				<c:if test="${tgiftaudit.lotno eq 'F47104'}">双色球</c:if>
				<c:if test="${tgiftaudit.lotno eq 'F47102'}">七乐彩</c:if>
				</td>
				</tr>
			  <c:if test="${tgiftaudit.lotno eq 'F47103'}">
		  	  <tr>
				  	<td align="right">玩法:</td>
				  	<td><select name="type">
				  			<option value="BH">3D包号</option>
				  			<option value="Z3">3D组三单式</option>
				  			<option value="Z6">3D组六单式</option>
				  			<option value="SJ">3D注码随机生成</option>
			  		</select></td>
			  </tr>
			  </c:if>
			  
			   <tr> 
			  	<td align="right">短信内容:</td>
			  	<td height="50px;" width="300px;">${tgiftaudit.smscontent}</td>
			  </tr>
			   <tr> 
			  	<td align="right">短信开关:</td>
			  	<td>
			  		<input name="notes" type="radio" value="on" checked="checked"/>开
					<input name="notes" type="radio" value="off" />关
				</td>
			  </tr>
			  <tr> 
			  	<td align="right">赠送手机号类型:</td>
			  	<td>
			  		<input type="radio" name="flag" value="0" checked="checked">未送|
					<input type="radio" name="flag" value="2">已送失败
				</td>
			  </tr>
			   <tr>
			  	<td align="right">本次赠送数量:</td>
			  	<td><input id="sendmun" type="text" name="sendmun"></td>
			  </tr>
			  <tr>
			  	<td colspan="2" style="text-align:center;"><input id="sb" type="button" value="提交" onclick="submit1();"/>
			  	</td>
			  </tr>
			</table>
		</form>
</body>
</html>