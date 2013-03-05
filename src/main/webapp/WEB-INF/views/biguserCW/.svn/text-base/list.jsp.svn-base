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
								<td width="1%" height="30" class="thOver"></td>
								<td width="8%" class="thOver"><strong>交易编号</strong>
								</td>
								<td width="3%" class="thOver"><strong>代理商编号</strong>
								</td>
								<td width="4%" class="thOver"><strong>代理商名称</strong>
								</td>
								<td width="3%" class="thOver"><strong>联系电话</strong>
								</td>
								<td width="5%" class="thOver"><strong>操作时间</strong>
								</td>
								<td width="3%" class="thOver"><strong>金额(元)</strong>
								</td>
								<td width="3%" class="thOver"><strong>交易类型</strong>
								</td>
								<td width="3%" class="thOver"><strong>交易状态</strong>
								</td>
								<td width="6%" class="thOver"><strong>操作</strong>
								</td>
							</tr>
							<c:forEach items="${list}" var="b" varStatus="num">
								<tr>
									<td title="${num.count}">${num.count}</td>
									<td title="${b.id}">${b.id}</td>
									<td title='${b.agencyno}'>${b.agencyno}</td>
									<td title='${b.agencyname}'>${b.agencyname}</td>
									<td title='${b.telephone}'>${b.telephone}</td>
									<td title="${b.plattime}"><fmt:formatDate value="${b.plattime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td title='${b.amt}'>${b.amt}</td>
									<td title='${b.type}'>${b.type}</td>
									<td title='${b.state}'>${b.state}</td>
									<td><a href="<%=request.getContextPath()%>/biguserCW/confirm?id=${b.id}" onclick="return confirm('确认充值吗？')">确认充值</a>&nbsp;&nbsp;
									<a href="<%=request.getContextPath()%>/biguserCW/cannel?id=${b.id}" onclick="return confirm('确认取消充值吗？')">取消充值</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table></td>
			</tr>
		</tbody>
	</table>
</body>
</html>