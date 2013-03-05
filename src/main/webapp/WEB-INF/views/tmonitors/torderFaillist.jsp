<%@page import="com.ruyicai.mgr.domain.Torder"%>
<%@page import="com.ruyicai.mgr.lot.LotCenter"%>
<%@page import="com.ruyicai.mgr.consts.LotState"%>
<%@page import="com.ruyicai.mgr.domain.Tlot"%>
<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.domain.Twininfo"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
function undeduct(id) {
	if(!window.confirm("确定要冲正吗?")) {
		return;
	}
	$.ajax({
		url:"<%=request.getContextPath() %>/tmonitors/torderUndeduct",
		type:"POST",
		data:"orderid=" + id + "&timestmap=" + new Date().getMilliseconds(),
		success:function(data){
			if(data.errorCode == "0") {
				$("#cz" + id).html("已冲正");
			}else {
				alert(data.value);
			}
		}
	});
}

function bet(id,body) {
	if(!window.confirm("确定要重投吗?")) {
		return;
	}
	$.ajax({
		url:"<%=request.getContextPath() %>/tmonitors/orderbet",
		type:"POST",
		data:"orderid=" + id + "&timestmap=" + new Date().getMilliseconds(),
		success:function(data){
			if(data.errorCode == "0") {
				$("#ct" + id).html("已重投");
			}else {
				alert(data.value);
			}
		}
	});
}

function betAll() {
	document.getElementById("betAll").disabled=true;

	location.href='<%=request.getContextPath() %>/tmonitors/torderbetAll'
}
</script>	
</head>
<body>
	<table width="100%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead">
				<td width="10%" height="30" class="thOver"><strong>订单编号</strong>
				</td>
				<td width="10%" height="30" class="thOver"><strong>期号</strong>
				</td>
				<td width="5%" height="30" class="thOver"><strong>彩种编号</strong>
				</td>
				<td width="10%" height="30" class="thOver"><strong>注码</strong>
				</td>
				<td width="10%" height="30" class="thOver"><strong>创建时间</strong>
				</td>
				<td width="6%" height="30" class="thOver"><strong>冲正</strong>
				</td>
				<td width="6%" height="30" class="thOver"><strong>重投</strong>
				</td>
			</tr>
			<c:forEach items="${torderFaillist}" var="torder">
			<%
				Torder torder = (Torder)pageContext.getAttribute("torder");
			%>
				<tr id="${torder.id}">
					<td title="${torder.id}">${torder.id}</td>
					<td title="${torder.batchcode}">${torder.batchcode}</td>
					<td title="<%=Lottype.getName(torder.getLotno())%>"><%=Lottype.getName(torder.getLotno())%></td>
					<td title="${torder.betcode}">${torder.betcode}</td>
					<td title="${torder.createtime}">
						<input type="text" id="time${torder.id}" value="<fmt:formatDate value="${torder.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/> " />
					</td>
					<td id="cz${torder.id}">
						<input type="button" value=" 冲正 " onclick="undeduct('${torder.id}');"/>
					</td>
					<td id="ct${torder.id}">
						<input type="button" value=" 重投 " onclick="bet('${torder.id}');"/>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="7">
					<input id="betAll" type="button" value="全部重投 " onclick="betAll();"/>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>