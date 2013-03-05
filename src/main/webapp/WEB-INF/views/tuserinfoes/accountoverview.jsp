<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script language="JavaScript">
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

function sel(){
	if(!window.confirm("确定修改用户状态吗?")) {
		return;
	}
	var URL="<%=request.getContextPath()%>/tuserinfoes/updateUserState?userno=${ao.userno}";
    URL=URL+'&state='+document.getElementById('state').value;
    location.href=URL;
}

function certid(){
	if(!window.confirm("确定修改用户身份证吗?")) {
		return;
	}
	var URL="<%=request.getContextPath()%>/tuserinfoes/updateUserCertid?userno=${ao.userno}";
    URL=URL+'&certid='+document.getElementById('certid').value;
    location.href=URL;
}

function czmm(){
	if(!window.confirm("确定手工重置吗?")) {
		return;
	}
	var pwd = document.getElementById('cz').value;
	if(pwd == "" || pwd.length < 6){
		alert("密码为空或长度不够");
		return false;
	}
	var URL="<%=request.getContextPath()%>/tuserinfoes/updateUserPwd?userno=${ao.userno}";
    URL=URL+'&pwd='+pwd;
    location.href=URL;
}

function sjcz(){
	var mobileid = document.getElementById("mobileid").value;;
	if(mobileid == ""){
		alert("手机号码必填!");
		return;
	}
	if(!window.confirm("确定随机重置吗?")) {
		return;
	}
	var URL="<%=request.getContextPath()%>/tuserinfoes/updateUserPwdSJ?userno=${ao.userno}&mobileid="+mobileid;
    location.href=URL;
}
</script>

<body style="margin-left: 10px;">
<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" class="thOver" colspan="2"><strong>修改用户状态</strong></td>
				<td width="" height="30" class="thOver" colspan="2"><strong>${user.userName }|${user.mobileid}</strong></td>
			</tr>
			<tr >
				<td colspan="2">
					用户状态：
					<select id="state" name="state" style="width:80px;overflow: hidden;">
							<option value="1" <c:if test='${1==user.state}'>selected</c:if>>正常</option>
							<option value="0" <c:if test='${0==user.state}'>selected</c:if>>关闭</option>
							<option value="2" <c:if test='${2==user.state}'>selected</c:if>>暂停</option>
					</select>
					<input type="button" onclick="sel()" value="保存"/>
				</td>
				<td colspan="2">
					身份证号：
					<input type="text" id="certid" value="${user.certid}"/>
					<input type="button" onclick="certid()" value="保存"/>
				</td>
			</tr>
			<tr class="dataTableHead">
				<td width="" height="30" class="thOver" colspan="4"><strong>用户密码重置</strong></td>
			</tr>
			<tr>
				<td colspan="4">
					手工重置:&nbsp;&nbsp;<input type="text" id="cz"></input>&nbsp;&nbsp;
					<input type="button" onclick="czmm()" value="手工重置"/>
				</td>
				
			</tr>
			<tr>
				<td colspan="4">
					随机重置:&nbsp;&nbsp;<input type="button" onclick="sjcz()" value="随机重置"/>
					发送短信手机号为：<input type="text" id="mobileid" value="${user.mobileid}"/>
				</td>
				
			</tr>
		</tbody>
	</table>
