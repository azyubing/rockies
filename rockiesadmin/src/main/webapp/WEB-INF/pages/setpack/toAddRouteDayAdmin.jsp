<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
%>    
<!DOCTYPE html>
<html>
<head>
<link href='<%=basePath%>assets/fullcalender/fullcalendar.min.css' rel='stylesheet' />

</head>
<body>

<div class="main-content" style="margin-top: 60px;">
<div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
		<ul class="breadcrumb">
			<li>
				<span style="color: #669FC7;">商品管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">套餐管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">套餐行程管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">套餐行程设定</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">套餐行程设定</h3>
				<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">套餐信息：</h3>
					  </div>
					  <div class="panel-body">
					  		<div class="row">
					  			<div class="col-md-4"><label>套餐名称：</label>${row.pname }</div>
					  			<div class="col-md-4"><label>套餐英文名：</label>${row.pname_en }</div>
					  			<div class="col-md-4"><label>套餐编号：</label>${row.pid }</div>
					  		</div>
					  </div>
				</div>
			</div>
		</div>
		
		<!-- roomPrice -->
		<div class="row">
			<div class="col-md-12">
				<div class="widget-box">
					
					<div class="widget-body">
						<div class="widget-main">
							<form action="<%=basePath%>setpack/addRouteDayAdmin.html" id="addForm" name="addForm" method="post">
								<div class="form-group " >
									<label class="control-label" for="form-input-readonly" style="border-bottom: 1px dotted #478fca;">
										<h4>套餐行程概览(第<input type="text" id="pday" name="pday" value="${pday}" style="width: 30px;"/>天)</h4>
									</label> 
								</div>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;标签： </label>
										<input id="tabs" name="tabs" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
										<span style="color: red;">*多个标签用|隔开。</span>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;途径地址： </label>
										<input id="mapAddress" name="mapAddress" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地图纬度： </label>
										<input id="mapLat" name="mapLat" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
										<a href="http://www.google.cn/maps/" target="_blank" alt="哈哈">点击获取经纬度</a>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地图经度： </label>
										<input id="mapLng" name="mapLng" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-12">
										<label class="" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;套餐行程描述： </label>
										<textarea id="description" name="description" class="form-control" rows="3"  style="width: 83%;margin-left: 25px;"></textarea>
									</div>
								</div>
								<br>
								<input type="hidden" id="pid" name="pid" value="${row.pid }" />
								
								<input type="hidden" id="menu" name="menu" value="${menu }" />
								<input type="hidden" id="attachedImgurl" name="img1" />
							</form>
							<form action="<%=basePath%>img/uploadFileList.html" id="attachedImgFileForm" method="post" enctype="multipart/form-data" >	
								<div class="row">
									<div class="col-md-12 attachedImg">
										<label class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;套餐行程附属图（多选）<span style="color:red">(需要上传宽高比列为2:1的图片，用于详细页面滚动显示)</span>： </label>
										<div class="col-md-12" style="margin-left: 13px;">
											<input class="id-input-file-3" type="file" id="uploadList" name="uploadList" multiple=""  style="width: 85%;"/>
											<button id="attachedImgBtn" type="button" class="btn btn-sm btn-success" ><i class="fa fa-upload" ></i> 上传 </button>
											<div id="attachedImgMsg"></div>
										</div>
									</div>
								</div>
								<br>
							</form>
							<div class="row">
								<div class="col-md-12" id="attachedImgPreview">
									
									
								</div>
							</div>
							<br>	
							<div class="form-group col-md-12" ><div class="hr hr8 hr-double"></div></div>
							<br>
							<div class="row">
								<div class="col-md-4">&nbsp;</div>
								<div class="col-md-4">
									<div class="input-group" >
										<span class="">
											<button id="returnBtn" type="button" class="btn btn-green btn-sm">
												返回
												<i class="ace-icon fa fa-arrow-left icon-on-right bigger-110"></i>
											</button>&nbsp;&nbsp;&nbsp;
											<button id="addBtn" type="button" class="btn btn-sm btn-info">
												保存
												<i class="ace-icon fa fa-plus icon-on-right bigger-110"></i>
											</button>&nbsp;&nbsp;&nbsp;
										</span>
									</div>
								</div>
								<div class="col-md-4">&nbsp;</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		
	</div><!-- /.page-content-area -->
