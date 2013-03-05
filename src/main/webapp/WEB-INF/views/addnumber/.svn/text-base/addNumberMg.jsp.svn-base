<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/map.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Dialog.js"></script>
</head>
<script type="text/javascript">
<% 
String errormsg = (String)request.getAttribute("errormsg");
String result = (String)request.getAttribute("result");
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

function sortSelect(){
	var _orderBy = $("#orderBy");
	var _orderDir = $("#orderDir")
	var nodeValue = $("#mysort option:selected").val();
	if(nodeValue==""){
		_orderBy.val("");
		_orderDir.val("");
	}else if (nodeValue == "1"){
		_orderBy.val("batchnum");
		_orderDir.val("ASC")
	}else if (nodeValue == "2"){
		_orderBy.val("batchnum");
		_orderDir.val("DESC")
	}else if (nodeValue == "3"){
		_orderBy.val("lastnum");
		_orderDir.val("ASC")
	}else if (nodeValue == "4"){
		_orderBy.val("lastnum");
		_orderDir.val("DESC")
	}else if (nodeValue == "5"){
		_orderBy.val("prizeend");
		_orderDir.val("ASC")
	}else if (nodeValue == "6"){
		_orderBy.val("prizeend");
		_orderDir.val("DESC")
	}
}

function process(tsubscribeNo){
	var tip = "确认<font color='red'>取消追号</font>？";
	Dialog.confirm(tip,function(){
		$.ajax({
			url:"<%=request.getContextPath() %>/addnumber/cancelAddNumber",
			type:"POST",
			data:{"tsubscribeNo":tsubscribeNo},
			success:function(data){
				if(data.errorCode == "0") {
					Dialog.alert(data.value);
					document.submitForm.submit();
				}else{
					Dialog.alert("操作异常");
				}
			}
		});
	},function(){return false;})
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
		tbody.append('<tr>')
		var _tsubscribe = data[i].tsubscribe;
		var _tuserinfo = data[i].tuserinfo;
		var _lotname = _map.get(_tsubscribe.lotno);
		var userno = _tuserinfo.userno;
		var nickname = '';
		if(_tuserinfo && _tuserinfo.nickname){
			nickname = _tuserinfo.nickname;
		}
		var url = '<%=request.getContextPath()%>/tuserinfoes/list?userno='+userno;
		tbody.append('<td>'+parseInt(${startLine }+1+i)+'</td><td>'+_tsubscribe.flowno+'</td><td>'+_lotname+'</td><td><a  href='+url+'>'+userno+'</a></td><td>'+_tsubscribe.beginbatch+'</td>'); 
		tbody.append('<td>' + _tsubscribe.batchnum + '</td>');
		tbody.append('<td>' + _tsubscribe.lastnum + '</td>');
		tbody.append('<td>' + _tsubscribe.beginbatch + '</td>');
		tbody.append('<td>' + _tsubscribe.lastbatch + '</td>');
		tbody.append('<td>' + parseFloat(parseInt(_tsubscribe.amount)/100) + '</td>');
		tbody.append('<td>' + parseFloat(parseInt(_tsubscribe.totalamt)/100) + '</td>');
		if(	""==_tsubscribe.ordertime){
			tbody.append('<td>' + "" + '</td>');
		}else if(_tsubscribe.ordertime==null){
			tbody.append('<td>' + "" + '</td>');
		}else {
			var startDa = new Date(_tsubscribe.ordertime);
			var startDaStr = startDa.format("yyyy-MM-dd hh:mm:ss");
			tbody.append('<td>' + startDaStr + '</td>');
		}
		
		if(_tsubscribe.type=="0"){
			tbody.append('<td>' + "追号" + '</td>');
		} else {
			tbody.append('<td>' + "套餐" + '</td>');
		}
		
		if( _tsubscribe.state=="0"){
			tbody.append('<td>' + "正常" + '</td>');
		}else if ( _tsubscribe.state=="1"){
			tbody.append('<td>' + "暂停" + '</td>');
		}else if( _tsubscribe.state=="2") {
			tbody.append('<td>' + "注销" + '</td>');
		}else if( _tsubscribe.state=="3") {
			tbody.append('<td>' + "结束" + '</td>');
		}
		
		
		if(_tsubscribe.endtime==null){
			tbody.append('<td>' + "" + '</td>');
		}else	if(	""==_tsubscribe.endtime){
			tbody.append('<td>' + "" + '</td>');
		}else {
			var endDa = new Date(_tsubscribe.endtime);
			var endDaStr = endDa.format("yyyy-MM-dd hh:mm:ss");
			tbody.append('<td>' + endDaStr + '</td>');
		}
		if(_tsubscribe.prizeend==null){
			tbody.append('<td>' + "是" + '</td>');
		} else {
			tbody.append('<td>' + "否" + '</td>');
		}
		
		tbody.append('<td>' + parseFloat(parseInt(_tsubscribe.prizeendamt)/100) + '</td>');
		tbody.append('<td>' + parseFloat(parseInt(_tsubscribe.leijiprizeendamt)/100) + '</td>');
		
		if(_tsubscribe.endsms=="1"){
			tbody.append('<td>' + "未发送" + '</td>');
		}else if (_tsubscribe.endsms=="2"){
			tbody.append('<td>' + "已发送" + '</td>');
		}else if(_tsubscribe.endsms==null){
			tbody.append('<td>' + "" + '</td>');
		} else {
			tbody.append('<td>' + _tsubscribe.endsms + '</td>');
		}
		
 		if((_tsubscribe.state==0)&&(_tsubscribe.lastnum!=0)&&(!_tsubscribe.cancancel||!_tsubscribe.cancancel!=1)){
					tbody.append('<td>' +"是" + '</td>');
			}else{
			tbody.append('<td>' + "否" + '</td>');
		} 
		
 		if((_tsubscribe.state==0)&&(_tsubscribe.lastnum!=0)&&(!_tsubscribe.cancancel||!_tsubscribe.cancancel!=1)){
			tbody.append('<td><a href="javascript:process(\''+ _tsubscribe.flowno+'\')">' +"撤单" + '</a></td>');
		}else{
			tbody.append('<td>' + "" + '</td>');
} 
 		
 		tbody.append('</tr>')
	});
});


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
</script>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form name="submitForm" action="<%=request.getContextPath()%>/addnumber/addNumberMg"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">用户编号:</td>
									<td><input name="userno" type="text" style="width: 120px" value="${userno }"></td>
									<td align="right">彩种:</td>
									<td><select name="lotno" style="width: 120px">
											<option value="">全部</option>
											<c:forEach items="${lotTypes}" var="types">
												<option value="${types.key }" <c:if test='${types.key eq lotno}'>selected</c:if>>${types.value }</option>
											</c:forEach>
									</select></td>
									<td align="right">开始投注期号:</td>
									<td><input name="beginbatch" type="text" style="width: 120px" value="${param.beginbatch }"></td>
									<td align="right">上次投注期号:</td>
									<td><input name="lastbatch" type="text" style="width: 120px" value="${param.lastbatch }"></td>
									<td align="right">追号状态:</td>
									<td><select name="state" style="width: 80px">
											<option value="" >全部</option>
											<option value="0" <c:if test='${"1" eq param.state}'>selected</c:if>>正常</option>
											<option value="1" <c:if test='${"1" eq param.state}'>selected</c:if>>暂停</option>
											<option value="2" <c:if test='${"2" eq param.state}'>selected</c:if>>注销</option>
											<option value="3" <c:if test='${"3" eq param.state}'>selected</c:if>>结束</option>
									</select></td>
									
									<td align="center"><input type="submit" value="查询"
										class="inputButton"></td>
								</tr>
								<tr>
									<td align="right">时间查询:</td>
									<td><input id="starttime" name="starttime" value="${param.starttime}"
										class="inputText" type="text" style="width: 120px;"
										 onclick="DateTime.onImageMouseDown(event,'Calendar','starttime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','starttime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif"></td>
										<td align="right">到:</td>
										<td>	<input id="endtime" name="endtime" value="${param.endtime}"
										class="inputText" type="text" ztype="date"
										style="width: 120px;"
										onclick="DateTime.onImageMouseDown(event,'Calendar','endtime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','endtime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
									</td>
									<td align="right">排序查询:</td>
									<td><select name="mysort" style="width: 130px" onchange="sortSelect()" id="mysort">
											<option value = "" >全部</option>
											<option value="1" <c:if test='${"1" eq param.mysort}'>selected</c:if>>购买期数从低到高</option>
											<option value="2" <c:if test='${"2" eq param.mysort}'>selected</c:if>>购买期数从高到低</option>
											<option value="3" <c:if test='${"3" eq param.mysort}'>selected</c:if>>剩余期数从低到高</option>
											<option value="4" <c:if test='${"4" eq param.mysort}'>selected</c:if>>剩余期数从高到低</option>
											<option value="5" <c:if test='${"5" eq param.mysort}'>selected</c:if>>中奖金额从低到高</option>
											<option value="6" <c:if test='${"6" eq param.mysort}'>selected</c:if>>中奖金额从高到低</option>
									</select></td>
								</tr>
							</table>
						</div>
						<input id="startLine" name="startLine" type="hidden" value="${startLine }" />
						<input id="endLine" name="endLine" type="hidden" value="${endLine }" />
						<input id="orderBy" name="orderBy" type="hidden" value="${orderBy }" />
						<input id="orderDir" name="orderDir" type="hidden" value="${orderDir }" />
					</form>
				</td>
			</tr>
			<tr>
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2"
						class="dataTable" style="overflow: auto;width: 1800px;">
							<tr class="dataTableHead">
								<td width="2%" height="30" class="thOver"></td>
								<td width="7%" class="thOver" title="订单编号"><strong>订单编号</strong></td>
								<td width="5%" class="thOver" title="彩种"><strong>彩种</strong></td>
								<td width="5%" class="thOver" title="用户编号"><strong>用户编号</strong></td>
								<td width="5%" class="thOver" title="期号"><strong>期号</strong></td>
						 		<td width="3%" class="thOver" title="购买期数"><strong>购买期数</strong></td>
						 		<td width="3%" class="thOver" title="剩余期数"><strong>剩余期数</strong></td>
						 		<td width="5%" class="thOver" title="开始期号"><strong>开始期号</strong></td>
						 		<td width="5%" class="thOver" title="上次期号"><strong>上次期号</strong></td>
						 		<td width="5%" class="thOver" title="单次投注金额"><strong>单次投注金额</strong></td>
						 		<td width="5%" class="thOver" title="剩余投注总金额"><strong>剩余投注总金额</strong></td>
						 		<td width="7%" class="thOver" title="创建订单时间"><strong>创建订单时间</strong></td>
						 		<td width="4%" class="thOver" title="追号方式"><strong>追号方式</strong></td>
						 		<td width="3%" class="thOver" title="状态"><strong>状态</strong></td>
						 		<td width="7%" class="thOver" title="结束时间"><strong>结束时间</strong></td>
						 		<td width="5%" class="thOver" title="中奖继续追号"><strong>中奖继续追号</strong></td>
						 		<td width="5%" class="thOver" title="中奖金额"><strong>中奖金额</strong></td>
						 		<td width="5%" class="thOver" title="停止追号奖金"><strong>停止追号奖金</strong></td>
						 		<td width="4%" class="thOver" title="余期不足短信"><strong>余期不足短信</strong></td>
						 		<td width="3%" class="thOver" title="允许撤单"><strong>允许撤单</strong></td>
						 		<td width="5%" class="thOver" title="操作"><strong>操作</strong></td>
							</tr>
							<tbody id="data" >
							</tbody>
							<tr>
								<td align="left" id="dg1_PageBar" colspan="17"><div
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