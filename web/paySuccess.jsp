<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/4/9
  Time: 17:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <%--<link rel="stylesheet" type="text/css"  href="css/head.css"/>--%>
    <link rel="stylesheet" type="text/css" href="css/top.css"/>
    <script type="text/javascript" src="js/jquery.js"></script>
    <title>购票成功</title>
</head>
<body>
<div class="header">
    <div class="header-inner">
        <div class="logo"></div>
        <div class="find">
            <ul>
                <li><a href="movie/load">首页</a></li>
                <li><a href="movie/findMoviesByType?typeId=0">影片</a></li>
                <li><a href="cinemaInfo/findByArea?keyword=all&seq=0&movieId=all">影院</a></li>
            </ul>
        </div>
        <div class="user" style="margin-top: 30px;">
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
<div>
    <p style="text-align: center;font-size: 20px;color: orange;">欢迎下次使用，祝您观影愉快！</p>
    <div style="width: 300px;margin: 30px auto;padding:10px 10px;border:1px solid #0a6999;">
        <p>电影名称：${sessionScope.order.moviename}</p>
        <p>时间：${sessionScope.order.ondate} ${sessionScope.order.starttime}-${sessionScope.order.endtime}</p>
        <p>影院：${sessionScope.order.cinemaname}</p>
        <p>座位：${order.sitposition }</p>
        <p>取票码：${sessionScope.order.ticketcode}</p>
    </div>
</div>
<iframe width="100%" height="100" src="foot.jsp" scrolling="no" frameborder="0" marginwidth="0" marginheight="0"></iframe>
</body>
</html>
