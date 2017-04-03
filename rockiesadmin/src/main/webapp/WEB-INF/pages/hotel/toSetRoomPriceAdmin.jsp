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
				<span style="color: #669FC7;">房型列表</span>
			</li>
			<li>
				<span style="color: #669FC7;">房型价格列表</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">房型价格列表</h3>
				
					<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">房型信息：</h3>
					  </div>
					  <div class="panel-body">
					  		<div class="row">
					  			<div class="col-md-4"><label>房型编号：</label>${row.roomType  }</div>
					  			<div class="col-md-4"><label>房型名称：</label>${row.roomTypeName }</div>
					  			<div class="col-md-4"><label>房型设施：</label>${row.roomInfra  }</div>
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
									<label class=""> 房型价格名称： </label>
									<input id="priceItem" type="text" class="form-control " placeholder="请输入内容" style="display: inline-block; width: 50%;"/>
									<input id="pid" value="${row.pid }" type="hidden" />
									<input id="roomType" value="${row.roomType }" type="hidden" />
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

	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Edit" id="detailBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 价格设定',
	        '</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="Delete" id="deleteBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 删除',
	        '</a>'
	    ].join('');
}


window.operateEvents = {
        'click #detailBtn': function (e, value, row, index) {
        	window.location.href='<%=basePath%>hotel/toUpdateRoomPriceAdmin.html?menu=${menu}&pid='+row.pid+'&roomType='+row.roomType+'&id='+row.id;
        },
        'click #deleteBtn': function (e, value, row, index) {
			$.messager.confirm("提示", "是否确认删除:"+name, function() {
				shCircleLoaderLoading();
				$.ajax({
					type:"POST",
					url:"<%=basePath%>hotel/deleteRoomPriceAdmin.html",
					data:{'pid': row.pid,'roomType': row.roomType,'id': row.id},
					dataType: "html",
					success: function(json){
						$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>hotel/setRoomPriceAdminSearchList.html"});
						shCircleLoaderDestroy();
					}
				});
			 });
        }
    };

function queryParams(params) {
	var param={
			pid: $("#pid").val(),
			roomType: $("#roomType").val(),
			priceItem: $("#priceItem").val(),
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
    		var url = "<%=basePath%>hotel/setRoomPriceAdminSearchList.html";
    		
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
                    field: 'priceItem',
                    title: '价格项',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'priceGroup',
                    title: '价格项分组',
                    align: 'center',
                    valign: 'middle',
                    formatter: function(value,row,index){
                    	if(value == 0){
                    		return "主价格项";
                    	}else{
                    		return "附加价格项";
                    	}
                    	
                    }
                }, {
                    field: 'roomPrice',
                    title: '房费',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'prepay',
                    title: '预付款',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'orderNum',
                    title: '排序',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'starttime',
                    title: '有效开始时间',
                    align: 'center',
                    valign: 'middle',
                    formatter: function(value,row,index){
                    	return value.substring(0,10);
                    }
                }, {
                    field: 'enddate',
                    title: '价格结束时间',
                    align: 'center',
                    valign: 'middle',
                    formatter: function(value,row,index){
                    	return value.substring(0,10);
                    }
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
			window.location.href='<%=basePath%>hotel/toAddRoomPriceAdmin.html?menu=${menu}&pid=${row.pid}&roomType=${row.roomType}';
		});
		
		$("#seach").click(function(){
			$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>hotel/setRoomPriceAdminSearchList.html"});
		});

		$(document).keydown(function(event){
			if(event.keyCode == "13"){
				$('#tableInfo').bootstrapTable("refresh",{url: "<%=basePath%>hotel/setRoomPriceAdminSearchList.html"});
			}
    	});
		
		$("#returnBtn").click(function(){
			setTimeout(function(){
				window.location.href='<%=basePath%>/hotel/toSetRoomAdmin.html?menu=${menu}&pid=${row.pid}';
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