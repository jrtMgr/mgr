<%@page import="com.ruyicai.mgr.domain.Tloguser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="include.jsp" /> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${cxt}/styles/default.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
var Tab = {};

Tab.onTabMouseOver = function(ele){
	if(ele.className=="divtab"){
		ele.className="divtabHover";
	}
}

Tab.onTabMouseOut = function(ele){
	if(ele.className=="divtabHover"){
		ele.className="divtab";
	}
}
function $(ele) {
  if (typeof(ele) == 'string'){
    ele = document.getElementById(ele)
    if(!ele){
  		return null;
    }
  }
  return ele;
}
function $T(tagName,ele){
	ele = $(ele);
	ele = ele || document;
	var ts = ele.getElementsByTagName(tagName);//此处返回的不是数组
	var arr = [];
	var len = ts.length;
	for(var i=0;i<len;i++){
		arr.push($(ts[i]));
	}
	return arr;
}
Tab.onTabClick = function(ele){
	var arr = $T("div",ele.parentElement);
	var len = arr.length;
	for(var i=0;i<len;i++){
		var c = arr[i];
		var cn = c.className;
		if(cn=="divtabCurrent"){
			c.className = "divtab";
			c.style.zIndex=""+(len-i)+"";
		}
	}
	ele.className="divtabCurrent";
	ele.style.zIndex="33";
}
var Application = {};
Application.onChildMenuClick = function(ele,flag){//flag仅在回退/前进时置为true
	if(!flag){
		var url = ele.getAttribute("url");
		$("_MainArea").src = url;
	}
	Tab.onTabClick(ele);
}

Application.onChildMenuMouseOver = function(ele){
	Tab.onTabMouseOver(ele);
}

Application.onChildMenuMouseOut = function(ele){
	Tab.onTabMouseOut(ele);
}
</script>
<title>金软瑞彩运营管理系统</title>
</head>
<body style="min-width:1003px">
	<table id="_TableHeader" width="100%" border="0" cellpadding="0"
		cellspacing="0" class="bluebg"
		style="background:#3388bb url(images/vistaBlue.jpg) repeat-x left top;">
		<tr>
			<td height="70" valign="bottom">
			<table height="70" border="0" cellpadding="0" cellspacing="0"
				style="position:relative;">
				<tr>
					<td style="padding:0"><img src="images/logo.png"></td>
				</tr>
			</table>
			</td>
			<td valign="bottom">
			<div style="text-align:right; margin:0 20px 15px 0;">当前用户：<b>admin</b>
			&nbsp;[&nbsp;<a href="javascript:void(0);"
				onClick="Application.logout();">退出登录</a> | <a
				href="javascript:Application.changePassword(0);">修改密码</a> ]</div>
			</td>
		</tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		<tr>
			<td style="padding:6px 3px 3px 3px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="2" height="2"
						style="background:url(images/jiao.gif) no-repeat right bottom;"></td>

					<td width="100%" id="_HMenutable" class="tabpageBar"></td>
				</tr>
				<tr valign="top">
					<td align="right" id="_VMenutable" class="verticalTabpageBar">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td align="right" valign="bottom">
							<div id="_ChildMenu">
								<div url="Document/DocList.jsp" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtabCurrent" id="_ChildMenuItem_129"><b><span style="margin-left: 20px">用户信息查询</span></b></div>
								<div url="Document/WorkList.jsp" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab" id="_ChildMenuItem_303"><b><span style="margin-left: 20px">彩票信息查询</span></b></div>
								<div url="Document /MagazineDocList.jsp" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab" id="_ChildMenuItem_341"><b><span style="margin-left: 20px">期刊文档</span></b></div>
								<div url="Document/RecycleBin.jsp" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab" id="_ChildMenuItem_397"><b><span style="margin-left: 20px">文档回收站</span></b></div>
								<div url="Document/Notes.jsp" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab" id="_ChildMenuItem_131"><b><span style="margin-left: 20px">个人备忘</span></b></div>
								<div url="Document/Message.jsp" onmouseout="Application.onChildMenuMouseOut(this)" onmouseover="Application.onChildMenuMouseOver(this)" onclick="Application.onChildMenuClick(this)" class="divtab" id="_ChildMenuItem_132"><b><span style="margin-left: 20px">短消息</span></b></div></div>
							</div>
							</td>
						</tr>
					</table>
					</td>
					<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						id="maintable"
						style="border-bottom:#999999 1px solid; border-right:#999999 1px solid;">
						<tr>
							<td><iframe id='_MainArea' frameborder="0" width="100%"
								height="500" src='about:blank' scrolling="auto"></iframe></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</body>
</html>