<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
	<head>
		<title>新增资讯</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="<%=request.getContextPath()%>/fckeditor/_samples/sample.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/fckeditor/fckeditor.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
	</head>

	<script language="javascript">


	//初始化fckeditor
	$(document).ready(function() {
		var msg = "${errormsg}";
		if(msg!=''&&msg!=null){
			alert(msg);
		}
		var oFCKeditor2 = new FCKeditor( 'content' ) ;
		oFCKeditor2.BasePath = "<%=request.getContextPath()%>/fckeditor/" ;
		oFCKeditor2.Height="400";
		oFCKeditor2.ReplaceTextarea() ;

		$("#btnOk").click(function(){
			if($('#title').val()==''){
				alert('标题不能为空！');
				$('#title').focus();
				return;
			}
			document.forms[0].submit();
		});
	});
</script>
	<body>
		<form action="<%=request.getContextPath()%>/news/add" method="post">
			<table class="List" width="100%">
				<tr>
					<td colspan="8" class="Main" align="center">
						<strong>编辑资讯</strong>
					</td>
				</tr>
				<TR >
					<TD class="Title" width="8%" align="right">
						标题长度:
					</TD>
					<TD width="42%" class="Content">
						<input type="text" name="a" size="53" disabled="disabled" value="一二三四五六七八九十一二三四五六七八九十一二三四" class="sInput"/>
					</TD>
					<TD class="Title" width="8%" align="right">
						资讯类别:
					</TD>
					<TD width="42%" class="Content">
					<span class="type">
						<select name="categoryId">
							<c:forEach items="${categoryList}" var="ca">
								<option value="${ca.id }" <c:if test="${ca.id == param.categoryId }">selected</c:if>>${ca.categoryName }</option>
							</c:forEach>
						</select>
						</span>
					</TD>
				</TR>
				<TR >
					<TD class="Title" width="8%" align="right">
						普通标题:
					</TD>
					<TD width="42%" class="Content">
						<input  type="text" name="title" id="title" size="53" class="sInput"/>
					</TD>
					<TD class="Title" width="8%" align="right">
						优先级:
					</TD>
					<TD width="42%" class="Content" >
						<select id="orderId" name="orderId" style="width:130px">
								<option value="0" >
									请选择
								</option>
								<option value="1">
									1
								</option>
								<option value="2">
									2
								</option>
								<option value="3">
									3
								</option>
								<option value="4">
									4
								</option>
								<option value="5">
									5
								</option>
								<option value="6">
									6
								</option>
								<option value="7">
									7
								</option>
								<option value="8" >
									8
								</option>
								<option value="9">
									9
								</option>
								<option value="100">
									最高
								</option>
						</select>
					</TD>
				</TR>
				<TR>
					<TD colspan="5" >
						<textarea rows="20" cols="180" name="content" ></textarea>
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

