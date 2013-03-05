<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Card Manage</title>
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
</script>
	<script type="text/javascript">
			// 验证如意彩充值卡序列号
			function checkNo()
			{
				var endNo = document.getElementById("endno").value;		  // 结束序列号
				var amt = document.getElementById("amt").value;
				var noPattern = /^([1-9]{1}[0-9]*)$/;	
				var bigNo = document.getElementById("beginno").value; // 上批次生成最大卡序列号 
				if(endNo==null || endNo == "")
				{
					window.alert("请输入您要生成的个数!");
					document.getElementById("endno").focus();
					return false;
				}
				if(! noPattern.test(endNo))
				{
					window.alert("结束序列号必数字!");
					document.getElementById("endno").focus();
					return false;
				}	
				if(amt==null||amt==""){
					window.alert("请输入 充值卡面值!");
					document.getElementById("amt").focus();
					return false;
				}	
				if(	! noPattern.test(amt)){
					window.alert("充值卡面值必需是数字!");
					document.getElementById("amt").focus();
					return false;
				}
				if(amt<=0){
					window.alert(" 充值卡面值无效  、必须大于零!");
					document.getElementById("amt").focus();
					return false;
				}
				if(amt>50000){
					window.alert("充值卡面值无效 允许最大金额为50000");
					document.getElementById("amt").focus();
					return false;
				}			
				return true;				
			}
			// 验证充值卡结束日期
			function checkendTime()
			{
				var endTime = document.getElementById("endtime").value;
				if(endTime==null || endTime=="")
				{
					window.alert("请您选择充值卡结束日期!");
					document.getElementById("endtime").focus();
					return false;
				}
				return true;				
			}
			  // 验证结束日期是否晚于开始日期
			  function compareDate()
			  {
				  var beginDate = document.getElementById("nowtime").value;
				  var endDate = document.getElementById("endtime").value;
				  var bs = beginDate.split(/-/);
				  var es = endDate.split(/-/);
				  var bDate = new Date(bs[0],bs[1],bs[2]);
				  var eDate = new Date(es[0],es[1],es[2]);
				  if(eDate<=bDate)
				  {
						window.alert("充值卡失效日期必须晚于当前时间!");
						document.getElementById("endtime").focus();					  
						return false;
				  }
				  return true;
			  }			
			// 表单提交验证
			function checkAll()
			{
				return  checkNo() && checkendTime() && compareDate();
			}
			//定义 select 原值
			  var oldValue,oldText;
			  //select下拉框的onkeydown事件，修改下拉框的值
			  function catch_keydown(sel){
			   switch(event.keyCode) {
			    case 13: //回车键
			     event.returnValue = false;
			     break;
			    case 27: //Esc键
			     sel.options[sel.selectedIndex].text = oldText;
			     sel.options[sel.selectedIndex].value = oldValue;
			     event.returnValue = false;
			     break;
			    case 8:  //空格健
			     var s = sel.options[sel.selectedIndex].text;
			     s = s.substr(0,s.length-1);
			     if (sel.options[0].value==sel.options[sel.selectedIndex].text){
			      sel.options[sel.selectedIndex].value=s;
			      sel.options[sel.selectedIndex].text=s;
			     }
			     event.returnValue = false;
			     break;
			   }
			   if (!event.returnValue && sel.onchange)
			    sel.onchange(sel)
			  }

			  //select下拉框的onkeypress事件，修改下拉框的值
			  function catch_press(sel){
			   if(sel.selectedIndex>=0){
			    var s = sel.options[sel.selectedIndex].text + String.fromCharCode(event.keyCode);
			    if (sel.options[sel.selectedIndex].value==sel.options[sel.selectedIndex].text){
			     sel.options[sel.selectedIndex].value=s;
			     sel.options[sel.selectedIndex].text=s;
			    }
			    event.returnValue = false;
			    if (!event.returnValue && sel.onchange)
			     sel.onchange(sel)
			   }
			  }

			  //select下拉框的onfocus事件，保存下拉框原来的值
			  function catch_focus(sel) {
			   oldText = sel.options[sel.selectedIndex].value;
			   oldValue = sel.options[sel.selectedIndex].value;
			  }
		</script>
<%
String nowtime =  new SimpleDateFormat("yyyy-MM-dd").format(new Date());
String beginno = (String)request.getAttribute("beginno");
%>	

