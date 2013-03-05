<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
function cxdj() {
	var filename = $.trim($("#filename").val());
	if(filename == "") {
		Dialog.alert("请输入文件名");
		return false;
	} 
	var url = "<%=request.getContextPath()%>/lotCenter/rePrizeNM?filename="+filename;
	location.href=url;
}
function qqdj() {
	var time = $.trim($("#time").val());
	if(time == "") {
		Dialog.alert("请输入时间");
		return false;
	} 
	var url = "<%=request.getContextPath()%>/lotCenter/nmencash?time="+time;
	location.href=url;
}

function dzdj() {
	var flowno = $.trim($("#flowno").val());
	if(flowno == "") {
		Dialog.alert("请输入票号");
		return false;
	} 
	var url = "<%=request.getContextPath()%>/lotCenter/singleencash?flowno="+flowno;
	location.href=url;
}

function sgdj() {
	var lotno = $.trim($("#lotno").val());
	if(lotno == "") {
		Dialog.alert("请输入lotno");
		return false;
	} 
	var batchcode = $.trim($("#batchcode").val());
	if(batchcode == "") {
		Dialog.alert("请输入batchcode");
		return false;
	} 
	var url = "<%=request.getContextPath()%>/lotCenter/encash?lotno="+lotno+"&batchcode="+batchcode;
	location.href=url;
}


function qqdj() {
	var time = $.trim($("#time").val());
	if(time == "") {
		Dialog.alert("请输入时间");
		return false;
	} 
	var url = "<%=request.getContextPath()%>/lotCenter/nmencash?time="+time;
	location.href=url;
}

function DYJxq() {
	var lotno = $.trim($("#lotnoxq").val());
	if(lotno == "") {
		Dialog.alert("请输入lotno");
		return false;
	} 
	var lotid = $.trim($("#lotid").val());
	if(lotid == "") {
		Dialog.alert("请输入lotid");
		return false;
	} 
	var url = "<%=request.getContextPath()%>/lotCenter/tlotctrl?lotno="+lotno+"&lotid="+lotid;
	location.href=url;
}

function DYJkj() {
	var lotno = $.trim($("#lotnokj").val());
	if(lotno == "") {
		Dialog.alert("请输入lotno");
		return false;
	} 
	var batchcode = $.trim($("#batchcodekj").val());
	if(batchcode == "") {
		Dialog.alert("请输入batchcode");
		return false;
	} 
	var url = "<%=request.getContextPath()%>/lotCenter/twininfo?lotno="+lotno+"&batchcode="+batchcode;
	location.href=url;
}

function DYJdj() {
	var lotno = $.trim($("#lotnodj").val());
	if(lotno == "") {
		Dialog.alert("请输入lotnodj");
		return false;
	} 
	var batchcode = $.trim($("#batchcodedj").val());
	if(batchcode == "") {
		Dialog.alert("请输入batchcode");
		return false;
	} 
	var url = "<%=request.getContextPath()%>/lotCenter/prizeorder?lotno="+lotno+"&batchcode="+batchcode;
	location.href=url;
}
function jrrcxq() {
	var lotno = $.trim($("#lotnojxq").val());
	if(lotno == "") {
		Dialog.alert("请输入lotno");
		return false;
	} 
	var batchcode = $.trim($("#batchcodejxq").val());
	if(batchcode == "") {
		Dialog.alert("请输入batchcode");
		return false;
	} 
	var agencyno = $.trim($("#agencynojxq").val());
	if(agencyno == "") {
		Dialog.alert("请输入agencyno");
		return false;
	} 
	var starttime = $.trim($("#starttime").val());
	if(starttime == "") {
		Dialog.alert("请输入starttime");
		return false;
	} 
	var endtime = $.trim($("#endtime").val());
	if(endtime == "") {
		Dialog.alert("请输入endtime");
		return false;
	} 
	var url = "<%=request.getContextPath()%>/lotCenter/newissue?lotno="+lotno+"&batchcode="+batchcode
			+"&agencyno="+agencyno+"&starttime="+starttime+"&endtime="+endtime;
	
	location.href=url;
}

</script>	
</head>
<body style="margin-left: 10px;">
<table width="50%" cellspacing="0" cellpadding="2" class="dataTable" style="margin-top: 10px;">
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="3"><strong>账户余额</strong></td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<a href="<%=request.getContextPath()%>/lotCenter/nmaccount">查看各彩票中心账户余额</a>
				</td>
			</tr>
