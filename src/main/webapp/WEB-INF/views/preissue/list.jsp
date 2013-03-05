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
					<form action="<%=request.getContextPath()%>/preissue/list"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">彩种:</td>
									<td><select name="lotno" style="width:100px">
											<option value="">全部</option>
											<c:forEach var="lot" items="<%=com.ruyicai.mgr.lot.Lottype.getMap().entrySet() %>">
												<option value="${lot.key}" <c:if test="${lot.key eq param.lotno}">selected</c:if>>${lot.value}</option>
											</c:forEach>
									</select>
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
								<td width="5%" class="thOver"><strong>彩种编号</strong>
								</td>
								<td width="6%" class="thOver"><strong>彩种名称</strong>
								</td>
								<td width="6%" class="thOver"><strong>期号</strong>
								</td>
								<td width="5%" class="thOver"><strong>状态</strong>
								</td>
								<td width="9%" class="thOver"><strong>开期时间</strong>
								</td>
								<td width="9%" class="thOver"><strong>结束投注时间</strong>
								</td>
								<td width="9%" class="thOver"><strong>合买终止时间</strong>
								</td>
								<td width="9%" class="thOver"><strong>封期时间</strong>
								</td>
								<td width="9%" class="thOver"><strong>创建时间</strong>
								</td>
								
								<td width="6%" class="thOver"><strong>操作</strong>
								</td>
							</tr>
							<c:forEach items="${tlotctrls}" var="tlotctrl" varStatus="num">
								<%
									Tlotctrl tlotctrl = (Tlotctrl) pageContext.getAttribute("tlotctrl");
									pageContext.setAttribute("tlotctrl", tlotctrl);
								%>
								<tr>
									<td title="${num.count}">${num.count}</td>
									<td >${tlotctrl.id.lotno}</td>
									<td title="<%=Lottype.getName(tlotctrl.getId().getLotno())%>"><%=Lottype.getName(tlotctrl.getId().getLotno())%></td>
									<td>${tlotctrl.id.batchcode}</td>
									<td title="<%=TlotCtrlState.getMemo(tlotctrl.getState())%>"><%=TlotCtrlState.getMemo(tlotctrl.getState())%></td>
									<td title="${tlotctrl.starttime}"><fmt:formatDate value="${tlotctrl.starttime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td title="${tlotctrl.endbettime}"><fmt:formatDate value="${tlotctrl.endbettime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td title="${tlotctrl.hemaiendtime}"><fmt:formatDate value="${tlotctrl.hemaiendtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td title="${tlotctrl.endtime}"><fmt:formatDate value="${tlotctrl.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td title="${tlotctrl.createtime}"><fmt:formatDate value="${tlotctrl.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>	<a href="<%=request.getContextPath()%>/preissue/editUI?lotno=${tlotctrl.id.lotno}&batchcode=${tlotctrl.id.batchcode}&agencyno=${tlotctrl.id.agencyno}">修改</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
			
			<tr><td style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px; font-size: 14px;"><a href="<%=request.getContextPath()%>/preissue/addUI">新增未开期</a></td></tr>
		</tbody>
	</table>
</body>
</html>