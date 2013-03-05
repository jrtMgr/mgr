<%@page import="com.ruyicai.mgr.controller.OpentimeController"%>
<%@page import="java.util.Map"%>
<%@page import="com.ruyicai.mgr.lot.LotCenter"%>
<%@page import="com.ruyicai.mgr.domain.Twininfo"%>
<%@page import="com.ruyicai.mgr.consts.TlotCtrlState"%>
<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.domain.Tlotctrl"%>
<%@page import="java.util.List"%>
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
					<form action="<%=request.getContextPath()%>/opentime/list"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">彩种:</td>
									<td><select name="lotno" style="width:100px">
											<c:forEach var="lot" items="<%=com.ruyicai.mgr.lot.Lottype.getMap().entrySet() %>">
												<option value="${lot.key}" <c:if test="${lot.key eq param.lotno}">selected</c:if>>${lot.value}</option>
											</c:forEach>
									</select>
									</td>
									<td>期号</td>
									<td><input type="text" value="${param.batchcode}" name="batchcode" id="batchcode" >
									</td>
									<td align="right">开奖时间:</td>
									<td><input id="starttime" name="starttime" value="${param.starttime}"
										class="inputText" type="text" style="width: 80px;"
										 onclick="DateTime.onImageMouseDown(event,'Calendar','starttime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','starttime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
										-&nbsp;<input id="endtime" name="endtime"  value="${param.endtime}"
										class="inputText" type="text" ztype="date"
										style="width: 80px;"
										onclick="DateTime.onImageMouseDown(event,'Calendar','endtime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','endtime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
										(必填)
									</td>
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
								<td width="2%" height="30" class="thOver"></td>
								<td width="6%" class="thOver"><strong>彩票中心</strong>
								</td>
								<td width="6%" class="thOver"><strong>基本号码</strong>
								</td>
								<td width="6%" class="thOver"><strong>特殊号码</strong>
								</td>
								<td width="9%" class="thOver"><strong>开奖时间</strong>
								</td>
							</tr>
							<%
								List<Twininfo> list = (List<Twininfo>) request.getAttribute("list");
								if (null != list) {
							%>
							<c:forEach items="${list}" var="twin" varStatus="num">
								<%Twininfo twininfo = (Twininfo)pageContext.getAttribute("twin");
								String center = LotCenter.getName(twininfo.getId().getAgencyno());
								%>
								<tr>
									<td title="${num.count}">${num.count}</td>
									<td><%=center %></td>
									<td>${twin.winbasecode}</td>
									<td>${twin.winspecialcode}</td>
									<td title="${twin.agencyopentime}"><fmt:formatDate value="${twin.agencyopentime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								</tr>
							</c:forEach>
							<% } %>
						</tbody>
					</table>
				</td>
			</tr>
			
			<tr>
				<td	style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2"	class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="2%" height="30" class="thOver"></td>
								<td width="6%" class="thOver"><strong>彩票中心</strong>
								</td>
								<td width="6%" class="thOver"><strong>中奖号码</strong>
								</td>
								<td width="9%" class="thOver"><strong>开奖时间</strong>
								</td>
								<td width="9%" class="thOver"><strong>权重</strong>
								</td>
							</tr>
							<c:forEach items="${list2}" var="pc" varStatus="num">
								<%
								Map<String, Object> pc = (Map<String, Object>)pageContext.getAttribute("pc");
								String center = OpentimeController.map.get((pc.get("agencyno").toString()));
								%>
								<tr>
									<td title="${num.count}">${num.count}</td>
									<td><%=center %></td>
									<td>${pc.wincode}</td>
									 <td>${pc.codedate}</td>
									 <td>${pc.weight}</td>
								 </tr>
							</c:forEach>
							<% //} %>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>