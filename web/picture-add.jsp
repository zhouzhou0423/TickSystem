<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
    <base href="<%=basePath%>">
    <script src="http://www.jq22.com/jquery/vue.min.js"></script>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="Content-Type" content="multipart/form-data;charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/picture-add.css">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5shiv.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
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
    <title>新增图片</title>
    <link href="lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
	<form class="form form-horizontal" id="form-movie-add" enctype="multipart/form-data">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>影片名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="movieName" name="movieName">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">导演：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="" placeholder="" id="movieDirector" name="movieDirector">
			</div>
		</div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">主演：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="movieActor" name="movieActor">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">其他演员：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="ortherActor" name="ortherActor">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">片长：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="timeLimit" name="timeLimit">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">电影简介：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea name="movieIntro" id="movieIntro" cols="" rows="" class="textarea"  placeholder="请输入电影简介..." datatype="*10-100" dragonfly="true" nullmsg="备注不能为空！" onKeyUp="wordStatic(this);"></textarea>
                <p class="textarea-numberbar" id="maxnum"><em class="textarea-length" id="num">0</em>/500</p>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>上映时间：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" onfocus="WdatePicker({ dateFmt:'yyyyMMdd' })" id="releaseTime" class="input-text Wdate" name="releaseTime">
            </div>
        </div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>电影类型：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<span class="select-box">
				<select name="typeId" id="typeId" class="selectpicker show-tick form-control" multiple data-live-search="false">
                    <c:forEach items="${typeList }" var="type">
                        <option value="${type.typeid }">${type.type }</option>
                    </c:forEach>
				</select>
				</span>
			</div>
		</div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">出厂国家：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="nation" name="nation">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">图片上传：</label>
            <div id="app" class="formControls col-xs-8 col-sm-9">
                <div class="hello">
                    <div class="upload">
                        <div class="upload_warp">
                            <div class="upload_warp_left" @click="fileClick">
                                <img src="images/upload.png">
                            </div>
                        </div>
                        <div class="upload_warp_text">
                            选中{{imgList.length}}张文件，共{{bytesToSize(this.size)}}
                        </div>
                        <input @change="fileChange($event)" type="file" name="file" id="upload_file" multiple style="display: none" />
                        <div class="upload_warp_img" v-show="imgList.length!=0">
                            <div class="upload_warp_img_div" v-for="(item,index) of imgList">
                                <div class="upload_warp_img_div_top">
                                    <div class="upload_warp_img_div_text">
                                        {{item.file.name}}
                                    </div>
                                    <img src="images/del.png" class="upload_warp_img_div_del" @click="fileDel(index)">
                                </div>
                                <img :src="item.file.src">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button id="addBtn" class="btn btn-secondary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 新增</button>
				<button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</div>

<script>
    //  import up from  './src/components/Hello'
    var app = new Vue({
        el: '#app',
        data() {
            return {
                imgList: [],
                size: 0
            }
        },
        methods: {
            fileClick() {
                document.getElementById('upload_file').click()
            },
            fileChange(el) {
                if (!el.target.files[0].size) return;
                this.fileList(el.target);
                el.target.value = ''
            },
            fileList(fileList) {
                let files = fileList.files;
                for (let i = 0; i < files.length; i++) {
                    //判断是否为文件夹
                    if (files[i].type != '') {
                        this.fileAdd(files[i]);
                    } else {
                        //文件夹处理
                        this.folders(fileList.items[i]);
                    }
                }
            },
            //文件夹处理
            folders(files) {
                let _this = this;
                //判断是否为原生file
                if (files.kind) {
                    files = files.webkitGetAsEntry();
                }
                files.createReader().readEntries(function(file) {
                    for (let i = 0; i < file.length; i++) {
                        if (file[i].isFile) {
                            _this.foldersAdd(file[i]);
                        } else {
                            _this.folders(file[i]);
                        }
                    }
                })
            },
            foldersAdd(entry) {
                let _this = this;
                entry.file(function(file) {
                    _this.fileAdd(file)
                })
            },
            fileAdd(file) {
                //总大小
                this.size = this.size + file.size;
                let reader = new FileReader();
                reader.vue = this;
                reader.readAsDataURL(file);
                reader.onload = function() {
                    file.src = this.result;
                    this.vue.imgList.push({
                        file
                    });
                }
            },
            fileDel(index) {
                this.size = this.size - this.imgList[index].file.size; //总大小
                this.imgList.splice(index, 1);
            },
            bytesToSize(bytes) {
                if (bytes === 0) return '0 B';
                let k = 1000, // or 1024
                    sizes = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'],
                    i = Math.floor(Math.log(bytes) / Math.log(k));
                return (bytes / Math.pow(k, i)).toPrecision(3) + ' ' + sizes[i];
            },
            dragenter(el) {
                el.stopPropagation();
                el.preventDefault();
            },
            dragover(el) {
                el.stopPropagation();
                el.preventDefault();
            },
            drop(el) {
                el.stopPropagation();
                el.preventDefault();
                this.fileList(el.dataTransfer);
            }
        }
    })
</script>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath() %>/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/css/bootstrap-select.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.10.0/js/bootstrap-select.min.js"></script>
<script>
    $(window).on('load', function () {
        $('#typeid').selectpicker({
            'selectedText': 'cat'
        });
    });
</script>
<script>
    function wordStatic(input) {
        // 获取要显示已经输入字数文本框对象
        var content = document.getElementById('num');
        if (content && input) {
            // 获取输入框输入内容长度并更新到界面
            var value = input.value;
            // 将换行符不计算为单词数
            value = value.replace(/\n|\r/gi,"");
            // 更新计数

            if(value.length>500){
                input.value=input.value.substring(0,500);
                content.style.color='red';
                content.innerText =500;
                return;
            }
            content.style.color='black';
            content.innerText = value.length;

        }
    }
</script>
<script type="text/javascript">
    function article_save(){
        alert("刷新父级的时候会自动关闭弹层。")
        window.parent.location.reload();
    }
    $("#addBtn").click(function () {
        $.ajax({
            url:"movieManage/uploads",
            type:"POST",
            data:$("#form-movie-add").serialize(),
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

</script>
</body>
</html>