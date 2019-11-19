﻿<%@ page import="java.util.Date" %>
<!DOCTYPE html>
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 影院管理 <span class="c-gray en">&gt;</span> 影片列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<div class="text-c"> 日期范围：
		<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'logmax\')||\'%y-%M-%d\'}' })" id="logmin" class="input-text Wdate" style="width:120px;">
		-
		<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'logmin\')}',maxDate:'%y-%M-%d' })" id="logmax" class="input-text Wdate" style="width:120px;">
		<input type="text" name="" placeholder=" 影片名称" style="width:250px" class="input-text">
		<button name="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 搜影片</button>
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"> <a class="btn btn-primary radius" onclick="picture_add('添加影片','movie/showAddMoviePage')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加影片</a></span> <span class="r">共有数据：<strong>${fn:length(cinemaMoviesList)}</strong> 条</span> </div>
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
					<th width="60">发布状态</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cinemaMoviesList }" var="cinemainfomovie">
					<tr class="text-c">
						<td><input name="" type="checkbox" value="${cinemainfomovie.movieid }"></td>
						<td>${cinemainfomovie.movieid }</td>
						<td><img style="width:auto;height:auto;max-width:80px;max-height:80px;" class="picture-thumb" src="${cinemainfomovie.movieimg }"></td>
						<td>${cinemainfomovie.moviename }</td>
						<td>${cinemainfomovie.typename }</td>
						<td class="text-c">${cinemainfomovie.timelimit }分钟</td>
						<td id="moviedate">${cinemainfomovie.releasetime }</td>
						<input type="hidden" id="nowdate" value="<% out.print(new java.text.SimpleDateFormat("yyyyMMdd").format(new Date())); %>">
						<c:choose>
							<c:when test="${cinemainfomovie.cmstate==-1 }">
								<td class="td-status"><span class="label label-defaunt radiuss">已下架</span></td>
								<td class="td-manage"><a style="text-decoration:none" onClick="picture_start(this,${cinemainfomovie.movieid },${cinemainfomovie.releasetime })" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>  <a style="text-decoration:none" class="ml-5" onClick="picture_del(this,${cinemainfomovie.movieid }')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
							</c:when>
							<c:when test="${cinemainfomovie.cmstate==0 }">
								<td class="td-status"><span class="label label-success radius">已上架</span></td>
								<td class="td-manage"><a style="text-decoration:none" onClick="picture_stop(this,${cinemainfomovie.movieid })" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>  <a style="text-decoration:none" class="ml-5" onClick="picture_del(this,${cinemainfomovie.movieid })" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
							</c:when>
							<c:when test="${cinemainfomovie.cmstate==1 }">
								<td class="td-status"><span class="label label-success radius">预售中</span></td>
								<td class="td-manage"><a style="text-decoration:none" onClick="picture_prestart(this,${cinemainfomovie.movieid },${cinemainfomovie.releasetime })" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>  <a style="text-decoration:none" class="ml-5" onClick="picture_del(this,${cinemainfomovie.movieid })" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
							</c:when>
							<c:when test="${cinemainfomovie.cmstate==2 }">
								<td class="td-status"><span class="label label-success radius">待上架</span></td>
								<td class="td-manage"><a style="text-decoration:none" onClick="picture_start(this,${cinemainfomovie.movieid },${cinemainfomovie.releasetime })" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>  <a style="text-decoration:none" class="ml-5" onClick="picture_del(this,${cinemainfomovie.movieid })" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a></td>
							</c:when>
						</c:choose>
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
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,8]}// 制定列不参与排序
	]
});

/*图片-添加*/
function picture_add(title,url){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}


/*图片-下架*/
function picture_stop(obj,id){
	layer.confirm('确认要下架吗？',function(index){
        $.ajax({
            url:"movieManage/changeState",
            type:"POST",
            data:"movieid="+id+"&cmstate=-1",
            dataType:"text",
            success:function(data){
                if(data=="true"){
                    $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="picture_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
                    $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
                    $(obj).remove();
					layer.msg('已下架!', {
						icon: 5,
						time: 1000,
						end: function () {
							window.location.reload();
						}
					})
                }else if(data=="false"){
					layer.msg('下架失败!', {
						icon: 5,
						time: 1000,
						end:function(){
							window.location.reload();
						}
					});
                }
            }
        });
	});
}
/*预售变发布*/
function picture_prestart(obj,id,moviedate){
    var nowdate=$("#nowdate").val();
    if(moviedate>nowDate){
        layer.msg('该影片暂时仅支持预售!',{icon: 6,time:1000});
    }else {
        layer.confirm('确认要发布吗？', function (index) {
            $.ajax({
                url: "movieManage/changeState",
                type: "POST",
                data: "movieid=" + id + "&cmstate=0",
                dataType: "text",
                success: function (data) {
                    if (data == "true") {
                        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="picture_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
                        $(obj).remove();
						layer.msg('已发布!', {
							icon: 6,
							time: 1000,
							end: function () {
								window.location.reload();
							}
						})
                    } else if (data == "false") {
						layer.msg('发布失败!', {
							icon: 5,
							time: 1000,
							end: function () {
								window.location.reload();
							}
						})
                    }
                }
            });

        });
    }
};
/*图片-发布*/
function picture_start(obj,id,moviedate){
	var nowdate=$("#nowdate").val();
	if(moviedate>nowdate){
		layer.confirm('确认要修改为预售吗？',function(index){
            $.ajax({
                url:"movieManage/changeState",
                type:"POST",
                data:"movieid="+id+"&cmstate=1",
                dataType:"text",
                success:function(data){
                    if(data=="true"){
                        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="picture_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">预售中</span>');
                        $(obj).remove();
						layer.msg('已成功预售!', {
							icon: 6,
							time: 1000,
							end: function () {
								window.location.reload();
							}
						})
                    }else if(data=="false"){
						layer.msg('预售失败!', {
							icon: 5,
							time: 1000,
							end: function () {
								window.location.reload();
							}
						})
                    }
                }
            });
		});
	}else{
		layer.confirm('确认要发布吗？',function(index){
            $.ajax({
                url:"movieManage/changeState",
                type:"POST",
                data:"movieid="+id+"&cmstate=0",
                dataType:"text",
                success:function(data){
                    if(data=="true"){
                        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="picture_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
                        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
                        $(obj).remove();
						layer.msg('已发布!', {
							icon: 6,
							time: 1000,
							end: function () {
								window.location.reload();
							}
						})
                    }else if(data=="false"){
						layer.msg('发布失败!', {
							icon: 5,
							time: 1000,
							end: function () {
								window.location.reload();
							}
						})
                    }
                }
            });

		});
	}

}

/*图片-删除*/
function picture_del(obj,id){
	layer.confirm('确认要删除吗？',function(){
		$.ajax({
			type: 'POST',
			url: 'cinemaInfo/deleteMovie',
			dataType: 'text',
			data:"movieId="+id,
			success: function(data){
				if ("success"==data){
					layer.msg('已删除!', {
						icon: 1,
						time: 1000,
						end: function () {
							window.location.reload();
						}
					});
				} else {
					layer.msg('删除失败!', {icon: 1, time: 2000});
				}
			}
		});		
	});
}
</script>
</body>
</html>