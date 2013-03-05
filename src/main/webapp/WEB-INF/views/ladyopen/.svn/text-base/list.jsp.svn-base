<%@page import="com.ruyicai.mgr.consts.EncashState"%>
<%@page import="com.ruyicai.mgr.consts.TlotCtrlState"%>
<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Set"%>
<%@page import="com.ruyicai.mgr.domain.Tlotctrl"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
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
<script type="text/javascript">
function change(sobj) {
	var info = $("#" + $(sobj).val()).val().split("_");
	var memo = $("#" + info[0] + "_memo");
	memo.attr("title", info[1]);
	memo.html(info[1]);
	var starttime = $("#" + info[0] + "_starttime");
	starttime.attr("title", info[2]);
	starttime.html(info[2]);
	var endbettime = $("#" + info[0] + "_endbettime");
	endbettime.attr("title", info[3]);
	endbettime.html(info[3]);
	var endtime = $("#" + info[0] + "_endtime");
	endtime.attr("title", info[4]);
	endtime.html(info[4]);
	var createtime = $("#" + info[0] + "_createtime");
	createtime.attr("title", info[5]);
	createtime.html(info[5]);
}
function openinfo(name) {
	var info = $("#" + $("#" + name + "_batchcode").val()).val().split("_");
	if("0" == info[6]) {
		Dialog.alert("当前期没有开奖信息");
		return;
	}
	var d = new Dialog("开奖信息", "ladyopen/openinfo?info=" + ($("#" + name + "_batchcode").val()), 400, 300);
	d.show();
}

function info(name) {
	var info = $("#" + $("#" + name + "_batchcode").val()).val().split("_");
	if("0" == info[6]) {
		Dialog.alert("当前期没有开奖信息");
		return;
	}
	var d = new Dialog("开奖信息", "ladyopen/info?info=" + ($("#" + name + "_batchcode").val()), 800, 450);
	d.show();
}

