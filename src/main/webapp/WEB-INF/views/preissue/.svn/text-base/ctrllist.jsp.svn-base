<%@page import="com.ruyicai.mgr.consts.TlotCtrlState"%>
<%@page import="com.ruyicai.mgr.domain.Tlotctrl"%>
<%@page import="com.ruyicai.mgr.lot.LotCenter"%>
<%@page import="com.ruyicai.mgr.consts.LotState"%>
<%@page import="com.ruyicai.mgr.domain.Tlot"%>
<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.domain.Twininfo"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" 
	src="<%=request.getContextPath()%>/js/jquery.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Dialog.js"></script>	
<script type="text/javascript">	
</script>	
</head>
<body>
	<table width="100%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead">
				<td width="2%" height="30" class="thOver"></td>
				<td width="10%" height="30" class="thOver"><strong>彩种</strong>
				</td>
				<td width="12%" height="30" class="thOver"><strong>名称</strong>
				</td>
				<td width="20%" height="30" class="thOver"><strong>期号</strong>
				</td>
				<td width="25%" height="30" class="thOver"><strong>开始时间</strong>
				</td>
				<td width="25%" height="30" class="thOver"><strong>封期时间</strong>
				</td>
				<td width="10%" height="30" class="thOver"><strong>状态</strong>
				</td>
			</tr>
			<c:forEach items="${tlotctrls}" var="tlotctrl" varStatus="num">
			<%
				Tlotctrl lotctrl = (Tlotctrl)pageContext.getAttribute("tlotctrl");
			String lottype = Lottype.getName(lotctrl.getId().getLotno());
			%>
				<tr>
					<td title="${num.count}">${num.count}</td>
					<td title="${tlotctrl.id.lotno}">
					${tlotctrl.id.lotno}
					</td>
					<td><%=lottype %>
					</td>
					<td title="${tlotctrl.id.batchcode}">${tlotctrl.id.batchcode}</td>
					<td>
						<fmt:formatDate value="${tlotctrl.starttime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<fmt:formatDate value="${tlotctrl.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<%=TlotCtrlState.getMemo(lotctrl.getState()) %>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>