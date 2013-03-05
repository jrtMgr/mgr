<%@page import="com.ruyicai.mgr.domain.Tcashrecordcfg"%>
<%@page import="com.ruyicai.mgr.domain.Tcashrecord"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
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


function chexiao(id){
	if(!window.confirm("确定撤销吗?")) {
		return;
	}
	var url = "<%=request.getContextPath()%>/tcashCW/chexiao?ttransactionid="+id+"&tcashrecordid=${param.tcashrecordid}";
	window.location.href = url;
}

function bohui(id){
	var rejectReason = document.getElementById("rejectReason2").value;
	if("" == rejectReason){
		alert("请选择驳回原因");
		return;
	}
	if(!window.confirm("确定驳回吗?")) {
		return;
	}
	var url = "<%=request.getContextPath()%>/tcashCW/bohui?ttransactionid="+id+"&rejectReason="+rejectReason+"&tcashrecordid=${param.tcashrecordid}";
	window.location.href = url;
}


function onsubmitfun() {
	var rejectReason = document.getElementById("rejectReason").value;
	var ttransactionids = document.getElementById("ttransactionid").value;
	if("" == rejectReason){
		alert("请选择驳回原因");
		return false;
	}
	if("" == ttransactionids){
		alert("请输入交易id");
		return false;
	}
	return true;
}
</script>
</head>
<body>
<table class="dataTable" width="70%">
	<tr class="dataTableHead" >
		<td class="thOver" style="border-top: 0px;" width="10%">excel中编号</td>
		<td class="thOver" style="border-top: 0px;" width="30%">交易ID</td>
		<td class="thOver" style="border-top: 0px;" width="10%">姓名</td>
		<td class="thOver" style="border-top: 0px;" width="10%">金额</td>
		<td class="thOver" style="border-top: 0px;" width="25%">操作</td>
	</tr>
	<tr>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td class="thOver" style="border-top: 0px;">驳回原因：<select name="rejectReason2" id="rejectReason2">
				<option value="">----请选择----</option>
				<option value="1">银行账号不存</option>
				<option value="2">账户姓名和银行开户姓名不符</option>
				<option value="3">处理失败,账号有误,请更换 </option>
				<option value="4">暂不支持该银行提现 </option>	
			</select>
		</td>
	</tr>
	<c:forEach items="${list}" var="tcrc" varStatus="num">
		<tr>
			<td >${tcrc.num}</td>
			<td><a href="<%=request.getContextPath()%>/tcash/list?ttransactionid=${tcrc.ttransactionid}">${tcrc.ttransactionid}</a></td>
			<td>${tcrc.name}</td>
			<td>${tcrc.amt}</td>
			<td><a style="cursor: hand;" onclick="chexiao('${tcrc.ttransactionid}')" >撤销成已审核</a>|
				<a style="cursor: hand;" onclick="bohui('${tcrc.ttransactionid}')">驳回</a>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan="3"><input type="button" value="返回" onclick="javascript:history.go(-1)"/></td>
	</tr>
	<tr><td colspan="3"><br><br></td></tr>
	<form action="<%=request.getContextPath()%>/tcashCW/bohui" method="post" onsubmit="return onsubmitfun();">
		
		<input type="hidden" value="${param.tcashrecordid}"	name="tcashrecordid">
		<tr>
			<td align="right">驳回原因：</td>
			<td class="thOver" style="border-top: 0px;"><select name="rejectReason" id="rejectReason">
					<option value="">----请选择----</option>
					<option value="1">银行账号不存</option>
					<option value="2">账户姓名和银行开户姓名不符</option>
					<option value="3 ">处理失败,账号有误,请更换 </option>
					<option value="4">暂不支持该银行提现 </option>	
				</select>
			</td>
			<td></td>
		</tr>
		<tr>
			<td align="right">输入交易ID驳回</td>
			<td colspan="2">
				<textarea rows="30" cols="80" name="ttransactionid" id="ttransactionid"></textarea><br/><font color="red">每个交易ID以逗号分隔</font>
			</td>
		</tr>
		<tr><td></td>
			<td colspan="2">
				<input type="submit" value=" 批量驳回 "	class="inputButton">
			</td>
		</tr>
	</form>
	
</table>
</body>
</html>