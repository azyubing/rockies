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
				<span style="color: #669FC7;">娱乐管理</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">娱乐管理</h3>
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
									<label class=""> 娱乐名称： </label>
									<input type="text" class="form-control " id="pname" placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
								</div>
								<div class="col-md-4">
									<div class="input-group" >
										<span class="">
											<button id="seach" type="button" class="btn btn-purple btn-sm">
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
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Set Price" id="setDiscountBtn">',
				'<i class="glyphicon glyphicon-edit"></i> 折扣设定',
			'</a>',
			'<a class="edit margin-left-20" href="javascript:void(0)" title="Set Price" id="setExtraCostBtn">',
				'<i class="glyphicon glyphicon-edit"></i> 加价设定',
			'</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="add room" id="toSetEntPriceAdmin">',
	            '<i class="glyphicon glyphicon-edit"></i> 查看价格',
	        '</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="add room" id="toSetEntRouteDetailAdmin">',
	            '<i class="glyphicon glyphicon-edit"></i> 娱乐行程',
	        '</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="update" id="detailBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 修改',
	        '</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="delete" id="deleteBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 删除',
	        '</a>'
	    ].join('');
    
}


window.operateEvents = {
		'click #setDiscountBtn': function (e, value, row, index) {
        	window.location.href='<%=basePath%>discount/discountAdmin.html?menu=${menu}&pid='+row.pid;
        },
        'click #setExtraCostBtn': function (e, value, row, index) {
        	window.location.href='<%=basePath%>extraCost/extraCostAdmin.html?menu=${menu}&pid='+row.pid;
        },
        'click #toSetEntPriceAdmin': function (e, value, row, index) {
			window.location.href='<%=basePath%>ent/toSetEntPriceAdmin.html?menu=${menu}&pid='+row.pid;
        },
        'click #toSetEntRouteDetailAdmin': function (e, value, row, index) {
			window.location.href='<%=basePath%>ent/toSetEntRouteDetailAdmin.html?menu=${menu}&pid='+row.pid;
        },
        'click #detailBtn': function (e, value, row, index) {
        	window.location.href='<%=basePath%>ent/toUpdateEntAdmin.html?menu=${menu}&pid='+row.pid;
        },
        'click #deleteBtn': function (e, value, row, index) {
			$.messager.confirm("提示", "是否确认删除:"+row.pname, function() {
				shCircleLoaderLoading();

				$.ajax({
					type:"POST",
					url:"<%=basePath%>ent/deleteEntAdmin.html",
					data:{'pid': row.pid},
					dataType: "html",
					success: function(json){
						$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>ent/entAdminSearchList.html"});
						shCircleLoaderDestroy();
					}
				});
			 });
        }
    };

function queryParams(params) {
	var param={
			pname: $("#pname").val(),
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
    		var url = "<%=basePath%>ent/entAdminSearchList.html";
    		
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
                    field: 'pname',
                    title: '娱乐名称',
                    align: 'center',
                    valign: 'middle'
                }, {
	                field: 'pname_en',
	                title: '英文名',
	                align: 'center',
	                valign: 'middle'
	            }, {
	                field: 'pstatus',
	                title: '状态',
	                align: 'center',
	                valign: 'middle',
	                formatter:function(value){
	                	if(value == 0){
		                	return '<span class="label label-sm label-success arrowed">未上架</span>';
	                	}else if(value == 1){
	                		return '<span class="label label-sm label-warning arrowed">已上架</span>';
	                	}else{
	                		return '<span class="label label-sm label-info arrowed">暂停</span>';
	                	}
	                }
	            }
	            , {
	                field: 'cityName',
	                title: '所属国',
	                align: 'center',
	                valign: 'middle'
	            }
	            , {
	                field: 'ctdt',
	                title: '创建时间',
	                align: 'center',
	                valign: 'middle'
	            }
	            , {
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
			window.location.href = '<%=basePath%>ent/toAddEntAdmin.html?menu=${menu}';
		});
		
		$("#seach").click(function(){
			$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>ent/entAdminSearchList.html"});
		});

		$(document).keydown(function(event){
			if(event.keyCode == "13"){
				$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>ent/entAdminSearchList.html"});
			}
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