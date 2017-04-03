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
				<span style="color: #669FC7;">评论管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">客户评论</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">客户评论</h3>
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
									<label class=""> 用户用户名： </label>
									<input type="text" class="form-control " id="customerName" placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
								</div>
								<div class="col-md-4">
									<label class=""> 商品名称： </label>
									<input type="text" class="form-control " id="searchproductName"  placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
								</div>
								<div class="col-md-4">
									<label class=""> 商品类别： </label>
									<select class="form-control" id="productType" style="display: inline-block; width: 50%;">  
										<option value="">-请选择-</option>
										<option value="0">套餐</option>
										<option value="1">酒店</option>
										<option value="2">别墅</option>
										<option value="3">交通</option>
										<option value="4">娱乐</option>
					                </select>
								</div>
								<div class="col-md-4">
									<label class=""> 评分区间： </label>
									<input type="text" placeholder="请输入内容" id="minCore" style="display: inline-block; width: 25%;"/>-
									<input type="text" placeholder="请输入内容" id="maxCore" style="display: inline-block; width:25%;"/>
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
        	var productId = row.pid;
        	var customerName = row.customerName;
        	var productName = row.pname;
        	var accountnumber = row.accountnumber; 
        	var core = row.core; 
        	var content = row.content; 
        	var userimg = row.userimg; 
        	$('#productName').val(productName);
        	$('#updateMenu').find("input[name='id']").val(id);
        	$('#updateMenu').find("input[name='productId']").val(productId);
        	$('#updateMenu').find("input[name='customerName']").val(customerName);
        	$('#updateMenu').find("input[name='accountnumber']").val(accountnumber);
        	$('#updateMenu').find("input[name='core']").val(core);
        	$('#updateMenu').find("textarea[name='content']").val(content);
        	$('#updateMenu').find("textarea[name='userimg']").val(userimg);
        	$('#updateMenu').modal('show');
        },
        'click #deleteBtn': function (e, value, row, index) {
        	var name = row.customerName+"对"+row.pname+"的品论";
        	var id = row.id;
			$.messager.confirm("提示", "是否确认删除:"+name, function() {
				shCircleLoaderLoading();
				shCircleLoaderDestroy();
				deleteForm(id);
			 });
        }
    };

