<%@page import="com.ruyicai.mgr.util.DateUtil"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Date"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
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
String result = (String)request.getAttribute("result");
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
function confirmupdate() {
	if(window.confirm("确定要修改吗?")) {
		return true;
	}
	return false;
}
</script>
<body><div style="margin-top: 10px;">
		<table width="100%" cellspacing="0" cellpadding="2"
			class="dataTable">
				<tr class="dataTableHead">
					<td width="4%" class="thOver"><strong>积分类型</strong></td>
					<td width="8%" class="thOver"><strong>项目事件</strong></td>
					<td width="5%" class="thOver"><strong>次数</strong></td>
					<td width="25%" class="thOver"><strong>增加积分</strong></td>
					<td width="8%" class="thOver"><strong>时间</strong></td>
					<td width="5%" class="thOver"><strong>状态</strong></td>
					<td width="3%" class="thOver"><strong>操作</strong></td>
				</tr>
				<c:forEach items="${list}" var="st">
					<tr>
					<form action="<%=request.getContextPath()%>/scoretype/saveOrUpdateScoreType" method="post" id="form1" onsubmit="return confirmupdate();">
						<input name="id" type="hidden" value="${tlottype.id.id}"> 
						<td><input style="width: 20px;" type="text" value="${st.scoreType}" name="scoreType" readonly="readonly"/></td>
						<td><input style="width: 100px;" type="text" value="${st.memo}" name="memo"/></td>
						<td><input style="width: 30px;" type="text" value="${st.times}" name="times"/></td>
						<td><input style="width: 400px;" type="text" value='${st.scoreJson}' name="scoreJson"/> </td>
						<td>
						<%
							Map m = (Map)pageContext.getAttribute("st");
							Long l = (Long)m.get("modifyTime");
							out.print(DateUtil.format(new Date(l)));
						%>
						</td>
						<td>
						<select name="state">
							<option value="1" <c:if test="${st.state == 1}"> selected="selected"</c:if>>启用</option>
							<option value="2" <c:if test="${st.state == 2}"> selected="selected"</c:if>>关闭</option>
						</select>
						</td>
						<td><input type="submit" value="修改"/></td>
					</form>
					</tr>
				</c:forEach>
				
		</table>
		</div>
</body>
</html>