<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/map.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Dialog.js"></script>
</head>
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
function fn(machineno){
	  var New=document.getElementsByName(machineno);
	   var strNew = "";
	   for(var i=0;i<New.length;i++){
	     if(New.item(i).checked){
	         strNew=New.item(i).getAttribute("value");  
	         break;
	 	 }
	   }
	if(strNew == ""){
		alert("请选择指令");
		return;
	}
	if(!window.confirm("确定向"+machineno+"发送  "+strNew+"吗?")) {
		return;
	}
	$.ajax({
		url:"<%=request.getContextPath() %>/betmachine/machineCommand",
		type:"POST",
		data:"machineno=" + machineno+"&type="+strNew,
		success:function(data){
			alert(data.value);
		}
	});
}
</script>
<body>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form name="submitForm" id="submitForm" action="<%=request.getContextPath()%>/betmachine/getMachineState"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="center">
										<input type="submit" value="查询" class="inputButton">
									</td>
								</tr>
							</table>
						</div>
					</form>
				</td>
			</tr>
			<tr>
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2"
						class="dataTable">
							<tr class="dataTableHead">
								<td width="6%" class="thOver"><strong>投注机号</strong></td>
								<td width="12%" class="thOver"><strong>活动状态</strong></td>
								<td width="5%" class="thOver"><strong>登录状态</strong></td>
								<td width="7%" class="thOver"><strong>出纸状态</strong></td>
								<td width="7%" class="thOver"><strong>余额</strong>(元)</td>
								<td width="10%" class="thOver"><strong>所属城市</strong></td>
								<td width="30%" class="thOver"><strong>操作</strong></td>
							</tr>
							<%
								String value = (String)request.getAttribute("result");
								if(!StringUtil.isEmpty(value)){
									String[] values = value.split("\\|");
									for (String v : values) {
										String[] detail = v.split("\\~");
							%>
							<tr>
								<td ><%= detail[0]%></td>
								<%
								if(detail[1].equals("0")) {%>
								<td >停止</td>
								<%}else if (detail[1].equals("1")){%>
								<td >在出票</td>
								<%}else if (detail[1].equals("2")){%>
								<td >在兑奖</td>
								<%}else{%>
								<td ><%=detail[1] %></td>
								<%} %>
								
								<%
								if(detail[2].equals("0")) {%>
								<td >未登录 </td>
								<%}else if (detail[2].equals("1")){%>
								<td >已登录</td>
								<%}else{%>
								<td ><%=detail[2] %></td>
								<%} %>
								
								<%
								if(detail[3].equals("0")) {%>
								<td >不出纸 </td>
								<%}else if (detail[3].equals("1")){%>
								<td >出纸</td>
								<%}else{%>
								<td ><%=detail[3] %></td>
								<%} %>
								<td ><%= detail[4]%></td>
								<td ><%= detail[5]%></td>
								<td>
									<input type="radio" id="<%=detail[0]%>" name="<%=detail[0]%>" value="0" >停止|
									<input type="radio" id="<%=detail[0]%>" name="<%=detail[0]%>" value="1" >登录|
									<input type="radio" id="<%=detail[0]%>" name="<%=detail[0]%>" value="2" >无纸|
									<input type="radio" id="<%=detail[0]%>" name="<%=detail[0]%>" value="3" >有纸|
									<input type="radio" id="<%=detail[0]%>" name="<%=detail[0]%>" value="4" >出票|
									<input type="radio" id="<%=detail[0]%>" name="<%=detail[0]%>" value="5" >兑奖
									<input type="button" value="发送指令" onclick="fn('<%=detail[0]%>')"/>
								</td>
							</tr>
							<%
									}
								}
							%>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>