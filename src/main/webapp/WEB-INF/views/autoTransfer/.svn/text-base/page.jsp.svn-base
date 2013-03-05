<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.domain.Ttransaction"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/Dialog.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
function goPercent(){
	document.getElementById("presentAmt").removeAttribute("readOnly");
	var percentVal = $("#presentPercent").val();
	if(percentVal!=""){
		document.getElementById("presentAmt").setAttribute("readOnly", true); 
	}
}
function goAmt(){
	document.getElementById("presentPercent").removeAttribute("readOnly");
	var amtVal = $("#presentAmt").val();
	if(amtVal!=""){
		document.getElementById("presentPercent").setAttribute("readOnly", true); 
	}
}
	
function active(){
	var t = document.getElementById("hasactive");
	var val = t.options[t.selectedIndex].value;
	if(val=="1")
	{
		document.getElementById("presentPercentTr").style.display='none';
		document.getElementById("presentAmtTr").style.display='none';
	}
	if(val=="2")
	{
		document.getElementById("presentPercentTr").style.display='block';
		document.getElementById("presentAmtTr").style.display='block';
	}

}
</script>
</head>
<body style="margin-left: 10px;">
	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="3"><strong>银行转账</strong></td>
			</tr>
			<form action="<%=request.getContextPath()%>/autoTransfer/transfer"
				method="post">
				<tr>
				</tr>
					<td align="right">用户编号</td>
					<td colspan="2"><input name="userno" type="text"
						style="width: 120px" id="userno" class="inputText"
						onfocus="this.select();" /></td>
				<tr>
				</tr>
					<td align="right">转账平台</td>
					<td colspan="2">
						<select name="accesstype" style="width: 80px">
							<option value="B" <c:if test='${"B" eq param.accesstype}'>selected</c:if>>互联网</option>
							<option value="C" <c:if test='${"C" eq param.accesstype}'>selected</c:if>>客户端</option>
							<option value="W" <c:if test='${"W" eq param.accesstype}'>selected</c:if>>wap</option>
						</select>
					</td>
				<tr>	<td align="right">转账方式</td>
					<td colspan="2">
						<select name="bankid" style="width: 80px">
							<option value="yzz001" <c:if test='${"B" eq param.bankid}'>selected</c:if>>银行转账</option>
							<option value="xrz001" <c:if test='${"C" eq param.bankid}'>selected</c:if>>现金入账</option>
						</select>
					</td>
					</tr>
				<tr>
					<td align="right">转账金额</td>
					<td colspan="2"><input name="amt" type="text"
						style="width: 120px" id="money" class="inputText"
						onfocus="this.select();" />元</td>
				</tr>
				<tr>
					<td align="right">有无活动</td>
					<td colspan="2">
					<select onchange="active()" name="hasactive" id="hasactive" style="width: 80px">					
					<option value="1"  <c:if test='${"1" eq param.hasactive}'>selected</c:if>>无活动</option>
					<option value="2" <c:if test='${"2" eq param.hasactive}'>selected</c:if>>有活动</option>
					</td>
				</tr>
				<tr id="presentPercentTr" style="display: none;">
					<td align="right">赠送比例</td>
					<td colspan="2"><input name="presentPercent" type="text"
						style="width: 120px" id="presentPercent" class="inputText"
						onfocus="this.select();" onblur="goPercent()"/>请填写小数，保留两位有效数字(如:0.20)</td>
				</tr>
				<tr id="presentAmtTr" style="display: none;">
					<td align="right">赠送金额</td>
					<td colspan="2"><input name="presentAmt" type="text"
						style="width: 120px" id="presentAmt" class="inputText"
						onfocus="this.select();" onblur="goAmt()"/>元</td>
				</tr>
				<tr>
					<td colspan="3" align="center"><input type="submit"
						value="提交" class="inputButton"></td>
				</tr>
				<td colspan="3">
					<font color="red">ps:&nbsp;&nbsp;&nbsp;&nbsp;"赠送比例"和"赠送金额"只有在活动期间填写且只能填写一个，按活动（赠送）需求填写</font>
				</td>
			</form>
		</tbody>
	</table>

</body>
</html>