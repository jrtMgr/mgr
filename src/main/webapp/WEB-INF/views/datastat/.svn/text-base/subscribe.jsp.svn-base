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
					<form action="<%=request.getContextPath()%>/datastat/subscribe"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
								  <td align="right">购彩时间:</td>
									<td><input id="starttime" name="starttime"  value="${starttime}"
										class="inputText" type="text" style="width: 80px;"
										onclick="DateTime.onImageMouseDown(event,'Calendar','starttime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','starttime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
										-&nbsp;<input id="endtime" name="endtime" value="${param.endtime}"
										class="inputText" type="text" ztype="date"
										style="width: 80px;"
										 onclick="DateTime.onImageMouseDown(event,'Calendar','endtime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','endtime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
									</td><tr/>
								  <tr>
								   <td align="right">彩种编号:</td>
								   <td>
								   <textarea rows="20" cols="50" name="lotnos" id="lotnos">${lotnos}</textarea><font color="red">格式：每个彩种编号间以英文逗号分隔</font>
								   </td>
								   </tr>
								  <tr>								
								  <td align="right"></td>
								  <td align="center"><input type="submit" value="查询" class="inputButton">&nbsp;&nbsp;&nbsp;
									<input type="button" value="重置" onclick="javascript:location.href='<%=request.getContextPath() %>/datastat/subscribe';" class="inputButton">
								    &nbsp;&nbsp;&nbsp;<input type="button" value="返 回 " class="inputButton" onclick="javascript:location.href='<%=request.getContextPath() %>/datastat/list';;"/>
								  </td>	
								</tr>
							</table>
						</div>
					</form>
				</td>
			   <tr>
				<td	style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2" class="dataTable">
						<tbody>
						<tr class="dataTableHead">
						     <td width="100%" class="thOver" colspan="3"><strong>追号用户</strong></td>
						</tr>
						<tr><td>用户数：</td><td title="${users}" colspan="2">${users}</td></tr>
						<tr><td>追号金额数：</td><td title="${amt}" colspan="2">${amt}</td></tr>
						<tr class="dataTableHead">
						     <td width="100%" class="thOver" colspan="3"><strong>彩种编号</strong></td>
						</tr>
						<c:forEach items="${list}" var="o" varStatus="num">
						<tr>
						        <td title="${num.count}">${num.count}</td>
								<td title="${o[0]}">${o[0]}</td>
								<td title="${o[1]}">${o[1]}</td>
						</tr>		
						</c:forEach>	   	       		     
				</tbody>
				</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>