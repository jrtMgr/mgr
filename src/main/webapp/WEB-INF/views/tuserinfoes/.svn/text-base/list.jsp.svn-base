<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.consts.UserState"%>
<%@page import="com.ruyicai.mgr.domain.Tuserinfo"%>
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
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form action="<%=request.getContextPath()%>/tuserinfoes/list" method="post">
						<div style="float: left;">
							用户编号:<input	name="userno" type="text" style="width: 120px" id="userno" value="${param.userno }" class="inputText" onfocus="this.select();" />
							手机:<input	name="mobileid" type="text" style="width: 120px" id="mobileid" value="${param.mobileid }" class="inputText" onfocus="this.select();" />
							用户名:<input	name="userName" type="text" style="width: 120px" id="userName" value="${param.userName }" class="inputText" onfocus="this.select();" />
							邮箱:<input	name="email" type="text" style="width: 120px" id="email" value="${param.email}" class="inputText" onfocus="this.select();" />
							身份证:<input	name="certid" type="text" style="width: 150px" id="certid" value="${param.certid }" class="inputText" onfocus="this.select();" />
							真实姓名:<input	name="name" type="text" style="width: 120px" id="name" value="${param.name }" class="inputText" onfocus="this.select();" />
							昵称:<input	name="nickname" type="text" style="width: 120px" id="name" value="${param.nickname }" class="inputText" onfocus="this.select();" />
							<input type="submit" value="查询" class="inputButton">
						</div>
					</form></td>
			</tr>
			<tr>
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2"
						class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="3%" height="30" class="thOver"></td>
								<td width="7%" class="thOver"><strong>用户编号</strong>
								</td>
								<td width="7%" class="thOver"><strong>用户昵称</strong>
								</td>
								<td width="12%" class="thOver"><strong>注册时间</strong>
								</td>
								<td width="6%" class="thOver"><strong>姓名</strong>
								</td>
								<td width="9%" class="thOver"><strong>手机</strong>
								</td>
								<td width="12%" class="thOver"><strong>用户名</strong>
								</td>
								<td width="12%" class="thOver"><strong>邮箱</strong>
								</td>
								<td width="12%" class="thOver"><strong>身份证</strong>
								</td>
								<td width="6%" class="thOver"><strong>渠道号</strong>
								</td>
								<td width="8%" class="thOver"><strong>用户系统</strong>
								</td>
								<td width="7%" class="thOver"><strong>状态</strong>
								</td>
							</tr>
							<c:if test="${!empty page.list}">
								<c:forEach items="${page.list}" var="user" varStatus="num">
									<tr>
										<td title="${num.count}">${num.count}</td>
										<td title="${user.userno}"><a href="<%=request.getContextPath()%>/tuserinfoes/accountoverview?userno=${user.userno}">${user.userno}</a></td>
										<td title="${user.nickname}">${user.nickname }</td>
										<td title="${user.regtime}"><fmt:formatDate value="${user.regtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td title="${user.name}">${user.name}</td>
										<td title="${user.mobileid}">${user.mobileid}</td>
										<td title="${user.userName}">${user.userName}</td>
										<td title="${user.email}">${user.email}</td>
										<td title="${user.certid}">${user.certid}</td>
										<td title="${user.channel}">${user.channel}</td>
										<td title="${user.subChannel}">${user.subChannel}</td>
										<td>
											<%
												Tuserinfo userinfo = (Tuserinfo) pageContext
																.getAttribute("user");
											%><%=UserState.getMemo(userinfo.getState().intValue())%></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table></td>
			</tr>
		</tbody>
	</table>
</body>
</html>