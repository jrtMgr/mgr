<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
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

function deletec(id){
	if(!window.confirm("确定删除吗?")) {
		return;
	}
	var url = "<%=request.getContextPath()%>/channel/deletechannel?id="+id;
	window.location.href = url;
}
</script>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form action="<%=request.getContextPath()%>/channel/list" method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td width="70" height="24px" align="right">
										业务名称：
									</td>
									<td width="120" align="left">
										<select name="ywid" id="ywid" class="select_text_long">
											<option value="" >
												---全部---
											</option>
											<c:forEach var="yw" items="${ywlist}" varStatus="it">
												<option value="${yw.id}" <c:if test="${yw.id eq param.ywid}">selected</c:if>>
													${yw.name}
												</option>
											</c:forEach>
										</select>
									</td>
									<td align="right">渠道ID:</td>
									<td><input name="id" type="text" style="width: 80px" value="${param.id}"
										 class="inputText" onfocus="this.select();" /></td>
									<td align="right">渠道名称:</td>
									<td><input name="name" type="text" style="width: 80px" value="${param.name}"
										id="name" class="inputText" onfocus="this.select();" /></td>
									<td align="right">用户名:</td>
									<td><input name="username" type="text" style="width: 80px" value="${param.username}"
										 class="inputText" onfocus="this.select();" /></td>
									<td align="right">显示行数:</td>
									<td><select id="maxResult" name="maxResult" style="width:60px">
											<option value="15" <c:if test='${"15" eq page.maxResult}'>selected</c:if>>15</option>
											<option value="30" <c:if test='${"30" eq page.maxResult}'>selected</c:if>>30</option>
											<option value="50" <c:if test='${"50" eq page.maxResult}'>selected</c:if>>50</option>
											<option value="100" <c:if test='${"100" eq page.maxResult}'>selected</c:if>>100</option>
									</select></td>
									<td></td>
									<td></td>	
									<td align="right" width="100px"><input type="submit" value="查询" class="inputButton">&nbsp;&nbsp;&nbsp;
									<input type="button" value="重置" onclick="javascript:location.href='<%=request.getContextPath() %>/channel/page';" class="inputButton">
									</td>
								</tr>
							</table>
						</div>
					</form>
				</td>
			</tr>
			<tr>
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2"
						class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="3%" height="30" class="thOver"></td>
								<td width="6%" class="thOver"><strong>渠道ID</strong></td>
								<td width="10%" class="thOver"><strong>业务名称</strong></td>
								<td width="12%" class="thOver"><strong>渠道名称</strong></td>
								<td width="15%" class="thOver"><strong>创建时间</strong></td>
								<td width="6%" class="thOver"><strong>状态</strong></td>
								<td width="4%" class="thOver"><strong>备注</strong></td>
								<td width="4%" class="thOver"><strong>联系方式</strong></td>
								<td width="7%" class="thOver"><strong>推广地址</strong></td>
								<td width="6%" class="thOver"><strong>是否扣量</strong></td>
								<td width="4%" class="thOver"><strong>注册率</strong></td>
								<td width="4%" class="thOver"><strong>充值率</strong></td>
								<td width="22%" class="thOver"><strong>操作</strong></td>
							</tr>
							<% 
								Page page2 = (Page)request.getAttribute("page");
								if(null != page2 && null != page2.getList()) {
							%>
								<c:forEach items="${page.list}" var="c" varStatus="num">
									<tr>
										<td title="${num.count}">${num.count}</td>
										<td title="${c.id}">${c.id}</td>
										<td>
											<c:forEach var="yw" items="${ywlist}" varStatus="it">
												<c:if test="${yw.id eq c.ywid}">${yw.name}</c:if>
											</c:forEach>
										</td>
										<td>${c.name}</td>
										<td title="${c.cjdate}">${c.cjdate}</td>
										<td title="${c.status}">${c.status}</td>
										<td title="${c.bz}">${c.bz}</td>
										<td title="${c.tel}">${c.tel}</td>
										<td title="${c.url}">${c.url}</td>
										<td title="${c.isopen}"><c:if test='${c.isopen == 1}'>扣量</c:if><c:if test='${c.isopen == 0}'>不扣量</c:if></td>
										<td title="${c.regist}">${c.regist}</td>
										<td title="${c.charge}">${c.charge}</td>
										<td><a onclick="deletec(${c.id})" style="cursor: hand;">删除</a> | 
											<a href="<%=request.getContextPath()%>/channel/editUI?id=${c.id}">编辑</a>|
											<a href="<%=request.getContextPath()%>/channel/channelDetailUI?id=${c.id}">结算数据详细</a> |
											<a href="<%=request.getContextPath()%>/channel/originalDetailUI?channelid=${c.id}">统计数据详细</a></td>
									</tr>
								</c:forEach>
							<tr>
								<td align="left" id="dg1_PageBar" colspan="17"><div
										style="float: right; font-family: Tahoma">
										<c:choose>
											<c:when test="${page.pageIndex != 0}">
												<a
													href="<%=request.getContextPath()%>/channel/list?ywid=${param.ywid}&starttime=${param.sellstarttime}&endtime=${param.sellendtime}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
											</c:when>
											<c:otherwise>
										第一页
									</c:otherwise>
										</c:choose>
										|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex > 0}">
												<a
													href="<%=request.getContextPath()%>/channel/list?ywid=${param.ywid}&starttime=${param.sellstarttime}&endtime=${param.sellendtime}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
											</c:when>
											<c:otherwise>
										上一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 < page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/channel/list?ywid=${param.ywid}&starttime=${param.sellstarttime}&endtime=${param.sellendtime}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
											</c:when>
											<c:otherwise>
										下一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 != page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/channel/list?ywid=${param.ywid}&starttime=${param.sellstarttime}&endtime=${param.sellendtime}&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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
												window.location.href="<%=request.getContextPath()%>/channel/list?type=${param.type}&id=${param.id}&channel=${param.channel}&subchannel=${param.subchannel}&starttime=${param.starttime}&endtime=${param.endtime}&lotno=${param.lotno}&agencyno=${param.agencyno}&batchcode=${param.batchcode}&prize=${param.prize}&state=${param.state}&bettype=${param.bettype}&sellstarttime=${param.sellstarttime}&sellendtime=${param.sellendtime}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
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
			<tr><td style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px; font-size: 14px;"><a href="<%=request.getContextPath()%>/channel/addUI">新增渠道</a></td></tr>
		</tbody>
	</table>
</body>
</html>