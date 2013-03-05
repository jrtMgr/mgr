<%@page import="com.ruyicai.mgr.util.DateUtil"%>
<%@page import="com.ruyicai.mgr.domain.CaseLotBuy"%>
<%@page import="com.ruyicai.mgr.domain.CaseLot"%>
<%@page import="com.ruyicai.mgr.controller.dto.CaseLotAndCaseLotBuyDTO"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
<%@page import="com.ruyicai.mgr.consts.SettleFlagState"%>
<%@page import="com.ruyicai.mgr.consts.BetType"%>
<%@page import="java.math.BigDecimal"%>
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
				<td style="padding: 2px 10px;">
					<form action="<%=request.getContextPath()%>/tuserinfoes/caselotlist"
						method="post">
						<input type="hidden" name="userno" value="${userno}">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">彩种:</td>
									<td><select name="lotno" style="width:80px">
											<option value="">全部</option>
											<c:forEach var="lot"
												items="<%=com.ruyicai.mgr.lot.Lottype.getMap().entrySet() %>">
												<option value="${lot.key}">${lot.value}</option>
											</c:forEach>
									</select></td>
									<td align="right">期号:</td>
									<td><input name="batchcode" type="text"
										style="width: 210px" id="batchcode" class="inputText"
										onfocus="this.select();" /></td>
									<td align="right">方案编号:</td>
									<td><input name="caselotId" type="text"
										style="width: 120px" id="caselotId" class="inputText"
										onfocus="this.select();" /></td>
								</tr>
								<tr>
									<td align="right">中奖:</td>
									<td><select name="prize" style="width:80px">
											<option value="">全部</option>
											<option value="yes">是</option>
											<option value="no">否</option>
									</select></td>
									<td align="right">投注时间:</td>
									<td><input id="starttime" name="starttime"
										class="inputText" type="text" style="width: 90px;"
										 onclick="DateTime.onImageMouseDown(event,'Calendar','starttime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','starttime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
										-&nbsp;<input id="endtime" name="endtime"
										class="inputText" type="text" ztype="date"
										style="width: 90px;"
										onclick="DateTime.onImageMouseDown(event,'Calendar','endtime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','endtime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
									</td>
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
								<td width="3%" height="30" class="thOver"></td>
								<td width="12%" class="thOver"><strong>合买方案号</strong></td>
								<td width="8%" class="thOver"><strong>用户编号</strong></td>
								<td width="8%" class="thOver"><strong>彩种</strong></td>
								<td width="8%" class="thOver"><strong>期号</strong></td>
								<td width="6%" class="thOver"><strong>方案金额（分）</strong></td>
								<td width="7%" class="thOver"><strong>认购金额（分）</strong></td>
								<td width="7%" class="thOver"><strong>保底金额（分）</strong></td>
								<td width="7%" class="thOver"><strong>冻结金额（分）</strong></td>
								<td width="7%" class="thOver"><strong>中奖金额（分）</strong></td>
								<td width="7%" class="thOver"><strong>佣金（分）</strong></td>
								<td width="13%" class="thOver"><strong>投注时间</strong></td>
								<td width="7%" class="thOver"><strong>合买状态</strong></td>
							</tr>
								<% 
									Page<CaseLotAndCaseLotBuyDTO> page2 = (Page<CaseLotAndCaseLotBuyDTO>)request.getAttribute("page");
									if(null != page2 && null != page2.getList()) {
									
									Object[] objs = (Object[])page2.getValue();
									BigDecimal sumnum = BigDecimal.ZERO;
									BigDecimal sumsafeAmt = BigDecimal.ZERO;
									BigDecimal sumfreezeSafeAmt = BigDecimal.ZERO;
									BigDecimal sumprizeAmt = BigDecimal.ZERO;
									BigDecimal sumcommisionPrizeAmt = BigDecimal.ZERO;
								%>
								<c:forEach items="${page.list}" var="dto" varStatus="num">
									<% 
										CaseLotAndCaseLotBuyDTO cc = (CaseLotAndCaseLotBuyDTO)pageContext.getAttribute("dto");
										CaseLot caselot = cc.getCaseLot();
										CaseLotBuy caselotbuy = cc.getCaseLotBuy();
										sumnum = sumnum.add(caselotbuy.getNum());
										sumsafeAmt = sumsafeAmt.add(caselotbuy.getSafeAmt());
										sumfreezeSafeAmt = sumfreezeSafeAmt.add(caselotbuy.getFreezeSafeAmt());
										sumprizeAmt = sumprizeAmt.add(caselotbuy.getPrizeAmt());
										sumcommisionPrizeAmt = sumcommisionPrizeAmt.add(caselotbuy.getCommisionPrizeAmt());
									%>
									<tr>
										<td title="${num.count}">${num.count}</td>
										<td title="<%=caselot.getId() %>"><a href="<%=request.getContextPath()%>/caselot/caselotListMg?search=<%=caselot.getId() %>"><%=caselot.getId() %></a></td>
										<td title="<%=caselotbuy.getUserno() %>"><%=caselotbuy.getUserno() %></td>
										<td title="<%=caselot.getLotno() %>"><%=caselot.getLotno() %></td>
										<td title="<%=caselot.getBatchcode() %>"><%=caselot.getBatchcode() %></td>
										<td title="<%=caselot.getTotalAmt() %>"><%=caselot.getTotalAmt() %></td>
										<td title="<%=caselotbuy.getNum() %>"><%=caselotbuy.getNum() %></td>
										<td title="<%=caselotbuy.getSafeAmt() %>"><%=caselotbuy.getSafeAmt() %></td>
										<td title="<%=caselotbuy.getFreezeSafeAmt() %>"><%=caselotbuy.getFreezeSafeAmt() %></td>
										<td title="<%=caselotbuy.getPrizeAmt() %>"><%=caselotbuy.getPrizeAmt() %></td>
										<td title="<%=caselotbuy.getCommisionPrizeAmt() %>"><%=caselotbuy.getCommisionPrizeAmt() %></td>
										<td title="<%=DateUtil.format(caselotbuy.getBuyTime()) %>"><%=DateUtil.format(caselotbuy.getBuyTime()) %></td>
										<td title="<%=caselot.getDisplayStateMemo() %>"><%=caselot.getDisplayStateMemo() %></td>
									</tr>
								</c:forEach>
							<tr>
								<td></td>
								<td>本页合计</td>
								<td></td>
								<td></td>
								<td></td>
								<td title="<%=sumnum%>"><%=sumnum%></td>
								<td title="<%=sumsafeAmt%>"><%=sumsafeAmt%></td>
								<td title="<%=sumfreezeSafeAmt%>"><%=sumfreezeSafeAmt%></td>
								<td title="<%=sumprizeAmt%>"><%=sumprizeAmt%></td>
								<td title="<%=sumcommisionPrizeAmt%>"><%=sumcommisionPrizeAmt%></td>
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
								<td title="<%=null == objs[2] ? 0 : objs[2]%>"><%=null == objs[2] ? 0 : objs[2]%></td>
								<td title="<%=null == objs[3] ? 0 : objs[3]%>"><%=null == objs[3] ? 0 : objs[3]%></td>
								<td title="<%=null == objs[4] ? 0 : objs[4]%>"><%=null == objs[4] ? 0 : objs[4]%></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="left" id="dg1_PageBar" colspan="17"><div
										style="float: right; font-family: Tahoma">
										<c:choose>
											<c:when test="${page.pageIndex != 0}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/caselotlist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&lotno=${param.lotno}&batchcode=${param.batchcode}&prize=${param.prize}&caselotId=${param.caselotId }&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
											</c:when>
											<c:otherwise>
										第一页
									</c:otherwise>
										</c:choose>
										|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex > 0}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/caselotlist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&lotno=${param.lotno}&batchcode=${param.batchcode}&prize=${param.prize}&caselotId=${param.caselotId }&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
											</c:when>
											<c:otherwise>
										上一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 < page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/caselotlist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&lotno=${param.lotno}&batchcode=${param.batchcode}&prize=${param.prize}&caselotId=${param.caselotId }&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
											</c:when>
											<c:otherwise>
										下一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 != page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/caselotlist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&lotno=${param.lotno}&batchcode=${param.batchcode}&prize=${param.prize}&caselotId=${param.caselotId }&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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
												window.location.href="<%=request.getContextPath()%>/tuserinfoes/caselotlist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&lotno=${param.lotno}&agencyno=${param.agencyno}&batchcode=${param.batchcode}&prize=${param.prize}&state=${param.state}&bettype=${param.bettype}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
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
							<td colspan="17"><input type="button" value=" 返 回 " class="inputButton" onclick="goback();"></td>
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