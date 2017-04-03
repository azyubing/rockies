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
<form action="<%=basePath%>img/uploadFileList.html" id="homeAndListPageImgFileForm" method="post" enctype="multipart/form-data" >	
	<div class="row">
		<div class="col-md-12">
			<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pTypeName }首页和list页显示的图片<span style="color:red">(需要上传比列为2:1的图片，用于首页和list页显示)</span>： </label>
			<div class="col-md-12 homeAndListPageImg" >
				<div class="col-md-4">
					<input type="file" class="id-input-file-2" id="uploadList" name="uploadList" style="display: block; border: 1px solid #333;"/>
					<input name="name" id="imgName" type="hidden">
				</div>
				<div class="col-md-8">
					<button id="homeAndListPageImgBtn" type="button" class="btn btn-sm btn-success" ><i class="fa fa-upload" ></i> 上传 </button>
					<div id="homeAndListPageImgMsg"></div>
				</div>
			</div>
		</div>
	</div>
	<br>
</form>
<div class="row">
	<div class="col-md-12" id="homeAndListPageImgPreview">
		
	</div>
</div>
<br>
<script type="text/javascript">
	
	
	$(function() { 
		homeAndListPageImgPreview('${row.size_img2}');
		$("#homeAndListPageImgurl").val('${row.size_img2}');
		
		$("#homeAndListPageImgBtn").click(function(){
			
			var imgpath = $(".homeAndListPageImg #uploadList");
			$(".homeAndListPageImg #imgName").val(imgpath.val());
			
			if(isNull(imgpath.val())){
				alert("请选择图片");
				return;
			}
			shCircleLoaderLoading();
			$("#homeAndListPageImgFileForm").ajaxSubmit({
                url:'<%=basePath%>img/uploadFileList.html',
                type:'POST',
                dataType:'html',
                success: function(json){
                	
                	var result =jQuery.parseJSON(json);
                	var errcode = result.errcode;
                	if(errcode == 0){
                		var data = result.data;
                		$("#homeAndListPageImgurl").val(data.join(","));
                    	$("#homeAndListPageImgMsg").text("${pTypeName }首页和list页显示的图片上传成功");
                    	homeAndListPageImgPreview(data.join(","));                        
                	}else{
                		$("#homeAndListPageImgMsg").text("${pTypeName }附属图上传失败:"+result.errmsg);
                	}
                	
                    shCircleLoaderDestroy();
                } 
            });
		});

	});



</script>	
<script type="text/javascript">

function homeAndListPageImgPreview(json){
	if(json == ''){
		return;
	}
	var homeAndListPageImgPreview = json;
	$("#homeAndListPageImgPreview").empty();
	$("#homeAndListPageImgPreview").append(
		'<label class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pTypeName }首页和list页显示的图片预览： </label>'+
		'<div class="col-md-12" style="margin-left: 13px;">'+
			'<ul class="ace-thumbnails clearfix">'+
				'<li>'+
					'<a href="'+homeAndListPageImgPreview+'" id="homeAndListPageImgHref" title="${pTypeName }首页和list页显示的图片预览" data-rel="colorbox">'+
						'<img width="300" height="150" alt="300x150" id="homeAndListPageImgReviewImage" src="'+homeAndListPageImgPreview+'" />'+
						'<div class="text">'+
							'<div class="inner">${pTypeName }首页和list页显示的图片预览</div>'+
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
