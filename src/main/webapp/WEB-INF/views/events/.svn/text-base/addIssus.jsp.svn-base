<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery.js"></script>
		<link href="<%=request.getContextPath()%>/styles/default.css" rel="stylesheet" type="text/css">
	</head>

	<script language="javascript">
	$(document).ready(function() {
		if(${!empty errormsg}){
			alert("${errormsg}");
		}
	});
	$(document).ready(function() {
		$("#btnOk").click(function(){
			var vdlId = $('#vdlId').val();
			if($('#vdlId').val()=='' && $('#any9Id').val()=='' && $('#games6Id').val()=='' && $('#games4Id').val()==''){
				alert("胜负彩、任9、六场半全场、四场进球不能全为空");
				$('#vdlId').focus();
				return;
			}
			var str = /^\d{7}$/;
			if(vdlId != ''){
				if(!str.test(vdlId)){
					alert("胜负彩期号应输入7位数字");
					$('#vdlId').focus();
					return;
				}
			}
			var any9Id = $('#any9Id').val();
			if(any9Id != ''){
				if(!str.test(any9Id)){
					alert("任9期号应输入7位数字");
					$('#any9Id').focus();
					return;
				}
			}
			var games6Id = $('#games6Id').val();
			if( games6Id != ''){
				if(!str.test(games6Id)){
					alert("6场半全场期号应输入7位数字");
					$('#games6Id').focus();
					return;
				}
			}
			var games4Id = $('#games4Id').val();
			if(games4Id != ''){
				if(!str.test(games4Id)){
					alert("四场进球期号应输入7位数字");
					$('#games4Id').focus();
					return;
				}
			}
			if(($('#vdlId').val()=='' && $('#any9Id').val()!='')||($('#vdlId').val()!='' && $('#any9Id').val()=='')||$('#vdlId').val()!=$('#any9Id').val()){
				alert("胜负彩和任9期号请填写一致");
				return;
			}
			
			document.addFrom.submit();
		});
	});

</script>
	<body>
		<form style="text-align: center;" name="addFrom" method="post" action="<%=request.getContextPath()%>/events/addIssus">
			<table width="28%" border="1px;">
				<TR >
					<TD  width="120px;" align="right" >
						玩法
					</TD>
					<TD >
						期号
					</TD>
				</TR>
				<TR >
					<TD align="right">
						胜负彩:
					</TD>
					<TD>
						<input  type="text" name="vdlId" id="vdlId" size="20" class="sInput" value="${iss.vdlId eq '' ? 1 : iss.vdlId + 1}" />
					</TD>
				</TR>
				<TR >
					<TD  align="right">
						任9:
					</TD>
					<TD>
						<input  type="text" name="any9Id" id="any9Id" size="20" class="sInput" value="${iss.any9Id eq '' ? 1 : iss.any9Id+1}" />
					</TD>
				</TR>
				<TR >
					<TD  align="right">
						六场半全场:
					</TD>
					<TD>
						<input  type="text" name="games6Id" id="games6Id" size="20" class="sInput" value="${iss.games6Id eq '' ? 1 : iss.games6Id +1}" />
					</TD>
				</TR>
				<TR >
					<TD  align="right">
						四场进球:
					</TD>
					<TD>
						<input  type="text" name="games4Id" id="games4Id" size="20" class="sInput" value="${iss.games4Id eq '' ? 1 : iss.games4Id+1}" />
					</TD>
				</TR>
				<TR >
					<TD  align="right">
						<input type="button" id="btnOk"  value="确定">
					</TD>
					<TD>
							<input type="button" onclick="javascript:history.go(-1);"  value="返回">
					</TD>
				</TR>
			</table>
		</form>
	</body>
</html>

