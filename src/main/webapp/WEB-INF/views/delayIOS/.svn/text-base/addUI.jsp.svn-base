<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
	<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath()%>/fckeditor/_samples/sample.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<script language="JavaScript" type="text/javascript" src="<%=request.getContextPath()%>/js/My97DatePicker/WdatePicker.js"></script>
		
	</head>

	<script language="javascript">


	$(document).ready(function() {
		var msg = "${errormsg}";
		if(msg!=''&&msg!=null){
			alert(msg);
		}
	});
	
	function submint1(){
		if(!window.confirm("确定添加吗?")) {
			return;
		}
		document.forms[0].submit();
	}
	function submint2(){
		if(!window.confirm("确定为所有用户添加吗?")) {
			return;
		}
		document.forms[1].submit();
	}
	function submint3(){
		if(!window.confirm("确定为添加定时短信吗?")) {
			return;
		}
		document.forms[2].submit();
	}
</script>
	<body>
	
	<form style="width: 50%" action="<%=request.getContextPath()%>/delayIOS/delaysend" method="post" method="post" id="form1">
	<table width="50%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr>
					<td colspan="2" class="Main" align="center">
						<strong>给用户推送ios消息</strong>
					</td>
				</tr>
				<TR >
					<TD align="right">
						推送内容:
					</TD>
					<TD>
						<textarea rows="20" cols="50" name="text">${param.text}</textarea>
					</TD>
				</TR>
				<TR >
					<TD align="right">
						用户编号（多个“,”分隔）:
					</TD>
					<TD>
						<textarea rows="20" cols="50" name="usernos">${param.usernos}</textarea>
					</TD>
					</TR>
				<TR>
					<TD align="right" >
						发送时间:
					</TD>
					<TD >
						<input type="text" name="sendTime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" style="width:200px"/>
						<%-- <input type="text" name="sendTime" value="${param.sendTime}"  />YYYY-MM-DD HH:MM:SS --%>
					</TD>
				</TR>
				<tr>
					<td align="right"><input type="button" value="提交 " class="inputButton" onclick="submint1();"> </td>
				</tr>
		</tbody>
	</table>

</form>
	<br>
<hr>
<br>
	<form style="width: 50%" action="<%=request.getContextPath()%>/delayIOS/sendAll" method="post" method="post" id="form2">
	<table width="50%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr>
					<td colspan="2" class="Main" align="center">
						<strong>给所有用户创建定时IOS渠道推送</strong>
					</td>
				</tr>
				<TR >
					<TD align="right">
						推送内容:
					</TD>
					<TD>
						<textarea rows="20" cols="50" name="text1">${param.text1}</textarea>
					</TD>
				</TR>
				<tr>
					<TD align="right" >
						发送时间:
					</TD>
					<TD >
					<input type="text" name="sendTime1" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" style="width:200px"/>
						
						<%-- <input type="text" name="sendTime1" value="${param.sendTime1}"  />YYYY-MM-DD HH:MM:SS --%>
					</TD>
				</TR>
				<tr>
					<td align="right"><input type="button" value="提交 " class="inputButton" onclick="submint2();"> </td>
				</tr>
		</tbody>
	</table>
</form>


	<br>
<hr>
<br>
	<form style="width: 50%" action="<%=request.getContextPath()%>/delayIOS/createdelaysmsWithString" method="post" method="post" id="form3">
	<table width="50%" cellspacing="0" cellpadding="2" class="dataTable">
		<tbody>
			<tr>
					<td colspan="2" class="Main" align="center">
						<strong>创建定时短信</strong>
					</td>
				</tr>
				<TR >
					<TD align="right">
						发送内容:
					</TD>
					<TD>
						<textarea rows="20" cols="50" name="text2">${param.text2}</textarea>
					</TD>
				</TR>
				<TR >
					<TD align="right">
						电话号码（多个“,”分隔）:
					</TD>
					<TD>
						<textarea rows="20" cols="50" name="mobileIds">${param.mobileIds}</textarea>
					</TD>
					</TR>
				<tr>
					<TD align="right" >
						发送时间:
					</TD>
					<TD >
					<input type="text" name="sendTime2" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" class="Wdate" style="width:200px"/>
						
					
						<%-- <input type="text" name="sendTime2" value="${param.sendTime2}"  />YYYY-MM-DD HH:MM:SS --%>
					</TD>
				</TR>
				<tr>
					<td align="right"><input type="button" value="提交 " class="inputButton" onclick="submint3();"> </td>
				</tr>
		</tbody>
	</table>
</form>
	</body>
	
	
	</body>
</html>

