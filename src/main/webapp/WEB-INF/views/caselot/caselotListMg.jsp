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

function confidential(state){
	if(state==0){
		return "对所有人立即公开";
	}
	if(state==1){
		return "保密"; 
	}
	if(state==2){
		return "对所有人截止后公开"; 
	}
	if(state==3){
		return "对跟单者立即公开"; 
	}
	if(state==4){
		return "对跟单者截止后公开"; 
	}
}

function parseObj(strData){
    return (new Function( "return " + strData ))();
}
function ctrllist(torderid) {
	var d = new Dialog(torderid+"订单详情", "tuserinfoes/gotlotlist?torderid=" + torderid,
			1200, 210);
	d.show();
}
function ctrllist1(torderid) {
	var d = new Dialog(torderid+"合买详情", "caselot/selectCaseLotBuysSimplify?caselotid=" + torderid,
			1200, 400);
	d.show();
}
function ctrlbetcode(lotno,orderinfo,lotmulti,orderid,bettype) {
	var d;
	var iscaseLot;
	if(lotno.indexOf("J")==0){
		if(bettype==3){
			iscaseLot=true;
		}else{
			iscaseLot=false;
		}
		d = new Dialog("查看详情", "tuserinfoes/parseOrderinfoJingCai?lotno=" + lotno + "&orderId=" + orderid + "&multiple=" + lotmulti + "&isCaseLot=" + iscaseLot,
				800, 210);
	}else{
		d = new Dialog("查看详情", "tuserinfoes/parseOrderinfo?lotno=" + lotno + "&orderinfo=" + orderinfo + "&multiple=" + lotmulti,
				800, 210);
	}
	d.show();
}


