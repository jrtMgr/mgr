<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
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
</head>
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
function approvalMore(){
	if( checkIDs()&&confirm('所选项将通过审核')){
		document.forms[1].submit();   
	}
}
function checkIDs(){
	var checkValues = document.getElementsByName("checkboxname");
	var temp="";
	for(var i=0;i<checkValues.length;i++){
	   if(checkValues[i].checked){
	      temp+=checkValues[i].value;
	   }
	}
	if(temp.length==0){
	   alert("请选择!"); 
	   return false; 
	}else{
	   return true;
	}
}
function huifu(id){
	fea_edit = "width=350,height=550,left=250,top=100,resizable=0,scrollbars=0,dependent=yes";
	var url = "<%=request.getContextPath()%>/tjingcairesult/editUI?id="+id;
	window.open(url,"yhgl_edit",fea_edit);
}

function deletec(id){
	if(!window.confirm("确定删除吗?")) {
		return;
	}
	var url = "<%=request.getContextPath()%>/tjingcairesult/deleteResult?id="+id;
	window.location.href = url;
}
</script>
<body >
	<div style="margin-top: 10px; margin-left: 20px"><a href="<%=request.getContextPath()%>/tjingcairesult/getfootballresult">抓取足球赛果</a>|
	 <a href="<%=request.getContextPath()%>/tjingcairesult/getbasketballresult">抓取蓝球赛果</a></div>
	
	
	<hr>
	<table width="135%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form action="<%=request.getContextPath()%>/tjingcairesult/list" method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">日期:</td>
									<td><input name="day" type="text" style="width: 110px" value="${param.day}"
										class="inputText" onfocus="this.select();" /></td>
									<td align="right">类型:</td>
									<td><select name="type" style="width:100px">
											<option value="">--请选择--</option>
											<option value="0" <c:if test='${0 == param.type}'>selected</c:if>>篮球</option>
											<option value="1" <c:if test='${1 == param.type}'>selected</c:if>>足球</option>
									</select></td>	
										<td align="right">审核:</td>
									<td><select name="audit" style="width:100px">
											<option value="">--请选择--</option>
											<option value="0" <c:if test='${0 == param.audit}'>selected</c:if>>已审核</option>
											<option value="1" <c:if test='${1 == param.audit}'>selected</c:if>>未审核</option>
									</select></td>
									<td align="right">显示行数:</td>
									<td><select id="maxResult" name="maxResult" style="width:60px">
											<option value="15" <c:if test='${"15" eq page.maxResult}'>selected</c:if>>15</option>
											<option value="30" <c:if test='${"30" eq page.maxResult}'>selected</c:if>>30</option>
											<option value="50" <c:if test='${"50" eq page.maxResult}'>selected</c:if>>50</option>
											<option value="100" <c:if test='${"100" eq page.maxResult}'>selected</c:if>>100</option>
									</select></td>
									<td></td>
									<td></td>
									<td align="center"><input type="submit" value="查询" class="inputButton">
									</td>
								</tr>
							</table>
						</div>
					</form>
				</td>
			</tr>
			<tr>
				<td	style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="135%" cellspacing="0" cellpadding="2"
						class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="2%" height="30" class="thOver"></td>
								<td width="5%" class="thOver"><strong>审核</strong></td>
								<td width="8%" class="thOver"><strong>主队客队</strong></td>
								<td width="3%" class="thOver"><strong>类型</strong></td>
								<td width="4%" class="thOver"><strong>日期</strong></td>
								<td width="3%" class="thOver"><strong>周数</strong></td>
								<td width="3%" class="thOver"><strong>场次	</strong></td>
								<td width="3%" class="thOver"><strong>状态</strong></td>
								<td width="7%" class="thOver"><strong>联赛</strong></td>
								<td width="4%" class="thOver"><strong>比赛取消</strong></td>
								<td width="3%" class="thOver"><strong>让分</strong></td>
								<td width="4%" class="thOver"><strong>预设总分</strong></td>
								<td width="4%" class="thOver"><strong>比分(主：客)</strong></td>
								<td width="4%" class="thOver"><strong>上半场比分 </strong></td>
								<td width="8%" class="thOver"><strong>篮球胜负/足彩胜负平彩金 </strong></td>
								<td width="6%" class="thOver"><strong>让分胜负奖金</strong></td>
								<td width="4%" class="thOver"><strong>胜分差奖金</strong></td>
								<td width="4%" class="thOver"><strong>大小分奖金</strong></td>
								<td width="5%" class="thOver"><strong>足球比分奖金</strong></td>
								<td width="5%" class="thOver"><strong>总进球奖金</strong></td>
								<td width="6%" class="thOver"><strong>半全场胜负奖金</strong></td>
								<td width="3%" class="thOver"><strong>删除</strong></td>
							</tr>
							<form action="<%=request.getContextPath()%>/tjingcairesult/appromore" method="post">
								<c:forEach items="${page.list}" var="tcr" varStatus="num">
									<tr>
										<td title="${num.count}">${num.count}</td>
										<td title="${tcr.audit}">
											<c:choose>
											   <c:when test="${tcr.audit == 0}">已审核 </c:when>
											   <c:otherwise>
											   <input name="checkboxname" type="checkbox"  value='${tcr.id}'/> <a href="javascript:huifu('${tcr.id}')">详细</a>
											   </c:otherwise>
											</c:choose>
										</td>
										<td >${tcr.team}</td>
										<td title="${tcr.type}">
											<c:if test="${tcr.type == 0}">篮球</c:if>
											<c:if test="${tcr.type == 1}">足球</c:if>
										</td>
										<td >${tcr.day}</td>
										<td >${tcr.weekid}</td>
										<td >${tcr.teamid}</td>
										<td title="${tcr.state}">
											<c:if test="${tcr.state == 0}">正常</c:if>
											<c:if test="${tcr.state == 1}">期结</c:if>
											<c:if test="${tcr.state == 2}">开奖</c:if>
											<c:if test="${tcr.state == 3}">算奖</c:if>
										</td>
										<td >${tcr.league}</td>
										<td title="${tcr.cancel}">${tcr.cancel}</td>
										<td title="${tcr.letpoint}">${tcr.letpoint}</td>
										<td title="${tcr.basepoint}">${tcr.basepoint}</td>
										<td title="${tcr.result}">${tcr.result}</td>
										<td title="${tcr.firsthalfresult}">${tcr.firsthalfresult}</td>
										<td title="${tcr.b0}">${tcr.b0}</td>
										<td title="${tcr.b1}">${tcr.b1}</td>
										<td title="${tcr.b2}">${tcr.b2}</td>
										<td title="${tcr.b3}">${tcr.b3}</td>
										<td title="${tcr.b4}">${tcr.b4}</td>
										<td title="${tcr.b5}">${tcr.b5}</td>
										<td title="${tcr.b6}">${tcr.b6}</td>
										<td><a onclick="deletec('${tcr.id}')" style="cursor: hand;">删除</a> </td>
									</tr>
								</c:forEach>
								</form>
								<tr>
								<td></td>
								<td >
									<input type="button" value="通过" onClick="javascript:approvalMore()"/>
								</td>
								<td>&nbsp;</td>
							<tr>
								<td align="left" id="dg1_PageBar" colspan="17"><div
										style="float: right; font-family: Tahoma">
										<c:choose>
											<c:when test="${page.pageIndex != 0}">
												<a href="<%=request.getContextPath()%>/tjingcairesult/list?id=${param.id}&day=${param.day}&type=${param.type}&audit=${param.audit}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
											</c:when>
											<c:otherwise>
										第一页
									</c:otherwise>
										</c:choose>
										|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex > 0}">
												<a
													href="<%=request.getContextPath()%>/tjingcairesult/list?id=${param.id}&day=${param.day}&type=${param.type}&audit=${param.audit}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
											</c:when>
											<c:otherwise>
										上一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 < page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tjingcairesult/list?id=${param.id}&day=${param.day}&type=${param.type}&audit=${param.audit}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
											</c:when>
											<c:otherwise>
										下一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 != page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tjingcairesult/list?id=${param.id}&day=${param.day}&type=${param.type}&audit=${param.audit}&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
											</c:when>
											<c:otherwise>
										最末页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp; &nbsp;&nbsp;转到第&nbsp;<input type="text"
											onkeyup="value=value.replace(/\D/g,'')" style="width: 40px"
											class="inputText" id="_PageBar_Index">&nbsp;页&nbsp;&nbsp;<input
											type="button" value="跳转" class="inputButton" onclick="go()">
										<script type="text/javascript">
											function go() {
												var pageindex = parseInt($("#_PageBar_Index").val()) - 1;
												window.location.href="<%=request.getContextPath()%>/tjingcairesult/list?id=${param.id}&day=${param.day}&type=${param.type}&audit=${param.audit}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
											}
										</script>
									</div>
									<div style="float: left; font-family: Tahoma">共
										${page.totalResult} 条记录，每页 ${page.maxResult} 条，当前第
										${page.pageIndex + 1} / ${page.totalPage} 页</div></td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
			<tr><td style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px; font-size: 14px;"><a href="<%=request.getContextPath()%>/tjingcairesult/addUI">新增</a></td></tr>
		</tbody>
	</table>
</body>
</html>