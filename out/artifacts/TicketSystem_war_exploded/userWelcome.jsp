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
</head>
<body>
<div class="page-container">
	<p class="f-20 text-success" style="margin:0 auto;text-align: center;font-size:25px;font-weight:normal;">个人中心</p>
	<form class="form form-horizontal" id="form-actor-edit">
		<br/>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-4">用户电话：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="hidden" class="userId" id="userId" name="userId" value="${sessionScope.user.userid}"/>
				<input type="text" class="input-text" value="${sessionScope.user.usertel}"  placeholder="" id="userTel" name="userTel">
			</div>
		</div><br/>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-4">用户名称：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="text" class="input-text" value="${sessionScope.user.username}" placeholder="" id="userName" name="userName">
			</div>
		</div><br/>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-4">用户密码：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<input type="password" class="input-text" value="${sessionScope.user.userpwd}" placeholder="" id="userPwd" name="userPwd">
			</div>
		</div><br/>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-4">用户头像：</label>
			<div class="formControls col-xs-8 col-sm-4">
				<span class="btn-upload form-group">
                    <input class="input-text upload-url" type="text" name="uploadfile-2" id="uploadfile-2" readonly value="${sessionScope.user.userphoto}" style="width:200px">
                    <a href="javascript:void(0);" class="btn btn-primary upload-btn"><i class="Hui-iconfont">&#xe642;</i> 上传头像</a>
                    <input type="file" id="file" multiple name="file" class="input-file" accept="image/*">
			    </span>
			</div>
		</div>
		<br/>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-4">
				<button id="addBtn" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 修改</button>
			</div>
		</div>
	</form>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript">
	$(function () {
		$("#addBtn").click(function () {
			var userName=$("#userName").val();
			var userTel=$("#userTel").val();
			var userPwd=$("#userPwd").val();
			var userId=$("#userId").val()
			if(!(/^1[34578]\d{9}$/.test(userTel))){
				alert("手机号码有误");
				return false;
			}
			if(userName==""){
				alert("用户名不能为空");
				return false;
			}
			if(userPwd==""){
				alert("密码不能为空");
				return false;
			}
			$.ajax({
				url:"user/checkUserName",
				type:"POST",
				data:"username="+userName+"&userId="+userId,
				dataType:"text",
				success:function(data){
					if("success"==data){
						$.ajax({
							url:"user/checkUserTel",
							type:"POST",
							data:"usertel="+userTel+"&userId="+userId,
							dataType:"text",
							success:function(ckteldata){
								if("success"==ckteldata){
									var formData = new FormData($("#form-actor-edit")[0]);
									$.ajax({
										url:"user/editUser",
										type:"POST",
										data:formData,
										dataType:"text",
										processData:false,
										contentType : false,
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
								}else {
									alert("该手机号码已被注册");
									return false;
								}
							}
						});
					}
					else {
						alert("该用户名已被占用");
						return false;
					}
				}
			});

		})
	})
</script>
</body>
</html>