<script type="text/javascript">
	function createXMLHTTP() {
        var xmlhttp;
		if (window.XMLHttpRequest) {
			try {
				xmlhttp = new XMLHttpRequest();
				xmlhttp.overrideMimeType("text/html;charset=UTF-8");//设定以UTF-8编码识别数据
			} catch (e) {}
		} else if (window.ActiveXObject) {
			try {
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				try {
					xmlhttp = new ActiveXObject("Msxml2.XMLHttp");
				} catch (e) {
					try {
						xmlhttp = new ActiveXObject("Msxml3.XMLHttp");
					} catch (e) {}
				}
			}
		}
        return xmlhttp;
         }
		var http;
		
		
		function getSellNos()// 发送请求,获取可销售数量
		{
			var message = document.getElementById("message");
			if(message)
			{
				message.innerHTML= "数据正在加载...";
			}
			http = createXMLHTTP();
			var cardType = document.getElementById("type2").value; // 获取充值卡类型
			var cardFrom = document.getElementById("cardfrom2").value;// 获取充值卡来源
			var cardMoney = document.getElementById("amt2").value;// 获取充值卡面值
			var channel2 = document.getElementById("channel2").value;// 获取channel2		
			var url="<%=request.getContextPath()%>/tcard/getcansells?amt="+cardMoney+"&type="+cardType+"&cardfrom="+cardFrom +"&channel="+channel2;
			http.open("POST",url+"&"+ Math.random(),true);
			http.onreadystatechange=processMaxCardNoResponse;
			http.send(null);
		}
		// 处理响应
		function processMaxCardNoResponse()
		{
			if(http.readyState==4)
			{
				if(http.status==200)
				{
					var sellNo = document.getElementById("count");
					var json = eval('(' + http.responseText + ')');
					var error_code = json.error_code;
					var value = json.value;
					sellNo.value = value;
					//alert("json=" + json + "；error_code=" + error_code + "；value=" + value);
					var message = document.getElementById("message");
					if(message)
					{
						message.innerHTML= "";
					}
					
				}
			}
		}	
		// 验证销售数量
		function checkSellNumber()
		{
			var sellNumber = document.getElementById("sellamt").value;
			var noPattern = /^([1-9]{1}[0-9]*)$/;
			if(sellNumber==null || sellNumber=="")
			{
				window.alert("请您输入销售数量");
				document.getElementById("sellamt").focus();
				return false;
			}
			if(! noPattern.test(sellNumber))
			{
				window.alert("销售数量必须是大于0的整数");
				document.getElementById("sellamt").focus();
				return false;
			}
			var count = document.getElementById("count").value;
			if(sellNumber > count)
			{
				window.alert("销售数量必须小于等于可用数量");
				document.getElementById("sellamt").focus();
				return false;
			}
			var cardMoney=document.getElementById("amt2").value;
			if(cardMoney==null||cardMoney==""){
				window.alert("请您输入充值卡面值");
				document.getElementById("amt2").focus();
			}
			if(! noPattern.test(cardMoney)){
				window.alert("充值卡面值只能是数字且大于零");
				return false;
			}
			if(cardMoney>50000){
				window.alert("充值面额最大允许50000");
				return false;
			}
			return true;
		}
		
	</script>		
