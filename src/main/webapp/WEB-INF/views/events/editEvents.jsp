<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<html>
	<head>
		<title>编辑场次</title>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<link href="<%=request.getContextPath()%>/styles/default.css" rel="stylesheet" type="text/css">
	</head>

	<script language="javascript">
	$(document).ready(function() {
		if(${!empty errormsg}){
			alert("${errormsg}");
		}
	});
	
	function submit_(){
			var strFloat = /^[0-9]{1,2}\.[0-9]{2,3}$/;
			var str = /^\d*$/;
			var state = true;
			var string ;
			if($.trim($('#ssmc').val())==''){
				alert('赛事名称不能为空');
				$('#ssmc').focus();
				return;
			}
			if($.trim($('#zd').val())==''){
				alert('主队名称不能为空');
				$('#zd').focus();
				return;
			}
			if($.trim($('#kd').val())==''){
				alert('客队名称不能为空');
				$('#kd').focus();
				return;
			}
			if($.trim($('#ksrq').val())==''){
				alert('开赛日期不能为空');
				$('#ksrq').focus();
				return;
			}
			$("[name='kssj']").each(function(){
				if(!/^[0-1][0-9]\:[0-5][0-9]$/.test(this.value) && !/^2[0-3]\:[0-5][0-9]$/.test(this.value) && this.value != ''){
					string = "开赛时间格式不正确";
					state = false;
				}
			});
			if(state){
				$("[name='ops']").each(function(){
					if(!strFloat.test(this.value) && this.value != ''){
						string = "欧赔平均胜率必须输入（00.00）格式的数字";
						state = false;
					}
				});
			}
			if(state){ 
				$("[name='opp']").each(function(){
					if(!strFloat.test(this.value) && this.value != ''){
						string = "欧赔平均平率必须输入（00.00）格式的数字";
						state = false;
					}
				});
			}
			if(state){
				$("[name='opf']").each(function(){
					if(!strFloat.test(this.value) && this.value != ''){
						string = "欧赔平均负率必须输入（00.00）格式的数字";
						state = false;
					}
				});
			}
			if(state){
				$("[name='lspmzd']").each(function(){
					if(!str.test(this.value)){
						string = "主队联赛排名必须输入数字";
						state = false;
					}
				});
			}
			if(state){
				$("[name='lspmkd']").each(function(){
					if(!str.test(this.value)){
						string = "客队联赛排名必须输入数字";
						state = false;
					}
				});
			}
			if(state){
				$("[name='z8s']").each(function(){
					if(!str.test(this.value)){
						string = "主队近10场胜场数必须输入数字";
						state = false;
					}
				});
			}
			if(state){
				$("[name='z8p']").each(function(){
					if(!str.test(this.value)){
						string = "主队近10场平场数必须输入数字";
						state = false;
					}
				});
			}
			if(state){
				$("[name='z8f']").each(function(){
					if(!str.test(this.value)){
						string = "主队近10场负场数必须输入数字";
						state = false;
					}
				});
			}
			if(state){
				$("[name='z8jq']").each(function(){
					if(!str.test(this.value)){
						string = "主队近10场进球数必须输入数字";
						state = false;
					}
				});
			}
			if(state){
				$("[name='z8sq']").each(function(){
					if(!str.test(this.value)){
						string = "主队近10场失球数必须输入数字";
						state = false;
					}
				});
			}
			if(state){
				$("[name='k8s']").each(function(){
					if(!str.test(this.value)){
						string = "客队近10场胜场数必须输入数字";
						state = false;
					}
				});
			}
			if(state){
				$("[name='k8p']").each(function(){
					if(!str.test(this.value)){
						string = "客队近10场平场数必须输入数字";
						state = false;
					}
				});
			}
			if(state){
				$("[name='k8f']").each(function(){
					if(!str.test(this.value)){
						string = "客队近10场负场数必须输入数字";
						state = false;
					}
				});
			}
			if(state){
				$("[name='k8jq']").each(function(){
					if(!str.test(this.value)){
						string = "客队近10场进球数必须输入数字";
						state = false;
					}
				});
			}
			if(state){
				$("[name='k8sq']").each(function(){
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
			document.addFrom.submit();
	}

</script>


	<body>
		<form name="addFrom" method="post" action="<%=request.getContextPath()%>/events/editEvents">
			<input id="id" name="id" type="hidden" value="${param.id }">
			<input id="cc" name="cc" type="hidden" value="${param.cc }">
			<table style="width: 60%" border="1">
				<tr>
					<td colspan="2" align="center">
						编辑场次
					</td>
				</tr>
				<tr>
					<td  align="right" >
						场次:
					</td>
					<td>
						${param.cc }
					</td>
				</tr>
				<tr >
					<td  align="right">
						赛事名称:
					</td>
					<td>
						<input  type="text" name="ssmc" id="ssmc" size="40" class="sinput" value="${tr.name }"/>*
					</td>
				</tr>
				<tr >
					<td align="right">
						主队:
					</td>
					<td>
						<input  type="text" name="zd" id="zd" size="20" class="sinput" value="${tr.hteam }"/>*
					</td>
				</tr>
				<tr >
					<td align="right">
						客队:
					</td>
					<td>
						<input  type="text" name="kd" id="kd" size="20" class="sinput" value="${tr.vteam }"/>*
					</td>
				</tr>
				<tr >
					<td align="right">
						开赛时间:
					</td>
					<td>
						日期：<input name="ksrq" id="ksrq" type="text" size="12" class="wdate" maxlength="16" onfocus="wdatepicker()" value="${fn:substring(tr.date, 0, 10)}"/>*
						&nbsp;时间：<input  type="text" name="kssj" id="kssj" size="5" class="sinput" value="${fn:substring(tr.date, 11, 16)}"/>
					</td>
				</tr>
				<tr >
					<td align="right">
						欧赔平均:
					</td>
					<td>
						胜：<input  type="text" name="ops" id="ops" size="4" class="sinput" value="${fn:split(tr.avgodds,'|')[0]}"/>
						平：<input  type="text" name="opp" id="opp" size="4" class="sinput" value="${fn:split(tr.avgodds,'|')[1]}"/>
						负：<input  type="text" name="opf" id="opf" size="4" class="sinput" value="${fn:split(tr.avgodds,'|')[2]}"/>
					</td>
				</tr>
				<tr >
					<td align="right">
						联赛排名:
					</td>
					<td>
						主队：<input  type="text" name="lspmzd" id="lspmzd" size="2" class="sinput" value="${fn:substring(fn:split(tr.leaguerank,'|')[0],2,fn:length(fn:split(tr.leaguerank,'|')[0]))}"/>
						客队：<input  type="text" name="lspmkd" id="lspmkd" size="2" class="sinput" value="${fn:substring(fn:split(tr.leaguerank,'|')[1],2,fn:length(fn:split(tr.leaguerank,'|')[1]))}"/>
					</td>
				</tr>
				
				<tr >
					<td align="right">
						全场比赛比分:
					</td>
					<td>
						主队：<input  type="text" name="bsbfzd" id="bsbfzd" size="2" class="sinput" value="${tr.bsbfzd }"/>
						客队：<input  type="text" name="bsbfkd" id="bsbfkd" size="2" class="sinput" value="${tr.bsbfkd}"/>
					</td>
				</tr>
				
				<tr >
					<td align="right">
						半场比赛比分:
					</td>
					<td>
						主队：<input  type="text" name="banchangzd" id="banchangzd" size="2" class="sinput" value="${tr.banchangzd }"/>
						客队：<input  type="text" name="banchangkd" id="banchangkd" size="2" class="sinput" value="${tr.banchangkd}"/>
					</td>
				</tr>
				
				
				<tr >
					<td align="right">
						主队近10:
					</td>
					<td>
						胜：<input  type="text" name="z8s" id="z8s" size="2" class="sinput" value="${fn:substring(fn:split(tr.hteam8,'|')[0],0,fn:length(fn:split(tr.hteam8,'|')[0])-1)}"/>
						平：<input  type="text" name="z8p" id="z8p" size="2" class="sinput" value="${fn:substring(fn:split(tr.hteam8,'|')[1],0,fn:length(fn:split(tr.hteam8,'|')[1])-1)}"/>
						负：<input  type="text" name="z8f" id="z8f" size="2" class="sinput" value="${fn:substring(fn:split(tr.hteam8,'|')[2],0,fn:length(fn:split(tr.hteam8,'|')[2])-1)}"/>
						进球：<input  type="text" name="z8jq" id="z8jq" size="2" class="sinput" value="${fn:substring(fn:split(tr.hteam8,'|')[3],1,fn:length(fn:split(tr.hteam8,'|')[3])-1)}"/>
						失球：<input  type="text" name="z8sq" id="z8sq" size="2" class="sinput" value="${fn:substring(fn:split(tr.hteam8,'|')[4],1,fn:length(fn:split(tr.hteam8,'|')[4])-1)}"/>
					</td>
				</tr>
				<tr >
					<td align="right">
						客队近10:
					</td>
					<td >
						胜：<input  type="text" name="k8s" id="k8s" size="2" class="sinput" value="${fn:substring(fn:split(tr.vteam8,'|')[0],0,fn:length(fn:split(tr.vteam8,'|')[0])-1)}"/>
						平：<input  type="text" name="k8p" id="k8p" size="2" class="sinput" value="${fn:substring(fn:split(tr.vteam8,'|')[1],0,fn:length(fn:split(tr.vteam8,'|')[1])-1)}"/>
						负：<input  type="text" name="k8f" id="k8f" size="2" class="sinput" value="${fn:substring(fn:split(tr.vteam8,'|')[2],0,fn:length(fn:split(tr.vteam8,'|')[2])-1)}"/>
						进球：<input  type="text" name="k8jq" id="k8jq" size="2" class="sinput" value="${fn:substring(fn:split(tr.vteam8,'|')[3],1,fn:length(fn:split(tr.vteam8,'|')[3])-1)}"/>
						失球：<input  type="text" name="k8sq" id="k8sq" size="2" class="sinput" value="${fn:substring(fn:split(tr.vteam8,'|')[4],1,fn:length(fn:split(tr.vteam8,'|')[4])-1)}"/>
					</td>
				</tr>
				<tr >
					<td  align="right">
						欧：
					</td>
					<td><input  type="text" name="ou" id="ou" size="60" class="sinput" value="${tr.ou}" /></td>
				</tr>
				<tr>
					<td  align="right">亚：</td>
					<td><input  type="text" name="ya" id="ya" size="60" class="sinput" value="${tr.ya}" /></td>
				</tr>
				<tr>
					<td  align="right">析：</td>
					<td><input  type="text" name="xi" id="xi" size="60" class="sinput"  value="${tr.xi}"/></td>
				</tr>
				<tr>
					<td  align="right">测：</td>
					<td><input  type="text" name="ce" id="ce" size="60" class="sinput"  value="${tr.ce}"/></td>
				</tr>
				<tr >
					<td colspan="2" align="center">
						<input type=button id="btnok" style="width: 40" class="button" value="确定" onclick="submit_()">
						<input name="button" type=button class="button" style="width: 40"
						id="btncancel" onclick="javascript:location.href='<%=request.getContextPath()%>/events/addAgainst?id=${param.id}'"  value="返回">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>

