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
				<span style="color: #669FC7;">折扣设定</span>
			</li>
		</ul>
		<!-- /section:basics/content.searchbox -->
	</div>
<!-- /section:basics/content.breadcrumbs -->
<div class="page-content">
	<div class="page-content-area">
        <div class="row">
			<div class="col-md-12">
				<h3 class="header smaller lighter blue">折扣设定</h3>
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
									<label class=""> 折扣类型： </label>
									<input id="pid" value="${row.pid }" type="hidden" />
									<input id="ptype" value="${row.ptype }" type="hidden" />
									<select id="discountType" name="discountType" class="form-control" id="addCourseStatus" name="courseStatus" style="display: inline-block; width: 50%;"/>  
										<option value="">-请选择-</option>
										<option value="0">提前预定折扣</option>
										<option value="1">人数折扣</option>
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
        	window.location.href='<%=basePath%>discount/toUpdateDiscountAdmin.html?menu=0_1_2&pid='+row.pid+'&discountNo='+row.discountNo ;
        },
        'click #deleteBtn': function (e, value, row, index) {
			$.messager.confirm("提示", "是否确认删除", function() {
				shCircleLoaderLoading();

				$.ajax({
					type:"POST",
					url:"<%=basePath%>discount/deleteDiscountAdmin.html",
					data:{'pid': row.pid,'discountNo': row.discountNo},
					dataType: "html",
					success: function(json){
						$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>discount/discountAdminSearchList.html"});
						shCircleLoaderDestroy();
					}
				});
			 });
        }
    };

function queryParams(params) {
	var param={
			pid: $("#pid").val(),
			discountType: $("#discountType").val(),
			offset: params.offset,
			limit: params.limit
	};
	  return param;
}

function doSearch(){
    	
    		var url = "<%=basePath%>discount/discountAdminSearchList.html";
    		
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
                    field: 'discountType',
                    title: '折扣类型',
                    align: 'center',
                    valign: 'middle',
                    formatter: function(value,row,index){
                    	if(value == '0'){
                    		return "提前预定折扣";
                    	}else{
                    		return "人数折扣";
                    	}
                    }
                }, {
	                field: 'caculateType',
	                title: '折扣计算方式',
	                align: 'center',
	                valign: 'middle',
                    formatter: function(value,row,index){
                    	if(value == '0'){
                    		return "按总价折扣比例";
                    	}else if(value == '1'){
                    		return "按优惠XXX元/每人";
                    	}else{
                    		return "按照折扣绝对金额计算";
                    	}
                    }
	            }, {
                    field: 'discountRate',
                    title: '总价折扣率',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'discountApiece',
                    title: '每人优惠金额',
                    align: 'center',
                    valign: 'middle'
                }, {
                    field: 'discountAmounts',
                    title: '优惠绝对金额',
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
			window.location.href = '<%=basePath%>discount/toAddDiscountAdmin.html?menu=${menu}&pid=${row.pid }';
		});
		
		$("#seach").click(function(){
			$('#tableInfo').bootstrapTable('refresh',{url: "<%=basePath%>discount/discountAdminSearchList.html"});
		});

		$(document).keydown(function(event){
			if(event.keyCode == "13"){
				$('#tableInfo').bootstrapTable("refresh",{url:"<%=basePath%>discount/discountAdminSearchList.html"});
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