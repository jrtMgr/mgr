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
					<form action="<%=request.getContextPath()%>/datastat/buyerloss"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
								  <td align="right">老用户购彩时间:</td>
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
									</td>
								  <td align="right"><!--  老用户最低购彩额(分):--></td>
								  <td><!--<input name="amt" type="text" style="width: 180px" value="${amt}"
										id="amt" class="inputText" onfocus="this.select();" />--></td>								 
								</tr>
								<tr>
								<td align="right">当前用户购彩时间:</td>
									<td><input id="starttime2" name="starttime2"  value="${starttime2}"
										class="inputText" type="text" style="width: 80px;"
										onclick="DateTime.onImageMouseDown(event,'Calendar','starttime2');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','starttime2');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
										-&nbsp;<input id="endtime2" name="endtime2" value="${param.endtime2}"
										class="inputText" type="text" ztype="date"
										style="width: 80px;"
										 onclick="DateTime.onImageMouseDown(event,'Calendar','endtime2');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','endtime2');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
								</td>
								 <td align="right"></td>
								  <td align="center"></td>	
								  </tr>
								  <tr>
								<td align="right">新用户注册时间:</td>
									<td><input id="starttime3" name="starttime3"  value="${starttime3}"
										class="inputText" type="text" style="width: 80px;"
										onclick="DateTime.onImageMouseDown(event,'Calendar','starttime3');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','starttime3');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
										-&nbsp;<input id="endtime3" name="endtime3" value="${param.endtime3}"
										class="inputText" type="text" ztype="date"
										style="width: 80px;"
										 onclick="DateTime.onImageMouseDown(event,'Calendar','endtime3');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','endtime3');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
								</td>
								 <td align="right"></td>
								  <td align="center"><input type="submit" value="查询" class="inputButton">&nbsp;&nbsp;&nbsp;
									<input type="button" value="重置" onclick="javascript:location.href='<%=request.getContextPath() %>/datastat/buyerloss';" class="inputButton">
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
					<tbody>
						<tr class="dataTableHead">
						     <td width="100%" class="thOver" colspan="5"><strong>用户流失率</strong></td>
						</tr>
						<tr><td>当前用户数：</td><td title="${currentusers}">${currentusers}</td></tr>
						<tr><td>老用户数：</td><td title="${oldusers}">${oldusers}</td></tr>
						<tr><td>当前老用户数：</td><td title="${currentoldusers}">${currentoldusers}</td></tr>
						<tr><td>新用户数：</td><td title="${newusers}">${newusers}</td></tr>
						<tr><td>其他用户数：</td><td title="${currentotherusers}">${currentotherusers}</td></tr>
						<tr class="dataTableHead">
						     <td width="100%" class="thOver" colspan="5"><strong>销量流失率</strong></td>
						</tr>
						<tr><td>当前销量（单位分）：</td><td title="${currentamt}">${currentamt}</td></tr>
						<tr><td>老用户销量（单位分）：</td><td title="${oldamt}">${oldamt}</td></tr>
						<tr><td>当前老用户销量（单位分）：</td><td title="${currentoldamt}">${currentoldamt}</td></tr>
						<tr><td>新用户销量（单位分）：</td><td title="${newamt}">${newamt}</td></tr>
						<tr><td>其他用户销量（单位分）：</td><td title="${currentotheramt}">${currentotheramt}</td></tr>								    		     
				</tbody>
				</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>