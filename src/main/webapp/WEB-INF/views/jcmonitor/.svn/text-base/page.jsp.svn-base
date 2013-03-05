<%@page import="com.ruyicai.mgr.consts.LotInState"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.ruyicai.mgr.domain.Tlot"%>
<%@page import="com.ruyicai.mgr.lot.LotCenter"%>
<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.consts.SettleFlagState"%>
<%@page import="com.ruyicai.mgr.consts.BetType"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
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
<script type="text/javascript">
function init(){
	var t=60; //定义刷新频率，单位:s
	var t1 = t * 1000;
	window.setInterval("refreshY()",t1);//这里的t1为毫秒
}
function refreshY(){
   document.forms[0].action="<%=request.getContextPath() %>/jcmonitor/page";
   document.forms[0].submit(); 
}


function continuebet(flowno) {
	$.ajax({
		url:"<%=request.getContextPath() %>/jcmonitor/continuebet",
		type:"POST",
		data:"flowno=" + flowno,
		success:function(data){
			if(data.errorCode == "0") {
				alert("重投成功");
				$("#td" + flowno).html("已投出");
			}else{
				alert(data.value);
			}
		}
	});
}

function chongzheng(orderid) {
	if(!window.confirm("确定要冲正吗?")) {
		return;
	}
	$.ajax({
		url:"<%=request.getContextPath() %>/jcmonitor/undeduct",
		type:"POST",
		data:"orderid=" + orderid,
		success:function(data){
			if(data.errorCode == "0") {
				alert("冲正成功");
				$("#cz"+orderid).html("已冲正");
			}
		}
	});
}

		function findMsg(flowno) {
			var d = new Dialog("查看详情", "jcmonitor/findMsg?flowno=" + flowno,
					600, 210);
			d.show();
		}
		function findJcFailList() {
			window.location.href = "<%=request.getContextPath()%>/jcmonitor/findJcList?flag=fail";
		}
		function findJctrueFailList() {
			window.location.href = "<%=request.getContextPath()%>/jcmonitor/findJcList?flag=trueFail";
		}
		function queryTicket(flowno) {
			$.ajax({
				url:"<%=request.getContextPath() %>/jcmonitor/queryTicket",
				type:"POST",
				data:"flowno=" + flowno,
				success:function(data){
					alert(data.value);
				}
			});
		}
		
		function undeduct(flowno) {
			if(!window.confirm("确定设置赔率吗?")) {
				return;
			}
			$.ajax({
				url:"<%=request.getContextPath() %>/jcmonitor/setpeilu",
				type:"POST",
				data:"flowno=" + flowno + "&timestmap=" + new Date().getMilliseconds(),
				success:function(data){
					if(data.errorCode == "0") {
						alert("操作成功");
					}else{
						alert("操作失败");
					}
				}
			});
		}
		function rebet() {
			if(!window.confirm("确定重投吗?")) {
				return;
			}
			$.ajax({
				url:"<%=request.getContextPath() %>/jcmonitor/rebet",
				type:"POST",
				data:"",
				success:function(data){
					if(data.errorCode == "0") {
						alert("重投成功");
					}else{
						alert("重投失败");
					}
				}
			});
		}
		
