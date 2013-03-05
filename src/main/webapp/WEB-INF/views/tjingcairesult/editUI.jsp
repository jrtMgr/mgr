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
var cTime=1;
function showerror() {
	Dialog.alert("<%=errormsg%>");
	<%
		if("操作成功".equals(errormsg)){
	%>
		//window.close();
		TimeClose();
	<%}%>
}
$(document).ready(function() {
	showerror();
	
});
<%	
}
%>	
function TimeClose()
{
     window.setTimeout('TimeClose()',1000);//让程序每秒重复执行当前函数。
     if(cTime<=0)//判断秒数如果为0
    	 window.close();//执行关闭网页的操作
     cTime--;//减少秒数
}

function submint1(){
	if(!window.confirm("确定修改吗?")) {
		return;
	}
	document.forms[0].submit();
}
function shenhe(id){
	if(!window.confirm("确定通过审核吗?")) {
		return;
	}
	window.location.href='<%=request.getContextPath()%>/tjingcairesult/audit?id='+id;
}

</script>	
</head>
<body>
<form action="<%=request.getContextPath()%>/tjingcairesult/edit" method="post" id="form1">
<input type="hidden" value="${tjingcaiMatches.id.type}" name="type"/>
	<table cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="2" align="center"><strong>竞彩赛果详细</strong>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">id：</td>
				<td align="left">
					<input name="id" style="width: 150px" class="inputText" onfocus="this.select();" value="${tjingcairesult.id}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">比赛取消：</td>
				<td align="left">
					<input name="cancel" style="width: 150px" class="inputText" onfocus="this.select();" value="${tjingcairesult.cancel}" />
				</td>
			</tr>
			<tr>
				<td height="30" align="right">让分：</td>
				<td align="left">
					<input name="letpoint" style="width: 150px" class="inputText" onfocus="this.select();" value="${tjingcairesult.letpoint}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">预设总分：</td>
				<td align="left">
					<input name="basepoint" style="width: 150px" class="inputText" onfocus="this.select();" value="${tjingcairesult.basepoint}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">比分：</td>
				<td align="left">
					<input name="result" style="width: 150px" class="inputText" onfocus="this.select();" value="${tjingcairesult.result}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">上半场比分：</td>
				<td align="left">
					<input name="firsthalfresult" style="width: 150px" class="inputText" onfocus="this.select();" value="${tjingcairesult.firsthalfresult}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">蓝彩胜负/足彩胜平负奖金：</td>
				<td align="left">
					<input name="b0" style="width: 150px" class="inputText" onfocus="this.select();" value="${tjingcairesult.b0}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">让分胜负奖金：</td>
				<td align="left">
					<input name="b1" style="width: 150px" class="inputText" onfocus="this.select();" value="${tjingcairesult.b1}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">胜分差奖金：</td>
				<td align="left">
					<input name="b2" style="width: 150px" class="inputText" onfocus="this.select();" value="${tjingcairesult.b2}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">大小分奖金：</td>
				<td align="left">
					<input name="b3" style="width: 150px" class="inputText" onfocus="this.select();" value="${tjingcairesult.b3}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">足彩比分奖金：</td>
				<td align="left">
					<input name="b4" style="width: 150px" class="inputText" onfocus="this.select();" value="${tjingcairesult.b4}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">总进球奖金：</td>
				<td align="left">
					<input name="b5" style="width: 150px" class="inputText" onfocus="this.select();" value="${tjingcairesult.b5}"/>
				</td>
			</tr>
			<tr>
				<td height="30" align="right">半全场胜负奖金：</td>
				<td align="left">
					<input name="b6" style="width: 150px" class="inputText" onfocus="this.select();" value="${tjingcairesult.b6}"/>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="button" value="审核 " class="inputButton" onclick="shenhe('${tjingcairesult.id}')">&nbsp;&nbsp;
				<input type="button" value="修改 " class="inputButton" onclick="submint1()">&nbsp;&nbsp;
				<input type="button" value="返回  " class="inputButton" onclick="javascript:location.href='<%=request.getContextPath()%>/tjingcairesult/page';"></td>
			</tr>
		</tbody>
</form>
</body>
</html>
