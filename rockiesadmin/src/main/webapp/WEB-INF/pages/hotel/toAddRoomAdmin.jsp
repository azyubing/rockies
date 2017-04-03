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
<title>Insert title here</title>

</head>
<body>

<div class="main-content" style="margin-top: 60px;">
<div class="breadcrumbs breadcrumbs-fixed" id="breadcrumbs">
		<ul class="breadcrumb">
			<li>
				<span style="color: #669FC7;">商品管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">酒店管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">房型列表</span>
			</li>
			<li>
				<span style="color: #669FC7;">房型新增</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">房型新增</h3>
				<div class="widget-box">
					
					<div class="widget-body">
						<div class="widget-main">
							<form action="<%=basePath%>hotel/addRoomAdmin.html" id="addForm" name="addForm" method="post">
								<div class="form-group " >
									<label class="control-label" for="form-input-readonly" style="border-bottom: 1px dotted #478fca;">
										<h4>房型概览</h4>
									</label> 
								</div>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;房型名称： </label>
										<input id="roomTypeName" name="roomTypeName" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;房型状态： </label>
										<select id="roomStatus" name="roomStatus" class="form-control" style="display: inline-block; width: 50%;"/>  
											<option value="0">有效</option>
											<option value="1">维修</option>
											<option value="2">暂停</option>
						                </select>
									</div>
								</div>
								<br>
								<!-- <div class="row">
									<div class="col-md-12">
										<label class="" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;房型描述： </label>
										<textarea id="description" name="description" class="form-control" rows="3"  style="width: 83%;margin-left: 25px;"></textarea>
									</div>
								</div>
								<br> -->
								<div class="row">
									<div class="col-md-12">
										<label class="" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;房型设施<span style="color: red;">*多项设施请以|隔开输入</span>： </label>
										<textarea id="roomInfra" name="roomInfra" class="form-control" rows="3"  style="width: 83%;margin-left: 25px;"></textarea>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;房型排序： </label>
										<input id="orderNum" name="orderNum" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;是否页面展示： </label>
										<select id="showInpage" name="showInpage" class="form-control" style="display: inline-block; width: 50%;"/>  
											<option value="0">否</option>
											<option value="1">是</option>
						                </select>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class="">可入住成人数： </label>	
										<div style="display: inline-block;   position: relative; top: 8px;">
											<input id="adultCnt" name="adultCnt" type="text" class="input-mini spinner3" value="0" />
										</div>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;可入住儿童数： </label>
										<div style="display: inline-block;   position: relative; top: 8px;">
											<input id="childCnt" name="childCnt" type="text" class="input-mini spinner3" value="0" />
										</div>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class="">最大加床数数： </label>	
										<div style="display: inline-block;   position: relative; top: 8px;">
											<input id="maxExtrabed" name="maxExtrabed" type="text" class="input-mini spinner3" value="0" />
										</div>
									</div>
								</div>
								<br>
								<input type="hidden" id="attachedImgurl" name="roomImg1" />
								<input type="hidden" id="pid" name="pid" value="${pid }" />
								<input type="hidden" id="menu" name="menu" value="${menu }" />
							</form>
							<form action="<%=basePath%>img/uploadFileList.html" id="attachedImgFileForm" method="post" enctype="multipart/form-data" >	
								<div class="row">
									<div class="col-md-12 attachedImg">
										<label class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;房型图（多选）<span style="color: red;">*用于酒店详细页面房型介绍部分显示，图片宽高比利 2:1</span>： </label>
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
<div>

</div>
</div><!-- /.main-content -->
<script type="text/javascript">

	$(function() { 
		
		$("#attachedImgBtn").click(function(){
			
			var imgpath = $(".attachedImg #uploadList");
			$(".attachedImg #imgName").val(imgpath.val());
			if(imgpath[0].files.length >7){
				alert("只能选择7张图片");
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
                    	$("#attachedImgMsg").text("房型图上传成功");
                    	attachedImgPreview(data.join(","));
                        
                	}else{
                		$("#attachedImgMsg").text("房型图上传失败:"+result.errmsg);
                	}
                    shCircleLoaderDestroy();
                } 
            });
		});

		$("#addBtn").click(function(){
			if(isNull($("#roomTypeName").val())){
				alert("请输入房型名称");
				return;
			}
			if($("#adultCnt").val() == 0){
				alert("请设定可入住成人数");
				return;
			}
			shCircleLoaderLoading();
			$("#addForm").submit();
			shCircleLoaderDestroy();
		});
		
		$("#returnBtn").click(function(){
			setTimeout(function(){
				window.history.go(-1);
			},500);
		});
		
		$('#addMenu').on('hide.bs.modal', function () {
			
		});
		
		$('#updateMenu').on('hide.bs.modal', function () {
			
		});
		
		
	});

	function submitForm(){
		
	}
	
	function updatesubmitForm(){
		
	}

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
				'<a href="'+attachedImgPreviewArray[index]+'" id="attachedImgHref" title="房型图'+(index+1)+'预览" data-rel="colorbox">'+
					'<img width="300" height="150" alt="300x150" id="attachedImgReviewImage" src="'+attachedImgPreviewArray[index]+'" />'+
					'<div class="text">'+
						'<div class="inner">房型图'+(index+1)+'预览</div>'+
					'</div>'+
				'</a>'+
			'</li>'
	});
	$("#attachedImgPreview").empty();
	$("#attachedImgPreview").append(
		'<label class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;房型图预览： </label>'+
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
		
		$('.spinner3').ace_spinner({
			value:0,min:0,max:10,step:1, 
			on_sides: true, 
			icon_up:'ace-icon fa fa-plus smaller-75', 
			icon_down:'ace-icon fa fa-minus smaller-75', 
			btn_up_class:'btn-success' , 
			btn_down_class:'btn-danger'
		});
		
		imgColorBox();
		getMenu();
		
	});
</script>
</body>
</html>