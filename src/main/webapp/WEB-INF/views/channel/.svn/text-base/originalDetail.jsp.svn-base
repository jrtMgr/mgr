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
					<form action="<%=request.getContextPath()%>/channel/originalDetail"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">统计时间:</td>
									<td><input id="starttime" name="starttime" value="${param.starttime}"
										class="inputText" type="text" style="width: 80px;"
										 onclick="DateTime.onImageMouseDown(event,'Calendar','starttime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','starttime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
										-&nbsp;<input id="endtime" name="endtime" value="${param.endtime}"
										class="inputText" type="text" ztype="date"
										style="width: 80px;"
										onclick="DateTime.onImageMouseDown(event,'Calendar','endtime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','endtime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
									</td>
									<td align="right">渠道ID:</td>
									<td>
									<input type="text" name="channelid" value="${param.channelid}" readonly="readonly"></input>
									</td>
									<td align="center">&nbsp;&nbsp;<input type="submit" value="查询" class="inputButton">&nbsp;&nbsp;&nbsp;
									<input type="button" value="重置" onclick="javascript:location.href='<%=request.getContextPath() %>/torders/page';" class="inputButton">
									</td>
								</tr>
							</table>
						</div>
					</form>
				</td>
			</tr>
			<tr>
				<td	style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
						style="margin-top: 10px">
						<tbody>
							<tr class="dataTableHead">
								<td width="" height="30" colspan="3" class="thOver"><strong>日期:${param.starttime}  ~ ${param.endtime}</strong></td>
							</tr>
							<tr>
								<td> 注册总人数：</td>
								<td>${CountRegistUser}</td>
								<td></td>
							</tr>
							<tr>
								<td>充值人数:</td>
								<td>${CountCharge}</td>
								<td></td>
							</tr>
							<tr>
								<td>充值总金额:</td>
								<td>${SumCharge}</td>
								<td></td>
							</tr>
							<tr>
								<td>购彩总金额:</td>
								<td>${BET_COUNT_MONEY}</td>
								<td></td>
							</tr>
							<tr>
								<td>购彩总人数:</td>
								<td>${BET_COUNT_USER}</td>
								<td></td>
							</tr>
							<tr>
								<td> 中奖总金额:</td>
								<td>${SumWin}</td>
								<td></td>
							</tr>
							<tr>
								<td>新用户充值金额:</td>
								<td>${SumNewCharge}</td>
								<td></td>
							</tr>
							<tr>
								<td>新用户充值人数:</td>
								<td>${SumNewChargeUser}</td>
								<td></td>
							</tr>
							<tr>
								<td>新用户购彩金额:</td>
								<td>${SumNewLot}</td>
								<td></td>
							</tr>
							<tr>
								<td>新用户购彩人数:</td>
								<td>${SumNewLotUser}</td>
								<td></td>
							</tr>
						</tbody>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>