<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
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
    <!--[if IE 6]>
    <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>修改用户信息</title>
    <link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
    <form class="form form-horizontal" id="form-actor-edit">
        <br/>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-4"><span class="c-red">*</span>影院名称：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="hidden" class="cinemaId" id="cinemaId" name="cinemaId" value="${requestScope.cinemaInfo.cinemaid}"/>
                <input type="text" class="input-text" value="${requestScope.cinemaInfo.cinemaname}" placeholder="" id="cinemaName" name="cinemaName">
            </div>
        </div><br/><br/>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-4"><span class="c-red">*</span>影院简介：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="${requestScope.cinemaInfo.cinemaintro}"  placeholder="" id="cinemaIntro" name="cinemaIntro">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-4"><span class="c-red">*</span>影院电话：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="${requestScope.cinemaInfo.cinematel}"  placeholder="" id="cinemaTel" name="cinemaTel">
            </div>
        </div><br/><br/>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-4"><span class="c-red">*</span>影院电话：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="password" class="input-text" value="${requestScope.cinemaInfo.cinemapwd}"  placeholder="" id="cinemaPwd" name="cinemaPwd">
            </div>
        </div><br/><br/>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-4"><span class="c-red">*</span>影院地址：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="${requestScope.cinemaInfo.cinemaaddr}" placeholder="" id="cinemaAddr" name="cinemaAddr">
            </div>
        </div><br/><br/>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-4"><span class="c-red">*</span>影院图片：</label>
            <div class="formControls col-xs-8 col-sm-4">
				<span class="btn-upload form-group">
                    <input class="input-text upload-url" type="text" name="uploadfile-2" id="uploadfile-2" readonly value="${requestScope.cinemaInfo.cinemaimg}" style="width:200px">
                    <a href="javascript:void(0);" class="btn btn-primary upload-btn"><i class="Hui-iconfont">&#xe642;</i> 上传图片</a>
                    <input type="file" id="file" multiple name="file" class="input-file" accept="image/*">
			    </span>
            </div>
        </div>
        <br/><br/>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-4">
                <button id="addBtn" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 修改</button>
                <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
            </div>
        </div>
    </form>
</div>


<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="lib/webuploader/0.1.5/webuploader.min.js"></script>
<script type="text/javascript">
    $(function () {
        $("#addBtn").click(function () {
            var cinemaTel=$("#cinemaTel").val();
            var cinemaId=$("#cinemaId").val();
            $("input[type$='text']").each(function(){
                if($(this).val()=="" || $("#cinemaPwd").val()=="")
                {
                    alert("必填项不能为空！");
                    return false;
                }
            });
            if(!(/^1[34578]\d{9}$/.test(cinemaTel))){
                alert("手机号码有误");
                return false;
            }
            $.ajax({
                url:"manage/checkCinemaTel",
                type:"POST",
                data:"cinematel="+cinemaTel+"&cinemaId="+cinemaId,
                dataType:"text",
                success:function(data){
                    if("success"==data){
                        var formData = new FormData($("#form-actor-edit")[0]);
                        $.ajax({
                            url:"cinemaInfo/editCinema",
                            type:"POST",
                            data:formData,
                            dataType:"text",
                            processData:false,
                            contentType : false,
                            success:function(data){
                                if("success"==data){
                                    alert("修改成功！");
                                    window.parent.location.reload();
                                }else{
                                    alert("修改失败！");
                                    return false;
                                }
                            }
                        });
                    }else {
                        alert("该手机号码已被注册");
                        return false;
                    }
                }
            });

        })
    })
</script>
</body>
</html>