</div><!-- /.page-content -->

</div><!-- /.main-content -->


<script src='<%=basePath%>assets/fullcalender/fullcalendar.min.js'></script>
<script src='<%=basePath%>assets/fullcalender/zh-cn.js'></script>

<script type="text/javascript">

	$(function() { 
		
		$("#returnBtn").click(function(){
			setTimeout(function(){
				window.history.go(-1);
			},500);
		});
		
		$("#addBtn").click(function(){
			if(!inputCheck()){
				return;
			}
			shCircleLoaderLoading();
			$("#addForm").submit();
			shCircleLoaderDestroy();
		});

		
	});

	function inputCheck(){
		if($("#mapAddress").val() == null || $("#mapAddress").val().trim() == ""){
			alert("请输入途径地址！如（上海-伦敦）");
			return false;
		}
		if($("#mapLat").val() == null || $("#mapLat").val().trim() == ""){
			alert("请输入行程纬度！");
			return false;
		}
		if($("#mapLng").val() == null || $("#mapLng").val().trim() == ""){
			alert("请输入行程经度！");
			return false;
		}
		
		if($("#description").val() == null || $("#description").val().trim() == ""){
			alert("请输入行程描述！");
			return false;
		}
		if($("#pday").val() == null || $("#pday").val().trim() == ""){
			alert("请输入行程天数！");
			return false;
		}else{
			if(!isPositiveNum($("#pday").val())){
				alert("行程天数只能是数字！");
				return false;
			}
		}
		
		return true;
	}
	
	function isPositiveNum(s){//是否为正整数 
		var re = /^[0-9]*[1-9][0-9]*$/ ; 
		return re.test(s) 
	} 
</script>	
<script type="text/javascript">
	
	
	$(function() { 
		
		$("#attachedImgurl").val('');
		
		$("#attachedImgBtn").click(function(){
			
			var imgpath = $(".attachedImg #uploadList");
			$(".attachedImg #imgName").val(imgpath.val());
			if(imgpath[0].files.length >3){
				alert("只能选择3张图片");
				return;
			}
			if(isNull(imgpath.val())){
				alert("请选择图片");
				return;
			}
			shCircleLoaderLoading();
			$("#attachedImgFileForm").ajaxSubmit({
                url:'<%=basePath%>img/uploadFileList.html',
                type:'POST',
                dataType:'html',
                success: function(json){
                	var result =jQuery.parseJSON(json);
                	var errcode = result.errcode;
                	if(errcode == 0){
                		var data = result.data;
                    	$("#attachedImgurl").val(data.join(","));
                    	$("#attachedImgMsg").text("套餐行程附属图上传成功");
                    	attachedImgPreview(data.join(","));
                        
                	}else{
                		$("#attachedImgMsg").text("套餐行程附属图上传失败:"+result.errmsg);
                	}
                    shCircleLoaderDestroy();
                } 
            });
		});
		
		
	});



</script>	
<script type="text/javascript">

function attachedImgPreview(json){
	if(json == ''){
		return;
	}
	var attachedImgPreviewArray = json.split(",");
	var li_img_Str = "";
	$(attachedImgPreviewArray).each(function(index){
		li_img_Str += 
			'<li>'+
				'<a href="'+attachedImgPreviewArray[index]+'" id="attachedImgHref" title="套餐行程附属图'+(index+1)+'预览" data-rel="colorbox">'+
					'<img width="300" height="150" alt="300x150" id="attachedImgReviewImage" src="'+attachedImgPreviewArray[index]+'" />'+
					'<div class="text">'+
						'<div class="inner">套餐行程附属图'+(index+1)+'预览</div>'+
					'</div>'+
				'</a>'+
			'</li>'
	});
	$("#attachedImgPreview").empty();
	$("#attachedImgPreview").append(
		'<label class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;套餐行程附属图预览： </label>'+
		'<div class="col-md-12" style="margin-left: 13px;">'+
			'<ul class="ace-thumbnails clearfix">'+
				li_img_Str+
			'</ul>'+
		'</div>'+
		'<script type="text/javascript">'+
			'jQuery(function($){'+
			    'imgColorBox();'+
			'});'+
		'<\/script>'
	);
}

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
		
		imgColorBox();
		getMenu();
		
	});
</script>
</body>
</html>