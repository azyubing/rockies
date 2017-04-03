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
				<span style="color: #669FC7;">标签设定</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">标签设定</h3>
				
					<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">酒店信息：</h3>
					  </div>
					  <div class="panel-body">
					  		<div class="row">
					  			<div class="col-md-4"><label>酒店名称：</label>XX酒店</div>
					  			<div class="col-md-4"><label>酒店英文名：</label>xx hotel</div>
					  			<div class="col-md-4"><label>酒店编号：</label>H0001</div>
					  		</div>
					 
					  </div>
					</div>
				
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
							<form class="">
								<div class="row">
									<div class="col-md-4">
										<label class=""> 标签名称： </label>
										<input type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									</div>
									<div class="col-md-8">
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
													新增
													<i class="ace-icon fa fa-plus icon-on-right bigger-110"></i>
												</button>&nbsp;&nbsp;&nbsp;
											</span>
										</div>
									</div>
									
								</div>
							</form>
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
			$('#updateMenu').modal('show');
        },
        'click #deleteBtn': function (e, value, row, index) {
			$.messager.confirm("提示", "是否确认删除:"+name, function() {
				shCircleLoaderLoading();
				shCircleLoaderDestroy();
			 });
        }
    };

function queryParams(params) {
	var param={
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
    		var url = "<%=basePath%>tag/tagAdminSearchList.html";
    		
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
                columns: [
                {
                    field: 'id',
                    title: '标签编号',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'name',
                    title: '标签名称',
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
			$('#addMenu').modal('show');
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
					<form role="form" action="<%=basePath%>addSiteMenu.html" method="post" id="addMenuForm">
						<div class="form-group">
							<label>标签名称 ：</label> 
							<input class="form-control" id="parentName" name="name" type="text">
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
					<form role="form" action="<%=basePath%>addSiteMenu.html" method="post" id="addMenuForm">
						<div class="form-group">
							<label>标签名称 ：</label> 
							<input class="form-control" id="parentName" name="name" type="text">
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