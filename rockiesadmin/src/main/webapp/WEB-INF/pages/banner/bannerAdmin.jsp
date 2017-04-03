<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath =
            request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                    + path + "/";
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>    
<!DOCTYPE html>
<html>
<head> 
<title>Insert title here</title>

</head>
<body>

<div class="main-content" style="margin-top: 60px;">
<div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
		<ul class="breadcrumb">
			<li>
				<span style="color: #669FC7;">首页管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">banner管理</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">banner管理</h3>
				<div class="widget-box">
					<div class="widget-header widget-header-small">
						<h5 class="widget-title lighter">操作</h5>
						<div class="widget-toolbar">
							<a href="#" data-action="collapse">
								<i class="ace-icon fa fa-chevron-up"></i>
							</a>
						</div>
					</div>

					<div class="widget-body">
						<div class="widget-main">
							<div class="row">
								<div class="col-md-12">
									<div class="input-group" >
										<span class="">
											<button id="addBtn" type="button" class="btn btn-sm btn-info">
												新增
												<i class="ace-icon fa fa-plus icon-on-right bigger-110"></i>
											</button>&nbsp;&nbsp;&nbsp;
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="table-header" style="margin-top: 20px;">&nbsp;</div>
				<table id="tableInfo"></table>
			</div>
		</div>
	</div><!-- /.page-content-area -->
</div><!-- /.page-content -->
<div>

</div>
</div><!-- /.main-content -->
<script type="text/javascript">
function operateFormatter(value, row, index) { 
	return [
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="detailBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 修改',
	        '</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="deleteBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 删除',
	        '</a>'
	    ].join('');
    
}


window.operateEvents = {
        'click #detailBtn': function (e, value, row, index) {
        	var id = row.id;
        	var name = row.name;
        	var desp = row.desp;
        	var sequence = row.sequence;
        	var imgPath = row.imgPath;
        	$('#updateMenu').find("input[name='id']").val(id);
        	$('#updateMenu').find("input[name='name']").val(name);
			$('#updateMenu').find("input[name='desp']").val(desp);
			$('#updateMenu').find("input[name='sequence']").val(sequence);
			$('#updateMenu').find("input[name='imgPath']").val(imgPath);
        	var mp4 = row.mediaMp4Path;
        	if(mp4!=null&&mp4!=''){
	        	var mp4Path = mp4.substr(mp4.lastIndexOf("/"));
	        	var ovg = row.mediaOgvPath;
	        	var ogvPath = ovg.substr(ovg.lastIndexOf("/"));
	        	var webm = row.mediaWebmPath;
	        	var webmPath = webm.substr(webm.lastIndexOf("/"));
				$('#updateMenu').find("input[name='mediaMp4Path']").show().val(mp4Path.substr(1,mp4Path.length));
				$('#updateMenu').find("input[name='mediaOgvPath']").show().val(ogvPath.substr(1,ogvPath.length));
				$('#updateMenu').find("input[name='mediaWebmPath']").show().val(webmPath.substr(1,webmPath.length));
        	}else{
        		$('#updateMenu').find("input[name='mediaMp4Path']").hide().val('');
				$('#updateMenu').find("input[name='mediaOgvPath']").hide().val('');
				$('#updateMenu').find("input[name='mediaWebmPath']").hide().val('');
        	}
			$('#updateMenu').find("img").attr("src",imgPath);
			imgColorBox();
			$('#updateMenu').modal('show');
        },
        'click #deleteBtn': function (e, value, row, index) {
        	var id = row.id;
        	var name = row.name;
			$.messager.confirm("提示", "是否确认删除:"+name, function() {
				shCircleLoaderLoading();
				shCircleLoaderDestroy();
				deleteRow(id);
			 });
        }
    };

