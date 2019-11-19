<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="utf-8">
    <base href="<%=basePath%>">
    <link href="css/top.css" rel="stylesheet" type="text/css" />
    <link href="css/detail.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="css/kuCity.css">
    <script type="text/javascript" src="js/jquery.js"></script>
    <title>影片详情页</title>
    <script>
        $(function(){
            //是否标为想看
            var isWant=$("#isWant").val();
            if (isWant=='true'){
                $(".wantLook").css('background-color','#FF1493');
            }
            var divs=$(".comment");
            var arr=new Array();
            <c:forEach items="${zanInfos}" var="zanInfo">
                arr.push(${zanInfo.commentid});
            </c:forEach>
            for (var i = 1; i <=divs.length; i++) {
                for (var j = 0; j < arr.length; j++) {
                    if (arr[j]==$("#cid"+i).val()) {
                        $("#like"+i).addClass('cs').attr("rel", "unlike");
                        break;
                    }
                }
            }

            $(".tab-gn").click(function(){
                $(this).addClass("active-gn").siblings().removeClass("active-gn");
                var idx = $(this).index();
                $(".main-gn").eq(idx).addClass("selected-gn").siblings().removeClass("selected-gn");
            });

            $(".write-com").click(function(){
                // $(".write-com-div").css('display','block');
                if (${sessionScope.user==null }){
                    alert("请先登录!");
                    return false;
                }
                $(".write-com-div").fadeIn();
            });
            //点击立即购票
            $(".buytick").click(function () {
                if (${sessionScope.user==null }){
                    alert("请先登录!");
                    return false;
                }
                var movieId=$("#movieId").val();
                window.location.href="cinemaInfo/findByArea?keyword=all&seq=0&movieId="+movieId;
            })
        })
    </script>
    <script>
        $(function(){
            $(".like").click(function(){
                if (${sessionScope.user==null }){
                    alert("请先登录!");
                    return false;
                }
                var A = $(this).attr("id");
                var B = A.split("like");
                var messageID = B[1];
                var C = parseInt($("#likeCount" + messageID).html());
                var commentId=$("#cid" + messageID).val();
                var zanCount=0;
                var isLike=0;
                $(this).css("background-position", "");
                var D = $(this).attr("rel");
                if (D === 'like')
                {
                    $("#likeCount" + messageID).html(C + 1);
                    $(this).addClass('cs').attr("rel", "unlike");
                    zanCount=C+1;
                    isLike=1;
                } else
                {
                    $("#likeCount" + messageID).html(C - 1);
                    $(this).removeClass('cs').attr("rel", "like");
                    $(this).css("background-position", "left");
                    zanCount=C-1;
                    isLike=0;
                }
                $.ajax({
                    url:"comment/updateZan",
                    type:"POST",
                    data:"commentId="+commentId+"&isLike="+isLike+"+&zanCount="+zanCount,
                    dataType:"text",
                    success:function(data){
                        if(data=="unLogin"){
                            alert("请先登录");
                            return false;
                        }
                    }
                });

            });
            //想看
            $(".wantLook").click(function () {
                if (${sessionScope.user==null }){
                    alert("请先登录!");
                    return false;
                }
                var movieId=$("#movieId").val();
                if($(".wantLook").css('background-color')=='rgb(255, 20, 147)'){
                    $.ajax({
                        url:"user/deleteWantLook",
                        type:"POST",
                        data:"movieId="+movieId,
                        dataType:"text",
                        success:function(data){
                            if(data=="success"){
                                $(".wantLook").css('background-color','#808080');
                                alert("已取消！");
                                //window.location.reload();
                                return true;
                            }else{
                                alert("操作失败！");
                                return false;
                            }
                        }
                    });

                }
                else{
                    $.ajax({
                        url:"user/wantLook",
                        type:"POST",
                        data:"movieId="+movieId,
                        dataType:"text",
                        success:function(data){
                            if(data=="success"){
                                $(".wantLook").css('background-color','#FF1493');
                                alert("已标为想看！");
                                return true;
                            }else{
                                alert("操作失败！");
                                return false;
                            }
                        }
                    });

                }
            });
            //评分
            var starRating = 0;
            $('.photo span').on('mouseenter',function () {
                var index = $(this).index()+1;
                $(this).prevAll().find('.high').css('z-index',1);
                $(this).find('.high').css('z-index',1);
                $(this).nextAll().find('.high').css('z-index',0);
                $('.starNum').html((index*2)+'分');
            });
            $('.photo').on('mouseleave',function () {
                $(this).find('.high').css('z-index',0);
                var count = starRating / 2;
                if(count == 5) {
                    $('.photo span').find('.high').css('z-index',1);
                } else {
                    $('.photo span').eq(count).prevAll().find('.high').css('z-index',1);
                }
                $('.starNum').html(starRating+'分');
            });
            $('.photo span').on('click',function () {
                var index = $(this).index()+1;
                $(this).prevAll().find('.high').css('z-index',1);
                $(this).find('.high').css('z-index',1);
                starRating = index*2;
                $('.starNum').html(starRating+'分');
                //alert('评分：'+(starRating.toFixed(1)+'分'))
            });
            //取消评分
            $('.cancleStar').on('click',function () {
                starRating = 0;
                $('.photo span').find('.high').css('z-index',0);
                // $('.starNum').html(starRating.toFixed(1)+'分');
                $(".write-com-div").fadeOut();
            })
            //确定评分
            $('.sureStar').on('click',function () {
                if(starRating===0) {
                    alert('最低一颗星！');
                } else {
                    var movieid=$(".movid").val();
                    var userid=$(".uid").val();
                    var username=$(".un").val();
                    var comment=$(".comm-con").val();
                    var grade=starRating;
                    $.ajax({
                        url:"comment/addComment",
                        type:"POST",
                        data:"movieid="+movieid+"&userid="+userid+"&username="+username+"&comment="+comment+"&grade="+grade,
                        dataType:"text",
                        success:function(data){
                            if("success"==data){
                                alert("评论成功");
                                window.location.reload();
                            }
                            else{
                                alert("评论失败");
                            }
                        }
                    });
                }
            })
        });
    </script>
    <title></title>
