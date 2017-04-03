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
				<span style="color: #669FC7;">${pTypeName }管理</span>
			</li>
			<li>
				<span style="color: #669FC7;">加价设定</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">加价设定</h3>
				<div class="panel panel-default">
					  <div class="panel-heading">
					    <h3 class="panel-title">${pTypeName }信息：</h3>
					  </div>
					  <div class="panel-body">
					  		<div class="row">
					  			<div class="col-md-4"><label>${pTypeName }名称：</label>${row.pname }</div>
					  			<div class="col-md-4"><label>${pTypeName }英文名：</label>${row.pname_en }</div>
					  			<div class="col-md-4"><label>${pTypeName }编号：</label>${row.pid }</div>
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
									<label class=""> 加价计算方式： </label>
									<input id="pid" value="${row.pid }" type="hidden" />
									<input id="ptype" value="${row.ptype }" type="hidden" />
									<select id="extraType" name="extraType" class="form-control" id="addCourseStatus" name="courseStatus" style="display: inline-block; width: 50%;"/>  
										<option value="">-请选择-</option>
										<option value="0">按总价加价比例计算</option>
										<option value="1">按加价XXX元/每人计算</option>
										<option value="2">按照加价绝对金额计算</option>
					                </select>
								</div>
								<div class="col-md-4">
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
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="update" id="detailBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 修改',
	        '</a>',
	        '<a class="edit margin-left-20" href="javascript:void(0)" title="delete" id="deleteBtn">',
	            '<i class="glyphicon glyphicon-edit"></i> 删除',
	        '</a>'
	    ].join('');
    
}


window.operateEvents = {
        'click #detailBtn': function (e, value, row, index) {
        	window.location.href='<%=basePath%>extraCost/toUpdateExtraCostAdmin.html?menu=0_1_2&pid='+row.pid+'&extraNo='+row.extraNo ;
        },
        'click #deleteBtn': function (e, value, row, index) {
			$.messager.confirm("提示", "是否确认删除", function() {
				shCircleLoaderLoading();

				$.ajax({
					type:"POST",
					url:"<%=basePath%>extraCost/deleteExtraCostAdmin.html",
					data:{'pid': row.pid,'extraNo': row.extraNo},
					dataType: "html",
					success: function(json){
						$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>extraCost/extraCostAdminSearchList.html"});
						shCircleLoaderDestroy();
					}
				});
			 });
        }
    };

function queryParams(params) {
	var param={
			pid: $("#pid").val(),
			extraType: $("#extraType").val(),
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
    		var url = "<%=basePath%>extraCost/extraCostAdminSearchList.html";
    		
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
	                field: 'extraType',
	                title: '加价计算方式',
	                align: 'center',
	                valign: 'middle',
                    formatter: function(value,row,index){
                    	if(value == '0'){
                    		return "按总价加价比例";
                    	}else if(value == '1'){
                    		return "按加价XXX元/每人";
                    	}else{
                    		return "按照加价绝对金额计算";
                    	}
                    }
	            }, {
                    field: 'extraRate',
                    title: '总价加价率',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'extraApiece',
                    title: '每人加价金额',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'extraAmounts',
                    title: '加价绝对金额',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'startdate',
                    title: '有效开始日期',
                    align: 'center',
                    valign: 'middle',
                    formatter: function(value,row,index){
                    	return value.substring(0,10);
                    }
                }, {
                    field: 'enddate',
                    title: '有效结束日期',
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
			window.location.href = '<%=basePath%>extraCost/toAddExtraCostAdmin.html?menu=${menu}&pid=${row.pid }';
		});
		
		$("#seach").click(function(){
			$('#tableInfo').bootstrapTable('refresh',{url:"<%=basePath%>extraCost/extraCostAdminSearchList.html"});
		});

		$(document).keydown(function(event){
			if(event.keyCode == "13"){
				$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>extraCost/extraCostAdminSearchList.html"});
			}
    	});
		
		$("#returnBtn").click(function(){
			setTimeout(function(){
				window.location.href = '<%=basePath%>${pTypeUrl}pid=${row.pid }&menu=${menu}';
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