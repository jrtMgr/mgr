<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.consts.UserState"%>
<%@page import="com.ruyicai.mgr.domain.Tuserinfo"%>
<%@page import="java.util.Map" %>
<%@page import="java.util.Map.Entry" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/map.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Dialog.js"></script>
<script type="text/javascript">
<%String errormsg = (String) request.getAttribute("errormsg");
String result = (String)request.getAttribute("result");
if (!StringUtil.isEmpty(errormsg)) {%>
function showerror() {
	Dialog.alert("<%=errormsg%>");
}
$(document).ready(function() {
	showerror();
});
<%}%>	
function sortSelect(){
	var _orderBy = $("#orderBy");
	var _orderDir = $("#orderDir")
	var nodeValue = $("#mysort option:selected").val();
	if(nodeValue==""){
		_orderBy.val("");
		_orderDir.val("");
	}else if (nodeValue == "1"){
		_orderBy.val("prizeamt");
		_orderDir.val("ASC")
	}else if (nodeValue == "2"){
		_orderBy.val("prizeamt");
		_orderDir.val("DESC")
	}
}

function ctrllist(torderid) {
	var d = new Dialog(torderid+"订单详情", "tuserinfoes/gotlotlist?torderid=" + torderid,
			1200, 210);
	d.show();
}
function ctrlbetcode(lotno,orderinfo,lotmulti,orderid,bettype) {
	var d;
	var iscaseLot;
	if(lotno.indexOf("J")==0){
		if(bettype==3){
			iscaseLot=true;
		}else{
			iscaseLot=false;
		}
		d = new Dialog("查看详情", "tuserinfoes/parseOrderinfoJingCai?lotno=" + lotno + "&orderId=" + orderid + "&multiple=" + lotmulti + "&isCaseLot=" + iscaseLot,
				800, 210);
	}else{
		d = new Dialog("查看详情", "tuserinfoes/parseOrderinfo?lotno=" + lotno + "&orderinfo=" + orderinfo + "&multiple=" + lotmulti,
				800, 210);
	}
	d.show();
}
function configState(state){
	if(state==0){
		return "处理中";
	}
	if(state==1){
		return "成功";
	}
	if(state==3){
		return "撤单";
	}
	if(state==4){
		return "合买撤单";
	}
}

function configBetType(bettype){
	if(bettype==0){
		return "追号";
	}
	if(bettype==1){
		return "套餐"; 
	}
	if(bettype==2){
		return "投注"; 
	}
	if(bettype==3){
		return "合买"; 
	}
	if(bettype==4){
		return "赠送"; 
	}
	if(bettype==5){
		return "无短信赠送";
	}
}

function configPrizeState(state){
	if(state==0){
		return "准备等待开奖";
	}
	if(state==1){
		return "等待开奖"; 
	}
	if(state==2){
		return "派奖处理中"; 
	}
	if(state==3){
		return "完成派奖"; 
	}
	if(state==4){
		return "中大奖"; 
	}
	if(state==5){
		return "中小奖";
	}
}


Date.prototype.format = function(format)
{
    var o =
    {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format))
    format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
    if(new RegExp("("+ k +")").test(format))
    format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}
