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
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form action="<%=request.getContextPath()%>/tcharges/lakalapay"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
								  <td align="right">商户订单号:</td>
								  <td><input name="ttransactionid" type="text" style="width: 250px" value="${ttransactionid}"
										id="ttransactionid" class="inputText" onfocus="this.select();" /></td>								   
								     <td align="right">订单时间(YYYYMMDD):</td>
								  <td><input name="ordertime" type="text" style="width: 180px" value="${param.ordertime}"
										id="ordertime" class="inputText" onfocus="this.select();" /></td>
								  <td align="right"></td>
								  <td align="center"><input type="submit" value="查询" class="inputButton">&nbsp;&nbsp;&nbsp;
									<input type="button" value="重置" onclick="javascript:location.href='<%=request.getContextPath() %>/tcharges/lakalapay';" class="inputButton">
								    &nbsp;&nbsp;&nbsp;<input type="button" value="返 回 " class="inputButton" onclick="javascript:location.href='<%=request.getContextPath() %>/tcharges/list';;"/>
								  </td>	
								</tr>
							</table>
						</div>
					</form>
				</td>
			   <tr>
				<td	style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2" class="dataTable">
						<tbody>
						<tr class="dataTableHead">
						     <td width="100%" class="thOver" colspan="2"><strong>拉卡拉订单明细</strong></td>
						</tr>
						<tr><td>交易时间：</td><td title="${accountDate}">${accountDate}</td></tr>
						<tr><td>支付金额（单位：分）：</td><td title="${amount}">${amount}</td></tr>		
				        <tr><td>支付方式：</td><td title="${payMethod}">${payMethod}</td></tr>
				        <tr><td>商户号：</td><td title="${merId}">${merId}</td></tr>
				        <tr><td>订单创建日期（年月日：YYYYMMDD）：</td><td title="${orderDate}">${orderDate}</td></tr>
				        <tr><td>商户订单号：</td><td title="${orderId}">${orderId}</td></tr>
				        <tr><td>拉卡拉流水号：</td><td title="${paySeq}">${paySeq}</td></tr>
				        <tr><td>查询结果（Y：支付成功， F：支付未成功 ，N：订单不存在）：</td><td title="${result}">${result}</td></tr>
				        <tr><td>版本号：</td><td title="${verId}">${verId}</td></tr>
				        <tr><td>验证字符串：</td><td title="${verifyString}">${verifyString}</td></tr>
				        <tr><td></td><td>
				        	<c:if test='${"Y" eq result}'>				        	
				        	 <a href="<%=request.getContextPath() %>/tcharges/doChargeProcess?amt=${amount}&ttransactionid=${orderId}
				        	 &bankorderid=${orderId}&banktrace=${paySeq}&retcode=${result}&retmemo=&bankordertime=${accountDate}&flag=lakalapay">置为成功</a>				        
				        	</c:if>				       
				        </td></tr>					     
				</tbody>
				</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>