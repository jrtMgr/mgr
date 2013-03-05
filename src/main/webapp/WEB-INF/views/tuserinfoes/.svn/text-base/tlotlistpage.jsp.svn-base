<%@page import="com.ruyicai.mgr.lot.LotCenter"%>
<%@page import="com.ruyicai.mgr.consts.LotState"%>
<%@page import="com.ruyicai.mgr.domain.Tlot"%>
<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.domain.Twininfo"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.lot.LotCenter"%>
<%@page import="java.util.Map" %>
<%@page import="java.util.Map.Entry" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Dialog.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/map.js"></script>
<script type="text/javascript">	
<%String errormsg = (String) request.getAttribute("errormsg");
			String result = (String) request.getAttribute("result");
			if (!StringUtil.isEmpty(errormsg)) {%>
function showerror() {
	Dialog.alert("<%=errormsg%>");
}
$(document).ready(function() {
	showerror();
});
<%}%>
function configState(state){
	if(state=="-1"){
		return "未处理";
	}
	if(state=="0"){
		return "失败";
	}
	if(state=="1"){
		return "成功";
	}
	if(state=="2"){
		return "处理中";
	} else {
		return state;
	}
}
function configFlag(flag){
	if(flag=="0"){
		return "已开奖";
	}
	if(flag=="1"){
		return "已兑奖";
	}
	if(flag=="2"){
		return "未开奖";
	}
	if(flag=="3"){
		return "未中奖";
	}
	if(flag=="9"){
		return "中大奖";
	}
}
function configFlag2(flag){
	if(flag=="0"){
		return "未派奖";
	}
	if(flag=="1"){
		return "已派奖";
	}
}
function configBetType(type){
	if(type=="0"){
		return "追号";
	}
	if(type=="1"){
		return "套餐";
	}
	if(type=="2"){
		return "投注";
	}
	if(type=="3"){
		return "合买";
	}
	if(type=="4"){
		return "赠送";
	}
	if(type=="5"){
		return "无短信赠送";
	}
}

function configSellWay(way){
	if(way=="0"){
		return "自选";
	}else if(way=="1"){
		return "手选";
	}else if(way=="2"){
		return "机选";
	}else{
		return "";
	}
}
function configLotCenter(agencyno){
	if(agencyno=="000002"){
		return "内蒙福彩";
	}
	if(agencyno=="tc0001"){
		return "大赢家";
	}
	if(agencyno=="R00001"){
		return "金软瑞彩";
	}
	if(agencyno=="000004"){
		return "山东体彩";
	}
	if(agencyno=="000006"){
		return "华彩";
	}
	if(agencyno=="tx0001"){
		return "腾讯";
	}
	if(agencyno=="000007"){
		return "竞彩";
	}
	if(agencyno=="fcby"){
		return "风采博雅";
	}
	if(agencyno=="000008"){
		return "掌中彩";
	}
	if(agencyno=="000009"){
		return "掌中弈";
	}
	if(agencyno=="sdfcby"){
		return "山东丰彩博雅";
	}
	if(agencyno=="zlren"){
		return "直立人";
	}
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
function parseObj(strData){
    return (new Function( "return " + strData ))();
}
$(document).ready(function() {
	var resultMessage = parseObj('<%=result%>');
	var data = resultMessage.value.list;
	var tbody = $("#data");
	// 将彩种放入map中
	var _map = new Map();
	<%Map<String, String> lotTypes = (Map) request
					.getAttribute("lotTypes");
			for (Entry lotType : lotTypes.entrySet()) {%>
			_map.put('<%=lotType.getKey()%>','<%=lotType.getValue()%>');
	<%}%>
$.each(data,function(i,n){
	tbody.append('<tr>');
	var _lotname = _map.get(data[i].lotno);
	tbody.append('<td>'+parseInt(1+i)+'</td><td>'+data[i].flowno+'</td>');
	tbody.append('<td>'+data[i].userno+'</td>');
	tbody.append('<td>'+configLotCenter(data[i].agencyno)+'</td>');
	tbody.append('<td>'+data[i].channel+'</td>');
	tbody.append('<td>'+_lotname+'</td>');
	tbody.append('<td>'+data[i].batchcode+'</td>');
	tbody.append('<td title="'+data[i].betcode+'">'+data[i].betcode+'</td>');
	tbody.append('<td>'+data[i].betnum+'</td>');
	tbody.append('<td>'+data[i].lotmulti+'</td>');
	tbody.append('<td>'+configSellWay(data[i].sellway)+'</td>');
	tbody.append('<td>'+parseFloat(parseInt(data[i].amt/100))+'</td>');
	tbody.append('<td>'+parseFloat(parseInt(data[i].prizeamt/100))+'</td>');
	var da = new Date(data[i].ordertime);
	var daStr = da.format("yyyy-MM-dd hh:mm:ss");
	tbody.append('<td>'+daStr+'</td>');
	tbody.append('<td>'+configBetType(data[i].bettype)+'</td>');
	tbody.append('<td>'+configFlag(data[i].settleflag)+'</td>');
	tbody.append('<td>'+configFlag2(data[i].transferstate)+'</td>');
	tbody.append('<td>'+configState(data[i].state)+'</td>');
	tbody.append('</tr>');
});
});
</script>	
</head>
<body>
	<table width="100%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead" style="width: 1200;">
				<td width="2%" height="30" class="thOver"></td>
				<td width="10%" height="30" class="thOver"><strong>流水号</strong>
				</td>
				<td width="6%" height="30" class="thOver"><strong>用户编号</strong>
				</td>
				<td width="5%" height="30" class="thOver"><strong>彩票中心</strong>
				</td>
				<td width="4%" height="30" class="thOver"><strong>渠道</strong>
				</td>
				<td width="5%" height="30" class="thOver"><strong>彩种</strong>
				</td>
				<td width="5%" height="30" class="thOver"><strong>期号</strong>
				</td>
				<td width="9%" height="30" class="thOver"><strong>注码</strong>
				</td>
				<td width="4%" height="30" class="thOver"><strong>注数</strong>
				<td width="4%" height="30" class="thOver"><strong>倍数</strong>
				</td>
				<td width="4%" height="30" class="thOver"><strong>销售方式</strong>
				</td>
				<td width="5%" height="30" class="thOver"><strong>金额</strong>
				</td>
				<td width="5%" height="30" class="thOver"><strong>中奖金额</strong>
				</td>
				<td width="11%" height="30" class="thOver"><strong>投注时间</strong>
				</td>
				<td width="4%" height="30" class="thOver"><strong>类型</strong>
				</td>
				<td width="4%" height="30" class="thOver"><strong>标识</strong>
				</td>
				<td width="4%" height="30" class="thOver" title="派奖标识"><strong>派奖标识</strong>
				</td>
				<td width="4%" height="30" class="thOver"><strong>状态</strong>
				</td>
			</tr>
			<tbody id="data">
			</tbody>
		</tbody>
	</table>
</body>
</html>