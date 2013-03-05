<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
<%@page import="com.ruyicai.mgr.consts.SettleFlagState"%>
<%@page import="com.ruyicai.mgr.consts.BetType"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.ruyicai.mgr.domain.Torder"%>
<%@page import="com.ruyicai.mgr.lot.LotCenter"%>
<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.consts.UserState"%>
<%@page import="com.ruyicai.mgr.domain.Tuserinfo"%>
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
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2"
						class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="4%" height="30" class="thOver"></td>
								<td width="14%" class="thOver"><strong>编号</strong></td>
								<td width="8%" class="thOver"><strong>期号</strong></td>
								<td width="7%" class="thOver"><strong>采种</strong></td>
								<td width="7%" class="thOver"><strong>订单金额(分)</strong></td>
								<td width="7%" class="thOver"><strong>中奖金额(分)</strong></td>
								<td width="6%" class="thOver"><strong>付款状态</strong></td>
								<td width="6%" class="thOver"><strong>状态</strong></td>
								<td width="12%" class="thOver"><strong>创建时间</strong></td>
								<td width="7%" class="thOver"><strong>所属用户编号</strong></td>
								<td width="7%" class="thOver"><strong>购买者编号</strong></td>
								<td width="15%" class="thOver"><strong>注码</strong></td>
							</tr>
							<c:forEach items="${torders}" var="torder" varStatus="num">
									<% 
									Torder torder = (Torder)pageContext.getAttribute("torder");
									String lottype = Lottype.getName(torder.getLotno());
									String state = "";
									if(torder.getOrderstate().intValue() == 0) {
										state = "等待处理";
									}else if(torder.getOrderstate().intValue() == 1) {
										state = "已购买";
									}else if(torder.getOrderstate().intValue() == 2) {
										state = "空订单";
									}else if(torder.getOrderstate().intValue() == 3) {
										state = "处理失败";
									}
									String paytype = "";
									if(torder.getPaytype().intValue() == 0){
										paytype = "未付款";
									}else if(torder.getPaytype().intValue() == 1){
										paytype = "已付款";
									}
								%>
									<tr>
										<td title="${num.count}">${num.count}</td>
										<td title="${torder.id}">${torder.id}</td>
										<td title="${torder.batchcode}">${torder.batchcode}</td>
										<td title="<%=lottype%>"><%=lottype%></td>
										<td title="${torder.amt}">${torder.amt}</td>
										<td title="${torder.orderprizeamt}">${torder.orderprizeamt}</td>
										<td title="<%=paytype%>"><%=paytype%></td>
										<td title="<%=state%>"><%=state%></td>
										<td title="${torder.createtime}"><fmt:formatDate value="${torder.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td title="${torder.userno}">${torder.userno}</td>
										<td title="${torder.buyuserno}">${torder.buyuserno}</td>
										<td title="${torder.betcode}">${torder.betcode}</td>
									</tr>
								</c:forEach>
							<tr>
							<td colspan="16"><input type="button" value=" 返 回 " class="inputButton" onclick="window.history.back();"></td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>