<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="org.json.JSONObject"%>
<%@page import="com.ruyicai.mgr.util.DateUtil"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
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
	window.opener.location="<%=request.getContextPath() %>/cmsg/mpage";
	window.close();
	
}
$(document).ready(function() {
	showerror();
});
<%	
}
%>	


function updateCommisionRatio(userno,lotno) {
	var commisionRatio = $("#c"+lotno).attr("value");
	if(!window.confirm("确定改为："+commisionRatio)){
		return;
	}
	
	$.ajax({
		url:"<%=request.getContextPath()%>/agent/updateCommisionRatio",
		type:"POST",
		data:"userno=" + userno+"&lotno="+lotno+"&commisionRatio="+commisionRatio,
		success:function(data){
			if(data.errorCode == "0") {
				alert("保存成功");
			}else{
				alert(data.value);
			}
		}
	});
}
</script>
</head>
<body style="margin-left: 10px; font-size: 12px;">
	
	<table style="width:50%"  border="1px" style="margin-top: 2px; margin-left: 30px; margin-bottom:20px;" class="dataTable" >
			<tr><td colspan="3">&nbsp;查询用户佣金比例列表</td></tr>
			<c:forEach items="${list}" var="cr">
				<% Map m = (Map)pageContext.getAttribute("cr"); 
					Map mm = (Map)(m.get("id"));
					String s = (String)mm.get("lotno");
					System.out.println(s);
				%>
				<tr>
					<td style="width:2%">用户编号：${cr.id.userno}</td>
					<td style="width:2%">彩种：<%=Lottype.getName(s) %></td>
					<td style="width:3%">比率:<input type="text" value="${cr.percent}" name="commisionRatio" id="c${cr.id.lotno}"/></td>
					<td style="width:1%"><input type="button" value="&nbsp保&nbsp存&nbsp" onclick="updateCommisionRatio('${cr.id.userno}','${cr.id.lotno}')"/> </td>
				</tr>
			</c:forEach>
			
	</table>
	
</body>
</html>