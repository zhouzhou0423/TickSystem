<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<meta charset="utf-8">
		<base href="<%=basePath%>">
		<title>影片</title>
		<link rel="stylesheet" type="text/css" href="css/film.css" />
		<link rel="stylesheet" type="text/css" href="css/css.css">
		<link rel="stylesheet" type="text/css" href="css/top.css"/>
		<link rel="stylesheet" type="text/css" href="css/moviePage.css"/>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script>
			$(function(){
				$(".area li:eq(0)").removeClass("active");
				var i=$("#typeId").val();
				$('#'+i).addClass("active");
			});
			function detail(id,orgin){
				location.href="movie/showdetail?movieid="+id+"&orign="+orgin;
			}
			function buyTicket(id) {
				if (${sessionScope.user==null }){
					alert("请先登录!");
					return false;
				}
				window.location.href="cinemaInfo/findByArea?keyword=all&seq=0&movieId="+id;
			}
			function loginOut(){
				top.location.href="user/loginout";
			}
		</script>
	</head>
	<body>
		<div class="header">
			<div class="header-inner">
				<div class="logo"></div>
				<div class="find">
					<ul>
						<li><a href="movie/load">首页</a></li>
						<li><a href="movie/findAllMovies" style="color:red;">影片</a></li>
                        <li><a href="cinemaInfo/findByArea?keyword=all&seq=0&movieId=all">影院</a></li>
					</ul>
				</div>
				<div class="user">
					<ul>
						<c:choose>
							<c:when test="${sessionScope.user==null }">
								<li><a href="login.jsp">登录/注册</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="userCenter.jsp">${sessionScope.user.username},欢迎您!</a></li>
								<li><a href="javascript:void(0);" onclick="loginOut()">退出登录</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>
		<br/>
		<div class="findcondition">
			<div class="areakey">
				类型：
			</div>
			<input type="hidden" class="typeId" id="typeId" value="${typeId}"/>
			<ul class="area">
				<li id="0" class="active"><a href="movie/findMoviesByType?typeId=0">全部</a></li>
				<c:forEach items="${typeList}" var="type">
					<li id="${type.typeid}"><a href="movie/findMoviesByType?typeId=${type.typeid}">${type.type}</a></li>
				</c:forEach>
			</ul>
		</div>
		<div class="list-film">
			<c:choose>
				<c:when test="${empty movieInfoList }">
					<div class="empty" style="width:100%;height:50px;text-align: center;line-height: 50px;color: grey">
						<h1 style="font-size: 20px">暂无影片</h1>
					</div>
				</c:when>
				<c:otherwise>
					<h3>影片列表</h3>
					<ul>
					<c:forEach items="${movieInfoList }" var="movie">
						<li>
							<a href="javascript:void(0);" onclick="detail(${movie.movieid},'other')"><img src="${movie.movieimg}" alt=""></a>
							<span class="name">${movie.moviename}</span>
							<span class="buy"><a href="javascript:void(0);" onclick="buyTicket(${movie.movieid})">购票</a></span>
						</li>
					</c:forEach>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
		<iframe width="100%" height="100" src="foot.jsp" scrolling="no" frameborder="0" marginwidth="0" marginheight="0"></iframe>
	</body>
</html>
