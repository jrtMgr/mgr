<%@page import="java.util.Map"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
<%@page import="com.ruyicai.mgr.consts.SettleFlagState"%>
<%@page import="com.ruyicai.mgr.consts.BetType"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.ruyicai.mgr.domain.Tlot"%>
<%@page import="com.ruyicai.mgr.lot.LotCenter"%>
<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.consts.UserState"%>
<%@page import="com.ruyicai.mgr.domain.Tuserinfo"%>
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

function showerror(msg) {
	Dialog.alert(msg);
}
$(document).ready(function() {
	<% 
	String errormsg = (String)request.getAttribute("errormsg");
	if(!StringUtil.isEmpty(errormsg)) {
	%>
		showerror(<%=errormsg%>);
	<%	
	}
	%>	
	var centerwin = $("#center");
	centerwin.mywin();
});


function viewNews(id){
	var centerwin = $("#center");
	$("#realcontent").html("");
	$("#realcontent").load("<%=request.getContextPath()%>/newsappro/contentUI?id="+id);
	centerwin.show();
}

$.fn.mywin = function() {
	var currentwin = this;
	var windowobj = $(window);
	var browserwidth = windowobj.width();
	var browserheight = windowobj.height();
	var scrollLeft = windowobj.scrollLeft();
	var scrollTop = windowobj.scrollTop();	
	var cwinwidth = currentwin.outerWidth(true);
	var cwinheight = currentwin.outerHeight(true);
	var left = scrollLeft + (browserwidth - cwinwidth) / 2;	
	var top = scrollTop + (browserheight - cwinheight) / 2;
	currentwin.animate({
		left: left,
		top: top
	},100);	

	currentwin.children(".title").children("img").click(function() {
		currentwin.hide("slow")	;
	});
}
function approvalMore(){
	if( checkIDs()&&confirm('所选项将通过审批')){
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
	   alert("请选择资讯!"); 
	   return false; 
	}else{
	   return true;
	}
}
</script>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form action="<%=request.getContextPath()%>/newsappro/list" method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td width="70" height="24px" align="right">
										客户端：
									</td>
									<td width="70" align="left">
										wap
									</td>
									<td width="70" height="24px" align="right">
										资讯类别：
									</td>
									<td width="120" align="left">
										<select name="categoryId">
											<option value="">全部</option>
											<c:forEach items="${categoryList}" var="ca">
												<option value="${ca.id }" <c:if test="${ca.id eq param.categoryId}">selected</c:if>>${ca.categoryName }</option>
											</c:forEach>
										</select>
									</td>
									<td align="right">是否审批:</td>
									<td>
										<select name="checked">
											<option value="" >全部</option>
											<option value="0" <c:if test="${'0' eq param.checked}">selected</c:if>>通过</option>
											<option value="1" <c:if test="${'1' eq param.checked}">selected</c:if>>未通过</option>
										</select>
									</td>
									
									<td align="right">显示行数:</td>
									<td><select id="maxResult" name="maxResult" style="width:60px">
											<option value="15" <c:if test='${"15" eq page.maxResult}'>selected</c:if>>15</option>
											<option value="30" <c:if test='${"30" eq page.maxResult}'>selected</c:if>>30</option>
											<option value="50" <c:if test='${"50" eq page.maxResult}'>selected</c:if>>50</option>
											<option value="100" <c:if test='${"100" eq page.maxResult}'>selected</c:if>>100</option>
									</select></td>
									<td></td>
									<td></td>	
									<td align="right" width="100px"><input type="submit" value="查询" class="inputButton">&nbsp;&nbsp;&nbsp;
									<input type="button" value="重置" onclick="javascript:location.href='<%=request.getContextPath() %>/newsappro/page';" class="inputButton">
									</td>
								</tr>
							</table>
						</div>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<div class="window" id="center">
						<div class="content" id="realcontent">
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2"
						class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="3%" height="30" class="thOver"></td>
								<td width="5%" class="thOver">审核</td>
								<td width="10%" class="thOver"><strong>资讯类别</strong></td>
								<td width="30%" class="thOver"><strong>标题</strong></td>
								<td width="18%" class="thOver"><strong>创建时间</strong></td>
								<td width="10%" class="thOver"><strong>审批状态</strong></td>
								<td width="14%" class="thOver"><strong>优先级</strong></td>
								<td width="15%" class="thOver"><strong>操作</strong></td>
							</tr>
							<% 
								Page page2 = (Page)request.getAttribute("page");
								if(null != page2 && null != page2.getList()) {
							%>
							<form action="<%=request.getContextPath()%>/newsappro/appromore" method="post">
							<c:forEach items="${page.list}" var="tr" varStatus="num">
								<tr class="DataAutoWidth">
									<td title="${num.count}">${num.count}</td>
									<td>
										<c:if test="${tr.checked eq '0'}"><input name="no" type="checkbox"  value="${tr.id}" disabled="disabled"/></c:if>  
										<c:if test="${tr.checked eq '1'}"><input name="checkboxname" type="checkbox"  value="${tr.id}" /></c:if>  
									</td>
									<td>
										<c:forEach items="${categoryList}" var="ca">
											<c:if test="${ca.id eq tr.categoryId}">${ca.categoryName }</c:if>
										</c:forEach>
									</td>
									<td>
										${tr.title}
									</td>
									<td>
									${tr.pubDate}
									</td>
									<td>
										 <c:if test="${tr.checked eq '0'}">通过</c:if>  
										 <c:if test="${tr.checked eq '1'}">未通过</c:if>  
									</td>
									<td>
										${tr.orderId}
									</td>
									<td>
										<a style="cursor: hand;" onclick="javascript:viewNews('${tr.id}')">详细</a>
										
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td>&nbsp;</td>
								<td colspan="3">
									<input type="button" value="通过" onClick="javascript:approvalMore()"/>
								</td>
							</tr>
							</form>
							<tr>
								<td align="left" id="dg1_PageBar" colspan="17"><div
										style="float: right; font-family: Tahoma">
										<c:choose>
											<c:when test="${page.pageIndex != 0}">
												<a
													href="<%=request.getContextPath()%>/news/list?categoryId=${param.categoryId}&checked=${param.checked}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
											</c:when>
											<c:otherwise>
										第一页
									</c:otherwise>
										</c:choose>
										|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex > 0}">
												<a
													href="<%=request.getContextPath()%>/news/list?categoryId=${param.categoryId}&checked=${param.checked}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
											</c:when>
											<c:otherwise>
										上一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 < page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/news/list?categoryId=${param.categoryId}&checked=${param.checked}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
											</c:when>
											<c:otherwise>
										下一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 != page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/news/list?categoryId=${param.categoryId}&checked=${param.checked}&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
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
												window.location.href="<%=request.getContextPath()%>/news/list?categoryId=${param.categoryId}&checked=${param.checked}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
											}
										</script>
									</div>
									<div style="float: left; font-family: Tahoma">共
										${page.totalResult} 条记录，每页 ${page.maxResult} 条，当前第
										${page.pageIndex + 1} / ${page.totalPage} 页</div></td>
							</tr>
							<%} %>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>