function huifu(c,b){
	fea_edit = "width=500,height=300,left=250,top=200,resizable=0,scrollbars=0,dependent=yes"
	var bb = encodeURI(b);
	var url = "<%=request.getContextPath()%>/caselot/huifuUI?c="+c+"&b="+bb;
	window.open(url,"yhgl_edit",fea_edit);
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
		var _lotname = _map.get(data[i].caseLot.lotno);
		var _description = data[i].caseLot.description;
		if(""==_description||null==_description){
			_description = "";
		}
		var userno = data[i].caseLot.starter;
		var nickname = '';
		if(data[i].starter && data[i].starter.nickname){
			nickname = data[i].starter.nickname;
		}
		var url = '<%=request.getContextPath()%>/tuserinfoes/list?userno='+userno;
		var url2 = '<%=request.getContextPath()%>/caselot/selectCaseLotBuysSimplify?caselotid='+data[i].caseLot.id;
		tbody.append('<td>'+parseInt(${startLine }+1+i)+'</td><td><a href="javascript:ctrllist1(\''+data[i].caseLot.id+'\')">'+data[i].caseLot.id+'</a></td><td title='+data[i].torder.id+' ><a href="javascript:ctrllist(\''+data[i].torder.id+'\')">'+data[i].torder.id+'</a></td><td>' +_lotname+'</td><td>'+data[i].caseLot.batchcode+'</td>');
		tbody.append('<td title="'+data[i].torder.orderinfo+'"><a href="javascript:ctrlbetcode(\''+data[i].torder.lotno+'\',\''+data[i].torder.orderinfo+'\',\''+data[i].torder.lotmulti+'\',\''+data[i].torder.id+'\',\''+data[i].torder.bettype+'\')">'+data[i].torder.orderinfo+'</td>');
		tbody.append('<td><a  href='+url+'>'+userno+'</a></td>');
		tbody.append('<td><a  href='+url+'>'+nickname+'</a></td>');
		var c = "'"+data[i].caseLot.id+"'";
		var b = "'"+_description+"'";
		var a = '<a href="javascript:huifu('+c+','+b+')">' + _description + '</a>';
		tbody.append('<td title="'+_description+'">'+a+'</td>');
		tbody.append('<td>'+data[i].achievement.effectiveAchievement+"|"+data[i].achievement.ineffectiveAchievement+'</td>');
		tbody.append('<td>'+parseFloat(parseInt(data[i].caseLot.totalAmt)/100)+'</td>');
		tbody.append('<td>'+parseFloat((parseInt(data[i].caseLot.buyAmtByStarter)+parseInt(data[i].caseLot.buyAmtByFollower))/100)+'</td>');
		tbody.append('<td>'+Math.round(parseFloat((parseInt(data[i].caseLot.buyAmtByStarter)+parseInt(data[i].caseLot.buyAmtByFollower))/parseInt(data[i].caseLot.totalAmt))*100)+'%</td>');
		tbody.append('<td>'+parseFloat(parseInt(data[i].caseLot.safeAmt)/100)+'</td>');
		var da = new Date(data[i].caseLot.startTime);
		var daStr = da.format("yyyy-MM-dd hh:mm:ss");
		tbody.append('<td>'+daStr+'</td>');
		tbody.append('<td>'+data[i].caseLot.participantCount+'</td>');
		tbody.append('<td>'+data[i].caseLot.displayStateMemo+'</td>');
		var operate = "";
		/*合买状态 1, "认购中",2, "满员", 3, "成功", 4, "撤单",5, "流单", 6, "已中奖" */
		var displayState = data[i].caseLot.displayState;
		/*置顶状态 0:普通 9:置顶状态 */
		var sortState = data[i].caseLot.sortState;
		/*认购比例  */
		var percent = Math.round(parseFloat((parseInt(data[i].caseLot.buyAmtByStarter)+parseInt(data[i].caseLot.buyAmtByFollower))/parseInt(data[i].caseLot.totalAmt))*100);
		tbody.append('<td>' + confidential(data[i].caseLot.visibility) + '</td>');
		tbody.append('<td>' + parseFloat(parseInt(data[i].caseLot.winPreAmt)/100) + '</td>');
		if(displayState==1){
			if(sortState==0){
				if(percent<50){
					if(data[i].caseLot.id==4){
						operate = '<a href="javascript:process(\''+data[i].caseLot.id+'\',\'9\')">合买置顶</a>&nbsp;&nbsp;&nbsp;<a href = "javascript:cancelOrder(\''+data[i].caseLot.starter+"\',\'"+ data[i].caseLot.id +'\')">撤单</a>';
					}
					if(data[i].caseLot.id!=4){
						operate = '<a href="javascript:process(\''+data[i].caseLot.id+'\',\'9\')">合买置顶</a>&nbsp;&nbsp;&nbsp;<a href = "javascript:cancelOrder(\''+data[i].caseLot.starter+"\',\'"+ data[i].caseLot.id +'\')">撤单</a>&nbsp;&nbsp;&nbsp;<a href = "javascript:forcedToCancel(\''+data[i].caseLot.id+'\')">强制撤单</a>';
					}
				} else {
					if(data[i].caseLot.id==4){
						operate = '<a href="javascript:process(\''+data[i].caseLot.id+'\',\'9\')">合买置顶</a>';
					}
					if(data[i].caseLot.id!=4){
						operate = '<a href="javascript:process(\''+data[i].caseLot.id+'\',\'9\')">合买置顶</a>&nbsp;&nbsp;&nbsp;<a href = "javascript:forcedToCancel(\''+data[i].caseLot.id+'\')">强制撤单</a>';
					}
				}
			}
			if(sortState==9){
				if(percent<50){
					if(data[i].caseLot.id==4){
						operate = '<a href="javascript:process(\''+data[i].caseLot.id+'\',\'0\')">取消置顶</a>&nbsp;&nbsp;&nbsp;<a href = "javascript:cancelOrder(\''+data[i].caseLot.starter+"," + data[i].caseLot.id +')">撤单</a>';
					}
					if(data[i].caseLot.id!=4){
						operate = '<a href="javascript:process(\''+data[i].caseLot.id+'\',\'0\')">取消置顶</a>&nbsp;&nbsp;&nbsp;<a href = "javascript:cancelOrder(\''+data[i].caseLot.starter+"," + data[i].caseLot.id +')">撤单</a>&nbsp;&nbsp;&nbsp;<a href = "javascript:forcedToCancel(\''+data[i].caseLot.id+'\')">强制撤单</a>';
					}
				}else{
					if(data[i].caseLot.id==4){
						operate = '<a href="javascript:process(\''+data[i].caseLot.id+'\',\'0\')">取消置顶</a>';
					}
					if(data[i].caseLot.id!=4){
						operate = '<a href="javascript:process(\''+data[i].caseLot.id+'\',\'0\')">取消置顶</a>&nbsp;&nbsp;&nbsp;<a href = "javascript:forcedToCancel(\''+data[i].caseLot.id+'\')">强制撤单</a>';
					}
				}
			}
		}
		if(displayState!=1&&sortState==9){
			if(data[i].caseLot.id==4){
				operate = '<a href="javascript:process(\''+data[i].caseLot.id+'\',\'0\')">取消置顶</a>';
			}
			if(data[i].caseLot.id!=4){
				operate = '<a href="javascript:process(\''+data[i].caseLot.id+'\',\'0\')">取消置顶</a>&nbsp;&nbsp;&nbsp;<a href = "javascript:forcedToCancel(\''+data[i].caseLot.id+'\')">强制撤单</a>';
			}
		}
		tbody.append('<td>' + operate + '</td>');
/* 		if(data[i].caseLot. == 0){
			tbody.append('<td><a href="javascript:process(\''+data[i].caseLot.id+'\',\'9\')">合买置顶</a></td>');
		}else if(data[i].caseLot.sortState == 9){
			tbody.append('<td><a href="javascript:process(\''+data[i].caseLot.id+'\',\'0\')">取消置顶</a></td>');
		}  */
		tbody.append('</tr>')
	});
});

