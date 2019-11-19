<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<meta charset="utf-8">
		<title>欢迎使用狐狸影院</title>
		<base href="<%=basePath%>">
		<link rel="stylesheet" href="css/login.css">
		<link rel="stylesheet" type="text/css" href="css/styles.css">
		<link href="css/alert.css" type="text/css" rel="stylesheet" />
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript">
			window.onblur=function() {
				var inputs = $("input");
				for (var i = 0; i < inputs.length; i++) {
					document.getElementsByTagName('input')[i].blur();
				}
			}
			$(function(){

				$("#loginbtn").click(function(){
					var usertel=$("#usertel").val();
					var userpwd=$("#userpwd").val();
					var reuserpwd=$("#repasspwd").val();
					if(usertel==""){
						alert("手机号码不能为空");
						return false;
					}
					if(userpwd==""){
						alert("密码不能为空");
						return false;
					}
					if(reuserpwd!=userpwd){
						alert("密码不一致");
						return false;
					}
					$.ajax({
					url:"user/login",
					type:"POST",
					data:$("#loginForm").serialize(),
					dataType:"text",
					success:function(data){
					    if("success"==data){
							window.location.href="movie/load";
							//window.location=document.referrer;
                            return;
                        }else if ("error"==data) {
							alert("用户名或密码错误");
							return false;
						}else {
					    	window.location.href=data;
						}
					}
					});
				})
				
				$("#regbtn").click(function(){
					var username=$("#regusername").val();
					var userpwd=$("#reguserpwd").val();
					var reuserpwd=$("#reuserpwd").val();
					var usertel=$("#regusertel").val();
					if(username==""){
						alert("用户名不能为空");
						return false;
					}
					if(userpwd==""){
						alert("密码不能为空");
						return false;
					}
					if(!(/^[a-zA-Z0-9]{6,10}$/.test(userpwd))){
						alert("密码必须为包含6至10位的数字或字母！");
						return false;
					}
					if(reuserpwd!=userpwd){
						alert("密码不一致");
						return false;
					}
					if(usertel==""){
						alert("手机号不能为空");
						return false;
					}
					if(!(/^1[34578]\d{9}$/.test(usertel))){
						alert("手机号码有误");
						return false;
					}

					$.ajax({
						url:"user/checkUserName",
						type:"POST",
						data:"userId=0&username="+username,
						dataType:"text",
						success:function(data){
                            if("success"==data){
                                $.ajax({
                                    url:"user/checkUserTel",
                                    type:"POST",
                                    data:"userId=0&usertel="+usertel,
                                    dataType:"text",
                                    success:function(ckteldata){
                                        if("success"==ckteldata){
                                            $.ajax({
                                                url:"user/reg",
                                                type:"POST",
                                                data:$("#regForm").serialize(),
                                                dataType:"html",
                                                success:function(regdata){
                                                    if("success"==regdata){
                                                        alert("注册成功，将自动为您登录！");
                                                        window.location.href="movie/load";
														//window.location=document.referrer;
                                                        return;
                                                    }else {
                                                        alert("注册失败！");
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
				/*忘记密码  */
				$("#forget").click(function () {
				    $(".hide-center").fadeIn("slow");
				    $(".overCurtain").fadeIn("slow");
				})
				$("#close").click(function () {
				    $(".hide-center").fadeOut("slow");
				    $(".overCurtain").fadeOut("slow");
				})
				
				$("#BSignIn").click(function(){
					var username=$("#findregname").val();
					var usertel=$("#findregtel").val();
					var code=$("#input-bottomright-loginInput").val();
					if(username==""){
						alert("用户名不能为空");
						return false;
					}
					if(usertel==""){
						alert("手机号不能为空");
						return false;
					}
					if(!(/^1[34578]\d{9}$/.test(usertel))){
						alert("手机号码有误");
						return false;
					}
					if(code==""){
						alert("验证码不能为空");
						return false;
					}
					$.ajax({
						url:"user/findPwd",
						type:"POST",
						data:$("#searchform").serialize(),
						dataType:"text",
						success:function(data){
							alert(data);
							if(data!="用户名或手机号码不正确"){
								window.location.reload();
							}
							
						}
					});
				});
				
			})
		</script>
		<script type="text/javascript">
			function checkUserName(){
				var username=$("#regusername").val();
				$.ajax({
					url:"user/checkUserName",
					type:"POST",
					data:"userId=0&username="+username,
					dataType:"text",
					success:function(data){
					    if("error"==data){
                            alert("该用户名已被占用");
                            return false;
                        }
					}
				});
			}
			function checkUserTel(){
				var usertel=$("#regusertel").val();
				if(usertel==""){
					alert("手机号不能为空");
					return false;
				}
				if(!(/^1[34578]\d{9}$/.test(usertel))){
					alert("手机号码有误");
					return false;
				}
				$.ajax({
					url:"user/checkUserTel",
					type:"POST",
					data:"userId=0&usertel="+usertel,
					dataType:"text",
					success:function(data){
					    if("error"==data){
                            alert("该手机号码已被注册");
                            return;
                        }
					}
				});
			}
			function checkPwd(){
				var userpwd=$("#reguserpwd").val();
				if(!(/^[a-zA-Z0-9]{6,10}$/.test(userpwd))){
					alert("密码必须为包含6至10位的数字或字母！");
					return false;
				}
			}
			function refresh(img){
				img.src="image.jsp?time="+new Date().getTime();
			}
		</script>
	</head>
	
	<body>
		<div class="all">
			<div class="header">
				<img src="images/foxlogo.png" >
				<a href="movie/load">狐狸影院</a>
			</div>
			<div class="center">
				<img src="images/pi.png" >
				<div class="right">
					<div class="login-html">
						<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">登录</label>
						<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">注册</label>
						
						<div class="login-form">
						<form id="loginForm">
							<div class="sign-in-htm">
									<div class="group">
										<label for="user" class="label">手机号码</label>
										<input id="usertel" type="text" class="input"  name="usertel">
									</div>
									<div class="group">
										<label for="pass" class="label">密码</label>
										<input id="userpwd" type="password" class="input" data-type="password" name="userpwd">
									</div>
									<div class="group">
										<label for="pass" class="label">确认密码</label>
										<input id="repasspwd" type="password" class="input" data-type="password">
									</div>
									<div class="group">
										<input type="hidden" name="action" value="login"/>
										<input type="button" class="button" value="登录" id="loginbtn">
									</div>
									<div class="foot-lnk">
										<a href="javascript:void(0);" id="forget">忘记密码?</a>
									</div>
									<a href="admlogin.jsp" class="shop">→商家请戳</a>
								</div>
							</form>
							<form id="regForm">
								<div class="sign-up-htm">
									<div class="group">
										<label for="user" class="label">用户名</label>
										<input id="regusername" type="text" class="input" name="regusername" onblur="checkUserName()">
									</div>
									<div class="group">
										<label for="pass" class="label">手机号码</label>
										<input id="regusertel" type="text" class="input" name="regusertel" onblur="checkUserTel()">
									</div>
									<div class="group">
										<label for="pass" class="label">密码</label>
										<input id="reguserpwd" type="password" class="input" name="reguserpwd" data-type="password" onblur="checkPwd()">
									</div>
									<div class="group">
										<label for="pass" class="label">确认密码</label>
										<input id="reuserpwd" type="password" class="input" data-type="password">
									</div>
									<div class="group">
										<input type="hidden" name="action" value="reg"/>
										<input type="button" class="button" value="注册" id="regbtn">
									</div>
									<a href="admlogin.jsp" class="shop">→商家请戳</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<form id="searchform">
		<div class="overCurtain"></div>
	
		<div class="hide-center">
		    <div id="formhead">
		        <div id="formhead-title">
		            密码查询
		        </div>
		        <button type="button" id="close">X</button>
		    </div>
		    <div id="formbody">
		    	<div class="loginUserName">
		            <input id="findregname" name="username" class="loginInput" placeholder="用户名" type="text" >
		        </div>
		        <div class="loginUserTel">
		            <input id="findregtel" name="usertel" class="loginInput" placeholder="已注册的手机号码" type="text" >
		        </div>
		        <div class="loginPassword" style="position: relative;">
		            <input id="input-bottomright-loginInput" name="code" class="loginInput" placeholder="验证码" style="border-bottom-right-radius:5px;width: 130px;">
		            <img id="code" src="image.jsp" style="cursor: hand;width: 95px;height: 33px;position: absolute;top: 12px;margin-left: 5px;border:1px solid black;" onclick="refresh(this)"/>
		        </div>
		        <div id="formfoot">
		        	<input type="hidden" name="action" value="findPwd">
		            <button id="BSignIn" type="button">查询</button>
		        </div>
		    </div>
		</div>
	</form>
	</body>
</html>
