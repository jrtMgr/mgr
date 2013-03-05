<%@page import="com.ruyicai.mgr.consts.TransactionState"%>
<%@page import="com.ruyicai.mgr.domain.Ttransaction"%>
<%@page import="com.ruyicai.mgr.charge.ChargeType"%>
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
					<form action="<%=request.getContextPath()%>/tuserinfoes/tchargelist"
						method="post">
						<input type="hidden" name="userno" value="${userno}">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">充值方式:</td>
									<td><select name="bankid" style="width:60px">
											<option value="">全部</option>
											<c:forEach
												items="<%=com.ruyicai.mgr.charge.ChargeType.getMap().entrySet() %>"
												var="center">
												<option value="${center.key}">${center.value}</option>
											</c:forEach>
									</select></td>
									<td align="right">充值时间:</td>
									<td><input id="starttime" name="starttime"
										class="inputText" type="text" style="width: 80px;"
										>
										<img vspace="1" align="absmiddle"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
										-&nbsp;<input id="endtime" name="endtime"
										class="inputText" type="text" ztype="date"
										style="width: 80px;"
										>
										<img vspace="1" align="absmiddle"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
									</td>
									<td align="right">状态:</td>
									<td><select name="state" style="width:80px">
											<option value="110">全部</option>
											<option value="0">未处理</option>
											<option value="2">失败</option>
											<option value="1">成功</option>
											<option value="3">处理中</option>
									</select></td>
									<td align="right">显示行数:</td>
									<td><select id="maxResult" name="maxResult" style="width:60px">
											<option value="15">15</option>
											<option value="30">30</option>
											<option value="50">50</option>
									</select></td>
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
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2"
						class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="4%" height="30" class="thOver"></td>
								<td width="25%" class="thOver"><strong>交易ID</strong></td>
								<td width="6%" class="thOver"><strong>用户编号</strong></td>
								<td width="6%" class="thOver"><strong>充值方式</strong></td>
								<td width="12%" class="thOver"><strong>充值时间</strong></td>
								<td width="5%" class="thOver"><strong>金额</strong></td>
								<td width="5%" class="thOver"><strong>手续费</strong></td>
								<td width="5%" class="thOver"><strong>状态</strong></td>
								<td width="9%" class="thOver"><strong>银行返回</strong></td>
								<td width="8%" class="thOver"><strong>备注</strong></td>
								<td width="5%" class="thOver"><strong>渠道</strong></td>
							</tr>
							<% 
								Page<Ttransaction> page2 = (Page<Ttransaction>)request.getAttribute("page");
								if(null != page2 && null != page2.getList()) {
									
								
								Object[] objs = (Object[])page2.getValue();
								BigDecimal sumamt = BigDecimal.ZERO;
								BigDecimal sumprizeamt = BigDecimal.ZERO;
								
							%>
								<c:forEach items="${page.list}" var="transaction" varStatus="num">
									<% 
									Ttransaction ttransaction = (Ttransaction)pageContext.getAttribute("transaction");
									String bank = ChargeType.getMemo(ttransaction.getBankid());
									bank = StringUtil.isEmpty(bank) ? "无" : bank;
									String state = TransactionState.getMemo(ttransaction.getState());
									sumamt = sumamt.add(ttransaction.getAmt());
									sumprizeamt = sumprizeamt.add(ttransaction.getFee());
								%>
									<tr>
										<td title="${num.count}">${num.count}</td>
										<td title="${transaction.id}">${transaction.id}</td>
										<td title="${transaction.userno}">${transaction.userno}</td>
										<td title="<%=bank%>"><%=bank%></td>
										<td title="${transaction.plattime}"><fmt:formatDate value="${transaction.plattime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td title="${transaction.amt}">${transaction.amt}</td>
										<td title="${transaction.fee}">${transaction.fee}</td>
										<td title="<%=state%>"><%=state%></td>
										<td title="${transaction.retmemo}">${transaction.retmemo}</td>
										<td title="${transaction.memo}">${transaction.memo}</td>
										<td title="${transaction.channel}">${transaction.channel}</td>
									</tr>
								</c:forEach>
							<tr>
								<td></td>
								<td>本页合计</td>
								<td></td>
								<td></td>
								<td></td>
								<td title="<%=sumamt%>"><%=sumamt%></td>
								<td title="<%=sumprizeamt%>"><%=sumprizeamt%></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td>总合计</td>
								<td></td>
								<td></td>
								<td></td>
								<td title="<%=null == objs[0] ? 0 : objs[0]%>"><%=null == objs[0] ? 0 : objs[0]%></td>
								<td title="<%=null == objs[1] ? 0 : objs[1]%>"><%=null == objs[1] ? 0 : objs[1]%></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="left" id="dg1_PageBar" colspan="11"><div
										style="float: right; font-family: Tahoma">
										<c:choose>
											<c:when test="${page.pageIndex != 0}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/tchargelist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&channeltype=${param.channeltype}&bankid=${param.bankid}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
											</c:when>
											<c:otherwise>
										第一页
									</c:otherwise>
										</c:choose>
										|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex > 0}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/tchargelist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&channeltype=${param.channeltype}&bankid=${param.bankid}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
											</c:when>
											<c:otherwise>
										上一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 < page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/tchargelist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&channeltype=${param.channeltype}&bankid=${param.bankid}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
											</c:when>
											<c:otherwise>
										下一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 != page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/tchargelist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&channeltype=${param.channeltype}&bankid=${param.bankid}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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
												window.location.href="<%=request.getContextPath()%>/tuserinfoes/tchargelist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&channeltype=${param.channeltype}&bankid=${param.bankid}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
											}
											function goback() {
												window.location.href="<%=request.getContextPath()%>/tuserinfoes/info?userno=${userno}";
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
						<%} %>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>