</table>			
<table width="50%" cellspacing="0" cellpadding="2" class="dataTable" style="margin-top: 10px; height: 120px">
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="3"><strong>内蒙福彩后台管理</strong></td>
			</tr>
			<tr>
				<td align="right">文件名：</td>
				<td>
					<input name="filename" type="text" style="width: 120px" id="filename" class="inputText" onfocus="this.select();" />
				</td>
				<td>
					<a onclick="cxdj();" style="cursor:pointer;">重新兑奖 </a>
				</td>
				</tr>
			<tr>
				<td align="right">time：</td>
				<td>
					<input name="time" type="text" style="width: 120px" id="time"	class="inputText" onfocus="this.select();" />
				</td>
				<td>
					<a onclick="qqdj();" style="cursor:pointer;">请求兑奖</a>
				</td>
			</tr>
			<tr>
				<td align="right">flowno：</td>
				<td>
					<input name="flowno" type="text" style="width: 120px" id="flowno" class="inputText" onfocus="this.select();"/>
				</td>
				<td>
					<a onclick="dzdj();" style="cursor:pointer;">单张兑奖 </a>
				</td>
			</tr>
			<tr>
				<td align="right">lotno：
					<input name="lotno" type="text" style="width: 60px" id="lotno" class="inputText" onfocus="this.select();"/>
				</td>
				<td>batchcode：
					<input name="batchcode" type="text" style="width: 60px" id="batchcode" class="inputText" onfocus="this.select();"/>
				</td>
				<td>
					<a onclick="sgdj();" style="cursor:pointer;">手工请求如意彩兑奖</a>
				</td>
			</tr>
	</table>
	<table width="50%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px;height: 120px">
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="3"><strong>大赢家后台管理</strong></td>
			</tr>
			<tr>
				<td align="right">lotno：
					<input name="lotnoxq" type="text" style="width: 60px" id="lotnoxq" class="inputText" onfocus="this.select();"/>
				</td>
				<td>lotid：
					<input name="lotid" type="text" style="width: 60px" id="lotid" class="inputText" onfocus="this.select();"/>
				</td>
				<td>
					<a onclick="DYJxq();" style="cursor:pointer;">大赢家新期</a>
				</td>
			</tr>
			<tr>
				<td align="right">lotno：
					<input name="lotnokj" type="text" style="width: 60px" id="lotnokj" class="inputText" onfocus="this.select();"/>
				</td>
				<td>batchcode：
					<input name="batchcodekj" type="text" style="width: 60px" id="batchcodekj" class="inputText" onfocus="this.select();"/>
				</td>
				<td>
					<a onclick="DYJkj();" style="cursor:pointer;">大赢家开奖</a>
				</td>
			</tr>
			<tr>
				<td align="right">lotno：
					<input name="lotnodj" type="text" style="width: 60px" id="lotnodj" class="inputText" onfocus="this.select();"/>
				</td>
				<td>batchcode：
					<input name="batchcodedj" type="text" style="width: 60px" id="batchcodedj" class="inputText" onfocus="this.select();"/>
				</td>
				<td>
					<a onclick="DYJdj();" style="cursor:pointer;">大赢家兑奖</a>
				</td>
			</tr>
	</table>
	
	<table width="50%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px;height: 120px">
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="3"><strong>金软瑞彩彩票后台管理</strong></td>
			</tr>
			<tr>
				<td align="left" colspan="3">lotno：
					<input name="lotnojxq" type="text" style="width: 60px" id="lotnojxq" class="inputText" onfocus="this.select();"/>
					batchcode：
					<input name="batchcodejxq" type="text" style="width: 60px" id="batchcodejxq" class="inputText" onfocus="this.select();"/>
					agencyno：
					<select name="agencynojxq" id="agencynojxq" class="inputText" style="width: 60px">
						<option value="T0001">福彩</option>
						<option value="00002">体彩</option>
					</select>
					<br>
					<br>
					starttime：
					<input name="starttime" type="text" style="width: 60px" id="starttime" class="inputText" onfocus="this.select();"/>
					endtime：
					<input name="endtime" type="text" style="width: 60px" id="endtime" class="inputText" onfocus="this.select();"/>
					<a onclick="jrrcxq();" style="cursor:pointer;">开新期</a>
				</td>
			</tr>
	</table>
</body>
</html>