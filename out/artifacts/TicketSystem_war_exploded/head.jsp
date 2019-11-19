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
		<title>狐狸影院</title>
		<link rel="stylesheet" type="text/css"  href="css/head.css"/>
		<link rel="stylesheet" type="text/css" href="css/slider.css">
		<link rel="stylesheet" type="text/css" href="css/css.css">
		<link rel="stylesheet" type="text/css" href="css/top.css"/>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script>
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
						<li><a href="movie/load" style="color: red">首页</a></li>
						<li><a href="movie/findMoviesByType?typeId=0">影片</a></li>
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
		<div id="slider">
		  <div class="slider_box" id="slider_name">
		    <div class="silder_tip" id="movie_tip">电影</div>
		    <ul class="silder_con">
		    	<c:forEach items="${topMovies }" var="movie">
		    		<li class="silder_panel clearfix"> <a href="#" class="f_l" onclick="detail(${movie.movieid},'top')"><img src="${movie.movieimg}" /></a>
				        <div class="silder_intro f_r">
				          <h3> <strong><a href="javascript:void(0);" onclick="detail(${movie.movieid},'top')">${movie.moviename}</a></strong></h3>
				          <ul>
				            <li>
				              <label>类型：</label>
				              <span><a href="javascript:void(0);">${movie.typename}</a></span></li>
				            <li>
				              <label>导演：</label>
				              <span><a href="javascript:void(0);">${movie.moviedirector}</a></span></li>
				            <li>
				              <label>主演：</label>
				              <span><a href="javascript:void(0);">${movie.movieactor}</a></span></li>
				          </ul>
				          <p>[<strong>影片介绍</strong>]${movie.movieintro}</p>
				          <a class="buy_tickets"  href="javascript:void(0);" onclick="buyTicket(${movie.movieid})">马上购票</a>
				         </div>
		      		</li>
		    	</c:forEach>
		      
		     
		    </ul>
		    <ul class="silder_nav clearfix">
		    	<c:forEach items="${topMovies }" var="movie">
		    		<li><a href="javascript:void(0);"><img src="${movie.movieimg }"/></a></li>
		    	</c:forEach>
		    </ul>
		  </div>
		  <div class="silderBox"></div>
		</div>
		<!-- 正在热映 -->
		<div class="hotmovies">
			<div class="all">
				<label>正在热映</label>
				<a href="movie/findMoviesByType?typeId=0">全部></a>
			</div>
			<div class="movies">
				<ul>
					<c:forEach items="${hotMovies }" var="movie">
		    			<li>
							<a href="javascript:void(0);" onclick="detail(${movie.movieid},'hot')"><img src="${movie.movieimg}" alt=""></a>
							<span class="name">${movie.moviename}</span>
							<span class="buy"><a href="javascript:void(0);" onclick="buyTicket(${movie.movieid})">购票</a></span>
						</li>
		    		</c:forEach>
				</ul>
			</div>
			<div class="top-one">
				<div class="title">
					<label>今日票房</label>
				</div>
				<div class="order">
					<ul>
						<c:forEach items="${todayTopMovies }" var="movie" varStatus="count">
			    			<li><span class="num">${count.count }</span><a href="javascript:void(0);" onclick="detail(${movie.movieid},'todayTop')">${movie.moviename}</a><span class="totalnum">${movie.countnumber}</span></li>
		    			</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<!-- 即将上映 -->
		<div class="upcoming">
			<div class="all">
				<label>即将上映</label>
				<a href="movie/findMoviesByType?typeId=0">全部></a>
			</div>
			<div class="movies">
				<ul>
					<c:forEach items="${wantMovies }" var="movie">
		    			<li>
							<a href="javascript:void(0);" onclick="detail(${movie.movieid},'want')"><img src="${movie.movieimg}" alt=""></a>
							<span class="name">${movie.moviename}</span>
							<span class="buy"><a href="javascript:void(0);">购票</a></span>
						</li>
		    		</c:forEach>
				</ul>
			</div>
			<div class="top-two">
				<div class="title">
					<label>最受期待</label>
				</div>
				<div class="order">
					<ul>
						<ul>
						<c:forEach items="${todayMostMovies }" var="movie" varStatus="count">
			    			<li><span class="num">${count.count}  </span><a href="javascript:void(0);" onclick="detail(${movie.movieid},'todayMost')">${movie.moviename}</a><span class="totalnum">${movie.wantseecount}</span></li>
		    			</c:forEach>
					</ul>
					</ul>
				</div>
			</div>
		</div>
		<iframe width="100%" height="100" src="foot.jsp" scrolling="no" frameborder="0" marginwidth="0" marginheight="0"></iframe>
		<script src="js/jquery.slides.js" type="text/javascript"></script>
	</body>
</html>
