<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=utf-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta charset="utf-8">
	<base href="<%=basePath%>">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />

	<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
	<script type="text/javascript" src="js/jquery.js"></script>
<title>首页</title>

<script type="text/javascript">
	$(function() {
		var myDate = new Date;
		var year = myDate.getFullYear(); //获取当前年
		var mon = myDate.getMonth() + 1; //获取当前月
		var date = myDate.getDate();
		var week = myDate.getDay();
		var weeks = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];
		console.log(year, mon, date, weeks[week]);
		$("#time").html(year + "年" + mon + "月" + date + "日" + weeks[week]);
	})
</script>
<script type="text/javascript">
	function get10(i) {
		if (i < 10) {
			i = "0" + i
		};
		return i
	}
	setInterval(function() {
		var d = new Date()
		var t = d.getHours()
		var m = d.getMinutes()
		var s = d.getSeconds()
		$(".time").html(get10(t) + ":" + get10(m) + " <font color='#808080' style='font-size:32px'>" + get10(s) + "</font>")
	}, 100)
</script>
</head>
<body>
<div class="page-container">
	<p class="f-20 text-success" style="margin-top: 80px;margin-left: 300px;font-size:25px;font-weight:normal;">欢迎使用狐狸影院后台管理系统</p>
	<span id="time" style="top: 234px;left: 290px;font-size: 20px;"></span><div class="time" style="top: 220px;left: 540px"></div>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 

</body>
</html>