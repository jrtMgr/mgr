<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>资讯内容</title>
	</head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script language="javascript">

	function approval(flag){
		$("#checked").val(flag);
		document.forms[1].submit();
	}
	</script>
	
	<body>
		<form method="post" action="<%=request.getContextPath()%>/newsappro/appro">
			<input id="id" name="id" type="hidden" value="${news.id}">
			<input id="checked" name="checked" type="hidden" >
			<div id="maincontent">
				<div style="border: 3px;">
					${news.content}
				</div>
				<div class="functions">
					<div style="float: left;">
						<input type="button" align="left" id="del" name="del" class="button" value="通过" style="width: 40"
									onClick="javascript:approval('0')"/>
						<input type="button" align="left" id="add" name="add" class="button" value="未通过" style="width: 50"
									onClick="javascript:approval('1')" />
					</div> 
				</div>
			</div>
		</form>
	</body>
</html>