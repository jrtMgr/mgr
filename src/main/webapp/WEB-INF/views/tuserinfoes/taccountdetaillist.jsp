<%@page import="com.ruyicai.mgr.consts.TransactionState"%>
<%@page import="com.ruyicai.mgr.consts.TransactionType"%>
<%@page import="com.ruyicai.mgr.domain.Taccountdetail"%>
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

function ctrllist(torderid) {
	var d = new Dialog(torderid+"订单详情", "tuserinfoes/gotlotlist?torderid=" + torderid,
			1200, 210);
	d.show();
}
</script>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form action="<%=request.getContextPath()%>/tuserinfoes/taccountdetaillist"
						method="post">
						<input type="hidden" name="userno" value="${userno}">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">交易类型:</td>
									<td><select name="transactiontype" style="width:120px">
											<option value="">全部</option>
											<%
												TransactionType[] transactionTypes = TransactionType.values();
												for(TransactionType transactionType : transactionTypes) {
											%>	
													<option value="<%=transactionType.value()%>"><%=transactionType.memo() %></option>
											<%
												}
											 %>
									</select></td>
									<td align="right">平台时间:</td>
									<td><input id="starttime" name="starttime"
										class="inputText" type="text" style="width: 80px;"
										 onclick="DateTime.onImageMouseDown(event,'Calendar','starttime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','starttime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
										-&nbsp;<input id="endtime" name="endtime"
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
					<table width="180%" cellspacing="0" cellpadding="2"
						class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="2%" height="30" class="thOver"></td>
								<td width="10%" class="thOver"><strong>标识</strong></td>
								<td width="5%" class="thOver" title="变动金额（分）"><strong>变动金额（分）</strong></td>
								<td width="5%" class="thOver" title="余额（分）"><strong>余额（分）</strong></td>
								<td width="8%" class="thOver" title="-1：出帐1：进账"><strong>-1：出帐1：进账</strong></td>
								<td width="5%" class="thOver" title="可提现变动金额（分）"><strong>可提现变动金额（分）</strong></td>
								<td width="5%" class="thOver" title="可提现余额（分）"><strong>可提现余额</strong></td>
								<td width="5%" class="thOver" title="冻结变动金额（分）"><strong>冻结变动金额</strong></td>
								<td width="5%" class="thOver" title="余额，变动后的冻结金额（分）"><strong>余额，变动后的冻结金额（分）</strong></td>
								<td width="8%" class="thOver" title="账户科目明细描述"><strong>账户科目明细描述</strong></td>
								<td width="5%" class="thOver"><strong>用户标识</strong></td>
								<td width="15%" class="thOver"><strong>时间</strong></td>
								<td width="5%" class="thOver"><strong>交易状态</strong></td>
								<td width="20%" class="thOver"><strong>交易标识</strong></td>
								<td width="5%" class="thOver"><strong>交易标识</strong></td>
								<td width="5%" class="thOver"><strong>账户标识</strong></td>
								<td width="15%" class="thOver"><strong>流水号</strong></td>
								<td width="15%" class="thOver"><strong>otherID</strong></td>
							</tr>							
								<c:forEach items="${page.list}" var="taccountdetail" varStatus="num">
									<% 
									Taccountdetail taccountdetail = (Taccountdetail)pageContext.getAttribute("taccountdetail");									
									String state = TransactionState.getMemo(taccountdetail.getState());
									String type = 	TransactionType.getMemo(taccountdetail.getTtransactiontype());
									String str = "";
									if(!StringUtil.isEmpty(taccountdetail.getFlowno())) {										
										if (TransactionType.hemaifanjiang.value().equals(taccountdetail.getTtransactiontype()) || TransactionType.hemaiyongjin.value().equals(taccountdetail.getTtransactiontype()) || TransactionType.hemaikoukuan.value().equals(taccountdetail.getTtransactiontype())) {
											str = "<a href='" + request.getContextPath() + "/caselot/caselotListMg?search=" + taccountdetail.getFlowno() + "'>" + taccountdetail.getFlowno() + "</a>";											
										} else if (TransactionType.duijianghuakuan.value().equals(taccountdetail.getTtransactiontype()) || TransactionType.touzhu.value().equals(taccountdetail.getTtransactiontype()) || TransactionType.zhuihaotaocan.value().equals(taccountdetail.getTtransactiontype())) {
											str = "<a href=\"javascript:ctrllist(\'" + taccountdetail.getFlowno() + "\')\">" + taccountdetail.getFlowno() +"</a>";
										} else {
											str =  taccountdetail.getFlowno();
										}
									}
								%>
									<tr>
										<td title="${num.count}">${num.count}</td>
										<td title="${taccountdetail.id}">${taccountdetail.id}</td>
										<td title="${taccountdetail.amt}">${taccountdetail.amt}</td>
										<td title="${taccountdetail.balance}">${taccountdetail.balance}</td>
										<td title="${taccountdetail.blsign}">${taccountdetail.blsign}</td>
										<td title="${taccountdetail.drawamt}">${taccountdetail.drawamt}</td>
										<td title="${taccountdetail.drawbalance}">${taccountdetail.drawbalance}</td>
										<td title="${taccountdetail.freezeamt}">${taccountdetail.freezeamt}</td>
										<td title="${taccountdetail.freezebalance}">${taccountdetail.freezebalance}</td>
										<td title="${taccountdetail.memo}">${taccountdetail.memo}</td>
										<td title="${taccountdetail.mobileid}">${taccountdetail.mobileid}</td>
										<td title="${taccountdetail.plattime}"><fmt:formatDate value="${taccountdetail.plattime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td title="<%=state%>"><%=state%></td>
										<td title="${taccountdetail.ttransactionid}">${taccountdetail.ttransactionid}</td>
										<td title="<%=type%>"><%=type%></td>
										<td title="${taccountdetail.userno}">${taccountdetail.userno}</td>
										<td title="${taccountdetail.flowno}"><%=str%></td>									
										<td title="${taccountdetail.otherid}">${taccountdetail.otherid}</td>									
									</tr>
								</c:forEach>							
							<tr>
								<td align="left" id="dg1_PageBar" colspan="11"><div
										style="float: right; font-family: Tahoma">
										<c:choose>
											<c:when test="${page.pageIndex != 0}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/taccountdetaillist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&transactiontype=${param.transactiontype}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
											</c:when>
											<c:otherwise>
										第一页
									</c:otherwise>
										</c:choose>
										|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex > 0}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/taccountdetaillist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&transactiontype=${param.transactiontype}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
											</c:when>
											<c:otherwise>
										上一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 < page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/taccountdetaillist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&transactiontype=${param.transactiontype}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
											</c:when>
											<c:otherwise>
										下一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 != page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/taccountdetaillist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&transactiontype=${param.transactiontype}&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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
												window.location.href="<%=request.getContextPath()%>/tuserinfoes/taccountdetaillist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&transactiontype=${param.transactiontype}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
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
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>