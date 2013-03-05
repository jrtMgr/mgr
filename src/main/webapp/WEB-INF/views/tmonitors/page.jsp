<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<frameset rows="45,*" cols="*" frameborder="no" border="0"
	noresize="false" resizable="no" framespacing="0">
	<frame src="<%=request.getContextPath()%>/tmonitors/top" name="top"
		noresize="noresize" id="top" title="top" scrolling="no" />
	<frame src="<%=request.getContextPath()%>/tmonitors/index" name="main" id="main"
			title="content" />
</frameset>
</html>