function queryParams(params) {
	var param={
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
    		var url = "<%=basePath%>banner/bannerAdminSearchList.html";
    		
            $('#tableInfo').bootstrapTable({
                method: 'get',
                url: url,
		        cache: false,
		        height: 450,
		        striped: true,
		        pagination: true,
		        sidePagination: 'server',
		        queryParams: queryParams,
		        pageNumber: 1,
		        pageSize: 10,
		        pageList: [10, 20],
		        minimumCountColumns: 2,
                columns: [{
                    field: 'state',                    
                    checkbox: true
                },{
                    field: 'name',
                    title: '名称',
                    align: 'center',
                    valign: 'middle'
                }, {
	                field: 'desp',
	                title: '描述',
	                align: 'center',
	                valign: 'middle'
	            }, {
	                field: 'sequence',
	                title: '序号',
	                align: 'center',
	                valign: 'middle'
	            }, {
                    field: 'operate',
                    title: '操作',
                    align: 'center',
                    valign: 'middle',
                    clickToSelect: false,
                    formatter: operateFormatter,
                    events: operateEvents
                }]
            });
    }


	$(function() { 
		
		$(window).resize(function () {$('#tableInfo').bootstrapTable('resetView'); });
		doSearch();

		$("#addBtn").click(function(){
			$('#addMenu').find("input[name='name']").val("");
			$('#addMenu').find("input[name='desp']").val("");
			$('#addMenu').find("input[name='sequence']").val("");
			$('#addMenu').find("input[name='imgPath']").val("");
			$('#addMenu').find("input[name='file']").val("");
			$('#addMenu').find("input[name='webmMedia']").val("");
			$('#addMenu').find("input[name='ogvMedia']").val("");
			$('#addMenu').find("input[name='mp4Media']").val("");
			$("#addMenu").find("img").attr("src","<%=basePath%>assets/u210.png");
			$("#addimgName").val("");
			imgColorBox();
			$('#addMenu').modal('show');
		});
		
		$('#addMenu').on('hide.bs.modal', function () {
			
		});
		
		$('#updateMenu').on('hide.bs.modal', function () {
			
		});
		
		// 图片上传
		$(".licenceDocurlImgBtn").click(function(){
			var form = $(this).parents("form");
			var imgpath = $(form).find("input[name='uploadList']");
			if(imgpath[0].files.length >5){
				alert("只能选择5张图片");
				return;
			}
			if(isNull(imgpath.val())){
				alert("请选择图片");
				return;
			}
			shCircleLoaderLoading();
			$(form).ajaxSubmit({
                url:'<%=basePath%>img/uploadFileList.html',
                type:'POST',
                dataType:'json',
                success: function(json){
                	shCircleLoaderDestroy();
                	$(form).prev().prev().find("input[name='imgPath']").val(json.data);
                } 
            });
		});
		
		// 视频上传
		$(".licenceDocurlMediaBtn").click(function(){
			var form = $(this).parents("form");
			var webmMedia = $(form).find("input[name='webmMedia']").val();
			var ogvMedia = $(form).find("input[name='ogvMedia']").val();
			var mp4Media = $(form).find("input[name='mp4Media']").val();
			var flag = true;
			if(webmMedia!=null&&ogvMedia!=null&&mp4Media!=null&&webmMedia!=''&&ogvMedia!=''&&mp4Media!=''){
				flag = true;
			}else{
				flag=false;
			}
			if(!flag){
				alert("如上传视频，须同时上传三种视频");
				return false;
			}
			var webm = webmMedia.substr(webmMedia.indexOf("."));
			var ogv = ogvMedia.substr(ogvMedia.indexOf("."));
			var mp4 = mp4Media.substr(mp4Media.indexOf("."));
			if(webm!=".webm"){
				alert("上传webm格式视频， 视频格式错误");
				return false;
			}
			if(ogv!=".ogv"){
				alert("上传ogv格式视频， 视频格式错误");
				return false;
			}
			if(mp4!=".mp4"){
				alert("上传mp4格式视频， 视频格式错误");
				return false;
			}
			shCircleLoaderLoading();
			$(form).ajaxSubmit({
                url:'<%=basePath%>bannerMedia/uploadbannerMediaFileList.html',
                type:'POST',
                dataType:'json',
                success: function(json){
                	for(var i=0;i<json.length;i++){
                		var str = json[i].substr(json[i].indexOf("."));
                		if(str==".webm"){
            				$(form).prev().find("input[name='mediaWebmPath']").val(json[i]);
            			}
            			if(str==".ogv"){
            				$(form).prev().find("input[name='mediaOgvPath']").val(json[i]);
            			}
            			if(str==".mp4"){
            				$(form).prev().find("input[name='mediaMp4Path']").val(json[i]);
            			}
                	}
                	shCircleLoaderDestroy();
                } 
            });
		});
		
		
	});

	function submitForm(){
		var mediaWebmPath = $("#addMenuForm").find("input[name='mediaWebmPath']").val();
		var mediaOgvPath = $("#addMenuForm").find("input[name='mediaOgvPath']").val();
		var mediaMp4Path = $("#addMenuForm").find("input[name='mediaMp4Path']").val();
		var flag=true;
		if(mediaWebmPath!=''&&mediaOgvPath!=''&&mediaMp4Path!=''){
			flag=true;
		}else if(mediaWebmPath==''&&mediaOgvPath==''&&mediaMp4Path==''){
			flag=true;
		}else{
			flag=false;
		}
		if(!flag){
			alert("如上传视频，须同时上传三种视频");
			return false;
		}
		
		var imgPath = $('#addMenuForm').find("input[name='imgPath']").val();
		if(imgPath==null||imgPath==''){
			alert("请先上传图片");
			return false;
		}
		var sequence = $('#addMenuForm').find("input[name='sequence']").val();
		if(!isInteger(sequence)){
			alert("序号请输入正整数");
			return false;
		}
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>banner/saveOrUpdateBanner.html',    
            data:$('#addMenuForm').serialize(),    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("添加成功");
            		$('#addMenu').modal('hide');
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>banner/bannerAdminSearchList.html"});
            	}
            }    
        });
	}
	
	function updatesubmitForm(){
		var mediaWebmPath = $("#updateMenuForm").find("input[name='mediaWebmPath']").val();
		var mediaOgvPath = $("#updateMenuForm").find("input[name='mediaOgvPath']").val();
		var mediaWebmPath = $("#updateMenuForm").find("input[name='mediaWebmPath']").val();
		var flag=true;
		if(mediaWebmPath!=''&&mediaOgvPath!=''&&mediaWebmPath!=''){
			flag=true;
		}else if(mediaWebmPath==''&&mediaOgvPath==''&&mediaWebmPath==''){
			flag=true;
		}else{
			flag=false;
		}
		if(!flag){
			alert("如上传视频，须同时上传三种视频");
			return false;
		}
		
		var imgPath = $('#updateMenuForm').find("input[name='imgPath']").val();
		if(imgPath==null||imgPath==''){
			alert("请先上传图片");
			return false;
		}
		var sequence = $('#updateMenuForm').find("input[name='sequence']").val();
		if(!isInteger(sequence)){
			alert("序号请输入正整数");
			return false;
		}
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>banner/saveOrUpdateBanner.html',    
            data:$('#updateMenuForm').serialize(),    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("修改成功");
            		$('#updateMenu').modal('hide');
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>banner/bannerAdminSearchList.html"});
            	}
            }    
        });
	}
	
	function deleteRow(id){
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>banner/deleteBanner.html',    
            data:{"id":id},    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("删除成功");
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>banner/bannerAdminSearchList.html"});
            	}
            }    
        });
	}
	
	function setImage(obj){
		var path = URL.createObjectURL(obj.files[0]);
		$(obj).parent().parent().parent().find("img").attr("src",path);
	}
	
	
	
