<!DOCTYPE HTML>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
    <!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5shiv.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
    <link href="css/top.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <!--[if IE 6]>
    <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>购物车清单</title>
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
        <div class="user">
            <ul>
                <c:choose>
                    <c:when test="${sessionScope.user==null }">
                        <li><a href="login.jsp">登录/注册</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="userCenter.jsp" style="color: red">${sessionScope.user.username},欢迎您!</a></li>
                        <li><a href="javascript:void(0);" onclick="loginOut()">退出登录</a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</div>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20"><span class="r">共有数据：<strong>${fn:length(orderInfoList)}</strong> 条</span> </div>
    <div class="mt-20">
        <table class="table table-border table-bordered table-bg table-sort">
            <thead>
            <tr class="text-c">
                <th width="25"><input type="checkbox" name="" value=""></th>
                <th width="160">编号</th>
                <th width="120">影院</th>
                <th width="120">电影名称</th>
                <th width="200">放映时间</th>
                <th width="50">影厅</th>
                <th width="120">座位</th>
                <th width="50">数量</th>
                <th width="50">单价</th>
                <th width="80">小计</th>
                <th width="100">剩余时间</th>
                <th width="140">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${orderInfoList }" var="order" varStatus="count">
                <tr class="text-c">
                    <td><input name="" type="checkbox" value="${order.orderid }"></td>
                    <td>${order.orderid }</td>
                    <td>${order.cinemaname }</td>
                    <td>${order.moviename }</td>
                    <td>
                            ${order.ondate} ${order.starttime}-${order.endtime}
                        <input type="hidden" class="orderTime" id="orderTime" value="${order.ordertime }"/>
                    </td>
                    <td>${order.roomid }</td>
                    <td>${order.sitposition }</td>
                    <td>${order.num }</td>
                    <td>${order.movieprice }</td>
                    <td>${order.totalprice }</td>
                    <td class="timeout" id="${count.count}" data-times="${order.ordertime }"></td>
                    <td class="f-14 product-brand-manage">
                        <button id="payOrder" onclick="payOrder(${order.orderid })" class="btn btn-danger radius" type="button"> 立即付款</button>
                        <a style="text-decoration:none" class="ml-5" onClick="order_del(this,${order.orderid },${order.arrangeid },'${order.sitposition }')" href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
    $('.table-sort').dataTable({
        "aaSorting": [[ 1, "desc" ]],//默认第几个排序
        "bStateSave": true,//状态保存
        "aoColumnDefs": [
            //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
            {"orderable":false,"aTargets":[0,11]}// 制定列不参与排序
        ]
    });

    /*订单-删除*/
    function order_del(obj,orderId,arrangeId,sitposition){
        //layer.confirm('确认要删除吗？',function(){
        $.ajax({
            type: 'POST',
            url: 'sit/deleteOrder',
            data: 'orderId='+orderId+"&arrangeId="+arrangeId+"&sitposition="+sitposition,
            dataType:"text",
            success: function(data){
                if(data=="success"){
                    layer.msg('已删除!', {
                        icon: 1,
                        time: 1000,
                        end:function(){
                            window.location.reload();
                        }
                    });
                }
                else {
                    layer.msg('系统繁忙!',{icon:1,time:2000});
                }
            }
        });
        //});
    }
    //倒计时
    var endtime,nowtime,lefttime,d,h,m,s;
    var sh;
    $.fn.countDown = function(_boolean,_this){
        sh = [];
        _this.each(function(index, el){
            var thisObj = $(this);
            sh[index]=setInterval(function(){
                var times = thisObj.data("times");//获得timeBox的data值，即订单时间
                var orderTime = new Date(times);
                endtime = new Date(orderTime);
                endtime.setMinutes(endtime.getMinutes()+15);
                nowtime = new Date();
                lefttime=parseInt((endtime.getTime()-nowtime.getTime())/1000); //结束时间-当前时间得到毫秒数，再除以1000得到两个时间相差的秒数
                if(_boolean){
                    d=parseInt(lefttime/3600/24);
                    h=parseInt((lefttime/3600)%24);
                }else{
                    d=parseInt(lefttime/3600/24)*24;
                    h=parseInt((lefttime/3600)%24)+d;
                }
                m=parseInt((lefttime/60)%60);
                s=parseInt(lefttime%60);
                if(endtime.getTime() <= nowtime.getTime()){
                    /*d = h = m = s = "0";
                    clearInterval(sh[index]);*/
                    //自动删除已过时订单
                    var count = index+1;
                    var orderId=$("#orderId"+count).text();
                    var arrangeId = $("#arrangeId"+count).val();
                    var sitposition = $("#sitposition"+count).text();
                    order_del(this,orderId,arrangeId,sitposition);
                }
                // 三目运算符
                d = d < 10 ? "0"+ d : d;
                h = h < 10 ? "0"+ h : h;
                m = m < 10 ? "0"+ m : m;
                s = s < 10 ? "0"+ s : s;
                thisObj.html(h+":"+m+":"+s);
                if(lefttime<=0){
                    d = h = m = s = "00";
                    clearInterval(sh[index]);
                    //自动删除已过时订单
                    var count = index+1;
                    var orderId=$("#orderId"+count).text();
                    var arrangeId = $("#arrangeId"+count).val();
                    var sitposition = $("#sitposition"+count).text();
                    order_del(this,orderId,arrangeId,sitposition);
                }
            },1000);
        });
    }
    // 调用
    $(".timeout").countDown(false,$(".timeout"));
    function payOrder(orderId) {
        window.top.location.href="AliPay/pay?orderId="+orderId;
    }
</script>
</body>
</html>