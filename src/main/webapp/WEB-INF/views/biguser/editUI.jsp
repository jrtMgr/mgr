<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.domain.Twininfo"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>修改合作商</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/Dialog.js"></script>
<script type="text/javascript">	
<%String errormsg = (String) request.getAttribute("errormsg");
			if (!StringUtil.isEmpty(errormsg)) {%>
function showerror() {
	Dialog.alert("<%=errormsg%>");
}
$(document).ready(function() {
	showerror();
});
<%}%>	
function submint1(){
	if(!window.confirm("确定要修改吗?")) {
		return;
	}
	document.forms[0].submit();
}
</script>
</head>
<body>
	<form action="<%=request.getContextPath()%>/biguser/edit" method="post">
		<table width="55%" cellspacing="0" cellpadding="2" class="dataTable"
			style="margin-left: 50px;">
			<input name="agencyno" type="hidden" value="${subchannel.agencyno}"/>
			<tbody>
				<tr class="dataTableHead">
					<td height="30" class="thOver" colspan="4" align="center"><strong>修改  (*为必填)</strong>
					</td>
				</tr>
				<tr>
					<td align="right">合作商名称：</td>
					<td colspan="2"><input style="width: 250px;" name="agencyname" type="text" class="inputText" onfocus="this.select();" value="${subchannel.agencyname}"/></td>
					<td><font color="red">*</font></td>
				</tr>
				<tr>
					<td align="right">手机号：</td>
					<td colspan="2"><input style="width: 250px;" type="text" name="telephone" class="inputText" onfocus="this.select();" value="${subchannel.telephone}"/></td>
					<td></td>
				</tr>
				<tr>
					<td align="right">公钥：</td>
					<td colspan="2"><input style="width: 250px;" type="text" name="pubvatekey" class="inputText" onfocus="this.select();" value="${subchannel.pubvatekey}"/></td>
					<td></td>
				</tr>
				
				<tr>
					<td align="right">密钥：</td>
					<td colspan="2"><input style="width: 250px;" type="text" name="privatekey" class="inputText" onfocus="this.select();" value="${subchannel.privatekey}"/></td>
					<td><font color="red">*</font></td>
				</tr>
				<tr>
					<td align="right">私钥：</td>
					<td colspan="2">
						<input style="width: 250px;" type="text" name="siyao" class="inputText" onfocus="this.select();" value="${subchannel.siyao}"/>
					</td>
					<td></td>
				</tr>
				<tr>
					<td align="right">协议：</td>
					<td colspan="2">
						<select name="protocol">
							<option value="string" <c:if test="${'string' eq  subchannel.protocol}">selected</c:if>>string</option>
							<option value="XML" <c:if test="${'XML' eq  subchannel.protocol}">selected</c:if>>XML</option>
						</select>
					</td>
					<td></td>
				</tr>
				<tr>
					<td align="right">通信地址：</td>
					<td colspan="2"><input style="width: 250px;" type="text" name="address" class="inputText" onfocus="this.select();" value="${subchannel.address}" />
					</td>
					<td></td>
				</tr>
				<tr>
					<td align="right">异步通知地址：</td>
					<td colspan="2"><input style="width: 250px;" type="text" name="notifyurl" class="inputText" onfocus="this.select();" value="${subchannel.notifyurl}"/>
					</td>
					<td></td>
				</tr>
			</tbody>
		</table>
		<table width="45%" cellspacing="0" cellpadding="2"
			style="margin-top: 10px">
			<tr>
				<td align="center"><input type="button" value="提交 "
					class="inputButton" onclick="submint1()">&nbsp;&nbsp;<input
					type="button" value="返回  " class="inputButton"
					onclick="javascript:history.go(-1);">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
