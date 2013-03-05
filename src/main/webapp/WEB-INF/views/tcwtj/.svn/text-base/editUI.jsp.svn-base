<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css" rel="stylesheet" type="text/css">
</head>
<style type="text/css">
<!--
.tcwtj_td{
	text-align: right;
}
-->
</style>
<script language="javascript">
	function caleSum(){
			var usercharge = document.getElementById("usercharge").value;
			var channelcharge = document.getElementById("channelcharge").value;
			var encash = document.getElementById("encash").value;
			var activitypersent = document.getElementById("activitypersent").value;
			var chargepersent = document.getElementById("chargepersent").value;
			var cannel = document.getElementById("cannel").value;
			document.getElementById("inhj").value = parseFloat(usercharge)+parseFloat(channelcharge)+parseFloat(encash)+parseFloat(activitypersent)+parseFloat(chargepersent)+parseFloat(cannel);
	}
	function caleMin(){
		var userbet = document.getElementById("userbet").value;
		var channelbet = document.getElementById("channelbet").value;
		var draw = document.getElementById("draw").value;
		var fee = document.getElementById("fee").value;
		var other1 = document.getElementById("other1").value;
		document.getElementById("outhj").value = parseFloat(userbet)+parseFloat(channelbet)+parseFloat(draw)+parseFloat(fee)+parseFloat(other1);
}
</script>
<body style="margin-left: 10px;">
	<form action="<%=request.getContextPath()%>/tcwtj/edit" method="post" onsubmit="return confirm('是否确认保存?')">
	<input type="hidden" name="date" value="${date}">
	<input type="hidden" name="id" value="${tcwtj.id}">
	<table border="1px" style="margin-top: 2px; margin-left: 30px; margin-bottom:20px; width: 40%;" class="dataTable">
			<tr class="dataTableHead">
				<td width="" height="30" class="thOver" colspan="2" align="center"><strong>修改统计值</strong></td>
			</tr>
			<tr >
				<td colspan="2"><strong>增加金额</strong></td>
			</tr>
			<tr>
				<td  class="tcwtj_td">客户充值:</td>
				<td ><input type="text" name="usercharge" id="usercharge" value="${tcwtj.usercharge}" onblur="caleSum()"/> </td>
			</tr>
			<tr>
				<td class="tcwtj_td">渠道充值:</td>
				<td><input type="text" name="channelcharge" id="channelcharge" value="${tcwtj.channelcharge}" onblur="caleSum()"/></td>
			</tr>
			<tr>
				<td class="tcwtj_td">兑奖:</td>
				<td><input type="text" name="encash" id="encash" value="${tcwtj.encash}" onblur="caleSum()"/></td>
			</tr>
			<tr>
				<td class="tcwtj_td">活动赠彩:</td>
				<td><input type="text" name="activitypersent" id="activitypersent" value="${tcwtj.activitypersent}" onblur="caleSum()"/></td>
			</tr>
			<tr>
				<td class="tcwtj_td">充值赠彩:</td>
				<td><input type="text" name="chargepersent" id="chargepersent" value="${tcwtj.chargepersent}" onblur="caleSum()"/></td>
			</tr>
			<tr>
				<td class="tcwtj_td">撤单:</td>
				<td><input type="text" name="cannel" id="cannel" value="${tcwtj.cannel}" onblur="caleSum()"/></td>
			</tr>
			<tr>
				<td class="tcwtj_td">增加合计:</td>
				<td><input type="text" name="inhj" id="inhj" value="${tcwtj.inhj}" readonly="readonly"/></td>
			</tr>
			
			<tr>
				<td colspan="2"><strong>减少金额</strong></td>
			</tr>
			<tr>
				<td class="tcwtj_td">客户投注:</td>
				<td> <input type="text" name="userbet" id="userbet" value="${tcwtj.userbet}" onblur="caleMin()"/> </td>
			</tr>
			<tr>
				<td class="tcwtj_td">渠道投注:</td>
				<td> <input type="text" name="channelbet" id="channelbet" value="${tcwtj.channelbet}" onblur="caleMin()"/></td>
			</tr>
			<tr>
				<td class="tcwtj_td">提现:</td>
				<td><input type="text" name="draw" id="draw" value="${tcwtj.draw}" onblur="caleMin()"/></td>
			</tr>
			<tr>
				<td class="tcwtj_td">手续费:</td>
				<td><input type="text" name="fee" id="fee" value="${tcwtj.fee}" onblur="caleMin()"/></td>
			</tr>
			<tr>
				<td class="tcwtj_td">其他:</td>
				<td><input type="text" name="other1" id="other1" value="${tcwtj.other1}" onblur="caleMin()"/></td>
			</tr>
			<tr>
				<td class="tcwtj_td">减少合计:</td>
				<td><input type="text" name="outhj" id="outhj" value="${tcwtj.outhj}" readonly="readonly"/></td>
			</tr>
			<tr>
				<td class="tcwtj_td">账户余额:</td>
				<td><input type="text" name="balance" value="${tcwtj.balance}"/></td>
			</tr>
			<tr>
				<td class="tcwtj_td">备注:</td>
				<td><textarea rows="0" cols="30" name="memo">${tcwtj.memo}</textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="保存"/> &nbsp&nbsp <input type="button" value="返回" onclick="javascript:history.back()"/></td>
			</tr>
	</table>
	
	</form>
</body>
</html>