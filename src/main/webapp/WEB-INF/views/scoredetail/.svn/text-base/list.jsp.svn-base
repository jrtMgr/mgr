<%@page import="java.util.Set"%>
<%@page import="com.ruyicai.mgr.controller.ScoredetailController"%>
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
Set<Entry<String, String>> s =  ScoredetailController.scoreTypeMap.entrySet();
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
<%
if(!StringUtil.isEmpty(result)) {
%>
$(document).ready(function() {
	
	// 将彩种放入map中
	var _map = new Map();
	<%
		for(Entry<String, String> e :s){
	%>
			_map.put('<%=e.getKey()%>','<%=e.getValue()%>');
	<%
		}
	%>
	var resultMessage = parseObj('<%=result%>');
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
		tbody.append('<tr>');
		var userno = data[i].userno;
		var url = '<%=request.getContextPath()%>/tuserinfoes/list?userno='+userno;
		tbody.append('<td><a  href='+url+'>'+userno+'</a></td>');
		if(data[i].bussinessId){
			tbody.append('<td>'+data[i].bussinessId+'</td>');
		}else{
			tbody.append('<td></td>');
		}
		if(data[i].scoreType){
			var memo = _map.get(data[i].scoreType);
			tbody.append('<td>'+memo+'</td>');
		}else{
			tbody.append('<td></td>');
		}
		if(data[i].score){
			tbody.append('<td>'+data[i].score+'</td>');
		}else{
			tbody.append('<td></td>');
		}
		if(data[i].currentScore){
			tbody.append('<td>'+data[i].currentScore+'</td>');
		}else{
			tbody.append('<td></td>');
		}
		if(data[i].createTime){
			var da = new Date(data[i].createTime);
			var daStr = da.format("yyyy-MM-dd hh:mm:ss");
			tbody.append('<td>'+daStr+'</td>');
		}else{
			tbody.append('<td></td>');
		}
		if(data[i].state){
			tbody.append('<td>'+data[i].state+'</td>');
		}else{
			tbody.append('<td></td>');
		}
		if(data[i].memo){
			tbody.append('<td>'+data[i].memo+'</td>');
		}else{
			tbody.append('<td></td>');
		}
		tbody.append('<td></td>');
		tbody.append('</tr>')
	});
}); 	

<%}%>

function addscore(){
	var userno = document.getElementById("userno1").value;
	var giveScore = document.getElementById("giveScore1").value;
	var memo = document.getElementById("memo1").value;
	alert(memo);
	if(userno == "" || giveScore==""){
		Dialog.alert("请输入用户编号或积分");
		return;
	}
	Dialog.confirm("确定添加吗？",function(){
		$.ajax({
			url:"<%=request.getContextPath() %>/scoredetail/addScore",
			type:"POST",
			data:{"userno":userno,"giveScore":giveScore,"memo":memo},
			success:function(data){
				if(data.errorCode == "0") {
					Dialog.alert("添加成功"+data.value);
				}else{
					Dialog.alert("添加失败"+data.errorCode);
				}
				
			}
		});
	},function(){return false;})
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
</script>
<body>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form name="submitForm" id="submitForm" action="<%=request.getContextPath()%>/scoredetail/list1"
						method="post">
						<input id="startLine" name="startLine" type="hidden" value="${startLine }" />
						<input id="endLine" name="endLine" type="hidden" value="${endLine }" />
						<input id="orderBy" name="orderBy" type="hidden" value="${orderBy }" />
						<input id="orderDir" name="orderDir" type="hidden" value="${orderDir }" />
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">用户ID:</td>
									<td><input name="userno" id="userno" type="text" style="width: 120px" value="${param.userno}"></td>
									<td></td>
									<td align="right" >时间:</td>
									<td width="250px;"><input id="starttime" name="starttime" value="${param.starttime}"
										class="inputText" type="text" style="width: 90px;"
										 onclick="DateTime.onImageMouseDown(event,'Calendar','starttime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','starttime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
										-&nbsp;
										<input id="endtime" name="endtime" value="${param.endtime}"
										class="inputText" type="text" ztype="date"	style="width: 90px;"
										onclick="DateTime.onImageMouseDown(event,'Calendar','endtime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','endtime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
									</td>
									<td align="right">积分类型:</td>
									<td>
										<select name="scoreType" id="scoreType" style="width: 120px">
												<option value="">全部</option>
												<option value="-2" <c:if test="${param.scoreType == -2}">selected</c:if> >全部(不包括兑换积分)</option>
												<%
													for(Entry<String, String> e :s){
												%>
													<option value="<%=e.getKey()%>" <%if(e.getKey().equals(request.getParameter("scoreType"))){ %>selected<%} %> ><%=e.getValue()%></option>
												<%} %>
										</select>
									</td>
									<td align="center">
										<input type="submit" value="查询" class="inputButton">
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
							<tr class="dataTableHead">
								<td width="6%" class="thOver"><strong>用户编号</strong></td>
								<td width="12%" class="thOver"><strong>方案编号或订单号</strong></td>
								<td width="5%" class="thOver"><strong>积分类型</strong></td>
								<td width="7%" class="thOver"><strong>积分</strong></td>
								<td width="7%" class="thOver"><strong>积分总额</strong></td>
								<td width="10%" class="thOver"><strong>时间</strong></td>
								<td width="6%" class="thOver"><strong>状态</strong></td>
								<td width="8%" class="thOver"><strong>备注</strong></td>
								<td width="8%" class="thOver"><strong>操作</strong></td>
							</tr>
							<tbody id="data">
							</tbody>
							<tr>
								<td align="left" id="dg1_PageBar" colspan="8"><div
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
												var s = $("#startLine").val();
												if(s == ""){
													s = 0;
												}
												var _index = parseInt(s) + (parseInt($("#maxResult").text()));
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
		<table width="30%" cellspacing="0" cellpadding="2"	class="dataTable">
				<tr class="dataTableHead" >
					<td  class="thOver" colspan="2"><strong>手动添加积分</strong></td>
				</tr>
				<tr>
					<td  class="thOver" style="width: 20px;" align="right">用户编号：</td>
					<td class="thOver"><input type="text"  name="userno1" id="userno1"/></td>
				</tr>
				<tr>
					<td class="thOver"  align="right">积分：</td>
					<td class="thOver"><input type="text" name="giveScore1" id="giveScore1"/></td>
				</tr>
				<tr>
					<td class="thOver"  align="right">备注：</td>
					<td class="thOver"><input type="text" name="memo1" id="memo1"/></td>
				</tr>
				<tr>
					<td class="thOver" colspan="2" align="center"><input type="button" value="提交" onclick="addscore()"/></td>
				</tr>
		</table>
</body>
</html>