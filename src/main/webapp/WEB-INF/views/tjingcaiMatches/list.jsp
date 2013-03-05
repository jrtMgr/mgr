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

function selectall(){ 
	var a = document.getElementsByTagName("input"); 
	for (var i=0; i<a.length; i++) 
	if (a[i].type == "checkbox") a[i].checked =!a[i].checked; 
} 
function jingcaiend(type,day,weekid,teamid){
	if(window.confirm('确定期结')){
		window.location.href = "<%=request.getContextPath()%>/tjingcaiMatches/jingcaiend?type="+type+"&day="+day+"&weekid="+weekid+"&teamid="+teamid;
	}
}
</script>
<body>
	<div style="margin-top: 10px; margin-left: 20px"><a href="<%=request.getContextPath()%>/tjingcaiMatches/getfootballmatches">抓取足球赛事</a>| 
	<a href="<%=request.getContextPath()%>/tjingcaiMatches/getbasketballmatches">抓取蓝球赛事</a></div>
	<table width="120%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form action="<%=request.getContextPath()%>/tjingcaiMatches/list"
						method="post">
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
									<td align="right">状态:</td>
									<td><select name="state" style="width:100px">
											<option value="">--请选择--</option>
											<option value="0" <c:if test='${0 == param.state}'>selected</c:if>>正常</option>
											<option value="1" <c:if test='${1 == param.state}'>selected</c:if>>期结</option>
											<option value="2" <c:if test='${2 == param.state}'>selected</c:if>>开奖</option>
											<option value="3" <c:if test='${3 == param.state}'>selected</c:if>>算奖</option>
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
					<table width="120%" cellspacing="0" cellpadding="2"
						class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="3%" height="30" class="thOver"></td>
								<td width="6%" class="thOver"><strong>修改</strong></td>
								<td width="4%" class="thOver"><strong>类型 </strong></td>
								<td width="7%" class="thOver"><strong>日期</strong></td>
								<td width="5%" class="thOver"><strong>周数</strong></td>
								<td width="6%" class="thOver"><strong>场次</strong></td>
								<td width="12%" class="thOver"><strong>销售时间 </strong></td>
								<td width="6%" class="thOver"><strong>销售状态 </strong></td>
								<td width="7%" class="thOver"><strong>状态</strong></td>
								<td width="6%" class="thOver"><strong>联赛</strong></td>
								<td width="9%" class="thOver"><strong>主队：客队</strong></td>
								<td width="10%" class="thOver"><strong>比赛时间</strong></td>
								<td width="7%" class="thOver"><strong>不支持玩法</strong></td>
								<td width="7%" class="thOver"><strong>简称</strong></td>
								<td width="6%" class="thOver"><strong>审核</strong></td>
								<td width="5%" class="thOver"><strong>抓取赛果</strong></td>
							</tr>
							<form action="<%=request.getContextPath()%>/tjingcaiMatches/appromore" method="post">
								<c:forEach items="${page.list}" var="tjm" varStatus="num">
									<tr>
										<td title="${num.count}">${num.count}</td>
										<td>
										<a href="<%=request.getContextPath()%>/tjingcaiMatches/editUI?type=${tjm.id.type}&day=${tjm.id.day}&weekid=${tjm.id.weekid}&teamid=${tjm.id.teamid}">修改</a>|
										<a onclick="jingcaiend('${tjm.id.type}','${tjm.id.day}','${tjm.id.weekid}','${tjm.id.teamid}')" style="cursor: hand;">期结</a>
										</td>
										
										<td title="${tjm.id.type}">
										<c:if test="${tjm.id.type == 0}">篮球</c:if>
										<c:if test="${tjm.id.type == 1}">足球</c:if>
										</td>
										<td title="${tjm.id.day}">${tjm.id.day}</td>
										<td title="${tjm.id.weekid}">${tjm.id.weekid}</td>
										<td title="${tjm.id.teamid}">${tjm.id.teamid}</td>
										<td title="${tjm.endtime}">${tjm.endtime}</td>
										<td title="${tjm.saleflag}">${tjm.saleflag}</td>
										<td title="${tjm.state}">
											<c:if test="${tjm.state == 0}">正常</c:if>
											<c:if test="${tjm.state == 1}">期结</c:if>
											<c:if test="${tjm.state == 2}">开奖</c:if>
											<c:if test="${tjm.state == 3}">算奖</c:if>
										</td>
										<td title="${tjm.league}">${tjm.league}</td>
										<td title="${tjm.team}">${tjm.team}</td>
										<td title="${tjm.time}"><fmt:formatDate value="${tjm.time}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td title="${tjm.unsupport}">${tjm.unsupport}</td>
										<td title="${tjm.shortname}">${tjm.shortname}</td>
										<td title="${tjm.audit}">
											<c:if test="${tjm.audit == 0}">已审核</c:if>
											<c:if test="${tjm.audit == 1}">未审核<input name="checkboxname" type="checkbox"  value='${tjm.id}'/> </c:if>
											<c:if test="${tjm.audit == null}"><input name="checkboxname" type="checkbox"  value='${tjm.id}'/> </c:if>
										</td>
										<td><a href="<%=request.getContextPath()%>/tjingcaiMatches/getbasketballmatches?type=${tjm.id.type}&day=${tjm.id.day}&weekid=${tjm.id.weekid}&teamid=${tjm.id.teamid}">抓取赛果</a></td>
									</tr>
								</c:forEach>
								</form>
								<tr>
								<td colspan="13">&nbsp;</td>
								<td >
									<input type="button" value="通过" onClick="javascript:approvalMore()"/>
									<input type="button" value="反选" onClick="javascript:selectall()"/>
								</td>
								<td	>&nbsp;</td>
							</tr>
							<tr>
								<td align="left" id="dg1_PageBar" colspan="17"><div
										style="float: right; font-family: Tahoma">
										<c:choose>
											<c:when test="${page.pageIndex != 0}">
												<a href="<%=request.getContextPath()%>/tjingcaiMatches/list?day=${param.day}&type=${param.type}&state=${param.state}&audit=${param.audit}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
											</c:when>
											<c:otherwise>
										第一页
									</c:otherwise>
										</c:choose>
										|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex > 0}">
												<a
													href="<%=request.getContextPath()%>/tjingcaiMatches/list?day=${param.day}&type=${param.type}&state=${param.state}&audit=${param.audit}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
											</c:when>
											<c:otherwise>
										上一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 < page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tjingcaiMatches/list?day=${param.day}&type=${param.type}&state=${param.state}&audit=${param.audit}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
											</c:when>
											<c:otherwise>
										下一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 != page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tjingcaiMatches/list?day=${param.day}&type=${param.type}&state=${param.state}&audit=${param.audit}&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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
												window.location.href="<%=request.getContextPath()%>/tjingcaiMatches/list?day=${param.day}&type=${param.type}&state=${param.state}&audit=${param.audit}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
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
			<tr><td style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px; font-size: 14px;"><a href="<%=request.getContextPath()%>/tjingcaiMatches/addUI">新增</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/tjingcaiMatches/saveEventsUI">添加简称</a></td></tr>
		</tbody>
	</table>
</body>
</html>