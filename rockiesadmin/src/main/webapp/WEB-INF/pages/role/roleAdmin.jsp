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
				<span style="color: #669FC7;">系统管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">角色管理</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">角色管理</h3>
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
									<label class=""> 角色名称： </label>
									<input type="text" class="form-control " id="roleName" placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
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
			'<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="roleBtn">',
				'<i class="glyphicon glyphicon-edit"></i> 配置权限',
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
		'click #roleBtn': function (e, value, row, index) {
			var roleId = row.roleId;
			setTimeout(function(){
				window.location.href="<%=basePath%>role/roleMenuList.html?menu=${menu}&roleId="+roleId;
			},500);
        },
        'click #detailBtn': function (e, value, row, index) {
        	$('#updateMenu').find("input[name='roleId']").val(row.roleId);
        	$('#updateMenu').find("input[name='roleStatus']").val(row.roleStatus);
        	$('#updateMenu').find("input[name='roleName']").val(row.roleName);
        	$('#updateMenu').find("input[name='roleNameEn']").val(row.roleNameEn);
			$('#updateMenu').modal('show');
        },
        'click #deleteBtn': function (e, value, row, index) {
        	var roleId = row.roleId;
        	var roleName = row.roleName;
			$.messager.confirm("提示", "是否确认删除:"+roleName, function() {
				shCircleLoaderLoading();
				shCircleLoaderDestroy();
				deleteRow(roleId);
			 });
        }
    };

function queryParams(params) {
	var param={
			roleName:$("#roleName").val(),
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
    		var url = "<%=basePath%>role/roleAdminSearchList.html";
    		
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
                    field: 'roleId',
                    title: '角色编号',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'roleName',
                    title: '角色名称',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'roleNameEn',
                    title: '角色英文名',
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
        	$('#addMenu').find("input[name='roleName']").val('');
        	$('#addMenu').find("input[name='roleNameEn']").val('');
			$('#addMenu').modal('show');
		});
		
		$('#addMenu').on('hide.bs.modal', function () {
			
		});
		
		$('#updateMenu').on('hide.bs.modal', function () {
			
		});
		
		$("#search").click(function(){
			$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>role/roleAdminSearchList.html"});
		});

		$(document).keydown(function(event){
			if(event.keyCode == "13"){
				$('#tableInfo').bootstrapTable("refresh",{url: "<%=basePath%>role/roleAdminSearchList.html"});
			}
    	});
	});

	function submitForm(){
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>role/saveOrUpdateroleAdmin.html',    
            data:$('#addForm').serialize(),    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("添加成功");
            		$('#addMenu').modal('hide');
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>role/roleAdminSearchList.html"});
            	}
            }    
        });
	}
	function updatesubmitForm(){
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>role/saveOrUpdateroleAdmin.html',    
            data:$('#updateForm').serialize(),    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("修改成功");
            		$('#updateMenu').modal('hide');
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>role/roleAdminSearchList.html"});
            	}
            }    
        });
	}
	
	
	function deleteRow(roleId){
		$.ajax({    
            type:'post',        
            url:'<%=basePath%>role/deleteroleAdmin.html',    
            data:{"roleId":roleId},    
            cache:false,    
            dataType:'text',    
            success:function(data){
            	if(data=='success'){
            		alert("删除成功");
            		$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>role/roleAdminSearchList.html"});
            	}
            }    
        });
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
					<form role="form" action="" method="post" id="addForm">
					<input type="hidden" name="roleStatus" value="1">
						<div class="form-group">
							<label>角色名称 ：</label> 
							<input class="form-control"  name="roleName" type="text">
						</div>
						<div class="form-group">
							<label>角色英文名称：</label> 
							<input class="form-control"  name="roleNameEn" type="text">
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
					<form role="form" action="" method="post" id="updateForm">
					<input type="hidden" name="roleId">
					<input type="hidden" name="roleStatus" >
						<div class="form-group">
							<label>角色名称 ：</label> 
							<input class="form-control"  name="roleName" type="text">
						</div>
						<div class="form-group">
							<label>角色英文名称：</label> 
							<input class="form-control"  name="roleNameEn" type="text">
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