<%@ page language="java" contentType="text/html; charset=utf-8"%>
<jsp:directive.page import="com.ruyicai.mgr.domain.Tgiftaudit" />

<html>
<head>
<link href="<%=request.getContextPath()%>/styles/default.css" rel="stylesheet" type="text/css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<title>审核列表页面</title>
<script type="text/javascript" >

</script>
</head>
<body>
	<div style="padding-left: 20px;padding-top: 10px;">
		<a href="<%=request.getContextPath()%>/batchgift/page?flag=0">查看待审</a> |
		<a href="<%=request.getContextPath()%>/batchgift/page?flag=1">查看已审未完成</a> |
		<a href="<%=request.getContextPath()%>/batchgift/page?flag=2">查看已完成</a>|
		<a href="<%=request.getContextPath()%>/batchgift/page?flag=-1">查看已撤销</a>
	</div>
	<table class="dataTable" width="60%">
		<tr class="dataTableHead" >
			<td class="thOver" style="border-top: 0px;" width="100px">编号</td>
			<td class="thOver" style="border-top: 0px;">赠送创建时间</td>
			<td class="thOver" style="border-top: 0px;">客户编号</td>
			<td class="thOver" style="border-top: 0px;">客户名称</td>
			<td class="thOver" style="border-top: 0px;">总需金额</td>
			<td class="thOver" style="border-top: 0px;">已赠送数量</td>
			<td class="thOver" style="border-top: 0px;">状态</td>
			<td class="thOver" style="border-top: 0px;">操作</td>
		</tr>
		<c:forEach items="${page.list}" var="tgiftaudit" varStatus="num">
			<tr>
				<td width="10%">${num.count}</td>
				<td><fmt:formatDate value="${tgiftaudit.agetime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${tgiftaudit.biguserno}</td>
				<td>${tgiftaudit.bigname}</td>
				<td>${tgiftaudit.allamt/100} 元</td>
				<td>${tgiftaudit.success}</td>
				<td>
					<c:if test="${tgiftaudit.flag ==-1}">已撤销</c:if>
					<c:if test="${tgiftaudit.flag ==0}">待审核</c:if>
					<c:if test="${tgiftaudit.flag ==1}">已审核未完成</c:if>
					<c:if test="${tgiftaudit.flag ==2}">已完成</c:if>
				</td>
				<td>
					<c:if test="${param.flag ==0}">
						<a href="<%=request.getContextPath()%>/batchgift/doAudit?id=${tgiftaudit.id}">撤销</a>
					</c:if>
					<c:if test="${param.flag ==1}">
						<a href="<%=request.getContextPath()%>/batchgift/todetail?id=${tgiftaudit.id}">详细</a>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td align="left" id="dg1_PageBar" colspan="7"><div
					style="float: right; font-family: Tahoma">
					<c:choose>
						<c:when test="${page.pageIndex != 0}">
							<a	href="<%=request.getContextPath()%>/batchgift/page?flag=${param.flag}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
						</c:when>
						<c:otherwise>
					第一页
				</c:otherwise>
					</c:choose>
					|&nbsp;
					<c:choose>
						<c:when test="${page.pageIndex > 0}">
							<a href="<%=request.getContextPath()%>/batchgift/page?flag=${param.flag}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
						</c:when>
						<c:otherwise>
					上一页
				</c:otherwise>
					</c:choose>
					&nbsp;|&nbsp;
					<c:choose>
						<c:when test="${page.pageIndex + 1 < page.totalPage}">
							<a href="<%=request.getContextPath()%>/batchgift/page?flag=${param.flag}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
						</c:when>
						<c:otherwise>
					下一页
				</c:otherwise>
					</c:choose>
					&nbsp;|&nbsp;
					<c:choose>
						<c:when test="${page.pageIndex + 1 != page.totalPage}">
							<a href="<%=request.getContextPath()%>/batchgift/page?flag=${param.flag}&tmaxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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
							window.location.href="<%=request.getContextPath()%>/batchgift/page?flag=${param.flag}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
						}
					</script>
				</div>
				<div style="float: left; font-family: Tahoma">共
					${page.totalResult} 条记录，每页 ${page.maxResult} 条，当前第
					${page.pageIndex + 1} / ${page.totalPage} 页</div></td>
		</tr>
		<tr>
			<td colspan="6" align="left"><a style="cursor:hand; font-size: 12px;" href="<%=request.getContextPath()%>/batchgift/addTaskFile">新增大客户赠送</a>
			</td>
		</tr>
	</table>
</body>
</html>