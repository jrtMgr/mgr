<%@page import="com.ruyicai.mgr.domain.Tlot"%>
<%@page import="com.ruyicai.mgr.domain.Tlotmap"%>
<%@page import="com.ruyicai.mgr.domain.TlotmapPK"%>
<%@page import="com.ruyicai.mgr.domain.Twininfo"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="java.util.Map" %>
<%@page import="java.util.Map.Entry" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Dialog.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/map.js"></script>
<script type="text/javascript">	
<%String errormsg = (String) request.getAttribute("errormsg");
			String result = (String) request.getAttribute("result");
			if (!StringUtil.isEmpty(errormsg)) {%>
function showerror() {
	Dialog.alert("<%=errormsg%>");
}
$(document).ready(function() {
	showerror();
});
<%}%>

Date.prototype.format = function(format)
{
    var o =
    {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(),    //day
        "h+" : this.getHours(),   //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3),  //quarter
        "S" : this.getMilliseconds() //millisecond
    }
    if(/(y+)/.test(format))
    format=format.replace(RegExp.$1,(this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
    if(new RegExp("("+ k +")").test(format))
    format = format.replace(RegExp.$1,RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
    return format;
}
</script>	
</head>
<body>
	<table width="100%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead" >
				<td width="1%" height="30" class="thOver"></td>
				<td width="10%" height="30" class="thOver"><strong>流水号</strong>
				</td>
				<td width="6%" height="30" class="thOver"><strong>投票状态</strong>
				</td>
				<td width="5%" height="30" class="thOver"><strong>中奖状态</strong>
				</td>
				<td width="4%" height="30" class="thOver"><strong>状态</strong>
				</td>
			</tr>
			<c:forEach items="${tlotmaps}" var="tlotmap">
			<%
				Tlotmap tlotmap = (Tlotmap)pageContext.getAttribute("tlotmap");
				int typebet = tlotmap.getBetflag();
				String typebetStr = "";
				if(typebet==0){
					typebetStr = "失败";
				}
				if(typebet==1){
					typebetStr = "成功";
				}
				if(typebet==2){
					typebetStr = "未通知";
				}
				int flag = tlotmap.getWinflag();
				String winFlagStr = "";
				if(flag==0){
					winFlagStr = "失败";
				}
				if(flag==1){
					winFlagStr = "成功";
				}
				if(flag==2){
					winFlagStr = "未通知";
				}
				int state = tlotmap.getState();
				String stateStr = "";
				if(state==0){
					stateStr = "处理中";
				}
			%>
				<tr><td>*</td>
					<td title="${tlotmap.id.agencyflowno}">
					${tlotmap.id.agencyflowno}
					</td>
					<td title="<%=typebetStr%>">
					<%=typebetStr%>
					</td><td title="<%=winFlagStr%>">
					<%=winFlagStr%>
					</td><td title="<%=stateStr%>">
					<%=stateStr%>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>