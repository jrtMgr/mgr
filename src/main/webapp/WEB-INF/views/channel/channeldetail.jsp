<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	alert("<%=errormsg%>");
}
$(document).ready(function() {
	showerror();
});
<%	
}
%>	
</script>
</head>
<body>
	<table cellspacing="0" cellpadding="2" class="dataTable" style="width: 500px;">
		<tbody>
		<tr class="dataTableHead">
				<td class="thOver" colspan="2" style="text-align: center;"><strong>id:${param.id}</strong>
				</td>
			</tr>
			<tr>
				<td align="center" colspan="2">时间:
				<form action="<%=request.getContextPath()%>/channel/channelDetaildo" method="post">
				<input type="hidden" name="id" value="${param.id}"/>
				<input id="starttime" name="starttime" value="${param.starttime}"
					class="inputText" type="text" style="width: 100px;"
					 onclick="DateTime.onImageMouseDown(event,'Calendar','starttime');">
					<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','starttime');"
					style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
					src="<%=request.getContextPath()%>/images/Calendar.gif">
					-&nbsp;<input id="endtime" name="endtime" value="${param.endtime}"
					class="inputText" type="text" ztype="date"
					style="width: 100px;"
					onclick="DateTime.onImageMouseDown(event,'Calendar','endtime');">
					<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','endtime');"
					style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
					src="<%=request.getContextPath()%>/images/Calendar.gif">
					<input type="submit" value="提交 " class="inputButton">
					</form>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td></td>
			</tr>
			<tr>
				<td align="right">注册用户数:</td>
				<td>
				<input type="text" class="inputText" onfocus="this.select();" value="${regnum}"></td>
			</tr>
			<tr>
				<td align="right">注册当天充值的用户数:</td>
				<td>
					<input type="text"  class="inputText" onfocus="this.select();" value="${regpaynum}"/>
				</td>
			</tr>
			<tr>
				<td align="right">充值总金额:</td>
				<td>
				<input type="text" class="inputText" onfocus="this.select();" value="${paymoney}"/>
				</td>
			</tr>
			<tr>
				<td align="right">购彩总金额:</td>
				<td>
				<input type="text" class="inputText" onfocus="this.select();" value="${buymoney}"/>
				</td>
			</tr>
			<tr>
				<td align="right">访问数:</td>
				<td>
				<input type="text"  class="inputText" onfocus="this.select();" value="${visitnum}"/>
				</td>
			</tr>
			<tr>
				<td align="right">结算金额:</td>
				<td>
				<input type="text" class="inputText" onfocus="this.select();" value="${balance}"/>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>
