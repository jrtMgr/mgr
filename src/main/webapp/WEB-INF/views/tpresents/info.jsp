<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.domain.Ttransaction"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 	
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

function onsubmitfun() {
	if($.trim($("#money").val()) == "") {
		Dialog.alert("请输入彩金金额");
		return false;
	} 
	if($.trim($("#userflag").val()) == "") {
		Dialog.alert("请输入用户标识");
		return false;
	} 
	return true;
}
</script>	
</head>
<body style="margin-left: 10px;">
<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="3"><strong>赠送彩金</strong></td>
			</tr>
<form action="<%=request.getContextPath()%>/tpresents/present" method="post" onsubmit="return onsubmitfun();">
			<tr>
				<td align="right">
					彩金金额
				</td>
				<td colspan="2">
					<input name="money" type="text" style="width: 120px" id="money"
								class="inputText" onfocus="this.select();" />元
				</td>
				</tr>
			<tr>
				<td align="right">可提现</td>
				<td colspan="2">
					<select name="draw">
						<option value="0" selected="selected">否</option>
						<option value="1">是</option>
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">用户系统</td>
				<td colspan="2">
					<input name="subchannel" type="text" style="width: 120px" id="subchannel"
								class="inputText" onfocus="this.select();" value="00092493" />
				</td>
			</tr>
			<tr>
				<td align="right">渠道</td>
				<td colspan="2">
					<input name="channel" type="text" style="width: 120px" id="channel"
								class="inputText" onfocus="this.select();" value="1" />
				</td>
			</tr>
			<tr>
				<td align="right">短信内容</td>
				<td colspan="2">
					<textarea rows="20" cols="50" name="sms"></textarea>
				</td>
			</tr>
			<tr>
				<td align="right">用户标识</td>
				<td colspan="2">
					<textarea rows="20" cols="50" name="userflag" id="userflag"></textarea><br/>
					<font color="red">格式：<br>&nbsp;&nbsp;&nbsp;&nbsp;用户编号：0~00000000<br/>&nbsp;&nbsp;&nbsp;&nbsp;手机号：1~15811212096<br/>&nbsp;&nbsp;&nbsp;&nbsp;邮箱：2~zhangfuqiang@ruyicai.com<br/>&nbsp;&nbsp;&nbsp;&nbsp;用户名：3~zhangfuqiang<br/>每个用户间以逗号分隔</font>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
				<input type="submit" value=" 赠 送 "
								class="inputButton">
				</td>
			</tr>
			<tr><td colspan="3"><a href="<%=request.getContextPath()%>/tpresents/list">查看我的赠送记录</a></td></tr>
</form>

<%Page<Ttransaction> page2 = (Page<Ttransaction>)request.getAttribute("page");
	if(page2!=null && page2.getList().size()!=0) {
		%>
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
		<tr class="dataTableHead" >
			<td class="thOver" style="border-top: 0px;" width="20px">编号</td>
			<td class="thOver" style="border-top: 0px;">赠送时间</td>
			<td class="thOver" style="border-top: 0px;">被送人编号</td>
		</tr>
		<c:forEach items="${page.list}" var="tra" varStatus="num">
			<tr>
				<td width="10%">${num.count}</td>
				<td title="${tra.plattime}"><fmt:formatDate value="${tra.plattime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${tra.userno}</td>
			</tr>
		</c:forEach>
		<tr>
			<td align="left" id="dg1_PageBar" colspan="3"><div
					style="float: right; font-family: Tahoma">
					<c:choose>
						<c:when test="${page.pageIndex != 0}">
							<a	href="<%=request.getContextPath()%>/tpresents/list?maxResult=${page.maxResult}&pageIndex=0">第一页</a>
						</c:when>
						<c:otherwise>
					第一页
				</c:otherwise>
					</c:choose>
					|&nbsp;
					<c:choose>
						<c:when test="${page.pageIndex > 0}">
							<a href="<%=request.getContextPath()%>/tpresents/list?maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
						</c:when>
						<c:otherwise>
					上一页
				</c:otherwise>
					</c:choose>
					&nbsp;|&nbsp;
					<c:choose>
						<c:when test="${page.pageIndex + 1 < page.totalPage}">
							<a href="<%=request.getContextPath()%>/tpresents/list?maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
						</c:when>
						<c:otherwise>
					下一页
				</c:otherwise>
					</c:choose>
					&nbsp;|&nbsp;
					<c:choose>
						<c:when test="${page.pageIndex + 1 != page.totalPage}">
							<a href="<%=request.getContextPath()%>/tpresents/list?tmaxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
						</c:when>
						<c:otherwise>
					最末页
				</c:otherwise>
					</c:choose>
					&nbsp;|&nbsp; &nbsp;&nbsp;转到第&nbsp;<input type="text"
						onkeyup="value=value.replace(/\D/g,'')" style="width: 40px"
						class="inputText" id="_PageBar_Index">&nbsp;页&nbsp;&nbsp;<input
						type="button" value="跳转" class="inputButton" onclick="go()">
					<script type="text/javascript">
						function go() {
							var pageindex = parseInt($("#_PageBar_Index").val()) - 1;
							window.location.href="<%=request.getContextPath()%>/tpresents/list?maxResult=${page.maxResult}&pageIndex=" + pageindex;
						}
					</script>
				</div>
				<div style="float: left; font-family: Tahoma">共
					${page.totalResult} 条记录，每页 ${page.maxResult} 条，当前第
					${page.pageIndex + 1} / ${page.totalPage} 页</div></td>
		</tr>
	<%} %>
		</tbody>
	</table>
	
</body>
</html>