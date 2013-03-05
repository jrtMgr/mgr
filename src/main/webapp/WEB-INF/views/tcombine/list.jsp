<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</head>
<script type="text/javascript">	
<%String errormsg = (String) request.getAttribute("errormsg");
			if (!StringUtil.isEmpty(errormsg)) {%>
function showerror() {
	Dialog.alert("<%=errormsg%>");
}
$(document).ready(function() {
	showerror();
});
<%}%>	

function toMethod(){
	var repeat=document.getElementById("repeat").value;
	var fromUserno=document.getElementById("fromUserno").value;
	var toUserno=document.getElementById("toUserno").value;
	if(repeat==1){
			location.href="<%=request.getContextPath()%>/tcombine/combineRepeatUsername?fromUserno="
					+fromUserno+"&toUserno="+toUserno;
		} else {
			location.href ="<%=request.getContextPath()%>/tcombine/combineTuserinfo?fromUserno="
					+fromUserno+"&toUserno="+toUserno;
		}
	}
</script>
<body>
	<table width="50%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="3"><strong>用户合并</strong></td>
			</tr>
			<form>
			<tr>
				<td align="right">用户名是否重复</td>
				<td colspan="2"><select name="repeat" id="repeat">
						<option value="0" selected="selected">否</option>
						<option value="1">是</option>
				</select></td>
			</tr>
			<tr>
				<td align="right">被合并用户编号</td>
				<td colspan="2"><input name="fromUserno" type="text" id="fromUserno"
					style="width: 120px" id="fromUserno" class="inputText"
					onfocus="this.select();" /></td>
			</tr>
			<tr>
				<td align="right">合并到用户编号</td>
				<td colspan="2"><input name="toUserno" type="text" id="toUserno"
					style="width: 120px" id="toUserno" class="inputText"
					onfocus="this.select();" /></td>
			</tr>
			<tr>
			<td align="right"></td>
			<td colspan="2"><input type="button" value="提交" onclick="toMethod()"/></td>
			</tr>
			</form>
		</tbody>
	</table>
</body>
</html>