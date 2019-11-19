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
    <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
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
    <title>影院资料审核</title>
    <link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
    <h2 style="text-align: center;margin-bottom: 20px;">请先完成影院资料审核</h2>
    <form class="form form-horizontal" id="form-cinemaInfo-update" enctype="multipart/form-data">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-4">影院名称：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" disabled="disabled" value="${sessionScope.cinema.cinemaname}" placeholder="" id="cinemaname" name="timeLimit">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-4">影院电话：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" disabled="disabled" value="${sessionScope.cinema.cinematel}" placeholder="" id="cinematel" name="timeLimit">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-4">影院地址：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <span class="select-box">
					<select name="cinemaAddr" class="select" id="cinemaAddr">
						<option value="">--请选择--</option>
                        <option value="岳麓区">岳麓区</option>
                        <option value="开福区">开福区</option>
                        <option value="天心区">天心区</option>
                        <option value="望城区">望城区</option>
                        <option value="芙蓉区">芙蓉区</option>
                        <option value="长沙县">长沙县</option>
                        <option value="宁乡县">宁乡县</option>
                        <option value="雨花区">雨花区</option>
                        <option value="浏阳市">浏阳市</option>
					</select>
				</span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-4">详细地址：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="" placeholder="" id="moreAddr" name="moreAddr">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-4">影院简介：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <textarea name="cinemaIntro" id="cinemaIntro" cols="" rows="" class="textarea"  placeholder="请输入影院简介..." datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" onKeyUp="wordStatic(this);"></textarea>
                <p class="textarea-numberbar" id="maxnum"><em class="textarea-length" id="num">0</em>/200</p>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-4">影院图片：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <span class="btn-upload form-group">
                    <input class="input-text upload-url" type="text" name="uploadfile-2" id="uploadfile-2" readonly style="width:200px">
                    <a href="javascript:void(0);" class="btn btn-primary upload-btn"><i class="Hui-iconfont">&#xe642;</i> 上传图片</a>
                    <input type="file" id="file" multiple name="file" class="input-file" accept="image/*">
			    </span>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-4">
                <button id="updateBtn" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 提交审核</button>
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
<script type="text/javascript">
    function article_save(){
        alert("刷新父级的时候会自动关闭弹层。")
        window.parent.location.reload();
    }

    $(function () {
        $("#updateBtn").click(function () {
            var cinemaAddr=$("#cinemaAddr").val();
            var moreAddr=$("#moreAddr").val();
            var cinemaIntro=$("#cinemaIntro").val();
            var cinemaImg=$("#uploadfile-2").val();
            if(cinemaAddr=="" || moreAddr=="" ||cinemaIntro=="" ||cinemaImg==""){
                alert("请将所有信息填写完全！！");
                return false;
            }
            var formData=new FormData($( "#form-cinemaInfo-update" )[0]);
            $.ajax({
                url:"manage/updateCinemaInfo",
                type:"POST",
                data:formData,
                dataType:"text",
                processData:false,
                contentType : false,
                success:function(data){
                    if("success"==data){
                        alert("提交审核成功，请耐心等候结果。如有问题请联系 010-88888888");
                        window.parent.location.href="wait.jsp";
                    }else{
                        alert("提交失败，请检查是否输入有误！");
                        return false;
                    }
                }
            });
        })
    })
    function wordStatic(input) {
        // 获取要显示已经输入字数文本框对象
        var content = document.getElementById('num');
        if (content && input) {
            // 获取输入框输入内容长度并更新到界面
            var value = input.value;
            // 将换行符不计算为单词数
            value = value.replace(/\n|\r/gi,"");
            // 更新计数

            if(value.length>200){
                input.value=input.value.substring(0,200);
                content.style.color='red';
                content.innerText =200;
                return;
            }
            content.style.color='black';
            content.innerText = value.length;
        }
    }
</script>
</body>
</html>