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
				$("#loginbtn").click(function(){
					var admtel=$("#admtel").val();
					var admpwd=$("#admpwd").val();
					if(admtel==""){
						alert("电话号码不能为空");
						return false;
					}
					if(admpwd==""){
						alert("密码不能为空");
						return false;
					}
					$.ajax({
						url: "manage/webLogin",
						type: "POST",
						data: $("#login").serialize(),
						dataType: "text",
						success: function (data) {
							if ("success" == data) {
								window.location.href = "webAdmIndex.jsp";
							} else {
								alert("电话号码或密码错误");
							}
						}
					});
				})
			})
		</script>
</head>
<div id="login-div" style="display: block;">
	<form id="login">
	<h3>欢迎使用火狐影院管理后台</h3><br>
		<ul id="choice">
			<li class="choose" id="login_li">登录</li>
		</ul>
	<fieldset>
		<h2 class="fs-title">登录</h2>
		<input type="text" id="admtel" name="admtel" placeholder="电话号码" />
		<input type="password" id="admpwd" name="admpwd" placeholder="密码" />
		<input type="button" name="login" id="loginbtn" class="action-button" value="登录" />
	</fieldset>
	</form>
</div>
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<br><br><br><br><br><br><br><br><br><br>
<br><br><br><br><br><br><br><br><br><br>

</body>
</html>