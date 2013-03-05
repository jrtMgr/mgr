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
<script type="text/javascript" src="<%=request.getContextPath()%>/fckeditor/fckeditor.js"></script>
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


function submint1(){
	if(!window.confirm("确定添加吗?")) {
		return;
	}
	document.forms[0].submit();
}
//初始化fckeditor
$(document).ready(function() {
	var msg = "${errormsg}";
	if(msg!=''&&msg!=null){
		alert(msg);
	}
	var oFCKeditor2 = new FCKeditor( 'content' ) ;
	oFCKeditor2.BasePath = "<%=request.getContextPath()%>/fckeditor/" ;
	oFCKeditor2.Height="400";
	oFCKeditor2.ReplaceTextarea() ;

	$("#btnOk").click(function(){
		if($('#title').val()==''){
			alert('标题不能为空！');
			$('#title').focus();
			return;
		}
		document.forms[0].submit();
	});
});
</script>	
</head>
<body>
<form action="<%=request.getContextPath()%>/letter/add" method="post" id="form1">
	<table width="80%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="5" align="center"><strong>新增站内消息</strong>
				</td>
			</tr>
			<tr>
				<td align="left">站内信类别：<select name="letterType" style="width:100px">
							<option value="0" <c:if test='${0 == param.letterType}'>selected</c:if>>普通站内信</option>
							<option value="1" <c:if test='${1 == param.letterType}'>selected</c:if>>开奖站内信</option>
							<option value="2" <c:if test='${2 == param.letterType}'>selected</c:if>>中奖站内信</option>
					</select></td>
				
				<td align="left" >发送人编号 ：<input name="fromUserno" style="width: 100px" class="inputText" onfocus="this.select();" value="01637141"/></td>
				<td align="right" >接收人编号(多个“,”分开) ：</td>
				<td align="left" rowspan="2" colspan="2">
					<textarea rows="6" cols="70" name="toUserno" id="toUserno" ></textarea>
				</td>
			</tr>
			<tr>
				<td align="right">标题 ：</td>
				<td colspan="2"><input name="title" style="width: 240px" class="inputText" onfocus="this.select();" value="${param.title}"/>
				</td>
				
			</tr>
			<tr>
				<td colspan="5"><textarea rows="15" cols="80" name="content" id="content" >${param.content}</textarea></td>
			</tr>
		</tbody>
	</table>
	<table width="80%" cellspacing="0" cellpadding="2"	style="margin-top: 10px">
		<tr>
			<td align="center"><input type="button" value="提交 " class="inputButton" onclick="submint1()">&nbsp;&nbsp;<input type="button" value="返回  " class="inputButton" onclick="javascript:location.href='<%=request.getContextPath()%>/tjingcaiMatches/page';"></td>
		</tr>
	</table>
</form>
</body>
</html>
