<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
</head>
<%
	List<String> result = (ArrayList<String>) request.getAttribute("result");
%>
<body>
	<table width="100%" cellspacing="0" cellpadding="2" >
		<tbody id="data">
			<%
				for(String str : result ){
			%>
			<tr>
				<td><%=str %></td>
			</tr>
			<%	
				}
			%>
		</tbody>
	</table>
</body>
</html>