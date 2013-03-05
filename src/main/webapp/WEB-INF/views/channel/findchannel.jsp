<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.domain.statis.TCooperat"%>
<%@ page language="java" import="java.util.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>渠道管理</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript">
<% 
String errormsg = (String)request.getAttribute("errormsg");
if(!StringUtil.isEmpty(errormsg)) {
%>
function showerror() {
	alert("<%=errormsg%>");
	window.location.href= "<%=request.getContextPath()%>/channel/page";
}
$(document).ready(function() {
	showerror();
	
});
<%	
}
%>	

	function checkingcount() {
		var cooperat_type = document.getElementById("cooperat_type").value;
		if (cooperat_type == "CPA+CPS") {
			document.getElementById("countchange").innerHTML = "CPA:  <input id='count0' name='count0' type='text'	style='"+"width: 30px;height: 13px;'"+" />&nbsp;&nbsp;&nbsp;<select id='count_type0' name='count_type0'>	<option value='元/个'>元/个</option></select>";
			document.getElementById("countchange").innerHTML += "CPS:  <input id='count1' name='count1' type='text'	style='"+"width:30px;height: 13px;'"+" />&nbsp;&nbsp;&nbsp;<select id='count_type1' name='count_type1'><option value='百分比'>百分比 </option></select>";
		} else if (cooperat_type == "CPA" || cooperat_type == "CPC") {
			document.getElementById("countchange").innerHTML = "<input id='count0' name='count0' type='text'	style='"+"width: 30px;height: 13px;'"+" />	&nbsp;&nbsp;&nbsp;<select id='count_type0' name='count_type0'><option value='元/个'>元/个</option></select>";
		}
		else if (cooperat_type == "CPS") {
			document.getElementById("countchange").innerHTML = "<input id='count0' name='count0' type='text'	style='"+"width: 30px;height: 13px;'"+" />	&nbsp;&nbsp;&nbsp;<select id='count_type0' name='count_type0'><option value='百分比'>百分比 </option></select>";
		}
		else if (cooperat_type == "RIVET") {
			document.getElementById("countchange").innerHTML = "<input id='count0' name='count0' type='text'	style='"+"width: 30px;height: 13px;'"+" />	&nbsp;&nbsp;&nbsp;<select id='count_type0' name='count_type0'><option value='元/月'>元/月</option></select>";
		}
	}

	function removechild(type, falg) {
		var flag = document.getElementById(falg).value;
		var dlog = document.getElementById('dlog').value;
		if (dlog == 'true') {
			if (flag == 'false') {
				document.getElementById(type).removeChild(
						document.getElementById(type).firstChild);
				document.getElementById(type).removeChild(
						document.getElementById(type).firstChild);
				document.getElementById(falg).value = 'true';
			}
		}
	}
</script>
</head>