<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" class="thOver" colspan="10"><strong>查询</strong></td>
			</tr>
			<tr>
			   <td>
			      账户概况
			   </td>
			   <td>
				   <a href="<%=request.getContextPath()%>/tuserinfoes/taccountdetaillist?userno=${ao.userno}">账户明细</a>
				</td>
				<td>
				   <a href="<%=request.getContextPath()%>/tuserinfoes/useractionlist?userno=${ao.userno}">投注信息</a>
				</td>
				<td>
				   <a href="<%=request.getContextPath()%>/tuserinfoes/caselotlist?userno=${ao.userno}">合买信息</a>
				</td>
				<td>
					<a href="<%=request.getContextPath()%>/tuserinfoes/tchargelist?userno=${ao.userno}">充值信息</a>
				</td>
				<!-- <td>
				赠送彩金信息
				
					<a href="<%=request.getContextPath()%>/tuserinfoes/tpresentlist?userno=${ao.userno}">赠送彩金信息</a>
					 
				</td>-->
				<td>
					<a href="<%=request.getContextPath()%>/tuserinfoes/tsubscribelist?userno=${ao.userno}">追号信息</a>
				</td>
				<td>
					<a href="<%=request.getContextPath()%>/tuserinfoes/tcashlist?userno=${ao.userno}">提现信息</a>
				</td>
			</tr>
		</tbody>
	</table>

	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="3" class="thOver"><strong>默认账户（单位：元）</strong>&nbsp;&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/tuserinfoes/taccountdetailinfo?userno=${ao.userno}">账户明细2</a></td>
			</tr>
			<c:if test="${ao != null}">
				<tr>
					<td width="150">账户余额:</td>
					<td>${ao.balance}</td>
					<td></td>
				</tr>
				<tr>
					<td>可提现余额:</td>
					<td>${ao.drawbalance}</td>
					<td></td>
				</tr>
				<tr>
					<td>冻结金额:</td>
					<td>${ao.freezebalance}</td>
					<td></td>
				</tr>				
			</c:if>
		</tbody>
	</table>
	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="3" class="thOver"><strong>进账（单位：元）</strong>（合计:${ao.inheji}）</td>
			</tr>
			<c:choose>
				<c:when test="${null != ao}">
				<tr>
					<td width="150">充值:</td>
					<td>${ao.incharge}</td>
					<td></td>
				</tr>
				<tr>
					<td>赠送:</td>
					<td>${ao.inpresent}</td>
					<td></td>
				</tr>
				<tr>
					<td>奖金:</td>
					<td>${ao.inprize}</td>
					<td></td>
				</tr>
				<%-- <tr>
					<td>中奖加奖:</td>
					<td>${ao.inzjjj}</td>
					<td></td>
				</tr>
				<tr>
					<td>合买发单返奖:</td>
					<td>${ao.inhmfdbackprize}</td>
					<td></td>
				</tr> --%>
				<!-- 	<tr>
					<td>合买佣金:</td>
					<td>${ao.inhmyj}</td>
					<td></td>
				</tr> -->				
			<%-- 	<tr>
					<td>取消追号:</td>
					<td>${ao.inqxzh}</td>
					<td></td>
				</tr> --%>
				<tr>
					<td>积分兑换彩金:</td>
					<td>${ao.jfdhcj}</td>
					<td></td>
				</tr>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>

	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="3" class="thOver"><strong>出账（单位：元）</strong>（合计:${ao.ouheji}）</td>
			</tr>
			<c:choose>
				<c:when test="${null != ao}">
				<tr>
					<td width="150">充值手续费:</td>
					<td>${ao.outchargefee}</td>
					<td></td>
				</tr>
				<tr>
					<td>提现手续费:</td>
					<td>${ao.outcashfee}</td>
					<td></td>
				</tr>
				<tr>
					<td>提现:</td>
					<td>${ao.outcash}</td>
					<td></td>
				</tr>
					<tr>
					<td>申请提现:</td>
					<td>${ao.outverifycash}</td>
					<td></td>
				</tr>
				<tr>
					<td>投注:</td>
					<td>${ao.outbetsuccess}</td>
					<td></td>
				</tr>
				<tr>
					<td>追号:</td>
					<td>${ao.outsubscribesuccess}</td>
					<td></td>
				</tr>
				<tr>
					<td>合买:</td>
					<td>${ao.outhmsuccess}</td>
					<td></td>
				</tr>
			<%-- 	<tr>
					<td>预存追号:</td>
					<td>${ao.outyczh}</td>
					<td></td>
				</tr> --%>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
</body>
</html>