function process(id,sortState){
	var tip;
	if(sortState == 0){
		tip = "确认<font color='red'>取消置顶</font>？";
	}else if(sortState == 9){
		tip = "确认<font color='red'>置顶</font>？";
	}else{
		Dialog.alert("操作异常");
		return false;
	}
	Dialog.confirm(tip,function(){
		$.ajax({
			url:"<%=request.getContextPath() %>/caselot/updateCaselotBySortState",
			type:"POST",
			data:{"caselotid":id,"sortState":sortState},
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

function forcedToCancel(caseLotId){
	var tip = "确认<font color='red'>强制撤单</font>？";
	Dialog.confirm(tip,function(){
		$.ajax({
			url:"<%=request.getContextPath() %>/caselot/forcedToCancelCaselot",
			type:"POST",
			data:{"caseId":caseLotId},
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

function cancelOrder(userno,caselotid){
	var tip = "确认<font color='red'>撤单</font>？";
	Dialog.confirm(tip,function(){
		$.ajax({
			url:"<%=request.getContextPath() %>/caselot/cancelOrder",
			type:"POST",
			data:{"userno":userno,"caselotid":caselotid},
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

function sortSelect(){
	var _orderBy = $("#orderBy");
	var _orderDir = $("#orderDir")
	var nodeValue = $("#mysort option:selected").val();
	if(nodeValue==""){
		_orderBy.val("");
		_orderDir.val("");
	}else if (nodeValue == "1"){
		_orderBy.val("(buyAmtByStarter+buyAmtByFollower)/totalAmt");
		_orderDir.val("ASC")
	}else if (nodeValue == "2"){
		_orderBy.val("(buyAmtByStarter+buyAmtByFollower)/totalAmt");
		_orderDir.val("DESC")
	}else if (nodeValue == "3"){
		_orderBy.val("totalAmt");
		_orderDir.val("ASC")
	}else if (nodeValue == "4"){
		_orderBy.val("totalAmt");
		_orderDir.val("DESC")
	}else if (nodeValue == "5"){
		_orderBy.val("participantCount");
		_orderDir.val("ASC")
	}else if (nodeValue == "6"){
		_orderBy.val("participantCount");
		_orderDir.val("DESC")
	}else if (nodeValue == "7"){
		_orderBy.val("startTime");
		_orderDir.val("ASC")
	}else if (nodeValue == "8"){
		_orderBy.val("startTime");
		_orderDir.val("DESC")
	}else if (nodeValue == "9"){
		_orderBy.val("winPreAmt");
		_orderDir.val("ASC")
	}else if (nodeValue == "0"){
		_orderBy.val("winPreAmt");
		_orderDir.val("DESC")
	}
}
</script>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="110%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form name="submitForm" action="<%=request.getContextPath()%>/caselot/caselotListMg"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">合买编号或昵称:</td>
									<td><input name="search" type="text" style="width: 120px" value="${search }"></td>
									<td align="right">彩种:</td>
									<td><select name="lotno" style="width: 120px">
											<option value="">全部</option>
											<c:forEach items="${lotTypes}" var="types">
												<option value="${types.key }" <c:if test='${types.key eq lotno}'>selected</c:if>>${types.value }</option>
											</c:forEach>
									</select></td>
									<td align="right">期号:</td>
									<td><input name="batchcode" type="text" style="width: 120px" value="${param.batchcode }"></td>
									<td align="right">合买状态:</td>
									<td><select name="displayState" style="width: 80px">
											<option value="" >全部</option>
											<option value="1" <c:if test='${"1" eq param.displayState}'>selected</c:if>>认购中</option>
											<option value="2" <c:if test='${"2" eq param.displayState}'>selected</c:if>>满员</option>
											<option value="3" <c:if test='${"3" eq param.displayState}'>selected</c:if>>成功</option>
											<option value="4" <c:if test='${"4" eq param.displayState}'>selected</c:if>>撤单</option>
											<option value="5" <c:if test='${"5" eq param.displayState}'>selected</c:if>>流单</option>
											<option value="6" <c:if test='${"6" eq param.displayState}'>selected</c:if>>已中奖</option>
									</select></td>
									<td align="right">置顶类型:</td>
									<td><select name="sortStateFlag" style="width: 80px">
											<option value="0" <c:if test='${"0" eq param.sortStateFlag}'>selected</c:if>>全部合买</option>
											<option value="1" <c:if test='${"1" eq param.sortStateFlag}'>selected</c:if>>置顶合买</option>
									</select></td>
									<td align="right">排序查询:</td>
									<td><select name="mysort" style="width: 130px" onchange="sortSelect()" id="mysort">
											<option value = "" >全部</option>
											<option value="1" <c:if test='${"1" eq param.mysort}'>selected</c:if>>认购百分比从低到高</option>
											<option value="2" <c:if test='${"2" eq param.mysort}'>selected</c:if>>认购百分比从高到低</option>
											<option value="3" <c:if test='${"3" eq param.mysort}'>selected</c:if>>方案金额从低到高</option>
											<option value="4" <c:if test='${"4" eq param.mysort}'>selected</c:if>>方案金额从高到低</option>
											<option value="5" <c:if test='${"5" eq param.mysort}'>selected</c:if>>参与人数从低到高</option>
											<option value="6" <c:if test='${"6" eq param.mysort}'>selected</c:if>>参与人数从高到低</option>
											<option value="7" <c:if test='${"7" eq param.mysort}'>selected</c:if>>创建时间从低到高</option>
											<option value="8" <c:if test='${"8" eq param.mysort}'>selected</c:if>>创建时间从高到低</option>
											<option value="9" <c:if test='${"9" eq param.mysort}'>selected</c:if>>税前奖金从低到高</option>
											<option value="0" <c:if test='${"0" eq param.mysort}'>selected</c:if>>税前奖金从高到低</option>
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
									<td align="right">方案金额:从</td>
									<td><input name="totalAmtStart" type="text" style="width: 120px" value="${param.totalAmtStart }"></td>
									<td align="right">到:</td>
									<td><input name="totalAmtEnd" type="text" style="width: 120px" value="${param.totalAmtEnd }"></td>
									<td align="right">方案描述:</td>
									<td><input name="description" type="text" style="width: 120px" value="${param.description }"></td>
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
					<table width="110%" cellspacing="0" cellpadding="2"
						class="dataTable" style="overflow: auto;width: 1800px;" >
							<tr class="dataTableHead">
								<td width="2%" height="30" class="thOver"></td>
								<td width="7%" class="thOver" title="合买编号"><strong>合买编号</strong></td>
								<td width="8%" class="thOver" title="订单编号"><strong>订单编号</strong></td>
								<td width="6%" class="thOver" title="彩种"><strong>彩种</strong></td>
								<td width="4%" class="thOver" title="期号"><strong>期号</strong></td>
								<td width="6%" class="thOver" title="发起人"><strong>注码</strong></td>
								<td width="4%" class="thOver" title="发起人"><strong>发起人</strong></td>
								<td width="4%" class="thOver" title="用户昵称"><strong>用户昵称</strong></td>
								<td width="4%" class="thOver" title="方案描述"><strong>方案描述</strong></td>
								<td width="4%" class="thOver" title="战绩"><strong>战绩</strong></td>
								<td width="4%" class="thOver" title="方案金额"><strong>方案金额</strong></td>
								<td width="4%" class="thOver" title="认购金额"><strong>认购金额</strong></td>
								<td width="4%" class="thOver" title="认购百分比"><strong>认购百分比</strong></td>
								<td width="4%" class="thOver" title="保底金额"><strong>保底金额</strong></td>
								<td width="8%" class="thOver" title="创建时间"><strong>创建时间</strong></td>
								<td width="4%" class="thOver" title="参与人数"><strong>参与人数</strong></td>
								<td width="5%" class="thOver" title="合买状态"><strong>合买状态</strong></td>
								<td width="7%" class="thOver" title="保密状态"><strong>保密状态</strong></td>
								<td width="5%" class="thOver" title="税前奖金"><strong>税前奖金</strong></td>
								<td width="13%" class="thOver" title="操作"><strong>操作</strong></td>
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