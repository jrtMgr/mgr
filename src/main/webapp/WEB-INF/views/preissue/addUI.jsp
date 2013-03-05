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
function submint1(){
	if(!window.confirm("确定手工添加吗?")) {
		return;
	}
	document.forms[0].submit();
}

function submint2(){
	if(!window.confirm("确定自动添加吗?")) {
		return;
	}
	var lotno = document.getElementById("lotno1").value;
	if(lotno.length<=0 || lotno.length == ""){
		alert("请选择采种！");
		return;
	}
	var num = document.getElementById("num").value;
	if(num.length<=0 || num.length == ""){
		alert("请输入数量。");
		return;
	}
	var url = "<%=request.getContextPath()%>/preissue/addauto?lotno="+lotno+"&num="+num;
	location.href= url;
}
</script>	
</head>
<body>
<form action="<%=request.getContextPath()%>/preissue/add" method="post" id="form1">
	<table width="50%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="2" align="center"><strong>手工添加未开期</strong>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">采种：</td>
				<td align="left">
					<select name="lotno" style="width:100px">
						<option value="">---请选择--</option>
						<c:forEach var="lot" items="<%=com.ruyicai.mgr.lot.Lottype.getMap().entrySet() %>">
							<option value="${lot.key}" <c:if test="${lot.key eq param.lotno}">selected</c:if>>${lot.value}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">期号：</td>
				<td align="left"><input name="batchcode" style="width: 120px" class="inputText" onfocus="this.select();"/></td>
			</tr>
			<tr>
				<td height="30" align="right">开始时间：</td>
				<td align="left"><input name="starttime" type="text" style="width: 120px" class="inputText" onfocus="this.select();"/>&nbsp;(yyyy-MM-dd HH:mm:ss)</td>
			</tr>
			<tr>
				<td height="30" align="right">封期时间：</td>
				<td align="left"><input name="endtime" type="text" style="width: 120px" class="inputText" onfocus="this.select();"/>&nbsp;(yyyy-MM-dd HH:mm:ss)</td>
			</tr>
			<tr>
				<td height="30" align="right">结束投注时间：</td>
				<td align="left"><input name="endbettime"  type="text" style="width: 120px" class="inputText" onfocus="this.select();"/>&nbsp;(yyyy-MM-dd HH:mm:ss)</td>
			</tr>
			<tr>
				<td height="30" align="right">合买结束时间：</td>
				<td align="left"><input name="hemaiendtime" type="text" style="width: 120px" class="inputText" onfocus="this.select();"/>&nbsp;(yyyy-MM-dd HH:mm:ss)</td>
			</tr>
		</tbody>
	</table>
	<table width="45%" cellspacing="0" cellpadding="2"	style="margin-top: 10px">
		<tr>
			<td align="center"><input type="button" value="提交 " class="inputButton" onclick="submint1()">&nbsp;&nbsp;<input type="button" value="返回  " class="inputButton" onclick="javascript:history.go(-1);"></td>
		</tr>
	</table>
	<br/>
	<br/>
	<table width="50%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="2" align="center"><strong>系统添加未开期</strong>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">采种：</td>
				<td align="left">
					<select name="lotno1" id="lotno1" style="width:100px" id="lotno">
						<option value="">---请选择--</option>
						<c:forEach var="lot" items="<%=com.ruyicai.mgr.lot.Lottype.getMap().entrySet() %>">
							<option value="${lot.key}" <c:if test="${lot.key eq param.lotno}">selected</c:if>>${lot.value}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">数量：</td>
				<td align="left">
					<input name="num" id="num" style="width: 120px" class="inputText" onfocus="this.select();"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right"></td>
				<td align="left"><a onclick="submint2()" style="cursor: hand;">添加</a></td>
			</tr>
		</tbody>
	</table>
</form>
</body>
</html>
