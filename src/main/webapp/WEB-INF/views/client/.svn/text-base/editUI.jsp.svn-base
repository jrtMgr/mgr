<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>修改</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
	</head>

	<script language="javascript">


	//初始化fckeditor
	$(document).ready(function() {
		var msg = "${errormsg}";
		if(msg!=''&&msg!=null){
			alert(msg);
		}

		$("#btnOk").click(function(){
			if($('#coopname').val()==''){
				alert('coopname不能为空！');
				$('#coopname').focus();
				return;
			}
			document.forms[0].submit();
		});
	});
</script>
	<body>
		<form action="<%=request.getContextPath()%>/client/edit" method="post">
			<input id="id" name="id" type="hidden" value="${client.id}">
			<table class="List" width="30%" border="1">
				<tr>
					<td colspan="2" class="Main" align="center">
						<strong>编辑</strong>
					</td>
				</tr>
				<TR >
					<TD>
						coopname:
					</TD>
					<TD>
						<input type="text" name="coopname" value="${ client.coopname}"  />
					</TD>
				</TR>
				<TR >
					<TD>
						coopid:
					</TD>
					<TD>
						<input type="text" name="coopid" value="${ client.coopid}"  />
					</TD>
				<TR>
					<TD>
						rate:
					</TD>
					<TD >
						<input type="text" name="rate" value="${ client.rate}"  />
					</TD>
				</TR>
				<TR>
					<TD>
						productno:
					</TD>
					<TD >
						<select id="productno" name="productno">
							<c:forEach items="${tproducttypes }" var="tproducttype">
								<option value="${tproducttype.productno }" <c:if test="${tproducttype.productno eq client.productno}">selected</c:if> >${tproducttype.productname}</option>
							</c:forEach>
						</select>
					</TD>
				</TR>
			</table>
			<div style="text-align:left; padding: 2px;">
				<input type=button id="btnOk" style="width: 40" class="button" value="确定">
			</div>
		</form>
	</body>
</html>

