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
    <title>新增演员</title>
    <link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
    <form class="form form-horizontal" id="form-role-add">
        <br/>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-4">角色名称：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <input type="text" class="input-text" value="" placeholder="" id="roleName" name="roleName">
            </div>
        </div>
        <br/>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-4">演员名称：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <span class="select-box">
					<select name="actorId" class="select">
						<c:forEach items="${actorInfos }" var="actor">
                            <option value="${actor.actorid }">${actor.actorname }</option>
                        </c:forEach>
					</select>
				</span>
            </div>
        </div>
        <br/>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-4">电影名称：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <span class="select-box">
					<select name="movieId" class="select">
						<c:forEach items="${movieInfos }" var="movie">
                            <option value="${movie.movieid }">${movie.moviename }</option>
                        </c:forEach>
					</select>
				</span>
            </div>
        </div>
        <br/>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-4">角色等级：</label>
            <div class="formControls col-xs-8 col-sm-4">
                <span class="select-box">
					<select name="isImportant" class="select">
                        <option value="0">导演</option>
                        <option value="1">主演</option>
                        <option value="2">其他演员</option>
					</select>
				</span>
            </div>
        </div>
        <br/>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-4">
                <button id="addBtn" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 新增</button>
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
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript">
    function article_save(){
        alert("刷新父级的时候会自动关闭弹层。")
        window.parent.location.reload();
    }
    $(function () {
        $("#addBtn").click(function () {
            $.ajax({
                url:"role/addRole",
                type:"POST",
                data:$("#form-role-add").serialize(),
                dataType:"text",
                success:function(data){
                    if("success"==data){
                        alert("添加成功！");
                        window.parent.location.reload();
                    }else{
                        alert("添加失败！");
                        return false;
                    }
                }
            });

        })
    })
</script>
</body>
</html>