function queryParams(params) {
	var param={
			customerName:$("#customerName").val(),
			productName:$("#searchproductName").val(),
			productType:$("#productType").val(),
			minCore:$("#minCore").val(),
			maxCore:$("#maxCore").val(),
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
    		var url = "<%=basePath%>evaluation/evaluationAdminSearchList.html";
    		
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
                    field: 'customerName',
                    title: '用户用户名',
                    align: 'center',
                    valign: 'middle'
                },{
                    field: 'accountnumber',
                    title: '帐号',
                    align: 'center',
                    valign: 'middle'
                }, {
	                field: 'core',
	                title: '打分',
	                align: 'center',
	                valign: 'middle'
	            }, {
	                field: 'ctdt',
	                title: '创建时间',
	                align: 'center',
	                valign: 'middle'
	            }, {
	                field: 'pname',
	                title: '商品名',
	                align: 'center',
	                valign: 'middle'
	            }, {
	                field: 'pid',
	                title: '商品编码',
	                align: 'center',
	                valign: 'middle'
	            }, {
	                field: 'content',
	                title: '评论内容',
	                align: 'center',
	                valign: 'middle'
	            }, {
	                field: 'userimg',
	                title: '用户头像',
	                align: 'left',
	                valign: 'middle'
	            },{
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
			setTimeout(function(){
				window.location.href="<%=basePath%>evaluation/evaluationProductAdmin.html?menu=${menu}";
			},500);
		});
		
		$("#search").click(function(){
			var minCore = $("#minCore").val();
			var maxCore = $("#maxCore").val();
			if(!isNull(minCore)){
				if(minCore.length>1){
					alert("请输入0-9的整数");
					return false;
				}
			}
			if(!isNull(maxCore)){
				if(maxCore.length>1){
					alert("请输入0-9的整数");
					return false;
				}
			}
			$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>evaluation/evaluationAdminSearchList.html"});
		});
		

		$(document).keydown(function(event){
			var minCore = $("#minCore").val();
			var maxCore = $("#maxCore").val();
			if(!isNull(minCore)){
				if(minCore.length>1){
					alert("请输入0-9的整数");
					return false;
				}
			}
			if(!isNull(maxCore)){
				if(maxCore.length>1){
					alert("请输入0-9的整数");
					return false;
				}
			}
			
			if(event.keyCode == "13"){
				$('#tableInfo').bootstrapTable("refresh",{url: "<%=basePath%>evaluation/evaluationAdminSearchList.html"});
			}
    	});
		
	});
	function deleteForm(id){
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>evaluation/deleteEvaluation.html',    
            data:{"id":id},    
            cache:false,    
            dataType:'text', 
            success:function(data){
            	if(data=='success'){
            		alert("删除成功");
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>evaluation/evaluationAdminSearchList.html"});
            	}
            }    
        });
	}
	
	function submitUpdateForm(){
		var core = $('#updateMenu').find("input[name='core']").val();
		var content = $('#updateMenu').find("textarea[name='content']").val();
		if(isNull(core)){
			alert("请打分");
			return false;
		}
		if(core.length>1||!isInteger(core)){
			alert("请输入0-9的整数");
			return false;
		}
		if(isNull(content)){
			alert("请填写评论内容");
			return false;
		}
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>evaluation/saveOrUpdateEvaluation.html',    
            data:$("#updateMenuForm").serialize(),    
            cache:false,    
            dataType:'text', 
            success:function(data){
            	if(data=='success'){
            		alert("修改成功");
            		$('#updateMenu').modal('hide');
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>evaluation/evaluationAdminSearchList.html"});
            	}
            }    
        });
	}
	
</script>	

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
					<form role="form" action="" method="post" id="updateMenuForm">
						<input type="hidden" name="id">
						<input type="hidden" name="productId">
						<div class="form-group">
							<label>选择的商品 ：</label> 
							<input class="form-control" id="productName" type="text" readonly="readonly">
						</div>
						<div class="form-group">
							<label>用户用户名 ：</label> 
							<input class="form-control" name="customerName" type="text">
						</div>
						<div class="form-group">
							<label>账号 ：</label> 
							<input class="form-control" name="accountnumber" type="text">
						</div>
						<div class="form-group">
							<label>打分 ：</label> 
							<input class="form-control"  name="core" type="text">
						</div>
						<div class="form-group">
							<label>评论内容 ：</label> 
							<textarea class="form-control" name="content" id="form-field-8" placeholder="Default Text" ></textarea>
						</div>
						<input type="hidden" id="userimg" name="userimg" value=""/> 
					</form>
					<form action="" id="addMenuImg" method="post" enctype="multipart/form-data" >
				    	<div class="form-group" >
							<label class="control-label" for="form-input-readonly">
								<h4>头像图片</h4>
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
											<div class="inner">用户头像 </div>
										</div>
									</a>
								</li>
							</ul>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button class="btn btn-info" type="button" id="updateBtn" onclick="submitUpdateForm();">
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
<script type="text/javascript">
	$(function() { 
		getMenu();
		// 图片上传
		$(".licenceDocurlImgBtn").click(function(){
			var form = $(this).parents("form");
			var imgpath = $(form).find("input[name='uploadList']");
			if(imgpath[0].files.length >1){
				alert("只能选择1张图片");
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
                	("#userimg").val(json.data);
                } 
            });
		});
		
	});
	
	function setImage(obj){
		var path = URL.createObjectURL(obj.files[0]);
		$(obj).parent().parent().parent().find("img").attr("src",path);
	}
</script>
</body>
</html>