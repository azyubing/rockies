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
				<span style="color: #669FC7;">首页管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">视频管理</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">视频管理</h3>
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
								<div class="col-md-4">
									<label class=""> 视频名称： </label>
									<input type="text" class="form-control " id="selectName" placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
								</div>
								<div class="col-md-4">
									<div class="input-group" >
										<span class="">
											<button type="button" id="search" class="btn btn-purple btn-sm">
												查询
												<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
											</button>&nbsp;&nbsp;&nbsp;
											<button id="addBtn" type="button" class="btn btn-sm btn-info">
												新增
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
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="tagBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 标签设定',
	        '</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="similarBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 相似套餐',
	        '</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="detailBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 修改',
	        '</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="deleteBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 删除',
	        '</a>'
	    ].join('');
}


window.operateEvents = {
		
		'click #tagBtn': function (e, value, row, index) {
			var id = row.id;
			var parm = "id="+id+"&menu=${menu}"
        	setTimeout(function(){
				window.location.href="<%=basePath%>media/mediaTagAdmin.html?"+parm;
			},500);
        },
        'click #similarBtn': function (e, value, row, index) {
        	var id = row.id
        	setTimeout(function(){
				window.location.href="<%=basePath%>media/mediaSimilarAdmin.html?menu=${menu}&id="+id;
			},500);
        },
        'click #detailBtn': function (e, value, row, index) {
        	$('#updateMenu').find("input[name='id']").val(row.id);
        	$('#updateMenu').find("input[name='name']").val(row.name);
        	$('#updateMenu').find("input[name='title']").val(row.title);
        	$('#updateMenu').find("input[name='remark']").val(row.remark);
        	$('#updateMenu').find("input[name='mediaUrl']").val(row.mediaUrl);
        	$('#updateMenu').find("input[name='packageUrl']").val(row.packageUrl);
        	$('#updateMenu').find("input[name='timelong']").val(row.timelong);
        	$('#updateMenu').find("input[name='img_path']").val(row.img_path);
        	$('#updateMenu').find("input[name='uploadList']").val('');
        	$('#updateMenu').find("img").attr("src",row.img_path);
        	$('#updateMenu').find("select option[value='"+row.mediaType+"']").attr("selected", "selected");
        	
			$('#updateMenu').modal('show');
        },
        'click #deleteBtn': function (e, value, row, index) {
        	var id = row.id;
        	var name= row.name;
			$.messager.confirm("提示", "是否确认删除:"+name, function() {
				shCircleLoaderLoading();
				shCircleLoaderDestroy();
				deleteForm(id);
			 });
        }
    };

