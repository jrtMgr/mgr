<%@page import="com.ruyicai.mgr.domain.Torder"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.ruyicai.mgr.domain.Tlot"%>
<%@page import="com.ruyicai.mgr.lot.Lottype"%>
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
function baocun(id){
	var agencyno = document.getElementById("torder_"+id).value;
	if(!window.confirm("确定修改agencyno为"+agencyno+"吗？")){
		return;
	}
	var url = "<%=request.getContextPath()%>/torders/updateAgencyno?id="+id+"&agencyno="+agencyno;
	window.location.href = url;
}
</script>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form action="<%=request.getContextPath()%>/torders/list"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">彩种:</td>
									<td><select name="lotno" style="width:120px">
											<c:forEach var="lot" items="<%=com.ruyicai.mgr.lot.Lottype.getMap().entrySet() %>">
												<option value="${lot.key}" <c:if test="${lot.key eq param.lotno}">selected</c:if>>${lot.value}</option>
											</c:forEach>
									</select>&nbsp;<font color="red">必选</font></td>
									<td align="right">起止期号:</td>
									<td colspan="2"><input name="startbatchcode" type="text" value="${param.startbatchcode}"
										style="width: 110px" class="inputText"
										onfocus="this.select();" />~<input name="endbatchcode" type="text" value="${param.endbatchcode}"
										style="width: 110px"  class="inputText"
										onfocus="this.select();" />
										<font color="red">必填</font>
										</td>
										<td align="right">订单编号:</td>
									<td colspan="3"><input name="id" type="text" style="width: 130px" value="${param.id}"
										class="inputText" onfocus="this.select();"/> <font color="red">根据编号查无视其他条件</font></td>
								</tr>
								<tr>
									<td align="right">订单创建时间:</td>
									<td align="left"><input id="sellstarttime" name="starttime" value="${param.starttime}"
										class="inputText" type="text" style="width: 80px;"
										 onclick="DateTime.onImageMouseDown(event,'Calendar','sellstarttime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','sellstarttime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
										-&nbsp;<input id="sellendtime" name="endtime" value="${param.endtime}"
										class="inputText" type="text" ztype="date"
										style="width: 80px;"
										onclick="DateTime.onImageMouseDown(event,'Calendar','sellendtime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','sellendtime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
									</td>
									<td align="right">状态:</td>
									<td><select name="state" style="width:60px">
											<option value="">全部</option>
											<option value="0" <c:if test='${"0" eq param.state}'>selected</c:if>>等待处理</option>
											<option value="1" <c:if test='${"1" eq param.state}'>selected</c:if>>已购买</option>
											<option value="2" <c:if test='${"2" eq param.state}'>selected</c:if>>空订单</option>
											<option value="3" <c:if test='${"3" eq param.state}'>selected</c:if>>处理失败</option>
									</select></td>
									<td align="right">显示行数:</td>
									<td><select id="maxResult" name="maxResult" style="width:60px">
											<option value="15" <c:if test='${"15" eq page.maxResult}'>selected</c:if>>15</option>
											<option value="30" <c:if test='${"30" eq page.maxResult}'>selected</c:if>>30</option>
											<option value="50" <c:if test='${"50" eq page.maxResult}'>selected</c:if>>50</option>
											<option value="100" <c:if test='${"100" eq page.maxResult}'>selected</c:if>>100</option>
									</select></td>
									<td></td>
									<td align="center">&nbsp;&nbsp;<input type="submit" value="查询" class="inputButton">&nbsp;&nbsp;&nbsp;
									<input type="button" value="重置" onclick="javascript:location.href='<%=request.getContextPath() %>/torders/page';" class="inputButton">
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
					<table width="119%" cellspacing="0" cellpadding="2"
						class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="4%" height="30" class="thOver"></td>
								<td width="14%" class="thOver"><strong>编号</strong></td>
								<td width="8%" class="thOver"><strong>期号</strong></td>
								<td width="7%" class="thOver"><strong>采种</strong></td>
								<td width="5%" class="thOver"><strong>订单金额(分)</strong></td>
								<td width="5%" class="thOver"><strong>中奖金额(分)</strong></td>
								<td width="6%" class="thOver"><strong>付款状态</strong></td>
								<td width="6%" class="thOver"><strong>状态</strong></td>
								<td width="10%" class="thOver"><strong>创建时间</strong></td>
								<td width="7%" class="thOver"><strong>所属用户编号</strong></td>
								<td width="7%" class="thOver"><strong>购买者编号</strong></td>
								<td width="12%" class="thOver"><strong>彩票中心编号</strong></td>
								<td width="15%" class="thOver"><strong>注码</strong></td>
								
							</tr>
							<% 
								Page<Tlot> page2 = (Page<Tlot>)request.getAttribute("page");
								if(null != page2 && null != page2.getList()) {
									
								
								Object[] objs = (Object[])page2.getValue();
								BigDecimal sumamt = BigDecimal.ZERO;
								BigDecimal sumprizeamt = BigDecimal.ZERO;
								
							%>
								<c:forEach items="${page.list}" var="torder" varStatus="num">
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
									sumamt = sumamt.add(torder.getAmt());
									sumprizeamt = sumprizeamt.add(torder.getOrderprizeamt());
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
										<td title="${torder.agencyno}"><input type="text" value="${torder.agencyno}" id="torder_${torder.id}" style="width: 90px;"/>
										<input type="button" value="保存" onclick="baocun('${torder.id}')"/></td>
										<td title="${torder.betcode}">${torder.betcode}</td>
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
								<td></td>
								<td></td>
							</tr>
							
							<tr>
								<td align="left" id="dg1_PageBar" colspan="18"><div
										style="float: right; font-family: Tahoma">
										<c:choose>
											<c:when test="${page.pageIndex != 0}">
												<a
													href="<%=request.getContextPath()%>/torders/list?id=${param.id}&starttime=${param.starttime}&endtime=${param.endtime}&lotno=${param.lotno}&batchcode=${param.batchcode}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
											</c:when>
											<c:otherwise>
										第一页
									</c:otherwise>
										</c:choose>
										|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex > 0}">
												<a
													href="<%=request.getContextPath()%>/torders/list?id=${param.id}&starttime=${param.starttime}&endtime=${param.endtime}&lotno=${param.lotno}&batchcode=${param.batchcode}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
											</c:when>
											<c:otherwise>
										上一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 < page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/torders/list?id=${param.id}&starttime=${param.starttime}&endtime=${param.endtime}&lotno=${param.lotno}&batchcode=${param.batchcode}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
											</c:when>
											<c:otherwise>
										下一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 != page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/torders/list?id=${param.id}&starttime=${param.starttime}&endtime=${param.endtime}&lotno=${param.lotno}&batchcode=${param.batchcode}&state=${param.state}&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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
												window.location.href="<%=request.getContextPath()%>/torders/list?type=${param.type}&id=${param.id}&channel=${param.channel}&subchannel=${param.subchannel}&starttime=${param.starttime}&endtime=${param.endtime}&lotno=${param.lotno}&agencyno=${param.agencyno}&batchcode=${param.batchcode}&prize=${param.prize}&state=${param.state}&bettype=${param.bettype}&sellstarttime=${param.sellstarttime}&sellendtime=${param.sellendtime}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
											}
										</script>
									</div>
									<div style="float: left; font-family: Tahoma">共
										${page.totalResult} 条记录，每页 ${page.maxResult} 条，当前第
										${page.pageIndex + 1} / ${page.totalPage} 页</div></td>
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