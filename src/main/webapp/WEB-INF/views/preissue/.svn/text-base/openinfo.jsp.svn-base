<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.domain.Twininfo"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript" 
	src="<%=request.getContextPath()%>/js/jquery.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Dialog.js"></script>		
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
function confirmSave() {
	Dialog.confirm("彩种:" + $("#lotname").val() + ", 期号:" + $("#batchcode").val() + "<br/>基本号码:" +  $("#winbasecode").val() + "<br/>特殊号码:" + $("#winspecialcode").val() + "<br/>确定要保存吗?", function() {
		$("#form1").submit();
	}, function(){}, 300, 170);
	return false;
}
</script>	
</head>
<body>
<% 
	Twininfo wininfo = (Twininfo)request.getAttribute("twininfo");
	if(null != wininfo) {
		String info = wininfo.getInfo();
		String[] values = StringUtil.isEmpty(info) ? null : info.split("\\,");
		String[] xiaoliang = new String[] {"0", "0", "0", "0"};
		if(null != values && values.length > 0) {
			String[] xl = values[0].split("\\_");
			xiaoliang[0] = xl[0];
			xiaoliang[1] = xl[1];
			xiaoliang[2] = xl[2];
			if(xl.length > 3) {
				xiaoliang[3] = xl[3];
			}
		}
		String[] jiangden = new String[] {"1_0_0", "2_0_0", "3_0_0", "4_0_0", "5_0_0", "6_0_0", "7_0_0", "8_0_0", "9_0_0", "10_0_0"};
		if(null != values && values.length > 1) {
			String[] jd = values[1].split("\\;");
			jiangden = new String[jd.length];
			for(int i = 0; i < jd.length; i++) {
				jiangden[i] = jd[i];
			}
		}
		pageContext.setAttribute("xl", xiaoliang);
		pageContext.setAttribute("jd", jiangden);
		if("T01001".equals(wininfo.getId().getLotno())) {
			String[] fujia = new String[] {"1_0_0", "2_0_0", "3_0_0", "4_0_0", "5_0_0", "6_0_0", "7_0_0", "8_0_0", "9_0_0", "10_0_0"};
			if(null != values && values.length > 2) {
				String[] fj = values[2].split("\\;");
				for(int i = 0; i < fj.length; i++) {
					fujia[i] = fj[i];
				}
			}
			pageContext.setAttribute("fj", fujia);
		}
%>
<input type="hidden" id="lotname" value="<%=Lottype.getName(wininfo.getId().getLotno())%>">
<form action="<%=request.getContextPath()%>/tlotctrls/opensave" method="post" id="form1" onsubmit="return confirmSave();">
<input type="hidden" name="lotno" value="${twininfo.id.lotno}">
<input type="hidden" name="batchcode" id="batchcode" value="${twininfo.id.batchcode}">
<table width="100%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
		<tr class="dataTableHead">
				<td height="30" class="thOver" colspan="4"><strong>基本信息</strong>
				</td>
			</tr>
			<tr>
				<td align="right" width="10%">基本号码:</td>
				<td width="40%"><input name="winbasecode" id="winbasecode" type="text" style="width: 120px"
		class="inputText" onfocus="this.select();" value="${twininfo.winbasecode}" /></td>
				<td align="right" width="10%">特殊号码:</td>
				<td width="40%" align="left"><input name="winspecialcode" id="winspecialcode" type="text" style="width: 120px"
		class="inputText" onfocus="this.select();" value="${twininfo.winspecialcode}" /></td>
			</tr>
			<tr>
				<td align="right" width="10%">实际销售额:</td>
				<td width="40%"><input name="shiji" type="text" style="width: 120px" id="id"
		class="inputText" onfocus="this.select();" value="${xl[0]}" /></td>
				<td align="right" width="10%">有效销售额:</td>
				<td width="40%" align="left"><input name="youxiao" type="text" style="width: 120px" id="id"
		class="inputText" onfocus="this.select();" value="${xl[1]}" /></td>
			</tr>
			<tr>
				<td height="30" align="right">滚入下期:</td>
				<td><input name="gunru" type="text" style="width: 120px" id="id"
		class="inputText" onfocus="this.select();" value="${xl[2]}" /></td>
				<td align="right">总投注额:</td>
				<td align="left"><input name="touzhu" type="text" style="width: 120px" id="id"
		class="inputText" onfocus="this.select();" value="${xl[3]}" /></td>
			</tr>
		</tbody>
	</table>
	<table width="100%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="20%" height="30" class="thOver"><strong>奖等</strong>
				</td>
				<td width="40%" height="30" class="thOver"><strong>数量</strong>
				</td>
				<td width="40%" height="30" class="thOver"><strong>奖金</strong>
				</td>
			</tr>
			<c:forEach items="${jd}" var="jdone">
				<% 
					String one = (String)pageContext.getAttribute("jdone");
					String[] ones = one.split("_");
					pageContext.setAttribute("ones", ones);
				%>
				<tr>
					<td>${ones[0]}
					<input type="hidden" name="grade" value="${ones[0]}" />
					</td>
					<td>
						<input name="num" type="text" style="width: 120px" id="num"
		class="inputText" onfocus="this.select();" value="${ones[1]}" />
					</td>
					<td>
						<input name="money" type="text" style="width: 120px"
		class="inputText" onfocus="this.select();" value="${ones[2]}" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<% 
		if("T01001".equals(wininfo.getId().getLotno())) {
			
	%>
	<strong>附加信息:</strong>
	<table width="100%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="20%" height="30" class="thOver"><strong>奖等</strong>
				</td>
				<td width="40%" height="30" class="thOver"><strong>数量</strong>
				</td>
				<td width="40%" height="30" class="thOver"><strong>奖金</strong>
				</td>
			</tr>
			<c:forEach items="${fj}" var="fuone">
				<% 
					String one = (String)pageContext.getAttribute("fuone");
					String[] ones = one.split("_");
					pageContext.setAttribute("ones", ones);
				%>
				<tr>
					<td>${ones[0]}
					<input type="hidden" name="fugrade" value="${ones[0]}" />
					</td>
					<td>
						<input name="funum" type="text" style="width: 120px"
		class="inputText" onfocus="this.select();" value="${ones[1]}" />
					</td>
					<td>
						<input name="fumoney" type="text" style="width: 120px"
		class="inputText" onfocus="this.select();" value="${ones[2]}" />
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<% 
		}
	%>
	<table width="100%" cellspacing="0" cellpadding="2"
		style="margin-top: 10px">
		<tr>
			<td align="center"><input type="submit" value=" 保存 " 
								class="inputButton"></td>
		</tr>
		</table>
		</form>
	<%
	}
	%>
</body>
</html>