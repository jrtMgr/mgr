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
					<form action="<%=request.getContextPath()%>/tcharges/list"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
								<td align="right">交易号:</td>
									<td><input name="ttransactionid" type="text" style="width: 180px" value="${param.ttransactionid}"
										id="ttransactionid" class="inputText" onfocus="this.select();" /></td>
									<td align="right">标识类别:</td>
									<td><select id="type" name="type" style="width:80px">
											<option value="n" >无</option>
											<option value="mobileid" <c:if test='${"mobileid" eq type}'>selected</c:if>>手机</option>
											<option value="userno" <c:if test='${"userno" eq type}'>selected</c:if>>用户编号</option>
											<option value="userName" <c:if test='${"userName" eq type}'>selected</c:if>>用户名</option>
											<option value="email" <c:if test='${"email" eq type}'>selected</c:if>>邮箱</option>
									</select></td>
									<td align="right">用户标识:</td>
									<td><input name="id" type="text" style="width: 110px" value="${id}"
										id="id" class="inputText" onfocus="this.select();" /></td>
									<td>充值渠道:</td>
									<td>
										<select name="channeltype" style="width:80px">
											<option value="tcharge" <c:if test='${"tcharge" eq channeltype}'>selected</c:if>>发生渠道</option>
											<option value="tuserinfo" <c:if test='${"tuserinfo" eq channeltype}'>selected</c:if>>用户渠道</option>
										</select>
									</td>
									<td align="right">渠道号:</td>
									<td><input name="channel" type="text" style="width: 110px" value="${param.channel}"
										id="channel" class="inputText" onfocus="this.select();" /></td>
										<td align="right">状态:</td>
									<td><select name="state" style="width:80px">
											<option value="110">全部</option>
											<option value="0" <c:if test='${"0" eq state}'>selected</c:if>>未处理</option>
											<option value="2" <c:if test='${"2" eq state}'>selected</c:if>>失败</option>
											<option value="1" <c:if test='${"1" eq state}'>selected</c:if>>成功</option>
											<option value="3" <c:if test='${"3" eq state}'>selected</c:if>>处理中</option>
									</select></td>
								</tr>
								<tr>
								<td align="right">交易时间:</td>
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
									<td align="right">交易方式:</td>
									<td><select name="ttype" style="width:100px">
											<option value="cz" <c:if test="${ttype eq 'cz'}">selected</c:if>>用户充值</option>
											<option value="kk" <c:if test="${ttype eq 'kk'}">selected</c:if>>易支付撤销扣款</option>
											<option value="bucz" <c:if test="${ttype eq 'bucz'}">selected</c:if>>大客户充值</option>
											<option value="zs" <c:if test="${ttype eq 'zs'}">selected</c:if>>赠送</option>
									</select></td>
									<td align="right">用户充值方式:</td>
									<td><select name="bankid" style="width:80px">
											<option value="">全部</option>
											<c:forEach
												items="<%=com.ruyicai.mgr.charge.ChargeType.getMap().entrySet() %>"
												var="center">
												<option value="${center.key}" <c:if test="${center.key eq param.bankid}">selected</c:if>>${center.value}</option>
											</c:forEach>
									</select></td>
									<td align="right">大客户充值:</td>
									<td><select name="agencyno" style="width:100px">
											<option value="">全部</option>
											<c:forEach items="${subchannels}" var="s">
												<option value="${s.agencyno}" <c:if test="${s.agencyno eq param.agencyno}">selected</c:if>>${s.agencyname}</option>
											</c:forEach>
									</select></td>
									<td align="right">显示行数:</td>
									<td><select id="maxResult" name="maxResult" style="width:60px">
											<option value="15" <c:if test='${"15" eq page.maxResult}'>selected</c:if>>15</option>
											<option value="30" <c:if test='${"30" eq page.maxResult}'>selected</c:if>>30</option>
											<option value="50" <c:if test='${"50" eq page.maxResult}'>selected</c:if>>50</option>
											<option value="100" <c:if test='${"100" eq page.maxResult}'>selected</c:if>>100</option>
											<option value="300" <c:if test='${"300" eq page.maxResult}'>selected</c:if>>300</option>
									</select></td>
									<td></td>
									<td align="center"><input type="submit" value="查询" class="inputButton">&nbsp;&nbsp;&nbsp;
									<input type="button" value="重置" onclick="javascript:location.href='<%=request.getContextPath() %>/tcharges/list';" class="inputButton">
								    </td>								
								</tr>
								<tr>
								 <td align="center" colspan="3"><input type="button" value="支付宝订单查询" onclick="javascript:location.href='<%=request.getContextPath() %>/tcharges/alipay';" class="inputButton"></td>
								 <td align="center" colspan="3"><input type="button" value="DNA订单查询" onclick="javascript:location.href='<%=request.getContextPath() %>/tcharges/dnapay';" class="inputButton"></td>
								 <td align="center" colspan="3"><input type="button" value="上海银联订单查询" onclick="javascript:location.href='<%=request.getContextPath() %>/tcharges/chinapay';" class="inputButton"></td>
								 <td align="center" colspan="3"><input type="button" value="银联手机订单查询" onclick="javascript:location.href='<%=request.getContextPath() %>/tcharges/lthjpay';" class="inputButton"></td>
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
								<td width="23%" class="thOver"><strong>交易ID</strong></td>
								<td width="8%" class="thOver"><strong>用户名称</strong></td>
								<td width="8%" class="thOver"><strong>充值方式</strong></td>
								<td width="12%" class="thOver"><strong>充值时间</strong></td>
								<td width="6%" class="thOver"><strong>金额</strong></td>
								<td width="5%" class="thOver"><strong>手续费</strong></td>
								<td width="5%" class="thOver"><strong>状态</strong></td>
								<td width="6%" class="thOver"><strong>银行返回</strong></td>
								<td width="8%" class="thOver"><strong>备注</strong></td>
								<td width="12%" class="thOver"><strong>如意卡卡号</strong></td>
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
										<td title="${transaction.userno}"><a href="<%=request.getContextPath()%>/tuserinfoes/list?userno=${transaction.userno}">${transaction.userno}</a>
										</td>
										<td><c:choose>
											<c:when test="${'bucz' eq ttype}">
												<c:forEach items="${subchannels}" var="s">
													 <c:if test="${s.agencyno eq transaction.userno}">${s.agencyname}</c:if>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<%=bank%>
											</c:otherwise>
										</c:choose></td>
										<td title="${transaction.plattime}"><fmt:formatDate value="${transaction.plattime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td title="${transaction.amt}">${transaction.amt}</td>
										<td title="${transaction.fee}">${transaction.fee}</td>
										<td title="<%=state%>"><%=state%></td>
										<td title="${transaction.retmemo}">${transaction.retmemo}</td>
										<td title="${transaction.memo}">${transaction.memo}</td>
										<td title="${transaction.bankaccount}">${transaction.bankaccount}</td>
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
													href="<%=request.getContextPath()%>/tcharges/list?type=${param.type}&id=${param.id}&channel=${param.channel}&starttime=${param.starttime}&endtime=${param.endtime}&channeltype=${param.channeltype}&ttype=${ttype}&bankid=${param.bankid}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
											</c:when>
											<c:otherwise>
										第一页
									</c:otherwise>
										</c:choose>
										|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex > 0}">
												<a
													href="<%=request.getContextPath()%>/tcharges/list?type=${param.type}&id=${param.id}&channel=${param.channel}&starttime=${param.starttime}&endtime=${param.endtime}&channeltype=${param.channeltype}&ttype=${ttype}&bankid=${param.bankid}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
											</c:when>
											<c:otherwise>
										上一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 < page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tcharges/list?type=${param.type}&id=${param.id}&channel=${param.channel}&starttime=${param.starttime}&endtime=${param.endtime}&channeltype=${param.channeltype}&ttype=${ttype}&bankid=${param.bankid}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
											</c:when>
											<c:otherwise>
										下一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 != page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tcharges/list?type=${param.type}&id=${param.id}&channel=${param.channel}&starttime=${param.starttime}&endtime=${param.endtime}&channeltype=${param.channeltype}&ttype=${ttype}&bankid=${param.bankid}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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
												window.location.href="<%=request.getContextPath()%>/tcharges/list?type=${param.type}&id=${param.id}&channel=${param.channel}&starttime=${param.starttime}&endtime=${param.endtime}&channeltype=${param.channeltype}&ttype=${ttype}&bankid=${param.bankid}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
											}
										</script>
									</div>
									<div style="float: left; font-family: Tahoma">共
										${page.totalResult} 条记录，每页 ${page.maxResult} 条，当前第
										${page.pageIndex + 1} / ${page.totalPage} 页</div></td>
							</tr>
							<%-- <tr>
								<td colspan="11" align="center"><a style="cursor:hand; font-size: 24px;" href="<%=request.getContextPath()%>/tcharges/generateReport?type=${param.type}&id=${param.id}&channel=${param.channel}&starttime=${param.starttime}&endtime=${param.endtime}&channeltype=${param.channeltype}&ttype=${ttype}&bankid=${param.bankid}&state=${param.state}&agencyno=${param.agencyno}">生成excel</a></td>
							</tr> --%>
						</tbody>
						<%} %>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>