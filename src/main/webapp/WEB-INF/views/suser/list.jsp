<%@page import="com.ruyicai.mgr.util.Page"%>
<%@page import="java.util.List"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.domain.statis.User"%>
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
function showerror() {
	Dialog.alert("<%=errormsg%>");
}
$(document).ready(function() {
	showerror();
});
<%	
}
%>	
</script>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form action="<%=request.getContextPath()%>/suser/list"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">登录名:</td>
									<td><input type="text" name="name"></input></td>
									<td align="right">角色:</td>
									<td><select name="roleid" style="width:100px">
											<option value="">全部</option>
											<c:forEach var="role" items="${roles}">
												<option value="${role.id}" <c:if test="${role.id eq param.roleid}">selected</c:if>>${role.name}</option>
											</c:forEach>
									</select>
									</td>
									<td align="right">显示行数:</td>
									<td><select id="maxResult" name="maxResult" style="width:60px">
											<option value="15" <c:if test='${"15" eq page.maxResult}'>selected</c:if>>15</option>
											<option value="30" <c:if test='${"30" eq page.maxResult}'>selected</c:if>>30</option>
											<option value="50" <c:if test='${"50" eq page.maxResult}'>selected</c:if>>50</option>
											<option value="100" <c:if test='${"100" eq page.maxResult}'>selected</c:if>>100</option>
									</select></td>
									<td></td>
									<td>&nbsp;&nbsp;&nbsp;<input type="submit" value="查询" class="inputButton">
									</td>
								</tr>
							</table>
						</div>
					</form>
				</td>
			</tr>
			<tr>
				<td	style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2"	class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="4%" height="30" class="thOver"></td>
								<td width="8%" class="thOver"><strong>登录名</strong>
								</td>
								<td width="12%" class="thOver"><strong>真实姓名</strong>
								</td>
								<td width="12%" class="thOver"><strong>联系方式</strong>
								</td>
								<td width="12%" class="thOver"><strong>当前角色</strong>
								</td>
								<td width="12%" class="thOver"><strong>注册时间</strong>
								</td>
								<td width="12%" class="thOver"><strong>备注</strong>
								</td>
								<td width="12%" class="thOver"><strong>操作</strong>
								</td>
							</tr>
							<%Page<User> page2 = (Page<User>)request.getAttribute("page");
							if(null != page2 && null != page2.getList()) { %>
							<c:forEach items="${page.list}" var="user" varStatus="num">
								<tr>
									<td>${num.count}</td>
									<td>${user.name}</td>
									<td>${user.realname}</td>
									<td>${user.tel}</td>
									<td>
										<c:forEach var="role" items="${roles}">
											<c:if test="${role.id eq user.roleid}">${role.name}</c:if>
										</c:forEach>
									</td>
									<td>${user.regdate}</td>
									<td>${user.bz}</td>
									<td>
										<a href="<%=request.getContextPath()%>/suser/editUI?id=${user.id}">修改用户</a> |
										<a href="<%=request.getContextPath()%>/suser/usercfglist?userid=${user.id}">用户渠道分配</a>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td align="left" id="dg1_PageBar" colspan="7"><div
										style="float: right; font-family: Tahoma">
										<c:choose>
											<c:when test="${page.pageIndex != 0}">
												<a
													href="<%=request.getContextPath()%>/suser/list?name=${param.name}&roleid=${param.roleid}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
											</c:when>
											<c:otherwise>
										第一页
									</c:otherwise>
										</c:choose>
										|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex > 0}">
												<a
													href="<%=request.getContextPath()%>/suser/list?name=${param.name}&roleid=${param.roleid}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
											</c:when>
											<c:otherwise>
										上一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 < page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/suser/list?name=${param.name}&roleid=${param.roleid}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
											</c:when>
											<c:otherwise>
										下一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 != page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/suser/list?name=${param.name}&roleid=${param.roleid}&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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
												window.location.href="<%=request.getContextPath()%>/suser/list?name=${param.name}&roleid=${param.roleid}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
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
				</td>
			</tr>
			
			<tr><td style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px; font-size: 14px;"><a href="<%=request.getContextPath()%>/suser/addUI">新增用户</a></td></tr>
		</tbody>
	</table>
</body>
</html>