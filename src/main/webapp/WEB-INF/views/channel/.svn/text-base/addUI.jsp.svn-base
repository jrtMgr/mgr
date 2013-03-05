<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.domain.statis.TCooperat"%>
<%@ page language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript">
<% 
String errormsg = (String)request.getAttribute("errormsg");
if(!StringUtil.isEmpty(errormsg)) {
%>
function showerror() {
	alert("<%=errormsg%>");
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
	<form action="<%=request.getContextPath()%>/channel/add" method="post">
		<table width="50%" border="0" align="left" cellpadding="3"
			cellspacing="1" style="magmargin-top: 20px;">
			<tr>
				<td colspan="2" align="center"><strong>新增渠道信息</strong></td>
			</tr>
			<tr>
				<td width="120px" align="right">业务名称:</td>
				<td width="120px"><select name="ywid">
						<c:forEach var="list" items="${ywlist}" varStatus="it">
							<option value="${list.id}" >${list.name}</option>
						</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td width="120px" align="right">渠道名称:</td>
				<td><input name="name" type="text" value="" />
				</td>
			</tr>
			<tr>
				<td width="120px" align="right">推广地址:</td>
				<td width="120px"><input name="url" type="text"
					/>
				</td>
			</tr>
			<tr>
				<td width="120px" align="right">联系方式:</td>
				<td width="120px"><input id="tel" name="tel" type="text"/>
				</td>
			</tr>
			<tr>
				<td width="120px" align="right">备注:</td>
				<td width="120px"><input name="bz" type="text"/>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td></td>
			</tr>
			<tr>
				<td width="120px"></td>
				<td><input type="submit" value="提 交"  />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<input type="button" value="返 回" onclick="javascript:history.go(-1);"/></td>
			</tr>
		</table>
	</form>
</body>
</html>
