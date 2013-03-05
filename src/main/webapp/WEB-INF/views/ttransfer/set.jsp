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
<script type="text/javascript">
function check() {
	if(!window.confirm("确定要保存吗?")) {
		return false;
	}
	return true;
}
</script>
<body>
	<div style="margin-top: 10px;"></div>
	<form action="<%=request.getContextPath()%>/ttransfer/set" method="post">
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>			
			<tr>
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">				
					<table width="100%" cellspacing="0" cellpadding="2"
						class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="100%" height="30" class="thOver" colspan="4">代充值权限维护</td>								
							</tr>
							<tr>	
							    <td width="8%" align="right">用户编号：</td>
							    <td width="42%" align="left"><input name="userno" type="text" style="width: 120px" 
										id="userno" class="inputText" onfocus="this.select();" value="${userno}"/></td>						 
							    <td width="8%" align="right">权限:</td>
							    <td width="42%" align="left"><select name="state" id="state" style="width:80px">									
									    <option value="0" <c:if test='${"0" eq state}'>selected</c:if>>无效</option>
									    <option value="1" <c:if test='${"1" eq state}'>selected</c:if>>有效</option></select></td>
							</tr>							
							<tr>
							   
							</tr>
							<tr><td width="8%" align="right"></td>
							    <td width="42%" align="left"><input type="submit" value="保存" class="inputButton" onclick="return check();"></td>
							    <td width="8%" align="right"><a href="<%=request.getContextPath() %>/ttransfer/list">返回</a></td>
							    <td width="42%" align="left"></td>
						    </tr>									
						</tbody>						
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
</body>
</html>