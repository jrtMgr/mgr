<%@page import="com.ruyicai.mgr.domain.Tactivities"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
<%@page import="java.util.Map"%>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/js/map.js"></script>
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
	$.each(data,function(i,n){
		tbody.append('<tr>')
		tbody.append('<td>'+parseInt(${startLine }+1+i)+'</td>');
		tbody.append('<td>'+data[i].id+'</td>');
		tbody.append('<td>' + data[i].sendusername + '</td>');
		tbody.append('<td>'+data[i].reciverUserno+'</td>');
		tbody.append('<td>' + data[i].amt + '</td>');
		tbody.append('<td>' +data[i].content +'</td>');
		if(data[i].reciveState==0){
			tbody.append('<td>' +"未领取" +'</td>');
		} else {
			tbody.append('<td>' + "已领取" +'</td>');
		}
		var da = new Date(data[i].createTime);
		var daStr = da.format("yyyy-MM-dd hh:mm:ss");
		tbody.append('<td title='+daStr+'>'+daStr+'</td>');
		tbody.append('<td>' +'' +'</td>');
		tbody.append('</tr>');
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
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
		<form name="submitForm" action="<%=request.getContextPath()%>/sendmoney/list" method="post">
						<td align="right">赠送人用户名:</td>
						<td><input name="sendusername" type="text" style="width: 120px" value="${param.sendusername}"></td>
						<td align="right">接收人编号:</td>
						<td><input name="reciverUserno" type="text" style="width: 120px" value="${param.reciverUserno}"></td>
						<td align="right">接受:</td>
						<td><select name="reciveState" style="width:100px">
								<option value="">--全部--</option>
								<option value="0" <c:if test='${0 == param.reciveState}'>selected</c:if>>未接受</option>
								<option value="1" <c:if test='${1 == param.reciveState}'>selected</c:if>>已接受</option>
						</select></td>
						<td align="center"><input type="submit" value="查询" class="inputButton"></td>
						<input name="startLine" id="startLine" type="hidden" style="width: 120px" value="${startLine}">
						<input name="endLine" id="endLine" type="hidden" style="width: 120px" value="${endLine}">
			</form>
								</tr>
							</table>
						</div>
					
				</td>
			</tr>
			<tr>
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2"
						class="dataTable">
							<tr class="dataTableHead">
								<td width="1%" height="30" class="thOver"></td>
								<td width="10%" class="thOver"><strong>ID</strong></td>
								<td width="4%" class="thOver"><strong>赠送人</strong></td>
								<td width="5%" class="thOver"><strong>接收人编号</strong></td>
								<td width="5%" class="thOver"><strong>金额(元)</strong></td>
								<td width="12%" class="thOver"><strong>红包内容</strong></td>
								<td width="4%" class="thOver"><strong>领取</strong></td>
								<td width="7%" class="thOver"><strong>创建时间</strong></td>
								<td width="3%" class="thOver"><strong></strong></td>
							</tr>
							<tbody id="data">
							</tbody>
							<tr>
								<td  align="left" id="dg1_PageBar" colspan="8"><div
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
														Dialog.alert("请输入正确的页数!");
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
			<tr><td>&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/sendmoney/addInfo">新增</a></td></tr>
		</tbody>
	</table>
</body>
</html>