<body>
	<form id="form1" action="<%=request.getContextPath()%>/channel/updatechannel" method="post">
		<input id="id" name="id" type="hidden" value="${model.id}" />
		<table width="50%" border="0" align="left" cellpadding="3"
			cellspacing="1" style="magmargin-top: 20px;">
			<tr>
				<td colspan="2" align="center"><strong>修改渠道信息</strong></td>
			</tr>
			<tr>
				<td width="120px" align="right">业务名称:</td>
				<td width="120px"><select id="ywid" name="ywid">
						<c:forEach var="list" items="${ywlist}" varStatus="it">
							<option value="${list.id}" <c:if test='${model.ywid eq list.id}'>selected</c:if>>${list.name}</option>
						</c:forEach>
				</select>
				</td>
			</tr>
			<tr>
				<td width="120px" align="right">渠道名称:</td>
				<td><input name="name" type="text" value="${model.name}" />
				</td>
			</tr>
			<tr>
				<td width="120px" align="right">合作模式:</td>
				<td>
					<select id="cooperat_type" name="cooperat_type"	onchange="checkingcount()"	onclick="removechild('cooperat_type','flag')">
						<%
						List<TCooperat> l = (List<TCooperat>) request.getAttribute("cooperat");
						TCooperat hs;
						if(l.size() == 1){
							hs = l.get(0);
						%>
						<option value='CPA' <c:if test='<%=hs.getCooperatType().equals("CPA")%>'>selected</c:if>>CPA</option>
						<option value='CPS' <c:if test='<%=hs.getCooperatType().equals("CPS")%>'>selected</c:if>>CPS</option>
						<option value='CPC' <c:if test='<%=hs.getCooperatType().equals("CPC")%>'>selected</c:if>>CPC</option>
						<option value='CPA+CPS'>CPA+CPS</option>
						<option value='RIVET' <c:if test='<%=hs.getCooperatType().equals("RIVET")%>'>selected</c:if>>RIVET(固定金额)</option>
						<%} else if (l.size() == 2){ %>
						<option value='CPA'>CPA</option>
						<option value='CPS'>CPS</option>
						<option value='CPC'>CPC</option>
						<option value='CPA+CPS' selected="selected">CPA+CPS</option>
						<option value='RIVET'>RIVET(固定金额)</option>
						<%}else{ %>
						<option value=''>--请选择--</option>
						<option value='CPA'>CPA</option>
						<option value='CPS'>CPS</option>
						<option value='CPC'>CPC</option>
						<option value='CPA+CPS'>CPA+CPS</option>
						<option value='RIVET'>RIVET(固定金额)</option>
						<%} %>
				</select>
			</tr>
			<tr>
				<td width="120px" align="right">合作成本:</td>
				<td><span id="countchange"> 
					<%
				 	if (l.size() == 2) {
				 		for (int i = 0; i < l.size(); i++) {
				 			hs = (TCooperat) l.get(i);
							out.print(hs.getCooperatType() + ":");
					%>	
						<input id="count<%=i%>" name="count<%=i%>" type="text" value="<%=hs.getCount()%>" style="width: 30px;"/>
						<select id="count_type<%=i%>" name="count_type<%=i%>">
							<option value="<%=hs.getCountType()%>"><%=hs.getCountType()%></option>
						</select> 
						&nbsp;
						<%
						}
					} else if(l.size() == 1){
						hs = l.get(0);
					%> 	
					<input id="count0" name="count0" type="text" value="<%=hs.getCount()%>" style="width: 30px;"/>
					<select id="count_type0" name="count_type0">
						<option value="<%=hs.getCountType()%>"><%=hs.getCountType()%></option>
					</select> 
					<%} %> 
				 </span>
				  </td>
			</tr>
			<tr>
				<td width="120px" align="right">推广地址:</td>
				<td width="120px"><input id="url" name="url" type="text"
					value="${model.url}" />
				</td>
			</tr>
			<tr>
				<td width="120px" align="right">联系方式:</td>
				<td width="120px"><input id="tel" name="tel" type="text"
					value="${model.tel}" />
				</td>
			</tr>
			<tr>
				<td width="120px" align="right">备注:</td>
				<td width="120px"><input id="bz" name="bz" type="text"
					value="${model.bz}" />
				</td>
			</tr>
			<tr>
				<td width="120px" align="right">是否扣量:</td>
				<td width="120px">
					<select id="isopen" name="isopen">
						<option value="0" <c:if test='${model.isopen == 0}'>selected</c:if>>不扣量</option>
						<option value="1" <c:if test='${model.isopen == 1}'>selected</c:if>>扣量</option>
					</select> 
				</td>
			</tr>
			<tr>
				<td width="120px" align="right">注册率:</td>
				<td width="120px"><input style="width: 60px;" id="regist" name="regist" type="text"
					value="${model.regist}" />例:0.28
				</td>
			</tr>
			<tr>
				<td width="120px" align="right">充值率:</td>
				<td width="120px"><input style="width: 60px;" id="charge" name="charge" type="text"
					value="${model.charge}" />例:0.12
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td></td>
			</tr>
			<tr>
				<td width="120px"></td>
				<td><input type="submit" value="提 交"  />
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
			<input type="button" value="返 回" onclick="javascript:history.go(-1);"/></td>
			</tr>
		</table>
	</form>
</body>
</html>
