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
				<span style="color: #669FC7;">套餐管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">套餐行程管理</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">套餐行程列表</h3>
				
					<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">套餐信息：</h3>
					  </div>
					  <div class="panel-body">
					  		<div class="row">
					  			<div class="col-md-4"><label>套餐名称：</label>${row.pname }</div>
					  			<div class="col-md-4"><label>套餐英文名：</label>${row.pname_en }</div>
					  			<div class="col-md-4"><label>套餐编号：</label>${row.pid }</div>
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
							<div class="row">
								<div class="col-md-4">
									<label class=""> 套餐行程标签： </label>
									<input id="tabs" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									<input id="pid" value="${row.pid }" type="hidden" />
								</div>
								<div class="col-md-8">
									<div class="input-group" >
										<span class="">
											<button id="seach" type="button" class="btn btn-purple btn-sm">
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
			'<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="toSetRouteDetailBtn">',
				'<i class="glyphicon glyphicon-edit"></i> 行程日详细',
			'</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="detailBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 修改',
	        '</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Delete" id="deleteBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 删除',
	        '</a>'
	    ].join('');
}


window.operateEvents = {
		'click #toSetRouteDetailBtn': function (e, value, row, index) {
        	window.location.href='<%=basePath%>setpack/toSetRouteDetailAdmin.html?menu=${menu}&pid='+row.pid+'&pday='+row.pday;
        },
        'click #detailBtn': function (e, value, row, index) {
        	window.location.href='<%=basePath%>setpack/toUpdateRouteDayAdmin.html?menu=${menu}&pid='+row.pid+'&pday='+row.pday;
        },
        'click #deleteBtn': function (e, value, row, index) {
			$.messager.confirm("提示", "是否确认删除:"+name, function() {
				shCircleLoaderLoading();
				$.ajax({
					type:"POST",
					url:"<%=basePath%>setpack/deleteRouteDayAdmin.html",
					data:{'pid': row.pid,'pday': row.pday },
					dataType: "html",
					success: function(json){
						$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>setpack/routeDayAdminSearchList.html"});
						shCircleLoaderDestroy();
					}
				});
			 });
        }
    };

function queryParams(params) {
	var param={
			pid: $("#pid").val(),
			tabs: $("#tabs").val(),
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
    		var url = "<%=basePath%>setpack/routeDayAdminSearchList.html";
    		
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
                    field: 'pday',
                    title: '第X日',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'tabs',
                    title: '标签',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'mapAddress',
                    title: '地图地址',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'mapLat',
                    title: '地图纬度',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'mapLng',
                    title: '地图经度',
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
			window.location.href='<%=basePath%>setpack/toAddRouteDayAdmin.html?menu=${menu}&pid=${row.pid}';
		});
		
		$("#seach").click(function(){
			$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>setpack/routeDayAdminSearchList.html"});
		});

		$(document).keydown(function(event){
			if(event.keyCode == "13"){
				$('#tableInfo').bootstrapTable("refresh",{url: "<%=basePath%>setpack/routeDayAdminSearchList.html"});
			}
    	});
		
		$("#returnBtn").click(function(){
			setTimeout(function(){
				window.location.href='<%=basePath%>setpack/packageAdmin.html?menu=${menu}';
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
	$(function() { 
		getMenu();
		
	});
</script>
</body>
</html>