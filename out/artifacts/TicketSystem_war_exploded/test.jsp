<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <script src="http://libs.baidu.com/jquery/1.8.3/jquery.min.js"></script>
</head>
<body>
<%-- <form action="${pageContext.request.contextPath}/AliPay/pay" method="post">
    <!-- 这里是表单内容 -->
    <button type="submit">付 款</button>
</form> --%>

<input type="button" id="btnPay" value="付款">
<div id="resultForm"></div>
</body>
</html>
<script type="text/javascript">
    $(function(){
        $('#btnPay').click(function() {
            $.ajax({
                url : 'AliPay/pay',
                type : "POST",
                data : "",
                contentType : "application/json",
                success : function(data) {
                    //后台会返回一个新的交易表单，并自动提交。所以随便放在一个地方就行
                    console.log(data+"123");
                    $("#resultForm").append(data);
                },
                error : function() {
                }
            });
        });
    });
</script>