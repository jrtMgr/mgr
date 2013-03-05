<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
<%@page import="com.ruyicai.mgr.consts.SettleFlagState"%>
<%@page import="com.ruyicai.mgr.consts.BetType"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.ruyicai.mgr.domain.Tlot"%>
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
					<form action="<%=request.getContextPath()%>/tlots/list"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">彩票编号:</td>
									<td ><input name="flowno" type="text" style="width: 110px" value="${param.flowno}"
										id="flowno" class="inputText" onfocus="this.select();"/><font color="red">根据编号查无视其他条件</font></td>
									<td align="right">彩种:</td>
									<td><select name="lotno" style="width:100px">
											<option value="">全部</option>
											<c:forEach var="lot" items="<%=com.ruyicai.mgr.lot.Lottype.getMap().entrySet() %>">
												<option value="${lot.key}" <c:if test="${lot.key eq param.lotno}">selected</c:if>>${lot.value}</option>
											</c:forEach>
									</select></td>
									<td align="right">期号:</td>
									<td ><input name="batchcode" type="text" value="${param.batchcode}"
										style="width: 80px" class="inputText"
										onfocus="this.select();" /></td>
									<td align="right">彩票中心:</td>
									<td><select name="agencyno" style="width:100px">
											<option value="">全部</option>
											<c:forEach items="<%=com.ruyicai.mgr.lot.LotCenter.getMap().entrySet() %>" var="center">
												<option value="${center.key}" <c:if test="${center.key eq param.agencyno}">selected</c:if>>${center.value}</option>
											</c:forEach>
									</select></td>
								</tr>
								<tr>
									<td align="right">渠道号:</td>
									<td><input name="channel" type="text" style="width: 110px" value="${param.channel}"
										id="channel" class="inputText" onfocus="this.select();" /></td>
									<td align="right">用户系统:</td>
									<td><select name="subchannel" style="width:80px">
											<option value="">全部</option>
											<option value="00092493"  <c:if test='${"00092493" eq param.subchannel}'>selected</c:if>>00092493</option>
											<option value="00092494"  <c:if test='${"00092494" eq param.subchannel}'>selected</c:if>>00092494</option>
											<option value="00092490"  <c:if test='${"00092490" eq param.subchannel}'>selected</c:if>>00092490</option>
											<option value="00358433"  <c:if test='${"00358433" eq param.subchannel}'>selected</c:if>>00358433</option>
									</select></td>
									<td align="right">标识类别:</td>
									<td><select id="usertype" name="usertype" style="width:60px">
											<option value="n" >无</option>
											<option value="mobileid" <c:if test='${"mobileid" eq usertype}'>selected</c:if>>手机</option>
											<option value="userName" <c:if test='${"userName" eq usertype}'>selected</c:if>>用户名</option>
											<option value="email" <c:if test='${"email" eq usertype}'>selected</c:if>>邮箱</option>
									</select></td>
									<td align="right">用户标识:</td>
									<td><input name="userid" type="text" style="width: 110px" value="${userid}"
										id="userid" class="inputText" onfocus="this.select();" /></td>
										<td align="right">状态:</td>
									<td><select name="state" style="width:60px">
											<option value="110">全部</option>
											<option value="-1" <c:if test='${"-1" eq param.state}'>selected</c:if>>未处理</option>
											<option value="0" <c:if test='${"0" eq param.state}'>selected</c:if>>失败</option>
											<option value="1" <c:if test='${"1" eq param.state}'>selected</c:if>>成功</option>
											<option value="2" <c:if test='${"2" eq param.state}'>selected</c:if>>处理中</option>
									</select></td>
								</tr>
								<tr>
									<td align="right">投注时间:</td>
									<td><input id="starttime" name="starttime" value="${param.starttime}"
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
									<td align="right">交易时间:</td>
									<td><input id="sellstarttime" name="sellstarttime" value="${sellstarttime}"
										class="inputText" type="text" style="width: 80px;"
										 onclick="DateTime.onImageMouseDown(event,'Calendar','sellstarttime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','sellstarttime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
										-&nbsp;<input id="sellendtime" name="sellendtime" value="${param.sellendtime}"
										class="inputText" type="text" ztype="date"
										style="width: 80px;"
										onclick="DateTime.onImageMouseDown(event,'Calendar','sellendtime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','sellendtime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
									</td>
									<td align="right">中奖:</td>
									<td><select name="prize" style="width:60px">
											<option value="">全部</option>
											<option value="yes" <c:if test='${"yes" eq prize}'>selected</c:if> >是</option>
											<option value="no" <c:if test='${"no" eq prize}'>selected</c:if>>否</option>
									</select></td>
									<td align="right">类型:</td>
									<td>
									<select name="bettype" style="width:60px">
									<option value="100">全部</option>
									<option value="2" <c:if test='${"2" eq param.bettype}'>selected</c:if>>投注</option>
									<option value="4" <c:if test='${"4" eq param.bettype}'>selected</c:if>>赠送</option>
									<option value="0" <c:if test='${"0" eq param.bettype}'>selected</c:if>>追号</option>
									<option value="1" <c:if test='${"1" eq param.bettype}'>selected</c:if>>套餐</option>
									<option value="3" <c:if test='${"3" eq param.bettype}'>selected</c:if>>合买</option>
									</select>
									</td>
									<td align="right">显示行数:</td>
									<td><select id="maxResult" name="maxResult" style="width:60px">
											<option value="15" <c:if test='${"15" eq page.maxResult}'>selected</c:if>>15</option>
											<option value="30" <c:if test='${"30" eq page.maxResult}'>selected</c:if>>30</option>
											<option value="50" <c:if test='${"50" eq page.maxResult}'>selected</c:if>>50</option>
											<option value="100" <c:if test='${"100" eq page.maxResult}'>selected</c:if>>100</option>
									</select></td>
									<td align="center"><input type="submit" value="查询" class="inputButton">&nbsp;&nbsp;&nbsp;
									<input type="button" value="重置" onclick="javascript:location.href='<%=request.getContextPath() %>/tlots/list';" class="inputButton">
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
								<td width="12%" class="thOver"><strong>流水号</strong></td>
								<td width="7%" class="thOver"><strong>用户编号</strong></td>
								<td width="6%" class="thOver"><strong>渠道</strong></td>
								<td width="6%" class="thOver"><strong>用户系统</strong></td>
								<td width="5%" class="thOver"><strong>彩种</strong></td>
								<td width="8%" class="thOver"><strong>期号</strong></td>
								<td width="4%" class="thOver"><strong>注数</strong></td>
								<td width="4%" class="thOver"><strong>倍数</strong></td>
								<td width="6%" class="thOver"><strong>金额</strong></td>
								<td width="5%" class="thOver"><strong>中奖金额</strong></td>
								<td width="12%" class="thOver"><strong>投注时间</strong></td>
								<td width="5%" class="thOver"><strong>类型</strong></td>
								<td width="6%" class="thOver"><strong>标识</strong></td>
								<td width="6%" class="thOver"><strong>赔率</strong></td>
								<td width="6%" class="thOver"><strong>状态</strong></td>
							</tr>
							<% 
								Page<Tlot> page2 = (Page<Tlot>)request.getAttribute("page");
								if(null != page2 && null != page2.getList()) {
									
								
								Object[] objs = (Object[])page2.getValue();
								BigDecimal sumamt = BigDecimal.ZERO;
								BigDecimal sumprizeamt = BigDecimal.ZERO;
								
							%>
								<c:forEach items="${page.list}" var="lot" varStatus="num">
									<% 
									Tlot tlot = (Tlot)pageContext.getAttribute("lot");
									String lottype = Lottype.getName(tlot.getLotno());
									String type = BetType.getBetType(tlot.getBettype());
									String flag = SettleFlagState.getMemo(tlot.getSettleflag());
									String state = "";
									if(new BigDecimal(-1).equals(tlot.getState())) {
										state = "未处理";
									}
									if(BigDecimal.ZERO.equals(tlot.getState())) {
										state = "失败";
									}
									if(BigDecimal.ONE.equals(tlot.getState())) {
										state = "成功";
									}
									if(new BigDecimal(2).equals(tlot.getState())) {
										state = "处理中";
									}
									sumamt = sumamt.add(tlot.getAmt());
									sumprizeamt = sumprizeamt.add(tlot.getPrizeamt());
								%>
									<tr>
										<td title="${num.count}">${num.count}</td>
										<td title="${lot.flowno}">${lot.flowno}</td>
										<td title="${lot.userno}">${lot.userno}</td>
										<td title="${lot.channel}">${lot.channel}</td>
										<td title="${lot.subchannel}">${lot.subchannel}</td>
										<td title="<%=lottype%>"><%=lottype%></td>
										<td title="${lot.batchcode}">${lot.batchcode}</td>
										<td title="${lot.betnum}">${lot.betnum}</td>
										<td title="${lot.lotmulti}">${lot.lotmulti}</td>
										<td title="${lot.amt}">${lot.amt}</td>
										<td title="${lot.prizeamt}">${lot.prizeamt}</td>
										<td title="${lot.ordertime}"><fmt:formatDate value="${lot.ordertime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td title="<%=type%>"><%=type%></td>
										<td title="<%=flag%>"><%=flag%></td>
									 	<td title="${lot.peilu}">${lot.peilu}</td> 
										<td title="<%=state%>"><%=state%></td>
									</tr>
								</c:forEach>
							<tr>
								<td></td>
								<td>本页合计</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
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
								<td></td>
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
								<td align="left" id="dg1_PageBar" colspan="17"><div
										style="float: right; font-family: Tahoma">
										<c:choose>
											<c:when test="${page.pageIndex != 0}">
												<a
													href="<%=request.getContextPath()%>/tlots/list?&flowno=${param.flowno}&lotno=${param.lotno}&batchcode=${param.tbatchcode}&channel=${param.channel}&subchannel=${param.subchannel}&starttime=${param.starttime}&endtime=${param.endtime}&usertype=${param.usertype}&userid=${param.userid}&prize=${param.prize}&state=${param.state}&bettype=${param.bettype}&sellstarttime=${param.sellstarttime}&sellendtime=${param.sellendtime}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
											</c:when>
											<c:otherwise>
										第一页
									</c:otherwise>
										</c:choose>
										|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex > 0}">
												<a
													href="<%=request.getContextPath()%>/tlots/list?flowno=${param.flowno}&lotno=${param.lotno}&batchcode=${param.batchcode}&channel=${param.channel}&subchannel=${param.subchannel}&starttime=${param.starttime}&endtime=${param.endtime}&usertype=${param.usertype}&userid=${param.userid}&prize=${param.prize}&state=${param.state}&bettype=${param.bettype}&sellstarttime=${param.sellstarttime}&sellendtime=${param.sellendtime}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
											</c:when>
											<c:otherwise>
										上一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 < page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tlots/list?flowno=${param.flowno}&lotno=${param.lotno}&batchcode=${param.batchcode}&channel=${param.channel}&subchannel=${param.subchannel}&starttime=${param.starttime}&endtime=${param.endtime}&usertype=${param.usertype}&userid=${param.userid}&prize=${param.prize}&state=${param.state}&bettype=${param.bettype}&sellstarttime=${param.sellstarttime}&sellendtime=${param.sellendtime}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
											</c:when>
											<c:otherwise>
										下一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 != page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tlots/list?flowno=${param.flowno}&lotno=${param.lotno}&batchcode=${param.batchcode}&channel=${param.channel}&subchannel=${param.subchannel}&starttime=${param.starttime}&endtime=${param.endtime}&usertype=${param.usertype}&userid=${param.userid}&prize=${param.prize}&state=${param.state}&bettype=${param.bettype}&sellstarttime=${param.sellstarttime}&sellendtime=${param.sellendtime}&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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
												window.location.href="<%=request.getContextPath()%>/tlots/list?flowno=${param.flowno}&lotno=${param.lotno}&batchcode=${param.batchcode}&channel=${param.channel}&subchannel=${param.subchannel}&starttime=${param.starttime}&endtime=${param.endtime}&usertype=${param.usertype}&userid=${param.userid}&prize=${param.prize}&state=${param.state}&bettype=${param.bettype}&sellstarttime=${param.sellstarttime}&sellendtime=${param.sellendtime}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
											}
										</script>
									</div>
									<div style="float: left; font-family: Tahoma">共
										${page.totalResult} 条记录，每页 ${page.maxResult} 条，当前第
										${page.pageIndex + 1} / ${page.totalPage} 页</div></td>
							</tr>
							<%-- <tr>
								<td colspan="17" align="center"><a style="cursor:hand; font-size: 24px;" href="<%=request.getContextPath()%>/tlots/generateReport?flowno=${param.flowno}&lotno=${param.lotno}&batchcode=${param.batchcode}&channel=${param.channel}&subchannel=${param.subchannel}&starttime=${param.starttime}&endtime=${param.endtime}&usertype=${param.usertype}&userid=${param.userid}&prize=${param.prize}&state=${param.state}&bettype=${param.bettype}&sellstarttime=${param.sellstarttime}&sellendtime=${param.sellendtime}">生成excel</a></td>
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