</script>
</head>
<body style="margin: 0;padding: 0" onload="init()">
	<form>
	<div style="margin: 0;padding: 0;font-size: 12px;margin-top: 10px;margin-bottom: 10px" >
		
		竞彩处理中数量:${fail}&nbsp;
		<input type="button" value=" 详细 " onclick="findJcFailList();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		竞彩失败数量:${trueFail}&nbsp;
		<input type="button" value=" 详细 " onclick="findJctrueFailList();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		<input type="button" value=" 刷新 " onclick="javascript:location.reload();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		上次刷新时间:
		<%
		Date d = new  Date() ;
		SimpleDateFormat s = new  SimpleDateFormat("hh:mm:ss");
		String nowtime = s.format(d);
		%>
		<%=nowtime %> 
		大客户失败数量:${bignotbet}&nbsp;
		<input type="button" value="重投" onclick="rebet();" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	</div>
	</form>
	<div id="container" style="margin: 0;padding: 0;border: 1px solid #B1B1AD;"></div>
	<table cellspacing="0" cellpadding="2"	class="dataTable">
				<tbody>
					<tr class="dataTableHead" style="width: 1300px;overflow: auto;">
						<td width="3%" height="30" class="thOver"></td>
						<td width="11%" class="thOver"><strong>流水号</strong></td>
						<td width="7%" class="thOver"><strong>用户编号</strong></td>
						<td width="9%" class="thOver"><strong>彩种</strong></td>
						<td width="5%" class="thOver"><strong>注数</strong></td>
						<td width="5%" class="thOver"><strong>倍数</strong></td>
						<td width="8%" class="thOver"><strong>金额</strong></td>
						<td width="5%" class="thOver"><strong>中奖金额</strong></td>
						<td width="6%" class="thOver"><strong>真实中奖金额</strong></td>
						<td width="13%" class="thOver"><strong>投注时间</strong></td>
						<td width="13%" class="thOver"><strong>最后出票时间</strong></td>
						<td width="9%" class="thOver"><strong>状态</strong></td>
						<td width="8%" class="thOver"><strong>操作</strong></td>
					</tr>
					<% 
						Page<Tlot> page2 = (Page<Tlot>)request.getAttribute("page");
						if(null != page2 && null != page2.getList()) {
						
					%>
						<c:forEach items="${page.list}" var="lot" varStatus="num">
							<% 
							Tlot tlot = (Tlot)pageContext.getAttribute("lot");
							String lottype = Lottype.getName(tlot.getLotno());
							String state = LotInState.getMemo(tlot.getInstate());
							String torderid = tlot.getTorderid();
						%>
							<tr>
								<td title="${num.count}">${num.count}</td>
								<td title="${lot.flowno}"><a href="javascript:findMsg('${lot.flowno}')">${lot.flowno}</a></td>
								<td title="${lot.userno}">${lot.userno}</td>
								<td title="<%=lottype%>"><%=lottype%></td>
								<td title="${lot.betnum}">${lot.betnum}</td>
								<td title="${lot.lotmulti}">${lot.lotmulti}</td>
								<td title="${lot.amt}">${lot.amt}</td>
								<td title="${lot.prizeamt}">${lot.prizeamt}</td>
								<td title="${lot.realprizeamt}">${lot.realprizeamt}</td>
								<td title="${lot.ordertime}"><fmt:formatDate value="${lot.ordertime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td title="${lot.latestprinttime}"><fmt:formatDate value="${lot.latestprinttime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								<td><%=state%>
								<%if(BigDecimal.ZERO.equals(tlot.getInstate()) || new BigDecimal(7).equals(tlot.getInstate()) ) {%>
									<input type="button" value="设置赔率" onclick="undeduct('${lot.flowno}');"/>
								<%} %>
								</td>
								<td ><a onclick="queryTicket('${lot.flowno}');" style="cursor:hand; ">查看票状态</a>
								<%if(BigDecimal.ZERO.equals(tlot.getInstate()) || new BigDecimal(7).equals(tlot.getInstate()) ) {%>
									<td id="td${lot.flowno}">
										<input type="button" value="重投" onclick="continuebet('${lot.flowno}');"/>
									</td>
									<td id="cz${lot.torderid}">
										<input type="button" value="冲正" onclick="chongzheng('${lot.torderid}');"/>
									</td>
								<%} %>
								
								</td>
							</tr>
						</c:forEach>
					<tr>
						<td align="left" id="dg1_PageBar" colspan="17"><div
								style="float: right; font-family: Tahoma">
								<c:choose>
									<c:when test="${page.pageIndex != 0}">
										<a
											href="<%=request.getContextPath()%>/jcmonitor/findJcList?flag=${param.flag}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
									</c:when>
									<c:otherwise>
								第一页
							</c:otherwise>
								</c:choose>
								|&nbsp;
								<c:choose>
									<c:when test="${page.pageIndex > 0}">
										<a
											href="<%=request.getContextPath()%>/jcmonitor/findJcList?flag=${param.flag}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
									</c:when>
									<c:otherwise>
								上一页
							</c:otherwise>
								</c:choose>
								&nbsp;|&nbsp;
								<c:choose>
									<c:when test="${page.pageIndex + 1 < page.totalPage}">
										<a
											href="<%=request.getContextPath()%>/jcmonitor/findJcList?flag=${param.flag}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
									</c:when>
									<c:otherwise>
								下一页
							</c:otherwise>
								</c:choose>
								&nbsp;|&nbsp;
								<c:choose>
									<c:when test="${page.pageIndex + 1 != page.totalPage}">
										<a
											href="<%=request.getContextPath()%>/jcmonitor/findJcList?flag=${param.flag}&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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
										window.location.href="<%=request.getContextPath()%>/findJcList?flag=${param.flag}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
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
</body>
</html>
