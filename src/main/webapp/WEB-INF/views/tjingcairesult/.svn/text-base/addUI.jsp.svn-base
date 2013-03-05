<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.domain.Twininfo"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
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
function submint1(){
	if(!window.confirm("确定添加吗?")) {
		return;
	}
	document.forms[0].submit();
}

</script>	
</head>
<body>
<form action="<%=request.getContextPath()%>/tjingcairesult/add" method="post" id="form1">
	<table width="50%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="2" align="center"><strong>新增竞彩赛果</strong>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">id：</td>
				<td align="left">
					<input name="id" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.id}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">比赛取消：</td>
				<td align="left">
					<input name="cancel" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.cancel}" />
				</td>
			</tr>
			<tr>
				<td height="30" align="right">让分：</td>
				<td align="left">
					<input name="letpoint" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.letpoint}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">预设总分：</td>
				<td align="left">
					<input name="basepoint" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.basepoint}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">比分：</td>
				<td align="left">
					<input name="result" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.result}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">上半场比分：</td>
				<td align="left">
					<input name="firsthalfresult" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.firsthalfresult}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">蓝彩胜负/足彩胜负平奖金：</td>
				<td align="left">
					<input name="b0" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.b0}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">让分胜负奖金：</td>
				<td align="left">
					<input name="b1" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.b1}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">胜分差奖金：</td>
				<td align="left">
					<input name="b2" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.b2}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">大小分奖金：</td>
				<td align="left">
					<input name="b3" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.b3}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">足踩比分奖金：</td>
				<td align="left">
					<input name="b4" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.b4}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">总进球奖金：</td>
				<td align="left">
					<input name="b5" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.b5}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">半全场胜负奖金：</td>
				<td align="left">
					<input name="b6" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.b6}"/>
				</td>
			</tr>
		</tbody>
	</table>
	<table width="45%" cellspacing="0" cellpadding="2"	style="margin-top: 10px">
		<tr>
			<td align="center"><input type="button" value="提交 " class="inputButton" onclick="submint1()">&nbsp;&nbsp;<input type="button" value="返回  " class="inputButton" onclick="javascript:location.href='<%=request.getContextPath()%>/tjingcairesult/page';"></td>
		</tr>
	</table>
</form>
</body>
</html>
