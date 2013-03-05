<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:directive.page
	import="org.springframework.web.context.WebApplicationContext" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<style type= "text/css" >    
    .x-selectable, .x-selectable * {    
        -moz-user-select: text! important ;    
        -khtml-user-select: text! important ;    
    }    
</style>
<%@ include file="/ext-base.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/msgcenter/selectSMSLog.js"></script>
</head>
</head>
<body>
</body>
</html>