</script>	
<div class="row" >	
	<!-- add -->
	<div class="modal fade" id="addMenu" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">新增</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="" method="post" id="addMenuForm">
						<input name="imgPath" type="hidden"/>
						<input name="mediaMp4Path" type="hidden"/>
						<input name="mediaOgvPath" type="hidden"/>
						<input name="mediaWebmPath" type="hidden"/>
						<br>
						<div class="form-group">
							<label>名称 ：</label> 
							<input class="form-control"  name="name" type="text">
						</div>
						<div class="form-group">
							<label>描述 ：</label> 
							<input class="form-control"  name="desp" type="text">
						</div>
						<div class="form-group">
							<label>序号：</label> 
							<input class="form-control"  name="sequence" type="text">
						</div>
					</form>
					<form action="" id="addMenuMedia" method="post" enctype="multipart/form-data" >
				    	<div class="form-group" >
							<label class="control-label" for="form-input-readonly">
								<h4>banner视频：</h4>
							</label> 
						</div>
						<div class="form-group col-md-12 licenceDocurl" >
							<div class="col-md-8" style=" margin-left: -20px;">
								webm格式视频
								<input type="file" name="webmMedia" class="id-input-file-2" onchange="setImage(this)" style="display: block; border: 1px solid #333;"/>
								<input name="mediaWebmPath" type="hidden">
							</div>
							<div class="col-md-8" style=" margin-left: -20px;">
								ogv格式视频
								<input type="file" name="ogvMedia" class="id-input-file-2" onchange="setImage(this)" style="display: block; border: 1px solid #333;"/>
								<input name="mediaOgvPath" type="hidden">
							</div>
							<div class="col-md-8" style=" margin-left: -20px;">
								mp4格式视频
								<input type="file" name="mp4Media" class="id-input-file-2" onchange="setImage(this)" style="display: block; border: 1px solid #333;"/>
								<input name="mediaMp4Path" type="hidden">
							</div>
							<div class="col-md-4">
								<button  type="button" class="btn btn-sm btn-success licenceDocurlMediaBtn"><i class="fa fa-upload" ></i> 上传 </button>
							</div>
						</div>
					</form>
					
					<form action="" id="addMenuImg" method="post" enctype="multipart/form-data" >
				    	<div class="form-group" >
							<label class="control-label" for="form-input-readonly">
								<h4>banner图片</h4>
							</label> 
						</div>
						<div class="form-group col-md-12 licenceDocurl" >
							<div class="col-md-8" style=" margin-left: -20px;">
								<input type="file" name="uploadList" class="id-input-file-2" onchange="setImage(this)" style="display: block; border: 1px solid #333;"/>
								<input name="imgName" type="hidden">
							</div>
							<div class="col-md-4">
								<button  type="button" class="btn btn-sm btn-success licenceDocurlImgBtn"><i class="fa fa-upload" ></i> 上传 </button>
							</div>
						</div>
						<div class="form-group" >
							<ul class="ace-thumbnails clearfix">
								<li>
									<a href="<%=basePath%>assets/u210.png"  title="banner图片" data-rel="colorbox">
										<img width="150" height="150" alt="150x150"  src="<%=basePath%>assets/u210.png" />
										<div class="text">
											<div class="inner">banner图片 </div>
										</div>
									</a>
								</li>
							</ul>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-info" type="button" id="addBtn" onclick="submitForm();">
						<i class="ace-icon fa fa-check bigger-110"></i>
						增加
					</button>
					&nbsp; &nbsp; &nbsp;
					<button class="btn" type="button" class="btn btn-default" data-dismiss="modal">
						<i class="ace-icon fa fa-undo bigger-110"></i>
						关闭
					</button>
				</div>
				<div class="modal-body" id="addMsg" style="color: red;"></div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</div>	
