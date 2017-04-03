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
<form action="<%=basePath%>img/uploadFileList.html" id="attachedImgFileForm" method="post" enctype="multipart/form-data" >	
	<div class="row">
		<div class="col-md-12 attachedImg">
			<label class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pTypeName }附属图（多选）<span style="color:red">(需要上传宽高比列为2:1的图片，用于详细页面滚动显示)</span>： </label>
			<div class="col-md-12" style="margin-left: 13px;">
				<input class="id-input-file-3" type="file" id="uploadList" name="uploadList" multiple="multiple"  style="width: 85%;"/>
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
<script type="text/javascript">
	
	
	$(function() { 
		attachedImgPreview('${row.size_img1}');
		$("#attachedImgurl").val('${row.size_img1}');
		
		$("#attachedImgBtn").click(function(){
			var imgpath = $(".attachedImg #uploadList");
			$(".attachedImg #imgName").val(imgpath.val());
			if(imgpath[0].files.length >20){
				alert("只能选择20张图片");
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
                    	$("#attachedImgMsg").text("${pTypeName }附属图上传成功");
                    	attachedImgPreview(data.join(","));
                        
                	}else{
                		$("#attachedImgMsg").text("${pTypeName }附属图上传失败:"+result.errmsg);
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
				'<a href="'+attachedImgPreviewArray[index]+'" id="attachedImgHref" title="${pTypeName }附属图'+(index+1)+'预览" data-rel="colorbox">'+
					'<img width="300" height="150" alt="300x150" id="attachedImgReviewImage" src="'+attachedImgPreviewArray[index]+'" />'+
					'<div class="text">'+
						'<div class="inner">${pTypeName }附属图'+(index+1)+'预览</div>'+
					'</div>'+
				'</a>'+
			'</li>'
	});
	$("#attachedImgPreview").empty();
	$("#attachedImgPreview").append(
		'<label class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pTypeName }附属图预览： </label>'+
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
