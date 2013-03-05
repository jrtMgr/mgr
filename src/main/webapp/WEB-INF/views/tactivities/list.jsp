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
/* function getTypeValue(key){
	var _map = new Map();
	_map.put("1", "充值成功");
	_map.put("2", "购彩成功");
	_map.put("3", "合买发单返奖");
	_map.put("4", "追号包年赠送");
	_map.put("5", "中奖加奖");
	_map.put("6", "首次充值赠送");
	_map.put("7", "充值赠送");
	_map.put("8", "老用户充值赠送");
	return _map.get(key)?_map.get(key):key;
} */

function wanfa(_wanfa){
	if(_wanfa==null){
		return "";
	} else {
		return _wanfa;
	}
}

function process(id,state){
	var tip;
	if(state == 0){
		tip = "确认<font color='red'>关闭</font>？";
	}else if(state == 1){
		tip = "确认<font color='red'>开启</font>？";
	}else{
		Dialog.alert("操作异常");
		return false;
	}
	Dialog.confirm(tip,function(){
		$.ajax({
			url:"<%=request.getContextPath() %>/tactivities/updateActiveState",
			type:"POST",
			data:{"id":id,"state":state},
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
		var type = "";
		tbody.append('<tr>')
		tbody.append('<td>'+parseInt(${startLine }+1+i)+'</td><td>'+data[i].id+'</td><td>' + data[i].lotno + '</td><td>' + wanfa(data[i].playtype)+ '</td><td>' +data[i].subChannel+'</td><td>'+data[i].channel+'</td>');
		tbody.append('<td>' +data[i].memo +'</td>');
		tbody.append('<td>' +data[i].express +'</td>');
		if(data[i].state==0){
			tbody.append('<td>' +"失效" +'</td>');
		} else {
			tbody.append('<td>' + "有效" +'</td>');
		}
		if(data[i].state==0){
			tbody.append('<td>' +'<a href="javascript:process(\''+data[i].id+'\',\'1\')">开启</a>' + '</td>');
		} else {
			tbody.append('<td>' + '<a href="javascript:process(\''+data[i].id+'\',\'0\')">关闭</a>' +'</td>');
		}
		tbody.append('</tr>')
	});
});
</script>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form name = "submitForm" action="<%=request.getContextPath()%>/tactivities/list"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td></td>
									<td align="center"><input type="submit" value="查询" class="inputButton">&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
							</table>
						</div>
						<input name="startLine" id="startLine" type="hidden" style="width: 120px" value="${startLine}">
							<input name="endLine" id="endLine" type="hidden" style="width: 120px" value="${endLine}">
					</form>
				</td>
			</tr>
			<tr>
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2"
						class="dataTable" style="overflow: auto;width: 1700px;">
							<tr class="dataTableHead">
								<td width="3%" height="30" class="thOver"></td>
								<td width="3%" class="thOver"><strong>ID</strong></td>
								<td width="4%" class="thOver"><strong>彩种</strong></td>
								<td width="11%" class="thOver"><strong>玩法</strong></td>
								<td width="5%" class="thOver"><strong>大渠道</strong></td>
								<td width="4%" class="thOver"><strong>渠道</strong></td>
								<td width="7%" class="thOver"><strong>活动描述</strong></td>
								<td width="55%" class="thOver"><strong>活动金额表达式</strong></td>
								<td width="4%" class="thOver"><strong>状态</strong></td>
								<td width="4%" class="thOver"><strong>操作</strong></td>
							</tr>
							<tbody id="data">
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