function queryParams(params) {
	var param={
			name:$("#selectName").val(),
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
    		var url = "<%=basePath%>media/mediaAdminSearchList.html";
    		
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
	                field: 'title',
	                title: '标题',
	                align: 'center',
	                valign: 'middle'
	            }, {
	                field: 'remark',
	                title: '介绍',
	                align: 'center',
	                valign: 'middle'
	            },{
	                field: 'mediaTypeName',
	                title: '视频来源',
	                align: 'center',
	                valign: 'middle'
	            } ,
	            /*
	            {
	                field: 'mediaUrl',
	                title: '视频链接地址',
	                align: 'center',
	                valign: 'middle'
	            }, {
	                field: 'packageUrl',
	                title: '套餐链接地址',
	                align: 'center',
	                valign: 'middle'
	            }
	            , */
	            {
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
			$('#addMenu').modal('show');
		});
		
		$('#addMenu').on('hide.bs.modal', function () {
			
		});
		
		$('#updateMenu').on('hide.bs.modal', function () {
			
		});
		$("#search").click(function(){
			$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>media/mediaAdminSearchList.html"});
		});
		$(document).keydown(function(event){
			if(event.keyCode == "13"){
				$('#tableInfo').bootstrapTable("refresh",{url: "<%=basePath%>media/mediaAdminSearchList.html"});
			}
    	});
		
		// 图片上传
		$(".licenceDocurlImgBtn").click(function(){
			var form = $(this).parents("form");
			var imgpath = $(form).find("input[name='uploadList']");
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
                	$(form).prev().find("input[name='img_path']").val(json.data);
                } 
            });
		});
		
		
	});

	function submitForm(){
		var mediaType =$('#addMenu').find("select[name='mediaType']").val(); 
		var mediaUrl =$('#addMenu').find("input[name='mediaUrl']").val().trim(); 
		if(mediaType==''){
			alert("请选择视频来源");
			return false;
		}
		var mediaTypeName = $('#addMenu').find("select[name='mediaType']").find("option:selected").text();
		$('#addMenu').find("input[name='mediaTypeName']").val(mediaTypeName);
		if(mediaUrl==null||mediaUrl==''){
			alert("请输入视频链接地址");
			$('#addMenu').find("input[name='mediaUrl']").focus();
			return false;
		}
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>media/saveOrUpdatemediaAdmin.html',    
            data:$('#addMenuForm').serialize(),    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("添加成功");
            		$('#addMenu').find("input[name='id']").val('');
                	$('#addMenu').find("input[name='name']").val('');
                	$('#addMenu').find("input[name='title']").val('');
                	$('#addMenu').find("input[name='remark']").val('');
                	$('#addMenu').find("input[name='mediaUrl']").val('');
                	$('#addMenu').find("input[name='packageUrl']").val('');
                	$('#addMenu').find("input[name='timelong']").val('');
            		$('#addMenu').modal('hide');
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>media/mediaAdminSearchList.html"});
            	}
            }    
        });
	}
	
	function updatesubmitForm(){
		var mediaType =$('#updateMenuForm').find("select[name='mediaType']").val(); 
		var mediaUrl =$('#updateMenuForm').find("input[name='mediaUrl']").val().trim(); 
		if(mediaType==''){
			alert("请选择视频来源");
			return false;
		}
		var mediaTypeName = $('#updateMenuForm').find("select[name='mediaType']").find("option:selected").text();
		$('#updateMenuForm').find("input[name='mediaTypeName']").val(mediaTypeName);
		if(mediaUrl==null||mediaUrl==''){
			alert("请输入视频链接地址");
			$('#updateMenuForm').find("input[name='mediaUrl']").focus();
			return false;
		}
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>media/saveOrUpdatemediaAdmin.html',    
            data:$('#updateMenuForm').serialize(),    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("修改成功");
            		$('#updateMenuForm').find("input[name='name']").val('');
            		$('#updateMenu').modal('hide');
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>media/mediaAdminSearchList.html"});
            	}
            }    
        });
	}
	
	
	function deleteForm(id){
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>/media/deletemediaAdmin.html',    
            data:{"id":id},    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("删除成功");
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>media/mediaAdminSearchList.html"});
            	}
            }    
        });
	}
	
	function setImage(obj){
		var path = URL.createObjectURL(obj.files[0]);
		$(obj).parent().parent().parent().next().find("img").attr("src",path);
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
					<input type="hidden" name="mediaTypeName">
					<input type="hidden" name="img_path">
						<div class="form-group">
							<label>名称 ：</label> 
							<input class="form-control"  name="name" type="text">
						</div>
						<div class="form-group">
							<label>标题 ：</label> 
							<input class="form-control"  name="title" type="text">
						</div>
						<div class="form-group">
							<label>介绍 ：</label> 
							<input class="form-control" name="remark" type="text">
						</div>
						<div class="form-group">
							<label>视频时长（以10:59形式输入）：</label> 
							<input class="form-control" name="timelong" type="text">
						</div>
						<div class="form-group">
							<label class=""> 视频来源： </label>
							<select class="form-control"  name="mediaType" style="display: inline-block; width: 50%;"/>  
								<option value="">-请选择-</option>
								<option value="0">腾讯</option>
								<option value="1">优酷</option>
								<option value="2">搜狐</option>
								<option value="3">土豆</option>
								<option value="4">酷6</option>
			                </select>
						</div>
						<div class="form-group">
							<label>视频链接地址 ：</label> 
							<input class="form-control"  name="mediaUrl" type="text">
						</div>
						<div class="form-group">
							<label>套餐链接地址 ：</label> 
							<input class="form-control"  name="packageUrl" type="text">
						</div>
					</form>
					<form action="" id="addMenuImg" method="post" enctype="multipart/form-data" >
				    	<div class="form-group" >
							<label class="control-label" for="form-input-readonly">
								<h4>视频图片</h4>
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
									<a href="<%=basePath%>assets/u210.png"  title="视频图片" data-rel="colorbox">
										<img width="150" height="150" alt="150x150"  src="<%=basePath%>assets/u210.png" />
										<div class="text">
											<div class="inner">视频图片 </div>
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
	<!-- add -->
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
					<form role="form" action="<%=basePath%>addSiteMenu.html" method="post" id="updateMenuForm">
					<input type="hidden" name="id"/>
					<input type="hidden" name="mediaTypeName">
					<input type="hidden" name="img_path">
						<div class="form-group">
							<label>名称 ：</label> 
							<input class="form-control"  name="name" type="text">
						</div>
						<div class="form-group">
							<label>标题 ：</label> 
							<input class="form-control"  name="title" type="text">
						</div>
						<div class="form-group">
							<label>介绍 ：</label> 
							<input class="form-control" name="remark" type="text">
						</div>
						<div class="form-group">
							<label>视频时长（以10:59形式输入）：</label> 
							<input class="form-control" name="timelong" type="text">
						</div>
						<div class="form-group">
							<label class=""> 视频来源： </label>
							<select class="form-control"  name="mediaType" style="display: inline-block; width: 50%;"/>  
								<option value="">-请选择-</option>
								<option value="0">腾讯</option>
								<option value="1">优酷</option>
								<option value="2">搜狐</option>
								<option value="3">土豆</option>
								<option value="4">酷6</option>
			                </select>
						</div>
						<div class="form-group">
							<label>视频链接地址 ：</label> 
							<input class="form-control"  name="mediaUrl" type="text">
						</div>
						<div class="form-group">
							<label>套餐链接地址 ：</label> 
							<input class="form-control"  name="packageUrl" type="text">
						</div>
					</form>
					<form action="" id="addMenuImg" method="post" enctype="multipart/form-data" >
				    	<div class="form-group" >
							<label class="control-label" for="form-input-readonly">
								<h4>视频图片</h4>
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
									<a href="<%=basePath%>assets/u210.png"  title="视频图片" data-rel="colorbox">
										<img width="150" height="150" alt="150x150"  src="<%=basePath%>assets/u210.png" />
										<div class="text">
											<div class="inner">视频图片 </div>
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
	$(function() { 
		getMenu();
		
	});
</script>
</body>
</html>