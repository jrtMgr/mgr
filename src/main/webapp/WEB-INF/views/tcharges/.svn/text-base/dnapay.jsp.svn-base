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
					<form action="<%=request.getContextPath()%>/tcharges/dnapay"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
								  <td align="right">商户订单号:</td>
								  <td><input name="ttransactionid" type="text" style="width: 250px" value="${ttransactionid}"
										id="ttransactionid" class="inputText" onfocus="this.select();" /></td>								   
								  <td align="right"></td>
								  <td align="center"><input type="submit" value="查询" class="inputButton">&nbsp;&nbsp;&nbsp;
									<input type="button" value="重置" onclick="javascript:location.href='<%=request.getContextPath() %>/tcharges/dnapay';" class="inputButton">
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
						     <td width="100%" class="thOver" colspan="2"><strong>DNA订单明细</strong></td>
						</tr>
						<tr><td>交易代码（0130:订单查询）：</td><td title="${ProcCode}">${ProcCode}</td></tr>
						<tr><td>用户账号（手机号|银行卡号）：</td><td title="${AccountNum}">${AccountNum}</td></tr>			
				        <tr><td>处理码（310000:查询订单状态）：</td><td title="${ProcessCode}">${ProcessCode}</td></tr>
				        <tr><td>交易金额（元）：</td><td title="${Amount}">${Amount}</td></tr>
				        <tr><td>系统跟踪号（流水号）：</td><td title="${AcqSsn}">${AcqSsn}</td></tr>
				        <tr><td>本地交易时间（时分秒：hhmmss）：</td><td title="${Ltime}">${Ltime}</td></tr>
				        <tr><td>本地交易日期（月日：MMDD）：</td><td title="${Ldate}">${Ldate}</td></tr>
				        <tr><td>清算日期（月日：MMDD）：</td><td title="${SettleDate}">${SettleDate}</td></tr>
				        <tr><td>银联流水号：</td><td title="${UpsNo}">${UpsNo}</td></tr>
				        <tr><td>终端流水号：</td><td title="${TsNo}">${TsNo}</td></tr>
				        <tr><td>交易参考：</td><td title="${Reference}">${Reference}</td></tr>
				        <tr><td>响应码（0000:交易成功）：</td><td title="${RespCode}">${RespCode}</td></tr>
				        <tr><td>备注：</td><td title="${Remark}">${Remark}</td></tr>
				        <tr><td>终端号：</td><td title="${TerminalNo}">${TerminalNo}</td></tr>
				        <tr><td>商户号：</td><td title="${MerchantNo}">${MerchantNo}</td></tr>
				        <tr><td>订单编号：</td><td title="${OrderNo}">${OrderNo}</td></tr>
				        <tr><td>订单状态：</td><td title="${OrderState}">${OrderState}</td></tr>
				        <tr><td>订单有效期（年月日时分秒：YYMMDDhhmmss）：</td><td title="${ValidTime}">${ValidTime}</td></tr>
				        <tr><td>订单类型（00:即时， 01:非即时 ）：</td><td title="${OrderType}">${OrderType}</td></tr>
				        <tr><td>MAC校验码：</td><td title="${Mac}">${Mac}</td></tr>
				        <tr><td></td><td>
				        	<c:if test='${"0000" eq RespCode}'>				        	
				        	 <a href="<%=request.getContextPath() %>/tcharges/doChargeProcess?amt=${Amount}&ttransactionid=${OrderNo}
				        	 &bankorderid=${OrderNo}&banktrace=${UpsNo}&retcode=${RespCode}&retmemo=&bankordertime=&flag=dnapay">置为成功</a>				        
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