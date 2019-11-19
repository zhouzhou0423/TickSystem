﻿<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
	<base href="<%=basePath%>">
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<!--[if lt IE 9]>
	<script type="text/javascript" src="lib/html5shiv.js"></script>
	<script type="text/javascript" src="lib/respond.min.js"></script>
	<![endif]-->
	<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
	<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
	<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
	<!--[if IE 6]>
	<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
	<script>DD_belatedPNG.fix('*');</script>
	<![endif]-->
	<title>影片列表</title>
</head>
<body>
<div class="page-container">
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="batchAdd()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 批量添加</a> </span> <span class="r">共有数据：<strong>${fn:length(movieInfos)}</strong> 条</span> </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="40"><input name="" type="checkbox" value=""></th>
					<th width="60">ID</th>
					<th width="100">封面</th>
					<th width="100">影片名称</th>
					<th width="100">分类</th>
					<th width="150">片长</th>
					<th width="150">上映时间</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${movieInfos }" var="movie">
					<tr class="text-c">
						<td><input name="checkbox" id="checkbox" type="checkbox" value="${movie.movieid }"></td>
						<td>${movie.movieid }</td>
						<td><img style="width:auto;height:auto;max-width:80px;max-height:80px;" class="picture-thumb" src="${movie.movieimg }"></td>
						<td>${movie.moviename }</td>
						<td>${movie.typename }</td>
						<td class="text-c">${movie.timelimit }分钟</td>
						<td id="moviedate">${movie.releasetime }</td>
						<td class="td-manage"><a style="text-decoration:none" onClick="movie_add(this,${movie.movieid })" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">


/*图片-添加*/
function batchAdd(){
	var a=document.getElementsByName("checkbox");
	var ids = new Array();
	for ( var i = 0; i < a.length; i++) {
		if (a[i].checked) {
			ids[i] = a[i].value;
		}
	}
	$.ajax({
		type: 'POST',
		url: 'cinemaInfo/batchAdd',
		dataType: 'text',
		data:"ids="+ids,
		success: function(data){
			if("success"==data){
				layer.msg('已添加!', {
					icon: 6,
					time: 1000,
					end: function () {
						window.parent.location.reload();
					}
				});
			} else {
				layer.msg('添加失败!',{icon:5,time:1000});
			}
		}
	});
}

function movie_add(obj,id){
	layer.confirm('确认要添加吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'cinemaInfo/addMovie',
			data:"movieId="+id,
			dataType: 'text',
			success: function(data){
				if("success"==data){
					layer.msg('已添加!', {
						icon: 6,
						time: 1000,
						end: function () {
							window.parent.location.reload();
						}
					});
				} else {
					layer.msg('添加失败!',{icon:5,time:1000});
				}
			}
		});
	});
}
</script>
</body>
</html>