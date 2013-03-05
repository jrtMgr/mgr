<%@page import="com.ruyicai.mgr.domain.TawardDetail"%>
<%@page import="com.ruyicai.mgr.domain.Tawardlevel"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.lot.Lottype"%>
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
function aduitSuccess(id){
	if(!window.confirm("确定通过吗?")) {
		return;
	}
	location.href="<%=request.getContextPath()%>/tawardlevelAudit/aduitSuccess?id="+id;
}
function aduitFail(id){
	if(!window.confirm("确定废弃吗?")) {
		return;
	}
	location.href="<%=request.getContextPath()%>/tawardlevelAudit/aduitFail?id="+id;
}
</script>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form action="<%=request.getContextPath()%>/tawardlevelAudit/list"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td><td align="right">彩种:</td>
									<td><select name="lotno" style="width:60px">
											<c:forEach var="lot" items="<%=com.ruyicai.mgr.lot.Lottype.getMap().entrySet() %>">
												<option value="${lot.key}" <c:if test="${lot.key eq param.lotno}">selected</c:if>>${lot.value}</option>
											</c:forEach>
									</select></td></td>
									<td align="center"><input type="submit" value="查询" class="inputButton">&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
							</table>
						</div>
					</form>
				</td>
			</tr>
			<tr>
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="99%" cellspacing="0" cellpadding="2" class="dataTable">
						<tbody>
							<tr>
								<td class="thOver" colspan="5">奖级信息</td>
							</tr>
							<tr class="dataTableHead">
								<td class="thOver"><strong>采种</strong></td>
								<td class="thOver"><strong>奖等</strong></td>
								<td class="thOver"><strong>名字</strong></td>
								<td class="thOver"><strong>奖级</strong></td>
								<td class="thOver"><strong>追奖</strong></td>
							</tr>
							<c:forEach items="${list}" var="tawardlevel">
							<% 
								Tawardlevel tawardlevel = (Tawardlevel)pageContext.getAttribute("tawardlevel");
								String lottype = Lottype.getName(tawardlevel.getLotno());
							%>
								<tr>
									<td title="<%=lottype%>"><%=lottype%></td>
									<td title="${tawardlevel.level}">${tawardlevel.level}</td>
									<td title="${tawardlevel.name}">${tawardlevel.name}</td>
									<td title="${tawardlevel.levelprize}">${tawardlevel.levelprize}</td>
									<td title="${tawardlevel.addprize}">${tawardlevel.addprize}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</td>
			</tr>
			
			<tr>
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="99%" cellspacing="0" cellpadding="2"
						class="dataTable">
						<tbody>
							<tr>
								<td class="thOver" colspan="9">追加信息</td>
							</tr>
							<tr  class="dataTableHead">
								<td class="thOver"><strong>采种</strong></td>
								<td class="thOver"><strong>奖等</strong></td>
								<td class="thOver"><strong>申请人ID</strong></td>
								<td class="thOver"><strong>申请时间</strong></td>
								<td class="thOver"><strong>加奖开始时间</strong></td>
								<td class="thOver"><strong>加奖结束时间</strong></td>
								<td class="thOver"><strong>追奖</strong></td>
								<td class="thOver"><strong>状态</strong></td>
								<td class="thOver"><strong>审核</strong></td>
							</tr>
							<c:forEach items="${awardlist}" var="tawardDetail">
							<% 
								TawardDetail tawardDetail = (TawardDetail)pageContext.getAttribute("tawardDetail");
								String lottype = Lottype.getName(tawardDetail.getLotno());
								String state = "";
								if(TawardDetail.STATE_AUDITING == tawardDetail.getState()){
									state = "正在审核";
								}else if (TawardDetail.STATE_AUDITED == tawardDetail.getState()){
									state = "审核通过";
								}else if (TawardDetail.STATE_ING == tawardDetail.getState()){
									state = "正在进行";
								}else if (TawardDetail.STATE_DEAD == tawardDetail.getState()){
									state = "废弃";
								}
								
							%>
								<tr>
									<td title="<%=lottype%>"><%=lottype%></td>
									<td title="${tawardDetail.level}">${tawardDetail.level}</td>
									<td title="${tawardDetail.userno}">${tawardDetail.userno}</td>
									<td title="${tawardDetail.applytime}"><fmt:formatDate value="${tawardDetail.applytime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td title="${tawardDetail.starttime}"><fmt:formatDate value="${tawardDetail.starttime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td title="${tawardDetail.endtime}"><fmt:formatDate value="${tawardDetail.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td title="${tawardDetail.addprize}">${tawardDetail.addprize}</td>
									<td title="<%=state%>"><%=state%></td>
									<td>
									<%if(tawardDetail.getState()==TawardDetail.STATE_AUDITING){ %>
										<a onclick="aduitSuccess(${tawardDetail.id});" style="cursor:hand; font-size: 12px;">通过</a>
										<a onclick="aduitFail(${tawardDetail.id});" style="cursor:hand; font-size: 12px;">废弃</a>
									<%}else if(tawardDetail.getState()==TawardDetail.STATE_AUDITED || tawardDetail.getState()==TawardDetail.STATE_ING){ %>
										<a onclick="aduitFail(${tawardDetail.id});" style="cursor:hand; font-size: 12px;">废弃</a>
									<%} %>
									</td>
								</tr>
							</c:forEach>
							<tr>
								<td colspan="9" align="left"><a style="cursor:hand; font-size: 12px;" href="<%=request.getContextPath()%>/tawardlevelAudit/addADUI?lotno=${param.lotno}">新增加奖</a></td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>