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
Array.prototype.remove = function(b) {
	var a = this.indexOf(b);
	if (a >= 0) {
	this.splice(a, 1);
	return true;
	}
	return false;
	}; 
	
var flownos = [];
<c:forEach items="${faillist}" var="lot">
flownos.push("${lot.flowno}");
</c:forEach>
function undeduct(orderid,flowno) {
	if(!window.confirm("确定要订单冲正吗?")) {
		return;
	}
	$.ajax({
		url:"<%=request.getContextPath() %>/tmonitors/torderUndeduct",
		type:"POST",
		data:"orderid=" + orderid + "&timestmap=" + new Date().getMilliseconds(),
		success:function(data){
			if(data.errorCode == "0") {
				alert(flowno+"冲正成功");
				flownos.remove(flowno);
				$("#cz" + flowno).html("已冲正");
			}
		}
	});
}

function continuebet(flowno) {
	$.ajax({
		url:"<%=request.getContextPath() %>/tmonitors/continuebet",
		type:"POST",
		data:"flowno=" + flowno + "&agencyno=" + $("#agencyno" + flowno).val() + "&ordertime=" + $("#time" + flowno).val() + "&timestmap=" + new Date().getMilliseconds(),
		success:function(data){
			if(data.errorCode == "0") {
				flownos.remove(flowno);
				$("#td" + flowno).html("已投出");
			}else{
				alert(data.value);
			}
		}
	});
}
function shuaxing(flowno) {
	$.ajax({
		url:"<%=request.getContextPath() %>/tmonitors/state",
		type:"POST",
		data:"flowno=" + flowno,
		success:function(data){
			$("#state" + flowno).html(data.value);
		}
	});
}

function updateTime() {
	$.ajax({
		url:"<%=request.getContextPath() %>/tmonitors/updateTime",
		type:"POST",
		success:function(data){
			alert(data.value);
		}
	});
}
function betAll() {
	document.getElementById("betAll").disabled=true;

	location.href='<%=request.getContextPath() %>/tmonitors/betAll'
}
</script>	
</head>
<body>
	<table width="100%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead">
				<td width="15%" height="30" class="thOver"><strong>流水号</strong>
				</td>
				<td width="11%" height="30" class="thOver"><strong>彩票中心</strong>
				</td>
				<td width="8%" height="30" class="thOver"><strong>彩种</strong>
				</td>
				<td width="10%" height="30" class="thOver"><strong>期号</strong>
				</td>
				<td width="18%" height="30" class="thOver"><strong>注码</strong>
				</td>
				<td width="15%" height="30" class="thOver"><strong>时间</strong>
				</td>
				<td width="9%" height="30" class="thOver"><strong>状态</strong>
				</td>
				<td width="8%" height="30" class="thOver"><strong>重投</strong>
				</td>
				<td width="8%" height="30" class="thOver"><strong>冲正</strong>
				</td>
			</tr>
			<c:forEach items="${faillist}" var="lot">
			<%
				Tlot tlot = (Tlot)pageContext.getAttribute("lot");
			String center = LotCenter.getName(tlot.getAgencyno());
			center = StringUtil.isEmpty(center) ? "无" : center;
			String lottype = Lottype.getName(tlot.getLotno());
			%>
				<tr id="${lot.flowno}">
					<td title="${lot.flowno}">
					${lot.flowno}
					</td>
					<td>
					<select name="agencyno${lot.flowno}" id="agencyno${lot.flowno}">
					<option value=" ">无</option>
					<c:forEach items="<%=LotCenter.getMap().entrySet() %>" var="lotcenter">
						<option value="${lotcenter.key}" <c:if test="${lotcenter.key eq lot.agencyno}">selected</c:if>>${lotcenter.value}</option>
					</c:forEach>
					</select>
					</td>
					<td><%=lottype%></td>
					<td title="${lot.batchcode}">${lot.batchcode}</td>
					<td title="${lot.betcode}">${lot.betcode}</td>
					<td title="${lot.lotcenterordertime}">
						<input type="text" id="time${lot.flowno}" value="<fmt:formatDate value="${lot.lotcenterordertime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
					</td>
					<td>
						<span id="state${lot.flowno}">
						${lot.instate}
						</span>
						&nbsp;<input type="button" value=" 刷 新 " onclick="shuaxing('${lot.flowno}');"/>
					</td>
					<td id="td${lot.flowno}">
					<input type="button" value=" 重 投 " onclick="continuebet('${lot.flowno}');"/>
					</td>
					<td id="cz${lot.flowno}">
					<input type="button" value=" 冲正 " onclick="undeduct('${lot.torderid}','${lot.flowno}');"/>
					</td>
				</tr>
			</c:forEach>
			<tr>
			<td colspan="4">
				<input type="button" value="全部改为彩票中心时间 " onclick="updateTime();"/>
				<input id="betAll" type="button" value="全部重投 " onclick="betAll();"/>
			</td>
			</tr>
		</tbody>
	</table>
</body>
</html>