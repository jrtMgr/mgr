<%@page import="com.ruyicai.mgr.util.Page"%>
<%@page import="java.util.List"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.domain.statis.User"%>
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

function addu(){
	var channelid = document.getElementById("channelid2").value;
	if(channelid == ""){
		alert("请输入channelid");
		return;
	}
	if(window.confirm("新增到编号为"+channelid+"的渠道中?")){
		var url = "<%=request.getContextPath()%>/suser/addusercfg?userid=${param.userid}&channelid="+channelid;
		location.href=url;
	}
}

function delu(id,userid){
	if(window.confirm("确定删除吗？")){
		var url = "<%=request.getContextPath()%>/suser/delUsercfg?id="+id+"&userid="+userid;
		location.href=url;
	}
}


</script>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="2"	class="dataTable">
			<tbody>
				<tr class="dataTableHead">
					<td width="4%" height="30" class="thOver"></td>
					<td width="12%" class="thOver"><strong>用户id</strong>
					</td>
					<td width="12%" class="thOver"><strong>用户名</strong>
					</td>
					<td width="12%" class="thOver"><strong>渠道id</strong>
					</td>
					<td width="12%" class="thOver"><strong>渠道名</strong>
					</td>
					<td width="12%" class="thOver"><strong>操作</strong>
					</td>
				</tr>
				<c:forEach items="${list}" var="usercfg" varStatus="num">
					<tr>
						<td>${num.count}</td>
						<td>${usercfg.userid}</td>
						<td>${usercfg.username}</td>
						<td>${usercfg.channelid}</td>
						<td>${usercfg.channelname}</td>
						<td><a style="cursor: hand;" onclick="delu('${usercfg.id}','${usercfg.userid}')">删除</a>|
							<a href="<%=request.getContextPath()%>/suser/editUsercfgUI?id=${usercfg.id}">修改用户渠道</a>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="3">渠道id: <input type="text" id="channelid2" name="channelid2"/><a style="cursor: hand;" onclick="addu()"> &nbsp;新增用户渠道</a></td>
				</tr>
			</tbody>
		</table>
</body>
</html>