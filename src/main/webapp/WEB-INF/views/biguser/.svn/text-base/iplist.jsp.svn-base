<%@page import="com.ruyicai.mgr.domain.Iptables"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script> 
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

function deleteip(id){
	if(!window.confirm("确定删除吗?")) {
		return;
	}
	var url = "<%=request.getContextPath()%>/biguser/deleteip?ipid="+id;
	location.href=url;
}
function baocun(id){
	if(!window.confirm("确定保存新的ip吗?")) {
		return;
	}
	var ipadrr = document.getElementById(id).value;
	var url = "<%=request.getContextPath()%>/biguser/editip?ipid="+id+"&ipaddr="+ipadrr;
	location.href=url;
}
</script>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="60%" cellspacing="0" cellpadding="2"
						class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="2%" height="30" class="thOver"></td>
								<td width="14%" class="thOver"><strong>ip地址</strong>
								</td>
								<td width="10%" class="thOver"><strong>失效时间</strong>
								</td>
								<td width="6%" class="thOver"><strong>IP状态</strong>
								</td>
								<td width="8%" class="thOver"><strong>渠道编号</strong>
								</td>
								<td width="10%" class="thOver"><strong>操作</strong>
								</td>
							</tr>
							<%String agencyno = null; %>
							<c:forEach items="${ips}" var="ip" varStatus="num">
								<tr>
									<td title="${num.count}">${num.count}</td>
									<td title="${ip.ipaddr}"><input id="${ip.ipid}" type="text" value="${ip.ipaddr}"/> &nbsp;
										<a style="cursor:hand; font-size: 12px;" onclick="baocun('${ip.ipid}')">保存</a></td>
									<td title="${ip.expiretime}"><fmt:formatDate value="${ip.expiretime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td title='${ip.state}'><c:if test="${ip.state == 0}">
										关闭
									</c:if>
									<c:if test="${ip.state == 1}">
										开启
									</c:if></td>
									<td title='${ip.agencyno}'>${ip.agencyno}</td>
									<td>
									<a onclick="deleteip('${ip.ipid}');" style="cursor:hand; font-size: 12px;">删除</a>
									</td>
								</tr>
							</c:forEach>
							<tr><td colspan="6">&nbsp;</td></tr>
							<tr>
								<td colspan="6" align="left">&nbsp;<a style="cursor:hand; font-size: 12px;" href="<%=request.getContextPath()%>/biguser/addipUI?agencyno=${param.agencyno}">新增IP</a>
								&nbsp;&nbsp;<a style="cursor:hand; font-size: 12px;" onclick="<%=request.getContextPath()%>/biguser/list">返回</a>
								</td>
							</tr>
						</tbody>
					</table></td>
			</tr>
		</tbody>
	</table>
</body>
</html>