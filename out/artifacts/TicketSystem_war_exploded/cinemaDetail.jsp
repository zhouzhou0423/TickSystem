<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
	<head>
		<meta charset="utf-8">
		<base href="<%=basePath%>">
		<link href="css/top.css" rel="stylesheet" type="text/css" />
		<link href="css/cinemadetail.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<script type="text/javascript" src="js/jquery.js"></script>
		<title>影院场次信息</title>
		<script type="text/javascript">
			$(function() {
				$("#today").html("今天:"+GetDateStr(0));
				$("#tomorrow").html("明天:"+GetDateStr(1));
				$("#thirdDay").html("后天:"+GetDateStr(2));
			})
			function GetDateStr(AddDayCount) {
				var dd = new Date();
				dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
				//var y = dd.getFullYear();
				var m = dd.getMonth()+1;//获取当前月份的日期
				var d = dd.getDate();
				if(m<10){
					m="0"+m;
				}if(d<10){
					d="0"+d;
				}
				return m+"月"+d+"日";
			}
			function changeArrange(cinemaId,dateStr) {
				var movieId=$("#movieID").val();
				$.ajax({
					url:"cinemaInfo/showArrange",
					type:"POST",
					data:"cinemaId="+cinemaId+"&movieId="+movieId+"&dateStr="+dateStr,
					dataType:"json",
					success:function(result){
						changeTable(result);
					}
				});
			}
		</script>
		<script>
			$(function(){
				$(".portfolio-items li:eq(0)").addClass("choosed");
				$(".showdate a:eq(0)").addClass("active");
				$(".portfolio-items li").click(function() {
					$(this).siblings('li').removeClass('choosed');  // 删除其他兄弟元素的样式
					$(this).addClass('choosed');                            // 添加当前元素的样式
				});
				$(".showdate a").click(function() {
					$(this).siblings('a').removeClass('active');
					$(this).addClass('active');
				});
			});
			function showArrange(movieId,cinemaId,count) {
				var cName=$("#name"+count).html();
				var ctime=$("#time"+count).val();
				var cTypeNation=$("#type"+count).val();
				$(".showdate a:eq(0)").addClass("active");
				$(".showdate a:eq(1)").removeClass("active");
				$(".showdate a:eq(2)").removeClass("active");
				$("#cMovieName").html(cName);
				$("#cMTypeNation").html("类型:"+cTypeNation);
				$("#cTimeLimit").html("时长:"+ctime+"分钟");
				$("#movieID").val(movieId);
				$.ajax({
					url:"cinemaInfo/showArrange",
					type:"POST",
					data:"cinemaId="+cinemaId+"&movieId="+movieId+"&dateStr=today",
					dataType:"json",
					success:function(result) {
						changeTable(result);
					}
				});
			}
			function changeTable(result) {
				//动态生成表格
				var tableInfos = document.getElementById('tableInfo');
				var code = '<table class="table table-striped">';
				code += '<tr><th>放映时间</th><th>放映厅</th><th>时长</th><th>售价</th><th>选座购票</th></tr>';
				if (result != null && result.length > 0) {
					for(var i=0;i<result.length;i++){

						code += '<tr><td class="time">'+result[i].starttime+"--"+result[i].endtime+'</td><td>'+result[i].roomid+"厅"
								+'</td><td>'+result[i].timelimit+"分钟"+'</td><td class="t-price">￥'+result[i].movieprice
								+'</td><td><a href="sit/showSitPage?arrangeId='+result[i].arrangeid+'&cinemaId='+result[i].cinemaid+'&roomId='+result[i].roomid+'" class="tobuyticket">'+"选座购票"+'</a></td></tr>';
					}
				}else {
					code += '<tr><td colspan="5">暂无场次信息</td></tr>';
				}
				tableInfos.innerHTML = code + '</table>';
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
						<li><a href="movie/findMoviesByType?typeId=0">影片</a></li>
						<li><a href="cinemaInfo/findByArea?keyword=all&seq=0&movieId=all" style="color: red">影院</a></li>
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
		
		<div class="second-bk">
			<div class="cinema-img">
				<img src="${cinemaInfo.cinemaimg }">
			</div>
			<div class="cinema-intro">
				<h3>${cinemaInfo.cinemaname }</h3>
				<ul>
					<li>${cinemaInfo.cinemaaddr }</li>
					<li>电话：${cinemaInfo.cinematel }</li>
					<li>影院简介</li>
					<li>${cinemaInfo.cinemaintro }</li>
				</ul>
			</div>
		</div>
		<c:choose>
            <c:when test="${empty movieInfoList }">
                <div class="empty" style="width:100%;height:50px;text-align: center;line-height: 50px;color: grey">
                    <h1>暂无影片</h1>
                </div>
            </c:when>
            <c:otherwise>
                <div class="demo-wrapper">
                    <ul class="portfolio-items">
                        <c:forEach items="${movieInfoList }" var="movieInfo" varStatus="count">
                            <li class="item">
                                <figure>
                                    <div class="view"> <img src="${movieInfo.movieimg}" /> </div>
                                    <figcaption>
                                        <p><span> <a id="name${count.count}" onclick="showArrange(${movieInfo.movieid},${cinemaInfo.cinemaid },${count.count})" href="javascript:void(0);">${movieInfo.moviename}</a></span></p>
                                        <p><span>${movieInfo.nation}</span></p>
                                    </figcaption>
                                </figure>
                                <div class="date"> ${movieInfo.releasetime}</div>
                                <input type="hidden" id="type${count.count}" value="${movieInfo.typename}--${movieInfo.nation}"/>
                                <input type="hidden" id="time${count.count}" value="${movieInfo.timelimit}"/>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
                <div class="moreinfo" id="moreInfo">
					<input type="hidden" id="movieID" value="${movieInfoList[0].movieid }"/>
                    <h3 id="cMovieName">${movieInfoList[0].moviename }</h3>
                    <ul>
                        <li id="cTimeLimit">时长：${movieInfoList[0].timelimit }分钟</li>
                        <li id="cMTypeNation">类型：${movieInfoList[0].typename }--${movieInfoList[0].nation}</li>
                    </ul>
                </div>
                <div class="showdate">
                    <span>放映时间：</span>
                    <a href="javascript:void(0);" onclick="changeArrange(${cinemaInfo.cinemaid },'today')" id="today"></a>
                    <a href="javascript:void(0);" onclick="changeArrange(${cinemaInfo.cinemaid },'tomorrow')" id="tomorrow"></a>
                    <a href="javascript:void(0);" onclick="changeArrange(${cinemaInfo.cinemaid },'thirdDay')" id="thirdDay"></a>
                </div>
				<div class="timeplan" id="tableInfo">
					<table class="table table-striped">
						<tr>
							<th>放映时间</th>
							<th>放映厅</th>
							<th>时长</th>
							<th>售价</th>
							<th>选座购票</th>
						</tr>
                        <c:choose>
                            <c:when test="${empty arrangedInfoList }">
								<tr><td colspan="5">暂无场次信息</td></tr>
                            </c:when>
							<c:otherwise>
								<c:forEach items="${arrangedInfoList}" var="arrangerdInfo">
									<tr>
										<td class="time">${arrangerdInfo.starttime }-${arrangerdInfo.endtime }</td>
										<td>${arrangerdInfo.roomid }厅</td>
										<td>${arrangerdInfo.timelimit }分钟</td>
										<td class="t-price">￥${arrangerdInfo.movieprice }</td>
										<td><a href="sit/showSitPage?arrangeId=${arrangerdInfo.arrangeid }&cinemaId=${arrangerdInfo.cinemaid }&roomId=${arrangerdInfo.roomid }" class="tobuyticket">选座购票</a></td>
									</tr>
								</c:forEach>
							</c:otherwise>
                        </c:choose>
				</table>
				</div>
            </c:otherwise>
		</c:choose>
		<script src="js/modernizr-1.5.min.js"></script> 
		<script src="js/scripts.js"></script>
	</body>
</html>
