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

function deletesub(id){
	if(!window.confirm("确定删除吗?")) {
		return;
	}
	var url = "<%=request.getContextPath()%>/biguser/deletesub?agencyno="+id;
	alert(url);
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
								<td width="4%" class="thOver"><strong>代理商编号</strong>
								</td>
								<td width="4%" class="thOver"><strong>代理商名称</strong>
								</td>
								<td width="4%" class="thOver"><strong>联系电话</strong>
								</td>
								<td width="8%" class="thOver"><strong>注册时间</strong>
								</td>
								<td width="2%" class="thOver"><strong>状态</strong>
								</td>
								<td width="10%" class="thOver"><strong>操作</strong>
								</td>
							</tr>
							<c:forEach items="${list}" var="subchannel" varStatus="num">
								<tr>
									<td title="${num.count}">${num.count}</td>
									<td title="${subchannel.agencyno}">${subchannel.agencyno}</td>
									<td title='${subchannel.agencyname}'>${subchannel.agencyname}</td>
									<td title='${subchannel.telephone}'>${subchannel.telephone}</td>
									<td title="${subchannel.regtime}"><fmt:formatDate value="${subchannel.regtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td title='${subchannel.state}'>${subchannel.state}</td>
									<td><a href=""></a>
								<%-- 	<a onclick="deletesub('${subchannel.agencyno}');" style="cursor:hand; font-size: 12px;">删除</a>| --%>
									<a href="<%=request.getContextPath()%>/biguser/editUI?agencyno=${subchannel.agencyno}">修改</a>|
									<a href="<%=request.getContextPath()%>/biguser/recharge?agencyno=${subchannel.agencyno}">充值</a>|
									<a href="<%=request.getContextPath()%>/biguser/getIps?agencyno=${subchannel.agencyno}">IP管理</a>
									</td>
								</tr>
							</c:forEach>
							<tr><td colspan="7">&nbsp;</td></tr>
							<tr>
								<td colspan="7" align="left"><a style="cursor:hand; font-size: 12px;" href="<%=request.getContextPath()%>/biguser/addUI">新增合作商</a>
								| <a style="cursor:hand; font-size: 12px;" href="<%=request.getContextPath()%>/biguser/addTlogicUI">逻辑机录入</a></td>
							</tr>
						</tbody>
					</table></td>
			</tr>
		</tbody>
	</table>
</body>
</html>