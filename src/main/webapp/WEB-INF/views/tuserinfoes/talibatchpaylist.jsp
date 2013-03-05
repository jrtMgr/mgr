<%@page import="java.util.Map"%>
<%@page import="com.ruyicai.mgr.consts.TransactionState"%>
<%@page import="com.ruyicai.mgr.domain.Nineteenpay"%>
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
					<form action="<%=request.getContextPath()%>/tuserinfoes/talibatchpaylist"
						method="post">
						<input type="hidden" name="userno1" value="${userno}">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">批次号:</td>
									<td><input name="id" type="text" style="width: 180px" value="${param.id}"
										id="id" class="inputText" onfocus="this.select();" /></td>
									<td align="right">用户姓名:</td>
									<td><input name="name" type="text" style="width: 120px" value="${param.name}"
										id="name" class="inputText" onfocus="this.select();" /></td>									
									<td align="right">用户账户:</td>
									<td><input name="account" type="text" style="width: 180px" value="${param.account}"
										id="account" class="inputText" onfocus="this.select();" /></td>	</tr>								
							    <tr>			
									<td align="right">支付时间:</td>
									<td><input id="starttime" name="starttime" value="${starttime}"
										class="inputText" type="text" style="width: 80px;"
										 onclick="DateTime.onImageMouseDown(event,'Calendar','starttime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','starttime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
										-&nbsp;<input id="endtime" name="endtime" value="${endtime}"
										class="inputText" type="text" ztype="date"
										style="width: 80px;"
										onclick="DateTime.onImageMouseDown(event,'Calendar','endtime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','endtime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
									</td>									
									<td align="right">显示行数:</td>
									<td><select id="maxResult" name="maxResult" style="width:60px">
											<option value="15">15</option>
											<option value="30">30</option>
											<option value="50">50</option>
									</select></td>
									<td></td>
									<td align="center"><input type="submit" value="查询" class="inputButton">&nbsp;&nbsp;<a onclick="javascript:history.go(-1)">返回</a></td>
								</tr>
								<tr><td colspan="6">付款详细数据格式：流水号1^收款方账号1^收款账号姓名1^付款金额1^备注说明1|流水号2^收款方账号2^收款账号姓名2^付款金额2^备注说明2。每条记录以“|”间隔。</td></tr>
								<tr><td colspan="6">转账成功详细数据：流水号^收款方账号^收款账号姓名^付款金额^成功标识(S)^成功原因(null)^支付宝内部流水号^完成时间。每条记录以“|”间隔。</td></tr>
								<tr><td colspan="6">转账失败详细数据：流水号^收款方账号^收款账号姓名^付款金额^失败标识(F)^失败原因^支付宝内部流水号^完成时间。每条记录以“|”间隔。</td></tr>
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
								<td width="2%" height="30" class="thOver"></td>
								<td width="5%" class="thOver" title="批次号"><strong>批次号</strong></td>
								<td width="25%" class="thOver" title="付款详细数据"><strong>付款详细数据</strong></td>
								<td width="2%" class="thOver" title="总笔数"><strong>总笔数</strong></td>
								<td width="6%" class="thOver" title="总金额（元）"><strong>总金额（元）</strong></td>
								<td width="8%" class="thOver" title="支付日期"><strong>支付日期</strong></td>
								<td width="8%" class="thOver" title="支付通知日期"><strong>支付通知日期</strong></td>
								<td width="22%" class="thOver" title="转账成功详细数据"><strong>转账成功详细数据</strong></td>
								<td width="22%" class="thOver" title="转账失败详细数据"><strong>转账失败详细数据</strong></td>								
							</tr>
								<c:forEach items="${page.list}" var="talibatchpay" varStatus="num">								
									<tr>
										<td title="${num.count}">${num.count}</td>
										<td title="${talibatchpay.id}">${talibatchpay.id}</td>
										<td title="${talibatchpay.detailData}">${talibatchpay.detailData}</td>									
										<td title="${talibatchpay.batchNum}">${talibatchpay.batchNum}</td>
										<td title="${talibatchpay.batchFee}">${talibatchpay.batchFee}</td>
										<td title="${talibatchpay.payDate}"><fmt:formatDate value="${talibatchpay.payDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td title="${talibatchpay.notifyTime}"><fmt:formatDate value="${talibatchpay.notifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td title="${talibatchpay.successDetails}">${talibatchpay.successDetails}</td>
										<td title="${talibatchpay.failDetails}">${talibatchpay.failDetails}</td>										
									</tr>
								</c:forEach>												
							<tr>
								<td align="left" id="dg1_PageBar" colspan="11"><div
										style="float: right; font-family: Tahoma">
										<c:choose>
											<c:when test="${page.pageIndex != 0}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/talibatchpaylist?transactionid=${transactionid}&userno=${userno}&cardtype=${cardtype}&cardno=${cardno}&cardpwd=${cardpwd}&starttime=${param.starttime}&endtime=${param.endtime}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
											</c:when>
											<c:otherwise>
										第一页
									</c:otherwise>
										</c:choose>
										|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex > 0}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/talibatchpaylist?transactionid=${transactionid}&userno=${userno}&cardtype=${cardtype}&cardno=${cardno}&cardpwd=${cardpwd}&starttime=${param.starttime}&endtime=${param.endtime}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
											</c:when>
											<c:otherwise>
										上一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 < page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/talibatchpaylist?transactionid=${transactionid}&userno=${userno}&cardtype=${cardtype}&cardno=${cardno}&cardpwd=${cardpwd}&starttime=${param.starttime}&endtime=${param.endtime}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
											</c:when>
											<c:otherwise>
										下一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 != page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/talibatchpaylist?transactionid=${transactionid}&userno=${userno}&cardtype=${cardtype}&cardno=${cardno}&cardpwd=${cardpwd}&starttime=${param.starttime}&endtime=${param.endtime}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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
												window.location.href="<%=request.getContextPath()%>/tuserinfoes/talibatchpaylist?transactionid=${transactionid}&userno=${userno}&cardtype=${cardtype}&cardno=${cardno}&cardpwd=${cardpwd}&starttime=${param.starttime}&endtime=${param.endtime}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
											}
											function goback() {
												window.location.href="<%=request.getContextPath()%>/tuserinfoes/talibatchpaylist?userno=${userno}";
											}
										</script>
									</div>
									<div style="float: left; font-family: Tahoma">共
										${page.totalResult} 条记录，每页 ${page.maxResult} 条，当前第
										${page.pageIndex + 1} / ${page.totalPage} 页</div></td>
							</tr>
							<tr>
							<td colspan="16"><input type="button" value=" 返 回 " class="inputButton" onclick="goback();"></td>
							</tr>
						</tbody>						
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>