</head>
<body>
	<div style="margin-top: 10px;"></div>
	<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td
				style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
				<table width="100%" cellspacing="0" cellpadding="2"
					class="dataTable">
					<tbody>
						<tr class="dataTableHead">
							<td class="thOver"><strong>卡生成</strong></td>
							<td class="thOver"></td>
						</tr>
						<tr>
							<td style="padding: 2px 10px;">
								<form action="<%=request.getContextPath()%>/tcard/gencard"
									method="post" onsubmit="return checkAll();">								
									<input type="hidden" name="nowtime" value="<%=nowtime %>"/>
									<input type="hidden" name="agencyno" value="000001"/>
									<div style="width:100%;">
										充值卡面值: <!--<select style="width:120px;" name="amt"  id="amt" class="inputText"  onkeydown="catch_keydown(this)" onkeypress="catch_press(this)" onfocus="catch_focus(this)">
												      <option  value="2">2</option>
												      <option  value="3">3</option>
												        <option  value="10">10</option>
												        <option  value="20">20</option>
												        <option  value="30">30</option>
												        <option  value="50">50</option>
												        <option  value="100">100</option> 
												        <option  value="500">500</option>
												        <option  value="1000">1000</option>
												        <option  value="5000">5000</option>
												      </select>-->
												      <input name="amt" type="text" style="width: 120px" id="amt" class="inputText" />(单位:元)
									</div><br/>
									<div style="width:100%;">
									充值卡种类 : <select name="type" style="width:120px;" class="inputText" id="type">
										        <option  value="1">普通卡</option>
										        <option value="2">注册卡</option>
										        <option value="8">VIP卡</option>			        
										       </select>
									</div><br/>
									<div style="width:100%;">
									 充值卡来源:<select name="cardfrom" style="width:120px;" class="inputText" id="cardfrom">
										        <option value="0">招商银行积分</option>
										        <option value="1">工商银行积分</option>
										        <option value="2">建设银行积分</option>
										      </select>		
									</div><br/>
									<!--<div style="float: left;">
									 渠道编号:<select name="agencyno" style="width:120px;" class="inputText" >
										        <option selected="selected">000001</option>
											        <option>000002</option>
											        <option>000003</option>
											        <option>000004</option>
											        <option>000005</option>
											        <option>000006</option>
											        <option>000007</option>
											        <option>000008</option>
											        <option>000009</option>
											        <option>000010</option>
										      </select>		
									</div><br/>
									--><div style="width:100%;">
									 channel:<input name="channel" type="text" style="width: 120px" id="channel"
											class="inputText" />(一般情况下请填1)	
									</div><br/>
									<div style="width:100%;">
									 开始号码:<input name="beginno" type="text" style="width: 120px" id="beginno" value="<%=beginno%>" readonly="readonly"
											class="inputText" />	
									</div><br/>
									<div style="width:100%;">
										 个数:<input name="endno" type="text" style="width: 120px" id="endno"
											class="inputText" />(单位:个)
								   </div><br/>
								   <div style="width:100%;">
								   失效日期：<input name="endtime" type="text" style="width: 80px" id="endtime"
											class="inputText" onclick="DateTime.onImageMouseDown(event,'Calendar','endtime');"  />
											<img vspace="1" align="absmiddle" onmousedown="DateTime.onImageMouseDown(event,'Calendar','endtime');"
										style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
										src="<%=request.getContextPath()%>/images/Calendar.gif">
								   </div><br/>
								    <div style="width:100%;">
								    <input type="submit" value="确定生成" class="inputButton">
								    </div>	
								</form>
							</td>
							<td></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
	<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td
				style="padding-top: 2px; padding-left: 6px; padding-right: 6px; padding-bottom: 2px;">
				<table width="100%" cellspacing="0" cellpadding="2"
					class="dataTable">
					<tbody>
						<tr class="dataTableHead">
							<td class="thOver"><strong>卡销售</strong></td>
							<td class="thOver"></td>
						</tr>
						<tr>
							<td style="padding: 2px 10px;">
								<form action="<%=request.getContextPath()%>/tcard/sellcard"
									method="post">									
									<div style="width:100%;">
										充值卡面值: <!--<select style="width:120px;" name="amt2"  id="amt2" class="inputText"  onkeydown="catch_keydown(this)" onkeypress="catch_press(this)" onfocus="catch_focus(this)">
												      <option  value="2">2</option>
												      <option  value="3">3</option>
												        <option  value="10">10</option>
												        <option  value="20">20</option>
												        <option  value="30">30</option>
												        <option  value="50">50</option>
												        <option  value="100">100</option> 
												        <option  value="500">500</option>
												        <option  value="1000">1000</option>
												        <option  value="5000">5000</option>
												      </select>-->
												       <input name="amt2" type="text" style="width: 120px" id="amt2" class="inputText" />(单位:元)
									</div><br/>
									<div style="width:100%;">
									充值卡种类 : <select name="type2" style="width:120px;" class="inputText" id="type2">
										        <option  value="1">普通卡</option>
										        <option value="2">注册卡</option>
										        <option value="8">VIP卡</option>			        
										       </select>
									</div><br/>
									<div style="width:100%;">
									 充值卡来源:<select name="cardfrom2" style="width:120px;" class="inputText" id="cardfrom2">
										        <option value="0">招商银行积分</option>
										        <option value="1">工商银行积分</option>
										        <option value="2">建设银行积分</option>
										      </select>		
									</div><br/>									
									<div style="width:100%;">
									 channel:<input name="channel2" type="text" style="width: 120px" id="channel2"
											class="inputText" />(一般情况下请填1)	
									</div><br/>
									<div style="width:100%;">
									 可用数量:<input name="count" type="text" style="width: 120px" id="count" readonly="readonly"
											class="inputText" /><label><input type="button" value="查看" onclick="getSellNos();"/></label>
											  <span id="message"></span>     
		 
									</div><br/>
									<div style="width:100%;">
										 销售数量:<input name="sellamt" type="text" style="width: 120px" id="sellamt"
											class="inputText" />(单位:个)
								   </div><br/>								 
								    <div style="width:100%;">
								    <input type="submit" value="确定销售" class="inputButton"  onclick="return checkSellNumber();">
								    </div>	
								</form>
							</td>
							<td></td>
						</tr>
					</tbody>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>