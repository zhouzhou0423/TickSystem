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
		<title>影院</title>
		<link rel="stylesheet" type="text/css" href="css/film.css" />
		<link rel="stylesheet" type="text/css" href="css/css.css">
		<link rel="stylesheet" type="text/css" href="css/top.css"/>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script>
			$(function(){
				$(".area li:eq(0)").removeClass("active");
				var i=$("#seq").val();
				$('#'+i).addClass("active");
			});
		</script>
	</head>
	<body>
		<div class="header">
			<div class="header-inner">
				<div class="logo"></div>
				<div class="find">
					<ul>
						<li><a href="movie/load">首页</a></li>
						<li><a href="movie/findMoviesByType?typeId=0">影片</a></li>
                        <li><a href="cinemaInfo/findByArea?keyword=all&seq=0&movieId=all" style="color:red;">影院</a></li>
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
				地区：
			</div>
			<input type="hidden" class="seq" id="seq" value="${seq}"/>
			<ul class="area">
				<li id="0" class="active"><a href="cinemaInfo/findByArea?keyword=all&seq=0&movieId=${requestScope.mId}">全部</a></li>
				<li id="1"><a href="cinemaInfo/findByArea?keyword=岳麓区&seq=1&movieId=${requestScope.mId}">岳麓区</a></li>
				<li id="2"><a href="cinemaInfo/findByArea?keyword=开福区&seq=2&movieId=${requestScope.mId}">开福区</a></li>
				<li id="3"><a href="cinemaInfo/findByArea?keyword=天心区&seq=3&movieId=${requestScope.mId}">天心区</a></li>
				<li id="4"><a href="cinemaInfo/findByArea?keyword=望城区&seq=4&movieId=${requestScope.mId}">望城区</a></li>
				<li id="5"><a href="cinemaInfo/findByArea?keyword=芙蓉区&seq=5&movieId=${requestScope.mId}">芙蓉区</a></li>
				<li id="6"><a href="cinemaInfo/findByArea?keyword=长沙县&seq=6&movieId=${requestScope.mId}">长沙县</a></li>
				<li id="7"><a href="cinemaInfo/findByArea?keyword=宁乡县&seq=7&movieId=${requestScope.mId}">宁乡县</a></li>
				<li id="8"><a href="cinemaInfo/findByArea?keyword=雨花区&seq=8&movieId=${requestScope.mId}">雨花区</a></li>
				<li id="9"><a href="cinemaInfo/findByArea?keyword=浏阳市&seq=9&movieId=${requestScope.mId}">浏阳市</a></li>
			</ul>
		</div>
		<div class="list-film">
			<c:choose>
				<c:when test="${empty cinemaInfos }">
					<div class="empty" style="width:100%;height:50px;text-align: center;line-height: 50px;color: grey">
						<h1 style="font-size: 20px">暂无影片</h1>
					</div>
				</c:when>
				<c:otherwise>
					<h3>影院列表</h3>
					<c:forEach items="${cinemaInfos }" var="cinemaInfo">
						<div class="filminfo">
							<div class="info">
								<a href="cinemaInfo/showCinemaDetail?cinemaId=${cinemaInfo.cinemaid}" class="cinemaname">${cinemaInfo.cinemaname}</a>
								<p>地址：${cinemaInfo.cinemaaddr}</p>
								<div class="info-buy">
									<label>￥${cinemaInfo.lowPrice}</label>起
									<a href="cinemaInfo/showCinemaDetail?cinemaId=${cinemaInfo.cinemaid}">选座购票</a>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
		<iframe width="100%" height="100" src="foot.jsp" scrolling="no" frameborder="0" marginwidth="0" marginheight="0"></iframe>
	</body>
</html>
