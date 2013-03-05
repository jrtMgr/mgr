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
		<script type="text/javascript" src="js/public.js"></script>
		<script type="text/javascript" src="js/checkbox.js"></script>
		<script type="text/javascript" src="js/window.js"></script>
		<script type="text/javascript" src="js/tableSort.js"></script>
	</head>

	<script language="javascript">
	$(document).ready(function() {
		if(${!empty errormsg}){
			alert("${errormsg}");
		}
		$("#maincontent .list tr:even").css("background-color","#eaf3ff");
		var listSize = '${fn:length(list)}';
		if(listSize==0){
			$("#addAgainst").attr("disabled","disabled");
		}
		if(${fn:split(IssList.type,'|')[0]==0}){
			$("[name='sfc']").attr("disabled",'disabled');
		}
		if(${fn:split(IssList.type,'|')[1]==0}){
			$("[name='game9']").attr("disabled","disabled");
		}
		if(${fn:split(IssList.type,'|')[2]==0}){
			$("[name='game6']").attr("disabled","disabled");
		}
		if(${fn:split(IssList.type,'|')[3]==0}){
			$("[name='game4']").attr("disabled","disabled");
		}
	});
	//编辑
	function editEvents(id,cc){
		location.href = '<%=request.getContextPath()%>/events/editEventsUI?id='+id+'&cc='+cc;
	}
	
	//发布本期对阵
	function release(){
		var num = 0;
		$("[name='sfc']").each(function(){
			if($(this).attr("checked")){
				num++;
			}
		});
		$("[name='game6']").each(function(){
			if($(this).attr("checked")){
	              num++;
			}
		});
		$("[name='game4']").each(function(){
			if($(this).attr("checked")){
				num++;
			}
		});
		if(num == 0){
			alert("对阵必须选择所属玩法");
			return;
		}
		
		if(${IssList.flag eq '1'}){
			alert("本期对阵已发布过");
			return;
		}
        if(!confirm("发布后无法修改对阵，确定发布？")){
	   		return;
	  	}
        var id = $("#id").val();
        location.href = '<%=request.getContextPath()%>/events/release?id='+id;
	}


	
	function savecc(){
		var num1 = 0;
		var str1 = "";
		$("input[name='sfc']").each(function(){
			if($(this).attr("checked")){
	              str1+="1,";
		    }else{
	              str1+="0,";
			}
		});
		var str2 = "";
		var num2 = 0;
		$("[name='game6']").each(function(){
			if($(this).attr("checked")){
	              str2+="1,";
	              num2+=1;
		    }else{
	              str2+="0,";
			}
		});
		if(num2 != 6 && num2!=0){
			alert("六场半全场只能选择6场");
			return;
		}
		
		var str3 = "";
		var num3 = 0;
		$("[name='game4']").each(function(){
			if($(this).attr("checked")){
	              str3+="1,";
	              num3+=1;
		    }else{
	              str3+="0,";
			}
		});
		if(num3 != 4 && num3!=0){
			alert("四场进球彩只能选择4场");
			return;
		}
		var id = $("#id").val();
		
		if(!confirm("确认修改?")){
			return;
		}
		location.href = '<%=request.getContextPath()%>/events/savecc?id='+id+'&str1='+str1+'&str2='+str2+'&str3='+str3;
	}
	</script>
	
	<body>
		<form name="searchFrom" method="post" action="<%=request.getContextPath()%>/events/savecc">
			<input id="id" name="id" type="hidden" value="${param.id}">
			<div align="left" id="maincontent">
				<table style="width: 30%" border="1" class="List" id="tblCondition">
					<tr>
						<td>玩法</td><td>胜负彩</td><td>任9</td><td>六场半全场</td><td>四场进球</td>
					</tr>
					<tr>
						<td>期号	</td>
						<td>
						${IssList.vdlId }
						</td>
						<td>
						${IssList.any9Id }
						</td>
						<td>
						${IssList.games6Id }
						</td>
						<td>
						${IssList.games4Id }
						</td>
					</tr>
					</table>
				<table width="100%" class="List" border="1px;">
				
						<tr style="background-color: #e4e4e4">
							<td>场次</td>
							<td>赛事名称	</td>
							<td>主队</td>
							<td>客队	</td>
							<td>胜负彩&任9</td>
							<td>六场半全场</td>
							<td> 四场进球<br>
							</td>
							<td> 开赛时间 </td>
							<td> 欧赔平均<br>&nbsp;胜&nbsp;&nbsp;|&nbsp;平&nbsp;&nbsp;|&nbsp;&nbsp;负 </td>
							<td> 联赛排名<br>主队|客队 </td>
							<td> 主队近10<br>&nbsp;胜&nbsp;|&nbsp;平&nbsp;|&nbsp;负&nbsp;|进球|失球 </td>
							<td> 客队近10<br>&nbsp;胜&nbsp;|&nbsp;平&nbsp;|&nbsp;负&nbsp;|进球|失球 </td>
							<td> 欧|亚|析|测 </td>
							<td> 半场进球 </td>
							<td> 全场进球 </td>
							<td> 操作 </td>
					</tr>
					<c:forEach items="${list}" var="tr">
						<tr class="DataAutoWidth">
							<td >
								${tr.num}
							</td>
							<td >
								${tr.name}
							</td>	
							<td>
								${tr.HTeam}
							</td>
							<td>
								${tr.VTeam}
							</td>
							<td>
								<input type="checkbox" name="sfc" value="1" 
								<c:if test="${fn:split(tr.flag,'|')[0]==1}">checked='checked'</c:if> 
								<c:if test="${IssList.flag=='1' || fn:split(IssList.type,'|')[0]==0}">disabled</c:if>>
							</td>
							<td>
								 <input type="checkbox" name="game6"  value="1" 
								 <c:if test="${fn:split(tr.flag,'|')[1]==1}">checked='checked'</c:if> 
								 <c:if test="${IssList.flag=='1' || fn:split(IssList.type,'|')[0]==0}">disabled</c:if>/>
							</td>
							<td>
								 <input type="checkbox" name="game4"  value="1" 
								 <c:if test="${fn:split(tr.flag,'|')[2]==1}">checked='checked'</c:if> 
								 <c:if test="${IssList.flag=='1' || fn:split(IssList.type,'|')[0]==0}">disabled</c:if>/>
							</td>
							<td>
								 ${tr.date}  
							</td>
							<td>
								 ${tr.avgOdds}  
							</td>
							<td>
								 ${tr.leagueRank}  
							</td>
							<td>
								 ${tr.HTeam8}  
							</td>
							<td>
								 ${tr.VTeam8}  
							</td>
							<td>
								<font title="${tr.ou}" <c:if test="${empty tr.ou}">color="red"</c:if> >欧</font>
								<font title="${tr.ya}" <c:if test="${empty tr.ya}">color="red"</c:if> >亚</font>
								<font title="${tr.xi}" <c:if test="${empty tr.xi}">color="red"</c:if> >析</font>
								<font title="${tr.ce}" <c:if test="${empty tr.ce}">color="red"</c:if> >测</font>
							</td>
							<td>
								 ${tr.banchangzd}-${tr.banchangkd}
							</td>
							<td>
								 ${tr.bsbfzd}-${tr.bsbfkd}
							</td>
							<td>
								<a href="#" style="text-decoration: none" onClick="editEvents('${tr.pid}','${tr.num}')">编辑</a>|
								<a href="#">删除</a>
							</td>
						</tr>
					</c:forEach>
					
					<c:if test="${empty list}">
						<tr>
							<td colspan="100%" align="center">
								<font color="red">
									期下无对阵
								</font>
							</td>
						</tr>
					</c:if>
				</table>
				</div>
				<div class="functions">
					<div style="float: left;">
						<input type="button" value="保存对阵" onclick="savecc();"/>
						<input type="button" value="发布本期对阵"  onClick="release();" />
						<br>
					</div> 
				</div>
		</form>
	</body>
</html>