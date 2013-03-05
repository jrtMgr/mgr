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
	if(!window.confirm("确定修改吗?")) {
		return;
	}
	document.forms[0].submit();
}

</script>	
</head>
<body>
<form action="<%=request.getContextPath()%>/tjingcaiMatches/edit" method="post" id="form1">
<input type="hidden" value="${tjingcaiMatches.id.type}" name="type"/>
<input type="hidden" value="${tjingcaiMatches.id.day}" name="day"/>
<input type="hidden" value="${tjingcaiMatches.id.weekid}" name="weekid"/>
<input type="hidden" value="${tjingcaiMatches.id.teamid}" name="teamid"/>
	<table width="50%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="2" align="center"><strong>修改竞彩信息</strong>
				</td>
			</tr>
			<tr>
				<td height="30" align="center" colspan="2">类型：${tjingcaiMatches.id.type} |日期：${tjingcaiMatches.id.day}| 周数：${tjingcaiMatches.id.weekid}| 场次：${tjingcaiMatches.id.teamid}</td>
			</tr>
			<tr>
				<td height="30" align="right">停售时间：</td>
				<td align="left">
					
					<input name="endtime" style="width: 120px" class="inputText" onfocus="this.select();" value="<fmt:formatDate value="${tjingcaiMatches.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">销售状态：</td>
				<td align="left">
					<input name="saleflag" style="width: 120px" class="inputText" onfocus="this.select();" value="${tjingcaiMatches.saleflag}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">状态：</td>
				<td align="left">
					<select name="state" style="width:100px">
							<option value="0" <c:if test='${0 == tjingcaiMatches.state}'>selected</c:if>>正常</option>
							<option value="1" <c:if test='${1 == tjingcaiMatches.state}'>selected</c:if>>期结</option>
							<option value="2" <c:if test='${2 == tjingcaiMatches.state}'>selected</c:if>>开奖</option>
							<option value="3" <c:if test='${3 == tjingcaiMatches.state}'>selected</c:if>>算奖</option>
					</select>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">联赛：</td>
				<td align="left">
					<input name="league" style="width: 120px" class="inputText" onfocus="this.select();" value="${tjingcaiMatches.league}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">主队：客队：</td>
				<td align="left">
					<input name="team" style="width: 120px" class="inputText" onfocus="this.select();" value="${tjingcaiMatches.team}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">比赛时间：</td>
				<td align="left">
					<input name="time" style="width: 120px" class="inputText" onfocus="this.select();" value="<fmt:formatDate value="${tjingcaiMatches.time}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
				</td>
			</tr>
			<tr>
				<td height="30" align="right">不支持玩法：</td>
				<td align="left">
					<input name="unsupport" style="width: 120px" class="inputText" onfocus="this.select();" value="${tjingcaiMatches.unsupport}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">简称：</td>
				<td align="left">
					<input name="shortname" style="width: 120px" class="inputText" onfocus="this.select();" value="${tjingcaiMatches.shortname}"/>
				</td>
			</tr>
		</tbody>
	</table>
	<table width="45%" cellspacing="0" cellpadding="2"	style="margin-top: 10px">
		<tr>
			<td align="center"><input type="button" value="提交 " class="inputButton" onclick="submint1()">&nbsp;&nbsp;<input type="button" value="返回  " class="inputButton" onclick="javascript:location.href='<%=request.getContextPath()%>/tjingcaiMatches/page';"></td>
		</tr>
	</table>
</form>
</body>
</html>
