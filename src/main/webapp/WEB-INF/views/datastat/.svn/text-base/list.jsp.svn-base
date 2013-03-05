<%@page import="com.ruyicai.mgr.consts.TransactionState"%>
<%@page import="com.ruyicai.mgr.domain.Ttransaction"%>
<%@page import="com.ruyicai.mgr.charge.ChargeType"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
<%@page import="java.math.BigDecimal"%>
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
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">								
								<tr>
								 <td align="center"><input type="button" value="优质用户" onclick="javascript:location.href='<%=request.getContextPath() %>/datastat/highbuyer';" class="inputButton"></td>
								 <td align="center"><input type="button" value="彩种购买数及销量" onclick="javascript:location.href='<%=request.getContextPath() %>/datastat/saledata';" class="inputButton"></td>
								 <td align="center"><input type="button" value="流失率" onclick="javascript:location.href='<%=request.getContextPath() %>/datastat/buyerloss';" class="inputButton"></td>
								 <td align="center"><input type="button" value="追号用户" onclick="javascript:location.href='<%=request.getContextPath() %>/datastat/subscribe';" class="inputButton"></td>
								 <td align="center"><input type="button" value="活动赠金" onclick="javascript:location.href='<%=request.getContextPath() %>/datastat/activitiespage';" class="inputButton"></td>
								</tr>
							</table>
						</div>
					</td>
				</tr>			
		</tbody>
	</table>
</body>
</html>