<div class="row" >	
	<!-- update -->
	<div class="modal fade" id="updateMenu" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">修改</h4>
				</div>
				<div class="modal-body">
					<form role="form" action="" method="post" id="updateMenuForm">
						<input name="id" type="hidden"/>
						<input name="imgPath" type="hidden"/>
						<input name="mediaMp4Path" type="hidden"/>
						<input name="mediaOgvPath" type="hidden"/>
						<input name="mediaWebmPath" type="hidden"/>
						<br>
						<div class="form-group">
							<label>名称 ：</label> 
							<input class="form-control"  name="name" type="text">
						</div>
						<div class="form-group">
							<label>描述 ：</label> 
							<input class="form-control"  name="desp" type="text">
						</div>
						<div class="form-group">
							<label>序号：</label> 
							<input class="form-control"  name="sequence" type="text">
						</div>
					</form>
					<form action="" id="updateMenuMedia" method="post" enctype="multipart/form-data" >
				    	<div class="form-group" >
							<label class="control-label" for="form-input-readonly">
								<h4>banner视频：</h4>
							</label> 
						</div>
						<div class="form-group col-md-12 licenceDocurl" >
							<div class="col-md-8" style=" margin-left: -20px;">
								webm格式视频
								<input type="file" name="webmMedia" class="id-input-file-2" onchange="setImage(this)" style="display: block; border: 1px solid #333;"/>
								<input name="mediaWebmPath" type="text">
							</div>
							<div class="col-md-8" style=" margin-left: -20px;">
								ogv格式视频
								<input type="file" name="ogvMedia" class="id-input-file-2" onchange="setImage(this)" style="display: block; border: 1px solid #333;"/>
								<input name="mediaOgvPath" type="text">
							</div>
							<div class="col-md-8" style=" margin-left: -20px;">
								mp4格式视频
								<input type="file" name="mp4Media" class="id-input-file-2" onchange="setImage(this)" style="display: block; border: 1px solid #333;"/>
								<input name="mediaMp4Path" type="text">
							</div>
							<div class="col-md-4">
								<button  type="button" class="btn btn-sm btn-success licenceDocurlMediaBtn"><i class="fa fa-upload" ></i> 上传 </button>
							</div>
						</div>
					</form>
					<form action="" id="updateMenuImg" method="post" enctype="multipart/form-data">
				    	<div class="form-group" >
							<label class="control-label" for="form-input-readonly">
								<h4>banner图片</h4>
							</label> 
						</div>
						<div class="form-group col-md-12 licenceDocurl" >
							<div class="col-md-8" style=" margin-left: -20px;">
								<input type="file" class="id-input-file-2" name="uploadList" onchange="setImage(this)" style="display: block; border: 1px solid #333;"/>
							</div>
							<div class="col-md-4">
								<button type="button" class="btn btn-sm btn-success licenceDocurlImgBtn"><i class="fa fa-upload" ></i> 上传 </button>
								<input name="imgName" type="hidden">
							</div>
						</div>
						<div class="form-group" >
							<ul class="ace-thumbnails clearfix">
								<li>
									<a href="<%=basePath%>assets/u210.png"  title="banner图片" data-rel="colorbox">
										<img width="150" height="150" alt="150x150"  src="<%=basePath%>assets/u210.png" />
										<div class="text">
											<div class="inner">banner图片 </div>
										</div>
									</a>
								</li>
							</ul>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-info" type="button" id="addBtn" onclick="updatesubmitForm();">
						<i class="ace-icon fa fa-check bigger-110"></i>
						修改
					</button>
					&nbsp; &nbsp; &nbsp;
					<button class="btn" type="button" class="btn btn-default" data-dismiss="modal">
						<i class="ace-icon fa fa-undo bigger-110"></i>
						关闭
					</button>
				</div>
				<div class="modal-body" id="updateaddMsg" style="color: red;"></div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</div>	
<script type="text/javascript">
function imgColorBox(){
	var $overflow = '';
	var colorbox_params = {
		rel: 'colorbox',
		reposition:true,
		scalePhotos:true,
		scrolling:false,
		previous:'<i class="ace-icon fa fa-arrow-left"></i>',
		next:'<i class="ace-icon fa fa-arrow-right"></i>',
		close:'&times;',
		current:'{current} of {total}',
		maxWidth:'100%',
		maxHeight:'100%',
		onOpen:function(){
			$overflow = document.body.style.overflow;
			//document.body.style.overflow = 'hidden';
		},
		onClosed:function(){
			document.body.style.overflow = $overflow;
		},
		onComplete:function(){
			$.colorbox.resize();
		}
	};

	$('.ace-thumbnails [data-rel="colorbox"]').colorbox(colorbox_params);
	$("#cboxLoadingGraphic").html("<i class='ace-icon fa fa-spinner orange'></i>");//let's add a custom loading icon
}
</script>
<script type="text/javascript">
	$(function() { 
		getMenu();
		
		
	});
</script>
</body>
</html>