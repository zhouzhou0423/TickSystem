<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page contentType="text/html; charset=utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <base href="<%=basePath%>">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="/favicon.ico" >
    <link rel="Shortcut Icon" href="/favicon.ico" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5shiv.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>

    <![endif]-->
    <link href="static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="static/h-ui.admin/css/H-ui.admin.css" rel="stylesheet" type="text/css" />
    <link href="lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />

    <link rel="stylesheet" type="text/css" href="css/sit.css" />
    <script type="text/javascript" src="js/jquery.js"></script>
    <!--[if IE 6]>
    <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>影厅</title>
</head>
<body>
<div class="page-container">
    <form class="form form-horizontal" id="form-actor-add">
        <div class="c">
            <label class="form-label col-xs-2 col-sm-7" style="color: red;font-size: 16px;">座位表示：c代表座位; 下划线 "_" 代表过道</label>
            <input type="hidden" class="count" id="count" value="1"/>
        </div><br/>
        <div class="row c0">
            <label class="form-label col-xs-2 col-sm-3">影厅编号：</label>
            <div class="formControls col-xs-8 col-sm-5">
                <input type="text" class="input-text" value="" placeholder="" id="rId" name="roomId">
            </div>
        </div><br/><br/>
        <div class="row cl">
            <label class="form-label col-xs-2 col-sm-3">行号：</label>
            <div class="formControls col-xs-4 col-sm-1">
                <input type="text" class="input-text" value="1" placeholder="" id="rownum" name="rownum">
            </div>
            <label class="form-label col-xs-4 col-sm-1">座位：</label>
            <div class="formControls col-xs-8 col-sm-3">
                <input type="text" class="input-text" value="" placeholder="" id="sit" name="sit">
            </div>
            <a id="addBtn" class="btn btn-primary add radius" type="button"><i class="Hui-iconfont">&#xe600;</i> 添加</a>
            <a id="saveBtn" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存</a>
            <a onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</a>
        </div>
    </form>
</div>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>

<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script>
    var count=$("#count").val();
    var form = $("#form-actor-add");
    $(".add").click(function () {
        var rowNum=$(".row").length-1;
        if (rowNum>=10){
            alert("最多添加十行！");
            return false;
        }
        count++;
        form.append('<div class="row c'+count+'">\n' +
            '            <label class="form-label col-xs-2 col-sm-3">行号：</label>\n' +
            '            <div class="formControls col-xs-4 col-sm-1">\n' +
            '                <input type="text" class="input-text" value="'+count+'" placeholder="" id="rownum" name="rownum">\n' +
            '            </div>\n' +
            '            <label class="form-label col-xs-4 col-sm-1">座位：</label>\n' +
            '            <div class="formControls col-xs-8 col-sm-3">\n' +
            '                <input type="text" class="input-text" value="" placeholder="" id="sit" name="sit">\n' +
            '            </div>\n' +
            '            <a id="delBtn'+count+'" class="btn btn-danger radius" onclick="deleteRow(this)" rowNum="'+count+'"><i class="Hui-iconfont">&#xe6e2;</i> 删除</a>\n' +
            '        </div>');
        $("#count").val(count);
    })
    function deleteRow(obj) {
        thisObj=$(obj);
        $("#count").val(count--);
        thisObj.parent().remove();
    }
    var rowNumArr=document.getElementsByName("rownum");
    var sitArr=document.getElementsByName("sit");
    var rowSit = new Array();
    var rowNum = new Array();


    $("#saveBtn").click(function () {
        var flag=true;
        $("input[type$='text']").each(function(){
            if($(this).val()=="") {
                alert("必填项不能为空！");
                flag=false;
                return false;
            }
        });
        if (flag){
            var roomId = $("#rId").val();
            $.ajax({
                type: 'POST',
                url: 'room/queryRoomIdIsExist',
                data: "roomId="+roomId,
                dataType:"text",
                async: false,
                success: function(data){
                    if(data=="success"){
                        for (var i = 0; i < sitArr.length; i++) {
                            rowSit[i]=sitArr[i].value;
                            rowNum[i]=rowNumArr[i].value
                        }
                        $.ajax({
                            type: 'POST',
                            url: 'room/addRoom',
                            data: "rowSit="+rowSit+"&rowNum="+rowNum+"&roomId="+roomId,
                            dataType:"text",
                            success: function(data){
                                if(data=="success"){
                                    alert("新增成功！");
                                    window.parent.location.reload();
                                }
                                else {
                                    alert("系统繁忙");
                                    return false;
                                }
                            }
                        });
                    }
                    else {
                        alert("该影厅编号已存在！");
                        return false;
                    }
                }
            });
        }

    })
</script>
</body>
</html>