<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath() %>/styles/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript">
function changePassword() {
	window.parent.content.location = "<%=request.getContextPath() %>/frame/blank";
}
function logout() {
	if(window.confirm("确定退出?")) {
				window.parent.location = "<%=request.getContextPath() %>/j_spring_security_logout";
	}
}
</script>
</head>
<body style="min-width:1003px">
	<table id="_TableHeader" width="100%" border="0" cellpadding="0"
		cellspacing="0" class="bluebg"
		style="background:#3388bb url(<%=request.getContextPath() %>/images/vistaBlue.jpg) repeat-x left top;">
		<tr>
			<td height="70" valign="bottom">
			<table height="70" border="0" cellpadding="0" cellspacing="0"
				style="position:relative;">
				<tr>
					<td style="padding:0"><img src="<%=request.getContextPath() %>/images/logo.png"></td>
				</tr>
			</table>
			</td>
			<td valign="bottom">
			<div style="text-align:right; margin:0 20px 15px 0;">当前用户：<b>${user.nickname}</b>
			&nbsp;[&nbsp;<a href="javascript:void(0);"
				onClick="logout();">退出登录</a> | <a
				href="javascript:changePassword();">修改密码</a> ]</div>
			</td>
		</tr>
	</table>
</body>
</html>