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
					<form action="<%=request.getContextPath()%>/webstatistic/list"
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
									<td align="right">渠道:</td>
									<td><select name="channelid" style="width:120px">
											<option value="-1">全部</option>
											<c:forEach items="${channels}" var="channel">
												<option value="${channel.id}" <c:if test='${channel.id eq param.channelid}'>selected</c:if>>${channel.name}</option>
											</c:forEach>
									</select></td>
								<!-- 	<td align="right">渠道:</td>
									<td><select name="channel" style="width:120px">
											<option value="2">web官网</option>
											<option value="40">世界杯推广页</option>
											<option value="97">内蒙古加奖</option>
											<option value="169">宁波完美3</option>
											<option value="217">天涯web</option>
											<option value="458">web3g彩票网</option>
											<option value="538">电信118114</option>
											<option value="539">新浪博客1</option>
											<option value="540">新浪博客2</option>
											<option value="541">新浪博客3</option>
											<option value="542">新浪博客4</option>
											<option value="551">内蒙古时空</option>
											<option value="553">9网广告联盟</option>
											<option value="562">dianxin.cn</option>
											<option value="563">3777导航</option>
											<option value="559">麦优网</option>
											<option value="564">广东电信纠错页面</option>
											<option value="566">彩票家</option>
											<option value="567">卡巴365</option>
											<option value="568">dianxin.cn新</option>
									</select></td> -->
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