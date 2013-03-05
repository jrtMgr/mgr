<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.domain.Tloguser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<%String errormsg = (String) request.getAttribute("errormsg");
			if (!StringUtil.isEmpty(errormsg)) {%>
function showerror() {
	Dialog.alert("<%=errormsg%>");
}
$(document).ready(function() {
	showerror();
});
<%}%>	

function _delete(id){
	if(!window.confirm("确定删除吗?")) {
		return;
	}
	location.href="<%=request.getContextPath()%>/tpermissions/delete?id="+id;
}
</script>
</head>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0">
		<tbody>
		<tr>
			<td style="padding: 2px 10px;">
				<form action="<%=request.getContextPath()%>/tpermissions/list"
					method="post">
					<div style="float: left;">
						<table width="100%" cellspacing="2" cellpadding="2" border="0">
							<tr>
								<td align="right">用户名:</td>
								<td><input name="username" type="text" style="width: 110px"
									value="${username}"  class="inputText"
									onfocus="this.select();" />
								</td>
								<td align="right">权限:</td>
								<td><select name="menuid" style="width: 100px">
									<option value="">全部</option>
									<c:forEach items="${list}" var="menu">
										<option value="${menu.id}" <c:if test='${menu.id == param.menuid}'>selected</c:if>>${menu.name}</option>
									</c:forEach>
								</select>
								</td>
								<td align="right">状态:</td>
								<td><select name="state" style="width: 100px">
										<option value="1" <c:if test='${1 == state}'>selected</c:if>>可用</option>
										<option value="0" <c:if test='${0 == state}'>selected</c:if>>不可用</option>
								</select>
								</td>
								<td align="right">显示行数:</td>
								<td><select id="maxResult" name="maxResult"
									style="width: 60px">
										<option value="15"
											<c:if test='${"15" eq page.maxResult}'>selected</c:if>>15</option>
										<option value="30"
											<c:if test='${"30" eq page.maxResult}'>selected</c:if>>30</option>
										<option value="50"
											<c:if test='${"50" eq page.maxResult}'>selected</c:if>>50</option>
										<option value="100"
											<c:if test='${"100" eq page.maxResult}'>selected</c:if>>100</option>
								</select>
								</td>
								<td></td>
								<td></td>
								<td align="center"><input type="submit" value="查询"
									class="inputButton">&nbsp;&nbsp;&nbsp; <input
									type="button" value="重置"
									onclick="javascript:location.href='<%=request.getContextPath()%>/tpermissions/page';"
									class="inputButton"></td>
							</tr>
						</table>
					</div>
				</form></td>
		</tr>
		<tr>
			<td	style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
				<table width="100%" cellspacing="0" cellpadding="2"
					class="dataTable">
					<tbody>
						<tr class="dataTableHead">
							<td width="3%" height="30" class="thOver"></td>
							<td width="33%" class="thOver"><strong>用户名</strong>
							</td>
							<td width="34%" class="thOver"><strong>状态</strong>
							</td>
							<td width="30%" class="thOver"><strong>修改</strong>
							</td>
						</tr>
						<c:forEach items="${page.list}" var="tpermissions" varStatus="num">
							<tr>
								<td title="${num.count}">${num.count}</td>
								<td title="${tpermissions.nickname}">${tpermissions.nickname}</td>
								<td title="${tpermissions.state}"><c:if test="${tpermissions.state == 0}">不可用</c:if>
									<c:if test="${tpermissions.state == 1 }">可用</c:if></td>
								<td>
									<c:if test="${tpermissions.state == 1 }">
										<a href="<%=request.getContextPath()%>/tpermissions/editUI?id=${tpermissions.id}">编辑权限</a>|
										<a onclick="_delete('${tpermissions.id}')"  style="cursor:hand;">删除</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>

						<tr>
							<td align="left" id="dg1_PageBar" colspan="17"><div
									style="float: right; font-family: Tahoma">
									<c:choose>
										<c:when test="${page.pageIndex != 0}">
											<a
												href="<%=request.getContextPath()%>/tpermissions/list?state=${param.state}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
										</c:when>
										<c:otherwise>
										第一页
									</c:otherwise>
									</c:choose>
									|&nbsp;
									<c:choose>
										<c:when test="${page.pageIndex > 0}">
											<a
												href="<%=request.getContextPath()%>/tpermissions/list?state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
										</c:when>
										<c:otherwise>
										上一页
									</c:otherwise>
									</c:choose>
									&nbsp;|&nbsp;
									<c:choose>
										<c:when test="${page.pageIndex + 1 < page.totalPage}">
											<a
												href="<%=request.getContextPath()%>/tpermissions/list?state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
										</c:when>
										<c:otherwise>
										下一页
									</c:otherwise>
									</c:choose>
									&nbsp;|&nbsp;
									<c:choose>
										<c:when test="${page.pageIndex + 1 != page.totalPage}">
											<a
												href="<%=request.getContextPath()%>/tpermissions/list?state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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
												window.location.href="<%=request.getContextPath()%>/tdnabind/list?state=${param.state}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
											}
										</script>
								</div>
								<div style="float: left; font-family: Tahoma">共
									${page.totalResult} 条记录，每页 ${page.maxResult} 条，当前第
									${page.pageIndex + 1} / ${page.totalPage} 页</div>
							</td>
						</tr>

					</tbody>
				</table></td>
		</tr>
		<tr>
			<td
				style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px; font-size: 14px;"><a
				href="<%=request.getContextPath()%>/tpermissions/addUI">新增用户</a>
			</td>
		</tr>
		</tbody>
	</table>
</body>
</html>