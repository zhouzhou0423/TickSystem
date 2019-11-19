<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" %>
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
	<title>修改场次</title>
	<link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
	<form class="form form-horizontal" id="form-article-add">
		<div class="row cl">
            <label class="form-label col-xs-4 col-sm-4">日期：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" disabled="disabled" value="${arrangedInfo.ondate }" id="ondate" class="input-text Wdate" name="ondate">
            </div>
        </div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-4">影厅：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="${arrangedInfo.roomid }" placeholder="" id="roomId" name="roomId">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-4">影片名称：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" disabled="disabled" value="${arrangedInfo.moviename }" placeholder="" id="moviename" name="moviename">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-4">开始时间：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="${arrangedInfo.starttime }" placeholder="" id="startTime" name="startTime">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-4">结束时间：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="${arrangedInfo.endtime }" placeholder="" id="endTime" name="endTime">
			</div>
		</div>
		<div class="row cl">
            <label class="form-label col-xs-4 col-sm-4">片长：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" disabled="disabled" class="input-text" value="${arrangedInfo.timelimit }" placeholder="" id="timelimit" name="timelimit">
            </div>
        </div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-4">票价：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="${arrangedInfo.movieprice }" placeholder="" id="moviePrice" name="moviePrice">
			</div>
		</div>
		<div class="row cl">
		    <label class="form-label col-xs-4 col-sm-4">已售/座位数：</label>
		    <div class="formControls col-xs-8 col-sm-4">
		        <input type="text" class="input-text" disabled="disabled" value="${arrangedInfo.salesit}/${arrangedInfo.sumsit}" placeholder="" id="sit" name="sit">
		    </div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-4">
				<input type="hidden" class="arrangeId" name="arrangeId" value="${arrangedInfo.arrangeid}"/>
				<button id="save" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</div>


<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript" src="lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript">
	$(function () {
		$("#save").click(function () {
			var startTime=$("#startTime").val();
			var endTime=$("#endTime").val();
			var a=startTime.split(":");
			var b=endTime.split(":");
			if(b[0]-a[0]<0){
				alert("结束时间不能早于开始时间！");
				return false;
			}
			var subTime=(b[0]-a[0])*60+(b[1]-a[1]);
			var timelimt=$("#timelimit").val();
			if(subTime<timelimt){
				alert("时间间隔小于片长，请重设！");
				return false;
			}
			$.ajax({
				url:"arrange/arrangeEdit",
				type:"POST",
				data:$("#form-article-add").serialize(),
				dataType:"text",
				success:function(data){
					if("success"==data){
						alert("修改成功！");
						window.parent.location.reload();
					}else{
						alert("修改失败！");
						return false;
					}

				}
			});
		})
	})
</script>
</body>
</html>