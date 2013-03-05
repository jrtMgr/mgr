<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Card File</title>
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" 
	src="<%=request.getContextPath()%>/js/jquery.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Dialog.js"></script>		
<script type="text/javascript">	
<% 
String errormsg = (String)request.getAttribute("errormsg");
String path = (String)request.getAttribute("path");
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
	
</head>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td
				style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
				<table width="100%" cellspacing="0" cellpadding="2"
					class="dataTable">
					<tbody>
						<tr class="dataTableHead">
							<td class="thOver"><strong>充值卡文件下载</strong></td>
							<td class="thOver"></td>
						</tr>
						<tr>
							<td style="padding: 2px 10px;">
							
									<div style="float: left;">
									   <a href=<%=path%> >下载</a>
									</div>									
							</td>
							<td></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</table>	
</body>
</html>