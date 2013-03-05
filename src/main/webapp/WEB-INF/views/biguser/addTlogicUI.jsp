<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.domain.Twininfo"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>增加</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/Dialog.js"></script>
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
function submint1(){
	if(!window.confirm("确定要添加吗?")) {
		return;
	}
	
	document.forms[0].submit();
}
</script>
</head>
<body>
	<form action="<%=request.getContextPath()%>/biguser/addTlogic" method="post">
		<table width="56%" cellspacing="0" cellpadding="2" class="dataTable"
			style="margin-left: 50px; margin-top: 20px;">
			<tbody align="center">
				<tr class="dataTableHead">
					<td height="30" class="thOver" ><strong>逻辑机号：</strong>
					</td>
					<td height="30" class="thOver"><strong>销售金额</strong>
					</td>
					<td height="30" class="thOver"><strong>销售时间(yyyymmdd)：</strong>
					</td>
				</tr>
				<tr>
					<td >89201</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
					<td rowspan="24" align="center"><input name="selldate" type="text" class="inputText" onfocus="this.select();" /></td>
				</tr>
				<tr>
					<td >89202</td>
					<td ><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89203</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89204</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89205</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89206</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89207</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89208</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89209</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89210</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89211</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89212</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89213</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89214</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89215</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89216</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89217</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89218</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89219</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89220</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89221</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89222</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89223</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
				<tr>
					<td>89224</td>
					<td><input name="moneycount" type="text" class="inputText" onfocus="this.select();" value="0"/></td>
				</tr>
			</tbody>
		</table>
		<table width="45%" cellspacing="0" cellpadding="2"
			style="margin-top: 10px">
			<tr>
				<td align="center" colspan="3"><input type="submit" value="提交 "
					class="inputButton" >&nbsp;&nbsp;<input
					type="button" value="返回  " class="inputButton"
					onclick="javascript:history.go(-1);">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