function fun(){
	document.forms[1].submit();
}
</script>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="100%" cellspacing="0" cellpadding="2"
						class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="2%" height="30" class="thOver"></td>
								<td width="4%" class="thOver"><strong>彩种编号</strong>
								</td>
								<td width="6%" class="thOver"><strong>彩种名称</strong>
								</td>
								<td width="8%" class="thOver"><strong>期号</strong>
								</td>
								<td width="8%" class="thOver"><strong>开奖</strong>
								</td>
								<td width="5%" class="thOver"><strong>状态</strong>
								</td>
								<td width="5%" class="thOver"><strong>派奖状态</strong>
								</td>
								<td width="9%" class="thOver"><strong>开期时间</strong>
								</td>
								<td width="9%" class="thOver"><strong>结束投注时间</strong>
								</td>
								<td width="9%" class="thOver"><strong>合买终止时间</strong>
								</td>
								<td width="9%" class="thOver"><strong>封期时间</strong>
								</td>
								<td width="9%" class="thOver"><strong>创建时间</strong>
								</td>
								
								
							</tr>
							<%
								Map<String, List<Tlotctrl>> tlotctrls = (Map<String, List<Tlotctrl>>) request.getAttribute("tlotctrls");
							%>
							<c:forEach items="<%=tlotctrls.entrySet() %>" var="entry"
								varStatus="num">
								<%
									Entry<String, List<Tlotctrl>> entry = (Entry<String, List<Tlotctrl>>) pageContext.getAttribute("entry");
									if(entry.getValue()!=null && entry.getValue().size() != 0){
										Tlotctrl tlotctrl = entry.getValue().get(0);
										pageContext.setAttribute("tlotctrl", tlotctrl);
								%>
								<tr>
									<td title="${num.count}">${num.count}</td>
									<td>${entry.key}</td>
									<td title="<%=Lottype.getName(entry.getKey())%>"><%=Lottype.getName(entry.getKey())%></td>
									<td><select id="${entry.key}_batchcode" style="width:100px" onchange="change(this);">
											<c:forEach items="${entry.value}" var="tlotctrl2">
												<option value="${tlotctrl2.id.lotno}_${tlotctrl2.id.batchcode}">${tlotctrl2.id.batchcode}</option>
											</c:forEach>
											<c:forEach items="${entry.value}" var="tlotctrl2">
												<% 
													Tlotctrl tlotctrl3 = (Tlotctrl) pageContext.getAttribute("tlotctrl2");
												%>
												<input type="hidden" id="<%=tlotctrl3.getId().getLotno()%>_<%=tlotctrl3.getId().getBatchcode()%>" value="<%=tlotctrl3.getId().getLotno()%>_<%=TlotCtrlState.getMemo(tlotctrl3.getState())%>_<fmt:formatDate value="${tlotctrl2.starttime}" pattern="yyyy-MM-dd HH:mm:ss"/>_<fmt:formatDate value="${tlotctrl2.endbettime}" pattern="yyyy-MM-dd HH:mm:ss"/>_<fmt:formatDate value="${tlotctrl2.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>_<fmt:formatDate value="${tlotctrl2.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>_<%=tlotctrl3.getState().intValue()%>" />
											</c:forEach>
									</select></td>
									<td id="${entry.key}_open" align="center">
										<a href="javascript:openinfo('${entry.key}');">客服开奖</a>
										<a href="javascript:info('${entry.key}');">奖级信息</a>
									</td>
									<td id="${entry.key}_memo" title="<%=TlotCtrlState.getMemo(tlotctrl.getState())%>"><%=TlotCtrlState.getMemo(tlotctrl.getState())%></td>
									<td><%=EncashState.getMemo(tlotctrl.getEncashstate())%></td>
									<td id="${entry.key}_starttime" title="${tlotctrl.starttime}"><fmt:formatDate value="${tlotctrl.starttime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td id="${entry.key}_endbettime" title="${tlotctrl.endbettime}"><fmt:formatDate value="${tlotctrl.endbettime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td title="${tlotctrl.hemaiendtime}">
										<fmt:formatDate value="${tlotctrl.hemaiendtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
									</td>
									<td id="${entry.key}_endtime" title="${tlotctrl.endtime}"><fmt:formatDate value="${tlotctrl.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td id="${entry.key}_createtime" title="${tlotctrl.createtime}"><fmt:formatDate value="${tlotctrl.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
								</tr>
								<%	} %>
							</c:forEach>
								
						</tbody>
					</table></td>
			</tr>
		</tbody>
	</table>
	
	<div>
    <div style="width:500px; float:left;">
	<form action="<%=request.getContextPath()%>/ladyopen/savetrycode" method="post">
	<table  cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="3"><strong>试机号</strong></td>
			</tr>
			<tr>
				<td align="right">
					lotno:&nbsp;
				</td>
				<td colspan="2">
					<input name="lotno" type="text" style="width: 120px" class="inputText" onfocus="this.select();" value="F47103"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					batchcode:&nbsp;
				</td>
				<td colspan="2">
					<input name="batchcode" type="text" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.batchcode}"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					tryCode:&nbsp;
				</td>
				<td colspan="2">
					<input name="tryCode" type="text" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.tryCode}"/>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="submit" value=" 确定 " class="inputButton">
				</td>
			</tr>
			<tr><td colspan="3">&nbsp;</td></tr>
		</tbody>
	</table>
	</form>
	</div>
	
	<div style="width:500px; float:left;"><form action="<%=request.getContextPath()%>/ladyopen/chearinfo" method="post">
	<table cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="3"><strong>清空开奖详情</strong></td>
			</tr>
			<tr>
				<td align="right">
					lotno:&nbsp;
				</td>
				<td colspan="2">
					<input name="lotno1" type="text" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.lotno1}"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					batchcode:&nbsp;
				</td>
				<td colspan="2">
					<input name="batchcode1" type="text" style="width: 120px" class="inputText" onfocus="this.select();" value="${param.batchcode1}"/>
				</td>
			</tr>
			<tr>
				<td align="right">
					agencyno:&nbsp;
				</td>
				<td colspan="2">
					<input name="agencyno1" type="text" style="width: 120px" class="inputText" onfocus="this.select();" value="R00001"/>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="button" value=" 确定 " class="inputButton" onclick="fun()">
				</td>
			</tr>
			<tr><td colspan="3">&nbsp;</td></tr>
		</tbody>
	</table>
	</form>
	</div>
	</div>
</body>
</html>