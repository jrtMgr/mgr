<%@page import="com.ruyicai.mgr.consts.CashDetailState"%>
<%@page import="com.ruyicai.mgr.domain.Tcashdetail"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="com.ruyicai.mgr.util.Page"%>
<%@page import="java.math.BigDecimal"%>
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
function findbyid(id){
	fea_edit = "width=500,height=300,left=250,top=200,resizable=0,scrollbars=0,dependent=yes"
	window.open("<%=request.getContextPath()%>/tcash/findCashById?id="+id,"yhgl_edit",fea_edit);
}
function findbyid2(id){
	fea_edit = "width=500,height=300,left=250,top=200,resizable=0,scrollbars=0,dependent=yes"
	window.open("<%=request.getContextPath()%>/tcash/findCashById2?id="+id,"yhgl_edit",fea_edit);
}

function approvalMore(){
	if( checkIDs()&&confirm('所选项将通过审核')){
		document.forms[1].action="<%=request.getContextPath()%>/tcash/appromore";
		document.forms[1].submit();   
	}
}

function cashMore(){
	if( checkIDs2()&&confirm('所选项将支付宝提现')){
		document.forms[1].action="<%=request.getContextPath()%>/tcash/cashMore";
		document.forms[1].target="_blank"; 
		document.forms[1].submit();   
	}
}
function checkIDs(){
	var checkValues = document.getElementsByName("checkboxname");
	var temp="";
	for(var i=0;i<checkValues.length;i++){
	   if(checkValues[i].checked){
	      temp+=checkValues[i].value;
	   }
	}
	if(temp.length==0){
	   alert("请选择!"); 
	   return false; 
	}else{
	   return true;
	}
}
function checkIDs2(){
	var checkValues = document.getElementsByName("checkboxname2");
	var temp="";
	for(var i=0;i<checkValues.length;i++){
	   if(checkValues[i].checked){
	      temp+=checkValues[i].value;
	   }
	}
	if(temp.length==0){
	   alert("请选择!"); 
	   return false; 
	}else{
	   return true;
	}
}


