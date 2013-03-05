<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<title>赛事管理</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<link href="<%=request.getContextPath()%>/styles/default.css" rel="stylesheet" type="text/css">
	</head>

	<script language="javascript">
	$(document).ready(function() {
		if(${!empty errormsg}){
			alert("${errormsg}");
		}
	});

	//编辑赛事
	function editEvents(id){
		location.href = '<%=request.getContextPath()%>/events/addAgainst?id='+id;
	}
	//删除本期
	function deleteIssue(id){
		if(!confirm("确认删除?")){
			return;
		}
		location.href = '<%=request.getContextPath()%>/events/deleteIssue?id='+id;
	}
	</script>
	
	<body style="padding-left: 10px;padding-top: 10px;">
	<div align="center" id="maincontent">
		<table style="width: 100%" border="1" class="List" id="tblCondition">
			<tr>
				<td colspan="100%" class="Main" align="center" >
					<font style="font-size: 24px;b">赛事管理</font>
				</td>
			</tr>
			
			<tr>
				<td>添加时间	</td>
				<td>胜负彩期数</td>
				<td>任9期数</td>
				<td>六场半全场期数</td>
				<td>四场进球期数</td>
				<td>第一场开赛时间</td>
				<td>最后一场开赛时间</td>
				<td>对阵状态	</td>
				<td>赛果状态	</td>
				<td align="center">操作	</td>
			</tr>
			<c:forEach items="${page.list}" var="tr">
				<tr>
					<td >${tr.addDate}</td>
					<td>${tr.vdlId}</td>
					<td>${tr.any9Id}</td>
					<td>${tr.games6Id}</td>
					<td>${tr.games4Id}</td>
					<td>${tr.d1}</td>
					<td>${tr.d2}</td>
					<td><font color="black" >${tr.dz}</font></td>
					<td>
						<font <c:if test="${(tr.dz eq '对阵已结束' && (tr.sg eq '未添加'||tr.sg eq '部分添加' ))}">color="red" </c:if> <c:if test="${!(tr.dz eq '对阵已结束' && (tr.sg eq '未添加'||tr.sg eq '部分添加' ))}">color="black"</c:if>>${tr.sg}</font>
					</td>
					<td>
						<a href="#" style="text-decoration: none" onClick="javascript:editEvents('${tr.id}')">编辑赛事</a> |
						<a href="#" style="text-decoration: none" onClick="javascript:deleteIssue('${tr.id}')">删除本期</a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="100%">
				<div style="float: right; font-family: Tahoma">
				<c:choose>
					<c:when test="${page.pageIndex != 0}">
						<a href="<%=request.getContextPath()%>/events/page?maxResult=${page.maxResult}&pageIndex=0">第一页</a>
					</c:when>
					<c:otherwise>
					第一页
				</c:otherwise>
					</c:choose>
					|&nbsp;
					<c:choose>
						<c:when test="${page.pageIndex > 0}">
							<a href="<%=request.getContextPath()%>/events/page?maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
						</c:when>
						<c:otherwise>
					上一页
				</c:otherwise>
					</c:choose>
					&nbsp;|&nbsp;
					<c:choose>
						<c:when test="${page.pageIndex + 1 < page.totalPage}">
							<a href="<%=request.getContextPath()%>/events/page?maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
						</c:when>
						<c:otherwise>
					下一页
				</c:otherwise>
					</c:choose>
					&nbsp;|&nbsp;
					<c:choose>
						<c:when test="${page.pageIndex + 1 != page.totalPage}">
							<a href="<%=request.getContextPath()%>/events/page?maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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
							window.location.href="<%=request.getContextPath()%>/events/page?maxResult=${page.maxResult}&pageIndex=" + pageindex;
						}
					</script>
				</div>
				<div style="float: left; font-family: Tahoma">共
					${page.totalResult} 条记录，每页 ${page.maxResult} 条，当前第
					${page.pageIndex + 1} / ${page.totalPage} 页
				</div>
				</td>
			</tr>
			<tr>
				<td colspan="10"><a href="<%=request.getContextPath()%>/events/addUI"><font style="font-size: 20px;"> 新增</font></a></td>
			</tr>
		</table>
		</div>
	</body>
</html>