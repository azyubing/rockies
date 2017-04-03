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
				<span style="color: #669FC7;">商品管理</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">商品管理</h3>
				
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
									<label class=""> 商品名称： </label>
									<input type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
								</div>
								<div class="col-md-4">
									<label class=""> 商品类别： </label>
									<select class="form-control" id="addCourseStatus" name="courseStatus" style="display: inline-block; width: 50%;"/>  
										<option value="">-请选择-</option>
										<option value="0">套餐</option>
										<option value="1">景点</option>
										<option value="2">酒店</option>
										<option value="3">交通</option>
					                </select>
								</div>
								<div class="col-md-4">&nbsp;</div>
							</div>
							<br>
							<div class="row">
								<div class="col-md-4">
									<label class=""> &nbsp;&nbsp;&nbsp;&nbsp;供应商： </label>
									<input type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
								</div>
								<div class="col-md-4">
									<label class=""> 所属国家： </label>
									<select class="form-control" id="addCourseStatus" name="courseStatus" style="display: inline-block; width: 50%;"/>  
										<option value="">-请选择-</option>
										<option value="0">中国</option>
										<option value="1">泰国</option>
										<option value="2">英国</option>
										<option value="3">德国</option>
					                </select>
								</div>
								<div class="col-md-4">
									<div class="input-group" >
										<span class="">
											<button type="button" class="btn btn-purple btn-sm">
												查询
												<i class="ace-icon fa fa-search icon-on-right bigger-110"></i>
											</button>&nbsp;&nbsp;&nbsp;
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

function queryParams(params) {
	var param={
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
    		var url = "<%=basePath%>product/productAdminSearchList";
    		
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
                    field: 'id',
                    title: '分类编号',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'name',
                    title: '商品名称',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'type',
                    title: '商品类别',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'supplier',
                    title: '供应商',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'country',
                    title: '所属国家',
                    align: 'center',
                    valign: 'middle'
                }, {
		            field: 'pstatus',
		            title: '状态',
		            align: 'center',
		            valign: 'middle',
	                formatter:function(value,row,index){
	                	if(value == 0){
		                	return '<span class="label label-sm label-success arrowed">未上架</span>';
	                	}else if(value == 1){
	                		return '<span class="label label-sm label-warning arrowed">已上架</span>';
	                	}else{
	                		return '<span class="label label-sm label-info arrowed">暂停</span>';
	                	}
	                }
		        }, {
		            field: 'startdate',
		            title: '开始时间',
		            align: 'center',
		            valign: 'middle',
	                formatter:function(value,row,index){
	                	return value.substring(0,10)
	                }
		        }, {
		            field: 'enddate',
		            title: '结束时间',
		            align: 'center',
		            valign: 'middle',
	                formatter:function(value,row,index){
	                	return value.substring(0,10)
	                }
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
		
		
	});

	function submitForm(){
		
	}
	
	function updatesubmitForm(){
		
	}

</script>	
<script type="text/javascript">
	$(function() { 
		getMenu();
		
	});
</script>
</body>
</html>