function parseObj(strData){
    return (new Function( "return " + strData ))();
}
$(document).ready(function() {
	var resultMessage = parseObj('<%=result %>');
	var page = resultMessage.value;
	$("#totalResult").text(page.totalResult);
	$("#maxResult").text(page.maxResult);
	$("#currentPageNo").text(page.currentPageNo);
	$("#totalPage").text(page.totalPage);
	if(page.currentPageNo < page.totalPage){
		$("#nextpage").show();
		$("#nextpage2").hide();
	}else{
		$("#nextpage").hide();
		$("#nextpage2").show();
	}
	if(page.currentPageNo < page.totalPage){
		$("#gotoend").show();
		$("#gotoend2").hide();
	}else{
		$("#gotoend").hide();
		$("#gotoend2").show();
	}
	var data = resultMessage.value.list;
	var tbody = $("#data");
	// 将彩种放入map中
	var _map = new Map();
	<%
		Map<String,String> lotTypes = (Map)request.getAttribute("lotTypes");
		for(Entry lotType :lotTypes.entrySet()){
	%>
			_map.put('<%=lotType.getKey() %>','<%=lotType.getValue() %>');
	<%
		}
	%>
$.each(data,function(i,n){
	tbody.append('<tr>');
	var _lotname = _map.get(data[i].lotno);
	var userno = data[i].orderuserno;
	var url = '<%=request.getContextPath()%>/tuserinfoes/list?userno='+userno;
	tbody.append('<td>'+parseInt(${startLine}+1+i)+'</td><td title='+data[i].torder.id+' ><a href="javascript:ctrllist(\''+data[i].torder.id+'\')">'+data[i].torder.id+'</a></td>');
	tbody.append('<td><a  href='+url+'>'+userno+'</a></td>');
	tbody.append('<td>'+data[i].torder.buyuserno+'</td>');
	tbody.append('<td title="'+data[i].torder.orderinfo+'"><a href="javascript:ctrlbetcode(\''+data[i].lotno+'\',\''+data[i].torder.orderinfo+'\',\''+data[i].torder.lotmulti+'\',\''+data[i].orderid+'\',\''+data[i].bettype+'\')">'+data[i].torder.orderinfo+'</td>');
	tbody.append('<td>' + parseFloat(parseInt(data[i].orderamt/100)) + '</td>');
	tbody.append('<td>' + data[i].wincode + '</td>');
	var da = new Date(data[i].modifytime);
	var daStr = da.format("yyyy-MM-dd hh:mm:ss");
	tbody.append('<td>' + daStr + '</td>');
	tbody.append('<td>' + _lotname + '</td>');
	tbody.append('<td>' + data[i].batchcode + '</td>');
	if(data[i].prizeamt==null){
		tbody.append('<td>' + "" + '</td>');
	}
	else{
		tbody.append('<td>' + parseFloat(parseInt(data[i].torder.orderprizeamt/100)) + '</td>');
	}
	tbody.append('<td>' + configPrizeState(data[i].torder.prizestate) + '</td>');
	tbody.append('<td>' + configBetType(data[i].bettype) + '</td>');
	tbody.append('<td>' + configState(data[i].torder.orderstate) + '</td>');
	tbody.append('</tr>');
});
});
</script>
</head>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="110%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form name="submitForm" action="<%=request.getContextPath()%>/tuserinfoes/useractionlist" method="post">
						<div style="float: left;width: 1200px;">
							&nbsp;&nbsp;&nbsp;&nbsp;彩种:<select name="lotno" style="width: 120px">
											<option value="">全部</option>
											<c:forEach items="${lotTypes}" var="types">
												<option value="${types.key }" <c:if test='${types.key eq lotno}'>selected</c:if>>${types.value }</option>
											</c:forEach>
									</select>
							期号:<input	name="batchcode" type="text" style="width: 120px" id="batchcode" value="${param.batchcode}" class="inputText" onfocus="this.select();" />
							投注类型:<select name="bettype" style="width: 80px">
											<option value="">全部</option>
											<option value="0" <c:if test='${"0" eq param.bettype}'>selected</c:if>>追号</option>
											<option value="1" <c:if test='${"1" eq param.bettype}'>selected</c:if>>套餐</option>
											<option value="2" <c:if test='${"2" eq param.bettype}'>selected</c:if>>投注</option>
											<option value="3" <c:if test='${"3" eq param.bettype}'>selected</c:if>>合买</option>
											<option value="4" <c:if test='${"4" eq param.bettype}'>selected</c:if>>赠送</option>
											<option value="5" <c:if test='${"5" eq param.bettype}'>selected</c:if>>无短信赠送</option>
									</select>
							状态:<select name="state" style="width: 80px">
											<option value="">全部</option>
											<option value="0" <c:if test='${"0" eq param.state}'>selected</c:if>>处理中</option>
											<option value="1" <c:if test='${"1" eq param.state}'>selected</c:if>>成功</option>
											<option value="3" <c:if test='${"3" eq param.state}'>selected</c:if>>撤单</option>
									</select>
							排序查询:<select name="mysort" style="width: 80px" onchange="sortSelect()" id="mysort">
											<option value="">全部</option>
											<option value="1" <c:if test='${"1" eq param.mysort}'>selected</c:if>>中奖金额从低到高</option>
											<option value="2" <c:if test='${"2" eq param.mysort}'>selected</c:if>>中奖金额从高到低</option>
									</select>
							<br/>
							投注时间:<input id="starttime" name="starttime" value="${param.starttime}"
										class="inputText" type="text" style="width: 120px;"
										 onclick="DateTime.onImageMouseDown(event,'Calendar','starttime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','starttime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
							到:<input id="endtime" name="endtime" value="${param.endtime}"
										class="inputText" type="text" ztype="date"
										style="width: 120px;"
										onclick="DateTime.onImageMouseDown(event,'Calendar','endtime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','endtime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
							投注金额:<input	name="startamt" type="text" style="width: 120px" id="startamt" value="${param.startamt}" class="inputText" onfocus="this.select();" />
							到:<input name="endamt" type="text" style="width: 120px" id="endamt" value="${param.endamt}" class="inputText" onfocus="this.select();" />
							<input type="submit" value="查询" class="inputButton">
							<input name="userno" value="${userno}" type="hidden" />
							<input id="startLine" name="startLine" type="hidden" value="${startLine }" />
							<input id="endLine" name="endLine" type="hidden" value="${endLine }" />
							<input id="orderBy" name="orderBy" type="hidden" value="${param.orderBy }" />
							<input id="orderDir" name="orderDir" type="hidden" value="${param.orderDir }" />
						</div>
					</form></td>
			</tr>
			<tr>
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2"
						class="dataTable" style="overflow: auto;">
							<tr class="dataTableHead">
								<td width="2%" height="30" class="thOver"></td>
								<td width="11%" class="thOver"><strong>订单号</strong>
								</td>
								<td width="6%" class="thOver"><strong>用户编号</strong>
								</td>
								<td width="6%" class="thOver"><strong>购买人编号</strong>
								</td>
								<td width="10%" class="thOver"><strong>投注号码</strong>
								</td>
								<td width="6%" class="thOver"><strong>订单金额</strong>
								</td>
								<td width="14%" class="thOver"><strong>开奖号码</strong>
								</td>
								<td width="11%" class="thOver"><strong>投注时间</strong>
								</td>
								<td width="7%" class="thOver"><strong>彩种</strong>
								</td>
								<td width="7%" class="thOver"><strong>期号</strong>
								</td>
								<td width="8%" class="thOver"><strong>中奖金额(元)</strong>
								</td>
								<td width="8%" class="thOver"><strong>派奖状态</strong>
								</td>
								<td width="6%" class="thOver"><strong>投注类型</strong>
								</td>
								<td width="5%" class="thOver"><strong>状态</strong>
								</td>
							</tr>
							<tbody id="data">
							</tbody>
							<tr>
								<td align="left" id="dg1_PageBar" colspan="18"><div
										style="float: right; font-family: Tahoma">
										<c:choose>
											<c:when test="${startLine != 0}">
												<a
													href="javascript:go('0')">第一页</a>
											</c:when>
											<c:otherwise>
												第一页
											</c:otherwise>
										</c:choose>
										|&nbsp;
										<c:choose>
											<c:when test="${startLine >= endLine}">
												<a
													href="javascript:gopre()">上一页</a>
											</c:when>
											<c:otherwise>
												上一页
											</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
											<span id="nextpage">
												<a
													href="javascript:gonext()">下一页</a>
											</span>
											<span id="nextpage2">
												下一页
											</span>
										&nbsp;|&nbsp;
											<span id="gotoend">
												<a
													href="javascript:goend()">最末页</a>
											</span>
											<span id="gotoend2">
												最末页
											</span>
										&nbsp;|&nbsp; &nbsp;&nbsp;转到第&nbsp;<input type="text"
											onkeyup="value=value.replace(/\D/g,'')" style="width: 40px"
											class="inputText" id="_PageBar_Index">&nbsp;页&nbsp;&nbsp;<input
											type="button" value="跳转" class="inputButton" onclick="go()">
										<script type="text/javascript">
											function gopre(){
												var _index = parseInt($("#startLine").val()) - (parseInt($("#maxResult").text()));
												go(_index);
											}
											function gonext(){
												var _index = parseInt($("#startLine").val()) + (parseInt($("#maxResult").text()));
												go(_index);
											}
											function goend(){
												var _index = parseInt($("#maxResult").text())*(parseInt($("#totalPage").text())-1);
												go(_index);
											}
										
											function go(index) {
												if(index || index == 0){
													if(index < 0){
														index = 0;
													}
													$("#startLine").val(index);
												}else{
													if($("#_PageBar_Index").val().trim() == ''){
														Dialog.alert("请输入正确的页数:)");
														return false;
													}
													var pageindex = parseInt($("#_PageBar_Index").val()) - 1;
													var _index = parseInt(pageindex)*parseInt($("#endLine").val());
													var _totalPage = parseInt($("#totalPage").text());
													if(pageindex < 0 || pageindex > _totalPage-1){
														Dialog.alert("请输入正确的页数:)");
														return false;
													}
													$("#startLine").val(_index);
												}
												document.submitForm.submit();
											}
										</script>
									</div>
									<div style="float: left; font-family: Tahoma">共
										<span id="totalResult"></span> 条记录，每页 <span id="maxResult"></span> 条，当前第
										<span id="currentPageNo"></span> / <span id="totalPage"></span> 页</div></td>
							</tr>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>