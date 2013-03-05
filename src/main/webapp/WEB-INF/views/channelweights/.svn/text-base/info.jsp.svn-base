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

function updateLotcheckswitch(id){
	if(window.confirm("确定修改吗？")){
		var s = document.getElementById("state"+id).value;
		location.href="<%=request.getContextPath()%>/channelweights/updateLotcheckswitch?lotno="+id+"&state="+s;
	}
}

function updatetagency(id){
	if(window.confirm("确定修改吗？")){
		var s = document.getElementById("weight"+id).value;
		location.href="<%=request.getContextPath()%>/channelweights/updatetagency?id="+id+"&weight="+s;
	}
}

function updatetthreshold(id){
	if(window.confirm("确定修改吗？")){
		var s = document.getElementById("threshold"+id).value;
		location.href="<%=request.getContextPath()%>/channelweights/updatethreshold?id="+id+"&threshold="+s;
	}
}
</script>	
</head>
<body style="margin-left: 10px;">
	<table width="30%" cellspacing="0" cellpadding="2"	class="dataTable">
			<tbody>
				<tr class="dataTableHead">
					<td height="30" class="thOver" colspan="2"><strong>lotcheckswitch</strong></td>
				</tr>
				<tr class="dataTableHead">
					<td width="4%" class="thOver"><strong>lotno</strong>
					</td>
					<td width="16%" class="thOver"><strong>state</strong>
					</td>
				</tr>
				<c:forEach items="${lotcheckswitchs }" var="lotcheckswitch" varStatus="num">
					<tr>
						<td>${lotcheckswitch.lotno}</td>
						<td>
						<select id="state${lotcheckswitch.lotno}">
							<option value="0" <c:if test="${lotcheckswitch.state == 0}"> selected="selected"</c:if>>关闭</option>
							<option value="1" <c:if test="${lotcheckswitch.state == 1}"> selected="selected"</c:if>>开启</option>
						</select>
						<input type="button" value="修改" onclick="updateLotcheckswitch('${lotcheckswitch.lotno}')"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<table width="100%" cellspacing="0" cellpadding="2"	class="dataTable" border="1">
			<tbody>
				<tr class="dataTableHead">
					<td height="30" class="thOver" colspan="7"><strong>tagency</strong></td>
				</tr>
				<tr class="dataTableHead">
					<td width="4%" class="thOver"><strong>id</strong>
					</td>
					<td width="6%" class="thOver"><strong>agencyname</strong>
					</td>
					<td width="6%" class="thOver"><strong>agencyno</strong>
					</td>
					<td width="6%" class="thOver"><strong>lotno</strong>
					</td>
					<td width="6%" class="thOver"><strong>type</strong>
					</td>
					<td width="16%" class="thOver"><strong>weight</strong>
					</td>
					<td width="6%" class="thOver"><strong>iscrawl</strong>
					</td>
				</tr>
				<c:forEach items="${tagencys}" var="tagency" varStatus="num">
					<tr>
						<td>${tagency.id}</td>
						<td>${tagency.agencyname}</td>
						<td>${tagency.agencyno}</td>
						<td>${tagency.lotno}</td>
						<td>${tagency.type}</td>
						<td><input style="width: 50px;" type="text" name="weight" value="${tagency.weight}" id="weight${tagency.id}"/>
						<input type="button" value="修改" onclick="updatetagency('${tagency.id}')"/></td>
						<td>${tagency.iscrawl}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<table width="60%" cellspacing="0" cellpadding="2"	class="dataTable" border="1">
			<tbody>
				<tr class="dataTableHead">
					<td height="30" class="thOver" colspan="4"><strong>tthreshold</strong></td>
				</tr>
				<tr class="dataTableHead">
					<td width="2%" class="thOver"><strong>id</strong>
					</td>
					<td width="3%" class="thOver"><strong>lotno</strong>
					</td>
					<td width="18%" class="thOver"><strong>threshold</strong>
					</td>
					<td width="6%" class="thOver"><strong>type</strong>
					</td>
				</tr>
				<c:forEach items="${tthresholds }" var="tthreshold" varStatus="num">
					<tr>
						<td>${tthreshold.id}</td>
						<td>${tthreshold.lotno}</td>
						<td><input style="width: 50px;" type="text"  value="${tthreshold.threshold}" id="threshold${tthreshold.id}"/>
						<input type="button" value="修改" onclick="updatetthreshold('${tthreshold.id}')"/></td>
						<td>${tthreshold.type}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
</body>
</html>