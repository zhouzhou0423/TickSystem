<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <meta charset="utf-8">
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>在线选座</title>
    <link rel="stylesheet" type="text/css" href="css/sit.css" />
</head>
    <body>
        <div class="container">
          <h2 class="title"><a href="javascript:history.back(-1)">场次</a>>在线选座</h2>
            <div class="demo clearfix">
                <!---左边座位列表----->
                <div id="seat_area">
                    <div class="front">屏幕</div>
                </div>
                <!---右边选座信息----->
                <div class="booking_area">
                    <p>电影：<span>${arrangedInfo.moviename}</span></p>
                    <p>售价：<span>${arrangedInfo.movieprice}</span></p>
                    <p>时间：<span>${arrangedInfo.ondate} ${arrangedInfo.starttime}</span></p>
                    <p>座位：</p>
                    <ul id="seats_chose"></ul>
                    <p>票数：<span id="tickects_num">0</span></p>
                    <p>总价：<b>￥<span id="total_price">0</span></b></p>
                    <input type="button" class="btn" id="buyTicket" value="确定购买"/>
                    <input type="hidden" class="arrangeId" id="arrangeId" value="${arrangedInfo.arrangeid}"/>
                    <input type="hidden" class="roomId" id="roomId" value="${roomInfos[0].roomid}"/>
                    <div id="legend"></div>
                </div>
            </div>
        </div>
        <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="js/jquery.seat-charts.min.js"></script>
        <script type="text/javascript">
            var price =${arrangedInfo.movieprice}; //电影票价
            $(document).ready(function() {
                var $cart = $('#seats_chose'), //座位区
                        $tickects_num = $('#tickects_num'), //票数
                        $total_price = $('#total_price'); //票价总额
                var sc = $('#seat_area').seatCharts({
                    map: [//座位结构图 a 代表座位; 下划线 "_" 代表过道
                        <c:forEach items="${roomInfos}" var="room">
                            '${room.rowsit}',
                        </c:forEach>
                    ],
                    naming: {//设置行列等信息
                        top: false, //不显示顶部横坐标（行） 
                        getLabel: function(character, row, column) { //返回座位信息 
                            return column;
                        }
                    },
                    legend: {//定义图例
                        node: $('#legend'),
                        items: [
                            ['c', 'available', '可选座'],
                            ['c', 'unavailable', '已售出']
                        ]
                    },
                    click: function() {
                        if (this.status() == 'available') { //若为可选座状态，添加座位
                            $('<li>' + (this.settings.row + 1) + '排' + this.settings.label + '座</li>')
                                    .attr('id', 'cart-item-' + this.settings.id)
                                    .data('seatId', this.settings.id)
                                    .appendTo($cart);
                            $tickects_num.text(sc.find('selected').length + 1); //统计选票数量
                            $total_price.text((getTotalPrice(sc) + price).toFixed(2));//计算票价总金额
                            return 'selected';
                        } else if (this.status() == 'selected') { //若为选中状态
                            $tickects_num.text(sc.find('selected').length - 1);//更新票数量
                            $total_price.text((getTotalPrice(sc) - price).toFixed(2));//更新票价总金额
                            $('#cart-item-' + this.settings.id).remove();//删除已预订座位
                            return 'available';
                        } else if (this.status() == 'unavailable') { //若为已售出状态
                            return 'unavailable';
                        } else {
                            return this.style();
                        }
                    }
                });
                //设置已售出的座位
                sc.get([
                    <c:forEach items="${sitInfos}" var="sit">
                        '${sit.sitposition}',
                    </c:forEach>
                ]).status('unavailable');
            });
            function getTotalPrice(sc) { //计算票价总额
                var total = 0;
                sc.find('selected').each(function() {
                    total += price;
                });
                return total;
            }
            $("#buyTicket").click(function () {
                var arrangeId=$("#arrangeId").val();
                var roomId=$("#roomId").val();
                var totalPrice=$("#total_price").text();
                var lis = $("#seats_chose li");
                var arr=$.makeArray(lis);
                var sits=new Array();
                for (var i = 0; i < arr.length; i++) {
                    sits[i]=arr[i].innerHTML.substring(0,3).replace("排","_");
                }
                $.ajax({
                    type: 'POST',
                    url: 'sit/buyTickets',
                    data: "sits="+sits+"&arrangeId="+arrangeId+"&roomId="+roomId+"&totalPrice="+totalPrice,
                    dataType:"text",
                    success: function(data){
                        if(data=="success"){
                            window.location.href="sit/queryOrderList?orderState=0&origin=0";
                        }
                        else {
                            alert("占座失败");
                        }
                    }
                });
            });

        </script>
    </body>
</html>





