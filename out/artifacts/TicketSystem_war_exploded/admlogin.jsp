<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<title>火狐影院管理后台</title>
		<link rel="stylesheet" media="screen" href="css/houtai.css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script>
			$(function(){
				$("#login-li").click(function(){
					$("#reg-div").hide();
					$("#login-div").show();
				})
				$("#reg_li").click(function(){
					$("#login-div").hide();
					$("#reg-div").show();
				})
				
				$("#loginbtn").click(function(){
					var cinematel=$("#cinematel").val();
					var cinemapwd=$("#cinemapwd").val();
					if(cinematel==""){
						alert("电话号码不能为空");
						return false;
					}
					if(cinemapwd==""){
						alert("密码不能为空");
						return false;
					}
					$.ajax({
						url: "manage/login",
						type: "POST",
						data: $("#login").serialize(),
						dataType: "text",
						success: function (data) {
							if ("success" == data) {
								window.location.href = "admIndex.jsp";
							} else {
								alert("电话号码或密码错误");
							}
						}
					});
				})
				
				$("#regbtn").click(function(){
					var cinemaname=$("#regcinemaname").val();
					var cinemapwd=$("#regcinemapwd").val();
					var ccinemapwd=$("#ccinemapwd").val();
					var cinematel=$("#regcinematel").val();
					if(cinemaname==""){
						alert("用户名不能为空");
						return false;
					}
					if(cinemapwd==""){
						alert("密码不能为空");
						return false;
					}
					if(ccinemapwd!=cinemapwd){
						alert("密码不一致");
						return false;
					}
					if(cinematel==""){
						alert("手机号不能为空");
						return false;
					}
					if(!(/^1[34578]\d{9}$/.test(cinematel))){
						alert("手机号码有误");
						return false;
					}
					$.ajax({
						url:"manage/checkCinemaTel",
						type:"POST",
						data:"cinematel="+cinematel+"&cinemaId=0",
						dataType:"text",
						success:function(data){
							if("success"==data){
								$.ajax({
									url:"manage/reg",
									type:"POST",
									data:$("#msform").serialize(),
									dataType:"text",
									success:function(regdata){
										if("success"==regdata){
											alert("注册成功，将自动为您登录！");
											window.location.href="admIndex.jsp";
										} else {
											alert("注册失败！");
										}
									}
								});
							} else {
								alert("该手机号码已被注册!");
								return false;
							}
						}
					});
				})
			})
			
			function checkCinemaTel(){
				var cinematel=$("#regcinematel").val();
				if(cinematel==""){
					alert("手机号不能为空");
					return false;
				}
				if(!(/^1[34578]\d{9}$/.test(cinematel))){
					alert("手机号码有误");
					return false;
				}
				$.ajax({
					url:"manage/checkCinemaTel",
					type:"POST",
					data:"cinematel="+cinematel+"&cinemaId=0",
					dataType:"text",
					success:function(data){
						if("error"==data){
							alert("该手机号码已被注册!");
							return false;
						}
					}
				});
			}
		</script>
</head>
<div id="reg-div" style="display: none;">
	<form id="msform">
	<h3>欢迎使用火狐在线影院管理后台</h3><br>
		<ul id="choice">
			<li class="" id="login-li">登录</li>
			<li class="choose" id="reg-li">注册</li>
		</ul>
	<fieldset>
		<h2 class="fs-title">创建您的账户信息</h2>
		<input type="text" id="regcinemaname" name="regcinemaname" placeholder="影院名称" />
		<input type="text" id="regcinematel" name="regcinematel" placeholder="电话号码" onblur="checkCinemaTel()"/>
		<input type="password" id="regcinemapwd" name="regcinemapwd" placeholder="密码" />
		<input type="password" id="ccinemapwd" name="ccinemapwd" placeholder="确认密码" />
		<input type="button" name="reg" id="regbtn" class="action-button" value="注册" />
	</fieldset>
</form>
</div>
<div id="login-div" style="display: block;">
	<form id="login">
	<h3>欢迎使用火狐影院管理后台</h3><br>
		<ul id="choice">
			<li class="choose" id="login_li">登录</li>
			<li class="" id="reg_li">注册</li>
		</ul>
	<fieldset>
		<h2 class="fs-title">登录</h2>
		<input type="text" id="cinematel" name="cinematel" placeholder="电话号码" />
		<input type="password" id="cinemapwd" name="cinemapwd" placeholder="密码" />
		<input type="button" name="login" id="loginbtn" class="action-button" value="登录" />
		<a href="webLogin.jsp" style="color: #0e73d0;font-size: 14px;margin-top: 10px">网站管理员</a>
	</fieldset>
	</form>
</div>
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>

</body>
</html>