<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.sym"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
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
			System.out.println(result);
			if (!StringUtil.isEmpty(errormsg)) {%>
function showerror() {
	Dialog.alert("<%=errormsg%>");
}
$(document).ready(function() {
	showerror();
});
<%}%>

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
	tbody.append('<td>'+parseInt(1+i)+'</td>');
	tbody.append('<td>'+data[i].userinfo.userName+'</td>');
	tbody.append('<td>'+data[i].caseLotBuy.userno+'</td>');
	tbody.append('<td>'+data[i].caseLotBuy.num+'</td>');
	tbody.append('<td>'+data[i].caseLotBuy.safeAmt+'</td>');
	tbody.append('<td>'+data[i].caseLotBuy.freezeSafeAmt+'</td>');
	tbody.append('<td>'+data[i].caseLotBuy.prizeAmt+'</td>');
	tbody.append('<td>'+data[i].caseLotBuy.commisionPrizeAmt+'</td>');
	var da = new Date(data[i].caseLotBuy.buyTime);
	var daStr = da.format("yyyy-MM-dd hh:mm:ss");
	tbody.append('<td>'+daStr+'</td>');
	tbody.append('</tr>');
});
});
</script>	
</head>
<body>
	<table width="100%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			 <tr>
				<td style="padding: 2px 10px;">
						<div style="float: left;">
							<table width="130%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">用户编号:</td>
									<form name="submitForm" action="<%=request.getContextPath()%>/caselot/selectCaseLotBuysSimplify" method="post">
									<td><input name="userno" type="text" style="width: 120px" value="${param.userno}"></td>
									<td align="center"><input type="submit" value="查询" 	class="inputButton"></td>
										<input name="caselotid" id="caselotid" type="hidden" value="${param.caselotid}">
										<input name="startLine" id="startLine" type="hidden" value="${startLine}">
										<input name="endLine" id="endLine" type="hidden" value="${endLine}">
									</form>
								</tr>
							</table>
						</div>
					
				</td>
			</tr> 
			<tr>
				<td >
					<table width="100%" cellspacing="0" cellpadding="2"
						class="dataTable">
							<tr class="dataTableHead" style="width: 1200;">
								<td width="2%" height="30" class="thOver"></td>
								<td width="6%" height="30" class="thOver"><strong>用户名</strong></td>
								<td width="8%" class="thOver"><strong>用户编号</strong></td>
								<td width="7%" class="thOver"><strong>认购金额</strong></td>
								<td width="7%" class="thOver"><strong>保底金额</strong></td>
								<td width="7%" class="thOver"><strong>冻结金额</strong></td>
								<td width="7%" class="thOver"><strong>中奖金额</strong></td>
								<td width="7%" class="thOver"><strong>佣金</strong></td>
								<td width="13%" class="thOver"><strong>投注时间</strong></td>
							</tr>
							<tbody id="data">
							</tbody>
							<tr>
								<td  align="left" id="dg1_PageBar" colspan="9"><div
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
		</tbody>
	</table>
</body>
</html>