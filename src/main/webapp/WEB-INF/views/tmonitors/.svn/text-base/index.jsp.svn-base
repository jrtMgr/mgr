<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=request.getContextPath()%>/styles/default.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Dialog.js"></script>		
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/highcharts/highcharts.js"></script>
<script type="text/javascript">
	var chart;
	var arr = [];
	<c:forEach items="${list}" var="num">
	arr.push(${num});
	</c:forEach>
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
						text : '出票监控'
					},
					xAxis : {
						categories : ['1','2','3','4','5','6','7','8','9','10','11','12','13','14','15','16','17','18','19','20',
						              '21','22','23','24','25','26','27','28','29','30','31','32','33','34','35','36','37','38','39','40',
						              '41','42','43','44','45','46','47','48','49','50','51','52','53','54','55','56','57','58','59','60']
					},
					yAxis : {
						min:0,
						max:${max} > 30 ? ${max} + 10 : 30,
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
	
		window.setInterval(function() {
			$.ajax({
				url:"<%=request.getContextPath() %>/tmonitors/num",
				type:"POST",
				data:"timestamt=" + new Date().getMilliseconds(),
				success:function(data){
					if(data.errorCode == "0") {
						chart.series[0].setData(data.value);
						var max = 0;
						for(var i = 0; i < data.value.length;i ++) {
							if(data.value[i] > max) {
								max = data.value[i];
							}
						}
						if(max > chart.yAxis[0].getExtremes().max) {
							chart.yAxis[0].setExtremes(0, max + 10);
						}
					}
				}
			});
		}, 5000);
		window.setInterval(function() {
			$.ajax({
				url:"<%=request.getContextPath() %>/tmonitors/failnum",
				type:"POST",
				data:"timestamt=" + new Date().getMilliseconds(),
				success:function(data){
					if(data.errorCode == "0") {
						$("#failnum").html(data.value[0]);
						$("#torderFailnum1").html(data.value[1]);
						$("#torderFailnum2").html(data.value[2]);
					}
				}
			});
		}, 60000);
		function showinfo() {
			var d = new Dialog("失败票信息", "tmonitors/faillist", 1050, 450);
			d.show();
		}
		function showTorderinfo() {
			var d = new Dialog("失败订单信息", "tmonitors/torderFaillist", 1050, 450);
			d.show();
		}
</script>
</head>
<body style="margin: 0;padding: 0">
	<div style="margin: 0;padding: 0;font-size: 12px;margin-top: 10px;margin-bottom: 10px">
		&nbsp;&nbsp;今日失败票数量:&nbsp;<span id="failnum">${failnum[0]}</span>&nbsp;&nbsp;
		<input type="button" value=" 查看详细 " onclick="showinfo();" />
		
		&nbsp;&nbsp;&nbsp;&nbsp;失败订单数量:&nbsp;<span id="torderFailnum1">${failnum[1]}</span>&nbsp;&nbsp;
		<input type="button" value=" 查看详细 " onclick="showTorderinfo();" />
	</div>
	<div id="container" style="margin: 0;padding: 0;border: 1px solid #B1B1AD;"></div>
</body>
</html>
