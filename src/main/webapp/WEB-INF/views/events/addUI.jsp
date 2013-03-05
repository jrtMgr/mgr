<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
	<head>
		<title>添加对阵（期数）</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<link href="<%=request.getContextPath()%>/styles/default.css" rel="stylesheet" type="text/css">
		<meta http-equiv="pragma" content="no-cache">
	</head>

	<script language="javascript">
	$(document).ready(function() {
		$("#maincontent .list tr:even").css("background-color","#eaf3ff");
	});
	//添加期号
	function addIssus(){
		location.href='<%=request.getContextPath()%>/events/addIssusUI';
	}
	
	//自动获取
	function getFlData(){
		var id = $("#id").val();
		if(id==""){
			alert("请先设置期号");
			return;
		}
		var url = $("#getFlDataUrl").val();

		$("#getId").val(id);
		$("#getFlUrl").val(url);

		document.getFlForm.submit();
		
	}

	//保存自动获取数据到数据库
	function saveFlData(operation){
		var length = $("[name='dataName']").length;
		var state = true;
		var strFloat = /^[0-9]{1,2}\.[0-9]{2,3}$/;
		var str = /^\d*$/;
		var string;
		$("[name='dataOps']").each(function(){
			if(!strFloat.test(this.value) && this.value != ''){
				string = "欧赔平均胜率必须输入（00.00）格式的数字";
				state = false;
			}
		});
		if(state){ 
			$("[name='dataOpp']").each(function(){
				if(!strFloat.test(this.value) && this.value != ''){
					string = "欧赔平均平率必须输入（00.00）格式的数字";
					state = false;
				}
			});
		}
		if(state){
			$("[name='dataOpf']").each(function(){
				if(!strFloat.test(this.value) && this.value != ''){
					string = "欧赔平均负率必须输入（00.00）格式的数字";
					state = false;
				}
			});
		}
		if(state){
			$("[name='dataLspmzd']").each(function(){
				if(!str.test(this.value)){
					string = "主队联赛排名必须输入数字";
					state = false;
				}
			});
		}
		if(state){
			$("[name='dataLspmkd']").each(function(){
				if(!str.test(this.value)){
					string = "客队联赛排名必须输入数字";
					state = false;
				}
			});
		}
		if(state){
			$("[name='dataZ8s']").each(function(){
				if(!str.test(this.value)){
					string = "主队近10场胜场数必须输入数字";
					state = false;
				}
			});
		}
		if(state){
			$("[name='dataZ8p']").each(function(){
				if(!str.test(this.value)){
					string = "主队近10场平场数必须输入数字";
					state = false;
				}
			});
		}
		if(state){
			$("[name='dataZ8f']").each(function(){
				if(!str.test(this.value)){
					string = "主队近10场负场数必须输入数字";
					state = false;
				}
			});
		}
		if(state){
			$("[name='dataZ8jq']").each(function(){
				if(!str.test(this.value)){
					string = "主队近10场进球数必须输入数字";
					state = false;
				}
			});
		}
		if(state){
			$("[name='dataZ8sq']").each(function(){
				if(!str.test(this.value)){
					string = "主队近10场失球数必须输入数字";
					state = false;
				}
			});
		}
		if(state){
			$("[name='dataK8s']").each(function(){
				if(!str.test(this.value)){
					string = "客队近10场胜场数必须输入数字";
					state = false;
				}
			});
		}
		if(state){
			$("[name='dataK8p']").each(function(){
				if(!str.test(this.value)){
					string = "客队近10场平场数必须输入数字";
					state = false;
				}
			});
		}
		if(state){
			$("[name='dataK8f']").each(function(){
				if(!str.test(this.value)){
					string = "客队近10场负场数必须输入数字";
					state = false;
				}
			});
		}
		if(state){
			$("[name='dataK8jq']").each(function(){
				if(!str.test(this.value)){
					string = "客队近10场进球数必须输入数字";
					state = false;
				}
			});
		}
		if(state){
			$("[name='dataK8sq']").each(function(){
				if(!str.test(this.value)){
					string = "客队近10场失球数必须输入数字";
					state = false;
				}
			});
		}

		if(!state){
			alert(string);
			return ;
		}
		
		document.searchFrom.submit();
	}
	</script>
	
	<body>
		<form name="searchFrom" method="post" action="<%=request.getContextPath()%>/events/saveFlData">
			<input id="id" name="id" type="hidden" value="${iss.id}">
			<div align="left" id="maincontent">
				<table style="width: 30%" border="1" class="List" id="tblCondition">
					<tr>
						<td colspan="100%" class="Main" align="center">
							添加对阵(期数)
						</td>
					</tr>
					<tr>
						<td class="Title" style="padding-left: 10px">
							<font size="2">期号设置：</font><br>
						</td>
					</tr>
					<tr>
						<td>玩法</td><td>胜负彩</td><td>任9</td><td>六场半全场</td><td>四场进球</td>
					</tr>
					<tr>
						<td>期号	</td>
						<td>
						${iss.vdlId }
						</td>
						<td>
						${iss.any9Id }
						</td>
						<td>
						${iss.games6Id }
						</td>
						<td>
						${iss.games4Id }
						</td>
					</tr>
					<tr>
						<td>
							<input type="button" align="left" id="add" name="add" class="button" value="新增期号" style="width: 80" 
								   onClick="addIssus()" />
						</td>
					</tr>
					</table>
					<table border="1" width="100%">
					<tr>
						<td style="padding-left: 10px" >
							<font size="2">场次设置:</font><br>
						</td>
					</tr>
					<tr>
						<td width="15%">
							用来获取对阵信息的URL地址：
						</td>
						<td width="30%"><input type="text" name="getFlDataUrl" id="getFlDataUrl" size="70%"></td>
						<td width="8%">
							<input type="button" value="自动获取" onclick="getFlData()" />
						</td>
					</tr>
				</table>
				<table width="100%" class="List" border="1px;">
						<tr style="background-color: #e4e4e4">
							<td>场次</td>
							<td>赛事名称	</td>
							<td>主队</td>
							<td>客队	</td>
							<td> 开赛时间 </td>
							<td> 欧赔平均<br>&nbsp;胜&nbsp;&nbsp;|&nbsp;平&nbsp;&nbsp;|&nbsp;&nbsp;负 </td>
					</tr>
					
					<c:forEach items="${dataList}" var="tr">
						<tr class="DataAutoWidth">
							<td >
								${tr.num}
								<input id="num" name="num" type="hidden" value="${tr.num}">
							</td>
							<td>
								${tr.name}
								<input type="hidden" name="dataName" value="${tr.name}">
							</td>
							<td>
								${tr.HTeam}
								<input type="hidden" name="dataHTeam" value="${tr.HTeam}">
							</td>
							<td>
								${tr.VTeam}
								<input type="hidden" name="dataVTeam" value="${tr.VTeam}">
							</td>
							<td>
								 ${tr.date}
								 <input type="hidden" name="dataDate" value="${tr.date}">  
							</td>
							<td>
								 ${tr.avgOdds}
								 <input type="hidden" name="avgOdds" value="${tr.avgOdds}">  
							</td>
						</tr>
					</c:forEach>
						<tr>
							<td align="left" colspan="100%">
								<input type="button" value="保存到数据库" onclick="saveFlData('save')" <c:if test="${empty dataList}">disabled</c:if>>
							</td>
						</tr>
				</table>
				</div>
		</form>
		<form action="<%=request.getContextPath()%>/events/getFlData" method="post" name="getFlForm">
			<input id="getId" name="id" type="hidden">
			<input id="getFlUrl" name="flUrl" type="hidden">
		</form>
	</body>
</html>