<%@page import="com.ruyicai.mgr.consts.CashDetailState"%>
<%@page import="com.ruyicai.mgr.domain.Tcashdetail"%>
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
					<form action="<%=request.getContextPath()%>/tuserinfoes/tcashlist"
						method="post">
						<input type="hidden" name="userno" value="${userno}">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">提现时间:</td>
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
									</td>
									<td align="right">状态:</td>
									<td><select name="state" style="width:80px">
											<option value="">全部</option>
											<option value="1" <c:if test="${state eq 1}">selected</c:if>>待解决</option>
											<option value="103" <c:if test="${state eq 103}">selected</c:if>>已审核</option>
											<option value="104" <c:if test="${state eq 104}">selected</c:if>>驳回</option>
											<option value="105" <c:if test="${state eq 105}">selected</c:if>>执行中</option>
											<option value="106" <c:if test="${state eq 106}">selected</c:if>>提现取消</option>
									</select></td>
									<td align="right">类型:</td>
									<td><select name="type" style="width:80px">
											<option value="">全部</option>
											<option value="1" <c:if test="${param.type eq 1}">selected</c:if>>银行卡提现</option>
											<option value="2" <c:if test="${param.type eq 2}">selected</c:if>>支付宝提现</option>
									</select></td>
									<td align="right">显示行数:</td>
									<td><select id="maxResult" name="maxResult" style="width:60px">
											<option value="15" <c:if test='${"15" eq page.maxResult}'>selected</c:if>>15</option>
											<option value="30" <c:if test='${"30" eq page.maxResult}'>selected</c:if>>30</option>
											<option value="50" <c:if test='${"50" eq page.maxResult}'>selected</c:if>>50</option>
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
								<td width="2%" height="30" class="thOver"></td>
								<td width="6%" class="thOver" title="用户编号"><strong>用户编号</strong></td>
								<td width="20%" class="thOver" title="交易ID"><strong>交易ID</strong></td>
								<td width="5%" class="thOver" title="用户名"><strong>用户名</strong></td>
								<td width="8%" class="thOver" title="提现金额"><strong>提现金额</strong></td>
								<td width="5%" class="thOver" title="手续费"><strong>手续费</strong></td>
								<td width="12%" class="thOver" title="提现时间"><strong>提现时间</strong></td>
								<td width="8%" class="thOver" title="开户名称"><strong>开户名称</strong></td>
								<td width="13%" class="thOver" title="银行账号"><strong>银行账号</strong></td>
								<td width="5%" class="thOver" title="状态"><strong>状态</strong></td>
								<td width="5%" class="thOver" title="提现类型"><strong>提现类型</strong></td>
								<td width="15%" class="thOver" title="驳回原因"><strong>驳回原因</strong></td>
							</tr>
							<% 
								Page<Tcashdetail> page2 = (Page<Tcashdetail>)request.getAttribute("page");
								if(null != page2 && null != page2.getList()) {
									
								
								Object[] objs = (Object[])page2.getValue();
								BigDecimal sumamt = BigDecimal.ZERO;
								BigDecimal sumprizeamt = BigDecimal.ZERO;
								
							%>
								<c:forEach items="${page.list}" var="tcashdetail" varStatus="num">
									<% 
									Tcashdetail tcashdetail = (Tcashdetail)pageContext.getAttribute("tcashdetail");
									String state = CashDetailState.getMemo(tcashdetail.getState());
									sumamt = sumamt.add(tcashdetail.getAmt());
									sumprizeamt = sumprizeamt.add(tcashdetail.getFee());
									String type = "";
									int typeb = tcashdetail.getType().intValue();
									if(typeb == 1){
										type = "银行卡提现";
									}else if(typeb == 2){
										type = "支付宝提现";
									}
								%>
									<tr>
										<td title="${num.count}">${num.count}</td>
										<td title="${tcashdetail.userno}">${tcashdetail.userno}</td>
										<td title="${tcashdetail.ttransactionid}">${tcashdetail.ttransactionid}</td>
										<td title="${tcashdetail.name}">${tcashdetail.name}</td>
										<td title="${tcashdetail.amt}">${tcashdetail.amt}</td>
										<td title="${tcashdetail.fee}">${tcashdetail.fee}</td>
										<td title="${tcashdetail.plattime}"><fmt:formatDate value="${tcashdetail.plattime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td title="${tcashdetail.bankname}">${tcashdetail.bankname}</td>
										<td title="${tcashdetail.bankaccount}">${tcashdetail.bankaccount}</td>
										<td title="<%=state%>"><%=state%></td>										
										<td title="<%=type%>"><%=type%></td>
										<td title="${tcashdetail.rejectReason}">${tcashdetail.rejectReason}</td>
									</tr>
								</c:forEach>
							<tr>
								<td></td>
								<td>本页合计</td>
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
													href="<%=request.getContextPath()%>/tuserinfoes/tcashlist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&channeltype=${param.channeltype}&bankid=${param.bankid}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
											</c:when>
											<c:otherwise>
										第一页
									</c:otherwise>
										</c:choose>
										|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex > 0}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/tcashlist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
											</c:when>
											<c:otherwise>
										上一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 < page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/tcashlist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
											</c:when>
											<c:otherwise>
										下一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 != page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tuserinfoes/tcashlist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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
												window.location.href="<%=request.getContextPath()%>/tuserinfoes/tcashlist?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
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