<%@page import="com.ruyicai.mgr.domain.Tdnabind"%>
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
					<form action="<%=request.getContextPath()%>/tdnabind/list"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">用户id:</td>
									<td><input name="userno" type="text" style="width: 110px" value="${userno}"
										id="userid" class="inputText" onfocus="this.select();" /></td>
									<td align="right">电话号码:</td>
									<td><input name="mobileid" type="text" style="width: 110px" value="${mobileid}"
										id="userid" class="inputText" onfocus="this.select();" /></td>	
									<td align="right">状态:</td>
									<td><select name="state" style="width:100px">
											<option value="">--请选择--</option>
											<option value="1" <c:if test='${1 == state}'>selected</c:if>>绑定</option>
											<option value="0" <c:if test='${0 == state}'>selected</c:if>>未绑定</option>
									</select></td>
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
								<td width="3%" height="30" class="thOver"></td>
								<td width="8%" class="thOver"><strong>手机号 </strong></td>
								<td width="7%" class="thOver"><strong>开户姓名 </strong></td>
								<td width="11%" class="thOver"><strong>银行卡号 </strong></td>
								<td width="12%" class="thOver"><strong>开户身份证号 </strong></td>
								<td width="8%" class="thOver"><strong>开户行所在地 </strong></td>
								<td width="16%" class="thOver"><strong>开户证件所在地 </strong></td>
								<td width="11%" class="thOver"><strong>首次绑定时间 </strong></td>
								<td width="7%" class="thOver"><strong>状态</strong></td>
								<td width="9%" class="thOver"><strong>银行名称</strong></td>
								<td width="8%" class="thOver"><strong>修改</strong></td>
							</tr>
								<c:forEach items="${page.list}" var="dna" varStatus="num">
									<tr>
										<td title="${num.count}">${num.count}</td>
										<td title="${dna.mobileid}">${dna.mobileid}</td>
										<td title="${dna.name}">${dna.name}</td>
										<td title="${dna.bankcardno}">${dna.bankcardno}</td>
										<td title="${dna.certid}">${dna.certid}</td>
										<td title="${dna.bankaddress}">${dna.bankaddress}</td>
										<td title="${dna.certidaddress}">${dna.certidaddress}</td>
										<td title="${dna.bindtime}"><fmt:formatDate value="${dna.bindtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td title="${dna.state}">
											<c:if test="${dna.state == 0}">未绑定</c:if>
											<c:if test="${dna.state == 1 }">绑定</c:if>
										</td>
										<td>${dna.bankname}</td>
										<td><a href="<%=request.getContextPath()%>/tdnabind/editUI?id=${dna.id}">修改</a></td>
									</tr>
								</c:forEach>
							<tr>
								<td align="left" id="dg1_PageBar" colspan="17"><div
										style="float: right; font-family: Tahoma">
										<c:choose>
											<c:when test="${page.pageIndex != 0}">
												<a
													href="<%=request.getContextPath()%>/tdnabind/list?state=${state}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
											</c:when>
											<c:otherwise>
										第一页
									</c:otherwise>
										</c:choose>
										|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex > 0}">
												<a
													href="<%=request.getContextPath()%>/tdnabind/list?state=${state}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
											</c:when>
											<c:otherwise>
										上一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 < page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tdnabind/list?state=${state}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
											</c:when>
											<c:otherwise>
										下一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 != page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tdnabind/list?state=${state}&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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