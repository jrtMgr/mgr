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
					<form action="<%=request.getContextPath()%>/tcharges/chinapay"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
								  <td align="right">商户订单号:</td>
								  <td><input name="ttransactionid" type="text" style="width: 250px" value="${ttransactionid}"
										id="ttransactionid" class="inputText" onfocus="this.select();" /></td>
								   <td align="right">交易日期:</td>
								  <td><input id="transdate" name="transdate"  value="${transdate}"
										class="inputText" type="text" style="width: 80px;"
										onclick="DateTime.onImageMouseDown(event,'Calendar','transdate');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','transdate');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif"></td>
								<td align="right">网关:</td>
								<td>
								<select name="gateid" style="width:80px">
											<option value=" ">有卡</option>
											<option value="2" <c:if test='${"2" eq gateid}'>selected</c:if>>无卡</option></td>											
								  <td align="right"></td>
								  <td align="center"><input type="submit" value="查询" class="inputButton">&nbsp;&nbsp;&nbsp;
									<input type="button" value="重置" onclick="javascript:location.href='<%=request.getContextPath() %>/tcharges/chinapay';" class="inputButton">
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
						     <td width="100%" class="thOver" colspan="2"><strong>上海银联订单明细</strong></td>
						</tr>
						<tr><td>买家支付宝账号：</td><td title="${buyer_email}">${buyer_email}</td></tr>
						<tr><td>买家支付宝唯一用户号：</td><td title="${buyer_id}">${buyer_id}</td></tr>			
				        <tr><td>交易金额是否调整过：</td><td title="${is_total_fee_adjust}">${is_total_fee_adjust}</td></tr>
				        <tr><td>商户网站唯一订单号：</td><td title="${out_trade_no}">${out_trade_no}</td></tr>
				        <tr><td>支付宝交易号：</td><td title="${trade_no}">${trade_no}</td></tr>
				        <tr><td>商品名称：</td><td title="${subject}">${subject}</td></tr>
				        <tr><td>商品描述：</td><td title=""></td></tr>
				        <tr><td>商品单价（元）：</td><td title="${price}">${price}</td></tr>
				        <tr><td>购买数量：</td><td title="${quantity}">${quantity}</td></tr>
				        <tr><td>交易总金额（元）：</td><td title="${total_fee}">${total_fee}</td></tr>
				        <tr><td>卖家的支付宝账号：</td><td title="${seller_email}">${seller_email}</td></tr>
				        <tr><td>卖家支付宝唯一用户号：</td><td title="${seller_id}">${seller_id}</td></tr>
				        <tr><td>折扣：</td><td title="${discount}">${discount}</td></tr>
				        <tr><td>交易冻结状态(1冻结 0未冻结)：</td><td title="${flag_trade_locked}">${flag_trade_locked}</td></tr>
				        <tr><td>退款状态：</td><td title="${refund_status}">${refund_status}</td></tr>
				        <tr><td>物流状态：</td><td title="${logistics_status}">${logistics_status}</td></tr>
				        <tr><td>交易附加状态：</td><td title="${additional_trade_status}">${additional_trade_status}</td></tr>
				        <tr><td>交易状态：</td><td title="${trade_status}">${trade_status}</td></tr>
				        <tr><td>交易创建时间：</td><td title="${gmt_create}">${gmt_create}</td></tr>
				        <tr><td>交易最近一次修改时间：</td><td title="${gmt_last_modified_time}">${gmt_last_modified_time}</td></tr>
				        <tr><td>付款时间：</td><td title="${gmt_payment}">${gmt_payment}</td></tr>
				        <tr><td>收款类型：</td><td title="${payment_type}">${payment_type}</td></tr>				     
				</tbody>
				</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>