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
				<span style="color: #669FC7;">交通管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">交通包车价格管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">价格设定</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">价格设定</h3>
				<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">交通包车信息：</h3>
					  </div>
					  <div class="panel-body">
					  		<div class="row">
					  			<div class="col-md-4"><label>交通包车名称：</label>${res.pname }</div>
					  			<div class="col-md-4"><label>交通包车英文名：</label>${res.pname_en }</div>
					  			<div class="col-md-4"><label>交通包车编号：</label>${res.pid }</div>
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
							<form action="<%=basePath%>traffic/updateTrafficCharteredPriceAdmin.html" id="updateForm" name="updateForm" method="post">
								<div class="form-group " >
									<label class="control-label" for="form-input-readonly" style="border-bottom: 1px dotted #478fca;">
										<h4>交通包车价格概览</h4>
									</label> 
								</div>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;车辆品牌： </label>
										<input id="carbrand" name="carbrand" value="${row.carbrand }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;最大乘坐人数： </label>	
										<div style="display: inline-block;   position: relative; top: 8px;">
											<input id="maxpeople" name="maxpeople" value="${row.maxpeople }" type="text" class="input-mini spinner3" value="0" />
										</div>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;开始时间：</label>
										<input id="validStarttime" name="validStarttime" value="${row.validStarttime }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
										<span style="color: red;">*输入的时间格式如:09:30</span>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;结束时间：</label>
										<input id="validEndtime" name="validEndtime" value="${row.validEndtime }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
										<span style="color: red;">*输入的时间格式如:09:30</span>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价格： </label>
										<input id="price" name="price" value="${row.price }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;预付款： </label>
										<input id="prepay" name="prepay" value="${row.prepay }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;包车时长： </label>
										<input id="hourCnt" name="hourCnt" value="${row.hourCnt }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-6">
										<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;包车排序： </label>
										<input id="orderNum" name="orderNum" value="${row.orderNum }" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
								</div>
								<br>
								<div class="row">
									<div class="col-md-12">
										<label class="" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;行李说明： </label>
										<textarea id="luggage" name="luggage" class="form-control" rows="3"  style="width: 83%;margin-left: 25px;">${row.luggage }</textarea>
									</div>
								</div>
								<br>
								<input type="hidden" id="attachedImgurl" name="carImg" />
								<input type="hidden" id="pid" name="pid" value="${row.pid }" />
								<input type="hidden" id="menu" name="menu" value="${menu }" />
								<input type="hidden" id="id" name="id" value="${row.id }"/>"
							</form>
							<form action="<%=basePath%>img/uploadFileList.html" id="attachedImgFileForm" method="post" enctype="multipart/form-data" >	
								<div class="row">
									<div class="col-md-12 attachedImg">
										<label class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交通包车附属图（单选）<span style="color:red">(需要上传宽高比列为2:1的图片，用于详细页面滚动显示)</span>： </label>
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
											<button id="updateBtn" type="button" class="btn btn-sm btn-info">
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
		
		$("#updateBtn").click(function(){
			if(isNull($("#carbrand").val())){
				alert("请输入车辆品牌");
				return;
			}
			if($("#maxpeople").val() == 0){
				alert("请设定最大乘坐人数");
				return;
			}
			if($("#validStarttime").val() == null || $("#validStarttime").val().trim() == "" || $("#validStarttime").val().indexOf(':') != 2){
				alert("请输入项目格式为“09:00”的价格有效开始时间！");
				return false;
			}
			if($("#validEndtime").val() == null || $("#validEndtime").val().trim() == "" || $("#validEndtime").val().indexOf(':') != 2){
				alert("请输入项目格式为“09:00”的价格有效开始时间！");
				return false;
			}
			
			if(isNull($("#price").val())){
				alert("请输入价格");
				return;
			}
			
			if(isNaN($("#price").val())){
				alert("价格必须为半角数字");
				return;
			}
			
			if(isNull($("#prepay").val())){
				alert("请输入预付款");
				return;
			}
			
			if(isNaN($("#prepay").val())){
				alert("预付款必须为半角数字");
				return;
			}
			
			if(isNull($("#hourCnt").val())){
				alert("请输入此价格的包车时长");
				return;
			}
			
			if(isNaN($("#hourCnt").val())){
				alert("包车时长必须为半角数字");
				return;
			}
			shCircleLoaderLoading();
			$("#updateForm").submit();
			shCircleLoaderDestroy();
		});

		
	});


	
	
</script>	
<script type="text/javascript">
	
	
	$(function() { 
		
		attachedImgPreview('${row.carImg}');

		$("#attachedImgurl").val('${row.carImg}');
		
		$("#attachedImgBtn").click(function(){
			
			var imgpath = $(".attachedImg #uploadList");
			$(".attachedImg #imgName").val(imgpath.val());
			if(imgpath[0].files.length >1){
				alert("只能选择1张图片");
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
                    	$("#attachedImgMsg").text("交通包车附属图上传成功");
                    	attachedImgPreview(data.join(","));
                        
                	}else{
                		$("#attachedImgMsg").text("交通包车附属图上传失败:"+result.errmsg);
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
				'<a href="'+attachedImgPreviewArray[index]+'" id="attachedImgHref" title="交通包车附属图'+(index+1)+'预览" data-rel="colorbox">'+
					'<img width="300" height="150" alt="300x150" id="attachedImgReviewImage" src="'+attachedImgPreviewArray[index]+'" />'+
					'<div class="text">'+
						'<div class="inner">交通包车附属图'+(index+1)+'预览</div>'+
					'</div>'+
				'</a>'+
			'</li>'
	});
	$("#attachedImgPreview").empty();
	$("#attachedImgPreview").append(
		'<label class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;交通包车附属图预览： </label>'+
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