</head>
<body>
    <div class="header">
        <div class="header-inner">
            <div class="logo"></div>
            <div class="find">
                <ul>
                    <li><a href="movie/load">首页</a></li>
                    <li><a href="movie/findMoviesByType?typeId=0" style="color: red">影片</a></li>
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

    <div class="second-bk">
        <div class="movie-img">
            <img src="${requestScope.moviedetail.movieimg }">
        </div>
        <div class="movie-intro">
            <h3>${requestScope.moviedetail.moviename }</h3>
            <ul>
                <li>${requestScope.moviedetail.typename }</li>
                <li>${requestScope.moviedetail.nation }/${requestScope.moviedetail.timelimit }分钟</li>
                <li>${requestScope.moviedetail.releasetime}上映</li>
            </ul>
        </div>
        <div class="movie-btn">
            <input type="hidden" class="isWant" id="isWant" value="${requestScope.isWantLook}"/>
            <button type="button" class="wantLook">想看</button>
            <button type="button" class="write-com">评分</button><br/>
            <button type="button" class="buytick">立即购票</button>
            <input type="hidden" class="movieId" id="movieId" value="${requestScope.moviedetail.movieid }"/>
        </div>
    </div>



<div class="third">
        <div>
            <ul class="tab-g clearfix">
                <li class="tab-gn active-gn">介绍</li>
                <li class="tab-gn">演职人员</li>
                <li class="tab-gn">评论</li>
            </ul>
        </div>

        <div class="bottom clearfix">
            <div class="products">
                <div class="main-gn selected-gn"><br>
                    <h3>剧情简介</h3><br>
                    <p>${requestScope.moviedetail.movieintro}</p><br>
                    <h3>演职人员</h3><br>
                    <p>导演</p><br>
                    <div class="director">
                        <img src="${requestScope.moviedirector.photo}">
                        <p>${requestScope.moviedirector.actorname}</p><br>
                    </div>
                    <p>演员</p><br>
                    <div class="actor">
                        <c:forEach items="${actors }" var="actor">
                            <div class="actor-intro">
                                <img src="${actor.photo}">
                                <p>${actor.actorname}</p>
                                <p>饰:${actor.rolename}</p><br>
                            </div>
                        </c:forEach>
                    </div>
                    <br>
                    <h3>热门短评<span class="write-com">写短评</span></h3><br>
                    <c:choose>
                        <c:when test="${empty comments }">
                            <div class="empty" style="width:100%;height:50px;text-align: center;line-height: 50px;color: grey">
                                <h1 style="font-size: 20px">暂无评论</h1>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="comments">
                                <c:forEach items="${comments }" var="comment" varStatus="count">
                                    <div class="comment">
                                        <div class="com-top">
                                            <div class="user-img">
                                                <img src="images/defaultphoto.png">
                                            </div>
                                            <div class="user-info">
                                                <p class="user-name">${comment.username}</p>
                                                <p class="com-time">
                                                        <fmt:formatDate var="reTime" value='${comment.time}' pattern='yyyy-MM-dd HH:mm:ss' />
                                                        ${reTime }
                                                    <span>${comment.grade}分</span>
                                                <div class="zan" id="zan${count.count }">
                                                    <div class="like" id="like${count.count }" rel="like">&#10084;</div>
                                                    <div class="likeCount" id="likeCount${count.count }">${comment.zancount}</div>
                                                    <input type="hidden" class="cid" id="cid${count.count}" value="${comment.commentid}"/>
                                                </div>
                                                </p>
                                            </div>
                                            <div class="com-text">
                                                <p>${comment.comment}</p>
                                            </div>

                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="main-gn"><br>
                    <p>导演</p><br>
                    <div class="director">
                        <img src="${requestScope.moviedirector.photo}">
                        <p>${requestScope.moviedirector.actorname}</p><br>
                    </div>
                    <p>演员</p><br>
                    <div class="actor">
                        <c:forEach items="${actors }" var="actor">
                            <div class="actor-intro">
                                <img src="${actor.photo}">
                                <p>${actor.actorname}</p>
                                <p>饰:${actor.rolename}</p><br>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <div class="main-gn"><br>
                <h3>热门短评<span class="write-com">写短评</span></h3><br>
                    <c:choose>
                        <c:when test="${empty comments }">
                            <div class="empty" style="width:100%;height:50px;text-align: center;line-height: 50px;color: grey">
                                <h1 style="font-size: 20px">暂无评论</h1>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="comments">
                                <c:forEach items="${comments }" var="comment" varStatus="count">
                                    <div class="comment">
                                        <div class="com-top">
                                            <div class="user-img">
                                                <img src="images/defaultphoto.png">
                                            </div>
                                            <div class="user-info">
                                                <p class="user-name">${comment.username}</p>
                                                <p class="com-time">
                                                        <fmt:formatDate var="reTime" value='${comment.time}' pattern='yyyy-MM-dd HH:mm:ss' />
                                                        ${reTime }
                                                    <span>${comment.grade}分</span>
                                                <div class="zan" id="zan${count.count }">
                                                    <div class="like" id="like${count.count }" rel="like">&#10084;</div>
                                                    <div class="likeCount" id="likeCount${count.count }">${comment.zancount}</div>
                                                    <input type="hidden" class="cid" id="cid${count.count}" value="${comment.commentid}"/>
                                                </div>
                                                </p>
                                            </div>
                                            <div class="com-text">
                                                <p>${comment.comment}</p>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                            </div>
                        </c:otherwise>
                    </c:choose>
            </div>
        </div>
    </div>
