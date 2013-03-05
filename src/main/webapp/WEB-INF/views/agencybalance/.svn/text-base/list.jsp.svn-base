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
function delete1(id){
	if(window.confirm("确定删除吗？")){
		window.location.href="<%=request.getContextPath()%>/agencybalance/delete1?id="+id;
	}
}
function parseObj(strData){
    return (new Function( "return " + strData ))();
}

//将彩种放入map中
var _map = new Map();
<%
	Map<String,String> lotTypes = com.ruyicai.mgr.controller.AgencyBalanceController.map;
	for(Entry lotType :lotTypes.entrySet()){
%>
		_map.put('<%=lotType.getKey() %>','<%=lotType.getValue() %>');
<%
	}
%>

$(document).ready(function() {
	var resultMessage = parseObj("<%=result%>");
	var data = resultMessage.value;
	var tbody = $("#data");
	$.each(data,function(i,n){
		var id = data[i].id;
		tbody.append('<tr>');
		tbody.append('<td title='+data[i].agencyno+'>'+_map.get(data[i].agencyno)+'</td>');
		tbody.append('<td title='+data[i].money+'>'+data[i].money+'</td>');
		tbody.append('<td title='+data[i].mobile+'>'+data[i].mobile+'</td>');
		var da = new Date(data[i].createtime);
		var daStr = da.format("yyyy-MM-dd hh:mm:ss");
		tbody.append('<td title='+daStr+'>'+daStr+'</td>');
		if(data[i].issendmsg==1){
			tbody.append('<td>发送</td>');
		}else{
			tbody.append('<td>不发送</td>');
		}
		tbody.append('<td><a href="javascript:delete1(\''+data[i].id+'\')">删除</a></td>');
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
	<table width="70%" cellspacing="0" cellpadding="2" class="dataTable">
		<tr class="dataTableHead">
			<td width="12%" class="thOver"><strong>彩票中心</strong></td>
			<td width="15%" class="thOver"><strong>预警金额（元）</strong></td>
			<td width="30%" class="thOver"><strong>电话号</strong></td>
			<td width="15%" class="thOver"><strong>创建时间</strong></td>
			<td width="8%" class="thOver"><strong>是否发送短信</strong></td>
			<td width="8%" class="thOver"><strong>操作</strong></td>
		</tr>
		<tbody id="data">
		</tbody>
		<tr>
			<td colspan="5" align="left"><a style="cursor:hand; font-size: 12px;" href="<%=request.getContextPath()%>/agencybalance/addUI">新增监控</a>
			</td>
		</tr>
	</table>
</body>
</html>