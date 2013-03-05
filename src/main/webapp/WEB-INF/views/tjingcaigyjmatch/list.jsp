<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
<%@page import="java.math.BigDecimal"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
</head>
<script type="text/javascript">	
<% 
String errormsg = (String)request.getAttribute("errormsg");
if(!StringUtil.isEmpty(errormsg)) {
%>
$(document).ready(function() {
	Dialog.alert("<%=errormsg%>");
});
<%	
}
%>	
</script>
<body>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form action="<%=request.getContextPath()%>/tjingcaigyjmatch/list"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">saishi:</td>
									<td><input name="saishi" type="text" style="width: 110px" value="${param.saishi}"
										id="saishi" class="inputText" onfocus="this.select();" /></td>
										
										<td align="right">type:</td>
									<td><input name="type" type="text" style="width: 110px" value="${param.type}"
										id="type" class="inputText" onfocus="this.select();" /></td>
										
										<td align="right">number:</td>
									<td><input name="number" type="text" style="width: 110px" value="${param.number}"
										id="number" class="inputText" onfocus="this.select();" /></td>
										
									<td align="right">显示行数:</td>
									<td><select id="maxResult" name="maxResult" style="width:60px">
											<option value="15" <c:if test='${"15" eq page.maxResult}'>selected</c:if>>15</option>
											<option value="30" <c:if test='${"30" eq page.maxResult}'>selected</c:if>>30</option>
											<option value="50" <c:if test='${"50" eq page.maxResult}'>selected</c:if>>50</option>
											<option value="100" <c:if test='${"100" eq page.maxResult}'>selected</c:if>>100</option>
									</select></td>
									<td></td>
									<td></td>
									<td align="center"><input type="submit" value="查询" class="inputButton">
									</td>
								</tr>
							</table>
						</div>
					</form>
				</td>
			</tr>
			<tr>
				<td	style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2"
						class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="5%" class="thOver"><strong>id</strong></td>
								<td width="6%" class="thOver"><strong>saishi</strong></td>
								<td width="6%" class="thOver"><strong>type </strong></td>
								<td width="6%" class="thOver"><strong>number</strong></td>
								<td width="12%" class="thOver"><strong>team</strong></td>
								<td width="6%" class="thOver"><strong>state</strong></td>
								<td width="8%" class="thOver"><strong>award</strong></td>
								<td width="8%" class="thOver"><strong>popularityRating</strong></td>
								<td width="8%" class="thOver"><strong>probability</strong></td>
								<td width="16%" class="thOver"></td>
							</tr>
							
							<c:forEach items="${page.list}" var="tjm" varStatus="num">
							<form action="<%=request.getContextPath()%>/tjingcaigyjmatch/updateState" method="post" onsubmit="return confirmupdate();">
							<input name="id" type="hidden" value="${tjm.id}"> 
								<tr>
								<td title="${tjm.id}">${tjm.id}</td>
								<td title="${tjm.saishi}">${tjm.saishi}</td>
								<td title="${tjm.type}">${tjm.type}</td>
								<td title="${tjm.number}">${tjm.number}</td>
								<td title="${tjm.team}">${tjm.team}</td>
								<td>
									<select name="state">
											<option value="1" <c:if test="${tjm.state == 1}"> selected="selected"</c:if>>关闭</option>
											<option value="0" <c:if test="${tjm.state == 0}"> selected="selected"</c:if>>打开</option>
									</select>
								</td>
								<td title="${tjm.award}">${tjm.award}</td>
								<td title="${tjm.popularityRating}">${tjm.popularityRating}</td>
								<td title="${tjm.probability}">${tjm.probability}</td>
								<td><input type="submit" value="修改"/></td>
								</tr>
								</form>
							</c:forEach>
							
							<tr>
								<td align="left" id="dg1_PageBar" colspan="10"><div
										style="float: right; font-family: Tahoma">
										<c:choose>
											<c:when test="${page.pageIndex != 0}">
												<a href="<%=request.getContextPath()%>/tjingcaigyjmatch/list?saishi=${param.saishi}&type=${param.type}&number=${param.number}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
											</c:when>
											<c:otherwise>
										第一页
									</c:otherwise>
										</c:choose>
										|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex > 0}">
												<a
													href="<%=request.getContextPath()%>/tjingcaigyjmatch/list?saishi=${param.saishi}&type=${param.type}&number=${param.number}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
											</c:when>
											<c:otherwise>
										上一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 < page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tjingcaigyjmatch/list?saishi=${param.saishi}&type=${param.type}&number=${param.number}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
											</c:when>
											<c:otherwise>
										下一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 != page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tjingcaigyjmatch/list?saishi=${param.saishi}&type=${param.type}&number=${param.number}&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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
												window.location.href="<%=request.getContextPath()%>/tjingcaigyjmatch/list?saishi=${param.saishi}&type=${param.type}&number=${param.number}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
											}
										</script>
									</div>
									<div style="float: left; font-family: Tahoma">共
										${page.totalResult} 条记录，每页 ${page.maxResult} 条，当前第
										${page.pageIndex + 1} / ${page.totalPage} 页</div></td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>