var selAll = document.getElementById("selAll");
function selectAll() {
  var obj = document.getElementsByName("checkboxname");
  var obj2 = document.getElementsByName("checkboxname2");
  
  if(document.getElementById("selAll").checked == false) {
  for(var i=0; i<obj.length; i++) {
    obj[i].checked=false;
  }}else {
  for(var i=0; i<obj.length; i++) {  
    obj[i].checked=true;}
  } 
  
  if(document.getElementById("selAll").checked == false) {
	  for(var i=0; i<obj2.length; i++) {
	    obj2[i].checked=false;
	  }}else {
	  for(var i=0; i<obj2.length; i++) {  
	    obj2[i].checked=true;}
	  } 
} 
</script>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tbody>
			<tr>
				<td style="padding: 2px 10px;">
					<form action="<%=request.getContextPath()%>/tcash/list"
						method="post">
						<div style="float: left;">
							<table width="100%" cellspacing="2" cellpadding="2" border="0">
								<tr>
									<td align="right">交易ID:</td>
									<td align="left"><input type="text" name="ttransactionid" value='${param.ttransactionid}'></input></td>
									<td align="right">用户编号:</td>
									<td align="left"><input type="text" name="userno" value="${param.userno}"></input></td>
									<td align="right">标识类别:</td>
									<td><select id="usertype" name="usertype" style="width:60px">
											<option value="n" >无</option>
											<option value="mobileid" <c:if test='${"mobileid" eq param.usertype}'>selected</c:if>>手机</option>
											<option value="userName" <c:if test='${"userName" eq param.usertype}'>selected</c:if>>用户名</option>
											<option value="email" <c:if test='${"email" eq param.usertype}'>selected</c:if>>邮箱</option>
									</select></td>
									<td align="right">用户标识:</td>
									<td><input name="userid" type="text" style="width: 110px" value="${param.userid}"									
										id="userid" class="inputText" onfocus="this.select();" /></td>
									<td align="right">用户系统:</td>
									<td><select name="subchannel" style="width:80px">
											<option value="00092493"  <c:if test='${"00092493" eq param.subchannel}'>selected</c:if>>00092493</option>
											<option value="00092494"  <c:if test='${"00092494" eq param.subchannel}'>selected</c:if>>00092494</option>
											<option value="00092490"  <c:if test='${"00092490" eq param.subchannel}'>selected</c:if>>00092490</option>
											<option value="00358433"  <c:if test='${"00358433" eq param.subchannel}'>selected</c:if>>00358433</option>
									</select></td>									
								</tr>
								<tr>
									<td align="right">提现时间:</td>
									<td><input id="starttime" name="starttime" value="${starttime}"
										class="inputText" type="text" style="width: 80px;"
										 onclick="DateTime.onImageMouseDown(event,'Calendar','starttime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','starttime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
										-&nbsp;<input id="endtime" name="endtime"  value="${param.endtime}"
										class="inputText" type="text" ztype="date"
										style="width: 80px;"
										onclick="DateTime.onImageMouseDown(event,'Calendar','endtime');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','endtime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
									</td>
									<td align="right">状态:</td>
									<td><select name="state" style="width:80px">
											<option value="">--请选择--</option>
											<option value="1" <c:if test="${param.state eq 1}">selected</c:if>>待解决</option>
											<option value="103" <c:if test="${param.state eq 103}">selected</c:if>>已审核</option>
											<option value="104" <c:if test="${param.state eq 104}">selected</c:if>>驳回</option>
											<option value="105" <c:if test="${param.state eq 105}">selected</c:if>>成功</option>
											<option value="106" <c:if test="${param.state eq 106}">selected</c:if>>提现取消</option>
									</select></td>
									
									<td align="right">类型:</td>
									<td><select name="type" style="width:80px">
											<option value="">全部</option>
											<option value="1" <c:if test="${param.type eq 1}">selected</c:if>>银行卡提现</option>
											<option value="2" <c:if test="${param.type eq 2}">selected</c:if>>支付宝提现</option>
									</select></td>
									<td align="right">显示行数:</td>
									<td><select id="maxResult" name="maxResult" style="width:60px">
											<option value="15" <c:if test='${"15" eq page.maxResult}'>selected</c:if>>15</option>
											<option value="30" <c:if test='${"30" eq page.maxResult}'>selected</c:if>>30</option>
											<option value="50" <c:if test='${"50" eq page.maxResult}'>selected</c:if>>50</option>
									</select></td>									
									<td>排序:</td>
									<td><select name="sort" style="width:80px" id="sort">
											<option value="1" <c:if test="${param.sort eq 1}">selected</c:if>>提现时间</option>
											<option value="2" <c:if test="${param.sort eq 2}">selected</c:if>>修改时间</option>											
									</select></td>
								</tr>
								<tr>
								 <td align="right">提现金额:</td>
									<td><input name="amt1" type="text" style="width: 80px" value="${param.amt1}"									
										id="amt1" class="inputText" onfocus="this.select();" />&nbsp;-&nbsp;<input name="amt2" type="text" style="width: 80px" value="${param.amt2}"									
										id="amt2" class="inputText" onfocus="this.select();" /></td>
									<td align="right">最后修改时间:</td>
									<td><input id="starttime2" name="starttime2" value="${param.starttime2}"
										class="inputText" type="text" style="width: 80px;"
										 onclick="DateTime.onImageMouseDown(event,'Calendar','starttime2');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','starttime2');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
										-&nbsp;<input id="endtime2" name="endtime2"  value="${param.endtime2}"
										class="inputText" type="text" ztype="date"
										style="width: 80px;"
										onclick="DateTime.onImageMouseDown(event,'Calendar','endtime2');">
										<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','endtime2');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
									</td>
									<td align="right">账号:</td>
									<td align="center"><input type="text" name="bankaccount" value='${param.bankaccount}'></input>&nbsp;&nbsp;<input type="submit" value="查询" class="inputButton"></td>	
								</tr>
							</table>
						</div>
					</form>
				</td>
			</tr>
			<tr>
				<td
					style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
					<table width="110%" cellspacing="0" cellpadding="2"
						class="dataTable">
						<tbody>
							<tr class="dataTableHead">
								<td width="2%" height="30" class="thOver"></td>
								<td width="4%" class="thOver" title="用户编号"><strong>用户编号</strong></td>
								<td width="6%" class="thOver" title="提现ID"><strong>提现ID</strong></td>
								<td width="6%" class="thOver" title="批次号"><strong>批次号</strong></td>
								<td width="12%" class="thOver" title="交易ID"><strong>交易ID</strong></td>
								<td width="4%" class="thOver" title="用户名"><strong>用户名</strong></td>
								<td width="5%" class="thOver" title="提现金额"><strong>提现金额</strong></td>
								<td width="3%" class="thOver" title="手续费"><strong>手续费</strong></td>
								<td width="5%" class="thOver" title="提现时间"><strong>提现时间</strong></td>
								<td width="5%" class="thOver" title="最后时间"><strong>最后时间</strong></td>
								<td width="8%" class="thOver" title="开户名称"><strong>开户名称</strong></td>
								<td width="14%" class="thOver" title="银行账号"><strong>银行账号</strong></td>
								<td width="4%" class="thOver" title="状态"><strong>状态</strong></td>
								<td width="6%" class="thOver" title="类型"><strong>类型</strong></td>
								<td width="8%" class="thOver" title="操作"><strong>操作</strong></td>
								<td width="8%" class="thOver" title="驳回原因"><strong>驳回原因</strong></td>
							</tr>
							<% 
								Page<Tcashdetail> page2 = (Page<Tcashdetail>)request.getAttribute("page");
								if(null != page2 && null != page2.getList()) {
									
								
								Object[] objs = (Object[])page2.getValue();
								BigDecimal sumamt = BigDecimal.ZERO;
								BigDecimal sumprizeamt = BigDecimal.ZERO;
								
							%>
								<form method="post">
								<c:forEach items="${page.list}" var="tcashdetail" varStatus="num">
									<% 
									Tcashdetail tcashdetail = (Tcashdetail)pageContext.getAttribute("tcashdetail");
									BigDecimal stat = tcashdetail.getState();
									String state = CashDetailState.getMemo(stat);
									String type = "";
									int typeb = tcashdetail.getType().intValue();
									if(typeb == 1){
										type = "银行卡提现";
									}else if(typeb == 2){
										type = "支付宝提现";
									}
									sumamt = sumamt.add(tcashdetail.getAmt());
									sumprizeamt = sumprizeamt.add(tcashdetail.getFee());
								%>
								
									<tr>
										<td title="${num.count}">${num.count}</td>
										<td><a href="<%=request.getContextPath()%>/tuserinfoes/list?userno=${tcashdetail.userno}">${tcashdetail.userno}</a></td>
										<td title="${tcashdetail.id}"><a href="<%=request.getContextPath()%>/tuserinfoes/talibatchpaylist?id=${tcashdetail.batchno}">${tcashdetail.id}</a></td>
										<td title="${tcashdetail.batchno}">${tcashdetail.batchno}</td>
										<td title="${tcashdetail.ttransactionid}">${tcashdetail.ttransactionid}</td>
										<td title="${tcashdetail.name}">${tcashdetail.name}</td>
										<td title="${tcashdetail.amt}">${tcashdetail.amt}</td>
										<td title="${tcashdetail.fee}">${tcashdetail.fee}</td>
										<td title="${tcashdetail.plattime}"><fmt:formatDate value="${tcashdetail.plattime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td title="${tcashdetail.modifytime}"><fmt:formatDate value="${tcashdetail.modifytime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td title="${tcashdetail.bankname}">${tcashdetail.bankname}</td>
										<td title="${tcashdetail.bankaccount}">${tcashdetail.bankaccount}</td>
										<td title="<%=state%>"><%=state%></td>
										<td title="<%=type%>"><%=type%></td>
										<td>
										<%if(stat.equals(CashDetailState.Tixian.value()) ){ %>
											<a onclick="findbyid('${tcashdetail.id}')" style="cursor: hand;">编辑</a>
											
											<input name="checkboxname" type="checkbox"  value="${tcashdetail.id}"/>
										<%}  else if(stat.equals(CashDetailState.Yishenghe.value()) && typeb == 2){%>
										<a href="<%=request.getContextPath()%>/tcash/batchpay?id=${tcashdetail.id}" style="cursor: hand;" target="_blank">提现</a>
										<input name="checkboxname2" type="checkbox"  value="${tcashdetail.id}"/>
										<% }
										if(stat.equals(CashDetailState.Yishenghe.value())){%>
										<a onclick="findbyid2('${tcashdetail.id}')" style="cursor: hand;">驳回</a>
										<% }%>
										</td>
										<td title="${tcashdetail.rejectReason}">${tcashdetail.rejectReason}</td>
									</tr>
								</c:forEach>
								</form>
								<tr>
								<td colspan="9">&nbsp;</td>
								<td colspan="4">
									<c:if test="${state eq 1}"><input type="button" value="财务通过审核" onClick="javascript:approvalMore()"/></c:if>							
									<c:if test="${(state eq 103) and (param.type eq 2)}"><input type="button" value="支付宝提现" onClick="javascript:cashMore()" /></c:if>
								</td>
								<td><input type="checkbox" id="selAll" onclick="selectAll();" />全选</td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td>本页合计</td>
								<td></td>
								<td></td>
								<td title="<%=sumamt%>"><%=sumamt%></td>
								<td title="<%=sumprizeamt%>"><%=sumprizeamt%></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td>总合计</td>
								<td></td>
								<td></td>
								<td title="<%=null == objs[0] ? 0 : objs[0]%>"><%=null == objs[0] ? 0 : objs[0]%></td>
								<td title="<%=null == objs[1] ? 0 : objs[1]%>"><%=null == objs[1] ? 0 : objs[1]%></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="left" id="dg1_PageBar" colspan="11"><div
										style="float: right; font-family: Tahoma">
										<c:choose>
											<c:when test="${page.pageIndex != 0}">
												<a
													href="<%=request.getContextPath()%>/tcash/list?userno=${userno}&userid=${userid}&usertype=${usertype}&starttime=${param.starttime}&endtime=${param.endtime}&starttime2=${param.starttime2}&endtime2=${param.endtime2}&amt1=${param.amt1}&amt2=${param.amt2}&state=${param.state}&type=${param.type}&sort=${param.sort}&maxResult=${page.maxResult}&pageIndex=0">第一页</a>
											</c:when>
											<c:otherwise>
										第一页
									</c:otherwise>
										</c:choose>
										|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex > 0}">
												<a
													href="<%=request.getContextPath()%>/tcash/list?userno=${userno}&userid=${userid}&usertype=${usertype}&starttime=${param.starttime}&endtime=${param.endtime}&starttime2=${param.starttime2}&endtime2=${param.endtime2}&amt1=${param.amt1}&amt2=${param.amt2}&state=${param.state}&type=${param.type}&sort=${param.sort}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex - 1}">上一页</a>
											</c:when>
											<c:otherwise>
										上一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 < page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tcash/list?userno=${userno}&userid=${userid}&usertype=${usertype}&starttime=${param.starttime}&endtime=${param.endtime}&starttime2=${param.starttime2}&endtime2=${param.endtime2}&amt1=${param.amt1}&amt2=${param.amt2}&state=${param.state}&type=${param.type}&sort=${param.sort}&maxResult=${page.maxResult}&pageIndex=${page.pageIndex + 1}">下一页</a>
											</c:when>
											<c:otherwise>
										下一页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp;
										<c:choose>
											<c:when test="${page.pageIndex + 1 != page.totalPage}">
												<a
													href="<%=request.getContextPath()%>/tcash/list?userno=${userno}&userid=${userid}&usertype=${usertype}&starttime=${param.starttime}&endtime=${param.endtime}&starttime2=${param.starttime2}&endtime2=${param.endtime2}&amt1=${param.amt1}&amt2=${param.amt2}&state=${param.state}&type=${param.type}&sort=${param.sort}&maxResult=${page.maxResult}&pageIndex=${page.totalPage - 1}">最末页</a>
											</c:when>
											<c:otherwise>
										最末页
									</c:otherwise>
										</c:choose>
										&nbsp;|&nbsp; &nbsp;&nbsp;转到第&nbsp;<input type="text"
											onkeyup="value=value.replace(/\D/g,'')" style="width: 40px"
											class="inputText" id="_PageBar_Index">&nbsp;页&nbsp;&nbsp;<input
											type="button" value="跳转" class="inputButton" onclick="go()">
										<script type="text/javascript">
											function go() {
												var pageindex = parseInt($("#_PageBar_Index").val()) - 1;
												window.location.href="<%=request.getContextPath()%>/tcash/list?userno=${userno}&starttime=${param.starttime}&endtime=${param.endtime}&starttime2=${param.starttime2}&endtime2=${param.endtime2}&amt1=${param.amt1}&amt2=${param.amt2}&state=${param.state}&type=${param.type}&sort=${param.sort}&maxResult=${page.maxResult}&pageIndex=" + pageindex;
											}
										</script>
									</div>
									<div style="float: left; font-family: Tahoma">共
										${page.totalResult} 条记录，每页 ${page.maxResult} 条，当前第	${page.pageIndex + 1} / ${page.totalPage} 页</div></td>
							</tr>
							<tr>
								<td colspan="9" align="center"><a style="cursor:hand; font-size: 24px;" href="<%=request.getContextPath()%>/tcash/generateReport?userno=${userno}&userid=${userid}&usertype=${usertype}&starttime=${param.starttime}&endtime=${param.endtime}&starttime2=${param.starttime2}&endtime2=${param.endtime2}&amt1=${param.amt1}&amt2=${param.amt2}&state=${param.state}&type=${param.type}&sort=${param.sort}">生成excel</a></td>
							</tr>
						</tbody>
						<%} %>
					</table>
				</td>
			</tr>
		</tbody>
	</table>
</body>
</html>