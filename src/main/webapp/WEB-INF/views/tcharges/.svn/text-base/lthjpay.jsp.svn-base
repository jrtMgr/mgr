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
					<form action="<%=request.getContextPath()%>/tcharges/lthjpay"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
								  <td align="right">商户订单号:</td>
								  <td><input name="ttransactionid" type="text" style="width: 250px" value="${param.ttransactionid}"
										id="ttransactionid" class="inputText" onfocus="this.select();" /></td>
								   <td align="right">订单时间(YYYYMMDDHHMMSS):</td>
								  <td><input name="ordertime" type="text" style="width: 180px" value="${param.ordertime}"
										id="ordertime" class="inputText" onfocus="this.select();" /></td>
								  <td align="right"></td>
								  <td align="center"><input type="submit" value="查询" class="inputButton">&nbsp;&nbsp;&nbsp;
									<input type="button" value="重置" onclick="javascript:location.href='<%=request.getContextPath() %>/tcharges/lthjpay';" class="inputButton">
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
						     <td width="100%" class="thOver" colspan="2"><strong>银联手机订单明细</strong></td>
						</tr>
						<tr><td>交易类型（01:消费,31:消费撤销,04:退货）：</td><td title="${transType}">${transType}</td></tr>								
						<tr><td>商户代码：</td><td title="${merchantId}">${merchantId}</td></tr>
				        <tr><td>商户订单号：</td><td title="${merchantOrderId}">${merchantOrderId}</td></tr>
				        <tr><td>商户订单时间(YYYYMMDDHHMMSS)：</td><td title="${merchantOrderTime}">${merchantOrderTime}</td></tr>
				        <tr><td>查询结果(0：成功,1：失败,2：处理中,3：无此交易)：</td><td title="${queryResult}">${queryResult}</td></tr>
				        <tr><td>清算日期（MMdd）：</td><td title="${settleDate}">${settleDate}</td></tr>
				        <tr><td>清算金额（单位为分）：</td><td title="${setlAmt}">${setlAmt}</td></tr>
				        <tr><td>清算币种(人民币为156)：</td><td title="${setlCurrency}">${setlCurrency}</td></tr>
				        <tr><td>清算汇率：</td><td title="${converRate}">${converRate}</td></tr>
				        <tr><td>CUPS交易流水号：</td><td title="${cupsQid}">${cupsQid}</td></tr>
				        <tr><td>CUPS系统跟踪号：</td><td title="${cupsTraceNum}">${cupsTraceNum}</td></tr>
				        <tr><td>CUPS系统跟踪时间：</td><td title="${cupsTraceTime}">${cupsTraceTime}</td></tr>
				        <tr><td>CUPS响应码(00表示交易成功，其他表示失败)：</td><td title="${cupsRespCode}">${cupsRespCode}</td></tr>
				        <tr><td>CUPS响应码描述：</td><td title="${cupsRespDesc}">${cupsRespDesc}</td></tr>
				        <tr><td></td><td><c:if test='${"01" eq transType}'>
				        	<c:if test='${"0" eq queryResult}'>
				        	<c:if test='${"00" eq cupsRespCode}'>
				        	 <a href="<%=request.getContextPath() %>/tcharges/doChargeProcess?amt=${setlAmt}&ttransactionid=${merchantOrderId}
				        	 &bankorderid=${cupsQid}&banktrace=${cupsTraceNum}&retcode=00&retmemo=Success!&bankordertime=&flag=lthjpay">置为成功</a>
				        	</c:if>
				        	</c:if>
				       
				        </c:if> </td></tr>
				        
				</tbody>
				</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>