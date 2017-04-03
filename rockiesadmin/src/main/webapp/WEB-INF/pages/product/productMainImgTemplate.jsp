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
<form action="<%=basePath%>img/uploadFileList.html" id="mainImgFileForm" method="post" enctype="multipart/form-data" >	
	<div class="row">
		<div class="col-md-12">
			<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pTypeName }主图<span style="color:red">(需要上传比列为1:1的图片，用于首页设定更多套餐时显示)</span>： </label>
			<div class="col-md-12 mainImg" >
				<div class="col-md-4">
					<input type="file" class="id-input-file-2" id="uploadList" name="uploadList" style="display: block; border: 1px solid #333;"/>
					<input name="name" id="imgName" type="hidden">
				</div>
				<div class="col-md-8">
					<button id="mainImgBtn" type="button" class="btn btn-sm btn-success" ><i class="fa fa-upload" ></i> 上传 </button>
					<div id="mainImgMsg"></div>
				</div>
			</div>
		</div>
	</div>
	<br>
</form>
<div class="row">
	<div class="col-md-12" id="mainImgPreview">
		
	</div>
</div>
<br>
<script type="text/javascript">
	
	
	$(function() { 
		mainImgPreview('${row.list_img}');
		$("#mainImgurl").val('${row.list_img}');
		
		$("#mainImgBtn").click(function(){
			
			var imgpath = $(".mainImg #uploadList");
			$(".mainImg #imgName").val(imgpath.val());
			
			if(isNull(imgpath.val())){
				alert("请选择图片");
				return;
			}
			shCircleLoaderLoading();
			$("#mainImgFileForm").ajaxSubmit({
                url:'<%=basePath%>img/uploadFileList.html',
                type:'POST',
                dataType:'html',
                success: function(json){
                	var result =jQuery.parseJSON(json);
                	var errcode = result.errcode;
                	if(errcode == 0){
                		var data = result.data;
                    	$("#mainImgurl").val(data);
                    	$("#mainImgMsg").text("${pTypeName }主图上传成功");
                    	mainImgPreview(data);                        
                	}else{
                		$("#mainImgMsg").text("${pTypeName }附属图上传失败:"+result.errmsg);
                	}
                	
                    shCircleLoaderDestroy();
                } 
            });
		});

	});



</script>	
<script type="text/javascript">

function mainImgPreview(json){
	if(json == ''){
		return;
	}
	var mainImgPreview = json;
	$("#mainImgPreview").empty();
	$("#mainImgPreview").append(
		'<label class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pTypeName }主图预览： </label>'+
		'<div class="col-md-12" style="margin-left: 13px;">'+
			'<ul class="ace-thumbnails clearfix">'+
				'<li>'+
					'<a href="'+mainImgPreview+'" id="mainImgHref" title="${pTypeName }主图预览" data-rel="colorbox">'+
						'<img width="150" height="150" alt="150x150" id="mainImgReviewImage" src="'+mainImgPreview+'" />'+
						'<div class="text">'+
							'<div class="inner">${pTypeName }主图预览</div>'+
						'</div>'+
					'</a>'+
				'</li>'+
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
