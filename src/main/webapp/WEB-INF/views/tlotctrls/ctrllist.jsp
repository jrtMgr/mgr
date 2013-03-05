<%@page import="com.ruyicai.mgr.consts.EncashState"%>
<%@page import="com.ruyicai.mgr.consts.TlotCtrlState"%>
<%@page import="com.ruyicai.mgr.domain.Tlotctrl"%>
<%@page import="com.ruyicai.mgr.lot.LotCenter"%>
<%@page import="com.ruyicai.mgr.consts.LotState"%>
<%@page import="com.ruyicai.mgr.domain.Tlot"%>
<%@page import="com.ruyicai.mgr.lot.Lottype"%>
<%@page import="com.ruyicai.mgr.domain.Twininfo"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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

function prize(lotno,batchcode) {
	$.ajax({
		url : "<%=request.getContextPath() %>/tlotctrls/getWincode",
		type:"POST",
		data:"timestamt=" + new Date().getMilliseconds() + "&lotno=" + lotno + "&batchcode="+batchcode,
		success:function(data){
			if(data.errorCode == "0") {
				 if (window.confirm("确定要重新算奖?开奖号码是："+data.value)) {
						$.ajax({
							url : "<%=request.getContextPath() %>/tlotctrls/prize",
							type:"POST",
							data:"timestamt=" + new Date().getMilliseconds() + "&lotno=" + lotno + "&batchcode="+batchcode+"&wincode="+data.value,
							success:function(data){
								if(data.errorCode == "0") {
									Dialog.alert("已请求重新算奖,请等待");
								}else{
									Dialog.alert("重新算奖请求发生异常");
								}
							}
						}); 
					 }
			}else if(data.errorCode == "700002"){
				Dialog.alert("开奖信息不存在");
			}else{
				Dialog.alert("取开奖号码发生异常");
			}
		}
	}); 
	
	
}
</script>	
</head>
<body>
	<table width="100%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr class="dataTableHead">
				<td width="3%" height="30" class="thOver"></td>
				<td width="6%" height="30" class="thOver"><strong>彩种</strong>
				</td>
				<td width="6%" height="30" class="thOver"><strong>名称</strong>
				</td>
				<td width="7%" height="30" class="thOver"><strong>期号</strong>
				</td>
				<td width="12%" height="30" class="thOver"><strong>开始时间</strong>
				</td>
				<td width="12%" height="30" class="thOver"><strong>封期时间</strong>
				</td>
				<td width="8%" height="30" class="thOver"><strong>状态</strong>
				</td>
				<td width="8%" height="30" class="thOver"><strong>派奖状态</strong>
				</td>
				<td width="22%" height="30" class="thOver"><strong>操作</strong>
				</td>
			</tr>
			<c:forEach items="${tlotctrls}" var="tlotctrl" varStatus="num">
			<%
				Tlotctrl lotctrl = (Tlotctrl)pageContext.getAttribute("tlotctrl");
			String lottype = Lottype.getName(lotctrl.getId().getLotno());
			%>
				<tr>
					<td title="${num.count}">${num.count}</td>
					<td title="${tlotctrl.id.lotno}">
					${tlotctrl.id.lotno}
					</td>
					<td><%=lottype %>
					</td>
					<td title="${tlotctrl.id.batchcode}">${tlotctrl.id.batchcode}</td>
					<td>
						<fmt:formatDate value="${tlotctrl.starttime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<fmt:formatDate value="${tlotctrl.endtime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<%=TlotCtrlState.getMemo(lotctrl.getState()) %>
					</td>
					<td>
						 <%=EncashState.getMemo(lotctrl.getEncashstate()) %> 
					</td>
					<td>
						<a href="<%=request.getContextPath()%>/tlotctrls/editUI?lotno=${tlotctrl.id.lotno}&batchcode=${tlotctrl.id.batchcode}&agencyno=${tlotctrl.id.agencyno}">修改</a>| 
						<a href="<%=request.getContextPath()%>/tlotctrls/checkencashend?lotno=${tlotctrl.id.lotno}&batchcode=${tlotctrl.id.batchcode}">检查派奖结束</a> | 
						<a href="<%=request.getContextPath()%>/tlotctrls/checkprizeend?lotno=${tlotctrl.id.lotno}&batchcode=${tlotctrl.id.batchcode}">检查算奖结束</a> |
						<a href="javascript:prize('${tlotctrl.id.lotno}','${tlotctrl.id.batchcode}')">重新算奖</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>