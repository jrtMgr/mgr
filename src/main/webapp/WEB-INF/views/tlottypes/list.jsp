<%@page import="java.math.BigDecimal"%>
<%@page import="com.ruyicai.mgr.lot.LotCenter"%>
<%@page import="com.ruyicai.mgr.domain.Tlottype"%>
<%@page import="com.ruyicai.mgr.consts.TlotCtrlState"%>
<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Set"%>
<%@page import="com.ruyicai.mgr.domain.Tlotctrl"%>
<%@page import="java.util.List"%>
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
</script>
<script type="text/javascript">
function confirmupdate() {
	if(window.confirm("确定要修改吗?")) {
		return true;
	}
	return false;
}

function jmx(id){
	alert(id);
	if(!window.confirm("确定调用jmx吗?")) {
		return;
	}
	window.location.href = "<%=request.getContextPath()%>/tlottypes/jmx?lotno="+id;
	
}
</script>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2"
						class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="2%" height="30" class="thOver"></td>
								<td width="4%" class="thOver"><strong>彩种编号</strong>
								</td>
								<td width="6%" class="thOver"><strong>调用jmx</strong>
								</td>
								<td width="6%" class="thOver"><strong>彩票中心</strong>
								</td>
								<td width="6%" class="thOver"><strong>彩种名称</strong>
								</td>
								<td width="6%" class="thOver"><strong>状态</strong>
								</td>
								<td width="6%" class="thOver"><strong>优先级</strong>
								</td>
								<td width="6%" class="thOver"><strong>期结提前时间</strong>
								</td>
								<td width="6%" class="thOver"><strong>自动算奖</strong>
								</td>
								<td width="6%" class="thOver"><strong>自动兑奖</strong>
								</td>
								<td width="6%" class="thOver"><strong>操作</strong>
								</td>
							</tr>
							<%
								List<Tlottype> tlottypes = (List<Tlottype>) request
										.getAttribute("tlottypes");
								if (null != tlottypes) {
							%>
							<c:forEach items="<%=tlottypes %>" var="tlottype"
								varStatus="num">
								<% 
								Tlottype type = (Tlottype)pageContext.getAttribute("tlottype");
								String center = LotCenter.getName(type.getId().getAgencyNo());
								center = StringUtil.isEmpty(center) ? "无" : center;
								String state = BigDecimal.ZERO.equals(type.getState()) ? "不可用" : "正常";
								String onprize = BigDecimal.ZERO.equals(type.getOnprize()) ? "关闭" : "打开";
								String autoencash = BigDecimal.ZERO.equals(type.getAutoencash()) ? "关闭" : "打开";
								%>
								<tr>
								<form action="<%=request.getContextPath()%>/tlottypes/update" method="post" id="form1" onsubmit="return confirmupdate();">
								<input name="id" type="hidden" value="${tlottype.id.id}"> 
								<input name="agencyno" type="hidden" value="${tlottype.id.agencyNo}"> 
									<td title="${num.count}">${num.count}</td>
									<td title="${tlottype.id.id}">${tlottype.id.id}</td>
									<td><a onclick="javascript:jmx('${tlottype.id.id}');" style="cursor: hand;"/>初始化权重</a></td>
									<td title="<%=center%>"><%=center%></td>
									<td title="${tlottype.cname}">${tlottype.cname}</td>
									<td>
									<select name="state">
										<option value="0" <c:if test="<%=BigDecimal.ZERO.equals(type.getState()) %>"> selected="selected"</c:if>>不可用</option>
										<option value="1" <c:if test="<%=BigDecimal.ONE.equals(type.getState()) %>"> selected="selected"</c:if>>正常</option>
										<option value="2" <c:if test="<%=type.getState().intValue()==2 %>"> selected="selected"</c:if>>暂停</option>
									</select>
									</td>
									<td title="${tlottype.lvl}">
										<input name="lvl" value="${tlottype.lvl}" size="5" />
									</td>
									<td title="${tlottype.betendlimit}">
										<input name="betendlimit" value="${tlottype.betendlimit}" size="5" /> 
									</td>
									<td>
										<select name="onprize">
											<option value="0" <c:if test="<%=0 == type.getOnprize() %>"> selected="selected"</c:if>>关闭</option>
											<option value="1" <c:if test="<%=1 == type.getOnprize() %>"> selected="selected"</c:if>>打开</option>
										</select>
									</td>
									<td>
										<select name="autoencash">
											<option value="0" <c:if test="<%=BigDecimal.ZERO.equals(type.getAutoencash()) %>"> selected="selected"</c:if>>关闭</option>
											<option value="1" <c:if test="<%=BigDecimal.ONE.equals(type.getAutoencash()) %>"> selected="selected"</c:if>>打开</option>
										</select>
									</td>
									<td><input type="submit" value="修改"/></td>
									</form>
								</tr>
							</c:forEach>
							<%} %>
						</tbody>
					</table></td>
			</tr>
		</tbody>
	</table>
</body>
</html>