</div>
    <div style="width: 150px;height: 200px;position: fixed;right: 30px;bottom: 30px;">
        <label style="padding-bottom: 10px">欢迎使用火狐影院</label>
        <img src="images/foxlogo.png" width="150" height="180"/>
    </div>
    <div class="write-com-div">
        <div id="starRating">
            <p class="starNum">请点击星星评分</p>
            <p class="photo">
                <span><i class="high"></i><i class="nohigh"></i></span>
                <span><i class="high"></i><i class="nohigh"></i></span>
                <span><i class="high"></i><i class="nohigh"></i></span>
                <span><i class="high"></i><i class="nohigh"></i></span>
                <span><i class="high"></i><i class="nohigh"></i></span>
            </p>
            <div class="content">
                <textarea class="comm-con" rows="10" cols="50" placeholder="快来说说你的看法吧!"></textarea>
            </div>
            <input type="hidden" class="movid" value="${requestScope.moviedetail.movieid}">
            <input type="hidden" class="uid" value="${sessionScope.user.userid}">
            <input type="hidden" class="un" value="${sessionScope.user.username}">
            <div class="bottoms">
                <a class="garyBtn cancleStar">取消评分</a><a class="blueBtn sureStar">确认</a>
            </div>
        </div>
    </div>

    <iframe width="100%" height="100" src="foot.jsp" scrolling="no" frameborder="0" marginwidth="0" marginheight="0"></iframe>
</body>
</html>
