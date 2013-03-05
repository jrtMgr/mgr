<%@page import="java.math.BigDecimal"%>
<%@page import="com.ruyicai.mgr.util.StringUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript">
function caitongtctransfer() {
	if(window.confirm("确定要划转吗?")) {
		return true;
	}
	return false;
}
</script>	
</head>

<body style="margin-left: 10px;">
	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="2" class="thOver"><strong>内蒙逻辑机账户余额</strong></td>
			</tr>
			<tr>
				<td width="150">15120001-15120010</td>
				<td>${b_15120001}</td>
			</tr>
			<tr>
				<td width="150">15120011-15120020</td>
				<td>${b_15120011}</td>
			</tr>
			<tr>
				<td width="150">15120021-15120030</td>
				<td>${b_15120021}</td>
			</tr>
		</tbody>
	</table>
	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="2" class="thOver"><strong>大赢家账户余额</strong></td>
			</tr>
			<tr>
				<td width="150">账户余额</td>
				<td>${dyj}</td>
			</tr>
		</tbody>
	</table>
		<%! 	
			String value;
			String[] values;
			public String transfer(String value) {
			try {
				return new BigDecimal(value).divide(new BigDecimal(100)).toString();
			} catch(Exception e) {}
			return value;
		}
		%>
	<%-- <table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="2" class="thOver"><strong>山东体彩账户余额</strong></td>
			</tr>
			<% 
					String value = (String)request.getAttribute("caitongtc");
					String[] values = value.split("\\_");
					if(values.length != 3) {
						values = new String[] {value, value, value};
					}
				%>
			
			<tr>
				<td width="150">账户余额</td>
				<td><%=transfer(values[0]) %></td>
			</tr>
			<tr>
				<td width="150">小奖账户金额</td>
				<td><%=transfer(values[1]) %>&nbsp;&nbsp;&nbsp;
					<form action="<%=request.getContextPath() %>/lotCenter/caitongtctransfer" onsubmit="return caitongtctransfer();">
						<input type="hidden" value="<%=values[1]%>" name="amt">
						<input type="submit" value="全部划转到账户余额"/>
					</form>
				</td>
			</tr>
			<tr>
				<td width="150">大奖账户金额</td>
				<td><%=transfer(values[2]) %></td>
			</tr>
		</tbody>
	</table> --%>
	
	<%-- <% 
					String huacai = (String)request.getAttribute("huacai");
				%>
	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="2" class="thOver"><strong>华彩账户余额</strong></td>
			</tr>
			<tr>
				<td width="150">账户余额</td>
				<td><%=transfer(huacai) %></td>
			</tr>
		</tbody>
	</table>
	--%>
	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="2" class="thOver"><strong>山东竞彩账户余额</strong></td>
			</tr>
			<% 
					value = (String)request.getAttribute("jingcai");
					values = value.split("\\_");
					if(values.length != 3) {
						values = new String[] {value, value, value};
					}
				%>
			<tr>
				<td width="150">账户余额</td>
				<td><%=transfer(values[0]) %></td>
			</tr>
			<tr>
				<td width="150">小奖账户金额</td>
				<td><%=transfer(values[1]) %>&nbsp;&nbsp;&nbsp;
					<form action="<%=request.getContextPath() %>/lotCenter/jingcaitransfer" onsubmit="return caitongtctransfer();">
						<input type="hidden" value="<%=values[1]%>" name="amt">
						<input type="submit" value="全部划转到账户余额"/>
					</form>
				</td>
			</tr>
			<tr>
				<td width="150">大奖账户金额</td>
				<td><%=transfer(values[2]) %></td>
			</tr>
		</tbody>
	</table> 
	
	 <% 
		String zhangzhongcai = (String)request.getAttribute("chongqingfucai");
		String tzje2 = "获取失败";
		String lj = "获取失败";
		if(!"null".equals(zhangzhongcai)){
			String[] zzy = zhangzhongcai.split("_");
			tzje2 = zzy[0];
			lj = zzy[1];
		}
	%>
	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="2" class="thOver"><strong>重庆福彩账户余额</strong></td>
			</tr>
			<tr>
				<td width="150">投注金额</td>
				<td><%=tzje2%></td>
			</tr>
			<tr>
				<td width="150">礼金</td>
				<td><%=lj%></td>
			</tr>
		</tbody>
	</table>
	 
	<%-- <% 
		String zhangzhongyitc = (String)request.getAttribute("zhangzhongyitc");
		String tzje = "获取失败";
		String jj = "获取失败";
		String yj = "获取失败";
		if(!"null".equals(zhangzhongyitc)){
			String[] zzy = zhangzhongyitc.split("_");
			tzje = transfer(zzy[2]);
			jj = transfer(zzy[3]);
			yj = transfer(zzy[4]);
		}
	%>
	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="2" class="thOver"><strong>掌中弈体彩账户余额</strong></td>
			</tr>
			<tr>
				<td width="150">投注金额</td>
				<td><%=tzje%></td>
			</tr>
			<tr>
				<td width="150">奖金</td>
				<td><%=jj%></td>
			</tr>
			<tr>
				<td width="150">佣金</td>
				<td><%=yj%></td>
			</tr>
		</tbody>
	</table> --%>
	
	<%-- <% 
		String fcby = (String)request.getAttribute("fcby");
		String bytz = "获取失败";
		String byjj = "获取失败";
		if(!"null".equals(fcby)){
			String[] zzy = fcby.split("_");
			bytz = transfer(zzy[0]);
			byjj = transfer(zzy[1]);
		}
	%>
	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="2" class="thOver"><strong>丰采博雅余额</strong></td>
			</tr>
			<tr>
				<td width="150">投注金额</td>
				<td><%=bytz%></td>
			</tr>
			<tr>
				<td width="150">奖金</td>
				<td><%=byjj%>
					<form action="<%=request.getContextPath() %>/lotCenter/transfer" onsubmit="return caitongtctransfer();">
						<input type="hidden" value="fcby" name="url">
						<input type="submit" value="全部划转到账户余额"/>
					</form>
				</td>
			</tr>
		</tbody>
	</table> --%>
	
	<%-- <% 
		String zlren = (String)request.getAttribute("zlren");
		String zltz = "获取失败";
		String zljj = "获取失败";
		if(!"null".equals(zlren)){
			String[] zzy = zlren.split("_");
			zltz = transfer(zzy[0]);
			zljj = transfer(zzy[1]);
		}
	%>
	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="2" class="thOver"><strong>直立人余额</strong></td>
			</tr>
			<tr>
				<td width="150">投注金额</td>
				<td><%=zltz%></td>
			</tr>
			<tr>
				<td width="150">奖金</td>
				<td><%=zljj%>
					<form action="<%=request.getContextPath() %>/lotCenter/transfer" onsubmit="return caitongtctransfer();">
						<input type="hidden" value="zlren" name="url">
						<input type="submit" value="全部划转到账户余额"/>
					</form>
				</td>
			</tr>
		</tbody>
	</table> --%>
	
	<% 
		String sdfcby = request.getAttribute("sdfcby") == null?"null":(String)request.getAttribute("sdfcby");
		System.out.println(sdfcby);
		String sdfcby1 = "获取失败";
		String sdfcby2 = "获取失败";
		if(!"null".equals(sdfcby)){
			String s[] = sdfcby.split("_");
			sdfcby1 = transfer(s[0]);
			sdfcby2 = transfer(s[1]);
		}
	%>
	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="2" class="thOver"><strong>山东丰彩博雅余额</strong></td>
			</tr>
			<tr>
				<td width="150">投注金额</td>
				<td><%=sdfcby1%></td>
			</tr>
			<tr>
				<td width="150">奖金</td>
				<td><%=sdfcby2%>
					<form action="<%=request.getContextPath() %>/lotCenter/transfer" onsubmit="return caitongtctransfer();">
						<input type="hidden" value="sdfcby" name="url">
						<input type="submit" value="全部划转到账户余额"/>
					</form>
				</td>
			</tr>
		</tbody>
	</table>
	
	
	
	
	
	
	<%-- <% 
		String yimingzhongtian = (String)request.getAttribute("yimingzhongtian");
		String ymzt = "获取失败";
		if(!"null".equals(yimingzhongtian)){
			ymzt = transfer(yimingzhongtian);
		}
	%>
	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="2" class="thOver"><strong>亿鸣中天余额</strong></td>
			</tr>
			<tr>
				<td width="150">金额</td>
				<td><%=ymzt%></td>
			</tr>
		</tbody>
	</table>
	 --%>
	<% 
		String by11c5 = (String)request.getAttribute("by11c5");
		String by11c5xs = "获取失败";
		if(!"null".equals(by11c5)){
			by11c5xs = transfer(by11c5);
		}
	%>
	<table width="60%" cellspacing="0" cellpadding="2" class="dataTable"
		style="margin-top: 10px">
		<tbody>
			<tr class="dataTableHead">
				<td width="" height="30" colspan="2" class="thOver"><strong>广东十一选五余额</strong></td>
			</tr>
			<tr>
				<td width="150">金额</td>
				<td><%=by11c5xs%></td>
			</tr>
		</tbody>
	</table>
	<br>
</body>
</html>