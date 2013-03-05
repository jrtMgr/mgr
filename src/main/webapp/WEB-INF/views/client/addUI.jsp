<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>新增资讯</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath()%>/styles/default.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	</head>

	<script language="javascript">


	$(document).ready(function() {
		var msg = "${errormsg}";
		if(msg!=''&&msg!=null){
			alert(msg);
		}

		$("#btnOk").click(function(){
			document.forms[0].submit();
		});
	});
</script>
	<body>
		<form action="<%=request.getContextPath()%>/client/add" method="post">
			<table class="List" width="30%" border="1">
				<tr>
					<td colspan="2" class="Main" align="center">
						<strong>新增</strong>
					</td>
				</tr>
				<TR >
					<TD>
						coopname:
					</TD>
					<TD>
						<input type="text" name="coopname" value="${param.coopname}"  />
					</TD>
				</TR>
				<TR >
					<TD>
						coopid:
					</TD>
					<TD>
						<input type="text" name="coopid" value="${param.coopid}"  />
					</TD>
				<TR>
					<TD>
						rate:
					</TD>
					<TD >
						<input type="text" name="rate" value="${param.rate}"  />
					</TD>
				</TR>
				<TR>
					<TD>
						productno:
					</TD>
					<TD >
						<select id="productno" name="productno">
							<c:forEach items="${tproducttypes }" var="tproducttype">
								<option value="${tproducttype.productno }" <c:if test="${tproducttype.productno eq param.productno}">selected</c:if> >${tproducttype.productname}</option>
							</c:forEach>
						</select>
					</TD>
				</TR>
			</table>
			<div style="text-align: left; padding: 2px;">
				<input type=button id="btnOk" style="width: 40" class="button" value="确定">
				<input type=button  style="width: 40" class="button" value="返回" onclick="javascript:history.go(-1)">
			</div>
		</form>
	</body>
</html>

