<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
	var chart;
	var arr = [];
	<c:forEach items="${list}" var="num">
	arr.push(${num});
	</c:forEach>
	<%
		String type = (String)request.getAttribute("type");
	if("1".equals(type)) {
		%>
		var categorie = ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20',
			              '21','22','23','24'];
		var sub = "${date}";
		<%
	} else {
		%>
		var categorie = ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20',
			              '21','22','23','24','25','26','27','28','29','30','31'];
		var sub = "${month}";
		<%
	}
	%>
	var max = ${max};
	$(document).ready(
			function() {
				chart = new Highcharts.Chart(
						{
							chart : {
								renderTo : 'container',
								defaultSeriesType : 'line',
								width:window.innerWidth - 50,
								height:window.innerHeight - 50
							},
							title : {
								text : '实时监控'
							},
							subtitle : {
								text : sub + ' 销量监控'
							},
							xAxis : {
								categories : categorie
							},
							yAxis : {
								min:0,
								max:max + 50,
								title : {
									text : '数量'
								}
							},
							tooltip : {
								enabled : true,
								formatter : function() {
									return '<b>' + this.series.name
											+ '</b><br/>' + this.x + ': '
											+ this.y + '°C';
								}
							},
							plotOptions : {
								line : {
									dataLabels : {
										enabled : true
									},
									enableMouseTracking : false
								}
							},
							series : [
									{
										name : 'TLOT',
										data : arr
									} ]
						});

			});
	function changefun(obj) {
		if($(obj).val() == "1") {
			$("#datespan").show();
			$("#monthspan").hide();
		} else {
			$("#datespan").hide();
			$("#monthspan").show();
		}
	}
</script>
</head>
<body style="margin: 0;padding: 0;font-size: 12px;margin-left: 10px">
	<div style="margin: 0;padding: 0;">
	<form action="<%=request.getContextPath()%>/tmonitors/sell" method="post">
	类型：
		<select name="type" onchange="changefun(this);">
			<option value="1">天/时</option>
			<option value="2">月/天</option>
		</select>
		&nbsp;日期：
		<span id="datespan">
			<input id="date" name="date" class="inputText" type="text"
								style="width: 90px;"
								onclick="DateTime.onImageMouseDown(event,'Calendar','date');">
							<img vspace="1" align="absmiddle"
								style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
								src="<%=request.getContextPath()%>/images/Calendar.gif">
		</span>
		<span id="monthspan" style="display:none;">
			<input id="month" name="month" class="inputText" type="text"
								style="width: 90px;"
								>
							<img vspace="1" align="absmiddle"
								style="position: relative; left: -25px; margin-right: -20px; cursor: pointer;"
								src="<%=request.getContextPath()%>/images/Calendar.gif">
		</span>
		&nbsp;
		<input type="submit" value=" 查 看 " />
		</form>
	</div>
	<div id="container" style="margin: 0;padding: 0;border: 1px solid #B1B1AD"></div>
</body>
</html>
