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

function parseObj(strData){
    return (new Function( "return " + strData ))();
}
$(document).ready(function() {
	var resultMessage = parseObj("<%=result%>");
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
		var id = data[i].letter.id;
		tbody.append('<tr>')
		tbody.append('<td></td><td>'+id+'</td>');
		tbody.append('<td title='+data[i].letter.fromUserno+'>'+data[i].letter.fromUserno+'</td>');
		tbody.append('<td title='+data[i].letter.toUserno+'>'+data[i].letter.toUserno+'</td>');
		if(data[i].letter.letterType==0){
			tbody.append('<td title='+data[i].letter.letterType+'>普通站内信</td>');
		}else if(data[i].letter.letterType==1){
			tbody.append('<td title='+data[i].letter.letterType+'>开奖站内信</td>');
		}else if(data[i].letter.letterType==2){
			tbody.append('<td title='+data[i].letter.letterType+'>中奖站内信</td>');
		}
		
		tbody.append('<td>'+data[i].letter.title+'</td>');
		tbody.append('<td>'+data[i].letter.content+'</td>');
		if(data[i].letter.hasRead == 0){
			tbody.append('<td title='+data[i].letter.hasRead+'>未读</td>');
		}else{
			tbody.append('<td title='+data[i].letter.hasRead+'>已读</td>');
		}
		
		if(data[i].letter.hasDel == 0){
			tbody.append('<td title='+data[i].letter.hasDel+'>未删除</td>');
		}else{
			tbody.append('<td title='+data[i].letter.hasDel+'>已删除</td>');
		}
		
		var createTime = new Date(data[i].letter.createTime);
		var createTimeStr = createTime.format("yyyy-MM-dd hh:mm:ss");
		tbody.append('<td title='+createTimeStr+'>'+createTimeStr+'</td>');
		var readTime = new Date(data[i].letter.readTime);
		var readTimeStr = readTime.format("yyyy-MM-dd hh:mm:ss");
		tbody.append('<td title='+readTimeStr+'>'+readTimeStr+'</td>');
		var delTime = new Date(data[i].letter.delTime);
		var delTimeStr = delTime.format("yyyy-MM-dd hh:mm:ss");
		tbody.append('<td title='+delTimeStr+'>'+delTimeStr+'</td>');
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
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<form action="<%=request.getContextPath()%>/letter/list" id="submitForm" name = "submitForm">
									<input name="startLine" id="startLine" type="hidden" style="width: 120px" value="${startLine}">
									<input name="endLine" id="endLine" type="hidden" style="width: 120px" value="${endLine}">
									<td align="right">发送人编号:</td>
									<td><input name="fromUserno" type="text" style="width: 120px" value="${param.fromUserno}"></td>
									<td align="right">接受人编号:</td>
									<td><input name="toUserno" type="text" style="width: 120px" value="${param.toUserno}"></td>
									<td><input type="submit" value="查询" 	class="inputButton"></td>
									
									</form>
								</tr>
							</table>
						</div>
					
				</td>
			</tr>
			<tr>
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="130%" cellspacing="0" cellpadding="2"
						class="dataTable">
							<tr class="dataTableHead">
								<td width="1%" height="30" class="thOver"></td>
								<td width="2%" class="thOver"><strong>ID</strong></td>
								<td width="4%" class="thOver"><strong>发送人编号</strong></td>
								<td width="4%" class="thOver"><strong>接受人编号</strong></td>
								<td width="4%" class="thOver"><strong>站内信类别</strong></td>
								<td width="7%" class="thOver"><strong>标题</strong></td>
								<td width="19%" class="thOver"><strong>内容</strong></td>
								<td width="3%" class="thOver"><strong>是否读</strong></td>
								<td width="3%" class="thOver"><strong>是否删除</strong></td>
								<td width="7%" class="thOver"><strong>创建时间</strong></td>
								<td width="7%" class="thOver"><strong>读取时间</strong></td>
								<td width="7%" class="thOver"><strong>删除时间</strong></td>
							</tr>
							<tbody id="data">
							</tbody>
							<tr>
								<td  align="left" id="dg1_PageBar" colspan="12"><div
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
			<tr><td style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px; font-size: 14px;"><a href="<%=request.getContextPath()%>/letter/addUI">新增</a></td></tr>
		</tbody>
	</table>
</body>
</html>