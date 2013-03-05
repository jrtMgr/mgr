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
function check() {
	if(!window.confirm("确定要保存吗?")) {
		return false;
	}
	return true;
}
</script>
<body>
	<div style="margin-top: 10px;"></div>
	<form action="<%=request.getContextPath()%>/tactivities/addbatch" method="post">
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>			
			<tr>
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">				
					<table width="100%" cellspacing="0" cellpadding="2"
						class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="100%" height="30" class="thOver" colspan="4">活动添加</td>								
							</tr>
							<tr>
							    <td width="8%" align="right">彩种:</td>
							    <td width="42%" align="left"><select name="lotno" id="lotno" style="width:80px">
									    <option value="" <c:if test='${"" eq lotno}'>selected</c:if>>无</option>
									    <option value="F47102" <c:if test='${"F47102" eq lotno}'>selected</c:if>>七乐彩</option>
									    <option value="F47104" <c:if test='${"F47104" eq lotno}'>selected</c:if>>双色球</option>
									    <option value="T01001" <c:if test='${"T01001" eq lotno}'>selected</c:if>>超级大乐透</option>
									    <option value="T01009" <c:if test='${"T01009" eq lotno}'>selected</c:if>>七星彩</option>
								        </select></td>
							    <td width="8%" align="right">大渠道:</td>
							    <td width="42%" align="left"><select name="subchannel" id="subchannel" style="width:80px">
										<option value="00092493" <c:if test='${"00092493" eq subchannel}'>selected</c:if>>00092493</option>
								        </select></td>
							</tr>							
							<tr><td width="8%" align="right">渠道：</td>
							    <td width="92%" align="left" colspan="3">
							         <textarea rows="20" cols="50" style="width: 98%" name="channel" id="channel" class="inputText" onfocus="this.select();" >
							         ${channel}</textarea>（渠道间用英文逗号隔开，如：1,2,3）
							         </td></tr>			
							<tr>
							    <td width="8%" align="right">活动类型:</td>
							    <td width="42%" align="left"><select name="activitytype" id="activitytype" style="width:80px">
									    <option value="1" <c:if test='${"1" eq activitytype}'>selected</c:if>>充值返现</option>
									    <option value="2" <c:if test='${"2" eq activitytype}'>selected</c:if>>合买发单返奖</option>
									    <option value="3" <c:if test='${"3" eq activitytype}'>selected</c:if>>加奖</option>
									    <option value="4" <c:if test='${"4" eq activitytype}'>selected</c:if>>第一次充值赠送</option>
								        </select></td>
							    <td width="8%" align="right">状态:</td>
							    <td width="42%" align="left"><select name="state" style="width:80px">
											<option value="0" <c:if test='${"0" eq state}'>selected</c:if>>失效</option>
											<option value="1" <c:if test='${"1" eq state}'>selected</c:if>>有效</option>
									</select></td>
							</tr>
							<tr>
							    <td width="8%" align="right">活动金额表达式：</td>
							    <td width="92%" align="left" colspan="3"><textarea rows="20" cols="50" style="width: 98%;text-align:left;" name="amt" id="amt" class="inputText" onfocus="this.select();" >
							         ${amt}</textarea></td>
							</tr>
							<tr><td width="8%" align="right"></td>
							    <td width="42%" align="left"><input type="submit" value="保存" class="inputButton" onclick="return check();"></td>
							    <td width="8%" align="right"><a href="<%=request.getContextPath() %>/tactivities/list">返回</a></td>
							    <td width="42%" align="left"></td>
						    </tr>							
						</tbody>						
					</table>
				</td>
			</tr>
		</tbody>
	</table>
	</form>
</body>
</html>