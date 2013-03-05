<%@page import="org.json.JSONObject"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
	<title>order commit</title>
</head>
<body>
<%
String errormsg = (String)request.getAttribute("errormsg");

JSONObject jo =  (JSONObject)request.getAttribute("jo"); 
String url = jo.has("url")? jo.getString("url") : "";
String batch_no = jo.has("batch_no")? jo.getString("batch_no") : "";
String notify_url = jo.has("notify_url")? jo.getString("notify_url") : "";
String account_name = jo.has("account_name")? jo.getString("account_name") : "";
String partner = jo.has("partner")? jo.getString("partner") : "";
String pay_date = jo.has("pay_date")? jo.getString("pay_date") : "";
String batch_fee = jo.has("batch_fee")? jo.getString("batch_fee") : "";
String service = jo.has("service")? jo.getString("service") : "";
String batch_num = jo.has("batch_num")? jo.getString("batch_num") : "";
String sign = jo.has("sign")? jo.getString("sign") : "";
String sign_type = jo.has("sign_type")? jo.getString("sign_type") : "";
String email = jo.has("email")? jo.getString("email") : "";
String detail_data = jo.has("detail_data")? jo.getString("detail_data") : "";
%>
	<form name="alipaysubmit" method="post"	action="<%=url%>">
			<input type=hidden name="batch_no" value="<%=batch_no%>">
			<input type=hidden name="notify_url" value="<%=notify_url%>">
			<input type=hidden name="account_name" value="<%=account_name%>">
			<input type=hidden name="partner" value="<%=partner%>">
			<input type=hidden name="pay_date" value="<%=pay_date%>">
			<input type=hidden name="batch_fee" value="<%=batch_fee%>">
			<input type=hidden name="service" value="<%=service%>">
			<input type=hidden name="sign" value="<%=sign%>">
			<input type=hidden name="sign_type" value="<%=sign_type%>">
			<input type=hidden name="batch_num" value="<%=batch_num%>">
			<input type=hidden name="email" value="<%=email%>">
			<input type=hidden name="detail_data" value="<%=detail_data%>">

<%
if(!StringUtil.isEmpty(errormsg)) {
%>
			<table>
				<tr>
					<td>
						<font color="#FF0000" size="+1">支付宝提现失败，原因：<%=errormsg%></font>						
					</td>
				</tr>
			</table><%	
}
%>	
	
<%
if(StringUtil.isEmpty(errormsg)) {
%>
如果您的浏览器没有弹出支付页面，请点击按钮<input type='button' name='v_action' value='支付宝网上安全批量付款到账户支付平台'
							onClick='document.alipaysubmit.submit()'>再次提交。<%	
}
%>	
</form>	
<%
if(StringUtil.isEmpty(errormsg)) {
%>
<script language=JavaScript>
	document.alipaysubmit.submit();
</script>
<%	
}
%>				
</body>
</html>
